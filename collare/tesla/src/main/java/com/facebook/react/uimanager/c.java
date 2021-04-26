package com.facebook.react.uimanager;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.facebook.i.a.a;
import com.facebook.react.bridge.WritableNativeMap;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* compiled from: DisplayMetricsHolder */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static DisplayMetrics f3186a;

    /* renamed from: b  reason: collision with root package name */
    private static DisplayMetrics f3187b;

    public static void a(DisplayMetrics displayMetrics) {
        f3186a = displayMetrics;
    }

    public static void a(Context context) {
        if (b() == null) {
            b(context);
        }
    }

    public static void b(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        a(displayMetrics);
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        displayMetrics2.setTo(displayMetrics);
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        a.a(windowManager, "WindowManager is null!");
        Display defaultDisplay = windowManager.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealMetrics(displayMetrics2);
        } else {
            try {
                Method method = Display.class.getMethod("getRawHeight", new Class[0]);
                displayMetrics2.widthPixels = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                displayMetrics2.heightPixels = ((Integer) method.invoke(defaultDisplay, new Object[0])).intValue();
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                throw new RuntimeException("Error getting real dimensions for API level < 17", e);
            }
        }
        b(displayMetrics2);
    }

    @Deprecated
    public static DisplayMetrics a() {
        return f3186a;
    }

    public static void b(DisplayMetrics displayMetrics) {
        f3187b = displayMetrics;
    }

    public static DisplayMetrics b() {
        return f3187b;
    }

    public static Map<String, Map<String, Object>> a(double d2) {
        a.a(Boolean.valueOf((f3186a == null && f3187b == null) ? false : true), "DisplayMetricsHolder must be initialized with initDisplayMetricsIfNotInitialized or initDisplayMetrics");
        HashMap hashMap = new HashMap();
        hashMap.put("windowPhysicalPixels", a(f3186a, d2));
        hashMap.put("screenPhysicalPixels", a(f3187b, d2));
        return hashMap;
    }

    public static WritableNativeMap b(double d2) {
        a.a(Boolean.valueOf((f3186a == null && f3187b == null) ? false : true), "DisplayMetricsHolder must be initialized with initDisplayMetricsIfNotInitialized or initDisplayMetrics");
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putMap("windowPhysicalPixels", b(f3186a, d2));
        writableNativeMap.putMap("screenPhysicalPixels", b(f3187b, d2));
        return writableNativeMap;
    }

    private static Map<String, Object> a(DisplayMetrics displayMetrics, double d2) {
        HashMap hashMap = new HashMap();
        hashMap.put("width", Integer.valueOf(displayMetrics.widthPixels));
        hashMap.put("height", Integer.valueOf(displayMetrics.heightPixels));
        hashMap.put("scale", Float.valueOf(displayMetrics.density));
        hashMap.put("fontScale", Double.valueOf(d2));
        hashMap.put("densityDpi", Integer.valueOf(displayMetrics.densityDpi));
        return hashMap;
    }

    private static WritableNativeMap b(DisplayMetrics displayMetrics, double d2) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putInt("width", displayMetrics.widthPixels);
        writableNativeMap.putInt("height", displayMetrics.heightPixels);
        writableNativeMap.putDouble("scale", (double) displayMetrics.density);
        writableNativeMap.putDouble("fontScale", d2);
        writableNativeMap.putDouble("densityDpi", (double) displayMetrics.densityDpi);
        return writableNativeMap;
    }
}
