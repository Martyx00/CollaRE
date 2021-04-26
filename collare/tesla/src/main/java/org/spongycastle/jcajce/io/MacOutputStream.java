package org.spongycastle.jcajce.io;

import java.io.OutputStream;
import javax.crypto.Mac;

public final class MacOutputStream extends OutputStream {
    private Mac mac;

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
        return this.mac.doFinal();
    }
}
