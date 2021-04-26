package com.facebook.imagepipeline.n;

import android.os.SystemClock;
import com.facebook.common.g.a;
import com.facebook.common.g.h;
import com.facebook.common.g.j;
import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.n.ag;
import java.io.InputStream;
import java.util.Map;

/* compiled from: NetworkFetchProducer */
public class af implements ak<d> {

    /* renamed from: a  reason: collision with root package name */
    private final h f2264a;

    /* renamed from: b  reason: collision with root package name */
    private final a f2265b;

    /* renamed from: c  reason: collision with root package name */
    private final ag f2266c;

    public af(h hVar, a aVar, ag agVar) {
        this.f2264a = hVar;
        this.f2265b = aVar;
        this.f2266c = agVar;
    }

    @Override // com.facebook.imagepipeline.n.ak
    public void a(k<d> kVar, al alVar) {
        alVar.c().a(alVar.b(), "NetworkFetchProducer");
        final t b2 = this.f2266c.b(kVar, alVar);
        this.f2266c.a(b2, new ag.a() {
            /* class com.facebook.imagepipeline.n.af.AnonymousClass1 */

            @Override // com.facebook.imagepipeline.n.ag.a
            public void a(InputStream inputStream, int i) {
                af.this.a(b2, inputStream, i);
            }

            @Override // com.facebook.imagepipeline.n.ag.a
            public void a(Throwable th) {
                af.this.a((af) b2, (t) th);
            }

            @Override // com.facebook.imagepipeline.n.ag.a
            public void a() {
                af.this.a(b2);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void a(t tVar, InputStream inputStream, int i) {
        j jVar;
        if (i > 0) {
            jVar = this.f2264a.a(i);
        } else {
            jVar = this.f2264a.a();
        }
        byte[] bArr = (byte[]) this.f2265b.a(16384);
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read < 0) {
                    this.f2266c.b(tVar, jVar.b());
                    b(jVar, tVar);
                    return;
                } else if (read > 0) {
                    jVar.write(bArr, 0, read);
                    a(jVar, tVar);
                    tVar.a().b(a(jVar.b(), i));
                }
            } finally {
                this.f2265b.a(bArr);
                jVar.close();
            }
        }
    }

    protected static float a(int i, int i2) {
        return i2 > 0 ? ((float) i) / ((float) i2) : 1.0f - ((float) Math.exp(((double) (-i)) / 50000.0d));
    }

    /* access modifiers changed from: protected */
    public void a(j jVar, t tVar) {
        long uptimeMillis = SystemClock.uptimeMillis();
        if (b(tVar) && uptimeMillis - tVar.f() >= 100) {
            tVar.a(uptimeMillis);
            tVar.d().a(tVar.c(), "NetworkFetchProducer", "intermediate_result");
            a(jVar, tVar.g(), tVar.h(), tVar.a());
        }
    }

    /* access modifiers changed from: protected */
    public void b(j jVar, t tVar) {
        Map<String, String> a2 = a(tVar, jVar.b());
        an d2 = tVar.d();
        d2.a(tVar.c(), "NetworkFetchProducer", a2);
        d2.a(tVar.c(), "NetworkFetchProducer", true);
        a(jVar, tVar.g() | 1, tVar.h(), tVar.a());
    }

    private void a(j jVar, int i, com.facebook.imagepipeline.e.a aVar, k<d> kVar) {
        Throwable th;
        d dVar;
        com.facebook.common.h.a a2 = com.facebook.common.h.a.a(jVar.a());
        try {
            dVar = new d(a2);
            try {
                dVar.a(aVar);
                dVar.m();
                kVar.b(dVar, i);
                d.d(dVar);
                com.facebook.common.h.a.c(a2);
            } catch (Throwable th2) {
                th = th2;
                d.d(dVar);
                com.facebook.common.h.a.c(a2);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            dVar = null;
            d.d(dVar);
            com.facebook.common.h.a.c(a2);
            throw th;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(t tVar, Throwable th) {
        tVar.d().a(tVar.c(), "NetworkFetchProducer", th, null);
        tVar.d().a(tVar.c(), "NetworkFetchProducer", false);
        tVar.a().b(th);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(t tVar) {
        tVar.d().b(tVar.c(), "NetworkFetchProducer", null);
        tVar.a().b();
    }

    private boolean b(t tVar) {
        if (!tVar.b().h()) {
            return false;
        }
        return this.f2266c.a(tVar);
    }

    private Map<String, String> a(t tVar, int i) {
        if (!tVar.d().b(tVar.c())) {
            return null;
        }
        return this.f2266c.a(tVar, i);
    }
}
