package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;

public class CMacWithIV extends CMac {
    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.crypto.macs.CMac
    public void validate(CipherParameters cipherParameters) {
    }

    public CMacWithIV(BlockCipher blockCipher) {
        super(blockCipher);
    }

    public CMacWithIV(BlockCipher blockCipher, int i) {
        super(blockCipher, i);
    }
}
