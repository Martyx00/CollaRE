package com.facebook.react.fabric.mounting;

import android.view.View;
import com.facebook.react.uimanager.o;
import com.facebook.yoga.YogaMeasureMode;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: LayoutMetricsConversions */
public class b {
    public static float a(int i) {
        return View.MeasureSpec.getMode(i) == 1073741824 ? (float) View.MeasureSpec.getSize(i) : BitmapDescriptorFactory.HUE_RED;
    }

    public static float b(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 0) {
            return -2.14748365E9f;
        }
        return (float) size;
    }

    public static float a(float f, float f2) {
        if (f == f2) {
            return o.a(f2);
        }
        if (f2 == -2.14748365E9f) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        return o.a(f2);
    }

    public static YogaMeasureMode b(float f, float f2) {
        if (f == f2) {
            return YogaMeasureMode.EXACTLY;
        }
        if (f2 == -2.14748365E9f) {
            return YogaMeasureMode.UNDEFINED;
        }
        return YogaMeasureMode.AT_MOST;
    }
}
