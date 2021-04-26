package com.google.android.gms.internal.measurement;

/* access modifiers changed from: package-private */
public final class zzic implements Runnable {
    private final /* synthetic */ long zzadq;
    private final /* synthetic */ zzhm zzaps;

    zzic(zzhm zzhm, long j) {
        this.zzaps = zzhm;
        this.zzadq = j;
    }

    public final void run() {
        this.zzaps.zzgj().zzamh.set(this.zzadq);
        this.zzaps.zzgi().zzjb().zzg("Minimum session duration set", Long.valueOf(this.zzadq));
    }
}
