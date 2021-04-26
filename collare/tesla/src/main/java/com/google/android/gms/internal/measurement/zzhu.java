package com.google.android.gms.internal.measurement;

import com.google.android.gms.measurement.AppMeasurement;

/* access modifiers changed from: package-private */
public final class zzhu implements Runnable {
    private final /* synthetic */ zzhm zzaps;
    private final /* synthetic */ AppMeasurement.ConditionalUserProperty zzapw;

    zzhu(zzhm zzhm, AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        this.zzaps = zzhm;
        this.zzapw = conditionalUserProperty;
    }

    public final void run() {
        this.zzaps.zzc(this.zzapw);
    }
}
