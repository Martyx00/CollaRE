package org.spongycastle.asn1;

public class DERBitString extends ASN1BitString {
    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return false;
    }

    public static DERBitString getInstance(Object obj) {
        if (obj == null || (obj instanceof DERBitString)) {
            return (DERBitString) obj;
        }
        if (obj instanceof DLBitString) {
            DLBitString dLBitString = (DLBitString) obj;
            return new DERBitString(dLBitString.data, dLBitString.padBits);
        } else if (obj instanceof byte[]) {
            try {
                return (DERBitString) fromByteArray((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static DERBitString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        if (z || (object instanceof DERBitString)) {
            return getInstance(object);
        }
        return fromOctetString(((ASN1OctetString) object).getOctets());
    }

    protected DERBitString(byte b2, int i) {
        this(toByteArray(b2), i);
    }

    private static byte[] toByteArray(byte b2) {
        return new byte[]{b2};
    }

    public DERBitString(byte[] bArr, int i) {
        super(bArr, i);
    }

    public DERBitString(byte[] bArr) {
        this(bArr, 0);
    }

    public DERBitString(int i) {
        super(getBytes(i), getPadBits(i));
    }

    public DERBitString(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER), 0);
    }

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public int encodedLength() {
        return StreamUtil.calculateBodyLength(this.data.length + 1) + 1 + this.data.length + 1;
    }

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1BitString, org.spongycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) {
        byte[] derForm = derForm(this.data, this.padBits);
        byte[] bArr = new byte[(derForm.length + 1)];
        bArr[0] = (byte) getPadBits();
        System.arraycopy(derForm, 0, bArr, 1, bArr.length - 1);
        aSN1OutputStream.writeEncoded(3, bArr);
    }

    static DERBitString fromOctetString(byte[] bArr) {
        if (bArr.length >= 1) {
            byte b2 = bArr[0];
            byte[] bArr2 = new byte[(bArr.length - 1)];
            if (bArr2.length != 0) {
                System.arraycopy(bArr, 1, bArr2, 0, bArr.length - 1);
            }
            return new DERBitString(bArr2, b2);
        }
        throw new IllegalArgumentException("truncated BIT STRING detected");
    }
}
