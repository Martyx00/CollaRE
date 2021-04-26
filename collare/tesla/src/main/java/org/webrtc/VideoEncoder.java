package org.webrtc;

import org.webrtc.EncodedImage;

public interface VideoEncoder {

    /* renamed from: org.webrtc.VideoEncoder$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        @CalledByNative
        public static long $default$createNativeVideoEncoder(VideoEncoder videoEncoder) {
            return 0;
        }

        @CalledByNative
        public static boolean $default$isHardwareEncoder(VideoEncoder videoEncoder) {
            return true;
        }
    }

    public interface Callback {
        void onEncodedFrame(EncodedImage encodedImage, CodecSpecificInfo codecSpecificInfo);
    }

    public static class CodecSpecificInfo {
    }

    public static class CodecSpecificInfoH264 extends CodecSpecificInfo {
    }

    public static class CodecSpecificInfoVP8 extends CodecSpecificInfo {
    }

    public static class CodecSpecificInfoVP9 extends CodecSpecificInfo {
    }

    @CalledByNative
    long createNativeVideoEncoder();

    @CalledByNative
    VideoCodecStatus encode(VideoFrame videoFrame, EncodeInfo encodeInfo);

    @CalledByNative
    String getImplementationName();

    @CalledByNative
    ScalingSettings getScalingSettings();

    @CalledByNative
    VideoCodecStatus initEncode(Settings settings, Callback callback);

    @CalledByNative
    boolean isHardwareEncoder();

    @CalledByNative
    VideoCodecStatus release();

    @CalledByNative
    VideoCodecStatus setChannelParameters(short s, long j);

    @CalledByNative
    VideoCodecStatus setRateAllocation(BitrateAllocation bitrateAllocation, int i);

    public static class Settings {
        public final boolean automaticResizeOn;
        public final int height;
        public final int maxFramerate;
        public final int numberOfCores;
        public final int startBitrate;
        public final int width;

        @CalledByNative("Settings")
        public Settings(int i, int i2, int i3, int i4, int i5, boolean z) {
            this.numberOfCores = i;
            this.width = i2;
            this.height = i3;
            this.startBitrate = i4;
            this.maxFramerate = i5;
            this.automaticResizeOn = z;
        }
    }

    public static class EncodeInfo {
        public final EncodedImage.FrameType[] frameTypes;

        @CalledByNative("EncodeInfo")
        public EncodeInfo(EncodedImage.FrameType[] frameTypeArr) {
            this.frameTypes = frameTypeArr;
        }
    }

    public static class BitrateAllocation {
        public final int[][] bitratesBbs;

        @CalledByNative("BitrateAllocation")
        public BitrateAllocation(int[][] iArr) {
            this.bitratesBbs = iArr;
        }

        public int getSum() {
            int[][] iArr = this.bitratesBbs;
            int length = iArr.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = i2;
                for (int i4 : iArr[i]) {
                    i3 += i4;
                }
                i++;
                i2 = i3;
            }
            return i2;
        }
    }

    public static class ScalingSettings {
        public static final ScalingSettings OFF = new ScalingSettings();
        public final Integer high;
        public final Integer low;
        public final boolean on;

        public ScalingSettings(int i, int i2) {
            this.on = true;
            this.low = Integer.valueOf(i);
            this.high = Integer.valueOf(i2);
        }

        private ScalingSettings() {
            this.on = false;
            this.low = null;
            this.high = null;
        }

        @Deprecated
        public ScalingSettings(boolean z) {
            this.on = z;
            this.low = null;
            this.high = null;
        }

        @Deprecated
        public ScalingSettings(boolean z, int i, int i2) {
            this.on = z;
            this.low = Integer.valueOf(i);
            this.high = Integer.valueOf(i2);
        }

        public String toString() {
            if (!this.on) {
                return "OFF";
            }
            return "[ " + this.low + ", " + this.high + " ]";
        }
    }
}
