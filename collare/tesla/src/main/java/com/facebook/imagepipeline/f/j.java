package com.facebook.imagepipeline.f;

import android.content.Context;
import android.os.Build;
import android.support.v4.util.Pools;
import com.facebook.b.a.d;
import com.facebook.b.b.i;
import com.facebook.common.g.g;
import com.facebook.imagepipeline.a.a.a;
import com.facebook.imagepipeline.c.f;
import com.facebook.imagepipeline.d.e;
import com.facebook.imagepipeline.d.h;
import com.facebook.imagepipeline.d.l;
import com.facebook.imagepipeline.d.m;
import com.facebook.imagepipeline.d.o;
import com.facebook.imagepipeline.h.c;
import com.facebook.imagepipeline.j.b;
import com.facebook.imagepipeline.memory.s;
import com.facebook.imagepipeline.n.au;

/* compiled from: ImagePipelineFactory */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private static final Class<?> f2129a = j.class;

    /* renamed from: b  reason: collision with root package name */
    private static j f2130b;

    /* renamed from: c  reason: collision with root package name */
    private final au f2131c;

    /* renamed from: d  reason: collision with root package name */
    private final h f2132d;
    private h<d, b> e;
    private o<d, b> f;
    private h<d, g> g;
    private o<d, g> h;
    private e i;
    private i j;
    private c k;
    private g l;
    private l m;
    private m n;
    private e o;
    private i p;
    private f q;
    private com.facebook.imagepipeline.l.e r;
    private a s;

    public static j a() {
        return (j) com.facebook.common.d.i.a(f2130b, "ImagePipelineFactory was not initialized!");
    }

    public static synchronized void a(Context context) {
        synchronized (j.class) {
            a(h.a(context).a());
        }
    }

    public static synchronized void a(h hVar) {
        synchronized (j.class) {
            if (f2130b != null) {
                com.facebook.common.e.a.b(f2129a, "ImagePipelineFactory has already been initialized! `ImagePipelineFactory.initialize(...)` should only be called once to avoid unexpected behavior.");
            }
            f2130b = new j(hVar);
        }
    }

    public j(h hVar) {
        this.f2132d = (h) com.facebook.common.d.i.a(hVar);
        this.f2131c = new au(hVar.k().e());
    }

    private a l() {
        if (this.s == null) {
            this.s = com.facebook.imagepipeline.a.a.b.a(i(), this.f2132d.k(), b());
        }
        return this.s;
    }

    public com.facebook.imagepipeline.i.a b(Context context) {
        a l2 = l();
        if (l2 == null) {
            return null;
        }
        return l2.a(context);
    }

    public h<d, b> b() {
        if (this.e == null) {
            this.e = com.facebook.imagepipeline.d.a.a(this.f2132d.b(), this.f2132d.p(), this.f2132d.c());
        }
        return this.e;
    }

    public o<d, b> c() {
        if (this.f == null) {
            this.f = com.facebook.imagepipeline.d.b.a(b(), this.f2132d.l());
        }
        return this.f;
    }

    public h<d, g> d() {
        if (this.g == null) {
            this.g = l.a(this.f2132d.j(), this.f2132d.p());
        }
        return this.g;
    }

    public o<d, g> e() {
        if (this.h == null) {
            this.h = m.a(d(), this.f2132d.l());
        }
        return this.h;
    }

    private c m() {
        c cVar;
        if (this.k == null) {
            if (this.f2132d.m() != null) {
                this.k = this.f2132d.m();
            } else {
                a l2 = l();
                c cVar2 = null;
                if (l2 != null) {
                    cVar2 = l2.a(this.f2132d.a());
                    cVar = l2.b(this.f2132d.a());
                } else {
                    cVar = null;
                }
                if (this.f2132d.w() == null) {
                    this.k = new com.facebook.imagepipeline.h.b(cVar2, cVar, j());
                } else {
                    this.k = new com.facebook.imagepipeline.h.b(cVar2, cVar, j(), this.f2132d.w().a());
                    com.facebook.g.d.a().a(this.f2132d.w().b());
                }
            }
        }
        return this.k;
    }

    public e f() {
        if (this.i == null) {
            this.i = new e(g(), this.f2132d.r().e(), this.f2132d.r().f(), this.f2132d.k().a(), this.f2132d.k().b(), this.f2132d.l());
        }
        return this.i;
    }

    public i g() {
        if (this.j == null) {
            this.j = this.f2132d.g().a(this.f2132d.o());
        }
        return this.j;
    }

    public g h() {
        if (this.l == null) {
            this.l = new g(o(), this.f2132d.t(), this.f2132d.n(), c(), e(), f(), p(), this.f2132d.d(), this.f2131c, com.facebook.common.d.m.a(false), this.f2132d.x().l());
        }
        return this.l;
    }

    public static f a(s sVar, com.facebook.imagepipeline.l.e eVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            return new com.facebook.imagepipeline.c.a(sVar.a());
        }
        if (Build.VERSION.SDK_INT >= 11) {
            return new com.facebook.imagepipeline.c.e(new com.facebook.imagepipeline.c.b(sVar.e()), eVar);
        }
        return new com.facebook.imagepipeline.c.c();
    }

    public f i() {
        if (this.q == null) {
            this.q = a(this.f2132d.r(), j());
        }
        return this.q;
    }

    public static com.facebook.imagepipeline.l.e a(s sVar, boolean z) {
        if (Build.VERSION.SDK_INT >= 21) {
            int c2 = sVar.c();
            return new com.facebook.imagepipeline.l.a(sVar.a(), c2, new Pools.a(c2));
        } else if (!z || Build.VERSION.SDK_INT >= 19) {
            return new com.facebook.imagepipeline.l.d(sVar.b());
        } else {
            return new com.facebook.imagepipeline.l.c();
        }
    }

    public com.facebook.imagepipeline.l.e j() {
        if (this.r == null) {
            this.r = a(this.f2132d.r(), this.f2132d.x().b());
        }
        return this.r;
    }

    private l n() {
        if (this.m == null) {
            this.m = this.f2132d.x().j().a(this.f2132d.e(), this.f2132d.r().g(), m(), this.f2132d.s(), this.f2132d.h(), this.f2132d.u(), this.f2132d.x().c(), this.f2132d.k(), this.f2132d.r().e(), c(), e(), f(), p(), this.f2132d.d(), i(), this.f2132d.x().g(), this.f2132d.x().h(), this.f2132d.x().k());
        }
        return this.m;
    }

    private m o() {
        boolean z = Build.VERSION.SDK_INT >= 24 && this.f2132d.x().f();
        if (this.n == null) {
            this.n = new m(this.f2132d.e().getApplicationContext().getContentResolver(), n(), this.f2132d.q(), this.f2132d.u(), this.f2132d.x().b(), this.f2131c, this.f2132d.x().a(), z, this.f2132d.x().i(), this.f2132d.i());
        }
        return this.n;
    }

    public i k() {
        if (this.p == null) {
            this.p = this.f2132d.g().a(this.f2132d.v());
        }
        return this.p;
    }

    private e p() {
        if (this.o == null) {
            this.o = new e(k(), this.f2132d.r().e(), this.f2132d.r().f(), this.f2132d.k().a(), this.f2132d.k().b(), this.f2132d.l());
        }
        return this.o;
    }
}
