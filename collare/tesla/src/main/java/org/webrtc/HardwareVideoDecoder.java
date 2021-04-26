package org.webrtc;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.SystemClock;
import android.view.Surface;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import org.webrtc.EglBase;
import org.webrtc.EncodedImage;
import org.webrtc.ThreadUtils;
import org.webrtc.VideoDecoder;
import org.webrtc.VideoFrame;

@TargetApi(16)
class HardwareVideoDecoder implements VideoDecoder, VideoSink {
    private static final int DEQUEUE_INPUT_TIMEOUT_US = 500000;
    private static final int DEQUEUE_OUTPUT_BUFFER_TIMEOUT_US = 100000;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final String MEDIA_FORMAT_KEY_CROP_BOTTOM = "crop-bottom";
    private static final String MEDIA_FORMAT_KEY_CROP_LEFT = "crop-left";
    private static final String MEDIA_FORMAT_KEY_CROP_RIGHT = "crop-right";
    private static final String MEDIA_FORMAT_KEY_CROP_TOP = "crop-top";
    private static final String MEDIA_FORMAT_KEY_SLICE_HEIGHT = "slice-height";
    private static final String MEDIA_FORMAT_KEY_STRIDE = "stride";
    private static final String TAG = "HardwareVideoDecoder";
    private VideoDecoder.Callback callback;
    private MediaCodec codec = null;
    private final String codecName;
    private final VideoCodecType codecType;
    private int colorFormat;
    private ThreadUtils.ThreadChecker decoderThreadChecker;
    private final Object dimensionLock = new Object();
    private final BlockingDeque<FrameInfo> frameInfos;
    private boolean hasDecodedFirstFrame;
    private int height;
    private boolean keyFrameRequired;
    private Thread outputThread;
    private ThreadUtils.ThreadChecker outputThreadChecker;
    private DecodedTextureMetadata renderedTextureMetadata;
    private final Object renderedTextureMetadataLock = new Object();
    private volatile boolean running = false;
    private final EglBase.Context sharedContext;
    private volatile Exception shutdownException = null;
    private int sliceHeight;
    private int stride;
    private Surface surface = null;
    private SurfaceTextureHelper surfaceTextureHelper;
    private int width;

    @Override // org.webrtc.VideoDecoder
    @CalledByNative
    public /* synthetic */ long createNativeVideoDecoder() {
        return VideoDecoder.CC.$default$createNativeVideoDecoder(this);
    }

    @Override // org.webrtc.VideoDecoder
    public String getImplementationName() {
        return "HWDecoder";
    }

    @Override // org.webrtc.VideoDecoder
    public boolean getPrefersLateDecoding() {
        return true;
    }

    /* access modifiers changed from: private */
    public static class FrameInfo {
        final long decodeStartTimeMs;
        final int rotation;

        FrameInfo(long j, int i) {
            this.decodeStartTimeMs = j;
            this.rotation = i;
        }
    }

    /* access modifiers changed from: private */
    public static class DecodedTextureMetadata {
        final Integer decodeTimeMs;
        final long presentationTimestampUs;

        DecodedTextureMetadata(long j, Integer num) {
            this.presentationTimestampUs = j;
            this.decodeTimeMs = num;
        }
    }

    HardwareVideoDecoder(String str, VideoCodecType videoCodecType, int i, EglBase.Context context) {
        if (isSupportedColorFormat(i)) {
            this.codecName = str;
            this.codecType = videoCodecType;
            this.colorFormat = i;
            this.sharedContext = context;
            this.frameInfos = new LinkedBlockingDeque();
            return;
        }
        throw new IllegalArgumentException("Unsupported color format: " + i);
    }

    @Override // org.webrtc.VideoDecoder
    public VideoCodecStatus initDecode(VideoDecoder.Settings settings, VideoDecoder.Callback callback2) {
        this.decoderThreadChecker = new ThreadUtils.ThreadChecker();
        this.callback = callback2;
        EglBase.Context context = this.sharedContext;
        if (context != null) {
            this.surfaceTextureHelper = SurfaceTextureHelper.create("decoder-texture-thread", context);
            this.surface = new Surface(this.surfaceTextureHelper.getSurfaceTexture());
            this.surfaceTextureHelper.startListening(this);
        }
        return initDecodeInternal(settings.width, settings.height);
    }

    private VideoCodecStatus initDecodeInternal(int i, int i2) {
        this.decoderThreadChecker.checkIsOnValidThread();
        Logging.d(TAG, "initDecodeInternal");
        if (this.outputThread != null) {
            Logging.e(TAG, "initDecodeInternal called while the codec is already running");
            return VideoCodecStatus.FALLBACK_SOFTWARE;
        }
        this.width = i;
        this.height = i2;
        this.stride = i;
        this.sliceHeight = i2;
        this.hasDecodedFirstFrame = false;
        this.keyFrameRequired = true;
        try {
            this.codec = MediaCodec.createByCodecName(this.codecName);
            try {
                MediaFormat createVideoFormat = MediaFormat.createVideoFormat(this.codecType.mimeType(), i, i2);
                if (this.sharedContext == null) {
                    createVideoFormat.setInteger("color-format", this.colorFormat);
                }
                this.codec.configure(createVideoFormat, this.surface, (MediaCrypto) null, 0);
                this.codec.start();
                this.running = true;
                this.outputThread = createOutputThread();
                this.outputThread.start();
                Logging.d(TAG, "initDecodeInternal done");
                return VideoCodecStatus.OK;
            } catch (IllegalStateException e) {
                Logging.e(TAG, "initDecode failed", e);
                release();
                return VideoCodecStatus.FALLBACK_SOFTWARE;
            }
        } catch (IOException | IllegalArgumentException unused) {
            Logging.e(TAG, "Cannot create media decoder " + this.codecName);
            return VideoCodecStatus.FALLBACK_SOFTWARE;
        }
    }

    @Override // org.webrtc.VideoDecoder
    public VideoCodecStatus decode(EncodedImage encodedImage, VideoDecoder.DecodeInfo decodeInfo) {
        int i;
        int i2;
        VideoCodecStatus reinitDecode;
        this.decoderThreadChecker.checkIsOnValidThread();
        boolean z = false;
        if (this.codec == null || this.callback == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("decode uninitalized, codec: ");
            if (this.codec != null) {
                z = true;
            }
            sb.append(z);
            sb.append(", callback: ");
            sb.append(this.callback);
            Logging.d(TAG, sb.toString());
            return VideoCodecStatus.UNINITIALIZED;
        } else if (encodedImage.buffer == null) {
            Logging.e(TAG, "decode() - no input data");
            return VideoCodecStatus.ERR_PARAMETER;
        } else {
            int remaining = encodedImage.buffer.remaining();
            if (remaining == 0) {
                Logging.e(TAG, "decode() - input buffer empty");
                return VideoCodecStatus.ERR_PARAMETER;
            }
            synchronized (this.dimensionLock) {
                i = this.width;
                i2 = this.height;
            }
            if (encodedImage.encodedWidth * encodedImage.encodedHeight > 0 && ((encodedImage.encodedWidth != i || encodedImage.encodedHeight != i2) && (reinitDecode = reinitDecode(encodedImage.encodedWidth, encodedImage.encodedHeight)) != VideoCodecStatus.OK)) {
                return reinitDecode;
            }
            if (this.keyFrameRequired) {
                if (encodedImage.frameType != EncodedImage.FrameType.VideoFrameKey) {
                    Logging.e(TAG, "decode() - key frame required first");
                    return VideoCodecStatus.NO_OUTPUT;
                } else if (!encodedImage.completeFrame) {
                    Logging.e(TAG, "decode() - complete frame required first");
                    return VideoCodecStatus.NO_OUTPUT;
                }
            }
            try {
                int dequeueInputBuffer = this.codec.dequeueInputBuffer(500000);
                if (dequeueInputBuffer < 0) {
                    Logging.e(TAG, "decode() - no HW buffers available; decoder falling behind");
                    return VideoCodecStatus.ERROR;
                }
                try {
                    ByteBuffer byteBuffer = this.codec.getInputBuffers()[dequeueInputBuffer];
                    if (byteBuffer.capacity() < remaining) {
                        Logging.e(TAG, "decode() - HW buffer too small");
                        return VideoCodecStatus.ERROR;
                    }
                    byteBuffer.put(encodedImage.buffer);
                    this.frameInfos.offer(new FrameInfo(SystemClock.elapsedRealtime(), encodedImage.rotation));
                    try {
                        this.codec.queueInputBuffer(dequeueInputBuffer, 0, remaining, TimeUnit.NANOSECONDS.toMicros(encodedImage.captureTimeNs), 0);
                        if (this.keyFrameRequired) {
                            this.keyFrameRequired = false;
                        }
                        return VideoCodecStatus.OK;
                    } catch (IllegalStateException e) {
                        Logging.e(TAG, "queueInputBuffer failed", e);
                        this.frameInfos.pollLast();
                        return VideoCodecStatus.ERROR;
                    }
                } catch (IllegalStateException e2) {
                    Logging.e(TAG, "getInputBuffers failed", e2);
                    return VideoCodecStatus.ERROR;
                }
            } catch (IllegalStateException e3) {
                Logging.e(TAG, "dequeueInputBuffer failed", e3);
                return VideoCodecStatus.ERROR;
            }
        }
    }

    @Override // org.webrtc.VideoDecoder
    public VideoCodecStatus release() {
        Logging.d(TAG, "release");
        VideoCodecStatus releaseInternal = releaseInternal();
        Surface surface2 = this.surface;
        if (surface2 != null) {
            surface2.release();
            this.surface = null;
            this.surfaceTextureHelper.stopListening();
            this.surfaceTextureHelper.dispose();
            this.surfaceTextureHelper = null;
        }
        synchronized (this.renderedTextureMetadataLock) {
            this.renderedTextureMetadata = null;
        }
        this.callback = null;
        this.frameInfos.clear();
        return releaseInternal;
    }

    private VideoCodecStatus releaseInternal() {
        if (!this.running) {
            Logging.d(TAG, "release: Decoder is not running.");
            return VideoCodecStatus.OK;
        }
        try {
            this.running = false;
            if (!ThreadUtils.joinUninterruptibly(this.outputThread, 5000)) {
                Logging.e(TAG, "Media decoder release timeout", new RuntimeException());
                return VideoCodecStatus.TIMEOUT;
            } else if (this.shutdownException != null) {
                Logging.e(TAG, "Media decoder release error", new RuntimeException(this.shutdownException));
                this.shutdownException = null;
                VideoCodecStatus videoCodecStatus = VideoCodecStatus.ERROR;
                this.codec = null;
                this.outputThread = null;
                return videoCodecStatus;
            } else {
                this.codec = null;
                this.outputThread = null;
                return VideoCodecStatus.OK;
            }
        } finally {
            this.codec = null;
            this.outputThread = null;
        }
    }

    private VideoCodecStatus reinitDecode(int i, int i2) {
        this.decoderThreadChecker.checkIsOnValidThread();
        VideoCodecStatus releaseInternal = releaseInternal();
        if (releaseInternal != VideoCodecStatus.OK) {
            return releaseInternal;
        }
        return initDecodeInternal(i, i2);
    }

    private Thread createOutputThread() {
        return new Thread("HardwareVideoDecoder.outputThread") {
            /* class org.webrtc.HardwareVideoDecoder.AnonymousClass1 */

            public void run() {
                HardwareVideoDecoder.this.outputThreadChecker = new ThreadUtils.ThreadChecker();
                while (HardwareVideoDecoder.this.running) {
                    HardwareVideoDecoder.this.deliverDecodedFrame();
                }
                HardwareVideoDecoder.this.releaseCodecOnOutputThread();
            }
        };
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void deliverDecodedFrame() {
        this.outputThreadChecker.checkIsOnValidThread();
        try {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            int dequeueOutputBuffer = this.codec.dequeueOutputBuffer(bufferInfo, 100000);
            if (dequeueOutputBuffer == -2) {
                reformat(this.codec.getOutputFormat());
            } else if (dequeueOutputBuffer < 0) {
                Logging.v(TAG, "dequeueOutputBuffer returned " + dequeueOutputBuffer);
            } else {
                FrameInfo poll = this.frameInfos.poll();
                Integer num = null;
                int i = 0;
                if (poll != null) {
                    num = Integer.valueOf((int) (SystemClock.elapsedRealtime() - poll.decodeStartTimeMs));
                    i = poll.rotation;
                }
                this.hasDecodedFirstFrame = true;
                if (this.surfaceTextureHelper != null) {
                    deliverTextureFrame(dequeueOutputBuffer, bufferInfo, i, num);
                } else {
                    deliverByteFrame(dequeueOutputBuffer, bufferInfo, i, num);
                }
            }
        } catch (IllegalStateException e) {
            Logging.e(TAG, "deliverDecodedFrame failed", e);
        }
    }

    private void deliverTextureFrame(int i, MediaCodec.BufferInfo bufferInfo, int i2, Integer num) {
        int i3;
        int i4;
        synchronized (this.dimensionLock) {
            i3 = this.width;
            i4 = this.height;
        }
        synchronized (this.renderedTextureMetadataLock) {
            if (this.renderedTextureMetadata == null) {
                this.surfaceTextureHelper.setTextureSize(i3, i4);
                this.surfaceTextureHelper.setFrameRotation(i2);
                this.renderedTextureMetadata = new DecodedTextureMetadata(bufferInfo.presentationTimeUs, num);
                this.codec.releaseOutputBuffer(i, true);
            }
        }
    }

    @Override // org.webrtc.VideoSink
    public void onFrame(VideoFrame videoFrame) {
        long j;
        int intValue;
        synchronized (this.renderedTextureMetadataLock) {
            if (this.renderedTextureMetadata != null) {
                j = this.renderedTextureMetadata.presentationTimestampUs * 1000;
                intValue = this.renderedTextureMetadata.decodeTimeMs.intValue();
                this.renderedTextureMetadata = null;
            } else {
                throw new IllegalStateException("Rendered texture metadata was null in onTextureFrameAvailable.");
            }
        }
        this.callback.onDecodedFrame(new VideoFrame(videoFrame.getBuffer(), videoFrame.getRotation(), j), Integer.valueOf(intValue), null);
    }

    private void deliverByteFrame(int i, MediaCodec.BufferInfo bufferInfo, int i2, Integer num) {
        int i3;
        int i4;
        int i5;
        int i6;
        VideoFrame.Buffer buffer;
        synchronized (this.dimensionLock) {
            i3 = this.width;
            i4 = this.height;
            i5 = this.stride;
            i6 = this.sliceHeight;
        }
        if (bufferInfo.size < ((i3 * i4) * 3) / 2) {
            Logging.e(TAG, "Insufficient output buffer size: " + bufferInfo.size);
            return;
        }
        int i7 = (bufferInfo.size >= ((i5 * i4) * 3) / 2 || i6 != i4 || i5 <= i3) ? i5 : (bufferInfo.size * 2) / (i4 * 3);
        ByteBuffer byteBuffer = this.codec.getOutputBuffers()[i];
        byteBuffer.position(bufferInfo.offset);
        byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
        ByteBuffer slice = byteBuffer.slice();
        if (this.colorFormat == 19) {
            buffer = copyI420Buffer(slice, i7, i6, i3, i4);
        } else {
            buffer = copyNV12ToI420Buffer(slice, i7, i6, i3, i4);
        }
        this.codec.releaseOutputBuffer(i, false);
        VideoFrame videoFrame = new VideoFrame(buffer, i2, bufferInfo.presentationTimeUs * 1000);
        this.callback.onDecodedFrame(videoFrame, num, null);
        videoFrame.release();
    }

    private VideoFrame.Buffer copyNV12ToI420Buffer(ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
        return new NV12Buffer(i3, i4, i, i2, byteBuffer, null).toI420();
    }

    private VideoFrame.Buffer copyI420Buffer(ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
        if (i % 2 == 0) {
            int i5 = (i3 + 1) / 2;
            int i6 = i2 % 2;
            int i7 = i6 == 0 ? (i4 + 1) / 2 : i4 / 2;
            int i8 = i / 2;
            int i9 = (i * i2) + 0;
            int i10 = i8 * i7;
            int i11 = i9 + ((i8 * i2) / 2);
            int i12 = i11 + i10;
            JavaI420Buffer allocate = JavaI420Buffer.allocate(i3, i4);
            byteBuffer.limit((i * i4) + 0);
            byteBuffer.position(0);
            YuvHelper.copyPlane(byteBuffer.slice(), i, allocate.getDataY(), allocate.getStrideY(), i3, i4);
            byteBuffer.limit(i9 + i10);
            byteBuffer.position(i9);
            YuvHelper.copyPlane(byteBuffer.slice(), i8, allocate.getDataU(), allocate.getStrideU(), i5, i7);
            if (i6 == 1) {
                byteBuffer.position(i9 + ((i7 - 1) * i8));
                ByteBuffer dataU = allocate.getDataU();
                dataU.position(allocate.getStrideU() * i7);
                dataU.put(byteBuffer);
            }
            byteBuffer.limit(i12);
            byteBuffer.position(i11);
            YuvHelper.copyPlane(byteBuffer.slice(), i8, allocate.getDataV(), allocate.getStrideV(), i5, i7);
            if (i6 == 1) {
                byteBuffer.position(i11 + (i8 * (i7 - 1)));
                ByteBuffer dataV = allocate.getDataV();
                dataV.position(allocate.getStrideV() * i7);
                dataV.put(byteBuffer);
            }
            return allocate;
        }
        throw new AssertionError("Stride is not divisible by two: " + i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00b7, code lost:
        if (r5.surfaceTextureHelper != null) goto L_0x0109;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00bf, code lost:
        if (r6.containsKey("color-format") == false) goto L_0x0109;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00c1, code lost:
        r5.colorFormat = r6.getInteger("color-format");
        org.webrtc.Logging.d(org.webrtc.HardwareVideoDecoder.TAG, "Color: 0x" + java.lang.Integer.toHexString(r5.colorFormat));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00eb, code lost:
        if (isSupportedColorFormat(r5.colorFormat) != false) goto L_0x0109;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00ed, code lost:
        stopOnOutputThread(new java.lang.IllegalStateException("Unsupported color format: " + r5.colorFormat));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0108, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0109, code lost:
        r0 = r5.dimensionLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x010b, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0112, code lost:
        if (r6.containsKey(org.webrtc.HardwareVideoDecoder.MEDIA_FORMAT_KEY_STRIDE) == false) goto L_0x011c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0114, code lost:
        r5.stride = r6.getInteger(org.webrtc.HardwareVideoDecoder.MEDIA_FORMAT_KEY_STRIDE);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0122, code lost:
        if (r6.containsKey(org.webrtc.HardwareVideoDecoder.MEDIA_FORMAT_KEY_SLICE_HEIGHT) == false) goto L_0x012c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0124, code lost:
        r5.sliceHeight = r6.getInteger(org.webrtc.HardwareVideoDecoder.MEDIA_FORMAT_KEY_SLICE_HEIGHT);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x012c, code lost:
        org.webrtc.Logging.d(org.webrtc.HardwareVideoDecoder.TAG, "Frame stride and slice height: " + r5.stride + " x " + r5.sliceHeight);
        r5.stride = java.lang.Math.max(r5.width, r5.stride);
        r5.sliceHeight = java.lang.Math.max(r5.height, r5.sliceHeight);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0162, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0163, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void reformat(android.media.MediaFormat r6) {
        /*
        // Method dump skipped, instructions count: 362
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.HardwareVideoDecoder.reformat(android.media.MediaFormat):void");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void releaseCodecOnOutputThread() {
        this.outputThreadChecker.checkIsOnValidThread();
        Logging.d(TAG, "Releasing MediaCodec on output thread");
        try {
            this.codec.stop();
        } catch (Exception e) {
            Logging.e(TAG, "Media decoder stop failed", e);
        }
        try {
            this.codec.release();
        } catch (Exception e2) {
            Logging.e(TAG, "Media decoder release failed", e2);
            this.shutdownException = e2;
        }
        Logging.d(TAG, "Release on output thread done");
    }

    private void stopOnOutputThread(Exception exc) {
        this.outputThreadChecker.checkIsOnValidThread();
        this.running = false;
        this.shutdownException = exc;
    }

    private boolean isSupportedColorFormat(int i) {
        for (int i2 : MediaCodecUtils.DECODER_COLOR_FORMATS) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }
}
