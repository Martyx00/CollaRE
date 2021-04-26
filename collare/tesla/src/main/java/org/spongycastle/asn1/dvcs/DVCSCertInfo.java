package org.spongycastle.asn1.dvcs;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.cmp.PKIStatusInfo;
import org.spongycastle.asn1.x509.DigestInfo;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.PolicyInformation;

public class DVCSCertInfo extends ASN1Object {
    private static final int DEFAULT_VERSION = 1;
    private static final int TAG_CERTS = 3;
    private static final int TAG_DV_STATUS = 0;
    private static final int TAG_POLICY = 1;
    private static final int TAG_REQ_SIGNATURE = 2;
    private ASN1Sequence certs;
    private DVCSRequestInformation dvReqInfo;
    private PKIStatusInfo dvStatus;
    private Extensions extensions;
    private DigestInfo messageImprint;
    private PolicyInformation policy;
    private ASN1Set reqSignature;
    private DVCSTime responseTime;
    private ASN1Integer serialNumber;
    private int version = 1;

    public DVCSCertInfo(DVCSRequestInformation dVCSRequestInformation, DigestInfo digestInfo, ASN1Integer aSN1Integer, DVCSTime dVCSTime) {
        this.dvReqInfo = dVCSRequestInformation;
        this.messageImprint = digestInfo;
        this.serialNumber = aSN1Integer;
        this.responseTime = dVCSTime;
    }

    private DVCSCertInfo(ASN1Sequence aSN1Sequence) {
        int i;
        ASN1Encodable objectAt = aSN1Sequence.getObjectAt(0);
        try {
            this.version = ASN1Integer.getInstance(objectAt).getValue().intValue();
            i = 2;
            try {
                objectAt = aSN1Sequence.getObjectAt(1);
            } catch (IllegalArgumentException unused) {
            }
        } catch (IllegalArgumentException unused2) {
            i = 1;
        }
        this.dvReqInfo = DVCSRequestInformation.getInstance(objectAt);
        int i2 = i + 1;
        this.messageImprint = DigestInfo.getInstance(aSN1Sequence.getObjectAt(i));
        int i3 = i2 + 1;
        this.serialNumber = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(i2));
        int i4 = i3 + 1;
        this.responseTime = DVCSTime.getInstance(aSN1Sequence.getObjectAt(i3));
        while (i4 < aSN1Sequence.size()) {
            int i5 = i4 + 1;
            ASN1Encodable objectAt2 = aSN1Sequence.getObjectAt(i4);
            if (objectAt2 instanceof ASN1TaggedObject) {
                ASN1TaggedObject instance = ASN1TaggedObject.getInstance(objectAt2);
                int tagNo = instance.getTagNo();
                switch (tagNo) {
                    case 0:
                        this.dvStatus = PKIStatusInfo.getInstance(instance, false);
                        continue;
                    case 1:
                        this.policy = PolicyInformation.getInstance(ASN1Sequence.getInstance(instance, false));
                        continue;
                    case 2:
                        this.reqSignature = ASN1Set.getInstance(instance, false);
                        continue;
                    case 3:
                        this.certs = ASN1Sequence.getInstance(instance, false);
                        continue;
                    default:
                        throw new IllegalArgumentException("Unknown tag encountered: " + tagNo);
                }
            } else {
                try {
                    this.extensions = Extensions.getInstance(objectAt2);
                } catch (IllegalArgumentException unused3) {
                }
            }
            i4 = i5;
        }
    }

    public static DVCSCertInfo getInstance(Object obj) {
        if (obj instanceof DVCSCertInfo) {
            return (DVCSCertInfo) obj;
        }
        if (obj != null) {
            return new DVCSCertInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static DVCSCertInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        int i = this.version;
        if (i != 1) {
            aSN1EncodableVector.add(new ASN1Integer((long) i));
        }
        aSN1EncodableVector.add(this.dvReqInfo);
        aSN1EncodableVector.add(this.messageImprint);
        aSN1EncodableVector.add(this.serialNumber);
        aSN1EncodableVector.add(this.responseTime);
        PKIStatusInfo pKIStatusInfo = this.dvStatus;
        if (pKIStatusInfo != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, pKIStatusInfo));
        }
        PolicyInformation policyInformation = this.policy;
        if (policyInformation != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, policyInformation));
        }
        ASN1Set aSN1Set = this.reqSignature;
        if (aSN1Set != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 2, aSN1Set));
        }
        ASN1Sequence aSN1Sequence = this.certs;
        if (aSN1Sequence != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 3, aSN1Sequence));
        }
        Extensions extensions2 = this.extensions;
        if (extensions2 != null) {
            aSN1EncodableVector.add(extensions2);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DVCSCertInfo {\n");
        if (this.version != 1) {
            stringBuffer.append("version: " + this.version + "\n");
        }
        stringBuffer.append("dvReqInfo: " + this.dvReqInfo + "\n");
        stringBuffer.append("messageImprint: " + this.messageImprint + "\n");
        stringBuffer.append("serialNumber: " + this.serialNumber + "\n");
        stringBuffer.append("responseTime: " + this.responseTime + "\n");
        if (this.dvStatus != null) {
            stringBuffer.append("dvStatus: " + this.dvStatus + "\n");
        }
        if (this.policy != null) {
            stringBuffer.append("policy: " + this.policy + "\n");
        }
        if (this.reqSignature != null) {
            stringBuffer.append("reqSignature: " + this.reqSignature + "\n");
        }
        if (this.certs != null) {
            stringBuffer.append("certs: " + this.certs + "\n");
        }
        if (this.extensions != null) {
            stringBuffer.append("extensions: " + this.extensions + "\n");
        }
        stringBuffer.append("}\n");
        return stringBuffer.toString();
    }

    public int getVersion() {
        return this.version;
    }

    private void setVersion(int i) {
        this.version = i;
    }

    public DVCSRequestInformation getDvReqInfo() {
        return this.dvReqInfo;
    }

    private void setDvReqInfo(DVCSRequestInformation dVCSRequestInformation) {
        this.dvReqInfo = dVCSRequestInformation;
    }

    public DigestInfo getMessageImprint() {
        return this.messageImprint;
    }

    private void setMessageImprint(DigestInfo digestInfo) {
        this.messageImprint = digestInfo;
    }

    public ASN1Integer getSerialNumber() {
        return this.serialNumber;
    }

    public DVCSTime getResponseTime() {
        return this.responseTime;
    }

    public PKIStatusInfo getDvStatus() {
        return this.dvStatus;
    }

    public PolicyInformation getPolicy() {
        return this.policy;
    }

    public ASN1Set getReqSignature() {
        return this.reqSignature;
    }

    public TargetEtcChain[] getCerts() {
        ASN1Sequence aSN1Sequence = this.certs;
        if (aSN1Sequence != null) {
            return TargetEtcChain.arrayFromSequence(aSN1Sequence);
        }
        return null;
    }

    public Extensions getExtensions() {
        return this.extensions;
    }
}
