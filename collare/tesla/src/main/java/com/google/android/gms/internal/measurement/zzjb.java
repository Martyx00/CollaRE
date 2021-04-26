package com.google.android.gms.internal.measurement;

final class zzjb implements Runnable {
    private final /* synthetic */ zziy zzare;
    private final /* synthetic */ zzfa zzarf;

    zzjb(zziy zziy, zzfa zzfa) {
        this.zzare = zziy;
        this.zzarf = zzfa;
    }

    public final void run() {
        synchronized (this.zzare) {
            zziy.zza(this.zzare, false);
            if (!this.zzare.zzaqv.isConnected()) {
                this.zzare.zzaqv.zzgi().zzjb().log("Connected to remote service");
                this.zzare.zzaqv.zza(this.zzarf);
            }
        }
    }
}
