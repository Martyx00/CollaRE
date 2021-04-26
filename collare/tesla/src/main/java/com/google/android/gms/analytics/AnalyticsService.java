package com.google.android.gms.analytics;

import android.app.Service;
import android.app.job.JobParameters;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.gms.internal.measurement.zzcu;
import com.google.android.gms.internal.measurement.zzcy;

public final class AnalyticsService extends Service implements zzcy {
    private zzcu<AnalyticsService> zzqo;

    private final zzcu<AnalyticsService> zzj() {
        if (this.zzqo == null) {
            this.zzqo = new zzcu<>(this);
        }
        return this.zzqo;
    }

    @Override // com.google.android.gms.internal.measurement.zzcy
    public final boolean callServiceStopSelfResult(int i) {
        return stopSelfResult(i);
    }

    public final IBinder onBind(Intent intent) {
        zzj();
        return null;
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

    @Override // com.google.android.gms.internal.measurement.zzcy
    public final void zza(JobParameters jobParameters, boolean z) {
        throw new UnsupportedOperationException();
    }
}
