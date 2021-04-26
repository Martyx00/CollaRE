package com.google.android.gms.internal.measurement;

import java.util.concurrent.atomic.AtomicReference;

/* access modifiers changed from: package-private */
public final class zzia implements Runnable {
    private final /* synthetic */ AtomicReference zzapr;
    private final /* synthetic */ zzhm zzaps;

    zzia(zzhm zzhm, AtomicReference atomicReference) {
        this.zzaps = zzhm;
        this.zzapr = atomicReference;
    }

    public final void run() {
        synchronized (this.zzapr) {
            try {
                this.zzapr.set(Double.valueOf(this.zzaps.zzgk().zzc(this.zzaps.zzfz().zzah(), zzez.zzajp)));
                this.zzapr.notify();
            } catch (Throwable th) {
                this.zzapr.notify();
                throw th;
            }
        }
    }
}
