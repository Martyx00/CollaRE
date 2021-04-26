package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;

public class CertificatePolicies extends ASN1Object {
    private final PolicyInformation[] policyInformation;

    public static CertificatePolicies getInstance(Object obj) {
        if (obj instanceof CertificatePolicies) {
            return (CertificatePolicies) obj;
        }
        if (obj != null) {
            return new CertificatePolicies(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static CertificatePolicies getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public static CertificatePolicies fromExtensions(Extensions extensions) {
        return getInstance(extensions.getExtensionParsedValue(Extension.certificatePolicies));
    }

    public CertificatePolicies(PolicyInformation policyInformation2) {
        this.policyInformation = new PolicyInformation[]{policyInformation2};
    }

    public CertificatePolicies(PolicyInformation[] policyInformationArr) {
        this.policyInformation = policyInformationArr;
    }

    private CertificatePolicies(ASN1Sequence aSN1Sequence) {
        this.policyInformation = new PolicyInformation[aSN1Sequence.size()];
        for (int i = 0; i != aSN1Sequence.size(); i++) {
            this.policyInformation[i] = PolicyInformation.getInstance(aSN1Sequence.getObjectAt(i));
        }
    }

    public PolicyInformation[] getPolicyInformation() {
        PolicyInformation[] policyInformationArr = this.policyInformation;
        PolicyInformation[] policyInformationArr2 = new PolicyInformation[policyInformationArr.length];
        System.arraycopy(policyInformationArr, 0, policyInformationArr2, 0, policyInformationArr.length);
        return policyInformationArr2;
    }

    public PolicyInformation getPolicyInformation(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        int i = 0;
        while (true) {
            PolicyInformation[] policyInformationArr = this.policyInformation;
            if (i == policyInformationArr.length) {
                return null;
            }
            if (aSN1ObjectIdentifier.equals(policyInformationArr[i].getPolicyIdentifier())) {
                return this.policyInformation[i];
            }
            i++;
        }
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(this.policyInformation);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.policyInformation.length; i++) {
            if (stringBuffer.length() != 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(this.policyInformation[i]);
        }
        return "CertificatePolicies: [" + ((Object) stringBuffer) + "]";
    }
}
