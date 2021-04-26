package org.spongycastle.asn1;

import java.util.Date;

public class DERGeneralizedTime extends ASN1GeneralizedTime {
    DERGeneralizedTime(byte[] bArr) {
        super(bArr);
    }

    public DERGeneralizedTime(Date date) {
        super(date);
    }

    public DERGeneralizedTime(String str) {
        super(str);
    }
}
