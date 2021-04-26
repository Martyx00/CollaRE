package com.google.android.gms.internal.measurement;

public final class zzkm extends zzacd<zzkm> {
    private static volatile zzkm[] zzaui;
    public String name = null;
    public Boolean zzauj = null;
    public Boolean zzauk = null;
    public Integer zzaul = null;

    public zzkm() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzkm[] zzlu() {
        if (zzaui == null) {
            synchronized (zzach.zzbzn) {
                if (zzaui == null) {
                    zzaui = new zzkm[0];
                }
            }
        }
        return zzaui;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkm)) {
            return false;
        }
        zzkm zzkm = (zzkm) obj;
        String str = this.name;
        if (str == null) {
            if (zzkm.name != null) {
                return false;
            }
        } else if (!str.equals(zzkm.name)) {
            return false;
        }
        Boolean bool = this.zzauj;
        if (bool == null) {
            if (zzkm.zzauj != null) {
                return false;
            }
        } else if (!bool.equals(zzkm.zzauj)) {
            return false;
        }
        Boolean bool2 = this.zzauk;
        if (bool2 == null) {
            if (zzkm.zzauk != null) {
                return false;
            }
        } else if (!bool2.equals(zzkm.zzauk)) {
            return false;
        }
        Integer num = this.zzaul;
        if (num == null) {
            if (zzkm.zzaul != null) {
                return false;
            }
        } else if (!num.equals(zzkm.zzaul)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzkm.zzbzd == null || zzkm.zzbzd.isEmpty() : this.zzbzd.equals(zzkm.zzbzd);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.name;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        Boolean bool = this.zzauj;
        int hashCode3 = (hashCode2 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.zzauk;
        int hashCode4 = (hashCode3 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Integer num = this.zzaul;
        int hashCode5 = (hashCode4 + (num == null ? 0 : num.hashCode())) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode5 + i;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int zza = super.zza();
        String str = this.name;
        if (str != null) {
            zza += zzacb.zzc(1, str);
        }
        Boolean bool = this.zzauj;
        if (bool != null) {
            bool.booleanValue();
            zza += zzacb.zzaq(2) + 1;
        }
        Boolean bool2 = this.zzauk;
        if (bool2 != null) {
            bool2.booleanValue();
            zza += zzacb.zzaq(3) + 1;
        }
        Integer num = this.zzaul;
        return num != null ? zza + zzacb.zzf(4, num.intValue()) : zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        String str = this.name;
        if (str != null) {
            zzacb.zzb(1, str);
        }
        Boolean bool = this.zzauj;
        if (bool != null) {
            zzacb.zza(2, bool.booleanValue());
        }
        Boolean bool2 = this.zzauk;
        if (bool2 != null) {
            zzacb.zza(3, bool2.booleanValue());
        }
        Integer num = this.zzaul;
        if (num != null) {
            zzacb.zze(4, num.intValue());
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
                this.name = zzaca.readString();
            } else if (zzvl == 16) {
                this.zzauj = Boolean.valueOf(zzaca.zzvm());
            } else if (zzvl == 24) {
                this.zzauk = Boolean.valueOf(zzaca.zzvm());
            } else if (zzvl == 32) {
                this.zzaul = Integer.valueOf(zzaca.zzvn());
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
