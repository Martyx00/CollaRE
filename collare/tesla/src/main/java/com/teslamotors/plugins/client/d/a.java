package com.teslamotors.plugins.client.d;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import com.teslamotors.plugins.client.f;
import java.util.HashMap;
import java.util.List;

/* compiled from: ContactsHelper */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5516a = "a";

    public static boolean a(Context context) {
        return f.a(context).d("android.permission.READ_CONTACTS");
    }

    public static void a(final Context context, final List<String> list, final com.teslamotors.plugins.client.a.a aVar) {
        new AsyncTask() {
            /* class com.teslamotors.plugins.client.d.a.AnonymousClass1 */

            /* access modifiers changed from: protected */
            /* JADX WARNING: Removed duplicated region for block: B:32:0x00b5 A[Catch:{ Exception -> 0x013f }] */
            /* JADX WARNING: Removed duplicated region for block: B:56:0x0143 A[Catch:{ Exception -> 0x01d0 }] */
            /* JADX WARNING: Removed duplicated region for block: B:58:0x0146 A[Catch:{ Exception -> 0x01d0 }] */
            @Override // android.os.AsyncTask
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Object doInBackground(java.lang.Object[] r18) {
                /*
                // Method dump skipped, instructions count: 495
                */
                throw new UnsupportedOperationException("Method not decompiled: com.teslamotors.plugins.client.d.a.AnonymousClass1.doInBackground(java.lang.Object[]):java.lang.Object");
            }
        }.execute(new Object[0]);
    }

    /* access modifiers changed from: private */
    public static HashMap<String, Object> b(String str, HashMap<String, HashMap<String, Object>> hashMap) {
        HashMap<String, Object> hashMap2 = hashMap.get(str);
        return hashMap2 == null ? new HashMap<>() : hashMap2;
    }

    /* access modifiers changed from: private */
    public static void b(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            try {
                cursor.close();
            } catch (Exception e) {
                Log.e(f5516a, "Failed to close cursor", e);
            }
        }
    }
}
