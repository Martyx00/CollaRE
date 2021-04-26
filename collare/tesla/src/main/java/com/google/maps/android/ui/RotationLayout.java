package com.google.maps.android.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class RotationLayout extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private int f4021a;

    public RotationLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3 = this.f4021a;
        if (i3 == 1 || i3 == 3) {
            super.onMeasure(i, i2);
            setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
            return;
        }
        super.onMeasure(i, i2);
    }

    public void setViewRotation(int i) {
        this.f4021a = ((i + 360) % 360) / 90;
    }

    public void dispatchDraw(Canvas canvas) {
        int i = this.f4021a;
        if (i == 0) {
            super.dispatchDraw(canvas);
            return;
        }
        if (i == 1) {
            canvas.translate((float) getWidth(), BitmapDescriptorFactory.HUE_RED);
            canvas.rotate(90.0f, (float) (getWidth() / 2), BitmapDescriptorFactory.HUE_RED);
            canvas.translate((float) (getHeight() / 2), (float) (getWidth() / 2));
        } else if (i == 2) {
            canvas.rotate(180.0f, (float) (getWidth() / 2), (float) (getHeight() / 2));
        } else {
            canvas.translate(BitmapDescriptorFactory.HUE_RED, (float) getHeight());
            canvas.rotate(270.0f, (float) (getWidth() / 2), BitmapDescriptorFactory.HUE_RED);
            canvas.translate((float) (getHeight() / 2), (float) ((-getWidth()) / 2));
        }
        super.dispatchDraw(canvas);
    }
}
