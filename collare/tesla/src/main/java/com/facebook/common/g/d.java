package com.facebook.common.g;

/* compiled from: NoOpMemoryTrimmableRegistry */
public class d implements c {

    /* renamed from: a  reason: collision with root package name */
    private static d f1754a;

    @Override // com.facebook.common.g.c
    public void a(b bVar) {
    }

    public static synchronized d a() {
        d dVar;
        synchronized (d.class) {
            if (f1754a == null) {
                f1754a = new d();
            }
            dVar = f1754a;
        }
        return dVar;
    }
}
