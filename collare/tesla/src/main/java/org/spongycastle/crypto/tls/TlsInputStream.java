package org.spongycastle.crypto.tls;

import java.io.InputStream;

/* access modifiers changed from: package-private */
public class TlsInputStream extends InputStream {
    private byte[] buf = new byte[1];
    private TlsProtocol handler = null;

    TlsInputStream(TlsProtocol tlsProtocol) {
        this.handler = tlsProtocol;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.handler.applicationDataAvailable();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        return this.handler.readApplicationData(bArr, i, i2);
    }

    @Override // java.io.InputStream
    public int read() {
        if (read(this.buf) < 0) {
            return -1;
        }
        return this.buf[0] & 255;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() {
        this.handler.close();
    }
}
