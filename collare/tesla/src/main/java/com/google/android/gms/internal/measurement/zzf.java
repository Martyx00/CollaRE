package com.google.android.gms.internal.measurement;

public final class zzf extends zzacd<zzf> {
    private static volatile zzf[] zznx;
    public String zzny = "";
    public long zznz = 0;
    public long zzoa = 2147483647L;
    public boolean zzob = false;
    public long zzoc = 0;

    public zzf() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzf[] zzc() {
        if (zznx == null) {
            synchronized (zzach.zzbzn) {
                if (zznx == null) {
                    zznx = new zzf[0];
                }
            }
        }
        return zznx;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzf)) {
            return false;
        }
        zzf zzf = (zzf) obj;
        String str = this.zzny;
        if (str == null) {
            if (zzf.zzny != null) {
                return false;
            }
        } else if (!str.equals(zzf.zzny)) {
            return false;
        }
        if (this.zznz == zzf.zznz && this.zzoa == zzf.zzoa && this.zzob == zzf.zzob && this.zzoc == zzf.zzoc) {
            return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzf.zzbzd == null || zzf.zzbzd.isEmpty() : this.zzbzd.equals(zzf.zzbzd);
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.zzny;
        int i = 0;
        int hashCode2 = str == null ? 0 : str.hashCode();
        long j = this.zznz;
        long j2 = this.zzoa;
        int i2 = (((((hashCode + hashCode2) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        int i3 = this.zzob ? 1231 : 1237;
        long j3 = this.zzoc;
        int i4 = (((i2 + i3) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return i4 + i;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int zza = super.zza();
        String str = this.zzny;
        if (str != null && !str.equals("")) {
            zza += zzacb.zzc(1, this.zzny);
        }
        long j = this.zznz;
        if (j != 0) {
            zza += zzacb.zzc(2, j);
        }
        long j2 = this.zzoa;
        if (j2 != 2147483647L) {
            zza += zzacb.zzc(3, j2);
        }
        if (this.zzob) {
            zza += zzacb.zzaq(4) + 1;
        }
        long j3 = this.zzoc;
        return j3 != 0 ? zza + zzacb.zzc(5, j3) : zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        String str = this.zzny;
        if (str != null && !str.equals("")) {
            zzacb.zzb(1, this.zzny);
        }
        long j = this.zznz;
        if (j != 0) {
            zzacb.zzb(2, j);
        }
        long j2 = this.zzoa;
        if (j2 != 2147483647L) {
            zzacb.zzb(3, j2);
        }
        boolean z = this.zzob;
        if (z) {
            zzacb.zza(4, z);
        }
        long j3 = this.zzoc;
        if (j3 != 0) {
            zzacb.zzb(5, j3);
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
            } else if (zzvl == 16) {
                this.zznz = zzaca.zzvo();
            } else if (zzvl == 24) {
                this.zzoa = zzaca.zzvo();
            } else if (zzvl == 32) {
                this.zzob = zzaca.zzvm();
            } else if (zzvl == 40) {
                this.zzoc = zzaca.zzvo();
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
