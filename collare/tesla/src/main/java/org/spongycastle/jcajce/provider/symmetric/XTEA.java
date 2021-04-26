package org.spongycastle.jcajce.provider.symmetric;

import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.engines.XTEAEngine;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import org.spongycastle.jcajce.provider.util.AlgorithmProvider;

public final class XTEA {

    public static class AlgParams extends IvAlgorithmParameters {
        /* access modifiers changed from: protected */
        @Override // org.spongycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters
        public String engineToString() {
            return "XTEA IV";
        }
    }

    private XTEA() {
    }

    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new XTEAEngine());
        }
    }

    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            super("XTEA", 128, new CipherKeyGenerator());
        }
    }

    public static class Mappings extends AlgorithmProvider {
        private static final String PREFIX = XTEA.class.getName();

        @Override // org.spongycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("Cipher.XTEA", PREFIX + "$ECB");
            configurableProvider.addAlgorithm("KeyGenerator.XTEA", PREFIX + "$KeyGen");
            configurableProvider.addAlgorithm("AlgorithmParameters.XTEA", PREFIX + "$AlgParams");
        }
    }
}
