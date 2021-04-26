package com.facebook.imagepipeline.n;

import com.facebook.b.a.d;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.d.p;
import com.facebook.imagepipeline.j.b;
import com.facebook.imagepipeline.o.e;
import java.util.Map;

/* compiled from: PostprocessedBitmapMemoryCacheProducer */
public class ai implements ak<com.facebook.common.h.a<b>> {

    /* renamed from: a  reason: collision with root package name */
    private final p<d, b> f2283a;

    /* renamed from: b  reason: collision with root package name */
    private final f f2284b;

    /* renamed from: c  reason: collision with root package name */
    private final ak<com.facebook.common.h.a<b>> f2285c;

    /* access modifiers changed from: protected */
    public String a() {
        return "PostprocessedBitmapMemoryCacheProducer";
    }

    public ai(p<d, b> pVar, f fVar, ak<com.facebook.common.h.a<b>> akVar) {
        this.f2283a = pVar;
        this.f2284b = fVar;
        this.f2285c = akVar;
    }

    @Override // com.facebook.imagepipeline.n.ak
    public void a(k<com.facebook.common.h.a<b>> kVar, al alVar) {
        an c2 = alVar.c();
        String b2 = alVar.b();
        com.facebook.imagepipeline.o.b a2 = alVar.a();
        Object d2 = alVar.d();
        com.facebook.imagepipeline.o.d q = a2.q();
        if (q == null || q.a() == null) {
            this.f2285c.a(kVar, alVar);
            return;
        }
        c2.a(b2, a());
        d b3 = this.f2284b.b(a2, d2);
        com.facebook.common.h.a<b> a3 = this.f2283a.a(b3);
        Map<String, String> map = null;
        if (a3 != null) {
            String a4 = a();
            if (c2.b(b2)) {
                map = com.facebook.common.d.f.a("cached_value_found", "true");
            }
            c2.a(b2, a4, map);
            c2.a(b2, "PostprocessedBitmapMemoryCacheProducer", true);
            kVar.b(1.0f);
            kVar.b(a3, 1);
            a3.close();
            return;
        }
        a aVar = new a(kVar, b3, q instanceof e, this.f2283a, alVar.a().o());
        String a5 = a();
        if (c2.b(b2)) {
            map = com.facebook.common.d.f.a("cached_value_found", "false");
        }
        c2.a(b2, a5, map);
        this.f2285c.a(aVar, alVar);
    }

    /* compiled from: PostprocessedBitmapMemoryCacheProducer */
    public static class a extends n<com.facebook.common.h.a<b>, com.facebook.common.h.a<b>> {

        /* renamed from: a  reason: collision with root package name */
        private final d f2286a;

        /* renamed from: b  reason: collision with root package name */
        private final boolean f2287b;

        /* renamed from: c  reason: collision with root package name */
        private final p<d, b> f2288c;

        /* renamed from: d  reason: collision with root package name */
        private final boolean f2289d;

        public a(k<com.facebook.common.h.a<b>> kVar, d dVar, boolean z, p<d, b> pVar, boolean z2) {
            super(kVar);
            this.f2286a = dVar;
            this.f2287b = z;
            this.f2288c = pVar;
            this.f2289d = z2;
        }

        /* access modifiers changed from: protected */
        public void a(com.facebook.common.h.a<b> aVar, int i) {
            com.facebook.common.h.a<b> aVar2 = null;
            if (aVar == null) {
                if (a(i)) {
                    d().b(aVar2, i);
                }
            } else if (!b(i) || this.f2287b) {
                if (this.f2289d) {
                    aVar2 = this.f2288c.a(this.f2286a, aVar);
                }
                try {
                    d().b(1.0f);
                    k d2 = d();
                    if (aVar2 != null) {
                        aVar = aVar2;
                    }
                    d2.b(aVar, i);
                } finally {
                    com.facebook.common.h.a.c(aVar2);
                }
            }
        }
    }
}
