package com.teslamotors.plugins.notifications;

import android.util.Log;
import com.facebook.react.modules.appstate.AppStateModule;
import com.google.android.gms.analytics.ecommerce.Promotion;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Notification */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private String f5613a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f5614b;

    /* renamed from: c  reason: collision with root package name */
    private String f5615c;

    /* renamed from: d  reason: collision with root package name */
    private int f5616d;
    private String e;
    private String f = null;
    private String g;
    private String h;
    private String i = null;
    private String j;
    private String k = null;
    private String l = "unknown";
    private long m;

    public a(Map<String, String> map, boolean z) {
        this.f5613a = map.get("alert_id");
        this.f5614b = Boolean.valueOf(map.get("delivery_confirmation")).booleanValue();
        this.h = map.get("product_type");
        this.i = map.get("product_subtype");
        this.j = map.get("product_id");
        this.k = map.get(Promotion.ACTION_VIEW);
        this.f5615c = map.get("override_string");
        this.f5616d = a(map.get("priority"));
        this.e = map.get("alert_args");
        b(this.e);
        this.m = c(map.get("alert_time"));
        this.g = map.get("txid");
        this.l = z ? AppStateModule.APP_STATE_ACTIVE : AppStateModule.APP_STATE_BACKGROUND;
    }

    public a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f5613a = jSONObject.getString("alert_id");
            this.f5615c = jSONObject.getString("override_string");
            this.f5614b = jSONObject.getBoolean("delivery_confirmation");
            this.h = jSONObject.getString("product_type");
            this.i = jSONObject.optString("product_subtype", null);
            this.j = jSONObject.getString("product_id");
            this.k = jSONObject.optString(Promotion.ACTION_VIEW, null);
            this.f5616d = jSONObject.optInt("priority", 0);
            this.e = jSONObject.getString("alert_args");
            b(this.e);
            this.m = jSONObject.getLong("alert_time");
            this.g = jSONObject.optString("txid", null);
            this.l = jSONObject.optString("app_state", "unknown");
        } catch (JSONException e2) {
            Log.e("Notification", "Failed to parse local Notification Json", e2);
        }
    }

    private int a(String str) {
        return (str == null || !str.equals("high")) ? 0 : 1;
    }

    private void b(String str) {
        if (str != null) {
            try {
                this.f = new JSONArray(str).getString(0);
            } catch (JSONException e2) {
                Log.e("Notification", "Failed to parse notification args", e2);
            }
        }
    }

    private long c(String str) {
        return str != null ? Long.parseLong(str) : System.currentTimeMillis() / 1000;
    }

    public int a() {
        return this.f5616d;
    }

    public String b() {
        return this.f;
    }

    public int c() {
        String str = this.g;
        if (str == null) {
            str = j();
        }
        if (str == null) {
            str = this.f5613a + this.m;
        }
        return str.hashCode();
    }

    public boolean d() {
        String str = this.f5613a;
        return str != null && str.startsWith("REFERRAL_");
    }

    public boolean e() {
        String str = this.f5615c;
        return str != null && !str.isEmpty();
    }

    public boolean f() {
        return this.f5614b;
    }

    public String g() {
        return this.f5615c;
    }

    public long h() {
        return this.m;
    }

    public JSONObject i() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("alert_id", this.f5613a);
            jSONObject.put("override_string", this.f5615c);
            jSONObject.put("delivery_confirmation", this.f5614b);
            jSONObject.put("product_type", this.h);
            jSONObject.put("product_id", this.j);
            jSONObject.put("priority", this.f5616d);
            jSONObject.put("alert_args", this.e);
            jSONObject.put("txid", this.g);
            jSONObject.put("alert_time", this.m);
            jSONObject.put("app_state", this.l);
            if (this.k != null) {
                jSONObject.put(Promotion.ACTION_VIEW, this.k);
            }
            if (this.i != null) {
                jSONObject.put("product_subtype", this.i);
            }
            return jSONObject;
        } catch (JSONException e2) {
            Log.e("Notification", "Failed to create JSON from notification", e2);
            return null;
        }
    }

    public String j() {
        JSONObject i2 = i();
        if (i2 == null) {
            return null;
        }
        return i2.toString();
    }
}
