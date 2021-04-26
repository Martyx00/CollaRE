package com.google.android.gms.internal.measurement;

final class zzak implements Runnable {
    private final /* synthetic */ zzai zzvg;
    private final /* synthetic */ boolean zzvh;

    zzak(zzai zzai, boolean z) {
        this.zzvg = zzai;
        this.zzvh = z;
    }

    public final void run() {
        this.zzvg.zzve.zzdi();
    }
}
