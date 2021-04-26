package com.google.android.gms.internal.measurement;

import java.util.concurrent.atomic.AtomicReference;

/* access modifiers changed from: package-private */
public final class zzhw implements Runnable {
    private final /* synthetic */ String zzadn;
    private final /* synthetic */ String zzadu;
    private final /* synthetic */ boolean zzadv;
    private final /* synthetic */ String zzapg;
    private final /* synthetic */ AtomicReference zzapr;
    private final /* synthetic */ zzhm zzaps;

    zzhw(zzhm zzhm, AtomicReference atomicReference, String str, String str2, String str3, boolean z) {
        this.zzaps = zzhm;
        this.zzapr = atomicReference;
        this.zzapg = str;
        this.zzadn = str2;
        this.zzadu = str3;
        this.zzadv = z;
    }

    public final void run() {
        this.zzaps.zzacv.zzga().zza(this.zzapr, this.zzapg, this.zzadn, this.zzadu, this.zzadv);
    }
}
