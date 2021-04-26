package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zzi;
import com.google.android.gms.internal.measurement.zzl;
import com.google.android.gms.internal.measurement.zzwc;

/* access modifiers changed from: package-private */
public final class zzad implements zzdh<zzwc> {
    private final /* synthetic */ zzy zzayp;

    private zzad(zzy zzy) {
        this.zzayp = zzy;
    }

    /* synthetic */ zzad(zzy zzy, zzz zzz) {
        this(zzy);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // com.google.android.gms.tagmanager.zzdh
    public final /* synthetic */ void onSuccess(zzwc zzwc) {
        zzl zzl;
        zzwc zzwc2 = zzwc;
        if (zzwc2.zzbob != null) {
            zzl = zzwc2.zzbob;
        } else {
            zzi zzi = zzwc2.zzpv;
            zzl zzl2 = new zzl();
            zzl2.zzpv = zzi;
            zzl2.zzpu = null;
            zzl2.zzpw = zzi.version;
            zzl = zzl2;
        }
        this.zzayp.zza(zzl, zzwc2.zzboa, true);
    }

    @Override // com.google.android.gms.tagmanager.zzdh
    public final void zzmw() {
    }

    @Override // com.google.android.gms.tagmanager.zzdh
    public final void zzq(int i) {
        if (!(this.zzayp.zzayk)) {
            this.zzayp.zzam(0);
        }
    }
}
