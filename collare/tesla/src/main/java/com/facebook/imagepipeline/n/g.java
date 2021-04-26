package com.facebook.imagepipeline.n;

import android.util.Pair;
import com.facebook.b.a.d;
import com.facebook.common.h.a;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.o.b;

/* compiled from: BitmapMemoryCacheKeyMultiplexProducer */
public class g extends ae<Pair<d, b.EnumC0051b>, a<com.facebook.imagepipeline.j.b>> {

    /* renamed from: b  reason: collision with root package name */
    private final f f2360b;

    public g(f fVar, ak akVar) {
        super(akVar);
        this.f2360b = fVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Pair<d, b.EnumC0051b> b(al alVar) {
        return Pair.create(this.f2360b.a(alVar.a(), alVar.d()), alVar.e());
    }

    public a<com.facebook.imagepipeline.j.b> a(a<com.facebook.imagepipeline.j.b> aVar) {
        return a.b(aVar);
    }
}
