package org.spongycastle.pqc.math.ntru.polynomial;

import org.spongycastle.util.Arrays;

public class LongPolynomial2 {
    private long[] coeffs;
    private int numCoeffs;

    public LongPolynomial2(IntegerPolynomial integerPolynomial) {
        int i;
        long j;
        this.numCoeffs = integerPolynomial.coeffs.length;
        this.coeffs = new long[((this.numCoeffs + 1) / 2)];
        int i2 = 0;
        for (int i3 = 0; i3 < this.numCoeffs; i3 = i) {
            int i4 = i3 + 1;
            int i5 = integerPolynomial.coeffs[i3];
            while (i5 < 0) {
                i5 += 2048;
            }
            if (i4 < this.numCoeffs) {
                i = i4 + 1;
                j = (long) integerPolynomial.coeffs[i4];
            } else {
                i = i4;
                j = 0;
            }
            while (j < 0) {
                j += 2048;
            }
            this.coeffs[i2] = ((long) i5) + (j << 24);
            i2++;
        }
    }

    private LongPolynomial2(long[] jArr) {
        this.coeffs = jArr;
    }

    private LongPolynomial2(int i) {
        this.coeffs = new long[i];
    }

    public LongPolynomial2 mult(LongPolynomial2 longPolynomial2) {
        long[] jArr;
        long[] jArr2;
        int length = this.coeffs.length;
        if (longPolynomial2.coeffs.length == length && this.numCoeffs == longPolynomial2.numCoeffs) {
            LongPolynomial2 multRecursive = multRecursive(longPolynomial2);
            if (multRecursive.coeffs.length > length) {
                if (this.numCoeffs % 2 == 0) {
                    int i = length;
                    while (true) {
                        jArr2 = multRecursive.coeffs;
                        if (i >= jArr2.length) {
                            break;
                        }
                        int i2 = i - length;
                        jArr2[i2] = (jArr2[i2] + jArr2[i]) & 34342963199L;
                        i++;
                    }
                    multRecursive.coeffs = Arrays.copyOf(jArr2, length);
                } else {
                    int i3 = length;
                    while (true) {
                        jArr = multRecursive.coeffs;
                        if (i3 >= jArr.length) {
                            break;
                        }
                        int i4 = i3 - length;
                        jArr[i4] = jArr[i4] + (jArr[i3 - 1] >> 24);
                        jArr[i4] = jArr[i4] + ((2047 & jArr[i3]) << 24);
                        jArr[i4] = jArr[i4] & 34342963199L;
                        i3++;
                    }
                    multRecursive.coeffs = Arrays.copyOf(jArr, length);
                    long[] jArr3 = multRecursive.coeffs;
                    int length2 = jArr3.length - 1;
                    jArr3[length2] = jArr3[length2] & 2047;
                }
            }
            LongPolynomial2 longPolynomial22 = new LongPolynomial2(multRecursive.coeffs);
            longPolynomial22.numCoeffs = this.numCoeffs;
            return longPolynomial22;
        }
        throw new IllegalArgumentException("Number of coefficients must be the same");
    }

    public IntegerPolynomial toIntegerPolynomial() {
        int[] iArr = new int[this.numCoeffs];
        int i = 0;
        int i2 = 0;
        while (true) {
            long[] jArr = this.coeffs;
            if (i >= jArr.length) {
                return new IntegerPolynomial(iArr);
            }
            int i3 = i2 + 1;
            iArr[i2] = (int) (jArr[i] & 2047);
            if (i3 < this.numCoeffs) {
                i2 = i3 + 1;
                iArr[i3] = (int) ((jArr[i] >> 24) & 2047);
            } else {
                i2 = i3;
            }
            i++;
        }
    }

    private LongPolynomial2 multRecursive(LongPolynomial2 longPolynomial2) {
        long[] jArr = this.coeffs;
        long[] jArr2 = longPolynomial2.coeffs;
        int length = jArr2.length;
        int i = 0;
        if (length <= 32) {
            int i2 = length * 2;
            LongPolynomial2 longPolynomial22 = new LongPolynomial2(new long[i2]);
            for (int i3 = 0; i3 < i2; i3++) {
                for (int max = Math.max(0, (i3 - length) + 1); max <= Math.min(i3, length - 1); max++) {
                    long j = jArr[i3 - max] * jArr2[max];
                    long[] jArr3 = longPolynomial22.coeffs;
                    jArr3[i3] = (jArr3[i3] + (j & ((j & 2047) + 34342961152L))) & 34342963199L;
                    int i4 = i3 + 1;
                    jArr3[i4] = (jArr3[i4] + ((j >>> 48) & 2047)) & 34342963199L;
                }
            }
            return longPolynomial22;
        }
        int i5 = length / 2;
        LongPolynomial2 longPolynomial23 = new LongPolynomial2(Arrays.copyOf(jArr, i5));
        LongPolynomial2 longPolynomial24 = new LongPolynomial2(Arrays.copyOfRange(jArr, i5, length));
        LongPolynomial2 longPolynomial25 = new LongPolynomial2(Arrays.copyOf(jArr2, i5));
        LongPolynomial2 longPolynomial26 = new LongPolynomial2(Arrays.copyOfRange(jArr2, i5, length));
        LongPolynomial2 longPolynomial27 = (LongPolynomial2) longPolynomial23.clone();
        longPolynomial27.add(longPolynomial24);
        LongPolynomial2 longPolynomial28 = (LongPolynomial2) longPolynomial25.clone();
        longPolynomial28.add(longPolynomial26);
        LongPolynomial2 multRecursive = longPolynomial23.multRecursive(longPolynomial25);
        LongPolynomial2 multRecursive2 = longPolynomial24.multRecursive(longPolynomial26);
        LongPolynomial2 multRecursive3 = longPolynomial27.multRecursive(longPolynomial28);
        multRecursive3.sub(multRecursive);
        multRecursive3.sub(multRecursive2);
        LongPolynomial2 longPolynomial29 = new LongPolynomial2(length * 2);
        int i6 = 0;
        while (true) {
            long[] jArr4 = multRecursive.coeffs;
            if (i6 >= jArr4.length) {
                break;
            }
            longPolynomial29.coeffs[i6] = jArr4[i6] & 34342963199L;
            i6++;
        }
        int i7 = 0;
        while (true) {
            long[] jArr5 = multRecursive3.coeffs;
            if (i7 >= jArr5.length) {
                break;
            }
            long[] jArr6 = longPolynomial29.coeffs;
            int i8 = i5 + i7;
            jArr6[i8] = (jArr6[i8] + jArr5[i7]) & 34342963199L;
            i7++;
        }
        while (true) {
            long[] jArr7 = multRecursive2.coeffs;
            if (i >= jArr7.length) {
                return longPolynomial29;
            }
            long[] jArr8 = longPolynomial29.coeffs;
            int i9 = (i5 * 2) + i;
            jArr8[i9] = (jArr8[i9] + jArr7[i]) & 34342963199L;
            i++;
        }
    }

    private void add(LongPolynomial2 longPolynomial2) {
        long[] jArr = longPolynomial2.coeffs;
        int length = jArr.length;
        long[] jArr2 = this.coeffs;
        if (length > jArr2.length) {
            this.coeffs = Arrays.copyOf(jArr2, jArr.length);
        }
        int i = 0;
        while (true) {
            long[] jArr3 = longPolynomial2.coeffs;
            if (i < jArr3.length) {
                long[] jArr4 = this.coeffs;
                jArr4[i] = (jArr4[i] + jArr3[i]) & 34342963199L;
                i++;
            } else {
                return;
            }
        }
    }

    private void sub(LongPolynomial2 longPolynomial2) {
        long[] jArr = longPolynomial2.coeffs;
        int length = jArr.length;
        long[] jArr2 = this.coeffs;
        if (length > jArr2.length) {
            this.coeffs = Arrays.copyOf(jArr2, jArr.length);
        }
        int i = 0;
        while (true) {
            long[] jArr3 = longPolynomial2.coeffs;
            if (i < jArr3.length) {
                long[] jArr4 = this.coeffs;
                jArr4[i] = 34342963199L & ((jArr4[i] + 140737496743936L) - jArr3[i]);
                i++;
            } else {
                return;
            }
        }
    }

    public void subAnd(LongPolynomial2 longPolynomial2, int i) {
        long j = (long) i;
        long j2 = (j << 24) + j;
        int i2 = 0;
        while (true) {
            long[] jArr = longPolynomial2.coeffs;
            if (i2 < jArr.length) {
                long[] jArr2 = this.coeffs;
                jArr2[i2] = ((jArr2[i2] + 140737496743936L) - jArr[i2]) & j2;
                i2++;
            } else {
                return;
            }
        }
    }

    public void mult2And(int i) {
        long j = (long) i;
        long j2 = (j << 24) + j;
        int i2 = 0;
        while (true) {
            long[] jArr = this.coeffs;
            if (i2 < jArr.length) {
                jArr[i2] = (jArr[i2] << 1) & j2;
                i2++;
            } else {
                return;
            }
        }
    }

    public Object clone() {
        LongPolynomial2 longPolynomial2 = new LongPolynomial2((long[]) this.coeffs.clone());
        longPolynomial2.numCoeffs = this.numCoeffs;
        return longPolynomial2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof LongPolynomial2) {
            return Arrays.areEqual(this.coeffs, ((LongPolynomial2) obj).coeffs);
        }
        return false;
    }
}
