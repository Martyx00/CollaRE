package org.spongycastle.jce.spec;

import org.spongycastle.math.ec.ECPoint;

public class ECPublicKeySpec extends ECKeySpec {
    private ECPoint q;

    public ECPublicKeySpec(ECPoint eCPoint, ECParameterSpec eCParameterSpec) {
        super(eCParameterSpec);
        if (eCPoint.getCurve() != null) {
            this.q = eCPoint.normalize();
        } else {
            this.q = eCPoint;
        }
    }

    public ECPoint getQ() {
        return this.q;
    }
}
