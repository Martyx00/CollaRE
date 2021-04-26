package com.facebook.react.modules.i18nmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.f.c;
import java.util.Locale;

/* compiled from: I18nUtil */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static a f2885a;

    private a() {
    }

    public static a a() {
        if (f2885a == null) {
            f2885a = new a();
        }
        return f2885a;
    }

    public boolean a(Context context) {
        if (d(context)) {
            return true;
        }
        if (!c(context) || !b()) {
            return false;
        }
        return true;
    }

    private boolean c(Context context) {
        return a(context, "RCTI18nUtil_allowRTL", true);
    }

    public void a(Context context, boolean z) {
        b(context, "RCTI18nUtil_allowRTL", z);
    }

    public boolean b(Context context) {
        return a(context, "RCTI18nUtil_makeRTLFlipLeftAndRightStyles", true);
    }

    public void b(Context context, boolean z) {
        b(context, "RCTI18nUtil_makeRTLFlipLeftAndRightStyles", z);
    }

    private boolean d(Context context) {
        return a(context, "RCTI18nUtil_forceRTL", false);
    }

    public void c(Context context, boolean z) {
        b(context, "RCTI18nUtil_forceRTL", z);
    }

    private boolean b() {
        return c.a(Locale.getDefault()) == 1;
    }

    private boolean a(Context context, String str, boolean z) {
        return context.getSharedPreferences("com.facebook.react.modules.i18nmanager.I18nUtil", 0).getBoolean(str, z);
    }

    private void b(Context context, String str, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences("com.facebook.react.modules.i18nmanager.I18nUtil", 0).edit();
        edit.putBoolean(str, z);
        edit.apply();
    }
}
