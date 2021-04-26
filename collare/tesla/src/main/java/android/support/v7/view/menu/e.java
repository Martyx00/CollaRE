package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.g.t;
import android.support.v7.a.a;
import android.support.v7.view.menu.o;
import android.support.v7.widget.aj;
import android.support.v7.widget.ak;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/* access modifiers changed from: package-private */
/* compiled from: CascadingMenuPopup */
public final class e extends m implements o, View.OnKeyListener, PopupWindow.OnDismissListener {
    private static final int g = a.g.abc_cascading_menu_item_layout;
    private PopupWindow.OnDismissListener A;

    /* renamed from: a  reason: collision with root package name */
    final Handler f967a;

    /* renamed from: b  reason: collision with root package name */
    final List<a> f968b = new ArrayList();

    /* renamed from: c  reason: collision with root package name */
    final ViewTreeObserver.OnGlobalLayoutListener f969c = new ViewTreeObserver.OnGlobalLayoutListener() {
        /* class android.support.v7.view.menu.e.AnonymousClass1 */

        public void onGlobalLayout() {
            if (e.this.d() && e.this.f968b.size() > 0 && !e.this.f968b.get(0).f978a.g()) {
                View view = e.this.f970d;
                if (view == null || !view.isShown()) {
                    e.this.c();
                    return;
                }
                for (a aVar : e.this.f968b) {
                    aVar.f978a.a();
                }
            }
        }
    };

    /* renamed from: d  reason: collision with root package name */
    View f970d;
    ViewTreeObserver e;
    boolean f;
    private final Context h;
    private final int i;
    private final int j;
    private final int k;
    private final boolean l;
    private final List<h> m = new ArrayList();
    private final View.OnAttachStateChangeListener n = new View.OnAttachStateChangeListener() {
        /* class android.support.v7.view.menu.e.AnonymousClass2 */

        public void onViewAttachedToWindow(View view) {
        }

        public void onViewDetachedFromWindow(View view) {
            if (e.this.e != null) {
                if (!e.this.e.isAlive()) {
                    e.this.e = view.getViewTreeObserver();
                }
                e.this.e.removeGlobalOnLayoutListener(e.this.f969c);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    };
    private final aj o = new aj() {
        /* class android.support.v7.view.menu.e.AnonymousClass3 */

        @Override // android.support.v7.widget.aj
        public void a(h hVar, MenuItem menuItem) {
            e.this.f967a.removeCallbacksAndMessages(hVar);
        }

        @Override // android.support.v7.widget.aj
        public void b(final h hVar, final MenuItem menuItem) {
            final a aVar = null;
            e.this.f967a.removeCallbacksAndMessages(null);
            int size = e.this.f968b.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    i = -1;
                    break;
                } else if (hVar == e.this.f968b.get(i).f979b) {
                    break;
                } else {
                    i++;
                }
            }
            if (i != -1) {
                int i2 = i + 1;
                if (i2 < e.this.f968b.size()) {
                    aVar = e.this.f968b.get(i2);
                }
                e.this.f967a.postAtTime(new Runnable() {
                    /* class android.support.v7.view.menu.e.AnonymousClass3.AnonymousClass1 */

                    public void run() {
                        if (aVar != null) {
                            e.this.f = true;
                            aVar.f979b.a(false);
                            e.this.f = false;
                        }
                        if (menuItem.isEnabled() && menuItem.hasSubMenu()) {
                            hVar.a(menuItem, 4);
                        }
                    }
                }, hVar, SystemClock.uptimeMillis() + 200);
            }
        }
    };
    private int p = 0;
    private int q = 0;
    private View r;
    private int s;
    private boolean t;
    private boolean u;
    private int v;
    private int w;
    private boolean x;
    private boolean y;
    private o.a z;

    @Override // android.support.v7.view.menu.o
    public boolean b() {
        return false;
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v7.view.menu.m
    public boolean f() {
        return false;
    }

    public e(Context context, View view, int i2, int i3, boolean z2) {
        this.h = context;
        this.r = view;
        this.j = i2;
        this.k = i3;
        this.l = z2;
        this.x = false;
        this.s = i();
        Resources resources = context.getResources();
        this.i = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(a.d.abc_config_prefDialogWidth));
        this.f967a = new Handler();
    }

    @Override // android.support.v7.view.menu.m
    public void a(boolean z2) {
        this.x = z2;
    }

    private ak h() {
        ak akVar = new ak(this.h, null, this.j, this.k);
        akVar.a(this.o);
        akVar.a((AdapterView.OnItemClickListener) this);
        akVar.a((PopupWindow.OnDismissListener) this);
        akVar.b(this.r);
        akVar.e(this.q);
        akVar.a(true);
        akVar.h(2);
        return akVar;
    }

    @Override // android.support.v7.view.menu.s
    public void a() {
        if (!d()) {
            for (h hVar : this.m) {
                c(hVar);
            }
            this.m.clear();
            this.f970d = this.r;
            if (this.f970d != null) {
                boolean z2 = this.e == null;
                this.e = this.f970d.getViewTreeObserver();
                if (z2) {
                    this.e.addOnGlobalLayoutListener(this.f969c);
                }
                this.f970d.addOnAttachStateChangeListener(this.n);
            }
        }
    }

    @Override // android.support.v7.view.menu.s
    public void c() {
        int size = this.f968b.size();
        if (size > 0) {
            a[] aVarArr = (a[]) this.f968b.toArray(new a[size]);
            for (int i2 = size - 1; i2 >= 0; i2--) {
                a aVar = aVarArr[i2];
                if (aVar.f978a.d()) {
                    aVar.f978a.c();
                }
            }
        }
    }

    public boolean onKey(View view, int i2, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i2 != 82) {
            return false;
        }
        c();
        return true;
    }

    private int i() {
        return t.d(this.r) == 1 ? 0 : 1;
    }

    private int d(int i2) {
        List<a> list = this.f968b;
        ListView a2 = list.get(list.size() - 1).a();
        int[] iArr = new int[2];
        a2.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.f970d.getWindowVisibleDisplayFrame(rect);
        if (this.s == 1) {
            if (iArr[0] + a2.getWidth() + i2 > rect.right) {
                return 0;
            }
            return 1;
        } else if (iArr[0] - i2 < 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override // android.support.v7.view.menu.m
    public void a(h hVar) {
        hVar.a(this, this.h);
        if (d()) {
            c(hVar);
        } else {
            this.m.add(hVar);
        }
    }

    private void c(h hVar) {
        View view;
        a aVar;
        int i2;
        int i3;
        int i4;
        LayoutInflater from = LayoutInflater.from(this.h);
        g gVar = new g(hVar, from, this.l, g);
        if (!d() && this.x) {
            gVar.a(true);
        } else if (d()) {
            gVar.a(m.b(hVar));
        }
        int a2 = a(gVar, null, this.h, this.i);
        ak h2 = h();
        h2.a((ListAdapter) gVar);
        h2.g(a2);
        h2.e(this.q);
        if (this.f968b.size() > 0) {
            List<a> list = this.f968b;
            aVar = list.get(list.size() - 1);
            view = a(aVar, hVar);
        } else {
            aVar = null;
            view = null;
        }
        if (view != null) {
            h2.c(false);
            h2.a((Object) null);
            int d2 = d(a2);
            boolean z2 = d2 == 1;
            this.s = d2;
            if (Build.VERSION.SDK_INT >= 26) {
                h2.b(view);
                i3 = 0;
                i2 = 0;
            } else {
                int[] iArr = new int[2];
                this.r.getLocationOnScreen(iArr);
                int[] iArr2 = new int[2];
                view.getLocationOnScreen(iArr2);
                if ((this.q & 7) == 5) {
                    iArr[0] = iArr[0] + this.r.getWidth();
                    iArr2[0] = iArr2[0] + view.getWidth();
                }
                i2 = iArr2[0] - iArr[0];
                i3 = iArr2[1] - iArr[1];
            }
            if ((this.q & 5) == 5) {
                i4 = z2 ? i2 + a2 : i2 - view.getWidth();
            } else {
                i4 = z2 ? i2 + view.getWidth() : i2 - a2;
            }
            h2.c(i4);
            h2.b(true);
            h2.d(i3);
        } else {
            if (this.t) {
                h2.c(this.v);
            }
            if (this.u) {
                h2.d(this.w);
            }
            h2.a(g());
        }
        this.f968b.add(new a(h2, hVar, this.s));
        h2.a();
        ListView e2 = h2.e();
        e2.setOnKeyListener(this);
        if (aVar == null && this.y && hVar.n() != null) {
            FrameLayout frameLayout = (FrameLayout) from.inflate(a.g.abc_popup_menu_header_item_layout, (ViewGroup) e2, false);
            frameLayout.setEnabled(false);
            ((TextView) frameLayout.findViewById(16908310)).setText(hVar.n());
            e2.addHeaderView(frameLayout, null, false);
            h2.a();
        }
    }

    private MenuItem a(h hVar, h hVar2) {
        int size = hVar.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = hVar.getItem(i2);
            if (item.hasSubMenu() && hVar2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    private View a(a aVar, h hVar) {
        int i2;
        g gVar;
        int firstVisiblePosition;
        MenuItem a2 = a(aVar.f979b, hVar);
        if (a2 == null) {
            return null;
        }
        ListView a3 = aVar.a();
        ListAdapter adapter = a3.getAdapter();
        int i3 = 0;
        if (adapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
            i2 = headerViewListAdapter.getHeadersCount();
            gVar = (g) headerViewListAdapter.getWrappedAdapter();
        } else {
            gVar = (g) adapter;
            i2 = 0;
        }
        int count = gVar.getCount();
        while (true) {
            if (i3 >= count) {
                i3 = -1;
                break;
            } else if (a2 == gVar.getItem(i3)) {
                break;
            } else {
                i3++;
            }
        }
        if (i3 != -1 && (firstVisiblePosition = (i3 + i2) - a3.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < a3.getChildCount()) {
            return a3.getChildAt(firstVisiblePosition);
        }
        return null;
    }

    @Override // android.support.v7.view.menu.s
    public boolean d() {
        return this.f968b.size() > 0 && this.f968b.get(0).f978a.d();
    }

    public void onDismiss() {
        a aVar;
        int size = this.f968b.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                aVar = null;
                break;
            }
            aVar = this.f968b.get(i2);
            if (!aVar.f978a.d()) {
                break;
            }
            i2++;
        }
        if (aVar != null) {
            aVar.f979b.a(false);
        }
    }

    @Override // android.support.v7.view.menu.o
    public void b(boolean z2) {
        for (a aVar : this.f968b) {
            a(aVar.a().getAdapter()).notifyDataSetChanged();
        }
    }

    @Override // android.support.v7.view.menu.o
    public void a(o.a aVar) {
        this.z = aVar;
    }

    @Override // android.support.v7.view.menu.o
    public boolean a(u uVar) {
        for (a aVar : this.f968b) {
            if (uVar == aVar.f979b) {
                aVar.a().requestFocus();
                return true;
            }
        }
        if (!uVar.hasVisibleItems()) {
            return false;
        }
        a((h) uVar);
        o.a aVar2 = this.z;
        if (aVar2 != null) {
            aVar2.a(uVar);
        }
        return true;
    }

    private int d(h hVar) {
        int size = this.f968b.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (hVar == this.f968b.get(i2).f979b) {
                return i2;
            }
        }
        return -1;
    }

    @Override // android.support.v7.view.menu.o
    public void a(h hVar, boolean z2) {
        int d2 = d(hVar);
        if (d2 >= 0) {
            int i2 = d2 + 1;
            if (i2 < this.f968b.size()) {
                this.f968b.get(i2).f979b.a(false);
            }
            a remove = this.f968b.remove(d2);
            remove.f979b.b(this);
            if (this.f) {
                remove.f978a.b((Object) null);
                remove.f978a.b(0);
            }
            remove.f978a.c();
            int size = this.f968b.size();
            if (size > 0) {
                this.s = this.f968b.get(size - 1).f980c;
            } else {
                this.s = i();
            }
            if (size == 0) {
                c();
                o.a aVar = this.z;
                if (aVar != null) {
                    aVar.a(hVar, true);
                }
                ViewTreeObserver viewTreeObserver = this.e;
                if (viewTreeObserver != null) {
                    if (viewTreeObserver.isAlive()) {
                        this.e.removeGlobalOnLayoutListener(this.f969c);
                    }
                    this.e = null;
                }
                this.f970d.removeOnAttachStateChangeListener(this.n);
                this.A.onDismiss();
            } else if (z2) {
                this.f968b.get(0).f979b.a(false);
            }
        }
    }

    @Override // android.support.v7.view.menu.m
    public void a(int i2) {
        if (this.p != i2) {
            this.p = i2;
            this.q = android.support.v4.g.e.a(i2, t.d(this.r));
        }
    }

    @Override // android.support.v7.view.menu.m
    public void a(View view) {
        if (this.r != view) {
            this.r = view;
            this.q = android.support.v4.g.e.a(this.p, t.d(this.r));
        }
    }

    @Override // android.support.v7.view.menu.m
    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.A = onDismissListener;
    }

    @Override // android.support.v7.view.menu.s
    public ListView e() {
        if (this.f968b.isEmpty()) {
            return null;
        }
        List<a> list = this.f968b;
        return list.get(list.size() - 1).a();
    }

    @Override // android.support.v7.view.menu.m
    public void b(int i2) {
        this.t = true;
        this.v = i2;
    }

    @Override // android.support.v7.view.menu.m
    public void c(int i2) {
        this.u = true;
        this.w = i2;
    }

    @Override // android.support.v7.view.menu.m
    public void c(boolean z2) {
        this.y = z2;
    }

    /* access modifiers changed from: private */
    /* compiled from: CascadingMenuPopup */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final ak f978a;

        /* renamed from: b  reason: collision with root package name */
        public final h f979b;

        /* renamed from: c  reason: collision with root package name */
        public final int f980c;

        public a(ak akVar, h hVar, int i) {
            this.f978a = akVar;
            this.f979b = hVar;
            this.f980c = i;
        }

        public ListView a() {
            return this.f978a.e();
        }
    }
}
