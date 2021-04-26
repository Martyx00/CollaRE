package com.google.android.gms.internal.measurement;

final class zzdx implements Runnable {
    private final /* synthetic */ long zzaex;
    private final /* synthetic */ zzdu zzaey;

    zzdx(zzdu zzdu, long j) {
        this.zzaey = zzdu;
        this.zzaex = j;
    }

    public final void run() {
        this.zzaey.zzq(this.zzaex);
    }
}
