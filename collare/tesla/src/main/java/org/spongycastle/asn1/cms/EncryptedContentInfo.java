package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.BERSequence;
import org.spongycastle.asn1.BERTaggedObject;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class EncryptedContentInfo extends ASN1Object {
    private AlgorithmIdentifier contentEncryptionAlgorithm;
    private ASN1ObjectIdentifier contentType;
    private ASN1OctetString encryptedContent;

    public EncryptedContentInfo(ASN1ObjectIdentifier aSN1ObjectIdentifier, AlgorithmIdentifier algorithmIdentifier, ASN1OctetString aSN1OctetString) {
        this.contentType = aSN1ObjectIdentifier;
        this.contentEncryptionAlgorithm = algorithmIdentifier;
        this.encryptedContent = aSN1OctetString;
    }

    private EncryptedContentInfo(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() >= 2) {
            this.contentType = (ASN1ObjectIdentifier) aSN1Sequence.getObjectAt(0);
            this.contentEncryptionAlgorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
            if (aSN1Sequence.size() > 2) {
                this.encryptedContent = ASN1OctetString.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(2), false);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Truncated Sequence Found");
    }

    public static EncryptedContentInfo getInstance(Object obj) {
        if (obj instanceof EncryptedContentInfo) {
            return (EncryptedContentInfo) obj;
        }
        if (obj != null) {
            return new EncryptedContentInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1ObjectIdentifier getContentType() {
        return this.contentType;
    }

    public AlgorithmIdentifier getContentEncryptionAlgorithm() {
        return this.contentEncryptionAlgorithm;
    }

    public ASN1OctetString getEncryptedContent() {
        return this.encryptedContent;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.contentType);
        aSN1EncodableVector.add(this.contentEncryptionAlgorithm);
        ASN1OctetString aSN1OctetString = this.encryptedContent;
        if (aSN1OctetString != null) {
            aSN1EncodableVector.add(new BERTaggedObject(false, 0, aSN1OctetString));
        }
        return new BERSequence(aSN1EncodableVector);
    }
}
