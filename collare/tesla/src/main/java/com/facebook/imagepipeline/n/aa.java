package com.facebook.imagepipeline.n;

import com.facebook.common.g.h;
import com.facebook.common.h.a;
import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.o.b;
import java.io.InputStream;
import java.util.concurrent.Executor;

/* compiled from: LocalFetchProducer */
public abstract class aa implements ak<d> {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f2240a;

    /* renamed from: b  reason: collision with root package name */
    private final h f2241b;

    /* access modifiers changed from: protected */
    public abstract d a(b bVar);

    /* access modifiers changed from: protected */
    public abstract String a();

    protected aa(Executor executor, h hVar) {
        this.f2240a = executor;
        this.f2241b = hVar;
    }

    @Override // com.facebook.imagepipeline.n.ak
    public void a(k<d> kVar, al alVar) {
        final an c2 = alVar.c();
        final String b2 = alVar.b();
        final b a2 = alVar.a();
        final AnonymousClass1 r9 = new ar<d>(kVar, a(), c2, b2) {
            /* class com.facebook.imagepipeline.n.aa.AnonymousClass1 */

            /* access modifiers changed from: protected */
            /* renamed from: d */
            public d c() {
                d a2 = aa.this.a(a2);
                if (a2 == null) {
                    c2.a(b2, aa.this.a(), false);
                    return null;
                }
                a2.m();
                c2.a(b2, aa.this.a(), true);
                return a2;
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void b(d dVar) {
                d.d(dVar);
            }
        };
        alVar.a(new e() {
            /* class com.facebook.imagepipeline.n.aa.AnonymousClass2 */

            @Override // com.facebook.imagepipeline.n.am, com.facebook.imagepipeline.n.e
            public void a() {
                r9.a();
            }
        });
        this.f2240a.execute(r9);
    }

    /* access modifiers changed from: protected */
    public d a(InputStream inputStream, int i) {
        a aVar;
        if (i <= 0) {
            try {
                aVar = a.a(this.f2241b.a(inputStream));
            } catch (Throwable th) {
                com.facebook.common.d.b.a(inputStream);
                a.c(null);
                throw th;
            }
        } else {
            aVar = a.a(this.f2241b.a(inputStream, i));
        }
        d dVar = new d(aVar);
        com.facebook.common.d.b.a(inputStream);
        a.c(aVar);
        return dVar;
    }

    /* access modifiers changed from: protected */
    public d b(InputStream inputStream, int i) {
        return a(inputStream, i);
    }
}
