package org.spongycastle.crypto.io;

import java.io.OutputStream;
import org.spongycastle.crypto.Mac;

public class MacOutputStream extends OutputStream {
    protected Mac mac;

    public MacOutputStream(Mac mac2) {
        this.mac = mac2;
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        this.mac.update((byte) i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        this.mac.update(bArr, i, i2);
    }

    public byte[] getMac() {
        byte[] bArr = new byte[this.mac.getMacSize()];
        this.mac.doFinal(bArr, 0);
        return bArr;
    }
}
