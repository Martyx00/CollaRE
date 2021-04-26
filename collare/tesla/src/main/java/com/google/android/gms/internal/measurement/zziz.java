package com.google.android.gms.internal.measurement;

final class zziz implements Runnable {
    private final /* synthetic */ zzfa zzard;
    private final /* synthetic */ zziy zzare;

    zziz(zziy zziy, zzfa zzfa) {
        this.zzare = zziy;
        this.zzard = zzfa;
    }

    public final void run() {
        synchronized (this.zzare) {
            zziy.zza(this.zzare, false);
            if (!this.zzare.zzaqv.isConnected()) {
                this.zzare.zzaqv.zzgi().zzjc().log("Connected to service");
                this.zzare.zzaqv.zza(this.zzard);
            }
        }
    }
}
