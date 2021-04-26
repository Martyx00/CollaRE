package com.facebook.react.uimanager;

import android.util.DisplayMetrics;
import android.util.TypedValue;

/* compiled from: PixelUtil */
public class o {
    public static float a(float f) {
        return TypedValue.applyDimension(1, f, c.a());
    }

    public static float a(double d2) {
        return a((float) d2);
    }

    public static float b(float f) {
        return f / c.b().scaledDensity;
    }

    public static float c(float f) {
        return a(f, Float.NaN);
    }

    public static float a(float f, float f2) {
        DisplayMetrics a2 = c.a();
        float f3 = a2.scaledDensity;
        float f4 = f3 / a2.density;
        if (f2 >= 1.0f && f2 < f4) {
            f3 = a2.density * f2;
        }
        return f * f3;
    }

    public static float d(float f) {
        return f / c.a().density;
    }
}
