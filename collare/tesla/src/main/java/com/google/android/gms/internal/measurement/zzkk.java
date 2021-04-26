package com.google.android.gms.internal.measurement;

public final class zzkk extends zzacd<zzkk> {
    private static volatile zzkk[] zzaub;
    public Integer zzatk = null;
    public String zzauc = null;
    public zzki zzaud = null;

    public zzkk() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzkk[] zzlt() {
        if (zzaub == null) {
            synchronized (zzach.zzbzn) {
                if (zzaub == null) {
                    zzaub = new zzkk[0];
                }
            }
        }
        return zzaub;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkk)) {
            return false;
        }
        zzkk zzkk = (zzkk) obj;
        Integer num = this.zzatk;
        if (num == null) {
            if (zzkk.zzatk != null) {
                return false;
            }
        } else if (!num.equals(zzkk.zzatk)) {
            return false;
        }
        String str = this.zzauc;
        if (str == null) {
            if (zzkk.zzauc != null) {
                return false;
            }
        } else if (!str.equals(zzkk.zzauc)) {
            return false;
        }
        zzki zzki = this.zzaud;
        if (zzki == null) {
            if (zzkk.zzaud != null) {
                return false;
            }
        } else if (!zzki.equals(zzkk.zzaud)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzkk.zzbzd == null || zzkk.zzbzd.isEmpty() : this.zzbzd.equals(zzkk.zzbzd);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzatk;
        int i = 0;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str = this.zzauc;
        int hashCode3 = hashCode2 + (str == null ? 0 : str.hashCode());
        zzki zzki = this.zzaud;
        int hashCode4 = ((hashCode3 * 31) + (zzki == null ? 0 : zzki.hashCode())) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode4 + i;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int zza = super.zza();
        Integer num = this.zzatk;
        if (num != null) {
            zza += zzacb.zzf(1, num.intValue());
        }
        String str = this.zzauc;
        if (str != null) {
            zza += zzacb.zzc(2, str);
        }
        zzki zzki = this.zzaud;
        return zzki != null ? zza + zzacb.zzb(3, zzki) : zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        Integer num = this.zzatk;
        if (num != null) {
            zzacb.zze(1, num.intValue());
        }
        String str = this.zzauc;
        if (str != null) {
            zzacb.zzb(2, str);
        }
        zzki zzki = this.zzaud;
        if (zzki != null) {
            zzacb.zza(3, zzki);
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
                this.zzatk = Integer.valueOf(zzaca.zzvn());
            } else if (zzvl == 18) {
                this.zzauc = zzaca.readString();
            } else if (zzvl == 26) {
                if (this.zzaud == null) {
                    this.zzaud = new zzki();
                }
                zzaca.zza(this.zzaud);
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
