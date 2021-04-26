package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;

public class OpenPGPCFBBlockCipher implements BlockCipher {
    private byte[] FR;
    private byte[] FRE;
    private byte[] IV;
    private int blockSize;
    private BlockCipher cipher;
    private int count;
    private boolean forEncryption;

    public OpenPGPCFBBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
        this.blockSize = blockCipher.getBlockSize();
        int i = this.blockSize;
        this.IV = new byte[i];
        this.FR = new byte[i];
        this.FRE = new byte[i];
    }

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/OpenPGPCFB";
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        return this.forEncryption ? encryptBlock(bArr, i, bArr2, i2) : decryptBlock(bArr, i, bArr2, i2);
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void reset() {
        this.count = 0;
        byte[] bArr = this.IV;
        byte[] bArr2 = this.FR;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.cipher.reset();
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        this.forEncryption = z;
        reset();
        this.cipher.init(true, cipherParameters);
    }

    private byte encryptByte(byte b2, int i) {
        return (byte) (b2 ^ this.FRE[i]);
    }

    private int encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3;
        int i4;
        int i5 = this.blockSize;
        if (i + i5 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + i5 <= bArr2.length) {
            int i6 = this.count;
            int i7 = 2;
            int i8 = 0;
            if (i6 > i5) {
                byte[] bArr3 = this.FR;
                int i9 = i5 - 2;
                byte encryptByte = encryptByte(bArr[i], i5 - 2);
                bArr2[i2] = encryptByte;
                bArr3[i9] = encryptByte;
                byte[] bArr4 = this.FR;
                int i10 = this.blockSize;
                int i11 = i10 - 1;
                byte encryptByte2 = encryptByte(bArr[i + 1], i10 - 1);
                bArr2[i2 + 1] = encryptByte2;
                bArr4[i11] = encryptByte2;
                this.cipher.processBlock(this.FR, 0, this.FRE, 0);
                while (i7 < this.blockSize) {
                    byte[] bArr5 = this.FR;
                    int i12 = i7 - 2;
                    byte encryptByte3 = encryptByte(bArr[i + i7], i12);
                    bArr2[i2 + i7] = encryptByte3;
                    bArr5[i12] = encryptByte3;
                    i7++;
                }
            } else if (i6 == 0) {
                this.cipher.processBlock(this.FR, 0, this.FRE, 0);
                while (true) {
                    i4 = this.blockSize;
                    if (i8 >= i4) {
                        break;
                    }
                    byte[] bArr6 = this.FR;
                    byte encryptByte4 = encryptByte(bArr[i + i8], i8);
                    bArr2[i2 + i8] = encryptByte4;
                    bArr6[i8] = encryptByte4;
                    i8++;
                }
                this.count += i4;
            } else if (i6 == i5) {
                this.cipher.processBlock(this.FR, 0, this.FRE, 0);
                bArr2[i2] = encryptByte(bArr[i], 0);
                bArr2[i2 + 1] = encryptByte(bArr[i + 1], 1);
                byte[] bArr7 = this.FR;
                System.arraycopy(bArr7, 2, bArr7, 0, this.blockSize - 2);
                System.arraycopy(bArr2, i2, this.FR, this.blockSize - 2, 2);
                this.cipher.processBlock(this.FR, 0, this.FRE, 0);
                while (true) {
                    i3 = this.blockSize;
                    if (i7 >= i3) {
                        break;
                    }
                    byte[] bArr8 = this.FR;
                    int i13 = i7 - 2;
                    byte encryptByte5 = encryptByte(bArr[i + i7], i13);
                    bArr2[i2 + i7] = encryptByte5;
                    bArr8[i13] = encryptByte5;
                    i7++;
                }
                this.count += i3;
            }
            return this.blockSize;
        } else {
            throw new DataLengthException("output buffer too short");
        }
    }

    private int decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3;
        int i4;
        int i5 = this.blockSize;
        if (i + i5 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + i5 <= bArr2.length) {
            int i6 = this.count;
            int i7 = 2;
            int i8 = 0;
            if (i6 > i5) {
                byte b2 = bArr[i];
                this.FR[i5 - 2] = b2;
                bArr2[i2] = encryptByte(b2, i5 - 2);
                byte b3 = bArr[i + 1];
                byte[] bArr3 = this.FR;
                int i9 = this.blockSize;
                bArr3[i9 - 1] = b3;
                bArr2[i2 + 1] = encryptByte(b3, i9 - 1);
                this.cipher.processBlock(this.FR, 0, this.FRE, 0);
                while (i7 < this.blockSize) {
                    byte b4 = bArr[i + i7];
                    int i10 = i7 - 2;
                    this.FR[i10] = b4;
                    bArr2[i2 + i7] = encryptByte(b4, i10);
                    i7++;
                }
            } else if (i6 == 0) {
                this.cipher.processBlock(this.FR, 0, this.FRE, 0);
                while (true) {
                    i4 = this.blockSize;
                    if (i8 >= i4) {
                        break;
                    }
                    int i11 = i + i8;
                    this.FR[i8] = bArr[i11];
                    bArr2[i8] = encryptByte(bArr[i11], i8);
                    i8++;
                }
                this.count += i4;
            } else if (i6 == i5) {
                this.cipher.processBlock(this.FR, 0, this.FRE, 0);
                byte b5 = bArr[i];
                byte b6 = bArr[i + 1];
                bArr2[i2] = encryptByte(b5, 0);
                bArr2[i2 + 1] = encryptByte(b6, 1);
                byte[] bArr4 = this.FR;
                System.arraycopy(bArr4, 2, bArr4, 0, this.blockSize - 2);
                byte[] bArr5 = this.FR;
                int i12 = this.blockSize;
                bArr5[i12 - 2] = b5;
                bArr5[i12 - 1] = b6;
                this.cipher.processBlock(bArr5, 0, this.FRE, 0);
                while (true) {
                    i3 = this.blockSize;
                    if (i7 >= i3) {
                        break;
                    }
                    byte b7 = bArr[i + i7];
                    int i13 = i7 - 2;
                    this.FR[i13] = b7;
                    bArr2[i2 + i7] = encryptByte(b7, i13);
                    i7++;
                }
                this.count += i3;
            }
            return this.blockSize;
        } else {
            throw new DataLengthException("output buffer too short");
        }
    }
}
