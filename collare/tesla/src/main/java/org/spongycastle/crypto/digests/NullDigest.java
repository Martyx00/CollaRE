package org.spongycastle.crypto.digests;

import java.io.ByteArrayOutputStream;
import org.spongycastle.crypto.Digest;

public class NullDigest implements Digest {
    private ByteArrayOutputStream bOut = new ByteArrayOutputStream();

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return "NULL";
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return this.bOut.size();
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte b2) {
        this.bOut.write(b2);
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        this.bOut.write(bArr, i, i2);
    }

    @Override // org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        byte[] byteArray = this.bOut.toByteArray();
        System.arraycopy(byteArray, 0, bArr, i, byteArray.length);
        reset();
        return byteArray.length;
    }

    @Override // org.spongycastle.crypto.Digest
    public void reset() {
        this.bOut.reset();
    }
}
