package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

public class ElGamalParameters implements CipherParameters {
    private BigInteger g;
    private int l;
    private BigInteger p;

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, 0);
    }

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        this.g = bigInteger2;
        this.p = bigInteger;
        this.l = i;
    }

    public BigInteger getP() {
        return this.p;
    }

    public BigInteger getG() {
        return this.g;
    }

    public int getL() {
        return this.l;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ElGamalParameters)) {
            return false;
        }
        ElGamalParameters elGamalParameters = (ElGamalParameters) obj;
        if (!elGamalParameters.getP().equals(this.p) || !elGamalParameters.getG().equals(this.g) || elGamalParameters.getL() != this.l) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG().hashCode()) + this.l;
    }
}
