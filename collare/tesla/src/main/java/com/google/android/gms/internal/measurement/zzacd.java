package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzacd;

public abstract class zzacd<M extends zzacd<M>> extends zzacj {
    protected zzacf zzbzd;

    @Override // com.google.android.gms.internal.measurement.zzacj
    public /* synthetic */ Object clone() {
        zzacd zzacd = (zzacd) super.clone();
        zzach.zza(this, zzacd);
        return zzacd;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj
    public int zza() {
        if (this.zzbzd == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.zzbzd.size(); i2++) {
            i += this.zzbzd.zzau(i2).zza();
        }
        return i;
    }

    public final <T> T zza(zzace<M, T> zzace) {
        zzacg zzat;
        zzacf zzacf = this.zzbzd;
        if (zzacf == null || (zzat = zzacf.zzat(zzace.tag >>> 3)) == null) {
            return null;
        }
        return (T) zzat.zzb(zzace);
    }

    @Override // com.google.android.gms.internal.measurement.zzacj
    public void zza(zzacb zzacb) {
        if (this.zzbzd != null) {
            for (int i = 0; i < this.zzbzd.size(); i++) {
                this.zzbzd.zzau(i).zza(zzacb);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzaca zzaca, int i) {
        int position = zzaca.getPosition();
        if (!zzaca.zzak(i)) {
            return false;
        }
        int i2 = i >>> 3;
        zzacl zzacl = new zzacl(i, zzaca.zzc(position, zzaca.getPosition() - position));
        zzacg zzacg = null;
        zzacf zzacf = this.zzbzd;
        if (zzacf == null) {
            this.zzbzd = new zzacf();
        } else {
            zzacg = zzacf.zzat(i2);
        }
        if (zzacg == null) {
            zzacg = new zzacg();
            this.zzbzd.zza(i2, zzacg);
        }
        zzacg.zza(zzacl);
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj
    public final /* synthetic */ zzacj zzvu() {
        return (zzacd) clone();
    }
}
