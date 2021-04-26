package io.a.a.a.a.g;

import com.google.firebase.analytics.FirebaseAnalytics;
import io.a.a.a.a.b.a;
import io.a.a.a.a.e.c;
import io.a.a.a.a.e.d;
import io.a.a.a.a.e.e;
import io.a.a.a.i;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
/* compiled from: DefaultSettingsSpiCall */
public class l extends a implements x {
    /* access modifiers changed from: package-private */
    public boolean a(int i) {
        return i == 200 || i == 201 || i == 202 || i == 203;
    }

    public l(i iVar, String str, String str2, e eVar) {
        this(iVar, str, str2, eVar, c.GET);
    }

    l(i iVar, String str, String str2, e eVar, c cVar) {
        super(iVar, str, str2, eVar, cVar);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0098  */
    @Override // io.a.a.a.a.g.x
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject a(io.a.a.a.a.g.w r8) {
        /*
        // Method dump skipped, instructions count: 185
        */
        throw new UnsupportedOperationException("Method not decompiled: io.a.a.a.a.g.l.a(io.a.a.a.a.g.w):org.json.JSONObject");
    }

    /* access modifiers changed from: package-private */
    public JSONObject a(d dVar) {
        int b2 = dVar.b();
        io.a.a.a.l g = io.a.a.a.c.g();
        g.a("Fabric", "Settings result was: " + b2);
        if (a(b2)) {
            return a(dVar.d());
        }
        io.a.a.a.l g2 = io.a.a.a.c.g();
        g2.e("Fabric", "Failed to retrieve settings from " + getUrl());
        return null;
    }

    private JSONObject a(String str) {
        try {
            return new JSONObject(str);
        } catch (Exception e) {
            io.a.a.a.l g = io.a.a.a.c.g();
            g.a("Fabric", "Failed to parse settings JSON from " + getUrl(), e);
            io.a.a.a.l g2 = io.a.a.a.c.g();
            g2.a("Fabric", "Settings response " + str);
            return null;
        }
    }

    private Map<String, String> b(w wVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("build_version", wVar.h);
        hashMap.put("display_version", wVar.g);
        hashMap.put(FirebaseAnalytics.b.SOURCE, Integer.toString(wVar.i));
        if (wVar.j != null) {
            hashMap.put("icon_hash", wVar.j);
        }
        String str = wVar.f;
        if (!io.a.a.a.a.b.i.d(str)) {
            hashMap.put("instance", str);
        }
        return hashMap;
    }

    private d a(d dVar, w wVar) {
        a(dVar, a.HEADER_API_KEY, wVar.f6074a);
        a(dVar, a.HEADER_CLIENT_TYPE, a.ANDROID_CLIENT_TYPE);
        a(dVar, a.HEADER_CLIENT_VERSION, this.kit.getVersion());
        a(dVar, a.HEADER_ACCEPT, a.ACCEPT_JSON_VALUE);
        a(dVar, "X-CRASHLYTICS-DEVICE-MODEL", wVar.f6075b);
        a(dVar, "X-CRASHLYTICS-OS-BUILD-VERSION", wVar.f6076c);
        a(dVar, "X-CRASHLYTICS-OS-DISPLAY-VERSION", wVar.f6077d);
        a(dVar, "X-CRASHLYTICS-INSTALLATION-ID", wVar.e);
        return dVar;
    }

    private void a(d dVar, String str, String str2) {
        if (str2 != null) {
            dVar.a(str, str2);
        }
    }
}
