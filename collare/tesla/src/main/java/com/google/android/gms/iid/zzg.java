package com.google.android.gms.iid;

import android.content.BroadcastReceiver;
import android.content.Intent;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* access modifiers changed from: package-private */
public final class zzg {
    final Intent intent;
    private final BroadcastReceiver.PendingResult zzbe;
    private boolean zzbf = false;
    private final ScheduledFuture<?> zzbg;

    zzg(Intent intent2, BroadcastReceiver.PendingResult pendingResult, ScheduledExecutorService scheduledExecutorService) {
        this.intent = intent2;
        this.zzbe = pendingResult;
        this.zzbg = scheduledExecutorService.schedule(new zzh(this, intent2), 9500, TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: package-private */
    public final synchronized void finish() {
        if (!this.zzbf) {
            this.zzbe.finish();
            this.zzbg.cancel(false);
            this.zzbf = true;
        }
    }
}
