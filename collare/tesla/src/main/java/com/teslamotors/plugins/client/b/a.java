package com.teslamotors.plugins.client.b;

import android.content.Context;
import com.teslamotors.plugins.client.d.d;
import java.util.Set;

/* compiled from: ConfigData */
public class a {
    public static String a(Context context) {
        return d.a(context).a("_OWNERAPI_AUTH_TOKEN");
    }

    public static String b(Context context) {
        return d.a(context).a("APP_UUID");
    }

    public static String c(Context context) {
        return d.a(context).a("_ACCOUNT_EMAIL");
    }

    public static String d(Context context) {
        return d.a(context).a("REMOTE_NOTIFICATION_TOKEN");
    }

    public static void a(Context context, String str) {
        d.a(context).a("REMOTE_NOTIFICATION_TOKEN", str);
    }

    public static String e(Context context) {
        return d.a(context).a("SELECTED_PRODUCT");
    }

    public static boolean f(Context context) {
        String a2 = d.a(context).a("CALENDAR_SYNC_ENABLED");
        if (a2 != null) {
            return Boolean.valueOf(a2).booleanValue();
        }
        return false;
    }

    public static String g(Context context) {
        return d.a(context).a("APP_PREVIOUSLY_LAUNCHED");
    }

    public static String h(Context context) {
        return d.a(context).a("COLORIZED_IMAGE_DIRECTORY_HASH");
    }

    public static void b(Context context, String str) {
        d.a(context).a("COLORIZED_IMAGE_DIRECTORY_HASH", str);
    }

    public static void c(Context context, String str) {
        d.a(context).a("APP_GIT_HASH", str);
    }

    public static String i(Context context) {
        return d.a(context).a("APP_GIT_HASH");
    }

    public static void j(Context context) {
        d.a(context).b("REFER_FRIENDS_VERSION");
    }

    public static void k(Context context) {
        d.a(context).b("REFER_FRIENDS_HAS_SEEN_SPLASH");
    }

    public static void a(String str, Context context) {
        d.a(context).a("SELECTED_VIN", str);
    }

    public static String l(Context context) {
        return d.a(context).a("SELECTED_VIN");
    }

    public static void d(Context context, String str) {
        d.a(context).a("SHARED_SELECTED_PRODUCT_DATA", str);
    }

    public static String m(Context context) {
        return d.a(context).a("SHARED_SELECTED_PRODUCT_DATA");
    }

    public static void b(String str, Context context) {
        d.a(context).a("_KEY_STORE_PASS", str);
    }

    public static String n(Context context) {
        return d.a(context).a("_KEY_STORE_PASS");
    }

    public static String c(String str, Context context) {
        return d.a(context).a(str);
    }

    public static void a(String str, String str2, Context context) {
        d.a(context).a(str, str2);
    }

    public static void b(String str, String str2, Context context) {
        d.a(context).a(str, str2);
    }

    public static void d(String str, Context context) {
        d.a(context).a("SELECTED_PRODUCT", str);
    }

    public static void a(String str, Set<String> set, Context context) {
        b.a(str, set, context);
    }

    public static Set<String> e(String str, Context context) {
        return b.a(str, context);
    }

    public static long a(long j, String str, Context context) {
        return b.a(j, str, context);
    }

    public static long f(String str, Context context) {
        return b.b(str, context);
    }

    public static long b(long j, String str, Context context) {
        return b.b(j, str, context);
    }

    public static long g(String str, Context context) {
        return b.c(str, context);
    }
}
