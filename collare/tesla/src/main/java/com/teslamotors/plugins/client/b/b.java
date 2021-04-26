package com.teslamotors.plugins.client.b;

import android.content.Context;
import com.teslamotors.plugins.client.d.e;
import java.util.Collections;
import java.util.Set;

/* compiled from: ConfigDataV3 */
public class b {
    public static String a(Context context) {
        return e.a().b("OWNERAPI_AUTH_TOKEN", e.a(context));
    }

    public static String b(Context context) {
        return e.a().b("SELECTED_PRODUCT", e.a(context));
    }

    public static String c(Context context) {
        return e.a().b("APP_PREVIOUSLY_LAUNCHED", e.b(context));
    }

    public static String d(Context context) {
        return e.a().b("APP_UUID", e.a(context));
    }

    public static String e(Context context) {
        return e.a().b("ACCOUNT_EMAIL", e.a(context));
    }

    public static String f(Context context) {
        return e.a().b("COLORIZED_IMAGE_DIRECTORY_HASH", e.b(context));
    }

    public static void a(String str, Set<String> set, Context context) {
        e.a().a(str, set, e.c(context));
    }

    public static Set<String> a(String str, Context context) {
        return e.a().a(str, e.c(context), Collections.EMPTY_SET);
    }

    public static long a(long j, String str, Context context) {
        return e.a().a(str, j, e.c(context));
    }

    public static long b(String str, Context context) {
        return e.a().a(str, e.c(context));
    }

    public static String g(Context context) {
        return e.a().b("SELECTED_VIN", e.b(context));
    }

    public static String h(Context context) {
        return e.a().b("SHARED_SELECTED_PRODUCT_DATA", e.a(context));
    }

    public static String i(Context context) {
        return e.a().b("KEY_STORE_PASS", e.c(context));
    }

    public static long b(long j, String str, Context context) {
        return e.a().a(str, j, e.c(context));
    }

    public static long c(String str, Context context) {
        return e.a().a(str, e.c(context));
    }

    public static String d(String str, Context context) {
        return e.a().b(str, e.c(context));
    }
}
