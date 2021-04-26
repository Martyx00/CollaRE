package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public final class ak implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    private final Context f3885a;

    /* renamed from: b  reason: collision with root package name */
    private final Intent f3886b;

    /* renamed from: c  reason: collision with root package name */
    private final ScheduledExecutorService f3887c;

    /* renamed from: d  reason: collision with root package name */
    private final Queue<ag> f3888d;
    private ai e;
    private boolean f;

    public ak(Context context, String str) {
        this(context, str, new ScheduledThreadPoolExecutor(0, new NamedThreadFactory("Firebase-FirebaseInstanceIdServiceConnection")));
    }

    private ak(Context context, String str, ScheduledExecutorService scheduledExecutorService) {
        this.f3888d = new ArrayDeque();
        this.f = false;
        this.f3885a = context.getApplicationContext();
        this.f3886b = new Intent(str).setPackage(this.f3885a.getPackageName());
        this.f3887c = scheduledExecutorService;
    }

    public final synchronized void a(Intent intent, BroadcastReceiver.PendingResult pendingResult) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "new intent queued in the bind-strategy delivery");
        }
        this.f3888d.add(new ag(intent, pendingResult, this.f3887c));
        a();
    }

    private final synchronized void a() {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "flush queue called");
        }
        while (!this.f3888d.isEmpty()) {
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "found intent to be delivered");
            }
            if (this.e == null || !this.e.isBinderAlive()) {
                if (Log.isLoggable("EnhancedIntentService", 3)) {
                    boolean z = !this.f;
                    StringBuilder sb = new StringBuilder(39);
                    sb.append("binder is dead. start connection? ");
                    sb.append(z);
                    Log.d("EnhancedIntentService", sb.toString());
                }
                if (!this.f) {
                    this.f = true;
                    try {
                        if (!ConnectionTracker.getInstance().bindService(this.f3885a, this.f3886b, this, 65)) {
                            Log.e("EnhancedIntentService", "binding to the service failed");
                            this.f = false;
                            b();
                        } else {
                            return;
                        }
                    } catch (SecurityException e2) {
                        Log.e("EnhancedIntentService", "Exception while binding the service", e2);
                    }
                }
                return;
            }
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "binder is alive, sending the intent.");
            }
            this.e.a(this.f3888d.poll());
        }
    }

    private final void b() {
        while (!this.f3888d.isEmpty()) {
            this.f3888d.poll().a();
        }
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this) {
            this.f = false;
            this.e = (ai) iBinder;
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                String valueOf = String.valueOf(componentName);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20);
                sb.append("onServiceConnected: ");
                sb.append(valueOf);
                Log.d("EnhancedIntentService", sb.toString());
            }
            if (iBinder == null) {
                Log.e("EnhancedIntentService", "Null service connection");
                b();
            } else {
                a();
            }
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
        a();
    }
}
