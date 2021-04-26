package org.spongycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat160;
import org.spongycastle.util.Arrays;

public class SecP160R1FieldElement extends ECFieldElement {
    public static final BigInteger Q = SecP160R1Curve.q;
    protected int[] x;

    @Override // org.spongycastle.math.ec.ECFieldElement
    public String getFieldName() {
        return "SecP160R1Field";
    }

    public SecP160R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP160R1FieldElement");
        }
        this.x = SecP160R1Field.fromBigInteger(bigInteger);
    }

    public SecP160R1FieldElement() {
        this.x = Nat160.create();
    }

    protected SecP160R1FieldElement(int[] iArr) {
        this.x = iArr;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean isZero() {
        return Nat160.isZero(this.x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean isOne() {
        return Nat160.isOne(this.x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean testBitZero() {
        return Nat160.getBit(this.x, 0) == 1;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat160.toBigInteger(this.x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public int getFieldSize() {
        return Q.bitLength();
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] create = Nat160.create();
        SecP160R1Field.add(this.x, ((SecP160R1FieldElement) eCFieldElement).x, create);
        return new SecP160R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement addOne() {
        int[] create = Nat160.create();
        SecP160R1Field.addOne(this.x, create);
        return new SecP160R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] create = Nat160.create();
        SecP160R1Field.subtract(this.x, ((SecP160R1FieldElement) eCFieldElement).x, create);
        return new SecP160R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] create = Nat160.create();
        SecP160R1Field.multiply(this.x, ((SecP160R1FieldElement) eCFieldElement).x, create);
        return new SecP160R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] create = Nat160.create();
        Mod.invert(SecP160R1Field.P, ((SecP160R1FieldElement) eCFieldElement).x, create);
        SecP160R1Field.multiply(create, this.x, create);
        return new SecP160R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement negate() {
        int[] create = Nat160.create();
        SecP160R1Field.negate(this.x, create);
        return new SecP160R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement square() {
        int[] create = Nat160.create();
        SecP160R1Field.square(this.x, create);
        return new SecP160R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement invert() {
        int[] create = Nat160.create();
        Mod.invert(SecP160R1Field.P, this.x, create);
        return new SecP160R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement sqrt() {
        int[] iArr = this.x;
        if (Nat160.isZero(iArr) || Nat160.isOne(iArr)) {
            return this;
        }
        int[] create = Nat160.create();
        SecP160R1Field.square(iArr, create);
        SecP160R1Field.multiply(create, iArr, create);
        int[] create2 = Nat160.create();
        SecP160R1Field.squareN(create, 2, create2);
        SecP160R1Field.multiply(create2, create, create2);
        SecP160R1Field.squareN(create2, 4, create);
        SecP160R1Field.multiply(create, create2, create);
        SecP160R1Field.squareN(create, 8, create2);
        SecP160R1Field.multiply(create2, create, create2);
        SecP160R1Field.squareN(create2, 16, create);
        SecP160R1Field.multiply(create, create2, create);
        SecP160R1Field.squareN(create, 32, create2);
        SecP160R1Field.multiply(create2, create, create2);
        SecP160R1Field.squareN(create2, 64, create);
        SecP160R1Field.multiply(create, create2, create);
        SecP160R1Field.square(create, create2);
        SecP160R1Field.multiply(create2, iArr, create2);
        SecP160R1Field.squareN(create2, 29, create2);
        SecP160R1Field.square(create2, create);
        if (Nat160.eq(iArr, create)) {
            return new SecP160R1FieldElement(create2);
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SecP160R1FieldElement)) {
            return false;
        }
        return Nat160.eq(this.x, ((SecP160R1FieldElement) obj).x);
    }

    public int hashCode() {
        return Q.hashCode() ^ Arrays.hashCode(this.x, 0, 5);
    }
}
