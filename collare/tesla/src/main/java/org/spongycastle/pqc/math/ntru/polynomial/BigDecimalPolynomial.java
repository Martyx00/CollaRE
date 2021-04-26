package org.spongycastle.pqc.math.ntru.polynomial;

import java.math.BigDecimal;

public class BigDecimalPolynomial {
    private static final BigDecimal ONE_HALF = new BigDecimal("0.5");
    private static final BigDecimal ZERO = new BigDecimal("0");
    BigDecimal[] coeffs;

    BigDecimalPolynomial(int i) {
        this.coeffs = new BigDecimal[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.coeffs[i2] = ZERO;
        }
    }

    BigDecimalPolynomial(BigDecimal[] bigDecimalArr) {
        this.coeffs = bigDecimalArr;
    }

    public BigDecimalPolynomial(BigIntPolynomial bigIntPolynomial) {
        int length = bigIntPolynomial.coeffs.length;
        this.coeffs = new BigDecimal[length];
        for (int i = 0; i < length; i++) {
            this.coeffs[i] = new BigDecimal(bigIntPolynomial.coeffs[i]);
        }
    }

    public void halve() {
        int i = 0;
        while (true) {
            BigDecimal[] bigDecimalArr = this.coeffs;
            if (i < bigDecimalArr.length) {
                bigDecimalArr[i] = bigDecimalArr[i].multiply(ONE_HALF);
                i++;
            } else {
                return;
            }
        }
    }

    public BigDecimalPolynomial mult(BigIntPolynomial bigIntPolynomial) {
        return mult(new BigDecimalPolynomial(bigIntPolynomial));
    }

    public BigDecimalPolynomial mult(BigDecimalPolynomial bigDecimalPolynomial) {
        BigDecimal[] bigDecimalArr;
        int length = this.coeffs.length;
        if (bigDecimalPolynomial.coeffs.length == length) {
            BigDecimalPolynomial multRecursive = multRecursive(bigDecimalPolynomial);
            if (multRecursive.coeffs.length > length) {
                int i = length;
                while (true) {
                    bigDecimalArr = multRecursive.coeffs;
                    if (i >= bigDecimalArr.length) {
                        break;
                    }
                    int i2 = i - length;
                    bigDecimalArr[i2] = bigDecimalArr[i2].add(bigDecimalArr[i]);
                    i++;
                }
                multRecursive.coeffs = copyOf(bigDecimalArr, length);
            }
            return multRecursive;
        }
        throw new IllegalArgumentException("Number of coefficients must be the same");
    }

    private BigDecimalPolynomial multRecursive(BigDecimalPolynomial bigDecimalPolynomial) {
        BigDecimal[] bigDecimalArr = this.coeffs;
        BigDecimal[] bigDecimalArr2 = bigDecimalPolynomial.coeffs;
        int length = bigDecimalArr2.length;
        int i = 0;
        if (length <= 1) {
            BigDecimal[] bigDecimalArr3 = (BigDecimal[]) bigDecimalArr.clone();
            for (int i2 = 0; i2 < this.coeffs.length; i2++) {
                bigDecimalArr3[i2] = bigDecimalArr3[i2].multiply(bigDecimalPolynomial.coeffs[0]);
            }
            return new BigDecimalPolynomial(bigDecimalArr3);
        }
        int i3 = length / 2;
        BigDecimalPolynomial bigDecimalPolynomial2 = new BigDecimalPolynomial(copyOf(bigDecimalArr, i3));
        BigDecimalPolynomial bigDecimalPolynomial3 = new BigDecimalPolynomial(copyOfRange(bigDecimalArr, i3, length));
        BigDecimalPolynomial bigDecimalPolynomial4 = new BigDecimalPolynomial(copyOf(bigDecimalArr2, i3));
        BigDecimalPolynomial bigDecimalPolynomial5 = new BigDecimalPolynomial(copyOfRange(bigDecimalArr2, i3, length));
        BigDecimalPolynomial bigDecimalPolynomial6 = (BigDecimalPolynomial) bigDecimalPolynomial2.clone();
        bigDecimalPolynomial6.add(bigDecimalPolynomial3);
        BigDecimalPolynomial bigDecimalPolynomial7 = (BigDecimalPolynomial) bigDecimalPolynomial4.clone();
        bigDecimalPolynomial7.add(bigDecimalPolynomial5);
        BigDecimalPolynomial multRecursive = bigDecimalPolynomial2.multRecursive(bigDecimalPolynomial4);
        BigDecimalPolynomial multRecursive2 = bigDecimalPolynomial3.multRecursive(bigDecimalPolynomial5);
        BigDecimalPolynomial multRecursive3 = bigDecimalPolynomial6.multRecursive(bigDecimalPolynomial7);
        multRecursive3.sub(multRecursive);
        multRecursive3.sub(multRecursive2);
        BigDecimalPolynomial bigDecimalPolynomial8 = new BigDecimalPolynomial((length * 2) - 1);
        int i4 = 0;
        while (true) {
            BigDecimal[] bigDecimalArr4 = multRecursive.coeffs;
            if (i4 >= bigDecimalArr4.length) {
                break;
            }
            bigDecimalPolynomial8.coeffs[i4] = bigDecimalArr4[i4];
            i4++;
        }
        int i5 = 0;
        while (true) {
            BigDecimal[] bigDecimalArr5 = multRecursive3.coeffs;
            if (i5 >= bigDecimalArr5.length) {
                break;
            }
            BigDecimal[] bigDecimalArr6 = bigDecimalPolynomial8.coeffs;
            int i6 = i3 + i5;
            bigDecimalArr6[i6] = bigDecimalArr6[i6].add(bigDecimalArr5[i5]);
            i5++;
        }
        while (true) {
            BigDecimal[] bigDecimalArr7 = multRecursive2.coeffs;
            if (i >= bigDecimalArr7.length) {
                return bigDecimalPolynomial8;
            }
            BigDecimal[] bigDecimalArr8 = bigDecimalPolynomial8.coeffs;
            int i7 = (i3 * 2) + i;
            bigDecimalArr8[i7] = bigDecimalArr8[i7].add(bigDecimalArr7[i]);
            i++;
        }
    }

    public void add(BigDecimalPolynomial bigDecimalPolynomial) {
        BigDecimal[] bigDecimalArr = bigDecimalPolynomial.coeffs;
        int length = bigDecimalArr.length;
        BigDecimal[] bigDecimalArr2 = this.coeffs;
        if (length > bigDecimalArr2.length) {
            int length2 = bigDecimalArr2.length;
            this.coeffs = copyOf(bigDecimalArr2, bigDecimalArr.length);
            while (true) {
                BigDecimal[] bigDecimalArr3 = this.coeffs;
                if (length2 >= bigDecimalArr3.length) {
                    break;
                }
                bigDecimalArr3[length2] = ZERO;
                length2++;
            }
        }
        int i = 0;
        while (true) {
            BigDecimal[] bigDecimalArr4 = bigDecimalPolynomial.coeffs;
            if (i < bigDecimalArr4.length) {
                BigDecimal[] bigDecimalArr5 = this.coeffs;
                bigDecimalArr5[i] = bigDecimalArr5[i].add(bigDecimalArr4[i]);
                i++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void sub(BigDecimalPolynomial bigDecimalPolynomial) {
        BigDecimal[] bigDecimalArr = bigDecimalPolynomial.coeffs;
        int length = bigDecimalArr.length;
        BigDecimal[] bigDecimalArr2 = this.coeffs;
        if (length > bigDecimalArr2.length) {
            int length2 = bigDecimalArr2.length;
            this.coeffs = copyOf(bigDecimalArr2, bigDecimalArr.length);
            while (true) {
                BigDecimal[] bigDecimalArr3 = this.coeffs;
                if (length2 >= bigDecimalArr3.length) {
                    break;
                }
                bigDecimalArr3[length2] = ZERO;
                length2++;
            }
        }
        int i = 0;
        while (true) {
            BigDecimal[] bigDecimalArr4 = bigDecimalPolynomial.coeffs;
            if (i < bigDecimalArr4.length) {
                BigDecimal[] bigDecimalArr5 = this.coeffs;
                bigDecimalArr5[i] = bigDecimalArr5[i].subtract(bigDecimalArr4[i]);
                i++;
            } else {
                return;
            }
        }
    }

    public BigIntPolynomial round() {
        int length = this.coeffs.length;
        BigIntPolynomial bigIntPolynomial = new BigIntPolynomial(length);
        for (int i = 0; i < length; i++) {
            bigIntPolynomial.coeffs[i] = this.coeffs[i].setScale(0, 6).toBigInteger();
        }
        return bigIntPolynomial;
    }

    public Object clone() {
        return new BigDecimalPolynomial((BigDecimal[]) this.coeffs.clone());
    }

    private BigDecimal[] copyOf(BigDecimal[] bigDecimalArr, int i) {
        BigDecimal[] bigDecimalArr2 = new BigDecimal[i];
        if (bigDecimalArr.length < i) {
            i = bigDecimalArr.length;
        }
        System.arraycopy(bigDecimalArr, 0, bigDecimalArr2, 0, i);
        return bigDecimalArr2;
    }

    private BigDecimal[] copyOfRange(BigDecimal[] bigDecimalArr, int i, int i2) {
        int i3 = i2 - i;
        BigDecimal[] bigDecimalArr2 = new BigDecimal[i3];
        if (bigDecimalArr.length - i < i3) {
            i3 = bigDecimalArr.length - i;
        }
        System.arraycopy(bigDecimalArr, i, bigDecimalArr2, 0, i3);
        return bigDecimalArr2;
    }

    public BigDecimal[] getCoeffs() {
        BigDecimal[] bigDecimalArr = this.coeffs;
        BigDecimal[] bigDecimalArr2 = new BigDecimal[bigDecimalArr.length];
        System.arraycopy(bigDecimalArr, 0, bigDecimalArr2, 0, bigDecimalArr.length);
        return bigDecimalArr2;
    }
}
