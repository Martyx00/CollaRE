package com.facebook.b.a;

import com.facebook.b.a.a;

/* compiled from: NoOpCacheErrorLogger */
public class g implements a {

    /* renamed from: a  reason: collision with root package name */
    private static g f1648a;

    @Override // com.facebook.b.a.a
    public void a(a.EnumC0036a aVar, Class<?> cls, String str, Throwable th) {
    }

    private g() {
    }

    public static synchronized g a() {
        g gVar;
        synchronized (g.class) {
            if (f1648a == null) {
                f1648a = new g();
            }
            gVar = f1648a;
        }
        return gVar;
    }
}
