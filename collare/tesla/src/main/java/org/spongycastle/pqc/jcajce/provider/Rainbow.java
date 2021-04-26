package org.spongycastle.pqc.jcajce.provider;

import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.spongycastle.pqc.asn1.PQCObjectIdentifiers;
import org.spongycastle.pqc.jcajce.provider.rainbow.RainbowKeyFactorySpi;

public class Rainbow {
    private static final String PREFIX = "org.spongycastle.pqc.jcajce.provider.rainbow.";

    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.spongycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.Rainbow", "org.spongycastle.pqc.jcajce.provider.rainbow.RainbowKeyFactorySpi");
            configurableProvider.addAlgorithm("KeyPairGenerator.Rainbow", "org.spongycastle.pqc.jcajce.provider.rainbow.RainbowKeyPairGeneratorSpi");
            addSignatureAlgorithm(configurableProvider, "SHA224", "Rainbow", "org.spongycastle.pqc.jcajce.provider.rainbow.SignatureSpi$withSha224", PQCObjectIdentifiers.rainbowWithSha224);
            addSignatureAlgorithm(configurableProvider, "SHA256", "Rainbow", "org.spongycastle.pqc.jcajce.provider.rainbow.SignatureSpi$withSha256", PQCObjectIdentifiers.rainbowWithSha256);
            addSignatureAlgorithm(configurableProvider, "SHA384", "Rainbow", "org.spongycastle.pqc.jcajce.provider.rainbow.SignatureSpi$withSha384", PQCObjectIdentifiers.rainbowWithSha384);
            addSignatureAlgorithm(configurableProvider, "SHA512", "Rainbow", "org.spongycastle.pqc.jcajce.provider.rainbow.SignatureSpi$withSha512", PQCObjectIdentifiers.rainbowWithSha512);
            registerOid(configurableProvider, PQCObjectIdentifiers.rainbow, "Rainbow", new RainbowKeyFactorySpi());
            registerOidAlgorithmParameters(configurableProvider, PQCObjectIdentifiers.rainbow, "Rainbow");
        }
    }
}
