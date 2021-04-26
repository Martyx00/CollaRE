package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.g.t;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.view.View;

/* access modifiers changed from: package-private */
/* compiled from: AppCompatBackgroundHelper */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private final View f1242a;

    /* renamed from: b  reason: collision with root package name */
    private final k f1243b;

    /* renamed from: c  reason: collision with root package name */
    private int f1244c = -1;

    /* renamed from: d  reason: collision with root package name */
    private at f1245d;
    private at e;
    private at f;

    f(View view) {
        this.f1242a = view;
        this.f1243b = k.a();
    }

    /* access modifiers changed from: package-private */
    public void a(AttributeSet attributeSet, int i) {
        av a2 = av.a(this.f1242a.getContext(), attributeSet, a.j.ViewBackgroundHelper, i, 0);
        try {
            if (a2.g(a.j.ViewBackgroundHelper_android_background)) {
                this.f1244c = a2.g(a.j.ViewBackgroundHelper_android_background, -1);
                ColorStateList b2 = this.f1243b.b(this.f1242a.getContext(), this.f1244c);
                if (b2 != null) {
                    b(b2);
                }
            }
            if (a2.g(a.j.ViewBackgroundHelper_backgroundTint)) {
                t.a(this.f1242a, a2.e(a.j.ViewBackgroundHelper_backgroundTint));
            }
            if (a2.g(a.j.ViewBackgroundHelper_backgroundTintMode)) {
                t.a(this.f1242a, ad.a(a2.a(a.j.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
        } finally {
            a2.a();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i) {
        this.f1244c = i;
        k kVar = this.f1243b;
        b(kVar != null ? kVar.b(this.f1242a.getContext(), i) : null);
        c();
    }

    /* access modifiers changed from: package-private */
    public void a(Drawable drawable) {
        this.f1244c = -1;
        b((ColorStateList) null);
        c();
    }

    /* access modifiers changed from: package-private */
    public void a(ColorStateList colorStateList) {
        if (this.e == null) {
            this.e = new at();
        }
        at atVar = this.e;
        atVar.f1188a = colorStateList;
        atVar.f1191d = true;
        c();
    }

    /* access modifiers changed from: package-private */
    public ColorStateList a() {
        at atVar = this.e;
        if (atVar != null) {
            return atVar.f1188a;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void a(PorterDuff.Mode mode) {
        if (this.e == null) {
            this.e = new at();
        }
        at atVar = this.e;
        atVar.f1189b = mode;
        atVar.f1190c = true;
        c();
    }

    /* access modifiers changed from: package-private */
    public PorterDuff.Mode b() {
        at atVar = this.e;
        if (atVar != null) {
            return atVar.f1189b;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void c() {
        Drawable background = this.f1242a.getBackground();
        if (background == null) {
            return;
        }
        if (!d() || !b(background)) {
            at atVar = this.e;
            if (atVar != null) {
                k.a(background, atVar, this.f1242a.getDrawableState());
                return;
            }
            at atVar2 = this.f1245d;
            if (atVar2 != null) {
                k.a(background, atVar2, this.f1242a.getDrawableState());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f1245d == null) {
                this.f1245d = new at();
            }
            at atVar = this.f1245d;
            atVar.f1188a = colorStateList;
            atVar.f1191d = true;
        } else {
            this.f1245d = null;
        }
        c();
    }

    private boolean d() {
        int i = Build.VERSION.SDK_INT;
        if (i <= 21) {
            return i == 21;
        }
        if (this.f1245d != null) {
            return true;
        }
        return false;
    }

    private boolean b(Drawable drawable) {
        if (this.f == null) {
            this.f = new at();
        }
        at atVar = this.f;
        atVar.a();
        ColorStateList p = t.p(this.f1242a);
        if (p != null) {
            atVar.f1191d = true;
            atVar.f1188a = p;
        }
        PorterDuff.Mode q = t.q(this.f1242a);
        if (q != null) {
            atVar.f1190c = true;
            atVar.f1189b = q;
        }
        if (!atVar.f1191d && !atVar.f1190c) {
            return false;
        }
        k.a(drawable, atVar, this.f1242a.getDrawableState());
        return true;
    }
}
