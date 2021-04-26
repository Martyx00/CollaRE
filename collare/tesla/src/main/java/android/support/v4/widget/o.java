package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.content.c;
import android.support.v4.g.k;
import android.support.v4.g.m;
import android.support.v4.g.n;
import android.support.v4.g.p;
import android.support.v4.g.t;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.ListView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: SwipeRefreshLayout */
public class o extends ViewGroup implements k, n {
    private static final int[] E = {16842766};
    private static final String n = "o";
    private boolean A;
    private int B;
    private boolean C;
    private final DecelerateInterpolator D;
    private int F;
    private Animation G;
    private Animation H;
    private Animation I;
    private Animation J;
    private Animation K;
    private int L;
    private a M;
    private Animation.AnimationListener N;
    private final Animation O;
    private final Animation P;

    /* renamed from: a  reason: collision with root package name */
    b f742a;

    /* renamed from: b  reason: collision with root package name */
    boolean f743b;

    /* renamed from: c  reason: collision with root package name */
    int f744c;

    /* renamed from: d  reason: collision with root package name */
    boolean f745d;
    c e;
    protected int f;
    float g;
    protected int h;
    int i;
    int j;
    d k;
    boolean l;
    boolean m;
    private View o;
    private int p;
    private float q;
    private float r;
    private final p s;
    private final m t;
    private final int[] u;
    private final int[] v;
    private boolean w;
    private int x;
    private float y;
    private float z;

    /* compiled from: SwipeRefreshLayout */
    public interface a {
        boolean a(o oVar, View view);
    }

    /* compiled from: SwipeRefreshLayout */
    public interface b {
        void a();
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.e.clearAnimation();
        this.k.stop();
        this.e.setVisibility(8);
        setColorViewAlpha(255);
        if (this.f745d) {
            setAnimationProgress(BitmapDescriptorFactory.HUE_RED);
        } else {
            setTargetOffsetTopAndBottom(this.h - this.f744c);
        }
        this.f744c = this.e.getTop();
    }

    public void setEnabled(boolean z2) {
        super.setEnabled(z2);
        if (!z2) {
            a();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
    }

    private void setColorViewAlpha(int i2) {
        this.e.getBackground().setAlpha(i2);
        this.k.setAlpha(i2);
    }

    public void a(boolean z2, int i2, int i3) {
        this.f745d = z2;
        this.h = i2;
        this.i = i3;
        this.m = true;
        a();
        this.f743b = false;
    }

    public int getProgressViewStartOffset() {
        return this.h;
    }

    public int getProgressViewEndOffset() {
        return this.i;
    }

    public void setSlingshotDistance(int i2) {
        this.j = i2;
    }

    public void setSize(int i2) {
        if (i2 == 0 || i2 == 1) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            if (i2 == 0) {
                this.L = (int) (displayMetrics.density * 56.0f);
            } else {
                this.L = (int) (displayMetrics.density * 40.0f);
            }
            this.e.setImageDrawable(null);
            this.k.a(i2);
            this.e.setImageDrawable(this.k);
        }
    }

    public o(Context context) {
        this(context, null);
    }

    public o(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f743b = false;
        this.q = -1.0f;
        this.u = new int[2];
        this.v = new int[2];
        this.B = -1;
        this.F = -1;
        this.N = new Animation.AnimationListener() {
            /* class android.support.v4.widget.o.AnonymousClass1 */

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (o.this.f743b) {
                    o.this.k.setAlpha(255);
                    o.this.k.start();
                    if (o.this.l && o.this.f742a != null) {
                        o.this.f742a.a();
                    }
                    o oVar = o.this;
                    oVar.f744c = oVar.e.getTop();
                    return;
                }
                o.this.a();
            }
        };
        this.O = new Animation() {
            /* class android.support.v4.widget.o.AnonymousClass6 */

            public void applyTransformation(float f, Transformation transformation) {
                int i;
                if (!o.this.m) {
                    i = o.this.i - Math.abs(o.this.h);
                } else {
                    i = o.this.i;
                }
                o.this.setTargetOffsetTopAndBottom((o.this.f + ((int) (((float) (i - o.this.f)) * f))) - o.this.e.getTop());
                o.this.k.b(1.0f - f);
            }
        };
        this.P = new Animation() {
            /* class android.support.v4.widget.o.AnonymousClass7 */

            public void applyTransformation(float f, Transformation transformation) {
                o.this.a(f);
            }
        };
        this.p = ViewConfiguration.get(context).getScaledTouchSlop();
        this.x = getResources().getInteger(17694721);
        setWillNotDraw(false);
        this.D = new DecelerateInterpolator(2.0f);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.L = (int) (displayMetrics.density * 40.0f);
        c();
        setChildrenDrawingOrderEnabled(true);
        this.i = (int) (displayMetrics.density * 64.0f);
        this.q = (float) this.i;
        this.s = new p(this);
        this.t = new m(this);
        setNestedScrollingEnabled(true);
        int i2 = -this.L;
        this.f744c = i2;
        this.h = i2;
        a(1.0f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, E);
        setEnabled(obtainStyledAttributes.getBoolean(0, true));
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int i2, int i3) {
        int i4 = this.F;
        if (i4 < 0) {
            return i3;
        }
        if (i3 == i2 - 1) {
            return i4;
        }
        return i3 >= i4 ? i3 + 1 : i3;
    }

    private void c() {
        this.e = new c(getContext(), -328966);
        this.k = new d(getContext());
        this.k.a(1);
        this.e.setImageDrawable(this.k);
        this.e.setVisibility(8);
        addView(this.e);
    }

    public void setOnRefreshListener(b bVar) {
        this.f742a = bVar;
    }

    public void setRefreshing(boolean z2) {
        int i2;
        if (!z2 || this.f743b == z2) {
            a(z2, false);
            return;
        }
        this.f743b = z2;
        if (!this.m) {
            i2 = this.i + this.h;
        } else {
            i2 = this.i;
        }
        setTargetOffsetTopAndBottom(i2 - this.f744c);
        this.l = false;
        b(this.N);
    }

    private void b(Animation.AnimationListener animationListener) {
        this.e.setVisibility(0);
        this.k.setAlpha(255);
        this.G = new Animation() {
            /* class android.support.v4.widget.o.AnonymousClass2 */

            public void applyTransformation(float f, Transformation transformation) {
                o.this.setAnimationProgress(f);
            }
        };
        this.G.setDuration((long) this.x);
        if (animationListener != null) {
            this.e.a(animationListener);
        }
        this.e.clearAnimation();
        this.e.startAnimation(this.G);
    }

    /* access modifiers changed from: package-private */
    public void setAnimationProgress(float f2) {
        this.e.setScaleX(f2);
        this.e.setScaleY(f2);
    }

    private void a(boolean z2, boolean z3) {
        if (this.f743b != z2) {
            this.l = z3;
            f();
            this.f743b = z2;
            if (this.f743b) {
                a(this.f744c, this.N);
            } else {
                a(this.N);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Animation.AnimationListener animationListener) {
        this.H = new Animation() {
            /* class android.support.v4.widget.o.AnonymousClass3 */

            public void applyTransformation(float f, Transformation transformation) {
                o.this.setAnimationProgress(1.0f - f);
            }
        };
        this.H.setDuration(150);
        this.e.a(animationListener);
        this.e.clearAnimation();
        this.e.startAnimation(this.H);
    }

    private void d() {
        this.I = a(this.k.getAlpha(), 76);
    }

    private void e() {
        this.J = a(this.k.getAlpha(), 255);
    }

    private Animation a(final int i2, final int i3) {
        AnonymousClass4 r0 = new Animation() {
            /* class android.support.v4.widget.o.AnonymousClass4 */

            public void applyTransformation(float f, Transformation transformation) {
                d dVar = o.this.k;
                int i = i2;
                dVar.setAlpha((int) (((float) i) + (((float) (i3 - i)) * f)));
            }
        };
        r0.setDuration(300);
        this.e.a(null);
        this.e.clearAnimation();
        this.e.startAnimation(r0);
        return r0;
    }

    @Deprecated
    public void setProgressBackgroundColor(int i2) {
        setProgressBackgroundColorSchemeResource(i2);
    }

    public void setProgressBackgroundColorSchemeResource(int i2) {
        setProgressBackgroundColorSchemeColor(c.c(getContext(), i2));
    }

    public void setProgressBackgroundColorSchemeColor(int i2) {
        this.e.setBackgroundColor(i2);
    }

    @Deprecated
    public void setColorScheme(int... iArr) {
        setColorSchemeResources(iArr);
    }

    public void setColorSchemeResources(int... iArr) {
        Context context = getContext();
        int[] iArr2 = new int[iArr.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr2[i2] = c.c(context, iArr[i2]);
        }
        setColorSchemeColors(iArr2);
    }

    public void setColorSchemeColors(int... iArr) {
        f();
        this.k.a(iArr);
    }

    private void f() {
        if (this.o == null) {
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                View childAt = getChildAt(i2);
                if (!childAt.equals(this.e)) {
                    this.o = childAt;
                    return;
                }
            }
        }
    }

    public void setDistanceToTriggerSync(int i2) {
        this.q = (float) i2;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() != 0) {
            if (this.o == null) {
                f();
            }
            View view = this.o;
            if (view != null) {
                int paddingLeft = getPaddingLeft();
                int paddingTop = getPaddingTop();
                view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
                int measuredWidth2 = this.e.getMeasuredWidth();
                int measuredHeight2 = this.e.getMeasuredHeight();
                int i6 = measuredWidth / 2;
                int i7 = measuredWidth2 / 2;
                int i8 = this.f744c;
                this.e.layout(i6 - i7, i8, i6 + i7, measuredHeight2 + i8);
            }
        }
    }

    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.o == null) {
            f();
        }
        View view = this.o;
        if (view != null) {
            view.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
            this.e.measure(View.MeasureSpec.makeMeasureSpec(this.L, 1073741824), View.MeasureSpec.makeMeasureSpec(this.L, 1073741824));
            this.F = -1;
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                if (getChildAt(i4) == this.e) {
                    this.F = i4;
                    return;
                }
            }
        }
    }

    public int getProgressCircleDiameter() {
        return this.L;
    }

    public boolean b() {
        a aVar = this.M;
        if (aVar != null) {
            return aVar.a(this, this.o);
        }
        View view = this.o;
        if (view instanceof ListView) {
            return l.b((ListView) view, -1);
        }
        return view.canScrollVertically(-1);
    }

    public void setOnChildScrollUpCallback(a aVar) {
        this.M = aVar;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        f();
        int actionMasked = motionEvent.getActionMasked();
        if (this.C && actionMasked == 0) {
            this.C = false;
        }
        if (!isEnabled() || this.C || b() || this.f743b || this.w) {
            return false;
        }
        if (actionMasked != 6) {
            switch (actionMasked) {
                case 0:
                    setTargetOffsetTopAndBottom(this.h - this.e.getTop());
                    this.B = motionEvent.getPointerId(0);
                    this.A = false;
                    int findPointerIndex = motionEvent.findPointerIndex(this.B);
                    if (findPointerIndex >= 0) {
                        this.z = motionEvent.getY(findPointerIndex);
                        break;
                    } else {
                        return false;
                    }
                case 1:
                case 3:
                    this.A = false;
                    this.B = -1;
                    break;
                case 2:
                    int i2 = this.B;
                    if (i2 != -1) {
                        int findPointerIndex2 = motionEvent.findPointerIndex(i2);
                        if (findPointerIndex2 >= 0) {
                            d(motionEvent.getY(findPointerIndex2));
                            break;
                        } else {
                            return false;
                        }
                    } else {
                        Log.e(n, "Got ACTION_MOVE event but don't have an active pointer id.");
                        return false;
                    }
            }
        } else {
            a(motionEvent);
        }
        return this.A;
    }

    public void requestDisallowInterceptTouchEvent(boolean z2) {
        if (Build.VERSION.SDK_INT >= 21 || !(this.o instanceof AbsListView)) {
            View view = this.o;
            if (view == null || t.r(view)) {
                super.requestDisallowInterceptTouchEvent(z2);
            }
        }
    }

    @Override // android.support.v4.g.n
    public boolean onStartNestedScroll(View view, View view2, int i2) {
        return isEnabled() && !this.C && !this.f743b && (i2 & 2) != 0;
    }

    @Override // android.support.v4.g.n
    public void onNestedScrollAccepted(View view, View view2, int i2) {
        this.s.a(view, view2, i2);
        startNestedScroll(i2 & 2);
        this.r = BitmapDescriptorFactory.HUE_RED;
        this.w = true;
    }

    @Override // android.support.v4.g.n
    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
        if (i3 > 0) {
            float f2 = this.r;
            if (f2 > BitmapDescriptorFactory.HUE_RED) {
                float f3 = (float) i3;
                if (f3 > f2) {
                    iArr[1] = i3 - ((int) f2);
                    this.r = BitmapDescriptorFactory.HUE_RED;
                } else {
                    this.r = f2 - f3;
                    iArr[1] = i3;
                }
                b(this.r);
            }
        }
        if (this.m && i3 > 0 && this.r == BitmapDescriptorFactory.HUE_RED && Math.abs(i3 - iArr[1]) > 0) {
            this.e.setVisibility(8);
        }
        int[] iArr2 = this.u;
        if (dispatchNestedPreScroll(i2 - iArr[0], i3 - iArr[1], iArr2, null)) {
            iArr[0] = iArr[0] + iArr2[0];
            iArr[1] = iArr[1] + iArr2[1];
        }
    }

    public int getNestedScrollAxes() {
        return this.s.a();
    }

    @Override // android.support.v4.g.n
    public void onStopNestedScroll(View view) {
        this.s.a(view);
        this.w = false;
        float f2 = this.r;
        if (f2 > BitmapDescriptorFactory.HUE_RED) {
            c(f2);
            this.r = BitmapDescriptorFactory.HUE_RED;
        }
        stopNestedScroll();
    }

    @Override // android.support.v4.g.n
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        dispatchNestedScroll(i2, i3, i4, i5, this.v);
        int i6 = i5 + this.v[1];
        if (i6 < 0 && !b()) {
            this.r += (float) Math.abs(i6);
            b(this.r);
        }
    }

    @Override // android.support.v4.g.k
    public void setNestedScrollingEnabled(boolean z2) {
        this.t.a(z2);
    }

    @Override // android.support.v4.g.k
    public boolean isNestedScrollingEnabled() {
        return this.t.a();
    }

    public boolean startNestedScroll(int i2) {
        return this.t.b(i2);
    }

    @Override // android.support.v4.g.k
    public void stopNestedScroll() {
        this.t.c();
    }

    public boolean hasNestedScrollingParent() {
        return this.t.b();
    }

    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return this.t.a(i2, i3, i4, i5, iArr);
    }

    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return this.t.a(i2, i3, iArr, iArr2);
    }

    @Override // android.support.v4.g.n
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return dispatchNestedPreFling(f2, f3);
    }

    @Override // android.support.v4.g.n
    public boolean onNestedFling(View view, float f2, float f3, boolean z2) {
        return dispatchNestedFling(f2, f3, z2);
    }

    public boolean dispatchNestedFling(float f2, float f3, boolean z2) {
        return this.t.a(f2, f3, z2);
    }

    public boolean dispatchNestedPreFling(float f2, float f3) {
        return this.t.a(f2, f3);
    }

    private boolean a(Animation animation) {
        return animation != null && animation.hasStarted() && !animation.hasEnded();
    }

    private void b(float f2) {
        this.k.a(true);
        float min = Math.min(1.0f, Math.abs(f2 / this.q));
        float max = (((float) Math.max(((double) min) - 0.4d, 0.0d)) * 5.0f) / 3.0f;
        float abs = Math.abs(f2) - this.q;
        int i2 = this.j;
        if (i2 <= 0) {
            i2 = this.m ? this.i - this.h : this.i;
        }
        float f3 = (float) i2;
        double max2 = (double) (Math.max((float) BitmapDescriptorFactory.HUE_RED, Math.min(abs, f3 * 2.0f) / f3) / 4.0f);
        float pow = ((float) (max2 - Math.pow(max2, 2.0d))) * 2.0f;
        int i3 = this.h + ((int) ((f3 * min) + (f3 * pow * 2.0f)));
        if (this.e.getVisibility() != 0) {
            this.e.setVisibility(0);
        }
        if (!this.f745d) {
            this.e.setScaleX(1.0f);
            this.e.setScaleY(1.0f);
        }
        if (this.f745d) {
            setAnimationProgress(Math.min(1.0f, f2 / this.q));
        }
        if (f2 < this.q) {
            if (this.k.getAlpha() > 76 && !a(this.I)) {
                d();
            }
        } else if (this.k.getAlpha() < 255 && !a(this.J)) {
            e();
        }
        this.k.a(BitmapDescriptorFactory.HUE_RED, Math.min(0.8f, max * 0.8f));
        this.k.b(Math.min(1.0f, max));
        this.k.c((((max * 0.4f) - 16.0f) + (pow * 2.0f)) * 0.5f);
        setTargetOffsetTopAndBottom(i3 - this.f744c);
    }

    private void c(float f2) {
        if (f2 > this.q) {
            a(true, true);
            return;
        }
        this.f743b = false;
        this.k.a(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
        AnonymousClass5 r0 = null;
        if (!this.f745d) {
            r0 = new Animation.AnimationListener() {
                /* class android.support.v4.widget.o.AnonymousClass5 */

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    if (!o.this.f745d) {
                        o.this.a((Animation.AnimationListener) null);
                    }
                }
            };
        }
        b(this.f744c, r0);
        this.k.a(false);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (this.C && actionMasked == 0) {
            this.C = false;
        }
        if (!isEnabled() || this.C || b() || this.f743b || this.w) {
            return false;
        }
        switch (actionMasked) {
            case 0:
                this.B = motionEvent.getPointerId(0);
                this.A = false;
                return true;
            case 1:
                int findPointerIndex = motionEvent.findPointerIndex(this.B);
                if (findPointerIndex < 0) {
                    Log.e(n, "Got ACTION_UP event but don't have an active pointer id.");
                    return false;
                }
                if (this.A) {
                    this.A = false;
                    c((motionEvent.getY(findPointerIndex) - this.y) * 0.5f);
                }
                this.B = -1;
                return false;
            case 2:
                int findPointerIndex2 = motionEvent.findPointerIndex(this.B);
                if (findPointerIndex2 < 0) {
                    Log.e(n, "Got ACTION_MOVE event but have an invalid active pointer id.");
                    return false;
                }
                float y2 = motionEvent.getY(findPointerIndex2);
                d(y2);
                if (!this.A) {
                    return true;
                }
                float f2 = (y2 - this.y) * 0.5f;
                if (f2 <= BitmapDescriptorFactory.HUE_RED) {
                    return false;
                }
                b(f2);
                return true;
            case 3:
                return false;
            case 4:
            default:
                return true;
            case 5:
                int actionIndex = motionEvent.getActionIndex();
                if (actionIndex < 0) {
                    Log.e(n, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                    return false;
                }
                this.B = motionEvent.getPointerId(actionIndex);
                return true;
            case 6:
                a(motionEvent);
                return true;
        }
    }

    private void d(float f2) {
        float f3 = this.z;
        int i2 = this.p;
        if (f2 - f3 > ((float) i2) && !this.A) {
            this.y = f3 + ((float) i2);
            this.A = true;
            this.k.setAlpha(76);
        }
    }

    private void a(int i2, Animation.AnimationListener animationListener) {
        this.f = i2;
        this.O.reset();
        this.O.setDuration(200);
        this.O.setInterpolator(this.D);
        if (animationListener != null) {
            this.e.a(animationListener);
        }
        this.e.clearAnimation();
        this.e.startAnimation(this.O);
    }

    private void b(int i2, Animation.AnimationListener animationListener) {
        if (this.f745d) {
            c(i2, animationListener);
            return;
        }
        this.f = i2;
        this.P.reset();
        this.P.setDuration(200);
        this.P.setInterpolator(this.D);
        if (animationListener != null) {
            this.e.a(animationListener);
        }
        this.e.clearAnimation();
        this.e.startAnimation(this.P);
    }

    /* access modifiers changed from: package-private */
    public void a(float f2) {
        int i2 = this.f;
        setTargetOffsetTopAndBottom((i2 + ((int) (((float) (this.h - i2)) * f2))) - this.e.getTop());
    }

    private void c(int i2, Animation.AnimationListener animationListener) {
        this.f = i2;
        this.g = this.e.getScaleX();
        this.K = new Animation() {
            /* class android.support.v4.widget.o.AnonymousClass8 */

            public void applyTransformation(float f, Transformation transformation) {
                o.this.setAnimationProgress(o.this.g + ((-o.this.g) * f));
                o.this.a(f);
            }
        };
        this.K.setDuration(150);
        if (animationListener != null) {
            this.e.a(animationListener);
        }
        this.e.clearAnimation();
        this.e.startAnimation(this.K);
    }

    /* access modifiers changed from: package-private */
    public void setTargetOffsetTopAndBottom(int i2) {
        this.e.bringToFront();
        t.d(this.e, i2);
        this.f744c = this.e.getTop();
    }

    private void a(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.B) {
            this.B = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
        }
    }
}
