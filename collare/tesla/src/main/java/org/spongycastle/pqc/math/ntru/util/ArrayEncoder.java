package org.spongycastle.pqc.math.ntru.util;

import java.io.InputStream;
import java.math.BigInteger;
import org.spongycastle.util.Arrays;

public class ArrayEncoder {
    private static final int[] BIT1_TABLE = {1, 1, 1, 0, 0, 0, 1, 0, 1};
    private static final int[] BIT2_TABLE = {1, 1, 1, 1, 0, 0, 0, 1, 0};
    private static final int[] BIT3_TABLE = {1, 0, 1, 0, 0, 1, 1, 1, 0};
    private static final int[] COEFF1_TABLE = {0, 0, 0, 1, 1, 1, -1, -1};
    private static final int[] COEFF2_TABLE = {0, 1, -1, 0, 1, -1, 0, 1};

    public static byte[] encodeModQ(int[] iArr, int i) {
        int numberOfLeadingZeros = 31 - Integer.numberOfLeadingZeros(i);
        byte[] bArr = new byte[(((iArr.length * numberOfLeadingZeros) + 7) / 8)];
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < iArr.length) {
            int i5 = i3;
            for (int i6 = 0; i6 < numberOfLeadingZeros; i6++) {
                bArr[i4] = (byte) ((((iArr[i2] >> i6) & 1) << i5) | bArr[i4]);
                if (i5 == 7) {
                    i4++;
                    i5 = 0;
                } else {
                    i5++;
                }
            }
            i2++;
            i3 = i5;
        }
        return bArr;
    }

    public static int[] decodeModQ(byte[] bArr, int i, int i2) {
        int[] iArr = new int[i];
        int numberOfLeadingZeros = 31 - Integer.numberOfLeadingZeros(i2);
        int i3 = i * numberOfLeadingZeros;
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            if (i5 > 0 && i5 % numberOfLeadingZeros == 0) {
                i4++;
            }
            iArr[i4] = iArr[i4] + (getBit(bArr, i5) << (i5 % numberOfLeadingZeros));
        }
        return iArr;
    }

    public static int[] decodeModQ(InputStream inputStream, int i, int i2) {
        return decodeModQ(Util.readFullLength(inputStream, (((31 - Integer.numberOfLeadingZeros(i2)) * i) + 7) / 8), i, i2);
    }

    public static int[] decodeMod3Sves(byte[] bArr, int i) {
        int[] iArr = new int[i];
        int i2 = 0;
        int i3 = 0;
        while (i2 < bArr.length * 8) {
            int i4 = i2 + 1;
            int i5 = i4 + 1;
            int i6 = i5 + 1;
            int bit = (getBit(bArr, i2) * 4) + (getBit(bArr, i4) * 2) + getBit(bArr, i5);
            int i7 = i3 + 1;
            iArr[i3] = COEFF1_TABLE[bit];
            i3 = i7 + 1;
            iArr[i7] = COEFF2_TABLE[bit];
            if (i3 > i - 2) {
                break;
            }
            i2 = i6;
        }
        return iArr;
    }

    public static byte[] encodeMod3Sves(int[] iArr) {
        byte[] bArr = new byte[(((((iArr.length * 3) + 1) / 2) + 7) / 8)];
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < (iArr.length / 2) * 2) {
            int i4 = i + 1;
            int i5 = iArr[i] + 1;
            int i6 = i4 + 1;
            int i7 = iArr[i4] + 1;
            if (i5 == 0 && i7 == 0) {
                throw new IllegalStateException("Illegal encoding!");
            }
            int i8 = (i5 * 3) + i7;
            int[] iArr2 = {BIT1_TABLE[i8], BIT2_TABLE[i8], BIT3_TABLE[i8]};
            for (int i9 = 0; i9 < 3; i9++) {
                bArr[i3] = (byte) (bArr[i3] | (iArr2[i9] << i2));
                if (i2 == 7) {
                    i3++;
                    i2 = 0;
                } else {
                    i2++;
                }
            }
            i = i6;
        }
        return bArr;
    }

    public static byte[] encodeMod3Tight(int[] iArr) {
        BigInteger bigInteger = BigInteger.ZERO;
        for (int length = iArr.length - 1; length >= 0; length--) {
            bigInteger = bigInteger.multiply(BigInteger.valueOf(3)).add(BigInteger.valueOf((long) (iArr[length] + 1)));
        }
        int bitLength = (BigInteger.valueOf(3).pow(iArr.length).bitLength() + 7) / 8;
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length >= bitLength) {
            return byteArray.length > bitLength ? Arrays.copyOfRange(byteArray, 1, byteArray.length) : byteArray;
        }
        byte[] bArr = new byte[bitLength];
        System.arraycopy(byteArray, 0, bArr, bitLength - byteArray.length, byteArray.length);
        return bArr;
    }

    public static int[] decodeMod3Tight(byte[] bArr, int i) {
        BigInteger bigInteger = new BigInteger(1, bArr);
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = bigInteger.mod(BigInteger.valueOf(3)).intValue() - 1;
            if (iArr[i2] > 1) {
                iArr[i2] = iArr[i2] - 3;
            }
            bigInteger = bigInteger.divide(BigInteger.valueOf(3));
        }
        return iArr;
    }

    public static int[] decodeMod3Tight(InputStream inputStream, int i) {
        return decodeMod3Tight(Util.readFullLength(inputStream, (int) Math.ceil(((((double) i) * Math.log(3.0d)) / Math.log(2.0d)) / 8.0d)), i);
    }

    private static int getBit(byte[] bArr, int i) {
        return ((bArr[i / 8] & 255) >> (i % 8)) & 1;
    }
}
