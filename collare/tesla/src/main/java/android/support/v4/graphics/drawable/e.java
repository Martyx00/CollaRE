package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.v4.graphics.drawable.d;
import android.util.Log;
import java.lang.reflect.Method;

/* access modifiers changed from: package-private */
/* compiled from: WrappedDrawableApi21 */
public class e extends d {

    /* renamed from: d  reason: collision with root package name */
    private static Method f567d;

    e(Drawable drawable) {
        super(drawable);
        d();
    }

    e(d.a aVar, Resources resources) {
        super(aVar, resources);
        d();
    }

    public void setHotspot(float f, float f2) {
        this.f561c.setHotspot(f, f2);
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        this.f561c.setHotspotBounds(i, i2, i3, i4);
    }

    public void getOutline(Outline outline) {
        this.f561c.getOutline(outline);
    }

    public Rect getDirtyBounds() {
        return this.f561c.getDirtyBounds();
    }

    @Override // android.support.v4.graphics.drawable.b, android.support.v4.graphics.drawable.d
    public void setTintList(ColorStateList colorStateList) {
        if (c()) {
            super.setTintList(colorStateList);
        } else {
            this.f561c.setTintList(colorStateList);
        }
    }

    @Override // android.support.v4.graphics.drawable.b, android.support.v4.graphics.drawable.d
    public void setTint(int i) {
        if (c()) {
            super.setTint(i);
        } else {
            this.f561c.setTint(i);
        }
    }

    @Override // android.support.v4.graphics.drawable.b, android.support.v4.graphics.drawable.d
    public void setTintMode(PorterDuff.Mode mode) {
        if (c()) {
            super.setTintMode(mode);
        } else {
            this.f561c.setTintMode(mode);
        }
    }

    @Override // android.support.v4.graphics.drawable.d
    public boolean setState(int[] iArr) {
        if (!super.setState(iArr)) {
            return false;
        }
        invalidateSelf();
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v4.graphics.drawable.d
    public boolean c() {
        if (Build.VERSION.SDK_INT != 21) {
            return false;
        }
        Drawable drawable = this.f561c;
        if ((drawable instanceof GradientDrawable) || (drawable instanceof DrawableContainer) || (drawable instanceof InsetDrawable) || (drawable instanceof RippleDrawable)) {
            return true;
        }
        return false;
    }

    public boolean isProjected() {
        Method method;
        if (!(this.f561c == null || (method = f567d) == null)) {
            try {
                return ((Boolean) method.invoke(this.f561c, new Object[0])).booleanValue();
            } catch (Exception e) {
                Log.w("WrappedDrawableApi21", "Error calling Drawable#isProjected() method", e);
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    @Override // android.support.v4.graphics.drawable.d
    public d.a b() {
        return new a(this.f560b, null);
    }

    /* compiled from: WrappedDrawableApi21 */
    private static class a extends d.a {
        a(d.a aVar, Resources resources) {
            super(aVar, resources);
        }

        @Override // android.support.v4.graphics.drawable.d.a
        public Drawable newDrawable(Resources resources) {
            return new e(this, resources);
        }
    }

    private void d() {
        if (f567d == null) {
            try {
                f567d = Drawable.class.getDeclaredMethod("isProjected", new Class[0]);
            } catch (Exception e) {
                Log.w("WrappedDrawableApi21", "Failed to retrieve Drawable#isProjected() method", e);
            }
        }
    }
}
