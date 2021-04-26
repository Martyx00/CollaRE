package com.facebook.imagepipeline.f;

import android.net.Uri;
import com.facebook.b.a.d;
import com.facebook.common.d.j;
import com.facebook.common.d.l;
import com.facebook.common.h.a;
import com.facebook.imagepipeline.d.e;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.d.p;
import com.facebook.imagepipeline.j.b;
import com.facebook.imagepipeline.k.c;
import com.facebook.imagepipeline.n.ak;
import com.facebook.imagepipeline.n.aq;
import com.facebook.imagepipeline.n.au;
import com.facebook.imagepipeline.o.b;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: ImagePipeline */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private static final CancellationException f2103a = new CancellationException("Prefetching is not enabled");

    /* renamed from: b  reason: collision with root package name */
    private final m f2104b;

    /* renamed from: c  reason: collision with root package name */
    private final c f2105c;

    /* renamed from: d  reason: collision with root package name */
    private final l<Boolean> f2106d;
    private final p<d, b> e;
    private final p<d, com.facebook.common.g.g> f;
    private final e g;
    private final e h;
    private final f i;
    private final au j;
    private final l<Boolean> k;
    private AtomicLong l = new AtomicLong();
    private final l<Boolean> m;

    public g(m mVar, Set<c> set, l<Boolean> lVar, p<d, b> pVar, p<d, com.facebook.common.g.g> pVar2, e eVar, e eVar2, f fVar, au auVar, l<Boolean> lVar2, l<Boolean> lVar3) {
        this.f2104b = mVar;
        this.f2105c = new com.facebook.imagepipeline.k.b(set);
        this.f2106d = lVar;
        this.e = pVar;
        this.f = pVar2;
        this.g = eVar;
        this.h = eVar2;
        this.i = fVar;
        this.j = auVar;
        this.k = lVar2;
        this.m = lVar3;
    }

    private String f() {
        return String.valueOf(this.l.getAndIncrement());
    }

    public com.facebook.c.c<a<b>> a(com.facebook.imagepipeline.o.b bVar, Object obj) {
        return a(bVar, obj, b.EnumC0051b.BITMAP_MEMORY_CACHE);
    }

    public com.facebook.c.c<a<com.facebook.imagepipeline.j.b>> b(com.facebook.imagepipeline.o.b bVar, Object obj) {
        return a(bVar, obj, b.EnumC0051b.FULL_FETCH);
    }

    public com.facebook.c.c<a<com.facebook.imagepipeline.j.b>> a(com.facebook.imagepipeline.o.b bVar, Object obj, b.EnumC0051b bVar2) {
        return a(bVar, obj, bVar2, null);
    }

    public com.facebook.c.c<a<com.facebook.imagepipeline.j.b>> a(com.facebook.imagepipeline.o.b bVar, Object obj, b.EnumC0051b bVar2, c cVar) {
        try {
            return a(this.f2104b.b(bVar), bVar, bVar2, obj, cVar);
        } catch (Exception e2) {
            return com.facebook.c.d.a(e2);
        }
    }

    public com.facebook.c.c<Void> c(com.facebook.imagepipeline.o.b bVar, Object obj) {
        return a(bVar, obj, com.facebook.imagepipeline.e.d.MEDIUM);
    }

    public com.facebook.c.c<Void> a(com.facebook.imagepipeline.o.b bVar, Object obj, com.facebook.imagepipeline.e.d dVar) {
        if (!this.f2106d.b().booleanValue()) {
            return com.facebook.c.d.a(f2103a);
        }
        try {
            return a(this.f2104b.a(bVar), bVar, b.EnumC0051b.FULL_FETCH, obj, dVar);
        } catch (Exception e2) {
            return com.facebook.c.d.a(e2);
        }
    }

    public void a() {
        AnonymousClass1 r0 = new j<d>() {
            /* class com.facebook.imagepipeline.f.g.AnonymousClass1 */

            public boolean a(d dVar) {
                return true;
            }
        };
        this.e.a(r0);
        this.f.a(r0);
    }

    public void b() {
        this.g.a();
        this.h.a();
    }

    public void c() {
        a();
        b();
    }

    public boolean a(Uri uri) {
        if (uri == null) {
            return false;
        }
        return this.e.b(c(uri));
    }

    public p<d, com.facebook.imagepipeline.j.b> d() {
        return this.e;
    }

    public boolean a(com.facebook.imagepipeline.o.b bVar) {
        if (bVar == null) {
            return false;
        }
        a<com.facebook.imagepipeline.j.b> a2 = this.e.a(this.i.a(bVar, null));
        try {
            return a.a((a<?>) a2);
        } finally {
            a.c(a2);
        }
    }

    public boolean b(Uri uri) {
        return a(uri, b.a.SMALL) || a(uri, b.a.DEFAULT);
    }

    public boolean a(Uri uri, b.a aVar) {
        return b(com.facebook.imagepipeline.o.c.a(uri).a(aVar).o());
    }

    public boolean b(com.facebook.imagepipeline.o.b bVar) {
        d c2 = this.i.c(bVar, null);
        switch (bVar.a()) {
            case DEFAULT:
                return this.g.b(c2);
            case SMALL:
                return this.h.b(c2);
            default:
                return false;
        }
    }

    private <T> com.facebook.c.c<a<T>> a(ak<a<T>> akVar, com.facebook.imagepipeline.o.b bVar, b.EnumC0051b bVar2, Object obj, c cVar) {
        boolean z;
        c a2 = a(bVar, cVar);
        try {
            b.EnumC0051b a3 = b.EnumC0051b.a(bVar.m(), bVar2);
            String f2 = f();
            if (!bVar.j()) {
                if (com.facebook.common.k.f.b(bVar.b())) {
                    z = false;
                    return com.facebook.imagepipeline.g.c.a(akVar, new aq(bVar, f2, a2, obj, a3, false, z, bVar.l()), a2);
                }
            }
            z = true;
            return com.facebook.imagepipeline.g.c.a(akVar, new aq(bVar, f2, a2, obj, a3, false, z, bVar.l()), a2);
        } catch (Exception e2) {
            return com.facebook.c.d.a(e2);
        }
    }

    private com.facebook.c.c<Void> a(ak<Void> akVar, com.facebook.imagepipeline.o.b bVar, b.EnumC0051b bVar2, Object obj, com.facebook.imagepipeline.e.d dVar) {
        c a2 = a(bVar, (c) null);
        try {
            return com.facebook.imagepipeline.g.d.a(akVar, new aq(bVar, f(), a2, obj, b.EnumC0051b.a(bVar.m(), bVar2), true, false, dVar), a2);
        } catch (Exception e2) {
            return com.facebook.c.d.a(e2);
        }
    }

    private c a(com.facebook.imagepipeline.o.b bVar, c cVar) {
        if (cVar == null) {
            if (bVar.r() == null) {
                return this.f2105c;
            }
            return new com.facebook.imagepipeline.k.b(this.f2105c, bVar.r());
        } else if (bVar.r() == null) {
            return new com.facebook.imagepipeline.k.b(this.f2105c, cVar);
        } else {
            return new com.facebook.imagepipeline.k.b(this.f2105c, cVar, bVar.r());
        }
    }

    private j<d> c(final Uri uri) {
        return new j<d>() {
            /* class com.facebook.imagepipeline.f.g.AnonymousClass2 */

            public boolean a(d dVar) {
                return dVar.a(uri);
            }
        };
    }

    public f e() {
        return this.i;
    }
}
