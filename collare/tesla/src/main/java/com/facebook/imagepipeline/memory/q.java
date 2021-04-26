package com.facebook.imagepipeline.memory;

import com.facebook.common.h.b;
import java.util.LinkedList;

/* compiled from: OOMSoftReferenceBucket */
class q<V> extends e<V> {

    /* renamed from: d  reason: collision with root package name */
    private LinkedList<b<V>> f2222d = new LinkedList<>();

    public q(int i, int i2, int i3) {
        super(i, i2, i3, false);
    }

    @Override // com.facebook.imagepipeline.memory.e
    public V d() {
        b<V> bVar = (b) this.f2207c.poll();
        V a2 = bVar.a();
        bVar.b();
        this.f2222d.add(bVar);
        return a2;
    }

    /* access modifiers changed from: package-private */
    @Override // com.facebook.imagepipeline.memory.e
    public void b(V v) {
        b<V> poll = this.f2222d.poll();
        if (poll == null) {
            poll = new b<>();
        }
        poll.a(v);
        this.f2207c.add(poll);
    }
}
