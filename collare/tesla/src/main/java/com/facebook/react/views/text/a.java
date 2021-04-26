package com.facebook.react.views.text;

import android.annotation.TargetApi;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

@TargetApi(21)
/* compiled from: CustomLetterSpacingSpan */
public class a extends MetricAffectingSpan implements l {

    /* renamed from: a  reason: collision with root package name */
    private final float f3428a;

    public a(float f) {
        this.f3428a = f;
    }

    public void updateDrawState(TextPaint textPaint) {
        a(textPaint);
    }

    public void updateMeasureState(TextPaint textPaint) {
        a(textPaint);
    }

    private void a(TextPaint textPaint) {
        if (!Float.isNaN(this.f3428a)) {
            textPaint.setLetterSpacing(this.f3428a);
        }
    }
}
