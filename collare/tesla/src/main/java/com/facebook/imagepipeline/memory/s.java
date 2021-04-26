package com.facebook.imagepipeline.memory;

import com.facebook.common.d.i;
import com.facebook.common.g.a;
import com.facebook.common.g.h;
import com.facebook.common.g.k;

/* compiled from: PoolFactory */
public class s {

    /* renamed from: a  reason: collision with root package name */
    private final r f2231a;

    /* renamed from: b  reason: collision with root package name */
    private d f2232b;

    /* renamed from: c  reason: collision with root package name */
    private j f2233c;

    /* renamed from: d  reason: collision with root package name */
    private l f2234d;
    private h e;
    private k f;
    private a g;

    public s(r rVar) {
        this.f2231a = (r) i.a(rVar);
    }

    public d a() {
        if (this.f2232b == null) {
            this.f2232b = new d(this.f2231a.c(), this.f2231a.a(), this.f2231a.b());
        }
        return this.f2232b;
    }

    public j b() {
        if (this.f2233c == null) {
            this.f2233c = new j(this.f2231a.c(), this.f2231a.f());
        }
        return this.f2233c;
    }

    public int c() {
        return this.f2231a.f().g;
    }

    public l d() {
        if (this.f2234d == null) {
            this.f2234d = new l(this.f2231a.c(), this.f2231a.d(), this.f2231a.e());
        }
        return this.f2234d;
    }

    public h e() {
        if (this.e == null) {
            this.e = new n(d(), f());
        }
        return this.e;
    }

    public k f() {
        if (this.f == null) {
            this.f = new k(g());
        }
        return this.f;
    }

    public a g() {
        if (this.g == null) {
            this.g = new k(this.f2231a.c(), this.f2231a.g(), this.f2231a.h());
        }
        return this.g;
    }
}
