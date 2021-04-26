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

public class OtherRevRefs extends ASN1Object {
    private ASN1ObjectIdentifier otherRevRefType;
    private ASN1Encodable otherRevRefs;

    public static OtherRevRefs getInstance(Object obj) {
        if (obj instanceof OtherRevRefs) {
            return (OtherRevRefs) obj;
        }
        if (obj != null) {
            return new OtherRevRefs(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private OtherRevRefs(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 2) {
            this.otherRevRefType = new ASN1ObjectIdentifier(((ASN1ObjectIdentifier) aSN1Sequence.getObjectAt(0)).getId());
            try {
                this.otherRevRefs = ASN1Primitive.fromByteArray(aSN1Sequence.getObjectAt(1).toASN1Primitive().getEncoded(ASN1Encoding.DER));
            } catch (IOException unused) {
                throw new IllegalStateException();
            }
        } else {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
    }

    public OtherRevRefs(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.otherRevRefType = aSN1ObjectIdentifier;
        this.otherRevRefs = aSN1Encodable;
    }

    public ASN1ObjectIdentifier getOtherRevRefType() {
        return this.otherRevRefType;
    }

    public ASN1Encodable getOtherRevRefs() {
        return this.otherRevRefs;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.otherRevRefType);
        aSN1EncodableVector.add(this.otherRevRefs);
        return new DERSequence(aSN1EncodableVector);
    }
}
