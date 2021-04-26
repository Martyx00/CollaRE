package org.spongycastle.crypto.params;

import org.spongycastle.math.ec.ECPoint;

public class ECPublicKeyParameters extends ECKeyParameters {
    private final ECPoint Q;

    public ECPublicKeyParameters(ECPoint eCPoint, ECDomainParameters eCDomainParameters) {
        super(false, eCDomainParameters);
        this.Q = validate(eCPoint);
    }

    private ECPoint validate(ECPoint eCPoint) {
        if (eCPoint == null) {
            throw new IllegalArgumentException("point has null value");
        } else if (!eCPoint.isInfinity()) {
            ECPoint normalize = eCPoint.normalize();
            if (normalize.isValid()) {
                return normalize;
            }
            throw new IllegalArgumentException("point not on curve");
        } else {
            throw new IllegalArgumentException("point at infinity");
        }
    }

    public ECPoint getQ() {
        return this.Q;
    }
}
