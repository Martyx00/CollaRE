package org.spongycastle.pqc.math.linearalgebra;

public class GF2nPolynomial {
    private GF2nElement[] coeff;
    private int size;

    public GF2nPolynomial(int i, GF2nElement gF2nElement) {
        this.size = i;
        this.coeff = new GF2nElement[this.size];
        for (int i2 = 0; i2 < this.size; i2++) {
            this.coeff[i2] = (GF2nElement) gF2nElement.clone();
        }
    }

    private GF2nPolynomial(int i) {
        this.size = i;
        this.coeff = new GF2nElement[this.size];
    }

    public GF2nPolynomial(GF2nPolynomial gF2nPolynomial) {
        int i = gF2nPolynomial.size;
        this.coeff = new GF2nElement[i];
        this.size = i;
        for (int i2 = 0; i2 < this.size; i2++) {
            this.coeff[i2] = (GF2nElement) gF2nPolynomial.coeff[i2].clone();
        }
    }

    public GF2nPolynomial(GF2Polynomial gF2Polynomial, GF2nField gF2nField) {
        this.size = gF2nField.getDegree() + 1;
        this.coeff = new GF2nElement[this.size];
        int i = 0;
        if (gF2nField instanceof GF2nONBField) {
            while (i < this.size) {
                if (gF2Polynomial.testBit(i)) {
                    this.coeff[i] = GF2nONBElement.ONE((GF2nONBField) gF2nField);
                } else {
                    this.coeff[i] = GF2nONBElement.ZERO((GF2nONBField) gF2nField);
                }
                i++;
            }
        } else if (gF2nField instanceof GF2nPolynomialField) {
            while (i < this.size) {
                if (gF2Polynomial.testBit(i)) {
                    this.coeff[i] = GF2nPolynomialElement.ONE((GF2nPolynomialField) gF2nField);
                } else {
                    this.coeff[i] = GF2nPolynomialElement.ZERO((GF2nPolynomialField) gF2nField);
                }
                i++;
            }
        } else {
            throw new IllegalArgumentException("PolynomialGF2n(Bitstring, GF2nField): B1 must be an instance of GF2nONBField or GF2nPolynomialField!");
        }
    }

    public final void assignZeroToElements() {
        for (int i = 0; i < this.size; i++) {
            this.coeff[i].assignZero();
        }
    }

    public final int size() {
        return this.size;
    }

    public final int getDegree() {
        for (int i = this.size - 1; i >= 0; i--) {
            if (!this.coeff[i].isZero()) {
                return i;
            }
        }
        return -1;
    }

    public final void enlarge(int i) {
        int i2 = this.size;
        if (i > i2) {
            GF2nElement[] gF2nElementArr = new GF2nElement[i];
            System.arraycopy(this.coeff, 0, gF2nElementArr, 0, i2);
            GF2nField field = this.coeff[0].getField();
            GF2nElement[] gF2nElementArr2 = this.coeff;
            if (gF2nElementArr2[0] instanceof GF2nPolynomialElement) {
                for (int i3 = this.size; i3 < i; i3++) {
                    gF2nElementArr[i3] = GF2nPolynomialElement.ZERO((GF2nPolynomialField) field);
                }
            } else if (gF2nElementArr2[0] instanceof GF2nONBElement) {
                for (int i4 = this.size; i4 < i; i4++) {
                    gF2nElementArr[i4] = GF2nONBElement.ZERO((GF2nONBField) field);
                }
            }
            this.size = i;
            this.coeff = gF2nElementArr;
        }
    }

    public final void shrink() {
        int i = this.size;
        while (true) {
            i--;
            if (!this.coeff[i].isZero() || i <= 0) {
                int i2 = i + 1;
            }
        }
        int i22 = i + 1;
        if (i22 < this.size) {
            GF2nElement[] gF2nElementArr = new GF2nElement[i22];
            System.arraycopy(this.coeff, 0, gF2nElementArr, 0, i22);
            this.coeff = gF2nElementArr;
            this.size = i22;
        }
    }

    public final void set(int i, GF2nElement gF2nElement) {
        if ((gF2nElement instanceof GF2nPolynomialElement) || (gF2nElement instanceof GF2nONBElement)) {
            this.coeff[i] = (GF2nElement) gF2nElement.clone();
            return;
        }
        throw new IllegalArgumentException("PolynomialGF2n.set f must be an instance of either GF2nPolynomialElement or GF2nONBElement!");
    }

    public final GF2nElement at(int i) {
        return this.coeff[i];
    }

    public final boolean isZero() {
        for (int i = 0; i < this.size; i++) {
            GF2nElement[] gF2nElementArr = this.coeff;
            if (!(gF2nElementArr[i] == null || gF2nElementArr[i].isZero())) {
                return false;
            }
        }
        return true;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GF2nPolynomial)) {
            return false;
        }
        GF2nPolynomial gF2nPolynomial = (GF2nPolynomial) obj;
        if (getDegree() != gF2nPolynomial.getDegree()) {
            return false;
        }
        for (int i = 0; i < this.size; i++) {
            if (!this.coeff[i].equals(gF2nPolynomial.coeff[i])) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return getDegree() + this.coeff.hashCode();
    }

    public final GF2nPolynomial add(GF2nPolynomial gF2nPolynomial) {
        GF2nPolynomial gF2nPolynomial2;
        int i = 0;
        if (size() >= gF2nPolynomial.size()) {
            gF2nPolynomial2 = new GF2nPolynomial(size());
            while (i < gF2nPolynomial.size()) {
                gF2nPolynomial2.coeff[i] = (GF2nElement) this.coeff[i].add(gF2nPolynomial.coeff[i]);
                i++;
            }
            while (i < size()) {
                gF2nPolynomial2.coeff[i] = this.coeff[i];
                i++;
            }
        } else {
            gF2nPolynomial2 = new GF2nPolynomial(gF2nPolynomial.size());
            while (i < size()) {
                gF2nPolynomial2.coeff[i] = (GF2nElement) this.coeff[i].add(gF2nPolynomial.coeff[i]);
                i++;
            }
            while (i < gF2nPolynomial.size()) {
                gF2nPolynomial2.coeff[i] = gF2nPolynomial.coeff[i];
                i++;
            }
        }
        return gF2nPolynomial2;
    }

    public final GF2nPolynomial scalarMultiply(GF2nElement gF2nElement) {
        GF2nPolynomial gF2nPolynomial = new GF2nPolynomial(size());
        for (int i = 0; i < size(); i++) {
            gF2nPolynomial.coeff[i] = (GF2nElement) this.coeff[i].multiply(gF2nElement);
        }
        return gF2nPolynomial;
    }

    public final GF2nPolynomial multiply(GF2nPolynomial gF2nPolynomial) {
        int size2 = size();
        if (size2 == gF2nPolynomial.size()) {
            GF2nPolynomial gF2nPolynomial2 = new GF2nPolynomial((size2 << 1) - 1);
            for (int i = 0; i < size(); i++) {
                for (int i2 = 0; i2 < gF2nPolynomial.size(); i2++) {
                    GF2nElement[] gF2nElementArr = gF2nPolynomial2.coeff;
                    int i3 = i + i2;
                    if (gF2nElementArr[i3] == null) {
                        gF2nElementArr[i3] = (GF2nElement) this.coeff[i].multiply(gF2nPolynomial.coeff[i2]);
                    } else {
                        gF2nElementArr[i3] = (GF2nElement) gF2nElementArr[i3].add(this.coeff[i].multiply(gF2nPolynomial.coeff[i2]));
                    }
                }
            }
            return gF2nPolynomial2;
        }
        throw new IllegalArgumentException("PolynomialGF2n.multiply: this and b must have the same size!");
    }

    public final GF2nPolynomial multiplyAndReduce(GF2nPolynomial gF2nPolynomial, GF2nPolynomial gF2nPolynomial2) {
        return multiply(gF2nPolynomial).reduce(gF2nPolynomial2);
    }

    public final GF2nPolynomial reduce(GF2nPolynomial gF2nPolynomial) {
        return remainder(gF2nPolynomial);
    }

    public final void shiftThisLeft(int i) {
        if (i > 0) {
            int i2 = this.size;
            GF2nField field = this.coeff[0].getField();
            enlarge(this.size + i);
            for (int i3 = i2 - 1; i3 >= 0; i3--) {
                GF2nElement[] gF2nElementArr = this.coeff;
                gF2nElementArr[i3 + i] = gF2nElementArr[i3];
            }
            GF2nElement[] gF2nElementArr2 = this.coeff;
            if (gF2nElementArr2[0] instanceof GF2nPolynomialElement) {
                for (int i4 = i - 1; i4 >= 0; i4--) {
                    this.coeff[i4] = GF2nPolynomialElement.ZERO((GF2nPolynomialField) field);
                }
            } else if (gF2nElementArr2[0] instanceof GF2nONBElement) {
                for (int i5 = i - 1; i5 >= 0; i5--) {
                    this.coeff[i5] = GF2nONBElement.ZERO((GF2nONBField) field);
                }
            }
        }
    }

    public final GF2nPolynomial shiftLeft(int i) {
        if (i <= 0) {
            return new GF2nPolynomial(this);
        }
        GF2nPolynomial gF2nPolynomial = new GF2nPolynomial(this.size + i, this.coeff[0]);
        gF2nPolynomial.assignZeroToElements();
        for (int i2 = 0; i2 < this.size; i2++) {
            gF2nPolynomial.coeff[i2 + i] = this.coeff[i2];
        }
        return gF2nPolynomial;
    }

    public final GF2nPolynomial[] divide(GF2nPolynomial gF2nPolynomial) {
        GF2nPolynomial[] gF2nPolynomialArr = new GF2nPolynomial[2];
        GF2nPolynomial gF2nPolynomial2 = new GF2nPolynomial(this);
        gF2nPolynomial2.shrink();
        int degree = gF2nPolynomial.getDegree();
        GF2nElement gF2nElement = (GF2nElement) gF2nPolynomial.coeff[degree].invert();
        if (gF2nPolynomial2.getDegree() < degree) {
            gF2nPolynomialArr[0] = new GF2nPolynomial(this);
            gF2nPolynomialArr[0].assignZeroToElements();
            gF2nPolynomialArr[0].shrink();
            gF2nPolynomialArr[1] = new GF2nPolynomial(this);
            gF2nPolynomialArr[1].shrink();
            return gF2nPolynomialArr;
        }
        gF2nPolynomialArr[0] = new GF2nPolynomial(this);
        gF2nPolynomialArr[0].assignZeroToElements();
        int degree2 = gF2nPolynomial2.getDegree() - degree;
        while (degree2 >= 0) {
            GF2nElement gF2nElement2 = (GF2nElement) gF2nPolynomial2.coeff[gF2nPolynomial2.getDegree()].multiply(gF2nElement);
            GF2nPolynomial scalarMultiply = gF2nPolynomial.scalarMultiply(gF2nElement2);
            scalarMultiply.shiftThisLeft(degree2);
            gF2nPolynomial2 = gF2nPolynomial2.add(scalarMultiply);
            gF2nPolynomial2.shrink();
            gF2nPolynomialArr[0].coeff[degree2] = (GF2nElement) gF2nElement2.clone();
            degree2 = gF2nPolynomial2.getDegree() - degree;
        }
        gF2nPolynomialArr[1] = gF2nPolynomial2;
        gF2nPolynomialArr[0].shrink();
        return gF2nPolynomialArr;
    }

    public final GF2nPolynomial remainder(GF2nPolynomial gF2nPolynomial) {
        GF2nPolynomial[] gF2nPolynomialArr = new GF2nPolynomial[2];
        return divide(gF2nPolynomial)[1];
    }

    public final GF2nPolynomial quotient(GF2nPolynomial gF2nPolynomial) {
        GF2nPolynomial[] gF2nPolynomialArr = new GF2nPolynomial[2];
        return divide(gF2nPolynomial)[0];
    }

    public final GF2nPolynomial gcd(GF2nPolynomial gF2nPolynomial) {
        GF2nPolynomial gF2nPolynomial2 = new GF2nPolynomial(this);
        GF2nPolynomial gF2nPolynomial3 = new GF2nPolynomial(gF2nPolynomial);
        gF2nPolynomial2.shrink();
        gF2nPolynomial3.shrink();
        GF2nPolynomial gF2nPolynomial4 = gF2nPolynomial2;
        GF2nPolynomial gF2nPolynomial5 = gF2nPolynomial3;
        while (!gF2nPolynomial5.isZero()) {
            gF2nPolynomial5 = gF2nPolynomial4.remainder(gF2nPolynomial5);
            gF2nPolynomial4 = gF2nPolynomial5;
        }
        return gF2nPolynomial4.scalarMultiply((GF2nElement) gF2nPolynomial4.coeff[gF2nPolynomial4.getDegree()].invert());
    }
}
