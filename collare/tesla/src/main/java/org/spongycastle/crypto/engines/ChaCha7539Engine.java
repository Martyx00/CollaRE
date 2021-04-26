package org.spongycastle.crypto.engines;

import org.spongycastle.util.Pack;

public class ChaCha7539Engine extends Salsa20Engine {
    /* access modifiers changed from: protected */
    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public int getNonceSize() {
        return 12;
    }

    @Override // org.spongycastle.crypto.StreamCipher, org.spongycastle.crypto.engines.Salsa20Engine
    public String getAlgorithmName() {
        return "ChaCha7539-" + this.rounds;
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public void advanceCounter(long j) {
        int i = (int) j;
        if (((int) (j >>> 32)) <= 0) {
            int i2 = this.engineState[12];
            int[] iArr = this.engineState;
            iArr[12] = iArr[12] + i;
            if (i2 != 0 && this.engineState[12] < i2) {
                throw new IllegalStateException("attempt to increase counter past 2^32.");
            }
            return;
        }
        throw new IllegalStateException("attempt to increase counter past 2^32.");
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public void advanceCounter() {
        int[] iArr = this.engineState;
        int i = iArr[12] + 1;
        iArr[12] = i;
        if (i == 0) {
            throw new IllegalStateException("attempt to increase counter past 2^32.");
        }
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public void retreatCounter(long j) {
        int i = (int) j;
        if (((int) (j >>> 32)) != 0) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        } else if ((((long) this.engineState[12]) & 4294967295L) >= (4294967295L & ((long) i))) {
            int[] iArr = this.engineState;
            iArr[12] = iArr[12] - i;
        } else {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public void retreatCounter() {
        if (this.engineState[12] != 0) {
            int[] iArr = this.engineState;
            iArr[12] = iArr[12] - 1;
            return;
        }
        throw new IllegalStateException("attempt to reduce counter past zero.");
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public long getCounter() {
        return ((long) this.engineState[12]) & 4294967295L;
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public void resetCounter() {
        this.engineState[12] = 0;
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public void setKey(byte[] bArr, byte[] bArr2) {
        if (bArr != null) {
            if (bArr.length == 32) {
                packTauOrSigma(bArr.length, this.engineState, 0);
                Pack.littleEndianToInt(bArr, 0, this.engineState, 4, 8);
            } else {
                throw new IllegalArgumentException(getAlgorithmName() + " requires 256 bit key");
            }
        }
        Pack.littleEndianToInt(bArr2, 0, this.engineState, 13, 3);
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public void generateKeyStream(byte[] bArr) {
        ChaChaEngine.chachaCore(this.rounds, this.engineState, this.x);
        Pack.intToLittleEndian(this.x, bArr, 0);
    }
}
