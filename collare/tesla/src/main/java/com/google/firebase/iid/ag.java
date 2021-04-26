package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.Intent;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* access modifiers changed from: package-private */
public final class ag {

    /* renamed from: a  reason: collision with root package name */
    final Intent f3876a;

    /* renamed from: b  reason: collision with root package name */
    private final BroadcastReceiver.PendingResult f3877b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f3878c = false;

    /* renamed from: d  reason: collision with root package name */
    private final ScheduledFuture<?> f3879d;

    ag(Intent intent, BroadcastReceiver.PendingResult pendingResult, ScheduledExecutorService scheduledExecutorService) {
        this.f3876a = intent;
        this.f3877b = pendingResult;
        this.f3879d = scheduledExecutorService.schedule(new ah(this, intent), 9000, TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: package-private */
    public final synchronized void a() {
        if (!this.f3878c) {
            this.f3877b.finish();
            this.f3879d.cancel(false);
            this.f3878c = true;
        }
    }
}
