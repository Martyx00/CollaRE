package android.support.v4.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* access modifiers changed from: package-private */
/* compiled from: MapCollections */
public abstract class h<K, V> {

    /* renamed from: b  reason: collision with root package name */
    h<K, V>.b f653b;

    /* renamed from: c  reason: collision with root package name */
    h<K, V>.c f654c;

    /* renamed from: d  reason: collision with root package name */
    h<K, V>.e f655d;

    /* access modifiers changed from: protected */
    public abstract int a();

    /* access modifiers changed from: protected */
    public abstract int a(Object obj);

    /* access modifiers changed from: protected */
    public abstract Object a(int i, int i2);

    /* access modifiers changed from: protected */
    public abstract V a(int i, V v);

    /* access modifiers changed from: protected */
    public abstract void a(int i);

    /* access modifiers changed from: protected */
    public abstract void a(K k, V v);

    /* access modifiers changed from: protected */
    public abstract int b(Object obj);

    /* access modifiers changed from: protected */
    public abstract Map<K, V> b();

    /* access modifiers changed from: protected */
    public abstract void c();

    h() {
    }

    /* compiled from: MapCollections */
    final class a<T> implements Iterator<T> {

        /* renamed from: a  reason: collision with root package name */
        final int f656a;

        /* renamed from: b  reason: collision with root package name */
        int f657b;

        /* renamed from: c  reason: collision with root package name */
        int f658c;

        /* renamed from: d  reason: collision with root package name */
        boolean f659d = false;

        a(int i) {
            this.f656a = i;
            this.f657b = h.this.a();
        }

        public boolean hasNext() {
            return this.f658c < this.f657b;
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                T t = (T) h.this.a(this.f658c, this.f656a);
                this.f658c++;
                this.f659d = true;
                return t;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            if (this.f659d) {
                this.f658c--;
                this.f657b--;
                this.f659d = false;
                h.this.a(this.f658c);
                return;
            }
            throw new IllegalStateException();
        }
    }

    /* compiled from: MapCollections */
    final class d implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V> {

        /* renamed from: a  reason: collision with root package name */
        int f662a;

        /* renamed from: b  reason: collision with root package name */
        int f663b;

        /* renamed from: c  reason: collision with root package name */
        boolean f664c = false;

        d() {
            this.f662a = h.this.a() - 1;
            this.f663b = -1;
        }

        public boolean hasNext() {
            return this.f663b < this.f662a;
        }

        /* renamed from: a */
        public Map.Entry<K, V> next() {
            if (hasNext()) {
                this.f663b++;
                this.f664c = true;
                return this;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            if (this.f664c) {
                h.this.a(this.f663b);
                this.f663b--;
                this.f662a--;
                this.f664c = false;
                return;
            }
            throw new IllegalStateException();
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            if (this.f664c) {
                return (K) h.this.a(this.f663b, 0);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            if (this.f664c) {
                return (V) h.this.a(this.f663b, 1);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            if (this.f664c) {
                return (V) h.this.a(this.f663b, v);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public boolean equals(Object obj) {
            if (!this.f664c) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            } else if (!(obj instanceof Map.Entry)) {
                return false;
            } else {
                Map.Entry entry = (Map.Entry) obj;
                if (!c.a(entry.getKey(), h.this.a(this.f663b, 0)) || !c.a(entry.getValue(), h.this.a(this.f663b, 1))) {
                    return false;
                }
                return true;
            }
        }

        public int hashCode() {
            int i;
            if (this.f664c) {
                int i2 = 0;
                Object a2 = h.this.a(this.f663b, 0);
                Object a3 = h.this.a(this.f663b, 1);
                if (a2 == null) {
                    i = 0;
                } else {
                    i = a2.hashCode();
                }
                if (a3 != null) {
                    i2 = a3.hashCode();
                }
                return i ^ i2;
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public String toString() {
            return getKey() + "=" + getValue();
        }
    }

    /* access modifiers changed from: package-private */
    /* compiled from: MapCollections */
    public final class b implements Set<Map.Entry<K, V>> {
        b() {
        }

        /* renamed from: a */
        public boolean add(Map.Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: android.support.v4.util.h */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection, java.util.Set
        public boolean addAll(Collection<? extends Map.Entry<K, V>> collection) {
            int a2 = h.this.a();
            Iterator<? extends Map.Entry<K, V>> it = collection.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                h.this.a(entry.getKey(), entry.getValue());
            }
            return a2 != h.this.a();
        }

        public void clear() {
            h.this.c();
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            int a2 = h.this.a(entry.getKey());
            if (a2 < 0) {
                return false;
            }
            return c.a(h.this.a(a2, 1), entry.getValue());
        }

        @Override // java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> collection) {
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        public boolean isEmpty() {
            return h.this.a() == 0;
        }

        @Override // java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return new d();
        }

        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        public int size() {
            return h.this.a();
        }

        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            throw new UnsupportedOperationException();
        }

        public boolean equals(Object obj) {
            return h.a((Set) this, obj);
        }

        public int hashCode() {
            int i;
            int i2;
            int i3 = 0;
            for (int a2 = h.this.a() - 1; a2 >= 0; a2--) {
                Object a3 = h.this.a(a2, 0);
                Object a4 = h.this.a(a2, 1);
                if (a3 == null) {
                    i = 0;
                } else {
                    i = a3.hashCode();
                }
                if (a4 == null) {
                    i2 = 0;
                } else {
                    i2 = a4.hashCode();
                }
                i3 += i ^ i2;
            }
            return i3;
        }
    }

    /* access modifiers changed from: package-private */
    /* compiled from: MapCollections */
    public final class c implements Set<K> {
        c() {
        }

        @Override // java.util.Collection, java.util.Set
        public boolean add(K k) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection, java.util.Set
        public boolean addAll(Collection<? extends K> collection) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            h.this.c();
        }

        public boolean contains(Object obj) {
            return h.this.a(obj) >= 0;
        }

        @Override // java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> collection) {
            return h.a((Map) h.this.b(), collection);
        }

        public boolean isEmpty() {
            return h.this.a() == 0;
        }

        @Override // java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<K> iterator() {
            return new a(0);
        }

        public boolean remove(Object obj) {
            int a2 = h.this.a(obj);
            if (a2 < 0) {
                return false;
            }
            h.this.a(a2);
            return true;
        }

        @Override // java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            return h.b(h.this.b(), collection);
        }

        @Override // java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            return h.c(h.this.b(), collection);
        }

        public int size() {
            return h.this.a();
        }

        public Object[] toArray() {
            return h.this.b(0);
        }

        @Override // java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            return (T[]) h.this.a(tArr, 0);
        }

        public boolean equals(Object obj) {
            return h.a((Set) this, obj);
        }

        public int hashCode() {
            int i;
            int i2 = 0;
            for (int a2 = h.this.a() - 1; a2 >= 0; a2--) {
                Object a3 = h.this.a(a2, 0);
                if (a3 == null) {
                    i = 0;
                } else {
                    i = a3.hashCode();
                }
                i2 += i;
            }
            return i2;
        }
    }

    /* access modifiers changed from: package-private */
    /* compiled from: MapCollections */
    public final class e implements Collection<V> {
        e() {
        }

        @Override // java.util.Collection
        public boolean add(V v) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean addAll(Collection<? extends V> collection) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            h.this.c();
        }

        public boolean contains(Object obj) {
            return h.this.b(obj) >= 0;
        }

        @Override // java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        public boolean isEmpty() {
            return h.this.a() == 0;
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new a(1);
        }

        public boolean remove(Object obj) {
            int b2 = h.this.b(obj);
            if (b2 < 0) {
                return false;
            }
            h.this.a(b2);
            return true;
        }

        @Override // java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            int a2 = h.this.a();
            int i = 0;
            boolean z = false;
            while (i < a2) {
                if (collection.contains(h.this.a(i, 1))) {
                    h.this.a(i);
                    i--;
                    a2--;
                    z = true;
                }
                i++;
            }
            return z;
        }

        @Override // java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            int a2 = h.this.a();
            int i = 0;
            boolean z = false;
            while (i < a2) {
                if (!collection.contains(h.this.a(i, 1))) {
                    h.this.a(i);
                    i--;
                    a2--;
                    z = true;
                }
                i++;
            }
            return z;
        }

        public int size() {
            return h.this.a();
        }

        public Object[] toArray() {
            return h.this.b(1);
        }

        @Override // java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) h.this.a(tArr, 1);
        }
    }

    public static <K, V> boolean a(Map<K, V> map, Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!map.containsKey(it.next())) {
                return false;
            }
        }
        return true;
    }

    public static <K, V> boolean b(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            map.remove(it.next());
        }
        return size != map.size();
    }

    public static <K, V> boolean c(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        Iterator<K> it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        return size != map.size();
    }

    public Object[] b(int i) {
        int a2 = a();
        Object[] objArr = new Object[a2];
        for (int i2 = 0; i2 < a2; i2++) {
            objArr[i2] = a(i2, i);
        }
        return objArr;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v9, resolved type: T[] */
    /* JADX WARN: Multi-variable type inference failed */
    public <T> T[] a(T[] tArr, int i) {
        int a2 = a();
        if (tArr.length < a2) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), a2));
        }
        for (int i2 = 0; i2 < a2; i2++) {
            tArr[i2] = a(i2, i);
        }
        if (tArr.length > a2) {
            tArr[a2] = null;
        }
        return tArr;
    }

    public static <T> boolean a(Set<T> set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set2 = (Set) obj;
        try {
            if (set.size() != set2.size() || !set.containsAll(set2)) {
                return false;
            }
            return true;
        } catch (NullPointerException unused) {
            return false;
        } catch (ClassCastException unused2) {
            return false;
        }
    }

    public Set<Map.Entry<K, V>> d() {
        if (this.f653b == null) {
            this.f653b = new b();
        }
        return this.f653b;
    }

    public Set<K> e() {
        if (this.f654c == null) {
            this.f654c = new c();
        }
        return this.f654c;
    }

    public Collection<V> f() {
        if (this.f655d == null) {
            this.f655d = new e();
        }
        return this.f655d;
    }
}
