package org.webrtc;

public class MediaSource {
    final long nativeSource;

    private static native State nativeGetState(long j);

    public enum State {
        INITIALIZING,
        LIVE,
        ENDED,
        MUTED;

        @CalledByNative("State")
        static State fromNativeIndex(int i) {
            return values()[i];
        }
    }

    public MediaSource(long j) {
        this.nativeSource = j;
    }

    public State state() {
        return nativeGetState(this.nativeSource);
    }

    public void dispose() {
        JniCommon.nativeReleaseRef(this.nativeSource);
    }
}
