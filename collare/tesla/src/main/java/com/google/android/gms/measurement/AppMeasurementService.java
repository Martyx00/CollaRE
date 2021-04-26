package com.google.android.gms.measurement;

import android.app.Service;
import android.app.job.JobParameters;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.gms.internal.measurement.zzje;
import com.google.android.gms.internal.measurement.zzji;

public final class AppMeasurementService extends Service implements zzji {
    private zzje<AppMeasurementService> zzadd;

    private final zzje<AppMeasurementService> zzfq() {
        if (this.zzadd == null) {
            this.zzadd = new zzje<>(this);
        }
        return this.zzadd;
    }

    @Override // com.google.android.gms.internal.measurement.zzji
    public final boolean callServiceStopSelfResult(int i) {
        return stopSelfResult(i);
    }

    public final IBinder onBind(Intent intent) {
        return zzfq().onBind(intent);
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

    public final int onStartCommand(Intent intent, int i, int i2) {
        return zzfq().onStartCommand(intent, i, i2);
    }

    public final boolean onUnbind(Intent intent) {
        return zzfq().onUnbind(intent);
    }

    @Override // com.google.android.gms.internal.measurement.zzji
    public final void zza(JobParameters jobParameters, boolean z) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.measurement.zzji
    public final void zzb(Intent intent) {
        AppMeasurementReceiver.completeWakefulIntent(intent);
    }
}
