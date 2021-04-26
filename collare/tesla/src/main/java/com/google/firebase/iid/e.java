package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class e {

    /* renamed from: a */
    private static e f3920a;

    /* renamed from: b */
    private final Context f3921b;

    /* renamed from: c */
    private final ScheduledExecutorService f3922c;

    /* renamed from: d */
    private g f3923d = new g(this);
    private int e = 1;

    public static synchronized e a(Context context) {
        e eVar;
        synchronized (e.class) {
            if (f3920a == null) {
                f3920a = new e(context, Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("MessengerIpcClient")));
            }
            eVar = f3920a;
        }
        return eVar;
    }

    private e(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.f3922c = scheduledExecutorService;
        this.f3921b = context.getApplicationContext();
    }

    public final Task<Void> a(int i, Bundle bundle) {
        return a(new m(a(), 2, bundle));
    }

    public final Task<Bundle> b(int i, Bundle bundle) {
        return a(new p(a(), 1, bundle));
    }

    private final synchronized <T> Task<T> a(n<T> nVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(nVar);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 9);
            sb.append("Queueing ");
            sb.append(valueOf);
            Log.d("MessengerIpcClient", sb.toString());
        }
        if (!this.f3923d.a(nVar)) {
            this.f3923d = new g(this);
            this.f3923d.a(nVar);
        }
        return nVar.f3936b.getTask();
    }

    private final synchronized int a() {
        int i;
        i = this.e;
        this.e = i + 1;
        return i;
    }
}
