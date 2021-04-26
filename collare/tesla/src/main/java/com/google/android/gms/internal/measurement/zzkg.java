package com.google.android.gms.internal.measurement;

public final class zzkg extends zzacd<zzkg> {
    private static volatile zzkg[] zzatd;
    public Integer zzate = null;
    public zzkk[] zzatf = zzkk.zzlt();
    public zzkh[] zzatg = zzkh.zzlr();
    public Boolean zzath = null;
    public Boolean zzati = null;

    public zzkg() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzkg[] zzlq() {
        if (zzatd == null) {
            synchronized (zzach.zzbzn) {
                if (zzatd == null) {
                    zzatd = new zzkg[0];
                }
            }
        }
        return zzatd;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkg)) {
            return false;
        }
        zzkg zzkg = (zzkg) obj;
        Integer num = this.zzate;
        if (num == null) {
            if (zzkg.zzate != null) {
                return false;
            }
        } else if (!num.equals(zzkg.zzate)) {
            return false;
        }
        if (!zzach.equals(this.zzatf, zzkg.zzatf) || !zzach.equals(this.zzatg, zzkg.zzatg)) {
            return false;
        }
        Boolean bool = this.zzath;
        if (bool == null) {
            if (zzkg.zzath != null) {
                return false;
            }
        } else if (!bool.equals(zzkg.zzath)) {
            return false;
        }
        Boolean bool2 = this.zzati;
        if (bool2 == null) {
            if (zzkg.zzati != null) {
                return false;
            }
        } else if (!bool2.equals(zzkg.zzati)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzkg.zzbzd == null || zzkg.zzbzd.isEmpty() : this.zzbzd.equals(zzkg.zzbzd);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzate;
        int i = 0;
        int hashCode2 = (((((hashCode + (num == null ? 0 : num.hashCode())) * 31) + zzach.hashCode(this.zzatf)) * 31) + zzach.hashCode(this.zzatg)) * 31;
        Boolean bool = this.zzath;
        int hashCode3 = (hashCode2 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.zzati;
        int hashCode4 = (hashCode3 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode4 + i;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int zza = super.zza();
        Integer num = this.zzate;
        if (num != null) {
            zza += zzacb.zzf(1, num.intValue());
        }
        zzkk[] zzkkArr = this.zzatf;
        int i = 0;
        if (zzkkArr != null && zzkkArr.length > 0) {
            int i2 = zza;
            int i3 = 0;
            while (true) {
                zzkk[] zzkkArr2 = this.zzatf;
                if (i3 >= zzkkArr2.length) {
                    break;
                }
                zzkk zzkk = zzkkArr2[i3];
                if (zzkk != null) {
                    i2 += zzacb.zzb(2, zzkk);
                }
                i3++;
            }
            zza = i2;
        }
        zzkh[] zzkhArr = this.zzatg;
        if (zzkhArr != null && zzkhArr.length > 0) {
            while (true) {
                zzkh[] zzkhArr2 = this.zzatg;
                if (i >= zzkhArr2.length) {
                    break;
                }
                zzkh zzkh = zzkhArr2[i];
                if (zzkh != null) {
                    zza += zzacb.zzb(3, zzkh);
                }
                i++;
            }
        }
        Boolean bool = this.zzath;
        if (bool != null) {
            bool.booleanValue();
            zza += zzacb.zzaq(4) + 1;
        }
        Boolean bool2 = this.zzati;
        if (bool2 == null) {
            return zza;
        }
        bool2.booleanValue();
        return zza + zzacb.zzaq(5) + 1;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        Integer num = this.zzate;
        if (num != null) {
            zzacb.zze(1, num.intValue());
        }
        zzkk[] zzkkArr = this.zzatf;
        int i = 0;
        if (zzkkArr != null && zzkkArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzkk[] zzkkArr2 = this.zzatf;
                if (i2 >= zzkkArr2.length) {
                    break;
                }
                zzkk zzkk = zzkkArr2[i2];
                if (zzkk != null) {
                    zzacb.zza(2, zzkk);
                }
                i2++;
            }
        }
        zzkh[] zzkhArr = this.zzatg;
        if (zzkhArr != null && zzkhArr.length > 0) {
            while (true) {
                zzkh[] zzkhArr2 = this.zzatg;
                if (i >= zzkhArr2.length) {
                    break;
                }
                zzkh zzkh = zzkhArr2[i];
                if (zzkh != null) {
                    zzacb.zza(3, zzkh);
                }
                i++;
            }
        }
        Boolean bool = this.zzath;
        if (bool != null) {
            zzacb.zza(4, bool.booleanValue());
        }
        Boolean bool2 = this.zzati;
        if (bool2 != null) {
            zzacb.zza(5, bool2.booleanValue());
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
                this.zzate = Integer.valueOf(zzaca.zzvn());
            } else if (zzvl == 18) {
                int zzb = zzacm.zzb(zzaca, 18);
                zzkk[] zzkkArr = this.zzatf;
                int length = zzkkArr == null ? 0 : zzkkArr.length;
                zzkk[] zzkkArr2 = new zzkk[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzatf, 0, zzkkArr2, 0, length);
                }
                while (length < zzkkArr2.length - 1) {
                    zzkkArr2[length] = new zzkk();
                    zzaca.zza(zzkkArr2[length]);
                    zzaca.zzvl();
                    length++;
                }
                zzkkArr2[length] = new zzkk();
                zzaca.zza(zzkkArr2[length]);
                this.zzatf = zzkkArr2;
            } else if (zzvl == 26) {
                int zzb2 = zzacm.zzb(zzaca, 26);
                zzkh[] zzkhArr = this.zzatg;
                int length2 = zzkhArr == null ? 0 : zzkhArr.length;
                zzkh[] zzkhArr2 = new zzkh[(zzb2 + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.zzatg, 0, zzkhArr2, 0, length2);
                }
                while (length2 < zzkhArr2.length - 1) {
                    zzkhArr2[length2] = new zzkh();
                    zzaca.zza(zzkhArr2[length2]);
                    zzaca.zzvl();
                    length2++;
                }
                zzkhArr2[length2] = new zzkh();
                zzaca.zza(zzkhArr2[length2]);
                this.zzatg = zzkhArr2;
            } else if (zzvl == 32) {
                this.zzath = Boolean.valueOf(zzaca.zzvm());
            } else if (zzvl == 40) {
                this.zzati = Boolean.valueOf(zzaca.zzvm());
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
