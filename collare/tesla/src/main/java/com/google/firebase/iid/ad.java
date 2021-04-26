package com.google.firebase.iid;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.content.i;
import android.util.Log;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class ad extends Service {

    /* renamed from: a  reason: collision with root package name */
    final ExecutorService f3866a;

    /* renamed from: b  reason: collision with root package name */
    private Binder f3867b;

    /* renamed from: c  reason: collision with root package name */
    private final Object f3868c;

    /* renamed from: d  reason: collision with root package name */
    private int f3869d;
    private int e;

    public ad() {
        String valueOf = String.valueOf(getClass().getSimpleName());
        this.f3866a = Executors.newSingleThreadExecutor(new NamedThreadFactory(valueOf.length() != 0 ? "Firebase-".concat(valueOf) : new String("Firebase-")));
        this.f3868c = new Object();
        this.e = 0;
    }

    /* access modifiers changed from: protected */
    public Intent a(Intent intent) {
        return intent;
    }

    public abstract void b(Intent intent);

    public boolean c(Intent intent) {
        return false;
    }

    public final synchronized IBinder onBind(Intent intent) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "Service received bind request");
        }
        if (this.f3867b == null) {
            this.f3867b = new ai(this);
        }
        return this.f3867b;
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        synchronized (this.f3868c) {
            this.f3869d = i2;
            this.e++;
        }
        Intent a2 = a(intent);
        if (a2 == null) {
            d(intent);
            return 2;
        } else if (c(a2)) {
            d(intent);
            return 2;
        } else {
            this.f3866a.execute(new af(this, a2, intent));
            return 3;
        }
    }

    /* access modifiers changed from: private */
    public final void d(Intent intent) {
        if (intent != null) {
            i.completeWakefulIntent(intent);
        }
        synchronized (this.f3868c) {
            this.e--;
            if (this.e == 0) {
                stopSelfResult(this.f3869d);
            }
        }
    }
}
