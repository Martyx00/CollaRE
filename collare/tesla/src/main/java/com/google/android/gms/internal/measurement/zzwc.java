package com.google.android.gms.internal.measurement;

public final class zzwc extends zzacd<zzwc> {
    public long zzboa = 0;
    public zzl zzbob = null;
    public zzi zzpv = null;

    public zzwc() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzwc)) {
            return false;
        }
        zzwc zzwc = (zzwc) obj;
        if (this.zzboa != zzwc.zzboa) {
            return false;
        }
        zzi zzi = this.zzpv;
        if (zzi == null) {
            if (zzwc.zzpv != null) {
                return false;
            }
        } else if (!zzi.equals(zzwc.zzpv)) {
            return false;
        }
        zzl zzl = this.zzbob;
        if (zzl == null) {
            if (zzwc.zzbob != null) {
                return false;
            }
        } else if (!zzl.equals(zzwc.zzbob)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzwc.zzbzd == null || zzwc.zzbzd.isEmpty() : this.zzbzd.equals(zzwc.zzbzd);
    }

    public final int hashCode() {
        long j = this.zzboa;
        zzi zzi = this.zzpv;
        int i = 0;
        int hashCode = ((((getClass().getName().hashCode() + 527) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + (zzi == null ? 0 : zzi.hashCode());
        zzl zzl = this.zzbob;
        int hashCode2 = ((hashCode * 31) + (zzl == null ? 0 : zzl.hashCode())) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode2 + i;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int zza = super.zza() + zzacb.zzc(1, this.zzboa);
        zzi zzi = this.zzpv;
        if (zzi != null) {
            zza += zzacb.zzb(2, zzi);
        }
        zzl zzl = this.zzbob;
        return zzl != null ? zza + zzacb.zzb(3, zzl) : zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        zzacb.zzb(1, this.zzboa);
        zzi zzi = this.zzpv;
        if (zzi != null) {
            zzacb.zza(2, zzi);
        }
        zzl zzl = this.zzbob;
        if (zzl != null) {
            zzacb.zza(3, zzl);
        }
        super.zza(zzacb);
    }

    @Override // com.google.android.gms.internal.measurement.zzacj
    public final /* synthetic */ zzacj zzb(zzaca zzaca) {
        zzacj zzacj;
        while (true) {
            int zzvl = zzaca.zzvl();
            if (zzvl == 0) {
                return this;
            }
            if (zzvl != 8) {
                if (zzvl == 18) {
                    if (this.zzpv == null) {
                        this.zzpv = new zzi();
                    }
                    zzacj = this.zzpv;
                } else if (zzvl == 26) {
                    if (this.zzbob == null) {
                        this.zzbob = new zzl();
                    }
                    zzacj = this.zzbob;
                } else if (!super.zza(zzaca, zzvl)) {
                    return this;
                }
                zzaca.zza(zzacj);
            } else {
                this.zzboa = zzaca.zzvo();
            }
        }
    }
}
