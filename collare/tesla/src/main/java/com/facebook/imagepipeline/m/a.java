package com.facebook.imagepipeline.m;

import android.graphics.Bitmap;
import com.facebook.b.a.d;
import com.facebook.common.d.i;
import com.facebook.imagepipeline.nativecode.NativeBlurFilter;

/* compiled from: IterativeBoxBlurPostProcessor */
public class a extends com.facebook.imagepipeline.o.a {

    /* renamed from: b  reason: collision with root package name */
    private final int f2184b;

    /* renamed from: c  reason: collision with root package name */
    private final int f2185c;

    /* renamed from: d  reason: collision with root package name */
    private d f2186d;

    public a(int i) {
        this(3, i);
    }

    public a(int i, int i2) {
        boolean z = true;
        i.a(i > 0);
        i.a(i2 <= 0 ? false : z);
        this.f2184b = i;
        this.f2185c = i2;
    }

    @Override // com.facebook.imagepipeline.o.a
    public void a(Bitmap bitmap) {
        NativeBlurFilter.a(bitmap, this.f2184b, this.f2185c);
    }

    @Override // com.facebook.imagepipeline.o.a, com.facebook.imagepipeline.o.d
    public d a() {
        if (this.f2186d == null) {
            this.f2186d = new com.facebook.b.a.i(String.format(null, "i%dr%d", Integer.valueOf(this.f2184b), Integer.valueOf(this.f2185c)));
        }
        return this.f2186d;
    }
}
