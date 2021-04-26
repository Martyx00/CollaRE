package org.webrtc;

/* access modifiers changed from: package-private */
public class FramerateBitrateAdjuster extends BaseBitrateAdjuster {
    private static final int INITIAL_FPS = 30;

    @Override // org.webrtc.BitrateAdjuster, org.webrtc.BaseBitrateAdjuster
    public int getCodecConfigFramerate() {
        return 30;
    }

    FramerateBitrateAdjuster() {
    }

    @Override // org.webrtc.BitrateAdjuster, org.webrtc.BaseBitrateAdjuster
    public void setTargets(int i, int i2) {
        if (this.targetFps == 0) {
            i2 = 30;
        }
        super.setTargets(i, i2);
        this.targetBitrateBps = (this.targetBitrateBps * 30) / this.targetFps;
    }
}
