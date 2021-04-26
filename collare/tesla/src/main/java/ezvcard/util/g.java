package ezvcard.util;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/* compiled from: ListMultimap */
public class g<K, V> implements Iterable<Map.Entry<K, List<V>>> {

    /* renamed from: a  reason: collision with root package name */
    private final Map<K, List<V>> f5850a;

    /* access modifiers changed from: protected */
    public K a(K k) {
        return k;
    }

    public g() {
        this(new LinkedHashMap());
    }

    public g(g<K, V> gVar) {
        this(a((Map) gVar.f5850a));
    }

    private static <K, V> Map<K, List<V>> a(Map<K, List<V>> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(map.size());
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            linkedHashMap.put(entry.getKey(), new ArrayList(entry.getValue()));
        }
        return linkedHashMap;
    }

    public g(Map<K, List<V>> map) {
        this.f5850a = map;
    }

    public void a(K k, V v) {
        K a2 = a((Object) k);
        List<V> list = this.f5850a.get(a2);
        if (list == null) {
            list = new ArrayList<>();
            this.f5850a.put(a2, list);
        }
        list.add(v);
    }

    public void a(K k, Collection<V> collection) {
        if (!collection.isEmpty()) {
            K a2 = a((Object) k);
            List<V> list = this.f5850a.get(a2);
            if (list == null) {
                list = new ArrayList<>();
                this.f5850a.put(a2, list);
            }
            list.addAll(collection);
        }
    }

    public List<V> b(K k) {
        K a2 = a((Object) k);
        List<V> list = this.f5850a.get(a2);
        if (list == null) {
            list = new ArrayList<>(0);
        }
        return new a(a2, list, null);
    }

    public V c(K k) {
        List<V> list = this.f5850a.get(a((Object) k));
        if (list == null) {
            return null;
        }
        return list.get(0);
    }

    public List<V> d(K k) {
        List<V> remove = this.f5850a.remove(a((Object) k));
        if (remove == null) {
            return Collections.emptyList();
        }
        List<V> unmodifiableList = Collections.unmodifiableList(new ArrayList(remove));
        remove.clear();
        return unmodifiableList;
    }

    public List<V> b(K k, V v) {
        List<V> d2 = d(k);
        if (v != null) {
            a((Object) k, (Object) v);
        }
        return d2;
    }

    public List<V> r() {
        ArrayList arrayList = new ArrayList();
        for (List<V> list : this.f5850a.values()) {
            arrayList.addAll(list);
        }
        return Collections.unmodifiableList(arrayList);
    }

    public int s() {
        int i = 0;
        for (List<V> list : this.f5850a.values()) {
            i += list.size();
        }
        return i;
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<K, List<V>>> iterator() {
        final Iterator<Map.Entry<K, List<V>>> it = this.f5850a.entrySet().iterator();
        return new Iterator<Map.Entry<K, List<V>>>() {
            /* class ezvcard.util.g.AnonymousClass1 */

            public boolean hasNext() {
                return it.hasNext();
            }

            /* renamed from: a */
            public Map.Entry<K, List<V>> next() {
                final Map.Entry entry = (Map.Entry) it.next();
                return new Map.Entry<K, List<V>>() {
                    /* class ezvcard.util.g.AnonymousClass1.AnonymousClass1 */

                    @Override // java.util.Map.Entry
                    public K getKey() {
                        return (K) entry.getKey();
                    }

                    /* renamed from: a */
                    public List<V> getValue() {
                        return Collections.unmodifiableList((List) entry.getValue());
                    }

                    /* renamed from: a */
                    public List<V> setValue(List<V> list) {
                        throw new UnsupportedOperationException();
                    }
                };
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public String toString() {
        return this.f5850a.toString();
    }

    public int hashCode() {
        return this.f5850a.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return this.f5850a.equals(((g) obj).f5850a);
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* compiled from: ListMultimap */
    public class a extends AbstractCollection<V> implements List<V> {

        /* renamed from: a  reason: collision with root package name */
        final K f5855a;

        /* renamed from: b  reason: collision with root package name */
        List<V> f5856b;

        /* renamed from: c  reason: collision with root package name */
        final g<K, V>.a f5857c;

        /* renamed from: d  reason: collision with root package name */
        final List<V> f5858d;

        a(K k, List<V> list, g<K, V>.a aVar) {
            List<V> list2;
            this.f5855a = k;
            this.f5856b = list;
            this.f5857c = aVar;
            if (aVar == null) {
                list2 = null;
            } else {
                list2 = aVar.e();
            }
            this.f5858d = list2;
        }

        @Override // java.util.List
        public boolean addAll(int i, Collection<? extends V> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean addAll = e().addAll(i, collection);
            if (addAll && size == 0) {
                d();
            }
            return addAll;
        }

        @Override // java.util.List
        public V get(int i) {
            a();
            return e().get(i);
        }

        @Override // java.util.List
        public V set(int i, V v) {
            a();
            return e().set(i, v);
        }

        @Override // java.util.List
        public void add(int i, V v) {
            a();
            boolean isEmpty = e().isEmpty();
            e().add(i, v);
            if (isEmpty) {
                d();
            }
        }

        @Override // java.util.List
        public V remove(int i) {
            a();
            V remove = e().remove(i);
            b();
            return remove;
        }

        public int indexOf(Object obj) {
            a();
            return e().indexOf(obj);
        }

        public int lastIndexOf(Object obj) {
            a();
            return e().lastIndexOf(obj);
        }

        @Override // java.util.List
        public ListIterator<V> listIterator() {
            a();
            return new C0160a();
        }

        @Override // java.util.List
        public ListIterator<V> listIterator(int i) {
            a();
            return new C0160a(i);
        }

        @Override // java.util.List
        public List<V> subList(int i, int i2) {
            a();
            return new a(c(), e().subList(i, i2), f() == null ? this : f());
        }

        /* access modifiers changed from: package-private */
        public void a() {
            List<V> list;
            g<K, V>.a aVar = this.f5857c;
            if (aVar != null) {
                aVar.a();
                if (this.f5857c.e() != this.f5858d) {
                    throw new ConcurrentModificationException();
                }
            } else if (this.f5856b.isEmpty() && (list = (List) g.this.f5850a.get(this.f5855a)) != null) {
                this.f5856b = list;
            }
        }

        /* access modifiers changed from: package-private */
        public void b() {
            g<K, V>.a aVar = this.f5857c;
            if (aVar != null) {
                aVar.b();
            } else if (this.f5856b.isEmpty()) {
                g.this.f5850a.remove(this.f5855a);
            }
        }

        /* access modifiers changed from: package-private */
        public K c() {
            return this.f5855a;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: java.util.Map */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public void d() {
            g<K, V>.a aVar = this.f5857c;
            if (aVar != null) {
                aVar.d();
            } else {
                g.this.f5850a.put(this.f5855a, this.f5856b);
            }
        }

        public int size() {
            a();
            return this.f5856b.size();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            a();
            return this.f5856b.equals(obj);
        }

        public int hashCode() {
            a();
            return this.f5856b.hashCode();
        }

        public String toString() {
            a();
            return this.f5856b.toString();
        }

        /* access modifiers changed from: package-private */
        public List<V> e() {
            return this.f5856b;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            a();
            return new C0160a();
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public boolean add(V v) {
            a();
            boolean isEmpty = this.f5856b.isEmpty();
            boolean add = this.f5856b.add(v);
            if (add && isEmpty) {
                d();
            }
            return add;
        }

        /* access modifiers changed from: package-private */
        public g<K, V>.a f() {
            return this.f5857c;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public boolean addAll(Collection<? extends V> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean addAll = this.f5856b.addAll(collection);
            if (addAll && size == 0) {
                d();
            }
            return addAll;
        }

        public boolean contains(Object obj) {
            a();
            return this.f5856b.contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            a();
            return this.f5856b.containsAll(collection);
        }

        public void clear() {
            if (size() != 0) {
                this.f5856b.clear();
                b();
            }
        }

        @Override // java.util.List
        public boolean remove(Object obj) {
            a();
            boolean remove = this.f5856b.remove(obj);
            if (remove) {
                b();
            }
            return remove;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            a();
            boolean removeAll = this.f5856b.removeAll(collection);
            if (removeAll) {
                b();
            }
            return removeAll;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            a();
            boolean retainAll = this.f5856b.retainAll(collection);
            if (retainAll) {
                b();
            }
            return retainAll;
        }

        /* renamed from: ezvcard.util.g$a$a  reason: collision with other inner class name */
        /* compiled from: ListMultimap */
        private class C0160a implements ListIterator<V> {

            /* renamed from: a  reason: collision with root package name */
            final ListIterator<V> f5859a;

            /* renamed from: b  reason: collision with root package name */
            final List<V> f5860b = a.this.f5856b;

            C0160a() {
                this.f5859a = a.this.f5856b.listIterator();
            }

            public C0160a(int i) {
                this.f5859a = a.this.f5856b.listIterator(i);
            }

            public boolean hasPrevious() {
                return b().hasPrevious();
            }

            @Override // java.util.ListIterator
            public V previous() {
                return b().previous();
            }

            public int nextIndex() {
                return b().nextIndex();
            }

            public int previousIndex() {
                return b().previousIndex();
            }

            @Override // java.util.ListIterator
            public void set(V v) {
                b().set(v);
            }

            @Override // java.util.ListIterator
            public void add(V v) {
                boolean isEmpty = a.this.isEmpty();
                b().add(v);
                if (isEmpty) {
                    a.this.d();
                }
            }

            /* access modifiers changed from: package-private */
            public void a() {
                a.this.a();
                if (a.this.f5856b != this.f5860b) {
                    throw new ConcurrentModificationException();
                }
            }

            public boolean hasNext() {
                a();
                return this.f5859a.hasNext();
            }

            @Override // java.util.Iterator, java.util.ListIterator
            public V next() {
                a();
                return this.f5859a.next();
            }

            public void remove() {
                this.f5859a.remove();
                a.this.b();
            }

            /* access modifiers changed from: package-private */
            public ListIterator<V> b() {
                a();
                return this.f5859a;
            }
        }
    }
}
