package com.facebook.imagepipeline.memory;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import com.facebook.common.d.i;
import com.facebook.common.g.c;

@TargetApi(21)
/* compiled from: BitmapPool */
public class d extends a<Bitmap> {
    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.memory.a
    public int c(int i) {
        return i;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.memory.a
    public int d(int i) {
        return i;
    }

    public d(c cVar, t tVar, u uVar) {
        super(cVar, tVar, uVar);
        a();
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public Bitmap b(int i) {
        return Bitmap.createBitmap(1, (int) Math.ceil(((double) i) / 2.0d), Bitmap.Config.RGB_565);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void b(Bitmap bitmap) {
        i.a(bitmap);
        bitmap.recycle();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public int c(Bitmap bitmap) {
        i.a(bitmap);
        return bitmap.getAllocationByteCount();
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public boolean d(Bitmap bitmap) {
        i.a(bitmap);
        return !bitmap.isRecycled() && bitmap.isMutable();
    }
}
