package com.google.android.gms.internal.measurement;

final class zzba implements Runnable {
    private final /* synthetic */ zzci zzwr;
    private final /* synthetic */ zzaz zzws;

    zzba(zzaz zzaz, zzci zzci) {
        this.zzws = zzaz;
        this.zzwr = zzci;
    }

    public final void run() {
        if (!this.zzws.zzwo.isConnected()) {
            this.zzws.zzwo.zzr("Connected to service after a timeout");
            this.zzws.zzwo.zza((zzax) this.zzwr);
        }
    }
}
