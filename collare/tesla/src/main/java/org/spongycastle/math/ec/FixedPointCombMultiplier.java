package org.spongycastle.math.ec;

import java.math.BigInteger;

public class FixedPointCombMultiplier extends AbstractECMultiplier {
    /* access modifiers changed from: protected */
    public int getWidthForCombSize(int i) {
        return i > 257 ? 6 : 5;
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.math.ec.AbstractECMultiplier
    public ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        ECCurve curve = eCPoint.getCurve();
        int combSize = FixedPointUtil.getCombSize(curve);
        if (bigInteger.bitLength() <= combSize) {
            FixedPointPreCompInfo precompute = FixedPointUtil.precompute(eCPoint, getWidthForCombSize(combSize));
            ECPoint[] preComp = precompute.getPreComp();
            int width = precompute.getWidth();
            int i = ((combSize + width) - 1) / width;
            int i2 = (width * i) - 1;
            ECPoint infinity = curve.getInfinity();
            for (int i3 = 0; i3 < i; i3++) {
                int i4 = 0;
                for (int i5 = i2 - i3; i5 >= 0; i5 -= i) {
                    i4 <<= 1;
                    if (bigInteger.testBit(i5)) {
                        i4 |= 1;
                    }
                }
                infinity = infinity.twicePlus(preComp[i4]);
            }
            return infinity;
        }
        throw new IllegalStateException("fixed-point comb doesn't support scalars larger than the curve order");
    }
}
