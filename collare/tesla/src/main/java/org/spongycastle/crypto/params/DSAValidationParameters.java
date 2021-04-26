package org.spongycastle.crypto.params;

import org.spongycastle.util.Arrays;

public class DSAValidationParameters {
    private int counter;
    private byte[] seed;
    private int usageIndex;

    public DSAValidationParameters(byte[] bArr, int i) {
        this(bArr, i, -1);
    }

    public DSAValidationParameters(byte[] bArr, int i, int i2) {
        this.seed = bArr;
        this.counter = i;
        this.usageIndex = i2;
    }

    public int getCounter() {
        return this.counter;
    }

    public byte[] getSeed() {
        return this.seed;
    }

    public int getUsageIndex() {
        return this.usageIndex;
    }

    public int hashCode() {
        return this.counter ^ Arrays.hashCode(this.seed);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DSAValidationParameters)) {
            return false;
        }
        DSAValidationParameters dSAValidationParameters = (DSAValidationParameters) obj;
        if (dSAValidationParameters.counter != this.counter) {
            return false;
        }
        return Arrays.areEqual(this.seed, dSAValidationParameters.seed);
    }
}
