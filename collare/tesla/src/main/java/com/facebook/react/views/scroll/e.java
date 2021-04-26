package com.facebook.react.views.scroll;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.g.t;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.OverScroller;
import android.widget.ScrollView;
import com.facebook.i.a.a;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.events.f;
import com.facebook.react.uimanager.j;
import com.facebook.react.uimanager.q;
import com.facebook.react.uimanager.r;
import com.google.android.gms.common.api.Api;
import java.lang.reflect.Field;
import java.util.List;

/* compiled from: ReactScrollView */
public class e extends ScrollView implements View.OnLayoutChangeListener, ViewGroup.OnHierarchyChangeListener, q {

    /* renamed from: a  reason: collision with root package name */
    private static Field f3387a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f3388b = false;

    /* renamed from: c  reason: collision with root package name */
    private final b f3389c = new b();

    /* renamed from: d  reason: collision with root package name */
    private final OverScroller f3390d;
    private final j e = new j();
    private final Rect f = new Rect();
    private boolean g;
    private Rect h;
    private String i = "hidden";
    private boolean j;
    private boolean k = false;
    private Runnable l;
    private boolean m;
    private boolean n = true;
    private boolean o;
    private a p = null;
    private String q;
    private Drawable r;
    private int s = 0;
    private int t = 0;
    private float u = 0.985f;
    private List<Integer> v;
    private boolean w = true;
    private boolean x = true;
    private View y;
    private com.facebook.react.views.view.e z;

    public e(ReactContext reactContext, a aVar) {
        super(reactContext);
        this.p = aVar;
        this.z = new com.facebook.react.views.view.e(this);
        this.f3390d = getOverScrollerFromParent();
        setOnHierarchyChangeListener(this);
        setScrollBarStyle(33554432);
    }

    private OverScroller getOverScrollerFromParent() {
        if (!f3388b) {
            f3388b = true;
            try {
                f3387a = ScrollView.class.getDeclaredField("mScroller");
                f3387a.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                Log.w("ReactNative", "Failed to get mScroller field for ScrollView! This app will exhibit the bounce-back scrolling bug :(");
            }
        }
        Field field = f3387a;
        if (field == null) {
            return null;
        }
        try {
            Object obj = field.get(this);
            if (obj instanceof OverScroller) {
                return (OverScroller) obj;
            }
            Log.w("ReactNative", "Failed to cast mScroller field in ScrollView (probably due to OEM changes to AOSP)! This app will exhibit the bounce-back scrolling bug :(");
            return null;
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Failed to get mScroller from ScrollView!", e2);
        }
    }

    public void setSendMomentumEvents(boolean z2) {
        this.o = z2;
    }

    public void setScrollPerfTag(String str) {
        this.q = str;
    }

    public void setScrollEnabled(boolean z2) {
        this.n = z2;
    }

    public void setPagingEnabled(boolean z2) {
        this.k = z2;
    }

    public void setDecelerationRate(float f2) {
        this.u = f2;
        OverScroller overScroller = this.f3390d;
        if (overScroller != null) {
            overScroller.setFriction(1.0f - this.u);
        }
    }

    public void setSnapInterval(int i2) {
        this.t = i2;
    }

    public void setSnapOffsets(List<Integer> list) {
        this.v = list;
    }

    public void setSnapToStart(boolean z2) {
        this.w = z2;
    }

    public void setSnapToEnd(boolean z2) {
        this.x = z2;
    }

    public void a() {
        awakenScrollBars();
    }

    public void setOverflow(String str) {
        this.i = str;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        j.a(i2, i3);
        setMeasuredDimension(View.MeasureSpec.getSize(i2), View.MeasureSpec.getSize(i3));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        scrollTo(getScrollX(), getScrollY());
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (this.m) {
            c();
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.m) {
            c();
        }
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        this.g = true;
        if (this.f3389c.a(i2, i3)) {
            if (this.m) {
                c();
            }
            g.a(this, this.f3389c.a(), this.f3389c.b());
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.n) {
            return false;
        }
        try {
            if (super.onInterceptTouchEvent(motionEvent)) {
                f.a(this, motionEvent);
                g.a(this);
                this.j = true;
                b();
                return true;
            }
        } catch (IllegalArgumentException e2) {
            Log.w("ReactNative", "Error intercepting touch event.", e2);
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.n) {
            return false;
        }
        this.e.a(motionEvent);
        if ((motionEvent.getAction() & 255) == 1 && this.j) {
            float a2 = this.e.a();
            float b2 = this.e.b();
            g.b(this, a2, b2);
            this.j = false;
            a(Math.round(a2), Math.round(b2));
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setRemoveClippedSubviews(boolean z2) {
        if (z2 && this.h == null) {
            this.h = new Rect();
        }
        this.m = z2;
        c();
    }

    @Override // com.facebook.react.uimanager.q
    public boolean getRemoveClippedSubviews() {
        return this.m;
    }

    @Override // com.facebook.react.uimanager.q
    public void c() {
        if (this.m) {
            a.a(this.h);
            r.a(this, this.h);
            View childAt = getChildAt(0);
            if (childAt instanceof q) {
                ((q) childAt).c();
            }
        }
    }

    @Override // com.facebook.react.uimanager.q
    public void a(Rect rect) {
        rect.set((Rect) a.a(this.h));
    }

    public void fling(int i2) {
        int abs = (int) (((float) Math.abs(i2)) * Math.signum(this.f3389c.b()));
        if (this.k) {
            c(abs);
        } else if (this.f3390d != null) {
            this.f3390d.fling(getScrollX(), getScrollY(), 0, abs, 0, 0, 0, Api.BaseClientBuilder.API_PRIORITY_OTHER, 0, ((getHeight() - getPaddingBottom()) - getPaddingTop()) / 2);
            t.b(this);
        } else {
            super.fling(abs);
        }
        a(0, abs);
    }

    private void b() {
        if (e()) {
            a.a(this.p);
            a.a(this.q);
            this.p.a(this.q);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void d() {
        if (e()) {
            a.a(this.p);
            a.a(this.q);
            this.p.b(this.q);
        }
    }

    private boolean e() {
        String str;
        return (this.p == null || (str = this.q) == null || str.isEmpty()) ? false : true;
    }

    private int getMaxScrollY() {
        return Math.max(0, this.y.getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
    }

    public void draw(Canvas canvas) {
        char c2 = 0;
        if (this.s != 0) {
            View childAt = getChildAt(0);
            if (!(this.r == null || childAt == null || childAt.getBottom() >= getHeight())) {
                this.r.setBounds(0, childAt.getBottom(), getWidth(), getHeight());
                this.r.draw(canvas);
            }
        }
        getDrawingRect(this.f);
        String str = this.i;
        if (str.hashCode() != 466743410 || !str.equals("visible")) {
            c2 = 65535;
        }
        if (c2 != 0) {
            canvas.clipRect(this.f);
        }
        super.draw(canvas);
    }

    private void a(int i2, int i3) {
        if ((this.o || this.k || e()) && this.l == null) {
            if (this.o) {
                b();
                g.a((ViewGroup) this, i2, i3);
            }
            this.g = false;
            this.l = new Runnable() {
                /* class com.facebook.react.views.scroll.e.AnonymousClass1 */

                /* renamed from: b  reason: collision with root package name */
                private boolean f3392b = false;

                public void run() {
                    if (e.this.g) {
                        e.this.g = false;
                        t.a(e.this, this, 20);
                    } else if (!e.this.k || this.f3392b) {
                        if (e.this.o) {
                            g.b(e.this);
                        }
                        e.this.l = null;
                        e.this.d();
                    } else {
                        this.f3392b = true;
                        e.this.c((e) 0);
                        t.a(e.this, this, 20);
                    }
                }
            };
            t.a(this, this.l, 20);
        }
    }

    private int a(int i2) {
        OverScroller overScroller = new OverScroller(getContext());
        overScroller.setFriction(1.0f - this.u);
        overScroller.fling(getScrollX(), getScrollY(), 0, i2, 0, 0, 0, getMaxScrollY(), 0, ((getHeight() - getPaddingBottom()) - getPaddingTop()) / 2);
        return overScroller.getFinalY();
    }

    private void b(int i2) {
        double snapInterval = (double) getSnapInterval();
        double scrollY = (double) getScrollY();
        double d2 = scrollY / snapInterval;
        int floor = (int) Math.floor(d2);
        int ceil = (int) Math.ceil(d2);
        int round = (int) Math.round(d2);
        int round2 = (int) Math.round(((double) a(i2)) / snapInterval);
        if (i2 > 0 && ceil == floor) {
            ceil++;
        } else if (i2 < 0 && floor == ceil) {
            floor--;
        }
        if (i2 > 0 && round < ceil && round2 > floor) {
            round = ceil;
        } else if (i2 < 0 && round > floor && round2 < ceil) {
            round = floor;
        }
        double d3 = ((double) round) * snapInterval;
        if (d3 != scrollY) {
            this.g = true;
            smoothScrollTo(getScrollX(), (int) d3);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0120  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(int r20) {
        /*
        // Method dump skipped, instructions count: 296
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.scroll.e.c(int):void");
    }

    private int getSnapInterval() {
        int i2 = this.t;
        if (i2 != 0) {
            return i2;
        }
        return getHeight();
    }

    public void setEndFillColor(int i2) {
        if (i2 != this.s) {
            this.s = i2;
            this.r = new ColorDrawable(this.s);
        }
    }

    /* access modifiers changed from: protected */
    public void onOverScrolled(int i2, int i3, boolean z2, boolean z3) {
        int maxScrollY;
        OverScroller overScroller = this.f3390d;
        if (overScroller != null && !overScroller.isFinished() && this.f3390d.getCurrY() != this.f3390d.getFinalY() && i3 >= (maxScrollY = getMaxScrollY())) {
            this.f3390d.abortAnimation();
            i3 = maxScrollY;
        }
        super.onOverScrolled(i2, i3, z2, z3);
    }

    public void onChildViewAdded(View view, View view2) {
        this.y = view2;
        this.y.addOnLayoutChangeListener(this);
    }

    public void onChildViewRemoved(View view, View view2) {
        this.y.removeOnLayoutChangeListener(this);
        this.y = null;
    }

    public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        int maxScrollY;
        if (this.y != null && getScrollY() > (maxScrollY = getMaxScrollY())) {
            scrollTo(getScrollX(), maxScrollY);
        }
    }

    public void setBackgroundColor(int i2) {
        this.z.a(i2);
    }

    public void a(int i2, float f2) {
        this.z.a(i2, f2);
    }

    public void a(int i2, float f2, float f3) {
        this.z.a(i2, f2, f3);
    }

    public void setBorderRadius(float f2) {
        this.z.a(f2);
    }

    public void a(float f2, int i2) {
        this.z.a(f2, i2);
    }

    public void setBorderStyle(String str) {
        this.z.a(str);
    }
}
