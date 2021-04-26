package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class ECPrivateKeyParameters extends ECKeyParameters {

    /* renamed from: d  reason: collision with root package name */
    BigInteger f6408d;

    public ECPrivateKeyParameters(BigInteger bigInteger, ECDomainParameters eCDomainParameters) {
        super(true, eCDomainParameters);
        this.f6408d = bigInteger;
    }

    public BigInteger getD() {
        return this.f6408d;
    }
}
