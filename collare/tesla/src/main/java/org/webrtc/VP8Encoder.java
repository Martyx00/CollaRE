package org.webrtc;

class VP8Encoder extends WrappedNativeVideoEncoder {
    static native long nativeCreateEncoder();

    @Override // org.webrtc.VideoEncoder, org.webrtc.WrappedNativeVideoEncoder
    public boolean isHardwareEncoder() {
        return false;
    }

    VP8Encoder() {
    }

    @Override // org.webrtc.VideoEncoder, org.webrtc.WrappedNativeVideoEncoder
    public long createNativeVideoEncoder() {
        return nativeCreateEncoder();
    }
}
