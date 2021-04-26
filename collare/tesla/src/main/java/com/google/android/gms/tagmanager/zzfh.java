package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zzm;

/* access modifiers changed from: package-private */
public final class zzfh {
    private zzdz<zzm> zzbdm;
    private zzm zzbdn;

    public zzfh(zzdz<zzm> zzdz, zzm zzm) {
        this.zzbdm = zzdz;
        this.zzbdn = zzm;
    }

    public final int getSize() {
        int zzwa = this.zzbdm.getObject().zzwa();
        zzm zzm = this.zzbdn;
        return zzwa + (zzm == null ? 0 : zzm.zzwa());
    }

    public final zzdz<zzm> zzot() {
        return this.zzbdm;
    }

    public final zzm zzou() {
        return this.zzbdn;
    }
}
