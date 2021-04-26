package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.PBEParametersGenerator;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.util.DigestFactory;
import org.spongycastle.util.Arrays;

public class PKCS5S2ParametersGenerator extends PBEParametersGenerator {
    private Mac hMac;
    private byte[] state;

    public PKCS5S2ParametersGenerator() {
        this(DigestFactory.createSHA1());
    }

    public PKCS5S2ParametersGenerator(Digest digest) {
        this.hMac = new HMac(digest);
        this.state = new byte[this.hMac.getMacSize()];
    }

    private void F(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2) {
        if (i != 0) {
            if (bArr != null) {
                this.hMac.update(bArr, 0, bArr.length);
            }
            this.hMac.update(bArr2, 0, bArr2.length);
            this.hMac.doFinal(this.state, 0);
            byte[] bArr4 = this.state;
            System.arraycopy(bArr4, 0, bArr3, i2, bArr4.length);
            for (int i3 = 1; i3 < i; i3++) {
                Mac mac = this.hMac;
                byte[] bArr5 = this.state;
                mac.update(bArr5, 0, bArr5.length);
                this.hMac.doFinal(this.state, 0);
                int i4 = 0;
                while (true) {
                    byte[] bArr6 = this.state;
                    if (i4 == bArr6.length) {
                        break;
                    }
                    int i5 = i2 + i4;
                    bArr3[i5] = (byte) (bArr6[i4] ^ bArr3[i5]);
                    i4++;
                }
            }
            return;
        }
        throw new IllegalArgumentException("iteration count must be at least 1.");
    }

    private byte[] generateDerivedKey(int i) {
        int macSize = this.hMac.getMacSize();
        int i2 = ((i + macSize) - 1) / macSize;
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[(i2 * macSize)];
        this.hMac.init(new KeyParameter(this.password));
        int i3 = 0;
        for (int i4 = 1; i4 <= i2; i4++) {
            int i5 = 3;
            while (true) {
                byte b2 = (byte) (bArr[i5] + 1);
                bArr[i5] = b2;
                if (b2 != 0) {
                    break;
                }
                i5--;
            }
            F(this.salt, this.iterationCount, bArr, bArr2, i3);
            i3 += macSize;
        }
        return bArr2;
    }

    @Override // org.spongycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int i) {
        int i2 = i / 8;
        return new KeyParameter(Arrays.copyOfRange(generateDerivedKey(i2), 0, i2), 0, i2);
    }

    @Override // org.spongycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int i, int i2) {
        int i3 = i / 8;
        int i4 = i2 / 8;
        byte[] generateDerivedKey = generateDerivedKey(i3 + i4);
        return new ParametersWithIV(new KeyParameter(generateDerivedKey, 0, i3), generateDerivedKey, i3, i4);
    }

    @Override // org.spongycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedMacParameters(int i) {
        return generateDerivedParameters(i);
    }
}
