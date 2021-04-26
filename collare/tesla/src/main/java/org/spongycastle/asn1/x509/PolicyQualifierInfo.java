package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DERSequence;

public class PolicyQualifierInfo extends ASN1Object {
    private ASN1ObjectIdentifier policyQualifierId;
    private ASN1Encodable qualifier;

    public PolicyQualifierInfo(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.policyQualifierId = aSN1ObjectIdentifier;
        this.qualifier = aSN1Encodable;
    }

    public PolicyQualifierInfo(String str) {
        this.policyQualifierId = PolicyQualifierId.id_qt_cps;
        this.qualifier = new DERIA5String(str);
    }

    public PolicyQualifierInfo(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 2) {
            this.policyQualifierId = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
            this.qualifier = aSN1Sequence.getObjectAt(1);
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
    }

    public static PolicyQualifierInfo getInstance(Object obj) {
        if (obj instanceof PolicyQualifierInfo) {
            return (PolicyQualifierInfo) obj;
        }
        if (obj != null) {
            return new PolicyQualifierInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1ObjectIdentifier getPolicyQualifierId() {
        return this.policyQualifierId;
    }

    public ASN1Encodable getQualifier() {
        return this.qualifier;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.policyQualifierId);
        aSN1EncodableVector.add(this.qualifier);
        return new DERSequence(aSN1EncodableVector);
    }
}
