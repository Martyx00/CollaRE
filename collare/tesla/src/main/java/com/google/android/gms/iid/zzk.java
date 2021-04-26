package com.google.android.gms.iid;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public final class zzk implements ServiceConnection {
    private final Intent zzbl;
    private final ScheduledExecutorService zzbm;
    private final Queue<zzg> zzbn;
    private zzi zzbo;
    private boolean zzbp;
    private final Context zzk;

    public zzk(Context context, String str) {
        this(context, str, new ScheduledThreadPoolExecutor(0));
    }

    private zzk(Context context, String str, ScheduledExecutorService scheduledExecutorService) {
        this.zzbn = new ArrayDeque();
        this.zzbp = false;
        this.zzk = context.getApplicationContext();
        this.zzbl = new Intent(str).setPackage(this.zzk.getPackageName());
        this.zzbm = scheduledExecutorService;
    }

    private final synchronized void zzl() {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "flush queue called");
        }
        while (!this.zzbn.isEmpty()) {
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "found intent to be delivered");
            }
            if (this.zzbo == null || !this.zzbo.isBinderAlive()) {
                if (Log.isLoggable("EnhancedIntentService", 3)) {
                    boolean z = !this.zzbp;
                    StringBuilder sb = new StringBuilder(39);
                    sb.append("binder is dead. start connection? ");
                    sb.append(z);
                    Log.d("EnhancedIntentService", sb.toString());
                }
                if (!this.zzbp) {
                    this.zzbp = true;
                    try {
                        if (!ConnectionTracker.getInstance().bindService(this.zzk, this.zzbl, this, 65)) {
                            Log.e("EnhancedIntentService", "binding to the service failed");
                            while (!this.zzbn.isEmpty()) {
                                this.zzbn.poll().finish();
                            }
                        } else {
                            return;
                        }
                    } catch (SecurityException e) {
                        Log.e("EnhancedIntentService", "Exception while binding the service", e);
                    }
                }
                return;
            }
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "binder is alive, sending the intent.");
            }
            this.zzbo.zzd(this.zzbn.poll());
        }
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this) {
            this.zzbp = false;
            this.zzbo = (zzi) iBinder;
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                String valueOf = String.valueOf(componentName);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20);
                sb.append("onServiceConnected: ");
                sb.append(valueOf);
                Log.d("EnhancedIntentService", sb.toString());
            }
            zzl();
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            String valueOf = String.valueOf(componentName);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 23);
            sb.append("onServiceDisconnected: ");
            sb.append(valueOf);
            Log.d("EnhancedIntentService", sb.toString());
        }
        zzl();
    }

    public final synchronized void zzd(Intent intent, BroadcastReceiver.PendingResult pendingResult) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "new intent queued in the bind-strategy delivery");
        }
        this.zzbn.add(new zzg(intent, pendingResult, this.zzbm));
        zzl();
    }
}
