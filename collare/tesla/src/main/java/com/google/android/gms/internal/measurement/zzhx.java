package com.google.android.gms.internal.measurement;

import java.util.concurrent.atomic.AtomicReference;

/* access modifiers changed from: package-private */
public final class zzhx implements Runnable {
    private final /* synthetic */ AtomicReference zzapr;
    private final /* synthetic */ zzhm zzaps;

    zzhx(zzhm zzhm, AtomicReference atomicReference) {
        this.zzaps = zzhm;
        this.zzapr = atomicReference;
    }

    public final void run() {
        synchronized (this.zzapr) {
            try {
                this.zzapr.set(this.zzaps.zzgk().zzba(this.zzaps.zzfz().zzah()));
                this.zzapr.notify();
            } catch (Throwable th) {
                this.zzapr.notify();
                throw th;
            }
        }
    }
}
