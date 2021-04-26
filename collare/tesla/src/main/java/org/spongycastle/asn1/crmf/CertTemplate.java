package org.spongycastle.asn1.crmf;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;

public class CertTemplate extends ASN1Object {
    private Extensions extensions;
    private X500Name issuer;
    private DERBitString issuerUID;
    private SubjectPublicKeyInfo publicKey;
    private ASN1Sequence seq;
    private ASN1Integer serialNumber;
    private AlgorithmIdentifier signingAlg;
    private X500Name subject;
    private DERBitString subjectUID;
    private OptionalValidity validity;
    private ASN1Integer version;

    private CertTemplate(ASN1Sequence aSN1Sequence) {
        this.seq = aSN1Sequence;
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objects.nextElement();
            switch (aSN1TaggedObject.getTagNo()) {
                case 0:
                    this.version = ASN1Integer.getInstance(aSN1TaggedObject, false);
                    break;
                case 1:
                    this.serialNumber = ASN1Integer.getInstance(aSN1TaggedObject, false);
                    break;
                case 2:
                    this.signingAlg = AlgorithmIdentifier.getInstance(aSN1TaggedObject, false);
                    break;
                case 3:
                    this.issuer = X500Name.getInstance(aSN1TaggedObject, true);
                    break;
                case 4:
                    this.validity = OptionalValidity.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, false));
                    break;
                case 5:
                    this.subject = X500Name.getInstance(aSN1TaggedObject, true);
                    break;
                case 6:
                    this.publicKey = SubjectPublicKeyInfo.getInstance(aSN1TaggedObject, false);
                    break;
                case 7:
                    this.issuerUID = DERBitString.getInstance(aSN1TaggedObject, false);
                    break;
                case 8:
                    this.subjectUID = DERBitString.getInstance(aSN1TaggedObject, false);
                    break;
                case 9:
                    this.extensions = Extensions.getInstance(aSN1TaggedObject, false);
                    break;
                default:
                    throw new IllegalArgumentException("unknown tag: " + aSN1TaggedObject.getTagNo());
            }
        }
    }

    public static CertTemplate getInstance(Object obj) {
        if (obj instanceof CertTemplate) {
            return (CertTemplate) obj;
        }
        if (obj != null) {
            return new CertTemplate(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public int getVersion() {
        return this.version.getValue().intValue();
    }

    public ASN1Integer getSerialNumber() {
        return this.serialNumber;
    }

    public AlgorithmIdentifier getSigningAlg() {
        return this.signingAlg;
    }

    public X500Name getIssuer() {
        return this.issuer;
    }

    public OptionalValidity getValidity() {
        return this.validity;
    }

    public X500Name getSubject() {
        return this.subject;
    }

    public SubjectPublicKeyInfo getPublicKey() {
        return this.publicKey;
    }

    public DERBitString getIssuerUID() {
        return this.issuerUID;
    }

    public DERBitString getSubjectUID() {
        return this.subjectUID;
    }

    public Extensions getExtensions() {
        return this.extensions;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        return this.seq;
    }
}
