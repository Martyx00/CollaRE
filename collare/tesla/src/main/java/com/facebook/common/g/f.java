package com.facebook.common.g;

import com.facebook.common.d.i;
import com.facebook.common.e.a;
import com.facebook.common.h.c;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: PooledByteArrayBufferedInputStream */
public class f extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    private final InputStream f1755a;

    /* renamed from: b  reason: collision with root package name */
    private final byte[] f1756b;

    /* renamed from: c  reason: collision with root package name */
    private final c<byte[]> f1757c;

    /* renamed from: d  reason: collision with root package name */
    private int f1758d = 0;
    private int e = 0;
    private boolean f = false;

    public f(InputStream inputStream, byte[] bArr, c<byte[]> cVar) {
        this.f1755a = (InputStream) i.a(inputStream);
        this.f1756b = (byte[]) i.a(bArr);
        this.f1757c = (c) i.a(cVar);
    }

    @Override // java.io.InputStream
    public int read() {
        i.b(this.e <= this.f1758d);
        b();
        if (!a()) {
            return -1;
        }
        byte[] bArr = this.f1756b;
        int i = this.e;
        this.e = i + 1;
        return bArr[i] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        i.b(this.e <= this.f1758d);
        b();
        if (!a()) {
            return -1;
        }
        int min = Math.min(this.f1758d - this.e, i2);
        System.arraycopy(this.f1756b, this.e, bArr, i, min);
        this.e += min;
        return min;
    }

    @Override // java.io.InputStream
    public int available() {
        i.b(this.e <= this.f1758d);
        b();
        return (this.f1758d - this.e) + this.f1755a.available();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() {
        if (!this.f) {
            this.f = true;
            this.f1757c.a(this.f1756b);
            super.close();
        }
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        i.b(this.e <= this.f1758d);
        b();
        int i = this.f1758d;
        int i2 = this.e;
        long j2 = (long) (i - i2);
        if (j2 >= j) {
            this.e = (int) (((long) i2) + j);
            return j;
        }
        this.e = i;
        return j2 + this.f1755a.skip(j - j2);
    }

    private boolean a() {
        if (this.e < this.f1758d) {
            return true;
        }
        int read = this.f1755a.read(this.f1756b);
        if (read <= 0) {
            return false;
        }
        this.f1758d = read;
        this.e = 0;
        return true;
    }

    private void b() {
        if (this.f) {
            throw new IOException("stream already closed");
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.Object
    public void finalize() {
        if (!this.f) {
            a.d("PooledByteInputStream", "Finalized without closing");
            close();
        }
        super.finalize();
    }
}
