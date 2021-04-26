package org.spongycastle.pqc.math.ntru.polynomial;

import java.lang.reflect.Array;
import org.spongycastle.util.Arrays;

public class LongPolynomial5 {
    private long[] coeffs;
    private int numCoeffs;

    public LongPolynomial5(IntegerPolynomial integerPolynomial) {
        this.numCoeffs = integerPolynomial.coeffs.length;
        this.coeffs = new long[((this.numCoeffs + 4) / 5)];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < this.numCoeffs; i3++) {
            long[] jArr = this.coeffs;
            jArr[i] = jArr[i] | (((long) integerPolynomial.coeffs[i3]) << i2);
            i2 += 12;
            if (i2 >= 60) {
                i++;
                i2 = 0;
            }
        }
    }

    private LongPolynomial5(long[] jArr, int i) {
        this.coeffs = jArr;
        this.numCoeffs = i;
    }

    public LongPolynomial5 mult(TernaryPolynomial ternaryPolynomial) {
        int i;
        long j;
        long[][] jArr = (long[][]) Array.newInstance(long.class, 5, (this.coeffs.length + ((ternaryPolynomial.size() + 4) / 5)) - 1);
        int[] ones = ternaryPolynomial.getOnes();
        for (int i2 = 0; i2 != ones.length; i2++) {
            int i3 = ones[i2];
            int i4 = i3 / 5;
            int i5 = i3 - (i4 * 5);
            int i6 = i4;
            int i7 = 0;
            while (true) {
                long[] jArr2 = this.coeffs;
                if (i7 >= jArr2.length) {
                    break;
                }
                jArr[i5][i6] = (jArr[i5][i6] + jArr2[i7]) & 576319980446939135L;
                i6++;
                i7++;
            }
        }
        int[] negOnes = ternaryPolynomial.getNegOnes();
        for (int i8 = 0; i8 != negOnes.length; i8++) {
            int i9 = negOnes[i8];
            int i10 = i9 / 5;
            int i11 = i9 - (i10 * 5);
            int i12 = i10;
            int i13 = 0;
            while (true) {
                long[] jArr3 = this.coeffs;
                if (i13 >= jArr3.length) {
                    break;
                }
                jArr[i11][i12] = ((jArr[i11][i12] + 576601524159907840L) - jArr3[i13]) & 576319980446939135L;
                i12++;
                i13++;
            }
        }
        long[] copyOf = Arrays.copyOf(jArr[0], jArr[0].length + 1);
        for (int i14 = 1; i14 <= 4; i14++) {
            int i15 = i14 * 12;
            int i16 = 60 - i15;
            long j2 = (1 << i16) - 1;
            int length = jArr[i14].length;
            int i17 = 0;
            while (i17 < length) {
                copyOf[i17] = (copyOf[i17] + ((jArr[i14][i17] & j2) << i15)) & 576319980446939135L;
                i17++;
                copyOf[i17] = (copyOf[i17] + (jArr[i14][i17] >> i16)) & 576319980446939135L;
            }
        }
        int i18 = (this.numCoeffs % 5) * 12;
        for (int length2 = this.coeffs.length - 1; length2 < copyOf.length; length2++) {
            if (length2 == this.coeffs.length - 1) {
                j = this.numCoeffs == 5 ? 0 : copyOf[length2] >> i18;
                i = 0;
            } else {
                j = copyOf[length2];
                i = (length2 * 5) - this.numCoeffs;
            }
            int i19 = i / 5;
            int i20 = i - (i19 * 5);
            long j3 = j << (i20 * 12);
            long j4 = j >> ((5 - i20) * 12);
            copyOf[i19] = (copyOf[i19] + j3) & 576319980446939135L;
            int i21 = i19 + 1;
            if (i21 < this.coeffs.length) {
                copyOf[i21] = (copyOf[i21] + j4) & 576319980446939135L;
            }
        }
        return new LongPolynomial5(copyOf, this.numCoeffs);
    }

    public IntegerPolynomial toIntegerPolynomial() {
        int[] iArr = new int[this.numCoeffs];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < this.numCoeffs; i3++) {
            iArr[i3] = (int) ((this.coeffs[i] >> i2) & 2047);
            i2 += 12;
            if (i2 >= 60) {
                i++;
                i2 = 0;
            }
        }
        return new IntegerPolynomial(iArr);
    }
}
