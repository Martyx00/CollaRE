package org.webrtc;

public class AudioTrack extends MediaStreamTrack {
    private static native void nativeSetVolume(long j, double d2);

    public AudioTrack(long j) {
        super(j);
    }

    public void setVolume(double d2) {
        nativeSetVolume(this.nativeTrack, d2);
    }
}
