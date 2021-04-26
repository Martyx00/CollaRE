package org.spongycastle.crypto.io;

import java.io.OutputStream;
import org.spongycastle.crypto.Digest;

public class DigestOutputStream extends OutputStream {
    protected Digest digest;

    public DigestOutputStream(Digest digest2) {
        this.digest = digest2;
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        this.digest.update((byte) i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        this.digest.update(bArr, i, i2);
    }

    public byte[] getDigest() {
        byte[] bArr = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr, 0);
        return bArr;
    }
}
