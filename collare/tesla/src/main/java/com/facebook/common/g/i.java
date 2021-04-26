package com.facebook.common.g;

import java.io.InputStream;

/* compiled from: PooledByteBufferInputStream */
public class i extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    final g f1759a;

    /* renamed from: b  reason: collision with root package name */
    int f1760b = 0;

    /* renamed from: c  reason: collision with root package name */
    int f1761c = 0;

    public boolean markSupported() {
        return true;
    }

    public i(g gVar) {
        com.facebook.common.d.i.a(!gVar.b());
        this.f1759a = (g) com.facebook.common.d.i.a(gVar);
    }

    @Override // java.io.InputStream
    public int available() {
        return this.f1759a.a() - this.f1760b;
    }

    public void mark(int i) {
        this.f1761c = this.f1760b;
    }

    @Override // java.io.InputStream
    public int read() {
        if (available() <= 0) {
            return -1;
        }
        g gVar = this.f1759a;
        int i = this.f1760b;
        this.f1760b = i + 1;
        return gVar.a(i) & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new ArrayIndexOutOfBoundsException("length=" + bArr.length + "; regionStart=" + i + "; regionLength=" + i2);
        }
        int available = available();
        if (available <= 0) {
            return -1;
        }
        if (i2 <= 0) {
            return 0;
        }
        int min = Math.min(available, i2);
        this.f1759a.a(this.f1760b, bArr, i, min);
        this.f1760b += min;
        return min;
    }

    @Override // java.io.InputStream
    public void reset() {
        this.f1760b = this.f1761c;
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        com.facebook.common.d.i.a(j >= 0);
        int min = Math.min((int) j, available());
        this.f1760b += min;
        return (long) min;
    }
}
