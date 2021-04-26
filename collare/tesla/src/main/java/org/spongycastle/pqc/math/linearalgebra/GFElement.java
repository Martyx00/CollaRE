package org.spongycastle.pqc.math.linearalgebra;

import java.math.BigInteger;

public interface GFElement {
    GFElement add(GFElement gFElement);

    void addToThis(GFElement gFElement);

    Object clone();

    boolean equals(Object obj);

    int hashCode();

    GFElement invert();

    boolean isOne();

    boolean isZero();

    GFElement multiply(GFElement gFElement);

    void multiplyThisBy(GFElement gFElement);

    GFElement subtract(GFElement gFElement);

    void subtractFromThis(GFElement gFElement);

    byte[] toByteArray();

    BigInteger toFlexiBigInt();

    String toString();

    String toString(int i);
}
