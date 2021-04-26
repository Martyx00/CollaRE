package com.google.android.gms.common;

import java.util.concurrent.Callable;

/* access modifiers changed from: package-private */
public final /* synthetic */ class zzd implements Callable {
    private final boolean zzq;
    private final String zzr;
    private final zze zzs;

    zzd(boolean z, String str, zze zze) {
        this.zzq = z;
        this.zzr = str;
        this.zzs = zze;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        return zzc.zza(this.zzq, this.zzr, this.zzs);
    }
}
