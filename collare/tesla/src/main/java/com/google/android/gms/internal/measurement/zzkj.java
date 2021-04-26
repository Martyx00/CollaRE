package com.google.android.gms.internal.measurement;

public final class zzkj extends zzacd<zzkj> {
    public Integer zzatw = null;
    public Boolean zzatx = null;
    public String zzaty = null;
    public String zzatz = null;
    public String zzaua = null;

    public zzkj() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzkj zzb(zzaca zzaca) {
        int zzvn;
        while (true) {
            int zzvl = zzaca.zzvl();
            if (zzvl == 0) {
                return this;
            }
            if (zzvl == 8) {
                try {
                    zzvn = zzaca.zzvn();
                    if (zzvn < 0 || zzvn > 4) {
                        StringBuilder sb = new StringBuilder(46);
                        sb.append(zzvn);
                        sb.append(" is not a valid enum ComparisonType");
                    } else {
                        this.zzatw = Integer.valueOf(zzvn);
                    }
                } catch (IllegalArgumentException unused) {
                    zzaca.zzam(zzaca.getPosition());
                    zza(zzaca, zzvl);
                }
            } else if (zzvl == 16) {
                this.zzatx = Boolean.valueOf(zzaca.zzvm());
            } else if (zzvl == 26) {
                this.zzaty = zzaca.readString();
            } else if (zzvl == 34) {
                this.zzatz = zzaca.readString();
            } else if (zzvl == 42) {
                this.zzaua = zzaca.readString();
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
        StringBuilder sb2 = new StringBuilder(46);
        sb2.append(zzvn);
        sb2.append(" is not a valid enum ComparisonType");
        throw new IllegalArgumentException(sb2.toString());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkj)) {
            return false;
        }
        zzkj zzkj = (zzkj) obj;
        Integer num = this.zzatw;
        if (num == null) {
            if (zzkj.zzatw != null) {
                return false;
            }
        } else if (!num.equals(zzkj.zzatw)) {
            return false;
        }
        Boolean bool = this.zzatx;
        if (bool == null) {
            if (zzkj.zzatx != null) {
                return false;
            }
        } else if (!bool.equals(zzkj.zzatx)) {
            return false;
        }
        String str = this.zzaty;
        if (str == null) {
            if (zzkj.zzaty != null) {
                return false;
            }
        } else if (!str.equals(zzkj.zzaty)) {
            return false;
        }
        String str2 = this.zzatz;
        if (str2 == null) {
            if (zzkj.zzatz != null) {
                return false;
            }
        } else if (!str2.equals(zzkj.zzatz)) {
            return false;
        }
        String str3 = this.zzaua;
        if (str3 == null) {
            if (zzkj.zzaua != null) {
                return false;
            }
        } else if (!str3.equals(zzkj.zzaua)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzkj.zzbzd == null || zzkj.zzbzd.isEmpty() : this.zzbzd.equals(zzkj.zzbzd);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzatw;
        int i = 0;
        int intValue = (hashCode + (num == null ? 0 : num.intValue())) * 31;
        Boolean bool = this.zzatx;
        int hashCode2 = (intValue + (bool == null ? 0 : bool.hashCode())) * 31;
        String str = this.zzaty;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.zzatz;
        int hashCode4 = (hashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.zzaua;
        int hashCode5 = (hashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode5 + i;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int zza = super.zza();
        Integer num = this.zzatw;
        if (num != null) {
            zza += zzacb.zzf(1, num.intValue());
        }
        Boolean bool = this.zzatx;
        if (bool != null) {
            bool.booleanValue();
            zza += zzacb.zzaq(2) + 1;
        }
        String str = this.zzaty;
        if (str != null) {
            zza += zzacb.zzc(3, str);
        }
        String str2 = this.zzatz;
        if (str2 != null) {
            zza += zzacb.zzc(4, str2);
        }
        String str3 = this.zzaua;
        return str3 != null ? zza + zzacb.zzc(5, str3) : zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        Integer num = this.zzatw;
        if (num != null) {
            zzacb.zze(1, num.intValue());
        }
        Boolean bool = this.zzatx;
        if (bool != null) {
            zzacb.zza(2, bool.booleanValue());
        }
        String str = this.zzaty;
        if (str != null) {
            zzacb.zzb(3, str);
        }
        String str2 = this.zzatz;
        if (str2 != null) {
            zzacb.zzb(4, str2);
        }
        String str3 = this.zzaua;
        if (str3 != null) {
            zzacb.zzb(5, str3);
        }
        super.zza(zzacb);
    }
}
