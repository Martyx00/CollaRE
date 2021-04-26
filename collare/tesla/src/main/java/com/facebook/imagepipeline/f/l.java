package com.facebook.imagepipeline.f;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import com.facebook.b.a.d;
import com.facebook.common.g.a;
import com.facebook.common.g.g;
import com.facebook.common.g.h;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.d.p;
import com.facebook.imagepipeline.h.c;
import com.facebook.imagepipeline.h.e;
import com.facebook.imagepipeline.j.b;
import com.facebook.imagepipeline.n.ab;
import com.facebook.imagepipeline.n.ac;
import com.facebook.imagepipeline.n.ad;
import com.facebook.imagepipeline.n.af;
import com.facebook.imagepipeline.n.ag;
import com.facebook.imagepipeline.n.ah;
import com.facebook.imagepipeline.n.ai;
import com.facebook.imagepipeline.n.aj;
import com.facebook.imagepipeline.n.ak;
import com.facebook.imagepipeline.n.ao;
import com.facebook.imagepipeline.n.ap;
import com.facebook.imagepipeline.n.as;
import com.facebook.imagepipeline.n.at;
import com.facebook.imagepipeline.n.au;
import com.facebook.imagepipeline.n.av;
import com.facebook.imagepipeline.n.aw;
import com.facebook.imagepipeline.n.ax;
import com.facebook.imagepipeline.n.az;
import com.facebook.imagepipeline.n.i;
import com.facebook.imagepipeline.n.j;
import com.facebook.imagepipeline.n.m;
import com.facebook.imagepipeline.n.o;
import com.facebook.imagepipeline.n.r;
import com.facebook.imagepipeline.n.s;
import com.facebook.imagepipeline.n.w;
import com.facebook.imagepipeline.n.x;
import com.facebook.imagepipeline.n.y;
import com.facebook.imagepipeline.n.z;

/* compiled from: ProducerFactory */
public class l {

    /* renamed from: a  reason: collision with root package name */
    private ContentResolver f2139a;

    /* renamed from: b  reason: collision with root package name */
    private Resources f2140b;

    /* renamed from: c  reason: collision with root package name */
    private AssetManager f2141c;

    /* renamed from: d  reason: collision with root package name */
    private final a f2142d;
    private final c e;
    private final e f;
    private final boolean g;
    private final boolean h;
    private final boolean i;
    private final e j;
    private final h k;
    private final com.facebook.imagepipeline.d.e l;
    private final com.facebook.imagepipeline.d.e m;
    private final p<d, g> n;
    private final p<d, b> o;
    private final f p;
    private final com.facebook.imagepipeline.c.f q;
    private final int r;
    private final int s;
    private boolean t;

    public l(Context context, a aVar, c cVar, e eVar, boolean z, boolean z2, boolean z3, e eVar2, h hVar, p<d, b> pVar, p<d, g> pVar2, com.facebook.imagepipeline.d.e eVar3, com.facebook.imagepipeline.d.e eVar4, f fVar, com.facebook.imagepipeline.c.f fVar2, int i2, int i3, boolean z4) {
        this.f2139a = context.getApplicationContext().getContentResolver();
        this.f2140b = context.getApplicationContext().getResources();
        this.f2141c = context.getApplicationContext().getAssets();
        this.f2142d = aVar;
        this.e = cVar;
        this.f = eVar;
        this.g = z;
        this.h = z2;
        this.i = z3;
        this.j = eVar2;
        this.k = hVar;
        this.o = pVar;
        this.n = pVar2;
        this.l = eVar3;
        this.m = eVar4;
        this.p = fVar;
        this.q = fVar2;
        this.r = i2;
        this.s = i3;
        this.t = z4;
    }

    public static com.facebook.imagepipeline.n.a a(ak<com.facebook.imagepipeline.j.d> akVar) {
        return new com.facebook.imagepipeline.n.a(akVar);
    }

    public com.facebook.imagepipeline.n.f b(ak<com.facebook.common.h.a<b>> akVar) {
        return new com.facebook.imagepipeline.n.f(this.o, this.p, akVar);
    }

    public com.facebook.imagepipeline.n.g c(ak<com.facebook.common.h.a<b>> akVar) {
        return new com.facebook.imagepipeline.n.g(this.p, akVar);
    }

    public com.facebook.imagepipeline.n.h d(ak<com.facebook.common.h.a<b>> akVar) {
        return new com.facebook.imagepipeline.n.h(this.o, this.p, akVar);
    }

    public static j a(ak<com.facebook.imagepipeline.j.d> akVar, ak<com.facebook.imagepipeline.j.d> akVar2) {
        return new j(akVar, akVar2);
    }

    public com.facebook.imagepipeline.n.l a() {
        return new com.facebook.imagepipeline.n.l(this.k);
    }

    public m e(ak<com.facebook.imagepipeline.j.d> akVar) {
        return new m(this.f2142d, this.j.c(), this.e, this.f, this.g, this.h, this.i, akVar);
    }

    public o f(ak<com.facebook.imagepipeline.j.d> akVar) {
        return new o(this.l, this.m, this.p, akVar);
    }

    public com.facebook.imagepipeline.n.p g(ak<com.facebook.imagepipeline.j.d> akVar) {
        return new com.facebook.imagepipeline.n.p(this.l, this.m, this.p, akVar);
    }

    public ah h(ak<com.facebook.imagepipeline.j.d> akVar) {
        return new ah(this.l, this.p, this.k, this.f2142d, akVar);
    }

    public r i(ak<com.facebook.imagepipeline.j.d> akVar) {
        return new r(this.p, akVar);
    }

    public s j(ak<com.facebook.imagepipeline.j.d> akVar) {
        return new s(this.n, this.p, akVar);
    }

    public w b() {
        return new w(this.j.a(), this.k, this.f2141c);
    }

    public x c() {
        return new x(this.j.a(), this.k, this.f2139a);
    }

    public y d() {
        return new y(this.j.a(), this.k, this.f2139a);
    }

    public z e() {
        return new z(this.j.a(), this.k, this.f2139a);
    }

    public aw a(ax<com.facebook.imagepipeline.j.d>[] axVarArr) {
        return new aw(axVarArr);
    }

    public ab f() {
        return new ab(this.j.a(), this.k);
    }

    public ao g() {
        return new ao(this.j.a(), this.k, this.f2139a);
    }

    public ac h() {
        return new ac(this.j.a(), this.k, this.f2140b);
    }

    public ad i() {
        return new ad(this.j.a(), this.f2139a);
    }

    public af a(ag agVar) {
        return new af(this.k, this.f2142d, agVar);
    }

    public ai k(ak<com.facebook.common.h.a<b>> akVar) {
        return new ai(this.o, this.p, akVar);
    }

    public aj l(ak<com.facebook.common.h.a<b>> akVar) {
        return new aj(akVar, this.q, this.j.d());
    }

    public ap a(ak<com.facebook.imagepipeline.j.d> akVar, boolean z, boolean z2) {
        return new ap(this.j.d(), this.k, z && !this.g, akVar, z2);
    }

    public static <T> as<T> m(ak<T> akVar) {
        return new as<>(akVar);
    }

    public <T> at<T> a(ak<T> akVar, au auVar) {
        return new at<>(akVar, auVar);
    }

    public <T> av<T> n(ak<T> akVar) {
        return new av<>(5, this.j.e(), akVar);
    }

    public az o(ak<com.facebook.imagepipeline.j.d> akVar) {
        return new az(this.j.d(), this.k, akVar);
    }

    public i p(ak<com.facebook.common.h.a<b>> akVar) {
        return new i(akVar, this.r, this.s, this.t);
    }
}
