package org.spongycastle.asn1.cms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.ASN1UTCTime;
import org.spongycastle.asn1.DERGeneralizedTime;
import org.spongycastle.asn1.DERUTCTime;

public class Time extends ASN1Object implements ASN1Choice {
    ASN1Primitive time;

    public static Time getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(aSN1TaggedObject.getObject());
    }

    public Time(ASN1Primitive aSN1Primitive) {
        if ((aSN1Primitive instanceof ASN1UTCTime) || (aSN1Primitive instanceof ASN1GeneralizedTime)) {
            this.time = aSN1Primitive;
            return;
        }
        throw new IllegalArgumentException("unknown object passed to Time");
    }

    public Time(Date date) {
        SimpleTimeZone simpleTimeZone = new SimpleTimeZone(0, "Z");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        simpleDateFormat.setTimeZone(simpleTimeZone);
        String str = simpleDateFormat.format(date) + "Z";
        int parseInt = Integer.parseInt(str.substring(0, 4));
        if (parseInt < 1950 || parseInt > 2049) {
            this.time = new DERGeneralizedTime(str);
        } else {
            this.time = new DERUTCTime(str.substring(2));
        }
    }

    public Time(Date date, Locale locale) {
        SimpleTimeZone simpleTimeZone = new SimpleTimeZone(0, "Z");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", locale);
        simpleDateFormat.setTimeZone(simpleTimeZone);
        String str = simpleDateFormat.format(date) + "Z";
        int parseInt = Integer.parseInt(str.substring(0, 4));
        if (parseInt < 1950 || parseInt > 2049) {
            this.time = new DERGeneralizedTime(str);
        } else {
            this.time = new DERUTCTime(str.substring(2));
        }
    }

    public static Time getInstance(Object obj) {
        if (obj == null || (obj instanceof Time)) {
            return (Time) obj;
        }
        if (obj instanceof ASN1UTCTime) {
            return new Time((ASN1UTCTime) obj);
        }
        if (obj instanceof ASN1GeneralizedTime) {
            return new Time((ASN1GeneralizedTime) obj);
        }
        throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
    }

    public String getTime() {
        ASN1Primitive aSN1Primitive = this.time;
        if (aSN1Primitive instanceof ASN1UTCTime) {
            return ((ASN1UTCTime) aSN1Primitive).getAdjustedTime();
        }
        return ((ASN1GeneralizedTime) aSN1Primitive).getTime();
    }

    public Date getDate() {
        try {
            if (this.time instanceof ASN1UTCTime) {
                return ((ASN1UTCTime) this.time).getAdjustedDate();
            }
            return ((ASN1GeneralizedTime) this.time).getDate();
        } catch (ParseException e) {
            throw new IllegalStateException("invalid date string: " + e.getMessage());
        }
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        return this.time;
    }
}
