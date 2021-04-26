package android.support.v7.widget;

import android.support.v4.g.t;
import android.support.v4.g.u;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;
import com.google.android.gms.common.api.Api;

/* access modifiers changed from: package-private */
/* compiled from: TooltipCompatHandler */
public class ay implements View.OnAttachStateChangeListener, View.OnHoverListener, View.OnLongClickListener {
    private static ay j;
    private static ay k;

    /* renamed from: a  reason: collision with root package name */
    private final View f1205a;

    /* renamed from: b  reason: collision with root package name */
    private final CharSequence f1206b;

    /* renamed from: c  reason: collision with root package name */
    private final int f1207c;

    /* renamed from: d  reason: collision with root package name */
    private final Runnable f1208d = new Runnable() {
        /* class android.support.v7.widget.ay.AnonymousClass1 */

        public void run() {
            ay.this.a(false);
        }
    };
    private final Runnable e = new Runnable() {
        /* class android.support.v7.widget.ay.AnonymousClass2 */

        public void run() {
            ay.this.a();
        }
    };
    private int f;
    private int g;
    private az h;
    private boolean i;

    public void onViewAttachedToWindow(View view) {
    }

    public static void a(View view, CharSequence charSequence) {
        ay ayVar = j;
        if (ayVar != null && ayVar.f1205a == view) {
            a((ay) null);
        }
        if (TextUtils.isEmpty(charSequence)) {
            ay ayVar2 = k;
            if (ayVar2 != null && ayVar2.f1205a == view) {
                ayVar2.a();
            }
            view.setOnLongClickListener(null);
            view.setLongClickable(false);
            view.setOnHoverListener(null);
            return;
        }
        new ay(view, charSequence);
    }

    private ay(View view, CharSequence charSequence) {
        this.f1205a = view;
        this.f1206b = charSequence;
        this.f1207c = u.a(ViewConfiguration.get(this.f1205a.getContext()));
        d();
        this.f1205a.setOnLongClickListener(this);
        this.f1205a.setOnHoverListener(this);
    }

    public boolean onLongClick(View view) {
        this.f = view.getWidth() / 2;
        this.g = view.getHeight() / 2;
        a(true);
        return true;
    }

    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.h != null && this.i) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.f1205a.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                d();
                a();
            }
        } else if (this.f1205a.isEnabled() && this.h == null && a(motionEvent)) {
            a(this);
        }
        return false;
    }

    public void onViewDetachedFromWindow(View view) {
        a();
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        long j2;
        if (t.u(this.f1205a)) {
            a((ay) null);
            ay ayVar = k;
            if (ayVar != null) {
                ayVar.a();
            }
            k = this;
            this.i = z;
            this.h = new az(this.f1205a.getContext());
            this.h.a(this.f1205a, this.f, this.g, this.i, this.f1206b);
            this.f1205a.addOnAttachStateChangeListener(this);
            if (this.i) {
                j2 = 2500;
            } else if ((t.l(this.f1205a) & 1) == 1) {
                j2 = 3000 - ((long) ViewConfiguration.getLongPressTimeout());
            } else {
                j2 = 15000 - ((long) ViewConfiguration.getLongPressTimeout());
            }
            this.f1205a.removeCallbacks(this.e);
            this.f1205a.postDelayed(this.e, j2);
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (k == this) {
            k = null;
            az azVar = this.h;
            if (azVar != null) {
                azVar.a();
                this.h = null;
                d();
                this.f1205a.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (j == this) {
            a((ay) null);
        }
        this.f1205a.removeCallbacks(this.e);
    }

    private static void a(ay ayVar) {
        ay ayVar2 = j;
        if (ayVar2 != null) {
            ayVar2.c();
        }
        j = ayVar;
        ay ayVar3 = j;
        if (ayVar3 != null) {
            ayVar3.b();
        }
    }

    private void b() {
        this.f1205a.postDelayed(this.f1208d, (long) ViewConfiguration.getLongPressTimeout());
    }

    private void c() {
        this.f1205a.removeCallbacks(this.f1208d);
    }

    private boolean a(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (Math.abs(x - this.f) <= this.f1207c && Math.abs(y - this.g) <= this.f1207c) {
            return false;
        }
        this.f = x;
        this.g = y;
        return true;
    }

    private void d() {
        this.f = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        this.g = Api.BaseClientBuilder.API_PRIORITY_OTHER;
    }
}
