package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.firebase.iid.ad;
import com.google.firebase.iid.y;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

public class FirebaseMessagingService extends ad {

    /* renamed from: b  reason: collision with root package name */
    private static final Queue<String> f3963b = new ArrayDeque(10);

    public void a() {
    }

    public void a(d dVar) {
    }

    public void a(String str) {
    }

    public void a(String str, Exception exc) {
    }

    public void b(String str) {
    }

    /* access modifiers changed from: protected */
    @Override // com.google.firebase.iid.ad
    public final Intent a(Intent intent) {
        return y.a().b();
    }

    @Override // com.google.firebase.iid.ad
    public final boolean c(Intent intent) {
        if (!"com.google.firebase.messaging.NOTIFICATION_OPEN".equals(intent.getAction())) {
            return false;
        }
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("pending_intent");
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException unused) {
                Log.e("FirebaseMessaging", "Notification pending intent canceled");
            }
        }
        if (!b.e(intent)) {
            return true;
        }
        b.b(intent);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00f7, code lost:
        if (r0.equals(com.google.android.gms.gcm.GoogleCloudMessaging.MESSAGE_TYPE_SEND_EVENT) == false) goto L_0x0118;
     */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x012f  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x015a  */
    @Override // com.google.firebase.iid.ad
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b(android.content.Intent r10) {
        /*
        // Method dump skipped, instructions count: 476
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.FirebaseMessagingService.b(android.content.Intent):void");
    }

    static void a(Bundle bundle) {
        Iterator<String> it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next != null && next.startsWith("google.c.")) {
                it.remove();
            }
        }
    }
}
