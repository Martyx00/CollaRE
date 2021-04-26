package com.google.android.gms.internal.measurement;

public final class zzkh extends zzacd<zzkh> {
    private static volatile zzkh[] zzatj;
    public Integer zzatk = null;
    public String zzatl = null;
    public zzki[] zzatm = zzki.zzls();
    private Boolean zzatn = null;
    public zzkj zzato = null;

    public zzkh() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzkh[] zzlr() {
        if (zzatj == null) {
            synchronized (zzach.zzbzn) {
                if (zzatj == null) {
                    zzatj = new zzkh[0];
                }
            }
        }
        return zzatj;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkh)) {
            return false;
        }
        zzkh zzkh = (zzkh) obj;
        Integer num = this.zzatk;
        if (num == null) {
            if (zzkh.zzatk != null) {
                return false;
            }
        } else if (!num.equals(zzkh.zzatk)) {
            return false;
        }
        String str = this.zzatl;
        if (str == null) {
            if (zzkh.zzatl != null) {
                return false;
            }
        } else if (!str.equals(zzkh.zzatl)) {
            return false;
        }
        if (!zzach.equals(this.zzatm, zzkh.zzatm)) {
            return false;
        }
        Boolean bool = this.zzatn;
        if (bool == null) {
            if (zzkh.zzatn != null) {
                return false;
            }
        } else if (!bool.equals(zzkh.zzatn)) {
            return false;
        }
        zzkj zzkj = this.zzato;
        if (zzkj == null) {
            if (zzkh.zzato != null) {
                return false;
            }
        } else if (!zzkj.equals(zzkh.zzato)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzkh.zzbzd == null || zzkh.zzbzd.isEmpty() : this.zzbzd.equals(zzkh.zzbzd);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzatk;
        int i = 0;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str = this.zzatl;
        int hashCode3 = (((hashCode2 + (str == null ? 0 : str.hashCode())) * 31) + zzach.hashCode(this.zzatm)) * 31;
        Boolean bool = this.zzatn;
        int hashCode4 = hashCode3 + (bool == null ? 0 : bool.hashCode());
        zzkj zzkj = this.zzato;
        int hashCode5 = ((hashCode4 * 31) + (zzkj == null ? 0 : zzkj.hashCode())) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode5 + i;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int zza = super.zza();
        Integer num = this.zzatk;
        if (num != null) {
            zza += zzacb.zzf(1, num.intValue());
        }
        String str = this.zzatl;
        if (str != null) {
            zza += zzacb.zzc(2, str);
        }
        zzki[] zzkiArr = this.zzatm;
        if (zzkiArr != null && zzkiArr.length > 0) {
            int i = 0;
            while (true) {
                zzki[] zzkiArr2 = this.zzatm;
                if (i >= zzkiArr2.length) {
                    break;
                }
                zzki zzki = zzkiArr2[i];
                if (zzki != null) {
                    zza += zzacb.zzb(3, zzki);
                }
                i++;
            }
        }
        Boolean bool = this.zzatn;
        if (bool != null) {
            bool.booleanValue();
            zza += zzacb.zzaq(4) + 1;
        }
        zzkj zzkj = this.zzato;
        return zzkj != null ? zza + zzacb.zzb(5, zzkj) : zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        Integer num = this.zzatk;
        if (num != null) {
            zzacb.zze(1, num.intValue());
        }
        String str = this.zzatl;
        if (str != null) {
            zzacb.zzb(2, str);
        }
        zzki[] zzkiArr = this.zzatm;
        if (zzkiArr != null && zzkiArr.length > 0) {
            int i = 0;
            while (true) {
                zzki[] zzkiArr2 = this.zzatm;
                if (i >= zzkiArr2.length) {
                    break;
                }
                zzki zzki = zzkiArr2[i];
                if (zzki != null) {
                    zzacb.zza(3, zzki);
                }
                i++;
            }
        }
        Boolean bool = this.zzatn;
        if (bool != null) {
            zzacb.zza(4, bool.booleanValue());
        }
        zzkj zzkj = this.zzato;
        if (zzkj != null) {
            zzacb.zza(5, zzkj);
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
                this.zzatl = zzaca.readString();
            } else if (zzvl == 26) {
                int zzb = zzacm.zzb(zzaca, 26);
                zzki[] zzkiArr = this.zzatm;
                int length = zzkiArr == null ? 0 : zzkiArr.length;
                zzki[] zzkiArr2 = new zzki[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzatm, 0, zzkiArr2, 0, length);
                }
                while (length < zzkiArr2.length - 1) {
                    zzkiArr2[length] = new zzki();
                    zzaca.zza(zzkiArr2[length]);
                    zzaca.zzvl();
                    length++;
                }
                zzkiArr2[length] = new zzki();
                zzaca.zza(zzkiArr2[length]);
                this.zzatm = zzkiArr2;
            } else if (zzvl == 32) {
                this.zzatn = Boolean.valueOf(zzaca.zzvm());
            } else if (zzvl == 42) {
                if (this.zzato == null) {
                    this.zzato = new zzkj();
                }
                zzaca.zza(this.zzato);
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
