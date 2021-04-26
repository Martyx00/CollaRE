package android.arch.a.b;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* compiled from: SafeIterableMap */
public class b<K, V> implements Iterable<Map.Entry<K, V>> {

    /* renamed from: a  reason: collision with root package name */
    private c<K, V> f44a;

    /* renamed from: b  reason: collision with root package name */
    private c<K, V> f45b;

    /* renamed from: c  reason: collision with root package name */
    private WeakHashMap<f<K, V>, Boolean> f46c = new WeakHashMap<>();

    /* renamed from: d  reason: collision with root package name */
    private int f47d = 0;

    /* access modifiers changed from: package-private */
    /* compiled from: SafeIterableMap */
    public interface f<K, V> {
        void a_(c<K, V> cVar);
    }

    /* access modifiers changed from: protected */
    public c<K, V> a(K k) {
        c<K, V> cVar = this.f44a;
        while (cVar != null && !cVar.f48a.equals(k)) {
            cVar = cVar.f50c;
        }
        return cVar;
    }

    public V a(K k, V v) {
        c<K, V> a2 = a((Object) k);
        if (a2 != null) {
            return a2.f49b;
        }
        b(k, v);
        return null;
    }

    /* access modifiers changed from: protected */
    public c<K, V> b(K k, V v) {
        c<K, V> cVar = new c<>(k, v);
        this.f47d++;
        c<K, V> cVar2 = this.f45b;
        if (cVar2 == null) {
            this.f44a = cVar;
            this.f45b = this.f44a;
            return cVar;
        }
        cVar2.f50c = cVar;
        cVar.f51d = cVar2;
        this.f45b = cVar;
        return cVar;
    }

    public V b(K k) {
        c<K, V> a2 = a((Object) k);
        if (a2 == null) {
            return null;
        }
        this.f47d--;
        if (!this.f46c.isEmpty()) {
            for (f<K, V> fVar : this.f46c.keySet()) {
                fVar.a_(a2);
            }
        }
        if (a2.f51d != null) {
            a2.f51d.f50c = a2.f50c;
        } else {
            this.f44a = a2.f50c;
        }
        if (a2.f50c != null) {
            a2.f50c.f51d = a2.f51d;
        } else {
            this.f45b = a2.f51d;
        }
        a2.f50c = null;
        a2.f51d = null;
        return a2.f49b;
    }

    public int a() {
        return this.f47d;
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<K, V>> iterator() {
        a aVar = new a(this.f44a, this.f45b);
        this.f46c.put(aVar, false);
        return aVar;
    }

    public Iterator<Map.Entry<K, V>> b() {
        C0001b bVar = new C0001b(this.f45b, this.f44a);
        this.f46c.put(bVar, false);
        return bVar;
    }

    public b<K, V>.d c() {
        b<K, V>.d dVar = new d();
        this.f46c.put(dVar, false);
        return dVar;
    }

    public Map.Entry<K, V> d() {
        return this.f44a;
    }

    public Map.Entry<K, V> e() {
        return this.f45b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (a() != bVar.a()) {
            return false;
        }
        Iterator<Map.Entry<K, V>> it = iterator();
        Iterator<Map.Entry<K, V>> it2 = bVar.iterator();
        while (it.hasNext() && it2.hasNext()) {
            Map.Entry<K, V> next = it.next();
            Map.Entry<K, V> next2 = it2.next();
            if ((next == null && next2 != null) || (next != null && !next.equals(next2))) {
                return false;
            }
        }
        if (it.hasNext() || it2.hasNext()) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<Map.Entry<K, V>> it = iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /* compiled from: SafeIterableMap */
    private static abstract class e<K, V> implements f<K, V>, Iterator<Map.Entry<K, V>> {

        /* renamed from: a  reason: collision with root package name */
        c<K, V> f55a;

        /* renamed from: b  reason: collision with root package name */
        c<K, V> f56b;

        /* access modifiers changed from: package-private */
        public abstract c<K, V> a(c<K, V> cVar);

        /* access modifiers changed from: package-private */
        public abstract c<K, V> b(c<K, V> cVar);

        e(c<K, V> cVar, c<K, V> cVar2) {
            this.f55a = cVar2;
            this.f56b = cVar;
        }

        public boolean hasNext() {
            return this.f56b != null;
        }

        @Override // android.arch.a.b.b.f
        public void a_(c<K, V> cVar) {
            if (this.f55a == cVar && cVar == this.f56b) {
                this.f56b = null;
                this.f55a = null;
            }
            c<K, V> cVar2 = this.f55a;
            if (cVar2 == cVar) {
                this.f55a = b(cVar2);
            }
            if (this.f56b == cVar) {
                this.f56b = b();
            }
        }

        private c<K, V> b() {
            c<K, V> cVar = this.f56b;
            c<K, V> cVar2 = this.f55a;
            if (cVar == cVar2 || cVar2 == null) {
                return null;
            }
            return a(cVar);
        }

        /* renamed from: a */
        public Map.Entry<K, V> next() {
            c<K, V> cVar = this.f56b;
            this.f56b = b();
            return cVar;
        }
    }

    /* access modifiers changed from: package-private */
    /* compiled from: SafeIterableMap */
    public static class a<K, V> extends e<K, V> {
        a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        /* access modifiers changed from: package-private */
        @Override // android.arch.a.b.b.e
        public c<K, V> a(c<K, V> cVar) {
            return cVar.f50c;
        }

        /* access modifiers changed from: package-private */
        @Override // android.arch.a.b.b.e
        public c<K, V> b(c<K, V> cVar) {
            return cVar.f51d;
        }
    }

    /* renamed from: android.arch.a.b.b$b  reason: collision with other inner class name */
    /* compiled from: SafeIterableMap */
    private static class C0001b<K, V> extends e<K, V> {
        C0001b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        /* access modifiers changed from: package-private */
        @Override // android.arch.a.b.b.e
        public c<K, V> a(c<K, V> cVar) {
            return cVar.f51d;
        }

        /* access modifiers changed from: package-private */
        @Override // android.arch.a.b.b.e
        public c<K, V> b(c<K, V> cVar) {
            return cVar.f50c;
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: SafeIterableMap */
    public class d implements f<K, V>, Iterator<Map.Entry<K, V>> {

        /* renamed from: b  reason: collision with root package name */
        private c<K, V> f53b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f54c;

        private d() {
            this.f54c = true;
        }

        @Override // android.arch.a.b.b.f
        public void a_(c<K, V> cVar) {
            c<K, V> cVar2 = this.f53b;
            if (cVar == cVar2) {
                this.f53b = cVar2.f51d;
                this.f54c = this.f53b == null;
            }
        }

        public boolean hasNext() {
            if (!this.f54c) {
                c<K, V> cVar = this.f53b;
                if (cVar == null || cVar.f50c == null) {
                    return false;
                }
                return true;
            } else if (b.this.f44a != null) {
                return true;
            } else {
                return false;
            }
        }

        /* renamed from: a */
        public Map.Entry<K, V> next() {
            if (this.f54c) {
                this.f54c = false;
                this.f53b = b.this.f44a;
            } else {
                c<K, V> cVar = this.f53b;
                this.f53b = cVar != null ? cVar.f50c : null;
            }
            return this.f53b;
        }
    }

    /* access modifiers changed from: package-private */
    /* compiled from: SafeIterableMap */
    public static class c<K, V> implements Map.Entry<K, V> {

        /* renamed from: a  reason: collision with root package name */
        final K f48a;

        /* renamed from: b  reason: collision with root package name */
        final V f49b;

        /* renamed from: c  reason: collision with root package name */
        c<K, V> f50c;

        /* renamed from: d  reason: collision with root package name */
        c<K, V> f51d;

        c(K k, V v) {
            this.f48a = k;
            this.f49b = v;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f48a;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f49b;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return ((Object) this.f48a) + "=" + ((Object) this.f49b);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            if (!this.f48a.equals(cVar.f48a) || !this.f49b.equals(cVar.f49b)) {
                return false;
            }
            return true;
        }
    }
}
