package com.google.protobuf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: MapField */
public class aa<K, V> implements ai {

    /* renamed from: b  reason: collision with root package name */
    private volatile boolean f4049b;

    /* renamed from: c  reason: collision with root package name */
    private volatile c f4050c;

    /* renamed from: d  reason: collision with root package name */
    private b<K, V> f4051d;
    private List<ac> e;
    private final a<K, V> f;

    /* access modifiers changed from: private */
    /* compiled from: MapField */
    public interface a<K, V> {
        ac a();

        ac a(K k, V v);

        void a(ac acVar, Map<K, V> map);
    }

    /* access modifiers changed from: private */
    /* compiled from: MapField */
    public enum c {
        MAP,
        LIST,
        BOTH
    }

    private ac a(K k, V v) {
        return this.f.a((Object) k, (Object) v);
    }

    private void a(ac acVar, Map<K, V> map) {
        this.f.a(acVar, (Map) map);
    }

    private List<ac> a(b<K, V> bVar) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<K, V> entry : bVar.entrySet()) {
            arrayList.add(a((Object) entry.getKey(), (Object) entry.getValue()));
        }
        return arrayList;
    }

    private b<K, V> a(List<ac> list) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (ac acVar : list) {
            a(acVar, (Map) linkedHashMap);
        }
        return new b<>(this, linkedHashMap);
    }

    public Map<K, V> a() {
        if (this.f4050c == c.LIST) {
            synchronized (this) {
                if (this.f4050c == c.LIST) {
                    this.f4051d = a(this.e);
                    this.f4050c = c.BOTH;
                }
            }
        }
        return Collections.unmodifiableMap(this.f4051d);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof aa)) {
            return false;
        }
        return ab.a((Map) a(), (Map) ((aa) obj).a());
    }

    public int hashCode() {
        return ab.a((Map) a());
    }

    /* access modifiers changed from: package-private */
    public List<ac> b() {
        if (this.f4050c == c.MAP) {
            synchronized (this) {
                if (this.f4050c == c.MAP) {
                    this.e = a(this.f4051d);
                    this.f4050c = c.BOTH;
                }
            }
        }
        return Collections.unmodifiableList(this.e);
    }

    /* access modifiers changed from: package-private */
    public List<ac> c() {
        if (this.f4050c != c.LIST) {
            if (this.f4050c == c.MAP) {
                this.e = a(this.f4051d);
            }
            this.f4051d = null;
            this.f4050c = c.LIST;
        }
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public ac d() {
        return this.f.a();
    }

    public boolean e() {
        return this.f4049b;
    }

    @Override // com.google.protobuf.ai
    public void f() {
        if (!e()) {
            throw new UnsupportedOperationException();
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: MapField */
    public static class b<K, V> implements Map<K, V> {

        /* renamed from: a  reason: collision with root package name */
        private final ai f4052a;

        /* renamed from: b  reason: collision with root package name */
        private final Map<K, V> f4053b;

        b(ai aiVar, Map<K, V> map) {
            this.f4052a = aiVar;
            this.f4053b = map;
        }

        public int size() {
            return this.f4053b.size();
        }

        public boolean isEmpty() {
            return this.f4053b.isEmpty();
        }

        public boolean containsKey(Object obj) {
            return this.f4053b.containsKey(obj);
        }

        public boolean containsValue(Object obj) {
            return this.f4053b.containsValue(obj);
        }

        @Override // java.util.Map
        public V get(Object obj) {
            return this.f4053b.get(obj);
        }

        @Override // java.util.Map
        public V put(K k, V v) {
            this.f4052a.f();
            u.a((Object) k);
            u.a((Object) v);
            return this.f4053b.put(k, v);
        }

        @Override // java.util.Map
        public V remove(Object obj) {
            this.f4052a.f();
            return this.f4053b.remove(obj);
        }

        @Override // java.util.Map
        public void putAll(Map<? extends K, ? extends V> map) {
            this.f4052a.f();
            for (Object obj : map.keySet()) {
                u.a(obj);
                u.a(map.get(obj));
            }
            this.f4053b.putAll(map);
        }

        public void clear() {
            this.f4052a.f();
            this.f4053b.clear();
        }

        @Override // java.util.Map
        public Set<K> keySet() {
            return new c(this.f4052a, this.f4053b.keySet());
        }

        @Override // java.util.Map
        public Collection<V> values() {
            return new a(this.f4052a, this.f4053b.values());
        }

        @Override // java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            return new c(this.f4052a, this.f4053b.entrySet());
        }

        public boolean equals(Object obj) {
            return this.f4053b.equals(obj);
        }

        public int hashCode() {
            return this.f4053b.hashCode();
        }

        public String toString() {
            return this.f4053b.toString();
        }

        /* compiled from: MapField */
        private static class a<E> implements Collection<E> {

            /* renamed from: a  reason: collision with root package name */
            private final ai f4054a;

            /* renamed from: b  reason: collision with root package name */
            private final Collection<E> f4055b;

            a(ai aiVar, Collection<E> collection) {
                this.f4054a = aiVar;
                this.f4055b = collection;
            }

            public int size() {
                return this.f4055b.size();
            }

            public boolean isEmpty() {
                return this.f4055b.isEmpty();
            }

            public boolean contains(Object obj) {
                return this.f4055b.contains(obj);
            }

            @Override // java.util.Collection, java.lang.Iterable
            public Iterator<E> iterator() {
                return new C0070b(this.f4054a, this.f4055b.iterator());
            }

            public Object[] toArray() {
                return this.f4055b.toArray();
            }

            @Override // java.util.Collection
            public <T> T[] toArray(T[] tArr) {
                return (T[]) this.f4055b.toArray(tArr);
            }

            @Override // java.util.Collection
            public boolean add(E e) {
                throw new UnsupportedOperationException();
            }

            public boolean remove(Object obj) {
                this.f4054a.f();
                return this.f4055b.remove(obj);
            }

            @Override // java.util.Collection
            public boolean containsAll(Collection<?> collection) {
                return this.f4055b.containsAll(collection);
            }

            @Override // java.util.Collection
            public boolean addAll(Collection<? extends E> collection) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.Collection
            public boolean removeAll(Collection<?> collection) {
                this.f4054a.f();
                return this.f4055b.removeAll(collection);
            }

            @Override // java.util.Collection
            public boolean retainAll(Collection<?> collection) {
                this.f4054a.f();
                return this.f4055b.retainAll(collection);
            }

            public void clear() {
                this.f4054a.f();
                this.f4055b.clear();
            }

            public boolean equals(Object obj) {
                return this.f4055b.equals(obj);
            }

            public int hashCode() {
                return this.f4055b.hashCode();
            }

            public String toString() {
                return this.f4055b.toString();
            }
        }

        /* access modifiers changed from: private */
        /* compiled from: MapField */
        public static class c<E> implements Set<E> {

            /* renamed from: a  reason: collision with root package name */
            private final ai f4058a;

            /* renamed from: b  reason: collision with root package name */
            private final Set<E> f4059b;

            c(ai aiVar, Set<E> set) {
                this.f4058a = aiVar;
                this.f4059b = set;
            }

            public int size() {
                return this.f4059b.size();
            }

            public boolean isEmpty() {
                return this.f4059b.isEmpty();
            }

            public boolean contains(Object obj) {
                return this.f4059b.contains(obj);
            }

            @Override // java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator<E> iterator() {
                return new C0070b(this.f4058a, this.f4059b.iterator());
            }

            public Object[] toArray() {
                return this.f4059b.toArray();
            }

            @Override // java.util.Collection, java.util.Set
            public <T> T[] toArray(T[] tArr) {
                return (T[]) this.f4059b.toArray(tArr);
            }

            @Override // java.util.Collection, java.util.Set
            public boolean add(E e) {
                this.f4058a.f();
                return this.f4059b.add(e);
            }

            public boolean remove(Object obj) {
                this.f4058a.f();
                return this.f4059b.remove(obj);
            }

            @Override // java.util.Collection, java.util.Set
            public boolean containsAll(Collection<?> collection) {
                return this.f4059b.containsAll(collection);
            }

            @Override // java.util.Collection, java.util.Set
            public boolean addAll(Collection<? extends E> collection) {
                this.f4058a.f();
                return this.f4059b.addAll(collection);
            }

            @Override // java.util.Collection, java.util.Set
            public boolean retainAll(Collection<?> collection) {
                this.f4058a.f();
                return this.f4059b.retainAll(collection);
            }

            @Override // java.util.Collection, java.util.Set
            public boolean removeAll(Collection<?> collection) {
                this.f4058a.f();
                return this.f4059b.removeAll(collection);
            }

            public void clear() {
                this.f4058a.f();
                this.f4059b.clear();
            }

            public boolean equals(Object obj) {
                return this.f4059b.equals(obj);
            }

            public int hashCode() {
                return this.f4059b.hashCode();
            }

            public String toString() {
                return this.f4059b.toString();
            }
        }

        /* renamed from: com.google.protobuf.aa$b$b  reason: collision with other inner class name */
        /* compiled from: MapField */
        private static class C0070b<E> implements Iterator<E> {

            /* renamed from: a  reason: collision with root package name */
            private final ai f4056a;

            /* renamed from: b  reason: collision with root package name */
            private final Iterator<E> f4057b;

            C0070b(ai aiVar, Iterator<E> it) {
                this.f4056a = aiVar;
                this.f4057b = it;
            }

            public boolean hasNext() {
                return this.f4057b.hasNext();
            }

            @Override // java.util.Iterator
            public E next() {
                return this.f4057b.next();
            }

            public void remove() {
                this.f4056a.f();
                this.f4057b.remove();
            }

            public boolean equals(Object obj) {
                return this.f4057b.equals(obj);
            }

            public int hashCode() {
                return this.f4057b.hashCode();
            }

            public String toString() {
                return this.f4057b.toString();
            }
        }
    }
}
