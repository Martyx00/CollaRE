package com.google.android.gms.internal.measurement;

final class zzdw implements Runnable {
    private final /* synthetic */ String zzadr;
    private final /* synthetic */ long zzaex;
    private final /* synthetic */ zzdu zzaey;

    zzdw(zzdu zzdu, String str, long j) {
        this.zzaey = zzdu;
        this.zzadr = str;
        this.zzaex = j;
    }

    public final void run() {
        zzdu.zzb(this.zzaey, this.zzadr, this.zzaex);
    }
}
