package com.google.protobuf;

import com.google.protobuf.u;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: MapFieldLite */
public final class ab<K, V> extends LinkedHashMap<K, V> {

    /* renamed from: b  reason: collision with root package name */
    private static final ab f4064b = new ab();

    /* renamed from: a  reason: collision with root package name */
    private boolean f4065a = true;

    private ab() {
    }

    static {
        f4064b.a();
    }

    @Override // java.util.LinkedHashMap, java.util.AbstractMap, java.util.Map, java.util.HashMap
    public Set<Map.Entry<K, V>> entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    public void clear() {
        c();
        super.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public V put(K k, V v) {
        c();
        u.a((Object) k);
        u.a((Object) v);
        return (V) super.put(k, v);
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public void putAll(Map<? extends K, ? extends V> map) {
        c();
        b(map);
        super.putAll(map);
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public V remove(Object obj) {
        c();
        return (V) super.remove(obj);
    }

    private static void b(Map<?, ?> map) {
        for (Object obj : map.keySet()) {
            u.a(obj);
            u.a(map.get(obj));
        }
    }

    private static boolean a(Object obj, Object obj2) {
        if (!(obj instanceof byte[]) || !(obj2 instanceof byte[])) {
            return obj.equals(obj2);
        }
        return Arrays.equals((byte[]) obj, (byte[]) obj2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <K, V> boolean a(java.util.Map<K, V> r4, java.util.Map<K, V> r5) {
        /*
            r0 = 1
            if (r4 != r5) goto L_0x0004
            return r0
        L_0x0004:
            int r1 = r4.size()
            int r2 = r5.size()
            r3 = 0
            if (r1 == r2) goto L_0x0010
            return r3
        L_0x0010:
            java.util.Set r4 = r4.entrySet()
            java.util.Iterator r4 = r4.iterator()
        L_0x0018:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L_0x0042
            java.lang.Object r1 = r4.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            boolean r2 = r5.containsKey(r2)
            if (r2 != 0) goto L_0x002f
            return r3
        L_0x002f:
            java.lang.Object r2 = r1.getValue()
            java.lang.Object r1 = r1.getKey()
            java.lang.Object r1 = r5.get(r1)
            boolean r1 = a(r2, r1)
            if (r1 != 0) goto L_0x0018
            return r3
        L_0x0042:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.ab.a(java.util.Map, java.util.Map):boolean");
    }

    public boolean equals(Object obj) {
        return (obj instanceof Map) && a(this, (Map) obj);
    }

    private static int a(Object obj) {
        if (obj instanceof byte[]) {
            return u.c((byte[]) obj);
        }
        if (!(obj instanceof u.a)) {
            return obj.hashCode();
        }
        throw new UnsupportedOperationException();
    }

    static <K, V> int a(Map<K, V> map) {
        int i = 0;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            i += a((Object) entry.getValue()) ^ a((Object) entry.getKey());
        }
        return i;
    }

    public int hashCode() {
        return a((Map) this);
    }

    public void a() {
        this.f4065a = false;
    }

    public boolean b() {
        return this.f4065a;
    }

    private void c() {
        if (!b()) {
            throw new UnsupportedOperationException();
        }
    }
}
