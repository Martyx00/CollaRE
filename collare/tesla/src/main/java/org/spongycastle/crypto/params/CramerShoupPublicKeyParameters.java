package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class CramerShoupPublicKeyParameters extends CramerShoupKeyParameters {

    /* renamed from: c  reason: collision with root package name */
    private BigInteger f6406c;

    /* renamed from: d  reason: collision with root package name */
    private BigInteger f6407d;
    private BigInteger h;

    public CramerShoupPublicKeyParameters(CramerShoupParameters cramerShoupParameters, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        super(false, cramerShoupParameters);
        this.f6406c = bigInteger;
        this.f6407d = bigInteger2;
        this.h = bigInteger3;
    }

    public BigInteger getC() {
        return this.f6406c;
    }

    public BigInteger getD() {
        return this.f6407d;
    }

    public BigInteger getH() {
        return this.h;
    }

    @Override // org.spongycastle.crypto.params.CramerShoupKeyParameters
    public int hashCode() {
        return ((this.f6406c.hashCode() ^ this.f6407d.hashCode()) ^ this.h.hashCode()) ^ super.hashCode();
    }

    @Override // org.spongycastle.crypto.params.CramerShoupKeyParameters
    public boolean equals(Object obj) {
        if (!(obj instanceof CramerShoupPublicKeyParameters)) {
            return false;
        }
        CramerShoupPublicKeyParameters cramerShoupPublicKeyParameters = (CramerShoupPublicKeyParameters) obj;
        if (!cramerShoupPublicKeyParameters.getC().equals(this.f6406c) || !cramerShoupPublicKeyParameters.getD().equals(this.f6407d) || !cramerShoupPublicKeyParameters.getH().equals(this.h) || !super.equals(obj)) {
            return false;
        }
        return true;
    }
}
