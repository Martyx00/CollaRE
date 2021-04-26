package com.facebook.imagepipeline.n;

import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.o.b;

/* compiled from: BranchOnSeparateImagesProducer */
public class j implements ak<d> {

    /* renamed from: a  reason: collision with root package name */
    private final ak<d> f2373a;

    /* renamed from: b  reason: collision with root package name */
    private final ak<d> f2374b;

    public j(ak<d> akVar, ak<d> akVar2) {
        this.f2373a = akVar;
        this.f2374b = akVar2;
    }

    @Override // com.facebook.imagepipeline.n.ak
    public void a(k<d> kVar, al alVar) {
        this.f2373a.a(new a(kVar, alVar), alVar);
    }

    /* compiled from: BranchOnSeparateImagesProducer */
    private class a extends n<d, d> {

        /* renamed from: b  reason: collision with root package name */
        private al f2376b;

        private a(k<d> kVar, al alVar) {
            super(kVar);
            this.f2376b = alVar;
        }

        /* access modifiers changed from: protected */
        public void a(d dVar, int i) {
            b a2 = this.f2376b.a();
            boolean a3 = a(i);
            boolean a4 = ay.a(dVar, a2.f());
            if (dVar != null && (a4 || a2.k())) {
                if (!a3 || !a4) {
                    d().b(dVar, a(i, 1));
                } else {
                    d().b(dVar, i);
                }
            }
            if (a3 && !a4) {
                d.d(dVar);
                j.this.f2374b.a(d(), this.f2376b);
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.n.n, com.facebook.imagepipeline.n.b
        public void a(Throwable th) {
            j.this.f2374b.a(d(), this.f2376b);
        }
    }
}
