package com.google.android.gms.internal.measurement;

public final class zzkl extends zzacd<zzkl> {
    public Integer zzaue = null;
    public String zzauf = null;
    public Boolean zzaug = null;
    public String[] zzauh = zzacm.zzbzx;

    public zzkl() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zze */
    public final zzkl zzb(zzaca zzaca) {
        int zzvn;
        while (true) {
            int zzvl = zzaca.zzvl();
            if (zzvl == 0) {
                return this;
            }
            if (zzvl == 8) {
                try {
                    zzvn = zzaca.zzvn();
                    if (zzvn < 0 || zzvn > 6) {
                        StringBuilder sb = new StringBuilder(41);
                        sb.append(zzvn);
                        sb.append(" is not a valid enum MatchType");
                    } else {
                        this.zzaue = Integer.valueOf(zzvn);
                    }
                } catch (IllegalArgumentException unused) {
                    zzaca.zzam(zzaca.getPosition());
                    zza(zzaca, zzvl);
                }
            } else if (zzvl == 18) {
                this.zzauf = zzaca.readString();
            } else if (zzvl == 24) {
                this.zzaug = Boolean.valueOf(zzaca.zzvm());
            } else if (zzvl == 34) {
                int zzb = zzacm.zzb(zzaca, 34);
                String[] strArr = this.zzauh;
                int length = strArr == null ? 0 : strArr.length;
                String[] strArr2 = new String[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzauh, 0, strArr2, 0, length);
                }
                while (length < strArr2.length - 1) {
                    strArr2[length] = zzaca.readString();
                    zzaca.zzvl();
                    length++;
                }
                strArr2[length] = zzaca.readString();
                this.zzauh = strArr2;
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
        StringBuilder sb2 = new StringBuilder(41);
        sb2.append(zzvn);
        sb2.append(" is not a valid enum MatchType");
        throw new IllegalArgumentException(sb2.toString());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkl)) {
            return false;
        }
        zzkl zzkl = (zzkl) obj;
        Integer num = this.zzaue;
        if (num == null) {
            if (zzkl.zzaue != null) {
                return false;
            }
        } else if (!num.equals(zzkl.zzaue)) {
            return false;
        }
        String str = this.zzauf;
        if (str == null) {
            if (zzkl.zzauf != null) {
                return false;
            }
        } else if (!str.equals(zzkl.zzauf)) {
            return false;
        }
        Boolean bool = this.zzaug;
        if (bool == null) {
            if (zzkl.zzaug != null) {
                return false;
            }
        } else if (!bool.equals(zzkl.zzaug)) {
            return false;
        }
        if (!zzach.equals(this.zzauh, zzkl.zzauh)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzkl.zzbzd == null || zzkl.zzbzd.isEmpty() : this.zzbzd.equals(zzkl.zzbzd);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzaue;
        int i = 0;
        int intValue = (hashCode + (num == null ? 0 : num.intValue())) * 31;
        String str = this.zzauf;
        int hashCode2 = (intValue + (str == null ? 0 : str.hashCode())) * 31;
        Boolean bool = this.zzaug;
        int hashCode3 = (((hashCode2 + (bool == null ? 0 : bool.hashCode())) * 31) + zzach.hashCode(this.zzauh)) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode3 + i;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int zza = super.zza();
        Integer num = this.zzaue;
        if (num != null) {
            zza += zzacb.zzf(1, num.intValue());
        }
        String str = this.zzauf;
        if (str != null) {
            zza += zzacb.zzc(2, str);
        }
        Boolean bool = this.zzaug;
        if (bool != null) {
            bool.booleanValue();
            zza += zzacb.zzaq(3) + 1;
        }
        String[] strArr = this.zzauh;
        if (strArr == null || strArr.length <= 0) {
            return zza;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            String[] strArr2 = this.zzauh;
            if (i >= strArr2.length) {
                return zza + i2 + (i3 * 1);
            }
            String str2 = strArr2[i];
            if (str2 != null) {
                i3++;
                i2 += zzacb.zzfr(str2);
            }
            i++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        Integer num = this.zzaue;
        if (num != null) {
            zzacb.zze(1, num.intValue());
        }
        String str = this.zzauf;
        if (str != null) {
            zzacb.zzb(2, str);
        }
        Boolean bool = this.zzaug;
        if (bool != null) {
            zzacb.zza(3, bool.booleanValue());
        }
        String[] strArr = this.zzauh;
        if (strArr != null && strArr.length > 0) {
            int i = 0;
            while (true) {
                String[] strArr2 = this.zzauh;
                if (i >= strArr2.length) {
                    break;
                }
                String str2 = strArr2[i];
                if (str2 != null) {
                    zzacb.zzb(4, str2);
                }
                i++;
            }
        }
        super.zza(zzacb);
    }
}
