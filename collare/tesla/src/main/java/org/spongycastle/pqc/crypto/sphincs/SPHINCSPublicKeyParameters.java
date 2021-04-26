package org.spongycastle.pqc.crypto.sphincs;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.util.Arrays;

public class SPHINCSPublicKeyParameters extends AsymmetricKeyParameter {
    private final byte[] keyData;

    public SPHINCSPublicKeyParameters(byte[] bArr) {
        super(false);
        this.keyData = Arrays.clone(bArr);
    }

    public byte[] getKeyData() {
        return Arrays.clone(this.keyData);
    }
}
