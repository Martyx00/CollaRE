package com.google.android.gms.iid;

import android.content.Intent;
import android.util.Log;

final class zzh implements Runnable {
    private final /* synthetic */ Intent zzbb;
    private final /* synthetic */ zzg zzbh;

    zzh(zzg zzg, Intent intent) {
        this.zzbh = zzg;
        this.zzbb = intent;
    }

    public final void run() {
        String action = this.zzbb.getAction();
        StringBuilder sb = new StringBuilder(String.valueOf(action).length() + 61);
        sb.append("Service took too long to process intent: ");
        sb.append(action);
        sb.append(" App may get closed.");
        Log.w("EnhancedIntentService", sb.toString());
        this.zzbh.finish();
    }
}
