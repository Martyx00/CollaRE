package org.spongycastle.jcajce.provider.asymmetric;

import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.jcajce.provider.asymmetric.dsa.DSAUtil;
import org.spongycastle.jcajce.provider.asymmetric.dsa.KeyFactorySpi;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.spongycastle.pqc.jcajce.spec.SPHINCS256KeyGenParameterSpec;

public class DSA {
    private static final String PREFIX = "org.spongycastle.jcajce.provider.asymmetric.dsa.";

    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.spongycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("AlgorithmParameters.DSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.AlgorithmParametersSpi");
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator.DSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.AlgorithmParameterGeneratorSpi");
            configurableProvider.addAlgorithm("KeyPairGenerator.DSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.KeyPairGeneratorSpi");
            configurableProvider.addAlgorithm("KeyFactory.DSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.KeyFactorySpi");
            configurableProvider.addAlgorithm("Signature.DSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$stdDSA");
            configurableProvider.addAlgorithm("Signature.NONEWITHDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$noneDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.RAWDSA", "NONEWITHDSA");
            configurableProvider.addAlgorithm("Signature.DETDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$detDSA");
            configurableProvider.addAlgorithm("Signature.SHA1WITHDETDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$detDSA");
            configurableProvider.addAlgorithm("Signature.SHA224WITHDETDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$detDSA224");
            configurableProvider.addAlgorithm("Signature.SHA256WITHDETDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$detDSA256");
            configurableProvider.addAlgorithm("Signature.SHA384WITHDETDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$detDSA384");
            configurableProvider.addAlgorithm("Signature.SHA512WITHDETDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$detDSA512");
            configurableProvider.addAlgorithm("Signature.DDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$detDSA");
            configurableProvider.addAlgorithm("Signature.SHA1WITHDDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$detDSA");
            configurableProvider.addAlgorithm("Signature.SHA224WITHDDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$detDSA224");
            configurableProvider.addAlgorithm("Signature.SHA256WITHDDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$detDSA256");
            configurableProvider.addAlgorithm("Signature.SHA384WITHDDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$detDSA384");
            configurableProvider.addAlgorithm("Signature.SHA512WITHDDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$detDSA512");
            configurableProvider.addAlgorithm("Signature.SHA3-224WITHDDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$detDSASha3_224");
            configurableProvider.addAlgorithm("Signature.SHA3-256WITHDDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$detDSASha3_256");
            configurableProvider.addAlgorithm("Signature.SHA3-384WITHDDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$detDSASha3_384");
            configurableProvider.addAlgorithm("Signature.SHA3-512WITHDDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$detDSASha3_512");
            addSignatureAlgorithm(configurableProvider, "SHA224", "DSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$dsa224", NISTObjectIdentifiers.dsa_with_sha224);
            addSignatureAlgorithm(configurableProvider, "SHA256", "DSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$dsa256", NISTObjectIdentifiers.dsa_with_sha256);
            addSignatureAlgorithm(configurableProvider, "SHA384", "DSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$dsa384", NISTObjectIdentifiers.dsa_with_sha384);
            addSignatureAlgorithm(configurableProvider, "SHA512", "DSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$dsa512", NISTObjectIdentifiers.dsa_with_sha512);
            addSignatureAlgorithm(configurableProvider, "SHA3-224", "DSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$dsaSha3_224", NISTObjectIdentifiers.id_dsa_with_sha3_224);
            addSignatureAlgorithm(configurableProvider, SPHINCS256KeyGenParameterSpec.SHA3_256, "DSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$dsaSha3_256", NISTObjectIdentifiers.id_dsa_with_sha3_256);
            addSignatureAlgorithm(configurableProvider, "SHA3-384", "DSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$dsaSha3_384", NISTObjectIdentifiers.id_dsa_with_sha3_384);
            addSignatureAlgorithm(configurableProvider, "SHA3-512", "DSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$dsaSha3_512", NISTObjectIdentifiers.id_dsa_with_sha3_512);
            configurableProvider.addAlgorithm("Alg.Alias.Signature.SHA/DSA", "DSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.SHA1withDSA", "DSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.SHA1WITHDSA", "DSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.1.3.14.3.2.26with1.2.840.10040.4.1", "DSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.1.3.14.3.2.26with1.2.840.10040.4.3", "DSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.DSAwithSHA1", "DSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.DSAWITHSHA1", "DSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.SHA1WithDSA", "DSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.DSAWithSHA1", "DSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.1.2.840.10040.4.3", "DSA");
            KeyFactorySpi keyFactorySpi = new KeyFactorySpi();
            for (int i = 0; i != DSAUtil.dsaOids.length; i++) {
                configurableProvider.addAlgorithm("Alg.Alias.Signature." + DSAUtil.dsaOids[i], "DSA");
                registerOid(configurableProvider, DSAUtil.dsaOids[i], "DSA", keyFactorySpi);
                registerOidAlgorithmParameters(configurableProvider, DSAUtil.dsaOids[i], "DSA");
            }
        }
    }
}
