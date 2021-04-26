package com.google.android.gms.internal.measurement;

public final class zzkn extends zzacd<zzkn> {
    public String zzafa = null;
    public Long zzaum = null;
    private Integer zzaun = null;
    public zzko[] zzauo = zzko.zzlv();
    public zzkm[] zzaup = zzkm.zzlu();
    public zzkg[] zzauq = zzkg.zzlq();

    public zzkn() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkn)) {
            return false;
        }
        zzkn zzkn = (zzkn) obj;
        Long l = this.zzaum;
        if (l == null) {
            if (zzkn.zzaum != null) {
                return false;
            }
        } else if (!l.equals(zzkn.zzaum)) {
            return false;
        }
        String str = this.zzafa;
        if (str == null) {
            if (zzkn.zzafa != null) {
                return false;
            }
        } else if (!str.equals(zzkn.zzafa)) {
            return false;
        }
        Integer num = this.zzaun;
        if (num == null) {
            if (zzkn.zzaun != null) {
                return false;
            }
        } else if (!num.equals(zzkn.zzaun)) {
            return false;
        }
        if (zzach.equals(this.zzauo, zzkn.zzauo) && zzach.equals(this.zzaup, zzkn.zzaup) && zzach.equals(this.zzauq, zzkn.zzauq)) {
            return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzkn.zzbzd == null || zzkn.zzbzd.isEmpty() : this.zzbzd.equals(zzkn.zzbzd);
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Long l = this.zzaum;
        int i = 0;
        int hashCode2 = (hashCode + (l == null ? 0 : l.hashCode())) * 31;
        String str = this.zzafa;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        Integer num = this.zzaun;
        int hashCode4 = (((((((hashCode3 + (num == null ? 0 : num.hashCode())) * 31) + zzach.hashCode(this.zzauo)) * 31) + zzach.hashCode(this.zzaup)) * 31) + zzach.hashCode(this.zzauq)) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode4 + i;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int zza = super.zza();
        Long l = this.zzaum;
        if (l != null) {
            zza += zzacb.zzc(1, l.longValue());
        }
        String str = this.zzafa;
        if (str != null) {
            zza += zzacb.zzc(2, str);
        }
        Integer num = this.zzaun;
        if (num != null) {
            zza += zzacb.zzf(3, num.intValue());
        }
        zzko[] zzkoArr = this.zzauo;
        int i = 0;
        if (zzkoArr != null && zzkoArr.length > 0) {
            int i2 = zza;
            int i3 = 0;
            while (true) {
                zzko[] zzkoArr2 = this.zzauo;
                if (i3 >= zzkoArr2.length) {
                    break;
                }
                zzko zzko = zzkoArr2[i3];
                if (zzko != null) {
                    i2 += zzacb.zzb(4, zzko);
                }
                i3++;
            }
            zza = i2;
        }
        zzkm[] zzkmArr = this.zzaup;
        if (zzkmArr != null && zzkmArr.length > 0) {
            int i4 = zza;
            int i5 = 0;
            while (true) {
                zzkm[] zzkmArr2 = this.zzaup;
                if (i5 >= zzkmArr2.length) {
                    break;
                }
                zzkm zzkm = zzkmArr2[i5];
                if (zzkm != null) {
                    i4 += zzacb.zzb(5, zzkm);
                }
                i5++;
            }
            zza = i4;
        }
        zzkg[] zzkgArr = this.zzauq;
        if (zzkgArr != null && zzkgArr.length > 0) {
            while (true) {
                zzkg[] zzkgArr2 = this.zzauq;
                if (i >= zzkgArr2.length) {
                    break;
                }
                zzkg zzkg = zzkgArr2[i];
                if (zzkg != null) {
                    zza += zzacb.zzb(6, zzkg);
                }
                i++;
            }
        }
        return zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        Long l = this.zzaum;
        if (l != null) {
            zzacb.zzb(1, l.longValue());
        }
        String str = this.zzafa;
        if (str != null) {
            zzacb.zzb(2, str);
        }
        Integer num = this.zzaun;
        if (num != null) {
            zzacb.zze(3, num.intValue());
        }
        zzko[] zzkoArr = this.zzauo;
        int i = 0;
        if (zzkoArr != null && zzkoArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzko[] zzkoArr2 = this.zzauo;
                if (i2 >= zzkoArr2.length) {
                    break;
                }
                zzko zzko = zzkoArr2[i2];
                if (zzko != null) {
                    zzacb.zza(4, zzko);
                }
                i2++;
            }
        }
        zzkm[] zzkmArr = this.zzaup;
        if (zzkmArr != null && zzkmArr.length > 0) {
            int i3 = 0;
            while (true) {
                zzkm[] zzkmArr2 = this.zzaup;
                if (i3 >= zzkmArr2.length) {
                    break;
                }
                zzkm zzkm = zzkmArr2[i3];
                if (zzkm != null) {
                    zzacb.zza(5, zzkm);
                }
                i3++;
            }
        }
        zzkg[] zzkgArr = this.zzauq;
        if (zzkgArr != null && zzkgArr.length > 0) {
            while (true) {
                zzkg[] zzkgArr2 = this.zzauq;
                if (i >= zzkgArr2.length) {
                    break;
                }
                zzkg zzkg = zzkgArr2[i];
                if (zzkg != null) {
                    zzacb.zza(6, zzkg);
                }
                i++;
            }
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
                this.zzaum = Long.valueOf(zzaca.zzvo());
            } else if (zzvl == 18) {
                this.zzafa = zzaca.readString();
            } else if (zzvl == 24) {
                this.zzaun = Integer.valueOf(zzaca.zzvn());
            } else if (zzvl == 34) {
                int zzb = zzacm.zzb(zzaca, 34);
                zzko[] zzkoArr = this.zzauo;
                int length = zzkoArr == null ? 0 : zzkoArr.length;
                zzko[] zzkoArr2 = new zzko[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzauo, 0, zzkoArr2, 0, length);
                }
                while (length < zzkoArr2.length - 1) {
                    zzkoArr2[length] = new zzko();
                    zzaca.zza(zzkoArr2[length]);
                    zzaca.zzvl();
                    length++;
                }
                zzkoArr2[length] = new zzko();
                zzaca.zza(zzkoArr2[length]);
                this.zzauo = zzkoArr2;
            } else if (zzvl == 42) {
                int zzb2 = zzacm.zzb(zzaca, 42);
                zzkm[] zzkmArr = this.zzaup;
                int length2 = zzkmArr == null ? 0 : zzkmArr.length;
                zzkm[] zzkmArr2 = new zzkm[(zzb2 + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.zzaup, 0, zzkmArr2, 0, length2);
                }
                while (length2 < zzkmArr2.length - 1) {
                    zzkmArr2[length2] = new zzkm();
                    zzaca.zza(zzkmArr2[length2]);
                    zzaca.zzvl();
                    length2++;
                }
                zzkmArr2[length2] = new zzkm();
                zzaca.zza(zzkmArr2[length2]);
                this.zzaup = zzkmArr2;
            } else if (zzvl == 50) {
                int zzb3 = zzacm.zzb(zzaca, 50);
                zzkg[] zzkgArr = this.zzauq;
                int length3 = zzkgArr == null ? 0 : zzkgArr.length;
                zzkg[] zzkgArr2 = new zzkg[(zzb3 + length3)];
                if (length3 != 0) {
                    System.arraycopy(this.zzauq, 0, zzkgArr2, 0, length3);
                }
                while (length3 < zzkgArr2.length - 1) {
                    zzkgArr2[length3] = new zzkg();
                    zzaca.zza(zzkgArr2[length3]);
                    zzaca.zzvl();
                    length3++;
                }
                zzkgArr2[length3] = new zzkg();
                zzaca.zza(zzkgArr2[length3]);
                this.zzauq = zzkgArr2;
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
