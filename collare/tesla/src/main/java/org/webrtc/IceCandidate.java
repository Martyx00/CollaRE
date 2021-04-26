package org.webrtc;

public class IceCandidate {
    public final String sdp;
    public final int sdpMLineIndex;
    public final String sdpMid;
    public final String serverUrl;

    public IceCandidate(String str, int i, String str2) {
        this.sdpMid = str;
        this.sdpMLineIndex = i;
        this.sdp = str2;
        this.serverUrl = "";
    }

    @CalledByNative
    IceCandidate(String str, int i, String str2, String str3) {
        this.sdpMid = str;
        this.sdpMLineIndex = i;
        this.sdp = str2;
        this.serverUrl = str3;
    }

    public String toString() {
        return this.sdpMid + ":" + this.sdpMLineIndex + ":" + this.sdp + ":" + this.serverUrl;
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public String getSdpMid() {
        return this.sdpMid;
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public String getSdp() {
        return this.sdp;
    }
}
