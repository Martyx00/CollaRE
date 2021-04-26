package org.webrtc;

import android.annotation.TargetApi;
import android.graphics.Matrix;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Bundle;
import android.view.Surface;
import com.google.android.gms.common.Scopes;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.webrtc.EglBase;
import org.webrtc.EglBase14;
import org.webrtc.VideoFrame;

@TargetApi(19)
@Deprecated
public class MediaCodecVideoEncoder {
    private static final int BITRATE_ADJUSTMENT_FPS = 30;
    private static final double BITRATE_CORRECTION_MAX_SCALE = 4.0d;
    private static final double BITRATE_CORRECTION_SEC = 3.0d;
    private static final int BITRATE_CORRECTION_STEPS = 20;
    private static final int COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m = 2141391876;
    private static final int DEQUEUE_TIMEOUT = 0;
    private static final String[] H264_HW_EXCEPTION_MODELS = {"SAMSUNG-SGH-I337", "Nexus 7", "Nexus 4"};
    private static final String H264_MIME_TYPE = "video/avc";
    private static final int MAXIMUM_INITIAL_FPS = 30;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_L_MS = 15000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_M_MS = 20000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_N_MS = 15000;
    private static final String TAG = "MediaCodecVideoEncoder";
    private static final int VIDEO_AVCLevel3 = 256;
    private static final int VIDEO_AVCProfileHigh = 8;
    private static final int VIDEO_ControlRateConstant = 2;
    private static final String VP8_MIME_TYPE = "video/x-vnd.on2.vp8";
    private static final String VP9_MIME_TYPE = "video/x-vnd.on2.vp9";
    private static int codecErrors;
    private static MediaCodecVideoEncoderErrorCallback errorCallback;
    private static final MediaCodecProperties exynosH264HighProfileHwProperties = new MediaCodecProperties("OMX.Exynos.", 23, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
    private static final MediaCodecProperties exynosH264HwProperties = new MediaCodecProperties("OMX.Exynos.", 21, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
    private static final MediaCodecProperties exynosVp8HwProperties = new MediaCodecProperties("OMX.Exynos.", 23, BitrateAdjustmentType.DYNAMIC_ADJUSTMENT);
    private static final MediaCodecProperties exynosVp9HwProperties = new MediaCodecProperties("OMX.Exynos.", 24, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
    private static final MediaCodecProperties[] h264HighProfileHwList = {exynosH264HighProfileHwProperties};
    private static Set<String> hwEncoderDisabledTypes = new HashSet();
    private static final MediaCodecProperties intelVp8HwProperties = new MediaCodecProperties("OMX.Intel.", 21, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties mediatekH264HwProperties = new MediaCodecProperties("OMX.MTK.", 27, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
    private static final MediaCodecProperties qcomH264HwProperties = new MediaCodecProperties("OMX.qcom.", 19, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties qcomVp8HwProperties = new MediaCodecProperties("OMX.qcom.", 19, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties qcomVp9HwProperties = new MediaCodecProperties("OMX.qcom.", 24, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static MediaCodecVideoEncoder runningInstance;
    private static EglBase staticEglBase;
    private static final int[] supportedColorList = {19, 21, 2141391872, COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m};
    private static final int[] supportedSurfaceColorList = {2130708361};
    private static final MediaCodecProperties[] vp9HwList = {qcomVp9HwProperties, exynosVp9HwProperties};
    private double bitrateAccumulator;
    private double bitrateAccumulatorMax;
    private int bitrateAdjustmentScaleExp;
    private BitrateAdjustmentType bitrateAdjustmentType = BitrateAdjustmentType.NO_ADJUSTMENT;
    private double bitrateObservationTimeMs;
    private int colorFormat;
    private ByteBuffer configData = null;
    private GlRectDrawer drawer;
    private EglBase14 eglBase;
    private long forcedKeyFrameMs;
    private int height;
    private Surface inputSurface;
    private long lastKeyFrameMs;
    private MediaCodec mediaCodec;
    private Thread mediaCodecThread;
    private ByteBuffer[] outputBuffers;
    private int profile;
    private int targetBitrateBps;
    private int targetFps;
    private VideoCodecType type;
    private int width;

    public enum BitrateAdjustmentType {
        NO_ADJUSTMENT,
        FRAMERATE_ADJUSTMENT,
        DYNAMIC_ADJUSTMENT
    }

    public interface MediaCodecVideoEncoderErrorCallback {
        void onMediaCodecVideoEncoderCriticalError(int i);
    }

    /* access modifiers changed from: private */
    public static native long nativeCreateEncoder(VideoCodecInfo videoCodecInfo, boolean z);

    private static native void nativeFillInputBuffer(long j, int i, ByteBuffer byteBuffer, int i2, ByteBuffer byteBuffer2, int i3, ByteBuffer byteBuffer3, int i4);

    public static VideoEncoderFactory createFactory() {
        return new DefaultVideoEncoderFactory(new HwEncoderFactory());
    }

    /* access modifiers changed from: package-private */
    public static class HwEncoderFactory implements VideoEncoderFactory {
        private final VideoCodecInfo[] supportedHardwareCodecs = getSupportedHardwareCodecs();

        HwEncoderFactory() {
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
            if (MediaCodecVideoEncoder.isVp8HwSupported()) {
                Logging.d(MediaCodecVideoEncoder.TAG, "VP8 HW Encoder supported.");
                arrayList.add(new VideoCodecInfo("VP8", new HashMap()));
            }
            if (MediaCodecVideoEncoder.isVp9HwSupported()) {
                Logging.d(MediaCodecVideoEncoder.TAG, "VP9 HW Encoder supported.");
                arrayList.add(new VideoCodecInfo("VP9", new HashMap()));
            }
            if (MediaCodecVideoDecoder.isH264HighProfileHwSupported()) {
                Logging.d(MediaCodecVideoEncoder.TAG, "H.264 High Profile HW Encoder supported.");
                arrayList.add(H264Utils.DEFAULT_H264_HIGH_PROFILE_CODEC);
            }
            if (MediaCodecVideoEncoder.isH264HwSupported()) {
                Logging.d(MediaCodecVideoEncoder.TAG, "H.264 HW Encoder supported.");
                arrayList.add(H264Utils.DEFAULT_H264_BASELINE_PROFILE_CODEC);
            }
            return (VideoCodecInfo[]) arrayList.toArray(new VideoCodecInfo[arrayList.size()]);
        }

        @Override // org.webrtc.VideoEncoderFactory
        public VideoCodecInfo[] getSupportedCodecs() {
            return this.supportedHardwareCodecs;
        }

        @Override // org.webrtc.VideoEncoderFactory
        public VideoEncoder createEncoder(final VideoCodecInfo videoCodecInfo) {
            if (!isCodecSupported(this.supportedHardwareCodecs, videoCodecInfo)) {
                Logging.d(MediaCodecVideoEncoder.TAG, "No HW video encoder for codec " + videoCodecInfo.name);
                return null;
            }
            Logging.d(MediaCodecVideoEncoder.TAG, "Create HW video encoder for " + videoCodecInfo.name);
            return new WrappedNativeVideoEncoder() {
                /* class org.webrtc.MediaCodecVideoEncoder.HwEncoderFactory.AnonymousClass1 */

                @Override // org.webrtc.VideoEncoder, org.webrtc.WrappedNativeVideoEncoder
                public boolean isHardwareEncoder() {
                    return true;
                }

                @Override // org.webrtc.VideoEncoder, org.webrtc.WrappedNativeVideoEncoder
                public long createNativeVideoEncoder() {
                    return MediaCodecVideoEncoder.nativeCreateEncoder(videoCodecInfo, MediaCodecVideoEncoder.staticEglBase instanceof EglBase14);
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

    public enum H264Profile {
        CONSTRAINED_BASELINE(0),
        BASELINE(1),
        MAIN(2),
        CONSTRAINED_HIGH(3),
        HIGH(4);
        
        private final int value;

        private H264Profile(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* access modifiers changed from: private */
    public static class MediaCodecProperties {
        public final BitrateAdjustmentType bitrateAdjustmentType;
        public final String codecPrefix;
        public final int minSdk;

        MediaCodecProperties(String str, int i, BitrateAdjustmentType bitrateAdjustmentType2) {
            this.codecPrefix = str;
            this.minSdk = i;
            this.bitrateAdjustmentType = bitrateAdjustmentType2;
        }
    }

    public static void setEglContext(EglBase.Context context) {
        if (staticEglBase != null) {
            Logging.w(TAG, "Egl context already set.");
            staticEglBase.release();
        }
        staticEglBase = EglBase.CC.create(context);
    }

    public static void disposeEglContext() {
        EglBase eglBase2 = staticEglBase;
        if (eglBase2 != null) {
            eglBase2.release();
            staticEglBase = null;
        }
    }

    static EglBase.Context getEglContext() {
        EglBase eglBase2 = staticEglBase;
        if (eglBase2 == null) {
            return null;
        }
        return eglBase2.getEglBaseContext();
    }

    private static MediaCodecProperties[] vp8HwList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(qcomVp8HwProperties);
        arrayList.add(exynosVp8HwProperties);
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-IntelVP8").equals(PeerConnectionFactory.TRIAL_ENABLED)) {
            arrayList.add(intelVp8HwProperties);
        }
        return (MediaCodecProperties[]) arrayList.toArray(new MediaCodecProperties[arrayList.size()]);
    }

    private static final MediaCodecProperties[] h264HwList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(qcomH264HwProperties);
        arrayList.add(exynosH264HwProperties);
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-MediaTekH264").equals(PeerConnectionFactory.TRIAL_ENABLED)) {
            arrayList.add(mediatekH264HwProperties);
        }
        return (MediaCodecProperties[]) arrayList.toArray(new MediaCodecProperties[arrayList.size()]);
    }

    public static void setErrorCallback(MediaCodecVideoEncoderErrorCallback mediaCodecVideoEncoderErrorCallback) {
        Logging.d(TAG, "Set error callback");
        errorCallback = mediaCodecVideoEncoderErrorCallback;
    }

    public static void disableVp8HwCodec() {
        Logging.w(TAG, "VP8 encoding is disabled by application.");
        hwEncoderDisabledTypes.add(VP8_MIME_TYPE);
    }

    public static void disableVp9HwCodec() {
        Logging.w(TAG, "VP9 encoding is disabled by application.");
        hwEncoderDisabledTypes.add(VP9_MIME_TYPE);
    }

    public static void disableH264HwCodec() {
        Logging.w(TAG, "H.264 encoding is disabled by application.");
        hwEncoderDisabledTypes.add(H264_MIME_TYPE);
    }

    public static boolean isVp8HwSupported() {
        return !hwEncoderDisabledTypes.contains(VP8_MIME_TYPE) && findHwEncoder(VP8_MIME_TYPE, vp8HwList(), supportedColorList) != null;
    }

    public static EncoderProperties vp8HwEncoderProperties() {
        if (hwEncoderDisabledTypes.contains(VP8_MIME_TYPE)) {
            return null;
        }
        return findHwEncoder(VP8_MIME_TYPE, vp8HwList(), supportedColorList);
    }

    public static boolean isVp9HwSupported() {
        return !hwEncoderDisabledTypes.contains(VP9_MIME_TYPE) && findHwEncoder(VP9_MIME_TYPE, vp9HwList, supportedColorList) != null;
    }

    public static boolean isH264HwSupported() {
        return !hwEncoderDisabledTypes.contains(H264_MIME_TYPE) && findHwEncoder(H264_MIME_TYPE, h264HwList(), supportedColorList) != null;
    }

    public static boolean isH264HighProfileHwSupported() {
        return !hwEncoderDisabledTypes.contains(H264_MIME_TYPE) && findHwEncoder(H264_MIME_TYPE, h264HighProfileHwList, supportedColorList) != null;
    }

    public static boolean isVp8HwSupportedUsingTextures() {
        return !hwEncoderDisabledTypes.contains(VP8_MIME_TYPE) && findHwEncoder(VP8_MIME_TYPE, vp8HwList(), supportedSurfaceColorList) != null;
    }

    public static boolean isVp9HwSupportedUsingTextures() {
        return !hwEncoderDisabledTypes.contains(VP9_MIME_TYPE) && findHwEncoder(VP9_MIME_TYPE, vp9HwList, supportedSurfaceColorList) != null;
    }

    public static boolean isH264HwSupportedUsingTextures() {
        return !hwEncoderDisabledTypes.contains(H264_MIME_TYPE) && findHwEncoder(H264_MIME_TYPE, h264HwList(), supportedSurfaceColorList) != null;
    }

    public static class EncoderProperties {
        public final BitrateAdjustmentType bitrateAdjustmentType;
        public final String codecName;
        public final int colorFormat;

        public EncoderProperties(String str, int i, BitrateAdjustmentType bitrateAdjustmentType2) {
            this.codecName = str;
            this.colorFormat = i;
            this.bitrateAdjustmentType = bitrateAdjustmentType2;
        }
    }

    private static EncoderProperties findHwEncoder(String str, MediaCodecProperties[] mediaCodecPropertiesArr, int[] iArr) {
        MediaCodecInfo mediaCodecInfo;
        String str2;
        boolean z;
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        if (!str.equals(H264_MIME_TYPE) || !Arrays.asList(H264_HW_EXCEPTION_MODELS).contains(Build.MODEL)) {
            for (int i = 0; i < MediaCodecList.getCodecCount(); i++) {
                try {
                    mediaCodecInfo = MediaCodecList.getCodecInfoAt(i);
                } catch (IllegalArgumentException e) {
                    Logging.e(TAG, "Cannot retrieve encoder codec info", e);
                    mediaCodecInfo = null;
                }
                if (mediaCodecInfo != null && mediaCodecInfo.isEncoder()) {
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
                        Logging.v(TAG, "Found candidate encoder " + str2);
                        BitrateAdjustmentType bitrateAdjustmentType2 = BitrateAdjustmentType.NO_ADJUSTMENT;
                        int length2 = mediaCodecPropertiesArr.length;
                        int i3 = 0;
                        while (true) {
                            if (i3 >= length2) {
                                z = false;
                                break;
                            }
                            MediaCodecProperties mediaCodecProperties = mediaCodecPropertiesArr[i3];
                            if (str2.startsWith(mediaCodecProperties.codecPrefix)) {
                                if (Build.VERSION.SDK_INT < mediaCodecProperties.minSdk) {
                                    Logging.w(TAG, "Codec " + str2 + " is disabled due to SDK version " + Build.VERSION.SDK_INT);
                                } else {
                                    if (mediaCodecProperties.bitrateAdjustmentType != BitrateAdjustmentType.NO_ADJUSTMENT) {
                                        bitrateAdjustmentType2 = mediaCodecProperties.bitrateAdjustmentType;
                                        Logging.w(TAG, "Codec " + str2 + " requires bitrate adjustment: " + bitrateAdjustmentType2);
                                    }
                                    z = true;
                                }
                            }
                            i3++;
                        }
                        if (!z) {
                            continue;
                        } else {
                            try {
                                MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
                                int[] iArr2 = capabilitiesForType.colorFormats;
                                for (int i4 : iArr2) {
                                    Logging.v(TAG, "   Color: 0x" + Integer.toHexString(i4));
                                }
                                for (int i5 : iArr) {
                                    int[] iArr3 = capabilitiesForType.colorFormats;
                                    for (int i6 : iArr3) {
                                        if (i6 == i5) {
                                            Logging.d(TAG, "Found target encoder for mime " + str + " : " + str2 + ". Color: 0x" + Integer.toHexString(i6) + ". Bitrate adjustment: " + bitrateAdjustmentType2);
                                            return new EncoderProperties(str2, i6, bitrateAdjustmentType2);
                                        }
                                    }
                                }
                                continue;
                            } catch (IllegalArgumentException e2) {
                                Logging.e(TAG, "Cannot retrieve encoder capabilities", e2);
                            }
                        }
                    }
                }
            }
            return null;
        }
        Logging.w(TAG, "Model: " + Build.MODEL + " has black listed H.264 encoder.");
        return null;
    }

    @CalledByNative
    MediaCodecVideoEncoder() {
    }

    private void checkOnMediaCodecThread() {
        if (this.mediaCodecThread.getId() != Thread.currentThread().getId()) {
            throw new RuntimeException("MediaCodecVideoEncoder previously operated on " + this.mediaCodecThread + " but is now called on " + Thread.currentThread());
        }
    }

    public static void printStackTrace() {
        Thread thread;
        MediaCodecVideoEncoder mediaCodecVideoEncoder = runningInstance;
        if (!(mediaCodecVideoEncoder == null || (thread = mediaCodecVideoEncoder.mediaCodecThread) == null)) {
            StackTraceElement[] stackTrace = thread.getStackTrace();
            if (stackTrace.length > 0) {
                Logging.d(TAG, "MediaCodecVideoEncoder stacks trace:");
                for (StackTraceElement stackTraceElement : stackTrace) {
                    Logging.d(TAG, stackTraceElement.toString());
                }
            }
        }
    }

    static MediaCodec createByCodecName(String str) {
        try {
            return MediaCodec.createByCodecName(str);
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    @CalledByNativeUnchecked
    public boolean initEncode(VideoCodecType videoCodecType, int i, int i2, int i3, int i4, int i5, boolean z) {
        boolean z2;
        EncoderProperties encoderProperties;
        String str;
        boolean z3;
        Logging.d(TAG, "Java initEncode: " + videoCodecType + ". Profile: " + i + " : " + i2 + " x " + i3 + ". @ " + i4 + " kbps. Fps: " + i5 + ". Encode from texture : " + z);
        this.profile = i;
        this.width = i2;
        this.height = i3;
        if (this.mediaCodecThread == null) {
            int i6 = 100;
            if (videoCodecType == VideoCodecType.VIDEO_CODEC_VP8) {
                str = VP8_MIME_TYPE;
                encoderProperties = findHwEncoder(VP8_MIME_TYPE, vp8HwList(), z ? supportedSurfaceColorList : supportedColorList);
                z2 = false;
            } else if (videoCodecType == VideoCodecType.VIDEO_CODEC_VP9) {
                str = VP9_MIME_TYPE;
                encoderProperties = findHwEncoder(VP9_MIME_TYPE, vp9HwList, z ? supportedSurfaceColorList : supportedColorList);
                z2 = false;
            } else if (videoCodecType == VideoCodecType.VIDEO_CODEC_H264) {
                EncoderProperties findHwEncoder = findHwEncoder(H264_MIME_TYPE, h264HwList(), z ? supportedSurfaceColorList : supportedColorList);
                if (i == H264Profile.CONSTRAINED_HIGH.getValue()) {
                    if (findHwEncoder(H264_MIME_TYPE, h264HighProfileHwList, z ? supportedSurfaceColorList : supportedColorList) != null) {
                        Logging.d(TAG, "High profile H.264 encoder supported.");
                        z3 = true;
                        z2 = z3;
                        str = H264_MIME_TYPE;
                        encoderProperties = findHwEncoder;
                        i6 = 20;
                    } else {
                        Logging.d(TAG, "High profile H.264 encoder requested, but not supported. Use baseline.");
                    }
                }
                z3 = false;
                z2 = z3;
                str = H264_MIME_TYPE;
                encoderProperties = findHwEncoder;
                i6 = 20;
            } else {
                throw new RuntimeException("initEncode: Non-supported codec " + videoCodecType);
            }
            if (encoderProperties != null) {
                runningInstance = this;
                this.colorFormat = encoderProperties.colorFormat;
                this.bitrateAdjustmentType = encoderProperties.bitrateAdjustmentType;
                int i7 = 30;
                if (this.bitrateAdjustmentType != BitrateAdjustmentType.FRAMERATE_ADJUSTMENT) {
                    i7 = Math.min(i5, 30);
                }
                this.forcedKeyFrameMs = 0;
                this.lastKeyFrameMs = -1;
                if (videoCodecType == VideoCodecType.VIDEO_CODEC_VP8 && encoderProperties.codecName.startsWith(qcomVp8HwProperties.codecPrefix)) {
                    if (Build.VERSION.SDK_INT == 21 || Build.VERSION.SDK_INT == 22) {
                        this.forcedKeyFrameMs = 15000;
                    } else if (Build.VERSION.SDK_INT == 23) {
                        this.forcedKeyFrameMs = QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_M_MS;
                    } else if (Build.VERSION.SDK_INT > 23) {
                        this.forcedKeyFrameMs = 15000;
                    }
                }
                Logging.d(TAG, "Color format: " + this.colorFormat + ". Bitrate adjustment: " + this.bitrateAdjustmentType + ". Key frame interval: " + this.forcedKeyFrameMs + " . Initial fps: " + i7);
                this.targetBitrateBps = i4 * 1000;
                this.targetFps = i7;
                this.bitrateAccumulatorMax = ((double) this.targetBitrateBps) / 8.0d;
                this.bitrateAccumulator = 0.0d;
                this.bitrateObservationTimeMs = 0.0d;
                this.bitrateAdjustmentScaleExp = 0;
                this.mediaCodecThread = Thread.currentThread();
                try {
                    MediaFormat createVideoFormat = MediaFormat.createVideoFormat(str, i2, i3);
                    createVideoFormat.setInteger("bitrate", this.targetBitrateBps);
                    createVideoFormat.setInteger("bitrate-mode", 2);
                    createVideoFormat.setInteger("color-format", encoderProperties.colorFormat);
                    createVideoFormat.setInteger("frame-rate", this.targetFps);
                    createVideoFormat.setInteger("i-frame-interval", i6);
                    if (z2) {
                        createVideoFormat.setInteger(Scopes.PROFILE, 8);
                        createVideoFormat.setInteger(FirebaseAnalytics.b.LEVEL, 256);
                    }
                    Logging.d(TAG, "  Format: " + createVideoFormat);
                    this.mediaCodec = createByCodecName(encoderProperties.codecName);
                    this.type = videoCodecType;
                    if (this.mediaCodec == null) {
                        Logging.e(TAG, "Can not create media encoder");
                        release();
                        return false;
                    }
                    this.mediaCodec.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
                    if (z) {
                        this.eglBase = new EglBase14((EglBase14.Context) getEglContext(), EglBase.CONFIG_RECORDABLE);
                        this.inputSurface = this.mediaCodec.createInputSurface();
                        this.eglBase.createSurface(this.inputSurface);
                        this.drawer = new GlRectDrawer();
                    }
                    this.mediaCodec.start();
                    this.outputBuffers = this.mediaCodec.getOutputBuffers();
                    Logging.d(TAG, "Output buffers: " + this.outputBuffers.length);
                    return true;
                } catch (IllegalStateException e) {
                    Logging.e(TAG, "initEncode failed", e);
                    release();
                    return false;
                }
            } else {
                throw new RuntimeException("Can not find HW encoder for " + videoCodecType);
            }
        } else {
            throw new RuntimeException("Forgot to release()?");
        }
    }

    /* access modifiers changed from: package-private */
    @CalledByNativeUnchecked
    public ByteBuffer[] getInputBuffers() {
        ByteBuffer[] inputBuffers = this.mediaCodec.getInputBuffers();
        Logging.d(TAG, "Input buffers: " + inputBuffers.length);
        return inputBuffers;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0031  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void checkKeyFrameRequired(boolean r7, long r8) {
        /*
            r6 = this;
            r0 = 500(0x1f4, double:2.47E-321)
            long r8 = r8 + r0
            r0 = 1000(0x3e8, double:4.94E-321)
            long r8 = r8 / r0
            long r0 = r6.lastKeyFrameMs
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x0010
            r6.lastKeyFrameMs = r8
        L_0x0010:
            r0 = 0
            if (r7 != 0) goto L_0x0022
            long r4 = r6.forcedKeyFrameMs
            int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r1 <= 0) goto L_0x0022
            long r1 = r6.lastKeyFrameMs
            long r1 = r1 + r4
            int r3 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r3 <= 0) goto L_0x0022
            r1 = 1
            goto L_0x0023
        L_0x0022:
            r1 = 0
        L_0x0023:
            if (r7 != 0) goto L_0x0027
            if (r1 == 0) goto L_0x0049
        L_0x0027:
            if (r7 == 0) goto L_0x0031
            java.lang.String r7 = "MediaCodecVideoEncoder"
            java.lang.String r1 = "Sync frame request"
            org.webrtc.Logging.d(r7, r1)
            goto L_0x0038
        L_0x0031:
            java.lang.String r7 = "MediaCodecVideoEncoder"
            java.lang.String r1 = "Sync frame forced"
            org.webrtc.Logging.d(r7, r1)
        L_0x0038:
            android.os.Bundle r7 = new android.os.Bundle
            r7.<init>()
            java.lang.String r1 = "request-sync"
            r7.putInt(r1, r0)
            android.media.MediaCodec r0 = r6.mediaCodec
            r0.setParameters(r7)
            r6.lastKeyFrameMs = r8
        L_0x0049:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.MediaCodecVideoEncoder.checkKeyFrameRequired(boolean, long):void");
    }

    /* access modifiers changed from: package-private */
    @CalledByNativeUnchecked
    public boolean encodeBuffer(boolean z, int i, int i2, long j) {
        checkOnMediaCodecThread();
        try {
            checkKeyFrameRequired(z, j);
            this.mediaCodec.queueInputBuffer(i, 0, i2, j, 0);
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "encodeBuffer failed", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    @CalledByNativeUnchecked
    public boolean encodeFrame(long j, boolean z, VideoFrame videoFrame, int i, long j2) {
        checkOnMediaCodecThread();
        try {
            checkKeyFrameRequired(z, j2);
            VideoFrame.Buffer buffer = videoFrame.getBuffer();
            if (buffer instanceof VideoFrame.TextureBuffer) {
                this.eglBase.makeCurrent();
                GLES20.glClear(16384);
                VideoFrameDrawer.drawTexture(this.drawer, (VideoFrame.TextureBuffer) buffer, new Matrix(), this.width, this.height, 0, 0, this.width, this.height);
                this.eglBase.swapBuffers(TimeUnit.MICROSECONDS.toNanos(j2));
            } else {
                VideoFrame.I420Buffer i420 = buffer.toI420();
                int i2 = (this.height + 1) / 2;
                ByteBuffer dataY = i420.getDataY();
                ByteBuffer dataU = i420.getDataU();
                ByteBuffer dataV = i420.getDataV();
                int strideY = i420.getStrideY();
                int strideU = i420.getStrideU();
                int strideV = i420.getStrideV();
                if (dataY.capacity() < this.height * strideY) {
                    throw new RuntimeException("Y-plane buffer size too small.");
                } else if (dataU.capacity() < strideU * i2) {
                    throw new RuntimeException("U-plane buffer size too small.");
                } else if (dataV.capacity() >= i2 * strideV) {
                    nativeFillInputBuffer(j, i, dataY, strideY, dataU, strideU, dataV, strideV);
                    i420.release();
                    this.mediaCodec.queueInputBuffer(i, 0, ((this.width * this.height) * 3) / 2, j2, 0);
                } else {
                    throw new RuntimeException("V-plane buffer size too small.");
                }
            }
            return true;
        } catch (RuntimeException e) {
            Logging.e(TAG, "encodeFrame failed", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    @CalledByNativeUnchecked
    public void release() {
        Logging.d(TAG, "Java releaseEncoder");
        checkOnMediaCodecThread();
        final AnonymousClass1CaughtException r0 = new Object() {
            /* class org.webrtc.MediaCodecVideoEncoder.AnonymousClass1CaughtException */
            Exception e;
        };
        boolean z = false;
        if (this.mediaCodec != null) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            new Thread(new Runnable() {
                /* class org.webrtc.MediaCodecVideoEncoder.AnonymousClass1 */

                public void run() {
                    Logging.d(MediaCodecVideoEncoder.TAG, "Java releaseEncoder on release thread");
                    try {
                        MediaCodecVideoEncoder.this.mediaCodec.stop();
                    } catch (Exception e) {
                        Logging.e(MediaCodecVideoEncoder.TAG, "Media encoder stop failed", e);
                    }
                    try {
                        MediaCodecVideoEncoder.this.mediaCodec.release();
                    } catch (Exception e2) {
                        Logging.e(MediaCodecVideoEncoder.TAG, "Media encoder release failed", e2);
                        r0.e = e2;
                    }
                    Logging.d(MediaCodecVideoEncoder.TAG, "Java releaseEncoder on release thread done");
                    countDownLatch.countDown();
                }
            }).start();
            if (!ThreadUtils.awaitUninterruptibly(countDownLatch, 5000)) {
                Logging.e(TAG, "Media encoder release timeout");
                z = true;
            }
            this.mediaCodec = null;
        }
        this.mediaCodecThread = null;
        GlRectDrawer glRectDrawer = this.drawer;
        if (glRectDrawer != null) {
            glRectDrawer.release();
            this.drawer = null;
        }
        EglBase14 eglBase14 = this.eglBase;
        if (eglBase14 != null) {
            eglBase14.release();
            this.eglBase = null;
        }
        Surface surface = this.inputSurface;
        if (surface != null) {
            surface.release();
            this.inputSurface = null;
        }
        runningInstance = null;
        if (z) {
            codecErrors++;
            if (errorCallback != null) {
                Logging.e(TAG, "Invoke codec error callback. Errors: " + codecErrors);
                errorCallback.onMediaCodecVideoEncoderCriticalError(codecErrors);
            }
            throw new RuntimeException("Media encoder release timeout.");
        } else if (r0.e == null) {
            Logging.d(TAG, "Java releaseEncoder done");
        } else {
            RuntimeException runtimeException = new RuntimeException(r0.e);
            runtimeException.setStackTrace(ThreadUtils.concatStackTraces(r0.e.getStackTrace(), runtimeException.getStackTrace()));
            throw runtimeException;
        }
    }

    @CalledByNativeUnchecked
    private boolean setRates(int i, int i2) {
        int i3;
        checkOnMediaCodecThread();
        int i4 = i * 1000;
        if (this.bitrateAdjustmentType == BitrateAdjustmentType.DYNAMIC_ADJUSTMENT) {
            double d2 = (double) i4;
            this.bitrateAccumulatorMax = d2 / 8.0d;
            int i5 = this.targetBitrateBps;
            if (i5 > 0 && i4 < i5) {
                this.bitrateAccumulator = (this.bitrateAccumulator * d2) / ((double) i5);
            }
        }
        this.targetBitrateBps = i4;
        this.targetFps = i2;
        if (this.bitrateAdjustmentType == BitrateAdjustmentType.FRAMERATE_ADJUSTMENT && (i3 = this.targetFps) > 0) {
            i4 = (this.targetBitrateBps * 30) / i3;
            Logging.v(TAG, "setRates: " + i + " -> " + (i4 / 1000) + " kbps. Fps: " + this.targetFps);
        } else if (this.bitrateAdjustmentType == BitrateAdjustmentType.DYNAMIC_ADJUSTMENT) {
            Logging.v(TAG, "setRates: " + i + " kbps. Fps: " + this.targetFps + ". ExpScale: " + this.bitrateAdjustmentScaleExp);
            int i6 = this.bitrateAdjustmentScaleExp;
            if (i6 != 0) {
                i4 = (int) (((double) i4) * getBitrateScale(i6));
            }
        } else {
            Logging.v(TAG, "setRates: " + i + " kbps. Fps: " + this.targetFps);
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("video-bitrate", i4);
            this.mediaCodec.setParameters(bundle);
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "setRates failed", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    @CalledByNativeUnchecked
    public int dequeueInputBuffer() {
        checkOnMediaCodecThread();
        try {
            return this.mediaCodec.dequeueInputBuffer(0);
        } catch (IllegalStateException e) {
            Logging.e(TAG, "dequeueIntputBuffer failed", e);
            return -2;
        }
    }

    static class OutputBufferInfo {
        public final ByteBuffer buffer;
        public final int index;
        public final boolean isKeyFrame;
        public final long presentationTimestampUs;

        public OutputBufferInfo(int i, ByteBuffer byteBuffer, boolean z, long j) {
            this.index = i;
            this.buffer = byteBuffer;
            this.isKeyFrame = z;
            this.presentationTimestampUs = j;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("OutputBufferInfo")
        public int getIndex() {
            return this.index;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("OutputBufferInfo")
        public ByteBuffer getBuffer() {
            return this.buffer;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("OutputBufferInfo")
        public boolean isKeyFrame() {
            return this.isKeyFrame;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("OutputBufferInfo")
        public long getPresentationTimestampUs() {
            return this.presentationTimestampUs;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00ad A[Catch:{ IllegalStateException -> 0x0176 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0142 A[Catch:{ IllegalStateException -> 0x0176 }] */
    @org.webrtc.CalledByNativeUnchecked
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.webrtc.MediaCodecVideoEncoder.OutputBufferInfo dequeueOutputBuffer() {
        /*
        // Method dump skipped, instructions count: 394
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.MediaCodecVideoEncoder.dequeueOutputBuffer():org.webrtc.MediaCodecVideoEncoder$OutputBufferInfo");
    }

    private double getBitrateScale(int i) {
        return Math.pow(BITRATE_CORRECTION_MAX_SCALE, ((double) i) / 20.0d);
    }

    private void reportEncodedFrame(int i) {
        if (this.targetFps != 0 && this.bitrateAdjustmentType == BitrateAdjustmentType.DYNAMIC_ADJUSTMENT) {
            int i2 = this.targetFps;
            this.bitrateAccumulator += ((double) i) - (((double) this.targetBitrateBps) / (((double) i2) * 8.0d));
            this.bitrateObservationTimeMs += 1000.0d / ((double) i2);
            double d2 = this.bitrateAccumulatorMax * BITRATE_CORRECTION_SEC;
            this.bitrateAccumulator = Math.min(this.bitrateAccumulator, d2);
            this.bitrateAccumulator = Math.max(this.bitrateAccumulator, -d2);
            if (this.bitrateObservationTimeMs > 3000.0d) {
                Logging.d(TAG, "Acc: " + ((int) this.bitrateAccumulator) + ". Max: " + ((int) this.bitrateAccumulatorMax) + ". ExpScale: " + this.bitrateAdjustmentScaleExp);
                double d3 = this.bitrateAccumulator;
                double d4 = this.bitrateAccumulatorMax;
                boolean z = true;
                if (d3 > d4) {
                    this.bitrateAdjustmentScaleExp -= (int) ((d3 / d4) + 0.5d);
                    this.bitrateAccumulator = d4;
                } else if (d3 < (-d4)) {
                    this.bitrateAdjustmentScaleExp += (int) (((-d3) / d4) + 0.5d);
                    this.bitrateAccumulator = -d4;
                } else {
                    z = false;
                }
                if (z) {
                    this.bitrateAdjustmentScaleExp = Math.min(this.bitrateAdjustmentScaleExp, 20);
                    this.bitrateAdjustmentScaleExp = Math.max(this.bitrateAdjustmentScaleExp, -20);
                    Logging.d(TAG, "Adjusting bitrate scale to " + this.bitrateAdjustmentScaleExp + ". Value: " + getBitrateScale(this.bitrateAdjustmentScaleExp));
                    setRates(this.targetBitrateBps / 1000, this.targetFps);
                }
                this.bitrateObservationTimeMs = 0.0d;
            }
        }
    }

    /* access modifiers changed from: package-private */
    @CalledByNativeUnchecked
    public boolean releaseOutputBuffer(int i) {
        checkOnMediaCodecThread();
        try {
            this.mediaCodec.releaseOutputBuffer(i, false);
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "releaseOutputBuffer failed", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public int getColorFormat() {
        return this.colorFormat;
    }

    @CalledByNative
    static boolean isTextureBuffer(VideoFrame.Buffer buffer) {
        return buffer instanceof VideoFrame.TextureBuffer;
    }
}
