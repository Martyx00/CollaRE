package org.webrtc;

/* access modifiers changed from: package-private */
public class VP9Encoder extends WrappedNativeVideoEncoder {
    static native long nativeCreateEncoder();

    static native boolean nativeIsSupported();

    @Override // org.webrtc.VideoEncoder, org.webrtc.WrappedNativeVideoEncoder
    public boolean isHardwareEncoder() {
        return false;
    }

    VP9Encoder() {
    }

    @Override // org.webrtc.VideoEncoder, org.webrtc.WrappedNativeVideoEncoder
    public long createNativeVideoEncoder() {
        return nativeCreateEncoder();
    }
}
