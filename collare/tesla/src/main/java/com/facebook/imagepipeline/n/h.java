package com.facebook.imagepipeline.n;

import com.facebook.b.a.d;
import com.facebook.common.h.a;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.d.p;
import com.facebook.imagepipeline.j.b;
import com.facebook.imagepipeline.j.g;
import com.facebook.imagepipeline.o.b;
import java.util.Map;

/* compiled from: BitmapMemoryCacheProducer */
public class h implements ak<a<b>> {

    /* renamed from: a  reason: collision with root package name */
    private final p<d, b> f2361a;

    /* renamed from: b  reason: collision with root package name */
    private final f f2362b;

    /* renamed from: c  reason: collision with root package name */
    private final ak<a<b>> f2363c;

    /* access modifiers changed from: protected */
    public String a() {
        return "BitmapMemoryCacheProducer";
    }

    public h(p<d, b> pVar, f fVar, ak<a<b>> akVar) {
        this.f2361a = pVar;
        this.f2362b = fVar;
        this.f2363c = akVar;
    }

    @Override // com.facebook.imagepipeline.n.ak
    public void a(k<a<b>> kVar, al alVar) {
        an c2 = alVar.c();
        String b2 = alVar.b();
        c2.a(b2, a());
        d a2 = this.f2362b.a(alVar.a(), alVar.d());
        a<b> a3 = this.f2361a.a(a2);
        Map<String, String> map = null;
        if (a3 != null) {
            boolean c3 = a3.a().d().c();
            if (c3) {
                c2.a(b2, a(), c2.b(b2) ? com.facebook.common.d.f.a("cached_value_found", "true") : null);
                c2.a(b2, a(), true);
                kVar.b(1.0f);
            }
            kVar.b(a3, b.a(c3));
            a3.close();
            if (c3) {
                return;
            }
        }
        if (alVar.e().a() >= b.EnumC0051b.BITMAP_MEMORY_CACHE.a()) {
            c2.a(b2, a(), c2.b(b2) ? com.facebook.common.d.f.a("cached_value_found", "false") : null);
            c2.a(b2, a(), false);
            kVar.b(null, 1);
            return;
        }
        k<a<com.facebook.imagepipeline.j.b>> a4 = a(kVar, a2, alVar.a().o());
        String a5 = a();
        if (c2.b(b2)) {
            map = com.facebook.common.d.f.a("cached_value_found", "false");
        }
        c2.a(b2, a5, map);
        this.f2363c.a(a4, alVar);
    }

    /* access modifiers changed from: protected */
    public k<a<com.facebook.imagepipeline.j.b>> a(k<a<com.facebook.imagepipeline.j.b>> kVar, final d dVar, final boolean z) {
        return new n<a<com.facebook.imagepipeline.j.b>, a<com.facebook.imagepipeline.j.b>>(kVar) {
            /* class com.facebook.imagepipeline.n.h.AnonymousClass1 */

            public void a(a<com.facebook.imagepipeline.j.b> aVar, int i) {
                a a2;
                boolean a3 = a(i);
                a<com.facebook.imagepipeline.j.b> aVar2 = null;
                if (aVar == null) {
                    if (a3) {
                        d().b(null, i);
                    }
                } else if (aVar.a().e() || b(i, 8)) {
                    d().b(aVar, i);
                } else {
                    if (!a3 && (a2 = h.this.f2361a.a(dVar)) != null) {
                        try {
                            g d2 = aVar.a().d();
                            g d3 = ((com.facebook.imagepipeline.j.b) a2.a()).d();
                            if (d3.c() || d3.a() >= d2.a()) {
                                d().b(a2, i);
                                a.c(a2);
                                return;
                            }
                        } finally {
                            a.c(a2);
                        }
                    }
                    if (z) {
                        aVar2 = h.this.f2361a.a(dVar, aVar);
                    }
                    if (a3) {
                        try {
                            d().b(1.0f);
                        } catch (Throwable th) {
                            a.c(aVar2);
                            throw th;
                        }
                    }
                    k d4 = d();
                    if (aVar2 != null) {
                        aVar = aVar2;
                    }
                    d4.b(aVar, i);
                    a.c(aVar2);
                }
            }
        };
    }
}
