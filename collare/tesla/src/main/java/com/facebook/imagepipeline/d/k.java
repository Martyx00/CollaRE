package com.facebook.imagepipeline.d;

import com.facebook.common.d.l;
import com.google.android.gms.common.api.Api;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* compiled from: DefaultEncodedMemoryCacheParamsSupplier */
public class k implements l<q> {
    /* renamed from: a */
    public q b() {
        int c2 = c();
        return new q(c2, Api.BaseClientBuilder.API_PRIORITY_OTHER, c2, Api.BaseClientBuilder.API_PRIORITY_OTHER, c2 / 8);
    }

    private int c() {
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min < 16777216) {
            return PKIFailureInfo.badCertTemplate;
        }
        if (min < 33554432) {
            return PKIFailureInfo.badSenderNonce;
        }
        return 4194304;
    }
}
