package com.teslamotors.plugins.notifications;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.u;

public class RegistrationIntentService extends u {
    private static int j = 1;

    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, RegistrationIntentService.class, j, intent);
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v4.app.u
    public void a(Intent intent) {
        b.a(getApplicationContext()).b();
    }
}
