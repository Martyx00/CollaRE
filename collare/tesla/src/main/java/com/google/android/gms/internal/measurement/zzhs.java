package com.google.android.gms.internal.measurement;

/* access modifiers changed from: package-private */
public final class zzhs implements Runnable {
    private final /* synthetic */ zzhm zzaps;
    private final /* synthetic */ long zzapv;

    zzhs(zzhm zzhm, long j) {
        this.zzaps = zzhm;
        this.zzapv = j;
    }

    public final void run() {
        zzhm zzhm = this.zzaps;
        long j = this.zzapv;
        zzhm.zzab();
        zzhm.zzfv();
        zzhm.zzch();
        zzhm.zzgi().zzjb().log("Resetting analytics data (FE)");
        zzhm.zzgd().zzkv();
        if (zzhm.zzgk().zzbd(zzhm.zzfz().zzah())) {
            zzhm.zzgj().zzaly.set(j);
        }
        boolean isEnabled = zzhm.zzacv.isEnabled();
        if (!zzhm.zzgk().zzho()) {
            zzhm.zzgj().zzh(!isEnabled);
        }
        zzhm.zzga().resetAnalyticsData();
        zzhm.zzapq = !isEnabled;
    }
}
