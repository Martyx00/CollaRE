package com.facebook.f.i;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import com.facebook.common.d.h;
import com.facebook.common.d.i;
import com.facebook.f.b.b;
import com.facebook.f.e.t;
import com.facebook.f.e.u;
import com.facebook.f.h.a;
import com.facebook.f.h.b;

/* compiled from: DraweeHolder */
public class b<DH extends com.facebook.f.h.b> implements u {

    /* renamed from: a  reason: collision with root package name */
    private boolean f1971a = false;

    /* renamed from: b  reason: collision with root package name */
    private boolean f1972b = false;

    /* renamed from: c  reason: collision with root package name */
    private boolean f1973c = true;

    /* renamed from: d  reason: collision with root package name */
    private DH f1974d;
    private a e = null;
    private final com.facebook.f.b.b f = com.facebook.f.b.b.a();

    public void a(Context context) {
    }

    public static <DH extends com.facebook.f.h.b> b<DH> a(DH dh, Context context) {
        b<DH> bVar = new b<>(dh);
        bVar.a(context);
        return bVar;
    }

    public b(DH dh) {
        if (dh != null) {
            a(dh);
        }
    }

    public void b() {
        this.f.a(b.a.ON_HOLDER_ATTACH);
        this.f1972b = true;
        i();
    }

    public void c() {
        this.f.a(b.a.ON_HOLDER_DETACH);
        this.f1972b = false;
        i();
    }

    public boolean a(MotionEvent motionEvent) {
        if (!j()) {
            return false;
        }
        return this.e.a(motionEvent);
    }

    @Override // com.facebook.f.e.u
    public void a(boolean z) {
        if (this.f1973c != z) {
            this.f.a(z ? b.a.ON_DRAWABLE_SHOW : b.a.ON_DRAWABLE_HIDE);
            this.f1973c = z;
            i();
        }
    }

    @Override // com.facebook.f.e.u
    public void a() {
        if (!this.f1971a) {
            com.facebook.common.e.a.b((Class<?>) com.facebook.f.b.b.class, "%x: Draw requested for a non-attached controller %x. %s", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(System.identityHashCode(this.e)), toString());
            this.f1972b = true;
            this.f1973c = true;
            i();
        }
    }

    private void a(u uVar) {
        Drawable f2 = f();
        if (f2 instanceof t) {
            ((t) f2).a(uVar);
        }
    }

    public void a(a aVar) {
        boolean z = this.f1971a;
        if (z) {
            h();
        }
        if (j()) {
            this.f.a(b.a.ON_CLEAR_OLD_CONTROLLER);
            this.e.a((com.facebook.f.h.b) null);
        }
        this.e = aVar;
        if (this.e != null) {
            this.f.a(b.a.ON_SET_CONTROLLER);
            this.e.a(this.f1974d);
        } else {
            this.f.a(b.a.ON_CLEAR_CONTROLLER);
        }
        if (z) {
            g();
        }
    }

    public a d() {
        return this.e;
    }

    public void a(DH dh) {
        this.f.a(b.a.ON_SET_HIERARCHY);
        boolean j = j();
        a((u) null);
        this.f1974d = (DH) ((com.facebook.f.h.b) i.a(dh));
        Drawable a2 = this.f1974d.a();
        a(a2 == null || a2.isVisible());
        a(this);
        if (j) {
            this.e.a(dh);
        }
    }

    public DH e() {
        return (DH) ((com.facebook.f.h.b) i.a(this.f1974d));
    }

    public Drawable f() {
        DH dh = this.f1974d;
        if (dh == null) {
            return null;
        }
        return dh.a();
    }

    private void g() {
        if (!this.f1971a) {
            this.f.a(b.a.ON_ATTACH_CONTROLLER);
            this.f1971a = true;
            a aVar = this.e;
            if (aVar != null && aVar.k() != null) {
                this.e.m();
            }
        }
    }

    private void h() {
        if (this.f1971a) {
            this.f.a(b.a.ON_DETACH_CONTROLLER);
            this.f1971a = false;
            if (j()) {
                this.e.n();
            }
        }
    }

    private void i() {
        if (!this.f1972b || !this.f1973c) {
            h();
        } else {
            g();
        }
    }

    public String toString() {
        return h.a(this).a("controllerAttached", this.f1971a).a("holderAttached", this.f1972b).a("drawableVisible", this.f1973c).a("events", this.f.toString()).toString();
    }

    private boolean j() {
        a aVar = this.e;
        return aVar != null && aVar.k() == this.f1974d;
    }
}
