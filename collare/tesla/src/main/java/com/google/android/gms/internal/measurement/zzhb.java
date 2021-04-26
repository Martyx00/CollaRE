package com.google.android.gms.internal.measurement;

final class zzhb implements Runnable {
    private final /* synthetic */ zzgp zzape;
    private final /* synthetic */ String zzapg;
    private final /* synthetic */ zzex zzaph;

    zzhb(zzgp zzgp, zzex zzex, String str) {
        this.zzape = zzgp;
        this.zzaph = zzex;
        this.zzapg = str;
    }

    public final void run() {
        zzgp.zza(this.zzape).zzlj();
        zzgp.zza(this.zzape).zzc(this.zzaph, this.zzapg);
    }
}
