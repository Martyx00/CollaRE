package com.facebook.f.e;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* compiled from: ForwardingDrawable */
public class g extends Drawable implements Drawable.Callback, c, r, s {

    /* renamed from: d  reason: collision with root package name */
    private static final Matrix f1915d = new Matrix();

    /* renamed from: a  reason: collision with root package name */
    private Drawable f1916a;

    /* renamed from: b  reason: collision with root package name */
    protected s f1917b;

    /* renamed from: c  reason: collision with root package name */
    private final d f1918c = new d();

    public g(Drawable drawable) {
        this.f1916a = drawable;
        e.a(this.f1916a, this, this);
    }

    public Drawable b(Drawable drawable) {
        Drawable c2 = c(drawable);
        invalidateSelf();
        return c2;
    }

    /* access modifiers changed from: protected */
    public Drawable c(Drawable drawable) {
        Drawable drawable2 = this.f1916a;
        e.a(drawable2, null, null);
        e.a(drawable, null, null);
        e.a(drawable, this.f1918c);
        e.a(drawable, this);
        e.a(drawable, this, this);
        this.f1916a = drawable;
        return drawable2;
    }

    public int getOpacity() {
        return this.f1916a.getOpacity();
    }

    public void setAlpha(int i) {
        this.f1918c.a(i);
        this.f1916a.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f1918c.a(colorFilter);
        this.f1916a.setColorFilter(colorFilter);
    }

    public void setDither(boolean z) {
        this.f1918c.a(z);
        this.f1916a.setDither(z);
    }

    public void setFilterBitmap(boolean z) {
        this.f1918c.b(z);
        this.f1916a.setFilterBitmap(z);
    }

    public boolean setVisible(boolean z, boolean z2) {
        super.setVisible(z, z2);
        return this.f1916a.setVisible(z, z2);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        this.f1916a.setBounds(rect);
    }

    public Drawable.ConstantState getConstantState() {
        return this.f1916a.getConstantState();
    }

    public boolean isStateful() {
        return this.f1916a.isStateful();
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        return this.f1916a.setState(iArr);
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        return this.f1916a.setLevel(i);
    }

    public void draw(Canvas canvas) {
        this.f1916a.draw(canvas);
    }

    public int getIntrinsicWidth() {
        return this.f1916a.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        return this.f1916a.getIntrinsicHeight();
    }

    public boolean getPadding(Rect rect) {
        return this.f1916a.getPadding(rect);
    }

    public Drawable mutate() {
        this.f1916a.mutate();
        return this;
    }

    public Drawable getCurrent() {
        return this.f1916a;
    }

    @Override // com.facebook.f.e.c
    public Drawable a(Drawable drawable) {
        return b(drawable);
    }

    @Override // com.facebook.f.e.c
    public Drawable a() {
        return getCurrent();
    }

    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    @Override // com.facebook.f.e.r
    public void a(s sVar) {
        this.f1917b = sVar;
    }

    /* access modifiers changed from: protected */
    public void b(Matrix matrix) {
        s sVar = this.f1917b;
        if (sVar != null) {
            sVar.a(matrix);
        } else {
            matrix.reset();
        }
    }

    @Override // com.facebook.f.e.s
    public void a(Matrix matrix) {
        b(matrix);
    }

    @Override // com.facebook.f.e.s
    public void a(RectF rectF) {
        s sVar = this.f1917b;
        if (sVar != null) {
            sVar.a(rectF);
        } else {
            rectF.set(getBounds());
        }
    }

    @TargetApi(21)
    public void setHotspot(float f, float f2) {
        this.f1916a.setHotspot(f, f2);
    }
}
