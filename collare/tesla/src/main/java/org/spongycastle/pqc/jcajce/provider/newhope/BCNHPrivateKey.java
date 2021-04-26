package org.spongycastle.pqc.jcajce.provider.newhope;

import java.io.IOException;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.pqc.asn1.PQCObjectIdentifiers;
import org.spongycastle.pqc.crypto.newhope.NHPrivateKeyParameters;
import org.spongycastle.pqc.jcajce.interfaces.NHPrivateKey;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;

public class BCNHPrivateKey implements NHPrivateKey {
    private static final long serialVersionUID = 1;
    private final NHPrivateKeyParameters params;

    public final String getAlgorithm() {
        return "NH";
    }

    public String getFormat() {
        return "PKCS#8";
    }

    public BCNHPrivateKey(NHPrivateKeyParameters nHPrivateKeyParameters) {
        this.params = nHPrivateKeyParameters;
    }

    public BCNHPrivateKey(PrivateKeyInfo privateKeyInfo) {
        this.params = new NHPrivateKeyParameters(convert(ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets()));
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BCNHPrivateKey)) {
            return false;
        }
        return Arrays.areEqual(this.params.getSecData(), ((BCNHPrivateKey) obj).params.getSecData());
    }

    public int hashCode() {
        return Arrays.hashCode(this.params.getSecData());
    }

    public byte[] getEncoded() {
        try {
            AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(PQCObjectIdentifiers.newHope);
            short[] secData = this.params.getSecData();
            byte[] bArr = new byte[(secData.length * 2)];
            for (int i = 0; i != secData.length; i++) {
                Pack.shortToLittleEndian(secData[i], bArr, i * 2);
            }
            return new PrivateKeyInfo(algorithmIdentifier, new DEROctetString(bArr)).getEncoded();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // org.spongycastle.pqc.jcajce.interfaces.NHPrivateKey
    public short[] getSecretData() {
        return this.params.getSecData();
    }

    /* access modifiers changed from: package-private */
    public CipherParameters getKeyParams() {
        return this.params;
    }

    private static short[] convert(byte[] bArr) {
        short[] sArr = new short[(bArr.length / 2)];
        for (int i = 0; i != sArr.length; i++) {
            sArr[i] = Pack.littleEndianToShort(bArr, i * 2);
        }
        return sArr;
    }
}
