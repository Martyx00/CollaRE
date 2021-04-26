package org.spongycastle.math.raw;

import java.math.BigInteger;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import org.spongycastle.util.Pack;

public abstract class Nat256 {
    private static final long M = 4294967295L;

    public static int add(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (((long) iArr[0]) & M) + (((long) iArr2[0]) & M) + 0;
        iArr3[0] = (int) j;
        long j2 = (j >>> 32) + (((long) iArr[1]) & M) + (((long) iArr2[1]) & M);
        iArr3[1] = (int) j2;
        long j3 = (j2 >>> 32) + (((long) iArr[2]) & M) + (((long) iArr2[2]) & M);
        iArr3[2] = (int) j3;
        long j4 = (j3 >>> 32) + (((long) iArr[3]) & M) + (((long) iArr2[3]) & M);
        iArr3[3] = (int) j4;
        long j5 = (j4 >>> 32) + (((long) iArr[4]) & M) + (((long) iArr2[4]) & M);
        iArr3[4] = (int) j5;
        long j6 = (j5 >>> 32) + (((long) iArr[5]) & M) + (((long) iArr2[5]) & M);
        iArr3[5] = (int) j6;
        long j7 = (j6 >>> 32) + (((long) iArr[6]) & M) + (((long) iArr2[6]) & M);
        iArr3[6] = (int) j7;
        long j8 = (j7 >>> 32) + (((long) iArr[7]) & M) + (((long) iArr2[7]) & M);
        iArr3[7] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int add(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        long j = (((long) iArr[i + 0]) & M) + (((long) iArr2[i2 + 0]) & M) + 0;
        iArr3[i3 + 0] = (int) j;
        long j2 = (j >>> 32) + (((long) iArr[i + 1]) & M) + (((long) iArr2[i2 + 1]) & M);
        iArr3[i3 + 1] = (int) j2;
        long j3 = (j2 >>> 32) + (((long) iArr[i + 2]) & M) + (((long) iArr2[i2 + 2]) & M);
        iArr3[i3 + 2] = (int) j3;
        long j4 = (j3 >>> 32) + (((long) iArr[i + 3]) & M) + (((long) iArr2[i2 + 3]) & M);
        iArr3[i3 + 3] = (int) j4;
        long j5 = (j4 >>> 32) + (((long) iArr[i + 4]) & M) + (((long) iArr2[i2 + 4]) & M);
        iArr3[i3 + 4] = (int) j5;
        long j6 = (j5 >>> 32) + (((long) iArr[i + 5]) & M) + (((long) iArr2[i2 + 5]) & M);
        iArr3[i3 + 5] = (int) j6;
        long j7 = (j6 >>> 32) + (((long) iArr[i + 6]) & M) + (((long) iArr2[i2 + 6]) & M);
        iArr3[i3 + 6] = (int) j7;
        long j8 = (j7 >>> 32) + (((long) iArr[i + 7]) & M) + (((long) iArr2[i2 + 7]) & M);
        iArr3[i3 + 7] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int addBothTo(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (((long) iArr[0]) & M) + (((long) iArr2[0]) & M) + (((long) iArr3[0]) & M) + 0;
        iArr3[0] = (int) j;
        long j2 = (j >>> 32) + (((long) iArr[1]) & M) + (((long) iArr2[1]) & M) + (((long) iArr3[1]) & M);
        iArr3[1] = (int) j2;
        long j3 = (j2 >>> 32) + (((long) iArr[2]) & M) + (((long) iArr2[2]) & M) + (((long) iArr3[2]) & M);
        iArr3[2] = (int) j3;
        long j4 = (j3 >>> 32) + (((long) iArr[3]) & M) + (((long) iArr2[3]) & M) + (((long) iArr3[3]) & M);
        iArr3[3] = (int) j4;
        long j5 = (j4 >>> 32) + (((long) iArr[4]) & M) + (((long) iArr2[4]) & M) + (((long) iArr3[4]) & M);
        iArr3[4] = (int) j5;
        long j6 = (j5 >>> 32) + (((long) iArr[5]) & M) + (((long) iArr2[5]) & M) + (((long) iArr3[5]) & M);
        iArr3[5] = (int) j6;
        long j7 = (j6 >>> 32) + (((long) iArr[6]) & M) + (((long) iArr2[6]) & M) + (((long) iArr3[6]) & M);
        iArr3[6] = (int) j7;
        long j8 = (j7 >>> 32) + (((long) iArr[7]) & M) + (((long) iArr2[7]) & M) + (((long) iArr3[7]) & M);
        iArr3[7] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int addBothTo(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        int i4 = i3 + 0;
        long j = (((long) iArr[i + 0]) & M) + (((long) iArr2[i2 + 0]) & M) + (((long) iArr3[i4]) & M) + 0;
        iArr3[i4] = (int) j;
        int i5 = i3 + 1;
        long j2 = (j >>> 32) + (((long) iArr[i + 1]) & M) + (((long) iArr2[i2 + 1]) & M) + (((long) iArr3[i5]) & M);
        iArr3[i5] = (int) j2;
        int i6 = i3 + 2;
        long j3 = (j2 >>> 32) + (((long) iArr[i + 2]) & M) + (((long) iArr2[i2 + 2]) & M) + (((long) iArr3[i6]) & M);
        iArr3[i6] = (int) j3;
        int i7 = i3 + 3;
        long j4 = (j3 >>> 32) + (((long) iArr[i + 3]) & M) + (((long) iArr2[i2 + 3]) & M) + (((long) iArr3[i7]) & M);
        iArr3[i7] = (int) j4;
        int i8 = i3 + 4;
        long j5 = (j4 >>> 32) + (((long) iArr[i + 4]) & M) + (((long) iArr2[i2 + 4]) & M) + (((long) iArr3[i8]) & M);
        iArr3[i8] = (int) j5;
        int i9 = i3 + 5;
        long j6 = (j5 >>> 32) + (((long) iArr[i + 5]) & M) + (((long) iArr2[i2 + 5]) & M) + (((long) iArr3[i9]) & M);
        iArr3[i9] = (int) j6;
        int i10 = i3 + 6;
        long j7 = (j6 >>> 32) + (((long) iArr[i + 6]) & M) + (((long) iArr2[i2 + 6]) & M) + (((long) iArr3[i10]) & M);
        iArr3[i10] = (int) j7;
        int i11 = i3 + 7;
        long j8 = (j7 >>> 32) + (((long) iArr[i + 7]) & M) + (((long) iArr2[i2 + 7]) & M) + (((long) iArr3[i11]) & M);
        iArr3[i11] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int addTo(int[] iArr, int[] iArr2) {
        long j = (((long) iArr[0]) & M) + (((long) iArr2[0]) & M) + 0;
        iArr2[0] = (int) j;
        long j2 = (j >>> 32) + (((long) iArr[1]) & M) + (((long) iArr2[1]) & M);
        iArr2[1] = (int) j2;
        long j3 = (j2 >>> 32) + (((long) iArr[2]) & M) + (((long) iArr2[2]) & M);
        iArr2[2] = (int) j3;
        long j4 = (j3 >>> 32) + (((long) iArr[3]) & M) + (((long) iArr2[3]) & M);
        iArr2[3] = (int) j4;
        long j5 = (j4 >>> 32) + (((long) iArr[4]) & M) + (((long) iArr2[4]) & M);
        iArr2[4] = (int) j5;
        long j6 = (j5 >>> 32) + (((long) iArr[5]) & M) + (((long) iArr2[5]) & M);
        iArr2[5] = (int) j6;
        long j7 = (j6 >>> 32) + (((long) iArr[6]) & M) + (((long) iArr2[6]) & M);
        iArr2[6] = (int) j7;
        long j8 = (j7 >>> 32) + (((long) iArr[7]) & M) + (M & ((long) iArr2[7]));
        iArr2[7] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int addTo(int[] iArr, int i, int[] iArr2, int i2, int i3) {
        int i4 = i2 + 0;
        long j = (((long) i3) & M) + (((long) iArr[i + 0]) & M) + (((long) iArr2[i4]) & M);
        iArr2[i4] = (int) j;
        int i5 = i2 + 1;
        long j2 = (j >>> 32) + (((long) iArr[i + 1]) & M) + (((long) iArr2[i5]) & M);
        iArr2[i5] = (int) j2;
        int i6 = i2 + 2;
        long j3 = (j2 >>> 32) + (((long) iArr[i + 2]) & M) + (((long) iArr2[i6]) & M);
        iArr2[i6] = (int) j3;
        int i7 = i2 + 3;
        long j4 = (j3 >>> 32) + (((long) iArr[i + 3]) & M) + (((long) iArr2[i7]) & M);
        iArr2[i7] = (int) j4;
        int i8 = i2 + 4;
        long j5 = (j4 >>> 32) + (((long) iArr[i + 4]) & M) + (((long) iArr2[i8]) & M);
        iArr2[i8] = (int) j5;
        int i9 = i2 + 5;
        long j6 = (j5 >>> 32) + (((long) iArr[i + 5]) & M) + (((long) iArr2[i9]) & M);
        iArr2[i9] = (int) j6;
        int i10 = i2 + 6;
        long j7 = (j6 >>> 32) + (((long) iArr[i + 6]) & M) + (((long) iArr2[i10]) & M);
        iArr2[i10] = (int) j7;
        int i11 = i2 + 7;
        long j8 = (j7 >>> 32) + (((long) iArr[i + 7]) & M) + (M & ((long) iArr2[i11]));
        iArr2[i11] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int addToEachOther(int[] iArr, int i, int[] iArr2, int i2) {
        int i3 = i + 0;
        int i4 = i2 + 0;
        long j = (((long) iArr[i3]) & M) + (((long) iArr2[i4]) & M) + 0;
        int i5 = (int) j;
        iArr[i3] = i5;
        iArr2[i4] = i5;
        int i6 = i + 1;
        int i7 = i2 + 1;
        long j2 = (j >>> 32) + (((long) iArr[i6]) & M) + (((long) iArr2[i7]) & M);
        int i8 = (int) j2;
        iArr[i6] = i8;
        iArr2[i7] = i8;
        int i9 = i + 2;
        int i10 = i2 + 2;
        long j3 = (j2 >>> 32) + (((long) iArr[i9]) & M) + (((long) iArr2[i10]) & M);
        int i11 = (int) j3;
        iArr[i9] = i11;
        iArr2[i10] = i11;
        int i12 = i + 3;
        int i13 = i2 + 3;
        long j4 = (j3 >>> 32) + (((long) iArr[i12]) & M) + (((long) iArr2[i13]) & M);
        int i14 = (int) j4;
        iArr[i12] = i14;
        iArr2[i13] = i14;
        int i15 = i + 4;
        int i16 = i2 + 4;
        long j5 = (j4 >>> 32) + (((long) iArr[i15]) & M) + (((long) iArr2[i16]) & M);
        int i17 = (int) j5;
        iArr[i15] = i17;
        iArr2[i16] = i17;
        int i18 = i + 5;
        int i19 = i2 + 5;
        long j6 = (j5 >>> 32) + (((long) iArr[i18]) & M) + (((long) iArr2[i19]) & M);
        int i20 = (int) j6;
        iArr[i18] = i20;
        iArr2[i19] = i20;
        int i21 = i + 6;
        int i22 = i2 + 6;
        long j7 = (j6 >>> 32) + (((long) iArr[i21]) & M) + (((long) iArr2[i22]) & M);
        int i23 = (int) j7;
        iArr[i21] = i23;
        iArr2[i22] = i23;
        int i24 = i + 7;
        int i25 = i2 + 7;
        long j8 = (j7 >>> 32) + (((long) iArr[i24]) & M) + (M & ((long) iArr2[i25]));
        int i26 = (int) j8;
        iArr[i24] = i26;
        iArr2[i25] = i26;
        return (int) (j8 >>> 32);
    }

    public static void copy(int[] iArr, int[] iArr2) {
        iArr2[0] = iArr[0];
        iArr2[1] = iArr[1];
        iArr2[2] = iArr[2];
        iArr2[3] = iArr[3];
        iArr2[4] = iArr[4];
        iArr2[5] = iArr[5];
        iArr2[6] = iArr[6];
        iArr2[7] = iArr[7];
    }

    public static void copy64(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0];
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
        jArr2[3] = jArr[3];
    }

    public static int[] create() {
        return new int[8];
    }

    public static long[] create64() {
        return new long[4];
    }

    public static int[] createExt() {
        return new int[16];
    }

    public static long[] createExt64() {
        return new long[8];
    }

    public static boolean diff(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        boolean gte = gte(iArr, i, iArr2, i2);
        if (gte) {
            sub(iArr, i, iArr2, i2, iArr3, i3);
        } else {
            sub(iArr2, i2, iArr, i, iArr3, i3);
        }
        return gte;
    }

    public static boolean eq(int[] iArr, int[] iArr2) {
        for (int i = 7; i >= 0; i--) {
            if (iArr[i] != iArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean eq64(long[] jArr, long[] jArr2) {
        for (int i = 3; i >= 0; i--) {
            if (jArr[i] != jArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 256) {
            throw new IllegalArgumentException();
        }
        int[] create = create();
        int i = 0;
        while (bigInteger.signum() != 0) {
            create[i] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
            i++;
        }
        return create;
    }

    public static long[] fromBigInteger64(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 256) {
            throw new IllegalArgumentException();
        }
        long[] create64 = create64();
        int i = 0;
        while (bigInteger.signum() != 0) {
            create64[i] = bigInteger.longValue();
            bigInteger = bigInteger.shiftRight(64);
            i++;
        }
        return create64;
    }

    public static int getBit(int[] iArr, int i) {
        if (i == 0) {
            return iArr[0] & 1;
        }
        if ((i & 255) != i) {
            return 0;
        }
        return (iArr[i >>> 5] >>> (i & 31)) & 1;
    }

    public static boolean gte(int[] iArr, int[] iArr2) {
        for (int i = 7; i >= 0; i--) {
            int i2 = iArr[i] ^ PKIFailureInfo.systemUnavail;
            int i3 = Integer.MIN_VALUE ^ iArr2[i];
            if (i2 < i3) {
                return false;
            }
            if (i2 > i3) {
                return true;
            }
        }
        return true;
    }

    public static boolean gte(int[] iArr, int i, int[] iArr2, int i2) {
        for (int i3 = 7; i3 >= 0; i3--) {
            int i4 = iArr[i + i3] ^ PKIFailureInfo.systemUnavail;
            int i5 = Integer.MIN_VALUE ^ iArr2[i2 + i3];
            if (i4 < i5) {
                return false;
            }
            if (i4 > i5) {
                return true;
            }
        }
        return true;
    }

    public static boolean isOne(int[] iArr) {
        if (iArr[0] != 1) {
            return false;
        }
        for (int i = 1; i < 8; i++) {
            if (iArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOne64(long[] jArr) {
        if (jArr[0] != 1) {
            return false;
        }
        for (int i = 1; i < 4; i++) {
            if (jArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero(int[] iArr) {
        for (int i = 0; i < 8; i++) {
            if (iArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero64(long[] jArr) {
        for (int i = 0; i < 4; i++) {
            if (jArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void mul(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = ((long) iArr2[0]) & M;
        long j2 = ((long) iArr2[1]) & M;
        long j3 = ((long) iArr2[2]) & M;
        long j4 = ((long) iArr2[3]) & M;
        long j5 = ((long) iArr2[4]) & M;
        long j6 = ((long) iArr2[5]) & M;
        long j7 = ((long) iArr2[6]) & M;
        long j8 = ((long) iArr2[7]) & M;
        long j9 = ((long) iArr[0]) & M;
        long j10 = (j9 * j) + 0;
        iArr3[0] = (int) j10;
        long j11 = (j10 >>> 32) + (j9 * j2);
        iArr3[1] = (int) j11;
        long j12 = (j11 >>> 32) + (j9 * j3);
        iArr3[2] = (int) j12;
        long j13 = (j12 >>> 32) + (j9 * j4);
        iArr3[3] = (int) j13;
        long j14 = (j13 >>> 32) + (j9 * j5);
        iArr3[4] = (int) j14;
        long j15 = (j14 >>> 32) + (j9 * j6);
        iArr3[5] = (int) j15;
        long j16 = (j15 >>> 32) + (j9 * j7);
        iArr3[6] = (int) j16;
        long j17 = (j16 >>> 32) + (j9 * j8);
        iArr3[7] = (int) j17;
        int i = (int) (j17 >>> 32);
        iArr3[8] = i;
        int i2 = 1;
        for (int i3 = 8; i2 < i3; i3 = 8) {
            long j18 = ((long) iArr[i2]) & M;
            int i4 = i2 + 0;
            long j19 = (j18 * j) + (((long) iArr3[i4]) & M) + 0;
            iArr3[i4] = (int) j19;
            int i5 = i2 + 1;
            long j20 = (j19 >>> 32) + (j18 * j2) + (((long) iArr3[i5]) & M);
            iArr3[i5] = (int) j20;
            int i6 = i2 + 2;
            long j21 = (j20 >>> 32) + (j18 * j3) + (((long) iArr3[i6]) & M);
            iArr3[i6] = (int) j21;
            int i7 = i2 + 3;
            long j22 = (j21 >>> 32) + (j18 * j4) + (((long) iArr3[i7]) & M);
            iArr3[i7] = (int) j22;
            int i8 = i2 + 4;
            long j23 = (j22 >>> 32) + (j18 * j5) + (((long) iArr3[i8]) & M);
            iArr3[i8] = (int) j23;
            int i9 = i2 + 5;
            long j24 = (j23 >>> 32) + (j18 * j6) + (((long) iArr3[i9]) & M);
            iArr3[i9] = (int) j24;
            int i10 = i2 + 6;
            long j25 = (j24 >>> 32) + (j18 * j7) + (((long) iArr3[i10]) & M);
            iArr3[i10] = (int) j25;
            int i11 = i2 + 7;
            long j26 = (j25 >>> 32) + (j18 * j8) + (((long) iArr3[i11]) & M);
            iArr3[i11] = (int) j26;
            iArr3[i2 + 8] = (int) (j26 >>> 32);
            j = j;
            i2 = i5;
        }
    }

    public static void mul(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        long j = ((long) iArr2[i2 + 0]) & M;
        long j2 = ((long) iArr2[i2 + 1]) & M;
        long j3 = ((long) iArr2[i2 + 2]) & M;
        long j4 = ((long) iArr2[i2 + 3]) & M;
        long j5 = ((long) iArr2[i2 + 4]) & M;
        long j6 = ((long) iArr2[i2 + 5]) & M;
        long j7 = ((long) iArr2[i2 + 6]) & M;
        long j8 = ((long) iArr2[i2 + 7]) & M;
        long j9 = ((long) iArr[i + 0]) & M;
        long j10 = (j9 * j) + 0;
        iArr3[i3 + 0] = (int) j10;
        long j11 = (j10 >>> 32) + (j9 * j2);
        iArr3[i3 + 1] = (int) j11;
        long j12 = (j11 >>> 32) + (j9 * j3);
        iArr3[i3 + 2] = (int) j12;
        long j13 = (j12 >>> 32) + (j9 * j4);
        iArr3[i3 + 3] = (int) j13;
        long j14 = (j13 >>> 32) + (j9 * j5);
        iArr3[i3 + 4] = (int) j14;
        long j15 = (j14 >>> 32) + (j9 * j6);
        iArr3[i3 + 5] = (int) j15;
        long j16 = (j15 >>> 32) + (j9 * j7);
        iArr3[i3 + 6] = (int) j16;
        long j17 = (j16 >>> 32) + (j9 * j8);
        iArr3[i3 + 7] = (int) j17;
        iArr3[i3 + 8] = (int) (j17 >>> 32);
        int i4 = 1;
        int i5 = i3;
        int i6 = 1;
        while (i6 < 8) {
            i5 += i4;
            long j18 = ((long) iArr[i + i6]) & M;
            int i7 = i5 + 0;
            long j19 = (j18 * j) + (((long) iArr3[i7]) & M) + 0;
            iArr3[i7] = (int) j19;
            int i8 = i5 + 1;
            long j20 = (j19 >>> 32) + (j18 * j2) + (((long) iArr3[i8]) & M);
            iArr3[i8] = (int) j20;
            int i9 = i5 + 2;
            long j21 = (j20 >>> 32) + (j18 * j3) + (((long) iArr3[i9]) & M);
            iArr3[i9] = (int) j21;
            int i10 = i5 + 3;
            long j22 = (j21 >>> 32) + (j18 * j4) + (((long) iArr3[i10]) & M);
            iArr3[i10] = (int) j22;
            int i11 = i5 + 4;
            long j23 = (j22 >>> 32) + (j18 * j5) + (((long) iArr3[i11]) & M);
            iArr3[i11] = (int) j23;
            int i12 = i5 + 5;
            long j24 = (j23 >>> 32) + (j18 * j6) + (((long) iArr3[i12]) & M);
            iArr3[i12] = (int) j24;
            int i13 = i5 + 6;
            long j25 = (j24 >>> 32) + (j18 * j7) + (((long) iArr3[i13]) & M);
            iArr3[i13] = (int) j25;
            int i14 = i5 + 7;
            long j26 = (j25 >>> 32) + (j18 * j8) + (((long) iArr3[i14]) & M);
            iArr3[i14] = (int) j26;
            iArr3[i5 + 8] = (int) (j26 >>> 32);
            i6++;
            j6 = j6;
            j3 = j3;
            i4 = 1;
        }
    }

    public static int mulAddTo(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = M;
        long j2 = ((long) iArr2[0]) & M;
        long j3 = ((long) iArr2[1]) & M;
        long j4 = ((long) iArr2[2]) & M;
        long j5 = ((long) iArr2[3]) & M;
        long j6 = ((long) iArr2[4]) & M;
        long j7 = ((long) iArr2[5]) & M;
        long j8 = ((long) iArr2[6]) & M;
        long j9 = ((long) iArr2[7]) & M;
        long j10 = 0;
        int i = 0;
        while (i < 8) {
            long j11 = ((long) iArr[i]) & j;
            int i2 = i + 0;
            long j12 = (j11 * j2) + (((long) iArr3[i2]) & j) + 0;
            iArr3[i2] = (int) j12;
            int i3 = i + 1;
            long j13 = (j12 >>> 32) + (j11 * j3) + (((long) iArr3[i3]) & j);
            iArr3[i3] = (int) j13;
            int i4 = i + 2;
            long j14 = (j13 >>> 32) + (j11 * j4) + (((long) iArr3[i4]) & j);
            iArr3[i4] = (int) j14;
            int i5 = i + 3;
            long j15 = (j14 >>> 32) + (j11 * j5) + (((long) iArr3[i5]) & j);
            iArr3[i5] = (int) j15;
            int i6 = i + 4;
            long j16 = (j15 >>> 32) + (j11 * j6) + (((long) iArr3[i6]) & j);
            iArr3[i6] = (int) j16;
            int i7 = i + 5;
            long j17 = (j16 >>> 32) + (j11 * j7) + (((long) iArr3[i7]) & j);
            iArr3[i7] = (int) j17;
            int i8 = i + 6;
            long j18 = (j17 >>> 32) + (j11 * j8) + (((long) iArr3[i8]) & j);
            iArr3[i8] = (int) j18;
            int i9 = i + 7;
            long j19 = (j18 >>> 32) + (j11 * j9) + (((long) iArr3[i9]) & j);
            iArr3[i9] = (int) j19;
            int i10 = i + 8;
            long j20 = (j19 >>> 32) + j10 + (((long) iArr3[i10]) & j);
            iArr3[i10] = (int) j20;
            j10 = j20 >>> 32;
            i = i3;
            j9 = j9;
            j7 = j7;
            j3 = j3;
            j = M;
        }
        return (int) j10;
    }

    public static int mulAddTo(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        long j = ((long) iArr2[i2 + 0]) & M;
        long j2 = ((long) iArr2[i2 + 1]) & M;
        long j3 = ((long) iArr2[i2 + 2]) & M;
        long j4 = ((long) iArr2[i2 + 3]) & M;
        long j5 = ((long) iArr2[i2 + 4]) & M;
        long j6 = ((long) iArr2[i2 + 5]) & M;
        long j7 = ((long) iArr2[i2 + 6]) & M;
        long j8 = ((long) iArr2[i2 + 7]) & M;
        int i4 = i3;
        long j9 = 0;
        int i5 = 0;
        while (i5 < 8) {
            long j10 = ((long) iArr[i + i5]) & M;
            int i6 = i4 + 0;
            long j11 = (j10 * j) + (((long) iArr3[i6]) & M) + 0;
            iArr3[i6] = (int) j11;
            int i7 = i4 + 1;
            long j12 = (j11 >>> 32) + (j10 * j2) + (((long) iArr3[i7]) & M);
            iArr3[i7] = (int) j12;
            int i8 = i4 + 2;
            long j13 = (j12 >>> 32) + (j10 * j3) + (((long) iArr3[i8]) & M);
            iArr3[i8] = (int) j13;
            int i9 = i4 + 3;
            long j14 = (j13 >>> 32) + (j10 * j4) + (((long) iArr3[i9]) & M);
            iArr3[i9] = (int) j14;
            int i10 = i4 + 4;
            long j15 = (j14 >>> 32) + (j10 * j5) + (((long) iArr3[i10]) & M);
            iArr3[i10] = (int) j15;
            int i11 = i4 + 5;
            long j16 = (j15 >>> 32) + (j10 * j6) + (((long) iArr3[i11]) & M);
            iArr3[i11] = (int) j16;
            int i12 = i4 + 6;
            long j17 = (j16 >>> 32) + (j10 * j7) + (((long) iArr3[i12]) & M);
            iArr3[i12] = (int) j17;
            int i13 = i4 + 7;
            long j18 = (j17 >>> 32) + (j10 * j8) + (((long) iArr3[i13]) & M);
            iArr3[i13] = (int) j18;
            int i14 = i4 + 8;
            long j19 = (j18 >>> 32) + j9 + (((long) iArr3[i14]) & M);
            iArr3[i14] = (int) j19;
            j9 = j19 >>> 32;
            i5++;
            i4 = i7;
            j = j;
            j2 = j2;
            j3 = j3;
        }
        return (int) j9;
    }

    public static long mul33Add(int i, int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        long j = ((long) i) & M;
        long j2 = ((long) iArr[i2 + 0]) & M;
        long j3 = (j * j2) + (((long) iArr2[i3 + 0]) & M) + 0;
        iArr3[i4 + 0] = (int) j3;
        long j4 = ((long) iArr[i2 + 1]) & M;
        long j5 = (j3 >>> 32) + (j * j4) + j2 + (((long) iArr2[i3 + 1]) & M);
        iArr3[i4 + 1] = (int) j5;
        long j6 = j5 >>> 32;
        long j7 = ((long) iArr[i2 + 2]) & M;
        long j8 = j6 + (j * j7) + j4 + (((long) iArr2[i3 + 2]) & M);
        iArr3[i4 + 2] = (int) j8;
        long j9 = ((long) iArr[i2 + 3]) & M;
        long j10 = (j8 >>> 32) + (j * j9) + j7 + (((long) iArr2[i3 + 3]) & M);
        iArr3[i4 + 3] = (int) j10;
        long j11 = ((long) iArr[i2 + 4]) & M;
        long j12 = (j10 >>> 32) + (j * j11) + j9 + (((long) iArr2[i3 + 4]) & M);
        iArr3[i4 + 4] = (int) j12;
        long j13 = ((long) iArr[i2 + 5]) & M;
        long j14 = (j12 >>> 32) + (j * j13) + j11 + (((long) iArr2[i3 + 5]) & M);
        iArr3[i4 + 5] = (int) j14;
        long j15 = ((long) iArr[i2 + 6]) & M;
        long j16 = (j14 >>> 32) + (j * j15) + j13 + (((long) iArr2[i3 + 6]) & M);
        iArr3[i4 + 6] = (int) j16;
        long j17 = ((long) iArr[i2 + 7]) & M;
        long j18 = (j16 >>> 32) + (j * j17) + j15 + (M & ((long) iArr2[i3 + 7]));
        iArr3[i4 + 7] = (int) j18;
        return (j18 >>> 32) + j17;
    }

    public static int mulByWord(int i, int[] iArr) {
        long j = ((long) i) & M;
        long j2 = ((((long) iArr[0]) & M) * j) + 0;
        iArr[0] = (int) j2;
        long j3 = (j2 >>> 32) + ((((long) iArr[1]) & M) * j);
        iArr[1] = (int) j3;
        long j4 = (j3 >>> 32) + ((((long) iArr[2]) & M) * j);
        iArr[2] = (int) j4;
        long j5 = (j4 >>> 32) + ((((long) iArr[3]) & M) * j);
        iArr[3] = (int) j5;
        long j6 = (j5 >>> 32) + ((((long) iArr[4]) & M) * j);
        iArr[4] = (int) j6;
        long j7 = (j6 >>> 32) + ((((long) iArr[5]) & M) * j);
        iArr[5] = (int) j7;
        long j8 = (j7 >>> 32) + ((((long) iArr[6]) & M) * j);
        iArr[6] = (int) j8;
        long j9 = (j8 >>> 32) + (j * (M & ((long) iArr[7])));
        iArr[7] = (int) j9;
        return (int) (j9 >>> 32);
    }

    public static int mulByWordAddTo(int i, int[] iArr, int[] iArr2) {
        long j = ((long) i) & M;
        long j2 = ((((long) iArr2[0]) & M) * j) + (((long) iArr[0]) & M) + 0;
        iArr2[0] = (int) j2;
        long j3 = (j2 >>> 32) + ((((long) iArr2[1]) & M) * j) + (((long) iArr[1]) & M);
        iArr2[1] = (int) j3;
        long j4 = (j3 >>> 32) + ((((long) iArr2[2]) & M) * j) + (((long) iArr[2]) & M);
        iArr2[2] = (int) j4;
        long j5 = (j4 >>> 32) + ((((long) iArr2[3]) & M) * j) + (((long) iArr[3]) & M);
        iArr2[3] = (int) j5;
        long j6 = (j5 >>> 32) + ((((long) iArr2[4]) & M) * j) + (((long) iArr[4]) & M);
        iArr2[4] = (int) j6;
        long j7 = (j6 >>> 32) + ((((long) iArr2[5]) & M) * j) + (((long) iArr[5]) & M);
        iArr2[5] = (int) j7;
        long j8 = (j7 >>> 32) + ((((long) iArr2[6]) & M) * j) + (((long) iArr[6]) & M);
        iArr2[6] = (int) j8;
        long j9 = (j8 >>> 32) + (j * (((long) iArr2[7]) & M)) + (M & ((long) iArr[7]));
        iArr2[7] = (int) j9;
        return (int) (j9 >>> 32);
    }

    public static int mulWordAddTo(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        long j = ((long) i) & M;
        int i4 = i3 + 0;
        long j2 = ((((long) iArr[i2 + 0]) & M) * j) + (((long) iArr2[i4]) & M) + 0;
        iArr2[i4] = (int) j2;
        int i5 = i3 + 1;
        long j3 = (j2 >>> 32) + ((((long) iArr[i2 + 1]) & M) * j) + (((long) iArr2[i5]) & M);
        iArr2[i5] = (int) j3;
        int i6 = i3 + 2;
        long j4 = (j3 >>> 32) + ((((long) iArr[i2 + 2]) & M) * j) + (((long) iArr2[i6]) & M);
        iArr2[i6] = (int) j4;
        int i7 = i3 + 3;
        long j5 = (j4 >>> 32) + ((((long) iArr[i2 + 3]) & M) * j) + (((long) iArr2[i7]) & M);
        iArr2[i7] = (int) j5;
        int i8 = i3 + 4;
        long j6 = (j5 >>> 32) + ((((long) iArr[i2 + 4]) & M) * j) + (((long) iArr2[i8]) & M);
        iArr2[i8] = (int) j6;
        int i9 = i3 + 5;
        long j7 = (j6 >>> 32) + ((((long) iArr[i2 + 5]) & M) * j) + (((long) iArr2[i9]) & M);
        iArr2[i9] = (int) j7;
        int i10 = i3 + 6;
        long j8 = (j7 >>> 32) + ((((long) iArr[i2 + 6]) & M) * j) + (((long) iArr2[i10]) & M);
        iArr2[i10] = (int) j8;
        int i11 = i3 + 7;
        long j9 = (j8 >>> 32) + (j * (((long) iArr[i2 + 7]) & M)) + (((long) iArr2[i11]) & M);
        iArr2[i11] = (int) j9;
        return (int) (j9 >>> 32);
    }

    public static int mul33DWordAdd(int i, long j, int[] iArr, int i2) {
        long j2 = ((long) i) & M;
        long j3 = j & M;
        int i3 = i2 + 0;
        long j4 = (j2 * j3) + (((long) iArr[i3]) & M) + 0;
        iArr[i3] = (int) j4;
        long j5 = j >>> 32;
        long j6 = (j2 * j5) + j3;
        int i4 = i2 + 1;
        long j7 = (j4 >>> 32) + j6 + (((long) iArr[i4]) & M);
        iArr[i4] = (int) j7;
        int i5 = i2 + 2;
        long j8 = (j7 >>> 32) + j5 + (((long) iArr[i5]) & M);
        iArr[i5] = (int) j8;
        int i6 = i2 + 3;
        long j9 = (j8 >>> 32) + (M & ((long) iArr[i6]));
        iArr[i6] = (int) j9;
        if ((j9 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(8, iArr, i2, 4);
    }

    public static int mul33WordAdd(int i, int i2, int[] iArr, int i3) {
        long j = ((long) i) & M;
        long j2 = ((long) i2) & M;
        int i4 = i3 + 0;
        long j3 = (j * j2) + (((long) iArr[i4]) & M) + 0;
        iArr[i4] = (int) j3;
        int i5 = i3 + 1;
        long j4 = (j3 >>> 32) + j2 + (((long) iArr[i5]) & M);
        iArr[i5] = (int) j4;
        long j5 = j4 >>> 32;
        int i6 = i3 + 2;
        long j6 = j5 + (((long) iArr[i6]) & M);
        iArr[i6] = (int) j6;
        if ((j6 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(8, iArr, i3, 3);
    }

    public static int mulWordDwordAdd(int i, long j, int[] iArr, int i2) {
        long j2 = ((long) i) & M;
        int i3 = i2 + 0;
        long j3 = ((j & M) * j2) + (((long) iArr[i3]) & M) + 0;
        iArr[i3] = (int) j3;
        long j4 = j2 * (j >>> 32);
        int i4 = i2 + 1;
        long j5 = (j3 >>> 32) + j4 + (((long) iArr[i4]) & M);
        iArr[i4] = (int) j5;
        int i5 = i2 + 2;
        long j6 = (j5 >>> 32) + (((long) iArr[i5]) & M);
        iArr[i5] = (int) j6;
        if ((j6 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(8, iArr, i2, 3);
    }

    public static int mulWord(int i, int[] iArr, int[] iArr2, int i2) {
        long j = ((long) i) & M;
        long j2 = 0;
        int i3 = 0;
        do {
            long j3 = j2 + ((((long) iArr[i3]) & M) * j);
            iArr2[i2 + i3] = (int) j3;
            j2 = j3 >>> 32;
            i3++;
        } while (i3 < 8);
        return (int) j2;
    }

    public static void square(int[] iArr, int[] iArr2) {
        long j = ((long) iArr[0]) & M;
        int i = 7;
        int i2 = 16;
        int i3 = 0;
        while (true) {
            int i4 = i - 1;
            long j2 = ((long) iArr[i]) & M;
            long j3 = j2 * j2;
            int i5 = i2 - 1;
            iArr2[i5] = (i3 << 31) | ((int) (j3 >>> 33));
            i2 = i5 - 1;
            iArr2[i2] = (int) (j3 >>> 1);
            int i6 = (int) j3;
            if (i4 <= 0) {
                long j4 = j * j;
                long j5 = (j4 >>> 33) | (((long) (i6 << 31)) & M);
                iArr2[0] = (int) j4;
                long j6 = ((long) iArr[1]) & M;
                long j7 = ((long) iArr2[2]) & M;
                long j8 = j5 + (j6 * j);
                int i7 = (int) j8;
                iArr2[1] = (i7 << 1) | (((int) (j4 >>> 32)) & 1);
                long j9 = j7 + (j8 >>> 32);
                long j10 = ((long) iArr[2]) & M;
                long j11 = ((long) iArr2[3]) & M;
                long j12 = ((long) iArr2[4]) & M;
                long j13 = j9 + (j10 * j);
                int i8 = (int) j13;
                iArr2[2] = (i8 << 1) | (i7 >>> 31);
                long j14 = j11 + (j13 >>> 32) + (j10 * j6);
                long j15 = j12 + (j14 >>> 32);
                long j16 = j14 & M;
                long j17 = ((long) iArr[3]) & M;
                long j18 = (((long) iArr2[5]) & M) + (j15 >>> 32);
                long j19 = j15 & M;
                long j20 = j18 & M;
                long j21 = j16 + (j17 * j);
                int i9 = (int) j21;
                iArr2[3] = (i8 >>> 31) | (i9 << 1);
                int i10 = i9 >>> 31;
                long j22 = j19 + (j21 >>> 32) + (j17 * j6);
                long j23 = j20 + (j22 >>> 32) + (j17 * j10);
                long j24 = j22 & M;
                long j25 = (((long) iArr2[6]) & M) + (j18 >>> 32) + (j23 >>> 32);
                long j26 = j23 & M;
                long j27 = ((long) iArr[4]) & M;
                long j28 = (((long) iArr2[7]) & M) + (j25 >>> 32);
                long j29 = j25 & M;
                long j30 = (((long) iArr2[8]) & M) + (j28 >>> 32);
                long j31 = j28 & M;
                long j32 = j24 + (j27 * j);
                int i11 = (int) j32;
                iArr2[4] = i10 | (i11 << 1);
                int i12 = i11 >>> 31;
                long j33 = j26 + (j32 >>> 32) + (j27 * j6);
                long j34 = j29 + (j33 >>> 32) + (j27 * j10);
                long j35 = j33 & M;
                long j36 = j31 + (j34 >>> 32) + (j27 * j17);
                long j37 = j34 & M;
                long j38 = j30 + (j36 >>> 32);
                long j39 = j36 & M;
                long j40 = ((long) iArr[5]) & M;
                long j41 = (((long) iArr2[9]) & M) + (j38 >>> 32);
                long j42 = j38 & M;
                long j43 = j41 & M;
                long j44 = j35 + (j40 * j);
                int i13 = (int) j44;
                iArr2[5] = i12 | (i13 << 1);
                int i14 = i13 >>> 31;
                long j45 = j37 + (j44 >>> 32) + (j40 * j6);
                long j46 = j39 + (j45 >>> 32) + (j40 * j10);
                long j47 = j45 & M;
                long j48 = j42 + (j46 >>> 32) + (j40 * j17);
                long j49 = j46 & M;
                long j50 = j43 + (j48 >>> 32) + (j40 * j27);
                long j51 = j48 & M;
                long j52 = (((long) iArr2[10]) & M) + (j41 >>> 32) + (j50 >>> 32);
                long j53 = j50 & M;
                long j54 = ((long) iArr[6]) & M;
                long j55 = (((long) iArr2[11]) & M) + (j52 >>> 32);
                long j56 = j52 & M;
                long j57 = (((long) iArr2[12]) & M) + (j55 >>> 32);
                long j58 = j55 & M;
                long j59 = j47 + (j54 * j);
                int i15 = (int) j59;
                iArr2[6] = (i15 << 1) | i14;
                long j60 = j49 + (j59 >>> 32) + (j54 * j6);
                long j61 = j51 + (j60 >>> 32) + (j54 * j10);
                long j62 = j60 & M;
                long j63 = j53 + (j61 >>> 32) + (j54 * j17);
                long j64 = j61 & M;
                long j65 = j56 + (j63 >>> 32) + (j54 * j27);
                long j66 = j63 & M;
                long j67 = j58 + (j65 >>> 32) + (j54 * j40);
                long j68 = j65 & M;
                long j69 = j57 + (j67 >>> 32);
                long j70 = j67 & M;
                long j71 = ((long) iArr[7]) & M;
                long j72 = (((long) iArr2[13]) & M) + (j69 >>> 32);
                long j73 = j69 & M;
                long j74 = j72 & M;
                long j75 = j62 + (j71 * j);
                int i16 = (int) j75;
                iArr2[7] = (i16 << 1) | (i15 >>> 31);
                long j76 = j64 + (j75 >>> 32) + (j71 * j6);
                long j77 = j66 + (j76 >>> 32) + (j71 * j10);
                long j78 = j68 + (j77 >>> 32) + (j71 * j17);
                long j79 = j70 + (j78 >>> 32) + (j71 * j27);
                long j80 = j73 + (j79 >>> 32) + (j71 * j40);
                long j81 = j74 + (j80 >>> 32) + (j71 * j54);
                long j82 = (((long) iArr2[14]) & M) + (j72 >>> 32) + (j81 >>> 32);
                int i17 = (int) j76;
                iArr2[8] = (i17 << 1) | (i16 >>> 31);
                int i18 = i17 >>> 31;
                int i19 = (int) j77;
                iArr2[9] = i18 | (i19 << 1);
                int i20 = i19 >>> 31;
                int i21 = (int) j78;
                iArr2[10] = i20 | (i21 << 1);
                int i22 = i21 >>> 31;
                int i23 = (int) j79;
                iArr2[11] = i22 | (i23 << 1);
                int i24 = i23 >>> 31;
                int i25 = (int) j80;
                iArr2[12] = i24 | (i25 << 1);
                int i26 = i25 >>> 31;
                int i27 = (int) j81;
                iArr2[13] = i26 | (i27 << 1);
                int i28 = i27 >>> 31;
                int i29 = (int) j82;
                iArr2[14] = i28 | (i29 << 1);
                iArr2[15] = (i29 >>> 31) | ((iArr2[15] + ((int) (j82 >>> 32))) << 1);
                return;
            }
            i = i4;
            i3 = i6;
        }
    }

    public static void square(int[] iArr, int i, int[] iArr2, int i2) {
        long j = ((long) iArr[i + 0]) & M;
        int i3 = 16;
        int i4 = 7;
        int i5 = 0;
        while (true) {
            int i6 = i4 - 1;
            long j2 = ((long) iArr[i + i4]) & M;
            long j3 = j2 * j2;
            int i7 = i3 - 1;
            iArr2[i2 + i7] = (i5 << 31) | ((int) (j3 >>> 33));
            i3 = i7 - 1;
            iArr2[i2 + i3] = (int) (j3 >>> 1);
            int i8 = (int) j3;
            if (i6 <= 0) {
                long j4 = j * j;
                long j5 = (((long) (i8 << 31)) & M) | (j4 >>> 33);
                iArr2[i2 + 0] = (int) j4;
                long j6 = ((long) iArr[i + 1]) & M;
                int i9 = i2 + 2;
                long j7 = ((long) iArr2[i9]) & M;
                long j8 = j5 + (j6 * j);
                int i10 = (int) j8;
                iArr2[i2 + 1] = (i10 << 1) | (((int) (j4 >>> 32)) & 1);
                int i11 = i10 >>> 31;
                long j9 = j7 + (j8 >>> 32);
                long j10 = ((long) iArr[i + 2]) & M;
                int i12 = i2 + 3;
                long j11 = ((long) iArr2[i12]) & M;
                int i13 = i2 + 4;
                long j12 = ((long) iArr2[i13]) & M;
                long j13 = j9 + (j10 * j);
                int i14 = (int) j13;
                iArr2[i9] = (i14 << 1) | i11;
                int i15 = i14 >>> 31;
                long j14 = j11 + (j13 >>> 32) + (j10 * j6);
                long j15 = j12 + (j14 >>> 32);
                long j16 = j14 & M;
                long j17 = ((long) iArr[i + 3]) & M;
                int i16 = i2 + 5;
                long j18 = (((long) iArr2[i16]) & M) + (j15 >>> 32);
                long j19 = j15 & M;
                int i17 = i2 + 6;
                long j20 = (((long) iArr2[i17]) & M) + (j18 >>> 32);
                long j21 = j18 & M;
                long j22 = j16 + (j17 * j);
                int i18 = (int) j22;
                iArr2[i12] = (i18 << 1) | i15;
                long j23 = j19 + (j22 >>> 32) + (j17 * j6);
                long j24 = j21 + (j23 >>> 32) + (j17 * j10);
                long j25 = j23 & M;
                long j26 = j20 + (j24 >>> 32);
                long j27 = j24 & M;
                long j28 = ((long) iArr[i + 4]) & M;
                int i19 = i2 + 7;
                long j29 = (((long) iArr2[i19]) & M) + (j26 >>> 32);
                long j30 = j26 & M;
                int i20 = i2 + 8;
                long j31 = (((long) iArr2[i20]) & M) + (j29 >>> 32);
                long j32 = j29 & M;
                long j33 = j25 + (j28 * j);
                int i21 = (int) j33;
                iArr2[i13] = (i18 >>> 31) | (i21 << 1);
                int i22 = i21 >>> 31;
                long j34 = j27 + (j33 >>> 32) + (j28 * j6);
                long j35 = j30 + (j34 >>> 32) + (j28 * j10);
                long j36 = j34 & M;
                long j37 = j32 + (j35 >>> 32) + (j28 * j17);
                long j38 = j35 & M;
                long j39 = j31 + (j37 >>> 32);
                long j40 = j37 & M;
                long j41 = ((long) iArr[i + 5]) & M;
                int i23 = i2 + 9;
                long j42 = (((long) iArr2[i23]) & M) + (j39 >>> 32);
                long j43 = j39 & M;
                int i24 = i2 + 10;
                long j44 = j42 & M;
                long j45 = j36 + (j41 * j);
                int i25 = (int) j45;
                iArr2[i16] = i22 | (i25 << 1);
                int i26 = i25 >>> 31;
                long j46 = j38 + (j45 >>> 32) + (j41 * j6);
                long j47 = j40 + (j46 >>> 32) + (j41 * j10);
                long j48 = j46 & M;
                long j49 = j43 + (j47 >>> 32) + (j41 * j17);
                long j50 = j47 & M;
                long j51 = j44 + (j49 >>> 32) + (j41 * j28);
                long j52 = j49 & M;
                long j53 = (((long) iArr2[i24]) & M) + (j42 >>> 32) + (j51 >>> 32);
                long j54 = j51 & M;
                long j55 = ((long) iArr[i + 6]) & M;
                int i27 = i2 + 11;
                long j56 = (((long) iArr2[i27]) & M) + (j53 >>> 32);
                long j57 = j53 & M;
                int i28 = i2 + 12;
                long j58 = (((long) iArr2[i28]) & M) + (j56 >>> 32);
                long j59 = j56 & M;
                long j60 = j48 + (j55 * j);
                int i29 = (int) j60;
                iArr2[i17] = i26 | (i29 << 1);
                int i30 = i29 >>> 31;
                long j61 = j50 + (j60 >>> 32) + (j55 * j6);
                long j62 = j52 + (j61 >>> 32) + (j55 * j10);
                long j63 = j61 & M;
                long j64 = j54 + (j62 >>> 32) + (j55 * j17);
                long j65 = j62 & M;
                long j66 = j57 + (j64 >>> 32) + (j55 * j28);
                long j67 = j64 & M;
                long j68 = j59 + (j66 >>> 32) + (j55 * j41);
                long j69 = j66 & M;
                long j70 = j58 + (j68 >>> 32);
                long j71 = j68 & M;
                long j72 = ((long) iArr[i + 7]) & M;
                int i31 = i2 + 13;
                long j73 = (((long) iArr2[i31]) & M) + (j70 >>> 32);
                long j74 = j70 & M;
                int i32 = i2 + 14;
                long j75 = j73 & M;
                long j76 = j63 + (j * j72);
                int i33 = (int) j76;
                iArr2[i19] = (i33 << 1) | i30;
                long j77 = j65 + (j76 >>> 32) + (j72 * j6);
                long j78 = j67 + (j77 >>> 32) + (j72 * j10);
                long j79 = j69 + (j78 >>> 32) + (j72 * j17);
                long j80 = j71 + (j79 >>> 32) + (j72 * j28);
                long j81 = j74 + (j80 >>> 32) + (j72 * j41);
                long j82 = j75 + (j81 >>> 32) + (j72 * j55);
                long j83 = (((long) iArr2[i32]) & M) + (j73 >>> 32) + (j82 >>> 32);
                int i34 = (int) j77;
                iArr2[i20] = (i33 >>> 31) | (i34 << 1);
                int i35 = (int) j78;
                iArr2[i23] = (i34 >>> 31) | (i35 << 1);
                int i36 = i35 >>> 31;
                int i37 = (int) j79;
                iArr2[i24] = i36 | (i37 << 1);
                int i38 = (int) j80;
                iArr2[i27] = (i37 >>> 31) | (i38 << 1);
                int i39 = (int) j81;
                iArr2[i28] = (i38 >>> 31) | (i39 << 1);
                int i40 = i39 >>> 31;
                int i41 = (int) j82;
                iArr2[i31] = i40 | (i41 << 1);
                int i42 = i41 >>> 31;
                int i43 = (int) j83;
                iArr2[i32] = i42 | (i43 << 1);
                int i44 = i43 >>> 31;
                int i45 = i2 + 15;
                iArr2[i45] = i44 | ((iArr2[i45] + ((int) (j83 >>> 32))) << 1);
                return;
            }
            i5 = i8;
            i4 = i6;
        }
    }

    public static int sub(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = ((((long) iArr[0]) & M) - (((long) iArr2[0]) & M)) + 0;
        iArr3[0] = (int) j;
        long j2 = (j >> 32) + ((((long) iArr[1]) & M) - (((long) iArr2[1]) & M));
        iArr3[1] = (int) j2;
        long j3 = (j2 >> 32) + ((((long) iArr[2]) & M) - (((long) iArr2[2]) & M));
        iArr3[2] = (int) j3;
        long j4 = (j3 >> 32) + ((((long) iArr[3]) & M) - (((long) iArr2[3]) & M));
        iArr3[3] = (int) j4;
        long j5 = (j4 >> 32) + ((((long) iArr[4]) & M) - (((long) iArr2[4]) & M));
        iArr3[4] = (int) j5;
        long j6 = (j5 >> 32) + ((((long) iArr[5]) & M) - (((long) iArr2[5]) & M));
        iArr3[5] = (int) j6;
        long j7 = (j6 >> 32) + ((((long) iArr[6]) & M) - (((long) iArr2[6]) & M));
        iArr3[6] = (int) j7;
        long j8 = (j7 >> 32) + ((((long) iArr[7]) & M) - (((long) iArr2[7]) & M));
        iArr3[7] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static int sub(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        long j = ((((long) iArr[i + 0]) & M) - (((long) iArr2[i2 + 0]) & M)) + 0;
        iArr3[i3 + 0] = (int) j;
        long j2 = (j >> 32) + ((((long) iArr[i + 1]) & M) - (((long) iArr2[i2 + 1]) & M));
        iArr3[i3 + 1] = (int) j2;
        long j3 = (j2 >> 32) + ((((long) iArr[i + 2]) & M) - (((long) iArr2[i2 + 2]) & M));
        iArr3[i3 + 2] = (int) j3;
        long j4 = (j3 >> 32) + ((((long) iArr[i + 3]) & M) - (((long) iArr2[i2 + 3]) & M));
        iArr3[i3 + 3] = (int) j4;
        long j5 = (j4 >> 32) + ((((long) iArr[i + 4]) & M) - (((long) iArr2[i2 + 4]) & M));
        iArr3[i3 + 4] = (int) j5;
        long j6 = (j5 >> 32) + ((((long) iArr[i + 5]) & M) - (((long) iArr2[i2 + 5]) & M));
        iArr3[i3 + 5] = (int) j6;
        long j7 = (j6 >> 32) + ((((long) iArr[i + 6]) & M) - (((long) iArr2[i2 + 6]) & M));
        iArr3[i3 + 6] = (int) j7;
        long j8 = (j7 >> 32) + ((((long) iArr[i + 7]) & M) - (((long) iArr2[i2 + 7]) & M));
        iArr3[i3 + 7] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static int subBothFrom(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (((((long) iArr3[0]) & M) - (((long) iArr[0]) & M)) - (((long) iArr2[0]) & M)) + 0;
        iArr3[0] = (int) j;
        long j2 = (j >> 32) + (((((long) iArr3[1]) & M) - (((long) iArr[1]) & M)) - (((long) iArr2[1]) & M));
        iArr3[1] = (int) j2;
        long j3 = (j2 >> 32) + (((((long) iArr3[2]) & M) - (((long) iArr[2]) & M)) - (((long) iArr2[2]) & M));
        iArr3[2] = (int) j3;
        long j4 = (j3 >> 32) + (((((long) iArr3[3]) & M) - (((long) iArr[3]) & M)) - (((long) iArr2[3]) & M));
        iArr3[3] = (int) j4;
        long j5 = (j4 >> 32) + (((((long) iArr3[4]) & M) - (((long) iArr[4]) & M)) - (((long) iArr2[4]) & M));
        iArr3[4] = (int) j5;
        long j6 = (j5 >> 32) + (((((long) iArr3[5]) & M) - (((long) iArr[5]) & M)) - (((long) iArr2[5]) & M));
        iArr3[5] = (int) j6;
        long j7 = (j6 >> 32) + (((((long) iArr3[6]) & M) - (((long) iArr[6]) & M)) - (((long) iArr2[6]) & M));
        iArr3[6] = (int) j7;
        long j8 = (j7 >> 32) + (((((long) iArr3[7]) & M) - (((long) iArr[7]) & M)) - (((long) iArr2[7]) & M));
        iArr3[7] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static int subFrom(int[] iArr, int[] iArr2) {
        long j = ((((long) iArr2[0]) & M) - (((long) iArr[0]) & M)) + 0;
        iArr2[0] = (int) j;
        long j2 = (j >> 32) + ((((long) iArr2[1]) & M) - (((long) iArr[1]) & M));
        iArr2[1] = (int) j2;
        long j3 = (j2 >> 32) + ((((long) iArr2[2]) & M) - (((long) iArr[2]) & M));
        iArr2[2] = (int) j3;
        long j4 = (j3 >> 32) + ((((long) iArr2[3]) & M) - (((long) iArr[3]) & M));
        iArr2[3] = (int) j4;
        long j5 = (j4 >> 32) + ((((long) iArr2[4]) & M) - (((long) iArr[4]) & M));
        iArr2[4] = (int) j5;
        long j6 = (j5 >> 32) + ((((long) iArr2[5]) & M) - (((long) iArr[5]) & M));
        iArr2[5] = (int) j6;
        long j7 = (j6 >> 32) + ((((long) iArr2[6]) & M) - (((long) iArr[6]) & M));
        iArr2[6] = (int) j7;
        long j8 = (j7 >> 32) + ((((long) iArr2[7]) & M) - (M & ((long) iArr[7])));
        iArr2[7] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static int subFrom(int[] iArr, int i, int[] iArr2, int i2) {
        int i3 = i2 + 0;
        long j = ((((long) iArr2[i3]) & M) - (((long) iArr[i + 0]) & M)) + 0;
        iArr2[i3] = (int) j;
        int i4 = i2 + 1;
        long j2 = (j >> 32) + ((((long) iArr2[i4]) & M) - (((long) iArr[i + 1]) & M));
        iArr2[i4] = (int) j2;
        int i5 = i2 + 2;
        long j3 = (j2 >> 32) + ((((long) iArr2[i5]) & M) - (((long) iArr[i + 2]) & M));
        iArr2[i5] = (int) j3;
        int i6 = i2 + 3;
        long j4 = (j3 >> 32) + ((((long) iArr2[i6]) & M) - (((long) iArr[i + 3]) & M));
        iArr2[i6] = (int) j4;
        int i7 = i2 + 4;
        long j5 = (j4 >> 32) + ((((long) iArr2[i7]) & M) - (((long) iArr[i + 4]) & M));
        iArr2[i7] = (int) j5;
        int i8 = i2 + 5;
        long j6 = (j5 >> 32) + ((((long) iArr2[i8]) & M) - (((long) iArr[i + 5]) & M));
        iArr2[i8] = (int) j6;
        int i9 = i2 + 6;
        long j7 = (j6 >> 32) + ((((long) iArr2[i9]) & M) - (((long) iArr[i + 6]) & M));
        iArr2[i9] = (int) j7;
        int i10 = i2 + 7;
        long j8 = (j7 >> 32) + ((((long) iArr2[i10]) & M) - (((long) iArr[i + 7]) & M));
        iArr2[i10] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static BigInteger toBigInteger(int[] iArr) {
        byte[] bArr = new byte[32];
        for (int i = 0; i < 8; i++) {
            int i2 = iArr[i];
            if (i2 != 0) {
                Pack.intToBigEndian(i2, bArr, (7 - i) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static BigInteger toBigInteger64(long[] jArr) {
        byte[] bArr = new byte[32];
        for (int i = 0; i < 4; i++) {
            long j = jArr[i];
            if (j != 0) {
                Pack.longToBigEndian(j, bArr, (3 - i) << 3);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static void zero(int[] iArr) {
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        iArr[5] = 0;
        iArr[6] = 0;
        iArr[7] = 0;
    }
}
