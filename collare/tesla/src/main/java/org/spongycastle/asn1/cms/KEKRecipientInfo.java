package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class KEKRecipientInfo extends ASN1Object {
    private ASN1OctetString encryptedKey;
    private KEKIdentifier kekid;
    private AlgorithmIdentifier keyEncryptionAlgorithm;
    private ASN1Integer version;

    public KEKRecipientInfo(KEKIdentifier kEKIdentifier, AlgorithmIdentifier algorithmIdentifier, ASN1OctetString aSN1OctetString) {
        this.version = new ASN1Integer(4);
        this.kekid = kEKIdentifier;
        this.keyEncryptionAlgorithm = algorithmIdentifier;
        this.encryptedKey = aSN1OctetString;
    }

    public KEKRecipientInfo(ASN1Sequence aSN1Sequence) {
        this.version = (ASN1Integer) aSN1Sequence.getObjectAt(0);
        this.kekid = KEKIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.keyEncryptionAlgorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(2));
        this.encryptedKey = (ASN1OctetString) aSN1Sequence.getObjectAt(3);
    }

    public static KEKRecipientInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public static KEKRecipientInfo getInstance(Object obj) {
        if (obj instanceof KEKRecipientInfo) {
            return (KEKRecipientInfo) obj;
        }
        if (obj != null) {
            return new KEKRecipientInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public KEKIdentifier getKekid() {
        return this.kekid;
    }

    public AlgorithmIdentifier getKeyEncryptionAlgorithm() {
        return this.keyEncryptionAlgorithm;
    }

    public ASN1OctetString getEncryptedKey() {
        return this.encryptedKey;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.version);
        aSN1EncodableVector.add(this.kekid);
        aSN1EncodableVector.add(this.keyEncryptionAlgorithm);
        aSN1EncodableVector.add(this.encryptedKey);
        return new DERSequence(aSN1EncodableVector);
    }
}
