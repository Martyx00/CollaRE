package com.facebook.common.h;

import com.facebook.common.d.b;
import com.facebook.common.d.i;
import java.io.Closeable;
import java.io.IOException;

/* compiled from: CloseableReference */
public final class a<T> implements Closeable, Cloneable {

    /* renamed from: a  reason: collision with root package name */
    private static Class<a> f1764a = a.class;

    /* renamed from: d  reason: collision with root package name */
    private static final c<Closeable> f1765d = new c<Closeable>() {
        /* class com.facebook.common.h.a.AnonymousClass1 */

        public void a(Closeable closeable) {
            try {
                b.a(closeable, true);
            } catch (IOException unused) {
            }
        }
    };

    /* renamed from: b  reason: collision with root package name */
    private boolean f1766b = false;

    /* renamed from: c  reason: collision with root package name */
    private final d<T> f1767c;

    private a(d<T> dVar) {
        this.f1767c = (d) i.a(dVar);
        dVar.c();
    }

    private a(T t, c<T> cVar) {
        this.f1767c = new d<>(t, cVar);
    }

    public static <T extends Closeable> a<T> a(T t) {
        if (t == null) {
            return null;
        }
        return new a<>(t, f1765d);
    }

    public static <T> a<T> a(T t, c<T> cVar) {
        if (t == null) {
            return null;
        }
        return new a<>(t, cVar);
    }

    public synchronized T a() {
        i.b(!this.f1766b);
        return this.f1767c.a();
    }

    /* renamed from: b */
    public synchronized a<T> clone() {
        i.b(d());
        return new a<>(this.f1767c);
    }

    public synchronized a<T> c() {
        if (!d()) {
            return null;
        }
        return clone();
    }

    public synchronized boolean d() {
        return !this.f1766b;
    }

    public int e() {
        if (d()) {
            return System.identityHashCode(this.f1767c.a());
        }
        return 0;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this) {
            if (!this.f1766b) {
                this.f1766b = true;
                this.f1767c.d();
            }
        }
    }

    public static boolean a(a<?> aVar) {
        return aVar != null && aVar.d();
    }

    public static <T> a<T> b(a<T> aVar) {
        if (aVar != null) {
            return aVar.c();
        }
        return null;
    }

    public static void c(a<?> aVar) {
        if (aVar != null) {
            aVar.close();
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.Object
    public void finalize() {
        try {
            synchronized (this) {
                if (!this.f1766b) {
                    com.facebook.common.e.a.b((Class<?>) f1764a, "Finalized without closing: %x %x (type = %s)", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(System.identityHashCode(this.f1767c)), this.f1767c.a().getClass().getName());
                    close();
                    super.finalize();
                }
            }
        } finally {
            super.finalize();
        }
    }
}
