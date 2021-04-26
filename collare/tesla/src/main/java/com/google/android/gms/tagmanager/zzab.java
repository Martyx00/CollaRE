package com.google.android.gms.tagmanager;

/* access modifiers changed from: package-private */
public final class zzab implements zzac {
    private final /* synthetic */ zzy zzayp;
    private Long zzayq;
    private final /* synthetic */ boolean zzayr;

    zzab(zzy zzy, boolean z) {
        this.zzayp = zzy;
        this.zzayr = z;
    }

    @Override // com.google.android.gms.tagmanager.zzac
    public final boolean zzb(Container container) {
        if (!this.zzayr) {
            return !container.isDefault();
        }
        long lastRefreshTime = container.getLastRefreshTime();
        if (this.zzayq == null) {
            this.zzayq = Long.valueOf(this.zzayp.zzayg.zzmy());
        }
        return lastRefreshTime + this.zzayq.longValue() >= this.zzayp.clock.currentTimeMillis();
    }
}
