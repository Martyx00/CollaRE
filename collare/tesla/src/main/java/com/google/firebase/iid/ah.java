package com.google.firebase.iid;

import android.content.Intent;
import android.util.Log;

final /* synthetic */ class ah implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final ag f3880a;

    /* renamed from: b  reason: collision with root package name */
    private final Intent f3881b;

    ah(ag agVar, Intent intent) {
        this.f3880a = agVar;
        this.f3881b = intent;
    }

    public final void run() {
        ag agVar = this.f3880a;
        String action = this.f3881b.getAction();
        StringBuilder sb = new StringBuilder(String.valueOf(action).length() + 61);
        sb.append("Service took too long to process intent: ");
        sb.append(action);
        sb.append(" App may get closed.");
        Log.w("EnhancedIntentService", sb.toString());
        agVar.a();
    }
}
