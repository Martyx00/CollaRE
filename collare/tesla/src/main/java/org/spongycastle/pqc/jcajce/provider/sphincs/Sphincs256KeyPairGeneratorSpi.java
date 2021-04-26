package org.spongycastle.pqc.jcajce.provider.sphincs;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.digests.SHA3Digest;
import org.spongycastle.crypto.digests.SHA512tDigest;
import org.spongycastle.pqc.crypto.sphincs.SPHINCS256KeyGenerationParameters;
import org.spongycastle.pqc.crypto.sphincs.SPHINCS256KeyPairGenerator;
import org.spongycastle.pqc.crypto.sphincs.SPHINCSPrivateKeyParameters;
import org.spongycastle.pqc.crypto.sphincs.SPHINCSPublicKeyParameters;
import org.spongycastle.pqc.jcajce.spec.SPHINCS256KeyGenParameterSpec;

public class Sphincs256KeyPairGeneratorSpi extends KeyPairGenerator {
    SPHINCS256KeyPairGenerator engine = new SPHINCS256KeyPairGenerator();
    boolean initialised = false;
    SPHINCS256KeyGenerationParameters param;
    SecureRandom random = new SecureRandom();
    ASN1ObjectIdentifier treeDigest = NISTObjectIdentifiers.id_sha512_256;

    public Sphincs256KeyPairGeneratorSpi() {
        super("SPHINCS256");
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i, SecureRandom secureRandom) {
        throw new IllegalArgumentException("use AlgorithmParameterSpec");
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        if (algorithmParameterSpec instanceof SPHINCS256KeyGenParameterSpec) {
            SPHINCS256KeyGenParameterSpec sPHINCS256KeyGenParameterSpec = (SPHINCS256KeyGenParameterSpec) algorithmParameterSpec;
            if (sPHINCS256KeyGenParameterSpec.getTreeDigest().equals(SPHINCS256KeyGenParameterSpec.SHA512_256)) {
                this.treeDigest = NISTObjectIdentifiers.id_sha512_256;
                this.param = new SPHINCS256KeyGenerationParameters(secureRandom, new SHA512tDigest(256));
            } else if (sPHINCS256KeyGenParameterSpec.getTreeDigest().equals(SPHINCS256KeyGenParameterSpec.SHA3_256)) {
                this.treeDigest = NISTObjectIdentifiers.id_sha3_256;
                this.param = new SPHINCS256KeyGenerationParameters(secureRandom, new SHA3Digest(256));
            }
            this.engine.init(this.param);
            this.initialised = true;
            return;
        }
        throw new InvalidAlgorithmParameterException("parameter object not a SPHINCS256KeyGenParameterSpec");
    }

    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            this.param = new SPHINCS256KeyGenerationParameters(this.random, new SHA512tDigest(256));
            this.engine.init(this.param);
            this.initialised = true;
        }
        AsymmetricCipherKeyPair generateKeyPair = this.engine.generateKeyPair();
        return new KeyPair(new BCSphincs256PublicKey(this.treeDigest, (SPHINCSPublicKeyParameters) generateKeyPair.getPublic()), new BCSphincs256PrivateKey(this.treeDigest, (SPHINCSPrivateKeyParameters) generateKeyPair.getPrivate()));
    }
}
