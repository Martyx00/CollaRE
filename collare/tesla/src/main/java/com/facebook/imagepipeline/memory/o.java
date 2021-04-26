package com.facebook.imagepipeline.memory;

import com.facebook.common.d.i;
import com.facebook.common.g.j;

/* compiled from: NativePooledByteBufferOutputStream */
public class o extends j {

    /* renamed from: a  reason: collision with root package name */
    private final l f2218a;

    /* renamed from: b  reason: collision with root package name */
    private com.facebook.common.h.a<NativeMemoryChunk> f2219b;

    /* renamed from: c  reason: collision with root package name */
    private int f2220c;

    public o(l lVar) {
        this(lVar, lVar.d());
    }

    public o(l lVar, int i) {
        i.a(i > 0);
        this.f2218a = (l) i.a(lVar);
        this.f2220c = 0;
        this.f2219b = com.facebook.common.h.a.a(this.f2218a.a(i), this.f2218a);
    }

    /* renamed from: c */
    public m a() {
        d();
        return new m(this.f2219b, this.f2220c);
    }

    @Override // com.facebook.common.g.j
    public int b() {
        return this.f2220c;
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        write(new byte[]{(byte) i});
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new ArrayIndexOutOfBoundsException("length=" + bArr.length + "; regionStart=" + i + "; regionLength=" + i2);
        }
        d();
        a(this.f2220c + i2);
        this.f2219b.a().a(this.f2220c, bArr, i, i2);
        this.f2220c += i2;
    }

    @Override // java.io.OutputStream, java.io.Closeable, com.facebook.common.g.j, java.lang.AutoCloseable
    public void close() {
        com.facebook.common.h.a.c(this.f2219b);
        this.f2219b = null;
        this.f2220c = -1;
        super.close();
    }

    /* access modifiers changed from: package-private */
    public void a(int i) {
        d();
        if (i > this.f2219b.a().b()) {
            NativeMemoryChunk nativeMemoryChunk = (NativeMemoryChunk) this.f2218a.a(i);
            this.f2219b.a().a(0, nativeMemoryChunk, 0, this.f2220c);
            this.f2219b.close();
            this.f2219b = com.facebook.common.h.a.a(nativeMemoryChunk, this.f2218a);
        }
    }

    private void d() {
        if (!com.facebook.common.h.a.a((com.facebook.common.h.a<?>) this.f2219b)) {
            throw new a();
        }
    }

    /* compiled from: NativePooledByteBufferOutputStream */
    public static class a extends RuntimeException {
        public a() {
            super("OutputStream no longer valid");
        }
    }
}
