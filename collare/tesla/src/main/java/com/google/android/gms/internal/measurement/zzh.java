package com.google.android.gms.internal.measurement;

public final class zzh extends zzacd<zzh> {
    private static volatile zzh[] zzog;
    public int key = 0;
    public int value = 0;

    public zzh() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzh[] zzd() {
        if (zzog == null) {
            synchronized (zzach.zzbzn) {
                if (zzog == null) {
                    zzog = new zzh[0];
                }
            }
        }
        return zzog;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzh)) {
            return false;
        }
        zzh zzh = (zzh) obj;
        if (this.key == zzh.key && this.value == zzh.value) {
            return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzh.zzbzd == null || zzh.zzbzd.isEmpty() : this.zzbzd.equals(zzh.zzbzd);
        }
        return false;
    }

    public final int hashCode() {
        return ((((((getClass().getName().hashCode() + 527) * 31) + this.key) * 31) + this.value) * 31) + ((this.zzbzd == null || this.zzbzd.isEmpty()) ? 0 : this.zzbzd.hashCode());
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        return super.zza() + zzacb.zzf(1, this.key) + zzacb.zzf(2, this.value);
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        zzacb.zze(1, this.key);
        zzacb.zze(2, this.value);
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
                this.key = zzaca.zzvn();
            } else if (zzvl == 16) {
                this.value = zzaca.zzvn();
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
