package com.google.android.gms.tasks;

final class zzl implements Runnable {
    private final /* synthetic */ Task zzg;
    private final /* synthetic */ zzk zzo;

    zzl(zzk zzk, Task task) {
        this.zzo = zzk;
        this.zzg = task;
    }

    public final void run() {
        synchronized (zzk.zza(this.zzo)) {
            if (zzk.zzb(this.zzo) != null) {
                zzk.zzb(this.zzo).onFailure(this.zzg.getException());
            }
        }
    }
}
