package com.facebook.imagepipeline.f;

import android.content.ContentResolver;
import android.net.Uri;
import com.facebook.common.d.i;
import com.facebook.common.h.a;
import com.facebook.common.l.c;
import com.facebook.imagepipeline.j.b;
import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.n.ag;
import com.facebook.imagepipeline.n.ak;
import com.facebook.imagepipeline.n.au;
import com.facebook.imagepipeline.n.av;
import com.facebook.imagepipeline.n.ax;
import com.facebook.imagepipeline.n.p;
import com.facebook.imagepipeline.o.b;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ProducerSequenceFactory */
public class m {

    /* renamed from: a  reason: collision with root package name */
    ak<a<b>> f2143a;

    /* renamed from: b  reason: collision with root package name */
    ak<d> f2144b;

    /* renamed from: c  reason: collision with root package name */
    ak<d> f2145c;

    /* renamed from: d  reason: collision with root package name */
    ak<Void> f2146d;
    ak<Void> e;
    ak<a<b>> f;
    ak<a<b>> g;
    ak<a<b>> h;
    ak<a<b>> i;
    ak<a<b>> j;
    ak<a<b>> k;
    ak<a<b>> l;
    Map<ak<a<b>>, ak<a<b>>> m = new HashMap();
    Map<ak<a<b>>, ak<Void>> n = new HashMap();
    Map<ak<a<b>>, ak<a<b>>> o = new HashMap();
    private final ContentResolver p;
    private final l q;
    private final ag r;
    private final boolean s;
    private final boolean t;
    private final boolean u;
    private final au v;
    private final boolean w;
    private final boolean x;
    private final boolean y;
    private ak<d> z;

    public m(ContentResolver contentResolver, l lVar, ag agVar, boolean z2, boolean z3, au auVar, boolean z4, boolean z5, boolean z6, boolean z7) {
        this.p = contentResolver;
        this.q = lVar;
        this.r = agVar;
        this.s = z2;
        this.t = z3;
        this.v = auVar;
        this.w = z4;
        this.x = z5;
        this.u = z6;
        this.y = z7;
    }

    public ak<Void> a(com.facebook.imagepipeline.o.b bVar) {
        c(bVar);
        int c2 = bVar.c();
        if (c2 == 0) {
            return c();
        }
        switch (c2) {
            case 2:
            case 3:
                return e();
            default:
                Uri b2 = bVar.b();
                throw new IllegalArgumentException("Unsupported uri scheme for encoded image fetch! Uri is: " + a(b2));
        }
    }

    private static void c(com.facebook.imagepipeline.o.b bVar) {
        i.a(bVar);
        i.a(bVar.m().a() <= b.EnumC0051b.ENCODED_MEMORY_CACHE.a());
    }

    public ak<a<com.facebook.imagepipeline.j.b>> b(com.facebook.imagepipeline.o.b bVar) {
        ak<a<com.facebook.imagepipeline.j.b>> d2 = d(bVar);
        if (bVar.q() != null) {
            d2 = f(d2);
        }
        return this.x ? g(d2) : d2;
    }

    private ak<a<com.facebook.imagepipeline.j.b>> d(com.facebook.imagepipeline.o.b bVar) {
        i.a(bVar);
        Uri b2 = bVar.b();
        i.a(b2, "Uri is null.");
        int c2 = bVar.c();
        if (c2 == 0) {
            return a();
        }
        switch (c2) {
            case 2:
                return h();
            case 3:
                return g();
            case 4:
                if (com.facebook.common.f.a.a(this.p.getType(b2))) {
                    return h();
                }
                return i();
            case 5:
                return l();
            case 6:
                return k();
            case 7:
                return m();
            case 8:
                return j();
            default:
                throw new IllegalArgumentException("Unsupported uri scheme! Uri is: " + a(b2));
        }
    }

    private synchronized ak<a<com.facebook.imagepipeline.j.b>> a() {
        if (this.f2143a == null) {
            this.f2143a = b(d());
        }
        return this.f2143a;
    }

    private synchronized ak<d> b() {
        if (this.f2145c == null) {
            this.f2145c = this.q.a(d(), this.v);
        }
        return this.f2145c;
    }

    private synchronized ak<Void> c() {
        if (this.e == null) {
            this.e = l.m(b());
        }
        return this.e;
    }

    private synchronized ak<d> d() {
        if (this.z == null) {
            this.z = l.a(c(this.q.a(this.r)));
            this.z = this.q.a(this.z, this.s, this.w);
        }
        return this.z;
    }

    private synchronized ak<Void> e() {
        if (this.f2146d == null) {
            this.f2146d = l.m(f());
        }
        return this.f2146d;
    }

    private synchronized ak<d> f() {
        if (this.f2144b == null) {
            this.f2144b = this.q.a(c(this.q.f()), this.v);
        }
        return this.f2144b;
    }

    private synchronized ak<a<com.facebook.imagepipeline.j.b>> g() {
        if (this.f == null) {
            this.f = a(this.q.f());
        }
        return this.f;
    }

    private synchronized ak<a<com.facebook.imagepipeline.j.b>> h() {
        if (this.g == null) {
            this.g = e(this.q.i());
        }
        return this.g;
    }

    private synchronized ak<a<com.facebook.imagepipeline.j.b>> i() {
        if (this.h == null) {
            this.h = a(this.q.c(), new ax[]{this.q.d(), this.q.e()});
        }
        return this.h;
    }

    private synchronized ak<a<com.facebook.imagepipeline.j.b>> j() {
        if (this.l == null) {
            this.l = a(this.q.g());
        }
        return this.l;
    }

    private synchronized ak<a<com.facebook.imagepipeline.j.b>> k() {
        if (this.i == null) {
            this.i = a(this.q.h());
        }
        return this.i;
    }

    private synchronized ak<a<com.facebook.imagepipeline.j.b>> l() {
        if (this.j == null) {
            this.j = a(this.q.b());
        }
        return this.j;
    }

    private synchronized ak<a<com.facebook.imagepipeline.j.b>> m() {
        if (this.k == null) {
            ak<d> a2 = this.q.a();
            if (c.f1793a && (!this.t || c.f1796d == null)) {
                a2 = this.q.o(a2);
            }
            l lVar = this.q;
            this.k = b(this.q.a(l.a(a2), true, this.w));
        }
        return this.k;
    }

    private ak<a<com.facebook.imagepipeline.j.b>> a(ak<d> akVar) {
        return a(akVar, new ax[]{this.q.e()});
    }

    private ak<a<com.facebook.imagepipeline.j.b>> a(ak<d> akVar, ax<d>[] axVarArr) {
        return b(b(c(akVar), axVarArr));
    }

    private ak<a<com.facebook.imagepipeline.j.b>> b(ak<d> akVar) {
        return e(this.q.e(akVar));
    }

    private ak<d> c(ak<d> akVar) {
        if (c.f1793a && (!this.t || c.f1796d == null)) {
            akVar = this.q.o(akVar);
        }
        if (this.y) {
            akVar = d(akVar);
        }
        return this.q.i(this.q.j(akVar));
    }

    private ak<d> d(ak<d> akVar) {
        p pVar;
        if (this.u) {
            pVar = this.q.g(this.q.h(akVar));
        } else {
            pVar = this.q.g(akVar);
        }
        return this.q.f(pVar);
    }

    private ak<a<com.facebook.imagepipeline.j.b>> e(ak<a<com.facebook.imagepipeline.j.b>> akVar) {
        return this.q.b(this.q.a(this.q.c(this.q.d(akVar)), this.v));
    }

    private ak<d> b(ak<d> akVar, ax<d>[] axVarArr) {
        av n2 = this.q.n(this.q.a(l.a(akVar), true, this.w));
        l lVar = this.q;
        return l.a(a(axVarArr), n2);
    }

    private ak<d> a(ax<d>[] axVarArr) {
        return this.q.a(this.q.a(axVarArr), true, this.w);
    }

    private synchronized ak<a<com.facebook.imagepipeline.j.b>> f(ak<a<com.facebook.imagepipeline.j.b>> akVar) {
        if (!this.m.containsKey(akVar)) {
            this.m.put(akVar, this.q.k(this.q.l(akVar)));
        }
        return this.m.get(akVar);
    }

    private synchronized ak<a<com.facebook.imagepipeline.j.b>> g(ak<a<com.facebook.imagepipeline.j.b>> akVar) {
        ak<a<com.facebook.imagepipeline.j.b>> akVar2;
        akVar2 = this.o.get(akVar);
        if (akVar2 == null) {
            akVar2 = this.q.p(akVar);
            this.o.put(akVar, akVar2);
        }
        return akVar2;
    }

    private static String a(Uri uri) {
        String valueOf = String.valueOf(uri);
        if (valueOf.length() <= 30) {
            return valueOf;
        }
        return valueOf.substring(0, 30) + "...";
    }
}
