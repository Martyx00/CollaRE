package com.google.android.gms.tasks;

final class zzj implements Runnable {
    private final /* synthetic */ Task zzg;
    private final /* synthetic */ zzi zzm;

    zzj(zzi zzi, Task task) {
        this.zzm = zzi;
        this.zzg = task;
    }

    public final void run() {
        synchronized (this.zzm.mLock) {
            if (this.zzm.zzl != null) {
                this.zzm.zzl.onComplete(this.zzg);
            }
        }
    }
}
