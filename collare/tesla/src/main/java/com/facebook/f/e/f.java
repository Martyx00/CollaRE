package com.facebook.f.e;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.facebook.common.d.i;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Arrays;

/* compiled from: FadeDrawable */
public class f extends a {

    /* renamed from: a  reason: collision with root package name */
    int f1911a;

    /* renamed from: b  reason: collision with root package name */
    int f1912b;

    /* renamed from: c  reason: collision with root package name */
    long f1913c;

    /* renamed from: d  reason: collision with root package name */
    int[] f1914d;
    int[] e;
    int f;
    boolean[] g;
    int h;
    private final Drawable[] i;

    public f(Drawable[] drawableArr) {
        super(drawableArr);
        i.b(drawableArr.length < 1 ? false : true, "At least one layer required!");
        this.i = drawableArr;
        this.f1914d = new int[drawableArr.length];
        this.e = new int[drawableArr.length];
        this.f = 255;
        this.g = new boolean[drawableArr.length];
        this.h = 0;
        g();
    }

    public void invalidateSelf() {
        if (this.h == 0) {
            super.invalidateSelf();
        }
    }

    public void b() {
        this.h++;
    }

    public void c() {
        this.h--;
        invalidateSelf();
    }

    public void c(int i2) {
        this.f1912b = i2;
        if (this.f1911a == 1) {
            this.f1911a = 0;
        }
    }

    private void g() {
        this.f1911a = 2;
        Arrays.fill(this.f1914d, 0);
        this.f1914d[0] = 255;
        Arrays.fill(this.e, 0);
        this.e[0] = 255;
        Arrays.fill(this.g, false);
        this.g[0] = true;
    }

    public void d(int i2) {
        this.f1911a = 0;
        this.g[i2] = true;
        invalidateSelf();
    }

    public void e(int i2) {
        this.f1911a = 0;
        this.g[i2] = false;
        invalidateSelf();
    }

    public void d() {
        this.f1911a = 0;
        Arrays.fill(this.g, true);
        invalidateSelf();
    }

    public void e() {
        this.f1911a = 2;
        for (int i2 = 0; i2 < this.i.length; i2++) {
            this.e[i2] = this.g[i2] ? 255 : 0;
        }
        invalidateSelf();
    }

    private boolean a(float f2) {
        boolean z = true;
        for (int i2 = 0; i2 < this.i.length; i2++) {
            int i3 = this.g[i2] ? 1 : -1;
            int[] iArr = this.e;
            iArr[i2] = (int) (((float) this.f1914d[i2]) + (((float) (i3 * 255)) * f2));
            if (iArr[i2] < 0) {
                iArr[i2] = 0;
            }
            int[] iArr2 = this.e;
            if (iArr2[i2] > 255) {
                iArr2[i2] = 255;
            }
            if (this.g[i2] && this.e[i2] < 255) {
                z = false;
            }
            if (!this.g[i2] && this.e[i2] > 0) {
                z = false;
            }
        }
        return z;
    }

    @Override // com.facebook.f.e.a
    public void draw(Canvas canvas) {
        boolean z;
        int i2 = 2;
        int i3 = 0;
        switch (this.f1911a) {
            case 0:
                System.arraycopy(this.e, 0, this.f1914d, 0, this.i.length);
                this.f1913c = f();
                z = a(this.f1912b == 0 ? 1.0f : BitmapDescriptorFactory.HUE_RED);
                if (!z) {
                    i2 = 1;
                }
                this.f1911a = i2;
                break;
            case 1:
                i.b(this.f1912b > 0);
                z = a(((float) (f() - this.f1913c)) / ((float) this.f1912b));
                if (!z) {
                    i2 = 1;
                }
                this.f1911a = i2;
                break;
            case 2:
                z = true;
                break;
            default:
                z = true;
                break;
        }
        while (true) {
            Drawable[] drawableArr = this.i;
            if (i3 < drawableArr.length) {
                a(canvas, drawableArr[i3], (this.e[i3] * this.f) / 255);
                i3++;
            } else if (!z) {
                invalidateSelf();
                return;
            } else {
                return;
            }
        }
    }

    private void a(Canvas canvas, Drawable drawable, int i2) {
        if (drawable != null && i2 > 0) {
            this.h++;
            drawable.mutate().setAlpha(i2);
            this.h--;
            drawable.draw(canvas);
        }
    }

    @Override // com.facebook.f.e.a
    public void setAlpha(int i2) {
        if (this.f != i2) {
            this.f = i2;
            invalidateSelf();
        }
    }

    public int getAlpha() {
        return this.f;
    }

    /* access modifiers changed from: protected */
    public long f() {
        return SystemClock.uptimeMillis();
    }
}
