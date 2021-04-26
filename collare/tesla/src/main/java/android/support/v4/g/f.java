package android.support.v4.g;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: KeyEventDispatcher */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f488a = false;

    /* renamed from: b  reason: collision with root package name */
    private static Method f489b = null;

    /* renamed from: c  reason: collision with root package name */
    private static boolean f490c = false;

    /* renamed from: d  reason: collision with root package name */
    private static Field f491d;

    /* compiled from: KeyEventDispatcher */
    public interface a {
        boolean a(KeyEvent keyEvent);
    }

    public static boolean a(View view, KeyEvent keyEvent) {
        return t.a(view, keyEvent);
    }

    public static boolean a(a aVar, View view, Window.Callback callback, KeyEvent keyEvent) {
        if (aVar == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            return aVar.a(keyEvent);
        }
        if (callback instanceof Activity) {
            return a((Activity) callback, keyEvent);
        }
        if (callback instanceof Dialog) {
            return a((Dialog) callback, keyEvent);
        }
        if ((view == null || !t.b(view, keyEvent)) && !aVar.a(keyEvent)) {
            return false;
        }
        return true;
    }

    private static boolean a(ActionBar actionBar, KeyEvent keyEvent) {
        if (!f488a) {
            try {
                f489b = actionBar.getClass().getMethod("onMenuKeyEvent", KeyEvent.class);
            } catch (NoSuchMethodException unused) {
            }
            f488a = true;
        }
        Method method = f489b;
        if (method != null) {
            try {
                return ((Boolean) method.invoke(actionBar, keyEvent)).booleanValue();
            } catch (IllegalAccessException | InvocationTargetException unused2) {
            }
        }
        return false;
    }

    private static boolean a(Activity activity, KeyEvent keyEvent) {
        activity.onUserInteraction();
        Window window = activity.getWindow();
        if (window.hasFeature(8)) {
            ActionBar actionBar = activity.getActionBar();
            if (keyEvent.getKeyCode() == 82 && actionBar != null && a(actionBar, keyEvent)) {
                return true;
            }
        }
        if (window.superDispatchKeyEvent(keyEvent)) {
            return true;
        }
        View decorView = window.getDecorView();
        if (t.b(decorView, keyEvent)) {
            return true;
        }
        return keyEvent.dispatch(activity, decorView != null ? decorView.getKeyDispatcherState() : null, activity);
    }

    private static DialogInterface.OnKeyListener a(Dialog dialog) {
        if (!f490c) {
            try {
                f491d = Dialog.class.getDeclaredField("mOnKeyListener");
                f491d.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            f490c = true;
        }
        Field field = f491d;
        if (field == null) {
            return null;
        }
        try {
            return (DialogInterface.OnKeyListener) field.get(dialog);
        } catch (IllegalAccessException unused2) {
            return null;
        }
    }

    private static boolean a(Dialog dialog, KeyEvent keyEvent) {
        DialogInterface.OnKeyListener a2 = a(dialog);
        if (a2 != null && a2.onKey(dialog, keyEvent.getKeyCode(), keyEvent)) {
            return true;
        }
        Window window = dialog.getWindow();
        if (window.superDispatchKeyEvent(keyEvent)) {
            return true;
        }
        View decorView = window.getDecorView();
        if (t.b(decorView, keyEvent)) {
            return true;
        }
        return keyEvent.dispatch(dialog, decorView != null ? decorView.getKeyDispatcherState() : null, dialog);
    }
}
