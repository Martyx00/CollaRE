package com.facebook.react.views.swiperefresh;

import android.support.v4.widget.o;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.events.f;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: ReactSwipeRefreshLayout */
public class a extends o {
    private boolean n = false;
    private boolean o = false;
    private float p = BitmapDescriptorFactory.HUE_RED;
    private int q;
    private float r;
    private boolean s;

    public a(ReactContext reactContext) {
        super(reactContext);
        this.q = ViewConfiguration.get(reactContext).getScaledTouchSlop();
    }

    @Override // android.support.v4.widget.o
    public void setRefreshing(boolean z) {
        this.o = z;
        if (this.n) {
            super.setRefreshing(z);
        }
    }

    public void setProgressViewOffset(float f) {
        this.p = f;
        if (this.n) {
            int progressCircleDiameter = getProgressCircleDiameter();
            a(false, Math.round(com.facebook.react.uimanager.o.a(f)) - progressCircleDiameter, Math.round(com.facebook.react.uimanager.o.a(f + 64.0f) - ((float) progressCircleDiameter)));
        }
    }

    @Override // android.support.v4.widget.o
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (!this.n) {
            this.n = true;
            setProgressViewOffset(this.p);
            setRefreshing(this.o);
        }
    }

    @Override // android.support.v4.widget.o
    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(z);
        }
    }

    @Override // android.support.v4.widget.o
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!a(motionEvent) || !super.onInterceptTouchEvent(motionEvent)) {
            return false;
        }
        f.a(this, motionEvent);
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return true;
    }

    private boolean a(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.r = motionEvent.getX();
            this.s = false;
        } else if (action == 2) {
            float abs = Math.abs(motionEvent.getX() - this.r);
            if (this.s || abs > ((float) this.q)) {
                this.s = true;
                return false;
            }
        }
        return true;
    }
}
