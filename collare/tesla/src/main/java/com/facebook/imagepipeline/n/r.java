package com.facebook.imagepipeline.n;

import android.util.Pair;
import com.facebook.b.a.d;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.o.b;

/* compiled from: EncodedCacheKeyMultiplexProducer */
public class r extends ae<Pair<d, b.EnumC0051b>, com.facebook.imagepipeline.j.d> {

    /* renamed from: b  reason: collision with root package name */
    private final f f2414b;

    public r(f fVar, ak akVar) {
        super(akVar);
        this.f2414b = fVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Pair<d, b.EnumC0051b> b(al alVar) {
        return Pair.create(this.f2414b.c(alVar.a(), alVar.d()), alVar.e());
    }

    public com.facebook.imagepipeline.j.d a(com.facebook.imagepipeline.j.d dVar) {
        return com.facebook.imagepipeline.j.d.a(dVar);
    }
}
