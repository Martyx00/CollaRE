package org.spongycastle.crypto.modes.gcm;

import java.lang.reflect.Array;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;

public class Tables8kGCMMultiplier implements GCMMultiplier {
    private byte[] H;
    private int[][][] M;

    @Override // org.spongycastle.crypto.modes.gcm.GCMMultiplier
    public void init(byte[] bArr) {
        if (this.M == null) {
            this.M = (int[][][]) Array.newInstance(int.class, 32, 16, 4);
        } else if (Arrays.areEqual(this.H, bArr)) {
            return;
        }
        this.H = Arrays.clone(bArr);
        GCMUtil.asInts(bArr, this.M[1][8]);
        for (int i = 4; i >= 1; i >>= 1) {
            int[][][] iArr = this.M;
            GCMUtil.multiplyP(iArr[1][i + i], iArr[1][i]);
        }
        int[][][] iArr2 = this.M;
        int i2 = 0;
        GCMUtil.multiplyP(iArr2[1][1], iArr2[0][8]);
        for (int i3 = 4; i3 >= 1; i3 >>= 1) {
            int[][][] iArr3 = this.M;
            GCMUtil.multiplyP(iArr3[0][i3 + i3], iArr3[0][i3]);
        }
        while (true) {
            for (int i4 = 2; i4 < 16; i4 += i4) {
                for (int i5 = 1; i5 < i4; i5++) {
                    int[][][] iArr4 = this.M;
                    GCMUtil.xor(iArr4[i2][i4], iArr4[i2][i5], iArr4[i2][i4 + i5]);
                }
            }
            i2++;
            if (i2 == 32) {
                return;
            }
            if (i2 > 1) {
                for (int i6 = 8; i6 > 0; i6 >>= 1) {
                    int[][][] iArr5 = this.M;
                    GCMUtil.multiplyP8(iArr5[i2 - 2][i6], iArr5[i2][i6]);
                }
            }
        }
    }

    @Override // org.spongycastle.crypto.modes.gcm.GCMMultiplier
    public void multiplyH(byte[] bArr) {
        int[] iArr = new int[4];
        for (int i = 15; i >= 0; i--) {
            int[][][] iArr2 = this.M;
            int i2 = i + i;
            int[] iArr3 = iArr2[i2][bArr[i] & 15];
            iArr[0] = iArr[0] ^ iArr3[0];
            iArr[1] = iArr[1] ^ iArr3[1];
            iArr[2] = iArr[2] ^ iArr3[2];
            iArr[3] = iArr3[3] ^ iArr[3];
            int[] iArr4 = iArr2[i2 + 1][(bArr[i] & 240) >>> 4];
            iArr[0] = iArr[0] ^ iArr4[0];
            iArr[1] = iArr[1] ^ iArr4[1];
            iArr[2] = iArr[2] ^ iArr4[2];
            iArr[3] = iArr[3] ^ iArr4[3];
        }
        Pack.intToBigEndian(iArr, bArr, 0);
    }
}
