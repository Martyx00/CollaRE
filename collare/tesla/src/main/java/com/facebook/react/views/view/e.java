package com.facebook.react.views.view;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.g.t;
import android.view.View;

/* compiled from: ReactViewBackgroundManager */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private d f3571a;

    /* renamed from: b  reason: collision with root package name */
    private View f3572b;

    public e(View view) {
        this.f3572b = view;
    }

    private d a() {
        if (this.f3571a == null) {
            this.f3571a = new d(this.f3572b.getContext());
            Drawable background = this.f3572b.getBackground();
            t.a(this.f3572b, (Drawable) null);
            if (background == null) {
                t.a(this.f3572b, this.f3571a);
            } else {
                t.a(this.f3572b, new LayerDrawable(new Drawable[]{this.f3571a, background}));
            }
        }
        return this.f3571a;
    }

    public void a(int i) {
        if (i != 0 || this.f3571a != null) {
            a().a(i);
        }
    }

    public void a(int i, float f) {
        a().a(i, f);
    }

    public void a(int i, float f, float f2) {
        a().a(i, f, f2);
    }

    public void a(float f) {
        a().a(f);
    }

    public void a(float f, int i) {
        a().a(f, i);
    }

    public void a(String str) {
        a().a(str);
    }
}
