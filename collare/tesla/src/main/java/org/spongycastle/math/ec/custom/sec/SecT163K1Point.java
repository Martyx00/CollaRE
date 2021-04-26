package org.spongycastle.math.ec.custom.sec;

import org.spongycastle.math.ec.ECConstants;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.ec.ECPoint;

public class SecT163K1Point extends ECPoint.AbstractF2m {
    public SecT163K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    public SecT163K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
        if ((eCFieldElement == null) == (eCFieldElement2 != null ? false : true)) {
            this.withCompression = z;
            return;
        }
        throw new IllegalArgumentException("Exactly one of the field elements is null");
    }

    SecT163K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        this.withCompression = z;
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint detach() {
        return new SecT163K1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECFieldElement getYCoord() {
        ECFieldElement eCFieldElement = this.x;
        ECFieldElement eCFieldElement2 = this.y;
        if (isInfinity() || eCFieldElement.isZero()) {
            return eCFieldElement2;
        }
        ECFieldElement multiply = eCFieldElement2.add(eCFieldElement).multiply(eCFieldElement);
        ECFieldElement eCFieldElement3 = this.zs[0];
        return !eCFieldElement3.isOne() ? multiply.divide(eCFieldElement3) : multiply;
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.math.ec.ECPoint
    public boolean getCompressionYTilde() {
        ECFieldElement rawXCoord = getRawXCoord();
        if (!rawXCoord.isZero() && getRawYCoord().testBitZero() != rawXCoord.testBitZero()) {
            return true;
        }
        return false;
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint add(ECPoint eCPoint) {
        ECFieldElement eCFieldElement;
        ECFieldElement eCFieldElement2;
        ECFieldElement eCFieldElement3;
        ECFieldElement eCFieldElement4;
        ECFieldElement eCFieldElement5;
        ECFieldElement eCFieldElement6;
        if (isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        ECFieldElement eCFieldElement7 = this.x;
        ECFieldElement rawXCoord = eCPoint.getRawXCoord();
        if (!eCFieldElement7.isZero()) {
            ECFieldElement eCFieldElement8 = this.y;
            ECFieldElement eCFieldElement9 = this.zs[0];
            ECFieldElement rawYCoord = eCPoint.getRawYCoord();
            ECFieldElement zCoord = eCPoint.getZCoord(0);
            boolean isOne = eCFieldElement9.isOne();
            if (!isOne) {
                eCFieldElement2 = rawXCoord.multiply(eCFieldElement9);
                eCFieldElement = rawYCoord.multiply(eCFieldElement9);
            } else {
                eCFieldElement2 = rawXCoord;
                eCFieldElement = rawYCoord;
            }
            boolean isOne2 = zCoord.isOne();
            if (!isOne2) {
                eCFieldElement7 = eCFieldElement7.multiply(zCoord);
                eCFieldElement3 = eCFieldElement8.multiply(zCoord);
            } else {
                eCFieldElement3 = eCFieldElement8;
            }
            ECFieldElement add = eCFieldElement3.add(eCFieldElement);
            ECFieldElement add2 = eCFieldElement7.add(eCFieldElement2);
            if (!add2.isZero()) {
                if (rawXCoord.isZero()) {
                    ECPoint normalize = normalize();
                    ECFieldElement xCoord = normalize.getXCoord();
                    ECFieldElement yCoord = normalize.getYCoord();
                    ECFieldElement divide = yCoord.add(rawYCoord).divide(xCoord);
                    eCFieldElement6 = divide.square().add(divide).add(xCoord).addOne();
                    if (eCFieldElement6.isZero()) {
                        return new SecT163K1Point(curve, eCFieldElement6, curve.getB(), this.withCompression);
                    }
                    eCFieldElement5 = divide.multiply(xCoord.add(eCFieldElement6)).add(eCFieldElement6).add(yCoord).divide(eCFieldElement6).add(eCFieldElement6);
                    eCFieldElement4 = curve.fromBigInteger(ECConstants.ONE);
                } else {
                    ECFieldElement square = add2.square();
                    ECFieldElement multiply = add.multiply(eCFieldElement7);
                    ECFieldElement multiply2 = add.multiply(eCFieldElement2);
                    ECFieldElement multiply3 = multiply.multiply(multiply2);
                    if (multiply3.isZero()) {
                        return new SecT163K1Point(curve, multiply3, curve.getB(), this.withCompression);
                    }
                    ECFieldElement multiply4 = add.multiply(square);
                    eCFieldElement4 = !isOne2 ? multiply4.multiply(zCoord) : multiply4;
                    ECFieldElement squarePlusProduct = multiply2.add(square).squarePlusProduct(eCFieldElement4, eCFieldElement8.add(eCFieldElement9));
                    if (!isOne) {
                        eCFieldElement4 = eCFieldElement4.multiply(eCFieldElement9);
                        eCFieldElement6 = multiply3;
                        eCFieldElement5 = squarePlusProduct;
                    } else {
                        eCFieldElement6 = multiply3;
                        eCFieldElement5 = squarePlusProduct;
                    }
                }
                return new SecT163K1Point(curve, eCFieldElement6, eCFieldElement5, new ECFieldElement[]{eCFieldElement4}, this.withCompression);
            } else if (add.isZero()) {
                return twice();
            } else {
                return curve.getInfinity();
            }
        } else if (rawXCoord.isZero()) {
            return curve.getInfinity();
        } else {
            return eCPoint.add(this);
        }
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint twice() {
        ECFieldElement eCFieldElement;
        ECFieldElement eCFieldElement2;
        if (isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        ECFieldElement eCFieldElement3 = this.x;
        if (eCFieldElement3.isZero()) {
            return curve.getInfinity();
        }
        ECFieldElement eCFieldElement4 = this.y;
        ECFieldElement eCFieldElement5 = this.zs[0];
        boolean isOne = eCFieldElement5.isOne();
        if (isOne) {
            eCFieldElement = eCFieldElement4;
        } else {
            eCFieldElement = eCFieldElement4.multiply(eCFieldElement5);
        }
        if (!isOne) {
            eCFieldElement5 = eCFieldElement5.square();
        }
        ECFieldElement add = eCFieldElement4.square().add(eCFieldElement).add(eCFieldElement5);
        if (add.isZero()) {
            return new SecT163K1Point(curve, add, curve.getB(), this.withCompression);
        }
        ECFieldElement square = add.square();
        if (isOne) {
            eCFieldElement2 = add;
        } else {
            eCFieldElement2 = add.multiply(eCFieldElement5);
        }
        ECFieldElement square2 = eCFieldElement4.add(eCFieldElement3).square();
        return new SecT163K1Point(curve, square, square2.add(add).add(eCFieldElement5).multiply(square2).add(square), new ECFieldElement[]{eCFieldElement2}, this.withCompression);
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint twicePlus(ECPoint eCPoint) {
        if (isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return twice();
        }
        ECCurve curve = getCurve();
        ECFieldElement eCFieldElement = this.x;
        if (eCFieldElement.isZero()) {
            return eCPoint;
        }
        ECFieldElement rawXCoord = eCPoint.getRawXCoord();
        ECFieldElement zCoord = eCPoint.getZCoord(0);
        if (rawXCoord.isZero() || !zCoord.isOne()) {
            return twice().add(eCPoint);
        }
        ECFieldElement eCFieldElement2 = this.y;
        ECFieldElement eCFieldElement3 = this.zs[0];
        ECFieldElement rawYCoord = eCPoint.getRawYCoord();
        ECFieldElement square = eCFieldElement.square();
        ECFieldElement square2 = eCFieldElement2.square();
        ECFieldElement square3 = eCFieldElement3.square();
        ECFieldElement add = square3.add(square2).add(eCFieldElement2.multiply(eCFieldElement3));
        ECFieldElement addOne = rawYCoord.addOne();
        ECFieldElement multiplyPlusProduct = rawYCoord.multiply(square3).add(square2).multiplyPlusProduct(add, square, square3);
        ECFieldElement multiply = rawXCoord.multiply(square3);
        ECFieldElement square4 = multiply.add(add).square();
        if (square4.isZero()) {
            if (multiplyPlusProduct.isZero()) {
                return eCPoint.twice();
            }
            return curve.getInfinity();
        } else if (multiplyPlusProduct.isZero()) {
            return new SecT163K1Point(curve, multiplyPlusProduct, curve.getB(), this.withCompression);
        } else {
            ECFieldElement multiply2 = multiplyPlusProduct.square().multiply(multiply);
            ECFieldElement multiply3 = multiplyPlusProduct.multiply(square4).multiply(square3);
            return new SecT163K1Point(curve, multiply2, multiplyPlusProduct.add(square4).square().multiplyPlusProduct(add, addOne, multiply3), new ECFieldElement[]{multiply3}, this.withCompression);
        }
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint negate() {
        if (isInfinity()) {
            return this;
        }
        ECFieldElement eCFieldElement = this.x;
        if (eCFieldElement.isZero()) {
            return this;
        }
        ECFieldElement eCFieldElement2 = this.y;
        ECFieldElement eCFieldElement3 = this.zs[0];
        return new SecT163K1Point(this.curve, eCFieldElement, eCFieldElement2.add(eCFieldElement3), new ECFieldElement[]{eCFieldElement3}, this.withCompression);
    }
}
