package org.spongycastle.crypto.tls;

import java.io.OutputStream;

/* access modifiers changed from: package-private */
public class TlsOutputStream extends OutputStream {
    private byte[] buf = new byte[1];
    private TlsProtocol handler;

    TlsOutputStream(TlsProtocol tlsProtocol) {
        this.handler = tlsProtocol;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        this.handler.writeData(bArr, i, i2);
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        byte[] bArr = this.buf;
        bArr[0] = (byte) i;
        write(bArr, 0, 1);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.handler.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        this.handler.flush();
    }
}
