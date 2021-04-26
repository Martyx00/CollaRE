package org.spongycastle.math.ec;

import java.math.BigInteger;

public class ReferenceMultiplier extends AbstractECMultiplier {
    /* access modifiers changed from: protected */
    @Override // org.spongycastle.math.ec.AbstractECMultiplier
    public ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        return ECAlgorithms.referenceMultiply(eCPoint, bigInteger);
    }
}
