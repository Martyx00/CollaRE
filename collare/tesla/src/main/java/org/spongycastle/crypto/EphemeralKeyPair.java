package org.spongycastle.crypto;

public class EphemeralKeyPair {
    private AsymmetricCipherKeyPair keyPair;
    private KeyEncoder publicKeyEncoder;

    public EphemeralKeyPair(AsymmetricCipherKeyPair asymmetricCipherKeyPair, KeyEncoder keyEncoder) {
        this.keyPair = asymmetricCipherKeyPair;
        this.publicKeyEncoder = keyEncoder;
    }

    public AsymmetricCipherKeyPair getKeyPair() {
        return this.keyPair;
    }

    public byte[] getEncodedPublicKey() {
        return this.publicKeyEncoder.getEncoded(this.keyPair.getPublic());
    }
}
