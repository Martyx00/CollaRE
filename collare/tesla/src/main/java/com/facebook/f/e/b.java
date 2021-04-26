package com.facebook.f.e;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.facebook.common.d.i;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: AutoRotateDrawable */
public class b extends g implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    float f1904a;

    /* renamed from: c  reason: collision with root package name */
    private int f1905c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f1906d;
    private boolean e;

    public b(Drawable drawable, int i) {
        this(drawable, i, true);
    }

    public b(Drawable drawable, int i, boolean z) {
        super((Drawable) i.a(drawable));
        this.f1904a = BitmapDescriptorFactory.HUE_RED;
        this.e = false;
        this.f1905c = i;
        this.f1906d = z;
    }

    @Override // com.facebook.f.e.g
    public void draw(Canvas canvas) {
        int save = canvas.save();
        Rect bounds = getBounds();
        int i = bounds.right - bounds.left;
        int i2 = bounds.bottom - bounds.top;
        float f = this.f1904a;
        if (!this.f1906d) {
            f = 360.0f - f;
        }
        canvas.rotate(f, (float) (bounds.left + (i / 2)), (float) (bounds.top + (i2 / 2)));
        super.draw(canvas);
        canvas.restoreToCount(save);
        b();
    }

    public void run() {
        this.e = false;
        this.f1904a += (float) c();
        invalidateSelf();
    }

    private void b() {
        if (!this.e) {
            this.e = true;
            scheduleSelf(this, SystemClock.uptimeMillis() + 20);
        }
    }

    private int c() {
        return (int) ((20.0f / ((float) this.f1905c)) * 360.0f);
    }
}
