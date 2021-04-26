package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.a.a;
import android.support.v7.view.menu.o;
import android.support.v7.widget.ak;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

/* access modifiers changed from: package-private */
/* compiled from: StandardMenuPopup */
public final class t extends m implements o, View.OnKeyListener, AdapterView.OnItemClickListener, PopupWindow.OnDismissListener {
    private static final int e = a.g.abc_popup_menu_item_layout;

    /* renamed from: a  reason: collision with root package name */
    final ak f1018a;

    /* renamed from: b  reason: collision with root package name */
    final ViewTreeObserver.OnGlobalLayoutListener f1019b = new ViewTreeObserver.OnGlobalLayoutListener() {
        /* class android.support.v7.view.menu.t.AnonymousClass1 */

        public void onGlobalLayout() {
            if (t.this.d() && !t.this.f1018a.g()) {
                View view = t.this.f1020c;
                if (view == null || !view.isShown()) {
                    t.this.c();
                } else {
                    t.this.f1018a.a();
                }
            }
        }
    };

    /* renamed from: c  reason: collision with root package name */
    View f1020c;

    /* renamed from: d  reason: collision with root package name */
    ViewTreeObserver f1021d;
    private final Context f;
    private final h g;
    private final g h;
    private final boolean i;
    private final int j;
    private final int k;
    private final int l;
    private final View.OnAttachStateChangeListener m = new View.OnAttachStateChangeListener() {
        /* class android.support.v7.view.menu.t.AnonymousClass2 */

        public void onViewAttachedToWindow(View view) {
        }

        public void onViewDetachedFromWindow(View view) {
            if (t.this.f1021d != null) {
                if (!t.this.f1021d.isAlive()) {
                    t.this.f1021d = view.getViewTreeObserver();
                }
                t.this.f1021d.removeGlobalOnLayoutListener(t.this.f1019b);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    };
    private PopupWindow.OnDismissListener n;
    private View o;
    private o.a p;
    private boolean q;
    private boolean r;
    private int s;
    private int t = 0;
    private boolean u;

    @Override // android.support.v7.view.menu.m
    public void a(h hVar) {
    }

    @Override // android.support.v7.view.menu.o
    public boolean b() {
        return false;
    }

    public t(Context context, h hVar, View view, int i2, int i3, boolean z) {
        this.f = context;
        this.g = hVar;
        this.i = z;
        this.h = new g(hVar, LayoutInflater.from(context), this.i, e);
        this.k = i2;
        this.l = i3;
        Resources resources = context.getResources();
        this.j = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(a.d.abc_config_prefDialogWidth));
        this.o = view;
        this.f1018a = new ak(this.f, null, this.k, this.l);
        hVar.a(this, context);
    }

    @Override // android.support.v7.view.menu.m
    public void a(boolean z) {
        this.h.a(z);
    }

    @Override // android.support.v7.view.menu.m
    public void a(int i2) {
        this.t = i2;
    }

    private boolean h() {
        View view;
        if (d()) {
            return true;
        }
        if (this.q || (view = this.o) == null) {
            return false;
        }
        this.f1020c = view;
        this.f1018a.a((PopupWindow.OnDismissListener) this);
        this.f1018a.a((AdapterView.OnItemClickListener) this);
        this.f1018a.a(true);
        View view2 = this.f1020c;
        boolean z = this.f1021d == null;
        this.f1021d = view2.getViewTreeObserver();
        if (z) {
            this.f1021d.addOnGlobalLayoutListener(this.f1019b);
        }
        view2.addOnAttachStateChangeListener(this.m);
        this.f1018a.b(view2);
        this.f1018a.e(this.t);
        if (!this.r) {
            this.s = a(this.h, null, this.f, this.j);
            this.r = true;
        }
        this.f1018a.g(this.s);
        this.f1018a.h(2);
        this.f1018a.a(g());
        this.f1018a.a();
        ListView e2 = this.f1018a.e();
        e2.setOnKeyListener(this);
        if (this.u && this.g.n() != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.f).inflate(a.g.abc_popup_menu_header_item_layout, (ViewGroup) e2, false);
            TextView textView = (TextView) frameLayout.findViewById(16908310);
            if (textView != null) {
                textView.setText(this.g.n());
            }
            frameLayout.setEnabled(false);
            e2.addHeaderView(frameLayout, null, false);
        }
        this.f1018a.a((ListAdapter) this.h);
        this.f1018a.a();
        return true;
    }

    @Override // android.support.v7.view.menu.s
    public void a() {
        if (!h()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // android.support.v7.view.menu.s
    public void c() {
        if (d()) {
            this.f1018a.c();
        }
    }

    @Override // android.support.v7.view.menu.s
    public boolean d() {
        return !this.q && this.f1018a.d();
    }

    public void onDismiss() {
        this.q = true;
        this.g.close();
        ViewTreeObserver viewTreeObserver = this.f1021d;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.f1021d = this.f1020c.getViewTreeObserver();
            }
            this.f1021d.removeGlobalOnLayoutListener(this.f1019b);
            this.f1021d = null;
        }
        this.f1020c.removeOnAttachStateChangeListener(this.m);
        PopupWindow.OnDismissListener onDismissListener = this.n;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.support.v7.view.menu.o
    public void b(boolean z) {
        this.r = false;
        g gVar = this.h;
        if (gVar != null) {
            gVar.notifyDataSetChanged();
        }
    }

    @Override // android.support.v7.view.menu.o
    public void a(o.a aVar) {
        this.p = aVar;
    }

    @Override // android.support.v7.view.menu.o
    public boolean a(u uVar) {
        if (uVar.hasVisibleItems()) {
            n nVar = new n(this.f, uVar, this.f1020c, this.i, this.k, this.l);
            nVar.a(this.p);
            nVar.a(m.b(uVar));
            nVar.a(this.n);
            this.n = null;
            this.g.a(false);
            int j2 = this.f1018a.j();
            int k2 = this.f1018a.k();
            if ((Gravity.getAbsoluteGravity(this.t, android.support.v4.g.t.d(this.o)) & 7) == 5) {
                j2 += this.o.getWidth();
            }
            if (nVar.a(j2, k2)) {
                o.a aVar = this.p;
                if (aVar == null) {
                    return true;
                }
                aVar.a(uVar);
                return true;
            }
        }
        return false;
    }

    @Override // android.support.v7.view.menu.o
    public void a(h hVar, boolean z) {
        if (hVar == this.g) {
            c();
            o.a aVar = this.p;
            if (aVar != null) {
                aVar.a(hVar, z);
            }
        }
    }

    @Override // android.support.v7.view.menu.m
    public void a(View view) {
        this.o = view;
    }

    public boolean onKey(View view, int i2, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i2 != 82) {
            return false;
        }
        c();
        return true;
    }

    @Override // android.support.v7.view.menu.m
    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.n = onDismissListener;
    }

    @Override // android.support.v7.view.menu.s
    public ListView e() {
        return this.f1018a.e();
    }

    @Override // android.support.v7.view.menu.m
    public void b(int i2) {
        this.f1018a.c(i2);
    }

    @Override // android.support.v7.view.menu.m
    public void c(int i2) {
        this.f1018a.d(i2);
    }

    @Override // android.support.v7.view.menu.m
    public void c(boolean z) {
        this.u = z;
    }
}
