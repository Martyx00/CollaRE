package org.spongycastle.asn1.crmf;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.Time;

public class OptionalValidity extends ASN1Object {
    private Time notAfter;
    private Time notBefore;

    private OptionalValidity(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objects.nextElement();
            if (aSN1TaggedObject.getTagNo() == 0) {
                this.notBefore = Time.getInstance(aSN1TaggedObject, true);
            } else {
                this.notAfter = Time.getInstance(aSN1TaggedObject, true);
            }
        }
    }

    public static OptionalValidity getInstance(Object obj) {
        if (obj instanceof OptionalValidity) {
            return (OptionalValidity) obj;
        }
        if (obj != null) {
            return new OptionalValidity(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public OptionalValidity(Time time, Time time2) {
        if (time == null && time2 == null) {
            throw new IllegalArgumentException("at least one of notBefore/notAfter must not be null.");
        }
        this.notBefore = time;
        this.notAfter = time2;
    }

    public Time getNotBefore() {
        return this.notBefore;
    }

    public Time getNotAfter() {
        return this.notAfter;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        Time time = this.notBefore;
        if (time != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, time));
        }
        Time time2 = this.notAfter;
        if (time2 != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 1, time2));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
