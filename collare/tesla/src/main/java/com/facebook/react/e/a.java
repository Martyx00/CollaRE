package com.facebook.react.e;

import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewParent;

/* compiled from: JSResponderHandler */
public class a implements b {

    /* renamed from: a  reason: collision with root package name */
    private volatile int f2683a = -1;

    /* renamed from: b  reason: collision with root package name */
    private ViewParent f2684b;

    public void a(int i, ViewParent viewParent) {
        this.f2683a = i;
        b();
        if (viewParent != null) {
            viewParent.requestDisallowInterceptTouchEvent(true);
            this.f2684b = viewParent;
        }
    }

    public void a() {
        this.f2683a = -1;
        b();
    }

    private void b() {
        ViewParent viewParent = this.f2684b;
        if (viewParent != null) {
            viewParent.requestDisallowInterceptTouchEvent(false);
            this.f2684b = null;
        }
    }

    @Override // com.facebook.react.e.b
    public boolean a(ViewGroup viewGroup, MotionEvent motionEvent) {
        int i = this.f2683a;
        if (i == -1 || motionEvent.getAction() == 1 || viewGroup.getId() != i) {
            return false;
        }
        return true;
    }
}
