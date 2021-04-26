package com.facebook.imagepipeline.l;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import com.facebook.common.d.i;
import com.facebook.common.g.g;
import com.facebook.common.h.a;
import com.facebook.imagepipeline.memory.j;

@TargetApi(19)
/* compiled from: KitKatPurgeableDecoder */
public class d extends b {

    /* renamed from: b  reason: collision with root package name */
    private final j f2183b;

    @Override // com.facebook.imagepipeline.l.b
    public /* bridge */ /* synthetic */ a a(Bitmap bitmap) {
        return super.a(bitmap);
    }

    @Override // com.facebook.imagepipeline.l.b, com.facebook.imagepipeline.l.e
    public /* bridge */ /* synthetic */ a a(com.facebook.imagepipeline.j.d dVar, Bitmap.Config config, Rect rect) {
        return super.a(dVar, config, rect);
    }

    @Override // com.facebook.imagepipeline.l.b, com.facebook.imagepipeline.l.e
    public /* bridge */ /* synthetic */ a a(com.facebook.imagepipeline.j.d dVar, Bitmap.Config config, Rect rect, int i) {
        return super.a(dVar, config, rect, i);
    }

    public d(j jVar) {
        this.f2183b = jVar;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.l.b
    public Bitmap a(a<g> aVar, BitmapFactory.Options options) {
        g a2 = aVar.a();
        int a3 = a2.a();
        a<byte[]> a4 = this.f2183b.a(a3);
        try {
            byte[] a5 = a4.a();
            a2.a(0, a5, 0, a3);
            return (Bitmap) i.a(BitmapFactory.decodeByteArray(a5, 0, a3, options), "BitmapFactory returned null");
        } finally {
            a.c(a4);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.l.b
    public Bitmap a(a<g> aVar, int i, BitmapFactory.Options options) {
        byte[] bArr = a(aVar, i) ? null : f2180a;
        g a2 = aVar.a();
        i.a(i <= a2.a());
        int i2 = i + 2;
        a<byte[]> a3 = this.f2183b.a(i2);
        try {
            byte[] a4 = a3.a();
            a2.a(0, a4, 0, i);
            if (bArr != null) {
                a(a4, i);
                i = i2;
            }
            return (Bitmap) i.a(BitmapFactory.decodeByteArray(a4, 0, i, options), "BitmapFactory returned null");
        } finally {
            a.c(a3);
        }
    }

    private static void a(byte[] bArr, int i) {
        bArr[i] = -1;
        bArr[i + 1] = -39;
    }
}
