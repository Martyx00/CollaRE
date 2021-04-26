package android.support.v4.g;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import android.view.ViewConfiguration;
import io.a.a.a.a.b.a;
import java.lang.reflect.Method;

/* compiled from: ViewConfigurationCompat */
public final class u {

    /* renamed from: a  reason: collision with root package name */
    private static Method f511a;

    static {
        if (Build.VERSION.SDK_INT == 25) {
            try {
                f511a = ViewConfiguration.class.getDeclaredMethod("getScaledScrollFactor", new Class[0]);
            } catch (Exception unused) {
                Log.i("ViewConfigCompat", "Could not find method getScaledScrollFactor() on ViewConfiguration");
            }
        }
    }

    public static int a(ViewConfiguration viewConfiguration) {
        if (Build.VERSION.SDK_INT >= 28) {
            return viewConfiguration.getScaledHoverSlop();
        }
        return viewConfiguration.getScaledTouchSlop() / 2;
    }

    public static boolean a(ViewConfiguration viewConfiguration, Context context) {
        if (Build.VERSION.SDK_INT >= 28) {
            return viewConfiguration.shouldShowMenuShortcutsWhenKeyboardPresent();
        }
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showMenuShortcutsWhenKeyboardPresent", "bool", a.ANDROID_CLIENT_TYPE);
        return identifier != 0 && resources.getBoolean(identifier);
    }
}
