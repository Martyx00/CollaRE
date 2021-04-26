package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.v4.widget.j;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.widget.ImageView;

/* compiled from: AppCompatImageHelper */
public class o {

    /* renamed from: a  reason: collision with root package name */
    private final ImageView f1263a;

    /* renamed from: b  reason: collision with root package name */
    private at f1264b;

    /* renamed from: c  reason: collision with root package name */
    private at f1265c;

    /* renamed from: d  reason: collision with root package name */
    private at f1266d;

    public o(ImageView imageView) {
        this.f1263a = imageView;
    }

    public void a(AttributeSet attributeSet, int i) {
        int g;
        av a2 = av.a(this.f1263a.getContext(), attributeSet, a.j.AppCompatImageView, i, 0);
        try {
            Drawable drawable = this.f1263a.getDrawable();
            if (!(drawable != null || (g = a2.g(a.j.AppCompatImageView_srcCompat, -1)) == -1 || (drawable = android.support.v7.b.a.a.b(this.f1263a.getContext(), g)) == null)) {
                this.f1263a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                ad.b(drawable);
            }
            if (a2.g(a.j.AppCompatImageView_tint)) {
                j.a(this.f1263a, a2.e(a.j.AppCompatImageView_tint));
            }
            if (a2.g(a.j.AppCompatImageView_tintMode)) {
                j.a(this.f1263a, ad.a(a2.a(a.j.AppCompatImageView_tintMode, -1), null));
            }
        } finally {
            a2.a();
        }
    }

    public void a(int i) {
        if (i != 0) {
            Drawable b2 = android.support.v7.b.a.a.b(this.f1263a.getContext(), i);
            if (b2 != null) {
                ad.b(b2);
            }
            this.f1263a.setImageDrawable(b2);
        } else {
            this.f1263a.setImageDrawable(null);
        }
        d();
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return Build.VERSION.SDK_INT < 21 || !(this.f1263a.getBackground() instanceof RippleDrawable);
    }

    /* access modifiers changed from: package-private */
    public void a(ColorStateList colorStateList) {
        if (this.f1265c == null) {
            this.f1265c = new at();
        }
        at atVar = this.f1265c;
        atVar.f1188a = colorStateList;
        atVar.f1191d = true;
        d();
    }

    /* access modifiers changed from: package-private */
    public ColorStateList b() {
        at atVar = this.f1265c;
        if (atVar != null) {
            return atVar.f1188a;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void a(PorterDuff.Mode mode) {
        if (this.f1265c == null) {
            this.f1265c = new at();
        }
        at atVar = this.f1265c;
        atVar.f1189b = mode;
        atVar.f1190c = true;
        d();
    }

    /* access modifiers changed from: package-private */
    public PorterDuff.Mode c() {
        at atVar = this.f1265c;
        if (atVar != null) {
            return atVar.f1189b;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void d() {
        Drawable drawable = this.f1263a.getDrawable();
        if (drawable != null) {
            ad.b(drawable);
        }
        if (drawable == null) {
            return;
        }
        if (!e() || !a(drawable)) {
            at atVar = this.f1265c;
            if (atVar != null) {
                k.a(drawable, atVar, this.f1263a.getDrawableState());
                return;
            }
            at atVar2 = this.f1264b;
            if (atVar2 != null) {
                k.a(drawable, atVar2, this.f1263a.getDrawableState());
            }
        }
    }

    private boolean e() {
        int i = Build.VERSION.SDK_INT;
        if (i <= 21) {
            return i == 21;
        }
        if (this.f1264b != null) {
            return true;
        }
        return false;
    }

    private boolean a(Drawable drawable) {
        if (this.f1266d == null) {
            this.f1266d = new at();
        }
        at atVar = this.f1266d;
        atVar.a();
        ColorStateList a2 = j.a(this.f1263a);
        if (a2 != null) {
            atVar.f1191d = true;
            atVar.f1188a = a2;
        }
        PorterDuff.Mode b2 = j.b(this.f1263a);
        if (b2 != null) {
            atVar.f1190c = true;
            atVar.f1189b = b2;
        }
        if (!atVar.f1191d && !atVar.f1190c) {
            return false;
        }
        k.a(drawable, atVar, this.f1263a.getDrawableState());
        return true;
    }
}
