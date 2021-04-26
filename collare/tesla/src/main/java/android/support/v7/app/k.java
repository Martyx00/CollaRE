package android.support.v7.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.h;
import android.util.Log;
import java.util.Calendar;

/* access modifiers changed from: package-private */
/* compiled from: TwilightManager */
public class k {

    /* renamed from: a  reason: collision with root package name */
    private static k f859a;

    /* renamed from: b  reason: collision with root package name */
    private final Context f860b;

    /* renamed from: c  reason: collision with root package name */
    private final LocationManager f861c;

    /* renamed from: d  reason: collision with root package name */
    private final a f862d = new a();

    static k a(Context context) {
        if (f859a == null) {
            Context applicationContext = context.getApplicationContext();
            f859a = new k(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return f859a;
    }

    k(Context context, LocationManager locationManager) {
        this.f860b = context;
        this.f861c = locationManager;
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        a aVar = this.f862d;
        if (c()) {
            return aVar.f863a;
        }
        Location b2 = b();
        if (b2 != null) {
            a(b2);
            return aVar.f863a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i = Calendar.getInstance().get(11);
        return i < 6 || i >= 22;
    }

    @SuppressLint({"MissingPermission"})
    private Location b() {
        Location location = null;
        Location a2 = h.a(this.f860b, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? a("network") : null;
        if (h.a(this.f860b, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            location = a("gps");
        }
        return (location == null || a2 == null) ? location != null ? location : a2 : location.getTime() > a2.getTime() ? location : a2;
    }

    private Location a(String str) {
        try {
            if (this.f861c.isProviderEnabled(str)) {
                return this.f861c.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e) {
            Log.d("TwilightManager", "Failed to get last known location", e);
            return null;
        }
    }

    private boolean c() {
        return this.f862d.f > System.currentTimeMillis();
    }

    private void a(Location location) {
        long j;
        a aVar = this.f862d;
        long currentTimeMillis = System.currentTimeMillis();
        j a2 = j.a();
        a2.a(currentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        long j2 = a2.f856a;
        a2.a(currentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z = a2.f858c == 1;
        long j3 = a2.f857b;
        long j4 = a2.f856a;
        a2.a(86400000 + currentTimeMillis, location.getLatitude(), location.getLongitude());
        long j5 = a2.f857b;
        if (j3 == -1 || j4 == -1) {
            j = 43200000 + currentTimeMillis;
        } else {
            j = (currentTimeMillis > j4 ? 0 + j5 : currentTimeMillis > j3 ? 0 + j4 : 0 + j3) + 60000;
        }
        aVar.f863a = z;
        aVar.f864b = j2;
        aVar.f865c = j3;
        aVar.f866d = j4;
        aVar.e = j5;
        aVar.f = j;
    }

    /* access modifiers changed from: private */
    /* compiled from: TwilightManager */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        boolean f863a;

        /* renamed from: b  reason: collision with root package name */
        long f864b;

        /* renamed from: c  reason: collision with root package name */
        long f865c;

        /* renamed from: d  reason: collision with root package name */
        long f866d;
        long e;
        long f;

        a() {
        }
    }
}
