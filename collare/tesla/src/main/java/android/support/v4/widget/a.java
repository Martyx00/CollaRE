package android.support.v4.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.support.v4.g.t;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: AutoScrollHelper */
public abstract class a implements View.OnTouchListener {
    private static final int r = ViewConfiguration.getTapTimeout();

    /* renamed from: a  reason: collision with root package name */
    final C0019a f683a = new C0019a();

    /* renamed from: b  reason: collision with root package name */
    final View f684b;

    /* renamed from: c  reason: collision with root package name */
    boolean f685c;

    /* renamed from: d  reason: collision with root package name */
    boolean f686d;
    boolean e;
    private final Interpolator f = new AccelerateInterpolator();
    private Runnable g;
    private float[] h = {BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED};
    private float[] i = {Float.MAX_VALUE, Float.MAX_VALUE};
    private int j;
    private int k;
    private float[] l = {BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED};
    private float[] m = {BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED};
    private float[] n = {Float.MAX_VALUE, Float.MAX_VALUE};
    private boolean o;
    private boolean p;
    private boolean q;

    static float a(float f2, float f3, float f4) {
        return f2 > f4 ? f4 : f2 < f3 ? f3 : f2;
    }

    static int a(int i2, int i3, int i4) {
        return i2 > i4 ? i4 : i2 < i3 ? i3 : i2;
    }

    public abstract void a(int i2, int i3);

    public abstract boolean e(int i2);

    public abstract boolean f(int i2);

    public a(View view) {
        this.f684b = view;
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        float f2 = (float) ((int) ((displayMetrics.density * 1575.0f) + 0.5f));
        a(f2, f2);
        float f3 = (float) ((int) ((displayMetrics.density * 315.0f) + 0.5f));
        b(f3, f3);
        a(1);
        e(Float.MAX_VALUE, Float.MAX_VALUE);
        d(0.2f, 0.2f);
        c(1.0f, 1.0f);
        b(r);
        c(500);
        d(500);
    }

    public a a(boolean z) {
        if (this.p && !z) {
            d();
        }
        this.p = z;
        return this;
    }

    public a a(float f2, float f3) {
        float[] fArr = this.n;
        fArr[0] = f2 / 1000.0f;
        fArr[1] = f3 / 1000.0f;
        return this;
    }

    public a b(float f2, float f3) {
        float[] fArr = this.m;
        fArr[0] = f2 / 1000.0f;
        fArr[1] = f3 / 1000.0f;
        return this;
    }

    public a c(float f2, float f3) {
        float[] fArr = this.l;
        fArr[0] = f2 / 1000.0f;
        fArr[1] = f3 / 1000.0f;
        return this;
    }

    public a a(int i2) {
        this.j = i2;
        return this;
    }

    public a d(float f2, float f3) {
        float[] fArr = this.h;
        fArr[0] = f2;
        fArr[1] = f3;
        return this;
    }

    public a e(float f2, float f3) {
        float[] fArr = this.i;
        fArr[0] = f2;
        fArr[1] = f3;
        return this;
    }

    public a b(int i2) {
        this.k = i2;
        return this;
    }

    public a c(int i2) {
        this.f683a.a(i2);
        return this;
    }

    public a d(int i2) {
        this.f683a.b(i2);
        return this;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!this.p) {
            return false;
        }
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.f686d = true;
                this.o = false;
                this.f683a.a(a(0, motionEvent.getX(), (float) view.getWidth(), (float) this.f684b.getWidth()), a(1, motionEvent.getY(), (float) view.getHeight(), (float) this.f684b.getHeight()));
                if (!this.e && a()) {
                    c();
                    break;
                }
            case 1:
            case 3:
                d();
                break;
            case 2:
                this.f683a.a(a(0, motionEvent.getX(), (float) view.getWidth(), (float) this.f684b.getWidth()), a(1, motionEvent.getY(), (float) view.getHeight(), (float) this.f684b.getHeight()));
                c();
                break;
        }
        if (!this.q || !this.e) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        C0019a aVar = this.f683a;
        int f2 = aVar.f();
        int e2 = aVar.e();
        return (f2 != 0 && f(f2)) || (e2 != 0 && e(e2));
    }

    private void c() {
        int i2;
        if (this.g == null) {
            this.g = new b();
        }
        this.e = true;
        this.f685c = true;
        if (this.o || (i2 = this.k) <= 0) {
            this.g.run();
        } else {
            t.a(this.f684b, this.g, (long) i2);
        }
        this.o = true;
    }

    private void d() {
        if (this.f685c) {
            this.e = false;
        } else {
            this.f683a.b();
        }
    }

    private float a(int i2, float f2, float f3, float f4) {
        float a2 = a(this.h[i2], f3, this.i[i2], f2);
        int i3 = (a2 > BitmapDescriptorFactory.HUE_RED ? 1 : (a2 == BitmapDescriptorFactory.HUE_RED ? 0 : -1));
        if (i3 == 0) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        float f5 = this.l[i2];
        float f6 = this.m[i2];
        float f7 = this.n[i2];
        float f8 = f5 * f4;
        if (i3 > 0) {
            return a(a2 * f8, f6, f7);
        }
        return -a((-a2) * f8, f6, f7);
    }

    private float a(float f2, float f3, float f4, float f5) {
        float f6;
        float a2 = a(f2 * f3, (float) BitmapDescriptorFactory.HUE_RED, f4);
        float f7 = f(f3 - f5, a2) - f(f5, a2);
        if (f7 < BitmapDescriptorFactory.HUE_RED) {
            f6 = -this.f.getInterpolation(-f7);
        } else if (f7 <= BitmapDescriptorFactory.HUE_RED) {
            return BitmapDescriptorFactory.HUE_RED;
        } else {
            f6 = this.f.getInterpolation(f7);
        }
        return a(f6, -1.0f, 1.0f);
    }

    private float f(float f2, float f3) {
        if (f3 == BitmapDescriptorFactory.HUE_RED) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        int i2 = this.j;
        switch (i2) {
            case 0:
            case 1:
                if (f2 < f3) {
                    if (f2 >= BitmapDescriptorFactory.HUE_RED) {
                        return 1.0f - (f2 / f3);
                    }
                    if (!this.e || i2 != 1) {
                        return BitmapDescriptorFactory.HUE_RED;
                    }
                    return 1.0f;
                }
                break;
            case 2:
                if (f2 < BitmapDescriptorFactory.HUE_RED) {
                    return f2 / (-f3);
                }
                break;
        }
        return BitmapDescriptorFactory.HUE_RED;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0);
        this.f684b.onTouchEvent(obtain);
        obtain.recycle();
    }

    /* access modifiers changed from: private */
    /* compiled from: AutoScrollHelper */
    public class b implements Runnable {
        b() {
        }

        public void run() {
            if (a.this.e) {
                if (a.this.f685c) {
                    a aVar = a.this;
                    aVar.f685c = false;
                    aVar.f683a.a();
                }
                C0019a aVar2 = a.this.f683a;
                if (aVar2.c() || !a.this.a()) {
                    a.this.e = false;
                    return;
                }
                if (a.this.f686d) {
                    a aVar3 = a.this;
                    aVar3.f686d = false;
                    aVar3.b();
                }
                aVar2.d();
                a.this.a(aVar2.g(), aVar2.h());
                t.a(a.this.f684b, this);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: android.support.v4.widget.a$a  reason: collision with other inner class name */
    /* compiled from: AutoScrollHelper */
    public static class C0019a {

        /* renamed from: a  reason: collision with root package name */
        private int f687a;

        /* renamed from: b  reason: collision with root package name */
        private int f688b;

        /* renamed from: c  reason: collision with root package name */
        private float f689c;

        /* renamed from: d  reason: collision with root package name */
        private float f690d;
        private long e = Long.MIN_VALUE;
        private long f = 0;
        private int g = 0;
        private int h = 0;
        private long i = -1;
        private float j;
        private int k;

        private float a(float f2) {
            return (-4.0f * f2 * f2) + (f2 * 4.0f);
        }

        C0019a() {
        }

        public void a(int i2) {
            this.f687a = i2;
        }

        public void b(int i2) {
            this.f688b = i2;
        }

        public void a() {
            this.e = AnimationUtils.currentAnimationTimeMillis();
            this.i = -1;
            this.f = this.e;
            this.j = 0.5f;
            this.g = 0;
            this.h = 0;
        }

        public void b() {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.k = a.a((int) (currentAnimationTimeMillis - this.e), 0, this.f688b);
            this.j = a(currentAnimationTimeMillis);
            this.i = currentAnimationTimeMillis;
        }

        public boolean c() {
            return this.i > 0 && AnimationUtils.currentAnimationTimeMillis() > this.i + ((long) this.k);
        }

        private float a(long j2) {
            if (j2 < this.e) {
                return BitmapDescriptorFactory.HUE_RED;
            }
            long j3 = this.i;
            if (j3 < 0 || j2 < j3) {
                return a.a(((float) (j2 - this.e)) / ((float) this.f687a), (float) BitmapDescriptorFactory.HUE_RED, 1.0f) * 0.5f;
            }
            long j4 = j2 - j3;
            float f2 = this.j;
            return (1.0f - f2) + (f2 * a.a(((float) j4) / ((float) this.k), (float) BitmapDescriptorFactory.HUE_RED, 1.0f));
        }

        public void d() {
            if (this.f != 0) {
                long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                float a2 = a(a(currentAnimationTimeMillis));
                this.f = currentAnimationTimeMillis;
                float f2 = ((float) (currentAnimationTimeMillis - this.f)) * a2;
                this.g = (int) (this.f689c * f2);
                this.h = (int) (f2 * this.f690d);
                return;
            }
            throw new RuntimeException("Cannot compute scroll delta before calling start()");
        }

        public void a(float f2, float f3) {
            this.f689c = f2;
            this.f690d = f3;
        }

        public int e() {
            float f2 = this.f689c;
            return (int) (f2 / Math.abs(f2));
        }

        public int f() {
            float f2 = this.f690d;
            return (int) (f2 / Math.abs(f2));
        }

        public int g() {
            return this.g;
        }

        public int h() {
            return this.h;
        }
    }
}
