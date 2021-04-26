package org.spongycastle.asn1.dvcs;

import java.util.Date;
import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.cms.ContentInfo;

public class DVCSTime extends ASN1Object implements ASN1Choice {
    private final ASN1GeneralizedTime genTime;
    private final ContentInfo timeStampToken;

    public DVCSTime(Date date) {
        this(new ASN1GeneralizedTime(date));
    }

    public DVCSTime(ASN1GeneralizedTime aSN1GeneralizedTime) {
        this.genTime = aSN1GeneralizedTime;
        this.timeStampToken = null;
    }

    public DVCSTime(ContentInfo contentInfo) {
        this.genTime = null;
        this.timeStampToken = contentInfo;
    }

    public static DVCSTime getInstance(Object obj) {
        if (obj instanceof DVCSTime) {
            return (DVCSTime) obj;
        }
        if (obj instanceof ASN1GeneralizedTime) {
            return new DVCSTime(ASN1GeneralizedTime.getInstance(obj));
        }
        if (obj != null) {
            return new DVCSTime(ContentInfo.getInstance(obj));
        }
        return null;
    }

    public static DVCSTime getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(aSN1TaggedObject.getObject());
    }

    public ASN1GeneralizedTime getGenTime() {
        return this.genTime;
    }

    public ContentInfo getTimeStampToken() {
        return this.timeStampToken;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1GeneralizedTime aSN1GeneralizedTime = this.genTime;
        if (aSN1GeneralizedTime != null) {
            return aSN1GeneralizedTime;
        }
        return this.timeStampToken.toASN1Primitive();
    }

    public String toString() {
        ASN1GeneralizedTime aSN1GeneralizedTime = this.genTime;
        if (aSN1GeneralizedTime != null) {
            return aSN1GeneralizedTime.toString();
        }
        return this.timeStampToken.toString();
    }
}
