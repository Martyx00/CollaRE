package android.support.v4.d;

import android.os.Build;

/* compiled from: BuildCompat */
public class a {
    public static boolean a() {
        if (Build.VERSION.CODENAME.length() != 1 || Build.VERSION.CODENAME.charAt(0) < 'Q' || Build.VERSION.CODENAME.charAt(0) > 'Z') {
            return false;
        }
        return true;
    }
}
