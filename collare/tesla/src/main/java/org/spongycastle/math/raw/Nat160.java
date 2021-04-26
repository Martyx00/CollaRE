package org.spongycastle.math.raw;

import java.math.BigInteger;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import org.spongycastle.util.Pack;

public abstract class Nat160 {
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
        return (int) (j5 >>> 32);
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
        return (int) (j5 >>> 32);
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
        long j5 = (j4 >>> 32) + (((long) iArr[4]) & M) + (M & ((long) iArr2[4]));
        iArr2[4] = (int) j5;
        return (int) (j5 >>> 32);
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
        long j5 = (j4 >>> 32) + (((long) iArr[i + 4]) & M) + (M & ((long) iArr2[i8]));
        iArr2[i8] = (int) j5;
        return (int) (j5 >>> 32);
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
        long j5 = (j4 >>> 32) + (((long) iArr[i15]) & M) + (M & ((long) iArr2[i16]));
        int i17 = (int) j5;
        iArr[i15] = i17;
        iArr2[i16] = i17;
        return (int) (j5 >>> 32);
    }

    public static void copy(int[] iArr, int[] iArr2) {
        iArr2[0] = iArr[0];
        iArr2[1] = iArr[1];
        iArr2[2] = iArr[2];
        iArr2[3] = iArr[3];
        iArr2[4] = iArr[4];
    }

    public static int[] create() {
        return new int[5];
    }

    public static int[] createExt() {
        return new int[10];
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
        for (int i = 4; i >= 0; i--) {
            if (iArr[i] != iArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 160) {
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

    public static int getBit(int[] iArr, int i) {
        if (i == 0) {
            return iArr[0] & 1;
        }
        int i2 = i >> 5;
        if (i2 < 0 || i2 >= 5) {
            return 0;
        }
        return (iArr[i2] >>> (i & 31)) & 1;
    }

    public static boolean gte(int[] iArr, int[] iArr2) {
        for (int i = 4; i >= 0; i--) {
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
        for (int i3 = 4; i3 >= 0; i3--) {
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
        for (int i = 1; i < 5; i++) {
            if (iArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero(int[] iArr) {
        for (int i = 0; i < 5; i++) {
            if (iArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void mul(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = ((long) iArr2[0]) & M;
        int i = 1;
        long j2 = ((long) iArr2[1]) & M;
        long j3 = ((long) iArr2[2]) & M;
        long j4 = ((long) iArr2[3]) & M;
        long j5 = ((long) iArr2[4]) & M;
        long j6 = ((long) iArr[0]) & M;
        long j7 = (j6 * j) + 0;
        iArr3[0] = (int) j7;
        long j8 = (j7 >>> 32) + (j6 * j2);
        iArr3[1] = (int) j8;
        long j9 = (j8 >>> 32) + (j6 * j3);
        iArr3[2] = (int) j9;
        long j10 = (j9 >>> 32) + (j6 * j4);
        iArr3[3] = (int) j10;
        long j11 = (j10 >>> 32) + (j6 * j5);
        iArr3[4] = (int) j11;
        int i2 = (int) (j11 >>> 32);
        iArr3[5] = i2;
        for (int i3 = 5; i < i3; i3 = 5) {
            long j12 = ((long) iArr[i]) & M;
            int i4 = i + 0;
            long j13 = (j12 * j) + (((long) iArr3[i4]) & M) + 0;
            iArr3[i4] = (int) j13;
            int i5 = i + 1;
            long j14 = (j13 >>> 32) + (j12 * j2) + (((long) iArr3[i5]) & M);
            iArr3[i5] = (int) j14;
            int i6 = i + 2;
            long j15 = (j14 >>> 32) + (j12 * j3) + (((long) iArr3[i6]) & M);
            iArr3[i6] = (int) j15;
            int i7 = i + 3;
            long j16 = (j15 >>> 32) + (j12 * j4) + (((long) iArr3[i7]) & M);
            iArr3[i7] = (int) j16;
            int i8 = i + 4;
            long j17 = (j16 >>> 32) + (j12 * j5) + (((long) iArr3[i8]) & M);
            iArr3[i8] = (int) j17;
            iArr3[i + 5] = (int) (j17 >>> 32);
            i = i5;
            j5 = j5;
            j = j;
            j2 = j2;
        }
    }

    public static void mul(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        long j = ((long) iArr2[i2 + 0]) & M;
        long j2 = ((long) iArr2[i2 + 1]) & M;
        long j3 = ((long) iArr2[i2 + 2]) & M;
        long j4 = ((long) iArr2[i2 + 3]) & M;
        long j5 = ((long) iArr2[i2 + 4]) & M;
        long j6 = ((long) iArr[i + 0]) & M;
        long j7 = (j6 * j) + 0;
        iArr3[i3 + 0] = (int) j7;
        long j8 = (j7 >>> 32) + (j6 * j2);
        iArr3[i3 + 1] = (int) j8;
        long j9 = (j8 >>> 32) + (j6 * j3);
        iArr3[i3 + 2] = (int) j9;
        long j10 = (j9 >>> 32) + (j6 * j4);
        iArr3[i3 + 3] = (int) j10;
        long j11 = (j10 >>> 32) + (j6 * j5);
        iArr3[i3 + 4] = (int) j11;
        iArr3[i3 + 5] = (int) (j11 >>> 32);
        int i4 = 1;
        int i5 = i3;
        int i6 = 1;
        while (i6 < 5) {
            i5 += i4;
            long j12 = ((long) iArr[i + i6]) & M;
            int i7 = i5 + 0;
            long j13 = (j12 * j) + (((long) iArr3[i7]) & M) + 0;
            iArr3[i7] = (int) j13;
            int i8 = i5 + 1;
            long j14 = (j13 >>> 32) + (j12 * j2) + (((long) iArr3[i8]) & M);
            iArr3[i8] = (int) j14;
            int i9 = i5 + 2;
            long j15 = (j14 >>> 32) + (j12 * j3) + (((long) iArr3[i9]) & M);
            iArr3[i9] = (int) j15;
            int i10 = i5 + 3;
            long j16 = (j15 >>> 32) + (j12 * j4) + (((long) iArr3[i10]) & M);
            iArr3[i10] = (int) j16;
            int i11 = i5 + 4;
            long j17 = (j16 >>> 32) + (j12 * j5) + (((long) iArr3[i11]) & M);
            iArr3[i11] = (int) j17;
            iArr3[i5 + 5] = (int) (j17 >>> 32);
            i6++;
            j3 = j3;
            j = j;
            i4 = 1;
        }
    }

    public static int mulAddTo(int[] iArr, int[] iArr2, int[] iArr3) {
        int i = 0;
        long j = M;
        long j2 = ((long) iArr2[0]) & M;
        long j3 = ((long) iArr2[1]) & M;
        long j4 = ((long) iArr2[2]) & M;
        long j5 = ((long) iArr2[3]) & M;
        long j6 = ((long) iArr2[4]) & M;
        long j7 = 0;
        while (i < 5) {
            long j8 = ((long) iArr[i]) & j;
            int i2 = i + 0;
            long j9 = (j8 * j2) + (((long) iArr3[i2]) & j) + 0;
            iArr3[i2] = (int) j9;
            int i3 = i + 1;
            long j10 = (j9 >>> 32) + (j8 * j3) + (((long) iArr3[i3]) & M);
            iArr3[i3] = (int) j10;
            int i4 = i + 2;
            long j11 = (j10 >>> 32) + (j8 * j4) + (((long) iArr3[i4]) & M);
            iArr3[i4] = (int) j11;
            int i5 = i + 3;
            long j12 = (j11 >>> 32) + (j8 * j5) + (((long) iArr3[i5]) & M);
            iArr3[i5] = (int) j12;
            int i6 = i + 4;
            long j13 = (j12 >>> 32) + (j8 * j6) + (((long) iArr3[i6]) & M);
            iArr3[i6] = (int) j13;
            int i7 = i + 5;
            long j14 = (j13 >>> 32) + j7 + (((long) iArr3[i7]) & M);
            iArr3[i7] = (int) j14;
            j7 = j14 >>> 32;
            i = i3;
            j = 4294967295L;
            j2 = j2;
            j4 = j4;
            j3 = j3;
        }
        return (int) j7;
    }

    public static int mulAddTo(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        long j = M;
        long j2 = ((long) iArr2[i2 + 0]) & M;
        long j3 = ((long) iArr2[i2 + 1]) & M;
        long j4 = ((long) iArr2[i2 + 2]) & M;
        long j5 = ((long) iArr2[i2 + 3]) & M;
        long j6 = ((long) iArr2[i2 + 4]) & M;
        int i4 = 0;
        int i5 = i3;
        long j7 = 0;
        while (i4 < 5) {
            long j8 = ((long) iArr[i + i4]) & j;
            int i6 = i5 + 0;
            long j9 = (j8 * j2) + (((long) iArr3[i6]) & j) + 0;
            iArr3[i6] = (int) j9;
            int i7 = i5 + 1;
            long j10 = (j9 >>> 32) + (j8 * j3) + (((long) iArr3[i7]) & M);
            iArr3[i7] = (int) j10;
            int i8 = i5 + 2;
            long j11 = (j10 >>> 32) + (j8 * j4) + (((long) iArr3[i8]) & M);
            iArr3[i8] = (int) j11;
            int i9 = i5 + 3;
            long j12 = (j11 >>> 32) + (j8 * j5) + (((long) iArr3[i9]) & M);
            iArr3[i9] = (int) j12;
            int i10 = i5 + 4;
            long j13 = (j12 >>> 32) + (j8 * j6) + (((long) iArr3[i10]) & M);
            iArr3[i10] = (int) j13;
            int i11 = i5 + 5;
            long j14 = (j13 >>> 32) + j7 + (((long) iArr3[i11]) & M);
            iArr3[i11] = (int) j14;
            j7 = j14 >>> 32;
            i4++;
            i5 = i7;
            j2 = j2;
            j = 4294967295L;
            j3 = j3;
            j4 = j4;
        }
        return (int) j7;
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
        long j12 = (j10 >>> 32) + (j * j11) + j9 + (M & ((long) iArr2[i3 + 4]));
        iArr3[i4 + 4] = (int) j12;
        return (j12 >>> 32) + j11;
    }

    public static int mulWordAddExt(int i, int[] iArr, int i2, int[] iArr2, int i3) {
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
        long j6 = (j5 >>> 32) + (j * (((long) iArr[i2 + 4]) & M)) + (((long) iArr2[i8]) & M);
        iArr2[i8] = (int) j6;
        return (int) (j6 >>> 32);
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
        return Nat.incAt(5, iArr, i2, 4);
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
        return Nat.incAt(5, iArr, i3, 3);
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
        return Nat.incAt(5, iArr, i2, 3);
    }

    public static int mulWordsAdd(int i, int i2, int[] iArr, int i3) {
        long j = (((long) i2) & M) * (((long) i) & M);
        int i4 = i3 + 0;
        long j2 = j + (((long) iArr[i4]) & M) + 0;
        iArr[i4] = (int) j2;
        int i5 = i3 + 1;
        long j3 = (j2 >>> 32) + (M & ((long) iArr[i5]));
        iArr[i5] = (int) j3;
        if ((j3 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(5, iArr, i3, 2);
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
        } while (i3 < 5);
        return (int) j2;
    }

    public static void square(int[] iArr, int[] iArr2) {
        long j = ((long) iArr[0]) & M;
        int i = 4;
        int i2 = 10;
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
                iArr2[3] = (i9 << 1) | (i8 >>> 31);
                long j22 = j19 + (j21 >>> 32) + (j17 * j6);
                long j23 = j20 + (j22 >>> 32) + (j17 * j10);
                long j24 = j22 & M;
                long j25 = (((long) iArr2[6]) & M) + (j18 >>> 32) + (j23 >>> 32);
                long j26 = j23 & M;
                long j27 = ((long) iArr[4]) & M;
                long j28 = (((long) iArr2[7]) & M) + (j25 >>> 32);
                long j29 = j25 & M;
                long j30 = j28 & M;
                long j31 = j24 + (j27 * j);
                int i10 = (int) j31;
                iArr2[4] = (i9 >>> 31) | (i10 << 1);
                long j32 = (j31 >>> 32) + (j6 * j27) + j26;
                long j33 = j29 + (j32 >>> 32) + (j27 * j10);
                long j34 = j30 + (j33 >>> 32) + (j27 * j17);
                long j35 = (((long) iArr2[8]) & M) + (j28 >>> 32) + (j34 >>> 32);
                int i11 = (int) j32;
                iArr2[5] = (i10 >>> 31) | (i11 << 1);
                int i12 = (int) j33;
                iArr2[6] = (i11 >>> 31) | (i12 << 1);
                int i13 = (int) j34;
                iArr2[7] = (i13 << 1) | (i12 >>> 31);
                int i14 = i13 >>> 31;
                int i15 = (int) j35;
                iArr2[8] = i14 | (i15 << 1);
                iArr2[9] = (i15 >>> 31) | ((iArr2[9] + ((int) (j35 >>> 32))) << 1);
                return;
            }
            i = i4;
            i3 = i6;
        }
    }

    public static void square(int[] iArr, int i, int[] iArr2, int i2) {
        long j = ((long) iArr[i + 0]) & M;
        int i3 = 10;
        int i4 = 4;
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
                long j31 = j29 & M;
                long j32 = j25 + (j * j28);
                int i21 = (int) j32;
                iArr2[i13] = (i21 << 1) | (i18 >>> 31);
                long j33 = j27 + (j32 >>> 32) + (j28 * j6);
                long j34 = j30 + (j33 >>> 32) + (j28 * j10);
                long j35 = j31 + (j34 >>> 32) + (j28 * j17);
                long j36 = (((long) iArr2[i20]) & M) + (j29 >>> 32) + (j35 >>> 32);
                int i22 = (int) j33;
                iArr2[i16] = (i21 >>> 31) | (i22 << 1);
                int i23 = (int) j34;
                iArr2[i17] = (i22 >>> 31) | (i23 << 1);
                int i24 = (int) j35;
                iArr2[i19] = (i23 >>> 31) | (i24 << 1);
                int i25 = i24 >>> 31;
                int i26 = (int) j36;
                iArr2[i20] = i25 | (i26 << 1);
                int i27 = i26 >>> 31;
                int i28 = i2 + 9;
                iArr2[i28] = i27 | ((iArr2[i28] + ((int) (j36 >>> 32))) << 1);
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
        return (int) (j5 >> 32);
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
        return (int) (j5 >> 32);
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
        return (int) (j5 >> 32);
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
        long j5 = (j4 >> 32) + ((((long) iArr2[4]) & M) - (M & ((long) iArr[4])));
        iArr2[4] = (int) j5;
        return (int) (j5 >> 32);
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
        return (int) (j5 >> 32);
    }

    public static BigInteger toBigInteger(int[] iArr) {
        byte[] bArr = new byte[20];
        for (int i = 0; i < 5; i++) {
            int i2 = iArr[i];
            if (i2 != 0) {
                Pack.intToBigEndian(i2, bArr, (4 - i) << 2);
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
    }
}
