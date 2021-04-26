package android.support.v4.content.a;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.a.a;
import android.util.AttributeSet;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* access modifiers changed from: package-private */
/* compiled from: GradientColorInflaterCompat */
public final class d {
    static Shader a(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        String name = xmlPullParser.getName();
        if (name.equals("gradient")) {
            TypedArray a2 = g.a(resources, theme, attributeSet, a.c.GradientColor);
            float a3 = g.a(a2, xmlPullParser, "startX", a.c.GradientColor_android_startX, (float) BitmapDescriptorFactory.HUE_RED);
            float a4 = g.a(a2, xmlPullParser, "startY", a.c.GradientColor_android_startY, (float) BitmapDescriptorFactory.HUE_RED);
            float a5 = g.a(a2, xmlPullParser, "endX", a.c.GradientColor_android_endX, (float) BitmapDescriptorFactory.HUE_RED);
            float a6 = g.a(a2, xmlPullParser, "endY", a.c.GradientColor_android_endY, (float) BitmapDescriptorFactory.HUE_RED);
            float a7 = g.a(a2, xmlPullParser, "centerX", a.c.GradientColor_android_centerX, (float) BitmapDescriptorFactory.HUE_RED);
            float a8 = g.a(a2, xmlPullParser, "centerY", a.c.GradientColor_android_centerY, (float) BitmapDescriptorFactory.HUE_RED);
            int a9 = g.a(a2, xmlPullParser, AppMeasurement.Param.TYPE, a.c.GradientColor_android_type, 0);
            int b2 = g.b(a2, xmlPullParser, "startColor", a.c.GradientColor_android_startColor, 0);
            boolean a10 = g.a(xmlPullParser, "centerColor");
            int b3 = g.b(a2, xmlPullParser, "centerColor", a.c.GradientColor_android_centerColor, 0);
            int b4 = g.b(a2, xmlPullParser, "endColor", a.c.GradientColor_android_endColor, 0);
            int a11 = g.a(a2, xmlPullParser, "tileMode", a.c.GradientColor_android_tileMode, 0);
            float a12 = g.a(a2, xmlPullParser, "gradientRadius", a.c.GradientColor_android_gradientRadius, (float) BitmapDescriptorFactory.HUE_RED);
            a2.recycle();
            a a13 = a(b(resources, xmlPullParser, attributeSet, theme), b2, b4, a10, b3);
            switch (a9) {
                case 1:
                    if (a12 > BitmapDescriptorFactory.HUE_RED) {
                        return new RadialGradient(a7, a8, a12, a13.f356a, a13.f357b, a(a11));
                    }
                    throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
                case 2:
                    return new SweepGradient(a7, a8, a13.f356a, a13.f357b);
                default:
                    return new LinearGradient(a3, a4, a5, a6, a13.f356a, a13.f357b, a(a11));
            }
        } else {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid gradient color tag " + name);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0089, code lost:
        throw new org.xmlpull.v1.XmlPullParserException(r9.getPositionDescription() + ": <item> tag requires a 'color' attribute and a 'offset' " + "attribute!");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.support.v4.content.a.d.a b(android.content.res.Resources r8, org.xmlpull.v1.XmlPullParser r9, android.util.AttributeSet r10, android.content.res.Resources.Theme r11) {
        /*
        // Method dump skipped, instructions count: 152
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.a.d.b(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):android.support.v4.content.a.d$a");
    }

    private static a a(a aVar, int i, int i2, boolean z, int i3) {
        if (aVar != null) {
            return aVar;
        }
        if (z) {
            return new a(i, i3, i2);
        }
        return new a(i, i2);
    }

    private static Shader.TileMode a(int i) {
        switch (i) {
            case 1:
                return Shader.TileMode.REPEAT;
            case 2:
                return Shader.TileMode.MIRROR;
            default:
                return Shader.TileMode.CLAMP;
        }
    }

    /* access modifiers changed from: package-private */
    /* compiled from: GradientColorInflaterCompat */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        final int[] f356a;

        /* renamed from: b  reason: collision with root package name */
        final float[] f357b;

        a(List<Integer> list, List<Float> list2) {
            int size = list.size();
            this.f356a = new int[size];
            this.f357b = new float[size];
            for (int i = 0; i < size; i++) {
                this.f356a[i] = list.get(i).intValue();
                this.f357b[i] = list2.get(i).floatValue();
            }
        }

        a(int i, int i2) {
            this.f356a = new int[]{i, i2};
            this.f357b = new float[]{BitmapDescriptorFactory.HUE_RED, 1.0f};
        }

        a(int i, int i2, int i3) {
            this.f356a = new int[]{i, i2, i3};
            this.f357b = new float[]{BitmapDescriptorFactory.HUE_RED, 0.5f, 1.0f};
        }
    }
}
