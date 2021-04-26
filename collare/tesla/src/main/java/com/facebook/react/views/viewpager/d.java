package com.facebook.react.views.viewpager;

import android.support.v4.g.r;
import android.support.v4.g.w;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.f;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ReactViewPager */
public class d extends w {

    /* renamed from: d  reason: collision with root package name */
    private final com.facebook.react.uimanager.events.d f3582d;
    private boolean e;
    private boolean f = true;
    private final Runnable g = new Runnable() {
        /* class com.facebook.react.views.viewpager.d.AnonymousClass1 */

        public void run() {
            d dVar = d.this;
            dVar.measure(View.MeasureSpec.makeMeasureSpec(dVar.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(d.this.getHeight(), 1073741824));
            d dVar2 = d.this;
            dVar2.layout(dVar2.getLeft(), d.this.getTop(), d.this.getRight(), d.this.getBottom());
        }
    };

    /* access modifiers changed from: private */
    /* compiled from: ReactViewPager */
    public class a extends r {

        /* renamed from: b  reason: collision with root package name */
        private final List<View> f3585b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f3586c;

        @Override // android.support.v4.g.r
        public boolean a(View view, Object obj) {
            return view == obj;
        }

        private a() {
            this.f3585b = new ArrayList();
            this.f3586c = false;
        }

        /* access modifiers changed from: package-private */
        public void b(View view, int i) {
            this.f3585b.add(i, view);
            c();
            d.this.setOffscreenPageLimit(this.f3585b.size());
        }

        /* access modifiers changed from: package-private */
        public void b(int i) {
            this.f3585b.remove(i);
            c();
            d.this.setOffscreenPageLimit(this.f3585b.size());
        }

        /* access modifiers changed from: package-private */
        public void a(List<View> list) {
            this.f3585b.clear();
            this.f3585b.addAll(list);
            c();
            this.f3586c = false;
        }

        /* access modifiers changed from: package-private */
        public View c(int i) {
            return this.f3585b.get(i);
        }

        @Override // android.support.v4.g.r
        public int a() {
            return this.f3585b.size();
        }

        @Override // android.support.v4.g.r
        public int a(Object obj) {
            if (this.f3586c || !this.f3585b.contains(obj)) {
                return -2;
            }
            return this.f3585b.indexOf(obj);
        }

        @Override // android.support.v4.g.r
        public Object a(ViewGroup viewGroup, int i) {
            View view = this.f3585b.get(i);
            viewGroup.addView(view, 0, d.this.generateDefaultLayoutParams());
            return view;
        }

        @Override // android.support.v4.g.r
        public void a(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }
    }

    /* compiled from: ReactViewPager */
    private class b implements w.f {
        private b() {
        }

        @Override // android.support.v4.g.w.f
        public void a(int i, float f, int i2) {
            d.this.f3582d.a(new a(d.this.getId(), i, f));
        }

        @Override // android.support.v4.g.w.f
        public void a(int i) {
            if (!d.this.e) {
                d.this.f3582d.a(new c(d.this.getId(), i));
            }
        }

        @Override // android.support.v4.g.w.f
        public void b(int i) {
            String str;
            switch (i) {
                case 0:
                    str = "idle";
                    break;
                case 1:
                    str = "dragging";
                    break;
                case 2:
                    str = "settling";
                    break;
                default:
                    throw new IllegalStateException("Unsupported pageScrollState");
            }
            d.this.f3582d.a(new b(d.this.getId(), str));
        }
    }

    public d(ReactContext reactContext) {
        super(reactContext);
        this.f3582d = ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
        this.e = false;
        setOnPageChangeListener(new b());
        setAdapter(new a());
    }

    @Override // android.support.v4.g.w
    public a getAdapter() {
        return (a) super.getAdapter();
    }

    @Override // android.support.v4.g.w
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.f) {
            return false;
        }
        try {
            if (super.onInterceptTouchEvent(motionEvent)) {
                f.a(this, motionEvent);
                return true;
            }
        } catch (IllegalArgumentException e2) {
            com.facebook.common.e.a.b("ReactNative", "Error intercepting touch event.", e2);
        }
        return false;
    }

    @Override // android.support.v4.g.w
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f) {
            return false;
        }
        try {
            return super.onTouchEvent(motionEvent);
        } catch (IllegalArgumentException e2) {
            com.facebook.common.e.a.b("ReactNative", "Error handling touch event.", e2);
            return false;
        }
    }

    public void b(int i, boolean z) {
        this.e = true;
        a(i, z);
        this.e = false;
    }

    public void setScrollEnabled(boolean z) {
        this.f = z;
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v4.g.w
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        requestLayout();
        post(this.g);
    }

    /* access modifiers changed from: package-private */
    public void a(View view, int i) {
        getAdapter().b(view, i);
    }

    /* access modifiers changed from: package-private */
    public void d(int i) {
        getAdapter().b(i);
    }

    /* access modifiers changed from: package-private */
    public int getViewCountInAdapter() {
        return getAdapter().a();
    }

    /* access modifiers changed from: package-private */
    public View e(int i) {
        return getAdapter().c(i);
    }

    public void setViews(List<View> list) {
        getAdapter().a(list);
    }
}
