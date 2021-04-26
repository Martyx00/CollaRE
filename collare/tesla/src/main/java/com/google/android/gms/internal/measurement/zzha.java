package com.google.android.gms.internal.measurement;

final class zzha implements Runnable {
    private final /* synthetic */ zzeb zzapd;
    private final /* synthetic */ zzgp zzape;
    private final /* synthetic */ zzex zzaph;

    zzha(zzgp zzgp, zzex zzex, zzeb zzeb) {
        this.zzape = zzgp;
        this.zzaph = zzex;
        this.zzapd = zzeb;
    }

    public final void run() {
        zzgp.zza(this.zzape).zzlj();
        zzgp.zza(this.zzape).zzb(this.zzaph, this.zzapd);
    }
}
