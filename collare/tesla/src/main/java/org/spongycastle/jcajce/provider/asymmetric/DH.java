package org.spongycastle.jcajce.provider.asymmetric;

import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;
import org.spongycastle.jcajce.provider.asymmetric.dh.KeyFactorySpi;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;

public class DH {
    private static final String PREFIX = "org.spongycastle.jcajce.provider.asymmetric.dh.";

    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.spongycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyPairGenerator.DH", "org.spongycastle.jcajce.provider.asymmetric.dh.KeyPairGeneratorSpi");
            configurableProvider.addAlgorithm("Alg.Alias.KeyPairGenerator.DIFFIEHELLMAN", "DH");
            configurableProvider.addAlgorithm("KeyAgreement.DH", "org.spongycastle.jcajce.provider.asymmetric.dh.KeyAgreementSpi");
            configurableProvider.addAlgorithm("Alg.Alias.KeyAgreement.DIFFIEHELLMAN", "DH");
            configurableProvider.addAlgorithm("KeyAgreement", PKCSObjectIdentifiers.id_alg_ESDH, "org.spongycastle.jcajce.provider.asymmetric.dh.KeyAgreementSpi$DHwithRFC2631KDF");
            configurableProvider.addAlgorithm("KeyAgreement", PKCSObjectIdentifiers.id_alg_SSDH, "org.spongycastle.jcajce.provider.asymmetric.dh.KeyAgreementSpi$DHwithRFC2631KDF");
            configurableProvider.addAlgorithm("KeyFactory.DH", "org.spongycastle.jcajce.provider.asymmetric.dh.KeyFactorySpi");
            configurableProvider.addAlgorithm("Alg.Alias.KeyFactory.DIFFIEHELLMAN", "DH");
            configurableProvider.addAlgorithm("AlgorithmParameters.DH", "org.spongycastle.jcajce.provider.asymmetric.dh.AlgorithmParametersSpi");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters.DIFFIEHELLMAN", "DH");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator.DIFFIEHELLMAN", "DH");
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator.DH", "org.spongycastle.jcajce.provider.asymmetric.dh.AlgorithmParameterGeneratorSpi");
            configurableProvider.addAlgorithm("Cipher.IES", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$IES");
            configurableProvider.addAlgorithm("Cipher.IESwithAES-CBC", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$IESwithAESCBC");
            configurableProvider.addAlgorithm("Cipher.IESWITHAES-CBC", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$IESwithAESCBC");
            configurableProvider.addAlgorithm("Cipher.IESWITHDESEDE-CBC", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$IESwithDESedeCBC");
            configurableProvider.addAlgorithm("Cipher.DHIES", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$IES");
            configurableProvider.addAlgorithm("Cipher.DHIESwithAES-CBC", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$IESwithAESCBC");
            configurableProvider.addAlgorithm("Cipher.DHIESWITHAES-CBC", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$IESwithAESCBC");
            configurableProvider.addAlgorithm("Cipher.DHIESWITHDESEDE-CBC", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$IESwithDESedeCBC");
            registerOid(configurableProvider, PKCSObjectIdentifiers.dhKeyAgreement, "DH", new KeyFactorySpi());
            registerOid(configurableProvider, X9ObjectIdentifiers.dhpublicnumber, "DH", new KeyFactorySpi());
        }
    }
}
