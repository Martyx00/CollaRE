package org.spongycastle.pqc.crypto.gmss.util;

import org.spongycastle.crypto.Digest;

public class GMSSRandom {
    private Digest messDigestTree;

    public GMSSRandom(Digest digest) {
        this.messDigestTree = digest;
    }

    public byte[] nextSeed(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        this.messDigestTree.update(bArr, 0, bArr.length);
        byte[] bArr3 = new byte[this.messDigestTree.getDigestSize()];
        this.messDigestTree.doFinal(bArr3, 0);
        addByteArrays(bArr, bArr3);
        addOne(bArr);
        return bArr3;
    }

    private void addByteArrays(byte[] bArr, byte[] bArr2) {
        byte b2 = 0;
        for (int i = 0; i < bArr.length; i++) {
            int i2 = (bArr[i] & 255) + (bArr2[i] & 255) + b2;
            bArr[i] = (byte) i2;
            b2 = (byte) (i2 >> 8);
        }
    }

    private void addOne(byte[] bArr) {
        byte b2 = 1;
        for (int i = 0; i < bArr.length; i++) {
            int i2 = (bArr[i] & 255) + b2;
            bArr[i] = (byte) i2;
            b2 = (byte) (i2 >> 8);
        }
    }
}
