package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;

public class Poly1305KeyGenerator extends CipherKeyGenerator {
    private static final byte R_MASK_HIGH_4 = 15;
    private static final byte R_MASK_LOW_2 = -4;

    @Override // org.spongycastle.crypto.CipherKeyGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        super.init(new KeyGenerationParameters(keyGenerationParameters.getRandom(), 256));
    }

    @Override // org.spongycastle.crypto.CipherKeyGenerator
    public byte[] generateKey() {
        byte[] generateKey = super.generateKey();
        clamp(generateKey);
        return generateKey;
    }

    public static void clamp(byte[] bArr) {
        if (bArr.length == 32) {
            bArr[3] = (byte) (bArr[3] & R_MASK_HIGH_4);
            bArr[7] = (byte) (bArr[7] & R_MASK_HIGH_4);
            bArr[11] = (byte) (bArr[11] & R_MASK_HIGH_4);
            bArr[15] = (byte) (bArr[15] & R_MASK_HIGH_4);
            bArr[4] = (byte) (bArr[4] & R_MASK_LOW_2);
            bArr[8] = (byte) (bArr[8] & R_MASK_LOW_2);
            bArr[12] = (byte) (bArr[12] & R_MASK_LOW_2);
            return;
        }
        throw new IllegalArgumentException("Poly1305 key must be 256 bits.");
    }

    public static void checkKey(byte[] bArr) {
        if (bArr.length == 32) {
            checkMask(bArr[3], R_MASK_HIGH_4);
            checkMask(bArr[7], R_MASK_HIGH_4);
            checkMask(bArr[11], R_MASK_HIGH_4);
            checkMask(bArr[15], R_MASK_HIGH_4);
            checkMask(bArr[4], R_MASK_LOW_2);
            checkMask(bArr[8], R_MASK_LOW_2);
            checkMask(bArr[12], R_MASK_LOW_2);
            return;
        }
        throw new IllegalArgumentException("Poly1305 key must be 256 bits.");
    }

    private static void checkMask(byte b2, byte b3) {
        if ((b2 & (~b3)) != 0) {
            throw new IllegalArgumentException("Invalid format for r portion of Poly1305 key.");
        }
    }
}
