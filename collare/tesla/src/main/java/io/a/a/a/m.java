package io.a.a.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import io.a.a.a.a.b.g;
import io.a.a.a.a.b.i;
import io.a.a.a.a.b.l;
import io.a.a.a.a.e.b;
import io.a.a.a.a.e.e;
import io.a.a.a.a.g.d;
import io.a.a.a.a.g.h;
import io.a.a.a.a.g.n;
import io.a.a.a.a.g.q;
import io.a.a.a.a.g.t;
import io.a.a.a.a.g.y;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/* access modifiers changed from: package-private */
/* compiled from: Onboarding */
public class m extends i<Boolean> {

    /* renamed from: a  reason: collision with root package name */
    private final e f6099a = new b();

    /* renamed from: b  reason: collision with root package name */
    private PackageManager f6100b;

    /* renamed from: c  reason: collision with root package name */
    private String f6101c;

    /* renamed from: d  reason: collision with root package name */
    private PackageInfo f6102d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private final Future<Map<String, k>> j;
    private final Collection<i> k;

    @Override // io.a.a.a.i
    public String getIdentifier() {
        return "io.fabric.sdk.android:fabric";
    }

    @Override // io.a.a.a.i
    public String getVersion() {
        return "1.4.4.27";
    }

    public m(Future<Map<String, k>> future, Collection<i> collection) {
        this.j = future;
        this.k = collection;
    }

    /* access modifiers changed from: protected */
    @Override // io.a.a.a.i
    public boolean onPreExecute() {
        try {
            this.g = getIdManager().i();
            this.f6100b = getContext().getPackageManager();
            this.f6101c = getContext().getPackageName();
            this.f6102d = this.f6100b.getPackageInfo(this.f6101c, 0);
            this.e = Integer.toString(this.f6102d.versionCode);
            this.f = this.f6102d.versionName == null ? "0.0" : this.f6102d.versionName;
            this.h = this.f6100b.getApplicationLabel(getContext().getApplicationInfo()).toString();
            this.i = Integer.toString(getContext().getApplicationInfo().targetSdkVersion);
            return true;
        } catch (PackageManager.NameNotFoundException e2) {
            c.g().e("Fabric", "Failed init", e2);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Boolean doInBackground() {
        boolean z;
        Map<String, k> map;
        String k2 = i.k(getContext());
        t c2 = c();
        if (c2 != null) {
            try {
                if (this.j != null) {
                    map = this.j.get();
                } else {
                    map = new HashMap<>();
                }
                z = a(k2, c2.f6069a, a(map, this.k).values());
            } catch (Exception e2) {
                c.g().e("Fabric", "Error performing auto configuration.", e2);
            }
            return Boolean.valueOf(z);
        }
        z = false;
        return Boolean.valueOf(z);
    }

    private t c() {
        try {
            q.a().a(this, this.idManager, this.f6099a, this.e, this.f, b()).c();
            return q.a().b();
        } catch (Exception e2) {
            c.g().e("Fabric", "Error dealing with settings", e2);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public Map<String, k> a(Map<String, k> map, Collection<i> collection) {
        for (i iVar : collection) {
            if (!map.containsKey(iVar.getIdentifier())) {
                map.put(iVar.getIdentifier(), new k(iVar.getIdentifier(), iVar.getVersion(), "binary"));
            }
        }
        return map;
    }

    private boolean a(String str, io.a.a.a.a.g.e eVar, Collection<k> collection) {
        if ("new".equals(eVar.f6034b)) {
            if (b(str, eVar, collection)) {
                return q.a().d();
            }
            c.g().e("Fabric", "Failed to create app with Crashlytics service.", null);
            return false;
        } else if ("configured".equals(eVar.f6034b)) {
            return q.a().d();
        } else {
            if (eVar.f) {
                c.g().a("Fabric", "Server says an update is required - forcing a full App update.");
                c(str, eVar, collection);
            }
            return true;
        }
    }

    private boolean b(String str, io.a.a.a.a.g.e eVar, Collection<k> collection) {
        return new h(this, b(), eVar.f6035c, this.f6099a).a(a(n.a(getContext(), str), collection));
    }

    private boolean c(String str, io.a.a.a.a.g.e eVar, Collection<k> collection) {
        return a(eVar, n.a(getContext(), str), collection);
    }

    private boolean a(io.a.a.a.a.g.e eVar, n nVar, Collection<k> collection) {
        return new y(this, b(), eVar.f6035c, this.f6099a).a(a(nVar, collection));
    }

    private d a(n nVar, Collection<k> collection) {
        Context context = getContext();
        return new d(new g().a(context), getIdManager().c(), this.f, this.e, i.a(i.m(context)), this.h, l.a(this.g).a(), this.i, "0", nVar, collection);
    }

    /* access modifiers changed from: package-private */
    public String b() {
        return i.b(getContext(), "com.crashlytics.ApiEndpoint");
    }
}
