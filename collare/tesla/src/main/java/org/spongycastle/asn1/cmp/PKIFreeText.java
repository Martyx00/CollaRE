package org.spongycastle.asn1.cmp;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERUTF8String;

public class PKIFreeText extends ASN1Object {
    ASN1Sequence strings;

    public static PKIFreeText getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public static PKIFreeText getInstance(Object obj) {
        if (obj instanceof PKIFreeText) {
            return (PKIFreeText) obj;
        }
        if (obj != null) {
            return new PKIFreeText(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private PKIFreeText(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            if (!(objects.nextElement() instanceof DERUTF8String)) {
                throw new IllegalArgumentException("attempt to insert non UTF8 STRING into PKIFreeText");
            }
        }
        this.strings = aSN1Sequence;
    }

    public PKIFreeText(DERUTF8String dERUTF8String) {
        this.strings = new DERSequence(dERUTF8String);
    }

    public PKIFreeText(String str) {
        this(new DERUTF8String(str));
    }

    public PKIFreeText(DERUTF8String[] dERUTF8StringArr) {
        this.strings = new DERSequence(dERUTF8StringArr);
    }

    public PKIFreeText(String[] strArr) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (String str : strArr) {
            aSN1EncodableVector.add(new DERUTF8String(str));
        }
        this.strings = new DERSequence(aSN1EncodableVector);
    }

    public int size() {
        return this.strings.size();
    }

    public DERUTF8String getStringAt(int i) {
        return (DERUTF8String) this.strings.getObjectAt(i);
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        return this.strings;
    }
}
