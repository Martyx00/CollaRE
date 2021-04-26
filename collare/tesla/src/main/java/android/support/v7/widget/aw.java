package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.g.aa;
import android.support.v4.g.t;
import android.support.v4.g.y;
import android.support.v7.a.a;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.o;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: ToolbarWidgetWrapper */
public class aw implements ac {

    /* renamed from: a  reason: collision with root package name */
    Toolbar f1196a;

    /* renamed from: b  reason: collision with root package name */
    CharSequence f1197b;

    /* renamed from: c  reason: collision with root package name */
    Window.Callback f1198c;

    /* renamed from: d  reason: collision with root package name */
    boolean f1199d;
    private int e;
    private View f;
    private View g;
    private Drawable h;
    private Drawable i;
    private Drawable j;
    private boolean k;
    private CharSequence l;
    private CharSequence m;
    private c n;
    private int o;
    private int p;
    private Drawable q;

    @Override // android.support.v7.widget.ac
    public void b(boolean z) {
    }

    public aw(Toolbar toolbar, boolean z) {
        this(toolbar, z, a.h.abc_action_bar_up_description, a.e.abc_ic_ab_back_material);
    }

    public aw(Toolbar toolbar, boolean z, int i2, int i3) {
        Drawable drawable;
        this.o = 0;
        this.p = 0;
        this.f1196a = toolbar;
        this.f1197b = toolbar.getTitle();
        this.l = toolbar.getSubtitle();
        this.k = this.f1197b != null;
        this.j = toolbar.getNavigationIcon();
        av a2 = av.a(toolbar.getContext(), null, a.j.ActionBar, a.C0020a.actionBarStyle, 0);
        this.q = a2.a(a.j.ActionBar_homeAsUpIndicator);
        if (z) {
            CharSequence c2 = a2.c(a.j.ActionBar_title);
            if (!TextUtils.isEmpty(c2)) {
                b(c2);
            }
            CharSequence c3 = a2.c(a.j.ActionBar_subtitle);
            if (!TextUtils.isEmpty(c3)) {
                c(c3);
            }
            Drawable a3 = a2.a(a.j.ActionBar_logo);
            if (a3 != null) {
                b(a3);
            }
            Drawable a4 = a2.a(a.j.ActionBar_icon);
            if (a4 != null) {
                a(a4);
            }
            if (this.j == null && (drawable = this.q) != null) {
                c(drawable);
            }
            c(a2.a(a.j.ActionBar_displayOptions, 0));
            int g2 = a2.g(a.j.ActionBar_customNavigationLayout, 0);
            if (g2 != 0) {
                a(LayoutInflater.from(this.f1196a.getContext()).inflate(g2, (ViewGroup) this.f1196a, false));
                c(this.e | 16);
            }
            int f2 = a2.f(a.j.ActionBar_height, 0);
            if (f2 > 0) {
                ViewGroup.LayoutParams layoutParams = this.f1196a.getLayoutParams();
                layoutParams.height = f2;
                this.f1196a.setLayoutParams(layoutParams);
            }
            int d2 = a2.d(a.j.ActionBar_contentInsetStart, -1);
            int d3 = a2.d(a.j.ActionBar_contentInsetEnd, -1);
            if (d2 >= 0 || d3 >= 0) {
                this.f1196a.a(Math.max(d2, 0), Math.max(d3, 0));
            }
            int g3 = a2.g(a.j.ActionBar_titleTextStyle, 0);
            if (g3 != 0) {
                Toolbar toolbar2 = this.f1196a;
                toolbar2.a(toolbar2.getContext(), g3);
            }
            int g4 = a2.g(a.j.ActionBar_subtitleTextStyle, 0);
            if (g4 != 0) {
                Toolbar toolbar3 = this.f1196a;
                toolbar3.b(toolbar3.getContext(), g4);
            }
            int g5 = a2.g(a.j.ActionBar_popupTheme, 0);
            if (g5 != 0) {
                this.f1196a.setPopupTheme(g5);
            }
        } else {
            this.e = r();
        }
        a2.a();
        e(i2);
        this.m = this.f1196a.getNavigationContentDescription();
        this.f1196a.setNavigationOnClickListener(new View.OnClickListener() {
            /* class android.support.v7.widget.aw.AnonymousClass1 */

            /* renamed from: a  reason: collision with root package name */
            final android.support.v7.view.menu.a f1200a = new android.support.v7.view.menu.a(aw.this.f1196a.getContext(), 0, 16908332, 0, 0, aw.this.f1197b);

            public void onClick(View view) {
                if (aw.this.f1198c != null && aw.this.f1199d) {
                    aw.this.f1198c.onMenuItemSelected(0, this.f1200a);
                }
            }
        });
    }

    public void e(int i2) {
        if (i2 != this.p) {
            this.p = i2;
            if (TextUtils.isEmpty(this.f1196a.getNavigationContentDescription())) {
                f(this.p);
            }
        }
    }

    private int r() {
        if (this.f1196a.getNavigationIcon() == null) {
            return 11;
        }
        this.q = this.f1196a.getNavigationIcon();
        return 15;
    }

    @Override // android.support.v7.widget.ac
    public ViewGroup a() {
        return this.f1196a;
    }

    @Override // android.support.v7.widget.ac
    public Context b() {
        return this.f1196a.getContext();
    }

    @Override // android.support.v7.widget.ac
    public boolean c() {
        return this.f1196a.g();
    }

    @Override // android.support.v7.widget.ac
    public void d() {
        this.f1196a.h();
    }

    @Override // android.support.v7.widget.ac
    public void a(Window.Callback callback) {
        this.f1198c = callback;
    }

    @Override // android.support.v7.widget.ac
    public void a(CharSequence charSequence) {
        if (!this.k) {
            e(charSequence);
        }
    }

    @Override // android.support.v7.widget.ac
    public CharSequence e() {
        return this.f1196a.getTitle();
    }

    public void b(CharSequence charSequence) {
        this.k = true;
        e(charSequence);
    }

    private void e(CharSequence charSequence) {
        this.f1197b = charSequence;
        if ((this.e & 8) != 0) {
            this.f1196a.setTitle(charSequence);
        }
    }

    public void c(CharSequence charSequence) {
        this.l = charSequence;
        if ((this.e & 8) != 0) {
            this.f1196a.setSubtitle(charSequence);
        }
    }

    @Override // android.support.v7.widget.ac
    public void f() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // android.support.v7.widget.ac
    public void g() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // android.support.v7.widget.ac
    public void a(int i2) {
        a(i2 != 0 ? android.support.v7.b.a.a.b(b(), i2) : null);
    }

    @Override // android.support.v7.widget.ac
    public void a(Drawable drawable) {
        this.h = drawable;
        s();
    }

    @Override // android.support.v7.widget.ac
    public void b(int i2) {
        b(i2 != 0 ? android.support.v7.b.a.a.b(b(), i2) : null);
    }

    public void b(Drawable drawable) {
        this.i = drawable;
        s();
    }

    private void s() {
        Drawable drawable;
        int i2 = this.e;
        if ((i2 & 2) == 0) {
            drawable = null;
        } else if ((i2 & 1) != 0) {
            drawable = this.i;
            if (drawable == null) {
                drawable = this.h;
            }
        } else {
            drawable = this.h;
        }
        this.f1196a.setLogo(drawable);
    }

    @Override // android.support.v7.widget.ac
    public boolean h() {
        return this.f1196a.a();
    }

    @Override // android.support.v7.widget.ac
    public boolean i() {
        return this.f1196a.b();
    }

    @Override // android.support.v7.widget.ac
    public boolean j() {
        return this.f1196a.c();
    }

    @Override // android.support.v7.widget.ac
    public boolean k() {
        return this.f1196a.d();
    }

    @Override // android.support.v7.widget.ac
    public boolean l() {
        return this.f1196a.e();
    }

    @Override // android.support.v7.widget.ac
    public void m() {
        this.f1199d = true;
    }

    @Override // android.support.v7.widget.ac
    public void a(Menu menu, o.a aVar) {
        if (this.n == null) {
            this.n = new c(this.f1196a.getContext());
            this.n.a(a.f.action_menu_presenter);
        }
        this.n.a(aVar);
        this.f1196a.a((h) menu, this.n);
    }

    @Override // android.support.v7.widget.ac
    public void n() {
        this.f1196a.f();
    }

    @Override // android.support.v7.widget.ac
    public int o() {
        return this.e;
    }

    @Override // android.support.v7.widget.ac
    public void c(int i2) {
        View view;
        int i3 = this.e ^ i2;
        this.e = i2;
        if (i3 != 0) {
            if ((i3 & 4) != 0) {
                if ((i2 & 4) != 0) {
                    u();
                }
                t();
            }
            if ((i3 & 3) != 0) {
                s();
            }
            if ((i3 & 8) != 0) {
                if ((i2 & 8) != 0) {
                    this.f1196a.setTitle(this.f1197b);
                    this.f1196a.setSubtitle(this.l);
                } else {
                    this.f1196a.setTitle((CharSequence) null);
                    this.f1196a.setSubtitle((CharSequence) null);
                }
            }
            if ((i3 & 16) != 0 && (view = this.g) != null) {
                if ((i2 & 16) != 0) {
                    this.f1196a.addView(view);
                } else {
                    this.f1196a.removeView(view);
                }
            }
        }
    }

    @Override // android.support.v7.widget.ac
    public void a(an anVar) {
        Toolbar toolbar;
        View view = this.f;
        if (view != null && view.getParent() == (toolbar = this.f1196a)) {
            toolbar.removeView(this.f);
        }
        this.f = anVar;
        if (anVar != null && this.o == 2) {
            this.f1196a.addView(this.f, 0);
            Toolbar.b bVar = (Toolbar.b) this.f.getLayoutParams();
            bVar.width = -2;
            bVar.height = -2;
            bVar.f808a = 8388691;
            anVar.setAllowCollapse(true);
        }
    }

    @Override // android.support.v7.widget.ac
    public void a(boolean z) {
        this.f1196a.setCollapsible(z);
    }

    @Override // android.support.v7.widget.ac
    public int p() {
        return this.o;
    }

    public void a(View view) {
        View view2 = this.g;
        if (!(view2 == null || (this.e & 16) == 0)) {
            this.f1196a.removeView(view2);
        }
        this.g = view;
        if (view != null && (this.e & 16) != 0) {
            this.f1196a.addView(this.g);
        }
    }

    @Override // android.support.v7.widget.ac
    public y a(final int i2, long j2) {
        return t.i(this.f1196a).a(i2 == 0 ? 1.0f : BitmapDescriptorFactory.HUE_RED).a(j2).a(new aa() {
            /* class android.support.v7.widget.aw.AnonymousClass2 */

            /* renamed from: c  reason: collision with root package name */
            private boolean f1204c = false;

            @Override // android.support.v4.g.aa, android.support.v4.g.z
            public void a(View view) {
                aw.this.f1196a.setVisibility(0);
            }

            @Override // android.support.v4.g.aa, android.support.v4.g.z
            public void b(View view) {
                if (!this.f1204c) {
                    aw.this.f1196a.setVisibility(i2);
                }
            }

            @Override // android.support.v4.g.aa, android.support.v4.g.z
            public void c(View view) {
                this.f1204c = true;
            }
        });
    }

    public void c(Drawable drawable) {
        this.j = drawable;
        t();
    }

    private void t() {
        if ((this.e & 4) != 0) {
            Toolbar toolbar = this.f1196a;
            Drawable drawable = this.j;
            if (drawable == null) {
                drawable = this.q;
            }
            toolbar.setNavigationIcon(drawable);
            return;
        }
        this.f1196a.setNavigationIcon((Drawable) null);
    }

    public void d(CharSequence charSequence) {
        this.m = charSequence;
        u();
    }

    public void f(int i2) {
        d(i2 == 0 ? null : b().getString(i2));
    }

    private void u() {
        if ((this.e & 4) == 0) {
            return;
        }
        if (TextUtils.isEmpty(this.m)) {
            this.f1196a.setNavigationContentDescription(this.p);
        } else {
            this.f1196a.setNavigationContentDescription(this.m);
        }
    }

    @Override // android.support.v7.widget.ac
    public void d(int i2) {
        this.f1196a.setVisibility(i2);
    }

    @Override // android.support.v7.widget.ac
    public void a(o.a aVar, h.a aVar2) {
        this.f1196a.a(aVar, aVar2);
    }

    @Override // android.support.v7.widget.ac
    public Menu q() {
        return this.f1196a.getMenu();
    }
}
