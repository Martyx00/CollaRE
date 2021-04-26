package com.teslamotors.plugins.calendar;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.u;
import android.util.Log;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableNativeMap;
import com.teslamotors.plugins.biometricauthentication.BiometricAuthenticationModule;
import com.teslamotors.plugins.client.d;
import com.teslamotors.plugins.client.d.a.a;
import com.teslamotors.plugins.client.d.a.b;
import com.teslamotors.plugins.client.f;
import org.json.JSONObject;

public class CalendarSyncService extends u {
    private static final String j = "CalendarSyncService";

    public static void a(Context context, Intent intent) {
        enqueueWork(context, CalendarSyncService.class, 1234, intent);
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v4.app.u
    public void a(Intent intent) {
        boolean booleanExtra = intent.getBooleanExtra("force_sync", true);
        boolean booleanExtra2 = intent.getBooleanExtra("sync_enabled", false);
        a(intent.getStringExtra("vid"), intent.getIntExtra("retries", 0), intent.getStringExtra("reason"), getApplicationContext(), Boolean.valueOf(booleanExtra), Boolean.valueOf(booleanExtra2), null);
    }

    static c a(String str, String str2, Context context, Boolean bool, Boolean bool2) {
        a aVar;
        if (str == null) {
            Log.i(j, "No vehicle id. Not syncing");
            return new c("CALENDAR_SYNC_NO_SELECTED_VEHICLE", null);
        } else if (bool2.booleanValue() || bool.booleanValue()) {
            if (bool2.booleanValue()) {
                aVar = a.a(context);
            } else {
                aVar = new a();
                aVar.a(true);
            }
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            f a2 = f.a(context);
            JSONObject jSONObject = new JSONObject();
            try {
                JSONObject b2 = aVar.b();
                b2.put("reason", str2);
                b2.put("uuid", a2.j());
                b2.put("phone_name", defaultAdapter != null ? defaultAdapter.getName() : null);
                b2.put("access_disabled", aVar.a());
                jSONObject.put("calendar_data", b2);
                return new c(null, jSONObject);
            } catch (Exception e) {
                Log.e(j, "Failed to serialize calendar data", e);
                return new c("CALENDAR_SYNC_FAILED_TO_SEND", null);
            }
        } else {
            Log.i(j, "Calendar sync is off and is not forced. Not syncing");
            return new c("CALENDAR_SYNC_NOT_REQUIRED", null);
        }
    }

    static String b(String str, String str2, Context context, Boolean bool, Boolean bool2) {
        c a2 = a(str, str2, context, bool, bool2);
        if (a2.f5489a != null) {
            return a2.f5489a;
        }
        d a3 = f.a(context).a(a2.f5490b, str, "CALENDAR_SYNC", null).a();
        if (a3 != null) {
            return d.n.contains(a3) ? "CALENDAR_SYNC_NOT_REQUIRED" : "CALENDAR_SYNC_FAILED_TO_SEND";
        }
        return "CALENDAR_SYNC_NO_ERROR";
    }

    static void a(final String str, int i, String str2, Context context, Boolean bool, Boolean bool2, final Promise promise) {
        final c a2 = a(str, str2, context, bool, bool2);
        if (a2.f5489a != null) {
            b(promise, a2.f5489a);
            return;
        }
        final f a3 = f.a(context);
        a aVar = new a();
        aVar.a(i);
        b bVar = new b();
        bVar.a(500);
        bVar.b(2000);
        aVar.a(bVar);
        aVar.a(new a.c() {
            /* class com.teslamotors.plugins.calendar.CalendarSyncService.AnonymousClass1 */

            @Override // com.teslamotors.plugins.client.d.a.a.c
            public void a(final a.d dVar) {
                a3.a(a2.f5490b, str, "CALENDAR_SYNC", new com.teslamotors.plugins.client.a.b() {
                    /* class com.teslamotors.plugins.calendar.CalendarSyncService.AnonymousClass1.AnonymousClass1 */

                    @Override // com.teslamotors.plugins.client.a.b
                    public void a(JSONObject jSONObject) {
                        dVar.a(null, jSONObject);
                    }

                    @Override // com.teslamotors.plugins.client.a.b
                    public void a(d dVar) {
                        dVar.a(dVar.a(), null);
                    }
                });
            }
        });
        aVar.a(new a.AbstractC0150a() {
            /* class com.teslamotors.plugins.calendar.CalendarSyncService.AnonymousClass2 */

            @Override // com.teslamotors.plugins.client.d.a.a.AbstractC0150a
            public void a(a aVar, String str, Object obj) {
                if (str != null) {
                    String str2 = CalendarSyncService.j;
                    Log.e(str2, "Failed to sync calendar:" + str);
                    CalendarSyncService.b(promise, "CALENDAR_SYNC_FAILED_TO_SEND");
                } else if (obj != null) {
                    CalendarSyncService.b(promise, "CALENDAR_SYNC_NO_ERROR");
                }
            }
        });
        aVar.a(new a.b() {
            /* class com.teslamotors.plugins.calendar.CalendarSyncService.AnonymousClass3 */

            /* renamed from: a  reason: collision with root package name */
            private int f5475a = 0;

            @Override // com.teslamotors.plugins.client.d.a.a.b
            public boolean a(a aVar, String str, Object obj) {
                if (d.o.contains(str)) {
                    return false;
                }
                if (d.OWNERAPI_ERROR_UNKNOWN_ERROR.a().equalsIgnoreCase(str)) {
                    this.f5475a++;
                }
                if (this.f5475a >= 3 || obj != null) {
                    return false;
                }
                return true;
            }
        });
        aVar.a();
    }

    /* access modifiers changed from: private */
    public static void b(Promise promise, String str) {
        if (promise != null) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString(BiometricAuthenticationModule.RESULT, str);
            promise.resolve(writableNativeMap);
        }
    }

    public static Intent b(Context context, Intent intent) {
        f a2 = f.a(context);
        String m = a2.m();
        if (m == null || !"VEHICLE".equals(a2.n())) {
            Log.i(j, "Not building calendar service intent; no stored vehicle id or non-vehicle product selected");
            return null;
        }
        boolean booleanExtra = intent.getBooleanExtra("force_sync", false);
        boolean booleanExtra2 = intent.getBooleanExtra("sync_enabled", f.a(context).p());
        String stringExtra = intent.getStringExtra("reason");
        int intExtra = intent.getIntExtra("retries", 0);
        if (stringExtra == null) {
            stringExtra = "calendar-updated";
            intExtra = 2;
        }
        Intent intent2 = new Intent(context, CalendarSyncService.class);
        intent2.putExtra("vid", m);
        intent2.putExtra("force_sync", booleanExtra);
        intent2.putExtra("sync_enabled", booleanExtra2);
        intent2.putExtra("reason", stringExtra);
        intent2.putExtra("retries", intExtra);
        return intent2;
    }
}
