package com.google.android.gms.internal.measurement;

import java.util.concurrent.Callable;

/* access modifiers changed from: package-private */
public final class zzjx implements Callable<String> {
    private final /* synthetic */ zzeb zzapd;
    private final /* synthetic */ zzjt zzasn;

    zzjx(zzjt zzjt, zzeb zzeb) {
        this.zzasn = zzjt;
        this.zzapd = zzeb;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() {
        zzea zzbf = this.zzasn.zzgk().zzbc(this.zzapd.packageName) ? this.zzasn.zzg(this.zzapd) : this.zzasn.zzjh().zzbf(this.zzapd.packageName);
        if (zzbf != null) {
            return zzbf.getAppInstanceId();
        }
        this.zzasn.zzgi().zziy().log("App info was null when attempting to get app instance id");
        return null;
    }
}
