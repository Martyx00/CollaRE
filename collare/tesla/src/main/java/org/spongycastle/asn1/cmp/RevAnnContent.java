package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.crmf.CertId;
import org.spongycastle.asn1.x509.Extensions;

public class RevAnnContent extends ASN1Object {
    private ASN1GeneralizedTime badSinceDate;
    private CertId certId;
    private Extensions crlDetails;
    private PKIStatus status;
    private ASN1GeneralizedTime willBeRevokedAt;

    private RevAnnContent(ASN1Sequence aSN1Sequence) {
        this.status = PKIStatus.getInstance(aSN1Sequence.getObjectAt(0));
        this.certId = CertId.getInstance(aSN1Sequence.getObjectAt(1));
        this.willBeRevokedAt = ASN1GeneralizedTime.getInstance(aSN1Sequence.getObjectAt(2));
        this.badSinceDate = ASN1GeneralizedTime.getInstance(aSN1Sequence.getObjectAt(3));
        if (aSN1Sequence.size() > 4) {
            this.crlDetails = Extensions.getInstance(aSN1Sequence.getObjectAt(4));
        }
    }

    public static RevAnnContent getInstance(Object obj) {
        if (obj instanceof RevAnnContent) {
            return (RevAnnContent) obj;
        }
        if (obj != null) {
            return new RevAnnContent(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public PKIStatus getStatus() {
        return this.status;
    }

    public CertId getCertId() {
        return this.certId;
    }

    public ASN1GeneralizedTime getWillBeRevokedAt() {
        return this.willBeRevokedAt;
    }

    public ASN1GeneralizedTime getBadSinceDate() {
        return this.badSinceDate;
    }

    public Extensions getCrlDetails() {
        return this.crlDetails;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.status);
        aSN1EncodableVector.add(this.certId);
        aSN1EncodableVector.add(this.willBeRevokedAt);
        aSN1EncodableVector.add(this.badSinceDate);
        Extensions extensions = this.crlDetails;
        if (extensions != null) {
            aSN1EncodableVector.add(extensions);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
