package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.concurrent.Callable;

final class zzgv implements Callable<List<zzkc>> {
    private final /* synthetic */ String zzadn;
    private final /* synthetic */ String zzadu;
    private final /* synthetic */ zzeb zzapd;
    private final /* synthetic */ zzgp zzape;

    zzgv(zzgp zzgp, zzeb zzeb, String str, String str2) {
        this.zzape = zzgp;
        this.zzapd = zzeb;
        this.zzadn = str;
        this.zzadu = str2;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<zzkc> call() {
        zzgp.zza(this.zzape).zzlj();
        return zzgp.zza(this.zzape).zzjh().zzb(this.zzapd.packageName, this.zzadn, this.zzadu);
    }
}
