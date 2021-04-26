package org.spongycastle.math.ec;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECPoint;

public class WTauNafMultiplier extends AbstractECMultiplier {
    static final String PRECOMP_NAME = "bc_wtnaf";

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.math.ec.AbstractECMultiplier
    public ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        if (eCPoint instanceof ECPoint.AbstractF2m) {
            ECPoint.AbstractF2m abstractF2m = (ECPoint.AbstractF2m) eCPoint;
            ECCurve.AbstractF2m abstractF2m2 = (ECCurve.AbstractF2m) abstractF2m.getCurve();
            int fieldSize = abstractF2m2.getFieldSize();
            byte byteValue = abstractF2m2.getA().toBigInteger().byteValue();
            byte mu = Tnaf.getMu(byteValue);
            return multiplyWTnaf(abstractF2m, Tnaf.partModReduction(bigInteger, fieldSize, byteValue, abstractF2m2.getSi(), mu, (byte) 10), abstractF2m2.getPreCompInfo(abstractF2m, PRECOMP_NAME), byteValue, mu);
        }
        throw new IllegalArgumentException("Only ECPoint.AbstractF2m can be used in WTauNafMultiplier");
    }

    private ECPoint.AbstractF2m multiplyWTnaf(ECPoint.AbstractF2m abstractF2m, ZTauElement zTauElement, PreCompInfo preCompInfo, byte b2, byte b3) {
        return multiplyFromWTnaf(abstractF2m, Tnaf.tauAdicWNaf(b3, zTauElement, (byte) 4, BigInteger.valueOf(16), Tnaf.getTw(b3, 4), b2 == 0 ? Tnaf.alpha0 : Tnaf.alpha1), preCompInfo);
    }

    private static ECPoint.AbstractF2m multiplyFromWTnaf(ECPoint.AbstractF2m abstractF2m, byte[] bArr, PreCompInfo preCompInfo) {
        ECPoint.AbstractF2m[] abstractF2mArr;
        ECCurve.AbstractF2m abstractF2m2 = (ECCurve.AbstractF2m) abstractF2m.getCurve();
        byte byteValue = abstractF2m2.getA().toBigInteger().byteValue();
        if (preCompInfo == null || !(preCompInfo instanceof WTauNafPreCompInfo)) {
            abstractF2mArr = Tnaf.getPreComp(abstractF2m, byteValue);
            WTauNafPreCompInfo wTauNafPreCompInfo = new WTauNafPreCompInfo();
            wTauNafPreCompInfo.setPreComp(abstractF2mArr);
            abstractF2m2.setPreCompInfo(abstractF2m, PRECOMP_NAME, wTauNafPreCompInfo);
        } else {
            abstractF2mArr = ((WTauNafPreCompInfo) preCompInfo).getPreComp();
        }
        ECPoint.AbstractF2m[] abstractF2mArr2 = new ECPoint.AbstractF2m[abstractF2mArr.length];
        for (int i = 0; i < abstractF2mArr.length; i++) {
            abstractF2mArr2[i] = (ECPoint.AbstractF2m) abstractF2mArr[i].negate();
        }
        ECPoint.AbstractF2m abstractF2m3 = (ECPoint.AbstractF2m) abstractF2m.getCurve().getInfinity();
        int i2 = 0;
        for (int length = bArr.length - 1; length >= 0; length--) {
            i2++;
            byte b2 = bArr[length];
            if (b2 != 0) {
                abstractF2m3 = (ECPoint.AbstractF2m) abstractF2m3.tauPow(i2).add(b2 > 0 ? abstractF2mArr[b2 >>> 1] : abstractF2mArr2[(-b2) >>> 1]);
                i2 = 0;
            }
        }
        return i2 > 0 ? abstractF2m3.tauPow(i2) : abstractF2m3;
    }
}
