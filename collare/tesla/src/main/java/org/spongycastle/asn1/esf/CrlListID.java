package org.spongycastle.asn1.esf;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class CrlListID extends ASN1Object {
    private ASN1Sequence crls;

    public static CrlListID getInstance(Object obj) {
        if (obj instanceof CrlListID) {
            return (CrlListID) obj;
        }
        if (obj != null) {
            return new CrlListID(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private CrlListID(ASN1Sequence aSN1Sequence) {
        this.crls = (ASN1Sequence) aSN1Sequence.getObjectAt(0);
        Enumeration objects = this.crls.getObjects();
        while (objects.hasMoreElements()) {
            CrlValidatedID.getInstance(objects.nextElement());
        }
    }

    public CrlListID(CrlValidatedID[] crlValidatedIDArr) {
        this.crls = new DERSequence(crlValidatedIDArr);
    }

    public CrlValidatedID[] getCrls() {
        CrlValidatedID[] crlValidatedIDArr = new CrlValidatedID[this.crls.size()];
        for (int i = 0; i < crlValidatedIDArr.length; i++) {
            crlValidatedIDArr[i] = CrlValidatedID.getInstance(this.crls.getObjectAt(i));
        }
        return crlValidatedIDArr;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(this.crls);
    }
}
