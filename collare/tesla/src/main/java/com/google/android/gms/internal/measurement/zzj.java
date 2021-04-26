package com.google.android.gms.internal.measurement;

public final class zzj extends zzacd<zzj> {
    private static volatile zzj[] zzox;
    public int[] zzoy = zzacm.zzbvp;
    public int[] zzoz = zzacm.zzbvp;
    public int[] zzpa = zzacm.zzbvp;
    public int[] zzpb = zzacm.zzbvp;
    public int[] zzpc = zzacm.zzbvp;
    public int[] zzpd = zzacm.zzbvp;
    public int[] zzpe = zzacm.zzbvp;
    public int[] zzpf = zzacm.zzbvp;
    public int[] zzpg = zzacm.zzbvp;
    public int[] zzph = zzacm.zzbvp;

    public zzj() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzj[] zze() {
        if (zzox == null) {
            synchronized (zzach.zzbzn) {
                if (zzox == null) {
                    zzox = new zzj[0];
                }
            }
        }
        return zzox;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzj)) {
            return false;
        }
        zzj zzj = (zzj) obj;
        if (zzach.equals(this.zzoy, zzj.zzoy) && zzach.equals(this.zzoz, zzj.zzoz) && zzach.equals(this.zzpa, zzj.zzpa) && zzach.equals(this.zzpb, zzj.zzpb) && zzach.equals(this.zzpc, zzj.zzpc) && zzach.equals(this.zzpd, zzj.zzpd) && zzach.equals(this.zzpe, zzj.zzpe) && zzach.equals(this.zzpf, zzj.zzpf) && zzach.equals(this.zzpg, zzj.zzpg) && zzach.equals(this.zzph, zzj.zzph)) {
            return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzj.zzbzd == null || zzj.zzbzd.isEmpty() : this.zzbzd.equals(zzj.zzbzd);
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + zzach.hashCode(this.zzoy)) * 31) + zzach.hashCode(this.zzoz)) * 31) + zzach.hashCode(this.zzpa)) * 31) + zzach.hashCode(this.zzpb)) * 31) + zzach.hashCode(this.zzpc)) * 31) + zzach.hashCode(this.zzpd)) * 31) + zzach.hashCode(this.zzpe)) * 31) + zzach.hashCode(this.zzpf)) * 31) + zzach.hashCode(this.zzpg)) * 31) + zzach.hashCode(this.zzph)) * 31) + ((this.zzbzd == null || this.zzbzd.isEmpty()) ? 0 : this.zzbzd.hashCode());
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] iArr5;
        int[] iArr6;
        int[] iArr7;
        int[] iArr8;
        int[] iArr9;
        int zza = super.zza();
        int[] iArr10 = this.zzoy;
        int i = 0;
        if (iArr10 != null && iArr10.length > 0) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                iArr9 = this.zzoy;
                if (i2 >= iArr9.length) {
                    break;
                }
                i3 += zzacb.zzao(iArr9[i2]);
                i2++;
            }
            zza = zza + i3 + (iArr9.length * 1);
        }
        int[] iArr11 = this.zzoz;
        if (iArr11 != null && iArr11.length > 0) {
            int i4 = 0;
            int i5 = 0;
            while (true) {
                iArr8 = this.zzoz;
                if (i4 >= iArr8.length) {
                    break;
                }
                i5 += zzacb.zzao(iArr8[i4]);
                i4++;
            }
            zza = zza + i5 + (iArr8.length * 1);
        }
        int[] iArr12 = this.zzpa;
        if (iArr12 != null && iArr12.length > 0) {
            int i6 = 0;
            int i7 = 0;
            while (true) {
                iArr7 = this.zzpa;
                if (i6 >= iArr7.length) {
                    break;
                }
                i7 += zzacb.zzao(iArr7[i6]);
                i6++;
            }
            zza = zza + i7 + (iArr7.length * 1);
        }
        int[] iArr13 = this.zzpb;
        if (iArr13 != null && iArr13.length > 0) {
            int i8 = 0;
            int i9 = 0;
            while (true) {
                iArr6 = this.zzpb;
                if (i8 >= iArr6.length) {
                    break;
                }
                i9 += zzacb.zzao(iArr6[i8]);
                i8++;
            }
            zza = zza + i9 + (iArr6.length * 1);
        }
        int[] iArr14 = this.zzpc;
        if (iArr14 != null && iArr14.length > 0) {
            int i10 = 0;
            int i11 = 0;
            while (true) {
                iArr5 = this.zzpc;
                if (i10 >= iArr5.length) {
                    break;
                }
                i11 += zzacb.zzao(iArr5[i10]);
                i10++;
            }
            zza = zza + i11 + (iArr5.length * 1);
        }
        int[] iArr15 = this.zzpd;
        if (iArr15 != null && iArr15.length > 0) {
            int i12 = 0;
            int i13 = 0;
            while (true) {
                iArr4 = this.zzpd;
                if (i12 >= iArr4.length) {
                    break;
                }
                i13 += zzacb.zzao(iArr4[i12]);
                i12++;
            }
            zza = zza + i13 + (iArr4.length * 1);
        }
        int[] iArr16 = this.zzpe;
        if (iArr16 != null && iArr16.length > 0) {
            int i14 = 0;
            int i15 = 0;
            while (true) {
                iArr3 = this.zzpe;
                if (i14 >= iArr3.length) {
                    break;
                }
                i15 += zzacb.zzao(iArr3[i14]);
                i14++;
            }
            zza = zza + i15 + (iArr3.length * 1);
        }
        int[] iArr17 = this.zzpf;
        if (iArr17 != null && iArr17.length > 0) {
            int i16 = 0;
            int i17 = 0;
            while (true) {
                iArr2 = this.zzpf;
                if (i16 >= iArr2.length) {
                    break;
                }
                i17 += zzacb.zzao(iArr2[i16]);
                i16++;
            }
            zza = zza + i17 + (iArr2.length * 1);
        }
        int[] iArr18 = this.zzpg;
        if (iArr18 != null && iArr18.length > 0) {
            int i18 = 0;
            int i19 = 0;
            while (true) {
                iArr = this.zzpg;
                if (i18 >= iArr.length) {
                    break;
                }
                i19 += zzacb.zzao(iArr[i18]);
                i18++;
            }
            zza = zza + i19 + (iArr.length * 1);
        }
        int[] iArr19 = this.zzph;
        if (iArr19 == null || iArr19.length <= 0) {
            return zza;
        }
        int i20 = 0;
        while (true) {
            int[] iArr20 = this.zzph;
            if (i >= iArr20.length) {
                return zza + i20 + (iArr20.length * 1);
            }
            i20 += zzacb.zzao(iArr20[i]);
            i++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        int[] iArr = this.zzoy;
        int i = 0;
        if (iArr != null && iArr.length > 0) {
            int i2 = 0;
            while (true) {
                int[] iArr2 = this.zzoy;
                if (i2 >= iArr2.length) {
                    break;
                }
                zzacb.zze(1, iArr2[i2]);
                i2++;
            }
        }
        int[] iArr3 = this.zzoz;
        if (iArr3 != null && iArr3.length > 0) {
            int i3 = 0;
            while (true) {
                int[] iArr4 = this.zzoz;
                if (i3 >= iArr4.length) {
                    break;
                }
                zzacb.zze(2, iArr4[i3]);
                i3++;
            }
        }
        int[] iArr5 = this.zzpa;
        if (iArr5 != null && iArr5.length > 0) {
            int i4 = 0;
            while (true) {
                int[] iArr6 = this.zzpa;
                if (i4 >= iArr6.length) {
                    break;
                }
                zzacb.zze(3, iArr6[i4]);
                i4++;
            }
        }
        int[] iArr7 = this.zzpb;
        if (iArr7 != null && iArr7.length > 0) {
            int i5 = 0;
            while (true) {
                int[] iArr8 = this.zzpb;
                if (i5 >= iArr8.length) {
                    break;
                }
                zzacb.zze(4, iArr8[i5]);
                i5++;
            }
        }
        int[] iArr9 = this.zzpc;
        if (iArr9 != null && iArr9.length > 0) {
            int i6 = 0;
            while (true) {
                int[] iArr10 = this.zzpc;
                if (i6 >= iArr10.length) {
                    break;
                }
                zzacb.zze(5, iArr10[i6]);
                i6++;
            }
        }
        int[] iArr11 = this.zzpd;
        if (iArr11 != null && iArr11.length > 0) {
            int i7 = 0;
            while (true) {
                int[] iArr12 = this.zzpd;
                if (i7 >= iArr12.length) {
                    break;
                }
                zzacb.zze(6, iArr12[i7]);
                i7++;
            }
        }
        int[] iArr13 = this.zzpe;
        if (iArr13 != null && iArr13.length > 0) {
            int i8 = 0;
            while (true) {
                int[] iArr14 = this.zzpe;
                if (i8 >= iArr14.length) {
                    break;
                }
                zzacb.zze(7, iArr14[i8]);
                i8++;
            }
        }
        int[] iArr15 = this.zzpf;
        if (iArr15 != null && iArr15.length > 0) {
            int i9 = 0;
            while (true) {
                int[] iArr16 = this.zzpf;
                if (i9 >= iArr16.length) {
                    break;
                }
                zzacb.zze(8, iArr16[i9]);
                i9++;
            }
        }
        int[] iArr17 = this.zzpg;
        if (iArr17 != null && iArr17.length > 0) {
            int i10 = 0;
            while (true) {
                int[] iArr18 = this.zzpg;
                if (i10 >= iArr18.length) {
                    break;
                }
                zzacb.zze(9, iArr18[i10]);
                i10++;
            }
        }
        int[] iArr19 = this.zzph;
        if (iArr19 != null && iArr19.length > 0) {
            while (true) {
                int[] iArr20 = this.zzph;
                if (i >= iArr20.length) {
                    break;
                }
                zzacb.zze(10, iArr20[i]);
                i++;
            }
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
                    int[] iArr = this.zzoy;
                    int length = iArr == null ? 0 : iArr.length;
                    int[] iArr2 = new int[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzoy, 0, iArr2, 0, length);
                    }
                    while (length < iArr2.length - 1) {
                        iArr2[length] = zzaca.zzvn();
                        zzaca.zzvl();
                        length++;
                    }
                    iArr2[length] = zzaca.zzvn();
                    this.zzoy = iArr2;
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
                    int[] iArr3 = this.zzoy;
                    int length2 = iArr3 == null ? 0 : iArr3.length;
                    int[] iArr4 = new int[(i2 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzoy, 0, iArr4, 0, length2);
                    }
                    while (length2 < iArr4.length) {
                        iArr4[length2] = zzaca.zzvn();
                        length2++;
                    }
                    this.zzoy = iArr4;
                    break;
                case 16:
                    int zzb2 = zzacm.zzb(zzaca, 16);
                    int[] iArr5 = this.zzoz;
                    int length3 = iArr5 == null ? 0 : iArr5.length;
                    int[] iArr6 = new int[(zzb2 + length3)];
                    if (length3 != 0) {
                        System.arraycopy(this.zzoz, 0, iArr6, 0, length3);
                    }
                    while (length3 < iArr6.length - 1) {
                        iArr6[length3] = zzaca.zzvn();
                        zzaca.zzvl();
                        length3++;
                    }
                    iArr6[length3] = zzaca.zzvn();
                    this.zzoz = iArr6;
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
                    int[] iArr7 = this.zzoz;
                    int length4 = iArr7 == null ? 0 : iArr7.length;
                    int[] iArr8 = new int[(i3 + length4)];
                    if (length4 != 0) {
                        System.arraycopy(this.zzoz, 0, iArr8, 0, length4);
                    }
                    while (length4 < iArr8.length) {
                        iArr8[length4] = zzaca.zzvn();
                        length4++;
                    }
                    this.zzoz = iArr8;
                    break;
                case 24:
                    int zzb3 = zzacm.zzb(zzaca, 24);
                    int[] iArr9 = this.zzpa;
                    int length5 = iArr9 == null ? 0 : iArr9.length;
                    int[] iArr10 = new int[(zzb3 + length5)];
                    if (length5 != 0) {
                        System.arraycopy(this.zzpa, 0, iArr10, 0, length5);
                    }
                    while (length5 < iArr10.length - 1) {
                        iArr10[length5] = zzaca.zzvn();
                        zzaca.zzvl();
                        length5++;
                    }
                    iArr10[length5] = zzaca.zzvn();
                    this.zzpa = iArr10;
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
                    int[] iArr11 = this.zzpa;
                    int length6 = iArr11 == null ? 0 : iArr11.length;
                    int[] iArr12 = new int[(i4 + length6)];
                    if (length6 != 0) {
                        System.arraycopy(this.zzpa, 0, iArr12, 0, length6);
                    }
                    while (length6 < iArr12.length) {
                        iArr12[length6] = zzaca.zzvn();
                        length6++;
                    }
                    this.zzpa = iArr12;
                    break;
                case 32:
                    int zzb4 = zzacm.zzb(zzaca, 32);
                    int[] iArr13 = this.zzpb;
                    int length7 = iArr13 == null ? 0 : iArr13.length;
                    int[] iArr14 = new int[(zzb4 + length7)];
                    if (length7 != 0) {
                        System.arraycopy(this.zzpb, 0, iArr14, 0, length7);
                    }
                    while (length7 < iArr14.length - 1) {
                        iArr14[length7] = zzaca.zzvn();
                        zzaca.zzvl();
                        length7++;
                    }
                    iArr14[length7] = zzaca.zzvn();
                    this.zzpb = iArr14;
                    continue;
                case 34:
                    i = zzaca.zzaf(zzaca.zzvn());
                    int position4 = zzaca.getPosition();
                    int i5 = 0;
                    while (zzaca.zzvr() > 0) {
                        zzaca.zzvn();
                        i5++;
                    }
                    zzaca.zzam(position4);
                    int[] iArr15 = this.zzpb;
                    int length8 = iArr15 == null ? 0 : iArr15.length;
                    int[] iArr16 = new int[(i5 + length8)];
                    if (length8 != 0) {
                        System.arraycopy(this.zzpb, 0, iArr16, 0, length8);
                    }
                    while (length8 < iArr16.length) {
                        iArr16[length8] = zzaca.zzvn();
                        length8++;
                    }
                    this.zzpb = iArr16;
                    break;
                case 40:
                    int zzb5 = zzacm.zzb(zzaca, 40);
                    int[] iArr17 = this.zzpc;
                    int length9 = iArr17 == null ? 0 : iArr17.length;
                    int[] iArr18 = new int[(zzb5 + length9)];
                    if (length9 != 0) {
                        System.arraycopy(this.zzpc, 0, iArr18, 0, length9);
                    }
                    while (length9 < iArr18.length - 1) {
                        iArr18[length9] = zzaca.zzvn();
                        zzaca.zzvl();
                        length9++;
                    }
                    iArr18[length9] = zzaca.zzvn();
                    this.zzpc = iArr18;
                    continue;
                case 42:
                    i = zzaca.zzaf(zzaca.zzvn());
                    int position5 = zzaca.getPosition();
                    int i6 = 0;
                    while (zzaca.zzvr() > 0) {
                        zzaca.zzvn();
                        i6++;
                    }
                    zzaca.zzam(position5);
                    int[] iArr19 = this.zzpc;
                    int length10 = iArr19 == null ? 0 : iArr19.length;
                    int[] iArr20 = new int[(i6 + length10)];
                    if (length10 != 0) {
                        System.arraycopy(this.zzpc, 0, iArr20, 0, length10);
                    }
                    while (length10 < iArr20.length) {
                        iArr20[length10] = zzaca.zzvn();
                        length10++;
                    }
                    this.zzpc = iArr20;
                    break;
                case 48:
                    int zzb6 = zzacm.zzb(zzaca, 48);
                    int[] iArr21 = this.zzpd;
                    int length11 = iArr21 == null ? 0 : iArr21.length;
                    int[] iArr22 = new int[(zzb6 + length11)];
                    if (length11 != 0) {
                        System.arraycopy(this.zzpd, 0, iArr22, 0, length11);
                    }
                    while (length11 < iArr22.length - 1) {
                        iArr22[length11] = zzaca.zzvn();
                        zzaca.zzvl();
                        length11++;
                    }
                    iArr22[length11] = zzaca.zzvn();
                    this.zzpd = iArr22;
                    continue;
                case 50:
                    i = zzaca.zzaf(zzaca.zzvn());
                    int position6 = zzaca.getPosition();
                    int i7 = 0;
                    while (zzaca.zzvr() > 0) {
                        zzaca.zzvn();
                        i7++;
                    }
                    zzaca.zzam(position6);
                    int[] iArr23 = this.zzpd;
                    int length12 = iArr23 == null ? 0 : iArr23.length;
                    int[] iArr24 = new int[(i7 + length12)];
                    if (length12 != 0) {
                        System.arraycopy(this.zzpd, 0, iArr24, 0, length12);
                    }
                    while (length12 < iArr24.length) {
                        iArr24[length12] = zzaca.zzvn();
                        length12++;
                    }
                    this.zzpd = iArr24;
                    break;
                case 56:
                    int zzb7 = zzacm.zzb(zzaca, 56);
                    int[] iArr25 = this.zzpe;
                    int length13 = iArr25 == null ? 0 : iArr25.length;
                    int[] iArr26 = new int[(zzb7 + length13)];
                    if (length13 != 0) {
                        System.arraycopy(this.zzpe, 0, iArr26, 0, length13);
                    }
                    while (length13 < iArr26.length - 1) {
                        iArr26[length13] = zzaca.zzvn();
                        zzaca.zzvl();
                        length13++;
                    }
                    iArr26[length13] = zzaca.zzvn();
                    this.zzpe = iArr26;
                    continue;
                case 58:
                    i = zzaca.zzaf(zzaca.zzvn());
                    int position7 = zzaca.getPosition();
                    int i8 = 0;
                    while (zzaca.zzvr() > 0) {
                        zzaca.zzvn();
                        i8++;
                    }
                    zzaca.zzam(position7);
                    int[] iArr27 = this.zzpe;
                    int length14 = iArr27 == null ? 0 : iArr27.length;
                    int[] iArr28 = new int[(i8 + length14)];
                    if (length14 != 0) {
                        System.arraycopy(this.zzpe, 0, iArr28, 0, length14);
                    }
                    while (length14 < iArr28.length) {
                        iArr28[length14] = zzaca.zzvn();
                        length14++;
                    }
                    this.zzpe = iArr28;
                    break;
                case 64:
                    int zzb8 = zzacm.zzb(zzaca, 64);
                    int[] iArr29 = this.zzpf;
                    int length15 = iArr29 == null ? 0 : iArr29.length;
                    int[] iArr30 = new int[(zzb8 + length15)];
                    if (length15 != 0) {
                        System.arraycopy(this.zzpf, 0, iArr30, 0, length15);
                    }
                    while (length15 < iArr30.length - 1) {
                        iArr30[length15] = zzaca.zzvn();
                        zzaca.zzvl();
                        length15++;
                    }
                    iArr30[length15] = zzaca.zzvn();
                    this.zzpf = iArr30;
                    continue;
                case 66:
                    i = zzaca.zzaf(zzaca.zzvn());
                    int position8 = zzaca.getPosition();
                    int i9 = 0;
                    while (zzaca.zzvr() > 0) {
                        zzaca.zzvn();
                        i9++;
                    }
                    zzaca.zzam(position8);
                    int[] iArr31 = this.zzpf;
                    int length16 = iArr31 == null ? 0 : iArr31.length;
                    int[] iArr32 = new int[(i9 + length16)];
                    if (length16 != 0) {
                        System.arraycopy(this.zzpf, 0, iArr32, 0, length16);
                    }
                    while (length16 < iArr32.length) {
                        iArr32[length16] = zzaca.zzvn();
                        length16++;
                    }
                    this.zzpf = iArr32;
                    break;
                case 72:
                    int zzb9 = zzacm.zzb(zzaca, 72);
                    int[] iArr33 = this.zzpg;
                    int length17 = iArr33 == null ? 0 : iArr33.length;
                    int[] iArr34 = new int[(zzb9 + length17)];
                    if (length17 != 0) {
                        System.arraycopy(this.zzpg, 0, iArr34, 0, length17);
                    }
                    while (length17 < iArr34.length - 1) {
                        iArr34[length17] = zzaca.zzvn();
                        zzaca.zzvl();
                        length17++;
                    }
                    iArr34[length17] = zzaca.zzvn();
                    this.zzpg = iArr34;
                    continue;
                case 74:
                    i = zzaca.zzaf(zzaca.zzvn());
                    int position9 = zzaca.getPosition();
                    int i10 = 0;
                    while (zzaca.zzvr() > 0) {
                        zzaca.zzvn();
                        i10++;
                    }
                    zzaca.zzam(position9);
                    int[] iArr35 = this.zzpg;
                    int length18 = iArr35 == null ? 0 : iArr35.length;
                    int[] iArr36 = new int[(i10 + length18)];
                    if (length18 != 0) {
                        System.arraycopy(this.zzpg, 0, iArr36, 0, length18);
                    }
                    while (length18 < iArr36.length) {
                        iArr36[length18] = zzaca.zzvn();
                        length18++;
                    }
                    this.zzpg = iArr36;
                    break;
                case 80:
                    int zzb10 = zzacm.zzb(zzaca, 80);
                    int[] iArr37 = this.zzph;
                    int length19 = iArr37 == null ? 0 : iArr37.length;
                    int[] iArr38 = new int[(zzb10 + length19)];
                    if (length19 != 0) {
                        System.arraycopy(this.zzph, 0, iArr38, 0, length19);
                    }
                    while (length19 < iArr38.length - 1) {
                        iArr38[length19] = zzaca.zzvn();
                        zzaca.zzvl();
                        length19++;
                    }
                    iArr38[length19] = zzaca.zzvn();
                    this.zzph = iArr38;
                    continue;
                case 82:
                    i = zzaca.zzaf(zzaca.zzvn());
                    int position10 = zzaca.getPosition();
                    int i11 = 0;
                    while (zzaca.zzvr() > 0) {
                        zzaca.zzvn();
                        i11++;
                    }
                    zzaca.zzam(position10);
                    int[] iArr39 = this.zzph;
                    int length20 = iArr39 == null ? 0 : iArr39.length;
                    int[] iArr40 = new int[(i11 + length20)];
                    if (length20 != 0) {
                        System.arraycopy(this.zzph, 0, iArr40, 0, length20);
                    }
                    while (length20 < iArr40.length) {
                        iArr40[length20] = zzaca.zzvn();
                        length20++;
                    }
                    this.zzph = iArr40;
                    break;
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
