package org.spongycastle.util.test;

import java.io.FilterOutputStream;
import java.io.OutputStream;

public class UncloseableOutputStream extends FilterOutputStream {
    public UncloseableOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.io.FilterOutputStream, java.lang.AutoCloseable
    public void close() {
        throw new RuntimeException("close() called on UncloseableOutputStream");
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(byte[] bArr, int i, int i2) {
        this.out.write(bArr, i, i2);
    }
}
