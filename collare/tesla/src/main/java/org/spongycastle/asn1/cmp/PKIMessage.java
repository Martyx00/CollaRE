package org.spongycastle.asn1.cmp;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class PKIMessage extends ASN1Object {
    private PKIBody body;
    private ASN1Sequence extraCerts;
    private PKIHeader header;
    private DERBitString protection;

    private PKIMessage(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.header = PKIHeader.getInstance(objects.nextElement());
        this.body = PKIBody.getInstance(objects.nextElement());
        while (objects.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objects.nextElement();
            if (aSN1TaggedObject.getTagNo() == 0) {
                this.protection = DERBitString.getInstance(aSN1TaggedObject, true);
            } else {
                this.extraCerts = ASN1Sequence.getInstance(aSN1TaggedObject, true);
            }
        }
    }

    public static PKIMessage getInstance(Object obj) {
        if (obj instanceof PKIMessage) {
            return (PKIMessage) obj;
        }
        if (obj != null) {
            return new PKIMessage(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public PKIMessage(PKIHeader pKIHeader, PKIBody pKIBody, DERBitString dERBitString, CMPCertificate[] cMPCertificateArr) {
        this.header = pKIHeader;
        this.body = pKIBody;
        this.protection = dERBitString;
        if (cMPCertificateArr != null) {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            for (CMPCertificate cMPCertificate : cMPCertificateArr) {
                aSN1EncodableVector.add(cMPCertificate);
            }
            this.extraCerts = new DERSequence(aSN1EncodableVector);
        }
    }

    public PKIMessage(PKIHeader pKIHeader, PKIBody pKIBody, DERBitString dERBitString) {
        this(pKIHeader, pKIBody, dERBitString, null);
    }

    public PKIMessage(PKIHeader pKIHeader, PKIBody pKIBody) {
        this(pKIHeader, pKIBody, null, null);
    }

    public PKIHeader getHeader() {
        return this.header;
    }

    public PKIBody getBody() {
        return this.body;
    }

    public DERBitString getProtection() {
        return this.protection;
    }

    public CMPCertificate[] getExtraCerts() {
        ASN1Sequence aSN1Sequence = this.extraCerts;
        if (aSN1Sequence == null) {
            return null;
        }
        CMPCertificate[] cMPCertificateArr = new CMPCertificate[aSN1Sequence.size()];
        for (int i = 0; i < cMPCertificateArr.length; i++) {
            cMPCertificateArr[i] = CMPCertificate.getInstance(this.extraCerts.getObjectAt(i));
        }
        return cMPCertificateArr;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.header);
        aSN1EncodableVector.add(this.body);
        addOptional(aSN1EncodableVector, 0, this.protection);
        addOptional(aSN1EncodableVector, 1, this.extraCerts);
        return new DERSequence(aSN1EncodableVector);
    }

    private void addOptional(ASN1EncodableVector aSN1EncodableVector, int i, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, i, aSN1Encodable));
        }
    }
}
