package com.google.android.gms.internal.measurement;

/* access modifiers changed from: package-private */
public final /* synthetic */ class zzcv implements Runnable {
    private final zzcu zzabn;
    private final int zzabo;
    private final zzcm zzabp;

    zzcv(zzcu zzcu, int i, zzcm zzcm) {
        this.zzabn = zzcu;
        this.zzabo = i;
        this.zzabp = zzcm;
    }

    public final void run() {
        this.zzabn.zza(this.zzabo, this.zzabp);
    }
}
