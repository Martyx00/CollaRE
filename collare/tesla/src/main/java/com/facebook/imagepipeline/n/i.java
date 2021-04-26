package com.facebook.imagepipeline.n;

import android.graphics.Bitmap;
import com.facebook.imagepipeline.j.b;
import com.facebook.imagepipeline.j.c;

/* compiled from: BitmapPrepareProducer */
public class i implements ak<com.facebook.common.h.a<b>> {

    /* renamed from: a  reason: collision with root package name */
    private final ak<com.facebook.common.h.a<b>> f2367a;

    /* renamed from: b  reason: collision with root package name */
    private final int f2368b;

    /* renamed from: c  reason: collision with root package name */
    private final int f2369c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f2370d;

    public i(ak<com.facebook.common.h.a<b>> akVar, int i, int i2, boolean z) {
        com.facebook.common.d.i.a(i <= i2);
        this.f2367a = (ak) com.facebook.common.d.i.a(akVar);
        this.f2368b = i;
        this.f2369c = i2;
        this.f2370d = z;
    }

    @Override // com.facebook.imagepipeline.n.ak
    public void a(k<com.facebook.common.h.a<b>> kVar, al alVar) {
        if (!alVar.f() || this.f2370d) {
            this.f2367a.a(new a(kVar, this.f2368b, this.f2369c), alVar);
        } else {
            this.f2367a.a(kVar, alVar);
        }
    }

    /* compiled from: BitmapPrepareProducer */
    private static class a extends n<com.facebook.common.h.a<b>, com.facebook.common.h.a<b>> {

        /* renamed from: a  reason: collision with root package name */
        private final int f2371a;

        /* renamed from: b  reason: collision with root package name */
        private final int f2372b;

        a(k<com.facebook.common.h.a<b>> kVar, int i, int i2) {
            super(kVar);
            this.f2371a = i;
            this.f2372b = i2;
        }

        /* access modifiers changed from: protected */
        public void a(com.facebook.common.h.a<b> aVar, int i) {
            a(aVar);
            d().b(aVar, i);
        }

        private void a(com.facebook.common.h.a<b> aVar) {
            b a2;
            Bitmap a3;
            int rowBytes;
            if (aVar != null && aVar.d() && (a2 = aVar.a()) != null && !a2.c() && (a2 instanceof c) && (a3 = ((c) a2).a()) != null && (rowBytes = a3.getRowBytes() * a3.getHeight()) >= this.f2371a && rowBytes <= this.f2372b) {
                a3.prepareToDraw();
            }
        }
    }
}
