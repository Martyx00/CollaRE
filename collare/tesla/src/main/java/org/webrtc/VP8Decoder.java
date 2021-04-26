package org.webrtc;

class VP8Decoder extends WrappedNativeVideoDecoder {
    static native long nativeCreateDecoder();

    VP8Decoder() {
    }

    @Override // org.webrtc.WrappedNativeVideoDecoder, org.webrtc.VideoDecoder
    public long createNativeVideoDecoder() {
        return nativeCreateDecoder();
    }
}
