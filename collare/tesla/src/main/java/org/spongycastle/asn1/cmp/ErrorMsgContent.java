package org.spongycastle.asn1.cmp;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class ErrorMsgContent extends ASN1Object {
    private ASN1Integer errorCode;
    private PKIFreeText errorDetails;
    private PKIStatusInfo pkiStatusInfo;

    private ErrorMsgContent(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.pkiStatusInfo = PKIStatusInfo.getInstance(objects.nextElement());
        while (objects.hasMoreElements()) {
            Object nextElement = objects.nextElement();
            if (nextElement instanceof ASN1Integer) {
                this.errorCode = ASN1Integer.getInstance(nextElement);
            } else {
                this.errorDetails = PKIFreeText.getInstance(nextElement);
            }
        }
    }

    public static ErrorMsgContent getInstance(Object obj) {
        if (obj instanceof ErrorMsgContent) {
            return (ErrorMsgContent) obj;
        }
        if (obj != null) {
            return new ErrorMsgContent(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ErrorMsgContent(PKIStatusInfo pKIStatusInfo) {
        this(pKIStatusInfo, null, null);
    }

    public ErrorMsgContent(PKIStatusInfo pKIStatusInfo, ASN1Integer aSN1Integer, PKIFreeText pKIFreeText) {
        if (pKIStatusInfo != null) {
            this.pkiStatusInfo = pKIStatusInfo;
            this.errorCode = aSN1Integer;
            this.errorDetails = pKIFreeText;
            return;
        }
        throw new IllegalArgumentException("'pkiStatusInfo' cannot be null");
    }

    public PKIStatusInfo getPKIStatusInfo() {
        return this.pkiStatusInfo;
    }

    public ASN1Integer getErrorCode() {
        return this.errorCode;
    }

    public PKIFreeText getErrorDetails() {
        return this.errorDetails;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.pkiStatusInfo);
        addOptional(aSN1EncodableVector, this.errorCode);
        addOptional(aSN1EncodableVector, this.errorDetails);
        return new DERSequence(aSN1EncodableVector);
    }

    private void addOptional(ASN1EncodableVector aSN1EncodableVector, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1EncodableVector.add(aSN1Encodable);
        }
    }
}
