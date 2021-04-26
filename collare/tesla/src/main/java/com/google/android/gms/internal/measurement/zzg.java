package com.google.android.gms.internal.measurement;

public final class zzg extends zzacd<zzg> {
    public zzm[] zzod = zzm.zzg();
    public zzm[] zzoe = zzm.zzg();
    public zzf[] zzof = zzf.zzc();

    public zzg() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzg)) {
            return false;
        }
        zzg zzg = (zzg) obj;
        if (zzach.equals(this.zzod, zzg.zzod) && zzach.equals(this.zzoe, zzg.zzoe) && zzach.equals(this.zzof, zzg.zzof)) {
            return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzg.zzbzd == null || zzg.zzbzd.isEmpty() : this.zzbzd.equals(zzg.zzbzd);
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((getClass().getName().hashCode() + 527) * 31) + zzach.hashCode(this.zzod)) * 31) + zzach.hashCode(this.zzoe)) * 31) + zzach.hashCode(this.zzof)) * 31) + ((this.zzbzd == null || this.zzbzd.isEmpty()) ? 0 : this.zzbzd.hashCode());
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int zza = super.zza();
        zzm[] zzmArr = this.zzod;
        int i = 0;
        if (zzmArr != null && zzmArr.length > 0) {
            int i2 = zza;
            int i3 = 0;
            while (true) {
                zzm[] zzmArr2 = this.zzod;
                if (i3 >= zzmArr2.length) {
                    break;
                }
                zzm zzm = zzmArr2[i3];
                if (zzm != null) {
                    i2 += zzacb.zzb(1, zzm);
                }
                i3++;
            }
            zza = i2;
        }
        zzm[] zzmArr3 = this.zzoe;
        if (zzmArr3 != null && zzmArr3.length > 0) {
            int i4 = zza;
            int i5 = 0;
            while (true) {
                zzm[] zzmArr4 = this.zzoe;
                if (i5 >= zzmArr4.length) {
                    break;
                }
                zzm zzm2 = zzmArr4[i5];
                if (zzm2 != null) {
                    i4 += zzacb.zzb(2, zzm2);
                }
                i5++;
            }
            zza = i4;
        }
        zzf[] zzfArr = this.zzof;
        if (zzfArr != null && zzfArr.length > 0) {
            while (true) {
                zzf[] zzfArr2 = this.zzof;
                if (i >= zzfArr2.length) {
                    break;
                }
                zzf zzf = zzfArr2[i];
                if (zzf != null) {
                    zza += zzacb.zzb(3, zzf);
                }
                i++;
            }
        }
        return zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        zzm[] zzmArr = this.zzod;
        int i = 0;
        if (zzmArr != null && zzmArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzm[] zzmArr2 = this.zzod;
                if (i2 >= zzmArr2.length) {
                    break;
                }
                zzm zzm = zzmArr2[i2];
                if (zzm != null) {
                    zzacb.zza(1, zzm);
                }
                i2++;
            }
        }
        zzm[] zzmArr3 = this.zzoe;
        if (zzmArr3 != null && zzmArr3.length > 0) {
            int i3 = 0;
            while (true) {
                zzm[] zzmArr4 = this.zzoe;
                if (i3 >= zzmArr4.length) {
                    break;
                }
                zzm zzm2 = zzmArr4[i3];
                if (zzm2 != null) {
                    zzacb.zza(2, zzm2);
                }
                i3++;
            }
        }
        zzf[] zzfArr = this.zzof;
        if (zzfArr != null && zzfArr.length > 0) {
            while (true) {
                zzf[] zzfArr2 = this.zzof;
                if (i >= zzfArr2.length) {
                    break;
                }
                zzf zzf = zzfArr2[i];
                if (zzf != null) {
                    zzacb.zza(3, zzf);
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
            if (zzvl == 10) {
                int zzb = zzacm.zzb(zzaca, 10);
                zzm[] zzmArr = this.zzod;
                int length = zzmArr == null ? 0 : zzmArr.length;
                zzm[] zzmArr2 = new zzm[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzod, 0, zzmArr2, 0, length);
                }
                while (length < zzmArr2.length - 1) {
                    zzmArr2[length] = new zzm();
                    zzaca.zza(zzmArr2[length]);
                    zzaca.zzvl();
                    length++;
                }
                zzmArr2[length] = new zzm();
                zzaca.zza(zzmArr2[length]);
                this.zzod = zzmArr2;
            } else if (zzvl == 18) {
                int zzb2 = zzacm.zzb(zzaca, 18);
                zzm[] zzmArr3 = this.zzoe;
                int length2 = zzmArr3 == null ? 0 : zzmArr3.length;
                zzm[] zzmArr4 = new zzm[(zzb2 + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.zzoe, 0, zzmArr4, 0, length2);
                }
                while (length2 < zzmArr4.length - 1) {
                    zzmArr4[length2] = new zzm();
                    zzaca.zza(zzmArr4[length2]);
                    zzaca.zzvl();
                    length2++;
                }
                zzmArr4[length2] = new zzm();
                zzaca.zza(zzmArr4[length2]);
                this.zzoe = zzmArr4;
            } else if (zzvl == 26) {
                int zzb3 = zzacm.zzb(zzaca, 26);
                zzf[] zzfArr = this.zzof;
                int length3 = zzfArr == null ? 0 : zzfArr.length;
                zzf[] zzfArr2 = new zzf[(zzb3 + length3)];
                if (length3 != 0) {
                    System.arraycopy(this.zzof, 0, zzfArr2, 0, length3);
                }
                while (length3 < zzfArr2.length - 1) {
                    zzfArr2[length3] = new zzf();
                    zzaca.zza(zzfArr2[length3]);
                    zzaca.zzvl();
                    length3++;
                }
                zzfArr2[length3] = new zzf();
                zzaca.zza(zzfArr2[length3]);
                this.zzof = zzfArr2;
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
