package org.spongycastle.pqc.crypto.rainbow.util;

import java.lang.reflect.Array;

public class ComputeInField {
    private short[][] A;
    short[] x;

    public short[] solveEquation(short[][] sArr, short[] sArr2) {
        try {
            if (sArr.length == sArr2.length) {
                this.A = (short[][]) Array.newInstance(short.class, sArr.length, sArr.length + 1);
                this.x = new short[sArr.length];
                for (int i = 0; i < sArr.length; i++) {
                    for (int i2 = 0; i2 < sArr[0].length; i2++) {
                        this.A[i][i2] = sArr[i][i2];
                    }
                }
                for (int i3 = 0; i3 < sArr2.length; i3++) {
                    this.A[i3][sArr2.length] = GF2Field.addElem(sArr2[i3], this.A[i3][sArr2.length]);
                }
                computeZerosUnder(false);
                substitute();
                return this.x;
            }
            throw new RuntimeException("The equation system is not solvable");
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public short[][] inverse(short[][] sArr) {
        try {
            this.A = (short[][]) Array.newInstance(short.class, sArr.length, sArr.length * 2);
            if (sArr.length == sArr[0].length) {
                for (int i = 0; i < sArr.length; i++) {
                    for (int i2 = 0; i2 < sArr.length; i2++) {
                        this.A[i][i2] = sArr[i][i2];
                    }
                    for (int length = sArr.length; length < sArr.length * 2; length++) {
                        this.A[i][length] = 0;
                    }
                    this.A[i][this.A.length + i] = 1;
                }
                computeZerosUnder(true);
                for (int i3 = 0; i3 < this.A.length; i3++) {
                    short invElem = GF2Field.invElem(this.A[i3][i3]);
                    for (int i4 = i3; i4 < this.A.length * 2; i4++) {
                        this.A[i3][i4] = GF2Field.multElem(this.A[i3][i4], invElem);
                    }
                }
                computeZerosAbove();
                short[][] sArr2 = (short[][]) Array.newInstance(short.class, this.A.length, this.A.length);
                for (int i5 = 0; i5 < this.A.length; i5++) {
                    for (int length2 = this.A.length; length2 < this.A.length * 2; length2++) {
                        sArr2[i5][length2 - this.A.length] = this.A[i5][length2];
                    }
                }
                return sArr2;
            }
            throw new RuntimeException("The matrix is not invertible. Please choose another one!");
        } catch (RuntimeException unused) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0058, code lost:
        r0 = r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void computeZerosUnder(boolean r10) {
        /*
            r9 = this;
            if (r10 == 0) goto L_0x0008
            short[][] r10 = r9.A
            int r10 = r10.length
            int r10 = r10 * 2
            goto L_0x000d
        L_0x0008:
            short[][] r10 = r9.A
            int r10 = r10.length
            int r10 = r10 + 1
        L_0x000d:
            r0 = 0
        L_0x000e:
            short[][] r1 = r9.A
            int r1 = r1.length
            int r1 = r1 + -1
            if (r0 >= r1) goto L_0x005a
            int r1 = r0 + 1
            r2 = r1
        L_0x0018:
            short[][] r3 = r9.A
            int r4 = r3.length
            if (r2 >= r4) goto L_0x0058
            r4 = r3[r2]
            short r4 = r4[r0]
            r3 = r3[r0]
            short r3 = r3[r0]
            short r3 = org.spongycastle.pqc.crypto.rainbow.util.GF2Field.invElem(r3)
            if (r3 == 0) goto L_0x0050
            r5 = r0
        L_0x002c:
            if (r5 >= r10) goto L_0x004d
            short[][] r6 = r9.A
            r6 = r6[r0]
            short r6 = r6[r5]
            short r6 = org.spongycastle.pqc.crypto.rainbow.util.GF2Field.multElem(r6, r3)
            short r6 = org.spongycastle.pqc.crypto.rainbow.util.GF2Field.multElem(r4, r6)
            short[][] r7 = r9.A
            r8 = r7[r2]
            r7 = r7[r2]
            short r7 = r7[r5]
            short r6 = org.spongycastle.pqc.crypto.rainbow.util.GF2Field.addElem(r7, r6)
            r8[r5] = r6
            int r5 = r5 + 1
            goto L_0x002c
        L_0x004d:
            int r2 = r2 + 1
            goto L_0x0018
        L_0x0050:
            java.lang.RuntimeException r10 = new java.lang.RuntimeException
            java.lang.String r0 = "Matrix not invertible! We have to choose another one!"
            r10.<init>(r0)
            throw r10
        L_0x0058:
            r0 = r1
            goto L_0x000e
        L_0x005a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.pqc.crypto.rainbow.util.ComputeInField.computeZerosUnder(boolean):void");
    }

    private void computeZerosAbove() {
        for (int length = this.A.length - 1; length > 0; length--) {
            for (int i = length - 1; i >= 0; i--) {
                short[][] sArr = this.A;
                short s = sArr[i][length];
                short invElem = GF2Field.invElem(sArr[length][length]);
                if (invElem != 0) {
                    int i2 = length;
                    while (true) {
                        short[][] sArr2 = this.A;
                        if (i2 >= sArr2.length * 2) {
                            break;
                        }
                        short multElem = GF2Field.multElem(s, GF2Field.multElem(sArr2[length][i2], invElem));
                        short[][] sArr3 = this.A;
                        sArr3[i][i2] = GF2Field.addElem(sArr3[i][i2], multElem);
                        i2++;
                    }
                } else {
                    throw new RuntimeException("The matrix is not invertible");
                }
            }
        }
    }

    private void substitute() {
        short[][] sArr = this.A;
        short invElem = GF2Field.invElem(sArr[sArr.length - 1][sArr.length - 1]);
        if (invElem != 0) {
            short[] sArr2 = this.x;
            short[][] sArr3 = this.A;
            sArr2[sArr3.length - 1] = GF2Field.multElem(sArr3[sArr3.length - 1][sArr3.length], invElem);
            for (int length = this.A.length - 2; length >= 0; length--) {
                short[][] sArr4 = this.A;
                short s = sArr4[length][sArr4.length];
                for (int length2 = sArr4.length - 1; length2 > length; length2--) {
                    s = GF2Field.addElem(s, GF2Field.multElem(this.A[length][length2], this.x[length2]));
                }
                short invElem2 = GF2Field.invElem(this.A[length][length]);
                if (invElem2 != 0) {
                    this.x[length] = GF2Field.multElem(s, invElem2);
                } else {
                    throw new RuntimeException("Not solvable equation system");
                }
            }
            return;
        }
        throw new RuntimeException("The equation system is not solvable");
    }

    public short[][] multiplyMatrix(short[][] sArr, short[][] sArr2) {
        if (sArr[0].length == sArr2.length) {
            this.A = (short[][]) Array.newInstance(short.class, sArr.length, sArr2[0].length);
            for (int i = 0; i < sArr.length; i++) {
                for (int i2 = 0; i2 < sArr2.length; i2++) {
                    for (int i3 = 0; i3 < sArr2[0].length; i3++) {
                        short multElem = GF2Field.multElem(sArr[i][i2], sArr2[i2][i3]);
                        short[][] sArr3 = this.A;
                        sArr3[i][i3] = GF2Field.addElem(sArr3[i][i3], multElem);
                    }
                }
            }
            return this.A;
        }
        throw new RuntimeException("Multiplication is not possible!");
    }

    public short[] multiplyMatrix(short[][] sArr, short[] sArr2) {
        if (sArr[0].length == sArr2.length) {
            short[] sArr3 = new short[sArr.length];
            for (int i = 0; i < sArr.length; i++) {
                for (int i2 = 0; i2 < sArr2.length; i2++) {
                    sArr3[i] = GF2Field.addElem(sArr3[i], GF2Field.multElem(sArr[i][i2], sArr2[i2]));
                }
            }
            return sArr3;
        }
        throw new RuntimeException("Multiplication is not possible!");
    }

    public short[] addVect(short[] sArr, short[] sArr2) {
        if (sArr.length == sArr2.length) {
            short[] sArr3 = new short[sArr.length];
            for (int i = 0; i < sArr3.length; i++) {
                sArr3[i] = GF2Field.addElem(sArr[i], sArr2[i]);
            }
            return sArr3;
        }
        throw new RuntimeException("Multiplication is not possible!");
    }

    public short[][] multVects(short[] sArr, short[] sArr2) {
        if (sArr.length == sArr2.length) {
            short[][] sArr3 = (short[][]) Array.newInstance(short.class, sArr.length, sArr2.length);
            for (int i = 0; i < sArr.length; i++) {
                for (int i2 = 0; i2 < sArr2.length; i2++) {
                    sArr3[i][i2] = GF2Field.multElem(sArr[i], sArr2[i2]);
                }
            }
            return sArr3;
        }
        throw new RuntimeException("Multiplication is not possible!");
    }

    public short[] multVect(short s, short[] sArr) {
        short[] sArr2 = new short[sArr.length];
        for (int i = 0; i < sArr2.length; i++) {
            sArr2[i] = GF2Field.multElem(s, sArr[i]);
        }
        return sArr2;
    }

    public short[][] multMatrix(short s, short[][] sArr) {
        short[][] sArr2 = (short[][]) Array.newInstance(short.class, sArr.length, sArr[0].length);
        for (int i = 0; i < sArr.length; i++) {
            for (int i2 = 0; i2 < sArr[0].length; i2++) {
                sArr2[i][i2] = GF2Field.multElem(s, sArr[i][i2]);
            }
        }
        return sArr2;
    }

    public short[][] addSquareMatrix(short[][] sArr, short[][] sArr2) {
        if (sArr.length == sArr2.length && sArr[0].length == sArr2[0].length) {
            short[][] sArr3 = (short[][]) Array.newInstance(short.class, sArr.length, sArr.length);
            for (int i = 0; i < sArr.length; i++) {
                for (int i2 = 0; i2 < sArr2.length; i2++) {
                    sArr3[i][i2] = GF2Field.addElem(sArr[i][i2], sArr2[i][i2]);
                }
            }
            return sArr3;
        }
        throw new RuntimeException("Addition is not possible!");
    }
}
