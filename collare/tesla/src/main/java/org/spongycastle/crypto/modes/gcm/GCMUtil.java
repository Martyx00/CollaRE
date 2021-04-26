package org.spongycastle.crypto.modes.gcm;

import org.spongycastle.util.Pack;

public abstract class GCMUtil {
    private static final int E1 = -520093696;
    private static final long E1L = -2233785415175766016L;
    private static final int[] LOOKUP = generateLookup();

    private static int[] generateLookup() {
        int[] iArr = new int[256];
        for (int i = 0; i < 256; i++) {
            int i2 = 0;
            for (int i3 = 7; i3 >= 0; i3--) {
                if (((1 << i3) & i) != 0) {
                    i2 ^= E1 >>> (7 - i3);
                }
            }
            iArr[i] = i2;
        }
        return iArr;
    }

    public static byte[] oneAsBytes() {
        byte[] bArr = new byte[16];
        bArr[0] = Byte.MIN_VALUE;
        return bArr;
    }

    public static int[] oneAsInts() {
        int[] iArr = new int[4];
        iArr[0] = Integer.MIN_VALUE;
        return iArr;
    }

    public static long[] oneAsLongs() {
        long[] jArr = new long[2];
        jArr[0] = Long.MIN_VALUE;
        return jArr;
    }

    public static byte[] asBytes(int[] iArr) {
        byte[] bArr = new byte[16];
        Pack.intToBigEndian(iArr, bArr, 0);
        return bArr;
    }

    public static void asBytes(int[] iArr, byte[] bArr) {
        Pack.intToBigEndian(iArr, bArr, 0);
    }

    public static byte[] asBytes(long[] jArr) {
        byte[] bArr = new byte[16];
        Pack.longToBigEndian(jArr, bArr, 0);
        return bArr;
    }

    public static void asBytes(long[] jArr, byte[] bArr) {
        Pack.longToBigEndian(jArr, bArr, 0);
    }

    public static int[] asInts(byte[] bArr) {
        int[] iArr = new int[4];
        Pack.bigEndianToInt(bArr, 0, iArr);
        return iArr;
    }

    public static void asInts(byte[] bArr, int[] iArr) {
        Pack.bigEndianToInt(bArr, 0, iArr);
    }

    public static long[] asLongs(byte[] bArr) {
        long[] jArr = new long[2];
        Pack.bigEndianToLong(bArr, 0, jArr);
        return jArr;
    }

    public static void asLongs(byte[] bArr, long[] jArr) {
        Pack.bigEndianToLong(bArr, 0, jArr);
    }

    public static void multiply(byte[] bArr, byte[] bArr2) {
        int[] asInts = asInts(bArr);
        multiply(asInts, asInts(bArr2));
        asBytes(asInts, bArr);
    }

    public static void multiply(int[] iArr, int[] iArr2) {
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = i;
        int i4 = i2;
        int i5 = iArr[2];
        int i6 = iArr[3];
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        while (i7 < 4) {
            int i12 = iArr2[i7];
            int i13 = i6;
            int i14 = i5;
            int i15 = i4;
            int i16 = i3;
            int i17 = i11;
            int i18 = i10;
            int i19 = i9;
            int i20 = i8;
            for (int i21 = 0; i21 < 32; i21++) {
                int i22 = i12 >> 31;
                i12 <<= 1;
                i20 ^= i16 & i22;
                i19 ^= i15 & i22;
                i18 ^= i14 & i22;
                i17 ^= i22 & i13;
                i13 = (i13 >>> 1) | (i14 << 31);
                i14 = (i14 >>> 1) | (i15 << 31);
                i15 = (i15 >>> 1) | (i16 << 31);
                i16 = (i16 >>> 1) ^ (((i13 << 31) >> 8) & E1);
            }
            i7++;
            i8 = i20;
            i9 = i19;
            i10 = i18;
            i11 = i17;
            i3 = i16;
            i4 = i15;
            i5 = i14;
            i6 = i13;
        }
        iArr[0] = i8;
        iArr[1] = i9;
        iArr[2] = i10;
        iArr[3] = i11;
    }

    public static void multiply(long[] jArr, long[] jArr2) {
        long j = 0;
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = 0;
        for (int i = 0; i < 2; i++) {
            long j5 = jArr2[i];
            for (int i2 = 0; i2 < 64; i2++) {
                long j6 = j5 >> 63;
                j5 <<= 1;
                j ^= j2 & j6;
                j4 ^= j6 & j3;
                j3 = (j3 >>> 1) | (j2 << 63);
                j2 = (j2 >>> 1) ^ (((j3 << 63) >> 8) & E1L);
            }
        }
        jArr[0] = j;
        jArr[1] = j4;
    }

    public static void multiplyP(int[] iArr) {
        iArr[0] = ((shiftRight(iArr) >> 8) & E1) ^ iArr[0];
    }

    public static void multiplyP(int[] iArr, int[] iArr2) {
        iArr2[0] = ((shiftRight(iArr, iArr2) >> 8) & E1) ^ iArr2[0];
    }

    public static void multiplyP8(int[] iArr) {
        int shiftRightN = shiftRightN(iArr, 8);
        iArr[0] = LOOKUP[shiftRightN >>> 24] ^ iArr[0];
    }

    public static void multiplyP8(int[] iArr, int[] iArr2) {
        int shiftRightN = shiftRightN(iArr, 8, iArr2);
        iArr2[0] = LOOKUP[shiftRightN >>> 24] ^ iArr2[0];
    }

    static int shiftRight(int[] iArr) {
        int i = iArr[0];
        iArr[0] = i >>> 1;
        int i2 = iArr[1];
        iArr[1] = (i << 31) | (i2 >>> 1);
        int i3 = i2 << 31;
        int i4 = iArr[2];
        iArr[2] = i3 | (i4 >>> 1);
        int i5 = i4 << 31;
        int i6 = iArr[3];
        iArr[3] = i5 | (i6 >>> 1);
        return i6 << 31;
    }

    static int shiftRight(int[] iArr, int[] iArr2) {
        int i = iArr[0];
        iArr2[0] = i >>> 1;
        int i2 = iArr[1];
        iArr2[1] = (i << 31) | (i2 >>> 1);
        int i3 = i2 << 31;
        int i4 = iArr[2];
        iArr2[2] = i3 | (i4 >>> 1);
        int i5 = iArr[3];
        iArr2[3] = (i4 << 31) | (i5 >>> 1);
        return i5 << 31;
    }

    static long shiftRight(long[] jArr) {
        long j = jArr[0];
        jArr[0] = j >>> 1;
        long j2 = jArr[1];
        jArr[1] = (j << 63) | (j2 >>> 1);
        return j2 << 63;
    }

    static long shiftRight(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        jArr2[0] = j >>> 1;
        long j2 = jArr[1];
        jArr2[1] = (j << 63) | (j2 >>> 1);
        return j2 << 63;
    }

    static int shiftRightN(int[] iArr, int i) {
        int i2 = iArr[0];
        int i3 = 32 - i;
        iArr[0] = i2 >>> i;
        int i4 = iArr[1];
        iArr[1] = (i2 << i3) | (i4 >>> i);
        int i5 = i4 << i3;
        int i6 = iArr[2];
        iArr[2] = i5 | (i6 >>> i);
        int i7 = i6 << i3;
        int i8 = iArr[3];
        iArr[3] = (i8 >>> i) | i7;
        return i8 << i3;
    }

    static int shiftRightN(int[] iArr, int i, int[] iArr2) {
        int i2 = iArr[0];
        int i3 = 32 - i;
        iArr2[0] = i2 >>> i;
        int i4 = iArr[1];
        iArr2[1] = (i2 << i3) | (i4 >>> i);
        int i5 = i4 << i3;
        int i6 = iArr[2];
        iArr2[2] = i5 | (i6 >>> i);
        int i7 = iArr[3];
        iArr2[3] = (i7 >>> i) | (i6 << i3);
        return i7 << i3;
    }

    public static void xor(byte[] bArr, byte[] bArr2) {
        int i = 0;
        do {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
            int i2 = i + 1;
            bArr[i2] = (byte) (bArr[i2] ^ bArr2[i2]);
            int i3 = i2 + 1;
            bArr[i3] = (byte) (bArr[i3] ^ bArr2[i3]);
            int i4 = i3 + 1;
            bArr[i4] = (byte) (bArr[i4] ^ bArr2[i4]);
            i = i4 + 1;
        } while (i < 16);
    }

    public static void xor(byte[] bArr, byte[] bArr2, int i, int i2) {
        while (true) {
            i2--;
            if (i2 >= 0) {
                bArr[i2] = (byte) (bArr[i2] ^ bArr2[i + i2]);
            } else {
                return;
            }
        }
    }

    public static void xor(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int i = 0;
        do {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i]);
            int i2 = i + 1;
            bArr3[i2] = (byte) (bArr[i2] ^ bArr2[i2]);
            int i3 = i2 + 1;
            bArr3[i3] = (byte) (bArr[i3] ^ bArr2[i3]);
            int i4 = i3 + 1;
            bArr3[i4] = (byte) (bArr[i4] ^ bArr2[i4]);
            i = i4 + 1;
        } while (i < 16);
    }

    public static void xor(int[] iArr, int[] iArr2) {
        iArr[0] = iArr[0] ^ iArr2[0];
        iArr[1] = iArr[1] ^ iArr2[1];
        iArr[2] = iArr[2] ^ iArr2[2];
        iArr[3] = iArr2[3] ^ iArr[3];
    }

    public static void xor(int[] iArr, int[] iArr2, int[] iArr3) {
        iArr3[0] = iArr[0] ^ iArr2[0];
        iArr3[1] = iArr[1] ^ iArr2[1];
        iArr3[2] = iArr[2] ^ iArr2[2];
        iArr3[3] = iArr[3] ^ iArr2[3];
    }

    public static void xor(long[] jArr, long[] jArr2) {
        jArr[0] = jArr[0] ^ jArr2[0];
        jArr[1] = jArr[1] ^ jArr2[1];
    }

    public static void xor(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr2[1] ^ jArr[1];
    }
}
