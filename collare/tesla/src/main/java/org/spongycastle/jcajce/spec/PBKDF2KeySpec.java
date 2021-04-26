package org.spongycastle.jcajce.spec;

import javax.crypto.spec.PBEKeySpec;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class PBKDF2KeySpec extends PBEKeySpec {
    private static final AlgorithmIdentifier defaultPRF = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_hmacWithSHA1, DERNull.INSTANCE);
    private AlgorithmIdentifier prf;

    public PBKDF2KeySpec(char[] cArr, byte[] bArr, int i, int i2, AlgorithmIdentifier algorithmIdentifier) {
        super(cArr, bArr, i, i2);
        this.prf = algorithmIdentifier;
    }

    public boolean isDefaultPrf() {
        return defaultPRF.equals(this.prf);
    }

    public AlgorithmIdentifier getPrf() {
        return this.prf;
    }
}
