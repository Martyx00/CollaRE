package com.google.android.gms.tasks;

final class zzn implements Runnable {
    private final /* synthetic */ Task zzg;
    private final /* synthetic */ zzm zzq;

    zzn(zzm zzm, Task task) {
        this.zzq = zzm;
        this.zzg = task;
    }

    public final void run() {
        synchronized (zzm.zza(this.zzq)) {
            if (zzm.zzb(this.zzq) != null) {
                zzm.zzb(this.zzq).onSuccess(this.zzg.getResult());
            }
        }
    }
}
