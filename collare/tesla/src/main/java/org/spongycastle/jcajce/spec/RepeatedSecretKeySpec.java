package org.spongycastle.jcajce.spec;

import javax.crypto.SecretKey;

public class RepeatedSecretKeySpec implements SecretKey {
    private String algorithm;

    public byte[] getEncoded() {
        return null;
    }

    public String getFormat() {
        return null;
    }

    public RepeatedSecretKeySpec(String str) {
        this.algorithm = str;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }
}
