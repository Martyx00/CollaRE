package org.spongycastle.crypto;

public interface Digest {
    int doFinal(byte[] bArr, int i);

    String getAlgorithmName();

    int getDigestSize();

    void reset();

    void update(byte b2);

    void update(byte[] bArr, int i, int i2);
}
