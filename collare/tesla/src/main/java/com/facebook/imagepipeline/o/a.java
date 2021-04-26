package com.facebook.imagepipeline.o;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.facebook.b.a.d;
import com.facebook.imagepipeline.c.f;
import com.facebook.imagepipeline.nativecode.Bitmaps;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: BasePostprocessor */
public abstract class a implements d {

    /* renamed from: a  reason: collision with root package name */
    public static final Bitmap.Config f2462a = Bitmap.Config.ARGB_8888;

    @Override // com.facebook.imagepipeline.o.d
    public d a() {
        return null;
    }

    public void a(Bitmap bitmap) {
    }

    @Override // com.facebook.imagepipeline.o.d
    public String b() {
        return "Unknown postprocessor";
    }

    @Override // com.facebook.imagepipeline.o.d
    public com.facebook.common.h.a<Bitmap> a(Bitmap bitmap, f fVar) {
        Bitmap.Config config = bitmap.getConfig();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (config == null) {
            config = f2462a;
        }
        com.facebook.common.h.a<Bitmap> a2 = fVar.a(width, height, config);
        try {
            a(a2.a(), bitmap);
            return com.facebook.common.h.a.b(a2);
        } finally {
            com.facebook.common.h.a.c(a2);
        }
    }

    public void a(Bitmap bitmap, Bitmap bitmap2) {
        b(bitmap, bitmap2);
        a(bitmap);
    }

    private static void b(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap.getConfig() == bitmap2.getConfig()) {
            Bitmaps.a(bitmap, bitmap2);
        } else {
            new Canvas(bitmap).drawBitmap(bitmap2, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (Paint) null);
        }
    }
}
