package com.facebook.react.views.text;

import android.content.res.AssetManager;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/* compiled from: CustomStyleSpan */
public class c extends MetricAffectingSpan implements l {

    /* renamed from: a  reason: collision with root package name */
    private final AssetManager f3430a;

    /* renamed from: b  reason: collision with root package name */
    private final int f3431b;

    /* renamed from: c  reason: collision with root package name */
    private final int f3432c;

    /* renamed from: d  reason: collision with root package name */
    private final String f3433d;

    public c(int i, int i2, String str, AssetManager assetManager) {
        this.f3431b = i;
        this.f3432c = i2;
        this.f3433d = str;
        this.f3430a = assetManager;
    }

    public void updateDrawState(TextPaint textPaint) {
        a(textPaint, this.f3431b, this.f3432c, this.f3433d, this.f3430a);
    }

    public void updateMeasureState(TextPaint textPaint) {
        a(textPaint, this.f3431b, this.f3432c, this.f3433d, this.f3430a);
    }

    private static void a(Paint paint, int i, int i2, String str, AssetManager assetManager) {
        int i3;
        Typeface typeface = paint.getTypeface();
        int i4 = 0;
        if (typeface == null) {
            i3 = 0;
        } else {
            i3 = typeface.getStyle();
        }
        if (i2 == 1 || ((i3 & 1) != 0 && i2 == -1)) {
            i4 = 1;
        }
        if (i == 2 || ((2 & i3) != 0 && i == -1)) {
            i4 |= 2;
        }
        if (str != null) {
            typeface = i.a().a(str, i4, assetManager);
        } else if (typeface != null) {
            typeface = Typeface.create(typeface, i4);
        }
        if (typeface != null) {
            paint.setTypeface(typeface);
        } else {
            paint.setTypeface(Typeface.defaultFromStyle(i4));
        }
        paint.setSubpixelText(true);
    }
}
