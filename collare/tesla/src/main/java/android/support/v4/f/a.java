package android.support.v4.f;

import android.os.Build;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

/* compiled from: ICUCompat */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private static Method f442a;

    /* renamed from: b  reason: collision with root package name */
    private static Method f443b;

    static {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                f443b = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", Locale.class);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        } else {
            try {
                Class<?> cls = Class.forName("libcore.icu.ICU");
                if (cls != null) {
                    f442a = cls.getMethod("getScript", String.class);
                    f443b = cls.getMethod("addLikelySubtags", String.class);
                }
            } catch (Exception e2) {
                f442a = null;
                f443b = null;
                Log.w("ICUCompat", e2);
            }
        }
    }

    public static String a(Locale locale) {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                return ((Locale) f443b.invoke(null, locale)).getScript();
            } catch (InvocationTargetException e) {
                Log.w("ICUCompat", e);
                return locale.getScript();
            } catch (IllegalAccessException e2) {
                Log.w("ICUCompat", e2);
                return locale.getScript();
            }
        } else {
            String b2 = b(locale);
            if (b2 != null) {
                return a(b2);
            }
            return null;
        }
    }

    private static String a(String str) {
        try {
            if (f442a != null) {
                return (String) f442a.invoke(null, str);
            }
        } catch (IllegalAccessException e) {
            Log.w("ICUCompat", e);
        } catch (InvocationTargetException e2) {
            Log.w("ICUCompat", e2);
        }
        return null;
    }

    private static String b(Locale locale) {
        String locale2 = locale.toString();
        try {
            if (f443b != null) {
                return (String) f443b.invoke(null, locale2);
            }
        } catch (IllegalAccessException e) {
            Log.w("ICUCompat", e);
        } catch (InvocationTargetException e2) {
            Log.w("ICUCompat", e2);
        }
        return locale2;
    }
}
