package com.teslamotors.plugins.colorblending;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import com.facebook.b.a.i;
import com.facebook.c.e;
import com.facebook.common.b.f;
import com.facebook.imagepipeline.f.j;
import com.facebook.imagepipeline.j.b;
import com.facebook.imagepipeline.o.a;
import com.facebook.imagepipeline.o.c;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.File;
import java.lang.ref.WeakReference;

/* compiled from: ColorPostprocessor */
public class d extends a {

    /* renamed from: b  reason: collision with root package name */
    private static final String f5581b = "d";
    private static final ColorMatrixColorFilter f = new ColorMatrixColorFilter(new ColorMatrix(new float[]{1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, -1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 255.0f}));

    /* renamed from: c  reason: collision with root package name */
    private final WeakReference<a> f5582c;

    /* renamed from: d  reason: collision with root package name */
    private c f5583d;
    private boolean e = false;

    public d(c cVar, a aVar) {
        this.f5583d = cVar;
        this.f5582c = new WeakReference<>(aVar);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Bitmap b(Bitmap bitmap) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ALPHA_8);
        Paint paint = new Paint();
        paint.setColorFilter(f);
        new Canvas(createBitmap).drawBitmap(bitmap, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, paint);
        return createBitmap;
    }

    public Uri a(String str) {
        return new com.facebook.react.views.b.a(this.f5582c.get().getContext(), str).b();
    }

    private void c(final Bitmap bitmap) {
        j.a().h().b(c.a(a(this.f5583d.d())).o(), this.f5582c.get().getContext()).a(new e<com.facebook.common.h.a<b>>() {
            /* class com.teslamotors.plugins.colorblending.d.AnonymousClass1 */

            @Override // com.facebook.c.e
            public void b(com.facebook.c.c<com.facebook.common.h.a<b>> cVar) {
            }

            @Override // com.facebook.c.e
            public void c(com.facebook.c.c<com.facebook.common.h.a<b>> cVar) {
            }

            @Override // com.facebook.c.e
            public void d(com.facebook.c.c<com.facebook.common.h.a<b>> cVar) {
            }

            @Override // com.facebook.c.e
            public void a(com.facebook.c.c<com.facebook.common.h.a<b>> cVar) {
                Bitmap bitmap = bitmap;
                if (bitmap != null && !bitmap.isRecycled()) {
                    if (!cVar.b()) {
                        cVar.h();
                        return;
                    }
                    com.facebook.common.h.a<b> d2 = cVar.d();
                    try {
                        d.this.b(bitmap, d.this.b((d) ((com.facebook.imagepipeline.j.a) d2.a()).a()));
                        cVar.h();
                    } finally {
                        com.facebook.common.h.a.c(d2);
                    }
                }
            }
        }, f.b());
    }

    /* access modifiers changed from: package-private */
    public void b(final Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap.isRecycled()) {
            Log.e(f5581b, String.format("Cannot postprocess image %s - bitmap has been recycled", this.f5583d.e()));
            return;
        }
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        if (this.f5583d.a() != 0.0d) {
            a(canvas, bitmap2, (int) this.f5583d.a(), paint);
        }
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.OVERLAY));
        paint.setColor(this.f5583d.f());
        canvas.drawBitmap(bitmap2, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, paint);
        if (this.f5583d.b() != 0.0d) {
            a(canvas, bitmap2, (int) this.f5583d.b(), paint);
        }
        paint.setXfermode(null);
        new AsyncTask() {
            /* class com.teslamotors.plugins.colorblending.d.AnonymousClass2 */

            /* access modifiers changed from: protected */
            /* JADX WARNING: Code restructure failed: missing block: B:19:0x0042, code lost:
                if (r0 == false) goto L_0x006e;
             */
            /* JADX WARNING: Removed duplicated region for block: B:33:0x0068 A[SYNTHETIC, Splitter:B:33:0x0068] */
            /* JADX WARNING: Removed duplicated region for block: B:41:0x0075 A[SYNTHETIC, Splitter:B:41:0x0075] */
            /* JADX WARNING: Removed duplicated region for block: B:45:0x007c  */
            @Override // android.os.AsyncTask
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Object doInBackground(java.lang.Object[] r9) {
                /*
                // Method dump skipped, instructions count: 128
                */
                throw new UnsupportedOperationException("Method not decompiled: com.teslamotors.plugins.colorblending.d.AnonymousClass2.doInBackground(java.lang.Object[]):java.lang.Object");
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
        this.f5582c.get().f();
    }

    @Override // com.facebook.imagepipeline.o.a
    public void a(Bitmap bitmap) {
        bitmap.setHasAlpha(true);
        if (this.f5583d.d() != null) {
            c(bitmap);
        } else {
            b(bitmap, bitmap.extractAlpha());
        }
    }

    private static void a(Canvas canvas, Bitmap bitmap, int i, Paint paint) {
        int argb = Color.argb(i, 255, 255, 255);
        if (i >= 0) {
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN));
        } else {
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DARKEN));
        }
        paint.setColor(argb);
        canvas.drawBitmap(bitmap, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, paint);
    }

    @Override // com.facebook.imagepipeline.o.a, com.facebook.imagepipeline.o.d
    public com.facebook.b.a.d a() {
        return new i(this.f5583d.e());
    }

    @Override // com.facebook.imagepipeline.o.a, com.facebook.imagepipeline.o.d
    public String b() {
        return f5581b;
    }

    /* access modifiers changed from: private */
    public static void b(File file) {
        if (file != null && file.exists()) {
            try {
                file.delete();
            } catch (Exception unused) {
            }
        }
    }
}
