package org.spongycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat128;
import org.spongycastle.math.raw.Nat256;

public class SecP128R1Field {
    private static final long M = 4294967295L;
    static final int[] P = {-1, -1, -1, P3};
    private static final int P3 = -3;
    static final int[] PExt = {1, 0, 0, 4, -2, -1, 3, PExt7};
    private static final int PExt7 = -4;
    private static final int[] PExtInv = {-1, -1, -1, -5, 1, 0, PExt7, 3};

    public static void add(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat128.add(iArr, iArr2, iArr3) != 0 || (iArr3[3] == P3 && Nat128.gte(iArr3, P))) {
            addPInvTo(iArr3);
        }
    }

    public static void addExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat256.add(iArr, iArr2, iArr3) != 0 || (iArr3[7] == PExt7 && Nat256.gte(iArr3, PExt))) {
            int[] iArr4 = PExtInv;
            Nat.addTo(iArr4.length, iArr4, iArr3);
        }
    }

    public static void addOne(int[] iArr, int[] iArr2) {
        if (Nat.inc(4, iArr, iArr2) != 0 || (iArr2[3] == P3 && Nat128.gte(iArr2, P))) {
            addPInvTo(iArr2);
        }
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] fromBigInteger = Nat128.fromBigInteger(bigInteger);
        if (fromBigInteger[3] == P3 && Nat128.gte(fromBigInteger, P)) {
            Nat128.subFrom(P, fromBigInteger);
        }
        return fromBigInteger;
    }

    public static void half(int[] iArr, int[] iArr2) {
        if ((iArr[0] & 1) == 0) {
            Nat.shiftDownBit(4, iArr, 0, iArr2);
        } else {
            Nat.shiftDownBit(4, iArr2, Nat128.add(iArr, P, iArr2));
        }
    }

    public static void multiply(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] createExt = Nat128.createExt();
        Nat128.mul(iArr, iArr2, createExt);
        reduce(createExt, iArr3);
    }

    public static void multiplyAddToExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat128.mulAddTo(iArr, iArr2, iArr3) != 0 || (iArr3[7] == PExt7 && Nat256.gte(iArr3, PExt))) {
            int[] iArr4 = PExtInv;
            Nat.addTo(iArr4.length, iArr4, iArr3);
        }
    }

    public static void negate(int[] iArr, int[] iArr2) {
        if (Nat128.isZero(iArr)) {
            Nat128.zero(iArr2);
        } else {
            Nat128.sub(P, iArr, iArr2);
        }
    }

    public static void reduce(int[] iArr, int[] iArr2) {
        long j = ((long) iArr[0]) & M;
        long j2 = ((long) iArr[1]) & M;
        long j3 = ((long) iArr[2]) & M;
        long j4 = ((long) iArr[3]) & M;
        long j5 = ((long) iArr[4]) & M;
        long j6 = ((long) iArr[5]) & M;
        long j7 = ((long) iArr[6]) & M;
        long j8 = ((long) iArr[7]) & M;
        long j9 = j4 + j8;
        long j10 = j7 + (j8 << 1);
        long j11 = j3 + j10;
        long j12 = j6 + (j10 << 1);
        long j13 = j2 + j12;
        long j14 = j5 + (j12 << 1);
        long j15 = j + j14;
        iArr2[0] = (int) j15;
        long j16 = j13 + (j15 >>> 32);
        iArr2[1] = (int) j16;
        long j17 = j11 + (j16 >>> 32);
        iArr2[2] = (int) j17;
        long j18 = j9 + (j14 << 1) + (j17 >>> 32);
        iArr2[3] = (int) j18;
        reduce32((int) (j18 >>> 32), iArr2);
    }

    public static void reduce32(int i, int[] iArr) {
        while (i != 0) {
            long j = ((long) i) & M;
            long j2 = (((long) iArr[0]) & M) + j;
            iArr[0] = (int) j2;
            long j3 = j2 >> 32;
            if (j3 != 0) {
                long j4 = j3 + (((long) iArr[1]) & M);
                iArr[1] = (int) j4;
                long j5 = (j4 >> 32) + (((long) iArr[2]) & M);
                iArr[2] = (int) j5;
                j3 = j5 >> 32;
            }
            long j6 = j3 + (M & ((long) iArr[3])) + (j << 1);
            iArr[3] = (int) j6;
            i = (int) (j6 >> 32);
        }
    }

    public static void square(int[] iArr, int[] iArr2) {
        int[] createExt = Nat128.createExt();
        Nat128.square(iArr, createExt);
        reduce(createExt, iArr2);
    }

    public static void squareN(int[] iArr, int i, int[] iArr2) {
        int[] createExt = Nat128.createExt();
        Nat128.square(iArr, createExt);
        reduce(createExt, iArr2);
        while (true) {
            i--;
            if (i > 0) {
                Nat128.square(iArr2, createExt);
                reduce(createExt, iArr2);
            } else {
                return;
            }
        }
    }

    public static void subtract(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat128.sub(iArr, iArr2, iArr3) != 0) {
            subPInvFrom(iArr3);
        }
    }

    public static void subtractExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat.sub(10, iArr, iArr2, iArr3) != 0) {
            int[] iArr4 = PExtInv;
            Nat.subFrom(iArr4.length, iArr4, iArr3);
        }
    }

    public static void twice(int[] iArr, int[] iArr2) {
        if (Nat.shiftUpBit(4, iArr, 0, iArr2) != 0 || (iArr2[3] == P3 && Nat128.gte(iArr2, P))) {
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
            long j4 = (j3 >> 32) + (((long) iArr[2]) & M);
            iArr[2] = (int) j4;
            j2 = j4 >> 32;
        }
        iArr[3] = (int) (j2 + (M & ((long) iArr[3])) + 2);
    }

    private static void subPInvFrom(int[] iArr) {
        long j = (((long) iArr[0]) & M) - 1;
        iArr[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            long j3 = j2 + (((long) iArr[1]) & M);
            iArr[1] = (int) j3;
            long j4 = (j3 >> 32) + (((long) iArr[2]) & M);
            iArr[2] = (int) j4;
            j2 = j4 >> 32;
        }
        iArr[3] = (int) (j2 + ((M & ((long) iArr[3])) - 2));
    }
}
