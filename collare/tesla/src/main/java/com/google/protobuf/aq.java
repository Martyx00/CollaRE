package com.google.protobuf;

import com.google.protobuf.r;
import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* access modifiers changed from: package-private */
/* compiled from: SmallSortedMap */
public class aq<K extends Comparable<K>, V> extends AbstractMap<K, V> {

    /* renamed from: a  reason: collision with root package name */
    private final int f4094a;

    /* renamed from: b  reason: collision with root package name */
    private List<aq<K, V>.b> f4095b;

    /* renamed from: c  reason: collision with root package name */
    private Map<K, V> f4096c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f4097d;
    private volatile aq<K, V>.d e;

    static <FieldDescriptorType extends r.a<FieldDescriptorType>> aq<FieldDescriptorType, Object> a(int i) {
        return new aq<FieldDescriptorType, Object>(i) {
            /* class com.google.protobuf.aq.AnonymousClass1 */

            @Override // java.util.AbstractMap, java.util.Map, com.google.protobuf.aq
            public /* synthetic */ Object put(Object obj, Object obj2) {
                return aq.super.put((r.a) obj, obj2);
            }

            @Override // com.google.protobuf.aq
            public void a() {
                if (!b()) {
                    for (int i = 0; i < c(); i++) {
                        Map.Entry<K, V> b2 = b(i);
                        if (((r.a) b2.getKey()).p()) {
                            b2.setValue((V) Collections.unmodifiableList(b2.getValue()));
                        }
                    }
                    for (Map.Entry<K, V> entry : e()) {
                        if (((r.a) entry.getKey()).p()) {
                            entry.setValue((V) Collections.unmodifiableList(entry.getValue()));
                        }
                    }
                }
                aq.super.a();
            }
        };
    }

    private aq(int i) {
        this.f4094a = i;
        this.f4095b = Collections.emptyList();
        this.f4096c = Collections.emptyMap();
    }

    public void a() {
        Map<K, V> map;
        if (!this.f4097d) {
            if (this.f4096c.isEmpty()) {
                map = Collections.emptyMap();
            } else {
                map = Collections.unmodifiableMap(this.f4096c);
            }
            this.f4096c = map;
            this.f4097d = true;
        }
    }

    public boolean b() {
        return this.f4097d;
    }

    public int c() {
        return this.f4095b.size();
    }

    public Map.Entry<K, V> b(int i) {
        return this.f4095b.get(i);
    }

    public int d() {
        return this.f4096c.size();
    }

    public Iterable<Map.Entry<K, V>> e() {
        if (this.f4096c.isEmpty()) {
            return a.a();
        }
        return this.f4096c.entrySet();
    }

    public int size() {
        return this.f4095b.size() + this.f4096c.size();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.google.protobuf.aq<K extends java.lang.Comparable<K>, V> */
    /* JADX WARN: Multi-variable type inference failed */
    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return a(comparable) >= 0 || this.f4096c.containsKey(comparable);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.google.protobuf.aq<K extends java.lang.Comparable<K>, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int a2 = a(comparable);
        return a2 >= 0 ? (V) this.f4095b.get(a2).getValue() : this.f4096c.get(comparable);
    }

    /* renamed from: a */
    public V put(K k, V v) {
        f();
        int a2 = a(k);
        if (a2 >= 0) {
            return (V) this.f4095b.get(a2).setValue(v);
        }
        h();
        int i = -(a2 + 1);
        if (i >= this.f4094a) {
            return g().put(k, v);
        }
        int size = this.f4095b.size();
        int i2 = this.f4094a;
        if (size == i2) {
            aq<K, V>.b remove = this.f4095b.remove(i2 - 1);
            g().put((K) remove.getKey(), (V) remove.getValue());
        }
        this.f4095b.add(i, new b(k, v));
        return null;
    }

    public void clear() {
        f();
        if (!this.f4095b.isEmpty()) {
            this.f4095b.clear();
        }
        if (!this.f4096c.isEmpty()) {
            this.f4096c.clear();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.google.protobuf.aq<K extends java.lang.Comparable<K>, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        f();
        Comparable comparable = (Comparable) obj;
        int a2 = a(comparable);
        if (a2 >= 0) {
            return (V) c(a2);
        }
        if (this.f4096c.isEmpty()) {
            return null;
        }
        return this.f4096c.remove(comparable);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private V c(int i) {
        f();
        V v = (V) this.f4095b.remove(i).getValue();
        if (!this.f4096c.isEmpty()) {
            Iterator<Map.Entry<K, V>> it = g().entrySet().iterator();
            this.f4095b.add(new b(this, it.next()));
            it.remove();
        }
        return v;
    }

    private int a(K k) {
        int size = this.f4095b.size() - 1;
        if (size >= 0) {
            int compareTo = k.compareTo(this.f4095b.get(size).getKey());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) / 2;
            int compareTo2 = k.compareTo(this.f4095b.get(i2).getKey());
            if (compareTo2 < 0) {
                size = i2 - 1;
            } else if (compareTo2 <= 0) {
                return i2;
            } else {
                i = i2 + 1;
            }
        }
        return -(i + 1);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.e == null) {
            this.e = new d();
        }
        return this.e;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void f() {
        if (this.f4097d) {
            throw new UnsupportedOperationException();
        }
    }

    private SortedMap<K, V> g() {
        f();
        if (this.f4096c.isEmpty() && !(this.f4096c instanceof TreeMap)) {
            this.f4096c = new TreeMap();
        }
        return (SortedMap) this.f4096c;
    }

    private void h() {
        f();
        if (this.f4095b.isEmpty() && !(this.f4095b instanceof ArrayList)) {
            this.f4095b = new ArrayList(this.f4094a);
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: SmallSortedMap */
    public class b implements Comparable<aq<K, V>.b>, Map.Entry<K, V> {

        /* renamed from: b  reason: collision with root package name */
        private final K f4101b;

        /* renamed from: c  reason: collision with root package name */
        private V f4102c;

        b(aq aqVar, Map.Entry<K, V> entry) {
            this(entry.getKey(), entry.getValue());
        }

        b(K k, V v) {
            this.f4101b = k;
            this.f4102c = v;
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [K, K extends java.lang.Comparable<K>] */
        /* renamed from: a */
        public K getKey() {
            return this.f4101b;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f4102c;
        }

        /* renamed from: a */
        public int compareTo(aq<K, V>.b bVar) {
            return getKey().compareTo(bVar.getKey());
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            aq.this.f();
            V v2 = this.f4102c;
            this.f4102c = v;
            return v2;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            if (!a(this.f4101b, entry.getKey()) || !a(this.f4102c, entry.getValue())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            K k = this.f4101b;
            int i = 0;
            int hashCode = k == null ? 0 : k.hashCode();
            V v = this.f4102c;
            if (v != null) {
                i = v.hashCode();
            }
            return hashCode ^ i;
        }

        public String toString() {
            return ((Object) this.f4101b) + "=" + ((Object) this.f4102c);
        }

        private boolean a(Object obj, Object obj2) {
            if (obj == null) {
                return obj2 == null;
            }
            return obj.equals(obj2);
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: SmallSortedMap */
    public class d extends AbstractSet<Map.Entry<K, V>> {
        private d() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return new c();
        }

        public int size() {
            return aq.this.size();
        }

        public boolean contains(Object obj) {
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = aq.this.get(entry.getKey());
            Object value = entry.getValue();
            return obj2 == value || (obj2 != null && obj2.equals(value));
        }

        /* renamed from: a */
        public boolean add(Map.Entry<K, V> entry) {
            if (contains(entry)) {
                return false;
            }
            aq.this.put(entry.getKey(), entry.getValue());
            return true;
        }

        public boolean remove(Object obj) {
            Map.Entry entry = (Map.Entry) obj;
            if (!contains(entry)) {
                return false;
            }
            aq.this.remove(entry.getKey());
            return true;
        }

        public void clear() {
            aq.this.clear();
        }
    }

    /* compiled from: SmallSortedMap */
    private class c implements Iterator<Map.Entry<K, V>> {

        /* renamed from: b  reason: collision with root package name */
        private int f4104b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f4105c;

        /* renamed from: d  reason: collision with root package name */
        private Iterator<Map.Entry<K, V>> f4106d;

        private c() {
            this.f4104b = -1;
        }

        public boolean hasNext() {
            if (this.f4104b + 1 < aq.this.f4095b.size()) {
                return true;
            }
            if (aq.this.f4096c.isEmpty() || !b().hasNext()) {
                return false;
            }
            return true;
        }

        /* renamed from: a */
        public Map.Entry<K, V> next() {
            this.f4105c = true;
            int i = this.f4104b + 1;
            this.f4104b = i;
            if (i < aq.this.f4095b.size()) {
                return (Map.Entry) aq.this.f4095b.get(this.f4104b);
            }
            return b().next();
        }

        public void remove() {
            if (this.f4105c) {
                this.f4105c = false;
                aq.this.f();
                if (this.f4104b < aq.this.f4095b.size()) {
                    aq aqVar = aq.this;
                    int i = this.f4104b;
                    this.f4104b = i - 1;
                    aqVar.c((aq) i);
                    return;
                }
                b().remove();
                return;
            }
            throw new IllegalStateException("remove() was called before next()");
        }

        /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.util.Iterator<java.util.Map$Entry<K, V>>, java.util.Iterator<java.util.Map$Entry<K extends java.lang.Comparable<K>, V>> */
        private Iterator<Map.Entry<K, V>> b() {
            if (this.f4106d == null) {
                this.f4106d = aq.this.f4096c.entrySet().iterator();
            }
            return (Iterator<Map.Entry<K, V>>) this.f4106d;
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: SmallSortedMap */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private static final Iterator<Object> f4098a = new Iterator<Object>() {
            /* class com.google.protobuf.aq.a.AnonymousClass1 */

            public boolean hasNext() {
                return false;
            }

            @Override // java.util.Iterator
            public Object next() {
                throw new NoSuchElementException();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };

        /* renamed from: b  reason: collision with root package name */
        private static final Iterable<Object> f4099b = new Iterable<Object>() {
            /* class com.google.protobuf.aq.a.AnonymousClass2 */

            @Override // java.lang.Iterable
            public Iterator<Object> iterator() {
                return a.f4098a;
            }
        };

        static <T> Iterable<T> a() {
            return (Iterable<T>) f4099b;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof aq)) {
            return super.equals(obj);
        }
        aq aqVar = (aq) obj;
        int size = size();
        if (size != aqVar.size()) {
            return false;
        }
        int c2 = c();
        if (c2 != aqVar.c()) {
            return entrySet().equals(aqVar.entrySet());
        }
        for (int i = 0; i < c2; i++) {
            if (!b(i).equals(aqVar.b(i))) {
                return false;
            }
        }
        if (c2 != size) {
            return this.f4096c.equals(aqVar.f4096c);
        }
        return true;
    }

    public int hashCode() {
        int c2 = c();
        int i = 0;
        for (int i2 = 0; i2 < c2; i2++) {
            i += this.f4095b.get(i2).hashCode();
        }
        return d() > 0 ? i + this.f4096c.hashCode() : i;
    }
}
