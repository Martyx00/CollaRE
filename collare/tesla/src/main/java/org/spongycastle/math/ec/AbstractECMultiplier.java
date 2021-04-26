package org.spongycastle.math.ec;

import java.math.BigInteger;

public abstract class AbstractECMultiplier implements ECMultiplier {
    /* access modifiers changed from: protected */
    public abstract ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger);

    @Override // org.spongycastle.math.ec.ECMultiplier
    public ECPoint multiply(ECPoint eCPoint, BigInteger bigInteger) {
        int signum = bigInteger.signum();
        if (signum == 0 || eCPoint.isInfinity()) {
            return eCPoint.getCurve().getInfinity();
        }
        ECPoint multiplyPositive = multiplyPositive(eCPoint, bigInteger.abs());
        if (signum <= 0) {
            multiplyPositive = multiplyPositive.negate();
        }
        return ECAlgorithms.validatePoint(multiplyPositive);
    }
}
