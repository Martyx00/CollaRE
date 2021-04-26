package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class CramerShoupPrivateKeyParameters extends CramerShoupKeyParameters {
    private CramerShoupPublicKeyParameters pk;
    private BigInteger x1;
    private BigInteger x2;
    private BigInteger y1;
    private BigInteger y2;
    private BigInteger z;

    public CramerShoupPrivateKeyParameters(CramerShoupParameters cramerShoupParameters, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5) {
        super(true, cramerShoupParameters);
        this.x1 = bigInteger;
        this.x2 = bigInteger2;
        this.y1 = bigInteger3;
        this.y2 = bigInteger4;
        this.z = bigInteger5;
    }

    public BigInteger getX1() {
        return this.x1;
    }

    public BigInteger getX2() {
        return this.x2;
    }

    public BigInteger getY1() {
        return this.y1;
    }

    public BigInteger getY2() {
        return this.y2;
    }

    public BigInteger getZ() {
        return this.z;
    }

    public void setPk(CramerShoupPublicKeyParameters cramerShoupPublicKeyParameters) {
        this.pk = cramerShoupPublicKeyParameters;
    }

    public CramerShoupPublicKeyParameters getPk() {
        return this.pk;
    }

    @Override // org.spongycastle.crypto.params.CramerShoupKeyParameters
    public int hashCode() {
        return ((((this.x1.hashCode() ^ this.x2.hashCode()) ^ this.y1.hashCode()) ^ this.y2.hashCode()) ^ this.z.hashCode()) ^ super.hashCode();
    }

    @Override // org.spongycastle.crypto.params.CramerShoupKeyParameters
    public boolean equals(Object obj) {
        if (!(obj instanceof CramerShoupPrivateKeyParameters)) {
            return false;
        }
        CramerShoupPrivateKeyParameters cramerShoupPrivateKeyParameters = (CramerShoupPrivateKeyParameters) obj;
        if (!cramerShoupPrivateKeyParameters.getX1().equals(this.x1) || !cramerShoupPrivateKeyParameters.getX2().equals(this.x2) || !cramerShoupPrivateKeyParameters.getY1().equals(this.y1) || !cramerShoupPrivateKeyParameters.getY2().equals(this.y2) || !cramerShoupPrivateKeyParameters.getZ().equals(this.z) || !super.equals(obj)) {
            return false;
        }
        return true;
    }
}
