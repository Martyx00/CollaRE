package org.spongycastle.crypto;

public interface DigestDerivationFunction extends DerivationFunction {
    Digest getDigest();
}
