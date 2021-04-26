package org.spongycastle.pqc.math.ntru.polynomial;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import org.spongycastle.util.Arrays;

public class BigIntPolynomial {
    private static final double LOG_10_2 = Math.log10(2.0d);
    BigInteger[] coeffs;

    BigIntPolynomial(int i) {
        this.coeffs = new BigInteger[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.coeffs[i2] = Constants.BIGINT_ZERO;
        }
    }

    BigIntPolynomial(BigInteger[] bigIntegerArr) {
        this.coeffs = bigIntegerArr;
    }

    public BigIntPolynomial(IntegerPolynomial integerPolynomial) {
        this.coeffs = new BigInteger[integerPolynomial.coeffs.length];
        int i = 0;
        while (true) {
            BigInteger[] bigIntegerArr = this.coeffs;
            if (i < bigIntegerArr.length) {
                bigIntegerArr[i] = BigInteger.valueOf((long) integerPolynomial.coeffs[i]);
                i++;
            } else {
                return;
            }
        }
    }

    static BigIntPolynomial generateRandomSmall(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        for (int i4 = 0; i4 < i2; i4++) {
            arrayList.add(Constants.BIGINT_ONE);
        }
        for (int i5 = 0; i5 < i3; i5++) {
            arrayList.add(BigInteger.valueOf(-1));
        }
        while (arrayList.size() < i) {
            arrayList.add(Constants.BIGINT_ZERO);
        }
        Collections.shuffle(arrayList, new SecureRandom());
        BigIntPolynomial bigIntPolynomial = new BigIntPolynomial(i);
        for (int i6 = 0; i6 < arrayList.size(); i6++) {
            bigIntPolynomial.coeffs[i6] = (BigInteger) arrayList.get(i6);
        }
        return bigIntPolynomial;
    }

    public BigIntPolynomial mult(BigIntPolynomial bigIntPolynomial) {
        BigInteger[] bigIntegerArr;
        int length = this.coeffs.length;
        if (bigIntPolynomial.coeffs.length == length) {
            BigIntPolynomial multRecursive = multRecursive(bigIntPolynomial);
            if (multRecursive.coeffs.length > length) {
                int i = length;
                while (true) {
                    bigIntegerArr = multRecursive.coeffs;
                    if (i >= bigIntegerArr.length) {
                        break;
                    }
                    int i2 = i - length;
                    bigIntegerArr[i2] = bigIntegerArr[i2].add(bigIntegerArr[i]);
                    i++;
                }
                multRecursive.coeffs = Arrays.copyOf(bigIntegerArr, length);
            }
            return multRecursive;
        }
        throw new IllegalArgumentException("Number of coefficients must be the same");
    }

    private BigIntPolynomial multRecursive(BigIntPolynomial bigIntPolynomial) {
        BigInteger[] bigIntegerArr = this.coeffs;
        BigInteger[] bigIntegerArr2 = bigIntPolynomial.coeffs;
        int length = bigIntegerArr2.length;
        int i = 0;
        if (length <= 1) {
            BigInteger[] clone = Arrays.clone(bigIntegerArr);
            for (int i2 = 0; i2 < this.coeffs.length; i2++) {
                clone[i2] = clone[i2].multiply(bigIntPolynomial.coeffs[0]);
            }
            return new BigIntPolynomial(clone);
        }
        int i3 = length / 2;
        BigIntPolynomial bigIntPolynomial2 = new BigIntPolynomial(Arrays.copyOf(bigIntegerArr, i3));
        BigIntPolynomial bigIntPolynomial3 = new BigIntPolynomial(Arrays.copyOfRange(bigIntegerArr, i3, length));
        BigIntPolynomial bigIntPolynomial4 = new BigIntPolynomial(Arrays.copyOf(bigIntegerArr2, i3));
        BigIntPolynomial bigIntPolynomial5 = new BigIntPolynomial(Arrays.copyOfRange(bigIntegerArr2, i3, length));
        BigIntPolynomial bigIntPolynomial6 = (BigIntPolynomial) bigIntPolynomial2.clone();
        bigIntPolynomial6.add(bigIntPolynomial3);
        BigIntPolynomial bigIntPolynomial7 = (BigIntPolynomial) bigIntPolynomial4.clone();
        bigIntPolynomial7.add(bigIntPolynomial5);
        BigIntPolynomial multRecursive = bigIntPolynomial2.multRecursive(bigIntPolynomial4);
        BigIntPolynomial multRecursive2 = bigIntPolynomial3.multRecursive(bigIntPolynomial5);
        BigIntPolynomial multRecursive3 = bigIntPolynomial6.multRecursive(bigIntPolynomial7);
        multRecursive3.sub(multRecursive);
        multRecursive3.sub(multRecursive2);
        BigIntPolynomial bigIntPolynomial8 = new BigIntPolynomial((length * 2) - 1);
        int i4 = 0;
        while (true) {
            BigInteger[] bigIntegerArr3 = multRecursive.coeffs;
            if (i4 >= bigIntegerArr3.length) {
                break;
            }
            bigIntPolynomial8.coeffs[i4] = bigIntegerArr3[i4];
            i4++;
        }
        int i5 = 0;
        while (true) {
            BigInteger[] bigIntegerArr4 = multRecursive3.coeffs;
            if (i5 >= bigIntegerArr4.length) {
                break;
            }
            BigInteger[] bigIntegerArr5 = bigIntPolynomial8.coeffs;
            int i6 = i3 + i5;
            bigIntegerArr5[i6] = bigIntegerArr5[i6].add(bigIntegerArr4[i5]);
            i5++;
        }
        while (true) {
            BigInteger[] bigIntegerArr6 = multRecursive2.coeffs;
            if (i >= bigIntegerArr6.length) {
                return bigIntPolynomial8;
            }
            BigInteger[] bigIntegerArr7 = bigIntPolynomial8.coeffs;
            int i7 = (i3 * 2) + i;
            bigIntegerArr7[i7] = bigIntegerArr7[i7].add(bigIntegerArr6[i]);
            i++;
        }
    }

    /* access modifiers changed from: package-private */
    public void add(BigIntPolynomial bigIntPolynomial, BigInteger bigInteger) {
        add(bigIntPolynomial);
        mod(bigInteger);
    }

    public void add(BigIntPolynomial bigIntPolynomial) {
        BigInteger[] bigIntegerArr = bigIntPolynomial.coeffs;
        int length = bigIntegerArr.length;
        BigInteger[] bigIntegerArr2 = this.coeffs;
        if (length > bigIntegerArr2.length) {
            int length2 = bigIntegerArr2.length;
            this.coeffs = Arrays.copyOf(bigIntegerArr2, bigIntegerArr.length);
            while (true) {
                BigInteger[] bigIntegerArr3 = this.coeffs;
                if (length2 >= bigIntegerArr3.length) {
                    break;
                }
                bigIntegerArr3[length2] = Constants.BIGINT_ZERO;
                length2++;
            }
        }
        int i = 0;
        while (true) {
            BigInteger[] bigIntegerArr4 = bigIntPolynomial.coeffs;
            if (i < bigIntegerArr4.length) {
                BigInteger[] bigIntegerArr5 = this.coeffs;
                bigIntegerArr5[i] = bigIntegerArr5[i].add(bigIntegerArr4[i]);
                i++;
            } else {
                return;
            }
        }
    }

    public void sub(BigIntPolynomial bigIntPolynomial) {
        BigInteger[] bigIntegerArr = bigIntPolynomial.coeffs;
        int length = bigIntegerArr.length;
        BigInteger[] bigIntegerArr2 = this.coeffs;
        if (length > bigIntegerArr2.length) {
            int length2 = bigIntegerArr2.length;
            this.coeffs = Arrays.copyOf(bigIntegerArr2, bigIntegerArr.length);
            while (true) {
                BigInteger[] bigIntegerArr3 = this.coeffs;
                if (length2 >= bigIntegerArr3.length) {
                    break;
                }
                bigIntegerArr3[length2] = Constants.BIGINT_ZERO;
                length2++;
            }
        }
        int i = 0;
        while (true) {
            BigInteger[] bigIntegerArr4 = bigIntPolynomial.coeffs;
            if (i < bigIntegerArr4.length) {
                BigInteger[] bigIntegerArr5 = this.coeffs;
                bigIntegerArr5[i] = bigIntegerArr5[i].subtract(bigIntegerArr4[i]);
                i++;
            } else {
                return;
            }
        }
    }

    public void mult(BigInteger bigInteger) {
        int i = 0;
        while (true) {
            BigInteger[] bigIntegerArr = this.coeffs;
            if (i < bigIntegerArr.length) {
                bigIntegerArr[i] = bigIntegerArr[i].multiply(bigInteger);
                i++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void mult(int i) {
        mult(BigInteger.valueOf((long) i));
    }

    public void div(BigInteger bigInteger) {
        BigInteger divide = bigInteger.add(Constants.BIGINT_ONE).divide(BigInteger.valueOf(2));
        int i = 0;
        while (true) {
            BigInteger[] bigIntegerArr = this.coeffs;
            if (i < bigIntegerArr.length) {
                bigIntegerArr[i] = bigIntegerArr[i].compareTo(Constants.BIGINT_ZERO) > 0 ? this.coeffs[i].add(divide) : this.coeffs[i].add(divide.negate());
                BigInteger[] bigIntegerArr2 = this.coeffs;
                bigIntegerArr2[i] = bigIntegerArr2[i].divide(bigInteger);
                i++;
            } else {
                return;
            }
        }
    }

    public BigDecimalPolynomial div(BigDecimal bigDecimal, int i) {
        BigDecimal divide = Constants.BIGDEC_ONE.divide(bigDecimal, ((int) (((double) maxCoeffAbs().bitLength()) * LOG_10_2)) + 1 + i + 1, 6);
        BigDecimalPolynomial bigDecimalPolynomial = new BigDecimalPolynomial(this.coeffs.length);
        for (int i2 = 0; i2 < this.coeffs.length; i2++) {
            bigDecimalPolynomial.coeffs[i2] = new BigDecimal(this.coeffs[i2]).multiply(divide).setScale(i, 6);
        }
        return bigDecimalPolynomial;
    }

    public int getMaxCoeffLength() {
        return ((int) (((double) maxCoeffAbs().bitLength()) * LOG_10_2)) + 1;
    }

    private BigInteger maxCoeffAbs() {
        BigInteger abs = this.coeffs[0].abs();
        int i = 1;
        while (true) {
            BigInteger[] bigIntegerArr = this.coeffs;
            if (i >= bigIntegerArr.length) {
                return abs;
            }
            BigInteger abs2 = bigIntegerArr[i].abs();
            if (abs2.compareTo(abs) > 0) {
                abs = abs2;
            }
            i++;
        }
    }

    public void mod(BigInteger bigInteger) {
        int i = 0;
        while (true) {
            BigInteger[] bigIntegerArr = this.coeffs;
            if (i < bigIntegerArr.length) {
                bigIntegerArr[i] = bigIntegerArr[i].mod(bigInteger);
                i++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public BigInteger sumCoeffs() {
        BigInteger bigInteger = Constants.BIGINT_ZERO;
        int i = 0;
        while (true) {
            BigInteger[] bigIntegerArr = this.coeffs;
            if (i >= bigIntegerArr.length) {
                return bigInteger;
            }
            bigInteger = bigInteger.add(bigIntegerArr[i]);
            i++;
        }
    }

    public Object clone() {
        return new BigIntPolynomial((BigInteger[]) this.coeffs.clone());
    }

    public int hashCode() {
        return 31 + Arrays.hashCode(this.coeffs);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && Arrays.areEqual(this.coeffs, ((BigIntPolynomial) obj).coeffs);
    }

    public BigInteger[] getCoeffs() {
        return Arrays.clone(this.coeffs);
    }
}
