package org.spongycastle.jce.spec;

import java.math.BigInteger;

public class GOST3410PublicKeyParameterSetSpec {

    /* renamed from: a  reason: collision with root package name */
    private BigInteger f6423a;
    private BigInteger p;
    private BigInteger q;

    public GOST3410PublicKeyParameterSetSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.p = bigInteger;
        this.q = bigInteger2;
        this.f6423a = bigInteger3;
    }

    public BigInteger getP() {
        return this.p;
    }

    public BigInteger getQ() {
        return this.q;
    }

    public BigInteger getA() {
        return this.f6423a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GOST3410PublicKeyParameterSetSpec)) {
            return false;
        }
        GOST3410PublicKeyParameterSetSpec gOST3410PublicKeyParameterSetSpec = (GOST3410PublicKeyParameterSetSpec) obj;
        if (!this.f6423a.equals(gOST3410PublicKeyParameterSetSpec.f6423a) || !this.p.equals(gOST3410PublicKeyParameterSetSpec.p) || !this.q.equals(gOST3410PublicKeyParameterSetSpec.q)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.f6423a.hashCode() ^ this.p.hashCode()) ^ this.q.hashCode();
    }
}
