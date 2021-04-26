package org.spongycastle.pqc.crypto.sphincs;

import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.crypto.sphincs.Tree;

public class SPHINCS256KeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private SecureRandom random;
    private Digest treeDigest;

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.random = keyGenerationParameters.getRandom();
        this.treeDigest = ((SPHINCS256KeyGenerationParameters) keyGenerationParameters).getTreeDigest();
    }

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        Tree.leafaddr leafaddr = new Tree.leafaddr();
        byte[] bArr = new byte[1088];
        this.random.nextBytes(bArr);
        byte[] bArr2 = new byte[1056];
        System.arraycopy(bArr, 32, bArr2, 0, 1024);
        leafaddr.level = 11;
        leafaddr.subtree = 0;
        leafaddr.subleaf = 0;
        Tree.treehash(new HashFunctions(this.treeDigest), bArr2, 1024, 5, bArr, leafaddr, bArr2, 0);
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new SPHINCSPublicKeyParameters(bArr2), (AsymmetricKeyParameter) new SPHINCSPrivateKeyParameters(bArr));
    }
}
