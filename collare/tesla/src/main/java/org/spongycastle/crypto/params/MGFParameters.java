package org.spongycastle.crypto.params;

import org.spongycastle.crypto.DerivationParameters;

public class MGFParameters implements DerivationParameters {
    byte[] seed;

    public MGFParameters(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public MGFParameters(byte[] bArr, int i, int i2) {
        this.seed = new byte[i2];
        System.arraycopy(bArr, i, this.seed, 0, i2);
    }

    public byte[] getSeed() {
        return this.seed;
    }
}
