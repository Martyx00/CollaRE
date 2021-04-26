package com.facebook.imagepipeline.c;

import android.graphics.Bitmap;
import com.facebook.common.h.a;

/* compiled from: PlatformBitmapFactory */
public abstract class f {
    public abstract a<Bitmap> a(int i, int i2, Bitmap.Config config);

    public a<Bitmap> b(int i, int i2, Bitmap.Config config) {
        return a(i, i2, config, null);
    }

    public a<Bitmap> a(int i, int i2) {
        return b(i, i2, Bitmap.Config.ARGB_8888);
    }

    public a<Bitmap> a(int i, int i2, Bitmap.Config config, Object obj) {
        return a(i, i2, config);
    }
}
