package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.g.n;
import android.support.v4.g.p;
import android.support.v4.g.t;
import android.support.v7.a.a;
import android.support.v7.view.menu.o;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.widget.OverScroller;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

public class ActionBarOverlayLayout extends ViewGroup implements n, ab {
    static final int[] e = {a.C0020a.actionBarSize, 16842841};
    private final Runnable A = new Runnable() {
        /* class android.support.v7.widget.ActionBarOverlayLayout.AnonymousClass3 */

        public void run() {
            ActionBarOverlayLayout.this.d();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.f1033c = actionBarOverlayLayout.f1031a.animate().translationY((float) (-ActionBarOverlayLayout.this.f1031a.getHeight())).setListener(ActionBarOverlayLayout.this.f1034d);
        }
    };
    private final p B;

    /* renamed from: a  reason: collision with root package name */
    ActionBarContainer f1031a;

    /* renamed from: b  reason: collision with root package name */
    boolean f1032b;

    /* renamed from: c  reason: collision with root package name */
    ViewPropertyAnimator f1033c;

    /* renamed from: d  reason: collision with root package name */
    final AnimatorListenerAdapter f1034d = new AnimatorListenerAdapter() {
        /* class android.support.v7.widget.ActionBarOverlayLayout.AnonymousClass1 */

        public void onAnimationEnd(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.f1033c = null;
            actionBarOverlayLayout.f1032b = false;
        }

        public void onAnimationCancel(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.f1033c = null;
            actionBarOverlayLayout.f1032b = false;
        }
    };
    private int f;
    private int g = 0;
    private ContentFrameLayout h;
    private ac i;
    private Drawable j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private int o;
    private int p;
    private final Rect q = new Rect();
    private final Rect r = new Rect();
    private final Rect s = new Rect();
    private final Rect t = new Rect();
    private final Rect u = new Rect();
    private final Rect v = new Rect();
    private final Rect w = new Rect();
    private a x;
    private OverScroller y;
    private final Runnable z = new Runnable() {
        /* class android.support.v7.widget.ActionBarOverlayLayout.AnonymousClass2 */

        public void run() {
            ActionBarOverlayLayout.this.d();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.f1033c = actionBarOverlayLayout.f1031a.animate().translationY(BitmapDescriptorFactory.HUE_RED).setListener(ActionBarOverlayLayout.this.f1034d);
        }
    };

    public interface a {
        void a(int i);

        void g(boolean z);

        void j();

        void k();

        void l();

        void m();
    }

    @Override // android.support.v4.g.n
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return false;
    }

    @Override // android.support.v4.g.n
    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
    }

    public void setShowingForActionMode(boolean z2) {
    }

    public void setUiOptions(int i2) {
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
        this.B = new p(this);
    }

    private void a(Context context) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(e);
        boolean z2 = false;
        this.f = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.j = obtainStyledAttributes.getDrawable(1);
        setWillNotDraw(this.j == null);
        obtainStyledAttributes.recycle();
        if (context.getApplicationInfo().targetSdkVersion < 19) {
            z2 = true;
        }
        this.k = z2;
        this.y = new OverScroller(context);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        d();
    }

    public void setActionBarVisibilityCallback(a aVar) {
        this.x = aVar;
        if (getWindowToken() != null) {
            this.x.a(this.g);
            int i2 = this.p;
            if (i2 != 0) {
                onWindowSystemUiVisibilityChanged(i2);
                t.m(this);
            }
        }
    }

    public void setOverlayMode(boolean z2) {
        this.l = z2;
        this.k = z2 && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public boolean a() {
        return this.l;
    }

    public void setHasNonEmbeddedTabs(boolean z2) {
        this.m = z2;
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a(getContext());
        t.m(this);
    }

    public void onWindowSystemUiVisibilityChanged(int i2) {
        if (Build.VERSION.SDK_INT >= 16) {
            super.onWindowSystemUiVisibilityChanged(i2);
        }
        c();
        int i3 = this.p ^ i2;
        this.p = i2;
        boolean z2 = false;
        boolean z3 = (i2 & 4) == 0;
        if ((i2 & 256) != 0) {
            z2 = true;
        }
        a aVar = this.x;
        if (aVar != null) {
            aVar.g(!z2);
            if (z3 || !z2) {
                this.x.j();
            } else {
                this.x.k();
            }
        }
        if ((i3 & 256) != 0 && this.x != null) {
            t.m(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        this.g = i2;
        a aVar = this.x;
        if (aVar != null) {
            aVar.a(i2);
        }
    }

    private boolean a(View view, Rect rect, boolean z2, boolean z3, boolean z4, boolean z5) {
        boolean z6;
        b bVar = (b) view.getLayoutParams();
        if (!z2 || bVar.leftMargin == rect.left) {
            z6 = false;
        } else {
            bVar.leftMargin = rect.left;
            z6 = true;
        }
        if (z3 && bVar.topMargin != rect.top) {
            bVar.topMargin = rect.top;
            z6 = true;
        }
        if (z5 && bVar.rightMargin != rect.right) {
            bVar.rightMargin = rect.right;
            z6 = true;
        }
        if (!z4 || bVar.bottomMargin == rect.bottom) {
            return z6;
        }
        bVar.bottomMargin = rect.bottom;
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean fitSystemWindows(Rect rect) {
        c();
        int l2 = t.l(this) & 256;
        boolean a2 = a(this.f1031a, rect, true, true, false, true);
        this.t.set(rect);
        bb.a(this, this.t, this.q);
        if (!this.u.equals(this.t)) {
            this.u.set(this.t);
            a2 = true;
        }
        if (!this.r.equals(this.q)) {
            this.r.set(this.q);
            a2 = true;
        }
        if (a2) {
            requestLayout();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public b generateDefaultLayoutParams() {
        return new b(-1, -1);
    }

    /* renamed from: a */
    public b generateLayoutParams(AttributeSet attributeSet) {
        return new b(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new b(layoutParams);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof b;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int i4;
        c();
        measureChildWithMargins(this.f1031a, i2, 0, i3, 0);
        b bVar = (b) this.f1031a.getLayoutParams();
        int max = Math.max(0, this.f1031a.getMeasuredWidth() + bVar.leftMargin + bVar.rightMargin);
        int max2 = Math.max(0, this.f1031a.getMeasuredHeight() + bVar.topMargin + bVar.bottomMargin);
        int combineMeasuredStates = View.combineMeasuredStates(0, this.f1031a.getMeasuredState());
        boolean z2 = (t.l(this) & 256) != 0;
        if (z2) {
            i4 = this.f;
            if (this.m && this.f1031a.getTabContainer() != null) {
                i4 += this.f;
            }
        } else {
            i4 = this.f1031a.getVisibility() != 8 ? this.f1031a.getMeasuredHeight() : 0;
        }
        this.s.set(this.q);
        this.v.set(this.t);
        if (this.l || z2) {
            this.v.top += i4;
            this.v.bottom += 0;
        } else {
            this.s.top += i4;
            this.s.bottom += 0;
        }
        a(this.h, this.s, true, true, true, true);
        if (!this.w.equals(this.v)) {
            this.w.set(this.v);
            this.h.a(this.v);
        }
        measureChildWithMargins(this.h, i2, 0, i3, 0);
        b bVar2 = (b) this.h.getLayoutParams();
        int max3 = Math.max(max, this.h.getMeasuredWidth() + bVar2.leftMargin + bVar2.rightMargin);
        int max4 = Math.max(max2, this.h.getMeasuredHeight() + bVar2.topMargin + bVar2.bottomMargin);
        int combineMeasuredStates2 = View.combineMeasuredStates(combineMeasuredStates, this.h.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(max3 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i2, combineMeasuredStates2), View.resolveSizeAndState(Math.max(max4 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i3, combineMeasuredStates2 << 16));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        getPaddingRight();
        int paddingTop = getPaddingTop();
        getPaddingBottom();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                b bVar = (b) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i7 = bVar.leftMargin + paddingLeft;
                int i8 = bVar.topMargin + paddingTop;
                childAt.layout(i7, i8, measuredWidth + i7, measuredHeight + i8);
            }
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.j != null && !this.k) {
            int bottom = this.f1031a.getVisibility() == 0 ? (int) (((float) this.f1031a.getBottom()) + this.f1031a.getTranslationY() + 0.5f) : 0;
            this.j.setBounds(0, bottom, getWidth(), this.j.getIntrinsicHeight() + bottom);
            this.j.draw(canvas);
        }
    }

    @Override // android.support.v4.g.n
    public boolean onStartNestedScroll(View view, View view2, int i2) {
        if ((i2 & 2) == 0 || this.f1031a.getVisibility() != 0) {
            return false;
        }
        return this.n;
    }

    @Override // android.support.v4.g.n
    public void onNestedScrollAccepted(View view, View view2, int i2) {
        this.B.a(view, view2, i2);
        this.o = getActionBarHideOffset();
        d();
        a aVar = this.x;
        if (aVar != null) {
            aVar.l();
        }
    }

    @Override // android.support.v4.g.n
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        this.o += i3;
        setActionBarHideOffset(this.o);
    }

    @Override // android.support.v4.g.n
    public void onStopNestedScroll(View view) {
        if (this.n && !this.f1032b) {
            if (this.o <= this.f1031a.getHeight()) {
                l();
            } else {
                m();
            }
        }
        a aVar = this.x;
        if (aVar != null) {
            aVar.m();
        }
    }

    @Override // android.support.v4.g.n
    public boolean onNestedFling(View view, float f2, float f3, boolean z2) {
        if (!this.n || !z2) {
            return false;
        }
        if (a(f2, f3)) {
            o();
        } else {
            n();
        }
        this.f1032b = true;
        return true;
    }

    public int getNestedScrollAxes() {
        return this.B.a();
    }

    /* access modifiers changed from: package-private */
    public void c() {
        if (this.h == null) {
            this.h = (ContentFrameLayout) findViewById(a.f.action_bar_activity_content);
            this.f1031a = (ActionBarContainer) findViewById(a.f.action_bar_container);
            this.i = a(findViewById(a.f.action_bar));
        }
    }

    private ac a(View view) {
        if (view instanceof ac) {
            return (ac) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
    }

    public void setHideOnContentScrollEnabled(boolean z2) {
        if (z2 != this.n) {
            this.n = z2;
            if (!z2) {
                d();
                setActionBarHideOffset(0);
            }
        }
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.f1031a;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    public void setActionBarHideOffset(int i2) {
        d();
        this.f1031a.setTranslationY((float) (-Math.max(0, Math.min(i2, this.f1031a.getHeight()))));
    }

    /* access modifiers changed from: package-private */
    public void d() {
        removeCallbacks(this.z);
        removeCallbacks(this.A);
        ViewPropertyAnimator viewPropertyAnimator = this.f1033c;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    private void l() {
        d();
        postDelayed(this.z, 600);
    }

    private void m() {
        d();
        postDelayed(this.A, 600);
    }

    private void n() {
        d();
        this.z.run();
    }

    private void o() {
        d();
        this.A.run();
    }

    private boolean a(float f2, float f3) {
        this.y.fling(0, 0, 0, (int) f3, 0, 0, PKIFailureInfo.systemUnavail, Api.BaseClientBuilder.API_PRIORITY_OTHER);
        return this.y.getFinalY() > this.f1031a.getHeight();
    }

    @Override // android.support.v7.widget.ab
    public void setWindowCallback(Window.Callback callback) {
        c();
        this.i.a(callback);
    }

    @Override // android.support.v7.widget.ab
    public void setWindowTitle(CharSequence charSequence) {
        c();
        this.i.a(charSequence);
    }

    public CharSequence getTitle() {
        c();
        return this.i.e();
    }

    @Override // android.support.v7.widget.ab
    public void a(int i2) {
        c();
        if (i2 == 2) {
            this.i.f();
        } else if (i2 == 5) {
            this.i.g();
        } else if (i2 == 109) {
            setOverlayMode(true);
        }
    }

    public void setIcon(int i2) {
        c();
        this.i.a(i2);
    }

    public void setIcon(Drawable drawable) {
        c();
        this.i.a(drawable);
    }

    public void setLogo(int i2) {
        c();
        this.i.b(i2);
    }

    @Override // android.support.v7.widget.ab
    public boolean e() {
        c();
        return this.i.h();
    }

    @Override // android.support.v7.widget.ab
    public boolean f() {
        c();
        return this.i.i();
    }

    @Override // android.support.v7.widget.ab
    public boolean g() {
        c();
        return this.i.j();
    }

    @Override // android.support.v7.widget.ab
    public boolean h() {
        c();
        return this.i.k();
    }

    @Override // android.support.v7.widget.ab
    public boolean i() {
        c();
        return this.i.l();
    }

    @Override // android.support.v7.widget.ab
    public void j() {
        c();
        this.i.m();
    }

    @Override // android.support.v7.widget.ab
    public void a(Menu menu, o.a aVar) {
        c();
        this.i.a(menu, aVar);
    }

    @Override // android.support.v7.widget.ab
    public void k() {
        c();
        this.i.n();
    }

    public static class b extends ViewGroup.MarginLayoutParams {
        public b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public b(int i, int i2) {
            super(i, i2);
        }

        public b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }
}
