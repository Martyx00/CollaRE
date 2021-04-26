package org.webrtc;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.os.SystemClock;
import android.view.Surface;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.webrtc.EglBase;
import org.webrtc.VideoDecoderFactory;
import org.webrtc.VideoFrame;

@Deprecated
public class MediaCodecVideoDecoder {
    private static final int COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m = 2141391876;
    private static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar16m4ka = 2141391874;
    private static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar32m4ka = 2141391873;
    private static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar64x32Tile2m8ka = 2141391875;
    private static final int DEQUEUE_INPUT_TIMEOUT = 500000;
    private static final String FORMAT_KEY_CROP_BOTTOM = "crop-bottom";
    private static final String FORMAT_KEY_CROP_LEFT = "crop-left";
    private static final String FORMAT_KEY_CROP_RIGHT = "crop-right";
    private static final String FORMAT_KEY_CROP_TOP = "crop-top";
    private static final String FORMAT_KEY_SLICE_HEIGHT = "slice-height";
    private static final String FORMAT_KEY_STRIDE = "stride";
    private static final String H264_MIME_TYPE = "video/avc";
    private static final long MAX_DECODE_TIME_MS = 200;
    private static final int MAX_QUEUED_OUTPUTBUFFERS = 3;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final String TAG = "MediaCodecVideoDecoder";
    private static final String VP8_MIME_TYPE = "video/x-vnd.on2.vp8";
    private static final String VP9_MIME_TYPE = "video/x-vnd.on2.vp9";
    private static int codecErrors = 0;
    private static EglBase eglBase = null;
    private static MediaCodecVideoDecoderErrorCallback errorCallback = null;
    private static Set<String> hwDecoderDisabledTypes = new HashSet();
    private static MediaCodecVideoDecoder runningInstance = null;
    private static final List<Integer> supportedColorList = Arrays.asList(19, 21, 2141391872, Integer.valueOf((int) COLOR_QCOM_FORMATYVU420PackedSemiPlanar32m4ka), Integer.valueOf((int) COLOR_QCOM_FORMATYVU420PackedSemiPlanar16m4ka), Integer.valueOf((int) COLOR_QCOM_FORMATYVU420PackedSemiPlanar64x32Tile2m8ka), Integer.valueOf((int) COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m));
    private static final String supportedExynosH264HighProfileHwCodecPrefix = "OMX.Exynos.";
    private static final String supportedMediaTekH264HighProfileHwCodecPrefix = "OMX.MTK.";
    private static final String supportedQcomH264HighProfileHwCodecPrefix = "OMX.qcom.";
    private static final String[] supportedVp9HwCodecPrefixes = {supportedQcomH264HighProfileHwCodecPrefix, supportedExynosH264HighProfileHwCodecPrefix};
    private int colorFormat;
    private final Queue<TimeStamps> decodeStartTimeMs = new ArrayDeque();
    private final Queue<DecodedOutputBuffer> dequeuedSurfaceOutputBuffers = new ArrayDeque();
    private int droppedFrames;
    private boolean hasDecodedFirstFrame;
    private int height;
    private ByteBuffer[] inputBuffers;
    private MediaCodec mediaCodec;
    private Thread mediaCodecThread;
    private ByteBuffer[] outputBuffers;
    private int sliceHeight;
    private int stride;
    private Surface surface = null;
    private TextureListener textureListener;
    private int width;

    public interface MediaCodecVideoDecoderErrorCallback {
        void onMediaCodecVideoDecoderCriticalError(int i);
    }

    /* access modifiers changed from: private */
    public static native long nativeCreateDecoder(String str, boolean z);

    public static VideoDecoderFactory createFactory() {
        return new DefaultVideoDecoderFactory(new HwDecoderFactory());
    }

    /* access modifiers changed from: package-private */
    public static class HwDecoderFactory implements VideoDecoderFactory {
        private final VideoCodecInfo[] supportedHardwareCodecs = getSupportedHardwareCodecs();

        @Override // org.webrtc.VideoDecoderFactory
        @Deprecated
        public /* synthetic */ VideoDecoder createDecoder(String str) {
            return VideoDecoderFactory.CC.$default$createDecoder(this, str);
        }

        HwDecoderFactory() {
        }

        private static boolean isSameCodec(VideoCodecInfo videoCodecInfo, VideoCodecInfo videoCodecInfo2) {
            if (!videoCodecInfo.name.equalsIgnoreCase(videoCodecInfo2.name)) {
                return false;
            }
            if (videoCodecInfo.name.equalsIgnoreCase("H264")) {
                return H264Utils.isSameH264Profile(videoCodecInfo.params, videoCodecInfo2.params);
            }
            return true;
        }

        private static boolean isCodecSupported(VideoCodecInfo[] videoCodecInfoArr, VideoCodecInfo videoCodecInfo) {
            for (VideoCodecInfo videoCodecInfo2 : videoCodecInfoArr) {
                if (isSameCodec(videoCodecInfo2, videoCodecInfo)) {
                    return true;
                }
            }
            return false;
        }

        private static VideoCodecInfo[] getSupportedHardwareCodecs() {
            ArrayList arrayList = new ArrayList();
            if (MediaCodecVideoDecoder.isVp8HwSupported()) {
                Logging.d(MediaCodecVideoDecoder.TAG, "VP8 HW Decoder supported.");
                arrayList.add(new VideoCodecInfo("VP8", new HashMap()));
            }
            if (MediaCodecVideoDecoder.isVp9HwSupported()) {
                Logging.d(MediaCodecVideoDecoder.TAG, "VP9 HW Decoder supported.");
                arrayList.add(new VideoCodecInfo("VP9", new HashMap()));
            }
            if (MediaCodecVideoDecoder.isH264HighProfileHwSupported()) {
                Logging.d(MediaCodecVideoDecoder.TAG, "H.264 High Profile HW Decoder supported.");
                arrayList.add(H264Utils.DEFAULT_H264_HIGH_PROFILE_CODEC);
            }
            if (MediaCodecVideoDecoder.isH264HwSupported()) {
                Logging.d(MediaCodecVideoDecoder.TAG, "H.264 HW Decoder supported.");
                arrayList.add(H264Utils.DEFAULT_H264_BASELINE_PROFILE_CODEC);
            }
            return (VideoCodecInfo[]) arrayList.toArray(new VideoCodecInfo[arrayList.size()]);
        }

        @Override // org.webrtc.VideoDecoderFactory
        public VideoCodecInfo[] getSupportedCodecs() {
            return this.supportedHardwareCodecs;
        }

        @Override // org.webrtc.VideoDecoderFactory
        public VideoDecoder createDecoder(final VideoCodecInfo videoCodecInfo) {
            if (!isCodecSupported(this.supportedHardwareCodecs, videoCodecInfo)) {
                Logging.d(MediaCodecVideoDecoder.TAG, "No HW video decoder for codec " + videoCodecInfo.name);
                return null;
            }
            Logging.d(MediaCodecVideoDecoder.TAG, "Create HW video decoder for " + videoCodecInfo.name);
            return new WrappedNativeVideoDecoder() {
                /* class org.webrtc.MediaCodecVideoDecoder.HwDecoderFactory.AnonymousClass1 */

                @Override // org.webrtc.WrappedNativeVideoDecoder, org.webrtc.VideoDecoder
                public long createNativeVideoDecoder() {
                    return MediaCodecVideoDecoder.nativeCreateDecoder(videoCodecInfo.name, MediaCodecVideoDecoder.useSurface());
                }
            };
        }
    }

    public enum VideoCodecType {
        VIDEO_CODEC_UNKNOWN,
        VIDEO_CODEC_VP8,
        VIDEO_CODEC_VP9,
        VIDEO_CODEC_H264;

        @CalledByNative("VideoCodecType")
        static VideoCodecType fromNativeIndex(int i) {
            return values()[i];
        }
    }

    private static final String[] supportedVp8HwCodecPrefixes() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(supportedQcomH264HighProfileHwCodecPrefix);
        arrayList.add("OMX.Nvidia.");
        arrayList.add(supportedExynosH264HighProfileHwCodecPrefix);
        arrayList.add("OMX.Intel.");
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-MediaTekVP8").equals(PeerConnectionFactory.TRIAL_ENABLED) && Build.VERSION.SDK_INT >= 24) {
            arrayList.add(supportedMediaTekH264HighProfileHwCodecPrefix);
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private static final String[] supportedH264HwCodecPrefixes() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(supportedQcomH264HighProfileHwCodecPrefix);
        arrayList.add("OMX.Intel.");
        arrayList.add(supportedExynosH264HighProfileHwCodecPrefix);
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-MediaTekH264").equals(PeerConnectionFactory.TRIAL_ENABLED) && Build.VERSION.SDK_INT >= 27) {
            arrayList.add(supportedMediaTekH264HighProfileHwCodecPrefix);
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static void setEglContext(EglBase.Context context) {
        if (eglBase != null) {
            Logging.w(TAG, "Egl context already set.");
            eglBase.release();
        }
        eglBase = EglBase.CC.create(context);
    }

    public static void disposeEglContext() {
        EglBase eglBase2 = eglBase;
        if (eglBase2 != null) {
            eglBase2.release();
            eglBase = null;
        }
    }

    static boolean useSurface() {
        return eglBase != null;
    }

    public static void setErrorCallback(MediaCodecVideoDecoderErrorCallback mediaCodecVideoDecoderErrorCallback) {
        Logging.d(TAG, "Set error callback");
        errorCallback = mediaCodecVideoDecoderErrorCallback;
    }

    public static void disableVp8HwCodec() {
        Logging.w(TAG, "VP8 decoding is disabled by application.");
        hwDecoderDisabledTypes.add(VP8_MIME_TYPE);
    }

    public static void disableVp9HwCodec() {
        Logging.w(TAG, "VP9 decoding is disabled by application.");
        hwDecoderDisabledTypes.add(VP9_MIME_TYPE);
    }

    public static void disableH264HwCodec() {
        Logging.w(TAG, "H.264 decoding is disabled by application.");
        hwDecoderDisabledTypes.add(H264_MIME_TYPE);
    }

    public static boolean isVp8HwSupported() {
        return !hwDecoderDisabledTypes.contains(VP8_MIME_TYPE) && findDecoder(VP8_MIME_TYPE, supportedVp8HwCodecPrefixes()) != null;
    }

    public static boolean isVp9HwSupported() {
        return !hwDecoderDisabledTypes.contains(VP9_MIME_TYPE) && findDecoder(VP9_MIME_TYPE, supportedVp9HwCodecPrefixes) != null;
    }

    public static boolean isH264HwSupported() {
        return !hwDecoderDisabledTypes.contains(H264_MIME_TYPE) && findDecoder(H264_MIME_TYPE, supportedH264HwCodecPrefixes()) != null;
    }

    public static boolean isH264HighProfileHwSupported() {
        if (hwDecoderDisabledTypes.contains(H264_MIME_TYPE)) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 21 && findDecoder(H264_MIME_TYPE, new String[]{supportedQcomH264HighProfileHwCodecPrefix}) != null) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 23 && findDecoder(H264_MIME_TYPE, new String[]{supportedExynosH264HighProfileHwCodecPrefix}) != null) {
            return true;
        }
        if (!PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-MediaTekH264").equals(PeerConnectionFactory.TRIAL_ENABLED) || Build.VERSION.SDK_INT < 27 || findDecoder(H264_MIME_TYPE, new String[]{supportedMediaTekH264HighProfileHwCodecPrefix}) == null) {
            return false;
        }
        return true;
    }

    public static void printStackTrace() {
        Thread thread;
        MediaCodecVideoDecoder mediaCodecVideoDecoder = runningInstance;
        if (!(mediaCodecVideoDecoder == null || (thread = mediaCodecVideoDecoder.mediaCodecThread) == null)) {
            StackTraceElement[] stackTrace = thread.getStackTrace();
            if (stackTrace.length > 0) {
                Logging.d(TAG, "MediaCodecVideoDecoder stacks trace:");
                for (StackTraceElement stackTraceElement : stackTrace) {
                    Logging.d(TAG, stackTraceElement.toString());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static class DecoderProperties {
        public final String codecName;
        public final int colorFormat;

        public DecoderProperties(String str, int i) {
            this.codecName = str;
            this.colorFormat = i;
        }
    }

    private static DecoderProperties findDecoder(String str, String[] strArr) {
        MediaCodecInfo mediaCodecInfo;
        String str2;
        boolean z;
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        Logging.d(TAG, "Trying to find HW decoder for mime " + str);
        for (int i = 0; i < MediaCodecList.getCodecCount(); i++) {
            try {
                mediaCodecInfo = MediaCodecList.getCodecInfoAt(i);
            } catch (IllegalArgumentException e) {
                Logging.e(TAG, "Cannot retrieve decoder codec info", e);
                mediaCodecInfo = null;
            }
            if (mediaCodecInfo != null && !mediaCodecInfo.isEncoder()) {
                String[] supportedTypes = mediaCodecInfo.getSupportedTypes();
                int length = supportedTypes.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        str2 = null;
                        break;
                    } else if (supportedTypes[i2].equals(str)) {
                        str2 = mediaCodecInfo.getName();
                        break;
                    } else {
                        i2++;
                    }
                }
                if (str2 == null) {
                    continue;
                } else {
                    Logging.d(TAG, "Found candidate decoder " + str2);
                    int length2 = strArr.length;
                    int i3 = 0;
                    while (true) {
                        if (i3 >= length2) {
                            z = false;
                            break;
                        } else if (str2.startsWith(strArr[i3])) {
                            z = true;
                            break;
                        } else {
                            i3++;
                        }
                    }
                    if (!z) {
                        continue;
                    } else {
                        try {
                            MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
                            int[] iArr = capabilitiesForType.colorFormats;
                            for (int i4 : iArr) {
                                Logging.v(TAG, "   Color: 0x" + Integer.toHexString(i4));
                            }
                            for (Integer num : supportedColorList) {
                                int intValue = num.intValue();
                                int[] iArr2 = capabilitiesForType.colorFormats;
                                int length3 = iArr2.length;
                                int i5 = 0;
                                while (true) {
                                    if (i5 < length3) {
                                        int i6 = iArr2[i5];
                                        if (i6 == intValue) {
                                            Logging.d(TAG, "Found target decoder " + str2 + ". Color: 0x" + Integer.toHexString(i6));
                                            return new DecoderProperties(str2, i6);
                                        }
                                        i5++;
                                    }
                                }
                            }
                            continue;
                        } catch (IllegalArgumentException e2) {
                            Logging.e(TAG, "Cannot retrieve decoder capabilities", e2);
                        }
                    }
                }
            }
        }
        Logging.d(TAG, "No HW decoder found for mime " + str);
        return null;
    }

    @CalledByNative
    MediaCodecVideoDecoder() {
    }

    private void checkOnMediaCodecThread() {
        if (this.mediaCodecThread.getId() != Thread.currentThread().getId()) {
            throw new IllegalStateException("MediaCodecVideoDecoder previously operated on " + this.mediaCodecThread + " but is now called on " + Thread.currentThread());
        }
    }

    @CalledByNativeUnchecked
    private boolean initDecode(VideoCodecType videoCodecType, int i, int i2) {
        String[] strArr;
        String str;
        SurfaceTextureHelper create;
        if (this.mediaCodecThread == null) {
            if (videoCodecType == VideoCodecType.VIDEO_CODEC_VP8) {
                str = VP8_MIME_TYPE;
                strArr = supportedVp8HwCodecPrefixes();
            } else if (videoCodecType == VideoCodecType.VIDEO_CODEC_VP9) {
                str = VP9_MIME_TYPE;
                strArr = supportedVp9HwCodecPrefixes;
            } else if (videoCodecType == VideoCodecType.VIDEO_CODEC_H264) {
                str = H264_MIME_TYPE;
                strArr = supportedH264HwCodecPrefixes();
            } else {
                throw new RuntimeException("initDecode: Non-supported codec " + videoCodecType);
            }
            DecoderProperties findDecoder = findDecoder(str, strArr);
            if (findDecoder != null) {
                Logging.d(TAG, "Java initDecode: " + videoCodecType + " : " + i + " x " + i2 + ". Color: 0x" + Integer.toHexString(findDecoder.colorFormat) + ". Use Surface: " + useSurface());
                runningInstance = this;
                this.mediaCodecThread = Thread.currentThread();
                try {
                    this.width = i;
                    this.height = i2;
                    this.stride = i;
                    this.sliceHeight = i2;
                    if (useSurface() && (create = SurfaceTextureHelper.create("Decoder SurfaceTextureHelper", eglBase.getEglBaseContext())) != null) {
                        this.textureListener = new TextureListener(create);
                        this.textureListener.setSize(i, i2);
                        this.surface = new Surface(create.getSurfaceTexture());
                    }
                    MediaFormat createVideoFormat = MediaFormat.createVideoFormat(str, i, i2);
                    if (!useSurface()) {
                        createVideoFormat.setInteger("color-format", findDecoder.colorFormat);
                    }
                    Logging.d(TAG, "  Format: " + createVideoFormat);
                    this.mediaCodec = MediaCodecVideoEncoder.createByCodecName(findDecoder.codecName);
                    if (this.mediaCodec == null) {
                        Logging.e(TAG, "Can not create media decoder");
                        return false;
                    }
                    this.mediaCodec.configure(createVideoFormat, this.surface, (MediaCrypto) null, 0);
                    this.mediaCodec.start();
                    this.colorFormat = findDecoder.colorFormat;
                    this.outputBuffers = this.mediaCodec.getOutputBuffers();
                    this.inputBuffers = this.mediaCodec.getInputBuffers();
                    this.decodeStartTimeMs.clear();
                    this.hasDecodedFirstFrame = false;
                    this.dequeuedSurfaceOutputBuffers.clear();
                    this.droppedFrames = 0;
                    Logging.d(TAG, "Input buffers: " + this.inputBuffers.length + ". Output buffers: " + this.outputBuffers.length);
                    return true;
                } catch (IllegalStateException e) {
                    Logging.e(TAG, "initDecode failed", e);
                    return false;
                }
            } else {
                throw new RuntimeException("Cannot find HW decoder for " + videoCodecType);
            }
        } else {
            throw new RuntimeException("initDecode: Forgot to release()?");
        }
    }

    @CalledByNativeUnchecked
    private void reset(int i, int i2) {
        if (this.mediaCodecThread == null || this.mediaCodec == null) {
            throw new RuntimeException("Incorrect reset call for non-initialized decoder.");
        }
        Logging.d(TAG, "Java reset: " + i + " x " + i2);
        this.mediaCodec.flush();
        this.width = i;
        this.height = i2;
        TextureListener textureListener2 = this.textureListener;
        if (textureListener2 != null) {
            textureListener2.setSize(i, i2);
        }
        this.decodeStartTimeMs.clear();
        this.dequeuedSurfaceOutputBuffers.clear();
        this.hasDecodedFirstFrame = false;
        this.droppedFrames = 0;
    }

    @CalledByNativeUnchecked
    private void release() {
        Logging.d(TAG, "Java releaseDecoder. Total number of dropped frames: " + this.droppedFrames);
        checkOnMediaCodecThread();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() {
            /* class org.webrtc.MediaCodecVideoDecoder.AnonymousClass1 */

            public void run() {
                try {
                    Logging.d(MediaCodecVideoDecoder.TAG, "Java releaseDecoder on release thread");
                    MediaCodecVideoDecoder.this.mediaCodec.stop();
                    MediaCodecVideoDecoder.this.mediaCodec.release();
                    Logging.d(MediaCodecVideoDecoder.TAG, "Java releaseDecoder on release thread done");
                } catch (Exception e) {
                    Logging.e(MediaCodecVideoDecoder.TAG, "Media decoder release failed", e);
                }
                countDownLatch.countDown();
            }
        }).start();
        if (!ThreadUtils.awaitUninterruptibly(countDownLatch, 5000)) {
            Logging.e(TAG, "Media decoder release timeout");
            codecErrors++;
            if (errorCallback != null) {
                Logging.e(TAG, "Invoke codec error callback. Errors: " + codecErrors);
                errorCallback.onMediaCodecVideoDecoderCriticalError(codecErrors);
            }
        }
        this.mediaCodec = null;
        this.mediaCodecThread = null;
        runningInstance = null;
        if (useSurface()) {
            this.surface.release();
            this.surface = null;
            this.textureListener.release();
        }
        Logging.d(TAG, "Java releaseDecoder done");
    }

    @CalledByNativeUnchecked
    private int dequeueInputBuffer() {
        checkOnMediaCodecThread();
        try {
            return this.mediaCodec.dequeueInputBuffer(500000);
        } catch (IllegalStateException e) {
            Logging.e(TAG, "dequeueIntputBuffer failed", e);
            return -2;
        }
    }

    @CalledByNativeUnchecked
    private boolean queueInputBuffer(int i, int i2, long j, long j2, long j3) {
        checkOnMediaCodecThread();
        try {
            this.inputBuffers[i].position(0);
            this.inputBuffers[i].limit(i2);
            this.decodeStartTimeMs.add(new TimeStamps(SystemClock.elapsedRealtime(), j2, j3));
            this.mediaCodec.queueInputBuffer(i, 0, i2, j, 0);
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "decode failed", e);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static class TimeStamps {
        private final long decodeStartTimeMs;
        private final long ntpTimeStampMs;
        private final long timeStampMs;

        public TimeStamps(long j, long j2, long j3) {
            this.decodeStartTimeMs = j;
            this.timeStampMs = j2;
            this.ntpTimeStampMs = j3;
        }
    }

    /* access modifiers changed from: private */
    public static class DecodedOutputBuffer {
        private final long decodeTimeMs;
        private final long endDecodeTimeMs;
        private final int index;
        private final long ntpTimeStampMs;
        private final int offset;
        private final long presentationTimeStampMs;
        private final int size;
        private final long timeStampMs;

        public DecodedOutputBuffer(int i, int i2, int i3, long j, long j2, long j3, long j4, long j5) {
            this.index = i;
            this.offset = i2;
            this.size = i3;
            this.presentationTimeStampMs = j;
            this.timeStampMs = j2;
            this.ntpTimeStampMs = j3;
            this.decodeTimeMs = j4;
            this.endDecodeTimeMs = j5;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("DecodedOutputBuffer")
        public int getIndex() {
            return this.index;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("DecodedOutputBuffer")
        public int getOffset() {
            return this.offset;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("DecodedOutputBuffer")
        public int getSize() {
            return this.size;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("DecodedOutputBuffer")
        public long getPresentationTimestampMs() {
            return this.presentationTimeStampMs;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("DecodedOutputBuffer")
        public long getTimestampMs() {
            return this.timeStampMs;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("DecodedOutputBuffer")
        public long getNtpTimestampMs() {
            return this.ntpTimeStampMs;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("DecodedOutputBuffer")
        public long getDecodeTimeMs() {
            return this.decodeTimeMs;
        }
    }

    /* access modifiers changed from: private */
    public static class DecodedTextureBuffer {
        private final long decodeTimeMs;
        private final long frameDelayMs;
        private final long ntpTimeStampMs;
        private final long presentationTimeStampMs;
        private final long timeStampMs;
        private final VideoFrame.Buffer videoFrameBuffer;

        public DecodedTextureBuffer(VideoFrame.Buffer buffer, long j, long j2, long j3, long j4, long j5) {
            this.videoFrameBuffer = buffer;
            this.presentationTimeStampMs = j;
            this.timeStampMs = j2;
            this.ntpTimeStampMs = j3;
            this.decodeTimeMs = j4;
            this.frameDelayMs = j5;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("DecodedTextureBuffer")
        public VideoFrame.Buffer getVideoFrameBuffer() {
            return this.videoFrameBuffer;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("DecodedTextureBuffer")
        public long getPresentationTimestampMs() {
            return this.presentationTimeStampMs;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("DecodedTextureBuffer")
        public long getTimeStampMs() {
            return this.timeStampMs;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("DecodedTextureBuffer")
        public long getNtpTimestampMs() {
            return this.ntpTimeStampMs;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("DecodedTextureBuffer")
        public long getDecodeTimeMs() {
            return this.decodeTimeMs;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("DecodedTextureBuffer")
        public long getFrameDelayMs() {
            return this.frameDelayMs;
        }
    }

    /* access modifiers changed from: private */
    public class TextureListener implements VideoSink {
        private DecodedOutputBuffer bufferToRender;
        private final Object newFrameLock = new Object();
        private DecodedTextureBuffer renderedBuffer;
        private final SurfaceTextureHelper surfaceTextureHelper;

        public TextureListener(SurfaceTextureHelper surfaceTextureHelper2) {
            this.surfaceTextureHelper = surfaceTextureHelper2;
            surfaceTextureHelper2.startListening(this);
        }

        public void addBufferToRender(DecodedOutputBuffer decodedOutputBuffer) {
            if (this.bufferToRender == null) {
                this.bufferToRender = decodedOutputBuffer;
            } else {
                Logging.e(MediaCodecVideoDecoder.TAG, "Unexpected addBufferToRender() called while waiting for a texture.");
                throw new IllegalStateException("Waiting for a texture.");
            }
        }

        public boolean isWaitingForTexture() {
            boolean z;
            synchronized (this.newFrameLock) {
                z = this.bufferToRender != null;
            }
            return z;
        }

        public void setSize(int i, int i2) {
            this.surfaceTextureHelper.setTextureSize(i, i2);
        }

        @Override // org.webrtc.VideoSink
        public void onFrame(VideoFrame videoFrame) {
            synchronized (this.newFrameLock) {
                if (this.renderedBuffer == null) {
                    VideoFrame.Buffer buffer = videoFrame.getBuffer();
                    buffer.retain();
                    this.renderedBuffer = new DecodedTextureBuffer(buffer, this.bufferToRender.presentationTimeStampMs, this.bufferToRender.timeStampMs, this.bufferToRender.ntpTimeStampMs, this.bufferToRender.decodeTimeMs, SystemClock.elapsedRealtime() - this.bufferToRender.endDecodeTimeMs);
                    this.bufferToRender = null;
                    this.newFrameLock.notifyAll();
                } else {
                    Logging.e(MediaCodecVideoDecoder.TAG, "Unexpected onFrame() called while already holding a texture.");
                    throw new IllegalStateException("Already holding a texture.");
                }
            }
        }

        public DecodedTextureBuffer dequeueTextureBuffer(int i) {
            DecodedTextureBuffer decodedTextureBuffer;
            synchronized (this.newFrameLock) {
                if (this.renderedBuffer == null && i > 0 && isWaitingForTexture()) {
                    try {
                        this.newFrameLock.wait((long) i);
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
                decodedTextureBuffer = this.renderedBuffer;
                this.renderedBuffer = null;
            }
            return decodedTextureBuffer;
        }

        public void release() {
            this.surfaceTextureHelper.stopListening();
            synchronized (this.newFrameLock) {
                if (this.renderedBuffer != null) {
                    this.renderedBuffer.getVideoFrameBuffer().release();
                    this.renderedBuffer = null;
                }
            }
            this.surfaceTextureHelper.dispose();
        }
    }

    @CalledByNativeUnchecked
    private DecodedOutputBuffer dequeueOutputBuffer(int i) {
        long j;
        int i2;
        int i3;
        checkOnMediaCodecThread();
        if (this.decodeStartTimeMs.isEmpty()) {
            return null;
        }
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        while (true) {
            int dequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(bufferInfo, TimeUnit.MILLISECONDS.toMicros((long) i));
            switch (dequeueOutputBuffer) {
                case -3:
                    this.outputBuffers = this.mediaCodec.getOutputBuffers();
                    Logging.d(TAG, "Decoder output buffers changed: " + this.outputBuffers.length);
                    if (!this.hasDecodedFirstFrame) {
                        break;
                    } else {
                        throw new RuntimeException("Unexpected output buffer change event.");
                    }
                case -2:
                    MediaFormat outputFormat = this.mediaCodec.getOutputFormat();
                    Logging.d(TAG, "Decoder format changed: " + outputFormat.toString());
                    if (!outputFormat.containsKey(FORMAT_KEY_CROP_LEFT) || !outputFormat.containsKey(FORMAT_KEY_CROP_RIGHT) || !outputFormat.containsKey(FORMAT_KEY_CROP_BOTTOM) || !outputFormat.containsKey(FORMAT_KEY_CROP_TOP)) {
                        i3 = outputFormat.getInteger("width");
                        i2 = outputFormat.getInteger("height");
                    } else {
                        i3 = (outputFormat.getInteger(FORMAT_KEY_CROP_RIGHT) + 1) - outputFormat.getInteger(FORMAT_KEY_CROP_LEFT);
                        i2 = (outputFormat.getInteger(FORMAT_KEY_CROP_BOTTOM) + 1) - outputFormat.getInteger(FORMAT_KEY_CROP_TOP);
                    }
                    if (!this.hasDecodedFirstFrame || (i3 == this.width && i2 == this.height)) {
                        this.width = i3;
                        this.height = i2;
                        TextureListener textureListener2 = this.textureListener;
                        if (textureListener2 != null) {
                            textureListener2.setSize(this.width, this.height);
                        }
                        if (!useSurface() && outputFormat.containsKey("color-format")) {
                            this.colorFormat = outputFormat.getInteger("color-format");
                            Logging.d(TAG, "Color: 0x" + Integer.toHexString(this.colorFormat));
                            if (!supportedColorList.contains(Integer.valueOf(this.colorFormat))) {
                                throw new IllegalStateException("Non supported color format: " + this.colorFormat);
                            }
                        }
                        if (outputFormat.containsKey(FORMAT_KEY_STRIDE)) {
                            this.stride = outputFormat.getInteger(FORMAT_KEY_STRIDE);
                        }
                        if (outputFormat.containsKey(FORMAT_KEY_SLICE_HEIGHT)) {
                            this.sliceHeight = outputFormat.getInteger(FORMAT_KEY_SLICE_HEIGHT);
                        }
                        Logging.d(TAG, "Frame stride and slice height: " + this.stride + " x " + this.sliceHeight);
                        this.stride = Math.max(this.width, this.stride);
                        this.sliceHeight = Math.max(this.height, this.sliceHeight);
                        break;
                    }
                    break;
                case -1:
                    return null;
                default:
                    this.hasDecodedFirstFrame = true;
                    TimeStamps remove = this.decodeStartTimeMs.remove();
                    long elapsedRealtime = SystemClock.elapsedRealtime() - remove.decodeStartTimeMs;
                    if (elapsedRealtime > MAX_DECODE_TIME_MS) {
                        Logging.e(TAG, "Very high decode time: " + elapsedRealtime + "ms. Q size: " + this.decodeStartTimeMs.size() + ". Might be caused by resuming H264 decoding after a pause.");
                        j = 200;
                    } else {
                        j = elapsedRealtime;
                    }
                    return new DecodedOutputBuffer(dequeueOutputBuffer, bufferInfo.offset, bufferInfo.size, TimeUnit.MICROSECONDS.toMillis(bufferInfo.presentationTimeUs), remove.timeStampMs, remove.ntpTimeStampMs, j, SystemClock.elapsedRealtime());
            }
        }
        throw new RuntimeException("Unexpected size change. Configured " + this.width + "*" + this.height + ". New " + i3 + "*" + i2);
    }

    @CalledByNativeUnchecked
    private DecodedTextureBuffer dequeueTextureBuffer(int i) {
        checkOnMediaCodecThread();
        if (useSurface()) {
            DecodedOutputBuffer dequeueOutputBuffer = dequeueOutputBuffer(i);
            if (dequeueOutputBuffer != null) {
                this.dequeuedSurfaceOutputBuffers.add(dequeueOutputBuffer);
            }
            MaybeRenderDecodedTextureBuffer();
            DecodedTextureBuffer dequeueTextureBuffer = this.textureListener.dequeueTextureBuffer(i);
            if (dequeueTextureBuffer != null) {
                MaybeRenderDecodedTextureBuffer();
                return dequeueTextureBuffer;
            } else if (this.dequeuedSurfaceOutputBuffers.size() < Math.min(3, this.outputBuffers.length) && (i <= 0 || this.dequeuedSurfaceOutputBuffers.isEmpty())) {
                return null;
            } else {
                this.droppedFrames++;
                DecodedOutputBuffer remove = this.dequeuedSurfaceOutputBuffers.remove();
                if (i > 0) {
                    Logging.w(TAG, "Draining decoder. Dropping frame with TS: " + remove.presentationTimeStampMs + ". Total number of dropped frames: " + this.droppedFrames);
                } else {
                    Logging.w(TAG, "Too many output buffers " + this.dequeuedSurfaceOutputBuffers.size() + ". Dropping frame with TS: " + remove.presentationTimeStampMs + ". Total number of dropped frames: " + this.droppedFrames);
                }
                this.mediaCodec.releaseOutputBuffer(remove.index, false);
                return new DecodedTextureBuffer(null, remove.presentationTimeStampMs, remove.timeStampMs, remove.ntpTimeStampMs, remove.decodeTimeMs, SystemClock.elapsedRealtime() - remove.endDecodeTimeMs);
            }
        } else {
            throw new IllegalStateException("dequeueTexture() called for byte buffer decoding.");
        }
    }

    private void MaybeRenderDecodedTextureBuffer() {
        if (!this.dequeuedSurfaceOutputBuffers.isEmpty() && !this.textureListener.isWaitingForTexture()) {
            DecodedOutputBuffer remove = this.dequeuedSurfaceOutputBuffers.remove();
            this.textureListener.addBufferToRender(remove);
            this.mediaCodec.releaseOutputBuffer(remove.index, true);
        }
    }

    @CalledByNativeUnchecked
    private void returnDecodedOutputBuffer(int i) {
        checkOnMediaCodecThread();
        if (!useSurface()) {
            this.mediaCodec.releaseOutputBuffer(i, false);
            return;
        }
        throw new IllegalStateException("returnDecodedOutputBuffer() called for surface decoding.");
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public ByteBuffer[] getInputBuffers() {
        return this.inputBuffers;
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public ByteBuffer[] getOutputBuffers() {
        return this.outputBuffers;
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public int getColorFormat() {
        return this.colorFormat;
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public int getWidth() {
        return this.width;
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public int getHeight() {
        return this.height;
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public int getStride() {
        return this.stride;
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public int getSliceHeight() {
        return this.sliceHeight;
    }
}
