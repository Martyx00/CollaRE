package com.google.android.gms.internal.measurement;

public final class zzkv extends zzacd<zzkv> {
    public long[] zzawl = zzacm.zzbzt;
    public long[] zzawm = zzacm.zzbzt;
    public zzkq[] zzawn = zzkq.zzlx();
    private zzkw[] zzawo = zzkw.zzmb();

    public zzkv() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkv)) {
            return false;
        }
        zzkv zzkv = (zzkv) obj;
        if (zzach.equals(this.zzawl, zzkv.zzawl) && zzach.equals(this.zzawm, zzkv.zzawm) && zzach.equals(this.zzawn, zzkv.zzawn) && zzach.equals(this.zzawo, zzkv.zzawo)) {
            return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzkv.zzbzd == null || zzkv.zzbzd.isEmpty() : this.zzbzd.equals(zzkv.zzbzd);
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((((getClass().getName().hashCode() + 527) * 31) + zzach.hashCode(this.zzawl)) * 31) + zzach.hashCode(this.zzawm)) * 31) + zzach.hashCode(this.zzawn)) * 31) + zzach.hashCode(this.zzawo)) * 31) + ((this.zzbzd == null || this.zzbzd.isEmpty()) ? 0 : this.zzbzd.hashCode());
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        long[] jArr;
        long[] jArr2;
        int zza = super.zza();
        long[] jArr3 = this.zzawl;
        int i = 0;
        if (jArr3 != null && jArr3.length > 0) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                jArr2 = this.zzawl;
                if (i2 >= jArr2.length) {
                    break;
                }
                i3 += zzacb.zzat(jArr2[i2]);
                i2++;
            }
            zza = zza + i3 + (jArr2.length * 1);
        }
        long[] jArr4 = this.zzawm;
        if (jArr4 != null && jArr4.length > 0) {
            int i4 = 0;
            int i5 = 0;
            while (true) {
                jArr = this.zzawm;
                if (i4 >= jArr.length) {
                    break;
                }
                i5 += zzacb.zzat(jArr[i4]);
                i4++;
            }
            zza = zza + i5 + (jArr.length * 1);
        }
        zzkq[] zzkqArr = this.zzawn;
        if (zzkqArr != null && zzkqArr.length > 0) {
            int i6 = zza;
            int i7 = 0;
            while (true) {
                zzkq[] zzkqArr2 = this.zzawn;
                if (i7 >= zzkqArr2.length) {
                    break;
                }
                zzkq zzkq = zzkqArr2[i7];
                if (zzkq != null) {
                    i6 += zzacb.zzb(3, zzkq);
                }
                i7++;
            }
            zza = i6;
        }
        zzkw[] zzkwArr = this.zzawo;
        if (zzkwArr != null && zzkwArr.length > 0) {
            while (true) {
                zzkw[] zzkwArr2 = this.zzawo;
                if (i >= zzkwArr2.length) {
                    break;
                }
                zzkw zzkw = zzkwArr2[i];
                if (zzkw != null) {
                    zza += zzacb.zzb(4, zzkw);
                }
                i++;
            }
        }
        return zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        long[] jArr = this.zzawl;
        int i = 0;
        if (jArr != null && jArr.length > 0) {
            int i2 = 0;
            while (true) {
                long[] jArr2 = this.zzawl;
                if (i2 >= jArr2.length) {
                    break;
                }
                zzacb.zza(1, jArr2[i2]);
                i2++;
            }
        }
        long[] jArr3 = this.zzawm;
        if (jArr3 != null && jArr3.length > 0) {
            int i3 = 0;
            while (true) {
                long[] jArr4 = this.zzawm;
                if (i3 >= jArr4.length) {
                    break;
                }
                zzacb.zza(2, jArr4[i3]);
                i3++;
            }
        }
        zzkq[] zzkqArr = this.zzawn;
        if (zzkqArr != null && zzkqArr.length > 0) {
            int i4 = 0;
            while (true) {
                zzkq[] zzkqArr2 = this.zzawn;
                if (i4 >= zzkqArr2.length) {
                    break;
                }
                zzkq zzkq = zzkqArr2[i4];
                if (zzkq != null) {
                    zzacb.zza(3, zzkq);
                }
                i4++;
            }
        }
        zzkw[] zzkwArr = this.zzawo;
        if (zzkwArr != null && zzkwArr.length > 0) {
            while (true) {
                zzkw[] zzkwArr2 = this.zzawo;
                if (i >= zzkwArr2.length) {
                    break;
                }
                zzkw zzkw = zzkwArr2[i];
                if (zzkw != null) {
                    zzacb.zza(4, zzkw);
                }
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
            if (zzvl == 0) {
                return this;
            }
            if (zzvl != 8) {
                if (zzvl == 10) {
                    i = zzaca.zzaf(zzaca.zzvn());
                    int position = zzaca.getPosition();
                    int i2 = 0;
                    while (zzaca.zzvr() > 0) {
                        zzaca.zzvo();
                        i2++;
                    }
                    zzaca.zzam(position);
                    long[] jArr = this.zzawl;
                    int length = jArr == null ? 0 : jArr.length;
                    long[] jArr2 = new long[(i2 + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzawl, 0, jArr2, 0, length);
                    }
                    while (length < jArr2.length) {
                        jArr2[length] = zzaca.zzvo();
                        length++;
                    }
                    this.zzawl = jArr2;
                } else if (zzvl == 16) {
                    int zzb = zzacm.zzb(zzaca, 16);
                    long[] jArr3 = this.zzawm;
                    int length2 = jArr3 == null ? 0 : jArr3.length;
                    long[] jArr4 = new long[(zzb + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzawm, 0, jArr4, 0, length2);
                    }
                    while (length2 < jArr4.length - 1) {
                        jArr4[length2] = zzaca.zzvo();
                        zzaca.zzvl();
                        length2++;
                    }
                    jArr4[length2] = zzaca.zzvo();
                    this.zzawm = jArr4;
                } else if (zzvl == 18) {
                    i = zzaca.zzaf(zzaca.zzvn());
                    int position2 = zzaca.getPosition();
                    int i3 = 0;
                    while (zzaca.zzvr() > 0) {
                        zzaca.zzvo();
                        i3++;
                    }
                    zzaca.zzam(position2);
                    long[] jArr5 = this.zzawm;
                    int length3 = jArr5 == null ? 0 : jArr5.length;
                    long[] jArr6 = new long[(i3 + length3)];
                    if (length3 != 0) {
                        System.arraycopy(this.zzawm, 0, jArr6, 0, length3);
                    }
                    while (length3 < jArr6.length) {
                        jArr6[length3] = zzaca.zzvo();
                        length3++;
                    }
                    this.zzawm = jArr6;
                } else if (zzvl == 26) {
                    int zzb2 = zzacm.zzb(zzaca, 26);
                    zzkq[] zzkqArr = this.zzawn;
                    int length4 = zzkqArr == null ? 0 : zzkqArr.length;
                    zzkq[] zzkqArr2 = new zzkq[(zzb2 + length4)];
                    if (length4 != 0) {
                        System.arraycopy(this.zzawn, 0, zzkqArr2, 0, length4);
                    }
                    while (length4 < zzkqArr2.length - 1) {
                        zzkqArr2[length4] = new zzkq();
                        zzaca.zza(zzkqArr2[length4]);
                        zzaca.zzvl();
                        length4++;
                    }
                    zzkqArr2[length4] = new zzkq();
                    zzaca.zza(zzkqArr2[length4]);
                    this.zzawn = zzkqArr2;
                } else if (zzvl == 34) {
                    int zzb3 = zzacm.zzb(zzaca, 34);
                    zzkw[] zzkwArr = this.zzawo;
                    int length5 = zzkwArr == null ? 0 : zzkwArr.length;
                    zzkw[] zzkwArr2 = new zzkw[(zzb3 + length5)];
                    if (length5 != 0) {
                        System.arraycopy(this.zzawo, 0, zzkwArr2, 0, length5);
                    }
                    while (length5 < zzkwArr2.length - 1) {
                        zzkwArr2[length5] = new zzkw();
                        zzaca.zza(zzkwArr2[length5]);
                        zzaca.zzvl();
                        length5++;
                    }
                    zzkwArr2[length5] = new zzkw();
                    zzaca.zza(zzkwArr2[length5]);
                    this.zzawo = zzkwArr2;
                } else if (!super.zza(zzaca, zzvl)) {
                    return this;
                }
                zzaca.zzal(i);
            } else {
                int zzb4 = zzacm.zzb(zzaca, 8);
                long[] jArr7 = this.zzawl;
                int length6 = jArr7 == null ? 0 : jArr7.length;
                long[] jArr8 = new long[(zzb4 + length6)];
                if (length6 != 0) {
                    System.arraycopy(this.zzawl, 0, jArr8, 0, length6);
                }
                while (length6 < jArr8.length - 1) {
                    jArr8[length6] = zzaca.zzvo();
                    zzaca.zzvl();
                    length6++;
                }
                jArr8[length6] = zzaca.zzvo();
                this.zzawl = jArr8;
            }
        }
    }
}
