package org.spongycastle.pqc.crypto;

import org.spongycastle.crypto.CipherParameters;

public interface MessageSigner {
    byte[] generateSignature(byte[] bArr);

    void init(boolean z, CipherParameters cipherParameters);

    boolean verifySignature(byte[] bArr, byte[] bArr2);
}
