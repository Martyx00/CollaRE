package android.support.v7.c.a;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.a;

/* compiled from: DrawableWrapper */
public class c extends Drawable implements Drawable.Callback {

    /* renamed from: a  reason: collision with root package name */
    private Drawable f904a;

    public c(Drawable drawable) {
        a(drawable);
    }

    public void draw(Canvas canvas) {
        this.f904a.draw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        this.f904a.setBounds(rect);
    }

    public void setChangingConfigurations(int i) {
        this.f904a.setChangingConfigurations(i);
    }

    public int getChangingConfigurations() {
        return this.f904a.getChangingConfigurations();
    }

    public void setDither(boolean z) {
        this.f904a.setDither(z);
    }

    public void setFilterBitmap(boolean z) {
        this.f904a.setFilterBitmap(z);
    }

    public void setAlpha(int i) {
        this.f904a.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f904a.setColorFilter(colorFilter);
    }

    public boolean isStateful() {
        return this.f904a.isStateful();
    }

    public boolean setState(int[] iArr) {
        return this.f904a.setState(iArr);
    }

    public int[] getState() {
        return this.f904a.getState();
    }

    public void jumpToCurrentState() {
        a.a(this.f904a);
    }

    public Drawable getCurrent() {
        return this.f904a.getCurrent();
    }

    public boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2) || this.f904a.setVisible(z, z2);
    }

    public int getOpacity() {
        return this.f904a.getOpacity();
    }

    public Region getTransparentRegion() {
        return this.f904a.getTransparentRegion();
    }

    public int getIntrinsicWidth() {
        return this.f904a.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        return this.f904a.getIntrinsicHeight();
    }

    public int getMinimumWidth() {
        return this.f904a.getMinimumWidth();
    }

    public int getMinimumHeight() {
        return this.f904a.getMinimumHeight();
    }

    public boolean getPadding(Rect rect) {
        return this.f904a.getPadding(rect);
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

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        return this.f904a.setLevel(i);
    }

    public void setAutoMirrored(boolean z) {
        a.a(this.f904a, z);
    }

    public boolean isAutoMirrored() {
        return a.b(this.f904a);
    }

    public void setTint(int i) {
        a.a(this.f904a, i);
    }

    public void setTintList(ColorStateList colorStateList) {
        a.a(this.f904a, colorStateList);
    }

    public void setTintMode(PorterDuff.Mode mode) {
        a.a(this.f904a, mode);
    }

    public void setHotspot(float f, float f2) {
        a.a(this.f904a, f, f2);
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        a.a(this.f904a, i, i2, i3, i4);
    }

    public Drawable a() {
        return this.f904a;
    }

    public void a(Drawable drawable) {
        Drawable drawable2 = this.f904a;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f904a = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
    }
}
