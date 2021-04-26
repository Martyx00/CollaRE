package com.facebook.common.h;

import com.facebook.common.d.i;
import java.util.IdentityHashMap;
import java.util.Map;

/* compiled from: SharedReference */
public class d<T> {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<Object, Integer> f1771a = new IdentityHashMap();

    /* renamed from: b  reason: collision with root package name */
    private T f1772b;

    /* renamed from: c  reason: collision with root package name */
    private int f1773c = 1;

    /* renamed from: d  reason: collision with root package name */
    private final c<T> f1774d;

    public d(T t, c<T> cVar) {
        this.f1772b = (T) i.a(t);
        this.f1774d = (c) i.a(cVar);
        a((Object) t);
    }

    private static void a(Object obj) {
        synchronized (f1771a) {
            Integer num = f1771a.get(obj);
            if (num == null) {
                f1771a.put(obj, 1);
            } else {
                f1771a.put(obj, Integer.valueOf(num.intValue() + 1));
            }
        }
    }

    private static void b(Object obj) {
        synchronized (f1771a) {
            Integer num = f1771a.get(obj);
            if (num == null) {
                com.facebook.common.e.a.d("SharedReference", "No entry in sLiveObjects for value of type %s", obj.getClass());
            } else if (num.intValue() == 1) {
                f1771a.remove(obj);
            } else {
                f1771a.put(obj, Integer.valueOf(num.intValue() - 1));
            }
        }
    }

    public synchronized T a() {
        return this.f1772b;
    }

    public synchronized boolean b() {
        return this.f1773c > 0;
    }

    public static boolean a(d<?> dVar) {
        return dVar != null && dVar.b();
    }

    public synchronized void c() {
        f();
        this.f1773c++;
    }

    public void d() {
        T t;
        if (e() == 0) {
            synchronized (this) {
                t = this.f1772b;
                this.f1772b = null;
            }
            this.f1774d.a(t);
            b(t);
        }
    }

    private synchronized int e() {
        f();
        i.a(this.f1773c > 0);
        this.f1773c--;
        return this.f1773c;
    }

    private void f() {
        if (!a((d<?>) this)) {
            throw new a();
        }
    }

    /* compiled from: SharedReference */
    public static class a extends RuntimeException {
        public a() {
            super("Null shared reference");
        }
    }
}
