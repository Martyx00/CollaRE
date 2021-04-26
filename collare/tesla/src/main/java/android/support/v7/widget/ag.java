package android.support.v7.widget;

import android.os.SystemClock;
import android.support.v7.view.menu.s;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: ForwardingListener */
public abstract class ag implements View.OnAttachStateChangeListener, View.OnTouchListener {

    /* renamed from: a  reason: collision with root package name */
    private final float f1129a;

    /* renamed from: b  reason: collision with root package name */
    private final int f1130b;

    /* renamed from: c  reason: collision with root package name */
    final View f1131c;

    /* renamed from: d  reason: collision with root package name */
    private final int f1132d;
    private Runnable e;
    private Runnable f;
    private boolean g;
    private int h;
    private final int[] i = new int[2];

    public abstract s a();

    public void onViewAttachedToWindow(View view) {
    }

    public ag(View view) {
        this.f1131c = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.f1129a = (float) ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        this.f1130b = ViewConfiguration.getTapTimeout();
        this.f1132d = (this.f1130b + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z;
        boolean z2 = this.g;
        if (z2) {
            z = b(motionEvent) || !c();
        } else {
            z = a(motionEvent) && b();
            if (z) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0);
                this.f1131c.onTouchEvent(obtain);
                obtain.recycle();
            }
        }
        this.g = z;
        if (z || z2) {
            return true;
        }
        return false;
    }

    public void onViewDetachedFromWindow(View view) {
        this.g = false;
        this.h = -1;
        Runnable runnable = this.e;
        if (runnable != null) {
            this.f1131c.removeCallbacks(runnable);
        }
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        s a2 = a();
        if (a2 == null || a2.d()) {
            return true;
        }
        a2.a();
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean c() {
        s a2 = a();
        if (a2 == null || !a2.d()) {
            return true;
        }
        a2.c();
        return true;
    }

    private boolean a(MotionEvent motionEvent) {
        View view = this.f1131c;
        if (!view.isEnabled()) {
            return false;
        }
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.h = motionEvent.getPointerId(0);
                if (this.e == null) {
                    this.e = new a();
                }
                view.postDelayed(this.e, (long) this.f1130b);
                if (this.f == null) {
                    this.f = new b();
                }
                view.postDelayed(this.f, (long) this.f1132d);
                break;
            case 1:
            case 3:
                e();
                break;
            case 2:
                int findPointerIndex = motionEvent.findPointerIndex(this.h);
                if (findPointerIndex >= 0 && !a(view, motionEvent.getX(findPointerIndex), motionEvent.getY(findPointerIndex), this.f1129a)) {
                    e();
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    return true;
                }
        }
        return false;
    }

    private void e() {
        Runnable runnable = this.f;
        if (runnable != null) {
            this.f1131c.removeCallbacks(runnable);
        }
        Runnable runnable2 = this.e;
        if (runnable2 != null) {
            this.f1131c.removeCallbacks(runnable2);
        }
    }

    /* access modifiers changed from: package-private */
    public void d() {
        e();
        View view = this.f1131c;
        if (view.isEnabled() && !view.isLongClickable() && b()) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0);
            view.onTouchEvent(obtain);
            obtain.recycle();
            this.g = true;
        }
    }

    private boolean b(MotionEvent motionEvent) {
        ae aeVar;
        View view = this.f1131c;
        s a2 = a();
        if (a2 == null || !a2.d() || (aeVar = (ae) a2.e()) == null || !aeVar.isShown()) {
            return false;
        }
        MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
        b(view, obtainNoHistory);
        a(aeVar, obtainNoHistory);
        boolean a3 = aeVar.a(obtainNoHistory, this.h);
        obtainNoHistory.recycle();
        int actionMasked = motionEvent.getActionMasked();
        return a3 && (actionMasked != 1 && actionMasked != 3);
    }

    private static boolean a(View view, float f2, float f3, float f4) {
        float f5 = -f4;
        return f2 >= f5 && f3 >= f5 && f2 < ((float) (view.getRight() - view.getLeft())) + f4 && f3 < ((float) (view.getBottom() - view.getTop())) + f4;
    }

    private boolean a(View view, MotionEvent motionEvent) {
        int[] iArr = this.i;
        view.getLocationOnScreen(iArr);
        motionEvent.offsetLocation((float) (-iArr[0]), (float) (-iArr[1]));
        return true;
    }

    private boolean b(View view, MotionEvent motionEvent) {
        int[] iArr = this.i;
        view.getLocationOnScreen(iArr);
        motionEvent.offsetLocation((float) iArr[0], (float) iArr[1]);
        return true;
    }

    /* access modifiers changed from: private */
    /* compiled from: ForwardingListener */
    public class a implements Runnable {
        a() {
        }

        public void run() {
            ViewParent parent = ag.this.f1131c.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ForwardingListener */
    public class b implements Runnable {
        b() {
        }

        public void run() {
            ag.this.d();
        }
    }
}
