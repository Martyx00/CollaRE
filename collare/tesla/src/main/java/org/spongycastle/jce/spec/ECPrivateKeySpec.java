package org.spongycastle.jce.spec;

import java.math.BigInteger;

public class ECPrivateKeySpec extends ECKeySpec {

    /* renamed from: d  reason: collision with root package name */
    private BigInteger f6421d;

    public ECPrivateKeySpec(BigInteger bigInteger, ECParameterSpec eCParameterSpec) {
        super(eCParameterSpec);
        this.f6421d = bigInteger;
    }

    public BigInteger getD() {
        return this.f6421d;
    }
}
