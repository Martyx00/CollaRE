package org.spongycastle.math.ec;

import java.math.BigInteger;

public class FixedPointUtil {
    public static final String PRECOMP_NAME = "bc_fixed_point";

    public static int getCombSize(ECCurve eCCurve) {
        BigInteger order = eCCurve.getOrder();
        return order == null ? eCCurve.getFieldSize() + 1 : order.bitLength();
    }

    public static FixedPointPreCompInfo getFixedPointPreCompInfo(PreCompInfo preCompInfo) {
        if (preCompInfo == null || !(preCompInfo instanceof FixedPointPreCompInfo)) {
            return new FixedPointPreCompInfo();
        }
        return (FixedPointPreCompInfo) preCompInfo;
    }

    public static FixedPointPreCompInfo precompute(ECPoint eCPoint, int i) {
        ECCurve curve = eCPoint.getCurve();
        int i2 = 1 << i;
        FixedPointPreCompInfo fixedPointPreCompInfo = getFixedPointPreCompInfo(curve.getPreCompInfo(eCPoint, PRECOMP_NAME));
        ECPoint[] preComp = fixedPointPreCompInfo.getPreComp();
        if (preComp == null || preComp.length < i2) {
            int combSize = ((getCombSize(curve) + i) - 1) / i;
            ECPoint[] eCPointArr = new ECPoint[i];
            eCPointArr[0] = eCPoint;
            for (int i3 = 1; i3 < i; i3++) {
                eCPointArr[i3] = eCPointArr[i3 - 1].timesPow2(combSize);
            }
            curve.normalizeAll(eCPointArr);
            ECPoint[] eCPointArr2 = new ECPoint[i2];
            eCPointArr2[0] = curve.getInfinity();
            for (int i4 = i - 1; i4 >= 0; i4--) {
                ECPoint eCPoint2 = eCPointArr[i4];
                int i5 = 1 << i4;
                for (int i6 = i5; i6 < i2; i6 += i5 << 1) {
                    eCPointArr2[i6] = eCPointArr2[i6 - i5].add(eCPoint2);
                }
            }
            curve.normalizeAll(eCPointArr2);
            fixedPointPreCompInfo.setPreComp(eCPointArr2);
            fixedPointPreCompInfo.setWidth(i);
            curve.setPreCompInfo(eCPoint, PRECOMP_NAME, fixedPointPreCompInfo);
        }
        return fixedPointPreCompInfo;
    }
}
