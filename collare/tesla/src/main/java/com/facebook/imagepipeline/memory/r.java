package com.facebook.imagepipeline.memory;

import com.facebook.common.g.c;
import com.facebook.common.g.d;

/* compiled from: PoolConfig */
public class r {

    /* renamed from: a  reason: collision with root package name */
    private final t f2223a;

    /* renamed from: b  reason: collision with root package name */
    private final u f2224b;

    /* renamed from: c  reason: collision with root package name */
    private final t f2225c;

    /* renamed from: d  reason: collision with root package name */
    private final c f2226d;
    private final t e;
    private final u f;
    private final t g;
    private final u h;

    private r(a aVar) {
        t tVar;
        u uVar;
        t tVar2;
        c cVar;
        t tVar3;
        u uVar2;
        t tVar4;
        u uVar3;
        if (aVar.f2227a == null) {
            tVar = f.a();
        } else {
            tVar = aVar.f2227a;
        }
        this.f2223a = tVar;
        if (aVar.f2228b == null) {
            uVar = p.a();
        } else {
            uVar = aVar.f2228b;
        }
        this.f2224b = uVar;
        if (aVar.f2229c == null) {
            tVar2 = h.a();
        } else {
            tVar2 = aVar.f2229c;
        }
        this.f2225c = tVar2;
        if (aVar.f2230d == null) {
            cVar = d.a();
        } else {
            cVar = aVar.f2230d;
        }
        this.f2226d = cVar;
        if (aVar.e == null) {
            tVar3 = i.a();
        } else {
            tVar3 = aVar.e;
        }
        this.e = tVar3;
        if (aVar.f == null) {
            uVar2 = p.a();
        } else {
            uVar2 = aVar.f;
        }
        this.f = uVar2;
        if (aVar.g == null) {
            tVar4 = g.a();
        } else {
            tVar4 = aVar.g;
        }
        this.g = tVar4;
        if (aVar.h == null) {
            uVar3 = p.a();
        } else {
            uVar3 = aVar.h;
        }
        this.h = uVar3;
    }

    public t a() {
        return this.f2223a;
    }

    public u b() {
        return this.f2224b;
    }

    public c c() {
        return this.f2226d;
    }

    public t d() {
        return this.e;
    }

    public u e() {
        return this.f;
    }

    public t f() {
        return this.f2225c;
    }

    public t g() {
        return this.g;
    }

    public u h() {
        return this.h;
    }

    public static a i() {
        return new a();
    }

    /* compiled from: PoolConfig */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private t f2227a;

        /* renamed from: b  reason: collision with root package name */
        private u f2228b;

        /* renamed from: c  reason: collision with root package name */
        private t f2229c;

        /* renamed from: d  reason: collision with root package name */
        private c f2230d;
        private t e;
        private u f;
        private t g;
        private u h;

        private a() {
        }

        public r a() {
            return new r(this);
        }
    }
}
