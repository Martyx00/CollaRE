package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

/* compiled from: ActivityCompat */
public class a extends android.support.v4.content.c {

    /* renamed from: a  reason: collision with root package name */
    private static b f150a;

    /* renamed from: android.support.v4.app.a$a  reason: collision with other inner class name */
    /* compiled from: ActivityCompat */
    public interface AbstractC0004a {
        void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);
    }

    /* compiled from: ActivityCompat */
    public interface b {
        boolean a(Activity activity, int i, int i2, Intent intent);

        boolean a(Activity activity, String[] strArr, int i);
    }

    /* compiled from: ActivityCompat */
    public interface c {
        void a(int i);
    }

    public static b a() {
        return f150a;
    }

    public static void a(Activity activity, Intent intent, int i, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            activity.startActivityForResult(intent, i, bundle);
        } else {
            activity.startActivityForResult(intent, i);
        }
    }

    public static void a(Activity activity, IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            activity.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
        } else {
            activity.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
        }
    }

    public static void a(Activity activity) {
        if (Build.VERSION.SDK_INT >= 16) {
            activity.finishAffinity();
        } else {
            activity.finish();
        }
    }

    public static void b(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.finishAfterTransition();
        } else {
            activity.finish();
        }
    }

    public static void a(final Activity activity, final String[] strArr, final int i) {
        b bVar = f150a;
        if (bVar != null && bVar.a(activity, strArr, i)) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity instanceof c) {
                ((c) activity).a(i);
            }
            activity.requestPermissions(strArr, i);
        } else if (activity instanceof AbstractC0004a) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                /* class android.support.v4.app.a.AnonymousClass1 */

                public void run() {
                    int[] iArr = new int[strArr.length];
                    PackageManager packageManager = activity.getPackageManager();
                    String packageName = activity.getPackageName();
                    int length = strArr.length;
                    for (int i = 0; i < length; i++) {
                        iArr[i] = packageManager.checkPermission(strArr[i], packageName);
                    }
                    ((AbstractC0004a) activity).onRequestPermissionsResult(i, strArr, iArr);
                }
            });
        }
    }

    public static boolean a(Activity activity, String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            return activity.shouldShowRequestPermissionRationale(str);
        }
        return false;
    }
}
