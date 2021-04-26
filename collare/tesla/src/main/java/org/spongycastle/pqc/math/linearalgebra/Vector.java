package org.spongycastle.pqc.math.linearalgebra;

public abstract class Vector {
    protected int length;

    public abstract Vector add(Vector vector);

    public abstract boolean equals(Object obj);

    public abstract byte[] getEncoded();

    public abstract int hashCode();

    public abstract boolean isZero();

    public abstract Vector multiply(Permutation permutation);

    public abstract String toString();

    public final int getLength() {
        return this.length;
    }
}
