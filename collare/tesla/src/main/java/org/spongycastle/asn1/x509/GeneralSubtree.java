package org.spongycastle.asn1.x509;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class GeneralSubtree extends ASN1Object {
    private static final BigInteger ZERO = BigInteger.valueOf(0);
    private GeneralName base;
    private ASN1Integer maximum;
    private ASN1Integer minimum;

    private GeneralSubtree(ASN1Sequence aSN1Sequence) {
        this.base = GeneralName.getInstance(aSN1Sequence.getObjectAt(0));
        switch (aSN1Sequence.size()) {
            case 1:
                return;
            case 2:
                ASN1TaggedObject instance = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(1));
                switch (instance.getTagNo()) {
                    case 0:
                        this.minimum = ASN1Integer.getInstance(instance, false);
                        return;
                    case 1:
                        this.maximum = ASN1Integer.getInstance(instance, false);
                        return;
                    default:
                        throw new IllegalArgumentException("Bad tag number: " + instance.getTagNo());
                }
            case 3:
                ASN1TaggedObject instance2 = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(1));
                if (instance2.getTagNo() == 0) {
                    this.minimum = ASN1Integer.getInstance(instance2, false);
                    ASN1TaggedObject instance3 = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(2));
                    if (instance3.getTagNo() == 1) {
                        this.maximum = ASN1Integer.getInstance(instance3, false);
                        return;
                    }
                    throw new IllegalArgumentException("Bad tag number for 'maximum': " + instance3.getTagNo());
                }
                throw new IllegalArgumentException("Bad tag number for 'minimum': " + instance2.getTagNo());
            default:
                throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
    }

    public GeneralSubtree(GeneralName generalName, BigInteger bigInteger, BigInteger bigInteger2) {
        this.base = generalName;
        if (bigInteger2 != null) {
            this.maximum = new ASN1Integer(bigInteger2);
        }
        if (bigInteger == null) {
            this.minimum = null;
        } else {
            this.minimum = new ASN1Integer(bigInteger);
        }
    }

    public GeneralSubtree(GeneralName generalName) {
        this(generalName, null, null);
    }

    public static GeneralSubtree getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return new GeneralSubtree(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public static GeneralSubtree getInstance(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof GeneralSubtree) {
            return (GeneralSubtree) obj;
        }
        return new GeneralSubtree(ASN1Sequence.getInstance(obj));
    }

    public GeneralName getBase() {
        return this.base;
    }

    public BigInteger getMinimum() {
        ASN1Integer aSN1Integer = this.minimum;
        if (aSN1Integer == null) {
            return ZERO;
        }
        return aSN1Integer.getValue();
    }

    public BigInteger getMaximum() {
        ASN1Integer aSN1Integer = this.maximum;
        if (aSN1Integer == null) {
            return null;
        }
        return aSN1Integer.getValue();
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.base);
        ASN1Integer aSN1Integer = this.minimum;
        if (aSN1Integer != null && !aSN1Integer.getValue().equals(ZERO)) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.minimum));
        }
        ASN1Integer aSN1Integer2 = this.maximum;
        if (aSN1Integer2 != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, aSN1Integer2));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
