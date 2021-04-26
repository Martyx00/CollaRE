package org.spongycastle.crypto.paddings;

import java.security.SecureRandom;
import org.spongycastle.crypto.InvalidCipherTextException;

public class PKCS7Padding implements BlockCipherPadding {
    @Override // org.spongycastle.crypto.paddings.BlockCipherPadding
    public String getPaddingName() {
        return "PKCS7";
    }

    @Override // org.spongycastle.crypto.paddings.BlockCipherPadding
    public void init(SecureRandom secureRandom) {
    }

    @Override // org.spongycastle.crypto.paddings.BlockCipherPadding
    public int addPadding(byte[] bArr, int i) {
        byte length = (byte) (bArr.length - i);
        while (i < bArr.length) {
            bArr[i] = length;
            i++;
        }
        return length;
    }

    @Override // org.spongycastle.crypto.paddings.BlockCipherPadding
    public int padCount(byte[] bArr) {
        int i = bArr[bArr.length - 1] & 255;
        byte b2 = (byte) i;
        boolean z = (i > bArr.length) | (i == 0);
        for (int i2 = 0; i2 < bArr.length; i2++) {
            z |= (bArr.length - i2 <= i) & (bArr[i2] != b2);
        }
        if (!z) {
            return i;
        }
        throw new InvalidCipherTextException("pad block corrupted");
    }
}
