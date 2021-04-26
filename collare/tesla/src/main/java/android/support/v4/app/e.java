package android.support.v4.app;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: BundleCompat */
public final class e {

    /* compiled from: BundleCompat */
    static class a {

        /* renamed from: a  reason: collision with root package name */
        private static Method f185a;

        /* renamed from: b  reason: collision with root package name */
        private static boolean f186b;

        public static IBinder a(Bundle bundle, String str) {
            if (!f186b) {
                try {
                    f185a = Bundle.class.getMethod("getIBinder", String.class);
                    f185a.setAccessible(true);
                } catch (NoSuchMethodException e) {
                    Log.i("BundleCompatBaseImpl", "Failed to retrieve getIBinder method", e);
                }
                f186b = true;
            }
            Method method = f185a;
            if (method != null) {
                try {
                    return (IBinder) method.invoke(bundle, str);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e2) {
                    Log.i("BundleCompatBaseImpl", "Failed to invoke getIBinder via reflection", e2);
                    f185a = null;
                }
            }
            return null;
        }
    }

    public static IBinder a(Bundle bundle, String str) {
        if (Build.VERSION.SDK_INT >= 18) {
            return bundle.getBinder(str);
        }
        return a.a(bundle, str);
    }
}
