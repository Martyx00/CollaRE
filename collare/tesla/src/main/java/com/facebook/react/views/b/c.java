package com.facebook.react.views.b;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import io.a.a.a.a.d.b;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ResourceDrawableIdHelper */
public class c {

    /* renamed from: b  reason: collision with root package name */
    private static volatile c f3313b;

    /* renamed from: a  reason: collision with root package name */
    private Map<String, Integer> f3314a = new HashMap();

    private c() {
    }

    public static c a() {
        if (f3313b == null) {
            synchronized (c.class) {
                if (f3313b == null) {
                    f3313b = new c();
                }
            }
        }
        return f3313b;
    }

    public int a(Context context, String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        String replace = str.toLowerCase().replace("-", b.ROLL_OVER_FILE_NAME_SEPARATOR);
        try {
            return Integer.parseInt(replace);
        } catch (NumberFormatException unused) {
            synchronized (this) {
                if (this.f3314a.containsKey(replace)) {
                    return this.f3314a.get(replace).intValue();
                }
                int identifier = context.getResources().getIdentifier(replace, "drawable", context.getPackageName());
                this.f3314a.put(replace, Integer.valueOf(identifier));
                return identifier;
            }
        }
    }

    public Drawable b(Context context, String str) {
        int a2 = a(context, str);
        if (a2 > 0) {
            return context.getResources().getDrawable(a2);
        }
        return null;
    }

    public Uri c(Context context, String str) {
        int a2 = a(context, str);
        return a2 > 0 ? new Uri.Builder().scheme("res").path(String.valueOf(a2)).build() : Uri.EMPTY;
    }
}
