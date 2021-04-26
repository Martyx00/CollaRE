package com.facebook.imagepipeline.n;

import a.h;
import com.facebook.imagepipeline.d.e;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.o.b;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: DiskCacheReadProducer */
public class o implements ak<d> {

    /* renamed from: a  reason: collision with root package name */
    private final e f2396a;

    /* renamed from: b  reason: collision with root package name */
    private final e f2397b;

    /* renamed from: c  reason: collision with root package name */
    private final f f2398c;

    /* renamed from: d  reason: collision with root package name */
    private final ak<d> f2399d;

    public o(e eVar, e eVar2, f fVar, ak<d> akVar) {
        this.f2396a = eVar;
        this.f2397b = eVar2;
        this.f2398c = fVar;
        this.f2399d = akVar;
    }

    @Override // com.facebook.imagepipeline.n.ak
    public void a(k<d> kVar, al alVar) {
        b a2 = alVar.a();
        if (!a2.n()) {
            c(kVar, alVar);
            return;
        }
        alVar.c().a(alVar.b(), "DiskCacheProducer");
        com.facebook.b.a.d c2 = this.f2398c.c(a2, alVar.d());
        e eVar = a2.a() == b.a.SMALL ? this.f2397b : this.f2396a;
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        eVar.a(c2, atomicBoolean).a((a.f<d, TContinuationResult>) b(kVar, alVar));
        a(atomicBoolean, alVar);
    }

    private a.f<d, Void> b(final k<d> kVar, final al alVar) {
        final String b2 = alVar.b();
        final an c2 = alVar.c();
        return new a.f<d, Void>() {
            /* class com.facebook.imagepipeline.n.o.AnonymousClass1 */

            /* renamed from: b */
            public Void a(h<d> hVar) {
                if (o.b(hVar)) {
                    c2.b(b2, "DiskCacheProducer", null);
                    kVar.b();
                } else if (hVar.d()) {
                    c2.a(b2, "DiskCacheProducer", hVar.f(), null);
                    o.this.f2399d.a(kVar, alVar);
                } else {
                    d e2 = hVar.e();
                    if (e2 != null) {
                        an anVar = c2;
                        String str = b2;
                        anVar.a(str, "DiskCacheProducer", o.a(anVar, str, true, e2.l()));
                        c2.a(b2, "DiskCacheProducer", true);
                        kVar.b(1.0f);
                        kVar.b(e2, 1);
                        e2.close();
                    } else {
                        an anVar2 = c2;
                        String str2 = b2;
                        anVar2.a(str2, "DiskCacheProducer", o.a(anVar2, str2, false, 0));
                        o.this.f2399d.a(kVar, alVar);
                    }
                }
                return null;
            }
        };
    }

    /* access modifiers changed from: private */
    public static boolean b(h<?> hVar) {
        return hVar.c() || (hVar.d() && (hVar.f() instanceof CancellationException));
    }

    private void c(k<d> kVar, al alVar) {
        if (alVar.e().a() >= b.EnumC0051b.DISK_CACHE.a()) {
            kVar.b(null, 1);
        } else {
            this.f2399d.a(kVar, alVar);
        }
    }

    static Map<String, String> a(an anVar, String str, boolean z, int i) {
        if (!anVar.b(str)) {
            return null;
        }
        if (z) {
            return com.facebook.common.d.f.a("cached_value_found", String.valueOf(z), "encodedImageSize", String.valueOf(i));
        }
        return com.facebook.common.d.f.a("cached_value_found", String.valueOf(z));
    }

    private void a(final AtomicBoolean atomicBoolean, al alVar) {
        alVar.a(new e() {
            /* class com.facebook.imagepipeline.n.o.AnonymousClass2 */

            @Override // com.facebook.imagepipeline.n.am, com.facebook.imagepipeline.n.e
            public void a() {
                atomicBoolean.set(true);
            }
        });
    }
}
