package com.facebook.react.views.text;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;

/* compiled from: DefaultStyleValuesUtil */
public final class d {
    public static ColorStateList a(Context context) {
        return a(context, 16842906);
    }

    public static ColorStateList b(Context context) {
        return a(context, 16842904);
    }

    public static int c(Context context) {
        return a(context, 16842905).getDefaultColor();
    }

    private static ColorStateList a(Context context, int i) {
        TypedArray typedArray = null;
        try {
            typedArray = context.getTheme().obtainStyledAttributes(new int[]{i});
            return typedArray.getColorStateList(0);
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }
}
