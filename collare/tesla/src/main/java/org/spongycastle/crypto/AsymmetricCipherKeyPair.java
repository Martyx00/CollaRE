package org.spongycastle.crypto;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;

public class AsymmetricCipherKeyPair {
    private AsymmetricKeyParameter privateParam;
    private AsymmetricKeyParameter publicParam;

    public AsymmetricCipherKeyPair(AsymmetricKeyParameter asymmetricKeyParameter, AsymmetricKeyParameter asymmetricKeyParameter2) {
        this.publicParam = asymmetricKeyParameter;
        this.privateParam = asymmetricKeyParameter2;
    }

    public AsymmetricCipherKeyPair(CipherParameters cipherParameters, CipherParameters cipherParameters2) {
        this.publicParam = (AsymmetricKeyParameter) cipherParameters;
        this.privateParam = (AsymmetricKeyParameter) cipherParameters2;
    }

    public AsymmetricKeyParameter getPublic() {
        return this.publicParam;
    }

    public AsymmetricKeyParameter getPrivate() {
        return this.privateParam;
    }
}
