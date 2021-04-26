package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.w;
import android.support.v4.g.aa;
import android.support.v4.g.ac;
import android.support.v4.g.f;
import android.support.v4.g.q;
import android.support.v4.g.t;
import android.support.v4.g.y;
import android.support.v4.g.z;
import android.support.v7.a.a;
import android.support.v7.view.b;
import android.support.v7.view.f;
import android.support.v7.view.i;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.o;
import android.support.v7.view.menu.p;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.ab;
import android.support.v7.widget.af;
import android.support.v7.widget.av;
import android.support.v7.widget.ba;
import android.support.v7.widget.bb;
import android.support.v7.widget.k;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.Thread;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* access modifiers changed from: package-private */
/* compiled from: AppCompatDelegateImpl */
public class f extends e implements h.a, LayoutInflater.Factory2 {
    private static final boolean u = (Build.VERSION.SDK_INT < 21);
    private static final int[] v = {16842836};
    private static boolean w = true;
    private g A;
    private boolean B = true;
    private boolean C;
    private ViewGroup D;
    private TextView E;
    private View F;
    private boolean G;
    private boolean H;
    private boolean I;
    private C0023f[] J;
    private C0023f K;
    private boolean L;
    private int M = -100;
    private boolean N;
    private d O;
    private final Runnable P = new Runnable() {
        /* class android.support.v7.app.f.AnonymousClass2 */

        public void run() {
            if ((f.this.t & 1) != 0) {
                f.this.g(0);
            }
            if ((f.this.t & 4096) != 0) {
                f.this.g(108);
            }
            f fVar = f.this;
            fVar.s = false;
            fVar.t = 0;
        }
    };
    private boolean Q;
    private Rect R;
    private Rect S;
    private AppCompatViewInflater T;

    /* renamed from: a  reason: collision with root package name */
    final Context f813a;

    /* renamed from: b  reason: collision with root package name */
    final Window f814b;

    /* renamed from: c  reason: collision with root package name */
    final Window.Callback f815c;

    /* renamed from: d  reason: collision with root package name */
    final Window.Callback f816d;
    final d e;
    a f;
    MenuInflater g;
    android.support.v7.view.b h;
    ActionBarContextView i;
    PopupWindow j;
    Runnable k;
    y l = null;
    boolean m;
    boolean n;
    boolean o;
    boolean p;
    boolean q;
    boolean r;
    boolean s;
    int t;
    private CharSequence x;
    private ab y;
    private a z;

    /* access modifiers changed from: package-private */
    public void a(ViewGroup viewGroup) {
    }

    static {
        if (u && !w) {
            final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                /* class android.support.v7.app.f.AnonymousClass1 */

                public void uncaughtException(Thread thread, Throwable th) {
                    if (a(th)) {
                        Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
                        notFoundException.initCause(th.getCause());
                        notFoundException.setStackTrace(th.getStackTrace());
                        defaultUncaughtExceptionHandler.uncaughtException(thread, notFoundException);
                        return;
                    }
                    defaultUncaughtExceptionHandler.uncaughtException(thread, th);
                }

                private boolean a(Throwable th) {
                    String message;
                    if (!(th instanceof Resources.NotFoundException) || (message = th.getMessage()) == null) {
                        return false;
                    }
                    if (message.contains("drawable") || message.contains("Drawable")) {
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    f(Context context, Window window, d dVar) {
        this.f813a = context;
        this.f814b = window;
        this.e = dVar;
        this.f815c = this.f814b.getCallback();
        Window.Callback callback = this.f815c;
        if (!(callback instanceof c)) {
            this.f816d = new c(callback);
            this.f814b.setCallback(this.f816d);
            av a2 = av.a(context, (AttributeSet) null, v);
            Drawable b2 = a2.b(0);
            if (b2 != null) {
                this.f814b.setBackgroundDrawable(b2);
            }
            a2.a();
            return;
        }
        throw new IllegalStateException("AppCompat has already installed itself into the Window");
    }

    @Override // android.support.v7.app.e
    public void a(Bundle bundle) {
        Window.Callback callback = this.f815c;
        if (callback instanceof Activity) {
            String str = null;
            try {
                str = w.b((Activity) callback);
            } catch (IllegalArgumentException unused) {
            }
            if (str != null) {
                a k2 = k();
                if (k2 == null) {
                    this.Q = true;
                } else {
                    k2.c(true);
                }
            }
        }
        if (bundle != null && this.M == -100) {
            this.M = bundle.getInt("appcompat:local_night_mode", -100);
        }
    }

    @Override // android.support.v7.app.e
    public void b(Bundle bundle) {
        u();
    }

    @Override // android.support.v7.app.e
    public a a() {
        t();
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public final a k() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public final Window.Callback l() {
        return this.f814b.getCallback();
    }

    private void t() {
        u();
        if (this.m && this.f == null) {
            Window.Callback callback = this.f815c;
            if (callback instanceof Activity) {
                this.f = new l((Activity) callback, this.n);
            } else if (callback instanceof Dialog) {
                this.f = new l((Dialog) callback);
            }
            a aVar = this.f;
            if (aVar != null) {
                aVar.c(this.Q);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final Context m() {
        a a2 = a();
        Context b2 = a2 != null ? a2.b() : null;
        return b2 == null ? this.f813a : b2;
    }

    @Override // android.support.v7.app.e
    public MenuInflater b() {
        if (this.g == null) {
            t();
            a aVar = this.f;
            this.g = new android.support.v7.view.g(aVar != null ? aVar.b() : this.f813a);
        }
        return this.g;
    }

    @Override // android.support.v7.app.e
    public <T extends View> T a(int i2) {
        u();
        return (T) this.f814b.findViewById(i2);
    }

    @Override // android.support.v7.app.e
    public void a(Configuration configuration) {
        a a2;
        if (this.m && this.C && (a2 = a()) != null) {
            a2.a(configuration);
        }
        k.a().a(this.f813a);
        i();
    }

    @Override // android.support.v7.app.e
    public void c() {
        i();
    }

    @Override // android.support.v7.app.e
    public void d() {
        a a2 = a();
        if (a2 != null) {
            a2.d(false);
        }
        d dVar = this.O;
        if (dVar != null) {
            dVar.d();
        }
    }

    @Override // android.support.v7.app.e
    public void e() {
        a a2 = a();
        if (a2 != null) {
            a2.d(true);
        }
    }

    @Override // android.support.v7.app.e
    public void a(View view) {
        u();
        ViewGroup viewGroup = (ViewGroup) this.D.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.f815c.onContentChanged();
    }

    @Override // android.support.v7.app.e
    public void b(int i2) {
        u();
        ViewGroup viewGroup = (ViewGroup) this.D.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.f813a).inflate(i2, viewGroup);
        this.f815c.onContentChanged();
    }

    @Override // android.support.v7.app.e
    public void a(View view, ViewGroup.LayoutParams layoutParams) {
        u();
        ViewGroup viewGroup = (ViewGroup) this.D.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.f815c.onContentChanged();
    }

    @Override // android.support.v7.app.e
    public void b(View view, ViewGroup.LayoutParams layoutParams) {
        u();
        ((ViewGroup) this.D.findViewById(16908290)).addView(view, layoutParams);
        this.f815c.onContentChanged();
    }

    @Override // android.support.v7.app.e
    public void c(Bundle bundle) {
        int i2 = this.M;
        if (i2 != -100) {
            bundle.putInt("appcompat:local_night_mode", i2);
        }
    }

    @Override // android.support.v7.app.e
    public void g() {
        if (this.s) {
            this.f814b.getDecorView().removeCallbacks(this.P);
        }
        this.r = true;
        a aVar = this.f;
        if (aVar != null) {
            aVar.g();
        }
        d dVar = this.O;
        if (dVar != null) {
            dVar.d();
        }
    }

    private void u() {
        if (!this.C) {
            this.D = v();
            CharSequence n2 = n();
            if (!TextUtils.isEmpty(n2)) {
                ab abVar = this.y;
                if (abVar != null) {
                    abVar.setWindowTitle(n2);
                } else if (k() != null) {
                    k().a(n2);
                } else {
                    TextView textView = this.E;
                    if (textView != null) {
                        textView.setText(n2);
                    }
                }
            }
            w();
            a(this.D);
            this.C = true;
            C0023f a2 = a(0, false);
            if (this.r) {
                return;
            }
            if (a2 == null || a2.j == null) {
                j(108);
            }
        }
    }

    private ViewGroup v() {
        ViewGroup viewGroup;
        Context context;
        TypedArray obtainStyledAttributes = this.f813a.obtainStyledAttributes(a.j.AppCompatTheme);
        if (obtainStyledAttributes.hasValue(a.j.AppCompatTheme_windowActionBar)) {
            if (obtainStyledAttributes.getBoolean(a.j.AppCompatTheme_windowNoTitle, false)) {
                c(1);
            } else if (obtainStyledAttributes.getBoolean(a.j.AppCompatTheme_windowActionBar, false)) {
                c(108);
            }
            if (obtainStyledAttributes.getBoolean(a.j.AppCompatTheme_windowActionBarOverlay, false)) {
                c(109);
            }
            if (obtainStyledAttributes.getBoolean(a.j.AppCompatTheme_windowActionModeOverlay, false)) {
                c(10);
            }
            this.p = obtainStyledAttributes.getBoolean(a.j.AppCompatTheme_android_windowIsFloating, false);
            obtainStyledAttributes.recycle();
            this.f814b.getDecorView();
            LayoutInflater from = LayoutInflater.from(this.f813a);
            if (this.q) {
                if (this.o) {
                    viewGroup = (ViewGroup) from.inflate(a.g.abc_screen_simple_overlay_action_mode, (ViewGroup) null);
                } else {
                    viewGroup = (ViewGroup) from.inflate(a.g.abc_screen_simple, (ViewGroup) null);
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    t.a(viewGroup, new q() {
                        /* class android.support.v7.app.f.AnonymousClass3 */

                        @Override // android.support.v4.g.q
                        public ac a(View view, ac acVar) {
                            int b2 = acVar.b();
                            int h = f.this.h(b2);
                            if (b2 != h) {
                                acVar = acVar.a(acVar.a(), h, acVar.c(), acVar.d());
                            }
                            return t.a(view, acVar);
                        }
                    });
                } else {
                    ((af) viewGroup).setOnFitSystemWindowsListener(new af.a() {
                        /* class android.support.v7.app.f.AnonymousClass4 */

                        @Override // android.support.v7.widget.af.a
                        public void a(Rect rect) {
                            rect.top = f.this.h(rect.top);
                        }
                    });
                }
            } else if (this.p) {
                viewGroup = (ViewGroup) from.inflate(a.g.abc_dialog_title_material, (ViewGroup) null);
                this.n = false;
                this.m = false;
            } else if (this.m) {
                TypedValue typedValue = new TypedValue();
                this.f813a.getTheme().resolveAttribute(a.C0020a.actionBarTheme, typedValue, true);
                if (typedValue.resourceId != 0) {
                    context = new android.support.v7.view.d(this.f813a, typedValue.resourceId);
                } else {
                    context = this.f813a;
                }
                viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(a.g.abc_screen_toolbar, (ViewGroup) null);
                this.y = (ab) viewGroup.findViewById(a.f.decor_content_parent);
                this.y.setWindowCallback(l());
                if (this.n) {
                    this.y.a(109);
                }
                if (this.G) {
                    this.y.a(2);
                }
                if (this.H) {
                    this.y.a(5);
                }
            } else {
                viewGroup = null;
            }
            if (viewGroup != null) {
                if (this.y == null) {
                    this.E = (TextView) viewGroup.findViewById(a.f.title);
                }
                bb.b(viewGroup);
                ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(a.f.action_bar_activity_content);
                ViewGroup viewGroup2 = (ViewGroup) this.f814b.findViewById(16908290);
                if (viewGroup2 != null) {
                    while (viewGroup2.getChildCount() > 0) {
                        View childAt = viewGroup2.getChildAt(0);
                        viewGroup2.removeViewAt(0);
                        contentFrameLayout.addView(childAt);
                    }
                    viewGroup2.setId(-1);
                    contentFrameLayout.setId(16908290);
                    if (viewGroup2 instanceof FrameLayout) {
                        ((FrameLayout) viewGroup2).setForeground(null);
                    }
                }
                this.f814b.setContentView(viewGroup);
                contentFrameLayout.setAttachListener(new ContentFrameLayout.a() {
                    /* class android.support.v7.app.f.AnonymousClass5 */

                    @Override // android.support.v7.widget.ContentFrameLayout.a
                    public void a() {
                    }

                    @Override // android.support.v7.widget.ContentFrameLayout.a
                    public void b() {
                        f.this.s();
                    }
                });
                return viewGroup;
            }
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.m + ", windowActionBarOverlay: " + this.n + ", android:windowIsFloating: " + this.p + ", windowActionModeOverlay: " + this.o + ", windowNoTitle: " + this.q + " }");
        }
        obtainStyledAttributes.recycle();
        throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
    }

    private void w() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.D.findViewById(16908290);
        View decorView = this.f814b.getDecorView();
        contentFrameLayout.a(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray obtainStyledAttributes = this.f813a.obtainStyledAttributes(a.j.AppCompatTheme);
        obtainStyledAttributes.getValue(a.j.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        obtainStyledAttributes.getValue(a.j.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        if (obtainStyledAttributes.hasValue(a.j.AppCompatTheme_windowFixedWidthMajor)) {
            obtainStyledAttributes.getValue(a.j.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout.getFixedWidthMajor());
        }
        if (obtainStyledAttributes.hasValue(a.j.AppCompatTheme_windowFixedWidthMinor)) {
            obtainStyledAttributes.getValue(a.j.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout.getFixedWidthMinor());
        }
        if (obtainStyledAttributes.hasValue(a.j.AppCompatTheme_windowFixedHeightMajor)) {
            obtainStyledAttributes.getValue(a.j.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout.getFixedHeightMajor());
        }
        if (obtainStyledAttributes.hasValue(a.j.AppCompatTheme_windowFixedHeightMinor)) {
            obtainStyledAttributes.getValue(a.j.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout.getFixedHeightMinor());
        }
        obtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    @Override // android.support.v7.app.e
    public boolean c(int i2) {
        int k2 = k(i2);
        if (this.q && k2 == 108) {
            return false;
        }
        if (this.m && k2 == 1) {
            this.m = false;
        }
        switch (k2) {
            case 1:
                x();
                this.q = true;
                return true;
            case 2:
                x();
                this.G = true;
                return true;
            case 5:
                x();
                this.H = true;
                return true;
            case 10:
                x();
                this.o = true;
                return true;
            case 108:
                x();
                this.m = true;
                return true;
            case 109:
                x();
                this.n = true;
                return true;
            default:
                return this.f814b.requestFeature(k2);
        }
    }

    @Override // android.support.v7.app.e
    public final void a(CharSequence charSequence) {
        this.x = charSequence;
        ab abVar = this.y;
        if (abVar != null) {
            abVar.setWindowTitle(charSequence);
        } else if (k() != null) {
            k().a(charSequence);
        } else {
            TextView textView = this.E;
            if (textView != null) {
                textView.setText(charSequence);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final CharSequence n() {
        Window.Callback callback = this.f815c;
        if (callback instanceof Activity) {
            return ((Activity) callback).getTitle();
        }
        return this.x;
    }

    /* access modifiers changed from: package-private */
    public void d(int i2) {
        if (i2 == 108) {
            a a2 = a();
            if (a2 != null) {
                a2.e(false);
            }
        } else if (i2 == 0) {
            C0023f a3 = a(i2, true);
            if (a3.o) {
                a(a3, false);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void e(int i2) {
        a a2;
        if (i2 == 108 && (a2 = a()) != null) {
            a2.e(true);
        }
    }

    @Override // android.support.v7.view.menu.h.a
    public boolean a(h hVar, MenuItem menuItem) {
        C0023f a2;
        Window.Callback l2 = l();
        if (l2 == null || this.r || (a2 = a((Menu) hVar.q())) == null) {
            return false;
        }
        return l2.onMenuItemSelected(a2.f836a, menuItem);
    }

    @Override // android.support.v7.view.menu.h.a
    public void a(h hVar) {
        a(hVar, true);
    }

    public android.support.v7.view.b a(b.a aVar) {
        d dVar;
        if (aVar != null) {
            android.support.v7.view.b bVar = this.h;
            if (bVar != null) {
                bVar.c();
            }
            b bVar2 = new b(aVar);
            a a2 = a();
            if (a2 != null) {
                this.h = a2.a(bVar2);
                android.support.v7.view.b bVar3 = this.h;
                if (!(bVar3 == null || (dVar = this.e) == null)) {
                    dVar.a(bVar3);
                }
            }
            if (this.h == null) {
                this.h = b(bVar2);
            }
            return this.h;
        }
        throw new IllegalArgumentException("ActionMode callback can not be null.");
    }

    @Override // android.support.v7.app.e
    public void f() {
        a a2 = a();
        if (a2 == null || !a2.e()) {
            j(0);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0029  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.support.v7.view.b b(android.support.v7.view.b.a r8) {
        /*
        // Method dump skipped, instructions count: 372
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.f.b(android.support.v7.view.b$a):android.support.v7.view.b");
    }

    /* access modifiers changed from: package-private */
    public final boolean o() {
        ViewGroup viewGroup;
        return this.C && (viewGroup = this.D) != null && t.t(viewGroup);
    }

    public boolean p() {
        return this.B;
    }

    /* access modifiers changed from: package-private */
    public void q() {
        y yVar = this.l;
        if (yVar != null) {
            yVar.b();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean r() {
        android.support.v7.view.b bVar = this.h;
        if (bVar != null) {
            bVar.c();
            return true;
        }
        a a2 = a();
        if (a2 == null || !a2.f()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i2, KeyEvent keyEvent) {
        a a2 = a();
        if (a2 != null && a2.a(i2, keyEvent)) {
            return true;
        }
        C0023f fVar = this.K;
        if (fVar == null || !a(fVar, keyEvent.getKeyCode(), keyEvent, 1)) {
            if (this.K == null) {
                C0023f a3 = a(0, true);
                b(a3, keyEvent);
                boolean a4 = a(a3, keyEvent.getKeyCode(), keyEvent, 1);
                a3.m = false;
                if (a4) {
                    return true;
                }
            }
            return false;
        }
        C0023f fVar2 = this.K;
        if (fVar2 != null) {
            fVar2.n = true;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean a(KeyEvent keyEvent) {
        View decorView;
        Window.Callback callback = this.f815c;
        boolean z2 = true;
        if (((callback instanceof f.a) || (callback instanceof g)) && (decorView = this.f814b.getDecorView()) != null && android.support.v4.g.f.a(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.f815c.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getAction() != 0) {
            z2 = false;
        }
        return z2 ? c(keyCode, keyEvent) : b(keyCode, keyEvent);
    }

    /* access modifiers changed from: package-private */
    public boolean b(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            boolean z2 = this.L;
            this.L = false;
            C0023f a2 = a(0, false);
            if (a2 != null && a2.o) {
                if (!z2) {
                    a(a2, true);
                }
                return true;
            } else if (r()) {
                return true;
            }
        } else if (i2 == 82) {
            e(0, keyEvent);
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean c(int i2, KeyEvent keyEvent) {
        boolean z2 = true;
        if (i2 == 4) {
            if ((keyEvent.getFlags() & 128) == 0) {
                z2 = false;
            }
            this.L = z2;
        } else if (i2 == 82) {
            d(0, keyEvent);
            return true;
        }
        return false;
    }

    public View a(View view, String str, Context context, AttributeSet attributeSet) {
        boolean z2;
        boolean z3 = false;
        if (this.T == null) {
            String string = this.f813a.obtainStyledAttributes(a.j.AppCompatTheme).getString(a.j.AppCompatTheme_viewInflaterClass);
            if (string == null || AppCompatViewInflater.class.getName().equals(string)) {
                this.T = new AppCompatViewInflater();
            } else {
                try {
                    this.T = (AppCompatViewInflater) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    this.T = new AppCompatViewInflater();
                }
            }
        }
        if (u) {
            if (!(attributeSet instanceof XmlPullParser)) {
                z3 = a((ViewParent) view);
            } else if (((XmlPullParser) attributeSet).getDepth() > 1) {
                z3 = true;
            }
            z2 = z3;
        } else {
            z2 = false;
        }
        return this.T.a(view, str, context, attributeSet, z2, u, true, ba.a());
    }

    private boolean a(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.f814b.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || t.u((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    @Override // android.support.v7.app.e
    public void h() {
        LayoutInflater from = LayoutInflater.from(this.f813a);
        if (from.getFactory() == null) {
            android.support.v4.g.g.a(from, this);
        } else if (!(from.getFactory2() instanceof f)) {
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return a(view, str, context, attributeSet);
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    private void a(C0023f fVar, KeyEvent keyEvent) {
        int i2;
        ViewGroup.LayoutParams layoutParams;
        if (!fVar.o && !this.r) {
            if (fVar.f836a == 0) {
                if ((this.f813a.getResources().getConfiguration().screenLayout & 15) == 4) {
                    return;
                }
            }
            Window.Callback l2 = l();
            if (l2 == null || l2.onMenuOpened(fVar.f836a, fVar.j)) {
                WindowManager windowManager = (WindowManager) this.f813a.getSystemService("window");
                if (windowManager != null && b(fVar, keyEvent)) {
                    if (fVar.g == null || fVar.q) {
                        if (fVar.g == null) {
                            if (!a(fVar) || fVar.g == null) {
                                return;
                            }
                        } else if (fVar.q && fVar.g.getChildCount() > 0) {
                            fVar.g.removeAllViews();
                        }
                        if (c(fVar) && fVar.a()) {
                            ViewGroup.LayoutParams layoutParams2 = fVar.h.getLayoutParams();
                            if (layoutParams2 == null) {
                                layoutParams2 = new ViewGroup.LayoutParams(-2, -2);
                            }
                            fVar.g.setBackgroundResource(fVar.f837b);
                            ViewParent parent = fVar.h.getParent();
                            if (parent != null && (parent instanceof ViewGroup)) {
                                ((ViewGroup) parent).removeView(fVar.h);
                            }
                            fVar.g.addView(fVar.h, layoutParams2);
                            if (!fVar.h.hasFocus()) {
                                fVar.h.requestFocus();
                            }
                        } else {
                            return;
                        }
                    } else if (!(fVar.i == null || (layoutParams = fVar.i.getLayoutParams()) == null || layoutParams.width != -1)) {
                        i2 = -1;
                        fVar.n = false;
                        WindowManager.LayoutParams layoutParams3 = new WindowManager.LayoutParams(i2, -2, fVar.f839d, fVar.e, 1002, 8519680, -3);
                        layoutParams3.gravity = fVar.f838c;
                        layoutParams3.windowAnimations = fVar.f;
                        windowManager.addView(fVar.g, layoutParams3);
                        fVar.o = true;
                        return;
                    }
                    i2 = -2;
                    fVar.n = false;
                    WindowManager.LayoutParams layoutParams32 = new WindowManager.LayoutParams(i2, -2, fVar.f839d, fVar.e, 1002, 8519680, -3);
                    layoutParams32.gravity = fVar.f838c;
                    layoutParams32.windowAnimations = fVar.f;
                    windowManager.addView(fVar.g, layoutParams32);
                    fVar.o = true;
                    return;
                }
                return;
            }
            a(fVar, true);
        }
    }

    private boolean a(C0023f fVar) {
        fVar.a(m());
        fVar.g = new e(fVar.l);
        fVar.f838c = 81;
        return true;
    }

    private void a(h hVar, boolean z2) {
        ab abVar = this.y;
        if (abVar == null || !abVar.e() || (ViewConfiguration.get(this.f813a).hasPermanentMenuKey() && !this.y.g())) {
            C0023f a2 = a(0, true);
            a2.q = true;
            a(a2, false);
            a(a2, (KeyEvent) null);
            return;
        }
        Window.Callback l2 = l();
        if (this.y.f() && z2) {
            this.y.i();
            if (!this.r) {
                l2.onPanelClosed(108, a(0, true).j);
            }
        } else if (l2 != null && !this.r) {
            if (this.s && (this.t & 1) != 0) {
                this.f814b.getDecorView().removeCallbacks(this.P);
                this.P.run();
            }
            C0023f a3 = a(0, true);
            if (a3.j != null && !a3.r && l2.onPreparePanel(0, a3.i, a3.j)) {
                l2.onMenuOpened(108, a3.j);
                this.y.h();
            }
        }
    }

    private boolean b(C0023f fVar) {
        Context context = this.f813a;
        if ((fVar.f836a == 0 || fVar.f836a == 108) && this.y != null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            theme.resolveAttribute(a.C0020a.actionBarTheme, typedValue, true);
            Resources.Theme theme2 = null;
            if (typedValue.resourceId != 0) {
                theme2 = context.getResources().newTheme();
                theme2.setTo(theme);
                theme2.applyStyle(typedValue.resourceId, true);
                theme2.resolveAttribute(a.C0020a.actionBarWidgetTheme, typedValue, true);
            } else {
                theme.resolveAttribute(a.C0020a.actionBarWidgetTheme, typedValue, true);
            }
            if (typedValue.resourceId != 0) {
                if (theme2 == null) {
                    theme2 = context.getResources().newTheme();
                    theme2.setTo(theme);
                }
                theme2.applyStyle(typedValue.resourceId, true);
            }
            if (theme2 != null) {
                android.support.v7.view.d dVar = new android.support.v7.view.d(context, 0);
                dVar.getTheme().setTo(theme2);
                context = dVar;
            }
        }
        h hVar = new h(context);
        hVar.a(this);
        fVar.a(hVar);
        return true;
    }

    private boolean c(C0023f fVar) {
        if (fVar.i != null) {
            fVar.h = fVar.i;
            return true;
        } else if (fVar.j == null) {
            return false;
        } else {
            if (this.A == null) {
                this.A = new g();
            }
            fVar.h = (View) fVar.a(this.A);
            if (fVar.h != null) {
                return true;
            }
            return false;
        }
    }

    private boolean b(C0023f fVar, KeyEvent keyEvent) {
        ab abVar;
        ab abVar2;
        ab abVar3;
        if (this.r) {
            return false;
        }
        if (fVar.m) {
            return true;
        }
        C0023f fVar2 = this.K;
        if (!(fVar2 == null || fVar2 == fVar)) {
            a(fVar2, false);
        }
        Window.Callback l2 = l();
        if (l2 != null) {
            fVar.i = l2.onCreatePanelView(fVar.f836a);
        }
        boolean z2 = fVar.f836a == 0 || fVar.f836a == 108;
        if (z2 && (abVar3 = this.y) != null) {
            abVar3.j();
        }
        if (fVar.i == null && (!z2 || !(k() instanceof i))) {
            if (fVar.j == null || fVar.r) {
                if (fVar.j == null && (!b(fVar) || fVar.j == null)) {
                    return false;
                }
                if (z2 && this.y != null) {
                    if (this.z == null) {
                        this.z = new a();
                    }
                    this.y.a(fVar.j, this.z);
                }
                fVar.j.h();
                if (!l2.onCreatePanelMenu(fVar.f836a, fVar.j)) {
                    fVar.a((h) null);
                    if (z2 && (abVar2 = this.y) != null) {
                        abVar2.a(null, this.z);
                    }
                    return false;
                }
                fVar.r = false;
            }
            fVar.j.h();
            if (fVar.s != null) {
                fVar.j.b(fVar.s);
                fVar.s = null;
            }
            if (!l2.onPreparePanel(0, fVar.i, fVar.j)) {
                if (z2 && (abVar = this.y) != null) {
                    abVar.a(null, this.z);
                }
                fVar.j.i();
                return false;
            }
            fVar.p = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            fVar.j.setQwertyMode(fVar.p);
            fVar.j.i();
        }
        fVar.m = true;
        fVar.n = false;
        this.K = fVar;
        return true;
    }

    /* access modifiers changed from: package-private */
    public void b(h hVar) {
        if (!this.I) {
            this.I = true;
            this.y.k();
            Window.Callback l2 = l();
            if (l2 != null && !this.r) {
                l2.onPanelClosed(108, hVar);
            }
            this.I = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void f(int i2) {
        a(a(i2, true), true);
    }

    /* access modifiers changed from: package-private */
    public void a(C0023f fVar, boolean z2) {
        ab abVar;
        if (!z2 || fVar.f836a != 0 || (abVar = this.y) == null || !abVar.f()) {
            WindowManager windowManager = (WindowManager) this.f813a.getSystemService("window");
            if (!(windowManager == null || !fVar.o || fVar.g == null)) {
                windowManager.removeView(fVar.g);
                if (z2) {
                    a(fVar.f836a, fVar, null);
                }
            }
            fVar.m = false;
            fVar.n = false;
            fVar.o = false;
            fVar.h = null;
            fVar.q = true;
            if (this.K == fVar) {
                this.K = null;
                return;
            }
            return;
        }
        b(fVar.j);
    }

    private boolean d(int i2, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        C0023f a2 = a(i2, true);
        if (!a2.o) {
            return b(a2, keyEvent);
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x006c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean e(int r4, android.view.KeyEvent r5) {
        /*
        // Method dump skipped, instructions count: 132
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.f.e(int, android.view.KeyEvent):boolean");
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, C0023f fVar, Menu menu) {
        if (menu == null) {
            if (fVar == null && i2 >= 0) {
                C0023f[] fVarArr = this.J;
                if (i2 < fVarArr.length) {
                    fVar = fVarArr[i2];
                }
            }
            if (fVar != null) {
                menu = fVar.j;
            }
        }
        if ((fVar == null || fVar.o) && !this.r) {
            this.f815c.onPanelClosed(i2, menu);
        }
    }

    /* access modifiers changed from: package-private */
    public C0023f a(Menu menu) {
        C0023f[] fVarArr = this.J;
        int length = fVarArr != null ? fVarArr.length : 0;
        for (int i2 = 0; i2 < length; i2++) {
            C0023f fVar = fVarArr[i2];
            if (fVar != null && fVar.j == menu) {
                return fVar;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public C0023f a(int i2, boolean z2) {
        C0023f[] fVarArr = this.J;
        if (fVarArr == null || fVarArr.length <= i2) {
            C0023f[] fVarArr2 = new C0023f[(i2 + 1)];
            if (fVarArr != null) {
                System.arraycopy(fVarArr, 0, fVarArr2, 0, fVarArr.length);
            }
            this.J = fVarArr2;
            fVarArr = fVarArr2;
        }
        C0023f fVar = fVarArr[i2];
        if (fVar != null) {
            return fVar;
        }
        C0023f fVar2 = new C0023f(i2);
        fVarArr[i2] = fVar2;
        return fVar2;
    }

    private boolean a(C0023f fVar, int i2, KeyEvent keyEvent, int i3) {
        boolean z2 = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((fVar.m || b(fVar, keyEvent)) && fVar.j != null) {
            z2 = fVar.j.performShortcut(i2, keyEvent, i3);
        }
        if (z2 && (i3 & 1) == 0 && this.y == null) {
            a(fVar, true);
        }
        return z2;
    }

    private void j(int i2) {
        this.t = (1 << i2) | this.t;
        if (!this.s) {
            t.a(this.f814b.getDecorView(), this.P);
            this.s = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void g(int i2) {
        C0023f a2;
        C0023f a3 = a(i2, true);
        if (a3.j != null) {
            Bundle bundle = new Bundle();
            a3.j.a(bundle);
            if (bundle.size() > 0) {
                a3.s = bundle;
            }
            a3.j.h();
            a3.j.clear();
        }
        a3.r = true;
        a3.q = true;
        if ((i2 == 108 || i2 == 0) && this.y != null && (a2 = a(0, false)) != null) {
            a2.m = false;
            b(a2, (KeyEvent) null);
        }
    }

    /* access modifiers changed from: package-private */
    public int h(int i2) {
        boolean z2;
        boolean z3;
        ActionBarContextView actionBarContextView = this.i;
        int i3 = 0;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z2 = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.i.getLayoutParams();
            z2 = true;
            if (this.i.isShown()) {
                if (this.R == null) {
                    this.R = new Rect();
                    this.S = new Rect();
                }
                Rect rect = this.R;
                Rect rect2 = this.S;
                rect.set(0, i2, 0, 0);
                bb.a(this.D, rect, rect2);
                if (marginLayoutParams.topMargin != (rect2.top == 0 ? i2 : 0)) {
                    marginLayoutParams.topMargin = i2;
                    View view = this.F;
                    if (view == null) {
                        this.F = new View(this.f813a);
                        this.F.setBackgroundColor(this.f813a.getResources().getColor(a.c.abc_input_method_navigation_guard));
                        this.D.addView(this.F, -1, new ViewGroup.LayoutParams(-1, i2));
                    } else {
                        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                        if (layoutParams.height != i2) {
                            layoutParams.height = i2;
                            this.F.setLayoutParams(layoutParams);
                        }
                    }
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (this.F == null) {
                    z2 = false;
                }
                if (!this.o && z2) {
                    i2 = 0;
                }
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                z3 = true;
                z2 = false;
            } else {
                z3 = false;
                z2 = false;
            }
            if (z3) {
                this.i.setLayoutParams(marginLayoutParams);
            }
        }
        View view2 = this.F;
        if (view2 != null) {
            if (!z2) {
                i3 = 8;
            }
            view2.setVisibility(i3);
        }
        return i2;
    }

    private void x() {
        if (this.C) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    private int k(int i2) {
        if (i2 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        } else if (i2 != 9) {
            return i2;
        } else {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            return 109;
        }
    }

    /* access modifiers changed from: package-private */
    public void s() {
        ab abVar = this.y;
        if (abVar != null) {
            abVar.k();
        }
        if (this.j != null) {
            this.f814b.getDecorView().removeCallbacks(this.k);
            if (this.j.isShowing()) {
                try {
                    this.j.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.j = null;
        }
        q();
        C0023f a2 = a(0, false);
        if (a2 != null && a2.j != null) {
            a2.j.close();
        }
    }

    @Override // android.support.v7.app.e
    public boolean i() {
        int y2 = y();
        int i2 = i(y2);
        boolean l2 = i2 != -1 ? l(i2) : false;
        if (y2 == 0) {
            z();
            this.O.c();
        }
        this.N = true;
        return l2;
    }

    /* access modifiers changed from: package-private */
    public int i(int i2) {
        if (i2 == -100) {
            return -1;
        }
        if (i2 != 0) {
            return i2;
        }
        if (Build.VERSION.SDK_INT >= 23 && ((UiModeManager) this.f813a.getSystemService(UiModeManager.class)).getNightMode() == 0) {
            return -1;
        }
        z();
        return this.O.a();
    }

    private int y() {
        int i2 = this.M;
        return i2 != -100 ? i2 : j();
    }

    private boolean l(int i2) {
        Resources resources = this.f813a.getResources();
        Configuration configuration = resources.getConfiguration();
        int i3 = configuration.uiMode & 48;
        int i4 = i2 == 2 ? 32 : 16;
        if (i3 == i4) {
            return false;
        }
        if (A()) {
            ((Activity) this.f813a).recreate();
            return true;
        }
        Configuration configuration2 = new Configuration(configuration);
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        configuration2.uiMode = i4 | (configuration2.uiMode & -49);
        resources.updateConfiguration(configuration2, displayMetrics);
        if (Build.VERSION.SDK_INT >= 26) {
            return true;
        }
        h.a(resources);
        return true;
    }

    private void z() {
        if (this.O == null) {
            this.O = new d(k.a(this.f813a));
        }
    }

    private boolean A() {
        if (this.N) {
            Context context = this.f813a;
            if (context instanceof Activity) {
                try {
                    if ((context.getPackageManager().getActivityInfo(new ComponentName(this.f813a, this.f813a.getClass()), 0).configChanges & 512) == 0) {
                        return true;
                    }
                    return false;
                } catch (PackageManager.NameNotFoundException e2) {
                    Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e2);
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* compiled from: AppCompatDelegateImpl */
    public class b implements b.a {

        /* renamed from: b  reason: collision with root package name */
        private b.a f827b;

        public b(b.a aVar) {
            this.f827b = aVar;
        }

        @Override // android.support.v7.view.b.a
        public boolean a(android.support.v7.view.b bVar, Menu menu) {
            return this.f827b.a(bVar, menu);
        }

        @Override // android.support.v7.view.b.a
        public boolean b(android.support.v7.view.b bVar, Menu menu) {
            return this.f827b.b(bVar, menu);
        }

        @Override // android.support.v7.view.b.a
        public boolean a(android.support.v7.view.b bVar, MenuItem menuItem) {
            return this.f827b.a(bVar, menuItem);
        }

        @Override // android.support.v7.view.b.a
        public void a(android.support.v7.view.b bVar) {
            this.f827b.a(bVar);
            if (f.this.j != null) {
                f.this.f814b.getDecorView().removeCallbacks(f.this.k);
            }
            if (f.this.i != null) {
                f.this.q();
                f fVar = f.this;
                fVar.l = t.i(fVar.i).a(BitmapDescriptorFactory.HUE_RED);
                f.this.l.a(new aa() {
                    /* class android.support.v7.app.f.b.AnonymousClass1 */

                    @Override // android.support.v4.g.aa, android.support.v4.g.z
                    public void b(View view) {
                        f.this.i.setVisibility(8);
                        if (f.this.j != null) {
                            f.this.j.dismiss();
                        } else if (f.this.i.getParent() instanceof View) {
                            t.m((View) f.this.i.getParent());
                        }
                        f.this.i.removeAllViews();
                        f.this.l.a((z) null);
                        f.this.l = null;
                    }
                });
            }
            if (f.this.e != null) {
                f.this.e.b(f.this.h);
            }
            f.this.h = null;
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: AppCompatDelegateImpl */
    public final class g implements o.a {
        g() {
        }

        @Override // android.support.v7.view.menu.o.a
        public void a(h hVar, boolean z) {
            h q = hVar.q();
            boolean z2 = q != hVar;
            f fVar = f.this;
            if (z2) {
                hVar = q;
            }
            C0023f a2 = fVar.a((Menu) hVar);
            if (a2 == null) {
                return;
            }
            if (z2) {
                f.this.a(a2.f836a, a2, q);
                f.this.a(a2, true);
                return;
            }
            f.this.a(a2, z);
        }

        @Override // android.support.v7.view.menu.o.a
        public boolean a(h hVar) {
            Window.Callback l;
            if (hVar != null || !f.this.m || (l = f.this.l()) == null || f.this.r) {
                return true;
            }
            l.onMenuOpened(108, hVar);
            return true;
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: AppCompatDelegateImpl */
    public final class a implements o.a {
        a() {
        }

        @Override // android.support.v7.view.menu.o.a
        public boolean a(h hVar) {
            Window.Callback l = f.this.l();
            if (l == null) {
                return true;
            }
            l.onMenuOpened(108, hVar);
            return true;
        }

        @Override // android.support.v7.view.menu.o.a
        public void a(h hVar, boolean z) {
            f.this.b(hVar);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: android.support.v7.app.f$f  reason: collision with other inner class name */
    /* compiled from: AppCompatDelegateImpl */
    public static final class C0023f {

        /* renamed from: a  reason: collision with root package name */
        int f836a;

        /* renamed from: b  reason: collision with root package name */
        int f837b;

        /* renamed from: c  reason: collision with root package name */
        int f838c;

        /* renamed from: d  reason: collision with root package name */
        int f839d;
        int e;
        int f;
        ViewGroup g;
        View h;
        View i;
        h j;
        android.support.v7.view.menu.f k;
        Context l;
        boolean m;
        boolean n;
        boolean o;
        public boolean p;
        boolean q = false;
        boolean r;
        Bundle s;

        C0023f(int i2) {
            this.f836a = i2;
        }

        public boolean a() {
            if (this.h == null) {
                return false;
            }
            if (this.i == null && this.k.a().getCount() <= 0) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public void a(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme newTheme = context.getResources().newTheme();
            newTheme.setTo(context.getTheme());
            newTheme.resolveAttribute(a.C0020a.actionBarPopupTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                newTheme.applyStyle(typedValue.resourceId, true);
            }
            newTheme.resolveAttribute(a.C0020a.panelMenuListTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                newTheme.applyStyle(typedValue.resourceId, true);
            } else {
                newTheme.applyStyle(a.i.Theme_AppCompat_CompactMenu, true);
            }
            android.support.v7.view.d dVar = new android.support.v7.view.d(context, 0);
            dVar.getTheme().setTo(newTheme);
            this.l = dVar;
            TypedArray obtainStyledAttributes = dVar.obtainStyledAttributes(a.j.AppCompatTheme);
            this.f837b = obtainStyledAttributes.getResourceId(a.j.AppCompatTheme_panelBackground, 0);
            this.f = obtainStyledAttributes.getResourceId(a.j.AppCompatTheme_android_windowAnimationStyle, 0);
            obtainStyledAttributes.recycle();
        }

        /* access modifiers changed from: package-private */
        public void a(h hVar) {
            android.support.v7.view.menu.f fVar;
            h hVar2 = this.j;
            if (hVar != hVar2) {
                if (hVar2 != null) {
                    hVar2.b(this.k);
                }
                this.j = hVar;
                if (hVar != null && (fVar = this.k) != null) {
                    hVar.a(fVar);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public p a(o.a aVar) {
            if (this.j == null) {
                return null;
            }
            if (this.k == null) {
                this.k = new android.support.v7.view.menu.f(this.l, a.g.abc_list_menu_item_layout);
                this.k.a(aVar);
                this.j.a(this.k);
            }
            return this.k.a(this.g);
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: AppCompatDelegateImpl */
    public class e extends ContentFrameLayout {
        public e(Context context) {
            super(context);
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return f.this.a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !a((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            f.this.f(0);
            return true;
        }

        public void setBackgroundResource(int i) {
            setBackgroundDrawable(android.support.v7.b.a.a.b(getContext(), i));
        }

        private boolean a(int i, int i2) {
            return i < -5 || i2 < -5 || i > getWidth() + 5 || i2 > getHeight() + 5;
        }
    }

    /* compiled from: AppCompatDelegateImpl */
    class c extends i {
        @Override // android.support.v7.view.i
        public void onContentChanged() {
        }

        c(Window.Callback callback) {
            super(callback);
        }

        @Override // android.support.v7.view.i
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return f.this.a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.support.v7.view.i
        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || f.this.a(keyEvent.getKeyCode(), keyEvent);
        }

        @Override // android.support.v7.view.i
        public boolean onCreatePanelMenu(int i, Menu menu) {
            if (i != 0 || (menu instanceof h)) {
                return super.onCreatePanelMenu(i, menu);
            }
            return false;
        }

        @Override // android.support.v7.view.i
        public boolean onPreparePanel(int i, View view, Menu menu) {
            h hVar = menu instanceof h ? (h) menu : null;
            if (i == 0 && hVar == null) {
                return false;
            }
            if (hVar != null) {
                hVar.c(true);
            }
            boolean onPreparePanel = super.onPreparePanel(i, view, menu);
            if (hVar != null) {
                hVar.c(false);
            }
            return onPreparePanel;
        }

        @Override // android.support.v7.view.i
        public boolean onMenuOpened(int i, Menu menu) {
            super.onMenuOpened(i, menu);
            f.this.e(i);
            return true;
        }

        @Override // android.support.v7.view.i
        public void onPanelClosed(int i, Menu menu) {
            super.onPanelClosed(i, menu);
            f.this.d(i);
        }

        @Override // android.support.v7.view.i
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (Build.VERSION.SDK_INT >= 23) {
                return null;
            }
            if (f.this.p()) {
                return a(callback);
            }
            return super.onWindowStartingActionMode(callback);
        }

        /* access modifiers changed from: package-private */
        public final ActionMode a(ActionMode.Callback callback) {
            f.a aVar = new f.a(f.this.f813a, callback);
            android.support.v7.view.b a2 = f.this.a(aVar);
            if (a2 != null) {
                return aVar.b(a2);
            }
            return null;
        }

        @Override // android.support.v7.view.i
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            if (!f.this.p() || i != 0) {
                return super.onWindowStartingActionMode(callback, i);
            }
            return a(callback);
        }

        @Override // android.support.v7.view.i, android.view.Window.Callback
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
            C0023f a2 = f.this.a(0, true);
            if (a2 == null || a2.j == null) {
                super.onProvideKeyboardShortcuts(list, menu, i);
            } else {
                super.onProvideKeyboardShortcuts(list, a2.j, i);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* compiled from: AppCompatDelegateImpl */
    public final class d {

        /* renamed from: b  reason: collision with root package name */
        private k f831b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f832c;

        /* renamed from: d  reason: collision with root package name */
        private BroadcastReceiver f833d;
        private IntentFilter e;

        d(k kVar) {
            this.f831b = kVar;
            this.f832c = kVar.a();
        }

        /* access modifiers changed from: package-private */
        public int a() {
            this.f832c = this.f831b.a();
            return this.f832c ? 2 : 1;
        }

        /* access modifiers changed from: package-private */
        public void b() {
            boolean a2 = this.f831b.a();
            if (a2 != this.f832c) {
                this.f832c = a2;
                f.this.i();
            }
        }

        /* access modifiers changed from: package-private */
        public void c() {
            d();
            if (this.f833d == null) {
                this.f833d = new BroadcastReceiver() {
                    /* class android.support.v7.app.f.d.AnonymousClass1 */

                    public void onReceive(Context context, Intent intent) {
                        d.this.b();
                    }
                };
            }
            if (this.e == null) {
                this.e = new IntentFilter();
                this.e.addAction("android.intent.action.TIME_SET");
                this.e.addAction("android.intent.action.TIMEZONE_CHANGED");
                this.e.addAction("android.intent.action.TIME_TICK");
            }
            f.this.f813a.registerReceiver(this.f833d, this.e);
        }

        /* access modifiers changed from: package-private */
        public void d() {
            if (this.f833d != null) {
                f.this.f813a.unregisterReceiver(this.f833d);
                this.f833d = null;
            }
        }
    }
}
