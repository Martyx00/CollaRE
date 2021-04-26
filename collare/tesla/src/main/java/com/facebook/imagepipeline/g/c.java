package com.facebook.imagepipeline.g;

import com.facebook.common.h.a;
import com.facebook.imagepipeline.n.ak;
import com.facebook.imagepipeline.n.aq;

/* compiled from: CloseableProducerToDataSourceAdapter */
public class c<T> extends a<a<T>> {
    /* access modifiers changed from: protected */
    @Override // com.facebook.c.a
    public /* bridge */ /* synthetic */ void a(Object obj) {
        a((a) ((a) obj));
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.g.a
    public /* bridge */ /* synthetic */ void a(Object obj, int i) {
        a((a) ((a) obj), i);
    }

    public static <T> com.facebook.c.c<a<T>> a(ak<a<T>> akVar, aq aqVar, com.facebook.imagepipeline.k.c cVar) {
        return new c(akVar, aqVar, cVar);
    }

    private c(ak<a<T>> akVar, aq aqVar, com.facebook.imagepipeline.k.c cVar) {
        super(akVar, aqVar, cVar);
    }

    /* renamed from: j */
    public a<T> d() {
        return a.b((a) super.d());
    }

    /* access modifiers changed from: protected */
    public void a(a<T> aVar) {
        a.c(aVar);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.facebook.imagepipeline.g.c<T> */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void a(a<T> aVar, int i) {
        super.a((Object) a.b(aVar), i);
    }
}
