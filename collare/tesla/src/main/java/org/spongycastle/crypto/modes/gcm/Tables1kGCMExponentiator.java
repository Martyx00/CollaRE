package org.spongycastle.crypto.modes.gcm;

import java.util.Vector;
import org.spongycastle.util.Arrays;

public class Tables1kGCMExponentiator implements GCMExponentiator {
    private Vector lookupPowX2;

    @Override // org.spongycastle.crypto.modes.gcm.GCMExponentiator
    public void init(byte[] bArr) {
        int[] asInts = GCMUtil.asInts(bArr);
        Vector vector = this.lookupPowX2;
        if (vector == null || !Arrays.areEqual(asInts, (int[]) vector.elementAt(0))) {
            this.lookupPowX2 = new Vector(8);
            this.lookupPowX2.addElement(asInts);
        }
    }

    @Override // org.spongycastle.crypto.modes.gcm.GCMExponentiator
    public void exponentiateX(long j, byte[] bArr) {
        int[] oneAsInts = GCMUtil.oneAsInts();
        int i = 0;
        while (j > 0) {
            if ((1 & j) != 0) {
                ensureAvailable(i);
                GCMUtil.multiply(oneAsInts, (int[]) this.lookupPowX2.elementAt(i));
            }
            i++;
            j >>>= 1;
        }
        GCMUtil.asBytes(oneAsInts, bArr);
    }

    private void ensureAvailable(int i) {
        int size = this.lookupPowX2.size();
        if (size <= i) {
            int[] iArr = (int[]) this.lookupPowX2.elementAt(size - 1);
            do {
                iArr = Arrays.clone(iArr);
                GCMUtil.multiply(iArr, iArr);
                this.lookupPowX2.addElement(iArr);
                size++;
            } while (size <= i);
        }
    }
}
