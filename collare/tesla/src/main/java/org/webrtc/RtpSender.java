package org.webrtc;

public class RtpSender {
    private MediaStreamTrack cachedTrack;
    private final DtmfSender dtmfSender;
    final long nativeRtpSender;
    private boolean ownsTrack = true;

    private static native long nativeGetDtmfSender(long j);

    private static native String nativeGetId(long j);

    private static native RtpParameters nativeGetParameters(long j);

    private static native long nativeGetTrack(long j);

    private static native boolean nativeSetParameters(long j, RtpParameters rtpParameters);

    private static native boolean nativeSetTrack(long j, long j2);

    @CalledByNative
    public RtpSender(long j) {
        this.nativeRtpSender = j;
        this.cachedTrack = MediaStreamTrack.createMediaStreamTrack(nativeGetTrack(j));
        long nativeGetDtmfSender = nativeGetDtmfSender(j);
        this.dtmfSender = nativeGetDtmfSender != 0 ? new DtmfSender(nativeGetDtmfSender) : null;
    }

    public boolean setTrack(MediaStreamTrack mediaStreamTrack, boolean z) {
        if (!nativeSetTrack(this.nativeRtpSender, mediaStreamTrack == null ? 0 : mediaStreamTrack.nativeTrack)) {
            return false;
        }
        MediaStreamTrack mediaStreamTrack2 = this.cachedTrack;
        if (mediaStreamTrack2 != null && this.ownsTrack) {
            mediaStreamTrack2.dispose();
        }
        this.cachedTrack = mediaStreamTrack;
        this.ownsTrack = z;
        return true;
    }

    public MediaStreamTrack track() {
        return this.cachedTrack;
    }

    public boolean setParameters(RtpParameters rtpParameters) {
        return nativeSetParameters(this.nativeRtpSender, rtpParameters);
    }

    public RtpParameters getParameters() {
        return nativeGetParameters(this.nativeRtpSender);
    }

    public String id() {
        return nativeGetId(this.nativeRtpSender);
    }

    public DtmfSender dtmf() {
        return this.dtmfSender;
    }

    public void dispose() {
        DtmfSender dtmfSender2 = this.dtmfSender;
        if (dtmfSender2 != null) {
            dtmfSender2.dispose();
        }
        MediaStreamTrack mediaStreamTrack = this.cachedTrack;
        if (mediaStreamTrack != null && this.ownsTrack) {
            mediaStreamTrack.dispose();
        }
        JniCommon.nativeReleaseRef(this.nativeRtpSender);
    }
}
