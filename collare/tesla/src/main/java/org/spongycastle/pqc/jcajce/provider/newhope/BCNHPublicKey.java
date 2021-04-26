package org.spongycastle.pqc.jcajce.provider.newhope;

import java.io.IOException;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.pqc.asn1.PQCObjectIdentifiers;
import org.spongycastle.pqc.crypto.newhope.NHPublicKeyParameters;
import org.spongycastle.pqc.jcajce.interfaces.NHPublicKey;
import org.spongycastle.util.Arrays;

public class BCNHPublicKey implements NHPublicKey {
    private static final long serialVersionUID = 1;
    private final NHPublicKeyParameters params;

    public final String getAlgorithm() {
        return "NH";
    }

    public String getFormat() {
        return "X.509";
    }

    public BCNHPublicKey(NHPublicKeyParameters nHPublicKeyParameters) {
        this.params = nHPublicKeyParameters;
    }

    public BCNHPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this.params = new NHPublicKeyParameters(subjectPublicKeyInfo.getPublicKeyData().getBytes());
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BCNHPublicKey)) {
            return false;
        }
        return Arrays.areEqual(this.params.getPubData(), ((BCNHPublicKey) obj).params.getPubData());
    }

    public int hashCode() {
        return Arrays.hashCode(this.params.getPubData());
    }

    public byte[] getEncoded() {
        try {
            return new SubjectPublicKeyInfo(new AlgorithmIdentifier(PQCObjectIdentifiers.newHope), this.params.getPubData()).getEncoded();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // org.spongycastle.pqc.jcajce.interfaces.NHPublicKey
    public byte[] getPublicData() {
        return this.params.getPubData();
    }

    /* access modifiers changed from: package-private */
    public CipherParameters getKeyParams() {
        return this.params;
    }
}
