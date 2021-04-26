package com.facebook.imagepipeline.n;

import com.facebook.imagepipeline.j.d;

/* compiled from: AddImageTransformMetaDataProducer */
public class a implements ak<d> {

    /* renamed from: a  reason: collision with root package name */
    private final ak<d> f2239a;

    public a(ak<d> akVar) {
        this.f2239a = akVar;
    }

    @Override // com.facebook.imagepipeline.n.ak
    public void a(k<d> kVar, al alVar) {
        this.f2239a.a(new C0049a(kVar), alVar);
    }

    /* renamed from: com.facebook.imagepipeline.n.a$a  reason: collision with other inner class name */
    /* compiled from: AddImageTransformMetaDataProducer */
    private static class C0049a extends n<d, d> {
        private C0049a(k<d> kVar) {
            super(kVar);
        }

        /* access modifiers changed from: protected */
        public void a(d dVar, int i) {
            if (dVar == null) {
                d().b(null, i);
                return;
            }
            if (!d.c(dVar)) {
                dVar.m();
            }
            d().b(dVar, i);
        }
    }
}
