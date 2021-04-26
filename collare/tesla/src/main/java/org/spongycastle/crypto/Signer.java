package org.spongycastle.crypto;

public interface Signer {
    byte[] generateSignature();

    void init(boolean z, CipherParameters cipherParameters);

    void reset();

    void update(byte b2);

    void update(byte[] bArr, int i, int i2);

    boolean verifySignature(byte[] bArr);
}
