package org.spongycastle.asn1.esf;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class OcspResponsesID extends ASN1Object {
    private OcspIdentifier ocspIdentifier;
    private OtherHash ocspRepHash;

    public static OcspResponsesID getInstance(Object obj) {
        if (obj instanceof OcspResponsesID) {
            return (OcspResponsesID) obj;
        }
        if (obj != null) {
            return new OcspResponsesID(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private OcspResponsesID(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 1 || aSN1Sequence.size() > 2) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        this.ocspIdentifier = OcspIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        if (aSN1Sequence.size() > 1) {
            this.ocspRepHash = OtherHash.getInstance(aSN1Sequence.getObjectAt(1));
        }
    }

    public OcspResponsesID(OcspIdentifier ocspIdentifier2) {
        this(ocspIdentifier2, null);
    }

    public OcspResponsesID(OcspIdentifier ocspIdentifier2, OtherHash otherHash) {
        this.ocspIdentifier = ocspIdentifier2;
        this.ocspRepHash = otherHash;
    }

    public OcspIdentifier getOcspIdentifier() {
        return this.ocspIdentifier;
    }

    public OtherHash getOcspRepHash() {
        return this.ocspRepHash;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.ocspIdentifier);
        OtherHash otherHash = this.ocspRepHash;
        if (otherHash != null) {
            aSN1EncodableVector.add(otherHash);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
