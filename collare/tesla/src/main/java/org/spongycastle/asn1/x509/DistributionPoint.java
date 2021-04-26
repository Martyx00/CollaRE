package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.util.Strings;

public class DistributionPoint extends ASN1Object {
    GeneralNames cRLIssuer;
    DistributionPointName distributionPoint;
    ReasonFlags reasons;

    public static DistributionPoint getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public static DistributionPoint getInstance(Object obj) {
        if (obj == null || (obj instanceof DistributionPoint)) {
            return (DistributionPoint) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new DistributionPoint((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("Invalid DistributionPoint: " + obj.getClass().getName());
    }

    public DistributionPoint(ASN1Sequence aSN1Sequence) {
        for (int i = 0; i != aSN1Sequence.size(); i++) {
            ASN1TaggedObject instance = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(i));
            switch (instance.getTagNo()) {
                case 0:
                    this.distributionPoint = DistributionPointName.getInstance(instance, true);
                    break;
                case 1:
                    this.reasons = new ReasonFlags(DERBitString.getInstance(instance, false));
                    break;
                case 2:
                    this.cRLIssuer = GeneralNames.getInstance(instance, false);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown tag encountered in structure: " + instance.getTagNo());
            }
        }
    }

    public DistributionPoint(DistributionPointName distributionPointName, ReasonFlags reasonFlags, GeneralNames generalNames) {
        this.distributionPoint = distributionPointName;
        this.reasons = reasonFlags;
        this.cRLIssuer = generalNames;
    }

    public DistributionPointName getDistributionPoint() {
        return this.distributionPoint;
    }

    public ReasonFlags getReasons() {
        return this.reasons;
    }

    public GeneralNames getCRLIssuer() {
        return this.cRLIssuer;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        DistributionPointName distributionPointName = this.distributionPoint;
        if (distributionPointName != null) {
            aSN1EncodableVector.add(new DERTaggedObject(0, distributionPointName));
        }
        ReasonFlags reasonFlags = this.reasons;
        if (reasonFlags != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, reasonFlags));
        }
        GeneralNames generalNames = this.cRLIssuer;
        if (generalNames != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 2, generalNames));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        String lineSeparator = Strings.lineSeparator();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DistributionPoint: [");
        stringBuffer.append(lineSeparator);
        DistributionPointName distributionPointName = this.distributionPoint;
        if (distributionPointName != null) {
            appendObject(stringBuffer, lineSeparator, "distributionPoint", distributionPointName.toString());
        }
        ReasonFlags reasonFlags = this.reasons;
        if (reasonFlags != null) {
            appendObject(stringBuffer, lineSeparator, "reasons", reasonFlags.toString());
        }
        GeneralNames generalNames = this.cRLIssuer;
        if (generalNames != null) {
            appendObject(stringBuffer, lineSeparator, "cRLIssuer", generalNames.toString());
        }
        stringBuffer.append("]");
        stringBuffer.append(lineSeparator);
        return stringBuffer.toString();
    }

    private void appendObject(StringBuffer stringBuffer, String str, String str2, String str3) {
        stringBuffer.append("    ");
        stringBuffer.append(str2);
        stringBuffer.append(":");
        stringBuffer.append(str);
        stringBuffer.append("    ");
        stringBuffer.append("    ");
        stringBuffer.append(str3);
        stringBuffer.append(str);
    }
}
