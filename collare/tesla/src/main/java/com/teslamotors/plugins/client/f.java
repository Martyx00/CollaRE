package com.teslamotors.plugins.client;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.measurement.AppMeasurement;
import com.idehub.GoogleAnalyticsBridge.a;
import com.teslamotors.plugins.client.a.c;
import com.teslamotors.plugins.client.a.e;
import com.teslamotors.plugins.client.d.b;
import com.teslamotors.plugins.client.d.d;
import com.teslamotors.plugins.client.d.g;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.spongycastle.crypto.tls.CipherSuite;

/* compiled from: TeslaClient */
public class f {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f5564a = (!f.class.desiredAssertionStatus());

    /* renamed from: b  reason: collision with root package name */
    private static f f5565b;

    /* renamed from: c  reason: collision with root package name */
    private static final String f5566c = f.class.getSimpleName();
    private static String t;

    /* renamed from: d  reason: collision with root package name */
    private boolean f5567d = false;
    private c e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private Map<String, Object> o;
    private final Context p;
    private HashMap<String, c> q;
    private String r;
    private String s;

    public static synchronized f a(Context context) {
        f fVar;
        synchronized (f.class) {
            if (f5565b == null) {
                f5565b = new f(context);
                f5565b.q = new HashMap<>();
            }
            fVar = f5565b;
        }
        return fVar;
    }

    private f(Context context) {
        String str;
        this.p = context;
        b(context);
        try {
            InputStream open = context.getAssets().open("shared/env.json");
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            JSONObject jSONObject = new JSONObject(new String(bArr, "UTF-8"));
            this.g = jSONObject.getString("OWNERAPI_BASE_URL");
            this.h = jSONObject.getString("OWNERAPI_CLIENT_ID");
            this.i = jSONObject.getString("OWNERAPI_CLIENT_SECRET");
            this.j = jSONObject.getString("STREAMING_SERVER_BASE_URL");
            if ("CN".equals(m("REGION"))) {
                str = jSONObject.getString("DEVICE_TYPE_ANDROID_JPUSH");
            } else {
                str = jSONObject.getString("DEVICE_TYPE_ANDROID_FCM");
            }
            this.k = str;
            this.l = jSONObject.getString("SSO_SERVER_CLIENT_ID");
            this.m = jSONObject.getString("GOOGLE_CLOUD_MESSAGING_SENDER_ID");
            this.n = jSONObject.getString("COLORIZED_IMAGE_DIR_HASH");
            String optString = jSONObject.optString("GOOGLE_ANALYTICS_ID");
            if (optString == null || optString.length() <= 0) {
                optString = null;
            }
            this.f = optString;
            if (this.f != null) {
                this.f5567d = true;
                a(false);
            }
            InputStream open2 = context.getAssets().open("shared/ownerapi_endpoints.json");
            byte[] bArr2 = new byte[open2.available()];
            open2.read(bArr2);
            open2.close();
            this.o = b.a(new JSONObject(new String(bArr2, "UTF-8")));
        } catch (Exception e2) {
            Log.e(f5566c, "Failed to initialize environment variables", e2);
        }
    }

    public void a(String str, String str2) {
        j(str2);
    }

    public void b(String str, String str2) {
        if (this.e != null) {
            j(str2);
        }
    }

    public void a(String str, String str2, Exception exc) {
        if (exc == null) {
            exc = new Exception(str2);
        }
        com.teslamotors.plugins.crashlytics.b.a(exc);
        j(str2);
    }

    public void a(boolean z) {
        if ((this.f5567d || z) && this.f != null) {
            a.a(this.p).a(this.f, z);
        }
    }

    public void a(int i2, String str) {
        if (this.f5567d && this.f != null) {
            a.a(this.p).a(this.f, i2, str);
        }
    }

    public void b(int i2, String str) {
        if (this.f5567d && this.f != null) {
            a.a(this.p).a(this.f, i2, str);
        }
    }

    public File a() {
        return new File(this.p.getCacheDir(), "colorized_images");
    }

    public void a(c cVar) {
        this.e = cVar;
    }

    public String b() {
        return Locale.getDefault().getCountry();
    }

    public String c() {
        return this.f;
    }

    public String d() {
        return this.g;
    }

    public String e() {
        return this.h;
    }

    public String f() {
        return this.i;
    }

    public String g() {
        return this.j;
    }

    public String h() {
        return this.l;
    }

    public String i() {
        return this.n;
    }

    public String j() {
        return com.teslamotors.plugins.client.b.a.b(this.p);
    }

    public String k() {
        return com.teslamotors.plugins.client.b.a.a(this.p);
    }

    public JSONObject a(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new JSONObject(str);
        } catch (JSONException e2) {
            a(f5566c, "Failed to get productString from json file", e2);
            return null;
        }
    }

    public String a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            return jSONObject.getString("id");
        } catch (JSONException e2) {
            a(f5566c, "Failed to getproduct with id string from json file", e2);
            return null;
        }
    }

    public String b(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            return jSONObject.getString(AppMeasurement.Param.TYPE);
        } catch (JSONException e2) {
            a(f5566c, "Failed to get type string from product json object", e2);
            return null;
        }
    }

    public String c(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            return jSONObject.getString("vin");
        } catch (JSONException e2) {
            a(f5566c, "Failed to get vin string from product json object", e2);
            return null;
        }
    }

    public JSONObject a(JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            try {
                jSONObject.put("id", str);
            } catch (JSONException e2) {
                a(f5566c, "Failed to insert value into request object", e2);
            }
        }
        return jSONObject;
    }

    public JSONObject b(JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            try {
                jSONObject.put(AppMeasurement.Param.TYPE, str);
            } catch (JSONException e2) {
                a(f5566c, "Failed to insert value into request object", e2);
            }
        }
        return jSONObject;
    }

    public JSONObject c(JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            try {
                jSONObject.put("vin", str);
            } catch (JSONException e2) {
                a(f5566c, "Failed to insert value into request object", e2);
            }
        }
        return jSONObject;
    }

    public JSONObject l() {
        return a(com.teslamotors.plugins.client.b.a.e(this.p));
    }

    public String m() {
        return a(l());
    }

    public String n() {
        return b(l());
    }

    public com.teslamotors.plugins.client.c.a o() {
        String m2 = com.teslamotors.plugins.client.b.a.m(this.p);
        if (m2 == null) {
            return null;
        }
        try {
            return new com.teslamotors.plugins.client.c.a(m2);
        } catch (Exception unused) {
            return null;
        }
    }

    public boolean p() {
        return com.teslamotors.plugins.client.b.a.f(this.p);
    }

    public String q() {
        return this.k;
    }

    public String r() {
        return com.teslamotors.plugins.client.b.a.d(this.p);
    }

    public void b(String str) {
        com.teslamotors.plugins.client.b.a.a(this.p, str);
    }

    public String s() {
        return com.teslamotors.plugins.client.b.a.h(this.p);
    }

    public void c(String str) {
        com.teslamotors.plugins.client.b.a.b(this.p, str);
    }

    public static void a(String str, Context context) {
        if (t == null && str != null) {
            t = str;
            if (!str.equals(com.teslamotors.plugins.client.b.a.i(context))) {
                com.teslamotors.plugins.client.b.a.c(context, str);
            }
        }
    }

    public boolean t() {
        String g2 = com.teslamotors.plugins.client.b.a.g(this.p);
        return g2 == null || !g2.equals(c(this.p));
    }

    public void u() {
        File a2 = a();
        if (a2.isDirectory()) {
            try {
                a(a2);
            } catch (Exception e2) {
                Log.e(f5566c, "Unable to clear colorized image directory.", e2);
            }
        }
    }

    public void v() {
        com.teslamotors.plugins.client.b.a.j(this.p);
        com.teslamotors.plugins.client.b.a.k(this.p);
    }

    public void d(JSONObject jSONObject) {
        try {
            final String string = jSONObject.getString("alert_id");
            jSONObject.put("time_received", System.currentTimeMillis() / 1000);
            jSONObject.put("device_type", q());
            jSONObject.put("device_token", r());
            a(jSONObject, null, "SEND_NOTIFICATION_CONFIRMATION", new com.teslamotors.plugins.client.a.b() {
                /* class com.teslamotors.plugins.client.f.AnonymousClass1 */

                @Override // com.teslamotors.plugins.client.a.b
                public void a(JSONObject jSONObject) {
                    String str = f.f5566c;
                    b.a(str, "Notification confirmation sent successfully for type: " + string);
                }

                @Override // com.teslamotors.plugins.client.a.b
                public void a(d dVar) {
                    String str = f.f5566c;
                    b.c(str, "Error sending notification confirmation: " + dVar);
                }
            });
        } catch (JSONException unused) {
            Log.e(f5566c, "Fail to send notification confirmation.");
        }
    }

    public boolean w() {
        int i2;
        try {
            i2 = Settings.Secure.getInt(this.p.getContentResolver(), "location_mode");
        } catch (Settings.SettingNotFoundException e2) {
            Log.e(f5566c, "Unable to determine Location Services status", e2);
            i2 = 0;
        }
        if (i2 != 0) {
            return true;
        }
        return false;
    }

    public boolean d(String str) {
        if (Build.VERSION.SDK_INT < 23 || this.p.checkSelfPermission(str) == 0) {
            return true;
        }
        return false;
    }

    public void a(Activity activity, String str, c cVar) {
        if (Build.VERSION.SDK_INT > 22) {
            this.q.put(str, cVar);
            android.support.v4.app.a.a(activity, new String[]{str}, (int) CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384);
            return;
        }
        cVar.a(-1, str);
    }

    public void a(Activity activity) {
        if (Build.VERSION.SDK_INT > 22) {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + this.p.getPackageName()));
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setFlags(268468224);
            try {
                activity.startActivityForResult(intent, CipherSuite.TLS_PSK_WITH_AES_128_GCM_SHA256);
            } catch (ActivityNotFoundException e2) {
                Log.e(f5566c, "Unable to open ACTION_APPLICATION_DETAILS_SETTINGS Intent", e2);
            }
        }
    }

    public void b(Activity activity) {
        Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setFlags(268468224);
        try {
            activity.startActivityForResult(intent, CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384);
        } catch (ActivityNotFoundException e2) {
            Log.e(f5566c, "Unable to open ACTION_LOCATION_SOURCE_SETTINGS Intent", e2);
        }
    }

    public e a(JSONObject jSONObject, String str, String str2, final com.teslamotors.plugins.client.a.b bVar) {
        String str3 = this.g + ((Map) this.o.get(str2)).get("URI");
        if (f5564a || str != null || !str3.contains("{vehicle_id}")) {
            String replace = str != null ? str3.replace("{vehicle_id}", str) : str3;
            String obj = ((Map) this.o.get(str2)).get("TYPE").toString();
            HashMap<String, String> H = Boolean.valueOf(((Map) this.o.get(str2)).get("AUTH").toString()).booleanValue() ? H() : new HashMap<>();
            H.put(io.a.a.a.a.b.a.HEADER_USER_AGENT, this.r);
            H.put("X-Tesla-User-Agent", this.s);
            String str4 = f5566c;
            Object[] objArr = new Object[7];
            objArr[0] = str2;
            objArr[1] = bVar == null ? "null callback" : "defined callback";
            objArr[2] = str;
            objArr[3] = obj;
            objArr[4] = replace;
            objArr[5] = H;
            objArr[6] = jSONObject;
            b.a(str4, String.format("Sending OAPI request to %s wth %s: vid %s, %s %s, %s %s", objArr));
            if (bVar == null) {
                return e.a(g.b(this.p).a(obj, replace, H, jSONObject, null));
            }
            g.b(this.p).a(obj, replace, H, jSONObject, new e() {
                /* class com.teslamotors.plugins.client.f.AnonymousClass2 */

                @Override // com.teslamotors.plugins.client.a.e
                public void a(Exception exc) {
                    Log.e(f.f5566c, "Failed OwnerAPI request:", exc);
                    bVar.a(d.OWNERAPI_ERROR_UNKNOWN_ERROR);
                }

                @Override // com.teslamotors.plugins.client.a.e
                public void a(Response response) {
                    e a2 = e.a(response);
                    if (a2.a() != null) {
                        bVar.a(a2.a());
                    } else {
                        bVar.a(a2.b());
                    }
                }
            });
            return null;
        }
        throw new AssertionError("Tried to send a vehicle OwnerAPI request with no vehicle ID");
    }

    public void a(String str, Integer num, Map<String, Object> map) {
        com.teslamotors.plugins.crashlytics.b.a(str, num, map);
    }

    public String x() {
        return com.teslamotors.plugins.client.b.a.c(this.p);
    }

    public String y() {
        String l2 = com.teslamotors.plugins.client.b.a.l(this.p);
        return l2 == null ? "" : l2;
    }

    public String e(String str) {
        return com.teslamotors.plugins.client.b.a.c(k(str), this.p);
    }

    public List<Pair<String, String>> z() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("VEHICLE_BLE_PUBLIC_KEY_");
        return d.a(this.p.getApplicationContext()).a(arrayList, arrayList2);
    }

    public List<Pair<String, String>> A() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("STORED_VEHICLE_");
        return d.a(this.p.getApplicationContext()).a(arrayList, arrayList2);
    }

    public boolean f(String str) {
        String c2;
        String a2;
        List<Pair<String, String>> A = A();
        if (A != null) {
            String str2 = f5566c;
            b(str2, "Looking for the product Id with vin: " + str);
            for (int i2 = 0; i2 < A.size(); i2++) {
                JSONObject a3 = a((String) A.get(i2).second);
                if (a3 != null && (c2 = c(a3)) != null && c2.equalsIgnoreCase(str) && (a2 = a(a3)) != null) {
                    JSONObject jSONObject = new JSONObject();
                    a(jSONObject, a2);
                    b(jSONObject, "VEHICLE");
                    c(jSONObject, c2);
                    com.teslamotors.plugins.client.b.a.d(jSONObject.toString(), this.p);
                    String str3 = f5566c;
                    b(str3, "Saving Selected Product with : " + jSONObject.toString());
                    return true;
                }
            }
        }
        return false;
    }

    public void c(String str, String str2) {
        com.teslamotors.plugins.client.b.a.a(k(str), str2, this.p);
    }

    public void a(String str, String str2, Set<String> set) {
        com.teslamotors.plugins.client.b.a.a(i(str, str2), set, this.p);
    }

    public Set<String> d(String str, String str2) {
        return com.teslamotors.plugins.client.b.a.e(i(str, str2), this.p);
    }

    public void g(String str) {
        com.teslamotors.plugins.client.b.a.b(str, this.p);
    }

    public String B() {
        return com.teslamotors.plugins.client.b.a.n(this.p);
    }

    public long a(long j2, String str, String str2) {
        return com.teslamotors.plugins.client.b.a.a(j2, h(str, str2), this.p);
    }

    public long e(String str, String str2) {
        return com.teslamotors.plugins.client.b.a.f(h(str, str2), this.p);
    }

    public long f(String str, String str2) {
        return com.teslamotors.plugins.client.b.a.g(j(str, str2), this.p);
    }

    public long a(String str, String str2, long j2) {
        return com.teslamotors.plugins.client.b.a.b(j2, j(str, str2), this.p);
    }

    public void g(String str, String str2) {
        com.teslamotors.plugins.client.b.a.b(l(str), str2, this.p);
    }

    public String h(String str) {
        return com.teslamotors.plugins.client.b.a.c(l(str), this.p);
    }

    private HashMap<String, String> H() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Authorization", String.format("Bearer %s", k()));
        return hashMap;
    }

    public void a(int i2, String[] strArr, int[] iArr) {
        for (String str : strArr) {
            if (this.q.containsKey(str)) {
                c cVar = this.q.get(str);
                if (iArr[Arrays.asList(strArr).indexOf(str)] == 0) {
                    cVar.a(i2, str);
                } else {
                    cVar.b(i2, str);
                }
            }
        }
    }

    private void b(Context context) {
        if (t == null) {
            t = com.teslamotors.plugins.client.b.a.i(context);
        }
        String c2 = c(context);
        this.s = String.format("TeslaApp/%s/%s/android/%s", c2, t, Build.VERSION.RELEASE);
        this.r = String.format("TeslaApp/%s %s", i(c2), System.getProperty("http.agent", String.format("%s %s)", Build.MODEL, Build.VERSION.CODENAME)));
    }

    private String c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName.replace(' ', '.');
        } catch (Exception unused) {
            return "Unknown";
        }
    }

    private String i(String str) {
        String[] split = str.split("-");
        return split.length > 0 ? split[split.length - 1] : "Unknown";
    }

    private void j(String str) {
        c cVar = this.e;
        if (cVar != null) {
            cVar.emitDeviceEvent("log", str);
        }
    }

    private boolean a(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                a(file2);
            }
        }
        return file.delete();
    }

    private String h(String str, String str2) {
        return String.format("VEHICLE_%s_%s_INITIALIZATION_VECTOR", str2, str);
    }

    private String i(String str, String str2) {
        return String.format("VEHICLE_BLE_%s_%s_PERIPHERALS", str2, str);
    }

    private String k(String str) {
        return String.format("VEHICLE_BLE_%s_NAME", str);
    }

    private String j(String str, String str2) {
        return String.format("VEHICLE_%s_%s_WHITELIST_COUNT", str2, str);
    }

    private String l(String str) {
        return String.format("VEHICLE_BLE_PUBLIC_KEY_%s", str);
    }

    public Class C() {
        try {
            return Class.forName(this.p.getPackageManager().getLaunchIntentForPackage(this.p.getPackageName()).getComponent().getClassName());
        } catch (ClassNotFoundException e2) {
            Log.e(f5566c, "Could not determine main activity class", e2);
            return null;
        }
    }

    public Intent D() {
        Class C = C();
        if (C == null) {
            return null;
        }
        return new Intent(this.p, C);
    }

    private String m(String str) {
        try {
            return (String) Class.forName("com.teslamotors.TeslaApp.BuildConfig").getField(str).get(null);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e2) {
            Log.e(f5566c, "Could not extract build config value", e2);
            return null;
        }
    }

    public String E() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= 24) {
            locale = this.p.getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = this.p.getResources().getConfiguration().locale;
        }
        return locale.getLanguage() + "-" + locale.getCountry();
    }

    public void F() {
        d a2 = d.a(this.p.getApplicationContext());
        if (a2.a("SCHEMA_ID") == null) {
            Bundle bundle = new Bundle();
            String e2 = com.teslamotors.plugins.client.b.b.e(this.p);
            if (e2 != null) {
                bundle.putString("_ACCOUNT_EMAIL", e2);
            }
            String i2 = com.teslamotors.plugins.client.b.b.i(this.p);
            if (i2 != null) {
                bundle.putString("_KEY_STORE_PASS", i2);
            }
            String g2 = com.teslamotors.plugins.client.b.b.g(this.p);
            if (g2 != null) {
                bundle.putString("SELECTED_VIN", g2);
            }
            String k2 = k(g2);
            String d2 = com.teslamotors.plugins.client.b.b.d(k2, this.p);
            if (d2 != null) {
                bundle.putString(k2, d2);
            }
            String h2 = com.teslamotors.plugins.client.b.b.h(this.p);
            if (h2 != null) {
                bundle.putString("SHARED_SELECTED_PRODUCT_DATA", h2);
            }
            String a3 = com.teslamotors.plugins.client.b.b.a(this.p);
            if (a3 != null) {
                bundle.putString("_OWNERAPI_AUTH_TOKEN", a3);
            }
            String b2 = com.teslamotors.plugins.client.b.b.b(this.p);
            if (b2 != null) {
                bundle.putString("SELECTED_PRODUCT", b2);
            }
            String f2 = com.teslamotors.plugins.client.b.b.f(this.p);
            if (f2 != null) {
                bundle.putString("COLORIZED_IMAGE_DIRECTORY_HASH", f2);
            }
            String d3 = com.teslamotors.plugins.client.b.b.d(this.p);
            if (d3 != null) {
                bundle.putString("APP_UUID", d3);
            }
            String c2 = com.teslamotors.plugins.client.b.b.c(this.p);
            if (c2 != null) {
                bundle.putString("APP_PREVIOUSLY_LAUNCHED", c2);
            }
            a2.a(bundle);
        }
    }
}
