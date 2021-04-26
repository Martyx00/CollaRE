package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v4.g.aa;
import android.support.v4.g.ab;
import android.support.v4.g.t;
import android.support.v4.g.y;
import android.support.v4.g.z;
import android.support.v7.a.a;
import android.support.v7.app.a;
import android.support.v7.view.b;
import android.support.v7.view.g;
import android.support.v7.view.h;
import android.support.v7.view.menu.h;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ac;
import android.support.v7.widget.an;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* compiled from: WindowDecorActionBar */
public class l extends a implements ActionBarOverlayLayout.a {
    static final /* synthetic */ boolean s = (!l.class.desiredAssertionStatus());
    private static final Interpolator t = new AccelerateInterpolator();
    private static final Interpolator u = new DecelerateInterpolator();
    private boolean A;
    private boolean B;
    private ArrayList<a.b> C = new ArrayList<>();
    private boolean D;
    private int E = 0;
    private boolean F;
    private boolean G = true;
    private boolean H;

    /* renamed from: a  reason: collision with root package name */
    Context f867a;

    /* renamed from: b  reason: collision with root package name */
    ActionBarOverlayLayout f868b;

    /* renamed from: c  reason: collision with root package name */
    ActionBarContainer f869c;

    /* renamed from: d  reason: collision with root package name */
    ac f870d;
    ActionBarContextView e;
    View f;
    an g;
    a h;
    b i;
    b.a j;
    boolean k = true;
    boolean l;
    boolean m;
    h n;
    boolean o;
    final z p = new aa() {
        /* class android.support.v7.app.l.AnonymousClass1 */

        @Override // android.support.v4.g.aa, android.support.v4.g.z
        public void b(View view) {
            if (l.this.k && l.this.f != null) {
                l.this.f.setTranslationY(BitmapDescriptorFactory.HUE_RED);
                l.this.f869c.setTranslationY(BitmapDescriptorFactory.HUE_RED);
            }
            l.this.f869c.setVisibility(8);
            l.this.f869c.setTransitioning(false);
            l lVar = l.this;
            lVar.n = null;
            lVar.h();
            if (l.this.f868b != null) {
                t.m(l.this.f868b);
            }
        }
    };
    final z q = new aa() {
        /* class android.support.v7.app.l.AnonymousClass2 */

        @Override // android.support.v4.g.aa, android.support.v4.g.z
        public void b(View view) {
            l lVar = l.this;
            lVar.n = null;
            lVar.f869c.requestLayout();
        }
    };
    final ab r = new ab() {
        /* class android.support.v7.app.l.AnonymousClass3 */

        @Override // android.support.v4.g.ab
        public void a(View view) {
            ((View) l.this.f869c.getParent()).invalidate();
        }
    };
    private Context v;
    private Activity w;
    private Dialog x;
    private ArrayList<Object> y = new ArrayList<>();
    private int z = -1;

    static boolean a(boolean z2, boolean z3, boolean z4) {
        if (z4) {
            return true;
        }
        return !z2 && !z3;
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.a
    public void m() {
    }

    public l(Activity activity, boolean z2) {
        this.w = activity;
        View decorView = activity.getWindow().getDecorView();
        a(decorView);
        if (!z2) {
            this.f = decorView.findViewById(16908290);
        }
    }

    public l(Dialog dialog) {
        this.x = dialog;
        a(dialog.getWindow().getDecorView());
    }

    private void a(View view) {
        this.f868b = (ActionBarOverlayLayout) view.findViewById(a.f.decor_content_parent);
        ActionBarOverlayLayout actionBarOverlayLayout = this.f868b;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.f870d = b(view.findViewById(a.f.action_bar));
        this.e = (ActionBarContextView) view.findViewById(a.f.action_context_bar);
        this.f869c = (ActionBarContainer) view.findViewById(a.f.action_bar_container);
        ac acVar = this.f870d;
        if (acVar == null || this.e == null || this.f869c == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with a compatible window decor layout");
        }
        this.f867a = acVar.b();
        boolean z2 = (this.f870d.o() & 4) != 0;
        if (z2) {
            this.A = true;
        }
        android.support.v7.view.a a2 = android.support.v7.view.a.a(this.f867a);
        a(a2.f() || z2);
        k(a2.d());
        TypedArray obtainStyledAttributes = this.f867a.obtainStyledAttributes(null, a.j.ActionBar, a.C0020a.actionBarStyle, 0);
        if (obtainStyledAttributes.getBoolean(a.j.ActionBar_hideOnContentScroll, false)) {
            b(true);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(a.j.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            a((float) dimensionPixelSize);
        }
        obtainStyledAttributes.recycle();
    }

    private ac b(View view) {
        if (view instanceof ac) {
            return (ac) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't make a decor toolbar out of ");
        sb.append(view != null ? view.getClass().getSimpleName() : "null");
        throw new IllegalStateException(sb.toString());
    }

    @Override // android.support.v7.app.a
    public void a(float f2) {
        t.a(this.f869c, f2);
    }

    @Override // android.support.v7.app.a
    public void a(Configuration configuration) {
        k(android.support.v7.view.a.a(this.f867a).d());
    }

    private void k(boolean z2) {
        this.D = z2;
        if (!this.D) {
            this.f870d.a((an) null);
            this.f869c.setTabContainer(this.g);
        } else {
            this.f869c.setTabContainer(null);
            this.f870d.a(this.g);
        }
        boolean z3 = true;
        boolean z4 = i() == 2;
        an anVar = this.g;
        if (anVar != null) {
            if (z4) {
                anVar.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.f868b;
                if (actionBarOverlayLayout != null) {
                    t.m(actionBarOverlayLayout);
                }
            } else {
                anVar.setVisibility(8);
            }
        }
        this.f870d.a(!this.D && z4);
        ActionBarOverlayLayout actionBarOverlayLayout2 = this.f868b;
        if (this.D || !z4) {
            z3 = false;
        }
        actionBarOverlayLayout2.setHasNonEmbeddedTabs(z3);
    }

    /* access modifiers changed from: package-private */
    public void h() {
        b.a aVar = this.j;
        if (aVar != null) {
            aVar.a(this.i);
            this.i = null;
            this.j = null;
        }
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.a
    public void a(int i2) {
        this.E = i2;
    }

    @Override // android.support.v7.app.a
    public void d(boolean z2) {
        h hVar;
        this.H = z2;
        if (!z2 && (hVar = this.n) != null) {
            hVar.c();
        }
    }

    @Override // android.support.v7.app.a
    public void e(boolean z2) {
        if (z2 != this.B) {
            this.B = z2;
            int size = this.C.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.C.get(i2).a(z2);
            }
        }
    }

    public void f(boolean z2) {
        a(z2 ? 4 : 0, 4);
    }

    @Override // android.support.v7.app.a
    public void a(boolean z2) {
        this.f870d.b(z2);
    }

    @Override // android.support.v7.app.a
    public void a(CharSequence charSequence) {
        this.f870d.a(charSequence);
    }

    public void a(int i2, int i3) {
        int o2 = this.f870d.o();
        if ((i3 & 4) != 0) {
            this.A = true;
        }
        this.f870d.c((i2 & i3) | ((~i3) & o2));
    }

    public int i() {
        return this.f870d.p();
    }

    @Override // android.support.v7.app.a
    public int a() {
        return this.f870d.o();
    }

    @Override // android.support.v7.app.a
    public b a(b.a aVar) {
        a aVar2 = this.h;
        if (aVar2 != null) {
            aVar2.c();
        }
        this.f868b.setHideOnContentScrollEnabled(false);
        this.e.c();
        a aVar3 = new a(this.e.getContext(), aVar);
        if (!aVar3.e()) {
            return null;
        }
        this.h = aVar3;
        aVar3.d();
        this.e.a(aVar3);
        j(true);
        this.e.sendAccessibilityEvent(32);
        return aVar3;
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.a
    public void g(boolean z2) {
        this.k = z2;
    }

    private void n() {
        if (!this.F) {
            this.F = true;
            ActionBarOverlayLayout actionBarOverlayLayout = this.f868b;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(true);
            }
            l(false);
        }
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.a
    public void j() {
        if (this.m) {
            this.m = false;
            l(true);
        }
    }

    private void o() {
        if (this.F) {
            this.F = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.f868b;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            l(false);
        }
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.a
    public void k() {
        if (!this.m) {
            this.m = true;
            l(true);
        }
    }

    @Override // android.support.v7.app.a
    public void b(boolean z2) {
        if (!z2 || this.f868b.a()) {
            this.o = z2;
            this.f868b.setHideOnContentScrollEnabled(z2);
            return;
        }
        throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
    }

    private void l(boolean z2) {
        if (a(this.l, this.m, this.F)) {
            if (!this.G) {
                this.G = true;
                h(z2);
            }
        } else if (this.G) {
            this.G = false;
            i(z2);
        }
    }

    public void h(boolean z2) {
        View view;
        View view2;
        h hVar = this.n;
        if (hVar != null) {
            hVar.c();
        }
        this.f869c.setVisibility(0);
        if (this.E != 0 || (!this.H && !z2)) {
            this.f869c.setAlpha(1.0f);
            this.f869c.setTranslationY(BitmapDescriptorFactory.HUE_RED);
            if (this.k && (view = this.f) != null) {
                view.setTranslationY(BitmapDescriptorFactory.HUE_RED);
            }
            this.q.b(null);
        } else {
            this.f869c.setTranslationY(BitmapDescriptorFactory.HUE_RED);
            float f2 = (float) (-this.f869c.getHeight());
            if (z2) {
                int[] iArr = {0, 0};
                this.f869c.getLocationInWindow(iArr);
                f2 -= (float) iArr[1];
            }
            this.f869c.setTranslationY(f2);
            h hVar2 = new h();
            y b2 = t.i(this.f869c).b(BitmapDescriptorFactory.HUE_RED);
            b2.a(this.r);
            hVar2.a(b2);
            if (this.k && (view2 = this.f) != null) {
                view2.setTranslationY(f2);
                hVar2.a(t.i(this.f).b(BitmapDescriptorFactory.HUE_RED));
            }
            hVar2.a(u);
            hVar2.a(250);
            hVar2.a(this.q);
            this.n = hVar2;
            hVar2.a();
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f868b;
        if (actionBarOverlayLayout != null) {
            t.m(actionBarOverlayLayout);
        }
    }

    public void i(boolean z2) {
        View view;
        h hVar = this.n;
        if (hVar != null) {
            hVar.c();
        }
        if (this.E != 0 || (!this.H && !z2)) {
            this.p.b(null);
            return;
        }
        this.f869c.setAlpha(1.0f);
        this.f869c.setTransitioning(true);
        h hVar2 = new h();
        float f2 = (float) (-this.f869c.getHeight());
        if (z2) {
            int[] iArr = {0, 0};
            this.f869c.getLocationInWindow(iArr);
            f2 -= (float) iArr[1];
        }
        y b2 = t.i(this.f869c).b(f2);
        b2.a(this.r);
        hVar2.a(b2);
        if (this.k && (view = this.f) != null) {
            hVar2.a(t.i(view).b(f2));
        }
        hVar2.a(t);
        hVar2.a(250);
        hVar2.a(this.p);
        this.n = hVar2;
        hVar2.a();
    }

    public void j(boolean z2) {
        y yVar;
        y yVar2;
        if (z2) {
            n();
        } else {
            o();
        }
        if (p()) {
            if (z2) {
                yVar = this.f870d.a(4, 100);
                yVar2 = this.e.a(0, 200);
            } else {
                yVar2 = this.f870d.a(0, 200);
                yVar = this.e.a(8, 100);
            }
            h hVar = new h();
            hVar.a(yVar, yVar2);
            hVar.a();
        } else if (z2) {
            this.f870d.d(4);
            this.e.setVisibility(0);
        } else {
            this.f870d.d(0);
            this.e.setVisibility(8);
        }
    }

    private boolean p() {
        return t.t(this.f869c);
    }

    @Override // android.support.v7.app.a
    public Context b() {
        if (this.v == null) {
            TypedValue typedValue = new TypedValue();
            this.f867a.getTheme().resolveAttribute(a.C0020a.actionBarWidgetTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                this.v = new ContextThemeWrapper(this.f867a, i2);
            } else {
                this.v = this.f867a;
            }
        }
        return this.v;
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.a
    public void l() {
        h hVar = this.n;
        if (hVar != null) {
            hVar.c();
            this.n = null;
        }
    }

    @Override // android.support.v7.app.a
    public boolean f() {
        ac acVar = this.f870d;
        if (acVar == null || !acVar.c()) {
            return false;
        }
        this.f870d.d();
        return true;
    }

    /* compiled from: WindowDecorActionBar */
    public class a extends b implements h.a {

        /* renamed from: b  reason: collision with root package name */
        private final Context f875b;

        /* renamed from: c  reason: collision with root package name */
        private final android.support.v7.view.menu.h f876c;

        /* renamed from: d  reason: collision with root package name */
        private b.a f877d;
        private WeakReference<View> e;

        public a(Context context, b.a aVar) {
            this.f875b = context;
            this.f877d = aVar;
            this.f876c = new android.support.v7.view.menu.h(context).a(1);
            this.f876c.a(this);
        }

        @Override // android.support.v7.view.b
        public MenuInflater a() {
            return new g(this.f875b);
        }

        @Override // android.support.v7.view.b
        public Menu b() {
            return this.f876c;
        }

        @Override // android.support.v7.view.b
        public void c() {
            if (l.this.h == this) {
                if (!l.a(l.this.l, l.this.m, false)) {
                    l lVar = l.this;
                    lVar.i = this;
                    lVar.j = this.f877d;
                } else {
                    this.f877d.a(this);
                }
                this.f877d = null;
                l.this.j(false);
                l.this.e.b();
                l.this.f870d.a().sendAccessibilityEvent(32);
                l.this.f868b.setHideOnContentScrollEnabled(l.this.o);
                l.this.h = null;
            }
        }

        @Override // android.support.v7.view.b
        public void d() {
            if (l.this.h == this) {
                this.f876c.h();
                try {
                    this.f877d.b(this, this.f876c);
                } finally {
                    this.f876c.i();
                }
            }
        }

        public boolean e() {
            this.f876c.h();
            try {
                return this.f877d.a(this, this.f876c);
            } finally {
                this.f876c.i();
            }
        }

        @Override // android.support.v7.view.b
        public void a(View view) {
            l.this.e.setCustomView(view);
            this.e = new WeakReference<>(view);
        }

        @Override // android.support.v7.view.b
        public void a(CharSequence charSequence) {
            l.this.e.setSubtitle(charSequence);
        }

        @Override // android.support.v7.view.b
        public void b(CharSequence charSequence) {
            l.this.e.setTitle(charSequence);
        }

        @Override // android.support.v7.view.b
        public void a(int i) {
            b(l.this.f867a.getResources().getString(i));
        }

        @Override // android.support.v7.view.b
        public void b(int i) {
            a((CharSequence) l.this.f867a.getResources().getString(i));
        }

        @Override // android.support.v7.view.b
        public CharSequence f() {
            return l.this.e.getTitle();
        }

        @Override // android.support.v7.view.b
        public CharSequence g() {
            return l.this.e.getSubtitle();
        }

        @Override // android.support.v7.view.b
        public void a(boolean z) {
            super.a(z);
            l.this.e.setTitleOptional(z);
        }

        @Override // android.support.v7.view.b
        public boolean h() {
            return l.this.e.d();
        }

        @Override // android.support.v7.view.b
        public View i() {
            WeakReference<View> weakReference = this.e;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // android.support.v7.view.menu.h.a
        public boolean a(android.support.v7.view.menu.h hVar, MenuItem menuItem) {
            b.a aVar = this.f877d;
            if (aVar != null) {
                return aVar.a(this, menuItem);
            }
            return false;
        }

        @Override // android.support.v7.view.menu.h.a
        public void a(android.support.v7.view.menu.h hVar) {
            if (this.f877d != null) {
                d();
                l.this.e.a();
            }
        }
    }

    @Override // android.support.v7.app.a
    public void c(boolean z2) {
        if (!this.A) {
            f(z2);
        }
    }

    @Override // android.support.v7.app.a
    public boolean a(int i2, KeyEvent keyEvent) {
        Menu b2;
        a aVar = this.h;
        if (aVar == null || (b2 = aVar.b()) == null) {
            return false;
        }
        boolean z2 = true;
        if (KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() == 1) {
            z2 = false;
        }
        b2.setQwertyMode(z2);
        return b2.performShortcut(i2, keyEvent, 0);
    }
}
