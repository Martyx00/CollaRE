package com.facebook.react.modules.systeminfo;

import android.os.Build;

/* compiled from: AndroidInfoHelpers */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f3012a = "a";

    /* renamed from: b  reason: collision with root package name */
    private static String f3013b;

    private static boolean b() {
        return Build.FINGERPRINT.contains("vbox");
    }

    public static String a() {
        if (b()) {
            return Build.MODEL;
        }
        return Build.MODEL + " - " + Build.VERSION.RELEASE + " - API " + Build.VERSION.SDK_INT;
    }
}
