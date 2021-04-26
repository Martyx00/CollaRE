package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;

public class CertConfirmContent extends ASN1Object {
    private ASN1Sequence content;

    private CertConfirmContent(ASN1Sequence aSN1Sequence) {
        this.content = aSN1Sequence;
    }

    public static CertConfirmContent getInstance(Object obj) {
        if (obj instanceof CertConfirmContent) {
            return (CertConfirmContent) obj;
        }
        if (obj != null) {
            return new CertConfirmContent(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public CertStatus[] toCertStatusArray() {
        CertStatus[] certStatusArr = new CertStatus[this.content.size()];
        for (int i = 0; i != certStatusArr.length; i++) {
            certStatusArr[i] = CertStatus.getInstance(this.content.getObjectAt(i));
        }
        return certStatusArr;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        return this.content;
    }
}
