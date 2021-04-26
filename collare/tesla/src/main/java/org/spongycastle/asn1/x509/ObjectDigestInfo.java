package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Enumerated;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERSequence;

public class ObjectDigestInfo extends ASN1Object {
    public static final int otherObjectDigest = 2;
    public static final int publicKey = 0;
    public static final int publicKeyCert = 1;
    AlgorithmIdentifier digestAlgorithm;
    ASN1Enumerated digestedObjectType;
    DERBitString objectDigest;
    ASN1ObjectIdentifier otherObjectTypeID;

    public static ObjectDigestInfo getInstance(Object obj) {
        if (obj instanceof ObjectDigestInfo) {
            return (ObjectDigestInfo) obj;
        }
        if (obj != null) {
            return new ObjectDigestInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static ObjectDigestInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public ObjectDigestInfo(int i, ASN1ObjectIdentifier aSN1ObjectIdentifier, AlgorithmIdentifier algorithmIdentifier, byte[] bArr) {
        this.digestedObjectType = new ASN1Enumerated(i);
        if (i == 2) {
            this.otherObjectTypeID = aSN1ObjectIdentifier;
        }
        this.digestAlgorithm = algorithmIdentifier;
        this.objectDigest = new DERBitString(bArr);
    }

    private ObjectDigestInfo(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() > 4 || aSN1Sequence.size() < 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        int i = 0;
        this.digestedObjectType = ASN1Enumerated.getInstance(aSN1Sequence.getObjectAt(0));
        if (aSN1Sequence.size() == 4) {
            this.otherObjectTypeID = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
            i = 1;
        }
        this.digestAlgorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(i + 1));
        this.objectDigest = DERBitString.getInstance(aSN1Sequence.getObjectAt(i + 2));
    }

    public ASN1Enumerated getDigestedObjectType() {
        return this.digestedObjectType;
    }

    public ASN1ObjectIdentifier getOtherObjectTypeID() {
        return this.otherObjectTypeID;
    }

    public AlgorithmIdentifier getDigestAlgorithm() {
        return this.digestAlgorithm;
    }

    public DERBitString getObjectDigest() {
        return this.objectDigest;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.digestedObjectType);
        ASN1ObjectIdentifier aSN1ObjectIdentifier = this.otherObjectTypeID;
        if (aSN1ObjectIdentifier != null) {
            aSN1EncodableVector.add(aSN1ObjectIdentifier);
        }
        aSN1EncodableVector.add(this.digestAlgorithm);
        aSN1EncodableVector.add(this.objectDigest);
        return new DERSequence(aSN1EncodableVector);
    }
}
