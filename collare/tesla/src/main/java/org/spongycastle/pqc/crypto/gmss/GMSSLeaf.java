package org.spongycastle.pqc.crypto.gmss;

import io.a.a.a.a.b.a;
import org.spongycastle.crypto.Digest;
import org.spongycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.encoders.Hex;

public class GMSSLeaf {
    private byte[] concHashs;
    private GMSSRandom gmssRandom;
    private int i;
    private int j;
    private int keysize;
    private byte[] leaf;
    private int mdsize;
    private Digest messDigestOTS;
    byte[] privateKeyOTS;
    private byte[] seed;
    private int steps;
    private int two_power_w;
    private int w;

    private int getLog(int i2) {
        int i3 = 1;
        int i4 = 2;
        while (i4 < i2) {
            i4 <<= 1;
            i3++;
        }
        return i3;
    }

    public GMSSLeaf(Digest digest, byte[][] bArr, int[] iArr) {
        this.i = iArr[0];
        this.j = iArr[1];
        this.steps = iArr[2];
        this.w = iArr[3];
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(this.messDigestOTS);
        this.mdsize = this.messDigestOTS.getDigestSize();
        int ceil = (int) Math.ceil(((double) (this.mdsize << 3)) / ((double) this.w));
        this.keysize = ceil + ((int) Math.ceil(((double) getLog((ceil << this.w) + 1)) / ((double) this.w)));
        this.two_power_w = 1 << this.w;
        this.privateKeyOTS = bArr[0];
        this.seed = bArr[1];
        this.concHashs = bArr[2];
        this.leaf = bArr[3];
    }

    GMSSLeaf(Digest digest, int i2, int i3) {
        this.w = i2;
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(this.messDigestOTS);
        this.mdsize = this.messDigestOTS.getDigestSize();
        double d2 = (double) i2;
        int ceil = (int) Math.ceil(((double) (this.mdsize << 3)) / d2);
        this.keysize = ceil + ((int) Math.ceil(((double) getLog((ceil << i2) + 1)) / d2));
        int i4 = 1 << i2;
        this.two_power_w = i4;
        int i5 = this.keysize;
        this.steps = (int) Math.ceil(((double) ((((i4 - 1) * i5) + 1) + i5)) / ((double) i3));
        int i6 = this.mdsize;
        this.seed = new byte[i6];
        this.leaf = new byte[i6];
        this.privateKeyOTS = new byte[i6];
        this.concHashs = new byte[(i6 * this.keysize)];
    }

    public GMSSLeaf(Digest digest, int i2, int i3, byte[] bArr) {
        this.w = i2;
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(this.messDigestOTS);
        this.mdsize = this.messDigestOTS.getDigestSize();
        double d2 = (double) i2;
        int ceil = (int) Math.ceil(((double) (this.mdsize << 3)) / d2);
        this.keysize = ceil + ((int) Math.ceil(((double) getLog((ceil << i2) + 1)) / d2));
        int i4 = 1 << i2;
        this.two_power_w = i4;
        int i5 = this.keysize;
        this.steps = (int) Math.ceil(((double) ((((i4 - 1) * i5) + 1) + i5)) / ((double) i3));
        int i6 = this.mdsize;
        this.seed = new byte[i6];
        this.leaf = new byte[i6];
        this.privateKeyOTS = new byte[i6];
        this.concHashs = new byte[(i6 * this.keysize)];
        initLeafCalc(bArr);
    }

    private GMSSLeaf(GMSSLeaf gMSSLeaf) {
        this.messDigestOTS = gMSSLeaf.messDigestOTS;
        this.mdsize = gMSSLeaf.mdsize;
        this.keysize = gMSSLeaf.keysize;
        this.gmssRandom = gMSSLeaf.gmssRandom;
        this.leaf = Arrays.clone(gMSSLeaf.leaf);
        this.concHashs = Arrays.clone(gMSSLeaf.concHashs);
        this.i = gMSSLeaf.i;
        this.j = gMSSLeaf.j;
        this.two_power_w = gMSSLeaf.two_power_w;
        this.w = gMSSLeaf.w;
        this.steps = gMSSLeaf.steps;
        this.seed = Arrays.clone(gMSSLeaf.seed);
        this.privateKeyOTS = Arrays.clone(gMSSLeaf.privateKeyOTS);
    }

    /* access modifiers changed from: package-private */
    public void initLeafCalc(byte[] bArr) {
        this.i = 0;
        this.j = 0;
        byte[] bArr2 = new byte[this.mdsize];
        System.arraycopy(bArr, 0, bArr2, 0, this.seed.length);
        this.seed = this.gmssRandom.nextSeed(bArr2);
    }

    /* access modifiers changed from: package-private */
    public GMSSLeaf nextLeaf() {
        GMSSLeaf gMSSLeaf = new GMSSLeaf(this);
        gMSSLeaf.updateLeafCalc();
        return gMSSLeaf;
    }

    private void updateLeafCalc() {
        byte[] bArr = new byte[this.messDigestOTS.getDigestSize()];
        for (int i2 = 0; i2 < this.steps + a.DEFAULT_TIMEOUT; i2++) {
            if (this.i == this.keysize && this.j == this.two_power_w - 1) {
                Digest digest = this.messDigestOTS;
                byte[] bArr2 = this.concHashs;
                digest.update(bArr2, 0, bArr2.length);
                this.leaf = new byte[this.messDigestOTS.getDigestSize()];
                this.messDigestOTS.doFinal(this.leaf, 0);
                return;
            }
            if (this.i == 0 || this.j == this.two_power_w - 1) {
                this.i++;
                this.j = 0;
                this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
            } else {
                Digest digest2 = this.messDigestOTS;
                byte[] bArr3 = this.privateKeyOTS;
                digest2.update(bArr3, 0, bArr3.length);
                this.privateKeyOTS = bArr;
                this.messDigestOTS.doFinal(this.privateKeyOTS, 0);
                this.j++;
                if (this.j == this.two_power_w - 1) {
                    byte[] bArr4 = this.privateKeyOTS;
                    byte[] bArr5 = this.concHashs;
                    int i3 = this.mdsize;
                    System.arraycopy(bArr4, 0, bArr5, (this.i - 1) * i3, i3);
                }
            }
        }
        throw new IllegalStateException("unable to updateLeaf in steps: " + this.steps + " " + this.i + " " + this.j);
    }

    public byte[] getLeaf() {
        return Arrays.clone(this.leaf);
    }

    public byte[][] getStatByte() {
        int i2 = this.mdsize;
        byte[][] bArr = {new byte[i2], new byte[i2], new byte[(this.keysize * i2)], new byte[i2]};
        bArr[0] = this.privateKeyOTS;
        bArr[1] = this.seed;
        bArr[2] = this.concHashs;
        bArr[3] = this.leaf;
        return bArr;
    }

    public int[] getStatInt() {
        return new int[]{this.i, this.j, this.steps, this.w};
    }

    public String toString() {
        String str = "";
        for (int i2 = 0; i2 < 4; i2++) {
            str = str + getStatInt()[i2] + " ";
        }
        String str2 = str + " " + this.mdsize + " " + this.keysize + " " + this.two_power_w + " ";
        byte[][] statByte = getStatByte();
        for (int i3 = 0; i3 < 4; i3++) {
            str2 = statByte[i3] != null ? str2 + new String(Hex.encode(statByte[i3])) + " " : str2 + "null ";
        }
        return str2;
    }
}
