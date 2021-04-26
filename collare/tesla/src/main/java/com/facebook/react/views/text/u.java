package com.facebook.react.views.text;

import android.text.TextPaint;
import android.text.style.CharacterStyle;

/* compiled from: ShadowStyleSpan */
public class u extends CharacterStyle implements l {

    /* renamed from: a  reason: collision with root package name */
    private final float f3464a;

    /* renamed from: b  reason: collision with root package name */
    private final float f3465b;

    /* renamed from: c  reason: collision with root package name */
    private final float f3466c;

    /* renamed from: d  reason: collision with root package name */
    private final int f3467d;

    public u(float f, float f2, float f3, int i) {
        this.f3464a = f;
        this.f3465b = f2;
        this.f3466c = f3;
        this.f3467d = i;
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setShadowLayer(this.f3466c, this.f3464a, this.f3465b, this.f3467d);
    }
}
