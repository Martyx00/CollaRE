package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.tls.CipherSuite;

public class NoekeonEngine implements BlockCipher {
    private static final int genericSize = 16;
    private static final int[] nullVector = {0, 0, 0, 0};
    private static final int[] roundConstants = {128, 27, 54, 108, 216, CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384, 77, CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA, 47, 94, 188, 99, 198, CipherSuite.TLS_DH_DSS_WITH_SEED_CBC_SHA, 53, 106, 212};
    private boolean _forEncryption;
    private boolean _initialised = false;
    private int[] decryptKeys = new int[4];
    private int[] state = new int[4];
    private int[] subKeys = new int[4];

    private int rotl(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Noekeon";
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void reset() {
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this._forEncryption = z;
            this._initialised = true;
            setKey(((KeyParameter) cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to Noekeon init - " + cipherParameters.getClass().getName());
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (!this._initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        } else if (this._forEncryption) {
            return encryptBlock(bArr, i, bArr2, i2);
        } else {
            return decryptBlock(bArr, i, bArr2, i2);
        }
    }

    private void setKey(byte[] bArr) {
        this.subKeys[0] = bytesToIntBig(bArr, 0);
        this.subKeys[1] = bytesToIntBig(bArr, 4);
        this.subKeys[2] = bytesToIntBig(bArr, 8);
        this.subKeys[3] = bytesToIntBig(bArr, 12);
    }

    private int encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        this.state[0] = bytesToIntBig(bArr, i);
        this.state[1] = bytesToIntBig(bArr, i + 4);
        this.state[2] = bytesToIntBig(bArr, i + 8);
        this.state[3] = bytesToIntBig(bArr, i + 12);
        int i3 = 0;
        while (i3 < 16) {
            int[] iArr = this.state;
            iArr[0] = iArr[0] ^ roundConstants[i3];
            theta(iArr, this.subKeys);
            pi1(this.state);
            gamma(this.state);
            pi2(this.state);
            i3++;
        }
        int[] iArr2 = this.state;
        iArr2[0] = roundConstants[i3] ^ iArr2[0];
        theta(iArr2, this.subKeys);
        intToBytesBig(this.state[0], bArr2, i2);
        intToBytesBig(this.state[1], bArr2, i2 + 4);
        intToBytesBig(this.state[2], bArr2, i2 + 8);
        intToBytesBig(this.state[3], bArr2, i2 + 12);
        return 16;
    }

    private int decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        this.state[0] = bytesToIntBig(bArr, i);
        this.state[1] = bytesToIntBig(bArr, i + 4);
        this.state[2] = bytesToIntBig(bArr, i + 8);
        this.state[3] = bytesToIntBig(bArr, i + 12);
        int[] iArr = this.subKeys;
        System.arraycopy(iArr, 0, this.decryptKeys, 0, iArr.length);
        theta(this.decryptKeys, nullVector);
        int i3 = 16;
        while (i3 > 0) {
            theta(this.state, this.decryptKeys);
            int[] iArr2 = this.state;
            iArr2[0] = iArr2[0] ^ roundConstants[i3];
            pi1(iArr2);
            gamma(this.state);
            pi2(this.state);
            i3--;
        }
        theta(this.state, this.decryptKeys);
        int[] iArr3 = this.state;
        iArr3[0] = roundConstants[i3] ^ iArr3[0];
        intToBytesBig(iArr3[0], bArr2, i2);
        intToBytesBig(this.state[1], bArr2, i2 + 4);
        intToBytesBig(this.state[2], bArr2, i2 + 8);
        intToBytesBig(this.state[3], bArr2, i2 + 12);
        return 16;
    }

    private void gamma(int[] iArr) {
        iArr[1] = iArr[1] ^ ((~iArr[3]) & (~iArr[2]));
        iArr[0] = iArr[0] ^ (iArr[2] & iArr[1]);
        int i = iArr[3];
        iArr[3] = iArr[0];
        iArr[0] = i;
        iArr[2] = iArr[2] ^ ((iArr[0] ^ iArr[1]) ^ iArr[3]);
        iArr[1] = ((~iArr[3]) & (~iArr[2])) ^ iArr[1];
        iArr[0] = (iArr[1] & iArr[2]) ^ iArr[0];
    }

    private void theta(int[] iArr, int[] iArr2) {
        int i = iArr[0] ^ iArr[2];
        int rotl = i ^ (rotl(i, 8) ^ rotl(i, 24));
        iArr[1] = iArr[1] ^ rotl;
        iArr[3] = rotl ^ iArr[3];
        for (int i2 = 0; i2 < 4; i2++) {
            iArr[i2] = iArr[i2] ^ iArr2[i2];
        }
        int i3 = iArr[1] ^ iArr[3];
        int rotl2 = i3 ^ (rotl(i3, 8) ^ rotl(i3, 24));
        iArr[0] = iArr[0] ^ rotl2;
        iArr[2] = rotl2 ^ iArr[2];
    }

    private void pi1(int[] iArr) {
        iArr[1] = rotl(iArr[1], 1);
        iArr[2] = rotl(iArr[2], 5);
        iArr[3] = rotl(iArr[3], 2);
    }

    private void pi2(int[] iArr) {
        iArr[1] = rotl(iArr[1], 31);
        iArr[2] = rotl(iArr[2], 27);
        iArr[3] = rotl(iArr[3], 30);
    }

    private int bytesToIntBig(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = (bArr[i] << 24) | ((bArr[i2] & 255) << 16);
        return (bArr[i3 + 1] & 255) | i4 | ((bArr[i3] & 255) << 8);
    }

    private void intToBytesBig(int i, byte[] bArr, int i2) {
        int i3 = i2 + 1;
        bArr[i2] = (byte) (i >>> 24);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i >>> 16);
        bArr[i4] = (byte) (i >>> 8);
        bArr[i4 + 1] = (byte) i;
    }
}
