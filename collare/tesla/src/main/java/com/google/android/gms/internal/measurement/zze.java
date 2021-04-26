package com.google.android.gms.internal.measurement;

public final class zze extends zzacd<zze> {
    private static volatile zze[] zzns;
    private int name = 0;
    public int[] zznt = zzacm.zzbvp;
    private int zznu = 0;
    private boolean zznv = false;
    private boolean zznw = false;

    public zze() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zze[] zzb() {
        if (zzns == null) {
            synchronized (zzach.zzbzn) {
                if (zzns == null) {
                    zzns = new zze[0];
                }
            }
        }
        return zzns;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zze)) {
            return false;
        }
        zze zze = (zze) obj;
        if (zzach.equals(this.zznt, zze.zznt) && this.zznu == zze.zznu && this.name == zze.name && this.zznv == zze.zznv && this.zznw == zze.zznw) {
            return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zze.zzbzd == null || zze.zzbzd.isEmpty() : this.zzbzd.equals(zze.zzbzd);
        }
        return false;
    }

    public final int hashCode() {
        int i = 1231;
        int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + zzach.hashCode(this.zznt)) * 31) + this.zznu) * 31) + this.name) * 31) + (this.zznv ? 1231 : 1237)) * 31;
        if (!this.zznw) {
            i = 1237;
        }
        return ((hashCode + i) * 31) + ((this.zzbzd == null || this.zzbzd.isEmpty()) ? 0 : this.zzbzd.hashCode());
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int[] iArr;
        int zza = super.zza();
        if (this.zznw) {
            zza += zzacb.zzaq(1) + 1;
        }
        int zzf = zza + zzacb.zzf(2, this.zznu);
        int[] iArr2 = this.zznt;
        if (iArr2 != null && iArr2.length > 0) {
            int i = 0;
            int i2 = 0;
            while (true) {
                iArr = this.zznt;
                if (i >= iArr.length) {
                    break;
                }
                i2 += zzacb.zzao(iArr[i]);
                i++;
            }
            zzf = zzf + i2 + (iArr.length * 1);
        }
        int i3 = this.name;
        if (i3 != 0) {
            zzf += zzacb.zzf(4, i3);
        }
        return this.zznv ? zzf + zzacb.zzaq(6) + 1 : zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        boolean z = this.zznw;
        if (z) {
            zzacb.zza(1, z);
        }
        zzacb.zze(2, this.zznu);
        int[] iArr = this.zznt;
        if (iArr != null && iArr.length > 0) {
            int i = 0;
            while (true) {
                int[] iArr2 = this.zznt;
                if (i >= iArr2.length) {
                    break;
                }
                zzacb.zze(3, iArr2[i]);
                i++;
            }
        }
        int i2 = this.name;
        if (i2 != 0) {
            zzacb.zze(4, i2);
        }
        boolean z2 = this.zznv;
        if (z2) {
            zzacb.zza(6, z2);
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
                this.zznw = zzaca.zzvm();
            } else if (zzvl == 16) {
                this.zznu = zzaca.zzvn();
            } else if (zzvl == 24) {
                int zzb = zzacm.zzb(zzaca, 24);
                int[] iArr = this.zznt;
                int length = iArr == null ? 0 : iArr.length;
                int[] iArr2 = new int[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zznt, 0, iArr2, 0, length);
                }
                while (length < iArr2.length - 1) {
                    iArr2[length] = zzaca.zzvn();
                    zzaca.zzvl();
                    length++;
                }
                iArr2[length] = zzaca.zzvn();
                this.zznt = iArr2;
            } else if (zzvl == 26) {
                int zzaf = zzaca.zzaf(zzaca.zzvn());
                int position = zzaca.getPosition();
                int i = 0;
                while (zzaca.zzvr() > 0) {
                    zzaca.zzvn();
                    i++;
                }
                zzaca.zzam(position);
                int[] iArr3 = this.zznt;
                int length2 = iArr3 == null ? 0 : iArr3.length;
                int[] iArr4 = new int[(i + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.zznt, 0, iArr4, 0, length2);
                }
                while (length2 < iArr4.length) {
                    iArr4[length2] = zzaca.zzvn();
                    length2++;
                }
                this.zznt = iArr4;
                zzaca.zzal(zzaf);
            } else if (zzvl == 32) {
                this.name = zzaca.zzvn();
            } else if (zzvl == 48) {
                this.zznv = zzaca.zzvm();
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
