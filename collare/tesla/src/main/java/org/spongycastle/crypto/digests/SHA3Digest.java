package org.spongycastle.crypto.digests;

public class SHA3Digest extends KeccakDigest {
    private static int checkBitLength(int i) {
        if (i == 224 || i == 256 || i == 384 || i == 512) {
            return i;
        }
        throw new IllegalArgumentException("'bitLength' " + i + " not supported for SHA-3");
    }

    public SHA3Digest() {
        this(256);
    }

    public SHA3Digest(int i) {
        super(checkBitLength(i));
    }

    public SHA3Digest(SHA3Digest sHA3Digest) {
        super(sHA3Digest);
    }

    @Override // org.spongycastle.crypto.digests.KeccakDigest, org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SHA3-" + this.fixedOutputLength;
    }

    @Override // org.spongycastle.crypto.digests.KeccakDigest, org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        absorb(new byte[]{2}, 0, 2);
        return super.doFinal(bArr, i);
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.crypto.digests.KeccakDigest
    public int doFinal(byte[] bArr, int i, byte b2, int i2) {
        if (i2 < 0 || i2 > 7) {
            throw new IllegalArgumentException("'partialBits' must be in the range [0,7]");
        }
        int i3 = (b2 & ((1 << i2) - 1)) | (2 << i2);
        int i4 = i2 + 2;
        if (i4 >= 8) {
            this.oneByte[0] = (byte) i3;
            absorb(this.oneByte, 0, 8);
            i4 -= 8;
            i3 >>>= 8;
        }
        return super.doFinal(bArr, i, (byte) i3, i4);
    }
}
