package com.google.android.gms.internal.measurement;

final class zzhg implements Runnable {
    private final /* synthetic */ zzeb zzapd;
    private final /* synthetic */ zzgp zzape;

    zzhg(zzgp zzgp, zzeb zzeb) {
        this.zzape = zzgp;
        this.zzapd = zzeb;
    }

    public final void run() {
        zzgp.zza(this.zzape).zzlj();
        zzgp.zza(this.zzape).zzf(this.zzapd);
    }
}
