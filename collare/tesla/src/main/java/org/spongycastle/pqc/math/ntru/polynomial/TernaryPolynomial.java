package org.spongycastle.pqc.math.ntru.polynomial;

public interface TernaryPolynomial extends Polynomial {
    void clear();

    int[] getNegOnes();

    int[] getOnes();

    @Override // org.spongycastle.pqc.math.ntru.polynomial.Polynomial
    IntegerPolynomial mult(IntegerPolynomial integerPolynomial);

    int size();
}
