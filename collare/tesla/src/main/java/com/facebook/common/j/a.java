package com.facebook.common.j;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: LimitedInputStream */
public class a extends FilterInputStream {

    /* renamed from: a  reason: collision with root package name */
    private int f1782a;

    /* renamed from: b  reason: collision with root package name */
    private int f1783b;

    public a(InputStream inputStream, int i) {
        super(inputStream);
        if (inputStream == null) {
            throw new NullPointerException();
        } else if (i >= 0) {
            this.f1782a = i;
            this.f1783b = -1;
        } else {
            throw new IllegalArgumentException("limit must be >= 0");
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        if (this.f1782a == 0) {
            return -1;
        }
        int read = this.in.read();
        if (read != -1) {
            this.f1782a--;
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        int i3 = this.f1782a;
        if (i3 == 0) {
            return -1;
        }
        int read = this.in.read(bArr, i, Math.min(i2, i3));
        if (read > 0) {
            this.f1782a -= read;
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) {
        long skip = this.in.skip(Math.min(j, (long) this.f1782a));
        this.f1782a = (int) (((long) this.f1782a) - skip);
        return skip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() {
        return Math.min(this.in.available(), this.f1782a);
    }

    public void mark(int i) {
        if (this.in.markSupported()) {
            this.in.mark(i);
            this.f1783b = this.f1782a;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() {
        if (!this.in.markSupported()) {
            throw new IOException("mark is not supported");
        } else if (this.f1783b != -1) {
            this.in.reset();
            this.f1782a = this.f1783b;
        } else {
            throw new IOException("mark not set");
        }
    }
}
