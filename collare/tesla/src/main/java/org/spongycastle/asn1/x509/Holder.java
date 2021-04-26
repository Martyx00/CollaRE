package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class Holder extends ASN1Object {
    public static final int V1_CERTIFICATE_HOLDER = 0;
    public static final int V2_CERTIFICATE_HOLDER = 1;
    IssuerSerial baseCertificateID;
    GeneralNames entityName;
    ObjectDigestInfo objectDigestInfo;
    private int version;

    public static Holder getInstance(Object obj) {
        if (obj instanceof Holder) {
            return (Holder) obj;
        }
        if (obj instanceof ASN1TaggedObject) {
            return new Holder(ASN1TaggedObject.getInstance(obj));
        }
        if (obj != null) {
            return new Holder(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private Holder(ASN1TaggedObject aSN1TaggedObject) {
        this.version = 1;
        switch (aSN1TaggedObject.getTagNo()) {
            case 0:
                this.baseCertificateID = IssuerSerial.getInstance(aSN1TaggedObject, true);
                break;
            case 1:
                this.entityName = GeneralNames.getInstance(aSN1TaggedObject, true);
                break;
            default:
                throw new IllegalArgumentException("unknown tag in Holder");
        }
        this.version = 0;
    }

    private Holder(ASN1Sequence aSN1Sequence) {
        this.version = 1;
        if (aSN1Sequence.size() <= 3) {
            for (int i = 0; i != aSN1Sequence.size(); i++) {
                ASN1TaggedObject instance = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(i));
                switch (instance.getTagNo()) {
                    case 0:
                        this.baseCertificateID = IssuerSerial.getInstance(instance, false);
                        break;
                    case 1:
                        this.entityName = GeneralNames.getInstance(instance, false);
                        break;
                    case 2:
                        this.objectDigestInfo = ObjectDigestInfo.getInstance(instance, false);
                        break;
                    default:
                        throw new IllegalArgumentException("unknown tag in Holder");
                }
            }
            this.version = 1;
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
    }

    public Holder(IssuerSerial issuerSerial) {
        this(issuerSerial, 1);
    }

    public Holder(IssuerSerial issuerSerial, int i) {
        this.version = 1;
        this.baseCertificateID = issuerSerial;
        this.version = i;
    }

    public int getVersion() {
        return this.version;
    }

    public Holder(GeneralNames generalNames) {
        this(generalNames, 1);
    }

    public Holder(GeneralNames generalNames, int i) {
        this.version = 1;
        this.entityName = generalNames;
        this.version = i;
    }

    public Holder(ObjectDigestInfo objectDigestInfo2) {
        this.version = 1;
        this.objectDigestInfo = objectDigestInfo2;
    }

    public IssuerSerial getBaseCertificateID() {
        return this.baseCertificateID;
    }

    public GeneralNames getEntityName() {
        return this.entityName;
    }

    public ObjectDigestInfo getObjectDigestInfo() {
        return this.objectDigestInfo;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        if (this.version == 1) {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            IssuerSerial issuerSerial = this.baseCertificateID;
            if (issuerSerial != null) {
                aSN1EncodableVector.add(new DERTaggedObject(false, 0, issuerSerial));
            }
            GeneralNames generalNames = this.entityName;
            if (generalNames != null) {
                aSN1EncodableVector.add(new DERTaggedObject(false, 1, generalNames));
            }
            ObjectDigestInfo objectDigestInfo2 = this.objectDigestInfo;
            if (objectDigestInfo2 != null) {
                aSN1EncodableVector.add(new DERTaggedObject(false, 2, objectDigestInfo2));
            }
            return new DERSequence(aSN1EncodableVector);
        }
        GeneralNames generalNames2 = this.entityName;
        if (generalNames2 != null) {
            return new DERTaggedObject(true, 1, generalNames2);
        }
        return new DERTaggedObject(true, 0, this.baseCertificateID);
    }
}
