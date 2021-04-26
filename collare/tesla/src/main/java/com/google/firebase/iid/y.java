package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.util.l;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.Queue;

public final class y {

    /* renamed from: b  reason: collision with root package name */
    private static y f3955b;

    /* renamed from: a  reason: collision with root package name */
    final Queue<Intent> f3956a = new ArrayDeque();

    /* renamed from: c  reason: collision with root package name */
    private final l<String, String> f3957c = new l<>();

    /* renamed from: d  reason: collision with root package name */
    private Boolean f3958d = null;
    private final Queue<Intent> e = new ArrayDeque();

    public static synchronized y a() {
        y yVar;
        synchronized (y.class) {
            if (f3955b == null) {
                f3955b = new y();
            }
            yVar = f3955b;
        }
        return yVar;
    }

    private y() {
    }

    public static PendingIntent a(Context context, int i, Intent intent, int i2) {
        return PendingIntent.getBroadcast(context, i, b(context, "com.google.firebase.MESSAGING_EVENT", intent), 1073741824);
    }

    public static void a(Context context, Intent intent) {
        context.sendBroadcast(b(context, "com.google.firebase.INSTANCE_ID_EVENT", intent));
    }

    public static void b(Context context, Intent intent) {
        context.sendBroadcast(b(context, "com.google.firebase.MESSAGING_EVENT", intent));
    }

    private static Intent b(Context context, String str, Intent intent) {
        Intent intent2 = new Intent(context, FirebaseInstanceIdReceiver.class);
        intent2.setAction(str);
        intent2.putExtra("wrapped_intent", intent);
        return intent2;
    }

    public final Intent b() {
        return this.e.poll();
    }

    public final int a(Context context, String str, Intent intent) {
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf = String.valueOf(str);
            Log.d("FirebaseInstanceId", valueOf.length() != 0 ? "Starting service: ".concat(valueOf) : new String("Starting service: "));
        }
        char c2 = 65535;
        int hashCode = str.hashCode();
        if (hashCode != -842411455) {
            if (hashCode == 41532704 && str.equals("com.google.firebase.MESSAGING_EVENT")) {
                c2 = 1;
            }
        } else if (str.equals("com.google.firebase.INSTANCE_ID_EVENT")) {
            c2 = 0;
        }
        switch (c2) {
            case 0:
                this.f3956a.offer(intent);
                break;
            case 1:
                this.e.offer(intent);
                break;
            default:
                String valueOf2 = String.valueOf(str);
                Log.w("FirebaseInstanceId", valueOf2.length() != 0 ? "Unknown service action: ".concat(valueOf2) : new String("Unknown service action: "));
                return 500;
        }
        Intent intent2 = new Intent(str);
        intent2.setPackage(context.getPackageName());
        return c(context, intent2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00db A[Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f2 A[Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00f7 A[Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0104 A[Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x010e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int c(android.content.Context r7, android.content.Intent r8) {
        /*
        // Method dump skipped, instructions count: 326
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.y.c(android.content.Context, android.content.Intent):int");
    }
}
