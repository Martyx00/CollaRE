package org.spongycastle.jce.interfaces;

import java.security.PublicKey;
import org.spongycastle.math.ec.ECPoint;

public interface ECPublicKey extends PublicKey, ECKey {
    ECPoint getQ();
}
