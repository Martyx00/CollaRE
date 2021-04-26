package android.support.v4.widget;

import android.content.Context;
import android.support.v4.g.t;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Arrays;

/* compiled from: ViewDragHelper */
public class s {
    private static final Interpolator v = new Interpolator() {
        /* class android.support.v4.widget.s.AnonymousClass1 */

        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private int f760a;

    /* renamed from: b  reason: collision with root package name */
    private int f761b;

    /* renamed from: c  reason: collision with root package name */
    private int f762c = -1;

    /* renamed from: d  reason: collision with root package name */
    private float[] f763d;
    private float[] e;
    private float[] f;
    private float[] g;
    private int[] h;
    private int[] i;
    private int[] j;
    private int k;
    private VelocityTracker l;
    private float m;
    private float n;
    private int o;
    private int p;
    private OverScroller q;
    private final a r;
    private View s;
    private boolean t;
    private final ViewGroup u;
    private final Runnable w = new Runnable() {
        /* class android.support.v4.widget.s.AnonymousClass2 */

        public void run() {
            s.this.c(0);
        }
    };

    /* compiled from: ViewDragHelper */
    public static abstract class a {
        public int a(View view) {
            return 0;
        }

        public int a(View view, int i, int i2) {
            return 0;
        }

        public void a(int i) {
        }

        public void a(int i, int i2) {
        }

        public void a(View view, float f, float f2) {
        }

        public void a(View view, int i, int i2, int i3, int i4) {
        }

        public abstract boolean a(View view, int i);

        public int b(View view) {
            return 0;
        }

        public int b(View view, int i, int i2) {
            return 0;
        }

        public void b(int i, int i2) {
        }

        public void b(View view, int i) {
        }

        public boolean b(int i) {
            return false;
        }

        public int c(int i) {
            return i;
        }
    }

    public static s a(ViewGroup viewGroup, a aVar) {
        return new s(viewGroup.getContext(), viewGroup, aVar);
    }

    public static s a(ViewGroup viewGroup, float f2, a aVar) {
        s a2 = a(viewGroup, aVar);
        a2.f761b = (int) (((float) a2.f761b) * (1.0f / f2));
        return a2;
    }

    private s(Context context, ViewGroup viewGroup, a aVar) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        } else if (aVar != null) {
            this.u = viewGroup;
            this.r = aVar;
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            this.o = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
            this.f761b = viewConfiguration.getScaledTouchSlop();
            this.m = (float) viewConfiguration.getScaledMaximumFlingVelocity();
            this.n = (float) viewConfiguration.getScaledMinimumFlingVelocity();
            this.q = new OverScroller(context, v);
        } else {
            throw new IllegalArgumentException("Callback may not be null");
        }
    }

    public void a(float f2) {
        this.n = f2;
    }

    public int a() {
        return this.f760a;
    }

    public void a(int i2) {
        this.p = i2;
    }

    public int b() {
        return this.o;
    }

    public void a(View view, int i2) {
        if (view.getParent() == this.u) {
            this.s = view;
            this.f762c = i2;
            this.r.b(view, i2);
            c(1);
            return;
        }
        throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.u + ")");
    }

    public View c() {
        return this.s;
    }

    public int d() {
        return this.f761b;
    }

    public void e() {
        this.f762c = -1;
        f();
        VelocityTracker velocityTracker = this.l;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.l = null;
        }
    }

    public boolean a(View view, int i2, int i3) {
        this.s = view;
        this.f762c = -1;
        boolean a2 = a(i2, i3, 0, 0);
        if (!a2 && this.f760a == 0 && this.s != null) {
            this.s = null;
        }
        return a2;
    }

    public boolean a(int i2, int i3) {
        if (this.t) {
            return a(i2, i3, (int) this.l.getXVelocity(this.f762c), (int) this.l.getYVelocity(this.f762c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    private boolean a(int i2, int i3, int i4, int i5) {
        int left = this.s.getLeft();
        int top = this.s.getTop();
        int i6 = i2 - left;
        int i7 = i3 - top;
        if (i6 == 0 && i7 == 0) {
            this.q.abortAnimation();
            c(0);
            return false;
        }
        this.q.startScroll(left, top, i6, i7, a(this.s, i6, i7, i4, i5));
        c(2);
        return true;
    }

    private int a(View view, int i2, int i3, int i4, int i5) {
        float f2;
        float f3;
        float f4;
        float f5;
        int b2 = b(i4, (int) this.n, (int) this.m);
        int b3 = b(i5, (int) this.n, (int) this.m);
        int abs = Math.abs(i2);
        int abs2 = Math.abs(i3);
        int abs3 = Math.abs(b2);
        int abs4 = Math.abs(b3);
        int i6 = abs3 + abs4;
        int i7 = abs + abs2;
        if (b2 != 0) {
            f3 = (float) abs3;
            f2 = (float) i6;
        } else {
            f3 = (float) abs;
            f2 = (float) i7;
        }
        float f6 = f3 / f2;
        if (b3 != 0) {
            f5 = (float) abs4;
            f4 = (float) i6;
        } else {
            f5 = (float) abs2;
            f4 = (float) i7;
        }
        float f7 = f5 / f4;
        return (int) ((((float) a(i2, b2, this.r.a(view))) * f6) + (((float) a(i3, b3, this.r.b(view))) * f7));
    }

    private int a(int i2, int i3, int i4) {
        int i5;
        if (i2 == 0) {
            return 0;
        }
        int width = this.u.getWidth();
        float f2 = (float) (width / 2);
        float b2 = f2 + (b(Math.min(1.0f, ((float) Math.abs(i2)) / ((float) width))) * f2);
        int abs = Math.abs(i3);
        if (abs > 0) {
            i5 = Math.round(Math.abs(b2 / ((float) abs)) * 1000.0f) * 4;
        } else {
            i5 = (int) (((((float) Math.abs(i2)) / ((float) i4)) + 1.0f) * 256.0f);
        }
        return Math.min(i5, 600);
    }

    private int b(int i2, int i3, int i4) {
        int abs = Math.abs(i2);
        if (abs < i3) {
            return 0;
        }
        if (abs > i4) {
            return i2 > 0 ? i4 : -i4;
        }
        return i2;
    }

    private float a(float f2, float f3, float f4) {
        float abs = Math.abs(f2);
        if (abs < f3) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        if (abs > f4) {
            return f2 > BitmapDescriptorFactory.HUE_RED ? f4 : -f4;
        }
        return f2;
    }

    private float b(float f2) {
        return (float) Math.sin((double) ((f2 - 0.5f) * 0.47123894f));
    }

    public boolean a(boolean z) {
        if (this.f760a == 2) {
            boolean computeScrollOffset = this.q.computeScrollOffset();
            int currX = this.q.getCurrX();
            int currY = this.q.getCurrY();
            int left = currX - this.s.getLeft();
            int top = currY - this.s.getTop();
            if (left != 0) {
                t.e(this.s, left);
            }
            if (top != 0) {
                t.d(this.s, top);
            }
            if (!(left == 0 && top == 0)) {
                this.r.a(this.s, currX, currY, left, top);
            }
            if (computeScrollOffset && currX == this.q.getFinalX() && currY == this.q.getFinalY()) {
                this.q.abortAnimation();
                computeScrollOffset = false;
            }
            if (!computeScrollOffset) {
                if (z) {
                    this.u.post(this.w);
                } else {
                    c(0);
                }
            }
        }
        if (this.f760a == 2) {
            return true;
        }
        return false;
    }

    private void a(float f2, float f3) {
        this.t = true;
        this.r.a(this.s, f2, f3);
        this.t = false;
        if (this.f760a == 1) {
            c(0);
        }
    }

    private void f() {
        float[] fArr = this.f763d;
        if (fArr != null) {
            Arrays.fill(fArr, (float) BitmapDescriptorFactory.HUE_RED);
            Arrays.fill(this.e, (float) BitmapDescriptorFactory.HUE_RED);
            Arrays.fill(this.f, (float) BitmapDescriptorFactory.HUE_RED);
            Arrays.fill(this.g, (float) BitmapDescriptorFactory.HUE_RED);
            Arrays.fill(this.h, 0);
            Arrays.fill(this.i, 0);
            Arrays.fill(this.j, 0);
            this.k = 0;
        }
    }

    private void e(int i2) {
        if (this.f763d != null && b(i2)) {
            this.f763d[i2] = 0.0f;
            this.e[i2] = 0.0f;
            this.f[i2] = 0.0f;
            this.g[i2] = 0.0f;
            this.h[i2] = 0;
            this.i[i2] = 0;
            this.j[i2] = 0;
            this.k = (~(1 << i2)) & this.k;
        }
    }

    private void f(int i2) {
        float[] fArr = this.f763d;
        if (fArr == null || fArr.length <= i2) {
            int i3 = i2 + 1;
            float[] fArr2 = new float[i3];
            float[] fArr3 = new float[i3];
            float[] fArr4 = new float[i3];
            float[] fArr5 = new float[i3];
            int[] iArr = new int[i3];
            int[] iArr2 = new int[i3];
            int[] iArr3 = new int[i3];
            float[] fArr6 = this.f763d;
            if (fArr6 != null) {
                System.arraycopy(fArr6, 0, fArr2, 0, fArr6.length);
                float[] fArr7 = this.e;
                System.arraycopy(fArr7, 0, fArr3, 0, fArr7.length);
                float[] fArr8 = this.f;
                System.arraycopy(fArr8, 0, fArr4, 0, fArr8.length);
                float[] fArr9 = this.g;
                System.arraycopy(fArr9, 0, fArr5, 0, fArr9.length);
                int[] iArr4 = this.h;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.i;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.j;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.f763d = fArr2;
            this.e = fArr3;
            this.f = fArr4;
            this.g = fArr5;
            this.h = iArr;
            this.i = iArr2;
            this.j = iArr3;
        }
    }

    private void a(float f2, float f3, int i2) {
        f(i2);
        float[] fArr = this.f763d;
        this.f[i2] = f2;
        fArr[i2] = f2;
        float[] fArr2 = this.e;
        this.g[i2] = f3;
        fArr2[i2] = f3;
        this.h[i2] = e((int) f2, (int) f3);
        this.k |= 1 << i2;
    }

    private void c(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i2 = 0; i2 < pointerCount; i2++) {
            int pointerId = motionEvent.getPointerId(i2);
            if (g(pointerId)) {
                float x = motionEvent.getX(i2);
                float y = motionEvent.getY(i2);
                this.f[pointerId] = x;
                this.g[pointerId] = y;
            }
        }
    }

    public boolean b(int i2) {
        return ((1 << i2) & this.k) != 0;
    }

    /* access modifiers changed from: package-private */
    public void c(int i2) {
        this.u.removeCallbacks(this.w);
        if (this.f760a != i2) {
            this.f760a = i2;
            this.r.a(i2);
            if (this.f760a == 0) {
                this.s = null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b(View view, int i2) {
        if (view == this.s && this.f762c == i2) {
            return true;
        }
        if (view == null || !this.r.a(view, i2)) {
            return false;
        }
        this.f762c = i2;
        a(view, i2);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00d9, code lost:
        if (r12 != r11) goto L_0x00e2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(android.view.MotionEvent r17) {
        /*
        // Method dump skipped, instructions count: 332
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.s.a(android.view.MotionEvent):boolean");
    }

    public void b(MotionEvent motionEvent) {
        int i2;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            e();
        }
        if (this.l == null) {
            this.l = VelocityTracker.obtain();
        }
        this.l.addMovement(motionEvent);
        int i3 = 0;
        switch (actionMasked) {
            case 0:
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                int pointerId = motionEvent.getPointerId(0);
                View d2 = d((int) x, (int) y);
                a(x, y, pointerId);
                b(d2, pointerId);
                int i4 = this.h[pointerId];
                int i5 = this.p;
                if ((i4 & i5) != 0) {
                    this.r.a(i4 & i5, pointerId);
                    return;
                }
                return;
            case 1:
                if (this.f760a == 1) {
                    g();
                }
                e();
                return;
            case 2:
                if (this.f760a != 1) {
                    int pointerCount = motionEvent.getPointerCount();
                    while (i3 < pointerCount) {
                        int pointerId2 = motionEvent.getPointerId(i3);
                        if (g(pointerId2)) {
                            float x2 = motionEvent.getX(i3);
                            float y2 = motionEvent.getY(i3);
                            float f2 = x2 - this.f763d[pointerId2];
                            float f3 = y2 - this.e[pointerId2];
                            b(f2, f3, pointerId2);
                            if (this.f760a != 1) {
                                View d3 = d((int) x2, (int) y2);
                                if (a(d3, f2, f3) && b(d3, pointerId2)) {
                                }
                            }
                            c(motionEvent);
                            return;
                        }
                        i3++;
                    }
                    c(motionEvent);
                    return;
                } else if (g(this.f762c)) {
                    int findPointerIndex = motionEvent.findPointerIndex(this.f762c);
                    float x3 = motionEvent.getX(findPointerIndex);
                    float y3 = motionEvent.getY(findPointerIndex);
                    float[] fArr = this.f;
                    int i6 = this.f762c;
                    int i7 = (int) (x3 - fArr[i6]);
                    int i8 = (int) (y3 - this.g[i6]);
                    b(this.s.getLeft() + i7, this.s.getTop() + i8, i7, i8);
                    c(motionEvent);
                    return;
                } else {
                    return;
                }
            case 3:
                if (this.f760a == 1) {
                    a(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
                }
                e();
                return;
            case 4:
            default:
                return;
            case 5:
                int pointerId3 = motionEvent.getPointerId(actionIndex);
                float x4 = motionEvent.getX(actionIndex);
                float y4 = motionEvent.getY(actionIndex);
                a(x4, y4, pointerId3);
                if (this.f760a == 0) {
                    b(d((int) x4, (int) y4), pointerId3);
                    int i9 = this.h[pointerId3];
                    int i10 = this.p;
                    if ((i9 & i10) != 0) {
                        this.r.a(i9 & i10, pointerId3);
                        return;
                    }
                    return;
                } else if (c((int) x4, (int) y4)) {
                    b(this.s, pointerId3);
                    return;
                } else {
                    return;
                }
            case 6:
                int pointerId4 = motionEvent.getPointerId(actionIndex);
                if (this.f760a == 1 && pointerId4 == this.f762c) {
                    int pointerCount2 = motionEvent.getPointerCount();
                    while (true) {
                        if (i3 < pointerCount2) {
                            int pointerId5 = motionEvent.getPointerId(i3);
                            if (pointerId5 != this.f762c) {
                                View d4 = d((int) motionEvent.getX(i3), (int) motionEvent.getY(i3));
                                View view = this.s;
                                if (d4 == view && b(view, pointerId5)) {
                                    i2 = this.f762c;
                                }
                            }
                            i3++;
                        } else {
                            i2 = -1;
                        }
                    }
                    if (i2 == -1) {
                        g();
                    }
                }
                e(pointerId4);
                return;
        }
    }

    private void b(float f2, float f3, int i2) {
        int i3 = 1;
        if (!a(f2, f3, i2, 1)) {
            i3 = 0;
        }
        if (a(f3, f2, i2, 4)) {
            i3 |= 4;
        }
        if (a(f2, f3, i2, 2)) {
            i3 |= 2;
        }
        if (a(f3, f2, i2, 8)) {
            i3 |= 8;
        }
        if (i3 != 0) {
            int[] iArr = this.i;
            iArr[i2] = iArr[i2] | i3;
            this.r.b(i3, i2);
        }
    }

    private boolean a(float f2, float f3, int i2, int i3) {
        float abs = Math.abs(f2);
        float abs2 = Math.abs(f3);
        if (!((this.h[i2] & i3) != i3 || (this.p & i3) == 0 || (this.j[i2] & i3) == i3 || (this.i[i2] & i3) == i3)) {
            int i4 = this.f761b;
            if (abs > ((float) i4) || abs2 > ((float) i4)) {
                if (abs < abs2 * 0.5f && this.r.b(i3)) {
                    int[] iArr = this.j;
                    iArr[i2] = iArr[i2] | i3;
                    return false;
                } else if ((this.i[i2] & i3) != 0 || abs <= ((float) this.f761b)) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean a(View view, float f2, float f3) {
        if (view == null) {
            return false;
        }
        boolean z = this.r.a(view) > 0;
        boolean z2 = this.r.b(view) > 0;
        if (z && z2) {
            int i2 = this.f761b;
            if ((f2 * f2) + (f3 * f3) > ((float) (i2 * i2))) {
                return true;
            }
            return false;
        } else if (z) {
            if (Math.abs(f2) > ((float) this.f761b)) {
                return true;
            }
            return false;
        } else if (!z2 || Math.abs(f3) <= ((float) this.f761b)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean d(int i2) {
        int length = this.f763d.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (b(i2, i3)) {
                return true;
            }
        }
        return false;
    }

    public boolean b(int i2, int i3) {
        if (!b(i3)) {
            return false;
        }
        boolean z = (i2 & 1) == 1;
        boolean z2 = (i2 & 2) == 2;
        float f2 = this.f[i3] - this.f763d[i3];
        float f3 = this.g[i3] - this.e[i3];
        if (z && z2) {
            int i4 = this.f761b;
            if ((f2 * f2) + (f3 * f3) > ((float) (i4 * i4))) {
                return true;
            }
            return false;
        } else if (z) {
            if (Math.abs(f2) > ((float) this.f761b)) {
                return true;
            }
            return false;
        } else if (!z2 || Math.abs(f3) <= ((float) this.f761b)) {
            return false;
        } else {
            return true;
        }
    }

    private void g() {
        this.l.computeCurrentVelocity(1000, this.m);
        a(a(this.l.getXVelocity(this.f762c), this.n, this.m), a(this.l.getYVelocity(this.f762c), this.n, this.m));
    }

    private void b(int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int left = this.s.getLeft();
        int top = this.s.getTop();
        if (i4 != 0) {
            int a2 = this.r.a(this.s, i2, i4);
            t.e(this.s, a2 - left);
            i6 = a2;
        } else {
            i6 = i2;
        }
        if (i5 != 0) {
            int b2 = this.r.b(this.s, i3, i5);
            t.d(this.s, b2 - top);
            i7 = b2;
        } else {
            i7 = i3;
        }
        if (i4 != 0 || i5 != 0) {
            this.r.a(this.s, i6, i7, i6 - left, i7 - top);
        }
    }

    public boolean c(int i2, int i3) {
        return b(this.s, i2, i3);
    }

    public boolean b(View view, int i2, int i3) {
        if (view != null && i2 >= view.getLeft() && i2 < view.getRight() && i3 >= view.getTop() && i3 < view.getBottom()) {
            return true;
        }
        return false;
    }

    public View d(int i2, int i3) {
        for (int childCount = this.u.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.u.getChildAt(this.r.c(childCount));
            if (i2 >= childAt.getLeft() && i2 < childAt.getRight() && i3 >= childAt.getTop() && i3 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    private int e(int i2, int i3) {
        int i4 = i2 < this.u.getLeft() + this.o ? 1 : 0;
        if (i3 < this.u.getTop() + this.o) {
            i4 |= 4;
        }
        if (i2 > this.u.getRight() - this.o) {
            i4 |= 2;
        }
        return i3 > this.u.getBottom() - this.o ? i4 | 8 : i4;
    }

    private boolean g(int i2) {
        if (b(i2)) {
            return true;
        }
        Log.e("ViewDragHelper", "Ignoring pointerId=" + i2 + " because ACTION_DOWN was not received " + "for this pointer before ACTION_MOVE. It likely happened because " + " ViewDragHelper did not receive all the events in the event stream.");
        return false;
    }
}
