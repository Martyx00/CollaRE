package com.google.android.gms.internal.measurement;

final class zzgz implements Runnable {
    private final /* synthetic */ zzeb zzapd;
    private final /* synthetic */ zzgp zzape;

    zzgz(zzgp zzgp, zzeb zzeb) {
        this.zzape = zzgp;
        this.zzapd = zzeb;
    }

    public final void run() {
        zzgp.zza(this.zzape).zzlj();
        zzgp.zza(this.zzape).zzd(this.zzapd);
    }
}
