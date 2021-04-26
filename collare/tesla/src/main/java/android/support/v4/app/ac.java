package android.support.v4.app;

import android.view.View;
import android.view.ViewTreeObserver;

/* compiled from: OneShotPreDrawListener */
class ac implements View.OnAttachStateChangeListener, ViewTreeObserver.OnPreDrawListener {

    /* renamed from: a  reason: collision with root package name */
    private final View f162a;

    /* renamed from: b  reason: collision with root package name */
    private ViewTreeObserver f163b;

    /* renamed from: c  reason: collision with root package name */
    private final Runnable f164c;

    private ac(View view, Runnable runnable) {
        this.f162a = view;
        this.f163b = view.getViewTreeObserver();
        this.f164c = runnable;
    }

    public static ac a(View view, Runnable runnable) {
        ac acVar = new ac(view, runnable);
        view.getViewTreeObserver().addOnPreDrawListener(acVar);
        view.addOnAttachStateChangeListener(acVar);
        return acVar;
    }

    public boolean onPreDraw() {
        a();
        this.f164c.run();
        return true;
    }

    public void a() {
        if (this.f163b.isAlive()) {
            this.f163b.removeOnPreDrawListener(this);
        } else {
            this.f162a.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        this.f162a.removeOnAttachStateChangeListener(this);
    }

    public void onViewAttachedToWindow(View view) {
        this.f163b = view.getViewTreeObserver();
    }

    public void onViewDetachedFromWindow(View view) {
        a();
    }
}
