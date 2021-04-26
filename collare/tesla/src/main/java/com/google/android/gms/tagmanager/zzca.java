package com.google.android.gms.tagmanager;

final class zzca implements Runnable {
    private final /* synthetic */ zzby zzban;
    private final /* synthetic */ long zzbao;
    private final /* synthetic */ String zzbap;
    private final /* synthetic */ zzbz zzbaq;

    zzca(zzbz zzbz, zzby zzby, long j, String str) {
        this.zzbaq = zzbz;
        this.zzban = zzby;
        this.zzbao = j;
        this.zzbap = str;
    }

    public final void run() {
        if (this.zzbaq.zzbam == null) {
            zzfn zzpc = zzfn.zzpc();
            zzpc.zza(this.zzbaq.zzqx, this.zzban);
            this.zzbaq.zzbam = zzpc.zzpd();
        }
        this.zzbaq.zzbam.zzb(this.zzbao, this.zzbap);
    }
}
