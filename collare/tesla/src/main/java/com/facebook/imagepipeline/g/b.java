package com.facebook.imagepipeline.g;

import android.graphics.Bitmap;
import com.facebook.c.c;
import com.facebook.common.h.a;

/* compiled from: BaseBitmapDataSubscriber */
public abstract class b extends com.facebook.c.b<a<com.facebook.imagepipeline.j.b>> {
    /* access modifiers changed from: protected */
    public abstract void a(Bitmap bitmap);

    @Override // com.facebook.c.b
    public void e(c<a<com.facebook.imagepipeline.j.b>> cVar) {
        if (cVar.b()) {
            a<com.facebook.imagepipeline.j.b> d2 = cVar.d();
            Bitmap bitmap = null;
            if (d2 != null && (d2.a() instanceof com.facebook.imagepipeline.j.a)) {
                bitmap = ((com.facebook.imagepipeline.j.a) d2.a()).a();
            }
            try {
                a(bitmap);
            } finally {
                a.c(d2);
            }
        }
    }
}
