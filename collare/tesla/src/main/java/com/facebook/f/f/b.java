package com.facebook.f.f;

import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import com.facebook.common.d.i;
import com.facebook.f.e.q;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.List;

/* compiled from: GenericDraweeHierarchyBuilder */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static final q.b f1951a = q.b.f;

    /* renamed from: b  reason: collision with root package name */
    public static final q.b f1952b = q.b.g;

    /* renamed from: c  reason: collision with root package name */
    private Resources f1953c;

    /* renamed from: d  reason: collision with root package name */
    private int f1954d;
    private float e;
    private Drawable f;
    private q.b g;
    private Drawable h;
    private q.b i;
    private Drawable j;
    private q.b k;
    private Drawable l;
    private q.b m;
    private q.b n;
    private Matrix o;
    private PointF p;
    private ColorFilter q;
    private Drawable r;
    private List<Drawable> s;
    private Drawable t;
    private d u;

    public b(Resources resources) {
        this.f1953c = resources;
        s();
    }

    public static b a(Resources resources) {
        return new b(resources);
    }

    private void s() {
        this.f1954d = 300;
        this.e = BitmapDescriptorFactory.HUE_RED;
        this.f = null;
        q.b bVar = f1951a;
        this.g = bVar;
        this.h = null;
        this.i = bVar;
        this.j = null;
        this.k = bVar;
        this.l = null;
        this.m = bVar;
        this.n = f1952b;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
    }

    public Resources a() {
        return this.f1953c;
    }

    public b a(int i2) {
        this.f1954d = i2;
        return this;
    }

    public int b() {
        return this.f1954d;
    }

    public Drawable c() {
        return this.f;
    }

    public q.b d() {
        return this.g;
    }

    public Drawable e() {
        return this.h;
    }

    public q.b f() {
        return this.i;
    }

    public Drawable g() {
        return this.j;
    }

    public q.b h() {
        return this.k;
    }

    public Drawable i() {
        return this.l;
    }

    public q.b j() {
        return this.m;
    }

    public b a(q.b bVar) {
        this.n = bVar;
        this.o = null;
        return this;
    }

    public q.b k() {
        return this.n;
    }

    public PointF l() {
        return this.p;
    }

    public ColorFilter m() {
        return this.q;
    }

    public Drawable n() {
        return this.r;
    }

    public List<Drawable> o() {
        return this.s;
    }

    public Drawable p() {
        return this.t;
    }

    public b a(d dVar) {
        this.u = dVar;
        return this;
    }

    public d q() {
        return this.u;
    }

    private void t() {
        List<Drawable> list = this.s;
        if (list != null) {
            for (Drawable drawable : list) {
                i.a(drawable);
            }
        }
    }

    public a r() {
        t();
        return new a(this);
    }
}
