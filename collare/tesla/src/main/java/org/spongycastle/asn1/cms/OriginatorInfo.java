package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class OriginatorInfo extends ASN1Object {
    private ASN1Set certs;
    private ASN1Set crls;

    public OriginatorInfo(ASN1Set aSN1Set, ASN1Set aSN1Set2) {
        this.certs = aSN1Set;
        this.crls = aSN1Set2;
    }

    private OriginatorInfo(ASN1Sequence aSN1Sequence) {
        switch (aSN1Sequence.size()) {
            case 0:
                return;
            case 1:
                ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Sequence.getObjectAt(0);
                switch (aSN1TaggedObject.getTagNo()) {
                    case 0:
                        this.certs = ASN1Set.getInstance(aSN1TaggedObject, false);
                        return;
                    case 1:
                        this.crls = ASN1Set.getInstance(aSN1TaggedObject, false);
                        return;
                    default:
                        throw new IllegalArgumentException("Bad tag in OriginatorInfo: " + aSN1TaggedObject.getTagNo());
                }
            case 2:
                this.certs = ASN1Set.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(0), false);
                this.crls = ASN1Set.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(1), false);
                return;
            default:
                throw new IllegalArgumentException("OriginatorInfo too big");
        }
    }

    public static OriginatorInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public static OriginatorInfo getInstance(Object obj) {
        if (obj instanceof OriginatorInfo) {
            return (OriginatorInfo) obj;
        }
        if (obj != null) {
            return new OriginatorInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1Set getCertificates() {
        return this.certs;
    }

    public ASN1Set getCRLs() {
        return this.crls;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1Set aSN1Set = this.certs;
        if (aSN1Set != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, aSN1Set));
        }
        ASN1Set aSN1Set2 = this.crls;
        if (aSN1Set2 != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, aSN1Set2));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
