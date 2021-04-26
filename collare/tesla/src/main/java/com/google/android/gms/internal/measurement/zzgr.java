package com.google.android.gms.internal.measurement;

final class zzgr implements Runnable {
    private final /* synthetic */ zzeb zzapd;
    private final /* synthetic */ zzgp zzape;
    private final /* synthetic */ zzef zzapf;

    zzgr(zzgp zzgp, zzef zzef, zzeb zzeb) {
        this.zzape = zzgp;
        this.zzapf = zzef;
        this.zzapd = zzeb;
    }

    public final void run() {
        zzgp.zza(this.zzape).zzlj();
        zzgp.zza(this.zzape).zzc(this.zzapf, this.zzapd);
    }
}
