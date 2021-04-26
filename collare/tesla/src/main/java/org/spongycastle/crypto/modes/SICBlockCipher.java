package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.SkippingStreamCipher;
import org.spongycastle.crypto.StreamBlockCipher;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;

public class SICBlockCipher extends StreamBlockCipher implements SkippingStreamCipher {
    private byte[] IV;
    private final int blockSize = this.cipher.getBlockSize();
    private int byteCount;
    private final BlockCipher cipher;
    private byte[] counter;
    private byte[] counterOut;

    public SICBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.cipher = blockCipher;
        int i = this.blockSize;
        this.IV = new byte[i];
        this.counter = new byte[i];
        this.counterOut = new byte[i];
        this.byteCount = 0;
    }

    @Override // org.spongycastle.crypto.StreamCipher, org.spongycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.IV = Arrays.clone(parametersWithIV.getIV());
            int i = this.blockSize;
            if (i >= this.IV.length) {
                int i2 = 8;
                if (8 > i / 2) {
                    i2 = i / 2;
                }
                if (this.blockSize - this.IV.length <= i2) {
                    if (parametersWithIV.getParameters() != null) {
                        this.cipher.init(true, parametersWithIV.getParameters());
                    }
                    reset();
                    return;
                }
                throw new IllegalArgumentException("CTR/SIC mode requires IV of at least: " + (this.blockSize - i2) + " bytes.");
            }
            throw new IllegalArgumentException("CTR/SIC mode requires IV no greater than: " + this.blockSize + " bytes.");
        }
        throw new IllegalArgumentException("CTR/SIC mode requires ParametersWithIV");
    }

    @Override // org.spongycastle.crypto.StreamCipher, org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/SIC";
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        processBytes(bArr, i, this.blockSize, bArr2, i2);
        return this.blockSize;
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.crypto.StreamBlockCipher
    public byte calculateByte(byte b2) {
        int i = this.byteCount;
        if (i == 0) {
            this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
            byte[] bArr = this.counterOut;
            int i2 = this.byteCount;
            this.byteCount = i2 + 1;
            return (byte) (b2 ^ bArr[i2]);
        }
        byte[] bArr2 = this.counterOut;
        this.byteCount = i + 1;
        byte b3 = (byte) (b2 ^ bArr2[i]);
        if (this.byteCount == this.counter.length) {
            this.byteCount = 0;
            incrementCounterAt(0);
            checkCounter();
        }
        return b3;
    }

    private void checkCounter() {
        if (this.IV.length < this.blockSize) {
            int i = 0;
            while (true) {
                byte[] bArr = this.IV;
                if (i == bArr.length) {
                    return;
                }
                if (this.counter[i] == bArr[i]) {
                    i++;
                } else {
                    throw new IllegalStateException("Counter in CTR/SIC mode out of range.");
                }
            }
        }
    }

    private void incrementCounterAt(int i) {
        byte b2;
        int length = this.counter.length - i;
        do {
            length--;
            if (length >= 0) {
                byte[] bArr = this.counter;
                b2 = (byte) (bArr[length] + 1);
                bArr[length] = b2;
            } else {
                return;
            }
        } while (b2 == 0);
    }

    private void incrementCounter(int i) {
        byte[] bArr = this.counter;
        byte b2 = bArr[bArr.length - 1];
        int length = bArr.length - 1;
        bArr[length] = (byte) (bArr[length] + i);
        if (b2 != 0 && bArr[bArr.length - 1] < b2) {
            incrementCounterAt(1);
        }
    }

    private void decrementCounterAt(int i) {
        byte b2;
        int length = this.counter.length - i;
        do {
            length--;
            if (length >= 0) {
                byte[] bArr = this.counter;
                b2 = (byte) (bArr[length] - 1);
                bArr[length] = b2;
            } else {
                return;
            }
        } while (b2 == -1);
    }

    private void adjustCounter(long j) {
        long j2;
        long j3;
        int i = 5;
        if (j >= 0) {
            long j4 = (((long) this.byteCount) + j) / ((long) this.blockSize);
            if (j4 > 255) {
                j3 = j4;
                while (i >= 1) {
                    long j5 = 1 << (i * 8);
                    while (j3 >= j5) {
                        incrementCounterAt(i);
                        j3 -= j5;
                    }
                    i--;
                }
            } else {
                j3 = j4;
            }
            incrementCounter((int) j3);
            this.byteCount = (int) ((j + ((long) this.byteCount)) - (((long) this.blockSize) * j4));
            return;
        }
        long j6 = ((-j) - ((long) this.byteCount)) / ((long) this.blockSize);
        if (j6 > 255) {
            j2 = j6;
            while (i >= 1) {
                long j7 = 1 << (i * 8);
                while (j2 > j7) {
                    decrementCounterAt(i);
                    j2 -= j7;
                }
                i--;
            }
        } else {
            j2 = j6;
        }
        for (long j8 = 0; j8 != j2; j8++) {
            decrementCounterAt(0);
        }
        int i2 = (int) (((long) this.byteCount) + j + (((long) this.blockSize) * j6));
        if (i2 >= 0) {
            this.byteCount = 0;
            return;
        }
        decrementCounterAt(0);
        this.byteCount = this.blockSize + i2;
    }

    @Override // org.spongycastle.crypto.StreamCipher, org.spongycastle.crypto.BlockCipher
    public void reset() {
        Arrays.fill(this.counter, (byte) 0);
        byte[] bArr = this.IV;
        System.arraycopy(bArr, 0, this.counter, 0, bArr.length);
        this.cipher.reset();
        this.byteCount = 0;
    }

    @Override // org.spongycastle.crypto.SkippingCipher
    public long skip(long j) {
        adjustCounter(j);
        checkCounter();
        this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
        return j;
    }

    @Override // org.spongycastle.crypto.SkippingCipher
    public long seekTo(long j) {
        reset();
        return skip(j);
    }

    @Override // org.spongycastle.crypto.SkippingCipher
    public long getPosition() {
        int i;
        byte[] bArr = this.counter;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        for (int length = bArr2.length - 1; length >= 1; length--) {
            byte[] bArr3 = this.IV;
            if (length < bArr3.length) {
                i = (bArr2[length] & 255) - (bArr3[length] & 255);
            } else {
                i = bArr2[length] & 255;
            }
            if (i < 0) {
                int i2 = length - 1;
                bArr2[i2] = (byte) (bArr2[i2] - 1);
                i += 256;
            }
            bArr2[length] = (byte) i;
        }
        return (Pack.bigEndianToLong(bArr2, bArr2.length - 8) * ((long) this.blockSize)) + ((long) this.byteCount);
    }
}
