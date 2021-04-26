package org.spongycastle.pqc.jcajce.provider.mceliece;

import java.io.IOException;
import java.security.PrivateKey;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.asn1.McElieceCCA2PrivateKey;
import org.spongycastle.pqc.asn1.PQCObjectIdentifiers;
import org.spongycastle.pqc.crypto.mceliece.McElieceCCA2PrivateKeyParameters;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;

public class BCMcElieceCCA2PrivateKey implements PrivateKey {
    private static final long serialVersionUID = 1;
    private McElieceCCA2PrivateKeyParameters params;

    public String getAlgorithm() {
        return "McEliece-CCA2";
    }

    public String getFormat() {
        return "PKCS#8";
    }

    public BCMcElieceCCA2PrivateKey(McElieceCCA2PrivateKeyParameters mcElieceCCA2PrivateKeyParameters) {
        this.params = mcElieceCCA2PrivateKeyParameters;
    }

    public int getN() {
        return this.params.getN();
    }

    public int getK() {
        return this.params.getK();
    }

    public int getT() {
        return this.params.getGoppaPoly().getDegree();
    }

    public GF2mField getField() {
        return this.params.getField();
    }

    public PolynomialGF2mSmallM getGoppaPoly() {
        return this.params.getGoppaPoly();
    }

    public Permutation getP() {
        return this.params.getP();
    }

    public GF2Matrix getH() {
        return this.params.getH();
    }

    public PolynomialGF2mSmallM[] getQInv() {
        return this.params.getQInv();
    }

    public String toString() {
        return (("" + " extension degree of the field      : " + getN() + "\n") + " dimension of the code              : " + getK() + "\n") + " irreducible Goppa polynomial       : " + getGoppaPoly() + "\n";
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BCMcElieceCCA2PrivateKey)) {
            return false;
        }
        BCMcElieceCCA2PrivateKey bCMcElieceCCA2PrivateKey = (BCMcElieceCCA2PrivateKey) obj;
        if (getN() != bCMcElieceCCA2PrivateKey.getN() || getK() != bCMcElieceCCA2PrivateKey.getK() || !getField().equals(bCMcElieceCCA2PrivateKey.getField()) || !getGoppaPoly().equals(bCMcElieceCCA2PrivateKey.getGoppaPoly()) || !getP().equals(bCMcElieceCCA2PrivateKey.getP()) || !getH().equals(bCMcElieceCCA2PrivateKey.getH())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((this.params.getK() * 37) + this.params.getN()) * 37) + this.params.getField().hashCode()) * 37) + this.params.getGoppaPoly().hashCode()) * 37) + this.params.getP().hashCode()) * 37) + this.params.getH().hashCode();
    }

    public byte[] getEncoded() {
        try {
            return new PrivateKeyInfo(new AlgorithmIdentifier(PQCObjectIdentifiers.mcElieceCca2), new McElieceCCA2PrivateKey(getN(), getK(), getField(), getGoppaPoly(), getP(), Utils.getDigAlgId(this.params.getDigest()))).getEncoded();
        } catch (IOException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public AsymmetricKeyParameter getKeyParams() {
        return this.params;
    }
}
