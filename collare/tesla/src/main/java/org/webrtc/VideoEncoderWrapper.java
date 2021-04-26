package org.webrtc;

import java.nio.ByteBuffer;
import org.webrtc.VideoEncoder;

class VideoEncoderWrapper {
    /* access modifiers changed from: private */
    public static native void nativeOnEncodedFrame(long j, ByteBuffer byteBuffer, int i, int i2, long j2, int i3, int i4, boolean z, Integer num);

    VideoEncoderWrapper() {
    }

    @CalledByNative
    static boolean getScalingSettingsOn(VideoEncoder.ScalingSettings scalingSettings) {
        return scalingSettings.on;
    }

    @CalledByNative
    static Integer getScalingSettingsLow(VideoEncoder.ScalingSettings scalingSettings) {
        return scalingSettings.low;
    }

    @CalledByNative
    static Integer getScalingSettingsHigh(VideoEncoder.ScalingSettings scalingSettings) {
        return scalingSettings.high;
    }

    @CalledByNative
    static VideoEncoder.Callback createEncoderCallback(long j) {
        return new VideoEncoder.Callback(j) {
            /* class org.webrtc.$$Lambda$VideoEncoderWrapper$dRHADZXMoanHTOdEf2Act96Dzi0 */
            private final /* synthetic */ long f$0;

            {
                this.f$0 = r1;
            }

            @Override // org.webrtc.VideoEncoder.Callback
            public final void onEncodedFrame(EncodedImage encodedImage, VideoEncoder.CodecSpecificInfo codecSpecificInfo) {
                VideoEncoderWrapper.lambda$createEncoderCallback$0(this.f$0, encodedImage, codecSpecificInfo);
            }
        };
    }
}
