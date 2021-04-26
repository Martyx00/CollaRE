package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.crmf.EncryptedValue;

public class CertOrEncCert extends ASN1Object implements ASN1Choice {
    private CMPCertificate certificate;
    private EncryptedValue encryptedCert;

    private CertOrEncCert(ASN1TaggedObject aSN1TaggedObject) {
        if (aSN1TaggedObject.getTagNo() == 0) {
            this.certificate = CMPCertificate.getInstance(aSN1TaggedObject.getObject());
        } else if (aSN1TaggedObject.getTagNo() == 1) {
            this.encryptedCert = EncryptedValue.getInstance(aSN1TaggedObject.getObject());
        } else {
            throw new IllegalArgumentException("unknown tag: " + aSN1TaggedObject.getTagNo());
        }
    }

    public static CertOrEncCert getInstance(Object obj) {
        if (obj instanceof CertOrEncCert) {
            return (CertOrEncCert) obj;
        }
        if (obj instanceof ASN1TaggedObject) {
            return new CertOrEncCert((ASN1TaggedObject) obj);
        }
        return null;
    }

    public CertOrEncCert(CMPCertificate cMPCertificate) {
        if (cMPCertificate != null) {
            this.certificate = cMPCertificate;
            return;
        }
        throw new IllegalArgumentException("'certificate' cannot be null");
    }

    public CertOrEncCert(EncryptedValue encryptedValue) {
        if (encryptedValue != null) {
            this.encryptedCert = encryptedValue;
            return;
        }
        throw new IllegalArgumentException("'encryptedCert' cannot be null");
    }

    public CMPCertificate getCertificate() {
        return this.certificate;
    }

    public EncryptedValue getEncryptedCert() {
        return this.encryptedCert;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        CMPCertificate cMPCertificate = this.certificate;
        if (cMPCertificate != null) {
            return new DERTaggedObject(true, 0, cMPCertificate);
        }
        return new DERTaggedObject(true, 1, this.encryptedCert);
    }
}
