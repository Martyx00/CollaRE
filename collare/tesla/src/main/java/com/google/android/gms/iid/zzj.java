package com.google.android.gms.iid;

import android.util.Log;

/* access modifiers changed from: package-private */
public final class zzj implements Runnable {
    private final /* synthetic */ zzg zzbj;
    private final /* synthetic */ zzi zzbk;

    zzj(zzi zzi, zzg zzg) {
        this.zzbk = zzi;
        this.zzbj = zzg;
    }

    public final void run() {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "bg processing of the intent starting now");
        }
        this.zzbk.zzbi.handleIntent(this.zzbj.intent);
        this.zzbj.finish();
    }
}
