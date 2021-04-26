package com.google.android.gms.measurement;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import com.google.android.gms.internal.measurement.zzje;
import com.google.android.gms.internal.measurement.zzji;

@TargetApi(24)
public final class AppMeasurementJobService extends JobService implements zzji {
    private zzje<AppMeasurementJobService> zzadd;

    private final zzje<AppMeasurementJobService> zzfq() {
        if (this.zzadd == null) {
            this.zzadd = new zzje<>(this);
        }
        return this.zzadd;
    }

    @Override // com.google.android.gms.internal.measurement.zzji
    public final boolean callServiceStopSelfResult(int i) {
        throw new UnsupportedOperationException();
    }

    public final void onCreate() {
        super.onCreate();
        zzfq().onCreate();
    }

    public final void onDestroy() {
        zzfq().onDestroy();
        super.onDestroy();
    }

    public final void onRebind(Intent intent) {
        zzfq().onRebind(intent);
    }

    public final boolean onStartJob(JobParameters jobParameters) {
        return zzfq().onStartJob(jobParameters);
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    public final boolean onUnbind(Intent intent) {
        return zzfq().onUnbind(intent);
    }

    @Override // com.google.android.gms.internal.measurement.zzji
    @TargetApi(24)
    public final void zza(JobParameters jobParameters, boolean z) {
        jobFinished(jobParameters, false);
    }

    @Override // com.google.android.gms.internal.measurement.zzji
    public final void zzb(Intent intent) {
    }
}
