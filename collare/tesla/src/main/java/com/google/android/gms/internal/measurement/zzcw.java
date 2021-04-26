package com.google.android.gms.internal.measurement;

import android.app.job.JobParameters;

/* access modifiers changed from: package-private */
public final /* synthetic */ class zzcw implements Runnable {
    private final zzcu zzabn;
    private final zzcm zzabq;
    private final JobParameters zzabr;

    zzcw(zzcu zzcu, zzcm zzcm, JobParameters jobParameters) {
        this.zzabn = zzcu;
        this.zzabq = zzcm;
        this.zzabr = jobParameters;
    }

    public final void run() {
        this.zzabn.zza(this.zzabq, this.zzabr);
    }
}
