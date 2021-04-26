package com.google.android.gms.tagmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.util.VisibleForTesting;

/* access modifiers changed from: package-private */
public class zzdn extends BroadcastReceiver {
    @VisibleForTesting
    private static final String zzaav = "com.google.android.gms.tagmanager.zzdn";
    private final zzfm zzbbk;

    zzdn(zzfm zzfm) {
        this.zzbbk = zzfm;
    }

    public static void zzp(Context context) {
        Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(zzaav, true);
        context.sendBroadcast(intent);
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            Bundle extras = intent.getExtras();
            Boolean bool = Boolean.FALSE;
            if (extras != null) {
                bool = Boolean.valueOf(intent.getExtras().getBoolean("noConnectivity"));
            }
            this.zzbbk.zzp(!bool.booleanValue());
        } else if ("com.google.analytics.RADIO_POWERED".equals(action) && !intent.hasExtra(zzaav)) {
            this.zzbbk.zzpb();
        }
    }
}
