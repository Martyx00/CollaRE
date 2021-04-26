package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.paddings.BlockCipherPadding;

public class CFBBlockCipherMac implements Mac {
    private byte[] buf;
    private int bufOff;
    private MacCFBBlockCipher cipher;
    private byte[] mac;
    private int macSize;
    private BlockCipherPadding padding;

    public CFBBlockCipherMac(BlockCipher blockCipher) {
        this(blockCipher, 8, (blockCipher.getBlockSize() * 8) / 2, null);
    }

    public CFBBlockCipherMac(BlockCipher blockCipher, BlockCipherPadding blockCipherPadding) {
        this(blockCipher, 8, (blockCipher.getBlockSize() * 8) / 2, blockCipherPadding);
    }

    public CFBBlockCipherMac(BlockCipher blockCipher, int i, int i2) {
        this(blockCipher, i, i2, null);
    }

    public CFBBlockCipherMac(BlockCipher blockCipher, int i, int i2, BlockCipherPadding blockCipherPadding) {
        this.padding = null;
        if (i2 % 8 == 0) {
            this.mac = new byte[blockCipher.getBlockSize()];
            this.cipher = new MacCFBBlockCipher(blockCipher, i);
            this.padding = blockCipherPadding;
            this.macSize = i2 / 8;
            this.buf = new byte[this.cipher.getBlockSize()];
            this.bufOff = 0;
            return;
        }
        throw new IllegalArgumentException("MAC size must be multiple of 8");
    }

    @Override // org.spongycastle.crypto.Mac
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName();
    }

    @Override // org.spongycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) {
        reset();
        this.cipher.init(cipherParameters);
    }

    @Override // org.spongycastle.crypto.Mac
    public int getMacSize() {
        return this.macSize;
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte b2) {
        int i = this.bufOff;
        byte[] bArr = this.buf;
        if (i == bArr.length) {
            this.cipher.processBlock(bArr, 0, this.mac, 0);
            this.bufOff = 0;
        }
        byte[] bArr2 = this.buf;
        int i2 = this.bufOff;
        this.bufOff = i2 + 1;
        bArr2[i2] = b2;
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte[] bArr, int i, int i2) {
        if (i2 >= 0) {
            int blockSize = this.cipher.getBlockSize();
            int i3 = this.bufOff;
            int i4 = blockSize - i3;
            if (i2 > i4) {
                System.arraycopy(bArr, i, this.buf, i3, i4);
                this.cipher.processBlock(this.buf, 0, this.mac, 0);
                this.bufOff = 0;
                i2 -= i4;
                i += i4;
                while (i2 > blockSize) {
                    this.cipher.processBlock(bArr, i, this.mac, 0);
                    i2 -= blockSize;
                    i += blockSize;
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
        int blockSize = this.cipher.getBlockSize();
        BlockCipherPadding blockCipherPadding = this.padding;
        if (blockCipherPadding == null) {
            while (true) {
                int i2 = this.bufOff;
                if (i2 >= blockSize) {
                    break;
                }
                this.buf[i2] = 0;
                this.bufOff = i2 + 1;
            }
        } else {
            blockCipherPadding.addPadding(this.buf, this.bufOff);
        }
        this.cipher.processBlock(this.buf, 0, this.mac, 0);
        this.cipher.getMacBlock(this.mac);
        System.arraycopy(this.mac, 0, bArr, i, this.macSize);
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
                this.cipher.reset();
                return;
            }
        }
    }
}
