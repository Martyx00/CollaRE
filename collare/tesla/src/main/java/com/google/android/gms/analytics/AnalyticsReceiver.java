package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.internal.measurement.zzct;

public final class AnalyticsReceiver extends BroadcastReceiver {
    private zzct zzqp;

    public final void onReceive(Context context, Intent intent) {
        if (this.zzqp == null) {
            this.zzqp = new zzct();
        }
        zzct.onReceive(context, intent);
    }
}
