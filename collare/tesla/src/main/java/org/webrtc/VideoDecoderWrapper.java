package org.webrtc;

import org.webrtc.VideoDecoder;

class VideoDecoderWrapper {
    /* access modifiers changed from: private */
    public static native void nativeOnDecodedFrame(long j, VideoFrame videoFrame, Integer num, Integer num2);

    VideoDecoderWrapper() {
    }

    @CalledByNative
    static VideoDecoder.Callback createDecoderCallback(long j) {
        return new VideoDecoder.Callback(j) {
            /* class org.webrtc.$$Lambda$VideoDecoderWrapper$m_PB1aePENdovsbFJCs5jSyVCEo */
            private final /* synthetic */ long f$0;

            {
                this.f$0 = r1;
            }

            @Override // org.webrtc.VideoDecoder.Callback
            public final void onDecodedFrame(VideoFrame videoFrame, Integer num, Integer num2) {
                VideoDecoderWrapper.lambda$createDecoderCallback$0(this.f$0, videoFrame, num, num2);
            }
        };
    }
}
