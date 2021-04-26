package org.spongycastle.jce.interfaces;

import java.math.BigInteger;
import javax.crypto.interfaces.DHPrivateKey;

public interface ElGamalPrivateKey extends DHPrivateKey, ElGamalKey {
    BigInteger getX();
}
