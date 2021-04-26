package org.spongycastle.asn1.pkcs;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class CRLBag extends ASN1Object {
    private ASN1ObjectIdentifier crlId;
    private ASN1Encodable crlValue;

    private CRLBag(ASN1Sequence aSN1Sequence) {
        this.crlId = (ASN1ObjectIdentifier) aSN1Sequence.getObjectAt(0);
        this.crlValue = ((ASN1TaggedObject) aSN1Sequence.getObjectAt(1)).getObject();
    }

    public static CRLBag getInstance(Object obj) {
        if (obj instanceof CRLBag) {
            return (CRLBag) obj;
        }
        if (obj != null) {
            return new CRLBag(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public CRLBag(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.crlId = aSN1ObjectIdentifier;
        this.crlValue = aSN1Encodable;
    }

    public ASN1ObjectIdentifier getCrlId() {
        return this.crlId;
    }

    public ASN1Encodable getCrlValue() {
        return this.crlValue;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.crlId);
        aSN1EncodableVector.add(new DERTaggedObject(0, this.crlValue));
        return new DERSequence(aSN1EncodableVector);
    }
}
