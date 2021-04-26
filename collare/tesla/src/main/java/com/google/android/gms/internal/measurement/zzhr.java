package com.google.android.gms.internal.measurement;

import java.util.concurrent.atomic.AtomicReference;

/* access modifiers changed from: package-private */
public final class zzhr implements Runnable {
    private final /* synthetic */ AtomicReference zzapr;
    private final /* synthetic */ zzhm zzaps;

    zzhr(zzhm zzhm, AtomicReference atomicReference) {
        this.zzaps = zzhm;
        this.zzapr = atomicReference;
    }

    public final void run() {
        this.zzaps.zzga().zza(this.zzapr);
    }
}
