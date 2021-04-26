package com.teslamotors.plugins.colorblending;

import android.graphics.Color;
import com.facebook.react.bridge.ReadableMap;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;

/* compiled from: ColorBlendingSpecification */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public static File f5577a = null;

    /* renamed from: b  reason: collision with root package name */
    private static final String f5578b = "c";

    /* renamed from: c  reason: collision with root package name */
    private float f5579c;

    /* renamed from: d  reason: collision with root package name */
    private float f5580d;
    private float e;
    private double f;
    private double g;
    private double h;
    private String i;
    private String j;

    public c(ReadableMap readableMap) {
        a(readableMap);
    }

    private void a(ReadableMap readableMap) {
        ReadableMap map;
        this.i = readableMap.getMap(FirebaseAnalytics.b.SOURCE).getString("uri");
        if (readableMap.hasKey("maskImage") && (map = readableMap.getMap("maskImage")) != null) {
            this.j = map.getString("uri");
        }
        this.f5579c = (float) readableMap.getDouble("hue");
        this.f5580d = (float) readableMap.getDouble("saturation");
        this.e = (float) readableMap.getDouble("brightness");
        this.f = readableMap.getDouble("alpha");
        this.g = readableMap.getDouble("preLightnessVariant");
        this.h = readableMap.getDouble("postLightnessVariant");
    }

    public double a() {
        return this.g;
    }

    public double b() {
        return this.h;
    }

    public String c() {
        return this.i;
    }

    public String d() {
        return this.j;
    }

    public String toString() {
        return String.format("%s %s hsv(%s %s %s) alpha:%s pre:%s post:%s", a(this.i), a(this.j), Float.valueOf(this.f5579c), Float.valueOf(this.f5580d), Float.valueOf(this.e), Double.valueOf(this.f), Double.valueOf(this.g), Double.valueOf(this.h));
    }

    public String e() {
        return String.format("%s%s%s%s%s%s%s%s", a(this.i), a(this.j), Float.valueOf(this.f5579c), Float.valueOf(this.f5580d), Float.valueOf(this.e), Double.valueOf(this.f), Double.valueOf(this.g), Double.valueOf(this.h));
    }

    public int f() {
        return Color.HSVToColor(new float[]{this.f5579c * 1.0f, this.f5580d * 0.01f, this.e * 0.01f});
    }

    private static String a(String str) {
        if (str == null) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(47) + 1;
        int indexOf = str.indexOf(63);
        return (lastIndexOf < 0 || indexOf < 0) ? str : str.substring(lastIndexOf, indexOf);
    }

    public File g() {
        return new File(f5577a, String.format("%s.png", Integer.valueOf(e().hashCode())));
    }
}
