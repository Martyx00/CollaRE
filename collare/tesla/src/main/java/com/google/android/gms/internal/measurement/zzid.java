package com.google.android.gms.internal.measurement;

/* access modifiers changed from: package-private */
public final class zzid implements Runnable {
    private final /* synthetic */ long zzadq;
    private final /* synthetic */ zzhm zzaps;

    zzid(zzhm zzhm, long j) {
        this.zzaps = zzhm;
        this.zzadq = j;
    }

    public final void run() {
        this.zzaps.zzgj().zzami.set(this.zzadq);
        this.zzaps.zzgi().zzjb().zzg("Session timeout duration set", Long.valueOf(this.zzadq));
    }
}
