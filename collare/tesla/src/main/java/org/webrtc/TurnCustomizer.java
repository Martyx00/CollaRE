package org.webrtc;

public class TurnCustomizer {
    final long nativeTurnCustomizer;

    private static native void nativeFreeTurnCustomizer(long j);

    public TurnCustomizer(long j) {
        this.nativeTurnCustomizer = j;
    }

    public void dispose() {
        nativeFreeTurnCustomizer(this.nativeTurnCustomizer);
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public long getNativeTurnCustomizer() {
        return this.nativeTurnCustomizer;
    }
}
