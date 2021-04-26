package com.facebook.react.views.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import com.facebook.react.uimanager.ae;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Arrays;
import java.util.Locale;

/* compiled from: ReactViewBackgroundDrawable */
public class d extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    private ae f3558a;

    /* renamed from: b  reason: collision with root package name */
    private ae f3559b;

    /* renamed from: c  reason: collision with root package name */
    private ae f3560c;

    /* renamed from: d  reason: collision with root package name */
    private b f3561d;
    private PathEffect e;
    private Path f;
    private Path g;
    private Path h;
    private Path i;
    private Path j;
    private RectF k;
    private RectF l;
    private RectF m;
    private RectF n;
    private PointF o;
    private PointF p;
    private PointF q;
    private PointF r;
    private boolean s = false;
    private float t = Float.NaN;
    private final Paint u = new Paint(1);
    private int v = 0;
    private int w = 255;
    private float[] x;
    private final Context y;
    private int z;

    /* compiled from: ReactViewBackgroundDrawable */
    public enum a {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_RIGHT,
        BOTTOM_LEFT,
        TOP_START,
        TOP_END,
        BOTTOM_START,
        BOTTOM_END
    }

    private static int a(float f2, float f3) {
        return ((((int) f2) << 24) & -16777216) | (((int) f3) & 16777215);
    }

    private static int a(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        int i10 = -1;
        int i11 = (i2 > 0 ? i6 : -1) & (i3 > 0 ? i7 : -1) & (i4 > 0 ? i8 : -1);
        if (i5 > 0) {
            i10 = i9;
        }
        int i12 = i10 & i11;
        if (i2 <= 0) {
            i6 = 0;
        }
        if (i3 <= 0) {
            i7 = 0;
        }
        int i13 = i6 | i7;
        if (i4 <= 0) {
            i8 = 0;
        }
        int i14 = i13 | i8;
        if (i5 <= 0) {
            i9 = 0;
        }
        if (i12 == (i14 | i9)) {
            return i12;
        }
        return 0;
    }

    public boolean c(int i2) {
        return false;
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    /* access modifiers changed from: private */
    /* compiled from: ReactViewBackgroundDrawable */
    public enum b {
        SOLID,
        DASHED,
        DOTTED;

        public static PathEffect a(b bVar, float f) {
            switch (bVar) {
                case SOLID:
                    return null;
                case DASHED:
                    float f2 = f * 3.0f;
                    return new DashPathEffect(new float[]{f2, f2, f2, f2}, BitmapDescriptorFactory.HUE_RED);
                case DOTTED:
                    return new DashPathEffect(new float[]{f, f, f, f}, BitmapDescriptorFactory.HUE_RED);
                default:
                    return null;
            }
        }
    }

    public d(Context context) {
        this.y = context;
    }

    public void draw(Canvas canvas) {
        h();
        if (!a()) {
            b(canvas);
        } else {
            a(canvas);
        }
    }

    public boolean a() {
        if (!com.facebook.yoga.a.a(this.t) && this.t > BitmapDescriptorFactory.HUE_RED) {
            return true;
        }
        float[] fArr = this.x;
        if (fArr != null) {
            for (float f2 : fArr) {
                if (!com.facebook.yoga.a.a(f2) && f2 > BitmapDescriptorFactory.HUE_RED) {
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.s = true;
    }

    public void setAlpha(int i2) {
        if (i2 != this.w) {
            this.w = i2;
            invalidateSelf();
        }
    }

    public int getAlpha() {
        return this.w;
    }

    public int getOpacity() {
        return a.a(a.a(this.v, this.w));
    }

    public void getOutline(Outline outline) {
        if (Build.VERSION.SDK_INT < 21) {
            super.getOutline(outline);
        } else if ((com.facebook.yoga.a.a(this.t) || this.t <= BitmapDescriptorFactory.HUE_RED) && this.x == null) {
            outline.setRect(getBounds());
        } else {
            g();
            outline.setConvexPath(this.h);
        }
    }

    public void a(int i2, float f2) {
        if (this.f3558a == null) {
            this.f3558a = new ae();
        }
        if (!com.facebook.react.uimanager.d.a(this.f3558a.b(i2), f2)) {
            this.f3558a.a(i2, f2);
            if (i2 != 8) {
                switch (i2) {
                }
                invalidateSelf();
            }
            this.s = true;
            invalidateSelf();
        }
    }

    public void a(int i2, float f2, float f3) {
        b(i2, f2);
        c(i2, f3);
    }

    private void b(int i2, float f2) {
        if (this.f3559b == null) {
            this.f3559b = new ae(BitmapDescriptorFactory.HUE_RED);
        }
        if (!com.facebook.react.uimanager.d.a(this.f3559b.b(i2), f2)) {
            this.f3559b.a(i2, f2);
            invalidateSelf();
        }
    }

    private void c(int i2, float f2) {
        if (this.f3560c == null) {
            this.f3560c = new ae(255.0f);
        }
        if (!com.facebook.react.uimanager.d.a(this.f3560c.b(i2), f2)) {
            this.f3560c.a(i2, f2);
            invalidateSelf();
        }
    }

    public void a(String str) {
        b bVar;
        if (str == null) {
            bVar = null;
        } else {
            bVar = b.valueOf(str.toUpperCase(Locale.US));
        }
        if (this.f3561d != bVar) {
            this.f3561d = bVar;
            this.s = true;
            invalidateSelf();
        }
    }

    public void a(float f2) {
        if (!com.facebook.react.uimanager.d.a(this.t, f2)) {
            this.t = f2;
            this.s = true;
            invalidateSelf();
        }
    }

    public void a(float f2, int i2) {
        if (this.x == null) {
            this.x = new float[8];
            Arrays.fill(this.x, Float.NaN);
        }
        if (!com.facebook.react.uimanager.d.a(this.x[i2], f2)) {
            this.x[i2] = f2;
            this.s = true;
            invalidateSelf();
        }
    }

    public float b() {
        return com.facebook.yoga.a.a(this.t) ? BitmapDescriptorFactory.HUE_RED : this.t;
    }

    public float a(a aVar) {
        return a(Float.NaN, aVar);
    }

    public float a(float f2, a aVar) {
        float[] fArr = this.x;
        if (fArr == null) {
            return f2;
        }
        float f3 = fArr[aVar.ordinal()];
        return com.facebook.yoga.a.a(f3) ? f2 : f3;
    }

    public void a(int i2) {
        this.v = i2;
        invalidateSelf();
    }

    public int c() {
        return this.z;
    }

    public boolean b(int i2) {
        if (this.z == i2) {
            return false;
        }
        this.z = i2;
        return c(i2);
    }

    public int d() {
        return this.v;
    }

    private void a(Canvas canvas) {
        int i2;
        int i3;
        float f2;
        float f3;
        float f4;
        float f5;
        g();
        canvas.save();
        int a2 = a.a(this.v, this.w);
        if (Color.alpha(a2) != 0) {
            this.u.setColor(a2);
            this.u.setStyle(Paint.Style.FILL);
            canvas.drawPath(this.f, this.u);
        }
        RectF f6 = f();
        if (f6.top > BitmapDescriptorFactory.HUE_RED || f6.bottom > BitmapDescriptorFactory.HUE_RED || f6.left > BitmapDescriptorFactory.HUE_RED || f6.right > BitmapDescriptorFactory.HUE_RED) {
            float e2 = e();
            if (f6.top != e2 || f6.bottom != e2 || f6.left != e2 || f6.right != e2) {
                this.u.setStyle(Paint.Style.FILL);
                canvas.clipPath(this.g, Region.Op.INTERSECT);
                canvas.clipPath(this.f, Region.Op.DIFFERENCE);
                boolean z2 = false;
                int e3 = e(0);
                int e4 = e(1);
                int e5 = e(2);
                int e6 = e(3);
                if (Build.VERSION.SDK_INT >= 17) {
                    if (c() == 1) {
                        z2 = true;
                    }
                    int e7 = e(4);
                    int e8 = e(5);
                    if (com.facebook.react.modules.i18nmanager.a.a().b(this.y)) {
                        if (d(4)) {
                            e3 = e7;
                        }
                        if (d(5)) {
                            e5 = e8;
                        }
                        i3 = z2 ? e5 : e3;
                        if (!z2) {
                            e3 = e5;
                        }
                        i2 = e3;
                    } else {
                        int i4 = z2 ? e8 : e7;
                        if (!z2) {
                            e7 = e8;
                        }
                        boolean d2 = d(4);
                        boolean d3 = d(5);
                        boolean z3 = z2 ? d3 : d2;
                        if (!z2) {
                            d2 = d3;
                        }
                        if (z3) {
                            e3 = i4;
                        }
                        if (d2) {
                            i3 = e3;
                            i2 = e7;
                        } else {
                            i3 = e3;
                            i2 = e5;
                        }
                    }
                } else {
                    i3 = e3;
                    i2 = e5;
                }
                float f7 = this.l.left;
                float f8 = this.l.right;
                float f9 = this.l.top;
                float f10 = this.l.bottom;
                if (f6.left > BitmapDescriptorFactory.HUE_RED) {
                    f3 = f10;
                    f4 = f9;
                    f5 = f8;
                    f2 = f7;
                    a(canvas, i3, f7, f9, this.o.x, this.o.y, this.r.x, this.r.y, f7, f3);
                } else {
                    f3 = f10;
                    f4 = f9;
                    f5 = f8;
                    f2 = f7;
                }
                if (f6.top > BitmapDescriptorFactory.HUE_RED) {
                    a(canvas, e4, f2, f4, this.o.x, this.o.y, this.p.x, this.p.y, f5, f4);
                }
                if (f6.right > BitmapDescriptorFactory.HUE_RED) {
                    a(canvas, i2, f5, f4, this.p.x, this.p.y, this.q.x, this.q.y, f5, f3);
                }
                if (f6.bottom > BitmapDescriptorFactory.HUE_RED) {
                    a(canvas, e6, f2, f3, this.r.x, this.r.y, this.q.x, this.q.y, f5, f3);
                }
            } else if (e2 > BitmapDescriptorFactory.HUE_RED) {
                this.u.setColor(a.a(e(8), this.w));
                this.u.setStyle(Paint.Style.STROKE);
                this.u.setStrokeWidth(e2);
                canvas.drawPath(this.j, this.u);
            }
        }
        canvas.restore();
    }

    private void g() {
        if (this.s) {
            this.s = false;
            if (this.f == null) {
                this.f = new Path();
            }
            if (this.g == null) {
                this.g = new Path();
            }
            if (this.h == null) {
                this.h = new Path();
            }
            if (this.j == null) {
                this.j = new Path();
            }
            if (this.k == null) {
                this.k = new RectF();
            }
            if (this.l == null) {
                this.l = new RectF();
            }
            if (this.m == null) {
                this.m = new RectF();
            }
            if (this.n == null) {
                this.n = new RectF();
            }
            this.f.reset();
            this.g.reset();
            this.h.reset();
            this.j.reset();
            this.k.set(getBounds());
            this.l.set(getBounds());
            this.m.set(getBounds());
            this.n.set(getBounds());
            float e2 = e();
            if (e2 > BitmapDescriptorFactory.HUE_RED) {
                float f2 = e2 * 0.5f;
                this.n.inset(f2, f2);
            }
            RectF f3 = f();
            this.k.top += f3.top;
            this.k.bottom -= f3.bottom;
            this.k.left += f3.left;
            this.k.right -= f3.right;
            float b2 = b();
            float a2 = a(b2, a.TOP_LEFT);
            float a3 = a(b2, a.TOP_RIGHT);
            float a4 = a(b2, a.BOTTOM_LEFT);
            float a5 = a(b2, a.BOTTOM_RIGHT);
            if (Build.VERSION.SDK_INT >= 17) {
                boolean z2 = c() == 1;
                float a6 = a(a.TOP_START);
                float a7 = a(a.TOP_END);
                float a8 = a(a.BOTTOM_START);
                float a9 = a(a.BOTTOM_END);
                if (com.facebook.react.modules.i18nmanager.a.a().b(this.y)) {
                    if (!com.facebook.yoga.a.a(a6)) {
                        a2 = a6;
                    }
                    if (!com.facebook.yoga.a.a(a7)) {
                        a3 = a7;
                    }
                    if (!com.facebook.yoga.a.a(a8)) {
                        a4 = a8;
                    }
                    if (!com.facebook.yoga.a.a(a9)) {
                        a5 = a9;
                    }
                    float f4 = z2 ? a3 : a2;
                    if (z2) {
                        a3 = a2;
                    }
                    float f5 = z2 ? a5 : a4;
                    if (z2) {
                        a5 = a4;
                    }
                    a4 = f5;
                    a2 = f4;
                } else {
                    float f6 = z2 ? a7 : a6;
                    if (!z2) {
                        a6 = a7;
                    }
                    float f7 = z2 ? a9 : a8;
                    if (!z2) {
                        a8 = a9;
                    }
                    if (!com.facebook.yoga.a.a(f6)) {
                        a2 = f6;
                    }
                    if (!com.facebook.yoga.a.a(a6)) {
                        a3 = a6;
                    }
                    if (!com.facebook.yoga.a.a(f7)) {
                        a4 = f7;
                    }
                    if (!com.facebook.yoga.a.a(a8)) {
                        a5 = a8;
                    }
                }
            }
            float max = Math.max(a2 - f3.left, (float) BitmapDescriptorFactory.HUE_RED);
            float max2 = Math.max(a2 - f3.top, (float) BitmapDescriptorFactory.HUE_RED);
            float max3 = Math.max(a3 - f3.right, (float) BitmapDescriptorFactory.HUE_RED);
            float max4 = Math.max(a3 - f3.top, (float) BitmapDescriptorFactory.HUE_RED);
            float max5 = Math.max(a5 - f3.right, (float) BitmapDescriptorFactory.HUE_RED);
            float max6 = Math.max(a5 - f3.bottom, (float) BitmapDescriptorFactory.HUE_RED);
            float max7 = Math.max(a4 - f3.left, (float) BitmapDescriptorFactory.HUE_RED);
            float max8 = Math.max(a4 - f3.bottom, (float) BitmapDescriptorFactory.HUE_RED);
            this.f.addRoundRect(this.k, new float[]{max, max2, max3, max4, max5, max6, max7, max8}, Path.Direction.CW);
            this.g.addRoundRect(this.l, new float[]{a2, a2, a3, a3, a5, a5, a4, a4}, Path.Direction.CW);
            ae aeVar = this.f3558a;
            float a10 = aeVar != null ? aeVar.a(8) / 2.0f : BitmapDescriptorFactory.HUE_RED;
            float f8 = a2 + a10;
            float f9 = a3 + a10;
            float f10 = a5 + a10;
            float f11 = a4 + a10;
            this.h.addRoundRect(this.m, new float[]{f8, f8, f9, f9, f10, f10, f11, f11}, Path.Direction.CW);
            this.j.addRoundRect(this.n, new float[]{max + a10, max2 + a10, max3 + a10, max4 + a10, max5 + a10, max6 + a10, max7 + a10, max8 + a10}, Path.Direction.CW);
            if (this.o == null) {
                this.o = new PointF();
            }
            this.o.x = this.k.left;
            this.o.y = this.k.top;
            a((double) this.k.left, (double) this.k.top, (double) (this.k.left + (max * 2.0f)), (double) (this.k.top + (max2 * 2.0f)), (double) this.l.left, (double) this.l.top, (double) this.k.left, (double) this.k.top, this.o);
            if (this.r == null) {
                this.r = new PointF();
            }
            this.r.x = this.k.left;
            this.r.y = this.k.bottom;
            a((double) this.k.left, (double) (this.k.bottom - (max8 * 2.0f)), (double) (this.k.left + (max7 * 2.0f)), (double) this.k.bottom, (double) this.l.left, (double) this.l.bottom, (double) this.k.left, (double) this.k.bottom, this.r);
            if (this.p == null) {
                this.p = new PointF();
            }
            this.p.x = this.k.right;
            this.p.y = this.k.top;
            a((double) (this.k.right - (max3 * 2.0f)), (double) this.k.top, (double) this.k.right, (double) (this.k.top + (max4 * 2.0f)), (double) this.l.right, (double) this.l.top, (double) this.k.right, (double) this.k.top, this.p);
            if (this.q == null) {
                this.q = new PointF();
            }
            this.q.x = this.k.right;
            this.q.y = this.k.bottom;
            a((double) (this.k.right - (max5 * 2.0f)), (double) (this.k.bottom - (max6 * 2.0f)), (double) this.k.right, (double) this.k.bottom, (double) this.l.right, (double) this.l.bottom, (double) this.k.right, (double) this.k.bottom, this.q);
        }
    }

    private static void a(double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, PointF pointF) {
        double d10 = (d2 + d4) / 2.0d;
        double d11 = (d3 + d5) / 2.0d;
        double d12 = d6 - d10;
        double d13 = d7 - d11;
        double abs = Math.abs(d4 - d2) / 2.0d;
        double abs2 = Math.abs(d5 - d3) / 2.0d;
        double d14 = ((d9 - d11) - d13) / ((d8 - d10) - d12);
        double d15 = d13 - (d12 * d14);
        double d16 = abs2 * abs2;
        double d17 = abs * abs;
        double d18 = d16 + (d17 * d14 * d14);
        double d19 = abs * 2.0d * abs * d15 * d14;
        double d20 = (-(d17 * ((d15 * d15) - d16))) / d18;
        double d21 = d18 * 2.0d;
        double sqrt = ((-d19) / d21) - Math.sqrt(d20 + Math.pow(d19 / d21, 2.0d));
        double d22 = sqrt + d10;
        double d23 = (d14 * sqrt) + d15 + d11;
        if (!Double.isNaN(d22) && !Double.isNaN(d23)) {
            pointF.x = (float) d22;
            pointF.y = (float) d23;
        }
    }

    public float b(float f2, int i2) {
        ae aeVar = this.f3558a;
        if (aeVar == null) {
            return f2;
        }
        float b2 = aeVar.b(i2);
        return com.facebook.yoga.a.a(b2) ? f2 : b2;
    }

    private void h() {
        b bVar = this.f3561d;
        this.e = bVar != null ? b.a(bVar, e()) : null;
        this.u.setPathEffect(this.e);
    }

    public float e() {
        ae aeVar = this.f3558a;
        return (aeVar == null || com.facebook.yoga.a.a(aeVar.b(8))) ? BitmapDescriptorFactory.HUE_RED : this.f3558a.b(8);
    }

    private void b(Canvas canvas) {
        int i2;
        int i3;
        int i4;
        d dVar;
        int a2 = a.a(this.v, this.w);
        if (Color.alpha(a2) != 0) {
            this.u.setColor(a2);
            this.u.setStyle(Paint.Style.FILL);
            canvas.drawRect(getBounds(), this.u);
        }
        RectF f2 = f();
        int round = Math.round(f2.left);
        int round2 = Math.round(f2.top);
        int round3 = Math.round(f2.right);
        int round4 = Math.round(f2.bottom);
        if (round > 0 || round3 > 0 || round2 > 0 || round4 > 0) {
            Rect bounds = getBounds();
            int e2 = e(0);
            int e3 = e(1);
            int e4 = e(2);
            int e5 = e(3);
            if (Build.VERSION.SDK_INT >= 17) {
                boolean z2 = c() == 1;
                int e6 = e(4);
                int e7 = e(5);
                if (com.facebook.react.modules.i18nmanager.a.a().b(this.y)) {
                    if (d(4)) {
                        e2 = e6;
                    }
                    if (d(5)) {
                        e4 = e7;
                    }
                    int i5 = z2 ? e4 : e2;
                    if (!z2) {
                        e2 = e4;
                    }
                    i2 = e2;
                    i3 = i5;
                } else {
                    int i6 = z2 ? e7 : e6;
                    if (!z2) {
                        e6 = e7;
                    }
                    boolean d2 = d(4);
                    boolean d3 = d(5);
                    boolean z3 = z2 ? d3 : d2;
                    if (!z2) {
                        d2 = d3;
                    }
                    if (z3) {
                        e2 = i6;
                    }
                    if (d2) {
                        i3 = e2;
                        i2 = e6;
                    } else {
                        i3 = e2;
                        i2 = e4;
                    }
                }
            } else {
                i3 = e2;
                i2 = e4;
            }
            int i7 = bounds.left;
            int i8 = bounds.top;
            int a3 = a(round, round2, round3, round4, i3, e3, i2, e5);
            if (a3 == 0) {
                this.u.setAntiAlias(false);
                int width = bounds.width();
                int height = bounds.height();
                if (round > 0) {
                    float f3 = (float) i7;
                    float f4 = (float) i8;
                    float f5 = (float) (i7 + round);
                    float f6 = (float) (i8 + round2);
                    int i9 = i8 + height;
                    i4 = i8;
                    a(canvas, i3, f3, f4, f5, f6, f5, (float) (i9 - round4), f3, (float) i9);
                } else {
                    i4 = i8;
                }
                if (round2 > 0) {
                    float f7 = (float) i7;
                    float f8 = (float) i4;
                    float f9 = (float) (i7 + round);
                    float f10 = (float) (i4 + round2);
                    int i10 = i7 + width;
                    a(canvas, e3, f7, f8, f9, f10, (float) (i10 - round3), f10, (float) i10, f8);
                }
                if (round3 > 0) {
                    int i11 = i7 + width;
                    float f11 = (float) i11;
                    int i12 = i4 + height;
                    float f12 = (float) (i11 - round3);
                    a(canvas, i2, f11, (float) i4, f11, (float) i12, f12, (float) (i12 - round4), f12, (float) (i4 + round2));
                }
                if (round4 > 0) {
                    float f13 = (float) i7;
                    int i13 = i4 + height;
                    float f14 = (float) i13;
                    int i14 = i7 + width;
                    float f15 = (float) (i13 - round4);
                    dVar = this;
                    dVar.a(canvas, e5, f13, f14, (float) i14, f14, (float) (i14 - round3), f15, (float) (i7 + round), f15);
                } else {
                    dVar = this;
                }
                dVar.u.setAntiAlias(true);
            } else if (Color.alpha(a3) != 0) {
                int i15 = bounds.right;
                int i16 = bounds.bottom;
                this.u.setColor(a3);
                if (round > 0) {
                    canvas.drawRect((float) i7, (float) i8, (float) (i7 + round), (float) (i16 - round4), this.u);
                }
                if (round2 > 0) {
                    canvas.drawRect((float) (i7 + round), (float) i8, (float) i15, (float) (i8 + round2), this.u);
                }
                if (round3 > 0) {
                    canvas.drawRect((float) (i15 - round3), (float) (i8 + round2), (float) i15, (float) i16, this.u);
                }
                if (round4 > 0) {
                    canvas.drawRect((float) i7, (float) (i16 - round4), (float) (i15 - round3), (float) i16, this.u);
                }
            }
        }
    }

    private void a(Canvas canvas, int i2, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        if (i2 != 0) {
            if (this.i == null) {
                this.i = new Path();
            }
            this.u.setColor(i2);
            this.i.reset();
            this.i.moveTo(f2, f3);
            this.i.lineTo(f4, f5);
            this.i.lineTo(f6, f7);
            this.i.lineTo(f8, f9);
            this.i.lineTo(f2, f3);
            canvas.drawPath(this.i, this.u);
        }
    }

    private boolean d(int i2) {
        ae aeVar = this.f3559b;
        float f2 = Float.NaN;
        float a2 = aeVar != null ? aeVar.a(i2) : Float.NaN;
        ae aeVar2 = this.f3560c;
        if (aeVar2 != null) {
            f2 = aeVar2.a(i2);
        }
        return !com.facebook.yoga.a.a(a2) && !com.facebook.yoga.a.a(f2);
    }

    private int e(int i2) {
        ae aeVar = this.f3559b;
        float a2 = aeVar != null ? aeVar.a(i2) : BitmapDescriptorFactory.HUE_RED;
        ae aeVar2 = this.f3560c;
        return a(aeVar2 != null ? aeVar2.a(i2) : 255.0f, a2);
    }

    public RectF f() {
        float b2 = b(BitmapDescriptorFactory.HUE_RED, 8);
        boolean z2 = true;
        float b3 = b(b2, 1);
        float b4 = b(b2, 3);
        float b5 = b(b2, 0);
        float b6 = b(b2, 2);
        if (Build.VERSION.SDK_INT >= 17 && this.f3558a != null) {
            if (c() != 1) {
                z2 = false;
            }
            float b7 = this.f3558a.b(4);
            float b8 = this.f3558a.b(5);
            if (com.facebook.react.modules.i18nmanager.a.a().b(this.y)) {
                if (com.facebook.yoga.a.a(b7)) {
                    b7 = b5;
                }
                if (!com.facebook.yoga.a.a(b8)) {
                    b6 = b8;
                }
                b5 = z2 ? b6 : b7;
                if (z2) {
                    b6 = b7;
                }
            } else {
                float f2 = z2 ? b8 : b7;
                if (!z2) {
                    b7 = b8;
                }
                if (!com.facebook.yoga.a.a(f2)) {
                    b5 = f2;
                }
                if (!com.facebook.yoga.a.a(b7)) {
                    b6 = b7;
                }
            }
        }
        return new RectF(b5, b3, b6, b4);
    }
}
