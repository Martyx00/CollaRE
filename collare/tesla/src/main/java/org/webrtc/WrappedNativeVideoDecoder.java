package org.webrtc;

import org.webrtc.VideoDecoder;

/* access modifiers changed from: package-private */
public abstract class WrappedNativeVideoDecoder implements VideoDecoder {
    @Override // org.webrtc.VideoDecoder
    public abstract long createNativeVideoDecoder();

    WrappedNativeVideoDecoder() {
    }

    @Override // org.webrtc.VideoDecoder
    public VideoCodecStatus initDecode(VideoDecoder.Settings settings, VideoDecoder.Callback callback) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // org.webrtc.VideoDecoder
    public VideoCodecStatus release() {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // org.webrtc.VideoDecoder
    public VideoCodecStatus decode(EncodedImage encodedImage, VideoDecoder.DecodeInfo decodeInfo) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // org.webrtc.VideoDecoder
    public boolean getPrefersLateDecoding() {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // org.webrtc.VideoDecoder
    public String getImplementationName() {
        throw new UnsupportedOperationException("Not implemented.");
    }
}
