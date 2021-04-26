package org.spongycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat192;

public class SecP192R1Field {
    private static final long M = 4294967295L;
    static final int[] P = {-1, -1, -2, -1, -1, -1};
    private static final int P5 = -1;
    static final int[] PExt = {1, 0, 2, 0, 1, 0, -2, -1, -3, -1, -1, -1};
    private static final int PExt11 = -1;
    private static final int[] PExtInv = {-1, -1, -3, -1, -2, -1, 1, 0, 2};

    public static void add(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat192.add(iArr, iArr2, iArr3) != 0 || (iArr3[5] == -1 && Nat192.gte(iArr3, P))) {
            addPInvTo(iArr3);
        }
    }

    public static void addExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat.add(12, iArr, iArr2, iArr3) != 0 || (iArr3[11] == -1 && Nat.gte(12, iArr3, PExt))) {
            int[] iArr4 = PExtInv;
            if (Nat.addTo(iArr4.length, iArr4, iArr3) != 0) {
                Nat.incAt(12, iArr3, PExtInv.length);
            }
        }
    }

    public static void addOne(int[] iArr, int[] iArr2) {
        if (Nat.inc(6, iArr, iArr2) != 0 || (iArr2[5] == -1 && Nat192.gte(iArr2, P))) {
            addPInvTo(iArr2);
        }
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] fromBigInteger = Nat192.fromBigInteger(bigInteger);
        if (fromBigInteger[5] == -1 && Nat192.gte(fromBigInteger, P)) {
            Nat192.subFrom(P, fromBigInteger);
        }
        return fromBigInteger;
    }

    public static void half(int[] iArr, int[] iArr2) {
        if ((iArr[0] & 1) == 0) {
            Nat.shiftDownBit(6, iArr, 0, iArr2);
        } else {
            Nat.shiftDownBit(6, iArr2, Nat192.add(iArr, P, iArr2));
        }
    }

    public static void multiply(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] createExt = Nat192.createExt();
        Nat192.mul(iArr, iArr2, createExt);
        reduce(createExt, iArr3);
    }

    public static void multiplyAddToExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat192.mulAddTo(iArr, iArr2, iArr3) != 0 || (iArr3[11] == -1 && Nat.gte(12, iArr3, PExt))) {
            int[] iArr4 = PExtInv;
            if (Nat.addTo(iArr4.length, iArr4, iArr3) != 0) {
                Nat.incAt(12, iArr3, PExtInv.length);
            }
        }
    }

    public static void negate(int[] iArr, int[] iArr2) {
        if (Nat192.isZero(iArr)) {
            Nat192.zero(iArr2);
        } else {
            Nat192.sub(P, iArr, iArr2);
        }
    }

    public static void reduce(int[] iArr, int[] iArr2) {
        long j = ((long) iArr[6]) & M;
        long j2 = ((long) iArr[7]) & M;
        long j3 = ((long) iArr[8]) & M;
        long j4 = ((long) iArr[9]) & M;
        long j5 = (((long) iArr[10]) & M) + j;
        long j6 = (((long) iArr[11]) & M) + j2;
        long j7 = (((long) iArr[0]) & M) + j5 + 0;
        int i = (int) j7;
        long j8 = (j7 >> 32) + (((long) iArr[1]) & M) + j6;
        iArr2[1] = (int) j8;
        long j9 = j5 + j3;
        long j10 = j6 + j4;
        long j11 = (j8 >> 32) + (((long) iArr[2]) & M) + j9;
        long j12 = j11 & M;
        long j13 = (j11 >> 32) + (((long) iArr[3]) & M) + j10;
        iArr2[3] = (int) j13;
        long j14 = (j13 >> 32) + (((long) iArr[4]) & M) + (j9 - j);
        iArr2[4] = (int) j14;
        long j15 = (j14 >> 32) + (((long) iArr[5]) & M) + (j10 - j2);
        iArr2[5] = (int) j15;
        long j16 = j15 >> 32;
        long j17 = j12 + j16;
        long j18 = j16 + (((long) i) & M);
        iArr2[0] = (int) j18;
        long j19 = j18 >> 32;
        if (j19 != 0) {
            long j20 = j19 + (M & ((long) iArr2[1]));
            iArr2[1] = (int) j20;
            j17 += j20 >> 32;
        }
        iArr2[2] = (int) j17;
        if (((j17 >> 32) != 0 && Nat.incAt(6, iArr2, 3) != 0) || (iArr2[5] == -1 && Nat192.gte(iArr2, P))) {
            addPInvTo(iArr2);
        }
    }

    public static void reduce32(int i, int[] iArr) {
        long j;
        if (i != 0) {
            long j2 = ((long) i) & M;
            long j3 = (((long) iArr[0]) & M) + j2 + 0;
            iArr[0] = (int) j3;
            long j4 = j3 >> 32;
            if (j4 != 0) {
                long j5 = j4 + (((long) iArr[1]) & M);
                iArr[1] = (int) j5;
                j4 = j5 >> 32;
            }
            long j6 = j4 + (M & ((long) iArr[2])) + j2;
            iArr[2] = (int) j6;
            j = j6 >> 32;
        } else {
            j = 0;
        }
        if ((j != 0 && Nat.incAt(6, iArr, 3) != 0) || (iArr[5] == -1 && Nat192.gte(iArr, P))) {
            addPInvTo(iArr);
        }
    }

    public static void square(int[] iArr, int[] iArr2) {
        int[] createExt = Nat192.createExt();
        Nat192.square(iArr, createExt);
        reduce(createExt, iArr2);
    }

    public static void squareN(int[] iArr, int i, int[] iArr2) {
        int[] createExt = Nat192.createExt();
        Nat192.square(iArr, createExt);
        reduce(createExt, iArr2);
        while (true) {
            i--;
            if (i > 0) {
                Nat192.square(iArr2, createExt);
                reduce(createExt, iArr2);
            } else {
                return;
            }
        }
    }

    public static void subtract(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat192.sub(iArr, iArr2, iArr3) != 0) {
            subPInvFrom(iArr3);
        }
    }

    public static void subtractExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat.sub(12, iArr, iArr2, iArr3) != 0) {
            int[] iArr4 = PExtInv;
            if (Nat.subFrom(iArr4.length, iArr4, iArr3) != 0) {
                Nat.decAt(12, iArr3, PExtInv.length);
            }
        }
    }

    public static void twice(int[] iArr, int[] iArr2) {
        if (Nat.shiftUpBit(6, iArr, 0, iArr2) != 0 || (iArr2[5] == -1 && Nat192.gte(iArr2, P))) {
            addPInvTo(iArr2);
        }
    }

    private static void addPInvTo(int[] iArr) {
        long j = (((long) iArr[0]) & M) + 1;
        iArr[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            long j3 = j2 + (((long) iArr[1]) & M);
            iArr[1] = (int) j3;
            j2 = j3 >> 32;
        }
        long j4 = j2 + (M & ((long) iArr[2])) + 1;
        iArr[2] = (int) j4;
        if ((j4 >> 32) != 0) {
            Nat.incAt(6, iArr, 3);
        }
    }

    private static void subPInvFrom(int[] iArr) {
        long j = (((long) iArr[0]) & M) - 1;
        iArr[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            long j3 = j2 + (((long) iArr[1]) & M);
            iArr[1] = (int) j3;
            j2 = j3 >> 32;
        }
        long j4 = j2 + ((M & ((long) iArr[2])) - 1);
        iArr[2] = (int) j4;
        if ((j4 >> 32) != 0) {
            Nat.decAt(6, iArr, 3);
        }
    }
}
