package org.spongycastle.asn1;

public class DERObjectIdentifier extends ASN1ObjectIdentifier {
    public DERObjectIdentifier(String str) {
        super(str);
    }

    DERObjectIdentifier(byte[] bArr) {
        super(bArr);
    }

    DERObjectIdentifier(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        super(aSN1ObjectIdentifier, str);
    }
}
