package org.spongycastle.crypto;

public interface DerivationFunction {
    int generateBytes(byte[] bArr, int i, int i2);

    void init(DerivationParameters derivationParameters);
}
