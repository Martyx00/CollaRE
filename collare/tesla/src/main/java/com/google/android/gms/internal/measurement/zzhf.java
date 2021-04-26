package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.concurrent.Callable;

final class zzhf implements Callable<List<zzkc>> {
    private final /* synthetic */ zzeb zzapd;
    private final /* synthetic */ zzgp zzape;

    zzhf(zzgp zzgp, zzeb zzeb) {
        this.zzape = zzgp;
        this.zzapd = zzeb;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<zzkc> call() {
        zzgp.zza(this.zzape).zzlj();
        return zzgp.zza(this.zzape).zzjh().zzbe(this.zzapd.packageName);
    }
}
