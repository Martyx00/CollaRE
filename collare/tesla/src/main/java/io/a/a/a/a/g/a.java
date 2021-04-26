package io.a.a.a.a.g;

import android.content.res.Resources;
import io.a.a.a.a.b.u;
import io.a.a.a.a.e.c;
import io.a.a.a.a.e.d;
import io.a.a.a.a.e.e;
import io.a.a.a.i;
import io.a.a.a.k;
import io.a.a.a.l;
import java.io.Closeable;
import java.io.InputStream;
import java.util.Locale;

/* compiled from: AbstractAppSpiCall */
abstract class a extends io.a.a.a.a.b.a {
    public a(i iVar, String str, String str2, e eVar, c cVar) {
        super(iVar, str, str2, eVar, cVar);
    }

    public boolean a(d dVar) {
        d b2 = b(a(getHttpRequest(), dVar), dVar);
        l g = io.a.a.a.c.g();
        g.a("Fabric", "Sending app info to " + getUrl());
        if (dVar.j != null) {
            l g2 = io.a.a.a.c.g();
            g2.a("Fabric", "App icon hash is " + dVar.j.f6048a);
            l g3 = io.a.a.a.c.g();
            g3.a("Fabric", "App icon size is " + dVar.j.f6050c + "x" + dVar.j.f6051d);
        }
        int b3 = b2.b();
        String str = "POST".equals(b2.o()) ? "Create" : "Update";
        l g4 = io.a.a.a.c.g();
        g4.a("Fabric", str + " app request ID: " + b2.b(io.a.a.a.a.b.a.HEADER_REQUEST_ID));
        l g5 = io.a.a.a.c.g();
        g5.a("Fabric", "Result was " + b3);
        return u.a(b3) == 0;
    }

    private d a(d dVar, d dVar2) {
        return dVar.a(io.a.a.a.a.b.a.HEADER_API_KEY, dVar2.f6029a).a(io.a.a.a.a.b.a.HEADER_CLIENT_TYPE, io.a.a.a.a.b.a.ANDROID_CLIENT_TYPE).a(io.a.a.a.a.b.a.HEADER_CLIENT_VERSION, this.kit.getVersion());
    }

    private d b(d dVar, d dVar2) {
        d e = dVar.e("app[identifier]", dVar2.f6030b).e("app[name]", dVar2.f).e("app[display_version]", dVar2.f6031c).e("app[build_version]", dVar2.f6032d).a("app[source]", Integer.valueOf(dVar2.g)).e("app[minimum_sdk_version]", dVar2.h).e("app[built_sdk_version]", dVar2.i);
        if (!io.a.a.a.a.b.i.d(dVar2.e)) {
            e.e("app[instance_identifier]", dVar2.e);
        }
        if (dVar2.j != null) {
            InputStream inputStream = null;
            try {
                inputStream = this.kit.getContext().getResources().openRawResource(dVar2.j.f6049b);
                e.e("app[icon][hash]", dVar2.j.f6048a).a("app[icon][data]", "icon.png", "application/octet-stream", inputStream).a("app[icon][width]", Integer.valueOf(dVar2.j.f6050c)).a("app[icon][height]", Integer.valueOf(dVar2.j.f6051d));
            } catch (Resources.NotFoundException e2) {
                l g = io.a.a.a.c.g();
                g.e("Fabric", "Failed to find app icon with resource ID: " + dVar2.j.f6049b, e2);
            } catch (Throwable th) {
                io.a.a.a.a.b.i.a((Closeable) inputStream, "Failed to close app icon InputStream.");
                throw th;
            }
            io.a.a.a.a.b.i.a((Closeable) inputStream, "Failed to close app icon InputStream.");
        }
        if (dVar2.k != null) {
            for (k kVar : dVar2.k) {
                e.e(a(kVar), kVar.b());
                e.e(b(kVar), kVar.c());
            }
        }
        return e;
    }

    /* access modifiers changed from: package-private */
    public String a(k kVar) {
        return String.format(Locale.US, "app[build][libraries][%s][version]", kVar.a());
    }

    /* access modifiers changed from: package-private */
    public String b(k kVar) {
        return String.format(Locale.US, "app[build][libraries][%s][type]", kVar.a());
    }
}
