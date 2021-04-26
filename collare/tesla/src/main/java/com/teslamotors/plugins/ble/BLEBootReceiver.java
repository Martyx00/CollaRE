package com.teslamotors.plugins.ble;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.teslamotors.plugins.client.f;

public class BLEBootReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5338a = "BLEBootReceiver";

    public void onReceive(Context context, Intent intent) {
        f a2 = f.a(context);
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED") && BLEService.a(context, a2.y(), a2.x())) {
            String y = a2.y();
            String e = a2.e(y);
            Intent intent2 = new Intent(context, BLEService.class);
            intent2.putExtra("VIN", y);
            intent2.putExtra("ACCOUNT_EMAIL", a2.x());
            intent2.putExtra("VEHICLE_NAME", e);
            if (Build.VERSION.SDK_INT < 26 || !BLEService.b(context, a2.y(), a2.x())) {
                context.startService(intent2);
            } else {
                context.startForegroundService(intent2);
            }
        }
    }
}
