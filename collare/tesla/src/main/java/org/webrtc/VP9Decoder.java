package org.webrtc;

/* access modifiers changed from: package-private */
public class VP9Decoder extends WrappedNativeVideoDecoder {
    static native long nativeCreateDecoder();

    static native boolean nativeIsSupported();

    VP9Decoder() {
    }

    @Override // org.webrtc.WrappedNativeVideoDecoder, org.webrtc.VideoDecoder
    public long createNativeVideoDecoder() {
        return nativeCreateDecoder();
    }
}
