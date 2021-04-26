package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.concurrent.Callable;

final class zzgy implements Callable<List<zzef>> {
    private final /* synthetic */ String zzadn;
    private final /* synthetic */ String zzadu;
    private final /* synthetic */ zzgp zzape;
    private final /* synthetic */ String zzapg;

    zzgy(zzgp zzgp, String str, String str2, String str3) {
        this.zzape = zzgp;
        this.zzapg = str;
        this.zzadn = str2;
        this.zzadu = str3;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<zzef> call() {
        zzgp.zza(this.zzape).zzlj();
        return zzgp.zza(this.zzape).zzjh().zzc(this.zzapg, this.zzadn, this.zzadu);
    }
}
