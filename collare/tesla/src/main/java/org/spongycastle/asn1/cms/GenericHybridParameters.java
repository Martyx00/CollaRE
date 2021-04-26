package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class GenericHybridParameters extends ASN1Object {
    private final AlgorithmIdentifier dem;
    private final AlgorithmIdentifier kem;

    private GenericHybridParameters(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 2) {
            this.kem = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
            this.dem = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
            return;
        }
        throw new IllegalArgumentException("ASN.1 SEQUENCE should be of length 2");
    }

    public static GenericHybridParameters getInstance(Object obj) {
        if (obj instanceof GenericHybridParameters) {
            return (GenericHybridParameters) obj;
        }
        if (obj != null) {
            return new GenericHybridParameters(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public GenericHybridParameters(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) {
        this.kem = algorithmIdentifier;
        this.dem = algorithmIdentifier2;
    }

    public AlgorithmIdentifier getDem() {
        return this.dem;
    }

    public AlgorithmIdentifier getKem() {
        return this.kem;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.kem);
        aSN1EncodableVector.add(this.dem);
        return new DERSequence(aSN1EncodableVector);
    }
}
