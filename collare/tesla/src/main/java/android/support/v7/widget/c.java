package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.g.c;
import android.support.v7.a.a;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.j;
import android.support.v7.view.menu.n;
import android.support.v7.view.menu.o;
import android.support.v7.view.menu.p;
import android.support.v7.view.menu.s;
import android.support.v7.view.menu.u;
import android.support.v7.widget.ActionMenuView;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* access modifiers changed from: package-private */
/* compiled from: ActionMenuPresenter */
public class c extends android.support.v7.view.menu.b implements c.a {
    private b A;
    d g;
    e h;
    a i;
    RunnableC0028c j;
    final f k = new f();
    int l;
    private Drawable m;
    private boolean n;
    private boolean o;
    private boolean p;
    private int q;
    private int r;
    private int s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private int x;
    private final SparseBooleanArray y = new SparseBooleanArray();
    private View z;

    public c(Context context) {
        super(context, a.g.abc_action_menu_layout, a.g.abc_action_menu_item_layout);
    }

    @Override // android.support.v7.view.menu.b, android.support.v7.view.menu.o
    public void a(Context context, h hVar) {
        super.a(context, hVar);
        Resources resources = context.getResources();
        android.support.v7.view.a a2 = android.support.v7.view.a.a(context);
        if (!this.p) {
            this.o = a2.b();
        }
        if (!this.v) {
            this.q = a2.c();
        }
        if (!this.t) {
            this.s = a2.a();
        }
        int i2 = this.q;
        if (this.o) {
            if (this.g == null) {
                this.g = new d(this.f959a);
                if (this.n) {
                    this.g.setImageDrawable(this.m);
                    this.m = null;
                    this.n = false;
                }
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.g.measure(makeMeasureSpec, makeMeasureSpec);
            }
            i2 -= this.g.getMeasuredWidth();
        } else {
            this.g = null;
        }
        this.r = i2;
        this.x = (int) (resources.getDisplayMetrics().density * 56.0f);
        this.z = null;
    }

    public void a(Configuration configuration) {
        if (!this.t) {
            this.s = android.support.v7.view.a.a(this.f960b).a();
        }
        if (this.f961c != null) {
            this.f961c.b(true);
        }
    }

    public void c(boolean z2) {
        this.o = z2;
        this.p = true;
    }

    public void d(boolean z2) {
        this.w = z2;
    }

    public void a(Drawable drawable) {
        d dVar = this.g;
        if (dVar != null) {
            dVar.setImageDrawable(drawable);
            return;
        }
        this.n = true;
        this.m = drawable;
    }

    public Drawable c() {
        d dVar = this.g;
        if (dVar != null) {
            return dVar.getDrawable();
        }
        if (this.n) {
            return this.m;
        }
        return null;
    }

    @Override // android.support.v7.view.menu.b
    public p a(ViewGroup viewGroup) {
        p pVar = this.f;
        p a2 = super.a(viewGroup);
        if (pVar != a2) {
            ((ActionMenuView) a2).setPresenter(this);
        }
        return a2;
    }

    @Override // android.support.v7.view.menu.b
    public View a(j jVar, View view, ViewGroup viewGroup) {
        View actionView = jVar.getActionView();
        if (actionView == null || jVar.n()) {
            actionView = super.a(jVar, view, viewGroup);
        }
        actionView.setVisibility(jVar.isActionViewExpanded() ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    @Override // android.support.v7.view.menu.b
    public void a(j jVar, p.a aVar) {
        aVar.a(jVar, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.f);
        if (this.A == null) {
            this.A = new b();
        }
        actionMenuItemView.setPopupCallback(this.A);
    }

    @Override // android.support.v7.view.menu.b
    public boolean a(int i2, j jVar) {
        return jVar.j();
    }

    @Override // android.support.v7.view.menu.b, android.support.v7.view.menu.o
    public void b(boolean z2) {
        super.b(z2);
        ((View) this.f).requestLayout();
        boolean z3 = false;
        if (this.f961c != null) {
            ArrayList<j> l2 = this.f961c.l();
            int size = l2.size();
            for (int i2 = 0; i2 < size; i2++) {
                android.support.v4.g.c a2 = l2.get(i2).a();
                if (a2 != null) {
                    a2.a(this);
                }
            }
        }
        ArrayList<j> m2 = this.f961c != null ? this.f961c.m() : null;
        if (this.o && m2 != null) {
            int size2 = m2.size();
            if (size2 == 1) {
                z3 = !m2.get(0).isActionViewExpanded();
            } else if (size2 > 0) {
                z3 = true;
            }
        }
        if (z3) {
            if (this.g == null) {
                this.g = new d(this.f959a);
            }
            ViewGroup viewGroup = (ViewGroup) this.g.getParent();
            if (viewGroup != this.f) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.g);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.f;
                actionMenuView.addView(this.g, actionMenuView.c());
            }
        } else {
            d dVar = this.g;
            if (dVar != null && dVar.getParent() == this.f) {
                ((ViewGroup) this.f).removeView(this.g);
            }
        }
        ((ActionMenuView) this.f).setOverflowReserved(this.o);
    }

    @Override // android.support.v7.view.menu.b
    public boolean a(ViewGroup viewGroup, int i2) {
        if (viewGroup.getChildAt(i2) == this.g) {
            return false;
        }
        return super.a(viewGroup, i2);
    }

    @Override // android.support.v7.view.menu.b, android.support.v7.view.menu.o
    public boolean a(u uVar) {
        boolean z2 = false;
        if (!uVar.hasVisibleItems()) {
            return false;
        }
        u uVar2 = uVar;
        while (uVar2.t() != this.f961c) {
            uVar2 = (u) uVar2.t();
        }
        View a2 = a(uVar2.getItem());
        if (a2 == null) {
            return false;
        }
        this.l = uVar.getItem().getItemId();
        int size = uVar.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            MenuItem item = uVar.getItem(i2);
            if (item.isVisible() && item.getIcon() != null) {
                z2 = true;
                break;
            }
            i2++;
        }
        this.i = new a(this.f960b, uVar, a2);
        this.i.a(z2);
        this.i.a();
        super.a(uVar);
        return true;
    }

    private View a(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.f;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if ((childAt instanceof p.a) && ((p.a) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    public boolean d() {
        if (!this.o || h() || this.f961c == null || this.f == null || this.j != null || this.f961c.m().isEmpty()) {
            return false;
        }
        this.j = new RunnableC0028c(new e(this.f960b, this.f961c, this.g, true));
        ((View) this.f).post(this.j);
        super.a((u) null);
        return true;
    }

    public boolean e() {
        if (this.j == null || this.f == null) {
            e eVar = this.h;
            if (eVar == null) {
                return false;
            }
            eVar.d();
            return true;
        }
        ((View) this.f).removeCallbacks(this.j);
        this.j = null;
        return true;
    }

    public boolean f() {
        return e() | g();
    }

    public boolean g() {
        a aVar = this.i;
        if (aVar == null) {
            return false;
        }
        aVar.d();
        return true;
    }

    public boolean h() {
        e eVar = this.h;
        return eVar != null && eVar.f();
    }

    public boolean i() {
        return this.j != null || h();
    }

    @Override // android.support.v7.view.menu.b, android.support.v7.view.menu.o
    public boolean b() {
        int i2;
        ArrayList<j> arrayList;
        int i3;
        int i4;
        int i5;
        boolean z2;
        c cVar = this;
        int i6 = 0;
        if (cVar.f961c != null) {
            arrayList = cVar.f961c.j();
            i2 = arrayList.size();
        } else {
            arrayList = null;
            i2 = 0;
        }
        int i7 = cVar.s;
        int i8 = cVar.r;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) cVar.f;
        int i9 = i7;
        boolean z3 = false;
        int i10 = 0;
        int i11 = 0;
        for (int i12 = 0; i12 < i2; i12++) {
            j jVar = arrayList.get(i12);
            if (jVar.l()) {
                i10++;
            } else if (jVar.k()) {
                i11++;
            } else {
                z3 = true;
            }
            if (cVar.w && jVar.isActionViewExpanded()) {
                i9 = 0;
            }
        }
        if (cVar.o && (z3 || i11 + i10 > i9)) {
            i9--;
        }
        int i13 = i9 - i10;
        SparseBooleanArray sparseBooleanArray = cVar.y;
        sparseBooleanArray.clear();
        if (cVar.u) {
            int i14 = cVar.x;
            i3 = i8 / i14;
            i4 = i14 + ((i8 % i14) / i3);
        } else {
            i4 = 0;
            i3 = 0;
        }
        int i15 = i8;
        int i16 = 0;
        int i17 = 0;
        while (i16 < i2) {
            j jVar2 = arrayList.get(i16);
            if (jVar2.l()) {
                View a2 = cVar.a(jVar2, cVar.z, viewGroup);
                if (cVar.z == null) {
                    cVar.z = a2;
                }
                if (cVar.u) {
                    i3 -= ActionMenuView.a(a2, i4, i3, makeMeasureSpec, i6);
                } else {
                    a2.measure(makeMeasureSpec, makeMeasureSpec);
                }
                int measuredWidth = a2.getMeasuredWidth();
                i15 -= measuredWidth;
                if (i17 != 0) {
                    measuredWidth = i17;
                }
                int groupId = jVar2.getGroupId();
                if (groupId != 0) {
                    z2 = true;
                    sparseBooleanArray.put(groupId, true);
                } else {
                    z2 = true;
                }
                jVar2.d(z2);
                i5 = i2;
                i17 = measuredWidth;
            } else if (jVar2.k()) {
                int groupId2 = jVar2.getGroupId();
                boolean z4 = sparseBooleanArray.get(groupId2);
                boolean z5 = (i13 > 0 || z4) && i15 > 0 && (!cVar.u || i3 > 0);
                if (z5) {
                    boolean z6 = z5;
                    View a3 = cVar.a(jVar2, cVar.z, viewGroup);
                    i5 = i2;
                    if (cVar.z == null) {
                        cVar.z = a3;
                    }
                    if (cVar.u) {
                        int a4 = ActionMenuView.a(a3, i4, i3, makeMeasureSpec, 0);
                        i3 -= a4;
                        if (a4 == 0) {
                            z6 = false;
                        }
                    } else {
                        a3.measure(makeMeasureSpec, makeMeasureSpec);
                    }
                    int measuredWidth2 = a3.getMeasuredWidth();
                    i15 -= measuredWidth2;
                    if (i17 == 0) {
                        i17 = measuredWidth2;
                    }
                    z5 = cVar.u ? z6 & (i15 >= 0) : z6 & (i15 + i17 > 0);
                } else {
                    i5 = i2;
                }
                if (z5 && groupId2 != 0) {
                    sparseBooleanArray.put(groupId2, true);
                } else if (z4) {
                    sparseBooleanArray.put(groupId2, false);
                    for (int i18 = 0; i18 < i16; i18++) {
                        j jVar3 = arrayList.get(i18);
                        if (jVar3.getGroupId() == groupId2) {
                            if (jVar3.j()) {
                                i13++;
                            }
                            jVar3.d(false);
                        }
                    }
                }
                if (z5) {
                    i13--;
                }
                jVar2.d(z5);
            } else {
                i5 = i2;
                jVar2.d(false);
            }
            i16++;
            i2 = i5;
            cVar = this;
            i6 = 0;
        }
        return true;
    }

    @Override // android.support.v7.view.menu.b, android.support.v7.view.menu.o
    public void a(h hVar, boolean z2) {
        f();
        super.a(hVar, z2);
    }

    @Override // android.support.v4.g.c.a
    public void a(boolean z2) {
        if (z2) {
            super.a((u) null);
        } else if (this.f961c != null) {
            this.f961c.a(false);
        }
    }

    public void a(ActionMenuView actionMenuView) {
        this.f = actionMenuView;
        actionMenuView.a(this.f961c);
    }

    /* access modifiers changed from: private */
    /* compiled from: ActionMenuPresenter */
    public class d extends p implements ActionMenuView.a {

        /* renamed from: b  reason: collision with root package name */
        private final float[] f1224b = new float[2];

        @Override // android.support.v7.widget.ActionMenuView.a
        public boolean c() {
            return false;
        }

        @Override // android.support.v7.widget.ActionMenuView.a
        public boolean d() {
            return false;
        }

        public d(Context context) {
            super(context, null, a.C0020a.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            ax.a(this, getContentDescription());
            setOnTouchListener(new ag(this, c.this) {
                /* class android.support.v7.widget.c.d.AnonymousClass1 */

                @Override // android.support.v7.widget.ag
                public s a() {
                    if (c.this.h == null) {
                        return null;
                    }
                    return c.this.h.b();
                }

                @Override // android.support.v7.widget.ag
                public boolean b() {
                    c.this.d();
                    return true;
                }

                @Override // android.support.v7.widget.ag
                public boolean c() {
                    if (c.this.j != null) {
                        return false;
                    }
                    c.this.e();
                    return true;
                }
            });
        }

        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            c.this.d();
            return true;
        }

        /* access modifiers changed from: protected */
        public boolean setFrame(int i, int i2, int i3, int i4) {
            boolean frame = super.setFrame(i, i2, i3, i4);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (!(drawable == null || background == null)) {
                int width = getWidth();
                int height = getHeight();
                int max = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                android.support.v4.graphics.drawable.a.a(background, paddingLeft - max, paddingTop - max, paddingLeft + max, paddingTop + max);
            }
            return frame;
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ActionMenuPresenter */
    public class e extends n {
        public e(Context context, h hVar, View view, boolean z) {
            super(context, hVar, view, z, a.C0020a.actionOverflowMenuStyle);
            a(8388613);
            a(c.this.k);
        }

        /* access modifiers changed from: protected */
        @Override // android.support.v7.view.menu.n
        public void e() {
            if (c.this.f961c != null) {
                c.this.f961c.close();
            }
            c.this.h = null;
            super.e();
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ActionMenuPresenter */
    public class a extends n {
        public a(Context context, u uVar, View view) {
            super(context, uVar, view, false, a.C0020a.actionOverflowMenuStyle);
            if (!((j) uVar.getItem()).j()) {
                a(c.this.g == null ? (View) c.this.f : c.this.g);
            }
            a(c.this.k);
        }

        /* access modifiers changed from: protected */
        @Override // android.support.v7.view.menu.n
        public void e() {
            c cVar = c.this;
            cVar.i = null;
            cVar.l = 0;
            super.e();
        }
    }

    /* compiled from: ActionMenuPresenter */
    private class f implements o.a {
        f() {
        }

        @Override // android.support.v7.view.menu.o.a
        public boolean a(h hVar) {
            if (hVar == null) {
                return false;
            }
            c.this.l = ((u) hVar).getItem().getItemId();
            o.a a2 = c.this.a();
            if (a2 != null) {
                return a2.a(hVar);
            }
            return false;
        }

        @Override // android.support.v7.view.menu.o.a
        public void a(h hVar, boolean z) {
            if (hVar instanceof u) {
                hVar.q().a(false);
            }
            o.a a2 = c.this.a();
            if (a2 != null) {
                a2.a(hVar, z);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: android.support.v7.widget.c$c  reason: collision with other inner class name */
    /* compiled from: ActionMenuPresenter */
    public class RunnableC0028c implements Runnable {

        /* renamed from: b  reason: collision with root package name */
        private e f1222b;

        public RunnableC0028c(e eVar) {
            this.f1222b = eVar;
        }

        public void run() {
            if (c.this.f961c != null) {
                c.this.f961c.g();
            }
            View view = (View) c.this.f;
            if (!(view == null || view.getWindowToken() == null || !this.f1222b.c())) {
                c.this.h = this.f1222b;
            }
            c.this.j = null;
        }
    }

    /* compiled from: ActionMenuPresenter */
    private class b extends ActionMenuItemView.b {
        b() {
        }

        @Override // android.support.v7.view.menu.ActionMenuItemView.b
        public s a() {
            if (c.this.i != null) {
                return c.this.i.b();
            }
            return null;
        }
    }
}
