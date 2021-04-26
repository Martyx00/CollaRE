package com.google.android.gms.internal.base;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public final class zae extends Drawable implements Drawable.Callback {
    private int mAlpha;
    private int mFrom;
    private boolean zamz;
    private int zanh;
    private long zani;
    private int zanj;
    private int zank;
    private int zanl;
    private boolean zanm;
    private zai zann;
    private Drawable zano;
    private Drawable zanp;
    private boolean zanq;
    private boolean zanr;
    private boolean zans;
    private int zant;

    public zae(Drawable drawable, Drawable drawable2) {
        this(null);
        drawable = drawable == null ? zag.zacg() : drawable;
        this.zano = drawable;
        drawable.setCallback(this);
        zai zai = this.zann;
        zai.zanw = drawable.getChangingConfigurations() | zai.zanw;
        drawable2 = drawable2 == null ? zag.zacg() : drawable2;
        this.zanp = drawable2;
        drawable2.setCallback(this);
        zai zai2 = this.zann;
        zai2.zanw = drawable2.getChangingConfigurations() | zai2.zanw;
    }

    zae(zai zai) {
        this.zanh = 0;
        this.zank = 255;
        this.mAlpha = 0;
        this.zamz = true;
        this.zann = new zai(zai);
    }

    public final void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public final int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.zann.mChangingConfigurations | this.zann.zanw;
    }

    public final void setAlpha(int i) {
        if (this.mAlpha == this.zank) {
            this.mAlpha = i;
        }
        this.zank = i;
        invalidateSelf();
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.zano.setColorFilter(colorFilter);
        this.zanp.setColorFilter(colorFilter);
    }

    public final int getIntrinsicWidth() {
        return Math.max(this.zano.getIntrinsicWidth(), this.zanp.getIntrinsicWidth());
    }

    public final int getIntrinsicHeight() {
        return Math.max(this.zano.getIntrinsicHeight(), this.zanp.getIntrinsicHeight());
    }

    /* access modifiers changed from: protected */
    public final void onBoundsChange(Rect rect) {
        this.zano.setBounds(rect);
        this.zanp.setBounds(rect);
    }

    public final Drawable.ConstantState getConstantState() {
        if (!canConstantState()) {
            return null;
        }
        this.zann.mChangingConfigurations = getChangingConfigurations();
        return this.zann;
    }

    public final int getOpacity() {
        if (!this.zans) {
            this.zant = Drawable.resolveOpacity(this.zano.getOpacity(), this.zanp.getOpacity());
            this.zans = true;
        }
        return this.zant;
    }

    private final boolean canConstantState() {
        if (!this.zanq) {
            this.zanr = (this.zano.getConstantState() == null || this.zanp.getConstantState() == null) ? false : true;
            this.zanq = true;
        }
        return this.zanr;
    }

    public final Drawable mutate() {
        if (!this.zanm && super.mutate() == this) {
            if (canConstantState()) {
                this.zano.mutate();
                this.zanp.mutate();
                this.zanm = true;
            } else {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
        }
        return this;
    }

    public final Drawable zacf() {
        return this.zanp;
    }

    public final void startTransition(int i) {
        this.mFrom = 0;
        this.zanj = this.zank;
        this.mAlpha = 0;
        this.zanl = 250;
        this.zanh = 1;
        invalidateSelf();
    }

    public final void draw(Canvas canvas) {
        boolean z = true;
        switch (this.zanh) {
            case 1:
                this.zani = SystemClock.uptimeMillis();
                this.zanh = 2;
                z = false;
                break;
            case 2:
                if (this.zani >= 0) {
                    float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.zani)) / ((float) this.zanl);
                    if (uptimeMillis < 1.0f) {
                        z = false;
                    }
                    if (z) {
                        this.zanh = 0;
                    }
                    this.mAlpha = (int) ((((float) this.zanj) * Math.min(uptimeMillis, 1.0f)) + BitmapDescriptorFactory.HUE_RED);
                    break;
                }
                break;
        }
        int i = this.mAlpha;
        boolean z2 = this.zamz;
        Drawable drawable = this.zano;
        Drawable drawable2 = this.zanp;
        if (z) {
            if (!z2 || i == 0) {
                drawable.draw(canvas);
            }
            int i2 = this.zank;
            if (i == i2) {
                drawable2.setAlpha(i2);
                drawable2.draw(canvas);
                return;
            }
            return;
        }
        if (z2) {
            drawable.setAlpha(this.zank - i);
        }
        drawable.draw(canvas);
        if (z2) {
            drawable.setAlpha(this.zank);
        }
        if (i > 0) {
            drawable2.setAlpha(i);
            drawable2.draw(canvas);
            drawable2.setAlpha(this.zank);
        }
        invalidateSelf();
    }
}
