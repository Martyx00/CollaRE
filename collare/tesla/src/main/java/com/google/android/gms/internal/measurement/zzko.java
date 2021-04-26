package com.google.android.gms.internal.measurement;

public final class zzko extends zzacd<zzko> {
    private static volatile zzko[] zzaur;
    public String value = null;
    public String zzny = null;

    public zzko() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzko[] zzlv() {
        if (zzaur == null) {
            synchronized (zzach.zzbzn) {
                if (zzaur == null) {
                    zzaur = new zzko[0];
                }
            }
        }
        return zzaur;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzko)) {
            return false;
        }
        zzko zzko = (zzko) obj;
        String str = this.zzny;
        if (str == null) {
            if (zzko.zzny != null) {
                return false;
            }
        } else if (!str.equals(zzko.zzny)) {
            return false;
        }
        String str2 = this.value;
        if (str2 == null) {
            if (zzko.value != null) {
                return false;
            }
        } else if (!str2.equals(zzko.value)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzko.zzbzd == null || zzko.zzbzd.isEmpty() : this.zzbzd.equals(zzko.zzbzd);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.zzny;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.value;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode3 + i;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int zza = super.zza();
        String str = this.zzny;
        if (str != null) {
            zza += zzacb.zzc(1, str);
        }
        String str2 = this.value;
        return str2 != null ? zza + zzacb.zzc(2, str2) : zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        String str = this.zzny;
        if (str != null) {
            zzacb.zzb(1, str);
        }
        String str2 = this.value;
        if (str2 != null) {
            zzacb.zzb(2, str2);
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
            if (zzvl == 10) {
                this.zzny = zzaca.readString();
            } else if (zzvl == 18) {
                this.value = zzaca.readString();
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
