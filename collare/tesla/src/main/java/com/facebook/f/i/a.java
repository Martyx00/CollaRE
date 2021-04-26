package com.facebook.f.i;

import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: AspectRatioMeasure */
public class a {

    /* renamed from: com.facebook.f.i.a$a  reason: collision with other inner class name */
    /* compiled from: AspectRatioMeasure */
    public static class C0047a {

        /* renamed from: a  reason: collision with root package name */
        public int f1969a;

        /* renamed from: b  reason: collision with root package name */
        public int f1970b;
    }

    private static boolean a(int i) {
        return i == 0 || i == -2;
    }

    public static void a(C0047a aVar, float f, ViewGroup.LayoutParams layoutParams, int i, int i2) {
        if (f > BitmapDescriptorFactory.HUE_RED && layoutParams != null) {
            if (a(layoutParams.height)) {
                aVar.f1970b = View.MeasureSpec.makeMeasureSpec(View.resolveSize((int) ((((float) (View.MeasureSpec.getSize(aVar.f1969a) - i)) / f) + ((float) i2)), aVar.f1970b), 1073741824);
            } else if (a(layoutParams.width)) {
                aVar.f1969a = View.MeasureSpec.makeMeasureSpec(View.resolveSize((int) ((((float) (View.MeasureSpec.getSize(aVar.f1970b) - i2)) * f) + ((float) i)), aVar.f1969a), 1073741824);
            }
        }
    }
}
