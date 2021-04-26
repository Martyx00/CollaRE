package com.google.maps.android.a.a;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BiMultiMap */
public class a<K> extends HashMap<K, Object> {

    /* renamed from: a  reason: collision with root package name */
    private final Map<Object, K> f3971a = new HashMap();

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.google.maps.android.a.a.a<K> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public void putAll(Map<? extends K, ?> map) {
        for (Map.Entry<? extends K, ?> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public Object put(K k, Object obj) {
        this.f3971a.put(obj, k);
        return super.put(k, obj);
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public Object remove(Object obj) {
        Object remove = super.remove(obj);
        if (remove instanceof Collection) {
            for (Object obj2 : (Collection) remove) {
                this.f3971a.remove(obj2);
            }
        } else {
            this.f3971a.remove(remove);
        }
        return remove;
    }

    public void clear() {
        super.clear();
        this.f3971a.clear();
    }

    /* renamed from: a */
    public a<K> clone() {
        a<K> aVar = new a<>();
        aVar.putAll((Map) super.clone());
        return aVar;
    }
}
