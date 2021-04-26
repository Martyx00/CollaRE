package com.google.android.gms.internal.measurement;

public final class zzl extends zzacd<zzl> {
    public zzk[] zzpu = zzk.zzf();
    public zzi zzpv = null;
    public String zzpw = "";

    public zzl() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzl)) {
            return false;
        }
        zzl zzl = (zzl) obj;
        if (!zzach.equals(this.zzpu, zzl.zzpu)) {
            return false;
        }
        zzi zzi = this.zzpv;
        if (zzi == null) {
            if (zzl.zzpv != null) {
                return false;
            }
        } else if (!zzi.equals(zzl.zzpv)) {
            return false;
        }
        String str = this.zzpw;
        if (str == null) {
            if (zzl.zzpw != null) {
                return false;
            }
        } else if (!str.equals(zzl.zzpw)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzl.zzbzd == null || zzl.zzbzd.isEmpty() : this.zzbzd.equals(zzl.zzbzd);
    }

    public final int hashCode() {
        int hashCode = ((getClass().getName().hashCode() + 527) * 31) + zzach.hashCode(this.zzpu);
        zzi zzi = this.zzpv;
        int i = 0;
        int hashCode2 = ((hashCode * 31) + (zzi == null ? 0 : zzi.hashCode())) * 31;
        String str = this.zzpw;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode3 + i;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int zza = super.zza();
        zzk[] zzkArr = this.zzpu;
        if (zzkArr != null && zzkArr.length > 0) {
            int i = 0;
            while (true) {
                zzk[] zzkArr2 = this.zzpu;
                if (i >= zzkArr2.length) {
                    break;
                }
                zzk zzk = zzkArr2[i];
                if (zzk != null) {
                    zza += zzacb.zzb(1, zzk);
                }
                i++;
            }
        }
        zzi zzi = this.zzpv;
        if (zzi != null) {
            zza += zzacb.zzb(2, zzi);
        }
        String str = this.zzpw;
        return (str == null || str.equals("")) ? zza : zza + zzacb.zzc(3, this.zzpw);
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        zzk[] zzkArr = this.zzpu;
        if (zzkArr != null && zzkArr.length > 0) {
            int i = 0;
            while (true) {
                zzk[] zzkArr2 = this.zzpu;
                if (i >= zzkArr2.length) {
                    break;
                }
                zzk zzk = zzkArr2[i];
                if (zzk != null) {
                    zzacb.zza(1, zzk);
                }
                i++;
            }
        }
        zzi zzi = this.zzpv;
        if (zzi != null) {
            zzacb.zza(2, zzi);
        }
        String str = this.zzpw;
        if (str != null && !str.equals("")) {
            zzacb.zzb(3, this.zzpw);
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
                zzk[] zzkArr = this.zzpu;
                int length = zzkArr == null ? 0 : zzkArr.length;
                zzk[] zzkArr2 = new zzk[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzpu, 0, zzkArr2, 0, length);
                }
                while (length < zzkArr2.length - 1) {
                    zzkArr2[length] = new zzk();
                    zzaca.zza(zzkArr2[length]);
                    zzaca.zzvl();
                    length++;
                }
                zzkArr2[length] = new zzk();
                zzaca.zza(zzkArr2[length]);
                this.zzpu = zzkArr2;
            } else if (zzvl == 18) {
                if (this.zzpv == null) {
                    this.zzpv = new zzi();
                }
                zzaca.zza(this.zzpv);
            } else if (zzvl == 26) {
                this.zzpw = zzaca.readString();
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
