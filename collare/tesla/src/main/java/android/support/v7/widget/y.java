package android.support.v7.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.a.f;
import android.support.v4.widget.b;
import android.support.v4.widget.p;
import android.support.v7.a.a;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.ref.WeakReference;

/* access modifiers changed from: package-private */
/* compiled from: AppCompatTextHelper */
public class y {

    /* renamed from: a  reason: collision with root package name */
    private final TextView f1300a;

    /* renamed from: b  reason: collision with root package name */
    private at f1301b;

    /* renamed from: c  reason: collision with root package name */
    private at f1302c;

    /* renamed from: d  reason: collision with root package name */
    private at f1303d;
    private at e;
    private at f;
    private at g;
    private final aa h;
    private int i = 0;
    private Typeface j;
    private boolean k;

    y(TextView textView) {
        this.f1300a = textView;
        this.h = new aa(this.f1300a);
    }

    /* access modifiers changed from: package-private */
    @SuppressLint({"NewApi"})
    public void a(AttributeSet attributeSet, int i2) {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        boolean z;
        boolean z2;
        Context context = this.f1300a.getContext();
        k a2 = k.a();
        av a3 = av.a(context, attributeSet, a.j.AppCompatTextHelper, i2, 0);
        int g2 = a3.g(a.j.AppCompatTextHelper_android_textAppearance, -1);
        if (a3.g(a.j.AppCompatTextHelper_android_drawableLeft)) {
            this.f1301b = a(context, a2, a3.g(a.j.AppCompatTextHelper_android_drawableLeft, 0));
        }
        if (a3.g(a.j.AppCompatTextHelper_android_drawableTop)) {
            this.f1302c = a(context, a2, a3.g(a.j.AppCompatTextHelper_android_drawableTop, 0));
        }
        if (a3.g(a.j.AppCompatTextHelper_android_drawableRight)) {
            this.f1303d = a(context, a2, a3.g(a.j.AppCompatTextHelper_android_drawableRight, 0));
        }
        if (a3.g(a.j.AppCompatTextHelper_android_drawableBottom)) {
            this.e = a(context, a2, a3.g(a.j.AppCompatTextHelper_android_drawableBottom, 0));
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (a3.g(a.j.AppCompatTextHelper_android_drawableStart)) {
                this.f = a(context, a2, a3.g(a.j.AppCompatTextHelper_android_drawableStart, 0));
            }
            if (a3.g(a.j.AppCompatTextHelper_android_drawableEnd)) {
                this.g = a(context, a2, a3.g(a.j.AppCompatTextHelper_android_drawableEnd, 0));
            }
        }
        a3.a();
        boolean z3 = this.f1300a.getTransformationMethod() instanceof PasswordTransformationMethod;
        boolean z4 = true;
        ColorStateList colorStateList3 = null;
        if (g2 != -1) {
            av a4 = av.a(context, g2, a.j.TextAppearance);
            if (z3 || !a4.g(a.j.TextAppearance_textAllCaps)) {
                z2 = false;
                z = false;
            } else {
                z = a4.a(a.j.TextAppearance_textAllCaps, false);
                z2 = true;
            }
            a(context, a4);
            if (Build.VERSION.SDK_INT < 23) {
                ColorStateList e2 = a4.g(a.j.TextAppearance_android_textColor) ? a4.e(a.j.TextAppearance_android_textColor) : null;
                colorStateList = a4.g(a.j.TextAppearance_android_textColorHint) ? a4.e(a.j.TextAppearance_android_textColorHint) : null;
                if (a4.g(a.j.TextAppearance_android_textColorLink)) {
                    colorStateList2 = a4.e(a.j.TextAppearance_android_textColorLink);
                    colorStateList3 = e2;
                } else {
                    colorStateList2 = null;
                    colorStateList3 = e2;
                }
            } else {
                colorStateList2 = null;
                colorStateList = null;
            }
            a4.a();
        } else {
            colorStateList2 = null;
            colorStateList = null;
            z2 = false;
            z = false;
        }
        av a5 = av.a(context, attributeSet, a.j.TextAppearance, i2, 0);
        if (z3 || !a5.g(a.j.TextAppearance_textAllCaps)) {
            z4 = z2;
        } else {
            z = a5.a(a.j.TextAppearance_textAllCaps, false);
        }
        if (Build.VERSION.SDK_INT < 23) {
            if (a5.g(a.j.TextAppearance_android_textColor)) {
                colorStateList3 = a5.e(a.j.TextAppearance_android_textColor);
            }
            if (a5.g(a.j.TextAppearance_android_textColorHint)) {
                colorStateList = a5.e(a.j.TextAppearance_android_textColorHint);
            }
            if (a5.g(a.j.TextAppearance_android_textColorLink)) {
                colorStateList2 = a5.e(a.j.TextAppearance_android_textColorLink);
            }
        }
        if (Build.VERSION.SDK_INT >= 28 && a5.g(a.j.TextAppearance_android_textSize) && a5.e(a.j.TextAppearance_android_textSize, -1) == 0) {
            this.f1300a.setTextSize(0, BitmapDescriptorFactory.HUE_RED);
        }
        a(context, a5);
        a5.a();
        if (colorStateList3 != null) {
            this.f1300a.setTextColor(colorStateList3);
        }
        if (colorStateList != null) {
            this.f1300a.setHintTextColor(colorStateList);
        }
        if (colorStateList2 != null) {
            this.f1300a.setLinkTextColor(colorStateList2);
        }
        if (!z3 && z4) {
            a(z);
        }
        Typeface typeface = this.j;
        if (typeface != null) {
            this.f1300a.setTypeface(typeface, this.i);
        }
        this.h.a(attributeSet, i2);
        if (b.f692a && this.h.a() != 0) {
            int[] e3 = this.h.e();
            if (e3.length > 0) {
                if (((float) this.f1300a.getAutoSizeStepGranularity()) != -1.0f) {
                    this.f1300a.setAutoSizeTextTypeUniformWithConfiguration(this.h.c(), this.h.d(), this.h.b(), 0);
                } else {
                    this.f1300a.setAutoSizeTextTypeUniformWithPresetSizes(e3, 0);
                }
            }
        }
        av a6 = av.a(context, attributeSet, a.j.AppCompatTextView);
        int e4 = a6.e(a.j.AppCompatTextView_firstBaselineToTopHeight, -1);
        int e5 = a6.e(a.j.AppCompatTextView_lastBaselineToBottomHeight, -1);
        int e6 = a6.e(a.j.AppCompatTextView_lineHeight, -1);
        a6.a();
        if (e4 != -1) {
            p.a(this.f1300a, e4);
        }
        if (e5 != -1) {
            p.b(this.f1300a, e5);
        }
        if (e6 != -1) {
            p.c(this.f1300a, e6);
        }
    }

    private void a(Context context, av avVar) {
        String d2;
        this.i = avVar.a(a.j.TextAppearance_android_textStyle, this.i);
        boolean z = true;
        if (avVar.g(a.j.TextAppearance_android_fontFamily) || avVar.g(a.j.TextAppearance_fontFamily)) {
            this.j = null;
            int i2 = avVar.g(a.j.TextAppearance_fontFamily) ? a.j.TextAppearance_fontFamily : a.j.TextAppearance_android_fontFamily;
            if (!context.isRestricted()) {
                final WeakReference weakReference = new WeakReference(this.f1300a);
                try {
                    this.j = avVar.a(i2, this.i, new f.a() {
                        /* class android.support.v7.widget.y.AnonymousClass1 */

                        @Override // android.support.v4.content.a.f.a
                        public void a(int i) {
                        }

                        @Override // android.support.v4.content.a.f.a
                        public void a(Typeface typeface) {
                            y.this.a(weakReference, typeface);
                        }
                    });
                    if (this.j != null) {
                        z = false;
                    }
                    this.k = z;
                } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
                }
            }
            if (this.j == null && (d2 = avVar.d(i2)) != null) {
                this.j = Typeface.create(d2, this.i);
            }
        } else if (avVar.g(a.j.TextAppearance_android_typeface)) {
            this.k = false;
            switch (avVar.a(a.j.TextAppearance_android_typeface, 1)) {
                case 1:
                    this.j = Typeface.SANS_SERIF;
                    return;
                case 2:
                    this.j = Typeface.SERIF;
                    return;
                case 3:
                    this.j = Typeface.MONOSPACE;
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(WeakReference<TextView> weakReference, Typeface typeface) {
        if (this.k) {
            this.j = typeface;
            TextView textView = weakReference.get();
            if (textView != null) {
                textView.setTypeface(typeface, this.i);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, int i2) {
        ColorStateList e2;
        av a2 = av.a(context, i2, a.j.TextAppearance);
        if (a2.g(a.j.TextAppearance_textAllCaps)) {
            a(a2.a(a.j.TextAppearance_textAllCaps, false));
        }
        if (Build.VERSION.SDK_INT < 23 && a2.g(a.j.TextAppearance_android_textColor) && (e2 = a2.e(a.j.TextAppearance_android_textColor)) != null) {
            this.f1300a.setTextColor(e2);
        }
        if (a2.g(a.j.TextAppearance_android_textSize) && a2.e(a.j.TextAppearance_android_textSize, -1) == 0) {
            this.f1300a.setTextSize(0, BitmapDescriptorFactory.HUE_RED);
        }
        a(context, a2);
        a2.a();
        Typeface typeface = this.j;
        if (typeface != null) {
            this.f1300a.setTypeface(typeface, this.i);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        this.f1300a.setAllCaps(z);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (!(this.f1301b == null && this.f1302c == null && this.f1303d == null && this.e == null)) {
            Drawable[] compoundDrawables = this.f1300a.getCompoundDrawables();
            a(compoundDrawables[0], this.f1301b);
            a(compoundDrawables[1], this.f1302c);
            a(compoundDrawables[2], this.f1303d);
            a(compoundDrawables[3], this.e);
        }
        if (Build.VERSION.SDK_INT < 17) {
            return;
        }
        if (this.f != null || this.g != null) {
            Drawable[] compoundDrawablesRelative = this.f1300a.getCompoundDrawablesRelative();
            a(compoundDrawablesRelative[0], this.f);
            a(compoundDrawablesRelative[2], this.g);
        }
    }

    private void a(Drawable drawable, at atVar) {
        if (drawable != null && atVar != null) {
            k.a(drawable, atVar, this.f1300a.getDrawableState());
        }
    }

    private static at a(Context context, k kVar, int i2) {
        ColorStateList b2 = kVar.b(context, i2);
        if (b2 == null) {
            return null;
        }
        at atVar = new at();
        atVar.f1191d = true;
        atVar.f1188a = b2;
        return atVar;
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z, int i2, int i3, int i4, int i5) {
        if (!b.f692a) {
            b();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, float f2) {
        if (!b.f692a && !c()) {
            b(i2, f2);
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.h.f();
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return this.h.g();
    }

    private void b(int i2, float f2) {
        this.h.a(i2, f2);
    }

    /* access modifiers changed from: package-private */
    public void a(int i2) {
        this.h.a(i2);
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3, int i4, int i5) {
        this.h.a(i2, i3, i4, i5);
    }

    /* access modifiers changed from: package-private */
    public void a(int[] iArr, int i2) {
        this.h.a(iArr, i2);
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return this.h.a();
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return this.h.b();
    }

    /* access modifiers changed from: package-private */
    public int f() {
        return this.h.c();
    }

    /* access modifiers changed from: package-private */
    public int g() {
        return this.h.d();
    }

    /* access modifiers changed from: package-private */
    public int[] h() {
        return this.h.e();
    }
}
