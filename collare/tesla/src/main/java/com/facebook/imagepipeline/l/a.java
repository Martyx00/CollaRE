package com.facebook.imagepipeline.l;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.support.v4.util.Pools;
import com.facebook.common.d.i;
import com.facebook.common.j.b;
import com.facebook.imagepipeline.memory.d;
import java.io.InputStream;
import java.nio.ByteBuffer;

@TargetApi(21)
/* compiled from: ArtDecoder */
public class a implements e {

    /* renamed from: b  reason: collision with root package name */
    private static final Class<?> f2176b = a.class;

    /* renamed from: d  reason: collision with root package name */
    private static final byte[] f2177d = {-1, -39};

    /* renamed from: a  reason: collision with root package name */
    final Pools.a<ByteBuffer> f2178a;

    /* renamed from: c  reason: collision with root package name */
    private final d f2179c;

    public a(d dVar, int i, Pools.a aVar) {
        this.f2179c = dVar;
        this.f2178a = aVar;
        for (int i2 = 0; i2 < i; i2++) {
            this.f2178a.a(ByteBuffer.allocate(16384));
        }
    }

    @Override // com.facebook.imagepipeline.l.e
    public com.facebook.common.h.a<Bitmap> a(com.facebook.imagepipeline.j.d dVar, Bitmap.Config config, Rect rect) {
        BitmapFactory.Options a2 = a(dVar, config);
        boolean z = a2.inPreferredConfig != Bitmap.Config.ARGB_8888;
        try {
            return a(dVar.d(), a2, rect);
        } catch (RuntimeException e) {
            if (z) {
                return a(dVar, Bitmap.Config.ARGB_8888, rect);
            }
            throw e;
        }
    }

    @Override // com.facebook.imagepipeline.l.e
    public com.facebook.common.h.a<Bitmap> a(com.facebook.imagepipeline.j.d dVar, Bitmap.Config config, Rect rect, int i) {
        boolean f = dVar.f(i);
        BitmapFactory.Options a2 = a(dVar, config);
        InputStream d2 = dVar.d();
        i.a(d2);
        if (dVar.l() > i) {
            d2 = new com.facebook.common.j.a(d2, i);
        }
        InputStream bVar = !f ? new b(d2, f2177d) : d2;
        boolean z = a2.inPreferredConfig != Bitmap.Config.ARGB_8888;
        try {
            return a(bVar, a2, rect);
        } catch (RuntimeException e) {
            if (z) {
                return a(dVar, Bitmap.Config.ARGB_8888, rect);
            }
            throw e;
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:15:0x0046 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [android.graphics.Rect] */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r11v8, types: [android.graphics.Bitmap] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:23|24|(1:26)) */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        com.facebook.common.e.a.c(com.facebook.imagepipeline.l.a.f2176b, "Could not decode region %s, decoding full bitmap instead.", (java.lang.Object[]) new java.lang.Object[]{r11});
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0060, code lost:
        if (r0 != null) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0062, code lost:
        r0.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0066, code lost:
        r10 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0069, code lost:
        r0.recycle();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0054 */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0082  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.common.h.a<android.graphics.Bitmap> a(java.io.InputStream r9, android.graphics.BitmapFactory.Options r10, android.graphics.Rect r11) {
        /*
        // Method dump skipped, instructions count: 198
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.l.a.a(java.io.InputStream, android.graphics.BitmapFactory$Options, android.graphics.Rect):com.facebook.common.h.a");
    }

    private static BitmapFactory.Options a(com.facebook.imagepipeline.j.d dVar, Bitmap.Config config) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = dVar.j();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(dVar.d(), null, options);
        if (options.outWidth == -1 || options.outHeight == -1) {
            throw new IllegalArgumentException();
        }
        options.inJustDecodeBounds = false;
        options.inDither = true;
        options.inPreferredConfig = config;
        options.inMutable = true;
        return options;
    }
}
