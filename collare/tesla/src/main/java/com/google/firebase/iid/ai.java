package com.google.firebase.iid;

import android.os.Binder;
import android.os.Process;
import android.util.Log;

public final class ai extends Binder {

    /* renamed from: a  reason: collision with root package name */
    private final ad f3882a;

    ai(ad adVar) {
        this.f3882a = adVar;
    }

    public final void a(ag agVar) {
        if (Binder.getCallingUid() == Process.myUid()) {
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "service received new intent via bind strategy");
            }
            if (this.f3882a.c(agVar.f3876a)) {
                agVar.a();
                return;
            }
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "intent being queued for bg execution");
            }
            this.f3882a.f3866a.execute(new aj(this, agVar));
            return;
        }
        throw new SecurityException("Binding only allowed within app");
    }
}
