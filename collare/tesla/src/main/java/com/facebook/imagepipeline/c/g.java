package com.facebook.imagepipeline.c;

import android.graphics.Bitmap;
import com.facebook.common.h.c;

/* compiled from: SimpleBitmapReleaser */
public class g implements c<Bitmap> {

    /* renamed from: a  reason: collision with root package name */
    private static g f2027a;

    public static g a() {
        if (f2027a == null) {
            f2027a = new g();
        }
        return f2027a;
    }

    private g() {
    }

    public void a(Bitmap bitmap) {
        bitmap.recycle();
    }
}
