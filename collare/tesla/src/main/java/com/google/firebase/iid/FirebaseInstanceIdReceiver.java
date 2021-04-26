package com.google.firebase.iid;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.content.i;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.PlatformVersion;

public final class FirebaseInstanceIdReceiver extends i {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f3853a = false;

    /* renamed from: b  reason: collision with root package name */
    private static ak f3854b;

    /* renamed from: c  reason: collision with root package name */
    private static ak f3855c;

    public final void onReceive(Context context, Intent intent) {
        if (intent != null) {
            Parcelable parcelableExtra = intent.getParcelableExtra("wrapped_intent");
            Intent intent2 = parcelableExtra instanceof Intent ? (Intent) parcelableExtra : null;
            if (intent2 != null) {
                a(context, intent2, intent.getAction());
            } else {
                a(context, intent, intent.getAction());
            }
        }
    }

    private final void a(Context context, Intent intent, String str) {
        String str2 = null;
        intent.setComponent(null);
        intent.setPackage(context.getPackageName());
        if (Build.VERSION.SDK_INT <= 18) {
            intent.removeCategory(context.getPackageName());
        }
        String stringExtra = intent.getStringExtra("gcm.rawData64");
        if (stringExtra != null) {
            intent.putExtra("rawData", Base64.decode(stringExtra, 0));
            intent.removeExtra("gcm.rawData64");
        }
        if ("google.com/iid".equals(intent.getStringExtra("from")) || "com.google.firebase.INSTANCE_ID_EVENT".equals(str)) {
            str2 = "com.google.firebase.INSTANCE_ID_EVENT";
        } else if ("com.google.android.c2dm.intent.RECEIVE".equals(str) || "com.google.firebase.MESSAGING_EVENT".equals(str)) {
            str2 = "com.google.firebase.MESSAGING_EVENT";
        } else {
            Log.d("FirebaseInstanceId", "Unexpected intent");
        }
        int i = -1;
        if (str2 != null) {
            i = a(this, context, str2, intent);
        }
        if (isOrderedBroadcast()) {
            setResultCode(i);
        }
    }

    @ShowFirstParty
    @SuppressLint({"InlinedApi"})
    public static int a(BroadcastReceiver broadcastReceiver, Context context, String str, Intent intent) {
        boolean z = true;
        boolean z2 = PlatformVersion.isAtLeastO() && context.getApplicationInfo().targetSdkVersion >= 26;
        if ((intent.getFlags() & 268435456) == 0) {
            z = false;
        }
        if (z2 && !z) {
            return b(broadcastReceiver, context, str, intent);
        }
        int a2 = y.a().a(context, str, intent);
        if (!PlatformVersion.isAtLeastO() || a2 != 402) {
            return a2;
        }
        b(broadcastReceiver, context, str, intent);
        return 403;
    }

    private static int b(BroadcastReceiver broadcastReceiver, Context context, String str, Intent intent) {
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf = String.valueOf(str);
            Log.d("FirebaseInstanceId", valueOf.length() != 0 ? "Binding to service: ".concat(valueOf) : new String("Binding to service: "));
        }
        if (broadcastReceiver.isOrderedBroadcast()) {
            broadcastReceiver.setResultCode(-1);
        }
        a(context, str).a(intent, broadcastReceiver.goAsync());
        return -1;
    }

    private static synchronized ak a(Context context, String str) {
        synchronized (FirebaseInstanceIdReceiver.class) {
            if ("com.google.firebase.MESSAGING_EVENT".equals(str)) {
                if (f3855c == null) {
                    f3855c = new ak(context, str);
                }
                return f3855c;
            }
            if (f3854b == null) {
                f3854b = new ak(context, str);
            }
            return f3854b;
        }
    }
}
