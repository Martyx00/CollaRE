package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class CAKeyUpdAnnContent extends ASN1Object {
    private CMPCertificate newWithNew;
    private CMPCertificate newWithOld;
    private CMPCertificate oldWithNew;

    private CAKeyUpdAnnContent(ASN1Sequence aSN1Sequence) {
        this.oldWithNew = CMPCertificate.getInstance(aSN1Sequence.getObjectAt(0));
        this.newWithOld = CMPCertificate.getInstance(aSN1Sequence.getObjectAt(1));
        this.newWithNew = CMPCertificate.getInstance(aSN1Sequence.getObjectAt(2));
    }

    public static CAKeyUpdAnnContent getInstance(Object obj) {
        if (obj instanceof CAKeyUpdAnnContent) {
            return (CAKeyUpdAnnContent) obj;
        }
        if (obj != null) {
            return new CAKeyUpdAnnContent(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public CAKeyUpdAnnContent(CMPCertificate cMPCertificate, CMPCertificate cMPCertificate2, CMPCertificate cMPCertificate3) {
        this.oldWithNew = cMPCertificate;
        this.newWithOld = cMPCertificate2;
        this.newWithNew = cMPCertificate3;
    }

    public CMPCertificate getOldWithNew() {
        return this.oldWithNew;
    }

    public CMPCertificate getNewWithOld() {
        return this.newWithOld;
    }

    public CMPCertificate getNewWithNew() {
        return this.newWithNew;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.oldWithNew);
        aSN1EncodableVector.add(this.newWithOld);
        aSN1EncodableVector.add(this.newWithNew);
        return new DERSequence(aSN1EncodableVector);
    }
}
