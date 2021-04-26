package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithSBox;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.math.ec.Tnaf;

public class GOST28147Mac implements Mac {
    private byte[] S = {9, 6, 3, 2, 8, 11, 1, 7, 10, 4, 14, 15, 12, 0, 13, 5, 3, 7, 14, 9, 8, 10, 15, 0, 5, 2, 6, 12, 11, 4, 13, 1, 14, 4, 6, 2, 11, 3, 13, 8, 12, 15, 5, 10, 0, 7, 1, 9, 14, 7, 10, 12, 13, 1, 3, 9, 0, 2, 11, 4, 15, 8, 5, 6, 11, 5, 1, 9, 8, 13, 15, 0, 14, 4, 2, 3, 12, 7, 10, 6, 3, 10, 13, 12, 1, 2, 0, 11, 7, 5, 9, 4, 8, 15, 14, 6, 1, 13, 2, 9, 7, 10, 6, 0, 8, 12, 4, 5, 15, 3, 11, 14, 11, 10, 15, 5, 0, 12, 14, 8, 6, 2, 3, 9, 1, 7, 13, 4};
    private int blockSize = 8;
    private byte[] buf;
    private int bufOff;
    private boolean firstStep = true;
    private byte[] mac;
    private int macSize = 4;
    private int[] workingKey = null;

    @Override // org.spongycastle.crypto.Mac
    public String getAlgorithmName() {
        return "GOST28147Mac";
    }

    public GOST28147Mac() {
        int i = this.blockSize;
        this.mac = new byte[i];
        this.buf = new byte[i];
        this.bufOff = 0;
    }

    private int[] generateWorkingKey(byte[] bArr) {
        if (bArr.length == 32) {
            int[] iArr = new int[8];
            for (int i = 0; i != 8; i++) {
                iArr[i] = bytesToint(bArr, i * 4);
            }
            return iArr;
        }
        throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
    }

    @Override // org.spongycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) {
        reset();
        this.buf = new byte[this.blockSize];
        if (cipherParameters instanceof ParametersWithSBox) {
            ParametersWithSBox parametersWithSBox = (ParametersWithSBox) cipherParameters;
            System.arraycopy(parametersWithSBox.getSBox(), 0, this.S, 0, parametersWithSBox.getSBox().length);
            if (parametersWithSBox.getParameters() != null) {
                this.workingKey = generateWorkingKey(((KeyParameter) parametersWithSBox.getParameters()).getKey());
            }
        } else if (cipherParameters instanceof KeyParameter) {
            this.workingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey());
        } else {
            throw new IllegalArgumentException("invalid parameter passed to GOST28147 init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.spongycastle.crypto.Mac
    public int getMacSize() {
        return this.macSize;
    }

    private int gost28147_mainStep(int i, int i2) {
        int i3 = i2 + i;
        byte[] bArr = this.S;
        int i4 = (bArr[((i3 >> 0) & 15) + 0] << 0) + (bArr[((i3 >> 4) & 15) + 16] << 4) + (bArr[((i3 >> 8) & 15) + 32] << 8) + (bArr[((i3 >> 12) & 15) + 48] << 12) + (bArr[((i3 >> 16) & 15) + 64] << Tnaf.POW_2_WIDTH) + (bArr[((i3 >> 20) & 15) + 80] << 20) + (bArr[((i3 >> 24) & 15) + 96] << 24) + (bArr[((i3 >> 28) & 15) + 112] << 28);
        return (i4 << 11) | (i4 >>> 21);
    }

    private void gost28147MacFunc(int[] iArr, byte[] bArr, int i, byte[] bArr2, int i2) {
        int bytesToint = bytesToint(bArr, i);
        int bytesToint2 = bytesToint(bArr, i + 4);
        int i3 = 0;
        while (i3 < 2) {
            int i4 = bytesToint2;
            int i5 = bytesToint;
            int i6 = 0;
            while (i6 < 8) {
                int gost28147_mainStep = i4 ^ gost28147_mainStep(i5, iArr[i6]);
                i6++;
                i4 = i5;
                i5 = gost28147_mainStep;
            }
            i3++;
            bytesToint = i5;
            bytesToint2 = i4;
        }
        intTobytes(bytesToint, bArr2, i2);
        intTobytes(bytesToint2, bArr2, i2 + 4);
    }

    private int bytesToint(byte[] bArr, int i) {
        return ((bArr[i + 3] << 24) & -16777216) + ((bArr[i + 2] << Tnaf.POW_2_WIDTH) & 16711680) + ((bArr[i + 1] << 8) & CipherSuite.DRAFT_TLS_DHE_RSA_WITH_AES_128_OCB) + (bArr[i] & 255);
    }

    private void intTobytes(int i, byte[] bArr, int i2) {
        bArr[i2 + 3] = (byte) (i >>> 24);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2] = (byte) i;
    }

    private byte[] CM5func(byte[] bArr, int i, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length - i)];
        System.arraycopy(bArr, i, bArr3, 0, bArr2.length);
        for (int i2 = 0; i2 != bArr2.length; i2++) {
            bArr3[i2] = (byte) (bArr3[i2] ^ bArr2[i2]);
        }
        return bArr3;
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte b2) {
        byte[] bArr;
        int i = this.bufOff;
        byte[] bArr2 = this.buf;
        if (i == bArr2.length) {
            byte[] bArr3 = new byte[bArr2.length];
            System.arraycopy(bArr2, 0, bArr3, 0, this.mac.length);
            if (this.firstStep) {
                this.firstStep = false;
                bArr = bArr3;
            } else {
                bArr = CM5func(this.buf, 0, this.mac);
            }
            gost28147MacFunc(this.workingKey, bArr, 0, this.mac, 0);
            this.bufOff = 0;
        }
        byte[] bArr4 = this.buf;
        int i2 = this.bufOff;
        this.bufOff = i2 + 1;
        bArr4[i2] = b2;
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte[] bArr, int i, int i2) {
        byte[] bArr2;
        if (i2 >= 0) {
            int i3 = this.blockSize;
            int i4 = this.bufOff;
            int i5 = i3 - i4;
            if (i2 > i5) {
                System.arraycopy(bArr, i, this.buf, i4, i5);
                byte[] bArr3 = this.buf;
                byte[] bArr4 = new byte[bArr3.length];
                System.arraycopy(bArr3, 0, bArr4, 0, this.mac.length);
                if (this.firstStep) {
                    this.firstStep = false;
                    bArr2 = bArr4;
                } else {
                    bArr2 = CM5func(this.buf, 0, this.mac);
                }
                gost28147MacFunc(this.workingKey, bArr2, 0, this.mac, 0);
                this.bufOff = 0;
                while (true) {
                    i2 -= i5;
                    i += i5;
                    if (i2 <= this.blockSize) {
                        break;
                    }
                    gost28147MacFunc(this.workingKey, CM5func(bArr, i, this.mac), 0, this.mac, 0);
                    i5 = this.blockSize;
                }
            }
            System.arraycopy(bArr, i, this.buf, this.bufOff, i2);
            this.bufOff += i2;
            return;
        }
        throw new IllegalArgumentException("Can't have a negative input length!");
    }

    @Override // org.spongycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) {
        byte[] bArr2;
        while (true) {
            int i2 = this.bufOff;
            if (i2 >= this.blockSize) {
                break;
            }
            this.buf[i2] = 0;
            this.bufOff = i2 + 1;
        }
        byte[] bArr3 = this.buf;
        byte[] bArr4 = new byte[bArr3.length];
        System.arraycopy(bArr3, 0, bArr4, 0, this.mac.length);
        if (this.firstStep) {
            this.firstStep = false;
            bArr2 = bArr4;
        } else {
            bArr2 = CM5func(this.buf, 0, this.mac);
        }
        gost28147MacFunc(this.workingKey, bArr2, 0, this.mac, 0);
        byte[] bArr5 = this.mac;
        int i3 = this.macSize;
        System.arraycopy(bArr5, (bArr5.length / 2) - i3, bArr, i, i3);
        reset();
        return this.macSize;
    }

    @Override // org.spongycastle.crypto.Mac
    public void reset() {
        int i = 0;
        while (true) {
            byte[] bArr = this.buf;
            if (i < bArr.length) {
                bArr[i] = 0;
                i++;
            } else {
                this.bufOff = 0;
                this.firstStep = true;
                return;
            }
        }
    }
}
