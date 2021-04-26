package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

public class GOST3410Parameters implements CipherParameters {

    /* renamed from: a  reason: collision with root package name */
    private BigInteger f6409a;
    private BigInteger p;
    private BigInteger q;
    private GOST3410ValidationParameters validation;

    public GOST3410Parameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.p = bigInteger;
        this.q = bigInteger2;
        this.f6409a = bigInteger3;
    }

    public GOST3410Parameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, GOST3410ValidationParameters gOST3410ValidationParameters) {
        this.f6409a = bigInteger3;
        this.p = bigInteger;
        this.q = bigInteger2;
        this.validation = gOST3410ValidationParameters;
    }

    public BigInteger getP() {
        return this.p;
    }

    public BigInteger getQ() {
        return this.q;
    }

    public BigInteger getA() {
        return this.f6409a;
    }

    public GOST3410ValidationParameters getValidationParameters() {
        return this.validation;
    }

    public int hashCode() {
        return (this.p.hashCode() ^ this.q.hashCode()) ^ this.f6409a.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GOST3410Parameters)) {
            return false;
        }
        GOST3410Parameters gOST3410Parameters = (GOST3410Parameters) obj;
        if (!gOST3410Parameters.getP().equals(this.p) || !gOST3410Parameters.getQ().equals(this.q) || !gOST3410Parameters.getA().equals(this.f6409a)) {
            return false;
        }
        return true;
    }
}
