package com.facebook.imagepipeline.j;

import com.facebook.common.e.a;
import java.io.Closeable;

/* compiled from: CloseableImage */
public abstract class b implements e, Closeable {
    public abstract int b();

    public abstract boolean c();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    public boolean e() {
        return false;
    }

    public g d() {
        return f.f2171a;
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.Object
    public void finalize() {
        if (!c()) {
            a.b("CloseableImage", "finalize: %s %x still open.", getClass().getSimpleName(), Integer.valueOf(System.identityHashCode(this)));
            try {
                close();
            } finally {
                super.finalize();
            }
        }
    }
}
