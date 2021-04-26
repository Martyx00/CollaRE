package com.facebook.imagepipeline.c;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import com.facebook.imagepipeline.memory.d;
import com.facebook.imagepipeline.nativecode.Bitmaps;

@TargetApi(21)
/* compiled from: ArtBitmapFactory */
public class a extends f {

    /* renamed from: a  reason: collision with root package name */
    private final d f2017a;

    public a(d dVar) {
        this.f2017a = dVar;
    }

    @Override // com.facebook.imagepipeline.c.f
    public com.facebook.common.h.a<Bitmap> a(int i, int i2, Bitmap.Config config) {
        Bitmap bitmap = (Bitmap) this.f2017a.a(com.facebook.h.a.a(i, i2, config));
        Bitmaps.a(bitmap, i, i2, config);
        return com.facebook.common.h.a.a(bitmap, this.f2017a);
    }
}
