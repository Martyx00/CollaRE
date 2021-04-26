package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

public class VMPCEngine implements StreamCipher {
    protected byte[] P = null;
    protected byte n = 0;
    protected byte s = 0;
    protected byte[] workingIV;
    protected byte[] workingKey;

    @Override // org.spongycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "VMPC";
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            if (parametersWithIV.getParameters() instanceof KeyParameter) {
                KeyParameter keyParameter = (KeyParameter) parametersWithIV.getParameters();
                this.workingIV = parametersWithIV.getIV();
                byte[] bArr = this.workingIV;
                if (bArr == null || bArr.length < 1 || bArr.length > 768) {
                    throw new IllegalArgumentException("VMPC requires 1 to 768 bytes of IV");
                }
                this.workingKey = keyParameter.getKey();
                initKey(this.workingKey, this.workingIV);
                return;
            }
            throw new IllegalArgumentException("VMPC init parameters must include a key");
        }
        throw new IllegalArgumentException("VMPC init parameters must include an IV");
    }

    /* access modifiers changed from: protected */
    public void initKey(byte[] bArr, byte[] bArr2) {
        this.s = 0;
        this.P = new byte[256];
        for (int i = 0; i < 256; i++) {
            this.P[i] = (byte) i;
        }
        for (int i2 = 0; i2 < 768; i2++) {
            byte[] bArr3 = this.P;
            int i3 = i2 & 255;
            this.s = bArr3[(this.s + bArr3[i3] + bArr[i2 % bArr.length]) & 255];
            byte b2 = bArr3[i3];
            byte b3 = this.s;
            bArr3[i3] = bArr3[b3 & 255];
            bArr3[b3 & 255] = b2;
        }
        for (int i4 = 0; i4 < 768; i4++) {
            byte[] bArr4 = this.P;
            int i5 = i4 & 255;
            this.s = bArr4[(this.s + bArr4[i5] + bArr2[i4 % bArr2.length]) & 255];
            byte b4 = bArr4[i5];
            byte b5 = this.s;
            bArr4[i5] = bArr4[b5 & 255];
            bArr4[b5 & 255] = b4;
        }
        this.n = 0;
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i3 + i2 <= bArr2.length) {
            for (int i4 = 0; i4 < i2; i4++) {
                byte[] bArr3 = this.P;
                byte b2 = this.s;
                byte b3 = this.n;
                this.s = bArr3[(b2 + bArr3[b3 & 255]) & 255];
                byte b4 = this.s;
                byte b5 = bArr3[(bArr3[bArr3[b4 & 255] & 255] + 1) & 255];
                byte b6 = bArr3[b3 & 255];
                bArr3[b3 & 255] = bArr3[b4 & 255];
                bArr3[b4 & 255] = b6;
                this.n = (byte) ((b3 + 1) & 255);
                bArr2[i4 + i3] = (byte) (bArr[i4 + i] ^ b5);
            }
            return i2;
        } else {
            throw new OutputLengthException("output buffer too short");
        }
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public void reset() {
        initKey(this.workingKey, this.workingIV);
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public byte returnByte(byte b2) {
        byte[] bArr = this.P;
        byte b3 = this.s;
        byte b4 = this.n;
        this.s = bArr[(b3 + bArr[b4 & 255]) & 255];
        byte b5 = this.s;
        byte b6 = bArr[(bArr[bArr[b5 & 255] & 255] + 1) & 255];
        byte b7 = bArr[b4 & 255];
        bArr[b4 & 255] = bArr[b5 & 255];
        bArr[b5 & 255] = b7;
        this.n = (byte) ((b4 + 1) & 255);
        return (byte) (b2 ^ b6);
    }
}
