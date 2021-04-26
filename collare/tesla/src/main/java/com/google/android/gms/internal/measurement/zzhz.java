package com.google.android.gms.internal.measurement;

import java.util.concurrent.atomic.AtomicReference;

/* access modifiers changed from: package-private */
public final class zzhz implements Runnable {
    private final /* synthetic */ AtomicReference zzapr;
    private final /* synthetic */ zzhm zzaps;

    zzhz(zzhm zzhm, AtomicReference atomicReference) {
        this.zzaps = zzhm;
        this.zzapr = atomicReference;
    }

    public final void run() {
        synchronized (this.zzapr) {
            try {
                this.zzapr.set(Integer.valueOf(this.zzaps.zzgk().zzb(this.zzaps.zzfz().zzah(), zzez.zzajo)));
                this.zzapr.notify();
            } catch (Throwable th) {
                this.zzapr.notify();
                throw th;
            }
        }
    }
}
