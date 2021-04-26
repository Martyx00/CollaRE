package com.facebook.f.e;

import android.graphics.drawable.Drawable;

/* compiled from: DrawableUtils */
public class e {
    public static int a(int i) {
        int i2 = i >>> 24;
        if (i2 == 255) {
            return -1;
        }
        return i2 == 0 ? -2 : -3;
    }

    public static int a(int i, int i2) {
        if (i2 == 255) {
            return i;
        }
        if (i2 == 0) {
            return i & 16777215;
        }
        return (i & 16777215) | ((((i >>> 24) * (i2 + (i2 >> 7))) >> 8) << 24);
    }

    public static void a(Drawable drawable, Drawable drawable2) {
        if (drawable2 != null && drawable != null && drawable != drawable2) {
            drawable.setBounds(drawable2.getBounds());
            drawable.setChangingConfigurations(drawable2.getChangingConfigurations());
            drawable.setLevel(drawable2.getLevel());
            drawable.setVisible(drawable2.isVisible(), false);
            drawable.setState(drawable2.getState());
        }
    }

    public static void a(Drawable drawable, d dVar) {
        if (drawable != null && dVar != null) {
            dVar.a(drawable);
        }
    }

    public static void a(Drawable drawable, Drawable.Callback callback, s sVar) {
        if (drawable != null) {
            drawable.setCallback(callback);
            if (drawable instanceof r) {
                ((r) drawable).a(sVar);
            }
        }
    }
}
