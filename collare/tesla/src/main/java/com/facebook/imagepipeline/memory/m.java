package com.facebook.imagepipeline.memory;

import com.facebook.common.d.i;
import com.facebook.common.g.g;
import com.facebook.common.h.a;

/* compiled from: NativePooledByteBuffer */
public class m implements g {

    /* renamed from: a  reason: collision with root package name */
    a<NativeMemoryChunk> f2214a;

    /* renamed from: b  reason: collision with root package name */
    private final int f2215b;

    public m(a<NativeMemoryChunk> aVar, int i) {
        i.a(aVar);
        i.a(i >= 0 && i <= aVar.a().b());
        this.f2214a = aVar.clone();
        this.f2215b = i;
    }

    @Override // com.facebook.common.g.g
    public synchronized int a() {
        c();
        return this.f2215b;
    }

    @Override // com.facebook.common.g.g
    public synchronized byte a(int i) {
        c();
        boolean z = true;
        i.a(i >= 0);
        if (i >= this.f2215b) {
            z = false;
        }
        i.a(z);
        return this.f2214a.a().a(i);
    }

    @Override // com.facebook.common.g.g
    public synchronized int a(int i, byte[] bArr, int i2, int i3) {
        c();
        i.a(i + i3 <= this.f2215b);
        return this.f2214a.a().b(i, bArr, i2, i3);
    }

    @Override // com.facebook.common.g.g
    public synchronized boolean b() {
        return !a.a((a<?>) this.f2214a);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        a.c(this.f2214a);
        this.f2214a = null;
    }

    /* access modifiers changed from: package-private */
    public synchronized void c() {
        if (b()) {
            throw new g.a();
        }
    }
}
