package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

public class RSABlindingParameters implements CipherParameters {
    private BigInteger blindingFactor;
    private RSAKeyParameters publicKey;

    public RSABlindingParameters(RSAKeyParameters rSAKeyParameters, BigInteger bigInteger) {
        if (!(rSAKeyParameters instanceof RSAPrivateCrtKeyParameters)) {
            this.publicKey = rSAKeyParameters;
            this.blindingFactor = bigInteger;
            return;
        }
        throw new IllegalArgumentException("RSA parameters should be for a public key");
    }

    public RSAKeyParameters getPublicKey() {
        return this.publicKey;
    }

    public BigInteger getBlindingFactor() {
        return this.blindingFactor;
    }
}
