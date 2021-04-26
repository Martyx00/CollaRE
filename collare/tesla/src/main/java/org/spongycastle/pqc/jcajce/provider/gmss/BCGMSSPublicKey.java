package org.spongycastle.pqc.jcajce.provider.gmss;

import java.security.PublicKey;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.pqc.asn1.GMSSPublicKey;
import org.spongycastle.pqc.asn1.PQCObjectIdentifiers;
import org.spongycastle.pqc.asn1.ParSet;
import org.spongycastle.pqc.crypto.gmss.GMSSParameters;
import org.spongycastle.pqc.crypto.gmss.GMSSPublicKeyParameters;
import org.spongycastle.pqc.jcajce.provider.util.KeyUtil;
import org.spongycastle.util.encoders.Hex;

public class BCGMSSPublicKey implements PublicKey, CipherParameters {
    private static final long serialVersionUID = 1;
    private GMSSParameters gmssParameterSet;
    private GMSSParameters gmssParams;
    private byte[] publicKeyBytes;

    public String getAlgorithm() {
        return "GMSS";
    }

    public String getFormat() {
        return "X.509";
    }

    public BCGMSSPublicKey(byte[] bArr, GMSSParameters gMSSParameters) {
        this.gmssParameterSet = gMSSParameters;
        this.publicKeyBytes = bArr;
    }

    public BCGMSSPublicKey(GMSSPublicKeyParameters gMSSPublicKeyParameters) {
        this(gMSSPublicKeyParameters.getPublicKey(), gMSSPublicKeyParameters.getParameters());
    }

    public byte[] getPublicKeyBytes() {
        return this.publicKeyBytes;
    }

    public GMSSParameters getParameterSet() {
        return this.gmssParameterSet;
    }

    public String toString() {
        String str = "GMSS public key : " + new String(Hex.encode(this.publicKeyBytes)) + "\nHeight of Trees: \n";
        for (int i = 0; i < this.gmssParameterSet.getHeightOfTrees().length; i++) {
            str = str + "Layer " + i + " : " + this.gmssParameterSet.getHeightOfTrees()[i] + " WinternitzParameter: " + this.gmssParameterSet.getWinternitzParameter()[i] + " K: " + this.gmssParameterSet.getK()[i] + "\n";
        }
        return str;
    }

    public byte[] getEncoded() {
        return KeyUtil.getEncodedSubjectPublicKeyInfo(new AlgorithmIdentifier(PQCObjectIdentifiers.gmss, new ParSet(this.gmssParameterSet.getNumOfLayers(), this.gmssParameterSet.getHeightOfTrees(), this.gmssParameterSet.getWinternitzParameter(), this.gmssParameterSet.getK()).toASN1Primitive()), new GMSSPublicKey(this.publicKeyBytes));
    }
}
