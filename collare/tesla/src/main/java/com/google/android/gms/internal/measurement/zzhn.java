package com.google.android.gms.internal.measurement;

import java.util.concurrent.atomic.AtomicReference;

/* access modifiers changed from: package-private */
public final class zzhn implements Runnable {
    private final /* synthetic */ AtomicReference zzapr;
    private final /* synthetic */ zzhm zzaps;

    zzhn(zzhm zzhm, AtomicReference atomicReference) {
        this.zzaps = zzhm;
        this.zzapr = atomicReference;
    }

    public final void run() {
        synchronized (this.zzapr) {
            try {
                this.zzapr.set(Boolean.valueOf(this.zzaps.zzgk().zzaz(this.zzaps.zzfz().zzah())));
                this.zzapr.notify();
            } catch (Throwable th) {
                this.zzapr.notify();
                throw th;
            }
        }
    }
}
