package org.spongycastle.crypto;

public interface Mac {
    int doFinal(byte[] bArr, int i);

    String getAlgorithmName();

    int getMacSize();

    void init(CipherParameters cipherParameters);

    void reset();

    void update(byte b2);

    void update(byte[] bArr, int i, int i2);
}
