package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.DESedeParameters;

public class DESedeKeyGenerator extends DESKeyGenerator {
    private static final int MAX_IT = 20;

    @Override // org.spongycastle.crypto.CipherKeyGenerator, org.spongycastle.crypto.generators.DESKeyGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.random = keyGenerationParameters.getRandom();
        this.strength = (keyGenerationParameters.getStrength() + 7) / 8;
        if (this.strength == 0 || this.strength == 21) {
            this.strength = 24;
        } else if (this.strength == 14) {
            this.strength = 16;
        } else if (this.strength != 24 && this.strength != 16) {
            throw new IllegalArgumentException("DESede key must be 192 or 128 bits long.");
        }
    }

    @Override // org.spongycastle.crypto.CipherKeyGenerator, org.spongycastle.crypto.generators.DESKeyGenerator
    public byte[] generateKey() {
        byte[] bArr = new byte[this.strength];
        int i = 0;
        while (true) {
            this.random.nextBytes(bArr);
            DESedeParameters.setOddParity(bArr);
            i++;
            if (i >= 20 || (!DESedeParameters.isWeakKey(bArr, 0, bArr.length) && DESedeParameters.isRealEDEKey(bArr, 0))) {
            }
        }
        if (!DESedeParameters.isWeakKey(bArr, 0, bArr.length) && DESedeParameters.isRealEDEKey(bArr, 0)) {
            return bArr;
        }
        throw new IllegalStateException("Unable to generate DES-EDE key");
    }
}
