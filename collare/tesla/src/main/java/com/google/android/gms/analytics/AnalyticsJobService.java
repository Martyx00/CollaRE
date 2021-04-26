package com.google.android.gms.analytics;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import com.google.android.gms.internal.measurement.zzcu;
import com.google.android.gms.internal.measurement.zzcy;

@TargetApi(24)
public final class AnalyticsJobService extends JobService implements zzcy {
    private zzcu<AnalyticsJobService> zzqo;

    private final zzcu<AnalyticsJobService> zzj() {
        if (this.zzqo == null) {
            this.zzqo = new zzcu<>(this);
        }
        return this.zzqo;
    }

    @Override // com.google.android.gms.internal.measurement.zzcy
    public final boolean callServiceStopSelfResult(int i) {
        return stopSelfResult(i);
    }

    public final void onCreate() {
        super.onCreate();
        zzj().onCreate();
    }

    public final void onDestroy() {
        zzj().onDestroy();
        super.onDestroy();
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        return zzj().onStartCommand(intent, i, i2);
    }

    public final boolean onStartJob(JobParameters jobParameters) {
        return zzj().onStartJob(jobParameters);
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzcy
    @TargetApi(24)
    public final void zza(JobParameters jobParameters, boolean z) {
        jobFinished(jobParameters, false);
    }
}
