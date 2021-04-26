package org.spongycastle.crypto.digests;

import org.spongycastle.crypto.Xof;

public class SHAKEDigest extends KeccakDigest implements Xof {
    private static int checkBitLength(int i) {
        if (i == 128 || i == 256) {
            return i;
        }
        throw new IllegalArgumentException("'bitLength' " + i + " not supported for SHAKE");
    }

    public SHAKEDigest() {
        this(128);
    }

    public SHAKEDigest(int i) {
        super(checkBitLength(i));
    }

    public SHAKEDigest(SHAKEDigest sHAKEDigest) {
        super(sHAKEDigest);
    }

    @Override // org.spongycastle.crypto.digests.KeccakDigest, org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SHAKE" + this.fixedOutputLength;
    }

    @Override // org.spongycastle.crypto.digests.KeccakDigest, org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        return doFinal(bArr, i, getDigestSize());
    }

    @Override // org.spongycastle.crypto.Xof
    public int doFinal(byte[] bArr, int i, int i2) {
        int doOutput = doOutput(bArr, i, i2);
        reset();
        return doOutput;
    }

    @Override // org.spongycastle.crypto.Xof
    public int doOutput(byte[] bArr, int i, int i2) {
        if (!this.squeezing) {
            absorb(new byte[]{15}, 0, 4);
        }
        squeeze(bArr, i, ((long) i2) * 8);
        return i2;
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.crypto.digests.KeccakDigest
    public int doFinal(byte[] bArr, int i, byte b2, int i2) {
        return doFinal(bArr, i, getDigestSize(), b2, i2);
    }

    /* access modifiers changed from: protected */
    public int doFinal(byte[] bArr, int i, int i2, byte b2, int i3) {
        if (i3 < 0 || i3 > 7) {
            throw new IllegalArgumentException("'partialBits' must be in the range [0,7]");
        }
        int i4 = (b2 & ((1 << i3) - 1)) | (15 << i3);
        int i5 = i3 + 4;
        if (i5 >= 8) {
            this.oneByte[0] = (byte) i4;
            absorb(this.oneByte, 0, 8);
            i5 -= 8;
            i4 >>>= 8;
        }
        if (i5 > 0) {
            this.oneByte[0] = (byte) i4;
            absorb(this.oneByte, 0, (long) i5);
        }
        squeeze(bArr, i, ((long) i2) * 8);
        reset();
        return i2;
    }
}
