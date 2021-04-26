package com.facebook.imagepipeline.d;

import android.app.ActivityManager;
import android.os.Build;
import com.facebook.common.d.l;
import com.google.android.gms.common.api.Api;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* compiled from: DefaultBitmapMemoryCacheParamsSupplier */
public class i implements l<q> {

    /* renamed from: a  reason: collision with root package name */
    private final ActivityManager f2063a;

    public i(ActivityManager activityManager) {
        this.f2063a = activityManager;
    }

    /* renamed from: a */
    public q b() {
        return new q(c(), 256, Api.BaseClientBuilder.API_PRIORITY_OTHER, Api.BaseClientBuilder.API_PRIORITY_OTHER, Api.BaseClientBuilder.API_PRIORITY_OTHER);
    }

    private int c() {
        int min = Math.min(this.f2063a.getMemoryClass() * PKIFailureInfo.badCertTemplate, (int) Api.BaseClientBuilder.API_PRIORITY_OTHER);
        if (min < 33554432) {
            return 4194304;
        }
        if (min < 67108864) {
            return 6291456;
        }
        if (Build.VERSION.SDK_INT < 11) {
            return 8388608;
        }
        return min / 4;
    }
}
