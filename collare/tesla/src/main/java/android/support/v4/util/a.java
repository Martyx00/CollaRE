package android.support.v4.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* compiled from: ArrayMap */
public class a<K, V> extends l<K, V> implements Map<K, V> {

    /* renamed from: a  reason: collision with root package name */
    h<K, V> f637a;

    public a() {
    }

    public a(int i) {
        super(i);
    }

    private h<K, V> b() {
        if (this.f637a == null) {
            this.f637a = new h<K, V>() {
                /* class android.support.v4.util.a.AnonymousClass1 */

                /* access modifiers changed from: protected */
                @Override // android.support.v4.util.h
                public int a() {
                    return a.this.h;
                }

                /* access modifiers changed from: protected */
                @Override // android.support.v4.util.h
                public Object a(int i, int i2) {
                    return a.this.g[(i << 1) + i2];
                }

                /* access modifiers changed from: protected */
                @Override // android.support.v4.util.h
                public int a(Object obj) {
                    return a.this.a(obj);
                }

                /* access modifiers changed from: protected */
                @Override // android.support.v4.util.h
                public int b(Object obj) {
                    return a.this.b(obj);
                }

                /* access modifiers changed from: protected */
                @Override // android.support.v4.util.h
                public Map<K, V> b() {
                    return a.this;
                }

                /* access modifiers changed from: protected */
                @Override // android.support.v4.util.h
                public void a(K k, V v) {
                    a.this.put(k, v);
                }

                /* access modifiers changed from: protected */
                @Override // android.support.v4.util.h
                public V a(int i, V v) {
                    return (V) a.this.a(i, v);
                }

                /* access modifiers changed from: protected */
                @Override // android.support.v4.util.h
                public void a(int i) {
                    a.this.d(i);
                }

                /* access modifiers changed from: protected */
                @Override // android.support.v4.util.h
                public void c() {
                    a.this.clear();
                }
            };
        }
        return this.f637a;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: android.support.v4.util.a<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        a(this.h + map.size());
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public boolean a(Collection<?> collection) {
        return h.c(this, collection);
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return b().d();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return b().e();
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return b().f();
    }
}
