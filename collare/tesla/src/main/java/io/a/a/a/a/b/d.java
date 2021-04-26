package io.a.a.a.a.b;

import android.content.Context;
import io.a.a.a.c;

/* compiled from: AdvertisingInfoReflectionStrategy */
class d implements f {

    /* renamed from: a  reason: collision with root package name */
    private final Context f5884a;

    public d(Context context) {
        this.f5884a = context.getApplicationContext();
    }

    /* access modifiers changed from: package-private */
    public boolean a(Context context) {
        try {
            if (((Integer) Class.forName("com.google.android.gms.common.GooglePlayServicesUtil").getMethod("isGooglePlayServicesAvailable", Context.class).invoke(null, context)).intValue() == 0) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // io.a.a.a.a.b.f
    public b a() {
        if (a(this.f5884a)) {
            return new b(b(), c());
        }
        return null;
    }

    private String b() {
        try {
            return (String) Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info").getMethod("getId", new Class[0]).invoke(d(), new Object[0]);
        } catch (Exception unused) {
            c.g().d("Fabric", "Could not call getId on com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            return null;
        }
    }

    private boolean c() {
        try {
            return ((Boolean) Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info").getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(d(), new Object[0])).booleanValue();
        } catch (Exception unused) {
            c.g().d("Fabric", "Could not call isLimitAdTrackingEnabled on com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            return false;
        }
    }

    private Object d() {
        try {
            return Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", Context.class).invoke(null, this.f5884a);
        } catch (Exception unused) {
            c.g().d("Fabric", "Could not call getAdvertisingIdInfo on com.google.android.gms.ads.identifier.AdvertisingIdClient");
            return null;
        }
    }
}
