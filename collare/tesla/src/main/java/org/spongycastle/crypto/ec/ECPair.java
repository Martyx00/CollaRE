package org.spongycastle.crypto.ec;

import org.spongycastle.math.ec.ECPoint;

public class ECPair {
    private final ECPoint x;
    private final ECPoint y;

    public ECPair(ECPoint eCPoint, ECPoint eCPoint2) {
        this.x = eCPoint;
        this.y = eCPoint2;
    }

    public ECPoint getX() {
        return this.x;
    }

    public ECPoint getY() {
        return this.y;
    }

    public boolean equals(ECPair eCPair) {
        return eCPair.getX().equals(getX()) && eCPair.getY().equals(getY());
    }

    public boolean equals(Object obj) {
        if (obj instanceof ECPair) {
            return equals((ECPair) obj);
        }
        return false;
    }

    public int hashCode() {
        return this.x.hashCode() + (this.y.hashCode() * 37);
    }
}
