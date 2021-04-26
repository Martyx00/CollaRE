package com.facebook.react.views.scroll;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.g.t;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.OverScroller;
import com.facebook.i.a.a;
import com.facebook.react.uimanager.events.f;
import com.facebook.react.uimanager.j;
import com.facebook.react.uimanager.q;
import com.facebook.react.uimanager.r;
import com.facebook.react.views.view.e;
import com.google.android.gms.common.api.Api;
import java.lang.reflect.Field;
import java.util.List;

/* compiled from: ReactHorizontalScrollView */
public class d extends HorizontalScrollView implements q {

    /* renamed from: a  reason: collision with root package name */
    private static Field f3381a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f3382b = false;

    /* renamed from: c  reason: collision with root package name */
    private final b f3383c = new b();

    /* renamed from: d  reason: collision with root package name */
    private final OverScroller f3384d;
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
    private e y = new e(this);

    public d(Context context, a aVar) {
        super(context);
        this.p = aVar;
        this.f3384d = getOverScrollerFromParent();
    }

    private OverScroller getOverScrollerFromParent() {
        if (!f3382b) {
            f3382b = true;
            try {
                f3381a = HorizontalScrollView.class.getDeclaredField("mScroller");
                f3381a.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                Log.w("ReactNative", "Failed to get mScroller field for HorizontalScrollView! This app will exhibit the bounce-back scrolling bug :(");
            }
        }
        Field field = f3381a;
        if (field == null) {
            return null;
        }
        try {
            Object obj = field.get(this);
            if (obj instanceof OverScroller) {
                return (OverScroller) obj;
            }
            Log.w("ReactNative", "Failed to cast mScroller field in HorizontalScrollView (probably due to OEM changes to AOSP)! This app will exhibit the bounce-back scrolling bug :(");
            return null;
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Failed to get mScroller from HorizontalScrollView!", e2);
        }
    }

    public void setScrollPerfTag(String str) {
        this.q = str;
    }

    public void setRemoveClippedSubviews(boolean z) {
        if (z && this.h == null) {
            this.h = new Rect();
        }
        this.m = z;
        c();
    }

    @Override // com.facebook.react.uimanager.q
    public boolean getRemoveClippedSubviews() {
        return this.m;
    }

    public void setSendMomentumEvents(boolean z) {
        this.o = z;
    }

    public void setScrollEnabled(boolean z) {
        this.n = z;
    }

    public void setPagingEnabled(boolean z) {
        this.k = z;
    }

    public void setDecelerationRate(float f2) {
        this.u = f2;
        OverScroller overScroller = this.f3384d;
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

    public void setSnapToStart(boolean z) {
        this.w = z;
    }

    public void setSnapToEnd(boolean z) {
        this.x = z;
    }

    public void a() {
        awakenScrollBars();
    }

    public void setOverflow(String str) {
        this.i = str;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        getDrawingRect(this.f);
        String str = this.i;
        if (((str.hashCode() == 466743410 && str.equals("visible")) ? (char) 0 : 65535) != 0) {
            canvas.clipRect(this.f);
        }
        super.onDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        j.a(i2, i3);
        setMeasuredDimension(View.MeasureSpec.getSize(i2), View.MeasureSpec.getSize(i3));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        scrollTo(getScrollX(), getScrollY());
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        this.g = true;
        if (this.f3383c.a(i2, i3)) {
            if (this.m) {
                c();
            }
            g.a(this, this.f3383c.a(), this.f3383c.b());
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

    public void fling(int i2) {
        int abs = (int) (((float) Math.abs(i2)) * Math.signum(this.f3383c.a()));
        if (this.k) {
            c(abs);
        } else if (this.f3384d != null) {
            this.f3384d.fling(getScrollX(), getScrollY(), abs, 0, 0, Api.BaseClientBuilder.API_PRIORITY_OTHER, 0, 0, ((getWidth() - t.f(this)) - t.g(this)) / 2, 0);
            t.b(this);
        } else {
            super.fling(abs);
        }
        a(abs, 0);
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

    private int getSnapInterval() {
        int i2 = this.t;
        if (i2 != 0) {
            return i2;
        }
        return getWidth();
    }

    public void setEndFillColor(int i2) {
        if (i2 != this.s) {
            this.s = i2;
            this.r = new ColorDrawable(this.s);
        }
    }

    /* access modifiers changed from: protected */
    public void onOverScrolled(int i2, int i3, boolean z, boolean z2) {
        int computeHorizontalScrollRange;
        OverScroller overScroller = this.f3384d;
        if (overScroller != null && !overScroller.isFinished() && this.f3384d.getCurrX() != this.f3384d.getFinalX() && i2 >= (computeHorizontalScrollRange = computeHorizontalScrollRange() - getWidth())) {
            this.f3384d.abortAnimation();
            i2 = computeHorizontalScrollRange;
        }
        super.onOverScrolled(i2, i3, z, z2);
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

    public void draw(Canvas canvas) {
        if (this.s != 0) {
            View childAt = getChildAt(0);
            if (!(this.r == null || childAt == null || childAt.getRight() >= getWidth())) {
                this.r.setBounds(childAt.getRight(), 0, getWidth(), getHeight());
                this.r.draw(canvas);
            }
        }
        super.draw(canvas);
    }

    private void a(int i2, int i3) {
        if ((this.o || this.k || e()) && this.l == null) {
            if (this.o) {
                g.a((ViewGroup) this, i2, i3);
            }
            this.g = false;
            this.l = new Runnable() {
                /* class com.facebook.react.views.scroll.d.AnonymousClass1 */

                /* renamed from: b  reason: collision with root package name */
                private boolean f3386b = false;

                public void run() {
                    if (d.this.g) {
                        d.this.g = false;
                        t.a(d.this, this, 20);
                    } else if (!d.this.k || this.f3386b) {
                        if (d.this.o) {
                            g.b(d.this);
                        }
                        d.this.l = null;
                        d.this.d();
                    } else {
                        this.f3386b = true;
                        d.this.c((d) 0);
                        t.a(d.this, this, 20);
                    }
                }
            };
            t.a(this, this.l, 20);
        }
    }

    private int a(int i2) {
        OverScroller overScroller = new OverScroller(getContext());
        overScroller.setFriction(1.0f - this.u);
        overScroller.fling(getScrollX(), getScrollY(), i2, 0, 0, Math.max(0, computeHorizontalScrollRange() - getWidth()), 0, 0, ((getWidth() - t.f(this)) - t.g(this)) / 2, 0);
        return overScroller.getFinalX();
    }

    private void b(int i2) {
        double snapInterval = (double) getSnapInterval();
        double scrollX = (double) getScrollX();
        double d2 = scrollX / snapInterval;
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
        if (d3 != scrollX) {
            this.g = true;
            smoothScrollTo((int) d3, getScrollY());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x013c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(int r28) {
        /*
        // Method dump skipped, instructions count: 324
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.scroll.d.c(int):void");
    }

    public void setBackgroundColor(int i2) {
        this.y.a(i2);
    }

    public void a(int i2, float f2) {
        this.y.a(i2, f2);
    }

    public void a(int i2, float f2, float f3) {
        this.y.a(i2, f2, f3);
    }

    public void setBorderRadius(float f2) {
        this.y.a(f2);
    }

    public void a(float f2, int i2) {
        this.y.a(f2, i2);
    }

    public void setBorderStyle(String str) {
        this.y.a(str);
    }
}
