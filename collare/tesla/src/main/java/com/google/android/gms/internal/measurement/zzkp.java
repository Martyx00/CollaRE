package com.google.android.gms.internal.measurement;

public final class zzkp extends zzacd<zzkp> {
    private static volatile zzkp[] zzaus;
    public Integer zzate = null;
    public zzkv zzaut = null;
    public zzkv zzauu = null;
    public Boolean zzauv = null;

    public zzkp() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzkp[] zzlw() {
        if (zzaus == null) {
            synchronized (zzach.zzbzn) {
                if (zzaus == null) {
                    zzaus = new zzkp[0];
                }
            }
        }
        return zzaus;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkp)) {
            return false;
        }
        zzkp zzkp = (zzkp) obj;
        Integer num = this.zzate;
        if (num == null) {
            if (zzkp.zzate != null) {
                return false;
            }
        } else if (!num.equals(zzkp.zzate)) {
            return false;
        }
        zzkv zzkv = this.zzaut;
        if (zzkv == null) {
            if (zzkp.zzaut != null) {
                return false;
            }
        } else if (!zzkv.equals(zzkp.zzaut)) {
            return false;
        }
        zzkv zzkv2 = this.zzauu;
        if (zzkv2 == null) {
            if (zzkp.zzauu != null) {
                return false;
            }
        } else if (!zzkv2.equals(zzkp.zzauu)) {
            return false;
        }
        Boolean bool = this.zzauv;
        if (bool == null) {
            if (zzkp.zzauv != null) {
                return false;
            }
        } else if (!bool.equals(zzkp.zzauv)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzkp.zzbzd == null || zzkp.zzbzd.isEmpty() : this.zzbzd.equals(zzkp.zzbzd);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzate;
        int i = 0;
        int hashCode2 = hashCode + (num == null ? 0 : num.hashCode());
        zzkv zzkv = this.zzaut;
        int hashCode3 = (hashCode2 * 31) + (zzkv == null ? 0 : zzkv.hashCode());
        zzkv zzkv2 = this.zzauu;
        int hashCode4 = ((hashCode3 * 31) + (zzkv2 == null ? 0 : zzkv2.hashCode())) * 31;
        Boolean bool = this.zzauv;
        int hashCode5 = (hashCode4 + (bool == null ? 0 : bool.hashCode())) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode5 + i;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int zza = super.zza();
        Integer num = this.zzate;
        if (num != null) {
            zza += zzacb.zzf(1, num.intValue());
        }
        zzkv zzkv = this.zzaut;
        if (zzkv != null) {
            zza += zzacb.zzb(2, zzkv);
        }
        zzkv zzkv2 = this.zzauu;
        if (zzkv2 != null) {
            zza += zzacb.zzb(3, zzkv2);
        }
        Boolean bool = this.zzauv;
        if (bool == null) {
            return zza;
        }
        bool.booleanValue();
        return zza + zzacb.zzaq(4) + 1;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        Integer num = this.zzate;
        if (num != null) {
            zzacb.zze(1, num.intValue());
        }
        zzkv zzkv = this.zzaut;
        if (zzkv != null) {
            zzacb.zza(2, zzkv);
        }
        zzkv zzkv2 = this.zzauu;
        if (zzkv2 != null) {
            zzacb.zza(3, zzkv2);
        }
        Boolean bool = this.zzauv;
        if (bool != null) {
            zzacb.zza(4, bool.booleanValue());
        }
        super.zza(zzacb);
    }

    @Override // com.google.android.gms.internal.measurement.zzacj
    public final /* synthetic */ zzacj zzb(zzaca zzaca) {
        zzkv zzkv;
        while (true) {
            int zzvl = zzaca.zzvl();
            if (zzvl == 0) {
                return this;
            }
            if (zzvl != 8) {
                if (zzvl == 18) {
                    if (this.zzaut == null) {
                        this.zzaut = new zzkv();
                    }
                    zzkv = this.zzaut;
                } else if (zzvl == 26) {
                    if (this.zzauu == null) {
                        this.zzauu = new zzkv();
                    }
                    zzkv = this.zzauu;
                } else if (zzvl == 32) {
                    this.zzauv = Boolean.valueOf(zzaca.zzvm());
                } else if (!super.zza(zzaca, zzvl)) {
                    return this;
                }
                zzaca.zza(zzkv);
            } else {
                this.zzate = Integer.valueOf(zzaca.zzvn());
            }
        }
    }
}
