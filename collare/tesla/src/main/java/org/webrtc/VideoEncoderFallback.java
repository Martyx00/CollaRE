package org.webrtc;

import org.webrtc.VideoEncoder;

public class VideoEncoderFallback extends WrappedNativeVideoEncoder {
    private final VideoEncoder fallback;
    private final VideoEncoder primary;

    private static native long nativeCreateEncoder(VideoEncoder videoEncoder, VideoEncoder videoEncoder2);

    @Override // org.webrtc.VideoEncoder, org.webrtc.WrappedNativeVideoEncoder
    public /* bridge */ /* synthetic */ VideoCodecStatus encode(VideoFrame videoFrame, VideoEncoder.EncodeInfo encodeInfo) {
        return super.encode(videoFrame, encodeInfo);
    }

    @Override // org.webrtc.VideoEncoder, org.webrtc.WrappedNativeVideoEncoder
    public /* bridge */ /* synthetic */ String getImplementationName() {
        return super.getImplementationName();
    }

    @Override // org.webrtc.VideoEncoder, org.webrtc.WrappedNativeVideoEncoder
    public /* bridge */ /* synthetic */ VideoEncoder.ScalingSettings getScalingSettings() {
        return super.getScalingSettings();
    }

    @Override // org.webrtc.VideoEncoder, org.webrtc.WrappedNativeVideoEncoder
    public /* bridge */ /* synthetic */ VideoCodecStatus initEncode(VideoEncoder.Settings settings, VideoEncoder.Callback callback) {
        return super.initEncode(settings, callback);
    }

    @Override // org.webrtc.VideoEncoder, org.webrtc.WrappedNativeVideoEncoder
    public /* bridge */ /* synthetic */ VideoCodecStatus release() {
        return super.release();
    }

    @Override // org.webrtc.VideoEncoder, org.webrtc.WrappedNativeVideoEncoder
    public /* bridge */ /* synthetic */ VideoCodecStatus setChannelParameters(short s, long j) {
        return super.setChannelParameters(s, j);
    }

    @Override // org.webrtc.VideoEncoder, org.webrtc.WrappedNativeVideoEncoder
    public /* bridge */ /* synthetic */ VideoCodecStatus setRateAllocation(VideoEncoder.BitrateAllocation bitrateAllocation, int i) {
        return super.setRateAllocation(bitrateAllocation, i);
    }

    public VideoEncoderFallback(VideoEncoder videoEncoder, VideoEncoder videoEncoder2) {
        this.fallback = videoEncoder;
        this.primary = videoEncoder2;
    }

    @Override // org.webrtc.VideoEncoder, org.webrtc.WrappedNativeVideoEncoder
    public long createNativeVideoEncoder() {
        return nativeCreateEncoder(this.fallback, this.primary);
    }

    @Override // org.webrtc.VideoEncoder, org.webrtc.WrappedNativeVideoEncoder
    public boolean isHardwareEncoder() {
        return this.primary.isHardwareEncoder();
    }
}
