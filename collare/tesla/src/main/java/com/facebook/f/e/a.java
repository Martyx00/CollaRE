package com.facebook.f.e;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.common.d.i;

/* compiled from: ArrayDrawable */
public class a extends Drawable implements Drawable.Callback, r, s {

    /* renamed from: a  reason: collision with root package name */
    private s f1898a;

    /* renamed from: b  reason: collision with root package name */
    private final d f1899b = new d();

    /* renamed from: c  reason: collision with root package name */
    private final Drawable[] f1900c;

    /* renamed from: d  reason: collision with root package name */
    private final c[] f1901d;
    private final Rect e = new Rect();
    private boolean f;
    private boolean g;
    private boolean h;

    public a(Drawable[] drawableArr) {
        int i = 0;
        this.f = false;
        this.g = false;
        this.h = false;
        i.a(drawableArr);
        this.f1900c = drawableArr;
        while (true) {
            Drawable[] drawableArr2 = this.f1900c;
            if (i < drawableArr2.length) {
                e.a(drawableArr2[i], this, this);
                i++;
            } else {
                this.f1901d = new c[drawableArr2.length];
                return;
            }
        }
    }

    public int a() {
        return this.f1900c.length;
    }

    public Drawable a(int i) {
        boolean z = true;
        i.a(i >= 0);
        if (i >= this.f1900c.length) {
            z = false;
        }
        i.a(z);
        return this.f1900c[i];
    }

    public Drawable a(int i, Drawable drawable) {
        boolean z = true;
        i.a(i >= 0);
        if (i >= this.f1900c.length) {
            z = false;
        }
        i.a(z);
        Drawable drawable2 = this.f1900c[i];
        if (drawable != drawable2) {
            if (drawable != null && this.h) {
                drawable.mutate();
            }
            e.a(this.f1900c[i], null, null);
            e.a(drawable, null, null);
            e.a(drawable, this.f1899b);
            e.a(drawable, this);
            e.a(drawable, this, this);
            this.g = false;
            this.f1900c[i] = drawable;
            invalidateSelf();
        }
        return drawable2;
    }

    public int getIntrinsicWidth() {
        int i = 0;
        int i2 = -1;
        while (true) {
            Drawable[] drawableArr = this.f1900c;
            if (i >= drawableArr.length) {
                break;
            }
            Drawable drawable = drawableArr[i];
            if (drawable != null) {
                i2 = Math.max(i2, drawable.getIntrinsicWidth());
            }
            i++;
        }
        if (i2 > 0) {
            return i2;
        }
        return -1;
    }

    public int getIntrinsicHeight() {
        int i = 0;
        int i2 = -1;
        while (true) {
            Drawable[] drawableArr = this.f1900c;
            if (i >= drawableArr.length) {
                break;
            }
            Drawable drawable = drawableArr[i];
            if (drawable != null) {
                i2 = Math.max(i2, drawable.getIntrinsicHeight());
            }
            i++;
        }
        if (i2 > 0) {
            return i2;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.f1900c;
            if (i < drawableArr.length) {
                Drawable drawable = drawableArr[i];
                if (drawable != null) {
                    drawable.setBounds(rect);
                }
                i++;
            } else {
                return;
            }
        }
    }

    public boolean isStateful() {
        if (!this.g) {
            this.f = false;
            int i = 0;
            while (true) {
                Drawable[] drawableArr = this.f1900c;
                boolean z = true;
                if (i >= drawableArr.length) {
                    break;
                }
                Drawable drawable = drawableArr[i];
                boolean z2 = this.f;
                if (drawable == null || !drawable.isStateful()) {
                    z = false;
                }
                this.f = z2 | z;
                i++;
            }
            this.g = true;
        }
        return this.f;
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        int i = 0;
        boolean z = false;
        while (true) {
            Drawable[] drawableArr = this.f1900c;
            if (i >= drawableArr.length) {
                return z;
            }
            Drawable drawable = drawableArr[i];
            if (drawable != null && drawable.setState(iArr)) {
                z = true;
            }
            i++;
        }
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        int i2 = 0;
        boolean z = false;
        while (true) {
            Drawable[] drawableArr = this.f1900c;
            if (i2 >= drawableArr.length) {
                return z;
            }
            Drawable drawable = drawableArr[i2];
            if (drawable != null && drawable.setLevel(i)) {
                z = true;
            }
            i2++;
        }
    }

    public void draw(Canvas canvas) {
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.f1900c;
            if (i < drawableArr.length) {
                Drawable drawable = drawableArr[i];
                if (drawable != null) {
                    drawable.draw(canvas);
                }
                i++;
            } else {
                return;
            }
        }
    }

    public boolean getPadding(Rect rect) {
        int i = 0;
        rect.left = 0;
        rect.top = 0;
        rect.right = 0;
        rect.bottom = 0;
        Rect rect2 = this.e;
        while (true) {
            Drawable[] drawableArr = this.f1900c;
            if (i >= drawableArr.length) {
                return true;
            }
            Drawable drawable = drawableArr[i];
            if (drawable != null) {
                drawable.getPadding(rect2);
                rect.left = Math.max(rect.left, rect2.left);
                rect.top = Math.max(rect.top, rect2.top);
                rect.right = Math.max(rect.right, rect2.right);
                rect.bottom = Math.max(rect.bottom, rect2.bottom);
            }
            i++;
        }
    }

    public Drawable mutate() {
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.f1900c;
            if (i < drawableArr.length) {
                Drawable drawable = drawableArr[i];
                if (drawable != null) {
                    drawable.mutate();
                }
                i++;
            } else {
                this.h = true;
                return this;
            }
        }
    }

    public int getOpacity() {
        if (this.f1900c.length == 0) {
            return -2;
        }
        int i = -1;
        int i2 = 1;
        while (true) {
            Drawable[] drawableArr = this.f1900c;
            if (i2 >= drawableArr.length) {
                return i;
            }
            Drawable drawable = drawableArr[i2];
            if (drawable != null) {
                i = Drawable.resolveOpacity(i, drawable.getOpacity());
            }
            i2++;
        }
    }

    public void setAlpha(int i) {
        this.f1899b.a(i);
        int i2 = 0;
        while (true) {
            Drawable[] drawableArr = this.f1900c;
            if (i2 < drawableArr.length) {
                Drawable drawable = drawableArr[i2];
                if (drawable != null) {
                    drawable.setAlpha(i);
                }
                i2++;
            } else {
                return;
            }
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f1899b.a(colorFilter);
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.f1900c;
            if (i < drawableArr.length) {
                Drawable drawable = drawableArr[i];
                if (drawable != null) {
                    drawable.setColorFilter(colorFilter);
                }
                i++;
            } else {
                return;
            }
        }
    }

    public void setDither(boolean z) {
        this.f1899b.a(z);
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.f1900c;
            if (i < drawableArr.length) {
                Drawable drawable = drawableArr[i];
                if (drawable != null) {
                    drawable.setDither(z);
                }
                i++;
            } else {
                return;
            }
        }
    }

    public void setFilterBitmap(boolean z) {
        this.f1899b.b(z);
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.f1900c;
            if (i < drawableArr.length) {
                Drawable drawable = drawableArr[i];
                if (drawable != null) {
                    drawable.setFilterBitmap(z);
                }
                i++;
            } else {
                return;
            }
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.f1900c;
            if (i >= drawableArr.length) {
                return visible;
            }
            Drawable drawable = drawableArr[i];
            if (drawable != null) {
                drawable.setVisible(z, z2);
            }
            i++;
        }
    }

    public c b(int i) {
        boolean z = true;
        i.a(i >= 0);
        if (i >= this.f1901d.length) {
            z = false;
        }
        i.a(z);
        c[] cVarArr = this.f1901d;
        if (cVarArr[i] == null) {
            cVarArr[i] = c(i);
        }
        return this.f1901d[i];
    }

    private c c(final int i) {
        return new c() {
            /* class com.facebook.f.e.a.AnonymousClass1 */

            @Override // com.facebook.f.e.c
            public Drawable a(Drawable drawable) {
                return a.this.a(i, drawable);
            }

            @Override // com.facebook.f.e.c
            public Drawable a() {
                return a.this.a(i);
            }
        };
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
        this.f1898a = sVar;
    }

    @Override // com.facebook.f.e.s
    public void a(Matrix matrix) {
        s sVar = this.f1898a;
        if (sVar != null) {
            sVar.a(matrix);
        } else {
            matrix.reset();
        }
    }

    @Override // com.facebook.f.e.s
    public void a(RectF rectF) {
        s sVar = this.f1898a;
        if (sVar != null) {
            sVar.a(rectF);
        } else {
            rectF.set(getBounds());
        }
    }

    @TargetApi(21)
    public void setHotspot(float f2, float f3) {
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.f1900c;
            if (i < drawableArr.length) {
                Drawable drawable = drawableArr[i];
                if (drawable != null) {
                    drawable.setHotspot(f2, f3);
                }
                i++;
            } else {
                return;
            }
        }
    }
}
