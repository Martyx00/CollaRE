package org.spongycastle.crypto;

public interface AsymmetricBlockCipher {
    int getInputBlockSize();

    int getOutputBlockSize();

    void init(boolean z, CipherParameters cipherParameters);

    byte[] processBlock(byte[] bArr, int i, int i2);
}
