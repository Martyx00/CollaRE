package org.spongycastle.crypto.digests;

import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.crypto.params.SkeinParameters;
import org.spongycastle.util.Memoable;

public class SkeinDigest implements ExtendedDigest, Memoable {
    public static final int SKEIN_1024 = 1024;
    public static final int SKEIN_256 = 256;
    public static final int SKEIN_512 = 512;
    private SkeinEngine engine;

    public SkeinDigest(int i, int i2) {
        this.engine = new SkeinEngine(i, i2);
        init(null);
    }

    public SkeinDigest(SkeinDigest skeinDigest) {
        this.engine = new SkeinEngine(skeinDigest.engine);
    }

    @Override // org.spongycastle.util.Memoable
    public void reset(Memoable memoable) {
        this.engine.reset(((SkeinDigest) memoable).engine);
    }

    @Override // org.spongycastle.util.Memoable
    public Memoable copy() {
        return new SkeinDigest(this);
    }

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return "Skein-" + (this.engine.getBlockSize() * 8) + "-" + (this.engine.getOutputSize() * 8);
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return this.engine.getOutputSize();
    }

    @Override // org.spongycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return this.engine.getBlockSize();
    }

    public void init(SkeinParameters skeinParameters) {
        this.engine.init(skeinParameters);
    }

    @Override // org.spongycastle.crypto.Digest
    public void reset() {
        this.engine.reset();
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte b2) {
        this.engine.update(b2);
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        this.engine.update(bArr, i, i2);
    }

    @Override // org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        return this.engine.doFinal(bArr, i);
    }
}
