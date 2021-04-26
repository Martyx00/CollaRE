package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class OtherKeyAttribute extends ASN1Object {
    private ASN1Encodable keyAttr;
    private ASN1ObjectIdentifier keyAttrId;

    public static OtherKeyAttribute getInstance(Object obj) {
        if (obj instanceof OtherKeyAttribute) {
            return (OtherKeyAttribute) obj;
        }
        if (obj != null) {
            return new OtherKeyAttribute(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public OtherKeyAttribute(ASN1Sequence aSN1Sequence) {
        this.keyAttrId = (ASN1ObjectIdentifier) aSN1Sequence.getObjectAt(0);
        this.keyAttr = aSN1Sequence.getObjectAt(1);
    }

    public OtherKeyAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.keyAttrId = aSN1ObjectIdentifier;
        this.keyAttr = aSN1Encodable;
    }

    public ASN1ObjectIdentifier getKeyAttrId() {
        return this.keyAttrId;
    }

    public ASN1Encodable getKeyAttr() {
        return this.keyAttr;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.keyAttrId);
        aSN1EncodableVector.add(this.keyAttr);
        return new DERSequence(aSN1EncodableVector);
    }
}
