package org.spongycastle.jcajce.provider.asymmetric.dstu;

import java.io.IOException;
import java.security.SignatureException;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.DEROctetString;

public class SignatureSpiLe extends SignatureSpi {
    /* access modifiers changed from: package-private */
    public void reverseBytes(byte[] bArr) {
        for (int i = 0; i < bArr.length / 2; i++) {
            byte b2 = bArr[i];
            bArr[i] = bArr[(bArr.length - 1) - i];
            bArr[(bArr.length - 1) - i] = b2;
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.security.SignatureSpi, org.spongycastle.jcajce.provider.asymmetric.dstu.SignatureSpi
    public byte[] engineSign() {
        byte[] octets = ASN1OctetString.getInstance(super.engineSign()).getOctets();
        reverseBytes(octets);
        try {
            return new DEROctetString(octets).getEncoded();
        } catch (Exception e) {
            throw new SignatureException(e.toString());
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.security.SignatureSpi, org.spongycastle.jcajce.provider.asymmetric.dstu.SignatureSpi
    public boolean engineVerify(byte[] bArr) {
        try {
            byte[] octets = ((ASN1OctetString) ASN1OctetString.fromByteArray(bArr)).getOctets();
            reverseBytes(octets);
            try {
                return super.engineVerify(new DEROctetString(octets).getEncoded());
            } catch (SignatureException e) {
                throw e;
            } catch (Exception e2) {
                throw new SignatureException(e2.toString());
            }
        } catch (IOException unused) {
            throw new SignatureException("error decoding signature bytes.");
        }
    }
}
