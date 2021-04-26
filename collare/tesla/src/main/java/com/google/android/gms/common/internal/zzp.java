package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.wrappers.Wrappers;

public final class zzp {
    private static Object sLock = new Object();
    private static boolean zzeo;
    private static String zzep;
    private static int zzeq;

    public static String zzc(Context context) {
        zze(context);
        return zzep;
    }

    public static int zzd(Context context) {
        zze(context);
        return zzeq;
    }

    private static void zze(Context context) {
        synchronized (sLock) {
            if (!zzeo) {
                zzeo = true;
                try {
                    Bundle bundle = Wrappers.packageManager(context).getApplicationInfo(context.getPackageName(), 128).metaData;
                    if (bundle != null) {
                        zzep = bundle.getString("com.google.app.id");
                        zzeq = bundle.getInt("com.google.android.gms.version");
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    Log.wtf("MetadataValueReader", "This should never happen.", e);
                }
            }
        }
    }
}
