package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.params.KeyParameter;

public class OldHMac implements Mac {
    private static final int BLOCK_LENGTH = 64;
    private static final byte IPAD = 54;
    private static final byte OPAD = 92;
    private Digest digest;
    private int digestSize;
    private byte[] inputPad = new byte[64];
    private byte[] outputPad = new byte[64];

    public OldHMac(Digest digest2) {
        this.digest = digest2;
        this.digestSize = digest2.getDigestSize();
    }

    @Override // org.spongycastle.crypto.Mac
    public String getAlgorithmName() {
        return this.digest.getAlgorithmName() + "/HMAC";
    }

    public Digest getUnderlyingDigest() {
        return this.digest;
    }

    @Override // org.spongycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) {
        this.digest.reset();
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        if (key.length <= 64) {
            System.arraycopy(key, 0, this.inputPad, 0, key.length);
            int length = key.length;
            while (true) {
                byte[] bArr = this.inputPad;
                if (length >= bArr.length) {
                    break;
                }
                bArr[length] = 0;
                length++;
            }
        } else {
            this.digest.update(key, 0, key.length);
            this.digest.doFinal(this.inputPad, 0);
            int i = this.digestSize;
            while (true) {
                byte[] bArr2 = this.inputPad;
                if (i >= bArr2.length) {
                    break;
                }
                bArr2[i] = 0;
                i++;
            }
        }
        byte[] bArr3 = this.inputPad;
        this.outputPad = new byte[bArr3.length];
        System.arraycopy(bArr3, 0, this.outputPad, 0, bArr3.length);
        int i2 = 0;
        while (true) {
            byte[] bArr4 = this.inputPad;
            if (i2 >= bArr4.length) {
                break;
            }
            bArr4[i2] = (byte) (bArr4[i2] ^ IPAD);
            i2++;
        }
        int i3 = 0;
        while (true) {
            byte[] bArr5 = this.outputPad;
            if (i3 < bArr5.length) {
                bArr5[i3] = (byte) (bArr5[i3] ^ OPAD);
                i3++;
            } else {
                Digest digest2 = this.digest;
                byte[] bArr6 = this.inputPad;
                digest2.update(bArr6, 0, bArr6.length);
                return;
            }
        }
    }

    @Override // org.spongycastle.crypto.Mac
    public int getMacSize() {
        return this.digestSize;
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte b2) {
        this.digest.update(b2);
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte[] bArr, int i, int i2) {
        this.digest.update(bArr, i, i2);
    }

    @Override // org.spongycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) {
        byte[] bArr2 = new byte[this.digestSize];
        this.digest.doFinal(bArr2, 0);
        Digest digest2 = this.digest;
        byte[] bArr3 = this.outputPad;
        digest2.update(bArr3, 0, bArr3.length);
        this.digest.update(bArr2, 0, bArr2.length);
        int doFinal = this.digest.doFinal(bArr, i);
        reset();
        return doFinal;
    }

    @Override // org.spongycastle.crypto.Mac
    public void reset() {
        this.digest.reset();
        Digest digest2 = this.digest;
        byte[] bArr = this.inputPad;
        digest2.update(bArr, 0, bArr.length);
    }
}
