package com.google.android.gms.internal.measurement;

public final class zzks extends zzacd<zzks> {
    private static volatile zzks[] zzavd;
    public String name = null;
    public String zzale = null;
    private Float zzasv = null;
    public Double zzasw = null;
    public Long zzave = null;

    public zzks() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzks[] zzlz() {
        if (zzavd == null) {
            synchronized (zzach.zzbzn) {
                if (zzavd == null) {
                    zzavd = new zzks[0];
                }
            }
        }
        return zzavd;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzks)) {
            return false;
        }
        zzks zzks = (zzks) obj;
        String str = this.name;
        if (str == null) {
            if (zzks.name != null) {
                return false;
            }
        } else if (!str.equals(zzks.name)) {
            return false;
        }
        String str2 = this.zzale;
        if (str2 == null) {
            if (zzks.zzale != null) {
                return false;
            }
        } else if (!str2.equals(zzks.zzale)) {
            return false;
        }
        Long l = this.zzave;
        if (l == null) {
            if (zzks.zzave != null) {
                return false;
            }
        } else if (!l.equals(zzks.zzave)) {
            return false;
        }
        Float f = this.zzasv;
        if (f == null) {
            if (zzks.zzasv != null) {
                return false;
            }
        } else if (!f.equals(zzks.zzasv)) {
            return false;
        }
        Double d2 = this.zzasw;
        if (d2 == null) {
            if (zzks.zzasw != null) {
                return false;
            }
        } else if (!d2.equals(zzks.zzasw)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzks.zzbzd == null || zzks.zzbzd.isEmpty() : this.zzbzd.equals(zzks.zzbzd);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.name;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.zzale;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Long l = this.zzave;
        int hashCode4 = (hashCode3 + (l == null ? 0 : l.hashCode())) * 31;
        Float f = this.zzasv;
        int hashCode5 = (hashCode4 + (f == null ? 0 : f.hashCode())) * 31;
        Double d2 = this.zzasw;
        int hashCode6 = (hashCode5 + (d2 == null ? 0 : d2.hashCode())) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode6 + i;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int zza = super.zza();
        String str = this.name;
        if (str != null) {
            zza += zzacb.zzc(1, str);
        }
        String str2 = this.zzale;
        if (str2 != null) {
            zza += zzacb.zzc(2, str2);
        }
        Long l = this.zzave;
        if (l != null) {
            zza += zzacb.zzc(3, l.longValue());
        }
        Float f = this.zzasv;
        if (f != null) {
            f.floatValue();
            zza += zzacb.zzaq(4) + 4;
        }
        Double d2 = this.zzasw;
        if (d2 == null) {
            return zza;
        }
        d2.doubleValue();
        return zza + zzacb.zzaq(5) + 8;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        String str = this.name;
        if (str != null) {
            zzacb.zzb(1, str);
        }
        String str2 = this.zzale;
        if (str2 != null) {
            zzacb.zzb(2, str2);
        }
        Long l = this.zzave;
        if (l != null) {
            zzacb.zzb(3, l.longValue());
        }
        Float f = this.zzasv;
        if (f != null) {
            zzacb.zza(4, f.floatValue());
        }
        Double d2 = this.zzasw;
        if (d2 != null) {
            zzacb.zza(5, d2.doubleValue());
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
            } else if (zzvl == 18) {
                this.zzale = zzaca.readString();
            } else if (zzvl == 24) {
                this.zzave = Long.valueOf(zzaca.zzvo());
            } else if (zzvl == 37) {
                this.zzasv = Float.valueOf(Float.intBitsToFloat(zzaca.zzvp()));
            } else if (zzvl == 41) {
                this.zzasw = Double.valueOf(Double.longBitsToDouble(zzaca.zzvq()));
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
