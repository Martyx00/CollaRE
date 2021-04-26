package com.facebook.common.a;

/* compiled from: NoOpDiskTrimmableRegistry */
public class c implements b {

    /* renamed from: a  reason: collision with root package name */
    private static c f1730a;

    @Override // com.facebook.common.a.b
    public void a(a aVar) {
    }

    private c() {
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (f1730a == null) {
                f1730a = new c();
            }
            cVar = f1730a;
        }
        return cVar;
    }
}
