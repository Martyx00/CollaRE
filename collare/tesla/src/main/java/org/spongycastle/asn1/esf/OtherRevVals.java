package org.spongycastle.asn1.esf;

import java.io.IOException;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class OtherRevVals extends ASN1Object {
    private ASN1ObjectIdentifier otherRevValType;
    private ASN1Encodable otherRevVals;

    public static OtherRevVals getInstance(Object obj) {
        if (obj instanceof OtherRevVals) {
            return (OtherRevVals) obj;
        }
        if (obj != null) {
            return new OtherRevVals(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private OtherRevVals(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 2) {
            this.otherRevValType = (ASN1ObjectIdentifier) aSN1Sequence.getObjectAt(0);
            try {
                this.otherRevVals = ASN1Primitive.fromByteArray(aSN1Sequence.getObjectAt(1).toASN1Primitive().getEncoded(ASN1Encoding.DER));
            } catch (IOException unused) {
                throw new IllegalStateException();
            }
        } else {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
    }

    public OtherRevVals(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.otherRevValType = aSN1ObjectIdentifier;
        this.otherRevVals = aSN1Encodable;
    }

    public ASN1ObjectIdentifier getOtherRevValType() {
        return this.otherRevValType;
    }

    public ASN1Encodable getOtherRevVals() {
        return this.otherRevVals;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.otherRevValType);
        aSN1EncodableVector.add(this.otherRevVals);
        return new DERSequence(aSN1EncodableVector);
    }
}
