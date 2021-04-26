package android.support.v4.widget;

import android.os.Build;
import android.support.v4.g.e;
import android.support.v4.g.t;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* compiled from: PopupWindowCompat */
public final class m {

    /* renamed from: a  reason: collision with root package name */
    private static Method f738a;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f739b;

    /* renamed from: c  reason: collision with root package name */
    private static Field f740c;

    /* renamed from: d  reason: collision with root package name */
    private static boolean f741d;

    public static void a(PopupWindow popupWindow, View view, int i, int i2, int i3) {
        if (Build.VERSION.SDK_INT >= 19) {
            popupWindow.showAsDropDown(view, i, i2, i3);
            return;
        }
        if ((e.a(i3, t.d(view)) & 7) == 5) {
            i -= popupWindow.getWidth() - view.getWidth();
        }
        popupWindow.showAsDropDown(view, i, i2);
    }

    public static void a(PopupWindow popupWindow, boolean z) {
        if (Build.VERSION.SDK_INT >= 23) {
            popupWindow.setOverlapAnchor(z);
        } else if (Build.VERSION.SDK_INT >= 21) {
            if (!f741d) {
                try {
                    f740c = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                    f740c.setAccessible(true);
                } catch (NoSuchFieldException e) {
                    Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", e);
                }
                f741d = true;
            }
            Field field = f740c;
            if (field != null) {
                try {
                    field.set(popupWindow, Boolean.valueOf(z));
                } catch (IllegalAccessException e2) {
                    Log.i("PopupWindowCompatApi21", "Could not set overlap anchor field in PopupWindow", e2);
                }
            }
        }
    }

    public static void a(PopupWindow popupWindow, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            popupWindow.setWindowLayoutType(i);
            return;
        }
        if (!f739b) {
            try {
                f738a = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", Integer.TYPE);
                f738a.setAccessible(true);
            } catch (Exception unused) {
            }
            f739b = true;
        }
        Method method = f738a;
        if (method != null) {
            try {
                method.invoke(popupWindow, Integer.valueOf(i));
            } catch (Exception unused2) {
            }
        }
    }
}
