package com.facebook.f.e;

import android.annotation.SuppressLint;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

/* compiled from: DrawableProperties */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private int f1907a = -1;

    /* renamed from: b  reason: collision with root package name */
    private boolean f1908b = false;

    /* renamed from: c  reason: collision with root package name */
    private ColorFilter f1909c = null;

    /* renamed from: d  reason: collision with root package name */
    private int f1910d = -1;
    private int e = -1;

    public void a(int i) {
        this.f1907a = i;
    }

    public void a(ColorFilter colorFilter) {
        this.f1909c = colorFilter;
        this.f1908b = true;
    }

    public void a(boolean z) {
        this.f1910d = z ? 1 : 0;
    }

    public void b(boolean z) {
        this.e = z ? 1 : 0;
    }

    @SuppressLint({"Range"})
    public void a(Drawable drawable) {
        if (drawable != null) {
            int i = this.f1907a;
            if (i != -1) {
                drawable.setAlpha(i);
            }
            if (this.f1908b) {
                drawable.setColorFilter(this.f1909c);
            }
            int i2 = this.f1910d;
            boolean z = true;
            if (i2 != -1) {
                drawable.setDither(i2 != 0);
            }
            int i3 = this.e;
            if (i3 != -1) {
                if (i3 == 0) {
                    z = false;
                }
                drawable.setFilterBitmap(z);
            }
        }
    }
}
