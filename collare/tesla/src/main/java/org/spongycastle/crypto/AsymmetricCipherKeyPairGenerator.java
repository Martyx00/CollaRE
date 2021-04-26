package org.spongycastle.crypto;

public interface AsymmetricCipherKeyPairGenerator {
    AsymmetricCipherKeyPair generateKeyPair();

    void init(KeyGenerationParameters keyGenerationParameters);
}
