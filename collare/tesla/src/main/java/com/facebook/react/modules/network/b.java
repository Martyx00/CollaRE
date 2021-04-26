package com.facebook.react.modules.network;

import java.io.FilterOutputStream;
import java.io.OutputStream;

/* compiled from: CountingOutputStream */
public class b extends FilterOutputStream {

    /* renamed from: a  reason: collision with root package name */
    private long f2927a = 0;

    public b(OutputStream outputStream) {
        super(outputStream);
    }

    public long a() {
        return this.f2927a;
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(byte[] bArr, int i, int i2) {
        this.out.write(bArr, i, i2);
        this.f2927a += (long) i2;
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(int i) {
        this.out.write(i);
        this.f2927a++;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.io.FilterOutputStream, java.lang.AutoCloseable
    public void close() {
        this.out.close();
    }
}
