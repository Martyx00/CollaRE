package com.facebook.f.e;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.common.d.i;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Arrays;

/* compiled from: RoundedColorDrawable */
public class l extends Drawable implements j {

    /* renamed from: a  reason: collision with root package name */
    final float[] f1925a = new float[8];

    /* renamed from: b  reason: collision with root package name */
    float[] f1926b;

    /* renamed from: c  reason: collision with root package name */
    final Paint f1927c = new Paint(1);

    /* renamed from: d  reason: collision with root package name */
    final Path f1928d = new Path();
    final Path e = new Path();
    private final float[] f = new float[8];
    private boolean g = false;
    private float h = BitmapDescriptorFactory.HUE_RED;
    private float i = BitmapDescriptorFactory.HUE_RED;
    private int j = 0;
    private boolean k = false;
    private int l = 0;
    private final RectF m = new RectF();
    private int n = 255;

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public l(int i2) {
        a(i2);
    }

    @TargetApi(11)
    public static l a(ColorDrawable colorDrawable) {
        return new l(colorDrawable.getColor());
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        a();
    }

    public void draw(Canvas canvas) {
        this.f1927c.setColor(e.a(this.l, this.n));
        this.f1927c.setStyle(Paint.Style.FILL);
        canvas.drawPath(this.f1928d, this.f1927c);
        if (this.h != BitmapDescriptorFactory.HUE_RED) {
            this.f1927c.setColor(e.a(this.j, this.n));
            this.f1927c.setStyle(Paint.Style.STROKE);
            this.f1927c.setStrokeWidth(this.h);
            canvas.drawPath(this.e, this.f1927c);
        }
    }

    @Override // com.facebook.f.e.j
    public void a(boolean z) {
        this.g = z;
        a();
        invalidateSelf();
    }

    @Override // com.facebook.f.e.j
    public void a(float[] fArr) {
        if (fArr == null) {
            Arrays.fill(this.f, (float) BitmapDescriptorFactory.HUE_RED);
        } else {
            i.a(fArr.length == 8, "radii should have exactly 8 values");
            System.arraycopy(fArr, 0, this.f, 0, 8);
        }
        a();
        invalidateSelf();
    }

    @Override // com.facebook.f.e.j
    public void a(float f2) {
        i.a(f2 >= BitmapDescriptorFactory.HUE_RED, "radius should be non negative");
        Arrays.fill(this.f, f2);
        a();
        invalidateSelf();
    }

    public void a(int i2) {
        if (this.l != i2) {
            this.l = i2;
            invalidateSelf();
        }
    }

    @Override // com.facebook.f.e.j
    public void a(int i2, float f2) {
        if (this.j != i2) {
            this.j = i2;
            invalidateSelf();
        }
        if (this.h != f2) {
            this.h = f2;
            a();
            invalidateSelf();
        }
    }

    @Override // com.facebook.f.e.j
    public void b(float f2) {
        if (this.i != f2) {
            this.i = f2;
            a();
            invalidateSelf();
        }
    }

    @Override // com.facebook.f.e.j
    public void b(boolean z) {
        if (this.k != z) {
            this.k = z;
            a();
            invalidateSelf();
        }
    }

    public void setAlpha(int i2) {
        if (i2 != this.n) {
            this.n = i2;
            invalidateSelf();
        }
    }

    public int getAlpha() {
        return this.n;
    }

    public int getOpacity() {
        return e.a(e.a(this.l, this.n));
    }

    private void a() {
        float[] fArr;
        float[] fArr2;
        this.f1928d.reset();
        this.e.reset();
        this.m.set(getBounds());
        RectF rectF = this.m;
        float f2 = this.h;
        rectF.inset(f2 / 2.0f, f2 / 2.0f);
        int i2 = 0;
        if (this.g) {
            this.e.addCircle(this.m.centerX(), this.m.centerY(), Math.min(this.m.width(), this.m.height()) / 2.0f, Path.Direction.CW);
        } else {
            int i3 = 0;
            while (true) {
                fArr2 = this.f1925a;
                if (i3 >= fArr2.length) {
                    break;
                }
                fArr2[i3] = (this.f[i3] + this.i) - (this.h / 2.0f);
                i3++;
            }
            this.e.addRoundRect(this.m, fArr2, Path.Direction.CW);
        }
        RectF rectF2 = this.m;
        float f3 = this.h;
        rectF2.inset((-f3) / 2.0f, (-f3) / 2.0f);
        float f4 = this.i + (this.k ? this.h : BitmapDescriptorFactory.HUE_RED);
        this.m.inset(f4, f4);
        if (this.g) {
            this.f1928d.addCircle(this.m.centerX(), this.m.centerY(), Math.min(this.m.width(), this.m.height()) / 2.0f, Path.Direction.CW);
        } else if (this.k) {
            if (this.f1926b == null) {
                this.f1926b = new float[8];
            }
            while (true) {
                fArr = this.f1926b;
                if (i2 >= fArr.length) {
                    break;
                }
                fArr[i2] = this.f[i2] - this.h;
                i2++;
            }
            this.f1928d.addRoundRect(this.m, fArr, Path.Direction.CW);
        } else {
            this.f1928d.addRoundRect(this.m, this.f, Path.Direction.CW);
        }
        float f5 = -f4;
        this.m.inset(f5, f5);
    }
}
