package com.facebook.react.views.toolbar;

import android.graphics.drawable.Drawable;
import com.facebook.f.e.g;
import com.facebook.imagepipeline.j.e;

/* compiled from: DrawableWithIntrinsicSize */
public class a extends g implements Drawable.Callback {

    /* renamed from: a  reason: collision with root package name */
    private final e f3544a;

    public a(Drawable drawable, e eVar) {
        super(drawable);
        this.f3544a = eVar;
    }

    @Override // com.facebook.f.e.g
    public int getIntrinsicWidth() {
        return this.f3544a.f();
    }

    @Override // com.facebook.f.e.g
    public int getIntrinsicHeight() {
        return this.f3544a.g();
    }
}
