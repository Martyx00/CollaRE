package org.webrtc;

class BaseBitrateAdjuster implements BitrateAdjuster {
    protected int targetBitrateBps = 0;
    protected int targetFps = 0;

    @Override // org.webrtc.BitrateAdjuster
    public void reportEncodedFrame(int i) {
    }

    BaseBitrateAdjuster() {
    }

    @Override // org.webrtc.BitrateAdjuster
    public void setTargets(int i, int i2) {
        this.targetBitrateBps = i;
        this.targetFps = i2;
    }

    @Override // org.webrtc.BitrateAdjuster
    public int getAdjustedBitrateBps() {
        return this.targetBitrateBps;
    }

    @Override // org.webrtc.BitrateAdjuster
    public int getCodecConfigFramerate() {
        return this.targetFps;
    }
}
