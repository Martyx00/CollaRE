package org.spongycastle.math.ec;

import java.math.BigInteger;
import java.util.Hashtable;
import org.spongycastle.math.ec.ECFieldElement;

public abstract class ECPoint {
    protected static ECFieldElement[] EMPTY_ZS = new ECFieldElement[0];
    protected ECCurve curve;
    protected Hashtable preCompTable;
    protected boolean withCompression;
    protected ECFieldElement x;
    protected ECFieldElement y;
    protected ECFieldElement[] zs;

    public abstract ECPoint add(ECPoint eCPoint);

    /* access modifiers changed from: protected */
    public abstract ECPoint detach();

    /* access modifiers changed from: protected */
    public abstract boolean getCompressionYTilde();

    public abstract ECPoint negate();

    /* access modifiers changed from: protected */
    public abstract boolean satisfiesCurveEquation();

    public abstract ECPoint subtract(ECPoint eCPoint);

    public abstract ECPoint twice();

    protected static ECFieldElement[] getInitialZCoords(ECCurve eCCurve) {
        int coordinateSystem = eCCurve == null ? 0 : eCCurve.getCoordinateSystem();
        if (coordinateSystem == 0 || coordinateSystem == 5) {
            return EMPTY_ZS;
        }
        ECFieldElement fromBigInteger = eCCurve.fromBigInteger(ECConstants.ONE);
        if (coordinateSystem != 6) {
            switch (coordinateSystem) {
                case 1:
                case 2:
                    break;
                case 3:
                    return new ECFieldElement[]{fromBigInteger, fromBigInteger, fromBigInteger};
                case 4:
                    return new ECFieldElement[]{fromBigInteger, eCCurve.getA()};
                default:
                    throw new IllegalArgumentException("unknown coordinate system");
            }
        }
        return new ECFieldElement[]{fromBigInteger};
    }

    protected ECPoint(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, getInitialZCoords(eCCurve));
    }

    protected ECPoint(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        this.preCompTable = null;
        this.curve = eCCurve;
        this.x = eCFieldElement;
        this.y = eCFieldElement2;
        this.zs = eCFieldElementArr;
    }

    /* access modifiers changed from: protected */
    public boolean satisfiesCofactor() {
        BigInteger cofactor = this.curve.getCofactor();
        return cofactor == null || cofactor.equals(ECConstants.ONE) || !ECAlgorithms.referenceMultiply(this, cofactor).isInfinity();
    }

    public final ECPoint getDetachedPoint() {
        return normalize().detach();
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    /* access modifiers changed from: protected */
    public int getCurveCoordinateSystem() {
        ECCurve eCCurve = this.curve;
        if (eCCurve == null) {
            return 0;
        }
        return eCCurve.getCoordinateSystem();
    }

    public ECFieldElement getX() {
        return normalize().getXCoord();
    }

    public ECFieldElement getY() {
        return normalize().getYCoord();
    }

    public ECFieldElement getAffineXCoord() {
        checkNormalized();
        return getXCoord();
    }

    public ECFieldElement getAffineYCoord() {
        checkNormalized();
        return getYCoord();
    }

    public ECFieldElement getXCoord() {
        return this.x;
    }

    public ECFieldElement getYCoord() {
        return this.y;
    }

    public ECFieldElement getZCoord(int i) {
        if (i >= 0) {
            ECFieldElement[] eCFieldElementArr = this.zs;
            if (i < eCFieldElementArr.length) {
                return eCFieldElementArr[i];
            }
        }
        return null;
    }

    public ECFieldElement[] getZCoords() {
        ECFieldElement[] eCFieldElementArr = this.zs;
        int length = eCFieldElementArr.length;
        if (length == 0) {
            return EMPTY_ZS;
        }
        ECFieldElement[] eCFieldElementArr2 = new ECFieldElement[length];
        System.arraycopy(eCFieldElementArr, 0, eCFieldElementArr2, 0, length);
        return eCFieldElementArr2;
    }

    public final ECFieldElement getRawXCoord() {
        return this.x;
    }

    public final ECFieldElement getRawYCoord() {
        return this.y;
    }

    /* access modifiers changed from: protected */
    public final ECFieldElement[] getRawZCoords() {
        return this.zs;
    }

    /* access modifiers changed from: protected */
    public void checkNormalized() {
        if (!isNormalized()) {
            throw new IllegalStateException("point not in normal form");
        }
    }

    public boolean isNormalized() {
        int curveCoordinateSystem = getCurveCoordinateSystem();
        if (curveCoordinateSystem == 0 || curveCoordinateSystem == 5 || isInfinity() || this.zs[0].isOne()) {
            return true;
        }
        return false;
    }

    public ECPoint normalize() {
        int curveCoordinateSystem;
        if (isInfinity() || (curveCoordinateSystem = getCurveCoordinateSystem()) == 0 || curveCoordinateSystem == 5) {
            return this;
        }
        ECFieldElement zCoord = getZCoord(0);
        if (zCoord.isOne()) {
            return this;
        }
        return normalize(zCoord.invert());
    }

    /* access modifiers changed from: package-private */
    public ECPoint normalize(ECFieldElement eCFieldElement) {
        int curveCoordinateSystem = getCurveCoordinateSystem();
        if (curveCoordinateSystem != 6) {
            switch (curveCoordinateSystem) {
                case 1:
                    break;
                case 2:
                case 3:
                case 4:
                    ECFieldElement square = eCFieldElement.square();
                    return createScaledPoint(square, square.multiply(eCFieldElement));
                default:
                    throw new IllegalStateException("not a projective coordinate system");
            }
        }
        return createScaledPoint(eCFieldElement, eCFieldElement);
    }

    /* access modifiers changed from: protected */
    public ECPoint createScaledPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return getCurve().createRawPoint(getRawXCoord().multiply(eCFieldElement), getRawYCoord().multiply(eCFieldElement2), this.withCompression);
    }

    public boolean isInfinity() {
        if (!(this.x == null || this.y == null)) {
            ECFieldElement[] eCFieldElementArr = this.zs;
            if (eCFieldElementArr.length <= 0 || !eCFieldElementArr[0].isZero()) {
                return false;
            }
        }
        return true;
    }

    public boolean isCompressed() {
        return this.withCompression;
    }

    public boolean isValid() {
        if (!isInfinity() && getCurve() != null && (!satisfiesCurveEquation() || !satisfiesCofactor())) {
            return false;
        }
        return true;
    }

    public ECPoint scaleX(ECFieldElement eCFieldElement) {
        return isInfinity() ? this : getCurve().createRawPoint(getRawXCoord().multiply(eCFieldElement), getRawYCoord(), getRawZCoords(), this.withCompression);
    }

    public ECPoint scaleY(ECFieldElement eCFieldElement) {
        return isInfinity() ? this : getCurve().createRawPoint(getRawXCoord(), getRawYCoord().multiply(eCFieldElement), getRawZCoords(), this.withCompression);
    }

    public boolean equals(ECPoint eCPoint) {
        ECPoint eCPoint2;
        ECPoint eCPoint3;
        if (eCPoint == null) {
            return false;
        }
        ECCurve curve2 = getCurve();
        ECCurve curve3 = eCPoint.getCurve();
        boolean z = curve2 == null;
        boolean z2 = curve3 == null;
        boolean isInfinity = isInfinity();
        boolean isInfinity2 = eCPoint.isInfinity();
        if (!isInfinity && !isInfinity2) {
            if (z && z2) {
                eCPoint3 = eCPoint;
                eCPoint2 = this;
            } else if (z) {
                eCPoint3 = eCPoint.normalize();
                eCPoint2 = this;
            } else if (z2) {
                eCPoint3 = eCPoint;
                eCPoint2 = normalize();
            } else if (!curve2.equals(curve3)) {
                return false;
            } else {
                ECPoint[] eCPointArr = {this, curve2.importPoint(eCPoint)};
                curve2.normalizeAll(eCPointArr);
                eCPoint2 = eCPointArr[0];
                eCPoint3 = eCPointArr[1];
            }
            if (!eCPoint2.getXCoord().equals(eCPoint3.getXCoord()) || !eCPoint2.getYCoord().equals(eCPoint3.getYCoord())) {
                return false;
            }
            return true;
        } else if (!isInfinity || !isInfinity2) {
            return false;
        } else {
            if (z || z2 || curve2.equals(curve3)) {
                return true;
            }
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ECPoint)) {
            return false;
        }
        return equals((ECPoint) obj);
    }

    public int hashCode() {
        int i;
        ECCurve curve2 = getCurve();
        if (curve2 == null) {
            i = 0;
        } else {
            i = ~curve2.hashCode();
        }
        if (isInfinity()) {
            return i;
        }
        ECPoint normalize = normalize();
        return (i ^ (normalize.getXCoord().hashCode() * 17)) ^ (normalize.getYCoord().hashCode() * 257);
    }

    public String toString() {
        if (isInfinity()) {
            return "INF";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('(');
        stringBuffer.append(getRawXCoord());
        stringBuffer.append(',');
        stringBuffer.append(getRawYCoord());
        for (int i = 0; i < this.zs.length; i++) {
            stringBuffer.append(',');
            stringBuffer.append(this.zs[i]);
        }
        stringBuffer.append(')');
        return stringBuffer.toString();
    }

    public byte[] getEncoded() {
        return getEncoded(this.withCompression);
    }

    public byte[] getEncoded(boolean z) {
        if (isInfinity()) {
            return new byte[1];
        }
        ECPoint normalize = normalize();
        byte[] encoded = normalize.getXCoord().getEncoded();
        if (z) {
            byte[] bArr = new byte[(encoded.length + 1)];
            bArr[0] = (byte) (normalize.getCompressionYTilde() ? 3 : 2);
            System.arraycopy(encoded, 0, bArr, 1, encoded.length);
            return bArr;
        }
        byte[] encoded2 = normalize.getYCoord().getEncoded();
        byte[] bArr2 = new byte[(encoded.length + encoded2.length + 1)];
        bArr2[0] = 4;
        System.arraycopy(encoded, 0, bArr2, 1, encoded.length);
        System.arraycopy(encoded2, 0, bArr2, encoded.length + 1, encoded2.length);
        return bArr2;
    }

    public ECPoint timesPow2(int i) {
        if (i >= 0) {
            ECPoint eCPoint = this;
            while (true) {
                i--;
                if (i < 0) {
                    return eCPoint;
                }
                eCPoint = eCPoint.twice();
            }
        } else {
            throw new IllegalArgumentException("'e' cannot be negative");
        }
    }

    public ECPoint twicePlus(ECPoint eCPoint) {
        return twice().add(eCPoint);
    }

    public ECPoint threeTimes() {
        return twicePlus(this);
    }

    public ECPoint multiply(BigInteger bigInteger) {
        return getCurve().getMultiplier().multiply(this, bigInteger);
    }

    public static abstract class AbstractFp extends ECPoint {
        protected AbstractFp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        protected AbstractFp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        /* access modifiers changed from: protected */
        @Override // org.spongycastle.math.ec.ECPoint
        public boolean getCompressionYTilde() {
            return getAffineYCoord().testBitZero();
        }

        /* access modifiers changed from: protected */
        @Override // org.spongycastle.math.ec.ECPoint
        public boolean satisfiesCurveEquation() {
            ECFieldElement eCFieldElement = this.x;
            ECFieldElement eCFieldElement2 = this.y;
            ECFieldElement a2 = this.curve.getA();
            ECFieldElement b2 = this.curve.getB();
            ECFieldElement square = eCFieldElement2.square();
            switch (getCurveCoordinateSystem()) {
                case 0:
                    break;
                case 1:
                    ECFieldElement eCFieldElement3 = this.zs[0];
                    if (!eCFieldElement3.isOne()) {
                        ECFieldElement square2 = eCFieldElement3.square();
                        ECFieldElement multiply = eCFieldElement3.multiply(square2);
                        square = square.multiply(eCFieldElement3);
                        a2 = a2.multiply(square2);
                        b2 = b2.multiply(multiply);
                        break;
                    }
                    break;
                case 2:
                case 3:
                case 4:
                    ECFieldElement eCFieldElement4 = this.zs[0];
                    if (!eCFieldElement4.isOne()) {
                        ECFieldElement square3 = eCFieldElement4.square();
                        ECFieldElement square4 = square3.square();
                        ECFieldElement multiply2 = square3.multiply(square4);
                        a2 = a2.multiply(square4);
                        b2 = b2.multiply(multiply2);
                        break;
                    }
                    break;
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
            return square.equals(eCFieldElement.square().add(a2).multiply(eCFieldElement).add(b2));
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint subtract(ECPoint eCPoint) {
            if (eCPoint.isInfinity()) {
                return this;
            }
            return add(eCPoint.negate());
        }
    }

    public static class Fp extends AbstractFp {
        public Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            this(eCCurve, eCFieldElement, eCFieldElement2, false);
        }

        public Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
            if ((eCFieldElement == null) == (eCFieldElement2 != null ? false : true)) {
                this.withCompression = z;
                return;
            }
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }

        Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
            this.withCompression = z;
        }

        /* access modifiers changed from: protected */
        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint detach() {
            return new Fp(null, getAffineXCoord(), getAffineYCoord());
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECFieldElement getZCoord(int i) {
            if (i == 1 && 4 == getCurveCoordinateSystem()) {
                return getJacobianModifiedW();
            }
            return super.getZCoord(i);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint add(ECPoint eCPoint) {
            ECFieldElement eCFieldElement;
            ECFieldElement eCFieldElement2;
            ECFieldElement eCFieldElement3;
            ECFieldElement eCFieldElement4;
            if (isInfinity()) {
                return eCPoint;
            }
            if (eCPoint.isInfinity()) {
                return this;
            }
            if (this == eCPoint) {
                return twice();
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement5 = this.x;
            ECFieldElement eCFieldElement6 = this.y;
            ECFieldElement eCFieldElement7 = eCPoint.x;
            ECFieldElement eCFieldElement8 = eCPoint.y;
            if (coordinateSystem != 4) {
                switch (coordinateSystem) {
                    case 0:
                        ECFieldElement subtract = eCFieldElement7.subtract(eCFieldElement5);
                        ECFieldElement subtract2 = eCFieldElement8.subtract(eCFieldElement6);
                        if (!subtract.isZero()) {
                            ECFieldElement divide = subtract2.divide(subtract);
                            ECFieldElement subtract3 = divide.square().subtract(eCFieldElement5).subtract(eCFieldElement7);
                            return new Fp(curve, subtract3, divide.multiply(eCFieldElement5.subtract(subtract3)).subtract(eCFieldElement6), this.withCompression);
                        } else if (subtract2.isZero()) {
                            return twice();
                        } else {
                            return curve.getInfinity();
                        }
                    case 1:
                        ECFieldElement eCFieldElement9 = this.zs[0];
                        ECFieldElement eCFieldElement10 = eCPoint.zs[0];
                        boolean isOne = eCFieldElement9.isOne();
                        boolean isOne2 = eCFieldElement10.isOne();
                        if (!isOne) {
                            eCFieldElement8 = eCFieldElement8.multiply(eCFieldElement9);
                        }
                        if (!isOne2) {
                            eCFieldElement6 = eCFieldElement6.multiply(eCFieldElement10);
                        }
                        ECFieldElement subtract4 = eCFieldElement8.subtract(eCFieldElement6);
                        if (!isOne) {
                            eCFieldElement7 = eCFieldElement7.multiply(eCFieldElement9);
                        }
                        if (!isOne2) {
                            eCFieldElement5 = eCFieldElement5.multiply(eCFieldElement10);
                        }
                        ECFieldElement subtract5 = eCFieldElement7.subtract(eCFieldElement5);
                        if (!subtract5.isZero()) {
                            if (isOne) {
                                eCFieldElement9 = eCFieldElement10;
                            } else if (!isOne2) {
                                eCFieldElement9 = eCFieldElement9.multiply(eCFieldElement10);
                            }
                            ECFieldElement square = subtract5.square();
                            ECFieldElement multiply = square.multiply(subtract5);
                            ECFieldElement multiply2 = square.multiply(eCFieldElement5);
                            ECFieldElement subtract6 = subtract4.square().multiply(eCFieldElement9).subtract(multiply).subtract(two(multiply2));
                            return new Fp(curve, subtract5.multiply(subtract6), multiply2.subtract(subtract6).multiplyMinusProduct(subtract4, eCFieldElement6, multiply), new ECFieldElement[]{multiply.multiply(eCFieldElement9)}, this.withCompression);
                        } else if (subtract4.isZero()) {
                            return twice();
                        } else {
                            return curve.getInfinity();
                        }
                    case 2:
                        break;
                    default:
                        throw new IllegalStateException("unsupported coordinate system");
                }
            }
            ECFieldElement eCFieldElement11 = this.zs[0];
            ECFieldElement eCFieldElement12 = eCPoint.zs[0];
            boolean isOne3 = eCFieldElement11.isOne();
            if (isOne3 || !eCFieldElement11.equals(eCFieldElement12)) {
                if (!isOne3) {
                    ECFieldElement square2 = eCFieldElement11.square();
                    eCFieldElement7 = square2.multiply(eCFieldElement7);
                    eCFieldElement8 = square2.multiply(eCFieldElement11).multiply(eCFieldElement8);
                }
                boolean isOne4 = eCFieldElement12.isOne();
                if (!isOne4) {
                    ECFieldElement square3 = eCFieldElement12.square();
                    eCFieldElement5 = square3.multiply(eCFieldElement5);
                    eCFieldElement6 = square3.multiply(eCFieldElement12).multiply(eCFieldElement6);
                }
                ECFieldElement subtract7 = eCFieldElement5.subtract(eCFieldElement7);
                ECFieldElement subtract8 = eCFieldElement6.subtract(eCFieldElement8);
                if (!subtract7.isZero()) {
                    eCFieldElement = subtract7.square();
                    ECFieldElement multiply3 = eCFieldElement.multiply(subtract7);
                    ECFieldElement multiply4 = eCFieldElement.multiply(eCFieldElement5);
                    ECFieldElement subtract9 = subtract8.square().add(multiply3).subtract(two(multiply4));
                    ECFieldElement multiplyMinusProduct = multiply4.subtract(subtract9).multiplyMinusProduct(subtract8, multiply3, eCFieldElement6);
                    ECFieldElement multiply5 = !isOne3 ? subtract7.multiply(eCFieldElement11) : subtract7;
                    if (!isOne4) {
                        eCFieldElement4 = multiply5.multiply(eCFieldElement12);
                    } else {
                        eCFieldElement4 = multiply5;
                    }
                    if (eCFieldElement4 == subtract7) {
                        eCFieldElement2 = multiplyMinusProduct;
                        eCFieldElement3 = subtract9;
                    } else {
                        eCFieldElement2 = multiplyMinusProduct;
                        eCFieldElement3 = subtract9;
                        eCFieldElement = null;
                    }
                } else if (subtract8.isZero()) {
                    return twice();
                } else {
                    return curve.getInfinity();
                }
            } else {
                ECFieldElement subtract10 = eCFieldElement5.subtract(eCFieldElement7);
                ECFieldElement subtract11 = eCFieldElement6.subtract(eCFieldElement8);
                if (!subtract10.isZero()) {
                    ECFieldElement square4 = subtract10.square();
                    ECFieldElement multiply6 = eCFieldElement5.multiply(square4);
                    ECFieldElement multiply7 = eCFieldElement7.multiply(square4);
                    ECFieldElement multiply8 = multiply6.subtract(multiply7).multiply(eCFieldElement6);
                    ECFieldElement subtract12 = subtract11.square().subtract(multiply6).subtract(multiply7);
                    ECFieldElement subtract13 = multiply6.subtract(subtract12).multiply(subtract11).subtract(multiply8);
                    eCFieldElement4 = subtract10.multiply(eCFieldElement11);
                    eCFieldElement2 = subtract13;
                    eCFieldElement3 = subtract12;
                    eCFieldElement = null;
                } else if (subtract11.isZero()) {
                    return twice();
                } else {
                    return curve.getInfinity();
                }
            }
            return new Fp(curve, eCFieldElement3, eCFieldElement2, coordinateSystem == 4 ? new ECFieldElement[]{eCFieldElement4, calculateJacobianModifiedW(eCFieldElement4, eCFieldElement)} : new ECFieldElement[]{eCFieldElement4}, this.withCompression);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint twice() {
            ECFieldElement eCFieldElement;
            ECFieldElement eCFieldElement2;
            ECFieldElement eCFieldElement3;
            ECFieldElement eCFieldElement4;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement5 = this.y;
            if (eCFieldElement5.isZero()) {
                return curve.getInfinity();
            }
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement6 = this.x;
            if (coordinateSystem == 4) {
                return twiceJacobianModified(true);
            }
            switch (coordinateSystem) {
                case 0:
                    ECFieldElement divide = three(eCFieldElement6.square()).add(getCurve().getA()).divide(two(eCFieldElement5));
                    ECFieldElement subtract = divide.square().subtract(two(eCFieldElement6));
                    return new Fp(curve, subtract, divide.multiply(eCFieldElement6.subtract(subtract)).subtract(eCFieldElement5), this.withCompression);
                case 1:
                    ECFieldElement eCFieldElement7 = this.zs[0];
                    boolean isOne = eCFieldElement7.isOne();
                    ECFieldElement a2 = curve.getA();
                    if (!a2.isZero() && !isOne) {
                        a2 = a2.multiply(eCFieldElement7.square());
                    }
                    ECFieldElement add = a2.add(three(eCFieldElement6.square()));
                    if (isOne) {
                        eCFieldElement = eCFieldElement5;
                    } else {
                        eCFieldElement = eCFieldElement5.multiply(eCFieldElement7);
                    }
                    ECFieldElement square = isOne ? eCFieldElement5.square() : eCFieldElement.multiply(eCFieldElement5);
                    ECFieldElement four = four(eCFieldElement6.multiply(square));
                    ECFieldElement subtract2 = add.square().subtract(two(four));
                    ECFieldElement two = two(eCFieldElement);
                    ECFieldElement multiply = subtract2.multiply(two);
                    ECFieldElement two2 = two(square);
                    return new Fp(curve, multiply, four.subtract(subtract2).multiply(add).subtract(two(two2.square())), new ECFieldElement[]{two(isOne ? two(two2) : two.square()).multiply(eCFieldElement)}, this.withCompression);
                case 2:
                    ECFieldElement eCFieldElement8 = this.zs[0];
                    boolean isOne2 = eCFieldElement8.isOne();
                    ECFieldElement square2 = eCFieldElement5.square();
                    ECFieldElement square3 = square2.square();
                    ECFieldElement a3 = curve.getA();
                    ECFieldElement negate = a3.negate();
                    if (negate.toBigInteger().equals(BigInteger.valueOf(3))) {
                        if (isOne2) {
                            eCFieldElement4 = eCFieldElement8;
                        } else {
                            eCFieldElement4 = eCFieldElement8.square();
                        }
                        eCFieldElement2 = three(eCFieldElement6.add(eCFieldElement4).multiply(eCFieldElement6.subtract(eCFieldElement4)));
                        eCFieldElement3 = four(square2.multiply(eCFieldElement6));
                    } else {
                        ECFieldElement three = three(eCFieldElement6.square());
                        if (isOne2) {
                            eCFieldElement2 = three.add(a3);
                        } else if (!a3.isZero()) {
                            ECFieldElement square4 = eCFieldElement8.square().square();
                            eCFieldElement2 = negate.bitLength() < a3.bitLength() ? three.subtract(square4.multiply(negate)) : three.add(square4.multiply(a3));
                        } else {
                            eCFieldElement2 = three;
                        }
                        eCFieldElement3 = four(eCFieldElement6.multiply(square2));
                    }
                    ECFieldElement subtract3 = eCFieldElement2.square().subtract(two(eCFieldElement3));
                    ECFieldElement subtract4 = eCFieldElement3.subtract(subtract3).multiply(eCFieldElement2).subtract(eight(square3));
                    ECFieldElement two3 = two(eCFieldElement5);
                    if (!isOne2) {
                        two3 = two3.multiply(eCFieldElement8);
                    }
                    return new Fp(curve, subtract3, subtract4, new ECFieldElement[]{two3}, this.withCompression);
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint twicePlus(ECPoint eCPoint) {
            if (this == eCPoint) {
                return threeTimes();
            }
            if (isInfinity()) {
                return eCPoint;
            }
            if (eCPoint.isInfinity()) {
                return twice();
            }
            ECFieldElement eCFieldElement = this.y;
            if (eCFieldElement.isZero()) {
                return eCPoint;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem == 0) {
                ECFieldElement eCFieldElement2 = this.x;
                ECFieldElement eCFieldElement3 = eCPoint.x;
                ECFieldElement eCFieldElement4 = eCPoint.y;
                ECFieldElement subtract = eCFieldElement3.subtract(eCFieldElement2);
                ECFieldElement subtract2 = eCFieldElement4.subtract(eCFieldElement);
                if (!subtract.isZero()) {
                    ECFieldElement square = subtract.square();
                    ECFieldElement subtract3 = square.multiply(two(eCFieldElement2).add(eCFieldElement3)).subtract(subtract2.square());
                    if (subtract3.isZero()) {
                        return curve.getInfinity();
                    }
                    ECFieldElement invert = subtract3.multiply(subtract).invert();
                    ECFieldElement multiply = subtract3.multiply(invert).multiply(subtract2);
                    ECFieldElement subtract4 = two(eCFieldElement).multiply(square).multiply(subtract).multiply(invert).subtract(multiply);
                    ECFieldElement add = subtract4.subtract(multiply).multiply(multiply.add(subtract4)).add(eCFieldElement3);
                    return new Fp(curve, add, eCFieldElement2.subtract(add).multiply(subtract4).subtract(eCFieldElement), this.withCompression);
                } else if (subtract2.isZero()) {
                    return threeTimes();
                } else {
                    return this;
                }
            } else if (coordinateSystem != 4) {
                return twice().add(eCPoint);
            } else {
                return twiceJacobianModified(false).add(eCPoint);
            }
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint threeTimes() {
            if (isInfinity()) {
                return this;
            }
            ECFieldElement eCFieldElement = this.y;
            if (eCFieldElement.isZero()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem == 0) {
                ECFieldElement eCFieldElement2 = this.x;
                ECFieldElement two = two(eCFieldElement);
                ECFieldElement square = two.square();
                ECFieldElement add = three(eCFieldElement2.square()).add(getCurve().getA());
                ECFieldElement subtract = three(eCFieldElement2).multiply(square).subtract(add.square());
                if (subtract.isZero()) {
                    return getCurve().getInfinity();
                }
                ECFieldElement invert = subtract.multiply(two).invert();
                ECFieldElement multiply = subtract.multiply(invert).multiply(add);
                ECFieldElement subtract2 = square.square().multiply(invert).subtract(multiply);
                ECFieldElement add2 = subtract2.subtract(multiply).multiply(multiply.add(subtract2)).add(eCFieldElement2);
                return new Fp(curve, add2, eCFieldElement2.subtract(add2).multiply(subtract2).subtract(eCFieldElement), this.withCompression);
            } else if (coordinateSystem != 4) {
                return twice().add(this);
            } else {
                return twiceJacobianModified(false).add(this);
            }
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint timesPow2(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("'e' cannot be negative");
            } else if (i == 0 || isInfinity()) {
                return this;
            } else {
                if (i == 1) {
                    return twice();
                }
                ECCurve curve = getCurve();
                ECFieldElement eCFieldElement = this.y;
                if (eCFieldElement.isZero()) {
                    return curve.getInfinity();
                }
                int coordinateSystem = curve.getCoordinateSystem();
                ECFieldElement a2 = curve.getA();
                ECFieldElement eCFieldElement2 = this.x;
                ECFieldElement fromBigInteger = this.zs.length < 1 ? curve.fromBigInteger(ECConstants.ONE) : this.zs[0];
                if (!fromBigInteger.isOne()) {
                    if (coordinateSystem != 4) {
                        switch (coordinateSystem) {
                            case 0:
                                break;
                            case 1:
                                ECFieldElement square = fromBigInteger.square();
                                eCFieldElement2 = eCFieldElement2.multiply(fromBigInteger);
                                eCFieldElement = eCFieldElement.multiply(square);
                                a2 = calculateJacobianModifiedW(fromBigInteger, square);
                                break;
                            case 2:
                                a2 = calculateJacobianModifiedW(fromBigInteger, null);
                                break;
                            default:
                                throw new IllegalStateException("unsupported coordinate system");
                        }
                    } else {
                        a2 = getJacobianModifiedW();
                    }
                }
                ECFieldElement eCFieldElement3 = a2;
                ECFieldElement eCFieldElement4 = eCFieldElement;
                int i2 = 0;
                while (i2 < i) {
                    if (eCFieldElement4.isZero()) {
                        return curve.getInfinity();
                    }
                    ECFieldElement three = three(eCFieldElement2.square());
                    ECFieldElement two = two(eCFieldElement4);
                    ECFieldElement multiply = two.multiply(eCFieldElement4);
                    ECFieldElement two2 = two(eCFieldElement2.multiply(multiply));
                    ECFieldElement two3 = two(multiply.square());
                    if (!eCFieldElement3.isZero()) {
                        three = three.add(eCFieldElement3);
                        eCFieldElement3 = two(two3.multiply(eCFieldElement3));
                    }
                    ECFieldElement subtract = three.square().subtract(two(two2));
                    eCFieldElement4 = three.multiply(two2.subtract(subtract)).subtract(two3);
                    fromBigInteger = fromBigInteger.isOne() ? two : two.multiply(fromBigInteger);
                    i2++;
                    eCFieldElement2 = subtract;
                }
                if (coordinateSystem != 4) {
                    switch (coordinateSystem) {
                        case 0:
                            ECFieldElement invert = fromBigInteger.invert();
                            ECFieldElement square2 = invert.square();
                            return new Fp(curve, eCFieldElement2.multiply(square2), eCFieldElement4.multiply(square2.multiply(invert)), this.withCompression);
                        case 1:
                            return new Fp(curve, eCFieldElement2.multiply(fromBigInteger), eCFieldElement4, new ECFieldElement[]{fromBigInteger.multiply(fromBigInteger.square())}, this.withCompression);
                        case 2:
                            return new Fp(curve, eCFieldElement2, eCFieldElement4, new ECFieldElement[]{fromBigInteger}, this.withCompression);
                        default:
                            throw new IllegalStateException("unsupported coordinate system");
                    }
                } else {
                    return new Fp(curve, eCFieldElement2, eCFieldElement4, new ECFieldElement[]{fromBigInteger, eCFieldElement3}, this.withCompression);
                }
            }
        }

        /* access modifiers changed from: protected */
        public ECFieldElement two(ECFieldElement eCFieldElement) {
            return eCFieldElement.add(eCFieldElement);
        }

        /* access modifiers changed from: protected */
        public ECFieldElement three(ECFieldElement eCFieldElement) {
            return two(eCFieldElement).add(eCFieldElement);
        }

        /* access modifiers changed from: protected */
        public ECFieldElement four(ECFieldElement eCFieldElement) {
            return two(two(eCFieldElement));
        }

        /* access modifiers changed from: protected */
        public ECFieldElement eight(ECFieldElement eCFieldElement) {
            return four(two(eCFieldElement));
        }

        /* access modifiers changed from: protected */
        public ECFieldElement doubleProductFromSquares(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3, ECFieldElement eCFieldElement4) {
            return eCFieldElement.add(eCFieldElement2).square().subtract(eCFieldElement3).subtract(eCFieldElement4);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint negate() {
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            if (curve.getCoordinateSystem() != 0) {
                return new Fp(curve, this.x, this.y.negate(), this.zs, this.withCompression);
            }
            return new Fp(curve, this.x, this.y.negate(), this.withCompression);
        }

        /* access modifiers changed from: protected */
        public ECFieldElement calculateJacobianModifiedW(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            ECFieldElement a2 = getCurve().getA();
            if (a2.isZero() || eCFieldElement.isOne()) {
                return a2;
            }
            if (eCFieldElement2 == null) {
                eCFieldElement2 = eCFieldElement.square();
            }
            ECFieldElement square = eCFieldElement2.square();
            ECFieldElement negate = a2.negate();
            if (negate.bitLength() < a2.bitLength()) {
                return square.multiply(negate).negate();
            }
            return square.multiply(a2);
        }

        /* access modifiers changed from: protected */
        public ECFieldElement getJacobianModifiedW() {
            ECFieldElement eCFieldElement = this.zs[1];
            if (eCFieldElement != null) {
                return eCFieldElement;
            }
            ECFieldElement[] eCFieldElementArr = this.zs;
            ECFieldElement calculateJacobianModifiedW = calculateJacobianModifiedW(this.zs[0], null);
            eCFieldElementArr[1] = calculateJacobianModifiedW;
            return calculateJacobianModifiedW;
        }

        /* access modifiers changed from: protected */
        public Fp twiceJacobianModified(boolean z) {
            ECFieldElement eCFieldElement = this.x;
            ECFieldElement eCFieldElement2 = this.y;
            ECFieldElement eCFieldElement3 = this.zs[0];
            ECFieldElement jacobianModifiedW = getJacobianModifiedW();
            ECFieldElement add = three(eCFieldElement.square()).add(jacobianModifiedW);
            ECFieldElement two = two(eCFieldElement2);
            ECFieldElement multiply = two.multiply(eCFieldElement2);
            ECFieldElement two2 = two(eCFieldElement.multiply(multiply));
            ECFieldElement subtract = add.square().subtract(two(two2));
            ECFieldElement two3 = two(multiply.square());
            ECFieldElement subtract2 = add.multiply(two2.subtract(subtract)).subtract(two3);
            ECFieldElement two4 = z ? two(two3.multiply(jacobianModifiedW)) : null;
            if (!eCFieldElement3.isOne()) {
                two = two.multiply(eCFieldElement3);
            }
            return new Fp(getCurve(), subtract, subtract2, new ECFieldElement[]{two, two4}, this.withCompression);
        }
    }

    public static abstract class AbstractF2m extends ECPoint {
        protected AbstractF2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        protected AbstractF2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        /* access modifiers changed from: protected */
        @Override // org.spongycastle.math.ec.ECPoint
        public boolean satisfiesCurveEquation() {
            ECFieldElement eCFieldElement;
            ECFieldElement eCFieldElement2;
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement3 = this.x;
            ECFieldElement a2 = curve.getA();
            ECFieldElement b2 = curve.getB();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem == 6) {
                ECFieldElement eCFieldElement4 = this.zs[0];
                boolean isOne = eCFieldElement4.isOne();
                if (eCFieldElement3.isZero()) {
                    ECFieldElement square = this.y.square();
                    if (!isOne) {
                        b2 = b2.multiply(eCFieldElement4.square());
                    }
                    return square.equals(b2);
                }
                ECFieldElement eCFieldElement5 = this.y;
                ECFieldElement square2 = eCFieldElement3.square();
                if (isOne) {
                    eCFieldElement2 = eCFieldElement5.square().add(eCFieldElement5).add(a2);
                    eCFieldElement = square2.square().add(b2);
                } else {
                    ECFieldElement square3 = eCFieldElement4.square();
                    ECFieldElement square4 = square3.square();
                    eCFieldElement2 = eCFieldElement5.add(eCFieldElement4).multiplyPlusProduct(eCFieldElement5, a2, square3);
                    eCFieldElement = square2.squarePlusProduct(b2, square4);
                }
                return eCFieldElement2.multiply(square2).equals(eCFieldElement);
            }
            ECFieldElement eCFieldElement6 = this.y;
            ECFieldElement multiply = eCFieldElement6.add(eCFieldElement3).multiply(eCFieldElement6);
            switch (coordinateSystem) {
                case 0:
                    break;
                default:
                    throw new IllegalStateException("unsupported coordinate system");
                case 1:
                    ECFieldElement eCFieldElement7 = this.zs[0];
                    if (!eCFieldElement7.isOne()) {
                        ECFieldElement multiply2 = eCFieldElement7.multiply(eCFieldElement7.square());
                        multiply = multiply.multiply(eCFieldElement7);
                        a2 = a2.multiply(eCFieldElement7);
                        b2 = b2.multiply(multiply2);
                        break;
                    }
                    break;
            }
            return multiply.equals(eCFieldElement3.add(a2).multiply(eCFieldElement3.square()).add(b2));
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint scaleX(ECFieldElement eCFieldElement) {
            if (isInfinity()) {
                return this;
            }
            switch (getCurveCoordinateSystem()) {
                case 5:
                    ECFieldElement rawXCoord = getRawXCoord();
                    ECFieldElement rawYCoord = getRawYCoord();
                    return getCurve().createRawPoint(rawXCoord, rawYCoord.add(rawXCoord).divide(eCFieldElement).add(rawXCoord.multiply(eCFieldElement)), getRawZCoords(), this.withCompression);
                case 6:
                    ECFieldElement rawXCoord2 = getRawXCoord();
                    ECFieldElement rawYCoord2 = getRawYCoord();
                    ECFieldElement eCFieldElement2 = getRawZCoords()[0];
                    ECFieldElement multiply = rawXCoord2.multiply(eCFieldElement.square());
                    ECFieldElement add = rawYCoord2.add(rawXCoord2).add(multiply);
                    ECFieldElement multiply2 = eCFieldElement2.multiply(eCFieldElement);
                    return getCurve().createRawPoint(multiply, add, new ECFieldElement[]{multiply2}, this.withCompression);
                default:
                    return ECPoint.super.scaleX(eCFieldElement);
            }
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint scaleY(ECFieldElement eCFieldElement) {
            if (isInfinity()) {
                return this;
            }
            switch (getCurveCoordinateSystem()) {
                case 5:
                case 6:
                    ECFieldElement rawXCoord = getRawXCoord();
                    return getCurve().createRawPoint(rawXCoord, getRawYCoord().add(rawXCoord).multiply(eCFieldElement).add(rawXCoord), getRawZCoords(), this.withCompression);
                default:
                    return ECPoint.super.scaleY(eCFieldElement);
            }
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint subtract(ECPoint eCPoint) {
            if (eCPoint.isInfinity()) {
                return this;
            }
            return add(eCPoint.negate());
        }

        public AbstractF2m tau() {
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement = this.x;
            switch (coordinateSystem) {
                case 0:
                case 5:
                    return (AbstractF2m) curve.createRawPoint(eCFieldElement.square(), this.y.square(), this.withCompression);
                case 1:
                case 6:
                    ECFieldElement eCFieldElement2 = this.y;
                    ECFieldElement eCFieldElement3 = this.zs[0];
                    return (AbstractF2m) curve.createRawPoint(eCFieldElement.square(), eCFieldElement2.square(), new ECFieldElement[]{eCFieldElement3.square()}, this.withCompression);
                case 2:
                case 3:
                case 4:
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
        }

        public AbstractF2m tauPow(int i) {
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement = this.x;
            switch (coordinateSystem) {
                case 0:
                case 5:
                    return (AbstractF2m) curve.createRawPoint(eCFieldElement.squarePow(i), this.y.squarePow(i), this.withCompression);
                case 1:
                case 6:
                    ECFieldElement eCFieldElement2 = this.y;
                    ECFieldElement eCFieldElement3 = this.zs[0];
                    return (AbstractF2m) curve.createRawPoint(eCFieldElement.squarePow(i), eCFieldElement2.squarePow(i), new ECFieldElement[]{eCFieldElement3.squarePow(i)}, this.withCompression);
                case 2:
                case 3:
                case 4:
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
        }
    }

    public static class F2m extends AbstractF2m {
        public F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            this(eCCurve, eCFieldElement, eCFieldElement2, false);
        }

        public F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
            if ((eCFieldElement == null) == (eCFieldElement2 != null ? false : true)) {
                if (eCFieldElement != null) {
                    ECFieldElement.F2m.checkFieldElements(this.x, this.y);
                    if (eCCurve != null) {
                        ECFieldElement.F2m.checkFieldElements(this.x, this.curve.getA());
                    }
                }
                this.withCompression = z;
                return;
            }
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }

        F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
            this.withCompression = z;
        }

        /* access modifiers changed from: protected */
        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint detach() {
            return new F2m(null, getAffineXCoord(), getAffineYCoord());
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECFieldElement getYCoord() {
            int curveCoordinateSystem = getCurveCoordinateSystem();
            switch (curveCoordinateSystem) {
                case 5:
                case 6:
                    ECFieldElement eCFieldElement = this.x;
                    ECFieldElement eCFieldElement2 = this.y;
                    if (isInfinity() || eCFieldElement.isZero()) {
                        return eCFieldElement2;
                    }
                    ECFieldElement multiply = eCFieldElement2.add(eCFieldElement).multiply(eCFieldElement);
                    if (6 != curveCoordinateSystem) {
                        return multiply;
                    }
                    ECFieldElement eCFieldElement3 = this.zs[0];
                    return !eCFieldElement3.isOne() ? multiply.divide(eCFieldElement3) : multiply;
                default:
                    return this.y;
            }
        }

        /* access modifiers changed from: protected */
        @Override // org.spongycastle.math.ec.ECPoint
        public boolean getCompressionYTilde() {
            ECFieldElement rawXCoord = getRawXCoord();
            if (rawXCoord.isZero()) {
                return false;
            }
            ECFieldElement rawYCoord = getRawYCoord();
            switch (getCurveCoordinateSystem()) {
                case 5:
                case 6:
                    if (rawYCoord.testBitZero() != rawXCoord.testBitZero()) {
                        return true;
                    }
                    return false;
                default:
                    return rawYCoord.divide(rawXCoord).testBitZero();
            }
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint add(ECPoint eCPoint) {
            ECFieldElement eCFieldElement;
            ECFieldElement eCFieldElement2;
            ECFieldElement eCFieldElement3;
            ECFieldElement eCFieldElement4;
            ECFieldElement eCFieldElement5;
            ECFieldElement eCFieldElement6;
            ECFieldElement eCFieldElement7;
            ECFieldElement eCFieldElement8;
            if (isInfinity()) {
                return eCPoint;
            }
            if (eCPoint.isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement9 = this.x;
            ECFieldElement eCFieldElement10 = eCPoint.x;
            if (coordinateSystem != 6) {
                switch (coordinateSystem) {
                    case 0:
                        ECFieldElement eCFieldElement11 = this.y;
                        ECFieldElement eCFieldElement12 = eCPoint.y;
                        ECFieldElement add = eCFieldElement9.add(eCFieldElement10);
                        ECFieldElement add2 = eCFieldElement11.add(eCFieldElement12);
                        if (!add.isZero()) {
                            ECFieldElement divide = add2.divide(add);
                            ECFieldElement add3 = divide.square().add(divide).add(add).add(curve.getA());
                            return new F2m(curve, add3, divide.multiply(eCFieldElement9.add(add3)).add(add3).add(eCFieldElement11), this.withCompression);
                        } else if (add2.isZero()) {
                            return twice();
                        } else {
                            return curve.getInfinity();
                        }
                    case 1:
                        ECFieldElement eCFieldElement13 = this.y;
                        ECFieldElement eCFieldElement14 = this.zs[0];
                        ECFieldElement eCFieldElement15 = eCPoint.y;
                        ECFieldElement eCFieldElement16 = eCPoint.zs[0];
                        boolean isOne = eCFieldElement16.isOne();
                        ECFieldElement multiply = eCFieldElement14.multiply(eCFieldElement15);
                        if (isOne) {
                            eCFieldElement7 = eCFieldElement13;
                        } else {
                            eCFieldElement7 = eCFieldElement13.multiply(eCFieldElement16);
                        }
                        ECFieldElement add4 = multiply.add(eCFieldElement7);
                        ECFieldElement multiply2 = eCFieldElement14.multiply(eCFieldElement10);
                        if (isOne) {
                            eCFieldElement8 = eCFieldElement9;
                        } else {
                            eCFieldElement8 = eCFieldElement9.multiply(eCFieldElement16);
                        }
                        ECFieldElement add5 = multiply2.add(eCFieldElement8);
                        if (!add5.isZero()) {
                            ECFieldElement square = add5.square();
                            ECFieldElement multiply3 = square.multiply(add5);
                            if (!isOne) {
                                eCFieldElement14 = eCFieldElement14.multiply(eCFieldElement16);
                            }
                            ECFieldElement add6 = add4.add(add5);
                            ECFieldElement add7 = add6.multiplyPlusProduct(add4, square, curve.getA()).multiply(eCFieldElement14).add(multiply3);
                            ECFieldElement multiply4 = add5.multiply(add7);
                            if (!isOne) {
                                square = square.multiply(eCFieldElement16);
                            }
                            return new F2m(curve, multiply4, add4.multiplyPlusProduct(eCFieldElement9, add5, eCFieldElement13).multiplyPlusProduct(square, add6, add7), new ECFieldElement[]{multiply3.multiply(eCFieldElement14)}, this.withCompression);
                        } else if (add4.isZero()) {
                            return twice();
                        } else {
                            return curve.getInfinity();
                        }
                    default:
                        throw new IllegalStateException("unsupported coordinate system");
                }
            } else if (!eCFieldElement9.isZero()) {
                ECFieldElement eCFieldElement17 = this.y;
                ECFieldElement eCFieldElement18 = this.zs[0];
                ECFieldElement eCFieldElement19 = eCPoint.y;
                ECFieldElement eCFieldElement20 = eCPoint.zs[0];
                boolean isOne2 = eCFieldElement18.isOne();
                if (!isOne2) {
                    eCFieldElement2 = eCFieldElement10.multiply(eCFieldElement18);
                    eCFieldElement = eCFieldElement19.multiply(eCFieldElement18);
                } else {
                    eCFieldElement2 = eCFieldElement10;
                    eCFieldElement = eCFieldElement19;
                }
                boolean isOne3 = eCFieldElement20.isOne();
                if (!isOne3) {
                    eCFieldElement9 = eCFieldElement9.multiply(eCFieldElement20);
                    eCFieldElement3 = eCFieldElement17.multiply(eCFieldElement20);
                } else {
                    eCFieldElement3 = eCFieldElement17;
                }
                ECFieldElement add8 = eCFieldElement3.add(eCFieldElement);
                ECFieldElement add9 = eCFieldElement9.add(eCFieldElement2);
                if (!add9.isZero()) {
                    if (eCFieldElement10.isZero()) {
                        ECPoint normalize = normalize();
                        ECFieldElement xCoord = normalize.getXCoord();
                        ECFieldElement yCoord = normalize.getYCoord();
                        ECFieldElement divide2 = yCoord.add(eCFieldElement19).divide(xCoord);
                        eCFieldElement6 = divide2.square().add(divide2).add(xCoord).add(curve.getA());
                        if (eCFieldElement6.isZero()) {
                            return new F2m(curve, eCFieldElement6, curve.getB().sqrt(), this.withCompression);
                        }
                        eCFieldElement5 = divide2.multiply(xCoord.add(eCFieldElement6)).add(eCFieldElement6).add(yCoord).divide(eCFieldElement6).add(eCFieldElement6);
                        eCFieldElement4 = curve.fromBigInteger(ECConstants.ONE);
                    } else {
                        ECFieldElement square2 = add9.square();
                        ECFieldElement multiply5 = add8.multiply(eCFieldElement9);
                        ECFieldElement multiply6 = add8.multiply(eCFieldElement2);
                        ECFieldElement multiply7 = multiply5.multiply(multiply6);
                        if (multiply7.isZero()) {
                            return new F2m(curve, multiply7, curve.getB().sqrt(), this.withCompression);
                        }
                        ECFieldElement multiply8 = add8.multiply(square2);
                        eCFieldElement4 = !isOne3 ? multiply8.multiply(eCFieldElement20) : multiply8;
                        ECFieldElement squarePlusProduct = multiply6.add(square2).squarePlusProduct(eCFieldElement4, eCFieldElement17.add(eCFieldElement18));
                        if (!isOne2) {
                            eCFieldElement4 = eCFieldElement4.multiply(eCFieldElement18);
                            eCFieldElement5 = squarePlusProduct;
                            eCFieldElement6 = multiply7;
                        } else {
                            eCFieldElement5 = squarePlusProduct;
                            eCFieldElement6 = multiply7;
                        }
                    }
                    return new F2m(curve, eCFieldElement6, eCFieldElement5, new ECFieldElement[]{eCFieldElement4}, this.withCompression);
                } else if (add8.isZero()) {
                    return twice();
                } else {
                    return curve.getInfinity();
                }
            } else if (eCFieldElement10.isZero()) {
                return curve.getInfinity();
            } else {
                return eCPoint.add(this);
            }
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint twice() {
            ECFieldElement eCFieldElement;
            ECFieldElement eCFieldElement2;
            ECFieldElement eCFieldElement3;
            ECFieldElement eCFieldElement4;
            ECFieldElement eCFieldElement5;
            ECFieldElement eCFieldElement6;
            ECFieldElement eCFieldElement7;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement8 = this.x;
            if (eCFieldElement8.isZero()) {
                return curve.getInfinity();
            }
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem != 6) {
                switch (coordinateSystem) {
                    case 0:
                        ECFieldElement add = this.y.divide(eCFieldElement8).add(eCFieldElement8);
                        ECFieldElement add2 = add.square().add(add).add(curve.getA());
                        return new F2m(curve, add2, eCFieldElement8.squarePlusProduct(add2, add.addOne()), this.withCompression);
                    case 1:
                        ECFieldElement eCFieldElement9 = this.y;
                        ECFieldElement eCFieldElement10 = this.zs[0];
                        boolean isOne = eCFieldElement10.isOne();
                        if (isOne) {
                            eCFieldElement7 = eCFieldElement8;
                        } else {
                            eCFieldElement7 = eCFieldElement8.multiply(eCFieldElement10);
                        }
                        if (!isOne) {
                            eCFieldElement9 = eCFieldElement9.multiply(eCFieldElement10);
                        }
                        ECFieldElement square = eCFieldElement8.square();
                        ECFieldElement add3 = square.add(eCFieldElement9);
                        ECFieldElement square2 = eCFieldElement7.square();
                        ECFieldElement add4 = add3.add(eCFieldElement7);
                        ECFieldElement multiplyPlusProduct = add4.multiplyPlusProduct(add3, square2, curve.getA());
                        return new F2m(curve, eCFieldElement7.multiply(multiplyPlusProduct), square.square().multiplyPlusProduct(eCFieldElement7, multiplyPlusProduct, add4), new ECFieldElement[]{eCFieldElement7.multiply(square2)}, this.withCompression);
                    default:
                        throw new IllegalStateException("unsupported coordinate system");
                }
            } else {
                ECFieldElement eCFieldElement11 = this.y;
                ECFieldElement eCFieldElement12 = this.zs[0];
                boolean isOne2 = eCFieldElement12.isOne();
                if (isOne2) {
                    eCFieldElement = eCFieldElement11;
                } else {
                    eCFieldElement = eCFieldElement11.multiply(eCFieldElement12);
                }
                if (isOne2) {
                    eCFieldElement2 = eCFieldElement12;
                } else {
                    eCFieldElement2 = eCFieldElement12.square();
                }
                ECFieldElement a2 = curve.getA();
                if (isOne2) {
                    eCFieldElement3 = a2;
                } else {
                    eCFieldElement3 = a2.multiply(eCFieldElement2);
                }
                ECFieldElement add5 = eCFieldElement11.square().add(eCFieldElement).add(eCFieldElement3);
                if (add5.isZero()) {
                    return new F2m(curve, add5, curve.getB().sqrt(), this.withCompression);
                }
                ECFieldElement square3 = add5.square();
                if (isOne2) {
                    eCFieldElement4 = add5;
                } else {
                    eCFieldElement4 = add5.multiply(eCFieldElement2);
                }
                ECFieldElement b2 = curve.getB();
                if (b2.bitLength() < (curve.getFieldSize() >> 1)) {
                    ECFieldElement square4 = eCFieldElement11.add(eCFieldElement8).square();
                    if (b2.isOne()) {
                        eCFieldElement6 = eCFieldElement3.add(eCFieldElement2).square();
                    } else {
                        eCFieldElement6 = eCFieldElement3.squarePlusProduct(b2, eCFieldElement2.square());
                    }
                    eCFieldElement5 = square4.add(add5).add(eCFieldElement2).multiply(square4).add(eCFieldElement6).add(square3);
                    if (a2.isZero()) {
                        eCFieldElement5 = eCFieldElement5.add(eCFieldElement4);
                    } else if (!a2.isOne()) {
                        eCFieldElement5 = eCFieldElement5.add(a2.addOne().multiply(eCFieldElement4));
                    }
                } else {
                    if (!isOne2) {
                        eCFieldElement8 = eCFieldElement8.multiply(eCFieldElement12);
                    }
                    eCFieldElement5 = eCFieldElement8.squarePlusProduct(add5, eCFieldElement).add(square3).add(eCFieldElement4);
                }
                return new F2m(curve, square3, eCFieldElement5, new ECFieldElement[]{eCFieldElement4}, this.withCompression);
            }
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
            if (curve.getCoordinateSystem() != 6) {
                return twice().add(eCPoint);
            }
            ECFieldElement eCFieldElement2 = eCPoint.x;
            ECFieldElement eCFieldElement3 = eCPoint.zs[0];
            if (eCFieldElement2.isZero() || !eCFieldElement3.isOne()) {
                return twice().add(eCPoint);
            }
            ECFieldElement eCFieldElement4 = this.y;
            ECFieldElement eCFieldElement5 = this.zs[0];
            ECFieldElement eCFieldElement6 = eCPoint.y;
            ECFieldElement square = eCFieldElement.square();
            ECFieldElement square2 = eCFieldElement4.square();
            ECFieldElement square3 = eCFieldElement5.square();
            ECFieldElement add = curve.getA().multiply(square3).add(square2).add(eCFieldElement4.multiply(eCFieldElement5));
            ECFieldElement addOne = eCFieldElement6.addOne();
            ECFieldElement multiplyPlusProduct = curve.getA().add(addOne).multiply(square3).add(square2).multiplyPlusProduct(add, square, square3);
            ECFieldElement multiply = eCFieldElement2.multiply(square3);
            ECFieldElement square4 = multiply.add(add).square();
            if (square4.isZero()) {
                if (multiplyPlusProduct.isZero()) {
                    return eCPoint.twice();
                }
                return curve.getInfinity();
            } else if (multiplyPlusProduct.isZero()) {
                return new F2m(curve, multiplyPlusProduct, curve.getB().sqrt(), this.withCompression);
            } else {
                ECFieldElement multiply2 = multiplyPlusProduct.square().multiply(multiply);
                ECFieldElement multiply3 = multiplyPlusProduct.multiply(square4).multiply(square3);
                return new F2m(curve, multiply2, multiplyPlusProduct.add(square4).square().multiplyPlusProduct(add, addOne, multiply3), new ECFieldElement[]{multiply3}, this.withCompression);
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
            switch (getCurveCoordinateSystem()) {
                case 0:
                    return new F2m(this.curve, eCFieldElement, this.y.add(eCFieldElement), this.withCompression);
                case 1:
                    ECFieldElement eCFieldElement2 = this.y;
                    ECFieldElement eCFieldElement3 = this.zs[0];
                    return new F2m(this.curve, eCFieldElement, eCFieldElement2.add(eCFieldElement), new ECFieldElement[]{eCFieldElement3}, this.withCompression);
                case 2:
                case 3:
                case 4:
                default:
                    throw new IllegalStateException("unsupported coordinate system");
                case 5:
                    return new F2m(this.curve, eCFieldElement, this.y.addOne(), this.withCompression);
                case 6:
                    ECFieldElement eCFieldElement4 = this.y;
                    ECFieldElement eCFieldElement5 = this.zs[0];
                    return new F2m(this.curve, eCFieldElement, eCFieldElement4.add(eCFieldElement5), new ECFieldElement[]{eCFieldElement5}, this.withCompression);
            }
        }
    }
}
