package org.spongycastle.crypto;

public interface StreamCipher {
    String getAlgorithmName();

    void init(boolean z, CipherParameters cipherParameters);

    int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3);

    void reset();

    byte returnByte(byte b2);
}
