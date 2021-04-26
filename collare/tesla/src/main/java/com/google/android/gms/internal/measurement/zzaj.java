package com.google.android.gms.internal.measurement;

/* access modifiers changed from: package-private */
public final class zzaj implements Runnable {
    private final /* synthetic */ int zzvf;
    private final /* synthetic */ zzai zzvg;

    zzaj(zzai zzai, int i) {
        this.zzvg = zzai;
        this.zzvf = i;
    }

    public final void run() {
        this.zzvg.zzve.zzg(((long) this.zzvf) * 1000);
    }
}
