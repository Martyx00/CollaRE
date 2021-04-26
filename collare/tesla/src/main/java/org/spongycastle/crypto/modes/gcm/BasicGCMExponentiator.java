package org.spongycastle.crypto.modes.gcm;

import org.spongycastle.util.Arrays;

public class BasicGCMExponentiator implements GCMExponentiator {
    private int[] x;

    @Override // org.spongycastle.crypto.modes.gcm.GCMExponentiator
    public void init(byte[] bArr) {
        this.x = GCMUtil.asInts(bArr);
    }

    @Override // org.spongycastle.crypto.modes.gcm.GCMExponentiator
    public void exponentiateX(long j, byte[] bArr) {
        int[] oneAsInts = GCMUtil.oneAsInts();
        if (j > 0) {
            int[] clone = Arrays.clone(this.x);
            do {
                if ((1 & j) != 0) {
                    GCMUtil.multiply(oneAsInts, clone);
                }
                GCMUtil.multiply(clone, clone);
                j >>>= 1;
            } while (j > 0);
        }
        GCMUtil.asBytes(oneAsInts, bArr);
    }
}
