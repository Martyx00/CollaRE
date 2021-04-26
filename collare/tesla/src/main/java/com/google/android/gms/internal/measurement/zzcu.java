package com.google.android.gms.internal.measurement;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzcy;
import com.google.android.gms.stats.WakeLock;

public final class zzcu<T extends Context & zzcy> {
    private static Boolean zzabm;
    private final Handler handler = new Handler();
    private final T zzabl;

    public zzcu(T t) {
        Preconditions.checkNotNull(t);
        this.zzabl = t;
    }

    private final void zzb(Runnable runnable) {
        zzat.zzc(this.zzabl).zzby().zza(new zzcx(this, runnable));
    }

    public static boolean zze(Context context) {
        Preconditions.checkNotNull(context);
        Boolean bool = zzabm;
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean zzc = zzdd.zzc(context, "com.google.android.gms.analytics.AnalyticsService");
        zzabm = Boolean.valueOf(zzc);
        return zzc;
    }

    public final void onCreate() {
        zzat.zzc(this.zzabl).zzbu().zzq("Local AnalyticsService is starting up");
    }

    public final void onDestroy() {
        zzat.zzc(this.zzabl).zzbu().zzq("Local AnalyticsService is shutting down");
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        try {
            synchronized (zzct.lock) {
                WakeLock wakeLock = zzct.zzabk;
                if (wakeLock != null && wakeLock.isHeld()) {
                    wakeLock.release();
                }
            }
        } catch (SecurityException unused) {
        }
        zzcm zzbu = zzat.zzc(this.zzabl).zzbu();
        if (intent == null) {
            zzbu.zzt("AnalyticsService started with null intent");
            return 2;
        }
        String action = intent.getAction();
        zzbu.zza("Local AnalyticsService called. startId, action", Integer.valueOf(i2), action);
        if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
            zzb(new zzcv(this, i2, zzbu));
        }
        return 2;
    }

    @TargetApi(24)
    public final boolean onStartJob(JobParameters jobParameters) {
        zzcm zzbu = zzat.zzc(this.zzabl).zzbu();
        String string = jobParameters.getExtras().getString("action");
        zzbu.zza("Local AnalyticsJobService called. action", string);
        if (!"com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(string)) {
            return true;
        }
        zzb(new zzcw(this, zzbu, jobParameters));
        return true;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(int i, zzcm zzcm) {
        if (this.zzabl.callServiceStopSelfResult(i)) {
            zzcm.zzq("Local AnalyticsService processed last dispatch request");
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzcm zzcm, JobParameters jobParameters) {
        zzcm.zzq("AnalyticsJobService processed last dispatch request");
        this.zzabl.zza(jobParameters, false);
    }
}
