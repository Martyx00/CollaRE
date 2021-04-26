package com.google.android.gms.internal.measurement;

public final class zzkr extends zzacd<zzkr> {
    private static volatile zzkr[] zzauz;
    public Integer count = null;
    public String name = null;
    public zzks[] zzava = zzks.zzlz();
    public Long zzavb = null;
    public Long zzavc = null;

    public zzkr() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzkr[] zzly() {
        if (zzauz == null) {
            synchronized (zzach.zzbzn) {
                if (zzauz == null) {
                    zzauz = new zzkr[0];
                }
            }
        }
        return zzauz;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkr)) {
            return false;
        }
        zzkr zzkr = (zzkr) obj;
        if (!zzach.equals(this.zzava, zzkr.zzava)) {
            return false;
        }
        String str = this.name;
        if (str == null) {
            if (zzkr.name != null) {
                return false;
            }
        } else if (!str.equals(zzkr.name)) {
            return false;
        }
        Long l = this.zzavb;
        if (l == null) {
            if (zzkr.zzavb != null) {
                return false;
            }
        } else if (!l.equals(zzkr.zzavb)) {
            return false;
        }
        Long l2 = this.zzavc;
        if (l2 == null) {
            if (zzkr.zzavc != null) {
                return false;
            }
        } else if (!l2.equals(zzkr.zzavc)) {
            return false;
        }
        Integer num = this.count;
        if (num == null) {
            if (zzkr.count != null) {
                return false;
            }
        } else if (!num.equals(zzkr.count)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzkr.zzbzd == null || zzkr.zzbzd.isEmpty() : this.zzbzd.equals(zzkr.zzbzd);
    }

    public final int hashCode() {
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + zzach.hashCode(this.zzava)) * 31;
        String str = this.name;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        Long l = this.zzavb;
        int hashCode3 = (hashCode2 + (l == null ? 0 : l.hashCode())) * 31;
        Long l2 = this.zzavc;
        int hashCode4 = (hashCode3 + (l2 == null ? 0 : l2.hashCode())) * 31;
        Integer num = this.count;
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
        zzks[] zzksArr = this.zzava;
        if (zzksArr != null && zzksArr.length > 0) {
            int i = 0;
            while (true) {
                zzks[] zzksArr2 = this.zzava;
                if (i >= zzksArr2.length) {
                    break;
                }
                zzks zzks = zzksArr2[i];
                if (zzks != null) {
                    zza += zzacb.zzb(1, zzks);
                }
                i++;
            }
        }
        String str = this.name;
        if (str != null) {
            zza += zzacb.zzc(2, str);
        }
        Long l = this.zzavb;
        if (l != null) {
            zza += zzacb.zzc(3, l.longValue());
        }
        Long l2 = this.zzavc;
        if (l2 != null) {
            zza += zzacb.zzc(4, l2.longValue());
        }
        Integer num = this.count;
        return num != null ? zza + zzacb.zzf(5, num.intValue()) : zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        zzks[] zzksArr = this.zzava;
        if (zzksArr != null && zzksArr.length > 0) {
            int i = 0;
            while (true) {
                zzks[] zzksArr2 = this.zzava;
                if (i >= zzksArr2.length) {
                    break;
                }
                zzks zzks = zzksArr2[i];
                if (zzks != null) {
                    zzacb.zza(1, zzks);
                }
                i++;
            }
        }
        String str = this.name;
        if (str != null) {
            zzacb.zzb(2, str);
        }
        Long l = this.zzavb;
        if (l != null) {
            zzacb.zzb(3, l.longValue());
        }
        Long l2 = this.zzavc;
        if (l2 != null) {
            zzacb.zzb(4, l2.longValue());
        }
        Integer num = this.count;
        if (num != null) {
            zzacb.zze(5, num.intValue());
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
                int zzb = zzacm.zzb(zzaca, 10);
                zzks[] zzksArr = this.zzava;
                int length = zzksArr == null ? 0 : zzksArr.length;
                zzks[] zzksArr2 = new zzks[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzava, 0, zzksArr2, 0, length);
                }
                while (length < zzksArr2.length - 1) {
                    zzksArr2[length] = new zzks();
                    zzaca.zza(zzksArr2[length]);
                    zzaca.zzvl();
                    length++;
                }
                zzksArr2[length] = new zzks();
                zzaca.zza(zzksArr2[length]);
                this.zzava = zzksArr2;
            } else if (zzvl == 18) {
                this.name = zzaca.readString();
            } else if (zzvl == 24) {
                this.zzavb = Long.valueOf(zzaca.zzvo());
            } else if (zzvl == 32) {
                this.zzavc = Long.valueOf(zzaca.zzvo());
            } else if (zzvl == 40) {
                this.count = Integer.valueOf(zzaca.zzvn());
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
