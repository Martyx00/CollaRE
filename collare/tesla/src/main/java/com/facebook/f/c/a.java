package com.facebook.f.c;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import com.facebook.common.d.h;
import com.facebook.common.d.i;
import com.facebook.f.b.a;
import com.facebook.f.b.b;
import com.facebook.f.b.c;
import com.facebook.f.g.a;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.concurrent.Executor;

/* compiled from: AbstractDraweeController */
public abstract class a<T, INFO> implements a.AbstractC0044a, a.AbstractC0046a, com.facebook.f.h.a {

    /* renamed from: a  reason: collision with root package name */
    private static final Class<?> f1870a = a.class;

    /* renamed from: b  reason: collision with root package name */
    private final b f1871b = b.a();

    /* renamed from: c  reason: collision with root package name */
    private final com.facebook.f.b.a f1872c;

    /* renamed from: d  reason: collision with root package name */
    private final Executor f1873d;
    private c e;
    private com.facebook.f.g.a f;
    private d<INFO> g;
    private e h;
    private com.facebook.f.h.c i;
    private Drawable j;
    private String k;
    private Object l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private String r;
    private com.facebook.c.c<T> s;
    private T t;
    private Drawable u;
    private boolean v = true;

    /* access modifiers changed from: protected */
    public abstract void a(Drawable drawable);

    /* access modifiers changed from: protected */
    public abstract void a(T t2);

    /* access modifiers changed from: protected */
    public void a(String str, T t2) {
    }

    /* access modifiers changed from: protected */
    public abstract com.facebook.c.c<T> c();

    /* access modifiers changed from: protected */
    public abstract INFO c(T t2);

    /* access modifiers changed from: protected */
    public abstract Drawable d(T t2);

    /* access modifiers changed from: protected */
    public T e() {
        return null;
    }

    /* access modifiers changed from: private */
    /* renamed from: com.facebook.f.c.a$a  reason: collision with other inner class name */
    /* compiled from: AbstractDraweeController */
    public static class C0045a<INFO> extends f<INFO> {
        private C0045a() {
        }

        public static <INFO> C0045a<INFO> a(d<? super INFO> dVar, d<? super INFO> dVar2) {
            C0045a<INFO> aVar = new C0045a<>();
            aVar.a(dVar);
            aVar.a(dVar2);
            return aVar;
        }
    }

    public a(com.facebook.f.b.a aVar, Executor executor, String str, Object obj) {
        this.f1872c = aVar;
        this.f1873d = executor;
        c(str, obj);
    }

    /* access modifiers changed from: protected */
    public void b(String str, Object obj) {
        c(str, obj);
        this.v = false;
    }

    private synchronized void c(String str, Object obj) {
        this.f1871b.a(b.a.ON_INIT_CONTROLLER);
        if (!this.v && this.f1872c != null) {
            this.f1872c.b(this);
        }
        this.m = false;
        this.o = false;
        a();
        this.q = false;
        if (this.e != null) {
            this.e.a();
        }
        if (this.f != null) {
            this.f.a();
            this.f.a(this);
        }
        if (this.g instanceof C0045a) {
            ((C0045a) this.g).a();
        } else {
            this.g = null;
        }
        this.h = null;
        if (this.i != null) {
            this.i.b();
            this.i.a((Drawable) null);
            this.i = null;
        }
        this.j = null;
        if (com.facebook.common.e.a.a(2)) {
            com.facebook.common.e.a.a(f1870a, "controller %x %s -> %s: initialize", Integer.valueOf(System.identityHashCode(this)), this.k, str);
        }
        this.k = str;
        this.l = obj;
    }

    @Override // com.facebook.f.b.a.AbstractC0044a
    public void f() {
        this.f1871b.a(b.a.ON_RELEASE_CONTROLLER);
        c cVar = this.e;
        if (cVar != null) {
            cVar.b();
        }
        com.facebook.f.g.a aVar = this.f;
        if (aVar != null) {
            aVar.b();
        }
        com.facebook.f.h.c cVar2 = this.i;
        if (cVar2 != null) {
            cVar2.b();
        }
        a();
    }

    private void a() {
        boolean z = this.n;
        this.n = false;
        this.p = false;
        com.facebook.c.c<T> cVar = this.s;
        if (cVar != null) {
            cVar.h();
            this.s = null;
        }
        Drawable drawable = this.u;
        if (drawable != null) {
            a(drawable);
        }
        if (this.r != null) {
            this.r = null;
        }
        this.u = null;
        T t2 = this.t;
        if (t2 != null) {
            d("release", t2);
            a((Object) this.t);
            this.t = null;
        }
        if (z) {
            j().a(this.k);
        }
    }

    public String g() {
        return this.k;
    }

    /* access modifiers changed from: protected */
    public c h() {
        if (this.e == null) {
            this.e = new c();
        }
        return this.e;
    }

    /* access modifiers changed from: protected */
    public com.facebook.f.g.a i() {
        return this.f;
    }

    /* access modifiers changed from: protected */
    public void a(com.facebook.f.g.a aVar) {
        this.f = aVar;
        com.facebook.f.g.a aVar2 = this.f;
        if (aVar2 != null) {
            aVar2.a(this);
        }
    }

    /* access modifiers changed from: protected */
    public void b(boolean z) {
        this.q = z;
    }

    public void a(String str) {
        this.r = str;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.facebook.f.c.d<? super INFO> */
    /* JADX WARN: Multi-variable type inference failed */
    public void a(d<? super INFO> dVar) {
        i.a(dVar);
        d<INFO> dVar2 = this.g;
        if (dVar2 instanceof C0045a) {
            ((C0045a) dVar2).a(dVar);
        } else if (dVar2 != null) {
            this.g = C0045a.a(dVar2, dVar);
        } else {
            this.g = dVar;
        }
    }

    public void b(d<? super INFO> dVar) {
        i.a(dVar);
        d<INFO> dVar2 = this.g;
        if (dVar2 instanceof C0045a) {
            ((C0045a) dVar2).b(dVar);
        } else if (dVar2 == dVar) {
            this.g = null;
        }
    }

    /* access modifiers changed from: protected */
    public d<INFO> j() {
        d<INFO> dVar = this.g;
        return dVar == null ? c.a() : dVar;
    }

    public void a(e eVar) {
        this.h = eVar;
    }

    @Override // com.facebook.f.h.a
    public com.facebook.f.h.b k() {
        return this.i;
    }

    @Override // com.facebook.f.h.a
    public void a(com.facebook.f.h.b bVar) {
        if (com.facebook.common.e.a.a(2)) {
            com.facebook.common.e.a.a(f1870a, "controller %x %s: setHierarchy: %s", Integer.valueOf(System.identityHashCode(this)), this.k, bVar);
        }
        this.f1871b.a(bVar != null ? b.a.ON_SET_HIERARCHY : b.a.ON_CLEAR_HIERARCHY);
        if (this.n) {
            this.f1872c.b(this);
            f();
        }
        com.facebook.f.h.c cVar = this.i;
        if (cVar != null) {
            cVar.a((Drawable) null);
            this.i = null;
        }
        if (bVar != null) {
            i.a(bVar instanceof com.facebook.f.h.c);
            this.i = (com.facebook.f.h.c) bVar;
            this.i.a(this.j);
        }
    }

    /* access modifiers changed from: protected */
    public void b(Drawable drawable) {
        this.j = drawable;
        com.facebook.f.h.c cVar = this.i;
        if (cVar != null) {
            cVar.a(this.j);
        }
    }

    /* access modifiers changed from: protected */
    public Drawable l() {
        return this.j;
    }

    @Override // com.facebook.f.h.a
    public void m() {
        if (com.facebook.common.e.a.a(2)) {
            com.facebook.common.e.a.a(f1870a, "controller %x %s: onAttach: %s", Integer.valueOf(System.identityHashCode(this)), this.k, this.n ? "request already submitted" : "request needs submit");
        }
        this.f1871b.a(b.a.ON_ATTACH_CONTROLLER);
        i.a(this.i);
        this.f1872c.b(this);
        this.m = true;
        if (!this.n) {
            q();
        }
    }

    @Override // com.facebook.f.h.a
    public void n() {
        if (com.facebook.common.e.a.a(2)) {
            com.facebook.common.e.a.a(f1870a, "controller %x %s: onDetach", Integer.valueOf(System.identityHashCode(this)), this.k);
        }
        this.f1871b.a(b.a.ON_DETACH_CONTROLLER);
        this.m = false;
        this.f1872c.a(this);
    }

    @Override // com.facebook.f.h.a
    public boolean a(MotionEvent motionEvent) {
        if (com.facebook.common.e.a.a(2)) {
            com.facebook.common.e.a.a(f1870a, "controller %x %s: onTouchEvent %s", Integer.valueOf(System.identityHashCode(this)), this.k, motionEvent);
        }
        com.facebook.f.g.a aVar = this.f;
        if (aVar == null) {
            return false;
        }
        if (!aVar.c() && !o()) {
            return false;
        }
        this.f.a(motionEvent);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean o() {
        return b();
    }

    private boolean b() {
        c cVar;
        return this.p && (cVar = this.e) != null && cVar.c();
    }

    @Override // com.facebook.f.g.a.AbstractC0046a
    public boolean p() {
        if (com.facebook.common.e.a.a(2)) {
            com.facebook.common.e.a.a(f1870a, "controller %x %s: onClick", Integer.valueOf(System.identityHashCode(this)), this.k);
        }
        if (!b()) {
            return false;
        }
        this.e.d();
        this.i.b();
        q();
        return true;
    }

    /* access modifiers changed from: protected */
    public void q() {
        T e2 = e();
        if (e2 != null) {
            this.s = null;
            this.n = true;
            this.p = false;
            this.f1871b.a(b.a.ON_SUBMIT_CACHE_HIT);
            j().a(this.k, this.l);
            a(this.k, (Object) e2);
            a(this.k, this.s, e2, 1.0f, true, true);
            return;
        }
        this.f1871b.a(b.a.ON_DATASOURCE_SUBMIT);
        j().a(this.k, this.l);
        this.i.a(BitmapDescriptorFactory.HUE_RED, true);
        this.n = true;
        this.p = false;
        this.s = c();
        if (com.facebook.common.e.a.a(2)) {
            com.facebook.common.e.a.a(f1870a, "controller %x %s: submitRequest: dataSource: %x", Integer.valueOf(System.identityHashCode(this)), this.k, Integer.valueOf(System.identityHashCode(this.s)));
        }
        final String str = this.k;
        final boolean c2 = this.s.c();
        this.s.a(new com.facebook.c.b<T>() {
            /* class com.facebook.f.c.a.AnonymousClass1 */

            @Override // com.facebook.c.b
            public void e(com.facebook.c.c<T> cVar) {
                boolean b2 = cVar.b();
                float g = cVar.g();
                T d2 = cVar.d();
                if (d2 != null) {
                    a.this.a(str, cVar, d2, g, b2, c2);
                } else if (b2) {
                    a.this.a((a) str, (String) cVar, (com.facebook.c.c) new NullPointerException(), (Throwable) true);
                }
            }

            @Override // com.facebook.c.b
            public void f(com.facebook.c.c<T> cVar) {
                a.this.a((a) str, (String) cVar, (com.facebook.c.c) cVar.f(), (Throwable) true);
            }

            @Override // com.facebook.c.e, com.facebook.c.b
            public void d(com.facebook.c.c<T> cVar) {
                boolean b2 = cVar.b();
                a.this.a((a) str, (String) cVar, (com.facebook.c.c) cVar.g(), (float) b2);
            }
        }, this.f1873d);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(String str, com.facebook.c.c<T> cVar, T t2, float f2, boolean z, boolean z2) {
        if (!a(str, (com.facebook.c.c) cVar)) {
            d("ignore_old_datasource @ onNewResult", t2);
            a((Object) t2);
            cVar.h();
            return;
        }
        this.f1871b.a(z ? b.a.ON_DATASOURCE_RESULT : b.a.ON_DATASOURCE_RESULT_INT);
        try {
            Drawable d2 = d(t2);
            T t3 = this.t;
            Drawable drawable = this.u;
            this.t = t2;
            this.u = d2;
            if (z) {
                try {
                    d("set_final_result @ onNewResult", t2);
                    this.s = null;
                    this.i.a(d2, 1.0f, z2);
                    j().a(str, c(t2), r());
                } catch (Throwable th) {
                    if (!(drawable == null || drawable == d2)) {
                        a(drawable);
                    }
                    if (!(t3 == null || t3 == t2)) {
                        d("release_previous_result @ onNewResult", t3);
                        a((Object) t3);
                    }
                    throw th;
                }
            } else {
                d("set_intermediate_result @ onNewResult", t2);
                this.i.a(d2, f2, z2);
                j().b(str, (Object) c(t2));
            }
            if (!(drawable == null || drawable == d2)) {
                a(drawable);
            }
            if (t3 != null && t3 != t2) {
                d("release_previous_result @ onNewResult", t3);
                a((Object) t3);
            }
        } catch (Exception e2) {
            d("drawable_failed @ onNewResult", t2);
            a((Object) t2);
            a(str, cVar, e2, z);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(String str, com.facebook.c.c<T> cVar, Throwable th, boolean z) {
        Drawable drawable;
        if (!a(str, (com.facebook.c.c) cVar)) {
            a("ignore_old_datasource @ onFailure", th);
            cVar.h();
            return;
        }
        this.f1871b.a(z ? b.a.ON_DATASOURCE_FAILURE : b.a.ON_DATASOURCE_FAILURE_INT);
        if (z) {
            a("final_failed @ onFailure", th);
            this.s = null;
            this.p = true;
            if (this.q && (drawable = this.u) != null) {
                this.i.a(drawable, 1.0f, true);
            } else if (b()) {
                this.i.b(th);
            } else {
                this.i.a(th);
            }
            j().a(this.k, th);
            return;
        }
        a("intermediate_failed @ onFailure", th);
        j().b(this.k, th);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(String str, com.facebook.c.c<T> cVar, float f2, boolean z) {
        if (!a(str, (com.facebook.c.c) cVar)) {
            a("ignore_old_datasource @ onProgress", (Throwable) null);
            cVar.h();
        } else if (!z) {
            this.i.a(f2, false);
        }
    }

    private boolean a(String str, com.facebook.c.c<T> cVar) {
        if (cVar == null && this.s == null) {
            return true;
        }
        if (!str.equals(this.k) || cVar != this.s || !this.n) {
            return false;
        }
        return true;
    }

    private void d(String str, T t2) {
        if (com.facebook.common.e.a.a(2)) {
            com.facebook.common.e.a.a(f1870a, "controller %x %s: %s: image: %s %x", Integer.valueOf(System.identityHashCode(this)), this.k, str, e(t2), Integer.valueOf(b((Object) t2)));
        }
    }

    private void a(String str, Throwable th) {
        if (com.facebook.common.e.a.a(2)) {
            com.facebook.common.e.a.a(f1870a, "controller %x %s: %s: failure: %s", Integer.valueOf(System.identityHashCode(this)), this.k, str, th);
        }
    }

    public Animatable r() {
        Drawable drawable = this.u;
        if (drawable instanceof Animatable) {
            return (Animatable) drawable;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public String e(T t2) {
        return t2 != null ? t2.getClass().getSimpleName() : "<null>";
    }

    /* access modifiers changed from: protected */
    public int b(T t2) {
        return System.identityHashCode(t2);
    }

    public String toString() {
        return h.a(this).a("isAttached", this.m).a("isRequestSubmitted", this.n).a("hasFetchFailed", this.p).a("fetchedImage", b((Object) this.t)).a("events", this.f1871b.toString()).toString();
    }
}
