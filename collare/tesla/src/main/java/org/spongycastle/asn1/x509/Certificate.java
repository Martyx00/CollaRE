package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.x500.X500Name;

public class Certificate extends ASN1Object {
    ASN1Sequence seq;
    DERBitString sig;
    AlgorithmIdentifier sigAlgId;
    TBSCertificate tbsCert;

    public static Certificate getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public static Certificate getInstance(Object obj) {
        if (obj instanceof Certificate) {
            return (Certificate) obj;
        }
        if (obj != null) {
            return new Certificate(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private Certificate(ASN1Sequence aSN1Sequence) {
        this.seq = aSN1Sequence;
        if (aSN1Sequence.size() == 3) {
            this.tbsCert = TBSCertificate.getInstance(aSN1Sequence.getObjectAt(0));
            this.sigAlgId = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
            this.sig = DERBitString.getInstance(aSN1Sequence.getObjectAt(2));
            return;
        }
        throw new IllegalArgumentException("sequence wrong size for a certificate");
    }

    public TBSCertificate getTBSCertificate() {
        return this.tbsCert;
    }

    public ASN1Integer getVersion() {
        return this.tbsCert.getVersion();
    }

    public int getVersionNumber() {
        return this.tbsCert.getVersionNumber();
    }

    public ASN1Integer getSerialNumber() {
        return this.tbsCert.getSerialNumber();
    }

    public X500Name getIssuer() {
        return this.tbsCert.getIssuer();
    }

    public Time getStartDate() {
        return this.tbsCert.getStartDate();
    }

    public Time getEndDate() {
        return this.tbsCert.getEndDate();
    }

    public X500Name getSubject() {
        return this.tbsCert.getSubject();
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.tbsCert.getSubjectPublicKeyInfo();
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return this.sigAlgId;
    }

    public DERBitString getSignature() {
        return this.sig;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        return this.seq;
    }
}
