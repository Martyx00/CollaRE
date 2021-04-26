package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;
import org.spongycastle.util.Pack;

public class SM3Digest extends GeneralDigest {
    private static final int BLOCK_SIZE = 16;
    private static final int DIGEST_LENGTH = 32;
    private static final int[] T = new int[64];
    private int[] V;
    private int[] W;
    private int[] W1;
    private int[] inwords;
    private int xOff;

    private int FF0(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private int FF1(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    private int GG0(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private int GG1(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    private int P0(int i) {
        return (i ^ ((i << 9) | (i >>> 23))) ^ ((i << 17) | (i >>> 15));
    }

    private int P1(int i) {
        return (i ^ ((i << 15) | (i >>> 17))) ^ ((i << 23) | (i >>> 9));
    }

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SM3";
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }

    static {
        int i;
        int i2 = 0;
        while (true) {
            if (i2 >= 16) {
                break;
            }
            T[i2] = (2043430169 >>> (32 - i2)) | (2043430169 << i2);
            i2++;
        }
        for (i = 16; i < 64; i++) {
            int i3 = i % 32;
            T[i] = (2055708042 >>> (32 - i3)) | (2055708042 << i3);
        }
    }

    public SM3Digest() {
        this.V = new int[8];
        this.inwords = new int[16];
        this.W = new int[68];
        this.W1 = new int[64];
        reset();
    }

    public SM3Digest(SM3Digest sM3Digest) {
        super(sM3Digest);
        this.V = new int[8];
        this.inwords = new int[16];
        this.W = new int[68];
        this.W1 = new int[64];
        copyIn(sM3Digest);
    }

    private void copyIn(SM3Digest sM3Digest) {
        int[] iArr = sM3Digest.V;
        int[] iArr2 = this.V;
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
        int[] iArr3 = sM3Digest.inwords;
        int[] iArr4 = this.inwords;
        System.arraycopy(iArr3, 0, iArr4, 0, iArr4.length);
        this.xOff = sM3Digest.xOff;
    }

    @Override // org.spongycastle.util.Memoable
    public Memoable copy() {
        return new SM3Digest(this);
    }

    @Override // org.spongycastle.util.Memoable
    public void reset(Memoable memoable) {
        SM3Digest sM3Digest = (SM3Digest) memoable;
        super.copyIn((GeneralDigest) sM3Digest);
        copyIn(sM3Digest);
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest, org.spongycastle.crypto.Digest
    public void reset() {
        super.reset();
        int[] iArr = this.V;
        iArr[0] = 1937774191;
        iArr[1] = 1226093241;
        iArr[2] = 388252375;
        iArr[3] = -628488704;
        iArr[4] = -1452330820;
        iArr[5] = 372324522;
        iArr[6] = -477237683;
        iArr[7] = -1325724082;
        this.xOff = 0;
    }

    @Override // org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.intToBigEndian(this.V[0], bArr, i + 0);
        Pack.intToBigEndian(this.V[1], bArr, i + 4);
        Pack.intToBigEndian(this.V[2], bArr, i + 8);
        Pack.intToBigEndian(this.V[3], bArr, i + 12);
        Pack.intToBigEndian(this.V[4], bArr, i + 16);
        Pack.intToBigEndian(this.V[5], bArr, i + 20);
        Pack.intToBigEndian(this.V[6], bArr, i + 24);
        Pack.intToBigEndian(this.V[7], bArr, i + 28);
        reset();
        return 32;
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.crypto.digests.GeneralDigest
    public void processWord(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = (bArr[i3 + 1] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8);
        int[] iArr = this.inwords;
        int i5 = this.xOff;
        iArr[i5] = i4;
        this.xOff = i5 + 1;
        if (this.xOff >= 16) {
            processBlock();
        }
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.crypto.digests.GeneralDigest
    public void processLength(long j) {
        int i = this.xOff;
        if (i > 14) {
            this.inwords[i] = 0;
            this.xOff = i + 1;
            processBlock();
        }
        while (true) {
            int i2 = this.xOff;
            if (i2 < 14) {
                this.inwords[i2] = 0;
                this.xOff = i2 + 1;
            } else {
                int[] iArr = this.inwords;
                this.xOff = i2 + 1;
                iArr[i2] = (int) (j >>> 32);
                int i3 = this.xOff;
                this.xOff = i3 + 1;
                iArr[i3] = (int) j;
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.crypto.digests.GeneralDigest
    public void processBlock() {
        int i;
        int i2;
        int i3 = 0;
        while (true) {
            i = 16;
            if (i3 >= 16) {
                break;
            }
            this.W[i3] = this.inwords[i3];
            i3++;
        }
        for (int i4 = 16; i4 < 68; i4++) {
            int[] iArr = this.W;
            int i5 = iArr[i4 - 3];
            int i6 = (i5 >>> 17) | (i5 << 15);
            int i7 = iArr[i4 - 13];
            iArr[i4] = (P1(i6 ^ (iArr[i4 - 16] ^ iArr[i4 - 9])) ^ ((i7 >>> 25) | (i7 << 7))) ^ this.W[i4 - 6];
        }
        int i8 = 0;
        while (true) {
            i2 = 64;
            if (i8 >= 64) {
                break;
            }
            int[] iArr2 = this.W1;
            int[] iArr3 = this.W;
            iArr2[i8] = iArr3[i8 + 4] ^ iArr3[i8];
            i8++;
        }
        int[] iArr4 = this.V;
        int i9 = iArr4[0];
        int i10 = iArr4[1];
        int i11 = iArr4[2];
        int i12 = iArr4[3];
        int i13 = iArr4[4];
        int i14 = iArr4[5];
        int i15 = iArr4[6];
        int i16 = iArr4[7];
        int i17 = i15;
        int i18 = 0;
        int i19 = i9;
        int i20 = i10;
        int i21 = i11;
        int i22 = i12;
        int i23 = i13;
        int i24 = i14;
        while (i18 < i) {
            int i25 = (i19 << 12) | (i19 >>> 20);
            int i26 = i25 + i23 + T[i18];
            int i27 = (i26 << 7) | (i26 >>> 25);
            int FF0 = FF0(i19, i20, i21) + i22;
            i18++;
            i16 = i17;
            i17 = (i24 << 19) | (i24 >>> 13);
            i24 = i23;
            i23 = P0(GG0(i23, i24, i17) + i16 + i27 + this.W[i18]);
            i = 16;
            i21 = (i20 << 9) | (i20 >>> 23);
            i20 = i19;
            i19 = FF0 + (i27 ^ i25) + this.W1[i18];
            i22 = i21;
        }
        int i28 = i20;
        int i29 = i19;
        int i30 = i22;
        int i31 = i21;
        int i32 = i24;
        int i33 = i23;
        int i34 = 16;
        while (i34 < i2) {
            int i35 = (i29 << 12) | (i29 >>> 20);
            int i36 = i35 + i33 + T[i34];
            int i37 = (i36 << 7) | (i36 >>> 25);
            int FF1 = FF1(i29, i28, i31) + i30;
            int GG1 = GG1(i33, i32, i17) + i16 + i37 + this.W[i34];
            int i38 = (i28 >>> 23) | (i28 << 9);
            i34++;
            i16 = i17;
            i17 = (i32 << 19) | (i32 >>> 13);
            i2 = 64;
            i31 = i38;
            i28 = i29;
            i29 = FF1 + (i37 ^ i35) + this.W1[i34];
            i30 = i31;
            i33 = P0(GG1);
            i32 = i33;
        }
        int[] iArr5 = this.V;
        iArr5[0] = iArr5[0] ^ i29;
        iArr5[1] = i28 ^ iArr5[1];
        iArr5[2] = iArr5[2] ^ i31;
        iArr5[3] = iArr5[3] ^ i30;
        iArr5[4] = iArr5[4] ^ i33;
        iArr5[5] = iArr5[5] ^ i32;
        iArr5[6] = iArr5[6] ^ i17;
        iArr5[7] = iArr5[7] ^ i16;
        this.xOff = 0;
    }
}
