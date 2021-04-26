package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.InvalidCipherTextException;

public class PaddedBlockCipher extends BufferedBlockCipher {
    public PaddedBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
        this.buf = new byte[blockCipher.getBlockSize()];
        this.bufOff = 0;
    }

    @Override // org.spongycastle.crypto.BufferedBlockCipher
    public int getOutputSize(int i) {
        int i2 = i + this.bufOff;
        int length = i2 % this.buf.length;
        if (length == 0) {
            return this.forEncryption ? i2 + this.buf.length : i2;
        }
        return (i2 - length) + this.buf.length;
    }

    @Override // org.spongycastle.crypto.BufferedBlockCipher
    public int getUpdateOutputSize(int i) {
        int i2 = i + this.bufOff;
        int length = i2 % this.buf.length;
        return length == 0 ? i2 - this.buf.length : i2 - length;
    }

    @Override // org.spongycastle.crypto.BufferedBlockCipher
    public int processByte(byte b2, byte[] bArr, int i) {
        int i2;
        if (this.bufOff == this.buf.length) {
            i2 = this.cipher.processBlock(this.buf, 0, bArr, i);
            this.bufOff = 0;
        } else {
            i2 = 0;
        }
        byte[] bArr2 = this.buf;
        int i3 = this.bufOff;
        this.bufOff = i3 + 1;
        bArr2[i3] = b2;
        return i2;
    }

    @Override // org.spongycastle.crypto.BufferedBlockCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (i2 >= 0) {
            int blockSize = getBlockSize();
            int updateOutputSize = getUpdateOutputSize(i2);
            if (updateOutputSize <= 0 || updateOutputSize + i3 <= bArr2.length) {
                int length = this.buf.length - this.bufOff;
                int i4 = 0;
                if (i2 > length) {
                    System.arraycopy(bArr, i, this.buf, this.bufOff, length);
                    this.bufOff = 0;
                    i2 -= length;
                    i += length;
                    i4 = this.cipher.processBlock(this.buf, 0, bArr2, i3) + 0;
                    while (i2 > this.buf.length) {
                        i4 += this.cipher.processBlock(bArr, i, bArr2, i3 + i4);
                        i2 -= blockSize;
                        i += blockSize;
                    }
                }
                System.arraycopy(bArr, i, this.buf, this.bufOff, i2);
                this.bufOff += i2;
                return i4;
            }
            throw new DataLengthException("output buffer too short");
        }
        throw new IllegalArgumentException("Can't have a negative input length!");
    }

    @Override // org.spongycastle.crypto.BufferedBlockCipher
    public int doFinal(byte[] bArr, int i) {
        int i2;
        int i3;
        int blockSize = this.cipher.getBlockSize();
        if (this.forEncryption) {
            if (this.bufOff != blockSize) {
                i3 = 0;
            } else if ((blockSize * 2) + i <= bArr.length) {
                i3 = this.cipher.processBlock(this.buf, 0, bArr, i);
                this.bufOff = 0;
            } else {
                throw new DataLengthException("output buffer too short");
            }
            byte b2 = (byte) (blockSize - this.bufOff);
            while (this.bufOff < blockSize) {
                this.buf[this.bufOff] = b2;
                this.bufOff++;
            }
            i2 = i3 + this.cipher.processBlock(this.buf, 0, bArr, i + i3);
        } else if (this.bufOff == blockSize) {
            int processBlock = this.cipher.processBlock(this.buf, 0, this.buf, 0);
            this.bufOff = 0;
            int i4 = this.buf[blockSize - 1] & 255;
            if (i4 < 0 || i4 > blockSize) {
                throw new InvalidCipherTextException("pad block corrupted");
            }
            i2 = processBlock - i4;
            System.arraycopy(this.buf, 0, bArr, i, i2);
        } else {
            throw new DataLengthException("last block incomplete in decryption");
        }
        reset();
        return i2;
    }
}
