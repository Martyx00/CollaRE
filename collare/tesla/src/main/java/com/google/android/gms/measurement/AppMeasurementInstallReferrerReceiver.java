package com.google.android.gms.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.internal.measurement.zzgd;
import com.google.android.gms.internal.measurement.zzgg;

public final class AppMeasurementInstallReferrerReceiver extends BroadcastReceiver implements zzgg {
    private zzgd zzadc;

    @Override // com.google.android.gms.internal.measurement.zzgg
    public final BroadcastReceiver.PendingResult doGoAsync() {
        return goAsync();
    }

    @Override // com.google.android.gms.internal.measurement.zzgg
    public final void doStartService(Context context, Intent intent) {
    }

    public final void onReceive(Context context, Intent intent) {
        if (this.zzadc == null) {
            this.zzadc = new zzgd(this);
        }
        this.zzadc.onReceive(context, intent);
    }
}
