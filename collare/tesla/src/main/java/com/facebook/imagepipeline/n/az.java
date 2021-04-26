package com.facebook.imagepipeline.n;

import com.facebook.common.d.i;
import com.facebook.common.g.h;
import com.facebook.common.g.j;
import com.facebook.common.k.e;
import com.facebook.g.b;
import com.facebook.g.c;
import com.facebook.imagepipeline.j.d;
import java.io.InputStream;
import java.util.concurrent.Executor;

/* compiled from: WebpTranscodeProducer */
public class az implements ak<d> {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f2347a;

    /* renamed from: b  reason: collision with root package name */
    private final h f2348b;

    /* renamed from: c  reason: collision with root package name */
    private final ak<d> f2349c;

    public az(Executor executor, h hVar, ak<d> akVar) {
        this.f2347a = (Executor) i.a(executor);
        this.f2348b = (h) i.a(hVar);
        this.f2349c = (ak) i.a(akVar);
    }

    @Override // com.facebook.imagepipeline.n.ak
    public void a(k<d> kVar, al alVar) {
        this.f2349c.a(new a(kVar, alVar), alVar);
    }

    /* compiled from: WebpTranscodeProducer */
    private class a extends n<d, d> {

        /* renamed from: b  reason: collision with root package name */
        private final al f2353b;

        /* renamed from: c  reason: collision with root package name */
        private e f2354c = e.UNSET;

        public a(k<d> kVar, al alVar) {
            super(kVar);
            this.f2353b = alVar;
        }

        /* access modifiers changed from: protected */
        public void a(d dVar, int i) {
            if (this.f2354c == e.UNSET && dVar != null) {
                this.f2354c = az.b(dVar);
            }
            if (this.f2354c == e.NO) {
                d().b(dVar, i);
            } else if (!a(i)) {
            } else {
                if (this.f2354c != e.YES || dVar == null) {
                    d().b(dVar, i);
                } else {
                    az.this.a(dVar, d(), this.f2353b);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(d dVar, k<d> kVar, al alVar) {
        i.a(dVar);
        final d a2 = d.a(dVar);
        this.f2347a.execute(new ar<d>(kVar, alVar.c(), "WebpTranscodeProducer", alVar.b()) {
            /* class com.facebook.imagepipeline.n.az.AnonymousClass1 */

            /* JADX INFO: finally extract failed */
            /* access modifiers changed from: protected */
            /* renamed from: d */
            public d c() {
                j a2 = az.this.f2348b.a();
                try {
                    az.b(a2, a2);
                    com.facebook.common.h.a a3 = com.facebook.common.h.a.a(a2.a());
                    try {
                        d dVar = new d(a3);
                        dVar.b(a2);
                        com.facebook.common.h.a.c(a3);
                        return dVar;
                    } catch (Throwable th) {
                        com.facebook.common.h.a.c(a3);
                        throw th;
                    }
                } finally {
                    a2.close();
                }
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void b(d dVar) {
                d.d(dVar);
            }

            /* access modifiers changed from: protected */
            /* renamed from: b */
            public void a(d dVar) {
                d.d(a2);
                super.a((Object) dVar);
            }

            /* access modifiers changed from: protected */
            @Override // com.facebook.common.b.e, com.facebook.imagepipeline.n.ar
            public void a(Exception exc) {
                d.d(a2);
                super.a(exc);
            }

            /* access modifiers changed from: protected */
            @Override // com.facebook.common.b.e, com.facebook.imagepipeline.n.ar
            public void b() {
                d.d(a2);
                super.b();
            }
        });
    }

    /* access modifiers changed from: private */
    public static e b(d dVar) {
        i.a(dVar);
        c c2 = com.facebook.g.d.c(dVar.d());
        if (b.b(c2)) {
            com.facebook.imagepipeline.nativecode.b a2 = com.facebook.imagepipeline.nativecode.c.a();
            if (a2 == null) {
                return e.NO;
            }
            return e.a(!a2.a(c2));
        } else if (c2 == c.f1991a) {
            return e.UNSET;
        } else {
            return e.NO;
        }
    }

    /* access modifiers changed from: private */
    public static void b(d dVar, j jVar) {
        InputStream d2 = dVar.d();
        c c2 = com.facebook.g.d.c(d2);
        if (c2 == b.e || c2 == b.g) {
            com.facebook.imagepipeline.nativecode.c.a().a(d2, jVar, 80);
            dVar.a(b.f1987a);
        } else if (c2 == b.f || c2 == b.h) {
            com.facebook.imagepipeline.nativecode.c.a().a(d2, jVar);
            dVar.a(b.f1988b);
        } else {
            throw new IllegalArgumentException("Wrong image format");
        }
    }
}
