package com.facebook.common.d;

import java.io.FilterOutputStream;
import java.io.OutputStream;

/* compiled from: CountingOutputStream */
public class c extends FilterOutputStream {

    /* renamed from: a  reason: collision with root package name */
    private long f1738a = 0;

    public c(OutputStream outputStream) {
        super(outputStream);
    }

    public long a() {
        return this.f1738a;
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(byte[] bArr, int i, int i2) {
        this.out.write(bArr, i, i2);
        this.f1738a += (long) i2;
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(int i) {
        this.out.write(i);
        this.f1738a++;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.io.FilterOutputStream, java.lang.AutoCloseable
    public void close() {
        this.out.close();
    }
}
