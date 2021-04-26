package com.google.android.gms.stats;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.i;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.stats.WakeLockTracker;

@ShowFirstParty
@KeepForSdk
public abstract class GCoreWakefulBroadcastReceiver extends i {
    private static String TAG = "GCoreWakefulBroadcastReceiver";

    @KeepForSdk
    @SuppressLint({"UnwrappedWakefulBroadcastReceiver"})
    public static boolean completeWakefulIntent(Context context, Intent intent) {
        if (intent == null) {
            return false;
        }
        if (context != null) {
            WakeLockTracker.getInstance().registerReleaseEvent(context, intent);
        } else {
            String str = TAG;
            String valueOf = String.valueOf(intent.toUri(0));
            Log.w(str, valueOf.length() != 0 ? "context shouldn't be null. intent: ".concat(valueOf) : new String("context shouldn't be null. intent: "));
        }
        return i.completeWakefulIntent(intent);
    }
}
