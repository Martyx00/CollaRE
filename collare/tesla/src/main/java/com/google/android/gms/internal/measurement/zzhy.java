package com.google.android.gms.internal.measurement;

import java.util.concurrent.atomic.AtomicReference;

/* access modifiers changed from: package-private */
public final class zzhy implements Runnable {
    private final /* synthetic */ AtomicReference zzapr;
    private final /* synthetic */ zzhm zzaps;

    zzhy(zzhm zzhm, AtomicReference atomicReference) {
        this.zzaps = zzhm;
        this.zzapr = atomicReference;
    }

    public final void run() {
        synchronized (this.zzapr) {
            try {
                this.zzapr.set(Long.valueOf(this.zzaps.zzgk().zza(this.zzaps.zzfz().zzah(), zzez.zzajn)));
                this.zzapr.notify();
            } catch (Throwable th) {
                this.zzapr.notify();
                throw th;
            }
        }
    }
}
