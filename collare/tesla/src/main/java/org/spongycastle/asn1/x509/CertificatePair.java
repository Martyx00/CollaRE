package org.spongycastle.asn1.x509;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class CertificatePair extends ASN1Object {
    private Certificate forward;
    private Certificate reverse;

    public static CertificatePair getInstance(Object obj) {
        if (obj == null || (obj instanceof CertificatePair)) {
            return (CertificatePair) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new CertificatePair((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    private CertificatePair(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 1 || aSN1Sequence.size() == 2) {
            Enumeration objects = aSN1Sequence.getObjects();
            while (objects.hasMoreElements()) {
                ASN1TaggedObject instance = ASN1TaggedObject.getInstance(objects.nextElement());
                if (instance.getTagNo() == 0) {
                    this.forward = Certificate.getInstance(instance, true);
                } else if (instance.getTagNo() == 1) {
                    this.reverse = Certificate.getInstance(instance, true);
                } else {
                    throw new IllegalArgumentException("Bad tag number: " + instance.getTagNo());
                }
            }
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
    }

    public CertificatePair(Certificate certificate, Certificate certificate2) {
        this.forward = certificate;
        this.reverse = certificate2;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        Certificate certificate = this.forward;
        if (certificate != null) {
            aSN1EncodableVector.add(new DERTaggedObject(0, certificate));
        }
        Certificate certificate2 = this.reverse;
        if (certificate2 != null) {
            aSN1EncodableVector.add(new DERTaggedObject(1, certificate2));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public Certificate getForward() {
        return this.forward;
    }

    public Certificate getReverse() {
        return this.reverse;
    }
}
