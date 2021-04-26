package com.facebook.imagepipeline.h;

import android.graphics.Bitmap;
import com.facebook.common.h.a;
import com.facebook.g.c;
import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.j.f;
import com.facebook.imagepipeline.j.g;
import com.facebook.imagepipeline.l.e;
import java.util.Map;

/* compiled from: DefaultImageDecoder */
public class b implements c {

    /* renamed from: a  reason: collision with root package name */
    private final c f2151a;

    /* renamed from: b  reason: collision with root package name */
    private final c f2152b;

    /* renamed from: c  reason: collision with root package name */
    private final e f2153c;

    /* renamed from: d  reason: collision with root package name */
    private final c f2154d;
    private final Map<c, c> e;

    public b(c cVar, c cVar2, e eVar) {
        this(cVar, cVar2, eVar, null);
    }

    public b(c cVar, c cVar2, e eVar, Map<c, c> map) {
        this.f2154d = new c() {
            /* class com.facebook.imagepipeline.h.b.AnonymousClass1 */

            @Override // com.facebook.imagepipeline.h.c
            public com.facebook.imagepipeline.j.b a(d dVar, int i, g gVar, com.facebook.imagepipeline.e.b bVar) {
                c e = dVar.e();
                if (e == com.facebook.g.b.f1987a) {
                    return b.this.c(dVar, i, gVar, bVar);
                }
                if (e == com.facebook.g.b.f1989c) {
                    return b.this.b(dVar, i, gVar, bVar);
                }
                if (e == com.facebook.g.b.i) {
                    return b.this.d(dVar, i, gVar, bVar);
                }
                if (e != c.f1991a) {
                    return b.this.a(dVar, bVar);
                }
                throw new a("unknown image format", dVar);
            }
        };
        this.f2151a = cVar;
        this.f2152b = cVar2;
        this.f2153c = eVar;
        this.e = map;
    }

    @Override // com.facebook.imagepipeline.h.c
    public com.facebook.imagepipeline.j.b a(d dVar, int i, g gVar, com.facebook.imagepipeline.e.b bVar) {
        c cVar;
        if (bVar.g != null) {
            return bVar.g.a(dVar, i, gVar, bVar);
        }
        c e2 = dVar.e();
        if (e2 == null || e2 == c.f1991a) {
            e2 = com.facebook.g.d.c(dVar.d());
            dVar.a(e2);
        }
        Map<c, c> map = this.e;
        if (map == null || (cVar = map.get(e2)) == null) {
            return this.f2154d.a(dVar, i, gVar, bVar);
        }
        return cVar.a(dVar, i, gVar, bVar);
    }

    public com.facebook.imagepipeline.j.b b(d dVar, int i, g gVar, com.facebook.imagepipeline.e.b bVar) {
        c cVar;
        if (bVar.e || (cVar = this.f2151a) == null) {
            return a(dVar, bVar);
        }
        return cVar.a(dVar, i, gVar, bVar);
    }

    public com.facebook.imagepipeline.j.c a(d dVar, com.facebook.imagepipeline.e.b bVar) {
        a<Bitmap> a2 = this.f2153c.a(dVar, bVar.f, null);
        try {
            return new com.facebook.imagepipeline.j.c(a2, f.f2171a, dVar.f(), dVar.g());
        } finally {
            a2.close();
        }
    }

    public com.facebook.imagepipeline.j.c c(d dVar, int i, g gVar, com.facebook.imagepipeline.e.b bVar) {
        a<Bitmap> a2 = this.f2153c.a(dVar, bVar.f, null, i);
        try {
            return new com.facebook.imagepipeline.j.c(a2, gVar, dVar.f(), dVar.g());
        } finally {
            a2.close();
        }
    }

    public com.facebook.imagepipeline.j.b d(d dVar, int i, g gVar, com.facebook.imagepipeline.e.b bVar) {
        return this.f2152b.a(dVar, i, gVar, bVar);
    }
}
