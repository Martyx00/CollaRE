package org.spongycastle.asn1.esf;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.ocsp.ResponderID;

public class OcspIdentifier extends ASN1Object {
    private ResponderID ocspResponderID;
    private ASN1GeneralizedTime producedAt;

    public static OcspIdentifier getInstance(Object obj) {
        if (obj instanceof OcspIdentifier) {
            return (OcspIdentifier) obj;
        }
        if (obj != null) {
            return new OcspIdentifier(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private OcspIdentifier(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 2) {
            this.ocspResponderID = ResponderID.getInstance(aSN1Sequence.getObjectAt(0));
            this.producedAt = (ASN1GeneralizedTime) aSN1Sequence.getObjectAt(1);
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
    }

    public OcspIdentifier(ResponderID responderID, ASN1GeneralizedTime aSN1GeneralizedTime) {
        this.ocspResponderID = responderID;
        this.producedAt = aSN1GeneralizedTime;
    }

    public ResponderID getOcspResponderID() {
        return this.ocspResponderID;
    }

    public ASN1GeneralizedTime getProducedAt() {
        return this.producedAt;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.ocspResponderID);
        aSN1EncodableVector.add(this.producedAt);
        return new DERSequence(aSN1EncodableVector);
    }
}
