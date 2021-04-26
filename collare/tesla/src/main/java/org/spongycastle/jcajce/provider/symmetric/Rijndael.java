package org.spongycastle.jcajce.provider.symmetric;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.engines.RijndaelEngine;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BlockCipherProvider;
import org.spongycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import org.spongycastle.jcajce.provider.util.AlgorithmProvider;

public final class Rijndael {

    public static class AlgParams extends IvAlgorithmParameters {
        /* access modifiers changed from: protected */
        @Override // org.spongycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters
        public String engineToString() {
            return "Rijndael IV";
        }
    }

    private Rijndael() {
    }

    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new BlockCipherProvider() {
                /* class org.spongycastle.jcajce.provider.symmetric.Rijndael.ECB.AnonymousClass1 */

                @Override // org.spongycastle.jcajce.provider.symmetric.util.BlockCipherProvider
                public BlockCipher get() {
                    return new RijndaelEngine();
                }
            });
        }
    }

    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            super("Rijndael", 192, new CipherKeyGenerator());
        }
    }

    public static class Mappings extends AlgorithmProvider {
        private static final String PREFIX = Rijndael.class.getName();

        @Override // org.spongycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("Cipher.RIJNDAEL", PREFIX + "$ECB");
            configurableProvider.addAlgorithm("KeyGenerator.RIJNDAEL", PREFIX + "$KeyGen");
            configurableProvider.addAlgorithm("AlgorithmParameters.RIJNDAEL", PREFIX + "$AlgParams");
        }
    }
}
