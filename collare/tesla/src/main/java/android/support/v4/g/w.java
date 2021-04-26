package android.support.v4.g;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* compiled from: ViewPager */
public class w extends ViewGroup {

    /* renamed from: a  reason: collision with root package name */
    static final int[] f512a = {16842931};
    private static final j ai = new j();
    private static final Comparator<b> e = new Comparator<b>() {
        /* class android.support.v4.g.w.AnonymousClass1 */

        /* renamed from: a */
        public int compare(b bVar, b bVar2) {
            return bVar.f520b - bVar2.f520b;
        }
    };
    private static final Interpolator f = new Interpolator() {
        /* class android.support.v4.g.w.AnonymousClass2 */

        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    };
    private int A = 1;
    private boolean B;
    private boolean C;
    private int D;
    private int E;
    private int F;
    private float G;
    private float H;
    private float I;
    private float J;
    private int K = -1;
    private VelocityTracker L;
    private int M;
    private int N;
    private int O;
    private int P;
    private boolean Q;
    private EdgeEffect R;
    private EdgeEffect S;
    private boolean T = true;
    private boolean U = false;
    private boolean V;
    private int W;
    private List<f> aa;
    private f ab;
    private f ac;
    private List<e> ad;
    private g ae;
    private int af;
    private int ag;
    private ArrayList<View> ah;
    private final Runnable aj = new Runnable() {
        /* class android.support.v4.g.w.AnonymousClass3 */

        public void run() {
            w.this.setScrollState(0);
            w.this.c();
        }
    };
    private int ak = 0;

    /* renamed from: b  reason: collision with root package name */
    r f513b;

    /* renamed from: c  reason: collision with root package name */
    int f514c;

    /* renamed from: d  reason: collision with root package name */
    private int f515d;
    private final ArrayList<b> g = new ArrayList<>();
    private final b h = new b();
    private final Rect i = new Rect();
    private int j = -1;
    private Parcelable k = null;
    private ClassLoader l = null;
    private Scroller m;
    private boolean n;
    private h o;
    private int p;
    private Drawable q;
    private int r;
    private int s;
    private float t = -3.4028235E38f;
    private float u = Float.MAX_VALUE;
    private int v;
    private int w;
    private boolean x;
    private boolean y;
    private boolean z;

    @Target({ElementType.TYPE})
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    /* compiled from: ViewPager */
    public @interface a {
    }

    /* compiled from: ViewPager */
    public interface e {
        void a(w wVar, r rVar, r rVar2);
    }

    /* compiled from: ViewPager */
    public interface f {
        void a(int i);

        void a(int i, float f, int i2);

        void b(int i);
    }

    /* compiled from: ViewPager */
    public interface g {
        void a(View view, float f);
    }

    /* access modifiers changed from: package-private */
    /* compiled from: ViewPager */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        Object f519a;

        /* renamed from: b  reason: collision with root package name */
        int f520b;

        /* renamed from: c  reason: collision with root package name */
        boolean f521c;

        /* renamed from: d  reason: collision with root package name */
        float f522d;
        float e;

        b() {
        }
    }

    public w(Context context) {
        super(context);
        a();
    }

    /* access modifiers changed from: package-private */
    public void a() {
        setWillNotDraw(false);
        setDescendantFocusability(PKIFailureInfo.transactionIdInUse);
        setFocusable(true);
        Context context = getContext();
        this.m = new Scroller(context, f);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.F = viewConfiguration.getScaledPagingTouchSlop();
        this.M = (int) (400.0f * f2);
        this.N = viewConfiguration.getScaledMaximumFlingVelocity();
        this.R = new EdgeEffect(context);
        this.S = new EdgeEffect(context);
        this.O = (int) (25.0f * f2);
        this.P = (int) (2.0f * f2);
        this.D = (int) (f2 * 16.0f);
        t.a(this, new d());
        if (t.c(this) == 0) {
            t.a((View) this, 1);
        }
        t.a(this, new q() {
            /* class android.support.v4.g.w.AnonymousClass4 */

            /* renamed from: b  reason: collision with root package name */
            private final Rect f518b = new Rect();

            @Override // android.support.v4.g.q
            public ac a(View view, ac acVar) {
                ac a2 = t.a(view, acVar);
                if (a2.e()) {
                    return a2;
                }
                Rect rect = this.f518b;
                rect.left = a2.a();
                rect.top = a2.b();
                rect.right = a2.c();
                rect.bottom = a2.d();
                int childCount = w.this.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    ac b2 = t.b(w.this.getChildAt(i), a2);
                    rect.left = Math.min(b2.a(), rect.left);
                    rect.top = Math.min(b2.b(), rect.top);
                    rect.right = Math.min(b2.c(), rect.right);
                    rect.bottom = Math.min(b2.d(), rect.bottom);
                }
                return a2.a(rect.left, rect.top, rect.right, rect.bottom);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        removeCallbacks(this.aj);
        Scroller scroller = this.m;
        if (scroller != null && !scroller.isFinished()) {
            this.m.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: package-private */
    public void setScrollState(int i2) {
        if (this.ak != i2) {
            this.ak = i2;
            if (this.ae != null) {
                b(i2 != 0);
            }
            f(i2);
        }
    }

    public void setAdapter(r rVar) {
        r rVar2 = this.f513b;
        if (rVar2 != null) {
            rVar2.a((DataSetObserver) null);
            this.f513b.a((ViewGroup) this);
            for (int i2 = 0; i2 < this.g.size(); i2++) {
                b bVar = this.g.get(i2);
                this.f513b.a((ViewGroup) this, bVar.f520b, bVar.f519a);
            }
            this.f513b.b((ViewGroup) this);
            this.g.clear();
            f();
            this.f514c = 0;
            scrollTo(0, 0);
        }
        r rVar3 = this.f513b;
        this.f513b = rVar;
        this.f515d = 0;
        if (this.f513b != null) {
            if (this.o == null) {
                this.o = new h();
            }
            this.f513b.a((DataSetObserver) this.o);
            this.z = false;
            boolean z2 = this.T;
            this.T = true;
            this.f515d = this.f513b.a();
            if (this.j >= 0) {
                this.f513b.a(this.k, this.l);
                a(this.j, false, true);
                this.j = -1;
                this.k = null;
                this.l = null;
            } else if (!z2) {
                c();
            } else {
                requestLayout();
            }
        }
        List<e> list = this.ad;
        if (!(list == null || list.isEmpty())) {
            int size = this.ad.size();
            for (int i3 = 0; i3 < size; i3++) {
                this.ad.get(i3).a(this, rVar3, rVar);
            }
        }
    }

    private void f() {
        int i2 = 0;
        while (i2 < getChildCount()) {
            if (!((c) getChildAt(i2).getLayoutParams()).f523a) {
                removeViewAt(i2);
                i2--;
            }
            i2++;
        }
    }

    public r getAdapter() {
        return this.f513b;
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    public void setCurrentItem(int i2) {
        this.z = false;
        a(i2, !this.T, false);
    }

    public void a(int i2, boolean z2) {
        this.z = false;
        a(i2, z2, false);
    }

    public int getCurrentItem() {
        return this.f514c;
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, boolean z2, boolean z3) {
        a(i2, z2, z3, 0);
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, boolean z2, boolean z3, int i3) {
        r rVar = this.f513b;
        if (rVar == null || rVar.a() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (z3 || this.f514c != i2 || this.g.size() == 0) {
            boolean z4 = true;
            if (i2 < 0) {
                i2 = 0;
            } else if (i2 >= this.f513b.a()) {
                i2 = this.f513b.a() - 1;
            }
            int i4 = this.A;
            int i5 = this.f514c;
            if (i2 > i5 + i4 || i2 < i5 - i4) {
                for (int i6 = 0; i6 < this.g.size(); i6++) {
                    this.g.get(i6).f521c = true;
                }
            }
            if (this.f514c == i2) {
                z4 = false;
            }
            if (this.T) {
                this.f514c = i2;
                if (z4) {
                    e(i2);
                }
                requestLayout();
                return;
            }
            a(i2);
            a(i2, z2, i3, z4);
        } else {
            setScrollingCacheEnabled(false);
        }
    }

    private void a(int i2, boolean z2, int i3, boolean z3) {
        b b2 = b(i2);
        int clientWidth = b2 != null ? (int) (((float) getClientWidth()) * Math.max(this.t, Math.min(b2.e, this.u))) : 0;
        if (z2) {
            a(clientWidth, 0, i3);
            if (z3) {
                e(i2);
                return;
            }
            return;
        }
        if (z3) {
            e(i2);
        }
        a(false);
        scrollTo(clientWidth, 0);
        d(clientWidth);
    }

    @Deprecated
    public void setOnPageChangeListener(f fVar) {
        this.ab = fVar;
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int i2, int i3) {
        if (this.ag == 2) {
            i3 = (i2 - 1) - i3;
        }
        return ((c) this.ah.get(i3).getLayoutParams()).f;
    }

    public int getOffscreenPageLimit() {
        return this.A;
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + i2 + " too small; defaulting to " + 1);
            i2 = 1;
        }
        if (i2 != this.A) {
            this.A = i2;
            c();
        }
    }

    public void setPageMargin(int i2) {
        int i3 = this.p;
        this.p = i2;
        int width = getWidth();
        a(width, width, i2, i3);
        requestLayout();
    }

    public int getPageMargin() {
        return this.p;
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.q = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setPageMarginDrawable(int i2) {
        setPageMarginDrawable(android.support.v4.content.c.a(getContext(), i2));
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.q;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.q;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(getDrawableState());
        }
    }

    /* access modifiers changed from: package-private */
    public float a(float f2) {
        return (float) Math.sin((double) ((f2 - 0.5f) * 0.47123894f));
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3, int i4) {
        int i5;
        int i6;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        Scroller scroller = this.m;
        if (scroller != null && !scroller.isFinished()) {
            int currX = this.n ? this.m.getCurrX() : this.m.getStartX();
            this.m.abortAnimation();
            setScrollingCacheEnabled(false);
            i5 = currX;
        } else {
            i5 = getScrollX();
        }
        int scrollY = getScrollY();
        int i7 = i2 - i5;
        int i8 = i3 - scrollY;
        if (i7 == 0 && i8 == 0) {
            a(false);
            c();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i9 = clientWidth / 2;
        float f2 = (float) clientWidth;
        float f3 = (float) i9;
        float a2 = f3 + (a(Math.min(1.0f, (((float) Math.abs(i7)) * 1.0f) / f2)) * f3);
        int abs = Math.abs(i4);
        if (abs > 0) {
            i6 = Math.round(Math.abs(a2 / ((float) abs)) * 1000.0f) * 4;
        } else {
            i6 = (int) (((((float) Math.abs(i7)) / ((f2 * this.f513b.a(this.f514c)) + ((float) this.p))) + 1.0f) * 100.0f);
        }
        int min = Math.min(i6, 600);
        this.n = false;
        this.m.startScroll(i5, scrollY, i7, i8, min);
        t.b(this);
    }

    /* access modifiers changed from: package-private */
    public b a(int i2, int i3) {
        b bVar = new b();
        bVar.f520b = i2;
        bVar.f519a = this.f513b.a((ViewGroup) this, i2);
        bVar.f522d = this.f513b.a(i2);
        if (i3 < 0 || i3 >= this.g.size()) {
            this.g.add(bVar);
        } else {
            this.g.add(i3, bVar);
        }
        return bVar;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        int a2 = this.f513b.a();
        this.f515d = a2;
        boolean z2 = this.g.size() < (this.A * 2) + 1 && this.g.size() < a2;
        int i2 = this.f514c;
        int i3 = 0;
        boolean z3 = false;
        while (i3 < this.g.size()) {
            b bVar = this.g.get(i3);
            int a3 = this.f513b.a(bVar.f519a);
            if (a3 != -1) {
                if (a3 == -2) {
                    this.g.remove(i3);
                    i3--;
                    if (!z3) {
                        this.f513b.a((ViewGroup) this);
                        z3 = true;
                    }
                    this.f513b.a((ViewGroup) this, bVar.f520b, bVar.f519a);
                    if (this.f514c == bVar.f520b) {
                        i2 = Math.max(0, Math.min(this.f514c, a2 - 1));
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                } else if (bVar.f520b != a3) {
                    if (bVar.f520b == this.f514c) {
                        i2 = a3;
                    }
                    bVar.f520b = a3;
                    z2 = true;
                }
            }
            i3++;
        }
        if (z3) {
            this.f513b.b((ViewGroup) this);
        }
        Collections.sort(this.g, e);
        if (z2) {
            int childCount = getChildCount();
            for (int i4 = 0; i4 < childCount; i4++) {
                c cVar = (c) getChildAt(i4).getLayoutParams();
                if (!cVar.f523a) {
                    cVar.f525c = BitmapDescriptorFactory.HUE_RED;
                }
            }
            a(i2, false, true);
            requestLayout();
        }
    }

    /* access modifiers changed from: package-private */
    public void c() {
        a(this.f514c);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0064, code lost:
        if (r8.f520b == r17.f514c) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006a, code lost:
        r8 = null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r18) {
        /*
        // Method dump skipped, instructions count: 631
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.g.w.a(int):void");
    }

    private void g() {
        if (this.ag != 0) {
            ArrayList<View> arrayList = this.ah;
            if (arrayList == null) {
                this.ah = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                this.ah.add(getChildAt(i2));
            }
            Collections.sort(this.ah, ai);
        }
    }

    private void a(b bVar, int i2, b bVar2) {
        b bVar3;
        b bVar4;
        int a2 = this.f513b.a();
        int clientWidth = getClientWidth();
        float f2 = clientWidth > 0 ? ((float) this.p) / ((float) clientWidth) : BitmapDescriptorFactory.HUE_RED;
        if (bVar2 != null) {
            int i3 = bVar2.f520b;
            if (i3 < bVar.f520b) {
                float f3 = bVar2.e + bVar2.f522d + f2;
                int i4 = i3 + 1;
                int i5 = 0;
                while (i4 <= bVar.f520b && i5 < this.g.size()) {
                    b bVar5 = this.g.get(i5);
                    while (true) {
                        bVar4 = bVar5;
                        if (i4 > bVar4.f520b && i5 < this.g.size() - 1) {
                            i5++;
                            bVar5 = this.g.get(i5);
                        }
                    }
                    while (i4 < bVar4.f520b) {
                        f3 += this.f513b.a(i4) + f2;
                        i4++;
                    }
                    bVar4.e = f3;
                    f3 += bVar4.f522d + f2;
                    i4++;
                }
            } else if (i3 > bVar.f520b) {
                int size = this.g.size() - 1;
                float f4 = bVar2.e;
                while (true) {
                    i3--;
                    if (i3 < bVar.f520b || size < 0) {
                        break;
                    }
                    b bVar6 = this.g.get(size);
                    while (true) {
                        bVar3 = bVar6;
                        if (i3 < bVar3.f520b && size > 0) {
                            size--;
                            bVar6 = this.g.get(size);
                        }
                    }
                    while (i3 > bVar3.f520b) {
                        f4 -= this.f513b.a(i3) + f2;
                        i3--;
                    }
                    f4 -= bVar3.f522d + f2;
                    bVar3.e = f4;
                }
            }
        }
        int size2 = this.g.size();
        float f5 = bVar.e;
        int i6 = bVar.f520b - 1;
        this.t = bVar.f520b == 0 ? bVar.e : -3.4028235E38f;
        int i7 = a2 - 1;
        this.u = bVar.f520b == i7 ? (bVar.e + bVar.f522d) - 1.0f : Float.MAX_VALUE;
        int i8 = i2 - 1;
        while (i8 >= 0) {
            b bVar7 = this.g.get(i8);
            while (i6 > bVar7.f520b) {
                f5 -= this.f513b.a(i6) + f2;
                i6--;
            }
            f5 -= bVar7.f522d + f2;
            bVar7.e = f5;
            if (bVar7.f520b == 0) {
                this.t = f5;
            }
            i8--;
            i6--;
        }
        float f6 = bVar.e + bVar.f522d + f2;
        int i9 = bVar.f520b + 1;
        int i10 = i2 + 1;
        while (i10 < size2) {
            b bVar8 = this.g.get(i10);
            while (i9 < bVar8.f520b) {
                f6 += this.f513b.a(i9) + f2;
                i9++;
            }
            if (bVar8.f520b == i7) {
                this.u = (bVar8.f522d + f6) - 1.0f;
            }
            bVar8.e = f6;
            f6 += bVar8.f522d + f2;
            i10++;
            i9++;
        }
        this.U = false;
    }

    /* compiled from: ViewPager */
    public static class i extends a {
        public static final Parcelable.Creator<i> CREATOR = new Parcelable.ClassLoaderCreator<i>() {
            /* class android.support.v4.g.w.i.AnonymousClass1 */

            /* renamed from: a */
            public i createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new i(parcel, classLoader);
            }

            /* renamed from: a */
            public i createFromParcel(Parcel parcel) {
                return new i(parcel, null);
            }

            /* renamed from: a */
            public i[] newArray(int i) {
                return new i[i];
            }
        };

        /* renamed from: b  reason: collision with root package name */
        int f529b;

        /* renamed from: c  reason: collision with root package name */
        Parcelable f530c;

        /* renamed from: d  reason: collision with root package name */
        ClassLoader f531d;

        public i(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.support.v4.g.a
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f529b);
            parcel.writeParcelable(this.f530c, i);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f529b + "}";
        }

        i(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.f529b = parcel.readInt();
            this.f530c = parcel.readParcelable(classLoader);
            this.f531d = classLoader;
        }
    }

    public Parcelable onSaveInstanceState() {
        i iVar = new i(super.onSaveInstanceState());
        iVar.f529b = this.f514c;
        r rVar = this.f513b;
        if (rVar != null) {
            iVar.f530c = rVar.b();
        }
        return iVar;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof i)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        i iVar = (i) parcelable;
        super.onRestoreInstanceState(iVar.a());
        r rVar = this.f513b;
        if (rVar != null) {
            rVar.a(iVar.f530c, iVar.f531d);
            a(iVar.f529b, false, true);
            return;
        }
        this.j = iVar.f529b;
        this.k = iVar.f530c;
        this.l = iVar.f531d;
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        c cVar = (c) layoutParams;
        cVar.f523a |= c(view);
        if (!this.x) {
            super.addView(view, i2, layoutParams);
        } else if (cVar == null || !cVar.f523a) {
            cVar.f526d = true;
            addViewInLayout(view, i2, layoutParams);
        } else {
            throw new IllegalStateException("Cannot add pager decor view during layout");
        }
    }

    private static boolean c(View view) {
        return view.getClass().getAnnotation(a.class) != null;
    }

    public void removeView(View view) {
        if (this.x) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    /* access modifiers changed from: package-private */
    public b a(View view) {
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            b bVar = this.g.get(i2);
            if (this.f513b.a(view, bVar.f519a)) {
                return bVar;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public b b(View view) {
        while (true) {
            ViewParent parent = view.getParent();
            if (parent == this) {
                return a(view);
            }
            if (parent == null || !(parent instanceof View)) {
                return null;
            }
            view = (View) parent;
        }
    }

    /* access modifiers changed from: package-private */
    public b b(int i2) {
        for (int i3 = 0; i3 < this.g.size(); i3++) {
            b bVar = this.g.get(i3);
            if (bVar.f520b == i2) {
                return bVar;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.T = true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        c cVar;
        c cVar2;
        int i4;
        int i5;
        int i6;
        setMeasuredDimension(getDefaultSize(0, i2), getDefaultSize(0, i3));
        int measuredWidth = getMeasuredWidth();
        this.E = Math.min(measuredWidth / 10, this.D);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int i7 = measuredHeight;
        int i8 = paddingLeft;
        int i9 = 0;
        while (true) {
            boolean z2 = true;
            int i10 = 1073741824;
            if (i9 >= childCount) {
                break;
            }
            View childAt = getChildAt(i9);
            if (!(childAt.getVisibility() == 8 || (cVar2 = (c) childAt.getLayoutParams()) == null || !cVar2.f523a)) {
                int i11 = cVar2.f524b & 7;
                int i12 = cVar2.f524b & 112;
                boolean z3 = i12 == 48 || i12 == 80;
                if (!(i11 == 3 || i11 == 5)) {
                    z2 = false;
                }
                int i13 = PKIFailureInfo.systemUnavail;
                if (z3) {
                    i13 = 1073741824;
                    i4 = PKIFailureInfo.systemUnavail;
                } else {
                    i4 = z2 ? 1073741824 : PKIFailureInfo.systemUnavail;
                }
                if (cVar2.width == -2) {
                    i5 = i8;
                } else if (cVar2.width != -1) {
                    i5 = cVar2.width;
                    i13 = 1073741824;
                } else {
                    i5 = i8;
                    i13 = 1073741824;
                }
                if (cVar2.height != -2) {
                    i6 = cVar2.height != -1 ? cVar2.height : i7;
                } else {
                    i6 = i7;
                    i10 = i4;
                }
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i5, i13), View.MeasureSpec.makeMeasureSpec(i6, i10));
                if (z3) {
                    i7 -= childAt.getMeasuredHeight();
                } else if (z2) {
                    i8 -= childAt.getMeasuredWidth();
                }
            }
            i9++;
        }
        this.v = View.MeasureSpec.makeMeasureSpec(i8, 1073741824);
        this.w = View.MeasureSpec.makeMeasureSpec(i7, 1073741824);
        this.x = true;
        c();
        this.x = false;
        int childCount2 = getChildCount();
        for (int i14 = 0; i14 < childCount2; i14++) {
            View childAt2 = getChildAt(i14);
            if (childAt2.getVisibility() != 8 && ((cVar = (c) childAt2.getLayoutParams()) == null || !cVar.f523a)) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (((float) i8) * cVar.f525c), 1073741824), this.w);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 != i4) {
            int i6 = this.p;
            a(i2, i4, i6, i6);
        }
    }

    private void a(int i2, int i3, int i4, int i5) {
        if (i3 <= 0 || this.g.isEmpty()) {
            b b2 = b(this.f514c);
            int min = (int) ((b2 != null ? Math.min(b2.e, this.u) : BitmapDescriptorFactory.HUE_RED) * ((float) ((i2 - getPaddingLeft()) - getPaddingRight())));
            if (min != getScrollX()) {
                a(false);
                scrollTo(min, getScrollY());
            }
        } else if (!this.m.isFinished()) {
            this.m.setFinalX(getCurrentItem() * getClientWidth());
        } else {
            scrollTo((int) ((((float) getScrollX()) / ((float) (((i3 - getPaddingLeft()) - getPaddingRight()) + i5))) * ((float) (((i2 - getPaddingLeft()) - getPaddingRight()) + i4))), getScrollY());
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        boolean z3;
        b a2;
        int i6;
        int i7;
        int childCount = getChildCount();
        int i8 = i4 - i2;
        int i9 = i5 - i3;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollX = getScrollX();
        int i10 = paddingBottom;
        int i11 = 0;
        int i12 = paddingTop;
        int i13 = paddingLeft;
        for (int i14 = 0; i14 < childCount; i14++) {
            View childAt = getChildAt(i14);
            if (childAt.getVisibility() != 8) {
                c cVar = (c) childAt.getLayoutParams();
                if (cVar.f523a) {
                    int i15 = cVar.f524b & 7;
                    int i16 = cVar.f524b & 112;
                    if (i15 == 1) {
                        i6 = Math.max((i8 - childAt.getMeasuredWidth()) / 2, i13);
                    } else if (i15 == 3) {
                        i6 = i13;
                        i13 = childAt.getMeasuredWidth() + i13;
                    } else if (i15 != 5) {
                        i6 = i13;
                    } else {
                        i6 = (i8 - paddingRight) - childAt.getMeasuredWidth();
                        paddingRight += childAt.getMeasuredWidth();
                    }
                    if (i16 == 16) {
                        i7 = Math.max((i9 - childAt.getMeasuredHeight()) / 2, i12);
                    } else if (i16 == 48) {
                        i7 = i12;
                        i12 = childAt.getMeasuredHeight() + i12;
                    } else if (i16 != 80) {
                        i7 = i12;
                    } else {
                        i7 = (i9 - i10) - childAt.getMeasuredHeight();
                        i10 += childAt.getMeasuredHeight();
                    }
                    int i17 = i6 + scrollX;
                    childAt.layout(i17, i7, childAt.getMeasuredWidth() + i17, i7 + childAt.getMeasuredHeight());
                    i11++;
                }
            }
        }
        int i18 = (i8 - i13) - paddingRight;
        for (int i19 = 0; i19 < childCount; i19++) {
            View childAt2 = getChildAt(i19);
            if (childAt2.getVisibility() != 8) {
                c cVar2 = (c) childAt2.getLayoutParams();
                if (!cVar2.f523a && (a2 = a(childAt2)) != null) {
                    float f2 = (float) i18;
                    int i20 = ((int) (a2.e * f2)) + i13;
                    if (cVar2.f526d) {
                        cVar2.f526d = false;
                        childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (f2 * cVar2.f525c), 1073741824), View.MeasureSpec.makeMeasureSpec((i9 - i12) - i10, 1073741824));
                    }
                    childAt2.layout(i20, i12, childAt2.getMeasuredWidth() + i20, childAt2.getMeasuredHeight() + i12);
                }
            }
        }
        this.r = i12;
        this.s = i9 - i10;
        this.W = i11;
        if (this.T) {
            z3 = false;
            a(this.f514c, false, 0, false);
        } else {
            z3 = false;
        }
        this.T = z3;
    }

    public void computeScroll() {
        this.n = true;
        if (this.m.isFinished() || !this.m.computeScrollOffset()) {
            a(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.m.getCurrX();
        int currY = this.m.getCurrY();
        if (!(scrollX == currX && scrollY == currY)) {
            scrollTo(currX, currY);
            if (!d(currX)) {
                this.m.abortAnimation();
                scrollTo(0, currY);
            }
        }
        t.b(this);
    }

    private boolean d(int i2) {
        if (this.g.size() != 0) {
            b i3 = i();
            int clientWidth = getClientWidth();
            int i4 = this.p;
            int i5 = clientWidth + i4;
            float f2 = (float) clientWidth;
            int i6 = i3.f520b;
            float f3 = ((((float) i2) / f2) - i3.e) / (i3.f522d + (((float) i4) / f2));
            this.V = false;
            a(i6, f3, (int) (((float) i5) * f3));
            if (this.V) {
                return true;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        } else if (this.T) {
            return false;
        } else {
            this.V = false;
            a(0, BitmapDescriptorFactory.HUE_RED, 0);
            if (this.V) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i2, float f2, int i3) {
        int i4;
        if (this.W > 0) {
            int scrollX = getScrollX();
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            int width = getWidth();
            int childCount = getChildCount();
            int i5 = paddingRight;
            int i6 = paddingLeft;
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = getChildAt(i7);
                c cVar = (c) childAt.getLayoutParams();
                if (cVar.f523a) {
                    int i8 = cVar.f524b & 7;
                    if (i8 == 1) {
                        i4 = i6;
                        i6 = Math.max((width - childAt.getMeasuredWidth()) / 2, i6);
                    } else if (i8 == 3) {
                        i4 = childAt.getWidth() + i6;
                    } else if (i8 != 5) {
                        i4 = i6;
                    } else {
                        int measuredWidth = (width - i5) - childAt.getMeasuredWidth();
                        i5 += childAt.getMeasuredWidth();
                        i4 = i6;
                        i6 = measuredWidth;
                    }
                    int left = (i6 + scrollX) - childAt.getLeft();
                    if (left != 0) {
                        childAt.offsetLeftAndRight(left);
                    }
                    i6 = i4;
                }
            }
        }
        b(i2, f2, i3);
        if (this.ae != null) {
            int scrollX2 = getScrollX();
            int childCount2 = getChildCount();
            for (int i9 = 0; i9 < childCount2; i9++) {
                View childAt2 = getChildAt(i9);
                if (!((c) childAt2.getLayoutParams()).f523a) {
                    this.ae.a(childAt2, ((float) (childAt2.getLeft() - scrollX2)) / ((float) getClientWidth()));
                }
            }
        }
        this.V = true;
    }

    private void b(int i2, float f2, int i3) {
        f fVar = this.ab;
        if (fVar != null) {
            fVar.a(i2, f2, i3);
        }
        List<f> list = this.aa;
        if (list != null) {
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                f fVar2 = this.aa.get(i4);
                if (fVar2 != null) {
                    fVar2.a(i2, f2, i3);
                }
            }
        }
        f fVar3 = this.ac;
        if (fVar3 != null) {
            fVar3.a(i2, f2, i3);
        }
    }

    private void e(int i2) {
        f fVar = this.ab;
        if (fVar != null) {
            fVar.a(i2);
        }
        List<f> list = this.aa;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                f fVar2 = this.aa.get(i3);
                if (fVar2 != null) {
                    fVar2.a(i2);
                }
            }
        }
        f fVar3 = this.ac;
        if (fVar3 != null) {
            fVar3.a(i2);
        }
    }

    private void f(int i2) {
        f fVar = this.ab;
        if (fVar != null) {
            fVar.b(i2);
        }
        List<f> list = this.aa;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                f fVar2 = this.aa.get(i3);
                if (fVar2 != null) {
                    fVar2.b(i2);
                }
            }
        }
        f fVar3 = this.ac;
        if (fVar3 != null) {
            fVar3.b(i2);
        }
    }

    private void a(boolean z2) {
        boolean z3 = this.ak == 2;
        if (z3) {
            setScrollingCacheEnabled(false);
            if (!this.m.isFinished()) {
                this.m.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.m.getCurrX();
                int currY = this.m.getCurrY();
                if (!(scrollX == currX && scrollY == currY)) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        d(currX);
                    }
                }
            }
        }
        this.z = false;
        boolean z4 = z3;
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            b bVar = this.g.get(i2);
            if (bVar.f521c) {
                bVar.f521c = false;
                z4 = true;
            }
        }
        if (!z4) {
            return;
        }
        if (z2) {
            t.a(this, this.aj);
        } else {
            this.aj.run();
        }
    }

    private boolean a(float f2, float f3) {
        return (f2 < ((float) this.E) && f3 > BitmapDescriptorFactory.HUE_RED) || (f2 > ((float) (getWidth() - this.E)) && f3 < BitmapDescriptorFactory.HUE_RED);
    }

    private void b(boolean z2) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            getChildAt(i2).setLayerType(z2 ? this.af : 0, null);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            h();
            return false;
        }
        if (action != 0) {
            if (this.B) {
                return true;
            }
            if (this.C) {
                return false;
            }
        }
        if (action == 0) {
            float x2 = motionEvent.getX();
            this.I = x2;
            this.G = x2;
            float y2 = motionEvent.getY();
            this.J = y2;
            this.H = y2;
            this.K = motionEvent.getPointerId(0);
            this.C = false;
            this.n = true;
            this.m.computeScrollOffset();
            if (this.ak != 2 || Math.abs(this.m.getFinalX() - this.m.getCurrX()) <= this.P) {
                a(false);
                this.B = false;
            } else {
                this.m.abortAnimation();
                this.z = false;
                c();
                this.B = true;
                c(true);
                setScrollState(1);
            }
        } else if (action == 2) {
            int i2 = this.K;
            if (i2 != -1) {
                int findPointerIndex = motionEvent.findPointerIndex(i2);
                float x3 = motionEvent.getX(findPointerIndex);
                float f2 = x3 - this.G;
                float abs = Math.abs(f2);
                float y3 = motionEvent.getY(findPointerIndex);
                float abs2 = Math.abs(y3 - this.J);
                int i3 = (f2 > BitmapDescriptorFactory.HUE_RED ? 1 : (f2 == BitmapDescriptorFactory.HUE_RED ? 0 : -1));
                if (i3 == 0 || a(this.G, f2) || !a(this, false, (int) f2, (int) x3, (int) y3)) {
                    if (abs > ((float) this.F) && abs * 0.5f > abs2) {
                        this.B = true;
                        c(true);
                        setScrollState(1);
                        this.G = i3 > 0 ? this.I + ((float) this.F) : this.I - ((float) this.F);
                        this.H = y3;
                        setScrollingCacheEnabled(true);
                    } else if (abs2 > ((float) this.F)) {
                        this.C = true;
                    }
                    if (this.B && b(x3)) {
                        t.b(this);
                    }
                } else {
                    this.G = x3;
                    this.H = y3;
                    this.C = true;
                    return false;
                }
            }
        } else if (action == 6) {
            a(motionEvent);
        }
        if (this.L == null) {
            this.L = VelocityTracker.obtain();
        }
        this.L.addMovement(motionEvent);
        return this.B;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        r rVar;
        if (this.Q) {
            return true;
        }
        boolean z2 = false;
        if ((motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) || (rVar = this.f513b) == null || rVar.a() == 0) {
            return false;
        }
        if (this.L == null) {
            this.L = VelocityTracker.obtain();
        }
        this.L.addMovement(motionEvent);
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.m.abortAnimation();
                this.z = false;
                c();
                float x2 = motionEvent.getX();
                this.I = x2;
                this.G = x2;
                float y2 = motionEvent.getY();
                this.J = y2;
                this.H = y2;
                this.K = motionEvent.getPointerId(0);
                break;
            case 1:
                if (this.B) {
                    VelocityTracker velocityTracker = this.L;
                    velocityTracker.computeCurrentVelocity(1000, (float) this.N);
                    int xVelocity = (int) velocityTracker.getXVelocity(this.K);
                    this.z = true;
                    int clientWidth = getClientWidth();
                    int scrollX = getScrollX();
                    b i2 = i();
                    float f2 = (float) clientWidth;
                    a(a(i2.f520b, ((((float) scrollX) / f2) - i2.e) / (i2.f522d + (((float) this.p) / f2)), xVelocity, (int) (motionEvent.getX(motionEvent.findPointerIndex(this.K)) - this.I)), true, true, xVelocity);
                    z2 = h();
                    break;
                }
                break;
            case 2:
                if (!this.B) {
                    int findPointerIndex = motionEvent.findPointerIndex(this.K);
                    if (findPointerIndex == -1) {
                        z2 = h();
                        break;
                    } else {
                        float x3 = motionEvent.getX(findPointerIndex);
                        float abs = Math.abs(x3 - this.G);
                        float y3 = motionEvent.getY(findPointerIndex);
                        float abs2 = Math.abs(y3 - this.H);
                        if (abs > ((float) this.F) && abs > abs2) {
                            this.B = true;
                            c(true);
                            float f3 = this.I;
                            this.G = x3 - f3 > BitmapDescriptorFactory.HUE_RED ? f3 + ((float) this.F) : f3 - ((float) this.F);
                            this.H = y3;
                            setScrollState(1);
                            setScrollingCacheEnabled(true);
                            ViewParent parent = getParent();
                            if (parent != null) {
                                parent.requestDisallowInterceptTouchEvent(true);
                            }
                        }
                    }
                }
                if (this.B) {
                    z2 = false | b(motionEvent.getX(motionEvent.findPointerIndex(this.K)));
                    break;
                }
                break;
            case 3:
                if (this.B) {
                    a(this.f514c, true, 0, false);
                    z2 = h();
                    break;
                }
                break;
            case 5:
                int actionIndex = motionEvent.getActionIndex();
                this.G = motionEvent.getX(actionIndex);
                this.K = motionEvent.getPointerId(actionIndex);
                break;
            case 6:
                a(motionEvent);
                this.G = motionEvent.getX(motionEvent.findPointerIndex(this.K));
                break;
        }
        if (z2) {
            t.b(this);
        }
        return true;
    }

    private boolean h() {
        this.K = -1;
        j();
        this.R.onRelease();
        this.S.onRelease();
        return this.R.isFinished() || this.S.isFinished();
    }

    private void c(boolean z2) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z2);
        }
    }

    private boolean b(float f2) {
        boolean z2;
        boolean z3;
        float f3 = this.G - f2;
        this.G = f2;
        float scrollX = ((float) getScrollX()) + f3;
        float clientWidth = (float) getClientWidth();
        float f4 = this.t * clientWidth;
        float f5 = this.u * clientWidth;
        boolean z4 = false;
        b bVar = this.g.get(0);
        ArrayList<b> arrayList = this.g;
        b bVar2 = arrayList.get(arrayList.size() - 1);
        if (bVar.f520b != 0) {
            f4 = bVar.e * clientWidth;
            z2 = false;
        } else {
            z2 = true;
        }
        if (bVar2.f520b != this.f513b.a() - 1) {
            f5 = bVar2.e * clientWidth;
            z3 = false;
        } else {
            z3 = true;
        }
        if (scrollX < f4) {
            if (z2) {
                this.R.onPull(Math.abs(f4 - scrollX) / clientWidth);
                z4 = true;
            }
            scrollX = f4;
        } else if (scrollX > f5) {
            if (z3) {
                this.S.onPull(Math.abs(scrollX - f5) / clientWidth);
                z4 = true;
            }
            scrollX = f5;
        }
        int i2 = (int) scrollX;
        this.G += scrollX - ((float) i2);
        scrollTo(i2, getScrollY());
        d(i2);
        return z4;
    }

    private b i() {
        int i2;
        int clientWidth = getClientWidth();
        float scrollX = clientWidth > 0 ? ((float) getScrollX()) / ((float) clientWidth) : BitmapDescriptorFactory.HUE_RED;
        float f2 = clientWidth > 0 ? ((float) this.p) / ((float) clientWidth) : BitmapDescriptorFactory.HUE_RED;
        b bVar = null;
        int i3 = 0;
        boolean z2 = true;
        int i4 = -1;
        float f3 = BitmapDescriptorFactory.HUE_RED;
        float f4 = BitmapDescriptorFactory.HUE_RED;
        while (i3 < this.g.size()) {
            b bVar2 = this.g.get(i3);
            if (!z2 && bVar2.f520b != (i2 = i4 + 1)) {
                bVar2 = this.h;
                bVar2.e = f3 + f4 + f2;
                bVar2.f520b = i2;
                bVar2.f522d = this.f513b.a(bVar2.f520b);
                i3--;
            }
            f3 = bVar2.e;
            float f5 = bVar2.f522d + f3 + f2;
            if (!z2 && scrollX < f3) {
                return bVar;
            }
            if (scrollX < f5 || i3 == this.g.size() - 1) {
                return bVar2;
            }
            i4 = bVar2.f520b;
            f4 = bVar2.f522d;
            i3++;
            bVar = bVar2;
            z2 = false;
        }
        return bVar;
    }

    private int a(int i2, float f2, int i3, int i4) {
        if (Math.abs(i4) <= this.O || Math.abs(i3) <= this.M) {
            i2 += (int) (f2 + (i2 >= this.f514c ? 0.4f : 0.6f));
        } else if (i3 <= 0) {
            i2++;
        }
        if (this.g.size() <= 0) {
            return i2;
        }
        ArrayList<b> arrayList = this.g;
        return Math.max(this.g.get(0).f520b, Math.min(i2, arrayList.get(arrayList.size() - 1).f520b));
    }

    public void draw(Canvas canvas) {
        r rVar;
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        boolean z2 = false;
        if (overScrollMode == 0 || (overScrollMode == 1 && (rVar = this.f513b) != null && rVar.a() > 1)) {
            if (!this.R.isFinished()) {
                int save = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((float) ((-height) + getPaddingTop()), this.t * ((float) width));
                this.R.setSize(height, width);
                z2 = false | this.R.draw(canvas);
                canvas.restoreToCount(save);
            }
            if (!this.S.isFinished()) {
                int save2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate((float) (-getPaddingTop()), (-(this.u + 1.0f)) * ((float) width2));
                this.S.setSize(height2, width2);
                z2 |= this.S.draw(canvas);
                canvas.restoreToCount(save2);
            }
        } else {
            this.R.finish();
            this.S.finish();
        }
        if (z2) {
            t.b(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float f2;
        float f3;
        float f4;
        super.onDraw(canvas);
        if (this.p > 0 && this.q != null && this.g.size() > 0 && this.f513b != null) {
            int scrollX = getScrollX();
            int width = getWidth();
            float f5 = (float) width;
            float f6 = ((float) this.p) / f5;
            int i2 = 0;
            b bVar = this.g.get(0);
            float f7 = bVar.e;
            int size = this.g.size();
            int i3 = bVar.f520b;
            int i4 = this.g.get(size - 1).f520b;
            while (i3 < i4) {
                while (i3 > bVar.f520b && i2 < size) {
                    i2++;
                    bVar = this.g.get(i2);
                }
                if (i3 == bVar.f520b) {
                    f3 = (bVar.e + bVar.f522d) * f5;
                    f2 = bVar.e + bVar.f522d + f6;
                } else {
                    float a2 = this.f513b.a(i3);
                    f2 = f7 + a2 + f6;
                    f3 = (f7 + a2) * f5;
                }
                if (((float) this.p) + f3 > ((float) scrollX)) {
                    f4 = f6;
                    this.q.setBounds(Math.round(f3), this.r, Math.round(((float) this.p) + f3), this.s);
                    this.q.draw(canvas);
                } else {
                    f4 = f6;
                }
                if (f3 <= ((float) (scrollX + width))) {
                    i3++;
                    f7 = f2;
                    f6 = f4;
                } else {
                    return;
                }
            }
        }
    }

    private void a(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.K) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.G = motionEvent.getX(i2);
            this.K = motionEvent.getPointerId(i2);
            VelocityTracker velocityTracker = this.L;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    private void j() {
        this.B = false;
        this.C = false;
        VelocityTracker velocityTracker = this.L;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.L = null;
        }
    }

    private void setScrollingCacheEnabled(boolean z2) {
        if (this.y != z2) {
            this.y = z2;
        }
    }

    public boolean canScrollHorizontally(int i2) {
        if (this.f513b == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        if (i2 < 0) {
            if (scrollX > ((int) (((float) clientWidth) * this.t))) {
                return true;
            }
            return false;
        } else if (i2 <= 0 || scrollX >= ((int) (((float) clientWidth) * this.u))) {
            return false;
        } else {
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(View view, boolean z2, int i2, int i3, int i4) {
        int i5;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i6 = i3 + scrollX;
                if (i6 >= childAt.getLeft() && i6 < childAt.getRight() && (i5 = i4 + scrollY) >= childAt.getTop() && i5 < childAt.getBottom() && a(childAt, true, i2, i6 - childAt.getLeft(), i5 - childAt.getTop())) {
                    return true;
                }
            }
        }
        if (!z2 || !view.canScrollHorizontally(-i2)) {
            return false;
        }
        return true;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || a(keyEvent);
    }

    public boolean a(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 61) {
                switch (keyCode) {
                    case 21:
                        if (keyEvent.hasModifiers(2)) {
                            return d();
                        }
                        return c(17);
                    case 22:
                        if (keyEvent.hasModifiers(2)) {
                            return e();
                        }
                        return c(66);
                }
            } else if (keyEvent.hasNoModifiers()) {
                return c(2);
            } else {
                if (keyEvent.hasModifiers(1)) {
                    return c(1);
                }
            }
        }
        return false;
    }

    public boolean c(int i2) {
        boolean z2;
        View findFocus = findFocus();
        boolean z3 = false;
        View view = null;
        if (findFocus != this) {
            if (findFocus != null) {
                ViewParent parent = findFocus.getParent();
                while (true) {
                    if (!(parent instanceof ViewGroup)) {
                        z2 = false;
                        break;
                    } else if (parent == this) {
                        z2 = true;
                        break;
                    } else {
                        parent = parent.getParent();
                    }
                }
                if (!z2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(findFocus.getClass().getSimpleName());
                    for (ViewParent parent2 = findFocus.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
                        sb.append(" => ");
                        sb.append(parent2.getClass().getSimpleName());
                    }
                    Log.e("ViewPager", "arrowScroll tried to find focus based on non-child current focused view " + sb.toString());
                }
            }
            view = findFocus;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, view, i2);
        if (findNextFocus == null || findNextFocus == view) {
            if (i2 == 17 || i2 == 1) {
                z3 = d();
            } else if (i2 == 66 || i2 == 2) {
                z3 = e();
            }
        } else if (i2 == 17) {
            z3 = (view == null || a(this.i, findNextFocus).left < a(this.i, view).left) ? findNextFocus.requestFocus() : d();
        } else if (i2 == 66) {
            z3 = (view == null || a(this.i, findNextFocus).left > a(this.i, view).left) ? findNextFocus.requestFocus() : e();
        }
        if (z3) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i2));
        }
        return z3;
    }

    private Rect a(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect.left += viewGroup.getLeft();
            rect.right += viewGroup.getRight();
            rect.top += viewGroup.getTop();
            rect.bottom += viewGroup.getBottom();
            parent = viewGroup.getParent();
        }
        return rect;
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        int i2 = this.f514c;
        if (i2 <= 0) {
            return false;
        }
        a(i2 - 1, true);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        r rVar = this.f513b;
        if (rVar == null || this.f514c >= rVar.a() - 1) {
            return false;
        }
        a(this.f514c + 1, true);
        return true;
    }

    @Override // android.view.View, android.view.ViewGroup
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        b a2;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                if (childAt.getVisibility() == 0 && (a2 = a(childAt)) != null && a2.f520b == this.f514c) {
                    childAt.addFocusables(arrayList, i2, i3);
                }
            }
        }
        if ((descendantFocusability == 262144 && size != arrayList.size()) || !isFocusable()) {
            return;
        }
        if (((i3 & 1) != 1 || !isInTouchMode() || isFocusableInTouchMode()) && arrayList != null) {
            arrayList.add(this);
        }
    }

    @Override // android.view.View, android.view.ViewGroup
    public void addTouchables(ArrayList<View> arrayList) {
        b a2;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (a2 = a(childAt)) != null && a2.f520b == this.f514c) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean onRequestFocusInDescendants(int i2, Rect rect) {
        int i3;
        int i4;
        b a2;
        int childCount = getChildCount();
        int i5 = -1;
        if ((i2 & 2) != 0) {
            i5 = childCount;
            i4 = 0;
            i3 = 1;
        } else {
            i4 = childCount - 1;
            i3 = -1;
        }
        while (i4 != i5) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() == 0 && (a2 = a(childAt)) != null && a2.f520b == this.f514c && childAt.requestFocus(i2, rect)) {
                return true;
            }
            i4 += i3;
        }
        return false;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        b a2;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (a2 = a(childAt)) != null && a2.f520b == this.f514c && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new c();
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof c) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    /* access modifiers changed from: package-private */
    /* compiled from: ViewPager */
    public class d extends b {
        d() {
        }

        @Override // android.support.v4.g.b
        public void d(View view, AccessibilityEvent accessibilityEvent) {
            super.d(view, accessibilityEvent);
            accessibilityEvent.setClassName(w.class.getName());
            accessibilityEvent.setScrollable(b());
            if (accessibilityEvent.getEventType() == 4096 && w.this.f513b != null) {
                accessibilityEvent.setItemCount(w.this.f513b.a());
                accessibilityEvent.setFromIndex(w.this.f514c);
                accessibilityEvent.setToIndex(w.this.f514c);
            }
        }

        @Override // android.support.v4.g.b
        public void a(View view, android.support.v4.g.a.a aVar) {
            super.a(view, aVar);
            aVar.b(w.class.getName());
            aVar.i(b());
            if (w.this.canScrollHorizontally(1)) {
                aVar.a(4096);
            }
            if (w.this.canScrollHorizontally(-1)) {
                aVar.a(PKIFailureInfo.certRevoked);
            }
        }

        @Override // android.support.v4.g.b
        public boolean a(View view, int i, Bundle bundle) {
            if (super.a(view, i, bundle)) {
                return true;
            }
            if (i != 4096) {
                if (i != 8192 || !w.this.canScrollHorizontally(-1)) {
                    return false;
                }
                w wVar = w.this;
                wVar.setCurrentItem(wVar.f514c - 1);
                return true;
            } else if (!w.this.canScrollHorizontally(1)) {
                return false;
            } else {
                w wVar2 = w.this;
                wVar2.setCurrentItem(wVar2.f514c + 1);
                return true;
            }
        }

        private boolean b() {
            return w.this.f513b != null && w.this.f513b.a() > 1;
        }
    }

    /* compiled from: ViewPager */
    private class h extends DataSetObserver {
        h() {
        }

        public void onChanged() {
            w.this.b();
        }

        public void onInvalidated() {
            w.this.b();
        }
    }

    /* compiled from: ViewPager */
    public static class c extends ViewGroup.LayoutParams {

        /* renamed from: a  reason: collision with root package name */
        public boolean f523a;

        /* renamed from: b  reason: collision with root package name */
        public int f524b;

        /* renamed from: c  reason: collision with root package name */
        float f525c = BitmapDescriptorFactory.HUE_RED;

        /* renamed from: d  reason: collision with root package name */
        boolean f526d;
        int e;
        int f;

        public c() {
            super(-1, -1);
        }

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, w.f512a);
            this.f524b = obtainStyledAttributes.getInteger(0, 48);
            obtainStyledAttributes.recycle();
        }
    }

    /* access modifiers changed from: package-private */
    /* compiled from: ViewPager */
    public static class j implements Comparator<View> {
        j() {
        }

        /* renamed from: a */
        public int compare(View view, View view2) {
            c cVar = (c) view.getLayoutParams();
            c cVar2 = (c) view2.getLayoutParams();
            if (cVar.f523a != cVar2.f523a) {
                return cVar.f523a ? 1 : -1;
            }
            return cVar.e - cVar2.e;
        }
    }
}
