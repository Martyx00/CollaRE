package android.support.v7.c.a;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;
import android.util.SparseArray;
import org.spongycastle.crypto.tls.CipherSuite;

/* access modifiers changed from: package-private */
/* compiled from: DrawableContainer */
public class b extends Drawable implements Drawable.Callback {

    /* renamed from: a  reason: collision with root package name */
    private AbstractC0026b f896a;

    /* renamed from: b  reason: collision with root package name */
    private Rect f897b;

    /* renamed from: c  reason: collision with root package name */
    private Drawable f898c;

    /* renamed from: d  reason: collision with root package name */
    private Drawable f899d;
    private int e = 255;
    private boolean f;
    private int g = -1;
    private int h = -1;
    private boolean i;
    private Runnable j;
    private long k;
    private long l;
    private a m;

    b() {
    }

    public void draw(Canvas canvas) {
        Drawable drawable = this.f898c;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        Drawable drawable2 = this.f899d;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
    }

    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.f896a.getChangingConfigurations();
    }

    @SuppressLint({"WrongConstant"})
    @TargetApi(23)
    private boolean a() {
        return isAutoMirrored() && getLayoutDirection() == 1;
    }

    public boolean getPadding(Rect rect) {
        boolean z;
        Rect e2 = this.f896a.e();
        if (e2 != null) {
            rect.set(e2);
            z = (e2.right | ((e2.left | e2.top) | e2.bottom)) != 0;
        } else {
            Drawable drawable = this.f898c;
            if (drawable != null) {
                z = drawable.getPadding(rect);
            } else {
                z = super.getPadding(rect);
            }
        }
        if (a()) {
            int i2 = rect.left;
            rect.left = rect.right;
            rect.right = i2;
        }
        return z;
    }

    public void getOutline(Outline outline) {
        Drawable drawable = this.f898c;
        if (drawable != null) {
            drawable.getOutline(outline);
        }
    }

    public void setAlpha(int i2) {
        if (!this.f || this.e != i2) {
            this.f = true;
            this.e = i2;
            Drawable drawable = this.f898c;
            if (drawable == null) {
                return;
            }
            if (this.k == 0) {
                drawable.setAlpha(i2);
            } else {
                a(false);
            }
        }
    }

    public int getAlpha() {
        return this.e;
    }

    public void setDither(boolean z) {
        if (this.f896a.z != z) {
            AbstractC0026b bVar = this.f896a;
            bVar.z = z;
            Drawable drawable = this.f898c;
            if (drawable != null) {
                drawable.setDither(bVar.z);
            }
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        AbstractC0026b bVar = this.f896a;
        bVar.G = true;
        if (bVar.F != colorFilter) {
            this.f896a.F = colorFilter;
            Drawable drawable = this.f898c;
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        AbstractC0026b bVar = this.f896a;
        bVar.J = true;
        if (bVar.H != colorStateList) {
            this.f896a.H = colorStateList;
            android.support.v4.graphics.drawable.a.a(this.f898c, colorStateList);
        }
    }

    public void setTintMode(PorterDuff.Mode mode) {
        AbstractC0026b bVar = this.f896a;
        bVar.K = true;
        if (bVar.I != mode) {
            this.f896a.I = mode;
            android.support.v4.graphics.drawable.a.a(this.f898c, mode);
        }
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f899d;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
        Drawable drawable2 = this.f898c;
        if (drawable2 != null) {
            drawable2.setBounds(rect);
        }
    }

    public boolean isStateful() {
        return this.f896a.m();
    }

    public void setAutoMirrored(boolean z) {
        if (this.f896a.E != z) {
            AbstractC0026b bVar = this.f896a;
            bVar.E = z;
            Drawable drawable = this.f898c;
            if (drawable != null) {
                android.support.v4.graphics.drawable.a.a(drawable, bVar.E);
            }
        }
    }

    public boolean isAutoMirrored() {
        return this.f896a.E;
    }

    public void jumpToCurrentState() {
        boolean z;
        Drawable drawable = this.f899d;
        if (drawable != null) {
            drawable.jumpToCurrentState();
            this.f899d = null;
            this.h = -1;
            z = true;
        } else {
            z = false;
        }
        Drawable drawable2 = this.f898c;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
            if (this.f) {
                this.f898c.setAlpha(this.e);
            }
        }
        if (this.l != 0) {
            this.l = 0;
            z = true;
        }
        if (this.k != 0) {
            this.k = 0;
            z = true;
        }
        if (z) {
            invalidateSelf();
        }
    }

    public void setHotspot(float f2, float f3) {
        Drawable drawable = this.f898c;
        if (drawable != null) {
            android.support.v4.graphics.drawable.a.a(drawable, f2, f3);
        }
    }

    public void setHotspotBounds(int i2, int i3, int i4, int i5) {
        Rect rect = this.f897b;
        if (rect == null) {
            this.f897b = new Rect(i2, i3, i4, i5);
        } else {
            rect.set(i2, i3, i4, i5);
        }
        Drawable drawable = this.f898c;
        if (drawable != null) {
            android.support.v4.graphics.drawable.a.a(drawable, i2, i3, i4, i5);
        }
    }

    public void getHotspotBounds(Rect rect) {
        Rect rect2 = this.f897b;
        if (rect2 != null) {
            rect.set(rect2);
        } else {
            super.getHotspotBounds(rect);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f899d;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        Drawable drawable2 = this.f898c;
        if (drawable2 != null) {
            return drawable2.setState(iArr);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i2) {
        Drawable drawable = this.f899d;
        if (drawable != null) {
            return drawable.setLevel(i2);
        }
        Drawable drawable2 = this.f898c;
        if (drawable2 != null) {
            return drawable2.setLevel(i2);
        }
        return false;
    }

    public boolean onLayoutDirectionChanged(int i2) {
        return this.f896a.d(i2, d());
    }

    public int getIntrinsicWidth() {
        if (this.f896a.f()) {
            return this.f896a.g();
        }
        Drawable drawable = this.f898c;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return -1;
    }

    public int getIntrinsicHeight() {
        if (this.f896a.f()) {
            return this.f896a.h();
        }
        Drawable drawable = this.f898c;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return -1;
    }

    public int getMinimumWidth() {
        if (this.f896a.f()) {
            return this.f896a.i();
        }
        Drawable drawable = this.f898c;
        if (drawable != null) {
            return drawable.getMinimumWidth();
        }
        return 0;
    }

    public int getMinimumHeight() {
        if (this.f896a.f()) {
            return this.f896a.j();
        }
        Drawable drawable = this.f898c;
        if (drawable != null) {
            return drawable.getMinimumHeight();
        }
        return 0;
    }

    public void invalidateDrawable(Drawable drawable) {
        AbstractC0026b bVar = this.f896a;
        if (bVar != null) {
            bVar.b();
        }
        if (drawable == this.f898c && getCallback() != null) {
            getCallback().invalidateDrawable(this);
        }
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
        if (drawable == this.f898c && getCallback() != null) {
            getCallback().scheduleDrawable(this, runnable, j2);
        }
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        if (drawable == this.f898c && getCallback() != null) {
            getCallback().unscheduleDrawable(this, runnable);
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        Drawable drawable = this.f899d;
        if (drawable != null) {
            drawable.setVisible(z, z2);
        }
        Drawable drawable2 = this.f898c;
        if (drawable2 != null) {
            drawable2.setVisible(z, z2);
        }
        return visible;
    }

    public int getOpacity() {
        Drawable drawable = this.f898c;
        if (drawable == null || !drawable.isVisible()) {
            return -2;
        }
        return this.f896a.l();
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i2) {
        if (i2 == this.g) {
            return false;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        if (this.f896a.D > 0) {
            Drawable drawable = this.f899d;
            if (drawable != null) {
                drawable.setVisible(false, false);
            }
            Drawable drawable2 = this.f898c;
            if (drawable2 != null) {
                this.f899d = drawable2;
                this.h = this.g;
                this.l = ((long) this.f896a.D) + uptimeMillis;
            } else {
                this.f899d = null;
                this.h = -1;
                this.l = 0;
            }
        } else {
            Drawable drawable3 = this.f898c;
            if (drawable3 != null) {
                drawable3.setVisible(false, false);
            }
        }
        if (i2 < 0 || i2 >= this.f896a.j) {
            this.f898c = null;
            this.g = -1;
        } else {
            Drawable b2 = this.f896a.b(i2);
            this.f898c = b2;
            this.g = i2;
            if (b2 != null) {
                if (this.f896a.C > 0) {
                    this.k = uptimeMillis + ((long) this.f896a.C);
                }
                a(b2);
            }
        }
        if (!(this.k == 0 && this.l == 0)) {
            Runnable runnable = this.j;
            if (runnable == null) {
                this.j = new Runnable() {
                    /* class android.support.v7.c.a.b.AnonymousClass1 */

                    public void run() {
                        b.this.a(true);
                        b.this.invalidateSelf();
                    }
                };
            } else {
                unscheduleSelf(runnable);
            }
            a(true);
        }
        invalidateSelf();
        return true;
    }

    private void a(Drawable drawable) {
        if (this.m == null) {
            this.m = new a();
        }
        drawable.setCallback(this.m.a(drawable.getCallback()));
        try {
            if (this.f896a.C <= 0 && this.f) {
                drawable.setAlpha(this.e);
            }
            if (this.f896a.G) {
                drawable.setColorFilter(this.f896a.F);
            } else {
                if (this.f896a.J) {
                    android.support.v4.graphics.drawable.a.a(drawable, this.f896a.H);
                }
                if (this.f896a.K) {
                    android.support.v4.graphics.drawable.a.a(drawable, this.f896a.I);
                }
            }
            drawable.setVisible(isVisible(), true);
            drawable.setDither(this.f896a.z);
            drawable.setState(getState());
            drawable.setLevel(getLevel());
            drawable.setBounds(getBounds());
            if (Build.VERSION.SDK_INT >= 23) {
                drawable.setLayoutDirection(getLayoutDirection());
            }
            if (Build.VERSION.SDK_INT >= 19) {
                drawable.setAutoMirrored(this.f896a.E);
            }
            Rect rect = this.f897b;
            if (Build.VERSION.SDK_INT >= 21 && rect != null) {
                drawable.setHotspotBounds(rect.left, rect.top, rect.right, rect.bottom);
            }
        } finally {
            drawable.setCallback(this.m.a());
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0071 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(boolean r14) {
        /*
        // Method dump skipped, instructions count: 124
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.c.a.b.a(boolean):void");
    }

    public Drawable getCurrent() {
        return this.f898c;
    }

    /* access modifiers changed from: package-private */
    public final void a(Resources resources) {
        this.f896a.a(resources);
    }

    public void applyTheme(Resources.Theme theme) {
        this.f896a.a(theme);
    }

    public boolean canApplyTheme() {
        return this.f896a.canApplyTheme();
    }

    public final Drawable.ConstantState getConstantState() {
        if (!this.f896a.n()) {
            return null;
        }
        this.f896a.f = getChangingConfigurations();
        return this.f896a;
    }

    public Drawable mutate() {
        if (!this.i && super.mutate() == this) {
            AbstractC0026b c2 = c();
            c2.a();
            a(c2);
            this.i = true;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public AbstractC0026b c() {
        return this.f896a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.support.v7.c.a.b$b  reason: collision with other inner class name */
    /* compiled from: DrawableContainer */
    public static abstract class AbstractC0026b extends Drawable.ConstantState {
        boolean A;
        int B;
        int C;
        int D;
        boolean E;
        ColorFilter F;
        boolean G;
        ColorStateList H;
        PorterDuff.Mode I;
        boolean J;
        boolean K;

        /* renamed from: c  reason: collision with root package name */
        final b f902c;

        /* renamed from: d  reason: collision with root package name */
        Resources f903d;
        int e = CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256;
        int f;
        int g;
        SparseArray<Drawable.ConstantState> h;
        Drawable[] i;
        int j;
        boolean k;
        boolean l;
        Rect m;
        boolean n;
        boolean o;
        int p;
        int q;
        int r;
        int s;
        boolean t;
        int u;
        boolean v;
        boolean w;
        boolean x;
        boolean y;
        boolean z;

        AbstractC0026b(AbstractC0026b bVar, b bVar2, Resources resources) {
            Resources resources2;
            this.k = false;
            this.n = false;
            this.z = true;
            this.C = 0;
            this.D = 0;
            this.f902c = bVar2;
            if (resources != null) {
                resources2 = resources;
            } else {
                resources2 = bVar != null ? bVar.f903d : null;
            }
            this.f903d = resources2;
            this.e = b.a(resources, bVar != null ? bVar.e : 0);
            if (bVar != null) {
                this.f = bVar.f;
                this.g = bVar.g;
                this.x = true;
                this.y = true;
                this.k = bVar.k;
                this.n = bVar.n;
                this.z = bVar.z;
                this.A = bVar.A;
                this.B = bVar.B;
                this.C = bVar.C;
                this.D = bVar.D;
                this.E = bVar.E;
                this.F = bVar.F;
                this.G = bVar.G;
                this.H = bVar.H;
                this.I = bVar.I;
                this.J = bVar.J;
                this.K = bVar.K;
                if (bVar.e == this.e) {
                    if (bVar.l) {
                        this.m = new Rect(bVar.m);
                        this.l = true;
                    }
                    if (bVar.o) {
                        this.p = bVar.p;
                        this.q = bVar.q;
                        this.r = bVar.r;
                        this.s = bVar.s;
                        this.o = true;
                    }
                }
                if (bVar.t) {
                    this.u = bVar.u;
                    this.t = true;
                }
                if (bVar.v) {
                    this.w = bVar.w;
                    this.v = true;
                }
                Drawable[] drawableArr = bVar.i;
                this.i = new Drawable[drawableArr.length];
                this.j = bVar.j;
                SparseArray<Drawable.ConstantState> sparseArray = bVar.h;
                if (sparseArray != null) {
                    this.h = sparseArray.clone();
                } else {
                    this.h = new SparseArray<>(this.j);
                }
                int i2 = this.j;
                for (int i3 = 0; i3 < i2; i3++) {
                    if (drawableArr[i3] != null) {
                        Drawable.ConstantState constantState = drawableArr[i3].getConstantState();
                        if (constantState != null) {
                            this.h.put(i3, constantState);
                        } else {
                            this.i[i3] = drawableArr[i3];
                        }
                    }
                }
                return;
            }
            this.i = new Drawable[10];
            this.j = 0;
        }

        public int getChangingConfigurations() {
            return this.f | this.g;
        }

        public final int a(Drawable drawable) {
            int i2 = this.j;
            if (i2 >= this.i.length) {
                e(i2, i2 + 10);
            }
            drawable.mutate();
            drawable.setVisible(false, true);
            drawable.setCallback(this.f902c);
            this.i[i2] = drawable;
            this.j++;
            this.g = drawable.getChangingConfigurations() | this.g;
            b();
            this.m = null;
            this.l = false;
            this.o = false;
            this.x = false;
            return i2;
        }

        /* access modifiers changed from: package-private */
        public void b() {
            this.t = false;
            this.v = false;
        }

        /* access modifiers changed from: package-private */
        public final int c() {
            return this.i.length;
        }

        private void o() {
            SparseArray<Drawable.ConstantState> sparseArray = this.h;
            if (sparseArray != null) {
                int size = sparseArray.size();
                for (int i2 = 0; i2 < size; i2++) {
                    this.i[this.h.keyAt(i2)] = b(this.h.valueAt(i2).newDrawable(this.f903d));
                }
                this.h = null;
            }
        }

        private Drawable b(Drawable drawable) {
            if (Build.VERSION.SDK_INT >= 23) {
                drawable.setLayoutDirection(this.B);
            }
            Drawable mutate = drawable.mutate();
            mutate.setCallback(this.f902c);
            return mutate;
        }

        public final int d() {
            return this.j;
        }

        public final Drawable b(int i2) {
            int indexOfKey;
            Drawable drawable = this.i[i2];
            if (drawable != null) {
                return drawable;
            }
            SparseArray<Drawable.ConstantState> sparseArray = this.h;
            if (sparseArray == null || (indexOfKey = sparseArray.indexOfKey(i2)) < 0) {
                return null;
            }
            Drawable b2 = b(this.h.valueAt(indexOfKey).newDrawable(this.f903d));
            this.i[i2] = b2;
            this.h.removeAt(indexOfKey);
            if (this.h.size() == 0) {
                this.h = null;
            }
            return b2;
        }

        /* access modifiers changed from: package-private */
        public final boolean d(int i2, int i3) {
            int i4 = this.j;
            Drawable[] drawableArr = this.i;
            boolean z2 = false;
            for (int i5 = 0; i5 < i4; i5++) {
                if (drawableArr[i5] != null) {
                    boolean layoutDirection = Build.VERSION.SDK_INT >= 23 ? drawableArr[i5].setLayoutDirection(i2) : false;
                    if (i5 == i3) {
                        z2 = layoutDirection;
                    }
                }
            }
            this.B = i2;
            return z2;
        }

        /* access modifiers changed from: package-private */
        public final void a(Resources resources) {
            if (resources != null) {
                this.f903d = resources;
                int a2 = b.a(resources, this.e);
                int i2 = this.e;
                this.e = a2;
                if (i2 != a2) {
                    this.o = false;
                    this.l = false;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final void a(Resources.Theme theme) {
            if (theme != null) {
                o();
                int i2 = this.j;
                Drawable[] drawableArr = this.i;
                for (int i3 = 0; i3 < i2; i3++) {
                    if (drawableArr[i3] != null && drawableArr[i3].canApplyTheme()) {
                        drawableArr[i3].applyTheme(theme);
                        this.g |= drawableArr[i3].getChangingConfigurations();
                    }
                }
                a(theme.getResources());
            }
        }

        public boolean canApplyTheme() {
            int i2 = this.j;
            Drawable[] drawableArr = this.i;
            for (int i3 = 0; i3 < i2; i3++) {
                Drawable drawable = drawableArr[i3];
                if (drawable == null) {
                    Drawable.ConstantState constantState = this.h.get(i3);
                    if (constantState != null && constantState.canApplyTheme()) {
                        return true;
                    }
                } else if (drawable.canApplyTheme()) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            int i2 = this.j;
            Drawable[] drawableArr = this.i;
            for (int i3 = 0; i3 < i2; i3++) {
                if (drawableArr[i3] != null) {
                    drawableArr[i3].mutate();
                }
            }
            this.A = true;
        }

        public final void a(boolean z2) {
            this.k = z2;
        }

        public final Rect e() {
            if (this.k) {
                return null;
            }
            if (this.m != null || this.l) {
                return this.m;
            }
            o();
            Rect rect = new Rect();
            int i2 = this.j;
            Drawable[] drawableArr = this.i;
            Rect rect2 = null;
            for (int i3 = 0; i3 < i2; i3++) {
                if (drawableArr[i3].getPadding(rect)) {
                    if (rect2 == null) {
                        rect2 = new Rect(0, 0, 0, 0);
                    }
                    if (rect.left > rect2.left) {
                        rect2.left = rect.left;
                    }
                    if (rect.top > rect2.top) {
                        rect2.top = rect.top;
                    }
                    if (rect.right > rect2.right) {
                        rect2.right = rect.right;
                    }
                    if (rect.bottom > rect2.bottom) {
                        rect2.bottom = rect.bottom;
                    }
                }
            }
            this.l = true;
            this.m = rect2;
            return rect2;
        }

        public final void b(boolean z2) {
            this.n = z2;
        }

        public final boolean f() {
            return this.n;
        }

        public final int g() {
            if (!this.o) {
                k();
            }
            return this.p;
        }

        public final int h() {
            if (!this.o) {
                k();
            }
            return this.q;
        }

        public final int i() {
            if (!this.o) {
                k();
            }
            return this.r;
        }

        public final int j() {
            if (!this.o) {
                k();
            }
            return this.s;
        }

        /* access modifiers changed from: protected */
        public void k() {
            this.o = true;
            o();
            int i2 = this.j;
            Drawable[] drawableArr = this.i;
            this.q = -1;
            this.p = -1;
            this.s = 0;
            this.r = 0;
            for (int i3 = 0; i3 < i2; i3++) {
                Drawable drawable = drawableArr[i3];
                int intrinsicWidth = drawable.getIntrinsicWidth();
                if (intrinsicWidth > this.p) {
                    this.p = intrinsicWidth;
                }
                int intrinsicHeight = drawable.getIntrinsicHeight();
                if (intrinsicHeight > this.q) {
                    this.q = intrinsicHeight;
                }
                int minimumWidth = drawable.getMinimumWidth();
                if (minimumWidth > this.r) {
                    this.r = minimumWidth;
                }
                int minimumHeight = drawable.getMinimumHeight();
                if (minimumHeight > this.s) {
                    this.s = minimumHeight;
                }
            }
        }

        public final void c(int i2) {
            this.C = i2;
        }

        public final void d(int i2) {
            this.D = i2;
        }

        public final int l() {
            if (this.t) {
                return this.u;
            }
            o();
            int i2 = this.j;
            Drawable[] drawableArr = this.i;
            int opacity = i2 > 0 ? drawableArr[0].getOpacity() : -2;
            for (int i3 = 1; i3 < i2; i3++) {
                opacity = Drawable.resolveOpacity(opacity, drawableArr[i3].getOpacity());
            }
            this.u = opacity;
            this.t = true;
            return opacity;
        }

        public final boolean m() {
            if (this.v) {
                return this.w;
            }
            o();
            int i2 = this.j;
            Drawable[] drawableArr = this.i;
            boolean z2 = false;
            int i3 = 0;
            while (true) {
                if (i3 >= i2) {
                    break;
                } else if (drawableArr[i3].isStateful()) {
                    z2 = true;
                    break;
                } else {
                    i3++;
                }
            }
            this.w = z2;
            this.v = true;
            return z2;
        }

        public void e(int i2, int i3) {
            Drawable[] drawableArr = new Drawable[i3];
            System.arraycopy(this.i, 0, drawableArr, 0, i2);
            this.i = drawableArr;
        }

        public synchronized boolean n() {
            if (this.x) {
                return this.y;
            }
            o();
            this.x = true;
            int i2 = this.j;
            Drawable[] drawableArr = this.i;
            for (int i3 = 0; i3 < i2; i3++) {
                if (drawableArr[i3].getConstantState() == null) {
                    this.y = false;
                    return false;
                }
            }
            this.y = true;
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void a(AbstractC0026b bVar) {
        this.f896a = bVar;
        int i2 = this.g;
        if (i2 >= 0) {
            this.f898c = bVar.b(i2);
            Drawable drawable = this.f898c;
            if (drawable != null) {
                a(drawable);
            }
        }
        this.h = -1;
        this.f899d = null;
    }

    /* access modifiers changed from: package-private */
    /* compiled from: DrawableContainer */
    public static class a implements Drawable.Callback {

        /* renamed from: a  reason: collision with root package name */
        private Drawable.Callback f901a;

        public void invalidateDrawable(Drawable drawable) {
        }

        a() {
        }

        public a a(Drawable.Callback callback) {
            this.f901a = callback;
            return this;
        }

        public Drawable.Callback a() {
            Drawable.Callback callback = this.f901a;
            this.f901a = null;
            return callback;
        }

        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            Drawable.Callback callback = this.f901a;
            if (callback != null) {
                callback.scheduleDrawable(drawable, runnable, j);
            }
        }

        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            Drawable.Callback callback = this.f901a;
            if (callback != null) {
                callback.unscheduleDrawable(drawable, runnable);
            }
        }
    }

    static int a(Resources resources, int i2) {
        if (resources != null) {
            i2 = resources.getDisplayMetrics().densityDpi;
        }
        return i2 == 0 ? CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256 : i2;
    }
}
