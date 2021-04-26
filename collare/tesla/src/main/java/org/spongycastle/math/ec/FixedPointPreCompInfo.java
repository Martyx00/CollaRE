package org.spongycastle.math.ec;

public class FixedPointPreCompInfo implements PreCompInfo {
    protected ECPoint[] preComp = null;
    protected int width = -1;

    public ECPoint[] getPreComp() {
        return this.preComp;
    }

    public void setPreComp(ECPoint[] eCPointArr) {
        this.preComp = eCPointArr;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }
}
