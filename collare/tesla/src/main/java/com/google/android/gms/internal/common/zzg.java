package com.google.android.gms.internal.common;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

public final class zzg {
    private static volatile boolean zziy = (!zzam());

    public static boolean zzam() {
        return Build.VERSION.SDK_INT >= 24;
    }

    @TargetApi(24)
    public static Context getDeviceProtectedStorageContext(Context context) {
        if (context.isDeviceProtectedStorage()) {
            return context;
        }
        return context.createDeviceProtectedStorageContext();
    }
}
