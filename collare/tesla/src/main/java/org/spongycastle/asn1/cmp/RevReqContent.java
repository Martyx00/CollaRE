package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class RevReqContent extends ASN1Object {
    private ASN1Sequence content;

    private RevReqContent(ASN1Sequence aSN1Sequence) {
        this.content = aSN1Sequence;
    }

    public static RevReqContent getInstance(Object obj) {
        if (obj instanceof RevReqContent) {
            return (RevReqContent) obj;
        }
        if (obj != null) {
            return new RevReqContent(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public RevReqContent(RevDetails revDetails) {
        this.content = new DERSequence(revDetails);
    }

    public RevReqContent(RevDetails[] revDetailsArr) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i != revDetailsArr.length; i++) {
            aSN1EncodableVector.add(revDetailsArr[i]);
        }
        this.content = new DERSequence(aSN1EncodableVector);
    }

    public RevDetails[] toRevDetailsArray() {
        RevDetails[] revDetailsArr = new RevDetails[this.content.size()];
        for (int i = 0; i != revDetailsArr.length; i++) {
            revDetailsArr[i] = RevDetails.getInstance(this.content.getObjectAt(i));
        }
        return revDetailsArr;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        return this.content;
    }
}
