package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class ProtectedPart extends ASN1Object {
    private PKIBody body;
    private PKIHeader header;

    private ProtectedPart(ASN1Sequence aSN1Sequence) {
        this.header = PKIHeader.getInstance(aSN1Sequence.getObjectAt(0));
        this.body = PKIBody.getInstance(aSN1Sequence.getObjectAt(1));
    }

    public static ProtectedPart getInstance(Object obj) {
        if (obj instanceof ProtectedPart) {
            return (ProtectedPart) obj;
        }
        if (obj != null) {
            return new ProtectedPart(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ProtectedPart(PKIHeader pKIHeader, PKIBody pKIBody) {
        this.header = pKIHeader;
        this.body = pKIBody;
    }

    public PKIHeader getHeader() {
        return this.header;
    }

    public PKIBody getBody() {
        return this.body;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.header);
        aSN1EncodableVector.add(this.body);
        return new DERSequence(aSN1EncodableVector);
    }
}
