package com.google.android.gms.tasks;

final class zzh implements Runnable {
    private final /* synthetic */ zzg zzk;

    zzh(zzg zzg) {
        this.zzk = zzg;
    }

    public final void run() {
        synchronized (zzg.zza(this.zzk)) {
            if (zzg.zzb(this.zzk) != null) {
                zzg.zzb(this.zzk).onCanceled();
            }
        }
    }
}
