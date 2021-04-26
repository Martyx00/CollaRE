package io.a.a.a.a.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import io.a.a.a.a.f.d;

/* access modifiers changed from: package-private */
/* compiled from: AdvertisingInfoProvider */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private final Context f5880a;

    /* renamed from: b  reason: collision with root package name */
    private final io.a.a.a.a.f.c f5881b;

    public c(Context context) {
        this.f5880a = context.getApplicationContext();
        this.f5881b = new d(context, "TwitterAdvertisingInfoPreferences");
    }

    public b a() {
        b b2 = b();
        if (c(b2)) {
            io.a.a.a.c.g().a("Fabric", "Using AdvertisingInfo from Preference Store");
            a(b2);
            return b2;
        }
        b e = e();
        b(e);
        return e;
    }

    private void a(final b bVar) {
        new Thread(new h() {
            /* class io.a.a.a.a.b.c.AnonymousClass1 */

            @Override // io.a.a.a.a.b.h
            public void onRun() {
                b e = c.this.e();
                if (!bVar.equals(e)) {
                    io.a.a.a.c.g().a("Fabric", "Asychronously getting Advertising Info and storing it to preferences");
                    c.this.b(e);
                }
            }
        }).start();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @SuppressLint({"CommitPrefEdits"})
    private void b(b bVar) {
        if (c(bVar)) {
            io.a.a.a.a.f.c cVar = this.f5881b;
            cVar.a(cVar.b().putString("advertising_id", bVar.f5878a).putBoolean("limit_ad_tracking_enabled", bVar.f5879b));
            return;
        }
        io.a.a.a.a.f.c cVar2 = this.f5881b;
        cVar2.a(cVar2.b().remove("advertising_id").remove("limit_ad_tracking_enabled"));
    }

    /* access modifiers changed from: protected */
    public b b() {
        return new b(this.f5881b.a().getString("advertising_id", ""), this.f5881b.a().getBoolean("limit_ad_tracking_enabled", false));
    }

    public f c() {
        return new d(this.f5880a);
    }

    public f d() {
        return new e(this.f5880a);
    }

    private boolean c(b bVar) {
        return bVar != null && !TextUtils.isEmpty(bVar.f5878a);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private b e() {
        b a2 = c().a();
        if (!c(a2)) {
            a2 = d().a();
            if (!c(a2)) {
                io.a.a.a.c.g().a("Fabric", "AdvertisingInfo not present");
            } else {
                io.a.a.a.c.g().a("Fabric", "Using AdvertisingInfo from Service Provider");
            }
        } else {
            io.a.a.a.c.g().a("Fabric", "Using AdvertisingInfo from Reflection Provider");
        }
        return a2;
    }
}
