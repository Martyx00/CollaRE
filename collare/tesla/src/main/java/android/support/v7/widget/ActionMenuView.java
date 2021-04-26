package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.j;
import android.support.v7.view.menu.o;
import android.support.v7.view.menu.p;
import android.support.v7.widget.ah;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

public class ActionMenuView extends ah implements h.b, p {

    /* renamed from: a  reason: collision with root package name */
    h.a f1038a;

    /* renamed from: b  reason: collision with root package name */
    e f1039b;

    /* renamed from: c  reason: collision with root package name */
    private h f1040c;

    /* renamed from: d  reason: collision with root package name */
    private Context f1041d;
    private int e;
    private boolean f;
    private c g;
    private o.a h;
    private boolean i;
    private int j;
    private int k;
    private int l;

    public interface a {
        boolean c();

        boolean d();
    }

    public interface e {
        boolean a(MenuItem menuItem);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    public int getWindowAnimations() {
        return 0;
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.k = (int) (56.0f * f2);
        this.l = (int) (f2 * 4.0f);
        this.f1041d = context;
        this.e = 0;
    }

    public void setPopupTheme(int i2) {
        if (this.e != i2) {
            this.e = i2;
            if (i2 == 0) {
                this.f1041d = getContext();
            } else {
                this.f1041d = new ContextThemeWrapper(getContext(), i2);
            }
        }
    }

    public int getPopupTheme() {
        return this.e;
    }

    public void setPresenter(c cVar) {
        this.g = cVar;
        this.g.a(this);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        c cVar = this.g;
        if (cVar != null) {
            cVar.b(false);
            if (this.g.h()) {
                this.g.e();
                this.g.d();
            }
        }
    }

    public void setOnMenuItemClickListener(e eVar) {
        this.f1039b = eVar;
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v7.widget.ah
    public void onMeasure(int i2, int i3) {
        h hVar;
        boolean z = this.i;
        this.i = View.MeasureSpec.getMode(i2) == 1073741824;
        if (z != this.i) {
            this.j = 0;
        }
        int size = View.MeasureSpec.getSize(i2);
        if (!(!this.i || (hVar = this.f1040c) == null || size == this.j)) {
            this.j = size;
            hVar.b(true);
        }
        int childCount = getChildCount();
        if (!this.i || childCount <= 0) {
            for (int i4 = 0; i4 < childCount; i4++) {
                c cVar = (c) getChildAt(i4).getLayoutParams();
                cVar.rightMargin = 0;
                cVar.leftMargin = 0;
            }
            super.onMeasure(i2, i3);
            return;
        }
        c(i2, i3);
    }

    /* JADX WARN: Type inference failed for: r13v14, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r13v17 */
    /* JADX WARN: Type inference failed for: r13v18 */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x0240 A[LOOP:5: B:134:0x0240->B:138:0x025f, LOOP_START, PHI: r13 
      PHI: (r13v3 int) = (r13v2 int), (r13v4 int) binds: [B:133:0x023e, B:138:0x025f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x0264  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x0269  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(int r30, int r31) {
        /*
        // Method dump skipped, instructions count: 625
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActionMenuView.c(int, int):void");
    }

    static int a(View view, int i2, int i3, int i4, int i5) {
        c cVar = (c) view.getLayoutParams();
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i4) - i5, View.MeasureSpec.getMode(i4));
        ActionMenuItemView actionMenuItemView = view instanceof ActionMenuItemView ? (ActionMenuItemView) view : null;
        boolean z = true;
        boolean z2 = actionMenuItemView != null && actionMenuItemView.b();
        int i6 = 2;
        if (i3 <= 0 || (z2 && i3 < 2)) {
            i6 = 0;
        } else {
            view.measure(View.MeasureSpec.makeMeasureSpec(i3 * i2, PKIFailureInfo.systemUnavail), makeMeasureSpec);
            int measuredWidth = view.getMeasuredWidth();
            int i7 = measuredWidth / i2;
            if (measuredWidth % i2 != 0) {
                i7++;
            }
            if (!z2 || i7 >= 2) {
                i6 = i7;
            }
        }
        if (cVar.f1042a || !z2) {
            z = false;
        }
        cVar.f1045d = z;
        cVar.f1043b = i6;
        view.measure(View.MeasureSpec.makeMeasureSpec(i2 * i6, 1073741824), makeMeasureSpec);
        return i6;
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v7.widget.ah
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8;
        int i9;
        if (!this.i) {
            super.onLayout(z, i2, i3, i4, i5);
            return;
        }
        int childCount = getChildCount();
        int i10 = (i5 - i3) / 2;
        int dividerWidth = getDividerWidth();
        int i11 = i4 - i2;
        int paddingRight = (i11 - getPaddingRight()) - getPaddingLeft();
        boolean a2 = bb.a(this);
        int i12 = paddingRight;
        int i13 = 0;
        int i14 = 0;
        for (int i15 = 0; i15 < childCount; i15++) {
            View childAt = getChildAt(i15);
            if (childAt.getVisibility() != 8) {
                c cVar = (c) childAt.getLayoutParams();
                if (cVar.f1042a) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (a(i15)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (a2) {
                        i8 = getPaddingLeft() + cVar.leftMargin;
                        i9 = i8 + measuredWidth;
                    } else {
                        i9 = (getWidth() - getPaddingRight()) - cVar.rightMargin;
                        i8 = i9 - measuredWidth;
                    }
                    int i16 = i10 - (measuredHeight / 2);
                    childAt.layout(i8, i16, i9, measuredHeight + i16);
                    i12 -= measuredWidth;
                    i13 = 1;
                } else {
                    i12 -= (childAt.getMeasuredWidth() + cVar.leftMargin) + cVar.rightMargin;
                    a(i15);
                    i14++;
                }
            }
        }
        if (childCount == 1 && i13 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i17 = (i11 / 2) - (measuredWidth2 / 2);
            int i18 = i10 - (measuredHeight2 / 2);
            childAt2.layout(i17, i18, measuredWidth2 + i17, measuredHeight2 + i18);
            return;
        }
        int i19 = i14 - (i13 ^ 1);
        if (i19 > 0) {
            i6 = i12 / i19;
            i7 = 0;
        } else {
            i7 = 0;
            i6 = 0;
        }
        int max = Math.max(i7, i6);
        if (a2) {
            int width = getWidth() - getPaddingRight();
            while (i7 < childCount) {
                View childAt3 = getChildAt(i7);
                c cVar2 = (c) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !cVar2.f1042a) {
                    int i20 = width - cVar2.rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i21 = i10 - (measuredHeight3 / 2);
                    childAt3.layout(i20 - measuredWidth3, i21, i20, measuredHeight3 + i21);
                    width = i20 - ((measuredWidth3 + cVar2.leftMargin) + max);
                }
                i7++;
            }
            return;
        }
        int paddingLeft = getPaddingLeft();
        while (i7 < childCount) {
            View childAt4 = getChildAt(i7);
            c cVar3 = (c) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !cVar3.f1042a) {
                int i22 = paddingLeft + cVar3.leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i23 = i10 - (measuredHeight4 / 2);
                childAt4.layout(i22, i23, i22 + measuredWidth4, measuredHeight4 + i23);
                paddingLeft = i22 + measuredWidth4 + cVar3.rightMargin + max;
            }
            i7++;
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        i();
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        this.g.a(drawable);
    }

    public Drawable getOverflowIcon() {
        getMenu();
        return this.g.c();
    }

    public boolean a() {
        return this.f;
    }

    public void setOverflowReserved(boolean z) {
        this.f = z;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public c j() {
        c cVar = new c(-2, -2);
        cVar.h = 16;
        return cVar;
    }

    /* renamed from: a */
    public c generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public c generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return j();
        }
        c cVar = layoutParams instanceof c ? new c((c) layoutParams) : new c(layoutParams);
        if (cVar.h <= 0) {
            cVar.h = 16;
        }
        return cVar;
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v7.widget.ah
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams != null && (layoutParams instanceof c);
    }

    public c c() {
        c b2 = j();
        b2.f1042a = true;
        return b2;
    }

    @Override // android.support.v7.view.menu.h.b
    public boolean a(j jVar) {
        return this.f1040c.a(jVar, 0);
    }

    @Override // android.support.v7.view.menu.p
    public void a(h hVar) {
        this.f1040c = hVar;
    }

    public Menu getMenu() {
        if (this.f1040c == null) {
            Context context = getContext();
            this.f1040c = new h(context);
            this.f1040c.a(new d());
            this.g = new c(context);
            this.g.c(true);
            c cVar = this.g;
            o.a aVar = this.h;
            if (aVar == null) {
                aVar = new b();
            }
            cVar.a(aVar);
            this.f1040c.a(this.g, this.f1041d);
            this.g.a(this);
        }
        return this.f1040c;
    }

    public void a(o.a aVar, h.a aVar2) {
        this.h = aVar;
        this.f1038a = aVar2;
    }

    public h d() {
        return this.f1040c;
    }

    public boolean e() {
        c cVar = this.g;
        return cVar != null && cVar.d();
    }

    public boolean f() {
        c cVar = this.g;
        return cVar != null && cVar.e();
    }

    public boolean g() {
        c cVar = this.g;
        return cVar != null && cVar.h();
    }

    public boolean h() {
        c cVar = this.g;
        return cVar != null && cVar.i();
    }

    public void i() {
        c cVar = this.g;
        if (cVar != null) {
            cVar.f();
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(int i2) {
        boolean z = false;
        if (i2 == 0) {
            return false;
        }
        View childAt = getChildAt(i2 - 1);
        View childAt2 = getChildAt(i2);
        if (i2 < getChildCount() && (childAt instanceof a)) {
            z = false | ((a) childAt).d();
        }
        return (i2 <= 0 || !(childAt2 instanceof a)) ? z : z | ((a) childAt2).c();
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.g.d(z);
    }

    /* access modifiers changed from: private */
    public class d implements h.a {
        d() {
        }

        @Override // android.support.v7.view.menu.h.a
        public boolean a(h hVar, MenuItem menuItem) {
            return ActionMenuView.this.f1039b != null && ActionMenuView.this.f1039b.a(menuItem);
        }

        @Override // android.support.v7.view.menu.h.a
        public void a(h hVar) {
            if (ActionMenuView.this.f1038a != null) {
                ActionMenuView.this.f1038a.a(hVar);
            }
        }
    }

    /* access modifiers changed from: private */
    public static class b implements o.a {
        @Override // android.support.v7.view.menu.o.a
        public void a(h hVar, boolean z) {
        }

        @Override // android.support.v7.view.menu.o.a
        public boolean a(h hVar) {
            return false;
        }

        b() {
        }
    }

    public static class c extends ah.a {
        @ViewDebug.ExportedProperty

        /* renamed from: a  reason: collision with root package name */
        public boolean f1042a;
        @ViewDebug.ExportedProperty

        /* renamed from: b  reason: collision with root package name */
        public int f1043b;
        @ViewDebug.ExportedProperty

        /* renamed from: c  reason: collision with root package name */
        public int f1044c;
        @ViewDebug.ExportedProperty

        /* renamed from: d  reason: collision with root package name */
        public boolean f1045d;
        @ViewDebug.ExportedProperty
        public boolean e;
        boolean f;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public c(c cVar) {
            super(cVar);
            this.f1042a = cVar.f1042a;
        }

        public c(int i, int i2) {
            super(i, i2);
            this.f1042a = false;
        }
    }
}
