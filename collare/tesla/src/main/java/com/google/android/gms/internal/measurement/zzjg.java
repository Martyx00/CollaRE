package com.google.android.gms.internal.measurement;

import android.app.job.JobParameters;

final /* synthetic */ class zzjg implements Runnable {
    private final JobParameters zzabr;
    private final zzje zzarg;
    private final zzfi zzarj;

    zzjg(zzje zzje, zzfi zzfi, JobParameters jobParameters) {
        this.zzarg = zzje;
        this.zzarj = zzfi;
        this.zzabr = jobParameters;
    }

    public final void run() {
        this.zzarg.zza(this.zzarj, this.zzabr);
    }
}
