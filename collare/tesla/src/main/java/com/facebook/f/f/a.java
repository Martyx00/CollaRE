package com.facebook.f.f;

import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PointF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.common.d.i;
import com.facebook.f.e.f;
import com.facebook.f.e.g;
import com.facebook.f.e.h;
import com.facebook.f.e.p;
import com.facebook.f.e.q;
import com.facebook.f.h.c;

/* compiled from: GenericDraweeHierarchy */
public class a implements c {

    /* renamed from: a  reason: collision with root package name */
    private final Drawable f1947a = new ColorDrawable(0);

    /* renamed from: b  reason: collision with root package name */
    private final Resources f1948b;

    /* renamed from: c  reason: collision with root package name */
    private d f1949c;

    /* renamed from: d  reason: collision with root package name */
    private final c f1950d;
    private final f e;
    private final g f;

    a(b bVar) {
        this.f1948b = bVar.a();
        this.f1949c = bVar.q();
        this.f = new g(this.f1947a);
        int i = 1;
        int size = (bVar.o() != null ? bVar.o().size() : 1) + (bVar.p() != null ? 1 : 0);
        Drawable[] drawableArr = new Drawable[(size + 6)];
        drawableArr[0] = b(bVar.n(), null);
        drawableArr[1] = b(bVar.c(), bVar.d());
        drawableArr[2] = a(this.f, bVar.k(), bVar.l(), bVar.m());
        drawableArr[3] = b(bVar.i(), bVar.j());
        drawableArr[4] = b(bVar.e(), bVar.f());
        drawableArr[5] = b(bVar.g(), bVar.h());
        if (size > 0) {
            if (bVar.o() != null) {
                i = 0;
                for (Drawable drawable : bVar.o()) {
                    drawableArr[i + 6] = b(drawable, null);
                    i++;
                }
            }
            if (bVar.p() != null) {
                drawableArr[i + 6] = b(bVar.p(), null);
            }
        }
        this.e = new f(drawableArr);
        this.e.c(bVar.b());
        this.f1950d = new c(e.a(this.e, this.f1949c));
        this.f1950d.mutate();
        e();
    }

    private Drawable a(Drawable drawable, q.b bVar, PointF pointF, ColorFilter colorFilter) {
        drawable.setColorFilter(colorFilter);
        return e.a(drawable, bVar, pointF);
    }

    private Drawable b(Drawable drawable, q.b bVar) {
        return e.a(e.a(drawable, this.f1949c, this.f1948b), bVar);
    }

    private void d() {
        this.f.a(this.f1947a);
    }

    private void e() {
        f fVar = this.e;
        if (fVar != null) {
            fVar.b();
            this.e.d();
            f();
            b(1);
            this.e.e();
            this.e.c();
        }
    }

    private void f() {
        c(1);
        c(2);
        c(3);
        c(4);
        c(5);
    }

    private void b(int i) {
        if (i >= 0) {
            this.e.d(i);
        }
    }

    private void c(int i) {
        if (i >= 0) {
            this.e.e(i);
        }
    }

    private void a(float f2) {
        Drawable a2 = this.e.a(3);
        if (a2 != null) {
            if (f2 >= 0.999f) {
                if (a2 instanceof Animatable) {
                    ((Animatable) a2).stop();
                }
                c(3);
            } else {
                if (a2 instanceof Animatable) {
                    ((Animatable) a2).start();
                }
                b(3);
            }
            a2.setLevel(Math.round(f2 * 10000.0f));
        }
    }

    @Override // com.facebook.f.h.b
    public Drawable a() {
        return this.f1950d;
    }

    @Override // com.facebook.f.h.c
    public void b() {
        d();
        e();
    }

    @Override // com.facebook.f.h.c
    public void a(Drawable drawable, float f2, boolean z) {
        Drawable a2 = e.a(drawable, this.f1949c, this.f1948b);
        a2.mutate();
        this.f.a(a2);
        this.e.b();
        f();
        b(2);
        a(f2);
        if (z) {
            this.e.e();
        }
        this.e.c();
    }

    @Override // com.facebook.f.h.c
    public void a(float f2, boolean z) {
        if (this.e.a(3) != null) {
            this.e.b();
            a(f2);
            if (z) {
                this.e.e();
            }
            this.e.c();
        }
    }

    @Override // com.facebook.f.h.c
    public void a(Throwable th) {
        this.e.b();
        f();
        if (this.e.a(5) != null) {
            b(5);
        } else {
            b(1);
        }
        this.e.c();
    }

    @Override // com.facebook.f.h.c
    public void b(Throwable th) {
        this.e.b();
        f();
        if (this.e.a(4) != null) {
            b(4);
        } else {
            b(1);
        }
        this.e.c();
    }

    @Override // com.facebook.f.h.c
    public void a(Drawable drawable) {
        this.f1950d.d(drawable);
    }

    private com.facebook.f.e.c d(int i) {
        com.facebook.f.e.c b2 = this.e.b(i);
        if (b2.a() instanceof h) {
            b2 = (h) b2.a();
        }
        return b2.a() instanceof p ? (p) b2.a() : b2;
    }

    private void a(int i, Drawable drawable) {
        if (drawable == null) {
            this.e.a(i, null);
            return;
        }
        d(i).a(e.a(drawable, this.f1949c, this.f1948b));
    }

    private p e(int i) {
        com.facebook.f.e.c d2 = d(i);
        if (d2 instanceof p) {
            return (p) d2;
        }
        return e.a(d2, q.b.f1943a);
    }

    public void a(int i) {
        this.e.c(i);
    }

    public void a(q.b bVar) {
        i.a(bVar);
        e(2).a(bVar);
    }

    public void a(Drawable drawable, q.b bVar) {
        a(1, drawable);
        e(1).a(bVar);
    }

    public void b(Drawable drawable) {
        a(0, drawable);
    }

    public void a(d dVar) {
        this.f1949c = dVar;
        e.a((com.facebook.f.e.c) this.f1950d, this.f1949c);
        for (int i = 0; i < this.e.a(); i++) {
            e.a(d(i), this.f1949c, this.f1948b);
        }
    }

    public d c() {
        return this.f1949c;
    }
}
