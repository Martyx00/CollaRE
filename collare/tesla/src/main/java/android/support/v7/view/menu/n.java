package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.g.e;
import android.support.v4.g.t;
import android.support.v7.a.a;
import android.support.v7.view.menu.o;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/* compiled from: MenuPopupHelper */
public class n {

    /* renamed from: a  reason: collision with root package name */
    private final Context f1013a;

    /* renamed from: b  reason: collision with root package name */
    private final h f1014b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f1015c;

    /* renamed from: d  reason: collision with root package name */
    private final int f1016d;
    private final int e;
    private View f;
    private int g;
    private boolean h;
    private o.a i;
    private m j;
    private PopupWindow.OnDismissListener k;
    private final PopupWindow.OnDismissListener l;

    public n(Context context, h hVar, View view, boolean z, int i2) {
        this(context, hVar, view, z, i2, 0);
    }

    public n(Context context, h hVar, View view, boolean z, int i2, int i3) {
        this.g = 8388611;
        this.l = new PopupWindow.OnDismissListener() {
            /* class android.support.v7.view.menu.n.AnonymousClass1 */

            public void onDismiss() {
                n.this.e();
            }
        };
        this.f1013a = context;
        this.f1014b = hVar;
        this.f = view;
        this.f1015c = z;
        this.f1016d = i2;
        this.e = i3;
    }

    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.k = onDismissListener;
    }

    public void a(View view) {
        this.f = view;
    }

    public void a(boolean z) {
        this.h = z;
        m mVar = this.j;
        if (mVar != null) {
            mVar.a(z);
        }
    }

    public void a(int i2) {
        this.g = i2;
    }

    public void a() {
        if (!c()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public m b() {
        if (this.j == null) {
            this.j = g();
        }
        return this.j;
    }

    public boolean c() {
        if (f()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        a(0, 0, false, false);
        return true;
    }

    public boolean a(int i2, int i3) {
        if (f()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        a(i2, i3, true, true);
        return true;
    }

    private m g() {
        m mVar;
        Display defaultDisplay = ((WindowManager) this.f1013a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else {
            defaultDisplay.getSize(point);
        }
        if (Math.min(point.x, point.y) >= this.f1013a.getResources().getDimensionPixelSize(a.d.abc_cascading_menus_min_smallest_width)) {
            mVar = new e(this.f1013a, this.f, this.f1016d, this.e, this.f1015c);
        } else {
            mVar = new t(this.f1013a, this.f1014b, this.f, this.f1016d, this.e, this.f1015c);
        }
        mVar.a(this.f1014b);
        mVar.a(this.l);
        mVar.a(this.f);
        mVar.a(this.i);
        mVar.a(this.h);
        mVar.a(this.g);
        return mVar;
    }

    private void a(int i2, int i3, boolean z, boolean z2) {
        m b2 = b();
        b2.c(z2);
        if (z) {
            if ((e.a(this.g, t.d(this.f)) & 7) == 5) {
                i2 -= this.f.getWidth();
            }
            b2.b(i2);
            b2.c(i3);
            int i4 = (int) ((this.f1013a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            b2.a(new Rect(i2 - i4, i3 - i4, i2 + i4, i3 + i4));
        }
        b2.a();
    }

    public void d() {
        if (f()) {
            this.j.c();
        }
    }

    /* access modifiers changed from: protected */
    public void e() {
        this.j = null;
        PopupWindow.OnDismissListener onDismissListener = this.k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public boolean f() {
        m mVar = this.j;
        return mVar != null && mVar.d();
    }

    public void a(o.a aVar) {
        this.i = aVar;
        m mVar = this.j;
        if (mVar != null) {
            mVar.a(aVar);
        }
    }
}
