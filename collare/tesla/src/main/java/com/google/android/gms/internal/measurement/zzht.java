package com.google.android.gms.internal.measurement;

import com.google.android.gms.measurement.AppMeasurement;

/* access modifiers changed from: package-private */
public final class zzht implements Runnable {
    private final /* synthetic */ zzhm zzaps;
    private final /* synthetic */ AppMeasurement.ConditionalUserProperty zzapw;

    zzht(zzhm zzhm, AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        this.zzaps = zzhm;
        this.zzapw = conditionalUserProperty;
    }

    public final void run() {
        this.zzaps.zzb(this.zzapw);
    }
}
