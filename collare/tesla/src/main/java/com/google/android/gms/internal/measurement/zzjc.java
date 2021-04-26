package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Context;

final class zzjc implements Runnable {
    private final /* synthetic */ zziy zzare;

    zzjc(zziy zziy) {
        this.zzare = zziy;
    }

    public final void run() {
        zzik zzik = this.zzare.zzaqv;
        Context context = this.zzare.zzaqv.getContext();
        this.zzare.zzaqv.zzgl();
        zzik.onServiceDisconnected(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementService"));
    }
}
