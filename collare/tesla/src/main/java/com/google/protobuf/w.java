package com.google.protobuf;

import java.util.Iterator;
import java.util.Map;

/* compiled from: LazyField */
public class w extends x {

    /* renamed from: b  reason: collision with root package name */
    private final ad f4528b;

    public w(ad adVar, q qVar, g gVar) {
        super(qVar, gVar);
        this.f4528b = adVar;
    }

    public ad a() {
        return a(this.f4528b);
    }

    @Override // com.google.protobuf.x
    public int hashCode() {
        return a().hashCode();
    }

    @Override // com.google.protobuf.x
    public boolean equals(Object obj) {
        return a().equals(obj);
    }

    public String toString() {
        return a().toString();
    }

    /* access modifiers changed from: package-private */
    /* compiled from: LazyField */
    public static class a<K> implements Map.Entry<K, Object> {

        /* renamed from: a  reason: collision with root package name */
        private Map.Entry<K, w> f4529a;

        private a(Map.Entry<K, w> entry) {
            this.f4529a = entry;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f4529a.getKey();
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            w value = this.f4529a.getValue();
            if (value == null) {
                return null;
            }
            return value.a();
        }

        public w a() {
            return this.f4529a.getValue();
        }

        @Override // java.util.Map.Entry
        public Object setValue(Object obj) {
            if (obj instanceof ad) {
                return this.f4529a.getValue().b((ad) obj);
            }
            throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
        }
    }

    /* access modifiers changed from: package-private */
    /* compiled from: LazyField */
    public static class b<K> implements Iterator<Map.Entry<K, Object>> {

        /* renamed from: a  reason: collision with root package name */
        private Iterator<Map.Entry<K, Object>> f4530a;

        public b(Iterator<Map.Entry<K, Object>> it) {
            this.f4530a = it;
        }

        public boolean hasNext() {
            return this.f4530a.hasNext();
        }

        /* renamed from: a */
        public Map.Entry<K, Object> next() {
            Map.Entry<K, Object> next = this.f4530a.next();
            return next.getValue() instanceof w ? new a(next) : next;
        }

        public void remove() {
            this.f4530a.remove();
        }
    }
}
