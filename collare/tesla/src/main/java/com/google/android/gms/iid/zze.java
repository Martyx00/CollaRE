package com.google.android.gms.iid;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.stats.GCoreWakefulBroadcastReceiver;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class zze extends Service {
    private final Object lock = new Object();
    final ExecutorService zzax = Executors.newSingleThreadExecutor();
    private Binder zzay;
    private int zzaz;
    private int zzba = 0;

    /* access modifiers changed from: private */
    public final void zzf(Intent intent) {
        if (intent != null) {
            GCoreWakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
        synchronized (this.lock) {
            this.zzba--;
            if (this.zzba == 0) {
                stopSelfResult(this.zzaz);
            }
        }
    }

    public abstract void handleIntent(Intent intent);

    public final synchronized IBinder onBind(Intent intent) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "Service received bind request");
        }
        if (this.zzay == null) {
            this.zzay = new zzi(this);
        }
        return this.zzay;
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        synchronized (this.lock) {
            this.zzaz = i2;
            this.zzba++;
        }
        if (intent == null) {
            zzf(intent);
            return 2;
        }
        this.zzax.execute(new zzf(this, intent, intent));
        return 3;
    }
}
