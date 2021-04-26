package org.spongycastle.asn1.pkcs;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.BERSequence;
import org.spongycastle.asn1.BERTaggedObject;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class EncryptedData extends ASN1Object {
    ASN1ObjectIdentifier bagId;
    ASN1Primitive bagValue;
    ASN1Sequence data;

    public static EncryptedData getInstance(Object obj) {
        if (obj instanceof EncryptedData) {
            return (EncryptedData) obj;
        }
        if (obj != null) {
            return new EncryptedData(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private EncryptedData(ASN1Sequence aSN1Sequence) {
        if (((ASN1Integer) aSN1Sequence.getObjectAt(0)).getValue().intValue() == 0) {
            this.data = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
            return;
        }
        throw new IllegalArgumentException("sequence not version 0");
    }

    public EncryptedData(ASN1ObjectIdentifier aSN1ObjectIdentifier, AlgorithmIdentifier algorithmIdentifier, ASN1Encodable aSN1Encodable) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(aSN1ObjectIdentifier);
        aSN1EncodableVector.add(algorithmIdentifier.toASN1Primitive());
        aSN1EncodableVector.add(new BERTaggedObject(false, 0, aSN1Encodable));
        this.data = new BERSequence(aSN1EncodableVector);
    }

    public ASN1ObjectIdentifier getContentType() {
        return ASN1ObjectIdentifier.getInstance(this.data.getObjectAt(0));
    }

    public AlgorithmIdentifier getEncryptionAlgorithm() {
        return AlgorithmIdentifier.getInstance(this.data.getObjectAt(1));
    }

    public ASN1OctetString getContent() {
        if (this.data.size() == 3) {
            return ASN1OctetString.getInstance(ASN1TaggedObject.getInstance(this.data.getObjectAt(2)), false);
        }
        return null;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(0));
        aSN1EncodableVector.add(this.data);
        return new BERSequence(aSN1EncodableVector);
    }
}
