package com.google.android.gms.internal.measurement;

public final class zzd extends zzacd<zzd> {
    private int level = 1;
    private int zznq = 0;
    private int zznr = 0;

    public zzd() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zza */
    public final zzd zzb(zzaca zzaca) {
        int zzvn;
        while (true) {
            int zzvl = zzaca.zzvl();
            if (zzvl == 0) {
                return this;
            }
            if (zzvl == 8) {
                try {
                    zzvn = zzaca.zzvn();
                    if (zzvn <= 0 || zzvn > 3) {
                        StringBuilder sb = new StringBuilder(42);
                        sb.append(zzvn);
                        sb.append(" is not a valid enum CacheLevel");
                    } else {
                        this.level = zzvn;
                    }
                } catch (IllegalArgumentException unused) {
                    zzaca.zzam(zzaca.getPosition());
                    zza(zzaca, zzvl);
                }
            } else if (zzvl == 16) {
                this.zznq = zzaca.zzvn();
            } else if (zzvl == 24) {
                this.zznr = zzaca.zzvn();
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
        StringBuilder sb2 = new StringBuilder(42);
        sb2.append(zzvn);
        sb2.append(" is not a valid enum CacheLevel");
        throw new IllegalArgumentException(sb2.toString());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzd)) {
            return false;
        }
        zzd zzd = (zzd) obj;
        if (this.level == zzd.level && this.zznq == zzd.zznq && this.zznr == zzd.zznr) {
            return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzd.zzbzd == null || zzd.zzbzd.isEmpty() : this.zzbzd.equals(zzd.zzbzd);
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((getClass().getName().hashCode() + 527) * 31) + this.level) * 31) + this.zznq) * 31) + this.zznr) * 31) + ((this.zzbzd == null || this.zzbzd.isEmpty()) ? 0 : this.zzbzd.hashCode());
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int zza = super.zza();
        int i = this.level;
        if (i != 1) {
            zza += zzacb.zzf(1, i);
        }
        int i2 = this.zznq;
        if (i2 != 0) {
            zza += zzacb.zzf(2, i2);
        }
        int i3 = this.zznr;
        return i3 != 0 ? zza + zzacb.zzf(3, i3) : zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        int i = this.level;
        if (i != 1) {
            zzacb.zze(1, i);
        }
        int i2 = this.zznq;
        if (i2 != 0) {
            zzacb.zze(2, i2);
        }
        int i3 = this.zznr;
        if (i3 != 0) {
            zzacb.zze(3, i3);
        }
        super.zza(zzacb);
    }
}
