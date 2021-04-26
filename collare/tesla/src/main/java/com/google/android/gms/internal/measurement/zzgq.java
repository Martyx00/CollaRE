package com.google.android.gms.internal.measurement;

final class zzgq implements Runnable {
    private final /* synthetic */ zzeb zzapd;
    private final /* synthetic */ zzgp zzape;

    zzgq(zzgp zzgp, zzeb zzeb) {
        this.zzape = zzgp;
        this.zzapd = zzeb;
    }

    public final void run() {
        zzgp.zza(this.zzape).zzlj();
        zzgp.zza(this.zzape).zze(this.zzapd);
    }
}
