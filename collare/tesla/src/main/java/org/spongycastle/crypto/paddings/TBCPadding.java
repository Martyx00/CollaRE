package org.spongycastle.crypto.paddings;

import java.security.SecureRandom;

public class TBCPadding implements BlockCipherPadding {
    @Override // org.spongycastle.crypto.paddings.BlockCipherPadding
    public String getPaddingName() {
        return "TBC";
    }

    @Override // org.spongycastle.crypto.paddings.BlockCipherPadding
    public void init(SecureRandom secureRandom) {
    }

    @Override // org.spongycastle.crypto.paddings.BlockCipherPadding
    public int addPadding(byte[] bArr, int i) {
        byte b2;
        int length = bArr.length - i;
        int i2 = 255;
        if (i > 0) {
            if ((bArr[i - 1] & 1) != 0) {
                i2 = 0;
            }
            b2 = (byte) i2;
        } else {
            if ((bArr[bArr.length - 1] & 1) != 0) {
                i2 = 0;
            }
            b2 = (byte) i2;
        }
        while (i < bArr.length) {
            bArr[i] = b2;
            i++;
        }
        return length;
    }

    @Override // org.spongycastle.crypto.paddings.BlockCipherPadding
    public int padCount(byte[] bArr) {
        byte b2 = bArr[bArr.length - 1];
        int length = bArr.length - 1;
        while (length > 0 && bArr[length - 1] == b2) {
            length--;
        }
        return bArr.length - length;
    }
}
