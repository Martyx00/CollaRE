package com.facebook.react.views.a;

import android.content.Context;
import android.content.ContextWrapper;

/* compiled from: ContextUtils */
public class a {
    public static <T> T a(Context context, Class<? extends T> cls) {
        Context baseContext;
        while (!cls.isInstance(context)) {
            if (!(context instanceof ContextWrapper) || context == (baseContext = ((ContextWrapper) context).getBaseContext())) {
                return null;
            }
            context = (T) baseContext;
        }
        return (T) context;
    }
}
