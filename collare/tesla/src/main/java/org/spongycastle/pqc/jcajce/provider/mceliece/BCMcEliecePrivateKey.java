package org.spongycastle.pqc.jcajce.provider.mceliece;

import java.io.IOException;
import java.security.PrivateKey;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.asn1.McEliecePrivateKey;
import org.spongycastle.pqc.asn1.PQCObjectIdentifiers;
import org.spongycastle.pqc.crypto.mceliece.McEliecePrivateKeyParameters;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;
import org.spongycastle.util.Strings;

public class BCMcEliecePrivateKey implements PrivateKey, CipherParameters {
    private static final long serialVersionUID = 1;
    private McEliecePrivateKeyParameters params;

    public String getAlgorithm() {
        return "McEliece";
    }

    public String getFormat() {
        return "PKCS#8";
    }

    public BCMcEliecePrivateKey(McEliecePrivateKeyParameters mcEliecePrivateKeyParameters) {
        this.params = mcEliecePrivateKeyParameters;
    }

    public int getN() {
        return this.params.getN();
    }

    public int getK() {
        return this.params.getK();
    }

    public GF2mField getField() {
        return this.params.getField();
    }

    public PolynomialGF2mSmallM getGoppaPoly() {
        return this.params.getGoppaPoly();
    }

    public GF2Matrix getSInv() {
        return this.params.getSInv();
    }

    public Permutation getP1() {
        return this.params.getP1();
    }

    public Permutation getP2() {
        return this.params.getP2();
    }

    public GF2Matrix getH() {
        return this.params.getH();
    }

    public PolynomialGF2mSmallM[] getQInv() {
        return this.params.getQInv();
    }

    public String toString() {
        return (((((" length of the code          : " + getN() + Strings.lineSeparator()) + " dimension of the code       : " + getK() + Strings.lineSeparator()) + " irreducible Goppa polynomial: " + getGoppaPoly() + Strings.lineSeparator()) + " permutation P1              : " + getP1() + Strings.lineSeparator()) + " permutation P2              : " + getP2() + Strings.lineSeparator()) + " (k x k)-matrix S^-1         : " + getSInv();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BCMcEliecePrivateKey)) {
            return false;
        }
        BCMcEliecePrivateKey bCMcEliecePrivateKey = (BCMcEliecePrivateKey) obj;
        if (getN() != bCMcEliecePrivateKey.getN() || getK() != bCMcEliecePrivateKey.getK() || !getField().equals(bCMcEliecePrivateKey.getField()) || !getGoppaPoly().equals(bCMcEliecePrivateKey.getGoppaPoly()) || !getSInv().equals(bCMcEliecePrivateKey.getSInv()) || !getP1().equals(bCMcEliecePrivateKey.getP1()) || !getP2().equals(bCMcEliecePrivateKey.getP2())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((((this.params.getK() * 37) + this.params.getN()) * 37) + this.params.getField().hashCode()) * 37) + this.params.getGoppaPoly().hashCode()) * 37) + this.params.getP1().hashCode()) * 37) + this.params.getP2().hashCode()) * 37) + this.params.getSInv().hashCode();
    }

    public byte[] getEncoded() {
        try {
            try {
                return new PrivateKeyInfo(new AlgorithmIdentifier(PQCObjectIdentifiers.mcEliece), new McEliecePrivateKey(this.params.getN(), this.params.getK(), this.params.getField(), this.params.getGoppaPoly(), this.params.getP1(), this.params.getP2(), this.params.getSInv())).getEncoded();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public AsymmetricKeyParameter getKeyParams() {
        return this.params;
    }
}
