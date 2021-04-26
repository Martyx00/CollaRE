package com.google.firebase.iid;

import android.util.Log;

/* access modifiers changed from: package-private */
public final class aj implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final /* synthetic */ ag f3883a;

    /* renamed from: b  reason: collision with root package name */
    private final /* synthetic */ ai f3884b;

    aj(ai aiVar, ag agVar) {
        this.f3884b = aiVar;
        this.f3883a = agVar;
    }

    public final void run() {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "bg processing of the intent starting now");
        }
        this.f3884b.f3882a.b(this.f3883a.f3876a);
        this.f3883a.a();
    }
}
