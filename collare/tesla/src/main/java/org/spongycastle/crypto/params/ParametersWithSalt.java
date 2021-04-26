package org.spongycastle.crypto.params;

import org.spongycastle.crypto.CipherParameters;

public class ParametersWithSalt implements CipherParameters {
    private CipherParameters parameters;
    private byte[] salt;

    public ParametersWithSalt(CipherParameters cipherParameters, byte[] bArr) {
        this(cipherParameters, bArr, 0, bArr.length);
    }

    public ParametersWithSalt(CipherParameters cipherParameters, byte[] bArr, int i, int i2) {
        this.salt = new byte[i2];
        this.parameters = cipherParameters;
        System.arraycopy(bArr, i, this.salt, 0, i2);
    }

    public byte[] getSalt() {
        return this.salt;
    }

    public CipherParameters getParameters() {
        return this.parameters;
    }
}
