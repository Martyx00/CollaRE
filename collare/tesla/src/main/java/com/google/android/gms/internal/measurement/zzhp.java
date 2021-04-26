package com.google.android.gms.internal.measurement;

import java.util.concurrent.atomic.AtomicReference;

/* access modifiers changed from: package-private */
public final class zzhp implements Runnable {
    private final /* synthetic */ boolean zzadv;
    private final /* synthetic */ AtomicReference zzapr;
    private final /* synthetic */ zzhm zzaps;

    zzhp(zzhm zzhm, AtomicReference atomicReference, boolean z) {
        this.zzaps = zzhm;
        this.zzapr = atomicReference;
        this.zzadv = z;
    }

    public final void run() {
        this.zzaps.zzga().zza(this.zzapr, this.zzadv);
    }
}
