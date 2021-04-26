package org.spongycastle.pqc.crypto;

import org.spongycastle.crypto.CipherParameters;

public interface MessageEncryptor {
    void init(boolean z, CipherParameters cipherParameters);

    byte[] messageDecrypt(byte[] bArr);

    byte[] messageEncrypt(byte[] bArr);
}
