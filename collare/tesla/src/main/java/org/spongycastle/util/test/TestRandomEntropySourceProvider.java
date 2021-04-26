package org.spongycastle.util.test;

import java.security.SecureRandom;
import org.spongycastle.crypto.prng.EntropySource;
import org.spongycastle.crypto.prng.EntropySourceProvider;

public class TestRandomEntropySourceProvider implements EntropySourceProvider {
    private final boolean _predictionResistant;
    private final SecureRandom _sr = new SecureRandom();

    public TestRandomEntropySourceProvider(boolean z) {
        this._predictionResistant = z;
    }

    @Override // org.spongycastle.crypto.prng.EntropySourceProvider
    public EntropySource get(final int i) {
        return new EntropySource() {
            /* class org.spongycastle.util.test.TestRandomEntropySourceProvider.AnonymousClass1 */

            @Override // org.spongycastle.crypto.prng.EntropySource
            public boolean isPredictionResistant() {
                return TestRandomEntropySourceProvider.this._predictionResistant;
            }

            @Override // org.spongycastle.crypto.prng.EntropySource
            public byte[] getEntropy() {
                byte[] bArr = new byte[((i + 7) / 8)];
                TestRandomEntropySourceProvider.this._sr.nextBytes(bArr);
                return bArr;
            }

            @Override // org.spongycastle.crypto.prng.EntropySource
            public int entropySize() {
                return i;
            }
        };
    }
}
