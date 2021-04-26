package org.spongycastle.crypto.tls;

public interface TlsCipher {
    byte[] decodeCiphertext(long j, short s, byte[] bArr, int i, int i2);

    byte[] encodePlaintext(long j, short s, byte[] bArr, int i, int i2);

    int getPlaintextLimit(int i);
}
