package org.webrtc;

import org.webrtc.VideoEncoder;

/* access modifiers changed from: package-private */
public abstract class WrappedNativeVideoEncoder implements VideoEncoder {
    @Override // org.webrtc.VideoEncoder
    public abstract long createNativeVideoEncoder();

    @Override // org.webrtc.VideoEncoder
    public abstract boolean isHardwareEncoder();

    WrappedNativeVideoEncoder() {
    }

    @Override // org.webrtc.VideoEncoder
    public VideoCodecStatus initEncode(VideoEncoder.Settings settings, VideoEncoder.Callback callback) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // org.webrtc.VideoEncoder
    public VideoCodecStatus release() {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // org.webrtc.VideoEncoder
    public VideoCodecStatus encode(VideoFrame videoFrame, VideoEncoder.EncodeInfo encodeInfo) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // org.webrtc.VideoEncoder
    public VideoCodecStatus setChannelParameters(short s, long j) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // org.webrtc.VideoEncoder
    public VideoCodecStatus setRateAllocation(VideoEncoder.BitrateAllocation bitrateAllocation, int i) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // org.webrtc.VideoEncoder
    public VideoEncoder.ScalingSettings getScalingSettings() {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // org.webrtc.VideoEncoder
    public String getImplementationName() {
        throw new UnsupportedOperationException("Not implemented.");
    }
}
