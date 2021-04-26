package com.google.android.gms.internal.measurement;

public final class zzki extends zzacd<zzki> {
    private static volatile zzki[] zzatp;
    public zzkl zzatq = null;
    public zzkj zzatr = null;
    public Boolean zzats = null;
    public String zzatt = null;
    public Boolean zzatu = null;
    public Boolean zzatv = null;

    public zzki() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzki[] zzls() {
        if (zzatp == null) {
            synchronized (zzach.zzbzn) {
                if (zzatp == null) {
                    zzatp = new zzki[0];
                }
            }
        }
        return zzatp;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzki)) {
            return false;
        }
        zzki zzki = (zzki) obj;
        zzkl zzkl = this.zzatq;
        if (zzkl == null) {
            if (zzki.zzatq != null) {
                return false;
            }
        } else if (!zzkl.equals(zzki.zzatq)) {
            return false;
        }
        zzkj zzkj = this.zzatr;
        if (zzkj == null) {
            if (zzki.zzatr != null) {
                return false;
            }
        } else if (!zzkj.equals(zzki.zzatr)) {
            return false;
        }
        Boolean bool = this.zzats;
        if (bool == null) {
            if (zzki.zzats != null) {
                return false;
            }
        } else if (!bool.equals(zzki.zzats)) {
            return false;
        }
        String str = this.zzatt;
        if (str == null) {
            if (zzki.zzatt != null) {
                return false;
            }
        } else if (!str.equals(zzki.zzatt)) {
            return false;
        }
        Boolean bool2 = this.zzatu;
        if (bool2 == null) {
            if (zzki.zzatu != null) {
                return false;
            }
        } else if (!bool2.equals(zzki.zzatu)) {
            return false;
        }
        Boolean bool3 = this.zzatv;
        if (bool3 == null) {
            if (zzki.zzatv != null) {
                return false;
            }
        } else if (!bool3.equals(zzki.zzatv)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzki.zzbzd == null || zzki.zzbzd.isEmpty() : this.zzbzd.equals(zzki.zzbzd);
    }

    public final int hashCode() {
        zzkl zzkl = this.zzatq;
        int i = 0;
        int hashCode = ((getClass().getName().hashCode() + 527) * 31) + (zzkl == null ? 0 : zzkl.hashCode());
        zzkj zzkj = this.zzatr;
        int hashCode2 = ((hashCode * 31) + (zzkj == null ? 0 : zzkj.hashCode())) * 31;
        Boolean bool = this.zzats;
        int hashCode3 = (hashCode2 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str = this.zzatt;
        int hashCode4 = (hashCode3 + (str == null ? 0 : str.hashCode())) * 31;
        Boolean bool2 = this.zzatu;
        int hashCode5 = (hashCode4 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Boolean bool3 = this.zzatv;
        int hashCode6 = (hashCode5 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode6 + i;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int zza = super.zza();
        zzkl zzkl = this.zzatq;
        if (zzkl != null) {
            zza += zzacb.zzb(1, zzkl);
        }
        zzkj zzkj = this.zzatr;
        if (zzkj != null) {
            zza += zzacb.zzb(2, zzkj);
        }
        Boolean bool = this.zzats;
        if (bool != null) {
            bool.booleanValue();
            zza += zzacb.zzaq(3) + 1;
        }
        String str = this.zzatt;
        if (str != null) {
            zza += zzacb.zzc(4, str);
        }
        Boolean bool2 = this.zzatu;
        if (bool2 != null) {
            bool2.booleanValue();
            zza += zzacb.zzaq(5) + 1;
        }
        Boolean bool3 = this.zzatv;
        if (bool3 == null) {
            return zza;
        }
        bool3.booleanValue();
        return zza + zzacb.zzaq(6) + 1;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        zzkl zzkl = this.zzatq;
        if (zzkl != null) {
            zzacb.zza(1, zzkl);
        }
        zzkj zzkj = this.zzatr;
        if (zzkj != null) {
            zzacb.zza(2, zzkj);
        }
        Boolean bool = this.zzats;
        if (bool != null) {
            zzacb.zza(3, bool.booleanValue());
        }
        String str = this.zzatt;
        if (str != null) {
            zzacb.zzb(4, str);
        }
        Boolean bool2 = this.zzatu;
        if (bool2 != null) {
            zzacb.zza(5, bool2.booleanValue());
        }
        Boolean bool3 = this.zzatv;
        if (bool3 != null) {
            zzacb.zza(6, bool3.booleanValue());
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
            if (zzvl == 10) {
                if (this.zzatq == null) {
                    this.zzatq = new zzkl();
                }
                zzacj = this.zzatq;
            } else if (zzvl == 18) {
                if (this.zzatr == null) {
                    this.zzatr = new zzkj();
                }
                zzacj = this.zzatr;
            } else if (zzvl == 24) {
                this.zzats = Boolean.valueOf(zzaca.zzvm());
            } else if (zzvl == 34) {
                this.zzatt = zzaca.readString();
            } else if (zzvl == 40) {
                this.zzatu = Boolean.valueOf(zzaca.zzvm());
            } else if (zzvl == 48) {
                this.zzatv = Boolean.valueOf(zzaca.zzvm());
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
            zzaca.zza(zzacj);
        }
    }
}
