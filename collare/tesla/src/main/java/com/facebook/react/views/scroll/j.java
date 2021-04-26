package com.facebook.react.views.scroll;

import android.view.MotionEvent;
import android.view.VelocityTracker;

/* compiled from: VelocityHelper */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private VelocityTracker f3406a;

    /* renamed from: b  reason: collision with root package name */
    private float f3407b;

    /* renamed from: c  reason: collision with root package name */
    private float f3408c;

    public void a(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (this.f3406a == null) {
            this.f3406a = VelocityTracker.obtain();
        }
        this.f3406a.addMovement(motionEvent);
        if (action == 1 || action == 3) {
            this.f3406a.computeCurrentVelocity(1);
            this.f3407b = this.f3406a.getXVelocity();
            this.f3408c = this.f3406a.getYVelocity();
            VelocityTracker velocityTracker = this.f3406a;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.f3406a = null;
            }
        }
    }

    public float a() {
        return this.f3407b;
    }

    public float b() {
        return this.f3408c;
    }
}
