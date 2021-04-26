package org.spongycastle.jce.spec;

import java.security.PublicKey;
import java.security.spec.KeySpec;
import org.spongycastle.jce.interfaces.MQVPublicKey;

public class MQVPublicKeySpec implements KeySpec, MQVPublicKey {
    private PublicKey ephemeralKey;
    private PublicKey staticKey;

    public String getAlgorithm() {
        return "ECMQV";
    }

    public byte[] getEncoded() {
        return null;
    }

    public String getFormat() {
        return null;
    }

    public MQVPublicKeySpec(PublicKey publicKey, PublicKey publicKey2) {
        this.staticKey = publicKey;
        this.ephemeralKey = publicKey2;
    }

    @Override // org.spongycastle.jce.interfaces.MQVPublicKey
    public PublicKey getStaticKey() {
        return this.staticKey;
    }

    @Override // org.spongycastle.jce.interfaces.MQVPublicKey
    public PublicKey getEphemeralKey() {
        return this.ephemeralKey;
    }
}
