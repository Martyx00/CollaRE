package com.facebook.imagepipeline.n;

import com.facebook.common.g.g;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.d.p;
import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.o.b;
import java.util.Map;

/* compiled from: EncodedMemoryCacheProducer */
public class s implements ak<d> {

    /* renamed from: a  reason: collision with root package name */
    private final p<com.facebook.b.a.d, g> f2415a;

    /* renamed from: b  reason: collision with root package name */
    private final f f2416b;

    /* renamed from: c  reason: collision with root package name */
    private final ak<d> f2417c;

    public s(p<com.facebook.b.a.d, g> pVar, f fVar, ak<d> akVar) {
        this.f2415a = pVar;
        this.f2416b = fVar;
        this.f2417c = akVar;
    }

    /* JADX INFO: finally extract failed */
    @Override // com.facebook.imagepipeline.n.ak
    public void a(k<d> kVar, al alVar) {
        String b2 = alVar.b();
        an c2 = alVar.c();
        c2.a(b2, "EncodedMemoryCacheProducer");
        com.facebook.b.a.d c3 = this.f2416b.c(alVar.a(), alVar.d());
        com.facebook.common.h.a<g> a2 = this.f2415a.a(c3);
        Map<String, String> map = null;
        if (a2 != null) {
            try {
                d dVar = new d(a2);
                try {
                    if (c2.b(b2)) {
                        map = com.facebook.common.d.f.a("cached_value_found", "true");
                    }
                    c2.a(b2, "EncodedMemoryCacheProducer", map);
                    c2.a(b2, "EncodedMemoryCacheProducer", true);
                    kVar.b(1.0f);
                    kVar.b(dVar, 1);
                    d.d(dVar);
                } catch (Throwable th) {
                    d.d(dVar);
                    throw th;
                }
            } finally {
                com.facebook.common.h.a.c(a2);
            }
        } else if (alVar.e().a() >= b.EnumC0051b.ENCODED_MEMORY_CACHE.a()) {
            c2.a(b2, "EncodedMemoryCacheProducer", c2.b(b2) ? com.facebook.common.d.f.a("cached_value_found", "false") : null);
            c2.a(b2, "EncodedMemoryCacheProducer", false);
            kVar.b(null, 1);
            com.facebook.common.h.a.c(a2);
        } else {
            a aVar = new a(kVar, this.f2415a, c3, alVar.a().o());
            if (c2.b(b2)) {
                map = com.facebook.common.d.f.a("cached_value_found", "false");
            }
            c2.a(b2, "EncodedMemoryCacheProducer", map);
            this.f2417c.a(aVar, alVar);
            com.facebook.common.h.a.c(a2);
        }
    }

    /* compiled from: EncodedMemoryCacheProducer */
    private static class a extends n<d, d> {

        /* renamed from: a  reason: collision with root package name */
        private final p<com.facebook.b.a.d, g> f2418a;

        /* renamed from: b  reason: collision with root package name */
        private final com.facebook.b.a.d f2419b;

        /* renamed from: c  reason: collision with root package name */
        private final boolean f2420c;

        public a(k<d> kVar, p<com.facebook.b.a.d, g> pVar, com.facebook.b.a.d dVar, boolean z) {
            super(kVar);
            this.f2418a = pVar;
            this.f2419b = dVar;
            this.f2420c = z;
        }

        public void a(d dVar, int i) {
            if (b(i) || dVar == null || c(i, 10)) {
                d().b(dVar, i);
                return;
            }
            com.facebook.common.h.a<g> c2 = dVar.c();
            if (c2 != null) {
                com.facebook.common.h.a<g> aVar = null;
                try {
                    if (this.f2420c) {
                        aVar = this.f2418a.a(this.f2419b, c2);
                    }
                    if (aVar != null) {
                        try {
                            d dVar2 = new d(aVar);
                            dVar2.b(dVar);
                            try {
                                d().b(1.0f);
                                d().b(dVar2, i);
                                return;
                            } finally {
                                d.d(dVar2);
                            }
                        } finally {
                            com.facebook.common.h.a.c(aVar);
                        }
                    }
                } finally {
                    com.facebook.common.h.a.c(c2);
                }
            }
            d().b(dVar, i);
        }
    }
}
