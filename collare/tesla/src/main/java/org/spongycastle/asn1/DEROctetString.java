package org.spongycastle.asn1;

public class DEROctetString extends ASN1OctetString {
    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return false;
    }

    public DEROctetString(byte[] bArr) {
        super(bArr);
    }

    public DEROctetString(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER));
    }

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public int encodedLength() {
        return StreamUtil.calculateBodyLength(this.string.length) + 1 + this.string.length;
    }

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1OctetString, org.spongycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) {
        aSN1OutputStream.writeEncoded(4, this.string);
    }

    static void encode(DEROutputStream dEROutputStream, byte[] bArr) {
        dEROutputStream.writeEncoded(4, bArr);
    }
}
