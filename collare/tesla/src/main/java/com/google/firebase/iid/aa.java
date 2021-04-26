package com.google.firebase.iid;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public final class aa {

    /* renamed from: b  reason: collision with root package name */
    private static final long f3857b = TimeUnit.DAYS.toMillis(7);

    /* renamed from: a  reason: collision with root package name */
    final String f3858a;

    /* renamed from: c  reason: collision with root package name */
    private final String f3859c;

    /* renamed from: d  reason: collision with root package name */
    private final long f3860d;

    private aa(String str, String str2, long j) {
        this.f3858a = str;
        this.f3859c = str2;
        this.f3860d = j;
    }

    static aa a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (!str.startsWith("{")) {
            return new aa(str, null, 0);
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new aa(jSONObject.getString("token"), jSONObject.getString("appVersion"), jSONObject.getLong(AppMeasurement.Param.TIMESTAMP));
        } catch (JSONException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 23);
            sb.append("Failed to parse token: ");
            sb.append(valueOf);
            Log.w("FirebaseInstanceId", sb.toString());
            return null;
        }
    }

    static String a(String str, String str2, long j) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("token", str);
            jSONObject.put("appVersion", str2);
            jSONObject.put(AppMeasurement.Param.TIMESTAMP, j);
            return jSONObject.toString();
        } catch (JSONException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 24);
            sb.append("Failed to encode token: ");
            sb.append(valueOf);
            Log.w("FirebaseInstanceId", sb.toString());
            return null;
        }
    }

    static String a(aa aaVar) {
        if (aaVar == null) {
            return null;
        }
        return aaVar.f3858a;
    }

    /* access modifiers changed from: package-private */
    public final boolean b(String str) {
        return System.currentTimeMillis() > this.f3860d + f3857b || !str.equals(this.f3859c);
    }
}
