package com.google.android.gms.internal.measurement;

final class zzjn implements Runnable {
    private final /* synthetic */ long zzaex;
    private final /* synthetic */ zzjj zzaro;

    zzjn(zzjj zzjj, long j) {
        this.zzaro = zzjj;
        this.zzaex = j;
    }

    public final void run() {
        this.zzaro.zzal(this.zzaex);
    }
}
