package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.stats.WakeLock;

public final class zzct {
    static Object lock = new Object();
    static WakeLock zzabk;
    private static Boolean zzqt;

    public static void onReceive(Context context, Intent intent) {
        zzcm zzbu = zzat.zzc(context).zzbu();
        if (intent == null) {
            zzbu.zzt("AnalyticsReceiver called with null intent");
            return;
        }
        String action = intent.getAction();
        zzbu.zza("Local AnalyticsReceiver got", action);
        if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
            boolean zze = zzcu.zze(context);
            Intent intent2 = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            intent2.setComponent(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsService"));
            intent2.setAction("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            synchronized (lock) {
                context.startService(intent2);
                if (zze) {
                    try {
                        if (zzabk == null) {
                            WakeLock wakeLock = new WakeLock(context, 1, "Analytics WakeLock");
                            zzabk = wakeLock;
                            wakeLock.setReferenceCounted(false);
                        }
                        zzabk.acquire(1000);
                    } catch (SecurityException unused) {
                        zzbu.zzt("Analytics service at risk of not starting. For more reliable analytics, add the WAKE_LOCK permission to your manifest. See http://goo.gl/8Rd3yj for instructions.");
                    }
                }
            }
        }
    }

    public static boolean zza(Context context) {
        Preconditions.checkNotNull(context);
        Boolean bool = zzqt;
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean zza = zzdd.zza(context, "com.google.android.gms.analytics.AnalyticsReceiver", false);
        zzqt = Boolean.valueOf(zza);
        return zza;
    }
}
