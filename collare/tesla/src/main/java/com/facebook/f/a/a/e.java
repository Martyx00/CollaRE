package com.facebook.f.a.a;

import android.content.Context;
import android.net.Uri;
import com.facebook.common.h.a;
import com.facebook.f.a.a.a.f;
import com.facebook.f.c.b;
import com.facebook.f.c.d;
import com.facebook.imagepipeline.f.g;
import com.facebook.imagepipeline.o.b;
import com.facebook.imagepipeline.o.c;
import java.util.Set;

/* compiled from: PipelineDraweeControllerBuilder */
public class e extends b<e, com.facebook.imagepipeline.o.b, a<com.facebook.imagepipeline.j.b>, com.facebook.imagepipeline.j.e> {

    /* renamed from: a  reason: collision with root package name */
    private final g f1842a;

    /* renamed from: b  reason: collision with root package name */
    private final g f1843b;

    /* renamed from: c  reason: collision with root package name */
    private com.facebook.common.d.e<com.facebook.imagepipeline.i.a> f1844c;

    /* renamed from: d  reason: collision with root package name */
    private com.facebook.f.a.a.a.b f1845d;
    private f e;

    public e(Context context, g gVar, g gVar2, Set<d> set) {
        super(context, set);
        this.f1842a = gVar2;
        this.f1843b = gVar;
    }

    /* renamed from: a */
    public e b(Uri uri) {
        if (uri == null) {
            return (e) super.b((Object) null);
        }
        return (e) super.b(c.a(uri).a(com.facebook.imagepipeline.e.f.c()).o());
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public d b() {
        d dVar;
        com.facebook.imagepipeline.p.b.a("obtainController");
        try {
            com.facebook.f.h.a i = i();
            String m = m();
            if (i instanceof d) {
                dVar = (d) i;
            } else {
                dVar = this.f1843b.a();
            }
            dVar.a(a(dVar, m), m, p(), d(), this.f1844c, this.f1845d);
            dVar.a(this.e);
            return dVar;
        } finally {
            com.facebook.imagepipeline.p.b.a();
        }
    }

    private com.facebook.b.a.d p() {
        com.facebook.imagepipeline.o.b bVar = (com.facebook.imagepipeline.o.b) e();
        com.facebook.imagepipeline.d.f e2 = this.f1842a.e();
        if (e2 == null || bVar == null) {
            return null;
        }
        if (bVar.q() != null) {
            return e2.b(bVar, d());
        }
        return e2.a(bVar, d());
    }

    /* access modifiers changed from: protected */
    public com.facebook.c.c<a<com.facebook.imagepipeline.j.b>> a(com.facebook.f.h.a aVar, String str, com.facebook.imagepipeline.o.b bVar, Object obj, b.a aVar2) {
        return this.f1842a.a(bVar, obj, a(aVar2), a(aVar));
    }

    /* access modifiers changed from: protected */
    public com.facebook.imagepipeline.k.c a(com.facebook.f.h.a aVar) {
        if (aVar instanceof d) {
            return ((d) aVar).b();
        }
        return null;
    }

    public static b.EnumC0051b a(b.a aVar) {
        switch (aVar) {
            case FULL_FETCH:
                return b.EnumC0051b.FULL_FETCH;
            case DISK_CACHE:
                return b.EnumC0051b.DISK_CACHE;
            case BITMAP_MEMORY_CACHE:
                return b.EnumC0051b.BITMAP_MEMORY_CACHE;
            default:
                throw new RuntimeException("Cache level" + aVar + "is not supported. ");
        }
    }
}
