package com.facebook.react.uimanager.c;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/* compiled from: PositionAndSizeAnimation */
class m extends Animation implements j {

    /* renamed from: a  reason: collision with root package name */
    private final View f3219a;

    /* renamed from: b  reason: collision with root package name */
    private float f3220b;

    /* renamed from: c  reason: collision with root package name */
    private float f3221c;

    /* renamed from: d  reason: collision with root package name */
    private float f3222d;
    private float e;
    private int f;
    private int g;
    private int h;
    private int i;

    public boolean willChangeBounds() {
        return true;
    }

    public m(View view, int i2, int i3, int i4, int i5) {
        this.f3219a = view;
        b(i2, i3, i4, i5);
    }

    /* access modifiers changed from: protected */
    public void applyTransformation(float f2, Transformation transformation) {
        float f3 = this.f3220b + (this.f3222d * f2);
        float f4 = this.f3221c + (this.e * f2);
        this.f3219a.layout(Math.round(f3), Math.round(f4), Math.round(f3 + ((float) this.f) + (((float) this.h) * f2)), Math.round(f4 + ((float) this.g) + (((float) this.i) * f2)));
    }

    @Override // com.facebook.react.uimanager.c.j
    public void a(int i2, int i3, int i4, int i5) {
        b(i2, i3, i4, i5);
    }

    private void b(int i2, int i3, int i4, int i5) {
        this.f3220b = this.f3219a.getX() - this.f3219a.getTranslationX();
        this.f3221c = this.f3219a.getY() - this.f3219a.getTranslationY();
        this.f = this.f3219a.getWidth();
        this.g = this.f3219a.getHeight();
        this.f3222d = ((float) i2) - this.f3220b;
        this.e = ((float) i3) - this.f3221c;
        this.h = i4 - this.f;
        this.i = i5 - this.g;
    }
}
