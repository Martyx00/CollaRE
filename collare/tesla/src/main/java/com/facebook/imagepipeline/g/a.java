package com.facebook.imagepipeline.g;

import com.facebook.common.d.i;
import com.facebook.imagepipeline.k.c;
import com.facebook.imagepipeline.n.ak;
import com.facebook.imagepipeline.n.aq;
import com.facebook.imagepipeline.n.b;
import com.facebook.imagepipeline.n.k;

/* compiled from: AbstractProducerToDataSourceAdapter */
public abstract class a<T> extends com.facebook.c.a<T> {

    /* renamed from: a  reason: collision with root package name */
    private final aq f2147a;

    /* renamed from: b  reason: collision with root package name */
    private final c f2148b;

    protected a(ak<T> akVar, aq aqVar, c cVar) {
        this.f2147a = aqVar;
        this.f2148b = cVar;
        this.f2148b.a(aqVar.a(), this.f2147a.d(), this.f2147a.b(), this.f2147a.f());
        akVar.a(j(), aqVar);
    }

    private k<T> j() {
        return new b<T>() {
            /* class com.facebook.imagepipeline.g.a.AnonymousClass1 */

            /* access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.n.b
            public void a(T t, int i) {
                a.this.a((Object) t, i);
            }

            /* access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.n.b
            public void a(Throwable th) {
                a.this.b((a) th);
            }

            /* access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.n.b
            public void a() {
                a.this.k();
            }

            /* access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.n.b
            public void a(float f) {
                a.this.a((a) f);
            }
        };
    }

    /* access modifiers changed from: protected */
    public void a(T t, int i) {
        boolean a2 = b.a(i);
        if (super.a(t, a2) && a2) {
            this.f2148b.a(this.f2147a.a(), this.f2147a.b(), this.f2147a.f());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(Throwable th) {
        if (super.a(th)) {
            this.f2148b.a(this.f2147a.a(), this.f2147a.b(), th, this.f2147a.f());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void k() {
        i.b(a());
    }

    @Override // com.facebook.c.c, com.facebook.c.a
    public boolean h() {
        if (!super.h()) {
            return false;
        }
        if (super.b()) {
            return true;
        }
        this.f2148b.a_(this.f2147a.b());
        this.f2147a.i();
        return true;
    }
}
