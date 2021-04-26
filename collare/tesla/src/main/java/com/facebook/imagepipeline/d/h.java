package com.facebook.imagepipeline.d;

import android.graphics.Bitmap;
import android.os.SystemClock;
import com.facebook.common.d.i;
import com.facebook.common.d.j;
import com.facebook.common.d.l;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/* compiled from: CountingMemoryCache */
public class h<K, V> implements com.facebook.common.g.b, p<K, V> {

    /* renamed from: a  reason: collision with root package name */
    static final long f2051a = TimeUnit.MINUTES.toMillis(5);

    /* renamed from: b  reason: collision with root package name */
    final g<K, b<K, V>> f2052b;

    /* renamed from: c  reason: collision with root package name */
    final g<K, b<K, V>> f2053c;

    /* renamed from: d  reason: collision with root package name */
    final Map<Bitmap, Object> f2054d = new WeakHashMap();
    protected q e;
    private final v<V> f;
    private final a g;
    private final l<q> h;
    private long i;

    /* compiled from: CountingMemoryCache */
    public interface a {
    }

    /* compiled from: CountingMemoryCache */
    public interface c<K> {
        void a(K k, boolean z);
    }

    /* access modifiers changed from: package-private */
    /* compiled from: CountingMemoryCache */
    public static class b<K, V> {

        /* renamed from: a  reason: collision with root package name */
        public final K f2059a;

        /* renamed from: b  reason: collision with root package name */
        public final com.facebook.common.h.a<V> f2060b;

        /* renamed from: c  reason: collision with root package name */
        public int f2061c = 0;

        /* renamed from: d  reason: collision with root package name */
        public boolean f2062d = false;
        public final c<K> e;

        private b(K k, com.facebook.common.h.a<V> aVar, c<K> cVar) {
            this.f2059a = (K) i.a(k);
            this.f2060b = (com.facebook.common.h.a) i.a(com.facebook.common.h.a.b(aVar));
            this.e = cVar;
        }

        static <K, V> b<K, V> a(K k, com.facebook.common.h.a<V> aVar, c<K> cVar) {
            return new b<>(k, aVar, cVar);
        }
    }

    public h(v<V> vVar, a aVar, l<q> lVar) {
        this.f = vVar;
        this.f2052b = new g<>(a((v) vVar));
        this.f2053c = new g<>(a((v) vVar));
        this.g = aVar;
        this.h = lVar;
        this.e = this.h.b();
        this.i = SystemClock.uptimeMillis();
    }

    private v<b<K, V>> a(final v<V> vVar) {
        return new v<b<K, V>>() {
            /* class com.facebook.imagepipeline.d.h.AnonymousClass1 */

            @Override // com.facebook.imagepipeline.d.v
            public /* bridge */ /* synthetic */ int a(Object obj) {
                return a((b) ((b) obj));
            }

            public int a(b<K, V> bVar) {
                return vVar.a(bVar.f2060b.a());
            }
        };
    }

    @Override // com.facebook.imagepipeline.d.p
    public com.facebook.common.h.a<V> a(K k, com.facebook.common.h.a<V> aVar) {
        return a(k, aVar, null);
    }

    public com.facebook.common.h.a<V> a(K k, com.facebook.common.h.a<V> aVar, c<K> cVar) {
        b<K, V> b2;
        com.facebook.common.h.a<V> aVar2;
        com.facebook.common.h.a<V> aVar3;
        i.a(k);
        i.a(aVar);
        c();
        synchronized (this) {
            b2 = this.f2052b.b((Object) k);
            b<K, V> b3 = this.f2053c.b((Object) k);
            aVar2 = null;
            if (b3 != null) {
                f(b3);
                aVar3 = i(b3);
            } else {
                aVar3 = null;
            }
            if (b((Object) aVar.a())) {
                b<K, V> a2 = b.a(k, aVar, cVar);
                this.f2053c.a(k, a2);
                aVar2 = a((b) a2);
            }
        }
        com.facebook.common.h.a.c(aVar3);
        d(b2);
        d();
        return aVar2;
    }

    private synchronized boolean b(V v) {
        boolean z;
        int a2 = this.f.a(v);
        z = true;
        if (a2 > this.e.e || a() > this.e.f2069b - 1 || b() > this.e.f2068a - a2) {
            z = false;
        }
        return z;
    }

    @Override // com.facebook.imagepipeline.d.p
    public com.facebook.common.h.a<V> a(K k) {
        b<K, V> b2;
        com.facebook.common.h.a<V> a2;
        i.a(k);
        synchronized (this) {
            b2 = this.f2052b.b((Object) k);
            b<K, V> a3 = this.f2053c.a((Object) k);
            a2 = a3 != null ? a((b) a3) : null;
        }
        d(b2);
        c();
        d();
        return a2;
    }

    private synchronized com.facebook.common.h.a<V> a(final b<K, V> bVar) {
        g(bVar);
        return com.facebook.common.h.a.a(bVar.f2060b.a(), new com.facebook.common.h.c<V>() {
            /* class com.facebook.imagepipeline.d.h.AnonymousClass2 */

            @Override // com.facebook.common.h.c
            public void a(V v) {
                h.this.b((h) bVar);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(b<K, V> bVar) {
        boolean c2;
        com.facebook.common.h.a<V> i2;
        i.a(bVar);
        synchronized (this) {
            h(bVar);
            c2 = c(bVar);
            i2 = i(bVar);
        }
        com.facebook.common.h.a.c(i2);
        if (!c2) {
            bVar = null;
        }
        e(bVar);
        c();
        d();
    }

    private synchronized boolean c(b<K, V> bVar) {
        if (bVar.f2062d || bVar.f2061c != 0) {
            return false;
        }
        this.f2052b.a(bVar.f2059a, bVar);
        return true;
    }

    @Override // com.facebook.imagepipeline.d.p
    public int a(j<K> jVar) {
        ArrayList<b<K, V>> b2;
        ArrayList<b<K, V>> b3;
        synchronized (this) {
            b2 = this.f2052b.b((j) jVar);
            b3 = this.f2053c.b((j) jVar);
            c(b3);
        }
        a((ArrayList) b3);
        b((ArrayList) b2);
        c();
        d();
        return b3.size();
    }

    @Override // com.facebook.imagepipeline.d.p
    public synchronized boolean b(j<K> jVar) {
        return !this.f2053c.a((j) jVar).isEmpty();
    }

    private synchronized void c() {
        if (this.i + f2051a <= SystemClock.uptimeMillis()) {
            this.i = SystemClock.uptimeMillis();
            this.e = this.h.b();
        }
    }

    private void d() {
        ArrayList<b<K, V>> a2;
        synchronized (this) {
            a2 = a(Math.min(this.e.f2071d, this.e.f2069b - a()), Math.min(this.e.f2070c, this.e.f2068a - b()));
            c(a2);
        }
        a((ArrayList) a2);
        b((ArrayList) a2);
    }

    private synchronized ArrayList<b<K, V>> a(int i2, int i3) {
        int max = Math.max(i2, 0);
        int max2 = Math.max(i3, 0);
        if (this.f2052b.a() <= max && this.f2052b.b() <= max2) {
            return null;
        }
        ArrayList<b<K, V>> arrayList = new ArrayList<>();
        while (true) {
            if (this.f2052b.a() <= max && this.f2052b.b() <= max2) {
                return arrayList;
            }
            K c2 = this.f2052b.c();
            this.f2052b.b((Object) c2);
            arrayList.add(this.f2053c.b((Object) c2));
        }
    }

    private void a(ArrayList<b<K, V>> arrayList) {
        if (arrayList != null) {
            Iterator<b<K, V>> it = arrayList.iterator();
            while (it.hasNext()) {
                com.facebook.common.h.a.c(i(it.next()));
            }
        }
    }

    private void b(ArrayList<b<K, V>> arrayList) {
        if (arrayList != null) {
            Iterator<b<K, V>> it = arrayList.iterator();
            while (it.hasNext()) {
                d(it.next());
            }
        }
    }

    private static <K, V> void d(b<K, V> bVar) {
        if (bVar != null && bVar.e != null) {
            bVar.e.a(bVar.f2059a, false);
        }
    }

    private static <K, V> void e(b<K, V> bVar) {
        if (bVar != null && bVar.e != null) {
            bVar.e.a(bVar.f2059a, true);
        }
    }

    private synchronized void c(ArrayList<b<K, V>> arrayList) {
        if (arrayList != null) {
            Iterator<b<K, V>> it = arrayList.iterator();
            while (it.hasNext()) {
                f(it.next());
            }
        }
    }

    private synchronized void f(b<K, V> bVar) {
        i.a(bVar);
        i.b(!bVar.f2062d);
        bVar.f2062d = true;
    }

    private synchronized void g(b<K, V> bVar) {
        i.a(bVar);
        i.b(!bVar.f2062d);
        bVar.f2061c++;
    }

    private synchronized void h(b<K, V> bVar) {
        i.a(bVar);
        i.b(bVar.f2061c > 0);
        bVar.f2061c--;
    }

    private synchronized com.facebook.common.h.a<V> i(b<K, V> bVar) {
        i.a(bVar);
        return (!bVar.f2062d || bVar.f2061c != 0) ? null : bVar.f2060b;
    }

    public synchronized int a() {
        return this.f2053c.a() - this.f2052b.a();
    }

    public synchronized int b() {
        return this.f2053c.b() - this.f2052b.b();
    }
}
