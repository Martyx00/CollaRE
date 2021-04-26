package org.spongycastle.pqc.crypto.newhope;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.util.Arrays;

public class NHPrivateKeyParameters extends AsymmetricKeyParameter {
    final short[] secData;

    public NHPrivateKeyParameters(short[] sArr) {
        super(true);
        this.secData = Arrays.clone(sArr);
    }

    public short[] getSecData() {
        return Arrays.clone(this.secData);
    }
}
