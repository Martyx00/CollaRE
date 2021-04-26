package org.spongycastle.crypto;

public class MaxBytesExceededException extends RuntimeCryptoException {
    public MaxBytesExceededException() {
    }

    public MaxBytesExceededException(String str) {
        super(str);
    }
}
