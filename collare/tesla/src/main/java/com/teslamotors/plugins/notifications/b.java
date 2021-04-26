package com.teslamotors.plugins.notifications;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.provider.Settings;
import android.support.v4.app.y;
import com.teslamotors.plugins.client.f;
import com.teslamotors.plugins.notifications.d;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* compiled from: NotificationHelper */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5617a = "b";

    /* renamed from: b  reason: collision with root package name */
    private static b f5618b;

    /* renamed from: c  reason: collision with root package name */
    private final Context f5619c;

    /* renamed from: d  reason: collision with root package name */
    private e f5620d = null;

    private String d() {
        return "#CC0000";
    }

    public static b a(Context context) {
        if (f5618b == null) {
            f5618b = new b(context);
        }
        return f5618b;
    }

    private b(Context context) {
        Class<?> cls;
        this.f5619c = context;
        try {
            if ("CN".equals("US")) {
                cls = Class.forName("com.teslamotors.plugins.notifications.jpush.TeslaJPushRegistrationHelper");
            } else {
                cls = Class.forName("com.teslamotors.plugins.notifications.fcm.TeslaFcmRegistrationHelper");
            }
            this.f5620d = (e) cls.newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
            com.teslamotors.plugins.client.b.c(f5617a, "Failed to instantiate TokenRegistrationHelper");
        }
    }

    @TargetApi(26)
    public void a() {
        NotificationManager notificationManager = (NotificationManager) this.f5619c.getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            Resources resources = this.f5619c.getResources();
            NotificationChannel notificationChannel = new NotificationChannel("default_channel", resources.getString(d.b.notification_channel_default_name), 4);
            notificationChannel.setDescription(resources.getString(d.b.notification_channel_default_description));
            notificationChannel.setLockscreenVisibility(1);
            NotificationChannel notificationChannel2 = new NotificationChannel("phone_key_service_channel", resources.getString(d.b.notification_channel_phone_service_name), 2);
            notificationChannel2.setDescription(resources.getString(d.b.notification_channel_phone_service_description));
            notificationManager.createNotificationChannels(Arrays.asList(notificationChannel, notificationChannel2));
        }
    }

    public void b() {
        e eVar = this.f5620d;
        if (eVar != null) {
            eVar.a(this.f5619c);
        }
    }

    public void c() {
        e eVar = this.f5620d;
        if (eVar != null) {
            eVar.b(this.f5619c);
        }
    }

    public void a(Map<String, String> map) {
        a(new a(map, e()), true);
    }

    private void a(a aVar, boolean z) {
        if (aVar.f()) {
            f.a(this.f5619c).d(aVar.i());
        }
        if (aVar.d()) {
            f.a(this.f5619c).v();
        }
        String j = aVar.j();
        if (j != null && aVar.e()) {
            if (z) {
                NotificationManager notificationManager = (NotificationManager) this.f5619c.getSystemService("notification");
                Intent D = f.a(this.f5619c).D();
                if (D != null && notificationManager != null) {
                    D.addFlags(PKIFailureInfo.duplicateCertReq);
                    D.putExtra("notification", j);
                    D.setAction(Long.toString(System.currentTimeMillis()));
                    PendingIntent activity = PendingIntent.getActivity(this.f5619c, 0, D, 134217728);
                    String g = aVar.g();
                    y.c a2 = new y.c(this.f5619c, "default_channel").c(Color.parseColor(d())).a(d.a.ic_notification).b(true).a((CharSequence) aVar.b()).b((CharSequence) g).a(new y.b().a(g)).b(2).a(activity).d(1).a(aVar.h() * 1000);
                    if (aVar.a() > 0) {
                        a2.a(Settings.System.DEFAULT_NOTIFICATION_URI);
                    }
                    notificationManager.notify(aVar.c(), a2.b());
                } else {
                    return;
                }
            }
            b(j);
        }
    }

    private void b(String str) {
        Intent intent = new Intent(PushNotificationModule.NOTIFICATION_REACT_RECEIVED_EVENT);
        intent.putExtra("notification", str);
        android.support.v4.content.f.a(this.f5619c).a(intent);
    }

    public void a(String str) {
        Intent intent = new Intent("remoteNotificationRegistered");
        intent.putExtra("deviceToken", str);
        android.support.v4.content.f.a(this.f5619c).a(intent);
    }

    private boolean e() {
        return a(this.f5619c.getPackageName(), true);
    }

    private boolean a(String str, boolean z) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        ActivityManager activityManager = (ActivityManager) this.f5619c.getSystemService("activity");
        if (activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName.equals(str) && (!z || runningAppProcessInfo.importance == 100)) {
                return true;
            }
        }
        return false;
    }
}
