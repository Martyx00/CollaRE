package com.facebook.imagepipeline.d;

import com.facebook.common.d.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: CountingLruMap */
public class g<K, V> {

    /* renamed from: a  reason: collision with root package name */
    private final v<V> f2048a;

    /* renamed from: b  reason: collision with root package name */
    private final LinkedHashMap<K, V> f2049b = new LinkedHashMap<>();

    /* renamed from: c  reason: collision with root package name */
    private int f2050c = 0;

    public g(v<V> vVar) {
        this.f2048a = vVar;
    }

    public synchronized int a() {
        return this.f2049b.size();
    }

    public synchronized int b() {
        return this.f2050c;
    }

    public synchronized K c() {
        return this.f2049b.isEmpty() ? null : this.f2049b.keySet().iterator().next();
    }

    public synchronized ArrayList<Map.Entry<K, V>> a(j<K> jVar) {
        ArrayList<Map.Entry<K, V>> arrayList;
        arrayList = new ArrayList<>(this.f2049b.entrySet().size());
        for (Map.Entry<K, V> entry : this.f2049b.entrySet()) {
            if (jVar == null || jVar.a(entry.getKey())) {
                arrayList.add(entry);
            }
        }
        return arrayList;
    }

    public synchronized V a(K k) {
        return this.f2049b.get(k);
    }

    public synchronized V a(K k, V v) {
        V remove;
        remove = this.f2049b.remove(k);
        this.f2050c -= c(remove);
        this.f2049b.put(k, v);
        this.f2050c += c(v);
        return remove;
    }

    public synchronized V b(K k) {
        V remove;
        remove = this.f2049b.remove(k);
        this.f2050c -= c(remove);
        return remove;
    }

    public synchronized ArrayList<V> b(j<K> jVar) {
        ArrayList<V> arrayList;
        arrayList = new ArrayList<>();
        Iterator<Map.Entry<K, V>> it = this.f2049b.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (jVar == null || jVar.a(next.getKey())) {
                arrayList.add(next.getValue());
                this.f2050c -= c(next.getValue());
                it.remove();
            }
        }
        return arrayList;
    }

    private int c(V v) {
        if (v == null) {
            return 0;
        }
        return this.f2048a.a(v);
    }
}
