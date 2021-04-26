package org.spongycastle.asn1;

public class DERNull extends ASN1Null {
    public static final DERNull INSTANCE = new DERNull();
    private static final byte[] zeroBytes = new byte[0];

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public int encodedLength() {
        return 2;
    }

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return false;
    }

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Null, org.spongycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) {
        aSN1OutputStream.writeEncoded(5, zeroBytes);
    }
}
