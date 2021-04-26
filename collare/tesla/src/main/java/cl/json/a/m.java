package cl.json.a;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.os.Build;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactContext;

/* compiled from: TargetChosenReceiver */
public class m extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f1380a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private static String f1381b;

    /* renamed from: c  reason: collision with root package name */
    private static m f1382c;

    /* renamed from: d  reason: collision with root package name */
    private static Callback f1383d;
    private static Callback e;

    public static boolean a() {
        return Build.VERSION.SDK_INT >= 22;
    }

    public static void a(Callback callback, Callback callback2) {
        f1383d = callback;
        e = callback2;
    }

    @TargetApi(22)
    public static IntentSender a(ReactContext reactContext) {
        synchronized (f1380a) {
            if (f1381b == null) {
                f1381b = reactContext.getPackageName() + "/" + m.class.getName() + "_ACTION";
            }
            Context applicationContext = reactContext.getApplicationContext();
            if (f1382c != null) {
                applicationContext.unregisterReceiver(f1382c);
            }
            f1382c = new m();
            applicationContext.registerReceiver(f1382c, new IntentFilter(f1381b));
        }
        Intent intent = new Intent(f1381b);
        intent.setPackage(reactContext.getPackageName());
        intent.putExtra("receiver_token", f1382c.hashCode());
        return PendingIntent.getBroadcast(reactContext, 0, intent, 1342177280).getIntentSender();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        if (r5.hasExtra("receiver_token") == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0029, code lost:
        if (r5.getIntExtra("receiver_token", 0) == hashCode()) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        r4 = (android.content.ComponentName) r5.getParcelableExtra("android.intent.extra.CHOSEN_COMPONENT");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0036, code lost:
        if (r4 == null) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0038, code lost:
        a(true, true, r4.flattenToString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004a, code lost:
        a(true, true, "OK");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(android.content.Context r4, android.content.Intent r5) {
        /*
            r3 = this;
            java.lang.Object r0 = cl.json.a.m.f1380a
            monitor-enter(r0)
            cl.json.a.m r1 = cl.json.a.m.f1382c     // Catch:{ all -> 0x005b }
            if (r1 == r3) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x005b }
            return
        L_0x0009:
            android.content.Context r4 = r4.getApplicationContext()     // Catch:{ all -> 0x005b }
            cl.json.a.m r1 = cl.json.a.m.f1382c     // Catch:{ all -> 0x005b }
            r4.unregisterReceiver(r1)     // Catch:{ all -> 0x005b }
            r4 = 0
            cl.json.a.m.f1382c = r4     // Catch:{ all -> 0x005b }
            monitor-exit(r0)     // Catch:{ all -> 0x005b }
            java.lang.String r4 = "receiver_token"
            boolean r4 = r5.hasExtra(r4)
            if (r4 == 0) goto L_0x005a
            java.lang.String r4 = "receiver_token"
            r0 = 0
            int r4 = r5.getIntExtra(r4, r0)
            int r1 = r3.hashCode()
            if (r4 == r1) goto L_0x002c
            goto L_0x005a
        L_0x002c:
            java.lang.String r4 = "android.intent.extra.CHOSEN_COMPONENT"
            android.os.Parcelable r4 = r5.getParcelableExtra(r4)
            android.content.ComponentName r4 = (android.content.ComponentName) r4
            r5 = 2
            r1 = 1
            if (r4 == 0) goto L_0x004a
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r1)
            r5[r0] = r2
            java.lang.String r4 = r4.flattenToString()
            r5[r1] = r4
            a(r1, r5)
            goto L_0x0059
        L_0x004a:
            java.lang.Object[] r4 = new java.lang.Object[r5]
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r1)
            r4[r0] = r5
            java.lang.String r5 = "OK"
            r4[r1] = r5
            a(r1, r4)
        L_0x0059:
            return
        L_0x005a:
            return
        L_0x005b:
            r4 = move-exception
            monitor-exit(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: cl.json.a.m.onReceive(android.content.Context, android.content.Intent):void");
    }

    public static void a(boolean z, Object... objArr) {
        if (z) {
            Callback callback = f1383d;
            if (callback != null) {
                callback.invoke(objArr);
            }
        } else {
            Callback callback2 = e;
            if (callback2 != null) {
                callback2.invoke(objArr);
            }
        }
        f1383d = null;
        e = null;
    }
}
