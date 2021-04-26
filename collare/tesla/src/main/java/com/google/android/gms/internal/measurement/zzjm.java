package com.google.android.gms.internal.measurement;

final class zzjm implements Runnable {
    private final /* synthetic */ long zzaex;
    private final /* synthetic */ zzjj zzaro;

    zzjm(zzjj zzjj, long j) {
        this.zzaro = zzjj;
        this.zzaex = j;
    }

    public final void run() {
        this.zzaro.zzak(this.zzaex);
    }
}
