package com.google.android.gms.internal.measurement;

/* access modifiers changed from: package-private */
public final class zzal implements Runnable {
    private final /* synthetic */ zzai zzvg;
    private final /* synthetic */ String zzvi;
    private final /* synthetic */ Runnable zzvj;

    zzal(zzai zzai, String str, Runnable runnable) {
        this.zzvg = zzai;
        this.zzvi = str;
        this.zzvj = runnable;
    }

    public final void run() {
        this.zzvg.zzve.zzy(this.zzvi);
        Runnable runnable = this.zzvj;
        if (runnable != null) {
            runnable.run();
        }
    }
}
