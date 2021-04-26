package com.facebook.imagepipeline.d;

import com.facebook.common.d.j;
import com.facebook.common.h.a;

/* compiled from: InstrumentedMemoryCache */
public class o<K, V> implements p<K, V> {

    /* renamed from: a  reason: collision with root package name */
    private final p<K, V> f2066a;

    /* renamed from: b  reason: collision with root package name */
    private final r f2067b;

    public o(p<K, V> pVar, r rVar) {
        this.f2066a = pVar;
        this.f2067b = rVar;
    }

    @Override // com.facebook.imagepipeline.d.p
    public a<V> a(K k) {
        a<V> a2 = this.f2066a.a((Object) k);
        if (a2 == null) {
            this.f2067b.a();
        } else {
            this.f2067b.a(k);
        }
        return a2;
    }

    @Override // com.facebook.imagepipeline.d.p
    public a<V> a(K k, a<V> aVar) {
        this.f2067b.b();
        return this.f2066a.a(k, aVar);
    }

    @Override // com.facebook.imagepipeline.d.p
    public int a(j<K> jVar) {
        return this.f2066a.a((j) jVar);
    }

    @Override // com.facebook.imagepipeline.d.p
    public boolean b(j<K> jVar) {
        return this.f2066a.b(jVar);
    }
}
