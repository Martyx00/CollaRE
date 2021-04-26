package org.spongycastle.crypto.prng;

import java.security.SecureRandom;

public class X931SecureRandom extends SecureRandom {
    private final X931RNG drbg;
    private final boolean predictionResistant;
    private final SecureRandom randomSource;

    X931SecureRandom(SecureRandom secureRandom, X931RNG x931rng, boolean z) {
        this.randomSource = secureRandom;
        this.drbg = x931rng;
        this.predictionResistant = z;
    }

    @Override // java.security.SecureRandom
    public void setSeed(byte[] bArr) {
        synchronized (this) {
            if (this.randomSource != null) {
                this.randomSource.setSeed(bArr);
            }
        }
    }

    @Override // java.security.SecureRandom
    public void setSeed(long j) {
        synchronized (this) {
            if (this.randomSource != null) {
                this.randomSource.setSeed(j);
            }
        }
    }

    public void nextBytes(byte[] bArr) {
        synchronized (this) {
            if (this.drbg.generate(bArr, this.predictionResistant) < 0) {
                this.drbg.reseed();
                this.drbg.generate(bArr, this.predictionResistant);
            }
        }
    }

    public byte[] generateSeed(int i) {
        return EntropyUtil.generateSeed(this.drbg.getEntropySource(), i);
    }
}
