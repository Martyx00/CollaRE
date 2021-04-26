package com.google.android.gms.internal.measurement;

public final class zzm extends zzacd<zzm> {
    private static volatile zzm[] zzpx;
    public String string = "";
    public int type = 1;
    public zzm[] zzpy = zzg();
    public zzm[] zzpz = zzg();
    public zzm[] zzqa = zzg();
    public String zzqb = "";
    public String zzqc = "";
    public long zzqd = 0;
    public boolean zzqe = false;
    public zzm[] zzqf = zzg();
    public int[] zzqg = zzacm.zzbvp;
    public boolean zzqh = false;

    public zzm() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    private static int zza(int i) {
        if (i > 0 && i <= 17) {
            return i;
        }
        StringBuilder sb = new StringBuilder(40);
        sb.append(i);
        sb.append(" is not a valid enum Escaping");
        throw new IllegalArgumentException(sb.toString());
    }

    /* access modifiers changed from: private */
    /* renamed from: zzc */
    public final zzm zzb(zzaca zzaca) {
        int zzvn;
        while (true) {
            int zzvl = zzaca.zzvl();
            switch (zzvl) {
                case 0:
                    return this;
                case 8:
                    try {
                        zzvn = zzaca.zzvn();
                        if (zzvn <= 0 || zzvn > 8) {
                            StringBuilder sb = new StringBuilder(36);
                            sb.append(zzvn);
                            sb.append(" is not a valid enum Type");
                            break;
                        } else {
                            this.type = zzvn;
                            break;
                        }
                    } catch (IllegalArgumentException unused) {
                        zzaca.zzam(zzaca.getPosition());
                        zza(zzaca, zzvl);
                        break;
                    }
                case 18:
                    this.string = zzaca.readString();
                    break;
                case 26:
                    int zzb = zzacm.zzb(zzaca, 26);
                    zzm[] zzmArr = this.zzpy;
                    int length = zzmArr == null ? 0 : zzmArr.length;
                    zzm[] zzmArr2 = new zzm[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzpy, 0, zzmArr2, 0, length);
                    }
                    while (length < zzmArr2.length - 1) {
                        zzmArr2[length] = new zzm();
                        zzaca.zza(zzmArr2[length]);
                        zzaca.zzvl();
                        length++;
                    }
                    zzmArr2[length] = new zzm();
                    zzaca.zza(zzmArr2[length]);
                    this.zzpy = zzmArr2;
                    break;
                case 34:
                    int zzb2 = zzacm.zzb(zzaca, 34);
                    zzm[] zzmArr3 = this.zzpz;
                    int length2 = zzmArr3 == null ? 0 : zzmArr3.length;
                    zzm[] zzmArr4 = new zzm[(zzb2 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzpz, 0, zzmArr4, 0, length2);
                    }
                    while (length2 < zzmArr4.length - 1) {
                        zzmArr4[length2] = new zzm();
                        zzaca.zza(zzmArr4[length2]);
                        zzaca.zzvl();
                        length2++;
                    }
                    zzmArr4[length2] = new zzm();
                    zzaca.zza(zzmArr4[length2]);
                    this.zzpz = zzmArr4;
                    break;
                case 42:
                    int zzb3 = zzacm.zzb(zzaca, 42);
                    zzm[] zzmArr5 = this.zzqa;
                    int length3 = zzmArr5 == null ? 0 : zzmArr5.length;
                    zzm[] zzmArr6 = new zzm[(zzb3 + length3)];
                    if (length3 != 0) {
                        System.arraycopy(this.zzqa, 0, zzmArr6, 0, length3);
                    }
                    while (length3 < zzmArr6.length - 1) {
                        zzmArr6[length3] = new zzm();
                        zzaca.zza(zzmArr6[length3]);
                        zzaca.zzvl();
                        length3++;
                    }
                    zzmArr6[length3] = new zzm();
                    zzaca.zza(zzmArr6[length3]);
                    this.zzqa = zzmArr6;
                    break;
                case 50:
                    this.zzqb = zzaca.readString();
                    break;
                case 58:
                    this.zzqc = zzaca.readString();
                    break;
                case 64:
                    this.zzqd = zzaca.zzvo();
                    break;
                case 72:
                    this.zzqh = zzaca.zzvm();
                    break;
                case 80:
                    int zzb4 = zzacm.zzb(zzaca, 80);
                    int[] iArr = new int[zzb4];
                    int i = 0;
                    for (int i2 = 0; i2 < zzb4; i2++) {
                        if (i2 != 0) {
                            zzaca.zzvl();
                        }
                        int position = zzaca.getPosition();
                        try {
                            iArr[i] = zza(zzaca.zzvn());
                            i++;
                        } catch (IllegalArgumentException unused2) {
                            zzaca.zzam(position);
                            zza(zzaca, zzvl);
                        }
                    }
                    if (i == 0) {
                        break;
                    } else {
                        int[] iArr2 = this.zzqg;
                        int length4 = iArr2 == null ? 0 : iArr2.length;
                        if (length4 != 0 || i != iArr.length) {
                            int[] iArr3 = new int[(length4 + i)];
                            if (length4 != 0) {
                                System.arraycopy(this.zzqg, 0, iArr3, 0, length4);
                            }
                            System.arraycopy(iArr, 0, iArr3, length4, i);
                            this.zzqg = iArr3;
                            break;
                        } else {
                            this.zzqg = iArr;
                            break;
                        }
                    }
                case 82:
                    int zzaf = zzaca.zzaf(zzaca.zzvn());
                    int position2 = zzaca.getPosition();
                    int i3 = 0;
                    while (zzaca.zzvr() > 0) {
                        try {
                            zza(zzaca.zzvn());
                            i3++;
                        } catch (IllegalArgumentException unused3) {
                        }
                    }
                    if (i3 != 0) {
                        zzaca.zzam(position2);
                        int[] iArr4 = this.zzqg;
                        int length5 = iArr4 == null ? 0 : iArr4.length;
                        int[] iArr5 = new int[(i3 + length5)];
                        if (length5 != 0) {
                            System.arraycopy(this.zzqg, 0, iArr5, 0, length5);
                        }
                        while (zzaca.zzvr() > 0) {
                            int position3 = zzaca.getPosition();
                            try {
                                iArr5[length5] = zza(zzaca.zzvn());
                                length5++;
                            } catch (IllegalArgumentException unused4) {
                                zzaca.zzam(position3);
                                zza(zzaca, 80);
                            }
                        }
                        this.zzqg = iArr5;
                    }
                    zzaca.zzal(zzaf);
                    break;
                case 90:
                    int zzb5 = zzacm.zzb(zzaca, 90);
                    zzm[] zzmArr7 = this.zzqf;
                    int length6 = zzmArr7 == null ? 0 : zzmArr7.length;
                    zzm[] zzmArr8 = new zzm[(zzb5 + length6)];
                    if (length6 != 0) {
                        System.arraycopy(this.zzqf, 0, zzmArr8, 0, length6);
                    }
                    while (length6 < zzmArr8.length - 1) {
                        zzmArr8[length6] = new zzm();
                        zzaca.zza(zzmArr8[length6]);
                        zzaca.zzvl();
                        length6++;
                    }
                    zzmArr8[length6] = new zzm();
                    zzaca.zza(zzmArr8[length6]);
                    this.zzqf = zzmArr8;
                    break;
                case 96:
                    this.zzqe = zzaca.zzvm();
                    break;
                default:
                    if (super.zza(zzaca, zzvl)) {
                        break;
                    } else {
                        return this;
                    }
            }
        }
        StringBuilder sb2 = new StringBuilder(36);
        sb2.append(zzvn);
        sb2.append(" is not a valid enum Type");
        throw new IllegalArgumentException(sb2.toString());
    }

    public static zzm[] zzg() {
        if (zzpx == null) {
            synchronized (zzach.zzbzn) {
                if (zzpx == null) {
                    zzpx = new zzm[0];
                }
            }
        }
        return zzpx;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzm)) {
            return false;
        }
        zzm zzm = (zzm) obj;
        if (this.type != zzm.type) {
            return false;
        }
        String str = this.string;
        if (str == null) {
            if (zzm.string != null) {
                return false;
            }
        } else if (!str.equals(zzm.string)) {
            return false;
        }
        if (!zzach.equals(this.zzpy, zzm.zzpy) || !zzach.equals(this.zzpz, zzm.zzpz) || !zzach.equals(this.zzqa, zzm.zzqa)) {
            return false;
        }
        String str2 = this.zzqb;
        if (str2 == null) {
            if (zzm.zzqb != null) {
                return false;
            }
        } else if (!str2.equals(zzm.zzqb)) {
            return false;
        }
        String str3 = this.zzqc;
        if (str3 == null) {
            if (zzm.zzqc != null) {
                return false;
            }
        } else if (!str3.equals(zzm.zzqc)) {
            return false;
        }
        if (this.zzqd == zzm.zzqd && this.zzqe == zzm.zzqe && zzach.equals(this.zzqf, zzm.zzqf) && zzach.equals(this.zzqg, zzm.zzqg) && this.zzqh == zzm.zzqh) {
            return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzm.zzbzd == null || zzm.zzbzd.isEmpty() : this.zzbzd.equals(zzm.zzbzd);
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.type) * 31;
        String str = this.string;
        int i = 0;
        int hashCode2 = (((((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + zzach.hashCode(this.zzpy)) * 31) + zzach.hashCode(this.zzpz)) * 31) + zzach.hashCode(this.zzqa)) * 31;
        String str2 = this.zzqb;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.zzqc;
        int hashCode4 = str3 == null ? 0 : str3.hashCode();
        long j = this.zzqd;
        int i2 = (((hashCode3 + hashCode4) * 31) + ((int) (j ^ (j >>> 32)))) * 31;
        int i3 = 1231;
        int hashCode5 = (((((i2 + (this.zzqe ? 1231 : 1237)) * 31) + zzach.hashCode(this.zzqf)) * 31) + zzach.hashCode(this.zzqg)) * 31;
        if (!this.zzqh) {
            i3 = 1237;
        }
        int i4 = (hashCode5 + i3) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return i4 + i;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int[] iArr;
        int zza = super.zza() + zzacb.zzf(1, this.type);
        String str = this.string;
        if (str != null && !str.equals("")) {
            zza += zzacb.zzc(2, this.string);
        }
        zzm[] zzmArr = this.zzpy;
        int i = 0;
        if (zzmArr != null && zzmArr.length > 0) {
            int i2 = zza;
            int i3 = 0;
            while (true) {
                zzm[] zzmArr2 = this.zzpy;
                if (i3 >= zzmArr2.length) {
                    break;
                }
                zzm zzm = zzmArr2[i3];
                if (zzm != null) {
                    i2 += zzacb.zzb(3, zzm);
                }
                i3++;
            }
            zza = i2;
        }
        zzm[] zzmArr3 = this.zzpz;
        if (zzmArr3 != null && zzmArr3.length > 0) {
            int i4 = zza;
            int i5 = 0;
            while (true) {
                zzm[] zzmArr4 = this.zzpz;
                if (i5 >= zzmArr4.length) {
                    break;
                }
                zzm zzm2 = zzmArr4[i5];
                if (zzm2 != null) {
                    i4 += zzacb.zzb(4, zzm2);
                }
                i5++;
            }
            zza = i4;
        }
        zzm[] zzmArr5 = this.zzqa;
        if (zzmArr5 != null && zzmArr5.length > 0) {
            int i6 = zza;
            int i7 = 0;
            while (true) {
                zzm[] zzmArr6 = this.zzqa;
                if (i7 >= zzmArr6.length) {
                    break;
                }
                zzm zzm3 = zzmArr6[i7];
                if (zzm3 != null) {
                    i6 += zzacb.zzb(5, zzm3);
                }
                i7++;
            }
            zza = i6;
        }
        String str2 = this.zzqb;
        if (str2 != null && !str2.equals("")) {
            zza += zzacb.zzc(6, this.zzqb);
        }
        String str3 = this.zzqc;
        if (str3 != null && !str3.equals("")) {
            zza += zzacb.zzc(7, this.zzqc);
        }
        long j = this.zzqd;
        if (j != 0) {
            zza += zzacb.zzc(8, j);
        }
        if (this.zzqh) {
            zza += zzacb.zzaq(9) + 1;
        }
        int[] iArr2 = this.zzqg;
        if (iArr2 != null && iArr2.length > 0) {
            int i8 = 0;
            int i9 = 0;
            while (true) {
                iArr = this.zzqg;
                if (i8 >= iArr.length) {
                    break;
                }
                i9 += zzacb.zzao(iArr[i8]);
                i8++;
            }
            zza = zza + i9 + (iArr.length * 1);
        }
        zzm[] zzmArr7 = this.zzqf;
        if (zzmArr7 != null && zzmArr7.length > 0) {
            while (true) {
                zzm[] zzmArr8 = this.zzqf;
                if (i >= zzmArr8.length) {
                    break;
                }
                zzm zzm4 = zzmArr8[i];
                if (zzm4 != null) {
                    zza += zzacb.zzb(11, zzm4);
                }
                i++;
            }
        }
        return this.zzqe ? zza + zzacb.zzaq(12) + 1 : zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        zzacb.zze(1, this.type);
        String str = this.string;
        if (str != null && !str.equals("")) {
            zzacb.zzb(2, this.string);
        }
        zzm[] zzmArr = this.zzpy;
        int i = 0;
        if (zzmArr != null && zzmArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzm[] zzmArr2 = this.zzpy;
                if (i2 >= zzmArr2.length) {
                    break;
                }
                zzm zzm = zzmArr2[i2];
                if (zzm != null) {
                    zzacb.zza(3, zzm);
                }
                i2++;
            }
        }
        zzm[] zzmArr3 = this.zzpz;
        if (zzmArr3 != null && zzmArr3.length > 0) {
            int i3 = 0;
            while (true) {
                zzm[] zzmArr4 = this.zzpz;
                if (i3 >= zzmArr4.length) {
                    break;
                }
                zzm zzm2 = zzmArr4[i3];
                if (zzm2 != null) {
                    zzacb.zza(4, zzm2);
                }
                i3++;
            }
        }
        zzm[] zzmArr5 = this.zzqa;
        if (zzmArr5 != null && zzmArr5.length > 0) {
            int i4 = 0;
            while (true) {
                zzm[] zzmArr6 = this.zzqa;
                if (i4 >= zzmArr6.length) {
                    break;
                }
                zzm zzm3 = zzmArr6[i4];
                if (zzm3 != null) {
                    zzacb.zza(5, zzm3);
                }
                i4++;
            }
        }
        String str2 = this.zzqb;
        if (str2 != null && !str2.equals("")) {
            zzacb.zzb(6, this.zzqb);
        }
        String str3 = this.zzqc;
        if (str3 != null && !str3.equals("")) {
            zzacb.zzb(7, this.zzqc);
        }
        long j = this.zzqd;
        if (j != 0) {
            zzacb.zzb(8, j);
        }
        boolean z = this.zzqh;
        if (z) {
            zzacb.zza(9, z);
        }
        int[] iArr = this.zzqg;
        if (iArr != null && iArr.length > 0) {
            int i5 = 0;
            while (true) {
                int[] iArr2 = this.zzqg;
                if (i5 >= iArr2.length) {
                    break;
                }
                zzacb.zze(10, iArr2[i5]);
                i5++;
            }
        }
        zzm[] zzmArr7 = this.zzqf;
        if (zzmArr7 != null && zzmArr7.length > 0) {
            while (true) {
                zzm[] zzmArr8 = this.zzqf;
                if (i >= zzmArr8.length) {
                    break;
                }
                zzm zzm4 = zzmArr8[i];
                if (zzm4 != null) {
                    zzacb.zza(11, zzm4);
                }
                i++;
            }
        }
        boolean z2 = this.zzqe;
        if (z2) {
            zzacb.zza(12, z2);
        }
        super.zza(zzacb);
    }
}
