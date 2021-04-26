package com.facebook.react.views.text;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.uimanager.o;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: TextAttributes */
public class w {

    /* renamed from: a  reason: collision with root package name */
    private boolean f3472a = true;

    /* renamed from: b  reason: collision with root package name */
    private float f3473b = Float.NaN;

    /* renamed from: c  reason: collision with root package name */
    private float f3474c = Float.NaN;

    /* renamed from: d  reason: collision with root package name */
    private float f3475d = Float.NaN;
    private float e = Float.NaN;
    private float f = Float.NaN;
    private z g = z.UNSET;

    public w a(w wVar) {
        w wVar2 = new w();
        wVar2.f3472a = this.f3472a;
        wVar2.f3473b = !Float.isNaN(wVar.f3473b) ? wVar.f3473b : this.f3473b;
        wVar2.f3474c = !Float.isNaN(wVar.f3474c) ? wVar.f3474c : this.f3474c;
        wVar2.f3475d = !Float.isNaN(wVar.f3475d) ? wVar.f3475d : this.f3475d;
        wVar2.e = !Float.isNaN(wVar.e) ? wVar.e : this.e;
        wVar2.f = !Float.isNaN(wVar.f) ? wVar.f : this.f;
        wVar2.g = wVar.g != z.UNSET ? wVar.g : this.g;
        return wVar2;
    }

    public boolean a() {
        return this.f3472a;
    }

    public void a(boolean z) {
        this.f3472a = z;
    }

    public float b() {
        return this.f3473b;
    }

    public void a(float f2) {
        this.f3473b = f2;
    }

    public float c() {
        return this.f3474c;
    }

    public void b(float f2) {
        this.f3474c = f2;
    }

    public float d() {
        return this.f3475d;
    }

    public void c(float f2) {
        this.f3475d = f2;
    }

    public float e() {
        return this.e;
    }

    public void d(float f2) {
        if (f2 == BitmapDescriptorFactory.HUE_RED || f2 >= 1.0f) {
            this.e = f2;
            return;
        }
        throw new JSApplicationIllegalArgumentException("maxFontSizeMultiplier must be NaN, 0, or >= 1");
    }

    public float f() {
        return this.f;
    }

    public void e(float f2) {
        this.f = f2;
    }

    public z g() {
        return this.g;
    }

    public void a(z zVar) {
        this.g = zVar;
    }

    public int h() {
        float f2 = !Float.isNaN(this.f3473b) ? this.f3473b : 14.0f;
        if (this.f3472a) {
            return (int) Math.ceil((double) o.a(f2, k()));
        }
        return (int) Math.ceil((double) o.a(f2));
    }

    public float i() {
        float f2;
        if (Float.isNaN(this.f3474c)) {
            return Float.NaN;
        }
        if (this.f3472a) {
            f2 = o.a(this.f3474c, k());
        } else {
            f2 = o.a(this.f3474c);
        }
        return !Float.isNaN(this.f) && (this.f > f2 ? 1 : (this.f == f2 ? 0 : -1)) > 0 ? this.f : f2;
    }

    public float j() {
        float f2;
        if (Float.isNaN(this.f3475d)) {
            return Float.NaN;
        }
        if (this.f3472a) {
            f2 = o.a(this.f3475d, k());
        } else {
            f2 = o.a(this.f3475d);
        }
        return f2 / ((float) h());
    }

    public float k() {
        return !Float.isNaN(this.e) ? this.e : BitmapDescriptorFactory.HUE_RED;
    }

    public String toString() {
        return "TextAttributes {\n  getAllowFontScaling(): " + a() + "\n  getFontSize(): " + b() + "\n  getEffectiveFontSize(): " + h() + "\n  getHeightOfTallestInlineImage(): " + f() + "\n  getLetterSpacing(): " + d() + "\n  getEffectiveLetterSpacing(): " + j() + "\n  getLineHeight(): " + c() + "\n  getEffectiveLineHeight(): " + i() + "\n  getTextTransform(): " + g() + "\n  getMaxFontSizeMultiplier(): " + e() + "\n  getEffectiveMaxFontSizeMultiplier(): " + k() + "\n}";
    }
}
