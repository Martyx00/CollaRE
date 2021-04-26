package com.google.android.gms.internal.measurement;

final class zzhh implements Runnable {
    private final /* synthetic */ String zzaem;
    private final /* synthetic */ zzgp zzape;
    private final /* synthetic */ String zzapg;
    private final /* synthetic */ String zzapj;
    private final /* synthetic */ long zzapk;

    zzhh(zzgp zzgp, String str, String str2, String str3, long j) {
        this.zzape = zzgp;
        this.zzapj = str;
        this.zzapg = str2;
        this.zzaem = str3;
        this.zzapk = j;
    }

    public final void run() {
        String str = this.zzapj;
        if (str == null) {
            zzgp.zza(this.zzape).zzlm().zzgb().zza(this.zzapg, (zzig) null);
            return;
        }
        zzgp.zza(this.zzape).zzlm().zzgb().zza(this.zzapg, new zzig(this.zzaem, str, this.zzapk));
    }
}
