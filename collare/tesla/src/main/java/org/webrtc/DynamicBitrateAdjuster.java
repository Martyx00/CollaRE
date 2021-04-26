package org.webrtc;

/* access modifiers changed from: package-private */
public class DynamicBitrateAdjuster extends BaseBitrateAdjuster {
    private static final double BITRATE_ADJUSTMENT_MAX_SCALE = 4.0d;
    private static final double BITRATE_ADJUSTMENT_SEC = 3.0d;
    private static final int BITRATE_ADJUSTMENT_STEPS = 20;
    private static final double BITS_PER_BYTE = 8.0d;
    private int bitrateAdjustmentScaleExp = 0;
    private double deviationBytes = 0.0d;
    private double timeSinceLastAdjustmentMs = 0.0d;

    DynamicBitrateAdjuster() {
    }

    @Override // org.webrtc.BitrateAdjuster, org.webrtc.BaseBitrateAdjuster
    public void setTargets(int i, int i2) {
        if (this.targetBitrateBps > 0 && i < this.targetBitrateBps) {
            this.deviationBytes = (this.deviationBytes * ((double) i)) / ((double) this.targetBitrateBps);
        }
        super.setTargets(i, i2);
    }

    @Override // org.webrtc.BitrateAdjuster, org.webrtc.BaseBitrateAdjuster
    public void reportEncodedFrame(int i) {
        if (this.targetFps != 0) {
            this.deviationBytes += ((double) i) - ((((double) this.targetBitrateBps) / BITS_PER_BYTE) / ((double) this.targetFps));
            this.timeSinceLastAdjustmentMs += 1000.0d / ((double) this.targetFps);
            double d2 = ((double) this.targetBitrateBps) / BITS_PER_BYTE;
            double d3 = BITRATE_ADJUSTMENT_SEC * d2;
            this.deviationBytes = Math.min(this.deviationBytes, d3);
            this.deviationBytes = Math.max(this.deviationBytes, -d3);
            if (this.timeSinceLastAdjustmentMs > 3000.0d) {
                double d4 = this.deviationBytes;
                if (d4 > d2) {
                    this.bitrateAdjustmentScaleExp -= (int) ((d4 / d2) + 0.5d);
                    this.bitrateAdjustmentScaleExp = Math.max(this.bitrateAdjustmentScaleExp, -20);
                    this.deviationBytes = d2;
                } else {
                    double d5 = -d2;
                    if (d4 < d5) {
                        this.bitrateAdjustmentScaleExp += (int) (((-d4) / d2) + 0.5d);
                        this.bitrateAdjustmentScaleExp = Math.min(this.bitrateAdjustmentScaleExp, 20);
                        this.deviationBytes = d5;
                    }
                }
                this.timeSinceLastAdjustmentMs = 0.0d;
            }
        }
    }

    private double getBitrateAdjustmentScale() {
        return Math.pow(BITRATE_ADJUSTMENT_MAX_SCALE, ((double) this.bitrateAdjustmentScaleExp) / 20.0d);
    }

    @Override // org.webrtc.BitrateAdjuster, org.webrtc.BaseBitrateAdjuster
    public int getAdjustedBitrateBps() {
        return (int) (((double) this.targetBitrateBps) * getBitrateAdjustmentScale());
    }
}
