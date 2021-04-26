package com.google.android.gms.internal.measurement;

final class zzgt implements Runnable {
    private final /* synthetic */ zzgp zzape;
    private final /* synthetic */ zzef zzapf;

    zzgt(zzgp zzgp, zzef zzef) {
        this.zzape = zzgp;
        this.zzapf = zzef;
    }

    public final void run() {
        zzgp.zza(this.zzape).zzlj();
        zzgp.zza(this.zzape).zzf(this.zzapf);
    }
}
