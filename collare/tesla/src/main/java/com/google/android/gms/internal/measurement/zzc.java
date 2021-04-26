package com.google.android.gms.internal.measurement;

public final class zzc {

    public static final class zza extends zzacd<zza> {
        public static final zzace<zzm, zza> zzpi = zzace.zza(11, zza.class, 810);
        private static final zza[] zzpj = new zza[0];
        public int[] zzpk = zzacm.zzbvp;
        public int[] zzpl = zzacm.zzbvp;
        public int[] zzpm = zzacm.zzbvp;
        private int zzpn = 0;
        public int[] zzpo = zzacm.zzbvp;
        public int zzpp = 0;
        private int zzpq = 0;

        public zza() {
            this.zzbzd = null;
            this.zzbzo = -1;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (zzach.equals(this.zzpk, zza.zzpk) && zzach.equals(this.zzpl, zza.zzpl) && zzach.equals(this.zzpm, zza.zzpm) && this.zzpn == zza.zzpn && zzach.equals(this.zzpo, zza.zzpo) && this.zzpp == zza.zzpp && this.zzpq == zza.zzpq) {
                return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zza.zzbzd == null || zza.zzbzd.isEmpty() : this.zzbzd.equals(zza.zzbzd);
            }
            return false;
        }

        public final int hashCode() {
            return ((((((((((((((((getClass().getName().hashCode() + 527) * 31) + zzach.hashCode(this.zzpk)) * 31) + zzach.hashCode(this.zzpl)) * 31) + zzach.hashCode(this.zzpm)) * 31) + this.zzpn) * 31) + zzach.hashCode(this.zzpo)) * 31) + this.zzpp) * 31) + this.zzpq) * 31) + ((this.zzbzd == null || this.zzbzd.isEmpty()) ? 0 : this.zzbzd.hashCode());
        }

        /* access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
        public final int zza() {
            int[] iArr;
            int[] iArr2;
            int[] iArr3;
            int[] iArr4;
            int zza = super.zza();
            int[] iArr5 = this.zzpk;
            int i = 0;
            if (iArr5 != null && iArr5.length > 0) {
                int i2 = 0;
                int i3 = 0;
                while (true) {
                    iArr4 = this.zzpk;
                    if (i2 >= iArr4.length) {
                        break;
                    }
                    i3 += zzacb.zzao(iArr4[i2]);
                    i2++;
                }
                zza = zza + i3 + (iArr4.length * 1);
            }
            int[] iArr6 = this.zzpl;
            if (iArr6 != null && iArr6.length > 0) {
                int i4 = 0;
                int i5 = 0;
                while (true) {
                    iArr3 = this.zzpl;
                    if (i4 >= iArr3.length) {
                        break;
                    }
                    i5 += zzacb.zzao(iArr3[i4]);
                    i4++;
                }
                zza = zza + i5 + (iArr3.length * 1);
            }
            int[] iArr7 = this.zzpm;
            if (iArr7 != null && iArr7.length > 0) {
                int i6 = 0;
                int i7 = 0;
                while (true) {
                    iArr2 = this.zzpm;
                    if (i6 >= iArr2.length) {
                        break;
                    }
                    i7 += zzacb.zzao(iArr2[i6]);
                    i6++;
                }
                zza = zza + i7 + (iArr2.length * 1);
            }
            int i8 = this.zzpn;
            if (i8 != 0) {
                zza += zzacb.zzf(4, i8);
            }
            int[] iArr8 = this.zzpo;
            if (iArr8 != null && iArr8.length > 0) {
                int i9 = 0;
                while (true) {
                    iArr = this.zzpo;
                    if (i >= iArr.length) {
                        break;
                    }
                    i9 += zzacb.zzao(iArr[i]);
                    i++;
                }
                zza = zza + i9 + (iArr.length * 1);
            }
            int i10 = this.zzpp;
            if (i10 != 0) {
                zza += zzacb.zzf(6, i10);
            }
            int i11 = this.zzpq;
            return i11 != 0 ? zza + zzacb.zzf(7, i11) : zza;
        }

        @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
        public final void zza(zzacb zzacb) {
            int[] iArr = this.zzpk;
            int i = 0;
            if (iArr != null && iArr.length > 0) {
                int i2 = 0;
                while (true) {
                    int[] iArr2 = this.zzpk;
                    if (i2 >= iArr2.length) {
                        break;
                    }
                    zzacb.zze(1, iArr2[i2]);
                    i2++;
                }
            }
            int[] iArr3 = this.zzpl;
            if (iArr3 != null && iArr3.length > 0) {
                int i3 = 0;
                while (true) {
                    int[] iArr4 = this.zzpl;
                    if (i3 >= iArr4.length) {
                        break;
                    }
                    zzacb.zze(2, iArr4[i3]);
                    i3++;
                }
            }
            int[] iArr5 = this.zzpm;
            if (iArr5 != null && iArr5.length > 0) {
                int i4 = 0;
                while (true) {
                    int[] iArr6 = this.zzpm;
                    if (i4 >= iArr6.length) {
                        break;
                    }
                    zzacb.zze(3, iArr6[i4]);
                    i4++;
                }
            }
            int i5 = this.zzpn;
            if (i5 != 0) {
                zzacb.zze(4, i5);
            }
            int[] iArr7 = this.zzpo;
            if (iArr7 != null && iArr7.length > 0) {
                while (true) {
                    int[] iArr8 = this.zzpo;
                    if (i >= iArr8.length) {
                        break;
                    }
                    zzacb.zze(5, iArr8[i]);
                    i++;
                }
            }
            int i6 = this.zzpp;
            if (i6 != 0) {
                zzacb.zze(6, i6);
            }
            int i7 = this.zzpq;
            if (i7 != 0) {
                zzacb.zze(7, i7);
            }
            super.zza(zzacb);
        }

        @Override // com.google.android.gms.internal.measurement.zzacj
        public final /* synthetic */ zzacj zzb(zzaca zzaca) {
            int i;
            while (true) {
                int zzvl = zzaca.zzvl();
                switch (zzvl) {
                    case 0:
                        return this;
                    case 8:
                        int zzb = zzacm.zzb(zzaca, 8);
                        int[] iArr = this.zzpk;
                        int length = iArr == null ? 0 : iArr.length;
                        int[] iArr2 = new int[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzpk, 0, iArr2, 0, length);
                        }
                        while (length < iArr2.length - 1) {
                            iArr2[length] = zzaca.zzvn();
                            zzaca.zzvl();
                            length++;
                        }
                        iArr2[length] = zzaca.zzvn();
                        this.zzpk = iArr2;
                        continue;
                    case 10:
                        i = zzaca.zzaf(zzaca.zzvn());
                        int position = zzaca.getPosition();
                        int i2 = 0;
                        while (zzaca.zzvr() > 0) {
                            zzaca.zzvn();
                            i2++;
                        }
                        zzaca.zzam(position);
                        int[] iArr3 = this.zzpk;
                        int length2 = iArr3 == null ? 0 : iArr3.length;
                        int[] iArr4 = new int[(i2 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzpk, 0, iArr4, 0, length2);
                        }
                        while (length2 < iArr4.length) {
                            iArr4[length2] = zzaca.zzvn();
                            length2++;
                        }
                        this.zzpk = iArr4;
                        break;
                    case 16:
                        int zzb2 = zzacm.zzb(zzaca, 16);
                        int[] iArr5 = this.zzpl;
                        int length3 = iArr5 == null ? 0 : iArr5.length;
                        int[] iArr6 = new int[(zzb2 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzpl, 0, iArr6, 0, length3);
                        }
                        while (length3 < iArr6.length - 1) {
                            iArr6[length3] = zzaca.zzvn();
                            zzaca.zzvl();
                            length3++;
                        }
                        iArr6[length3] = zzaca.zzvn();
                        this.zzpl = iArr6;
                        continue;
                    case 18:
                        i = zzaca.zzaf(zzaca.zzvn());
                        int position2 = zzaca.getPosition();
                        int i3 = 0;
                        while (zzaca.zzvr() > 0) {
                            zzaca.zzvn();
                            i3++;
                        }
                        zzaca.zzam(position2);
                        int[] iArr7 = this.zzpl;
                        int length4 = iArr7 == null ? 0 : iArr7.length;
                        int[] iArr8 = new int[(i3 + length4)];
                        if (length4 != 0) {
                            System.arraycopy(this.zzpl, 0, iArr8, 0, length4);
                        }
                        while (length4 < iArr8.length) {
                            iArr8[length4] = zzaca.zzvn();
                            length4++;
                        }
                        this.zzpl = iArr8;
                        break;
                    case 24:
                        int zzb3 = zzacm.zzb(zzaca, 24);
                        int[] iArr9 = this.zzpm;
                        int length5 = iArr9 == null ? 0 : iArr9.length;
                        int[] iArr10 = new int[(zzb3 + length5)];
                        if (length5 != 0) {
                            System.arraycopy(this.zzpm, 0, iArr10, 0, length5);
                        }
                        while (length5 < iArr10.length - 1) {
                            iArr10[length5] = zzaca.zzvn();
                            zzaca.zzvl();
                            length5++;
                        }
                        iArr10[length5] = zzaca.zzvn();
                        this.zzpm = iArr10;
                        continue;
                    case 26:
                        i = zzaca.zzaf(zzaca.zzvn());
                        int position3 = zzaca.getPosition();
                        int i4 = 0;
                        while (zzaca.zzvr() > 0) {
                            zzaca.zzvn();
                            i4++;
                        }
                        zzaca.zzam(position3);
                        int[] iArr11 = this.zzpm;
                        int length6 = iArr11 == null ? 0 : iArr11.length;
                        int[] iArr12 = new int[(i4 + length6)];
                        if (length6 != 0) {
                            System.arraycopy(this.zzpm, 0, iArr12, 0, length6);
                        }
                        while (length6 < iArr12.length) {
                            iArr12[length6] = zzaca.zzvn();
                            length6++;
                        }
                        this.zzpm = iArr12;
                        break;
                    case 32:
                        this.zzpn = zzaca.zzvn();
                        continue;
                    case 40:
                        int zzb4 = zzacm.zzb(zzaca, 40);
                        int[] iArr13 = this.zzpo;
                        int length7 = iArr13 == null ? 0 : iArr13.length;
                        int[] iArr14 = new int[(zzb4 + length7)];
                        if (length7 != 0) {
                            System.arraycopy(this.zzpo, 0, iArr14, 0, length7);
                        }
                        while (length7 < iArr14.length - 1) {
                            iArr14[length7] = zzaca.zzvn();
                            zzaca.zzvl();
                            length7++;
                        }
                        iArr14[length7] = zzaca.zzvn();
                        this.zzpo = iArr14;
                        continue;
                    case 42:
                        i = zzaca.zzaf(zzaca.zzvn());
                        int position4 = zzaca.getPosition();
                        int i5 = 0;
                        while (zzaca.zzvr() > 0) {
                            zzaca.zzvn();
                            i5++;
                        }
                        zzaca.zzam(position4);
                        int[] iArr15 = this.zzpo;
                        int length8 = iArr15 == null ? 0 : iArr15.length;
                        int[] iArr16 = new int[(i5 + length8)];
                        if (length8 != 0) {
                            System.arraycopy(this.zzpo, 0, iArr16, 0, length8);
                        }
                        while (length8 < iArr16.length) {
                            iArr16[length8] = zzaca.zzvn();
                            length8++;
                        }
                        this.zzpo = iArr16;
                        break;
                    case 48:
                        this.zzpp = zzaca.zzvn();
                        continue;
                    case 56:
                        this.zzpq = zzaca.zzvn();
                        continue;
                    default:
                        if (!super.zza(zzaca, zzvl)) {
                            return this;
                        }
                        continue;
                }
                zzaca.zzal(i);
            }
        }
    }
}
