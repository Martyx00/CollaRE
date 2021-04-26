package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.api.Api;

public final class zzaca {
    private final byte[] buffer;
    private int zzbtk = 64;
    private int zzbtl = 67108864;
    private int zzbtp;
    private int zzbtr = Api.BaseClientBuilder.API_PRIORITY_OTHER;
    private final int zzbyw;
    private final int zzbyx;
    private int zzbyy;
    private int zzbyz;
    private int zzbza;
    private int zzbzb;

    private zzaca(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.zzbyw = i;
        int i3 = i2 + i;
        this.zzbyy = i3;
        this.zzbyx = i3;
        this.zzbyz = i;
    }

    public static zzaca zza(byte[] bArr, int i, int i2) {
        return new zzaca(bArr, 0, i2);
    }

    private final void zzan(int i) {
        if (i >= 0) {
            int i2 = this.zzbyz;
            int i3 = i2 + i;
            int i4 = this.zzbtr;
            if (i3 > i4) {
                zzan(i4 - i2);
                throw zzaci.zzvw();
            } else if (i <= this.zzbyy - i2) {
                this.zzbyz = i2 + i;
            } else {
                throw zzaci.zzvw();
            }
        } else {
            throw zzaci.zzvx();
        }
    }

    public static zzaca zzi(byte[] bArr) {
        return zza(bArr, 0, bArr.length);
    }

    private final void zztp() {
        this.zzbyy += this.zzbtp;
        int i = this.zzbyy;
        int i2 = this.zzbtr;
        if (i > i2) {
            this.zzbtp = i - i2;
            this.zzbyy = i - this.zzbtp;
            return;
        }
        this.zzbtp = 0;
    }

    private final byte zzvs() {
        int i = this.zzbyz;
        if (i != this.zzbyy) {
            byte[] bArr = this.buffer;
            this.zzbyz = i + 1;
            return bArr[i];
        }
        throw zzaci.zzvw();
    }

    public final int getPosition() {
        return this.zzbyz - this.zzbyw;
    }

    public final String readString() {
        int zzvn = zzvn();
        if (zzvn >= 0) {
            int i = this.zzbyy;
            int i2 = this.zzbyz;
            if (zzvn <= i - i2) {
                String str = new String(this.buffer, i2, zzvn, zzach.UTF_8);
                this.zzbyz += zzvn;
                return str;
            }
            throw zzaci.zzvw();
        }
        throw zzaci.zzvx();
    }

    public final void zza(zzacj zzacj) {
        int zzvn = zzvn();
        if (this.zzbzb < this.zzbtk) {
            int zzaf = zzaf(zzvn);
            this.zzbzb++;
            zzacj.zzb(this);
            zzaj(0);
            this.zzbzb--;
            zzal(zzaf);
            return;
        }
        throw zzaci.zzvz();
    }

    public final void zza(zzacj zzacj, int i) {
        int i2 = this.zzbzb;
        if (i2 < this.zzbtk) {
            this.zzbzb = i2 + 1;
            zzacj.zzb(this);
            zzaj((i << 3) | 4);
            this.zzbzb--;
            return;
        }
        throw zzaci.zzvz();
    }

    public final int zzaf(int i) {
        if (i >= 0) {
            int i2 = i + this.zzbyz;
            int i3 = this.zzbtr;
            if (i2 <= i3) {
                this.zzbtr = i2;
                zztp();
                return i3;
            }
            throw zzaci.zzvw();
        }
        throw zzaci.zzvx();
    }

    public final void zzaj(int i) {
        if (this.zzbza != i) {
            throw new zzaci("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final boolean zzak(int i) {
        int zzvl;
        switch (i & 7) {
            case 0:
                zzvn();
                return true;
            case 1:
                zzvq();
                return true;
            case 2:
                zzan(zzvn());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzvp();
                return true;
            default:
                throw new zzaci("Protocol message tag had invalid wire type.");
        }
        do {
            zzvl = zzvl();
            if (zzvl != 0) {
            }
            zzaj(((i >>> 3) << 3) | 4);
            return true;
        } while (zzak(zzvl));
        zzaj(((i >>> 3) << 3) | 4);
        return true;
    }

    public final void zzal(int i) {
        this.zzbtr = i;
        zztp();
    }

    public final void zzam(int i) {
        zzd(i, this.zzbza);
    }

    public final byte[] zzc(int i, int i2) {
        if (i2 == 0) {
            return zzacm.zzbzz;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buffer, this.zzbyw + i, bArr, 0, i2);
        return bArr;
    }

    /* access modifiers changed from: package-private */
    public final void zzd(int i, int i2) {
        int i3 = this.zzbyz;
        int i4 = this.zzbyw;
        if (i > i3 - i4) {
            StringBuilder sb = new StringBuilder(50);
            sb.append("Position ");
            sb.append(i);
            sb.append(" is beyond current ");
            sb.append(i3 - i4);
            throw new IllegalArgumentException(sb.toString());
        } else if (i >= 0) {
            this.zzbyz = i4 + i;
            this.zzbza = i2;
        } else {
            StringBuilder sb2 = new StringBuilder(24);
            sb2.append("Bad position ");
            sb2.append(i);
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    public final int zzvl() {
        if (this.zzbyz == this.zzbyy) {
            this.zzbza = 0;
            return 0;
        }
        this.zzbza = zzvn();
        int i = this.zzbza;
        if (i != 0) {
            return i;
        }
        throw new zzaci("Protocol message contained an invalid tag (zero).");
    }

    public final boolean zzvm() {
        return zzvn() != 0;
    }

    public final int zzvn() {
        int i;
        byte zzvs = zzvs();
        if (zzvs >= 0) {
            return zzvs;
        }
        int i2 = zzvs & Byte.MAX_VALUE;
        byte zzvs2 = zzvs();
        if (zzvs2 >= 0) {
            i = zzvs2 << 7;
        } else {
            i2 |= (zzvs2 & Byte.MAX_VALUE) << 7;
            byte zzvs3 = zzvs();
            if (zzvs3 >= 0) {
                i = zzvs3 << 14;
            } else {
                i2 |= (zzvs3 & Byte.MAX_VALUE) << 14;
                byte zzvs4 = zzvs();
                if (zzvs4 >= 0) {
                    i = zzvs4 << 21;
                } else {
                    int i3 = i2 | ((zzvs4 & Byte.MAX_VALUE) << 21);
                    byte zzvs5 = zzvs();
                    int i4 = i3 | (zzvs5 << 28);
                    if (zzvs5 >= 0) {
                        return i4;
                    }
                    for (int i5 = 0; i5 < 5; i5++) {
                        if (zzvs() >= 0) {
                            return i4;
                        }
                    }
                    throw zzaci.zzvy();
                }
            }
        }
        return i2 | i;
    }

    public final long zzvo() {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzvs = zzvs();
            j |= ((long) (zzvs & Byte.MAX_VALUE)) << i;
            if ((zzvs & 128) == 0) {
                return j;
            }
        }
        throw zzaci.zzvy();
    }

    public final int zzvp() {
        return (zzvs() & 255) | ((zzvs() & 255) << 8) | ((zzvs() & 255) << 16) | ((zzvs() & 255) << 24);
    }

    public final long zzvq() {
        byte zzvs = zzvs();
        byte zzvs2 = zzvs();
        return ((((long) zzvs2) & 255) << 8) | (((long) zzvs) & 255) | ((((long) zzvs()) & 255) << 16) | ((((long) zzvs()) & 255) << 24) | ((((long) zzvs()) & 255) << 32) | ((((long) zzvs()) & 255) << 40) | ((((long) zzvs()) & 255) << 48) | ((((long) zzvs()) & 255) << 56);
    }

    public final int zzvr() {
        int i = this.zzbtr;
        if (i == Integer.MAX_VALUE) {
            return -1;
        }
        return i - this.zzbyz;
    }
}
