package android.support.v4.app;

import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

/* compiled from: NotificationManagerCompat */
public final class ab {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f158a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private static Set<String> f159b = new HashSet();
    private static final Object e = new Object();

    /* renamed from: c  reason: collision with root package name */
    private final Context f160c;

    /* renamed from: d  reason: collision with root package name */
    private final NotificationManager f161d = ((NotificationManager) this.f160c.getSystemService("notification"));

    public static ab a(Context context) {
        return new ab(context);
    }

    private ab(Context context) {
        this.f160c = context;
    }

    public boolean a() {
        if (Build.VERSION.SDK_INT >= 24) {
            return this.f161d.areNotificationsEnabled();
        }
        if (Build.VERSION.SDK_INT < 19) {
            return true;
        }
        AppOpsManager appOpsManager = (AppOpsManager) this.f160c.getSystemService("appops");
        ApplicationInfo applicationInfo = this.f160c.getApplicationInfo();
        String packageName = this.f160c.getApplicationContext().getPackageName();
        int i = applicationInfo.uid;
        try {
            Class<?> cls = Class.forName(AppOpsManager.class.getName());
            if (((Integer) cls.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class).invoke(appOpsManager, Integer.valueOf(((Integer) cls.getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(i), packageName)).intValue() == 0) {
                return true;
            }
            return false;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | RuntimeException | InvocationTargetException unused) {
            return true;
        }
    }
}
