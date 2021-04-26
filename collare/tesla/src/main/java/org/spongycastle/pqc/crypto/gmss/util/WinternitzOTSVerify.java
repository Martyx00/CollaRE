package org.spongycastle.pqc.crypto.gmss.util;

import org.spongycastle.crypto.Digest;

public class WinternitzOTSVerify {
    private Digest messDigestOTS;
    private int w;

    public int getLog(int i) {
        int i2 = 1;
        int i3 = 2;
        while (i3 < i) {
            i3 <<= 1;
            i2++;
        }
        return i2;
    }

    public WinternitzOTSVerify(Digest digest, int i) {
        this.w = i;
        this.messDigestOTS = digest;
    }

    public int getSignatureLength() {
        int digestSize = this.messDigestOTS.getDigestSize();
        int i = this.w;
        int i2 = ((digestSize << 3) + (i - 1)) / i;
        int log = getLog((i2 << i) + 1);
        int i3 = this.w;
        return digestSize * (i2 + (((log + i3) - 1) / i3));
    }

    public byte[] Verify(byte[] bArr, byte[] bArr2) {
        byte[] bArr3;
        int i;
        byte[] bArr4 = bArr2;
        int digestSize = this.messDigestOTS.getDigestSize();
        byte[] bArr5 = new byte[digestSize];
        this.messDigestOTS.update(bArr, 0, bArr.length);
        byte[] bArr6 = new byte[this.messDigestOTS.getDigestSize()];
        this.messDigestOTS.doFinal(bArr6, 0);
        int i2 = digestSize << 3;
        int i3 = this.w;
        int i4 = ((i3 - 1) + i2) / i3;
        int log = getLog((i4 << i3) + 1);
        int i5 = this.w;
        int i6 = ((((log + i5) - 1) / i5) + i4) * digestSize;
        if (i6 != bArr4.length) {
            return null;
        }
        byte[] bArr7 = new byte[i6];
        int i7 = 8;
        if (8 % i5 == 0) {
            int i8 = 8 / i5;
            int i9 = (1 << i5) - 1;
            byte[] bArr8 = new byte[digestSize];
            int i10 = 0;
            int i11 = 0;
            int i12 = 0;
            while (i10 < bArr6.length) {
                byte[] bArr9 = bArr8;
                int i13 = i12;
                int i14 = i11;
                int i15 = 0;
                while (i15 < i8) {
                    int i16 = bArr6[i10] & i9;
                    i14 += i16;
                    int i17 = i13 * digestSize;
                    System.arraycopy(bArr4, i17, bArr9, 0, digestSize);
                    while (i16 < i9) {
                        this.messDigestOTS.update(bArr9, 0, bArr9.length);
                        bArr9 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(bArr9, 0);
                        i16++;
                        i14 = i14;
                    }
                    System.arraycopy(bArr9, 0, bArr7, i17, digestSize);
                    bArr6[i10] = (byte) (bArr6[i10] >>> this.w);
                    i13++;
                    i15++;
                    i8 = i8;
                    bArr4 = bArr2;
                }
                i10++;
                i11 = i14;
                i12 = i13;
                bArr8 = bArr9;
                bArr4 = bArr2;
            }
            int i18 = (i4 << this.w) - i11;
            int i19 = 0;
            while (i19 < log) {
                int i20 = i12 * digestSize;
                System.arraycopy(bArr2, i20, bArr8, 0, digestSize);
                for (int i21 = i18 & i9; i21 < i9; i21++) {
                    this.messDigestOTS.update(bArr8, 0, bArr8.length);
                    bArr8 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr8, 0);
                }
                System.arraycopy(bArr8, 0, bArr7, i20, digestSize);
                int i22 = this.w;
                i18 >>>= i22;
                i12++;
                i19 += i22;
            }
            bArr3 = bArr7;
        } else if (i5 < 8) {
            int i23 = digestSize / i5;
            int i24 = (1 << i5) - 1;
            byte[] bArr10 = new byte[digestSize];
            int i25 = 0;
            int i26 = 0;
            int i27 = 0;
            int i28 = 0;
            while (i25 < i23) {
                int i29 = i26;
                long j = 0;
                for (int i30 = 0; i30 < this.w; i30++) {
                    j ^= (long) ((bArr6[i29] & 255) << (i30 << 3));
                    i29++;
                }
                byte[] bArr11 = bArr10;
                int i31 = 0;
                while (i31 < i7) {
                    int i32 = (int) (j & ((long) i24));
                    i27 += i32;
                    int i33 = i28 * digestSize;
                    System.arraycopy(bArr4, i33, bArr11, 0, digestSize);
                    while (i32 < i24) {
                        this.messDigestOTS.update(bArr11, 0, bArr11.length);
                        bArr11 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(bArr11, 0);
                        i32++;
                    }
                    System.arraycopy(bArr11, 0, bArr7, i33, digestSize);
                    j >>>= this.w;
                    i28++;
                    i31++;
                    i25 = i25;
                    i7 = 8;
                }
                i25++;
                bArr10 = bArr11;
                i26 = i29;
                i7 = 8;
            }
            int i34 = digestSize % this.w;
            long j2 = 0;
            for (int i35 = 0; i35 < i34; i35++) {
                j2 ^= (long) ((bArr6[i26] & 255) << (i35 << 3));
                i26++;
            }
            int i36 = i34 << 3;
            byte[] bArr12 = bArr10;
            int i37 = 0;
            while (i37 < i36) {
                int i38 = (int) (j2 & ((long) i24));
                i27 += i38;
                int i39 = i28 * digestSize;
                System.arraycopy(bArr4, i39, bArr12, 0, digestSize);
                while (i38 < i24) {
                    this.messDigestOTS.update(bArr12, 0, bArr12.length);
                    bArr12 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr12, 0);
                    i38++;
                }
                System.arraycopy(bArr12, 0, bArr7, i39, digestSize);
                int i40 = this.w;
                j2 >>>= i40;
                i28++;
                i37 += i40;
            }
            int i41 = (i4 << this.w) - i27;
            int i42 = 0;
            while (i42 < log) {
                int i43 = i28 * digestSize;
                System.arraycopy(bArr4, i43, bArr12, 0, digestSize);
                for (int i44 = i41 & i24; i44 < i24; i44++) {
                    this.messDigestOTS.update(bArr12, 0, bArr12.length);
                    bArr12 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr12, 0);
                }
                System.arraycopy(bArr12, 0, bArr7, i43, digestSize);
                int i45 = this.w;
                i41 >>>= i45;
                i28++;
                i42 += i45;
            }
            bArr3 = bArr7;
        } else if (i5 < 57) {
            int i46 = i2 - i5;
            int i47 = (1 << i5) - 1;
            byte[] bArr13 = new byte[digestSize];
            int i48 = 0;
            int i49 = 0;
            int i50 = 0;
            while (i48 <= i46) {
                int i51 = i48 >>> 3;
                int i52 = i48 % 8;
                int i53 = i48 + this.w;
                int i54 = (i53 + 7) >>> 3;
                int i55 = 0;
                long j3 = 0;
                while (i51 < i54) {
                    j3 ^= (long) ((bArr6[i51] & 255) << (i55 << 3));
                    i55++;
                    i51++;
                    log = log;
                    i46 = i46;
                }
                long j4 = (long) i47;
                long j5 = (j3 >>> i52) & j4;
                i49 = (int) (((long) i49) + j5);
                int i56 = i50 * digestSize;
                System.arraycopy(bArr4, i56, bArr13, 0, digestSize);
                while (j5 < j4) {
                    this.messDigestOTS.update(bArr13, 0, bArr13.length);
                    bArr13 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr13, 0);
                    j5++;
                    j4 = j4;
                }
                System.arraycopy(bArr13, 0, bArr7, i56, digestSize);
                i50++;
                i4 = i4;
                i47 = i47;
                i48 = i53;
                log = log;
                i46 = i46;
            }
            int i57 = i48 >>> 3;
            if (i57 < digestSize) {
                int i58 = i48 % 8;
                int i59 = 0;
                long j6 = 0;
                while (i57 < digestSize) {
                    j6 ^= (long) ((bArr6[i57] & 255) << (i59 << 3));
                    i59++;
                    i57++;
                }
                i = i47;
                long j7 = (long) i;
                long j8 = (j6 >>> i58) & j7;
                i49 = (int) (((long) i49) + j8);
                int i60 = i50 * digestSize;
                System.arraycopy(bArr4, i60, bArr13, 0, digestSize);
                while (j8 < j7) {
                    this.messDigestOTS.update(bArr13, 0, bArr13.length);
                    bArr13 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr13, 0);
                    j8++;
                    j7 = j7;
                }
                System.arraycopy(bArr13, 0, bArr7, i60, digestSize);
                i50++;
            } else {
                i = i47;
            }
            int i61 = (i4 << this.w) - i49;
            int i62 = 0;
            while (i62 < log) {
                int i63 = i50 * digestSize;
                System.arraycopy(bArr4, i63, bArr13, 0, digestSize);
                for (long j9 = (long) (i61 & i); j9 < ((long) i); j9++) {
                    this.messDigestOTS.update(bArr13, 0, bArr13.length);
                    bArr13 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr13, 0);
                }
                System.arraycopy(bArr13, 0, bArr7, i63, digestSize);
                int i64 = this.w;
                i61 >>>= i64;
                i50++;
                i62 += i64;
                bArr7 = bArr7;
            }
            bArr3 = bArr7;
        } else {
            bArr3 = bArr7;
        }
        byte[] bArr14 = new byte[digestSize];
        this.messDigestOTS.update(bArr3, 0, bArr3.length);
        byte[] bArr15 = new byte[this.messDigestOTS.getDigestSize()];
        this.messDigestOTS.doFinal(bArr15, 0);
        return bArr15;
    }
}
