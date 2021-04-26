package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;

/* access modifiers changed from: package-private */
/* compiled from: WrappedDrawableApi14 */
public class d extends Drawable implements Drawable.Callback, b, c {

    /* renamed from: a  reason: collision with root package name */
    static final PorterDuff.Mode f559a = PorterDuff.Mode.SRC_IN;

    /* renamed from: b  reason: collision with root package name */
    a f560b;

    /* renamed from: c  reason: collision with root package name */
    Drawable f561c;

    /* renamed from: d  reason: collision with root package name */
    private int f562d;
    private PorterDuff.Mode e;
    private boolean f;
    private boolean g;

    /* access modifiers changed from: protected */
    public boolean c() {
        return true;
    }

    d(a aVar, Resources resources) {
        this.f560b = aVar;
        a(resources);
    }

    d(Drawable drawable) {
        this.f560b = b();
        a(drawable);
    }

    private void a(Resources resources) {
        a aVar = this.f560b;
        if (aVar != null && aVar.f564b != null) {
            a(this.f560b.f564b.newDrawable(resources));
        }
    }

    public void jumpToCurrentState() {
        this.f561c.jumpToCurrentState();
    }

    public void draw(Canvas canvas) {
        this.f561c.draw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f561c;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    public void setChangingConfigurations(int i) {
        this.f561c.setChangingConfigurations(i);
    }

    public int getChangingConfigurations() {
        int changingConfigurations = super.getChangingConfigurations();
        a aVar = this.f560b;
        return changingConfigurations | (aVar != null ? aVar.getChangingConfigurations() : 0) | this.f561c.getChangingConfigurations();
    }

    public void setDither(boolean z) {
        this.f561c.setDither(z);
    }

    public void setFilterBitmap(boolean z) {
        this.f561c.setFilterBitmap(z);
    }

    public void setAlpha(int i) {
        this.f561c.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f561c.setColorFilter(colorFilter);
    }

    public boolean isStateful() {
        a aVar;
        ColorStateList colorStateList = (!c() || (aVar = this.f560b) == null) ? null : aVar.f565c;
        return (colorStateList != null && colorStateList.isStateful()) || this.f561c.isStateful();
    }

    public boolean setState(int[] iArr) {
        return a(iArr) || this.f561c.setState(iArr);
    }

    public int[] getState() {
        return this.f561c.getState();
    }

    public Drawable getCurrent() {
        return this.f561c.getCurrent();
    }

    public boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2) || this.f561c.setVisible(z, z2);
    }

    public int getOpacity() {
        return this.f561c.getOpacity();
    }

    public Region getTransparentRegion() {
        return this.f561c.getTransparentRegion();
    }

    public int getIntrinsicWidth() {
        return this.f561c.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        return this.f561c.getIntrinsicHeight();
    }

    public int getMinimumWidth() {
        return this.f561c.getMinimumWidth();
    }

    public int getMinimumHeight() {
        return this.f561c.getMinimumHeight();
    }

    public boolean getPadding(Rect rect) {
        return this.f561c.getPadding(rect);
    }

    public void setAutoMirrored(boolean z) {
        this.f561c.setAutoMirrored(z);
    }

    public boolean isAutoMirrored() {
        return this.f561c.isAutoMirrored();
    }

    public Drawable.ConstantState getConstantState() {
        a aVar = this.f560b;
        if (aVar == null || !aVar.a()) {
            return null;
        }
        this.f560b.f563a = getChangingConfigurations();
        return this.f560b;
    }

    public Drawable mutate() {
        if (!this.g && super.mutate() == this) {
            this.f560b = b();
            Drawable drawable = this.f561c;
            if (drawable != null) {
                drawable.mutate();
            }
            a aVar = this.f560b;
            if (aVar != null) {
                Drawable drawable2 = this.f561c;
                aVar.f564b = drawable2 != null ? drawable2.getConstantState() : null;
            }
            this.g = true;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public a b() {
        return new b(this.f560b, null);
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
        return this.f561c.setLevel(i);
    }

    @Override // android.support.v4.graphics.drawable.b
    public void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.support.v4.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        this.f560b.f565c = colorStateList;
        a(getState());
    }

    @Override // android.support.v4.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        this.f560b.f566d = mode;
        a(getState());
    }

    private boolean a(int[] iArr) {
        if (!c()) {
            return false;
        }
        ColorStateList colorStateList = this.f560b.f565c;
        PorterDuff.Mode mode = this.f560b.f566d;
        if (colorStateList == null || mode == null) {
            this.f = false;
            clearColorFilter();
        } else {
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (!(this.f && colorForState == this.f562d && mode == this.e)) {
                setColorFilter(colorForState, mode);
                this.f562d = colorForState;
                this.e = mode;
                this.f = true;
                return true;
            }
        }
        return false;
    }

    @Override // android.support.v4.graphics.drawable.c
    public final Drawable a() {
        return this.f561c;
    }

    @Override // android.support.v4.graphics.drawable.c
    public final void a(Drawable drawable) {
        Drawable drawable2 = this.f561c;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f561c = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            setVisible(drawable.isVisible(), true);
            setState(drawable.getState());
            setLevel(drawable.getLevel());
            setBounds(drawable.getBounds());
            a aVar = this.f560b;
            if (aVar != null) {
                aVar.f564b = drawable.getConstantState();
            }
        }
        invalidateSelf();
    }

    /* access modifiers changed from: protected */
    /* compiled from: WrappedDrawableApi14 */
    public static abstract class a extends Drawable.ConstantState {

        /* renamed from: a  reason: collision with root package name */
        int f563a;

        /* renamed from: b  reason: collision with root package name */
        Drawable.ConstantState f564b;

        /* renamed from: c  reason: collision with root package name */
        ColorStateList f565c = null;

        /* renamed from: d  reason: collision with root package name */
        PorterDuff.Mode f566d = d.f559a;

        public abstract Drawable newDrawable(Resources resources);

        a(a aVar, Resources resources) {
            if (aVar != null) {
                this.f563a = aVar.f563a;
                this.f564b = aVar.f564b;
                this.f565c = aVar.f565c;
                this.f566d = aVar.f566d;
            }
        }

        public Drawable newDrawable() {
            return newDrawable(null);
        }

        public int getChangingConfigurations() {
            int i = this.f563a;
            Drawable.ConstantState constantState = this.f564b;
            return i | (constantState != null ? constantState.getChangingConfigurations() : 0);
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return this.f564b != null;
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: WrappedDrawableApi14 */
    public static class b extends a {
        b(a aVar, Resources resources) {
            super(aVar, resources);
        }

        @Override // android.support.v4.graphics.drawable.d.a
        public Drawable newDrawable(Resources resources) {
            return new d(this, resources);
        }
    }
}
