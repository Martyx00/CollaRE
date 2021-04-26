package com.google.android.gms.gcm;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* access modifiers changed from: package-private */
public final class zzf extends Handler {
    private final /* synthetic */ GoogleCloudMessaging zzai;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzf(GoogleCloudMessaging googleCloudMessaging, Looper looper) {
        super(looper);
        this.zzai = googleCloudMessaging;
    }

    public final void handleMessage(Message message) {
        if (message == null || !(message.obj instanceof Intent)) {
            Log.w("GCM", "Dropping invalid message");
        }
        Intent intent = (Intent) message.obj;
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(intent.getAction())) {
            this.zzai.zzag.add(intent);
        } else if (!(this.zzai.zzd(intent))) {
            intent.setPackage(this.zzai.zzk.getPackageName());
            this.zzai.zzk.sendBroadcast(intent);
        }
    }
}
