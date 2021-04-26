package com.teslamotors.plugins.calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CalendarReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5468a = "CalendarReceiver";

    public void onReceive(Context context, Intent intent) {
        if (CalendarSyncService.b(context, intent) != null) {
            CalendarSyncService.a(context, intent);
        }
    }
}
