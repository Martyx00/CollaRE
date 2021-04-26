package android.support.v4.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v4.g.a.a;
import android.support.v4.g.t;
import android.support.v4.widget.s;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.List;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* compiled from: DrawerLayout */
public class h extends ViewGroup {

    /* renamed from: a  reason: collision with root package name */
    static final int[] f719a = {16842931};

    /* renamed from: b  reason: collision with root package name */
    static final boolean f720b = (Build.VERSION.SDK_INT >= 19);

    /* renamed from: c  reason: collision with root package name */
    private static final int[] f721c = {16843828};

    /* renamed from: d  reason: collision with root package name */
    private static final boolean f722d;
    private float A;
    private Drawable B;
    private Drawable C;
    private Drawable D;
    private CharSequence E;
    private CharSequence F;
    private Object G;
    private boolean H;
    private Drawable I;
    private Drawable J;
    private Drawable K;
    private Drawable L;
    private final ArrayList<View> M;
    private Rect N;
    private Matrix O;
    private final b e;
    private float f;
    private int g;
    private int h;
    private float i;
    private Paint j;
    private final s k;
    private final s l;
    private final f m;
    private final f n;
    private int o;
    private boolean p;
    private boolean q;
    private int r;
    private int s;
    private int t;
    private int u;
    private boolean v;
    private boolean w;
    private c x;
    private List<c> y;
    private float z;

    /* compiled from: DrawerLayout */
    public interface c {
        void a(int i);

        void a(View view);

        void a(View view, float f);

        void b(View view);
    }

    static {
        boolean z2 = true;
        if (Build.VERSION.SDK_INT < 21) {
            z2 = false;
        }
        f722d = z2;
    }

    public h(Context context) {
        this(context, null);
    }

    public h(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public h(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.e = new b();
        this.h = -1728053248;
        this.j = new Paint();
        this.q = true;
        this.r = 3;
        this.s = 3;
        this.t = 3;
        this.u = 3;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        setDescendantFocusability(PKIFailureInfo.transactionIdInUse);
        float f2 = getResources().getDisplayMetrics().density;
        this.g = (int) ((64.0f * f2) + 0.5f);
        float f3 = 400.0f * f2;
        this.m = new f(3);
        this.n = new f(5);
        this.k = s.a(this, 1.0f, this.m);
        this.k.a(1);
        this.k.a(f3);
        this.m.a(this.k);
        this.l = s.a(this, 1.0f, this.n);
        this.l.a(2);
        this.l.a(f3);
        this.n.a(this.l);
        setFocusableInTouchMode(true);
        t.a((View) this, 1);
        t.a(this, new a());
        setMotionEventSplittingEnabled(false);
        if (t.n(this)) {
            if (Build.VERSION.SDK_INT >= 21) {
                setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                    /* class android.support.v4.widget.h.AnonymousClass1 */

                    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                        ((h) view).a(windowInsets, windowInsets.getSystemWindowInsetTop() > 0);
                        return windowInsets.consumeSystemWindowInsets();
                    }
                });
                setSystemUiVisibility(1280);
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(f721c);
                try {
                    this.B = obtainStyledAttributes.getDrawable(0);
                } finally {
                    obtainStyledAttributes.recycle();
                }
            } else {
                this.B = null;
            }
        }
        this.f = f2 * 10.0f;
        this.M = new ArrayList<>();
    }

    public void setDrawerElevation(float f2) {
        this.f = f2;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (g(childAt)) {
                t.a(childAt, this.f);
            }
        }
    }

    public float getDrawerElevation() {
        return f722d ? this.f : BitmapDescriptorFactory.HUE_RED;
    }

    public void a(Object obj, boolean z2) {
        this.G = obj;
        this.H = z2;
        setWillNotDraw(!z2 && getBackground() == null);
        requestLayout();
    }

    public void setScrimColor(int i2) {
        this.h = i2;
        invalidate();
    }

    @Deprecated
    public void setDrawerListener(c cVar) {
        c cVar2 = this.x;
        if (cVar2 != null) {
            b(cVar2);
        }
        if (cVar != null) {
            a(cVar);
        }
        this.x = cVar;
    }

    public void a(c cVar) {
        if (cVar != null) {
            if (this.y == null) {
                this.y = new ArrayList();
            }
            this.y.add(cVar);
        }
    }

    public void b(c cVar) {
        List<c> list;
        if (cVar != null && (list = this.y) != null) {
            list.remove(cVar);
        }
    }

    public void setDrawerLockMode(int i2) {
        a(i2, 3);
        a(i2, 5);
    }

    public void a(int i2, int i3) {
        int a2 = android.support.v4.g.e.a(i3, t.d(this));
        if (i3 == 3) {
            this.r = i2;
        } else if (i3 == 5) {
            this.s = i2;
        } else if (i3 == 8388611) {
            this.t = i2;
        } else if (i3 == 8388613) {
            this.u = i2;
        }
        if (i2 != 0) {
            (a2 == 3 ? this.k : this.l).e();
        }
        switch (i2) {
            case 1:
                View c2 = c(a2);
                if (c2 != null) {
                    i(c2);
                    return;
                }
                return;
            case 2:
                View c3 = c(a2);
                if (c3 != null) {
                    h(c3);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public int a(int i2) {
        int i3;
        int i4;
        int i5;
        int d2 = t.d(this);
        if (i2 == 3) {
            int i6 = this.r;
            if (i6 != 3) {
                return i6;
            }
            int i7 = d2 == 0 ? this.t : this.u;
            if (i7 != 3) {
                return i7;
            }
            return 0;
        } else if (i2 == 5) {
            int i8 = this.s;
            if (i8 != 3) {
                return i8;
            }
            if (d2 == 0) {
                i3 = this.u;
            } else {
                i3 = this.t;
            }
            if (i3 != 3) {
                return i3;
            }
            return 0;
        } else if (i2 == 8388611) {
            int i9 = this.t;
            if (i9 != 3) {
                return i9;
            }
            if (d2 == 0) {
                i4 = this.r;
            } else {
                i4 = this.s;
            }
            if (i4 != 3) {
                return i4;
            }
            return 0;
        } else if (i2 != 8388613) {
            return 0;
        } else {
            int i10 = this.u;
            if (i10 != 3) {
                return i10;
            }
            if (d2 == 0) {
                i5 = this.s;
            } else {
                i5 = this.r;
            }
            if (i5 != 3) {
                return i5;
            }
            return 0;
        }
    }

    public int a(View view) {
        if (g(view)) {
            return a(((d) view.getLayoutParams()).f726a);
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public CharSequence b(int i2) {
        int a2 = android.support.v4.g.e.a(i2, t.d(this));
        if (a2 == 3) {
            return this.E;
        }
        if (a2 == 5) {
            return this.F;
        }
        return null;
    }

    private boolean a(float f2, float f3, View view) {
        if (this.N == null) {
            this.N = new Rect();
        }
        view.getHitRect(this.N);
        return this.N.contains((int) f2, (int) f3);
    }

    private boolean a(MotionEvent motionEvent, View view) {
        if (!view.getMatrix().isIdentity()) {
            MotionEvent b2 = b(motionEvent, view);
            boolean dispatchGenericMotionEvent = view.dispatchGenericMotionEvent(b2);
            b2.recycle();
            return dispatchGenericMotionEvent;
        }
        float scrollX = (float) (getScrollX() - view.getLeft());
        float scrollY = (float) (getScrollY() - view.getTop());
        motionEvent.offsetLocation(scrollX, scrollY);
        boolean dispatchGenericMotionEvent2 = view.dispatchGenericMotionEvent(motionEvent);
        motionEvent.offsetLocation(-scrollX, -scrollY);
        return dispatchGenericMotionEvent2;
    }

    private MotionEvent b(MotionEvent motionEvent, View view) {
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.offsetLocation((float) (getScrollX() - view.getLeft()), (float) (getScrollY() - view.getTop()));
        Matrix matrix = view.getMatrix();
        if (!matrix.isIdentity()) {
            if (this.O == null) {
                this.O = new Matrix();
            }
            matrix.invert(this.O);
            obtain.transform(this.O);
        }
        return obtain;
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3, View view) {
        int a2 = this.k.a();
        int a3 = this.l.a();
        int i4 = 2;
        if (a2 == 1 || a3 == 1) {
            i4 = 1;
        } else if (!(a2 == 2 || a3 == 2)) {
            i4 = 0;
        }
        if (view != null && i3 == 0) {
            d dVar = (d) view.getLayoutParams();
            if (dVar.f727b == BitmapDescriptorFactory.HUE_RED) {
                b(view);
            } else if (dVar.f727b == 1.0f) {
                c(view);
            }
        }
        if (i4 != this.o) {
            this.o = i4;
            List<c> list = this.y;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.y.get(size).a(i4);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b(View view) {
        View rootView;
        d dVar = (d) view.getLayoutParams();
        if ((dVar.f729d & 1) == 1) {
            dVar.f729d = 0;
            List<c> list = this.y;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.y.get(size).b(view);
                }
            }
            c(view, false);
            if (hasWindowFocus() && (rootView = getRootView()) != null) {
                rootView.sendAccessibilityEvent(32);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void c(View view) {
        d dVar = (d) view.getLayoutParams();
        if ((dVar.f729d & 1) == 0) {
            dVar.f729d = 1;
            List<c> list = this.y;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.y.get(size).a(view);
                }
            }
            c(view, true);
            if (hasWindowFocus()) {
                sendAccessibilityEvent(32);
            }
        }
    }

    private void c(View view, boolean z2) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((z2 || g(childAt)) && (!z2 || childAt != view)) {
                t.a(childAt, 4);
            } else {
                t.a(childAt, 1);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(View view, float f2) {
        List<c> list = this.y;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.y.get(size).a(view, f2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b(View view, float f2) {
        d dVar = (d) view.getLayoutParams();
        if (f2 != dVar.f727b) {
            dVar.f727b = f2;
            a(view, f2);
        }
    }

    /* access modifiers changed from: package-private */
    public float d(View view) {
        return ((d) view.getLayoutParams()).f727b;
    }

    /* access modifiers changed from: package-private */
    public int e(View view) {
        return android.support.v4.g.e.a(((d) view.getLayoutParams()).f726a, t.d(this));
    }

    /* access modifiers changed from: package-private */
    public boolean a(View view, int i2) {
        return (e(view) & i2) == i2;
    }

    /* access modifiers changed from: package-private */
    public View a() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((((d) childAt.getLayoutParams()).f729d & 1) == 1) {
                return childAt;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void c(View view, float f2) {
        float d2 = d(view);
        float width = (float) view.getWidth();
        int i2 = ((int) (width * f2)) - ((int) (d2 * width));
        if (!a(view, 3)) {
            i2 = -i2;
        }
        view.offsetLeftAndRight(i2);
        b(view, f2);
    }

    /* access modifiers changed from: package-private */
    public View c(int i2) {
        int a2 = android.support.v4.g.e.a(i2, t.d(this)) & 7;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if ((e(childAt) & 7) == a2) {
                return childAt;
            }
        }
        return null;
    }

    static String d(int i2) {
        if ((i2 & 3) == 3) {
            return "LEFT";
        }
        return (i2 & 5) == 5 ? "RIGHT" : Integer.toHexString(i2);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.q = true;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.q = true;
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"WrongConstant"})
    public void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        if (!(mode == 1073741824 && mode2 == 1073741824)) {
            if (isInEditMode()) {
                if (mode != Integer.MIN_VALUE && mode == 0) {
                    size = 300;
                }
                if (mode2 != Integer.MIN_VALUE && mode2 == 0) {
                    size2 = 300;
                }
            } else {
                throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
            }
        }
        setMeasuredDimension(size, size2);
        int i4 = 0;
        boolean z2 = this.G != null && t.n(this);
        int d2 = t.d(this);
        int childCount = getChildCount();
        int i5 = 0;
        boolean z3 = false;
        boolean z4 = false;
        while (i5 < childCount) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                d dVar = (d) childAt.getLayoutParams();
                if (z2) {
                    int a2 = android.support.v4.g.e.a(dVar.f726a, d2);
                    if (t.n(childAt)) {
                        if (Build.VERSION.SDK_INT >= 21) {
                            WindowInsets windowInsets = (WindowInsets) this.G;
                            if (a2 == 3) {
                                windowInsets = windowInsets.replaceSystemWindowInsets(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), i4, windowInsets.getSystemWindowInsetBottom());
                            } else if (a2 == 5) {
                                windowInsets = windowInsets.replaceSystemWindowInsets(i4, windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
                            }
                            childAt.dispatchApplyWindowInsets(windowInsets);
                        }
                    } else if (Build.VERSION.SDK_INT >= 21) {
                        WindowInsets windowInsets2 = (WindowInsets) this.G;
                        if (a2 == 3) {
                            windowInsets2 = windowInsets2.replaceSystemWindowInsets(windowInsets2.getSystemWindowInsetLeft(), windowInsets2.getSystemWindowInsetTop(), i4, windowInsets2.getSystemWindowInsetBottom());
                        } else if (a2 == 5) {
                            windowInsets2 = windowInsets2.replaceSystemWindowInsets(i4, windowInsets2.getSystemWindowInsetTop(), windowInsets2.getSystemWindowInsetRight(), windowInsets2.getSystemWindowInsetBottom());
                        }
                        dVar.leftMargin = windowInsets2.getSystemWindowInsetLeft();
                        dVar.topMargin = windowInsets2.getSystemWindowInsetTop();
                        dVar.rightMargin = windowInsets2.getSystemWindowInsetRight();
                        dVar.bottomMargin = windowInsets2.getSystemWindowInsetBottom();
                    }
                }
                if (f(childAt)) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec((size - dVar.leftMargin) - dVar.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec((size2 - dVar.topMargin) - dVar.bottomMargin, 1073741824));
                } else if (g(childAt)) {
                    if (f722d) {
                        float j2 = t.j(childAt);
                        float f2 = this.f;
                        if (j2 != f2) {
                            t.a(childAt, f2);
                        }
                    }
                    int e2 = e(childAt) & 7;
                    boolean z5 = e2 == 3;
                    if ((!z5 || !z3) && (z5 || !z4)) {
                        if (z5) {
                            z3 = true;
                        } else {
                            z4 = true;
                        }
                        childAt.measure(getChildMeasureSpec(i2, this.g + dVar.leftMargin + dVar.rightMargin, dVar.width), getChildMeasureSpec(i3, dVar.topMargin + dVar.bottomMargin, dVar.height));
                        i5++;
                        i4 = 0;
                    } else {
                        throw new IllegalStateException("Child drawer has absolute gravity " + d(e2) + " but this " + "DrawerLayout" + " already has a " + "drawer view along that edge");
                    }
                } else {
                    throw new IllegalStateException("Child " + childAt + " at index " + i5 + " does not have a valid layout_gravity - must be Gravity.LEFT, " + "Gravity.RIGHT or Gravity.NO_GRAVITY");
                }
            }
            i5++;
            i4 = 0;
        }
    }

    private void e() {
        if (!f722d) {
            this.C = f();
            this.D = g();
        }
    }

    private Drawable f() {
        int d2 = t.d(this);
        if (d2 == 0) {
            Drawable drawable = this.I;
            if (drawable != null) {
                a(drawable, d2);
                return this.I;
            }
        } else {
            Drawable drawable2 = this.J;
            if (drawable2 != null) {
                a(drawable2, d2);
                return this.J;
            }
        }
        return this.K;
    }

    private Drawable g() {
        int d2 = t.d(this);
        if (d2 == 0) {
            Drawable drawable = this.J;
            if (drawable != null) {
                a(drawable, d2);
                return this.J;
            }
        } else {
            Drawable drawable2 = this.I;
            if (drawable2 != null) {
                a(drawable2, d2);
                return this.I;
            }
        }
        return this.L;
    }

    private boolean a(Drawable drawable, int i2) {
        if (drawable == null || !android.support.v4.graphics.drawable.a.b(drawable)) {
            return false;
        }
        android.support.v4.graphics.drawable.a.b(drawable, i2);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        float f2;
        int i6;
        this.p = true;
        int i7 = i4 - i2;
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                d dVar = (d) childAt.getLayoutParams();
                if (f(childAt)) {
                    childAt.layout(dVar.leftMargin, dVar.topMargin, dVar.leftMargin + childAt.getMeasuredWidth(), dVar.topMargin + childAt.getMeasuredHeight());
                } else {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (a(childAt, 3)) {
                        float f3 = (float) measuredWidth;
                        i6 = (-measuredWidth) + ((int) (dVar.f727b * f3));
                        f2 = ((float) (measuredWidth + i6)) / f3;
                    } else {
                        float f4 = (float) measuredWidth;
                        int i9 = i7 - ((int) (dVar.f727b * f4));
                        f2 = ((float) (i7 - i9)) / f4;
                        i6 = i9;
                    }
                    boolean z3 = f2 != dVar.f727b;
                    int i10 = dVar.f726a & 112;
                    if (i10 == 16) {
                        int i11 = i5 - i3;
                        int i12 = (i11 - measuredHeight) / 2;
                        if (i12 < dVar.topMargin) {
                            i12 = dVar.topMargin;
                        } else if (i12 + measuredHeight > i11 - dVar.bottomMargin) {
                            i12 = (i11 - dVar.bottomMargin) - measuredHeight;
                        }
                        childAt.layout(i6, i12, measuredWidth + i6, measuredHeight + i12);
                    } else if (i10 != 80) {
                        childAt.layout(i6, dVar.topMargin, measuredWidth + i6, dVar.topMargin + measuredHeight);
                    } else {
                        int i13 = i5 - i3;
                        childAt.layout(i6, (i13 - dVar.bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i6, i13 - dVar.bottomMargin);
                    }
                    if (z3) {
                        b(childAt, f2);
                    }
                    int i14 = dVar.f727b > BitmapDescriptorFactory.HUE_RED ? 0 : 4;
                    if (childAt.getVisibility() != i14) {
                        childAt.setVisibility(i14);
                    }
                }
            }
        }
        this.p = false;
        this.q = false;
    }

    public void requestLayout() {
        if (!this.p) {
            super.requestLayout();
        }
    }

    public void computeScroll() {
        int childCount = getChildCount();
        float f2 = BitmapDescriptorFactory.HUE_RED;
        for (int i2 = 0; i2 < childCount; i2++) {
            f2 = Math.max(f2, ((d) getChildAt(i2).getLayoutParams()).f727b);
        }
        this.i = f2;
        boolean a2 = this.k.a(true);
        boolean a3 = this.l.a(true);
        if (a2 || a3) {
            t.b(this);
        }
    }

    private static boolean m(View view) {
        Drawable background = view.getBackground();
        if (background == null || background.getOpacity() != -1) {
            return false;
        }
        return true;
    }

    public void setStatusBarBackground(Drawable drawable) {
        this.B = drawable;
        invalidate();
    }

    public Drawable getStatusBarBackgroundDrawable() {
        return this.B;
    }

    public void setStatusBarBackground(int i2) {
        this.B = i2 != 0 ? android.support.v4.content.c.a(getContext(), i2) : null;
        invalidate();
    }

    public void setStatusBarBackgroundColor(int i2) {
        this.B = new ColorDrawable(i2);
        invalidate();
    }

    public void onRtlPropertiesChanged(int i2) {
        e();
    }

    public void onDraw(Canvas canvas) {
        int i2;
        super.onDraw(canvas);
        if (this.H && this.B != null) {
            if (Build.VERSION.SDK_INT >= 21) {
                Object obj = this.G;
                i2 = obj != null ? ((WindowInsets) obj).getSystemWindowInsetTop() : 0;
            } else {
                i2 = 0;
            }
            if (i2 > 0) {
                this.B.setBounds(0, 0, getWidth(), i2);
                this.B.draw(canvas);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View view, long j2) {
        int i2;
        int height = getHeight();
        boolean f2 = f(view);
        int width = getWidth();
        int save = canvas.save();
        int i3 = 0;
        if (f2) {
            int childCount = getChildCount();
            i2 = width;
            int i4 = 0;
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = getChildAt(i5);
                if (childAt != view && childAt.getVisibility() == 0 && m(childAt) && g(childAt) && childAt.getHeight() >= height) {
                    if (a(childAt, 3)) {
                        int right = childAt.getRight();
                        if (right > i4) {
                            i4 = right;
                        }
                    } else {
                        int left = childAt.getLeft();
                        if (left < i2) {
                            i2 = left;
                        }
                    }
                }
            }
            canvas.clipRect(i4, 0, i2, getHeight());
            i3 = i4;
        } else {
            i2 = width;
        }
        boolean drawChild = super.drawChild(canvas, view, j2);
        canvas.restoreToCount(save);
        float f3 = this.i;
        if (f3 > BitmapDescriptorFactory.HUE_RED && f2) {
            int i6 = this.h;
            this.j.setColor((i6 & 16777215) | (((int) (((float) ((-16777216 & i6) >>> 24)) * f3)) << 24));
            canvas.drawRect((float) i3, BitmapDescriptorFactory.HUE_RED, (float) i2, (float) getHeight(), this.j);
        } else if (this.C != null && a(view, 3)) {
            int intrinsicWidth = this.C.getIntrinsicWidth();
            int right2 = view.getRight();
            float max = Math.max((float) BitmapDescriptorFactory.HUE_RED, Math.min(((float) right2) / ((float) this.k.b()), 1.0f));
            this.C.setBounds(right2, view.getTop(), intrinsicWidth + right2, view.getBottom());
            this.C.setAlpha((int) (max * 255.0f));
            this.C.draw(canvas);
        } else if (this.D != null && a(view, 5)) {
            int intrinsicWidth2 = this.D.getIntrinsicWidth();
            int left2 = view.getLeft();
            float max2 = Math.max((float) BitmapDescriptorFactory.HUE_RED, Math.min(((float) (getWidth() - left2)) / ((float) this.l.b()), 1.0f));
            this.D.setBounds(left2 - intrinsicWidth2, view.getTop(), left2, view.getBottom());
            this.D.setAlpha((int) (max2 * 255.0f));
            this.D.draw(canvas);
        }
        return drawChild;
    }

    /* access modifiers changed from: package-private */
    public boolean f(View view) {
        return ((d) view.getLayoutParams()).f726a == 0;
    }

    /* access modifiers changed from: package-private */
    public boolean g(View view) {
        int a2 = android.support.v4.g.e.a(((d) view.getLayoutParams()).f726a, t.d(view));
        return ((a2 & 3) == 0 && (a2 & 5) == 0) ? false : true;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z2;
        View d2;
        int actionMasked = motionEvent.getActionMasked();
        boolean a2 = this.k.a(motionEvent) | this.l.a(motionEvent);
        switch (actionMasked) {
            case 0:
                float x2 = motionEvent.getX();
                float y2 = motionEvent.getY();
                this.z = x2;
                this.A = y2;
                z2 = this.i > BitmapDescriptorFactory.HUE_RED && (d2 = this.k.d((int) x2, (int) y2)) != null && f(d2);
                this.v = false;
                this.w = false;
                break;
            case 1:
            case 3:
                a(true);
                this.v = false;
                this.w = false;
                z2 = false;
                break;
            case 2:
                if (this.k.d(3)) {
                    this.m.a();
                    this.n.a();
                }
                z2 = false;
                break;
            default:
                z2 = false;
                break;
        }
        if (a2 || z2 || h() || this.w) {
            return true;
        }
        return false;
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) == 0 || motionEvent.getAction() == 10 || this.i <= BitmapDescriptorFactory.HUE_RED) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        int childCount = getChildCount();
        if (childCount == 0) {
            return false;
        }
        float x2 = motionEvent.getX();
        float y2 = motionEvent.getY();
        for (int i2 = childCount - 1; i2 >= 0; i2--) {
            View childAt = getChildAt(i2);
            if (a(x2, y2, childAt) && !f(childAt) && a(motionEvent, childAt)) {
                return true;
            }
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z2;
        View a2;
        this.k.b(motionEvent);
        this.l.b(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action != 3) {
            switch (action) {
                case 0:
                    float x2 = motionEvent.getX();
                    float y2 = motionEvent.getY();
                    this.z = x2;
                    this.A = y2;
                    this.v = false;
                    this.w = false;
                    break;
                case 1:
                    float x3 = motionEvent.getX();
                    float y3 = motionEvent.getY();
                    View d2 = this.k.d((int) x3, (int) y3);
                    if (d2 != null && f(d2)) {
                        float f2 = x3 - this.z;
                        float f3 = y3 - this.A;
                        int d3 = this.k.d();
                        if ((f2 * f2) + (f3 * f3) < ((float) (d3 * d3)) && (a2 = a()) != null) {
                            z2 = a(a2) == 2;
                            a(z2);
                            this.v = false;
                            break;
                        }
                    }
                    z2 = true;
                    a(z2);
                    this.v = false;
            }
        } else {
            a(true);
            this.v = false;
            this.w = false;
        }
        return true;
    }

    public void requestDisallowInterceptTouchEvent(boolean z2) {
        super.requestDisallowInterceptTouchEvent(z2);
        this.v = z2;
        if (z2) {
            a(true);
        }
    }

    public void b() {
        a(false);
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z2) {
        int childCount = getChildCount();
        boolean z3 = false;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            d dVar = (d) childAt.getLayoutParams();
            if (g(childAt) && (!z2 || dVar.f728c)) {
                int width = childAt.getWidth();
                if (a(childAt, 3)) {
                    z3 |= this.k.a(childAt, -width, childAt.getTop());
                } else {
                    z3 |= this.l.a(childAt, getWidth(), childAt.getTop());
                }
                dVar.f728c = false;
            }
        }
        this.m.a();
        this.n.a();
        if (z3) {
            invalidate();
        }
    }

    public void h(View view) {
        a(view, true);
    }

    public void a(View view, boolean z2) {
        if (g(view)) {
            d dVar = (d) view.getLayoutParams();
            if (this.q) {
                dVar.f727b = 1.0f;
                dVar.f729d = 1;
                c(view, true);
            } else if (z2) {
                dVar.f729d |= 2;
                if (a(view, 3)) {
                    this.k.a(view, 0, view.getTop());
                } else {
                    this.l.a(view, getWidth() - view.getWidth(), view.getTop());
                }
            } else {
                c(view, 1.0f);
                a(dVar.f726a, 0, view);
                view.setVisibility(0);
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
    }

    public void e(int i2) {
        a(i2, true);
    }

    public void a(int i2, boolean z2) {
        View c2 = c(i2);
        if (c2 != null) {
            a(c2, z2);
            return;
        }
        throw new IllegalArgumentException("No drawer view found with gravity " + d(i2));
    }

    public void i(View view) {
        b(view, true);
    }

    public void b(View view, boolean z2) {
        if (g(view)) {
            d dVar = (d) view.getLayoutParams();
            if (this.q) {
                dVar.f727b = BitmapDescriptorFactory.HUE_RED;
                dVar.f729d = 0;
            } else if (z2) {
                dVar.f729d |= 4;
                if (a(view, 3)) {
                    this.k.a(view, -view.getWidth(), view.getTop());
                } else {
                    this.l.a(view, getWidth(), view.getTop());
                }
            } else {
                c(view, BitmapDescriptorFactory.HUE_RED);
                a(dVar.f726a, 0, view);
                view.setVisibility(4);
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
    }

    public void f(int i2) {
        b(i2, true);
    }

    public void b(int i2, boolean z2) {
        View c2 = c(i2);
        if (c2 != null) {
            b(c2, z2);
            return;
        }
        throw new IllegalArgumentException("No drawer view found with gravity " + d(i2));
    }

    public boolean j(View view) {
        if (g(view)) {
            return (((d) view.getLayoutParams()).f729d & 1) == 1;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public boolean k(View view) {
        if (g(view)) {
            return ((d) view.getLayoutParams()).f727b > BitmapDescriptorFactory.HUE_RED;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    private boolean h() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (((d) getChildAt(i2).getLayoutParams()).f728c) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new d(-1, -1);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof d) {
            return new d((d) layoutParams);
        }
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new d((ViewGroup.MarginLayoutParams) layoutParams) : new d(layoutParams);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof d) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new d(getContext(), attributeSet);
    }

    @Override // android.view.View, android.view.ViewGroup
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        if (getDescendantFocusability() != 393216) {
            int childCount = getChildCount();
            boolean z2 = false;
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                if (!g(childAt)) {
                    this.M.add(childAt);
                } else if (j(childAt)) {
                    childAt.addFocusables(arrayList, i2, i3);
                    z2 = true;
                }
            }
            if (!z2) {
                int size = this.M.size();
                for (int i5 = 0; i5 < size; i5++) {
                    View view = this.M.get(i5);
                    if (view.getVisibility() == 0) {
                        view.addFocusables(arrayList, i2, i3);
                    }
                }
            }
            this.M.clear();
        }
    }

    private boolean i() {
        return c() != null;
    }

    /* access modifiers changed from: package-private */
    public View c() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (g(childAt) && k(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void d() {
        if (!this.w) {
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0);
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                getChildAt(i2).dispatchTouchEvent(obtain);
            }
            obtain.recycle();
            this.w = true;
        }
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 != 4 || !i()) {
            return super.onKeyDown(i2, keyEvent);
        }
        keyEvent.startTracking();
        return true;
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 != 4) {
            return super.onKeyUp(i2, keyEvent);
        }
        View c2 = c();
        if (c2 != null && a(c2) == 0) {
            b();
        }
        return c2 != null;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        View c2;
        if (!(parcelable instanceof e)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        e eVar = (e) parcelable;
        super.onRestoreInstanceState(eVar.a());
        if (!(eVar.f730b == 0 || (c2 = c(eVar.f730b)) == null)) {
            h(c2);
        }
        if (eVar.f731c != 3) {
            a(eVar.f731c, 3);
        }
        if (eVar.f732d != 3) {
            a(eVar.f732d, 5);
        }
        if (eVar.e != 3) {
            a(eVar.e, 8388611);
        }
        if (eVar.f != 3) {
            a(eVar.f, 8388613);
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        e eVar = new e(super.onSaveInstanceState());
        int childCount = getChildCount();
        int i2 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            d dVar = (d) getChildAt(i2).getLayoutParams();
            boolean z2 = true;
            boolean z3 = dVar.f729d == 1;
            if (dVar.f729d != 2) {
                z2 = false;
            }
            if (z3 || z2) {
                eVar.f730b = dVar.f726a;
            } else {
                i2++;
            }
        }
        eVar.f731c = this.r;
        eVar.f732d = this.s;
        eVar.e = this.t;
        eVar.f = this.u;
        return eVar;
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i2, layoutParams);
        if (a() != null || g(view)) {
            t.a(view, 4);
        } else {
            t.a(view, 1);
        }
        if (!f720b) {
            t.a(view, this.e);
        }
    }

    static boolean l(View view) {
        return (t.c(view) == 4 || t.c(view) == 2) ? false : true;
    }

    /* access modifiers changed from: protected */
    /* compiled from: DrawerLayout */
    public static class e extends android.support.v4.g.a {
        public static final Parcelable.Creator<e> CREATOR = new Parcelable.ClassLoaderCreator<e>() {
            /* class android.support.v4.widget.h.e.AnonymousClass1 */

            /* renamed from: a */
            public e createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new e(parcel, classLoader);
            }

            /* renamed from: a */
            public e createFromParcel(Parcel parcel) {
                return new e(parcel, null);
            }

            /* renamed from: a */
            public e[] newArray(int i) {
                return new e[i];
            }
        };

        /* renamed from: b  reason: collision with root package name */
        int f730b = 0;

        /* renamed from: c  reason: collision with root package name */
        int f731c;

        /* renamed from: d  reason: collision with root package name */
        int f732d;
        int e;
        int f;

        public e(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f730b = parcel.readInt();
            this.f731c = parcel.readInt();
            this.f732d = parcel.readInt();
            this.e = parcel.readInt();
            this.f = parcel.readInt();
        }

        public e(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.support.v4.g.a
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f730b);
            parcel.writeInt(this.f731c);
            parcel.writeInt(this.f732d);
            parcel.writeInt(this.e);
            parcel.writeInt(this.f);
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: DrawerLayout */
    public class f extends s.a {

        /* renamed from: b  reason: collision with root package name */
        private final int f734b;

        /* renamed from: c  reason: collision with root package name */
        private s f735c;

        /* renamed from: d  reason: collision with root package name */
        private final Runnable f736d = new Runnable() {
            /* class android.support.v4.widget.h.f.AnonymousClass1 */

            public void run() {
                f.this.b();
            }
        };

        @Override // android.support.v4.widget.s.a
        public boolean b(int i) {
            return false;
        }

        f(int i) {
            this.f734b = i;
        }

        public void a(s sVar) {
            this.f735c = sVar;
        }

        public void a() {
            h.this.removeCallbacks(this.f736d);
        }

        @Override // android.support.v4.widget.s.a
        public boolean a(View view, int i) {
            return h.this.g(view) && h.this.a(view, this.f734b) && h.this.a(view) == 0;
        }

        @Override // android.support.v4.widget.s.a
        public void a(int i) {
            h.this.a(this.f734b, i, this.f735c.c());
        }

        @Override // android.support.v4.widget.s.a
        public void a(View view, int i, int i2, int i3, int i4) {
            float f;
            int width = view.getWidth();
            if (h.this.a(view, 3)) {
                f = ((float) (i + width)) / ((float) width);
            } else {
                f = ((float) (h.this.getWidth() - i)) / ((float) width);
            }
            h.this.b(view, f);
            view.setVisibility(f == BitmapDescriptorFactory.HUE_RED ? 4 : 0);
            h.this.invalidate();
        }

        @Override // android.support.v4.widget.s.a
        public void b(View view, int i) {
            ((d) view.getLayoutParams()).f728c = false;
            c();
        }

        private void c() {
            int i = 3;
            if (this.f734b == 3) {
                i = 5;
            }
            View c2 = h.this.c(i);
            if (c2 != null) {
                h.this.i(c2);
            }
        }

        @Override // android.support.v4.widget.s.a
        public void a(View view, float f, float f2) {
            int i;
            float d2 = h.this.d(view);
            int width = view.getWidth();
            if (h.this.a(view, 3)) {
                int i2 = (f > BitmapDescriptorFactory.HUE_RED ? 1 : (f == BitmapDescriptorFactory.HUE_RED ? 0 : -1));
                i = (i2 > 0 || (i2 == 0 && d2 > 0.5f)) ? 0 : -width;
            } else {
                int width2 = h.this.getWidth();
                if (f < BitmapDescriptorFactory.HUE_RED || (f == BitmapDescriptorFactory.HUE_RED && d2 > 0.5f)) {
                    width2 -= width;
                }
                i = width2;
            }
            this.f735c.a(i, view.getTop());
            h.this.invalidate();
        }

        @Override // android.support.v4.widget.s.a
        public void a(int i, int i2) {
            h.this.postDelayed(this.f736d, 160);
        }

        /* access modifiers changed from: package-private */
        public void b() {
            View view;
            int i;
            int b2 = this.f735c.b();
            int i2 = 0;
            boolean z = this.f734b == 3;
            if (z) {
                view = h.this.c(3);
                if (view != null) {
                    i2 = -view.getWidth();
                }
                i = i2 + b2;
            } else {
                view = h.this.c(5);
                i = h.this.getWidth() - b2;
            }
            if (view == null) {
                return;
            }
            if (((z && view.getLeft() < i) || (!z && view.getLeft() > i)) && h.this.a(view) == 0) {
                this.f735c.a(view, i, view.getTop());
                ((d) view.getLayoutParams()).f728c = true;
                h.this.invalidate();
                c();
                h.this.d();
            }
        }

        @Override // android.support.v4.widget.s.a
        public void b(int i, int i2) {
            View view;
            if ((i & 1) == 1) {
                view = h.this.c(3);
            } else {
                view = h.this.c(5);
            }
            if (view != null && h.this.a(view) == 0) {
                this.f735c.a(view, i2);
            }
        }

        @Override // android.support.v4.widget.s.a
        public int a(View view) {
            if (h.this.g(view)) {
                return view.getWidth();
            }
            return 0;
        }

        @Override // android.support.v4.widget.s.a
        public int a(View view, int i, int i2) {
            if (h.this.a(view, 3)) {
                return Math.max(-view.getWidth(), Math.min(i, 0));
            }
            int width = h.this.getWidth();
            return Math.max(width - view.getWidth(), Math.min(i, width));
        }

        @Override // android.support.v4.widget.s.a
        public int b(View view, int i, int i2) {
            return view.getTop();
        }
    }

    /* compiled from: DrawerLayout */
    public static class d extends ViewGroup.MarginLayoutParams {

        /* renamed from: a  reason: collision with root package name */
        public int f726a = 0;

        /* renamed from: b  reason: collision with root package name */
        float f727b;

        /* renamed from: c  reason: collision with root package name */
        boolean f728c;

        /* renamed from: d  reason: collision with root package name */
        int f729d;

        public d(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, h.f719a);
            this.f726a = obtainStyledAttributes.getInt(0, 0);
            obtainStyledAttributes.recycle();
        }

        public d(int i, int i2) {
            super(i, i2);
        }

        public d(d dVar) {
            super((ViewGroup.MarginLayoutParams) dVar);
            this.f726a = dVar.f726a;
        }

        public d(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public d(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }
    }

    /* compiled from: DrawerLayout */
    class a extends android.support.v4.g.b {

        /* renamed from: b  reason: collision with root package name */
        private final Rect f725b = new Rect();

        a() {
        }

        @Override // android.support.v4.g.b
        public void a(View view, android.support.v4.g.a.a aVar) {
            if (h.f720b) {
                super.a(view, aVar);
            } else {
                android.support.v4.g.a.a a2 = android.support.v4.g.a.a.a(aVar);
                super.a(view, a2);
                aVar.a(view);
                ViewParent e = t.e(view);
                if (e instanceof View) {
                    aVar.c((View) e);
                }
                a(aVar, a2);
                a2.s();
                a(aVar, (ViewGroup) view);
            }
            aVar.b(h.class.getName());
            aVar.a(false);
            aVar.b(false);
            aVar.a(a.C0010a.f461a);
            aVar.a(a.C0010a.f462b);
        }

        @Override // android.support.v4.g.b
        public void d(View view, AccessibilityEvent accessibilityEvent) {
            super.d(view, accessibilityEvent);
            accessibilityEvent.setClassName(h.class.getName());
        }

        @Override // android.support.v4.g.b
        public boolean b(View view, AccessibilityEvent accessibilityEvent) {
            CharSequence b2;
            if (accessibilityEvent.getEventType() != 32) {
                return super.b(view, accessibilityEvent);
            }
            List<CharSequence> text = accessibilityEvent.getText();
            View c2 = h.this.c();
            if (c2 == null || (b2 = h.this.b(h.this.e(c2))) == null) {
                return true;
            }
            text.add(b2);
            return true;
        }

        @Override // android.support.v4.g.b
        public boolean a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (h.f720b || h.l(view)) {
                return super.a(viewGroup, view, accessibilityEvent);
            }
            return false;
        }

        private void a(android.support.v4.g.a.a aVar, ViewGroup viewGroup) {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (h.l(childAt)) {
                    aVar.b(childAt);
                }
            }
        }

        private void a(android.support.v4.g.a.a aVar, android.support.v4.g.a.a aVar2) {
            Rect rect = this.f725b;
            aVar2.a(rect);
            aVar.b(rect);
            aVar2.c(rect);
            aVar.d(rect);
            aVar.c(aVar2.g());
            aVar.a(aVar2.o());
            aVar.b(aVar2.p());
            aVar.d(aVar2.r());
            aVar.h(aVar2.l());
            aVar.f(aVar2.j());
            aVar.a(aVar2.e());
            aVar.b(aVar2.f());
            aVar.d(aVar2.h());
            aVar.e(aVar2.i());
            aVar.g(aVar2.k());
            aVar.a(aVar2.b());
        }
    }

    /* compiled from: DrawerLayout */
    static final class b extends android.support.v4.g.b {
        b() {
        }

        @Override // android.support.v4.g.b
        public void a(View view, android.support.v4.g.a.a aVar) {
            super.a(view, aVar);
            if (!h.l(view)) {
                aVar.c((View) null);
            }
        }
    }
}
