package com.teslamotors.share;

import android.content.Context;
import com.google.android.gms.measurement.AppMeasurement;
import com.teslamotors.plugins.client.c.a;
import com.teslamotors.plugins.client.f;
import com.teslamotors.share.b;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: VehicleClient */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private Context f5669a;

    e(Context context) {
        this.f5669a = context;
    }

    public boolean a() {
        return i().k() != null;
    }

    public boolean b() {
        return i().t();
    }

    public boolean c() {
        JSONObject l = i().l();
        if (!(l != null && "VEHICLE".equals(l.optString(AppMeasurement.Param.TYPE))) || j() == null) {
            return false;
        }
        return true;
    }

    public boolean d() {
        return j() != null && j().f();
    }

    public String e() {
        if (j() != null) {
            return j().b();
        }
        return null;
    }

    public boolean f() {
        return j() != null && j().g();
    }

    public String g() {
        String e;
        if (j() == null) {
            return null;
        }
        String h = j().h();
        String c2 = j().c();
        if ((h != null && (c2 == null || !h.equals(c2.substring(c2.length() - 6)))) || (e = j().e()) == null) {
            return h;
        }
        if (e.equals("model3") || (c2 != null && c2.charAt(3) == '3')) {
            h = this.f5669a.getString(b.d.vehicle_default_name_model_3);
        }
        if (e.equals("modelx") || (c2 != null && c2.charAt(3) == 'X')) {
            h = this.f5669a.getString(b.d.vehicle_default_name_model_x);
        }
        if (e.equals("models") || (c2 != null && c2.charAt(3) == 'S')) {
            h = this.f5669a.getString(b.d.vehicle_default_name_model_s);
        }
        return (e.equals("modely") || (c2 != null && c2.charAt(3) == 'Y')) ? this.f5669a.getString(b.d.vehicle_default_name_model_y) : h;
    }

    public void a(boolean z) {
        if (j() != null) {
            try {
                j().a(z);
            } catch (JSONException unused) {
            }
        }
    }

    public void b(boolean z) {
        if (j() != null) {
            try {
                j().b(z);
            } catch (JSONException unused) {
            }
        }
    }

    public void h() {
        if (j() != null) {
            try {
                j().a();
            } catch (JSONException unused) {
            }
        }
    }

    public void a(JSONObject jSONObject, com.teslamotors.plugins.client.a.b bVar) {
        if (e() != null) {
            i().a(jSONObject, e(), "SHARE_TO_VEHICLE", bVar);
        }
    }

    private f i() {
        return f.a(this.f5669a);
    }

    private a j() {
        return i().o();
    }
}
