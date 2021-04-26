package org.spongycastle.math.ec;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECPoint;

/* access modifiers changed from: package-private */
public class Tnaf {
    private static final BigInteger MINUS_ONE = ECConstants.ONE.negate();
    private static final BigInteger MINUS_THREE = ECConstants.THREE.negate();
    private static final BigInteger MINUS_TWO = ECConstants.TWO.negate();
    public static final byte POW_2_WIDTH = 16;
    public static final byte WIDTH = 4;
    public static final ZTauElement[] alpha0;
    public static final byte[][] alpha0Tnaf = {null, new byte[]{1}, null, new byte[]{-1, 0, 1}, null, new byte[]{1, 0, 1}, null, new byte[]{-1, 0, 0, 1}};
    public static final ZTauElement[] alpha1 = {null, new ZTauElement(ECConstants.ONE, ECConstants.ZERO), null, new ZTauElement(MINUS_THREE, ECConstants.ONE), null, new ZTauElement(MINUS_ONE, ECConstants.ONE), null, new ZTauElement(ECConstants.ONE, ECConstants.ONE), null};
    public static final byte[][] alpha1Tnaf = {null, new byte[]{1}, null, new byte[]{-1, 0, 1}, null, new byte[]{1, 0, 1}, null, new byte[]{-1, 0, 0, -1}};

    public static byte getMu(int i) {
        return (byte) (i == 0 ? -1 : 1);
    }

    Tnaf() {
    }

    static {
        BigInteger bigInteger = MINUS_ONE;
        alpha0 = new ZTauElement[]{null, new ZTauElement(ECConstants.ONE, ECConstants.ZERO), null, new ZTauElement(MINUS_THREE, MINUS_ONE), null, new ZTauElement(bigInteger, bigInteger), null, new ZTauElement(ECConstants.ONE, MINUS_ONE), null};
    }

    public static BigInteger norm(byte b2, ZTauElement zTauElement) {
        BigInteger multiply = zTauElement.u.multiply(zTauElement.u);
        BigInteger multiply2 = zTauElement.u.multiply(zTauElement.v);
        BigInteger shiftLeft = zTauElement.v.multiply(zTauElement.v).shiftLeft(1);
        if (b2 == 1) {
            return multiply.add(multiply2).add(shiftLeft);
        }
        if (b2 == -1) {
            return multiply.subtract(multiply2).add(shiftLeft);
        }
        throw new IllegalArgumentException("mu must be 1 or -1");
    }

    public static SimpleBigDecimal norm(byte b2, SimpleBigDecimal simpleBigDecimal, SimpleBigDecimal simpleBigDecimal2) {
        SimpleBigDecimal multiply = simpleBigDecimal.multiply(simpleBigDecimal);
        SimpleBigDecimal multiply2 = simpleBigDecimal.multiply(simpleBigDecimal2);
        SimpleBigDecimal shiftLeft = simpleBigDecimal2.multiply(simpleBigDecimal2).shiftLeft(1);
        if (b2 == 1) {
            return multiply.add(multiply2).add(shiftLeft);
        }
        if (b2 == -1) {
            return multiply.subtract(multiply2).add(shiftLeft);
        }
        throw new IllegalArgumentException("mu must be 1 or -1");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x008c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.spongycastle.math.ec.ZTauElement round(org.spongycastle.math.ec.SimpleBigDecimal r7, org.spongycastle.math.ec.SimpleBigDecimal r8, byte r9) {
        /*
        // Method dump skipped, instructions count: 182
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.math.ec.Tnaf.round(org.spongycastle.math.ec.SimpleBigDecimal, org.spongycastle.math.ec.SimpleBigDecimal, byte):org.spongycastle.math.ec.ZTauElement");
    }

    public static SimpleBigDecimal approximateDivisionByN(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, byte b2, int i, int i2) {
        int i3 = ((i + 5) / 2) + i2;
        BigInteger multiply = bigInteger2.multiply(bigInteger.shiftRight(((i - i3) - 2) + b2));
        BigInteger add = multiply.add(bigInteger3.multiply(multiply.shiftRight(i)));
        int i4 = i3 - i2;
        BigInteger shiftRight = add.shiftRight(i4);
        if (add.testBit(i4 - 1)) {
            shiftRight = shiftRight.add(ECConstants.ONE);
        }
        return new SimpleBigDecimal(shiftRight, i2);
    }

    public static byte[] tauAdicNaf(byte b2, ZTauElement zTauElement) {
        BigInteger bigInteger;
        if (b2 == 1 || b2 == -1) {
            int bitLength = norm(b2, zTauElement).bitLength();
            byte[] bArr = new byte[(bitLength > 30 ? bitLength + 4 : 34)];
            BigInteger bigInteger2 = zTauElement.u;
            BigInteger bigInteger3 = zTauElement.v;
            int i = 0;
            int i2 = 0;
            while (true) {
                if (!bigInteger2.equals(ECConstants.ZERO) || !bigInteger3.equals(ECConstants.ZERO)) {
                    if (bigInteger2.testBit(0)) {
                        bArr[i2] = (byte) ECConstants.TWO.subtract(bigInteger2.subtract(bigInteger3.shiftLeft(1)).mod(ECConstants.FOUR)).intValue();
                        if (bArr[i2] == 1) {
                            bigInteger2 = bigInteger2.clearBit(0);
                        } else {
                            bigInteger2 = bigInteger2.add(ECConstants.ONE);
                        }
                        i = i2;
                    } else {
                        bArr[i2] = 0;
                    }
                    BigInteger shiftRight = bigInteger2.shiftRight(1);
                    if (b2 == 1) {
                        bigInteger = bigInteger3.add(shiftRight);
                    } else {
                        bigInteger = bigInteger3.subtract(shiftRight);
                    }
                    BigInteger negate = bigInteger2.shiftRight(1).negate();
                    i2++;
                    bigInteger2 = bigInteger;
                    bigInteger3 = negate;
                } else {
                    int i3 = i + 1;
                    byte[] bArr2 = new byte[i3];
                    System.arraycopy(bArr, 0, bArr2, 0, i3);
                    return bArr2;
                }
            }
        } else {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
    }

    public static ECPoint.AbstractF2m tau(ECPoint.AbstractF2m abstractF2m) {
        return abstractF2m.tau();
    }

    public static byte getMu(ECCurve.AbstractF2m abstractF2m) {
        if (abstractF2m.isKoblitz()) {
            return abstractF2m.getA().isZero() ? (byte) -1 : 1;
        }
        throw new IllegalArgumentException("No Koblitz curve (ABC), TNAF multiplication not possible");
    }

    public static byte getMu(ECFieldElement eCFieldElement) {
        return (byte) (eCFieldElement.isZero() ? -1 : 1);
    }

    public static BigInteger[] getLucas(byte b2, int i, boolean z) {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        if (b2 == 1 || b2 == -1) {
            if (z) {
                bigInteger = ECConstants.TWO;
                bigInteger2 = BigInteger.valueOf((long) b2);
            } else {
                bigInteger = ECConstants.ZERO;
                bigInteger2 = ECConstants.ONE;
            }
            BigInteger bigInteger3 = bigInteger2;
            BigInteger bigInteger4 = bigInteger;
            int i2 = 1;
            while (i2 < i) {
                i2++;
                bigInteger3 = (b2 == 1 ? bigInteger3 : bigInteger3.negate()).subtract(bigInteger4.shiftLeft(1));
                bigInteger4 = bigInteger3;
            }
            return new BigInteger[]{bigInteger4, bigInteger3};
        }
        throw new IllegalArgumentException("mu must be 1 or -1");
    }

    public static BigInteger getTw(byte b2, int i) {
        if (i != 4) {
            BigInteger[] lucas = getLucas(b2, i, false);
            BigInteger bit = ECConstants.ZERO.setBit(i);
            return ECConstants.TWO.multiply(lucas[0]).multiply(lucas[1].modInverse(bit)).mod(bit);
        } else if (b2 == 1) {
            return BigInteger.valueOf(6);
        } else {
            return BigInteger.valueOf(10);
        }
    }

    public static BigInteger[] getSi(ECCurve.AbstractF2m abstractF2m) {
        if (abstractF2m.isKoblitz()) {
            int fieldSize = abstractF2m.getFieldSize();
            int intValue = abstractF2m.getA().toBigInteger().intValue();
            byte mu = getMu(intValue);
            int shiftsForCofactor = getShiftsForCofactor(abstractF2m.getCofactor());
            BigInteger[] lucas = getLucas(mu, (fieldSize + 3) - intValue, false);
            if (mu == 1) {
                lucas[0] = lucas[0].negate();
                lucas[1] = lucas[1].negate();
            }
            return new BigInteger[]{ECConstants.ONE.add(lucas[1]).shiftRight(shiftsForCofactor), ECConstants.ONE.add(lucas[0]).shiftRight(shiftsForCofactor).negate()};
        }
        throw new IllegalArgumentException("si is defined for Koblitz curves only");
    }

    public static BigInteger[] getSi(int i, int i2, BigInteger bigInteger) {
        byte mu = getMu(i2);
        int shiftsForCofactor = getShiftsForCofactor(bigInteger);
        BigInteger[] lucas = getLucas(mu, (i + 3) - i2, false);
        if (mu == 1) {
            lucas[0] = lucas[0].negate();
            lucas[1] = lucas[1].negate();
        }
        return new BigInteger[]{ECConstants.ONE.add(lucas[1]).shiftRight(shiftsForCofactor), ECConstants.ONE.add(lucas[0]).shiftRight(shiftsForCofactor).negate()};
    }

    protected static int getShiftsForCofactor(BigInteger bigInteger) {
        if (bigInteger != null) {
            if (bigInteger.equals(ECConstants.TWO)) {
                return 1;
            }
            if (bigInteger.equals(ECConstants.FOUR)) {
                return 2;
            }
        }
        throw new IllegalArgumentException("h (Cofactor) must be 2 or 4");
    }

    public static ZTauElement partModReduction(BigInteger bigInteger, int i, byte b2, BigInteger[] bigIntegerArr, byte b3, byte b4) {
        BigInteger bigInteger2;
        if (b3 == 1) {
            bigInteger2 = bigIntegerArr[0].add(bigIntegerArr[1]);
        } else {
            bigInteger2 = bigIntegerArr[0].subtract(bigIntegerArr[1]);
        }
        BigInteger bigInteger3 = getLucas(b3, i, true)[1];
        ZTauElement round = round(approximateDivisionByN(bigInteger, bigIntegerArr[0], bigInteger3, b2, i, b4), approximateDivisionByN(bigInteger, bigIntegerArr[1], bigInteger3, b2, i, b4), b3);
        return new ZTauElement(bigInteger.subtract(bigInteger2.multiply(round.u)).subtract(BigInteger.valueOf(2).multiply(bigIntegerArr[1]).multiply(round.v)), bigIntegerArr[1].multiply(round.u).subtract(bigIntegerArr[0].multiply(round.v)));
    }

    public static ECPoint.AbstractF2m multiplyRTnaf(ECPoint.AbstractF2m abstractF2m, BigInteger bigInteger) {
        ECCurve.AbstractF2m abstractF2m2 = (ECCurve.AbstractF2m) abstractF2m.getCurve();
        int fieldSize = abstractF2m2.getFieldSize();
        int intValue = abstractF2m2.getA().toBigInteger().intValue();
        return multiplyTnaf(abstractF2m, partModReduction(bigInteger, fieldSize, (byte) intValue, abstractF2m2.getSi(), getMu(intValue), (byte) 10));
    }

    public static ECPoint.AbstractF2m multiplyTnaf(ECPoint.AbstractF2m abstractF2m, ZTauElement zTauElement) {
        return multiplyFromTnaf(abstractF2m, tauAdicNaf(getMu(((ECCurve.AbstractF2m) abstractF2m.getCurve()).getA()), zTauElement));
    }

    public static ECPoint.AbstractF2m multiplyFromTnaf(ECPoint.AbstractF2m abstractF2m, byte[] bArr) {
        ECPoint.AbstractF2m abstractF2m2 = (ECPoint.AbstractF2m) abstractF2m.negate();
        ECPoint.AbstractF2m abstractF2m3 = (ECPoint.AbstractF2m) abstractF2m.getCurve().getInfinity();
        int i = 0;
        for (int length = bArr.length - 1; length >= 0; length--) {
            i++;
            byte b2 = bArr[length];
            if (b2 != 0) {
                abstractF2m3 = (ECPoint.AbstractF2m) abstractF2m3.tauPow(i).add(b2 > 0 ? abstractF2m : abstractF2m2);
                i = 0;
            }
        }
        return i > 0 ? abstractF2m3.tauPow(i) : abstractF2m3;
    }

    public static byte[] tauAdicWNaf(byte b2, ZTauElement zTauElement, byte b3, BigInteger bigInteger, BigInteger bigInteger2, ZTauElement[] zTauElementArr) {
        BigInteger bigInteger3;
        byte b4;
        byte b5;
        boolean z;
        if (b2 == 1 || b2 == -1) {
            int bitLength = norm(b2, zTauElement).bitLength();
            byte[] bArr = new byte[(bitLength > 30 ? bitLength + 4 + b3 : b3 + 34)];
            BigInteger shiftRight = bigInteger.shiftRight(1);
            BigInteger bigInteger4 = zTauElement.u;
            BigInteger bigInteger5 = zTauElement.v;
            int i = 0;
            while (true) {
                if (bigInteger4.equals(ECConstants.ZERO) && bigInteger5.equals(ECConstants.ZERO)) {
                    return bArr;
                }
                if (bigInteger4.testBit(0)) {
                    BigInteger mod = bigInteger4.add(bigInteger5.multiply(bigInteger2)).mod(bigInteger);
                    if (mod.compareTo(shiftRight) >= 0) {
                        b4 = (byte) mod.subtract(bigInteger).intValue();
                    } else {
                        b4 = (byte) mod.intValue();
                    }
                    bArr[i] = b4;
                    if (b4 < 0) {
                        b5 = (byte) (-b4);
                        z = false;
                    } else {
                        b5 = b4;
                        z = true;
                    }
                    if (z) {
                        bigInteger4 = bigInteger4.subtract(zTauElementArr[b5].u);
                        bigInteger5 = bigInteger5.subtract(zTauElementArr[b5].v);
                    } else {
                        bigInteger4 = bigInteger4.add(zTauElementArr[b5].u);
                        bigInteger5 = bigInteger5.add(zTauElementArr[b5].v);
                    }
                } else {
                    bArr[i] = 0;
                }
                if (b2 == 1) {
                    bigInteger3 = bigInteger5.add(bigInteger4.shiftRight(1));
                } else {
                    bigInteger3 = bigInteger5.subtract(bigInteger4.shiftRight(1));
                }
                BigInteger negate = bigInteger4.shiftRight(1).negate();
                i++;
                bigInteger4 = bigInteger3;
                bigInteger5 = negate;
            }
        } else {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
    }

    public static ECPoint.AbstractF2m[] getPreComp(ECPoint.AbstractF2m abstractF2m, byte b2) {
        byte[][] bArr = b2 == 0 ? alpha0Tnaf : alpha1Tnaf;
        ECPoint.AbstractF2m[] abstractF2mArr = new ECPoint.AbstractF2m[((bArr.length + 1) >>> 1)];
        abstractF2mArr[0] = abstractF2m;
        int length = bArr.length;
        for (int i = 3; i < length; i += 2) {
            abstractF2mArr[i >>> 1] = multiplyFromTnaf(abstractF2m, bArr[i]);
        }
        abstractF2m.getCurve().normalizeAll(abstractF2mArr);
        return abstractF2mArr;
    }
}
