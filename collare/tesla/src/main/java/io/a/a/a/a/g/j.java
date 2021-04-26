package io.a.a.a.a.g;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import io.a.a.a.a.b.k;
import io.a.a.a.a.b.q;
import io.a.a.a.a.f.c;
import io.a.a.a.a.f.d;
import io.a.a.a.i;
import io.a.a.a.l;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
/* compiled from: DefaultSettingsController */
public class j implements s {

    /* renamed from: a  reason: collision with root package name */
    private final w f6040a;

    /* renamed from: b  reason: collision with root package name */
    private final v f6041b;

    /* renamed from: c  reason: collision with root package name */
    private final k f6042c;

    /* renamed from: d  reason: collision with root package name */
    private final g f6043d;
    private final x e;
    private final i f;
    private final c g = new d(this.f);

    public j(i iVar, w wVar, k kVar, v vVar, g gVar, x xVar) {
        this.f = iVar;
        this.f6040a = wVar;
        this.f6042c = kVar;
        this.f6041b = vVar;
        this.f6043d = gVar;
        this.e = xVar;
    }

    @Override // io.a.a.a.a.g.s
    public t a() {
        return a(r.USE_CACHE);
    }

    @Override // io.a.a.a.a.g.s
    public t a(r rVar) {
        JSONObject a2;
        t tVar = null;
        if (!new q().c(this.f.getContext())) {
            io.a.a.a.c.g().a("Fabric", "Not fetching settings, because data collection is disabled by Firebase.");
            return null;
        }
        try {
            if (!io.a.a.a.c.h() && !d()) {
                tVar = b(rVar);
            }
            if (tVar == null && (a2 = this.e.a(this.f6040a)) != null) {
                tVar = this.f6041b.a(this.f6042c, a2);
                this.f6043d.a(tVar.g, a2);
                a(a2, "Loaded settings: ");
                a(b());
            }
            if (tVar == null) {
                return b(r.IGNORE_CACHE_EXPIRATION);
            }
            return tVar;
        } catch (Exception e2) {
            io.a.a.a.c.g().e("Fabric", "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", e2);
            return null;
        }
    }

    private t b(r rVar) {
        Exception e2;
        t tVar = null;
        try {
            if (r.SKIP_CACHE_LOOKUP.equals(rVar)) {
                return null;
            }
            JSONObject a2 = this.f6043d.a();
            if (a2 != null) {
                t a3 = this.f6041b.a(this.f6042c, a2);
                if (a3 != null) {
                    a(a2, "Loaded cached settings: ");
                    long a4 = this.f6042c.a();
                    if (!r.IGNORE_CACHE_EXPIRATION.equals(rVar)) {
                        if (a3.a(a4)) {
                            io.a.a.a.c.g().a("Fabric", "Cached settings have expired.");
                            return null;
                        }
                    }
                    try {
                        io.a.a.a.c.g().a("Fabric", "Returning cached settings.");
                        return a3;
                    } catch (Exception e3) {
                        e2 = e3;
                        tVar = a3;
                        io.a.a.a.c.g().e("Fabric", "Failed to get cached settings", e2);
                        return tVar;
                    }
                } else {
                    io.a.a.a.c.g().e("Fabric", "Failed to transform cached settings data.", null);
                    return null;
                }
            } else {
                io.a.a.a.c.g().a("Fabric", "No cached settings data found.");
                return null;
            }
        } catch (Exception e4) {
            e2 = e4;
            io.a.a.a.c.g().e("Fabric", "Failed to get cached settings", e2);
            return tVar;
        }
    }

    private void a(JSONObject jSONObject, String str) {
        l g2 = io.a.a.a.c.g();
        g2.a("Fabric", str + jSONObject.toString());
    }

    /* access modifiers changed from: package-private */
    public String b() {
        return io.a.a.a.a.b.i.a(io.a.a.a.a.b.i.m(this.f.getContext()));
    }

    /* access modifiers changed from: package-private */
    public String c() {
        return this.g.a().getString("existing_instance_identifier", "");
    }

    /* access modifiers changed from: package-private */
    @SuppressLint({"CommitPrefEdits"})
    public boolean a(String str) {
        SharedPreferences.Editor b2 = this.g.b();
        b2.putString("existing_instance_identifier", str);
        return this.g.a(b2);
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        return !c().equals(b());
    }
}
