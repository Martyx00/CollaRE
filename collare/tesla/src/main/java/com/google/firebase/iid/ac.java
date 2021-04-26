package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class ac extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    private ab f3865a;

    public ac(ab abVar) {
        this.f3865a = abVar;
    }

    public final void a() {
        if (FirebaseInstanceId.h()) {
            Log.d("FirebaseInstanceId", "Connectivity change received registered");
        }
        this.f3865a.a().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public final void onReceive(Context context, Intent intent) {
        ab abVar = this.f3865a;
        if (abVar != null && abVar.b()) {
            if (FirebaseInstanceId.h()) {
                Log.d("FirebaseInstanceId", "Connectivity changed. Starting background sync.");
            }
            FirebaseInstanceId.a(this.f3865a, 0);
            this.f3865a.a().unregisterReceiver(this);
            this.f3865a = null;
        }
    }
}
