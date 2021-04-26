package org.spongycastle.jcajce.provider.asymmetric;

import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;

public class IES {
    private static final String PREFIX = "org.spongycastle.jcajce.provider.asymmetric.ies.";

    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.spongycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("AlgorithmParameters.IES", "org.spongycastle.jcajce.provider.asymmetric.ies.AlgorithmParametersSpi");
            configurableProvider.addAlgorithm("AlgorithmParameters.ECIES", "org.spongycastle.jcajce.provider.asymmetric.ies.AlgorithmParametersSpi");
        }
    }
}
