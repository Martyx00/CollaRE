package com.facebook.imagepipeline.f;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import com.facebook.common.d.l;
import com.facebook.common.l.b;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.d.h;
import com.facebook.imagepipeline.d.i;
import com.facebook.imagepipeline.d.j;
import com.facebook.imagepipeline.d.k;
import com.facebook.imagepipeline.d.n;
import com.facebook.imagepipeline.d.q;
import com.facebook.imagepipeline.d.t;
import com.facebook.imagepipeline.f.i;
import com.facebook.imagepipeline.h.c;
import com.facebook.imagepipeline.h.d;
import com.facebook.imagepipeline.h.e;
import com.facebook.imagepipeline.h.g;
import com.facebook.imagepipeline.memory.r;
import com.facebook.imagepipeline.memory.s;
import com.facebook.imagepipeline.n.ag;
import com.facebook.imagepipeline.n.u;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* compiled from: ImagePipelineConfig */
public class h {
    private static b z = new b();

    /* renamed from: a  reason: collision with root package name */
    private final Bitmap.Config f2111a;

    /* renamed from: b  reason: collision with root package name */
    private final l<q> f2112b;

    /* renamed from: c  reason: collision with root package name */
    private final h.a f2113c;

    /* renamed from: d  reason: collision with root package name */
    private final f f2114d;
    private final Context e;
    private final boolean f;
    private final f g;
    private final l<q> h;
    private final e i;
    private final n j;
    private final c k;
    private final l<Boolean> l;
    private final com.facebook.b.b.c m;
    private final com.facebook.common.g.c n;
    private final ag o;
    private final int p;
    private final com.facebook.imagepipeline.c.f q;
    private final s r;
    private final e s;
    private final Set<com.facebook.imagepipeline.k.c> t;
    private final boolean u;
    private final com.facebook.b.b.c v;
    private final d w;
    private final i x;
    private final boolean y;

    private h(a aVar) {
        l<q> lVar;
        h.a aVar2;
        Bitmap.Config config;
        f fVar;
        f fVar2;
        l<q> lVar2;
        n nVar;
        l<Boolean> lVar3;
        com.facebook.b.b.c cVar;
        com.facebook.common.g.c cVar2;
        int i2;
        ag agVar;
        s sVar;
        e eVar;
        Set<com.facebook.imagepipeline.k.c> set;
        com.facebook.b.b.c cVar3;
        e eVar2;
        com.facebook.common.l.b a2;
        this.x = aVar.x.a();
        if (aVar.f2117b == null) {
            lVar = new i((ActivityManager) aVar.e.getSystemService("activity"));
        } else {
            lVar = aVar.f2117b;
        }
        this.f2112b = lVar;
        if (aVar.f2118c == null) {
            aVar2 = new com.facebook.imagepipeline.d.d();
        } else {
            aVar2 = aVar.f2118c;
        }
        this.f2113c = aVar2;
        if (aVar.f2116a == null) {
            config = Bitmap.Config.ARGB_8888;
        } else {
            config = aVar.f2116a;
        }
        this.f2111a = config;
        if (aVar.f2119d == null) {
            fVar = j.a();
        } else {
            fVar = aVar.f2119d;
        }
        this.f2114d = fVar;
        this.e = (Context) com.facebook.common.d.i.a(aVar.e);
        if (aVar.u == null) {
            fVar2 = new b(new d());
        } else {
            fVar2 = aVar.u;
        }
        this.g = fVar2;
        this.f = aVar.f;
        if (aVar.g == null) {
            lVar2 = new k();
        } else {
            lVar2 = aVar.g;
        }
        this.h = lVar2;
        if (aVar.i == null) {
            nVar = t.i();
        } else {
            nVar = aVar.i;
        }
        this.j = nVar;
        this.k = aVar.j;
        if (aVar.k == null) {
            lVar3 = new l<Boolean>() {
                /* class com.facebook.imagepipeline.f.h.AnonymousClass1 */

                /* renamed from: a */
                public Boolean b() {
                    return true;
                }
            };
        } else {
            lVar3 = aVar.k;
        }
        this.l = lVar3;
        if (aVar.l == null) {
            cVar = b(aVar.e);
        } else {
            cVar = aVar.l;
        }
        this.m = cVar;
        if (aVar.m == null) {
            cVar2 = com.facebook.common.g.d.a();
        } else {
            cVar2 = aVar.m;
        }
        this.n = cVar2;
        if (aVar.w < 0) {
            i2 = 30000;
        } else {
            i2 = aVar.w;
        }
        this.p = i2;
        if (aVar.n == null) {
            agVar = new u(this.p);
        } else {
            agVar = aVar.n;
        }
        this.o = agVar;
        this.q = aVar.o;
        if (aVar.p == null) {
            sVar = new s(r.i().a());
        } else {
            sVar = aVar.p;
        }
        this.r = sVar;
        if (aVar.q == null) {
            eVar = new g();
        } else {
            eVar = aVar.q;
        }
        this.s = eVar;
        if (aVar.r == null) {
            set = new HashSet<>();
        } else {
            set = aVar.r;
        }
        this.t = set;
        this.u = aVar.s;
        if (aVar.t == null) {
            cVar3 = this.m;
        } else {
            cVar3 = aVar.t;
        }
        this.v = cVar3;
        this.w = aVar.v;
        int c2 = this.r.c();
        if (aVar.h == null) {
            eVar2 = new a(c2);
        } else {
            eVar2 = aVar.h;
        }
        this.i = eVar2;
        this.y = aVar.y;
        com.facebook.common.l.b e2 = this.x.e();
        if (e2 != null) {
            a(e2, this.x, new com.facebook.imagepipeline.c.d(r()));
        } else if (this.x.b() && com.facebook.common.l.c.f1793a && (a2 = com.facebook.common.l.c.a()) != null) {
            a(a2, this.x, new com.facebook.imagepipeline.c.d(r()));
        }
    }

    private static void a(com.facebook.common.l.b bVar, i iVar, com.facebook.common.l.a aVar) {
        com.facebook.common.l.c.f1796d = bVar;
        b.a d2 = iVar.d();
        if (d2 != null) {
            bVar.a(d2);
        }
        if (aVar != null) {
            bVar.a(aVar);
        }
    }

    private static com.facebook.b.b.c b(Context context) {
        return com.facebook.b.b.c.a(context).a();
    }

    public Bitmap.Config a() {
        return this.f2111a;
    }

    public l<q> b() {
        return this.f2112b;
    }

    public h.a c() {
        return this.f2113c;
    }

    public f d() {
        return this.f2114d;
    }

    public Context e() {
        return this.e;
    }

    public static b f() {
        return z;
    }

    public f g() {
        return this.g;
    }

    public boolean h() {
        return this.f;
    }

    public boolean i() {
        return this.y;
    }

    public l<q> j() {
        return this.h;
    }

    public e k() {
        return this.i;
    }

    public n l() {
        return this.j;
    }

    public c m() {
        return this.k;
    }

    public l<Boolean> n() {
        return this.l;
    }

    public com.facebook.b.b.c o() {
        return this.m;
    }

    public com.facebook.common.g.c p() {
        return this.n;
    }

    public ag q() {
        return this.o;
    }

    public s r() {
        return this.r;
    }

    public e s() {
        return this.s;
    }

    public Set<com.facebook.imagepipeline.k.c> t() {
        return Collections.unmodifiableSet(this.t);
    }

    public boolean u() {
        return this.u;
    }

    public com.facebook.b.b.c v() {
        return this.v;
    }

    public d w() {
        return this.w;
    }

    public i x() {
        return this.x;
    }

    public static a a(Context context) {
        return new a(context);
    }

    /* compiled from: ImagePipelineConfig */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private boolean f2120a;

        private b() {
            this.f2120a = false;
        }

        public boolean a() {
            return this.f2120a;
        }
    }

    /* compiled from: ImagePipelineConfig */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private Bitmap.Config f2116a;

        /* renamed from: b  reason: collision with root package name */
        private l<q> f2117b;

        /* renamed from: c  reason: collision with root package name */
        private h.a f2118c;

        /* renamed from: d  reason: collision with root package name */
        private f f2119d;
        private final Context e;
        private boolean f;
        private l<q> g;
        private e h;
        private n i;
        private c j;
        private l<Boolean> k;
        private com.facebook.b.b.c l;
        private com.facebook.common.g.c m;
        private ag n;
        private com.facebook.imagepipeline.c.f o;
        private s p;
        private e q;
        private Set<com.facebook.imagepipeline.k.c> r;
        private boolean s;
        private com.facebook.b.b.c t;
        private f u;
        private d v;
        private int w;
        private final i.a x;
        private boolean y;

        private a(Context context) {
            this.f = false;
            this.s = true;
            this.w = -1;
            this.x = new i.a(this);
            this.y = true;
            this.e = (Context) com.facebook.common.d.i.a(context);
        }

        public a a(boolean z) {
            this.f = z;
            return this;
        }

        public a a(ag agVar) {
            this.n = agVar;
            return this;
        }

        public a a(Set<com.facebook.imagepipeline.k.c> set) {
            this.r = set;
            return this;
        }

        public h a() {
            return new h(this);
        }
    }
}
