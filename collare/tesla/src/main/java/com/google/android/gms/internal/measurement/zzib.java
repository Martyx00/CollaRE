package com.google.android.gms.internal.measurement;

/* access modifiers changed from: package-private */
public final class zzib implements Runnable {
    private final /* synthetic */ boolean zzadp;
    private final /* synthetic */ zzhm zzaps;

    zzib(zzhm zzhm, boolean z) {
        this.zzaps = zzhm;
        this.zzadp = z;
    }

    public final void run() {
        this.zzaps.zzi(this.zzadp);
    }
}
