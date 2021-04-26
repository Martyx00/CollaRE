package com.facebook.imagepipeline.c;

import android.graphics.Bitmap;
import com.facebook.common.h.a;

/* compiled from: GingerbreadBitmapFactory */
public class c extends f {
    @Override // com.facebook.imagepipeline.c.f
    public a<Bitmap> a(int i, int i2, Bitmap.Config config) {
        return a.a(Bitmap.createBitmap(i, i2, config), g.a());
    }
}
