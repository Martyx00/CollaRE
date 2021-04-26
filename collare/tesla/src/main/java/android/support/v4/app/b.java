package android.support.v4.app;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Build;

/* compiled from: AppOpsManagerCompat */
public final class b {
    public static String a(String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            return AppOpsManager.permissionToOp(str);
        }
        return null;
    }

    public static int a(Context context, String str, String str2) {
        if (Build.VERSION.SDK_INT >= 23) {
            return ((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteProxyOpNoThrow(str, str2);
        }
        return 1;
    }
}
