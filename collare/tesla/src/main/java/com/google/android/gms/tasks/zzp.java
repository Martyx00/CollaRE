package com.google.android.gms.tasks;

import java.util.concurrent.CancellationException;

final class zzp implements Runnable {
    private final /* synthetic */ Task zzg;
    private final /* synthetic */ zzo zzs;

    zzp(zzo zzo, Task task) {
        this.zzs = zzo;
        this.zzg = task;
    }

    public final void run() {
        try {
            Task then = this.zzs.zzr.then(this.zzg.getResult());
            if (then == null) {
                this.zzs.onFailure(new NullPointerException("Continuation returned null"));
                return;
            }
            then.addOnSuccessListener(TaskExecutors.zzw, this.zzs);
            then.addOnFailureListener(TaskExecutors.zzw, this.zzs);
            then.addOnCanceledListener(TaskExecutors.zzw, this.zzs);
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                this.zzs.onFailure((Exception) e.getCause());
            } else {
                this.zzs.onFailure(e);
            }
        } catch (CancellationException unused) {
            this.zzs.onCanceled();
        } catch (Exception e2) {
            this.zzs.onFailure(e2);
        }
    }
}
