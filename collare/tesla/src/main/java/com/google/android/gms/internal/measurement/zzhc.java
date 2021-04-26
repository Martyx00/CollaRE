package com.google.android.gms.internal.measurement;

import java.util.concurrent.Callable;

final class zzhc implements Callable<byte[]> {
    private final /* synthetic */ zzgp zzape;
    private final /* synthetic */ String zzapg;
    private final /* synthetic */ zzex zzaph;

    zzhc(zzgp zzgp, zzex zzex, String str) {
        this.zzape = zzgp;
        this.zzaph = zzex;
        this.zzapg = str;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final /* synthetic */ byte[] call() {
        zzgp.zza(this.zzape).zzlj();
        return zzgp.zza(this.zzape).zza(this.zzaph, this.zzapg);
    }
}
