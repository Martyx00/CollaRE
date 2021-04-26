package com.google.android.gms.internal.measurement;

import android.content.Intent;

final /* synthetic */ class zzjf implements Runnable {
    private final int zzabo;
    private final zzje zzarg;
    private final zzfi zzarh;
    private final Intent zzari;

    zzjf(zzje zzje, int i, zzfi zzfi, Intent intent) {
        this.zzarg = zzje;
        this.zzabo = i;
        this.zzarh = zzfi;
        this.zzari = intent;
    }

    public final void run() {
        this.zzarg.zza(this.zzabo, this.zzarh, this.zzari);
    }
}
