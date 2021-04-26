package com.facebook.react.views.drawer;

import android.support.v4.widget.h;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.events.f;

/* access modifiers changed from: package-private */
/* compiled from: ReactDrawerLayout */
public class a extends h {

    /* renamed from: c  reason: collision with root package name */
    private int f3319c = 8388611;

    /* renamed from: d  reason: collision with root package name */
    private int f3320d = -1;

    public a(ReactContext reactContext) {
        super(reactContext);
    }

    @Override // android.support.v4.widget.h
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            if (!super.onInterceptTouchEvent(motionEvent)) {
                return false;
            }
            f.a(this, motionEvent);
            return true;
        } catch (IllegalArgumentException e) {
            Log.w("ReactNative", "Error intercepting touch event.", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void e() {
        e(this.f3319c);
    }

    /* access modifiers changed from: package-private */
    public void f() {
        f(this.f3319c);
    }

    /* access modifiers changed from: package-private */
    public void g(int i) {
        this.f3319c = i;
        g();
    }

    /* access modifiers changed from: package-private */
    public void h(int i) {
        this.f3320d = i;
        g();
    }

    /* access modifiers changed from: package-private */
    public void g() {
        if (getChildCount() == 2) {
            View childAt = getChildAt(1);
            h.d dVar = (h.d) childAt.getLayoutParams();
            dVar.f726a = this.f3319c;
            dVar.width = this.f3320d;
            childAt.setLayoutParams(dVar);
            childAt.setClickable(true);
        }
    }
}
