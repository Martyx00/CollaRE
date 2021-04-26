package com.google.android.gms.internal.measurement;

public final class zzkx extends zzacd<zzkx> {
    private static volatile zzkx[] zzawr;
    public String name = null;
    public String zzale = null;
    private Float zzasv = null;
    public Double zzasw = null;
    public Long zzave = null;
    public Long zzaws = null;

    public zzkx() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzkx[] zzmc() {
        if (zzawr == null) {
            synchronized (zzach.zzbzn) {
                if (zzawr == null) {
                    zzawr = new zzkx[0];
                }
            }
        }
        return zzawr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkx)) {
            return false;
        }
        zzkx zzkx = (zzkx) obj;
        Long l = this.zzaws;
        if (l == null) {
            if (zzkx.zzaws != null) {
                return false;
            }
        } else if (!l.equals(zzkx.zzaws)) {
            return false;
        }
        String str = this.name;
        if (str == null) {
            if (zzkx.name != null) {
                return false;
            }
        } else if (!str.equals(zzkx.name)) {
            return false;
        }
        String str2 = this.zzale;
        if (str2 == null) {
            if (zzkx.zzale != null) {
                return false;
            }
        } else if (!str2.equals(zzkx.zzale)) {
            return false;
        }
        Long l2 = this.zzave;
        if (l2 == null) {
            if (zzkx.zzave != null) {
                return false;
            }
        } else if (!l2.equals(zzkx.zzave)) {
            return false;
        }
        Float f = this.zzasv;
        if (f == null) {
            if (zzkx.zzasv != null) {
                return false;
            }
        } else if (!f.equals(zzkx.zzasv)) {
            return false;
        }
        Double d2 = this.zzasw;
        if (d2 == null) {
            if (zzkx.zzasw != null) {
                return false;
            }
        } else if (!d2.equals(zzkx.zzasw)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzkx.zzbzd == null || zzkx.zzbzd.isEmpty() : this.zzbzd.equals(zzkx.zzbzd);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Long l = this.zzaws;
        int i = 0;
        int hashCode2 = (hashCode + (l == null ? 0 : l.hashCode())) * 31;
        String str = this.name;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.zzale;
        int hashCode4 = (hashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Long l2 = this.zzave;
        int hashCode5 = (hashCode4 + (l2 == null ? 0 : l2.hashCode())) * 31;
        Float f = this.zzasv;
        int hashCode6 = (hashCode5 + (f == null ? 0 : f.hashCode())) * 31;
        Double d2 = this.zzasw;
        int hashCode7 = (hashCode6 + (d2 == null ? 0 : d2.hashCode())) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode7 + i;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int zza = super.zza();
        Long l = this.zzaws;
        if (l != null) {
            zza += zzacb.zzc(1, l.longValue());
        }
        String str = this.name;
        if (str != null) {
            zza += zzacb.zzc(2, str);
        }
        String str2 = this.zzale;
        if (str2 != null) {
            zza += zzacb.zzc(3, str2);
        }
        Long l2 = this.zzave;
        if (l2 != null) {
            zza += zzacb.zzc(4, l2.longValue());
        }
        Float f = this.zzasv;
        if (f != null) {
            f.floatValue();
            zza += zzacb.zzaq(5) + 4;
        }
        Double d2 = this.zzasw;
        if (d2 == null) {
            return zza;
        }
        d2.doubleValue();
        return zza + zzacb.zzaq(6) + 8;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        Long l = this.zzaws;
        if (l != null) {
            zzacb.zzb(1, l.longValue());
        }
        String str = this.name;
        if (str != null) {
            zzacb.zzb(2, str);
        }
        String str2 = this.zzale;
        if (str2 != null) {
            zzacb.zzb(3, str2);
        }
        Long l2 = this.zzave;
        if (l2 != null) {
            zzacb.zzb(4, l2.longValue());
        }
        Float f = this.zzasv;
        if (f != null) {
            zzacb.zza(5, f.floatValue());
        }
        Double d2 = this.zzasw;
        if (d2 != null) {
            zzacb.zza(6, d2.doubleValue());
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
                this.zzaws = Long.valueOf(zzaca.zzvo());
            } else if (zzvl == 18) {
                this.name = zzaca.readString();
            } else if (zzvl == 26) {
                this.zzale = zzaca.readString();
            } else if (zzvl == 32) {
                this.zzave = Long.valueOf(zzaca.zzvo());
            } else if (zzvl == 45) {
                this.zzasv = Float.valueOf(Float.intBitsToFloat(zzaca.zzvp()));
            } else if (zzvl == 49) {
                this.zzasw = Double.valueOf(Double.longBitsToDouble(zzaca.zzvq()));
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
