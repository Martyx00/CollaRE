package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class InfoTypeAndValue extends ASN1Object {
    private ASN1ObjectIdentifier infoType;
    private ASN1Encodable infoValue;

    private InfoTypeAndValue(ASN1Sequence aSN1Sequence) {
        this.infoType = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        if (aSN1Sequence.size() > 1) {
            this.infoValue = aSN1Sequence.getObjectAt(1);
        }
    }

    public static InfoTypeAndValue getInstance(Object obj) {
        if (obj instanceof InfoTypeAndValue) {
            return (InfoTypeAndValue) obj;
        }
        if (obj != null) {
            return new InfoTypeAndValue(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public InfoTypeAndValue(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.infoType = aSN1ObjectIdentifier;
        this.infoValue = null;
    }

    public InfoTypeAndValue(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.infoType = aSN1ObjectIdentifier;
        this.infoValue = aSN1Encodable;
    }

    public ASN1ObjectIdentifier getInfoType() {
        return this.infoType;
    }

    public ASN1Encodable getInfoValue() {
        return this.infoValue;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.infoType);
        ASN1Encodable aSN1Encodable = this.infoValue;
        if (aSN1Encodable != null) {
            aSN1EncodableVector.add(aSN1Encodable);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
