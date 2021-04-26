package com.facebook.imagepipeline.l;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Build;
import com.facebook.common.d.i;
import com.facebook.common.d.n;
import com.facebook.common.g.g;
import com.facebook.common.h.a;
import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.memory.c;
import com.facebook.imagepipeline.nativecode.Bitmaps;
import java.util.Locale;

/* access modifiers changed from: package-private */
/* compiled from: DalvikPurgeableDecoder */
public abstract class b implements e {

    /* renamed from: a  reason: collision with root package name */
    protected static final byte[] f2180a = {-1, -39};

    /* renamed from: b  reason: collision with root package name */
    private final com.facebook.imagepipeline.memory.b f2181b = c.a();

    /* access modifiers changed from: package-private */
    public abstract Bitmap a(a<g> aVar, int i, BitmapFactory.Options options);

    /* access modifiers changed from: package-private */
    public abstract Bitmap a(a<g> aVar, BitmapFactory.Options options);

    b() {
    }

    @Override // com.facebook.imagepipeline.l.e
    public a<Bitmap> a(d dVar, Bitmap.Config config, Rect rect) {
        BitmapFactory.Options a2 = a(dVar.j(), config);
        a<g> c2 = dVar.c();
        i.a(c2);
        try {
            return a(a(c2, a2));
        } finally {
            a.c(c2);
        }
    }

    @Override // com.facebook.imagepipeline.l.e
    public a<Bitmap> a(d dVar, Bitmap.Config config, Rect rect, int i) {
        BitmapFactory.Options a2 = a(dVar.j(), config);
        a<g> c2 = dVar.c();
        i.a(c2);
        try {
            return a(a(c2, i, a2));
        } finally {
            a.c(c2);
        }
    }

    private static BitmapFactory.Options a(int i, Bitmap.Config config) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = true;
        options.inPreferredConfig = config;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = i;
        if (Build.VERSION.SDK_INT >= 11) {
            options.inMutable = true;
        }
        return options;
    }

    protected static boolean a(a<g> aVar, int i) {
        g a2 = aVar.a();
        if (i >= 2 && a2.a(i - 2) == -1 && a2.a(i - 1) == -39) {
            return true;
        }
        return false;
    }

    public a<Bitmap> a(Bitmap bitmap) {
        try {
            Bitmaps.a(bitmap);
            if (this.f2181b.a(bitmap)) {
                return a.a(bitmap, this.f2181b.e());
            }
            int a2 = com.facebook.h.a.a(bitmap);
            bitmap.recycle();
            throw new com.facebook.imagepipeline.e.g(String.format(Locale.US, "Attempted to pin a bitmap of size %d bytes. The current pool count is %d, the current pool size is %d bytes. The current pool max count is %d, the current pool max size is %d bytes.", Integer.valueOf(a2), Integer.valueOf(this.f2181b.a()), Long.valueOf(this.f2181b.b()), Integer.valueOf(this.f2181b.c()), Integer.valueOf(this.f2181b.d())));
        } catch (Exception e) {
            bitmap.recycle();
            throw n.b(e);
        }
    }
}
