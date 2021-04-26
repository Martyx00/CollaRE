package org.spongycastle.asn1.x9;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERSequence;

public class DHValidationParms extends ASN1Object {
    private ASN1Integer pgenCounter;
    private DERBitString seed;

    public static DHValidationParms getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public static DHValidationParms getInstance(Object obj) {
        if (obj instanceof DHValidationParms) {
            return (DHValidationParms) obj;
        }
        if (obj != null) {
            return new DHValidationParms(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public DHValidationParms(DERBitString dERBitString, ASN1Integer aSN1Integer) {
        if (dERBitString == null) {
            throw new IllegalArgumentException("'seed' cannot be null");
        } else if (aSN1Integer != null) {
            this.seed = dERBitString;
            this.pgenCounter = aSN1Integer;
        } else {
            throw new IllegalArgumentException("'pgenCounter' cannot be null");
        }
    }

    private DHValidationParms(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 2) {
            this.seed = DERBitString.getInstance(aSN1Sequence.getObjectAt(0));
            this.pgenCounter = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1));
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
    }

    public DERBitString getSeed() {
        return this.seed;
    }

    public ASN1Integer getPgenCounter() {
        return this.pgenCounter;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.seed);
        aSN1EncodableVector.add(this.pgenCounter);
        return new DERSequence(aSN1EncodableVector);
    }
}
