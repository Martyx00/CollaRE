package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import java.util.Collections;

final class zabo implements Runnable {
    private final /* synthetic */ ConnectionResult zaiz;
    private final /* synthetic */ GoogleApiManager.zac zajg;

    zabo(GoogleApiManager.zac zac, ConnectionResult connectionResult) {
        this.zajg = zac;
        this.zaiz = connectionResult;
    }

    public final void run() {
        if (this.zaiz.isSuccess()) {
            this.zajg.zajf = true;
            if (this.zajg.zaio.requiresSignIn()) {
                this.zajg.zabr();
                return;
            }
            try {
                this.zajg.zaio.getRemoteService(null, Collections.emptySet());
            } catch (SecurityException e) {
                Log.e("GoogleApiManager", "Failed to get service from broker. ", e);
                ((GoogleApiManager.zaa) GoogleApiManager.this.zaii.get(this.zajg.zafq)).onConnectionFailed(new ConnectionResult(10));
            }
        } else {
            ((GoogleApiManager.zaa) GoogleApiManager.this.zaii.get(this.zajg.zafq)).onConnectionFailed(this.zaiz);
        }
    }
}
