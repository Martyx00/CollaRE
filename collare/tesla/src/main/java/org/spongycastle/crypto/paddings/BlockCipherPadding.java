package org.spongycastle.crypto.paddings;

import java.security.SecureRandom;

public interface BlockCipherPadding {
    int addPadding(byte[] bArr, int i);

    String getPaddingName();

    void init(SecureRandom secureRandom);

    int padCount(byte[] bArr);
}
