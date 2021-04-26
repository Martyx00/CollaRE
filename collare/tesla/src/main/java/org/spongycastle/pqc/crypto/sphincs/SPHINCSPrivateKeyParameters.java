package org.spongycastle.pqc.crypto.sphincs;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.util.Arrays;

public class SPHINCSPrivateKeyParameters extends AsymmetricKeyParameter {
    private final byte[] keyData;

    public SPHINCSPrivateKeyParameters(byte[] bArr) {
        super(true);
        this.keyData = Arrays.clone(bArr);
    }

    public byte[] getKeyData() {
        return Arrays.clone(this.keyData);
    }
}
