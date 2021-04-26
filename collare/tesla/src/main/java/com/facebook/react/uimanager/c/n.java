package com.facebook.react.uimanager.c;

import android.view.animation.Interpolator;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;

/* access modifiers changed from: package-private */
/* compiled from: SimpleSpringInterpolator */
public class n implements Interpolator {

    /* renamed from: a  reason: collision with root package name */
    private final float f3223a;

    public static float a(ReadableMap readableMap) {
        if (readableMap.getType("springDamping").equals(ReadableType.Number)) {
            return (float) readableMap.getDouble("springDamping");
        }
        return 0.5f;
    }

    public n() {
        this.f3223a = 0.5f;
    }

    public n(float f) {
        this.f3223a = f;
    }

    public float getInterpolation(float f) {
        double pow = Math.pow(2.0d, (double) (-10.0f * f));
        float f2 = this.f3223a;
        return (float) ((pow * Math.sin(((((double) (f - (f2 / 4.0f))) * 3.141592653589793d) * 2.0d) / ((double) f2))) + 1.0d);
    }
}
