package org.spongycastle.pqc.math.ntru.polynomial;

import java.math.BigInteger;

public class Resultant {
    public BigInteger res;
    public BigIntPolynomial rho;

    Resultant(BigIntPolynomial bigIntPolynomial, BigInteger bigInteger) {
        this.rho = bigIntPolynomial;
        this.res = bigInteger;
    }
}
