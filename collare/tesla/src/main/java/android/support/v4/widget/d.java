package android.support.v4.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v4.g.b.b;
import android.support.v4.util.k;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: CircularProgressDrawable */
public class d extends Drawable implements Animatable {

    /* renamed from: c  reason: collision with root package name */
    private static final Interpolator f698c = new LinearInterpolator();

    /* renamed from: d  reason: collision with root package name */
    private static final Interpolator f699d = new b();
    private static final int[] e = {-16777216};

    /* renamed from: a  reason: collision with root package name */
    float f700a;

    /* renamed from: b  reason: collision with root package name */
    boolean f701b;
    private final a f = new a();
    private float g;
    private Resources h;
    private Animator i;

    private int a(float f2, int i2, int i3) {
        int i4 = (i2 >> 24) & 255;
        int i5 = (i2 >> 16) & 255;
        int i6 = (i2 >> 8) & 255;
        int i7 = i2 & 255;
        return ((i4 + ((int) (((float) (((i3 >> 24) & 255) - i4)) * f2))) << 24) | ((i5 + ((int) (((float) (((i3 >> 16) & 255) - i5)) * f2))) << 16) | ((i6 + ((int) (((float) (((i3 >> 8) & 255) - i6)) * f2))) << 8) | (i7 + ((int) (f2 * ((float) ((i3 & 255) - i7)))));
    }

    public int getOpacity() {
        return -3;
    }

    public d(Context context) {
        this.h = ((Context) k.a(context)).getResources();
        this.f.a(e);
        a(2.5f);
        a();
    }

    private void a(float f2, float f3, float f4, float f5) {
        a aVar = this.f;
        float f6 = this.h.getDisplayMetrics().density;
        aVar.a(f3 * f6);
        aVar.e(f2 * f6);
        aVar.b(0);
        aVar.a(f4 * f6, f5 * f6);
    }

    public void a(int i2) {
        if (i2 == 0) {
            a(11.0f, 3.0f, 12.0f, 6.0f);
        } else {
            a(7.5f, 2.5f, 10.0f, 5.0f);
        }
        invalidateSelf();
    }

    public void a(float f2) {
        this.f.a(f2);
        invalidateSelf();
    }

    public void a(boolean z) {
        this.f.a(z);
        invalidateSelf();
    }

    public void b(float f2) {
        this.f.f(f2);
        invalidateSelf();
    }

    public void a(float f2, float f3) {
        this.f.b(f2);
        this.f.c(f3);
        invalidateSelf();
    }

    public void c(float f2) {
        this.f.d(f2);
        invalidateSelf();
    }

    public void a(int... iArr) {
        this.f.a(iArr);
        this.f.b(0);
        invalidateSelf();
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        canvas.rotate(this.g, bounds.exactCenterX(), bounds.exactCenterY());
        this.f.a(canvas, bounds);
        canvas.restore();
    }

    public void setAlpha(int i2) {
        this.f.c(i2);
        invalidateSelf();
    }

    public int getAlpha() {
        return this.f.d();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f.a(colorFilter);
        invalidateSelf();
    }

    private void d(float f2) {
        this.g = f2;
    }

    public boolean isRunning() {
        return this.i.isRunning();
    }

    public void start() {
        this.i.cancel();
        this.f.k();
        if (this.f.i() != this.f.e()) {
            this.f701b = true;
            this.i.setDuration(666);
            this.i.start();
            return;
        }
        this.f.b(0);
        this.f.l();
        this.i.setDuration(1332);
        this.i.start();
    }

    public void stop() {
        this.i.cancel();
        d(BitmapDescriptorFactory.HUE_RED);
        this.f.a(false);
        this.f.b(0);
        this.f.l();
        invalidateSelf();
    }

    /* access modifiers changed from: package-private */
    public void a(float f2, a aVar) {
        if (f2 > 0.75f) {
            aVar.a(a((f2 - 0.75f) / 0.25f, aVar.h(), aVar.a()));
        } else {
            aVar.a(aVar.h());
        }
    }

    private void b(float f2, a aVar) {
        a(f2, aVar);
        aVar.b(aVar.f() + (((aVar.g() - 0.01f) - aVar.f()) * f2));
        aVar.c(aVar.g());
        aVar.d(aVar.j() + ((((float) (Math.floor((double) (aVar.j() / 0.8f)) + 1.0d)) - aVar.j()) * f2));
    }

    /* access modifiers changed from: package-private */
    public void a(float f2, a aVar, boolean z) {
        float f3;
        float f4;
        if (this.f701b) {
            b(f2, aVar);
        } else if (f2 != 1.0f || z) {
            float j = aVar.j();
            if (f2 < 0.5f) {
                float f5 = aVar.f();
                f3 = (f699d.getInterpolation(f2 / 0.5f) * 0.79f) + 0.01f + f5;
                f4 = f5;
            } else {
                f3 = aVar.f() + 0.79f;
                f4 = f3 - (((1.0f - f699d.getInterpolation((f2 - 0.5f) / 0.5f)) * 0.79f) + 0.01f);
            }
            aVar.b(f4);
            aVar.c(f3);
            aVar.d(j + (0.20999998f * f2));
            d((f2 + this.f700a) * 216.0f);
        }
    }

    private void a() {
        final a aVar = this.f;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(BitmapDescriptorFactory.HUE_RED, 1.0f);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            /* class android.support.v4.widget.d.AnonymousClass1 */

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                d.this.a(floatValue, aVar);
                d.this.a(floatValue, aVar, false);
                d.this.invalidateSelf();
            }
        });
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(f698c);
        ofFloat.addListener(new Animator.AnimatorListener() {
            /* class android.support.v4.widget.d.AnonymousClass2 */

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                d.this.f700a = BitmapDescriptorFactory.HUE_RED;
            }

            public void onAnimationRepeat(Animator animator) {
                d.this.a(1.0f, aVar, true);
                aVar.k();
                aVar.c();
                if (d.this.f701b) {
                    d.this.f701b = false;
                    animator.cancel();
                    animator.setDuration(1332);
                    animator.start();
                    aVar.a(false);
                    return;
                }
                d.this.f700a += 1.0f;
            }
        });
        this.i = ofFloat;
    }

    /* access modifiers changed from: private */
    /* compiled from: CircularProgressDrawable */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        final RectF f706a = new RectF();

        /* renamed from: b  reason: collision with root package name */
        final Paint f707b = new Paint();

        /* renamed from: c  reason: collision with root package name */
        final Paint f708c = new Paint();

        /* renamed from: d  reason: collision with root package name */
        final Paint f709d = new Paint();
        float e = BitmapDescriptorFactory.HUE_RED;
        float f = BitmapDescriptorFactory.HUE_RED;
        float g = BitmapDescriptorFactory.HUE_RED;
        float h = 5.0f;
        int[] i;
        int j;
        float k;
        float l;
        float m;
        boolean n;
        Path o;
        float p = 1.0f;
        float q;
        int r;
        int s;
        int t = 255;
        int u;

        a() {
            this.f707b.setStrokeCap(Paint.Cap.SQUARE);
            this.f707b.setAntiAlias(true);
            this.f707b.setStyle(Paint.Style.STROKE);
            this.f708c.setStyle(Paint.Style.FILL);
            this.f708c.setAntiAlias(true);
            this.f709d.setColor(0);
        }

        /* access modifiers changed from: package-private */
        public void a(float f2, float f3) {
            this.r = (int) f2;
            this.s = (int) f3;
        }

        /* access modifiers changed from: package-private */
        public void a(Canvas canvas, Rect rect) {
            RectF rectF = this.f706a;
            float f2 = this.q;
            float f3 = (this.h / 2.0f) + f2;
            if (f2 <= BitmapDescriptorFactory.HUE_RED) {
                f3 = (((float) Math.min(rect.width(), rect.height())) / 2.0f) - Math.max((((float) this.r) * this.p) / 2.0f, this.h / 2.0f);
            }
            rectF.set(((float) rect.centerX()) - f3, ((float) rect.centerY()) - f3, ((float) rect.centerX()) + f3, ((float) rect.centerY()) + f3);
            float f4 = this.e;
            float f5 = this.g;
            float f6 = (f4 + f5) * 360.0f;
            float f7 = ((this.f + f5) * 360.0f) - f6;
            this.f707b.setColor(this.u);
            this.f707b.setAlpha(this.t);
            float f8 = this.h / 2.0f;
            rectF.inset(f8, f8);
            canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, this.f709d);
            float f9 = -f8;
            rectF.inset(f9, f9);
            canvas.drawArc(rectF, f6, f7, false, this.f707b);
            a(canvas, f6, f7, rectF);
        }

        /* access modifiers changed from: package-private */
        public void a(Canvas canvas, float f2, float f3, RectF rectF) {
            if (this.n) {
                Path path = this.o;
                if (path == null) {
                    this.o = new Path();
                    this.o.setFillType(Path.FillType.EVEN_ODD);
                } else {
                    path.reset();
                }
                this.o.moveTo(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
                this.o.lineTo(((float) this.r) * this.p, BitmapDescriptorFactory.HUE_RED);
                Path path2 = this.o;
                float f4 = this.p;
                path2.lineTo((((float) this.r) * f4) / 2.0f, ((float) this.s) * f4);
                this.o.offset(((Math.min(rectF.width(), rectF.height()) / 2.0f) + rectF.centerX()) - ((((float) this.r) * this.p) / 2.0f), rectF.centerY() + (this.h / 2.0f));
                this.o.close();
                this.f708c.setColor(this.u);
                this.f708c.setAlpha(this.t);
                canvas.save();
                canvas.rotate(f2 + f3, rectF.centerX(), rectF.centerY());
                canvas.drawPath(this.o, this.f708c);
                canvas.restore();
            }
        }

        /* access modifiers changed from: package-private */
        public void a(int[] iArr) {
            this.i = iArr;
            b(0);
        }

        /* access modifiers changed from: package-private */
        public void a(int i2) {
            this.u = i2;
        }

        /* access modifiers changed from: package-private */
        public void b(int i2) {
            this.j = i2;
            this.u = this.i[this.j];
        }

        /* access modifiers changed from: package-private */
        public int a() {
            return this.i[b()];
        }

        /* access modifiers changed from: package-private */
        public int b() {
            return (this.j + 1) % this.i.length;
        }

        /* access modifiers changed from: package-private */
        public void c() {
            b(b());
        }

        /* access modifiers changed from: package-private */
        public void a(ColorFilter colorFilter) {
            this.f707b.setColorFilter(colorFilter);
        }

        /* access modifiers changed from: package-private */
        public void c(int i2) {
            this.t = i2;
        }

        /* access modifiers changed from: package-private */
        public int d() {
            return this.t;
        }

        /* access modifiers changed from: package-private */
        public void a(float f2) {
            this.h = f2;
            this.f707b.setStrokeWidth(f2);
        }

        /* access modifiers changed from: package-private */
        public void b(float f2) {
            this.e = f2;
        }

        /* access modifiers changed from: package-private */
        public float e() {
            return this.e;
        }

        /* access modifiers changed from: package-private */
        public float f() {
            return this.k;
        }

        /* access modifiers changed from: package-private */
        public float g() {
            return this.l;
        }

        /* access modifiers changed from: package-private */
        public int h() {
            return this.i[this.j];
        }

        /* access modifiers changed from: package-private */
        public void c(float f2) {
            this.f = f2;
        }

        /* access modifiers changed from: package-private */
        public float i() {
            return this.f;
        }

        /* access modifiers changed from: package-private */
        public void d(float f2) {
            this.g = f2;
        }

        /* access modifiers changed from: package-private */
        public void e(float f2) {
            this.q = f2;
        }

        /* access modifiers changed from: package-private */
        public void a(boolean z) {
            if (this.n != z) {
                this.n = z;
            }
        }

        /* access modifiers changed from: package-private */
        public void f(float f2) {
            if (f2 != this.p) {
                this.p = f2;
            }
        }

        /* access modifiers changed from: package-private */
        public float j() {
            return this.m;
        }

        /* access modifiers changed from: package-private */
        public void k() {
            this.k = this.e;
            this.l = this.f;
            this.m = this.g;
        }

        /* access modifiers changed from: package-private */
        public void l() {
            this.k = BitmapDescriptorFactory.HUE_RED;
            this.l = BitmapDescriptorFactory.HUE_RED;
            this.m = BitmapDescriptorFactory.HUE_RED;
            b(BitmapDescriptorFactory.HUE_RED);
            c(BitmapDescriptorFactory.HUE_RED);
            d(BitmapDescriptorFactory.HUE_RED);
        }
    }
}
