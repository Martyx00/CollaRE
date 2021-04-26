package com.facebook.f.e;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.common.d.i;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Arrays;

/* compiled from: RoundedDrawable */
public abstract class n extends Drawable implements j, r {
    private boolean A = true;
    private s B;

    /* renamed from: a  reason: collision with root package name */
    protected boolean f1936a = false;

    /* renamed from: b  reason: collision with root package name */
    protected boolean f1937b = false;

    /* renamed from: c  reason: collision with root package name */
    protected float f1938c = BitmapDescriptorFactory.HUE_RED;

    /* renamed from: d  reason: collision with root package name */
    protected final Path f1939d = new Path();
    protected boolean e = true;
    protected int f = 0;
    protected final Path g = new Path();
    final float[] h = new float[8];
    float[] i;
    final RectF j = new RectF();
    final RectF k = new RectF();
    final RectF l = new RectF();
    final RectF m = new RectF();
    RectF n;
    final Matrix o = new Matrix();
    final Matrix p = new Matrix();
    final Matrix q = new Matrix();
    final Matrix r = new Matrix();
    final Matrix s = new Matrix();
    Matrix t;
    Matrix u;
    final Matrix v = new Matrix();
    private final Drawable w;
    private final float[] x = new float[8];
    private float y = BitmapDescriptorFactory.HUE_RED;
    private boolean z = false;

    n(Drawable drawable) {
        this.w = drawable;
    }

    @Override // com.facebook.f.e.j
    public void a(boolean z2) {
        this.f1936a = z2;
        this.A = true;
        invalidateSelf();
    }

    @Override // com.facebook.f.e.j
    public void a(float f2) {
        boolean z2 = false;
        int i2 = (f2 > BitmapDescriptorFactory.HUE_RED ? 1 : (f2 == BitmapDescriptorFactory.HUE_RED ? 0 : -1));
        i.b(i2 >= 0);
        Arrays.fill(this.x, f2);
        if (i2 != 0) {
            z2 = true;
        }
        this.f1937b = z2;
        this.A = true;
        invalidateSelf();
    }

    @Override // com.facebook.f.e.j
    public void a(float[] fArr) {
        if (fArr == null) {
            Arrays.fill(this.x, (float) BitmapDescriptorFactory.HUE_RED);
            this.f1937b = false;
        } else {
            i.a(fArr.length == 8, "radii should have exactly 8 values");
            System.arraycopy(fArr, 0, this.x, 0, 8);
            this.f1937b = false;
            for (int i2 = 0; i2 < 8; i2++) {
                this.f1937b |= fArr[i2] > BitmapDescriptorFactory.HUE_RED;
            }
        }
        this.A = true;
        invalidateSelf();
    }

    @Override // com.facebook.f.e.j
    public void a(int i2, float f2) {
        if (this.f != i2 || this.f1938c != f2) {
            this.f = i2;
            this.f1938c = f2;
            this.A = true;
            invalidateSelf();
        }
    }

    @Override // com.facebook.f.e.j
    public void b(float f2) {
        if (this.y != f2) {
            this.y = f2;
            this.A = true;
            invalidateSelf();
        }
    }

    @Override // com.facebook.f.e.j
    public void b(boolean z2) {
        if (this.z != z2) {
            this.z = z2;
            this.A = true;
            invalidateSelf();
        }
    }

    @Override // com.facebook.f.e.r
    public void a(s sVar) {
        this.B = sVar;
    }

    /* access modifiers changed from: protected */
    public void b() {
        Matrix matrix;
        s sVar = this.B;
        if (sVar != null) {
            sVar.a(this.q);
            this.B.a(this.j);
        } else {
            this.q.reset();
            this.j.set(getBounds());
        }
        this.l.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (float) getIntrinsicWidth(), (float) getIntrinsicHeight());
        this.m.set(this.w.getBounds());
        this.o.setRectToRect(this.l, this.m, Matrix.ScaleToFit.FILL);
        if (this.z) {
            RectF rectF = this.n;
            if (rectF == null) {
                this.n = new RectF(this.j);
            } else {
                rectF.set(this.j);
            }
            RectF rectF2 = this.n;
            float f2 = this.f1938c;
            rectF2.inset(f2, f2);
            if (this.t == null) {
                this.t = new Matrix();
            }
            this.t.setRectToRect(this.j, this.n, Matrix.ScaleToFit.FILL);
        } else {
            Matrix matrix2 = this.t;
            if (matrix2 != null) {
                matrix2.reset();
            }
        }
        if (!this.q.equals(this.r) || !this.o.equals(this.p) || ((matrix = this.t) != null && !matrix.equals(this.u))) {
            this.e = true;
            this.q.invert(this.s);
            this.v.set(this.q);
            if (this.z) {
                this.v.postConcat(this.t);
            }
            this.v.preConcat(this.o);
            this.r.set(this.q);
            this.p.set(this.o);
            if (this.z) {
                Matrix matrix3 = this.u;
                if (matrix3 == null) {
                    this.u = new Matrix(this.t);
                } else {
                    matrix3.set(this.t);
                }
            } else {
                Matrix matrix4 = this.u;
                if (matrix4 != null) {
                    matrix4.reset();
                }
            }
        }
        if (!this.j.equals(this.k)) {
            this.A = true;
            this.k.set(this.j);
        }
    }

    /* access modifiers changed from: protected */
    public void c() {
        float[] fArr;
        if (this.A) {
            this.g.reset();
            RectF rectF = this.j;
            float f2 = this.f1938c;
            rectF.inset(f2 / 2.0f, f2 / 2.0f);
            if (this.f1936a) {
                this.g.addCircle(this.j.centerX(), this.j.centerY(), Math.min(this.j.width(), this.j.height()) / 2.0f, Path.Direction.CW);
            } else {
                int i2 = 0;
                while (true) {
                    fArr = this.h;
                    if (i2 >= fArr.length) {
                        break;
                    }
                    fArr[i2] = (this.x[i2] + this.y) - (this.f1938c / 2.0f);
                    i2++;
                }
                this.g.addRoundRect(this.j, fArr, Path.Direction.CW);
            }
            RectF rectF2 = this.j;
            float f3 = this.f1938c;
            rectF2.inset((-f3) / 2.0f, (-f3) / 2.0f);
            this.f1939d.reset();
            float f4 = this.y + (this.z ? this.f1938c : BitmapDescriptorFactory.HUE_RED);
            this.j.inset(f4, f4);
            if (this.f1936a) {
                this.f1939d.addCircle(this.j.centerX(), this.j.centerY(), Math.min(this.j.width(), this.j.height()) / 2.0f, Path.Direction.CW);
            } else if (this.z) {
                if (this.i == null) {
                    this.i = new float[8];
                }
                for (int i3 = 0; i3 < this.h.length; i3++) {
                    this.i[i3] = this.x[i3] - this.f1938c;
                }
                this.f1939d.addRoundRect(this.j, this.i, Path.Direction.CW);
            } else {
                this.f1939d.addRoundRect(this.j, this.x, Path.Direction.CW);
            }
            float f5 = -f4;
            this.j.inset(f5, f5);
            this.f1939d.setFillType(Path.FillType.WINDING);
            this.A = false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return this.f1936a || this.f1937b || this.f1938c > BitmapDescriptorFactory.HUE_RED;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        this.w.setBounds(rect);
    }

    public int getIntrinsicWidth() {
        return this.w.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        return this.w.getIntrinsicHeight();
    }

    public int getOpacity() {
        return this.w.getOpacity();
    }

    public void setColorFilter(int i2, PorterDuff.Mode mode) {
        this.w.setColorFilter(i2, mode);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.w.setColorFilter(colorFilter);
    }

    public ColorFilter getColorFilter() {
        return this.w.getColorFilter();
    }

    public void clearColorFilter() {
        this.w.clearColorFilter();
    }

    public int getAlpha() {
        return this.w.getAlpha();
    }

    public void setAlpha(int i2) {
        this.w.setAlpha(i2);
    }

    public void draw(Canvas canvas) {
        this.w.draw(canvas);
    }
}
