package com.google.android.gms.internal.measurement;

public final class zzkq extends zzacd<zzkq> {
    private static volatile zzkq[] zzauw;
    public Integer zzaux = null;
    public Long zzauy = null;

    public zzkq() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzkq[] zzlx() {
        if (zzauw == null) {
            synchronized (zzach.zzbzn) {
                if (zzauw == null) {
                    zzauw = new zzkq[0];
                }
            }
        }
        return zzauw;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkq)) {
            return false;
        }
        zzkq zzkq = (zzkq) obj;
        Integer num = this.zzaux;
        if (num == null) {
            if (zzkq.zzaux != null) {
                return false;
            }
        } else if (!num.equals(zzkq.zzaux)) {
            return false;
        }
        Long l = this.zzauy;
        if (l == null) {
            if (zzkq.zzauy != null) {
                return false;
            }
        } else if (!l.equals(zzkq.zzauy)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzkq.zzbzd == null || zzkq.zzbzd.isEmpty() : this.zzbzd.equals(zzkq.zzbzd);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzaux;
        int i = 0;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        Long l = this.zzauy;
        int hashCode3 = (hashCode2 + (l == null ? 0 : l.hashCode())) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode3 + i;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int zza = super.zza();
        Integer num = this.zzaux;
        if (num != null) {
            zza += zzacb.zzf(1, num.intValue());
        }
        Long l = this.zzauy;
        return l != null ? zza + zzacb.zzc(2, l.longValue()) : zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        Integer num = this.zzaux;
        if (num != null) {
            zzacb.zze(1, num.intValue());
        }
        Long l = this.zzauy;
        if (l != null) {
            zzacb.zzb(2, l.longValue());
        }
        super.zza(zzacb);
    }

    @Override // com.google.android.gms.internal.measurement.zzacj
    public final /* synthetic */ zzacj zzb(zzaca zzaca) {
        while (true) {
            int zzvl = zzaca.zzvl();
            if (zzvl == 0) {
                return this;
            }
            if (zzvl == 8) {
                this.zzaux = Integer.valueOf(zzaca.zzvn());
            } else if (zzvl == 16) {
                this.zzauy = Long.valueOf(zzaca.zzvo());
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
