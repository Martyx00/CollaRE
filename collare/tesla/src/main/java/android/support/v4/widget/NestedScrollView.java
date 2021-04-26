package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.g.l;
import android.support.v4.g.m;
import android.support.v4.g.o;
import android.support.v4.g.p;
import android.support.v4.g.t;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

public class NestedScrollView extends FrameLayout implements l, o {
    private static final a w = new a();
    private static final int[] x = {16843130};
    private float A;
    private b B;

    /* renamed from: a  reason: collision with root package name */
    private long f678a;

    /* renamed from: b  reason: collision with root package name */
    private final Rect f679b;

    /* renamed from: c  reason: collision with root package name */
    private OverScroller f680c;

    /* renamed from: d  reason: collision with root package name */
    private EdgeEffect f681d;
    private EdgeEffect e;
    private int f;
    private boolean g;
    private boolean h;
    private View i;
    private boolean j;
    private VelocityTracker k;
    private boolean l;
    private boolean m;
    private int n;
    private int o;
    private int p;
    private int q;
    private final int[] r;
    private final int[] s;
    private int t;
    private int u;
    private c v;
    private final p y;
    private final m z;

    public interface b {
        void a(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4);
    }

    private static int b(int i2, int i3, int i4) {
        if (i3 >= i4 || i2 < 0) {
            return 0;
        }
        return i3 + i2 > i4 ? i4 - i3 : i2;
    }

    @Override // android.support.v4.g.o
    public boolean a(View view, View view2, int i2, int i3) {
        return (i2 & 2) != 0;
    }

    public boolean shouldDelayChildPressedState() {
        return true;
    }

    public NestedScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f679b = new Rect();
        this.g = true;
        this.h = false;
        this.i = null;
        this.j = false;
        this.m = true;
        this.q = -1;
        this.r = new int[2];
        this.s = new int[2];
        a();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, x, i2, 0);
        setFillViewport(obtainStyledAttributes.getBoolean(0, false));
        obtainStyledAttributes.recycle();
        this.y = new p(this);
        this.z = new m(this);
        setNestedScrollingEnabled(true);
        t.a(this, w);
    }

    public boolean a(int i2, int i3) {
        return this.z.a(i2, i3);
    }

    public void a(int i2) {
        this.z.c(i2);
    }

    public boolean b(int i2) {
        return this.z.a(i2);
    }

    public boolean a(int i2, int i3, int i4, int i5, int[] iArr, int i6) {
        return this.z.a(i2, i3, i4, i5, iArr, i6);
    }

    public boolean a(int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        return this.z.a(i2, i3, iArr, iArr2, i4);
    }

    @Override // android.support.v4.g.k
    public void setNestedScrollingEnabled(boolean z2) {
        this.z.a(z2);
    }

    @Override // android.support.v4.g.k
    public boolean isNestedScrollingEnabled() {
        return this.z.a();
    }

    public boolean startNestedScroll(int i2) {
        return a(i2, 0);
    }

    @Override // android.support.v4.g.k
    public void stopNestedScroll() {
        a(0);
    }

    public boolean hasNestedScrollingParent() {
        return b(0);
    }

    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return a(i2, i3, i4, i5, iArr, 0);
    }

    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return a(i2, i3, iArr, iArr2, 0);
    }

    public boolean dispatchNestedFling(float f2, float f3, boolean z2) {
        return this.z.a(f2, f3, z2);
    }

    public boolean dispatchNestedPreFling(float f2, float f3) {
        return this.z.a(f2, f3);
    }

    @Override // android.support.v4.g.o
    public void b(View view, View view2, int i2, int i3) {
        this.y.a(view, view2, i2, i3);
        a(2, i3);
    }

    @Override // android.support.v4.g.o
    public void a(View view, int i2) {
        this.y.a(view, i2);
        a(i2);
    }

    @Override // android.support.v4.g.o
    public void a(View view, int i2, int i3, int i4, int i5, int i6) {
        int scrollY = getScrollY();
        scrollBy(0, i5);
        int scrollY2 = getScrollY() - scrollY;
        a(0, scrollY2, 0, i5 - scrollY2, (int[]) null, i6);
    }

    @Override // android.support.v4.g.o
    public void a(View view, int i2, int i3, int[] iArr, int i4) {
        a(i2, i3, iArr, (int[]) null, i4);
    }

    @Override // android.support.v4.g.n
    public boolean onStartNestedScroll(View view, View view2, int i2) {
        return a(view, view2, i2, 0);
    }

    @Override // android.support.v4.g.n
    public void onNestedScrollAccepted(View view, View view2, int i2) {
        b(view, view2, i2, 0);
    }

    @Override // android.support.v4.g.n
    public void onStopNestedScroll(View view) {
        a(view, 0);
    }

    @Override // android.support.v4.g.n
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        a(view, i2, i3, i4, i5, 0);
    }

    @Override // android.support.v4.g.n
    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
        a(view, i2, i3, iArr, 0);
    }

    @Override // android.support.v4.g.n
    public boolean onNestedFling(View view, float f2, float f3, boolean z2) {
        if (z2) {
            return false;
        }
        h((int) f3);
        return true;
    }

    @Override // android.support.v4.g.n
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return dispatchNestedPreFling(f2, f3);
    }

    public int getNestedScrollAxes() {
        return this.y.a();
    }

    /* access modifiers changed from: protected */
    public float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int scrollY = getScrollY();
        if (scrollY < verticalFadingEdgeLength) {
            return ((float) scrollY) / ((float) verticalFadingEdgeLength);
        }
        return 1.0f;
    }

    /* access modifiers changed from: protected */
    public float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        View childAt = getChildAt(0);
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int bottom = ((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - getScrollY()) - (getHeight() - getPaddingBottom());
        if (bottom < verticalFadingEdgeLength) {
            return ((float) bottom) / ((float) verticalFadingEdgeLength);
        }
        return 1.0f;
    }

    public int getMaxScrollAmount() {
        return (int) (((float) getHeight()) * 0.5f);
    }

    private void a() {
        this.f680c = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(PKIFailureInfo.transactionIdInUse);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.n = viewConfiguration.getScaledTouchSlop();
        this.o = viewConfiguration.getScaledMinimumFlingVelocity();
        this.p = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    public void addView(View view) {
        if (getChildCount() <= 0) {
            super.addView(view);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2) {
        if (getChildCount() <= 0) {
            super.addView(view, i2);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, i2, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public void setOnScrollChangeListener(b bVar) {
        this.B = bVar;
    }

    private boolean b() {
        if (getChildCount() <= 0) {
            return false;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        if (childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin > (getHeight() - getPaddingTop()) - getPaddingBottom()) {
            return true;
        }
        return false;
    }

    public void setFillViewport(boolean z2) {
        if (z2 != this.l) {
            this.l = z2;
            requestLayout();
        }
    }

    public void setSmoothScrollingEnabled(boolean z2) {
        this.m = z2;
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        b bVar = this.B;
        if (bVar != null) {
            bVar.a(this, i2, i3, i4, i5);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.l && View.MeasureSpec.getMode(i3) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredHeight2 = (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - layoutParams.topMargin) - layoutParams.bottomMargin;
            if (measuredHeight < measuredHeight2) {
                childAt.measure(getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), View.MeasureSpec.makeMeasureSpec(measuredHeight2, 1073741824));
            }
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || a(keyEvent);
    }

    public boolean a(KeyEvent keyEvent) {
        this.f679b.setEmpty();
        int i2 = 130;
        if (!b()) {
            if (!isFocused() || keyEvent.getKeyCode() == 4) {
                return false;
            }
            View findFocus = findFocus();
            if (findFocus == this) {
                findFocus = null;
            }
            View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, 130);
            if (findNextFocus == null || findNextFocus == this || !findNextFocus.requestFocus(130)) {
                return false;
            }
            return true;
        } else if (keyEvent.getAction() != 0) {
            return false;
        } else {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 62) {
                switch (keyCode) {
                    case 19:
                        if (!keyEvent.isAltPressed()) {
                            return e(33);
                        }
                        return d(33);
                    case 20:
                        if (!keyEvent.isAltPressed()) {
                            return e(130);
                        }
                        return d(130);
                    default:
                        return false;
                }
            } else {
                if (keyEvent.isShiftPressed()) {
                    i2 = 33;
                }
                c(i2);
                return false;
            }
        }
    }

    private boolean d(int i2, int i3) {
        if (getChildCount() <= 0) {
            return false;
        }
        int scrollY = getScrollY();
        View childAt = getChildAt(0);
        if (i3 < childAt.getTop() - scrollY || i3 >= childAt.getBottom() - scrollY || i2 < childAt.getLeft() || i2 >= childAt.getRight()) {
            return false;
        }
        return true;
    }

    private void c() {
        VelocityTracker velocityTracker = this.k;
        if (velocityTracker == null) {
            this.k = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
    }

    private void d() {
        if (this.k == null) {
            this.k = VelocityTracker.obtain();
        }
    }

    private void e() {
        VelocityTracker velocityTracker = this.k;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.k = null;
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean z2) {
        if (z2) {
            e();
        }
        super.requestDisallowInterceptTouchEvent(z2);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 2 && this.j) {
            return true;
        }
        int i2 = action & 255;
        if (i2 != 6) {
            switch (i2) {
                case 0:
                    int y2 = (int) motionEvent.getY();
                    if (d((int) motionEvent.getX(), y2)) {
                        this.f = y2;
                        this.q = motionEvent.getPointerId(0);
                        c();
                        this.k.addMovement(motionEvent);
                        this.f680c.computeScrollOffset();
                        this.j = !this.f680c.isFinished();
                        a(2, 0);
                        break;
                    } else {
                        this.j = false;
                        e();
                        break;
                    }
                case 1:
                case 3:
                    this.j = false;
                    this.q = -1;
                    e();
                    if (this.f680c.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                        t.b(this);
                    }
                    a(0);
                    break;
                case 2:
                    int i3 = this.q;
                    if (i3 != -1) {
                        int findPointerIndex = motionEvent.findPointerIndex(i3);
                        if (findPointerIndex != -1) {
                            int y3 = (int) motionEvent.getY(findPointerIndex);
                            if (Math.abs(y3 - this.f) > this.n && (2 & getNestedScrollAxes()) == 0) {
                                this.j = true;
                                this.f = y3;
                                d();
                                this.k.addMovement(motionEvent);
                                this.t = 0;
                                ViewParent parent = getParent();
                                if (parent != null) {
                                    parent.requestDisallowInterceptTouchEvent(true);
                                    break;
                                }
                            }
                        } else {
                            Log.e("NestedScrollView", "Invalid pointerId=" + i3 + " in onInterceptTouchEvent");
                            break;
                        }
                    }
                    break;
            }
        } else {
            a(motionEvent);
        }
        return this.j;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent;
        int i2;
        d();
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.t = 0;
        }
        obtain.offsetLocation(BitmapDescriptorFactory.HUE_RED, (float) this.t);
        switch (actionMasked) {
            case 0:
                if (getChildCount() != 0) {
                    boolean z2 = !this.f680c.isFinished();
                    this.j = z2;
                    if (z2 && (parent = getParent()) != null) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    }
                    if (!this.f680c.isFinished()) {
                        this.f680c.abortAnimation();
                    }
                    this.f = (int) motionEvent.getY();
                    this.q = motionEvent.getPointerId(0);
                    a(2, 0);
                    break;
                } else {
                    return false;
                }
            case 1:
                VelocityTracker velocityTracker = this.k;
                velocityTracker.computeCurrentVelocity(1000, (float) this.p);
                int yVelocity = (int) velocityTracker.getYVelocity(this.q);
                if (Math.abs(yVelocity) > this.o) {
                    h(-yVelocity);
                } else if (this.f680c.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    t.b(this);
                }
                this.q = -1;
                f();
                break;
            case 2:
                int findPointerIndex = motionEvent.findPointerIndex(this.q);
                if (findPointerIndex != -1) {
                    int y2 = (int) motionEvent.getY(findPointerIndex);
                    int i3 = this.f - y2;
                    if (a(0, i3, this.s, this.r, 0)) {
                        i3 -= this.s[1];
                        obtain.offsetLocation(BitmapDescriptorFactory.HUE_RED, (float) this.r[1]);
                        this.t += this.r[1];
                    }
                    if (this.j || Math.abs(i3) <= this.n) {
                        i2 = i3;
                    } else {
                        ViewParent parent2 = getParent();
                        if (parent2 != null) {
                            parent2.requestDisallowInterceptTouchEvent(true);
                        }
                        this.j = true;
                        i2 = i3 > 0 ? i3 - this.n : i3 + this.n;
                    }
                    if (this.j) {
                        this.f = y2 - this.r[1];
                        int scrollY = getScrollY();
                        int scrollRange = getScrollRange();
                        int overScrollMode = getOverScrollMode();
                        boolean z3 = overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0);
                        if (a(0, i2, 0, getScrollY(), 0, scrollRange, 0, 0, true) && !b(0)) {
                            this.k.clear();
                        }
                        int scrollY2 = getScrollY() - scrollY;
                        if (!a(0, scrollY2, 0, i2 - scrollY2, this.r, 0)) {
                            if (z3) {
                                g();
                                int i4 = scrollY + i2;
                                if (i4 < 0) {
                                    i.a(this.f681d, ((float) i2) / ((float) getHeight()), motionEvent.getX(findPointerIndex) / ((float) getWidth()));
                                    if (!this.e.isFinished()) {
                                        this.e.onRelease();
                                    }
                                } else if (i4 > scrollRange) {
                                    i.a(this.e, ((float) i2) / ((float) getHeight()), 1.0f - (motionEvent.getX(findPointerIndex) / ((float) getWidth())));
                                    if (!this.f681d.isFinished()) {
                                        this.f681d.onRelease();
                                    }
                                }
                                EdgeEffect edgeEffect = this.f681d;
                                if (edgeEffect != null && (!edgeEffect.isFinished() || !this.e.isFinished())) {
                                    t.b(this);
                                    break;
                                }
                            }
                        } else {
                            int i5 = this.f;
                            int[] iArr = this.r;
                            this.f = i5 - iArr[1];
                            obtain.offsetLocation(BitmapDescriptorFactory.HUE_RED, (float) iArr[1]);
                            this.t += this.r[1];
                            break;
                        }
                    }
                } else {
                    Log.e("NestedScrollView", "Invalid pointerId=" + this.q + " in onTouchEvent");
                    break;
                }
                break;
            case 3:
                if (this.j && getChildCount() > 0 && this.f680c.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    t.b(this);
                }
                this.q = -1;
                f();
                break;
            case 5:
                int actionIndex = motionEvent.getActionIndex();
                this.f = (int) motionEvent.getY(actionIndex);
                this.q = motionEvent.getPointerId(actionIndex);
                break;
            case 6:
                a(motionEvent);
                this.f = (int) motionEvent.getY(motionEvent.findPointerIndex(this.q));
                break;
        }
        VelocityTracker velocityTracker2 = this.k;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(obtain);
        }
        obtain.recycle();
        return true;
    }

    private void a(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.q) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.f = (int) motionEvent.getY(i2);
            this.q = motionEvent.getPointerId(i2);
            VelocityTracker velocityTracker = this.k;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8 && !this.j) {
            float axisValue = motionEvent.getAxisValue(9);
            if (axisValue != BitmapDescriptorFactory.HUE_RED) {
                int scrollRange = getScrollRange();
                int scrollY = getScrollY();
                int verticalScrollFactorCompat = scrollY - ((int) (axisValue * getVerticalScrollFactorCompat()));
                if (verticalScrollFactorCompat < 0) {
                    verticalScrollFactorCompat = 0;
                } else if (verticalScrollFactorCompat > scrollRange) {
                    verticalScrollFactorCompat = scrollRange;
                }
                if (verticalScrollFactorCompat != scrollY) {
                    super.scrollTo(getScrollX(), verticalScrollFactorCompat);
                    return true;
                }
            }
        }
        return false;
    }

    private float getVerticalScrollFactorCompat() {
        if (this.A == BitmapDescriptorFactory.HUE_RED) {
            TypedValue typedValue = new TypedValue();
            Context context = getContext();
            if (context.getTheme().resolveAttribute(16842829, typedValue, true)) {
                this.A = typedValue.getDimension(context.getResources().getDisplayMetrics());
            } else {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
        }
        return this.A;
    }

    /* access modifiers changed from: protected */
    public void onOverScrolled(int i2, int i3, boolean z2, boolean z3) {
        super.scrollTo(i2, i3);
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z2) {
        boolean z3;
        boolean z4;
        int overScrollMode = getOverScrollMode();
        boolean z5 = computeHorizontalScrollRange() > computeHorizontalScrollExtent();
        boolean z6 = computeVerticalScrollRange() > computeVerticalScrollExtent();
        boolean z7 = overScrollMode == 0 || (overScrollMode == 1 && z5);
        boolean z8 = overScrollMode == 0 || (overScrollMode == 1 && z6);
        int i10 = i4 + i2;
        int i11 = !z7 ? 0 : i8;
        int i12 = i5 + i3;
        int i13 = !z8 ? 0 : i9;
        int i14 = -i11;
        int i15 = i11 + i6;
        int i16 = -i13;
        int i17 = i13 + i7;
        if (i10 > i15) {
            i14 = i15;
            z3 = true;
        } else if (i10 < i14) {
            z3 = true;
        } else {
            i14 = i10;
            z3 = false;
        }
        if (i12 > i17) {
            i12 = i17;
            z4 = true;
        } else if (i12 < i16) {
            i12 = i16;
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 && !b(1)) {
            this.f680c.springBack(i14, i12, 0, 0, 0, getScrollRange());
        }
        onOverScrolled(i14, i12, z3, z4);
        if (z3 || z4) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public int getScrollRange() {
        if (getChildCount() <= 0) {
            return 0;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        return Math.max(0, ((childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
    }

    private View a(boolean z2, int i2, int i3) {
        ArrayList focusables = getFocusables(2);
        int size = focusables.size();
        View view = null;
        boolean z3 = false;
        for (int i4 = 0; i4 < size; i4++) {
            View view2 = (View) focusables.get(i4);
            int top = view2.getTop();
            int bottom = view2.getBottom();
            if (i2 < bottom && top < i3) {
                boolean z4 = i2 < top && bottom < i3;
                if (view == null) {
                    view = view2;
                    z3 = z4;
                } else {
                    boolean z5 = (z2 && top < view.getTop()) || (!z2 && bottom > view.getBottom());
                    if (z3) {
                        if (z4) {
                            if (!z5) {
                            }
                        }
                    } else if (z4) {
                        view = view2;
                        z3 = true;
                    } else if (!z5) {
                    }
                    view = view2;
                }
            }
        }
        return view;
    }

    public boolean c(int i2) {
        boolean z2 = i2 == 130;
        int height = getHeight();
        if (z2) {
            this.f679b.top = getScrollY() + height;
            int childCount = getChildCount();
            if (childCount > 0) {
                View childAt = getChildAt(childCount - 1);
                int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin + getPaddingBottom();
                if (this.f679b.top + height > bottom) {
                    this.f679b.top = bottom - height;
                }
            }
        } else {
            this.f679b.top = getScrollY() - height;
            if (this.f679b.top < 0) {
                this.f679b.top = 0;
            }
        }
        Rect rect = this.f679b;
        rect.bottom = rect.top + height;
        return a(i2, this.f679b.top, this.f679b.bottom);
    }

    public boolean d(int i2) {
        int childCount;
        boolean z2 = i2 == 130;
        int height = getHeight();
        Rect rect = this.f679b;
        rect.top = 0;
        rect.bottom = height;
        if (z2 && (childCount = getChildCount()) > 0) {
            View childAt = getChildAt(childCount - 1);
            this.f679b.bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin + getPaddingBottom();
            Rect rect2 = this.f679b;
            rect2.top = rect2.bottom - height;
        }
        return a(i2, this.f679b.top, this.f679b.bottom);
    }

    private boolean a(int i2, int i3, int i4) {
        int height = getHeight();
        int scrollY = getScrollY();
        int i5 = height + scrollY;
        boolean z2 = false;
        boolean z3 = i2 == 33;
        View a2 = a(z3, i3, i4);
        if (a2 == null) {
            a2 = this;
        }
        if (i3 < scrollY || i4 > i5) {
            g(z3 ? i3 - scrollY : i4 - i5);
            z2 = true;
        }
        if (a2 != findFocus()) {
            a2.requestFocus(i2);
        }
        return z2;
    }

    public boolean e(int i2) {
        View findFocus = findFocus();
        if (findFocus == this) {
            findFocus = null;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, i2);
        int maxScrollAmount = getMaxScrollAmount();
        if (findNextFocus == null || !a(findNextFocus, maxScrollAmount, getHeight())) {
            if (i2 == 33 && getScrollY() < maxScrollAmount) {
                maxScrollAmount = getScrollY();
            } else if (i2 == 130 && getChildCount() > 0) {
                View childAt = getChildAt(0);
                maxScrollAmount = Math.min((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - ((getScrollY() + getHeight()) - getPaddingBottom()), maxScrollAmount);
            }
            if (maxScrollAmount == 0) {
                return false;
            }
            if (i2 != 130) {
                maxScrollAmount = -maxScrollAmount;
            }
            g(maxScrollAmount);
        } else {
            findNextFocus.getDrawingRect(this.f679b);
            offsetDescendantRectToMyCoords(findNextFocus, this.f679b);
            g(a(this.f679b));
            findNextFocus.requestFocus(i2);
        }
        if (findFocus == null || !findFocus.isFocused() || !a(findFocus)) {
            return true;
        }
        int descendantFocusability = getDescendantFocusability();
        setDescendantFocusability(PKIFailureInfo.unsupportedVersion);
        requestFocus();
        setDescendantFocusability(descendantFocusability);
        return true;
    }

    private boolean a(View view) {
        return !a(view, 0, getHeight());
    }

    private boolean a(View view, int i2, int i3) {
        view.getDrawingRect(this.f679b);
        offsetDescendantRectToMyCoords(view, this.f679b);
        return this.f679b.bottom + i2 >= getScrollY() && this.f679b.top - i2 <= getScrollY() + i3;
    }

    private void g(int i2) {
        if (i2 == 0) {
            return;
        }
        if (this.m) {
            b(0, i2);
        } else {
            scrollBy(0, i2);
        }
    }

    public final void b(int i2, int i3) {
        if (getChildCount() != 0) {
            if (AnimationUtils.currentAnimationTimeMillis() - this.f678a > 250) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                int height = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int scrollY = getScrollY();
                this.u = getScrollY();
                OverScroller overScroller = this.f680c;
                int scrollX = getScrollX();
                overScroller.startScroll(scrollX, scrollY, 0, Math.max(0, Math.min(i3 + scrollY, Math.max(0, height - height2))) - scrollY);
                t.b(this);
            } else {
                if (!this.f680c.isFinished()) {
                    this.f680c.abortAnimation();
                }
                scrollBy(i2, i3);
            }
            this.f678a = AnimationUtils.currentAnimationTimeMillis();
        }
    }

    public final void c(int i2, int i3) {
        b(i2 - getScrollX(), i3 - getScrollY());
    }

    public int computeVerticalScrollRange() {
        int childCount = getChildCount();
        int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if (childCount == 0) {
            return height;
        }
        View childAt = getChildAt(0);
        int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
        int scrollY = getScrollY();
        int max = Math.max(0, bottom - height);
        if (scrollY < 0) {
            return bottom - scrollY;
        }
        return scrollY > max ? bottom + (scrollY - max) : bottom;
    }

    public int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    public int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    public int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    public int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    public int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    /* access modifiers changed from: protected */
    public void measureChild(View view, int i2, int i3) {
        view.measure(getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight(), view.getLayoutParams().width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    /* access modifiers changed from: protected */
    public void measureChildWithMargins(View view, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i3, marginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 0));
    }

    public void computeScroll() {
        int i2;
        if (this.f680c.computeScrollOffset()) {
            this.f680c.getCurrX();
            int currY = this.f680c.getCurrY();
            int i3 = currY - this.u;
            int i4 = a(0, i3, this.s, null, 1) ? i3 - this.s[1] : i3;
            if (i4 != 0) {
                int scrollRange = getScrollRange();
                int scrollY = getScrollY();
                a(0, i4, getScrollX(), scrollY, 0, scrollRange, 0, 0, false);
                int scrollY2 = getScrollY() - scrollY;
                if (!a(0, scrollY2, 0, i4 - scrollY2, (int[]) null, 1)) {
                    int overScrollMode = getOverScrollMode();
                    if (overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0)) {
                        g();
                        if (currY <= 0 && scrollY > 0) {
                            this.f681d.onAbsorb((int) this.f680c.getCurrVelocity());
                        } else if (currY >= scrollRange && scrollY < scrollRange) {
                            this.e.onAbsorb((int) this.f680c.getCurrVelocity());
                        }
                    }
                }
            }
            this.u = currY;
            t.b(this);
            return;
        }
        if (b(1)) {
            a(1);
            i2 = 0;
        } else {
            i2 = 0;
        }
        this.u = i2;
    }

    private void b(View view) {
        view.getDrawingRect(this.f679b);
        offsetDescendantRectToMyCoords(view, this.f679b);
        int a2 = a(this.f679b);
        if (a2 != 0) {
            scrollBy(0, a2);
        }
    }

    private boolean a(Rect rect, boolean z2) {
        int a2 = a(rect);
        boolean z3 = a2 != 0;
        if (z3) {
            if (z2) {
                scrollBy(0, a2);
            } else {
                b(0, a2);
            }
        }
        return z3;
    }

    /* access modifiers changed from: protected */
    public int a(Rect rect) {
        int i2;
        int i3;
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int scrollY = getScrollY();
        int i4 = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            scrollY += verticalFadingEdgeLength;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int i5 = rect.bottom < (childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin ? i4 - verticalFadingEdgeLength : i4;
        if (rect.bottom > i5 && rect.top > scrollY) {
            if (rect.height() > height) {
                i3 = (rect.top - scrollY) + 0;
            } else {
                i3 = (rect.bottom - i5) + 0;
            }
            return Math.min(i3, (childAt.getBottom() + layoutParams.bottomMargin) - i4);
        } else if (rect.top >= scrollY || rect.bottom >= i5) {
            return 0;
        } else {
            if (rect.height() > height) {
                i2 = 0 - (i5 - rect.bottom);
            } else {
                i2 = 0 - (scrollY - rect.top);
            }
            return Math.max(i2, -getScrollY());
        }
    }

    public void requestChildFocus(View view, View view2) {
        if (!this.g) {
            b(view2);
        } else {
            this.i = view2;
        }
        super.requestChildFocus(view, view2);
    }

    /* access modifiers changed from: protected */
    public boolean onRequestFocusInDescendants(int i2, Rect rect) {
        View view;
        if (i2 == 2) {
            i2 = 130;
        } else if (i2 == 1) {
            i2 = 33;
        }
        if (rect == null) {
            view = FocusFinder.getInstance().findNextFocus(this, null, i2);
        } else {
            view = FocusFinder.getInstance().findNextFocusFromRect(this, rect, i2);
        }
        if (view != null && !a(view)) {
            return view.requestFocus(i2, rect);
        }
        return false;
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z2) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        return a(rect, z2);
    }

    public void requestLayout() {
        this.g = true;
        super.requestLayout();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        int i6 = 0;
        this.g = false;
        View view = this.i;
        if (view != null && a(view, this)) {
            b(this.i);
        }
        this.i = null;
        if (!this.h) {
            if (this.v != null) {
                scrollTo(getScrollX(), this.v.f682a);
                this.v = null;
            }
            if (getChildCount() > 0) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                i6 = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            }
            int paddingTop = ((i5 - i3) - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            int b2 = b(scrollY, paddingTop, i6);
            if (b2 != scrollY) {
                scrollTo(getScrollX(), b2);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.h = true;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.h = false;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        View findFocus = findFocus();
        if (findFocus != null && this != findFocus && a(findFocus, 0, i5)) {
            findFocus.getDrawingRect(this.f679b);
            offsetDescendantRectToMyCoords(findFocus, this.f679b);
            g(a(this.f679b));
        }
    }

    private static boolean a(View view, View view2) {
        if (view == view2) {
            return true;
        }
        ViewParent parent = view.getParent();
        if (!(parent instanceof ViewGroup) || !a((View) parent, view2)) {
            return false;
        }
        return true;
    }

    public void f(int i2) {
        if (getChildCount() > 0) {
            a(2, 1);
            this.f680c.fling(getScrollX(), getScrollY(), 0, i2, 0, 0, PKIFailureInfo.systemUnavail, Api.BaseClientBuilder.API_PRIORITY_OTHER, 0, 0);
            this.u = getScrollY();
            t.b(this);
        }
    }

    private void h(int i2) {
        int scrollY = getScrollY();
        boolean z2 = (scrollY > 0 || i2 > 0) && (scrollY < getScrollRange() || i2 < 0);
        float f2 = (float) i2;
        if (!dispatchNestedPreFling(BitmapDescriptorFactory.HUE_RED, f2)) {
            dispatchNestedFling(BitmapDescriptorFactory.HUE_RED, f2, z2);
            f(i2);
        }
    }

    private void f() {
        this.j = false;
        e();
        a(0);
        EdgeEffect edgeEffect = this.f681d;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            this.e.onRelease();
        }
    }

    public void scrollTo(int i2, int i3) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int b2 = b(i2, (getWidth() - getPaddingLeft()) - getPaddingRight(), childAt.getWidth() + layoutParams.leftMargin + layoutParams.rightMargin);
            int b3 = b(i3, (getHeight() - getPaddingTop()) - getPaddingBottom(), childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin);
            if (b2 != getScrollX() || b3 != getScrollY()) {
                super.scrollTo(b2, b3);
            }
        }
    }

    private void g() {
        if (getOverScrollMode() == 2) {
            this.f681d = null;
            this.e = null;
        } else if (this.f681d == null) {
            Context context = getContext();
            this.f681d = new EdgeEffect(context);
            this.e = new EdgeEffect(context);
        }
    }

    public void draw(Canvas canvas) {
        int i2;
        super.draw(canvas);
        if (this.f681d != null) {
            int scrollY = getScrollY();
            int i3 = 0;
            if (!this.f681d.isFinished()) {
                int save = canvas.save();
                int width = getWidth();
                int height = getHeight();
                int min = Math.min(0, scrollY);
                if (Build.VERSION.SDK_INT < 21 || getClipToPadding()) {
                    width -= getPaddingLeft() + getPaddingRight();
                    i2 = getPaddingLeft() + 0;
                } else {
                    i2 = 0;
                }
                if (Build.VERSION.SDK_INT >= 21 && getClipToPadding()) {
                    height -= getPaddingTop() + getPaddingBottom();
                    min += getPaddingTop();
                }
                canvas.translate((float) i2, (float) min);
                this.f681d.setSize(width, height);
                if (this.f681d.draw(canvas)) {
                    t.b(this);
                }
                canvas.restoreToCount(save);
            }
            if (!this.e.isFinished()) {
                int save2 = canvas.save();
                int width2 = getWidth();
                int height2 = getHeight();
                int max = Math.max(getScrollRange(), scrollY) + height2;
                if (Build.VERSION.SDK_INT < 21 || getClipToPadding()) {
                    width2 -= getPaddingLeft() + getPaddingRight();
                    i3 = 0 + getPaddingLeft();
                }
                if (Build.VERSION.SDK_INT >= 21 && getClipToPadding()) {
                    height2 -= getPaddingTop() + getPaddingBottom();
                    max -= getPaddingBottom();
                }
                canvas.translate((float) (i3 - width2), (float) max);
                canvas.rotate(180.0f, (float) width2, BitmapDescriptorFactory.HUE_RED);
                this.e.setSize(width2, height2);
                if (this.e.draw(canvas)) {
                    t.b(this);
                }
                canvas.restoreToCount(save2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof c)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        c cVar = (c) parcelable;
        super.onRestoreInstanceState(cVar.getSuperState());
        this.v = cVar;
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        c cVar = new c(super.onSaveInstanceState());
        cVar.f682a = getScrollY();
        return cVar;
    }

    /* access modifiers changed from: package-private */
    public static class c extends View.BaseSavedState {
        public static final Parcelable.Creator<c> CREATOR = new Parcelable.Creator<c>() {
            /* class android.support.v4.widget.NestedScrollView.c.AnonymousClass1 */

            /* renamed from: a */
            public c createFromParcel(Parcel parcel) {
                return new c(parcel);
            }

            /* renamed from: a */
            public c[] newArray(int i) {
                return new c[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        public int f682a;

        c(Parcelable parcelable) {
            super(parcelable);
        }

        c(Parcel parcel) {
            super(parcel);
            this.f682a = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f682a);
        }

        public String toString() {
            return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.f682a + "}";
        }
    }

    static class a extends android.support.v4.g.b {
        a() {
        }

        @Override // android.support.v4.g.b
        public boolean a(View view, int i, Bundle bundle) {
            if (super.a(view, i, bundle)) {
                return true;
            }
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            if (!nestedScrollView.isEnabled()) {
                return false;
            }
            if (i == 4096) {
                int min = Math.min(nestedScrollView.getScrollY() + ((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), nestedScrollView.getScrollRange());
                if (min == nestedScrollView.getScrollY()) {
                    return false;
                }
                nestedScrollView.c(0, min);
                return true;
            } else if (i != 8192) {
                return false;
            } else {
                int max = Math.max(nestedScrollView.getScrollY() - ((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), 0);
                if (max == nestedScrollView.getScrollY()) {
                    return false;
                }
                nestedScrollView.c(0, max);
                return true;
            }
        }

        @Override // android.support.v4.g.b
        public void a(View view, android.support.v4.g.a.a aVar) {
            int scrollRange;
            super.a(view, aVar);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            aVar.b(ScrollView.class.getName());
            if (nestedScrollView.isEnabled() && (scrollRange = nestedScrollView.getScrollRange()) > 0) {
                aVar.i(true);
                if (nestedScrollView.getScrollY() > 0) {
                    aVar.a(PKIFailureInfo.certRevoked);
                }
                if (nestedScrollView.getScrollY() < scrollRange) {
                    aVar.a(4096);
                }
            }
        }

        @Override // android.support.v4.g.b
        public void d(View view, AccessibilityEvent accessibilityEvent) {
            super.d(view, accessibilityEvent);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            accessibilityEvent.setClassName(ScrollView.class.getName());
            accessibilityEvent.setScrollable(nestedScrollView.getScrollRange() > 0);
            accessibilityEvent.setScrollX(nestedScrollView.getScrollX());
            accessibilityEvent.setScrollY(nestedScrollView.getScrollY());
            android.support.v4.g.a.c.a(accessibilityEvent, nestedScrollView.getScrollX());
            android.support.v4.g.a.c.b(accessibilityEvent, nestedScrollView.getScrollRange());
        }
    }
}
