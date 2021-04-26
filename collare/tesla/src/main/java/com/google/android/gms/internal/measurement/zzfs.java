package com.google.android.gms.internal.measurement;

final class zzfs implements Runnable {
    private final /* synthetic */ boolean zzalp;
    private final /* synthetic */ zzfr zzalq;

    zzfs(zzfr zzfr, boolean z) {
        this.zzalq = zzfr;
        this.zzalp = z;
    }

    public final void run() {
        zzfr.zza(this.zzalq).zzm(this.zzalp);
    }
}
