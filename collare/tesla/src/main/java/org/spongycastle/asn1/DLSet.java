package org.spongycastle.asn1;

import java.util.Enumeration;

public class DLSet extends ASN1Set {
    private int bodyLength = -1;

    public DLSet() {
    }

    public DLSet(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable);
    }

    public DLSet(ASN1EncodableVector aSN1EncodableVector) {
        super(aSN1EncodableVector, false);
    }

    public DLSet(ASN1Encodable[] aSN1EncodableArr) {
        super(aSN1EncodableArr, false);
    }

    private int getBodyLength() {
        if (this.bodyLength < 0) {
            int i = 0;
            Enumeration objects = getObjects();
            while (objects.hasMoreElements()) {
                i += ((ASN1Encodable) objects.nextElement()).toASN1Primitive().toDLObject().encodedLength();
            }
            this.bodyLength = i;
        }
        return this.bodyLength;
    }

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public int encodedLength() {
        int bodyLength2 = getBodyLength();
        return StreamUtil.calculateBodyLength(bodyLength2) + 1 + bodyLength2;
    }

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive, org.spongycastle.asn1.ASN1Set
    public void encode(ASN1OutputStream aSN1OutputStream) {
        ASN1OutputStream dLSubStream = aSN1OutputStream.getDLSubStream();
        int bodyLength2 = getBodyLength();
        aSN1OutputStream.write(49);
        aSN1OutputStream.writeLength(bodyLength2);
        Enumeration objects = getObjects();
        while (objects.hasMoreElements()) {
            dLSubStream.writeObject((ASN1Encodable) objects.nextElement());
        }
    }
}
