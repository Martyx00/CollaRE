package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.widget.e;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.widget.CompoundButton;

/* access modifiers changed from: package-private */
/* compiled from: AppCompatCompoundButtonHelper */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private final CompoundButton f1251a;

    /* renamed from: b  reason: collision with root package name */
    private ColorStateList f1252b = null;

    /* renamed from: c  reason: collision with root package name */
    private PorterDuff.Mode f1253c = null;

    /* renamed from: d  reason: collision with root package name */
    private boolean f1254d = false;
    private boolean e = false;
    private boolean f;

    j(CompoundButton compoundButton) {
        this.f1251a = compoundButton;
    }

    /* access modifiers changed from: package-private */
    public void a(AttributeSet attributeSet, int i) {
        int resourceId;
        TypedArray obtainStyledAttributes = this.f1251a.getContext().obtainStyledAttributes(attributeSet, a.j.CompoundButton, i, 0);
        try {
            if (obtainStyledAttributes.hasValue(a.j.CompoundButton_android_button) && (resourceId = obtainStyledAttributes.getResourceId(a.j.CompoundButton_android_button, 0)) != 0) {
                this.f1251a.setButtonDrawable(android.support.v7.b.a.a.b(this.f1251a.getContext(), resourceId));
            }
            if (obtainStyledAttributes.hasValue(a.j.CompoundButton_buttonTint)) {
                e.a(this.f1251a, obtainStyledAttributes.getColorStateList(a.j.CompoundButton_buttonTint));
            }
            if (obtainStyledAttributes.hasValue(a.j.CompoundButton_buttonTintMode)) {
                e.a(this.f1251a, ad.a(obtainStyledAttributes.getInt(a.j.CompoundButton_buttonTintMode, -1), null));
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(ColorStateList colorStateList) {
        this.f1252b = colorStateList;
        this.f1254d = true;
        d();
    }

    /* access modifiers changed from: package-private */
    public ColorStateList a() {
        return this.f1252b;
    }

    /* access modifiers changed from: package-private */
    public void a(PorterDuff.Mode mode) {
        this.f1253c = mode;
        this.e = true;
        d();
    }

    /* access modifiers changed from: package-private */
    public PorterDuff.Mode b() {
        return this.f1253c;
    }

    /* access modifiers changed from: package-private */
    public void c() {
        if (this.f) {
            this.f = false;
            return;
        }
        this.f = true;
        d();
    }

    /* access modifiers changed from: package-private */
    public void d() {
        Drawable a2 = e.a(this.f1251a);
        if (a2 == null) {
            return;
        }
        if (this.f1254d || this.e) {
            Drawable mutate = android.support.v4.graphics.drawable.a.f(a2).mutate();
            if (this.f1254d) {
                android.support.v4.graphics.drawable.a.a(mutate, this.f1252b);
            }
            if (this.e) {
                android.support.v4.graphics.drawable.a.a(mutate, this.f1253c);
            }
            if (mutate.isStateful()) {
                mutate.setState(this.f1251a.getDrawableState());
            }
            this.f1251a.setButtonDrawable(mutate);
        }
    }

    /* access modifiers changed from: package-private */
    public int a(int i) {
        Drawable a2;
        return (Build.VERSION.SDK_INT >= 17 || (a2 = e.a(this.f1251a)) == null) ? i : i + a2.getIntrinsicWidth();
    }
}
