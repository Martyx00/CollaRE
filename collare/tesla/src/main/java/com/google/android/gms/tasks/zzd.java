package com.google.android.gms.tasks;

final class zzd implements Runnable {
    private final /* synthetic */ Task zzg;
    private final /* synthetic */ zzc zzh;

    zzd(zzc zzc, Task task) {
        this.zzh = zzc;
        this.zzg = task;
    }

    public final void run() {
        if (this.zzg.isCanceled()) {
            zzc.zza(this.zzh).zza();
            return;
        }
        try {
            zzc.zza(this.zzh).setResult(zzc.zzb(this.zzh).then(this.zzg));
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                zzc.zza(this.zzh).setException((Exception) e.getCause());
            } else {
                zzc.zza(this.zzh).setException(e);
            }
        } catch (Exception e2) {
            zzc.zza(this.zzh).setException(e2);
        }
    }
}
