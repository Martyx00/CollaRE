package com.google.android.gms.internal.measurement;

final class zzhd implements Runnable {
    private final /* synthetic */ zzeb zzapd;
    private final /* synthetic */ zzgp zzape;
    private final /* synthetic */ zzka zzapi;

    zzhd(zzgp zzgp, zzka zzka, zzeb zzeb) {
        this.zzape = zzgp;
        this.zzapi = zzka;
        this.zzapd = zzeb;
    }

    public final void run() {
        zzgp.zza(this.zzape).zzlj();
        zzgp.zza(this.zzape).zzc(this.zzapi, this.zzapd);
    }
}
