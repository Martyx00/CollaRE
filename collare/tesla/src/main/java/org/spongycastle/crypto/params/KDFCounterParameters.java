package org.spongycastle.crypto.params;

import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.util.Arrays;

public final class KDFCounterParameters implements DerivationParameters {
    private byte[] fixedInputDataCounterPrefix;
    private byte[] fixedInputDataCounterSuffix;
    private byte[] ki;
    private int r;

    public KDFCounterParameters(byte[] bArr, byte[] bArr2, int i) {
        this(bArr, null, bArr2, i);
    }

    public KDFCounterParameters(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        if (bArr != null) {
            this.ki = Arrays.clone(bArr);
            if (bArr2 == null) {
                this.fixedInputDataCounterPrefix = new byte[0];
            } else {
                this.fixedInputDataCounterPrefix = Arrays.clone(bArr2);
            }
            if (bArr3 == null) {
                this.fixedInputDataCounterSuffix = new byte[0];
            } else {
                this.fixedInputDataCounterSuffix = Arrays.clone(bArr3);
            }
            if (i == 8 || i == 16 || i == 24 || i == 32) {
                this.r = i;
                return;
            }
            throw new IllegalArgumentException("Length of counter should be 8, 16, 24 or 32");
        }
        throw new IllegalArgumentException("A KDF requires Ki (a seed) as input");
    }

    public byte[] getKI() {
        return this.ki;
    }

    public byte[] getFixedInputData() {
        return Arrays.clone(this.fixedInputDataCounterSuffix);
    }

    public byte[] getFixedInputDataCounterPrefix() {
        return Arrays.clone(this.fixedInputDataCounterPrefix);
    }

    public byte[] getFixedInputDataCounterSuffix() {
        return Arrays.clone(this.fixedInputDataCounterSuffix);
    }

    public int getR() {
        return this.r;
    }
}
