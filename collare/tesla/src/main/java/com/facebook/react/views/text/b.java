package com.facebook.react.views.text;

import android.graphics.Paint;
import android.text.style.LineHeightSpan;

/* compiled from: CustomLineHeightSpan */
public class b implements LineHeightSpan, l {

    /* renamed from: a  reason: collision with root package name */
    private final int f3429a;

    b(float f) {
        this.f3429a = (int) Math.ceil((double) f);
    }

    public void chooseHeight(CharSequence charSequence, int i, int i2, int i3, int i4, Paint.FontMetricsInt fontMetricsInt) {
        int i5 = fontMetricsInt.descent;
        int i6 = this.f3429a;
        if (i5 > i6) {
            int min = Math.min(i6, fontMetricsInt.descent);
            fontMetricsInt.descent = min;
            fontMetricsInt.bottom = min;
            fontMetricsInt.ascent = 0;
            fontMetricsInt.top = 0;
        } else if ((-fontMetricsInt.ascent) + fontMetricsInt.descent > this.f3429a) {
            fontMetricsInt.bottom = fontMetricsInt.descent;
            int i7 = (-this.f3429a) + fontMetricsInt.descent;
            fontMetricsInt.ascent = i7;
            fontMetricsInt.top = i7;
        } else if ((-fontMetricsInt.ascent) + fontMetricsInt.bottom > this.f3429a) {
            fontMetricsInt.top = fontMetricsInt.ascent;
            fontMetricsInt.bottom = fontMetricsInt.ascent + this.f3429a;
        } else {
            int i8 = (-fontMetricsInt.top) + fontMetricsInt.bottom;
            int i9 = this.f3429a;
            if (i8 > i9) {
                fontMetricsInt.top = fontMetricsInt.bottom - this.f3429a;
                return;
            }
            int i10 = i9 - ((-fontMetricsInt.top) + fontMetricsInt.bottom);
            double d2 = (double) (((float) i10) / 2.0f);
            fontMetricsInt.top = (int) (((double) fontMetricsInt.top) - Math.ceil(d2));
            fontMetricsInt.bottom = (int) (((double) fontMetricsInt.bottom) + Math.floor(d2));
            fontMetricsInt.ascent = fontMetricsInt.top;
            fontMetricsInt.descent = fontMetricsInt.bottom;
        }
    }
}
