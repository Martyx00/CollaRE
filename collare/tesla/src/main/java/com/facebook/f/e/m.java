package com.facebook.f.e;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.common.d.i;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Arrays;

/* compiled from: RoundedCornersDrawable */
public class m extends g implements j {

    /* renamed from: a  reason: collision with root package name */
    a f1929a = a.OVERLAY_COLOR;

    /* renamed from: c  reason: collision with root package name */
    final float[] f1930c = new float[8];

    /* renamed from: d  reason: collision with root package name */
    final Paint f1931d = new Paint(1);
    private final RectF e = new RectF();
    private RectF f;
    private Matrix g;
    private final float[] h = new float[8];
    private boolean i = false;
    private float j = BitmapDescriptorFactory.HUE_RED;
    private int k = 0;
    private int l = 0;
    private float m = BitmapDescriptorFactory.HUE_RED;
    private boolean n = false;
    private final Path o = new Path();
    private final Path p = new Path();
    private final RectF q = new RectF();

    /* compiled from: RoundedCornersDrawable */
    public enum a {
        OVERLAY_COLOR,
        CLIPPING
    }

    public m(Drawable drawable) {
        super((Drawable) i.a(drawable));
    }

    @Override // com.facebook.f.e.j
    public void a(boolean z) {
        this.i = z;
        b();
        invalidateSelf();
    }

    @Override // com.facebook.f.e.j
    public void a(float f2) {
        Arrays.fill(this.h, f2);
        b();
        invalidateSelf();
    }

    @Override // com.facebook.f.e.j
    public void a(float[] fArr) {
        if (fArr == null) {
            Arrays.fill(this.h, (float) BitmapDescriptorFactory.HUE_RED);
        } else {
            i.a(fArr.length == 8, "radii should have exactly 8 values");
            System.arraycopy(fArr, 0, this.h, 0, 8);
        }
        b();
        invalidateSelf();
    }

    public void a(int i2) {
        this.l = i2;
        invalidateSelf();
    }

    @Override // com.facebook.f.e.j
    public void a(int i2, float f2) {
        this.k = i2;
        this.j = f2;
        b();
        invalidateSelf();
    }

    @Override // com.facebook.f.e.j
    public void b(float f2) {
        this.m = f2;
        b();
        invalidateSelf();
    }

    @Override // com.facebook.f.e.j
    public void b(boolean z) {
        this.n = z;
        b();
        invalidateSelf();
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.f.e.g
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        b();
    }

    private void b() {
        float[] fArr;
        this.o.reset();
        this.p.reset();
        this.q.set(getBounds());
        RectF rectF = this.q;
        float f2 = this.m;
        rectF.inset(f2, f2);
        this.o.addRect(this.q, Path.Direction.CW);
        if (this.i) {
            this.o.addCircle(this.q.centerX(), this.q.centerY(), Math.min(this.q.width(), this.q.height()) / 2.0f, Path.Direction.CW);
        } else {
            this.o.addRoundRect(this.q, this.h, Path.Direction.CW);
        }
        RectF rectF2 = this.q;
        float f3 = this.m;
        rectF2.inset(-f3, -f3);
        RectF rectF3 = this.q;
        float f4 = this.j;
        rectF3.inset(f4 / 2.0f, f4 / 2.0f);
        if (this.i) {
            this.p.addCircle(this.q.centerX(), this.q.centerY(), Math.min(this.q.width(), this.q.height()) / 2.0f, Path.Direction.CW);
        } else {
            int i2 = 0;
            while (true) {
                fArr = this.f1930c;
                if (i2 >= fArr.length) {
                    break;
                }
                fArr[i2] = (this.h[i2] + this.m) - (this.j / 2.0f);
                i2++;
            }
            this.p.addRoundRect(this.q, fArr, Path.Direction.CW);
        }
        RectF rectF4 = this.q;
        float f5 = this.j;
        rectF4.inset((-f5) / 2.0f, (-f5) / 2.0f);
    }

    @Override // com.facebook.f.e.g
    public void draw(Canvas canvas) {
        this.e.set(getBounds());
        switch (this.f1929a) {
            case CLIPPING:
                int save = canvas.save();
                this.o.setFillType(Path.FillType.EVEN_ODD);
                canvas.clipPath(this.o);
                super.draw(canvas);
                canvas.restoreToCount(save);
                break;
            case OVERLAY_COLOR:
                if (this.n) {
                    RectF rectF = this.f;
                    if (rectF == null) {
                        this.f = new RectF(this.e);
                        this.g = new Matrix();
                    } else {
                        rectF.set(this.e);
                    }
                    RectF rectF2 = this.f;
                    float f2 = this.j;
                    rectF2.inset(f2, f2);
                    this.g.setRectToRect(this.e, this.f, Matrix.ScaleToFit.FILL);
                    int save2 = canvas.save();
                    canvas.clipRect(this.e);
                    canvas.concat(this.g);
                    super.draw(canvas);
                    canvas.restoreToCount(save2);
                } else {
                    super.draw(canvas);
                }
                this.f1931d.setStyle(Paint.Style.FILL);
                this.f1931d.setColor(this.l);
                this.f1931d.setStrokeWidth(BitmapDescriptorFactory.HUE_RED);
                this.o.setFillType(Path.FillType.EVEN_ODD);
                canvas.drawPath(this.o, this.f1931d);
                if (this.i) {
                    float width = ((this.e.width() - this.e.height()) + this.j) / 2.0f;
                    float height = ((this.e.height() - this.e.width()) + this.j) / 2.0f;
                    if (width > BitmapDescriptorFactory.HUE_RED) {
                        canvas.drawRect(this.e.left, this.e.top, this.e.left + width, this.e.bottom, this.f1931d);
                        canvas.drawRect(this.e.right - width, this.e.top, this.e.right, this.e.bottom, this.f1931d);
                    }
                    if (height > BitmapDescriptorFactory.HUE_RED) {
                        canvas.drawRect(this.e.left, this.e.top, this.e.right, this.e.top + height, this.f1931d);
                        canvas.drawRect(this.e.left, this.e.bottom - height, this.e.right, this.e.bottom, this.f1931d);
                        break;
                    }
                }
                break;
        }
        if (this.k != 0) {
            this.f1931d.setStyle(Paint.Style.STROKE);
            this.f1931d.setColor(this.k);
            this.f1931d.setStrokeWidth(this.j);
            this.o.setFillType(Path.FillType.EVEN_ODD);
            canvas.drawPath(this.p, this.f1931d);
        }
    }
}
