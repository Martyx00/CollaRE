package com.google.android.gms.tagmanager;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.measurement.zzl;

/* access modifiers changed from: package-private */
public final class zzae implements zzdh<zzl> {
    private final /* synthetic */ zzy zzayp;

    private zzae(zzy zzy) {
        this.zzayp = zzy;
    }

    /* synthetic */ zzae(zzy zzy, zzz zzz) {
        this(zzy);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // com.google.android.gms.tagmanager.zzdh
    public final /* synthetic */ void onSuccess(zzl zzl) {
        zzl zzl2 = zzl;
        this.zzayp.zzayg.zznb();
        synchronized (this.zzayp) {
            if (zzl2.zzpv == null) {
                if (this.zzayp.zzayl.zzpv == null) {
                    zzdi.e("Current resource is null; network resource is also null");
                    this.zzayp.zzam(this.zzayp.zzayg.zzmz());
                    return;
                }
                zzl2.zzpv = this.zzayp.zzayl.zzpv;
            }
            this.zzayp.zza(zzl2, this.zzayp.clock.currentTimeMillis(), false);
            long j = this.zzayp.zzaxr;
            StringBuilder sb = new StringBuilder(58);
            sb.append("setting refresh time to current time: ");
            sb.append(j);
            zzdi.v(sb.toString());
            if (!(this.zzayp.zzmv())) {
                this.zzayp.zza((zzy) zzl2);
            }
        }
    }

    @Override // com.google.android.gms.tagmanager.zzdh
    public final void zzmw() {
    }

    @Override // com.google.android.gms.tagmanager.zzdh
    public final void zzq(int i) {
        zzy zzy;
        ContainerHolder a_;
        if (i == zzcz.zzbbe) {
            this.zzayp.zzayg.zzna();
        }
        synchronized (this.zzayp) {
            if (!this.zzayp.isReady()) {
                if (this.zzayp.zzayj != null) {
                    zzy = this.zzayp;
                    a_ = this.zzayp.zzayj;
                } else {
                    zzy = this.zzayp;
                    a_ = this.zzayp.createFailedResult(Status.RESULT_TIMEOUT);
                }
                zzy.setResult(a_);
            }
        }
        this.zzayp.zzam(this.zzayp.zzayg.zzmz());
    }
}
