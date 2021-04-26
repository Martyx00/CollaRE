package android.arch.a.b;

import android.arch.a.b.b;
import java.util.HashMap;
import java.util.Map;

/* compiled from: FastSafeIterableMap */
public class a<K, V> extends b<K, V> {

    /* renamed from: a  reason: collision with root package name */
    private HashMap<K, b.c<K, V>> f43a = new HashMap<>();

    /* access modifiers changed from: protected */
    @Override // android.arch.a.b.b
    public b.c<K, V> a(K k) {
        return this.f43a.get(k);
    }

    @Override // android.arch.a.b.b
    public V a(K k, V v) {
        b.c<K, V> a2 = a((Object) k);
        if (a2 != null) {
            return a2.f49b;
        }
        this.f43a.put(k, b(k, v));
        return null;
    }

    @Override // android.arch.a.b.b
    public V b(K k) {
        V v = (V) super.b(k);
        this.f43a.remove(k);
        return v;
    }

    public boolean c(K k) {
        return this.f43a.containsKey(k);
    }

    public Map.Entry<K, V> d(K k) {
        if (c(k)) {
            return this.f43a.get(k).f51d;
        }
        return null;
    }
}
