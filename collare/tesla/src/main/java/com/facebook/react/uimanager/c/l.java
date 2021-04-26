package com.facebook.react.uimanager.c;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/* compiled from: OpacityAnimation */
class l extends Animation {

    /* renamed from: a  reason: collision with root package name */
    private final View f3214a;

    /* renamed from: b  reason: collision with root package name */
    private final float f3215b;

    /* renamed from: c  reason: collision with root package name */
    private final float f3216c;

    public boolean willChangeBounds() {
        return false;
    }

    /* compiled from: OpacityAnimation */
    static class a implements Animation.AnimationListener {

        /* renamed from: a  reason: collision with root package name */
        private final View f3217a;

        /* renamed from: b  reason: collision with root package name */
        private boolean f3218b = false;

        public void onAnimationRepeat(Animation animation) {
        }

        public a(View view) {
            this.f3217a = view;
        }

        public void onAnimationStart(Animation animation) {
            if (this.f3217a.hasOverlappingRendering() && this.f3217a.getLayerType() == 0) {
                this.f3218b = true;
                this.f3217a.setLayerType(2, null);
            }
        }

        public void onAnimationEnd(Animation animation) {
            if (this.f3218b) {
                this.f3217a.setLayerType(0, null);
            }
        }
    }

    public l(View view, float f, float f2) {
        this.f3214a = view;
        this.f3215b = f;
        this.f3216c = f2 - f;
        setAnimationListener(new a(view));
    }

    /* access modifiers changed from: protected */
    public void applyTransformation(float f, Transformation transformation) {
        this.f3214a.setAlpha(this.f3215b + (this.f3216c * f));
    }
}
