package com.github.reactnativecommunity.location;

import android.app.Activity;
import android.content.Intent;
import android.os.Looper;
import android.support.v4.app.a;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.SettingsClient;
import java.lang.ref.WeakReference;

/* compiled from: RNPlayServicesLocationProvider */
public class c implements b {

    /* renamed from: a  reason: collision with root package name */
    private final ReactApplicationContext f3768a;

    /* renamed from: b  reason: collision with root package name */
    private final FusedLocationProviderClient f3769b;

    /* renamed from: c  reason: collision with root package name */
    private final SettingsClient f3770c;

    /* renamed from: d  reason: collision with root package name */
    private LocationRequest f3771d = new LocationRequest();
    private boolean e = false;
    private WeakReference<Activity> f = null;
    private ReadableMap g = null;
    private Promise h = null;
    private LocationCallback i = new LocationCallback() {
        /* class com.github.reactnativecommunity.location.c.AnonymousClass3 */
    };

    public c(Activity activity, ReactApplicationContext reactApplicationContext) {
        this.f3768a = reactApplicationContext;
        if (activity != null) {
            this.f3769b = LocationServices.getFusedLocationProviderClient(activity);
            this.f3770c = LocationServices.getSettingsClient(activity);
            return;
        }
        this.f3769b = LocationServices.getFusedLocationProviderClient(reactApplicationContext);
        this.f3770c = LocationServices.getSettingsClient(reactApplicationContext);
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0164  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0169  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x003a  */
    @Override // com.github.reactnativecommunity.location.b
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(final android.app.Activity r7, final com.facebook.react.bridge.ReadableMap r8, final com.facebook.react.bridge.Promise r9) {
        /*
        // Method dump skipped, instructions count: 399
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.reactnativecommunity.location.c.a(android.app.Activity, com.facebook.react.bridge.ReadableMap, com.facebook.react.bridge.Promise):void");
    }

    @Override // com.github.reactnativecommunity.location.b
    public void a() {
        this.e = true;
        c();
    }

    @Override // com.github.reactnativecommunity.location.b
    public void b() {
        this.e = false;
        c();
    }

    public void a(int i2, int i3, Intent intent) {
        WeakReference<Activity> weakReference;
        if (i2 == 1234) {
            if (i3 != -1 || (weakReference = this.f) == null || weakReference.get() == null || this.g == null || this.h == null) {
                Promise promise = this.h;
                if (promise != null) {
                    promise.reject("500", "Error configuring react-native-location");
                }
            } else {
                a(this.f.get(), this.g, this.h);
            }
            this.f = null;
            this.g = null;
            this.h = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void c() {
        if (this.e) {
            int b2 = a.b(this.f3768a, "android.permission.ACCESS_FINE_LOCATION");
            int b3 = a.b(this.f3768a, "android.permission.ACCESS_COARSE_LOCATION");
            if (b2 == 0 || b3 == 0) {
                this.f3769b.requestLocationUpdates(this.f3771d, this.i, (Looper) null);
            } else {
                e.a(this.f3768a, "Attempted to start updating the location without location permissions", "403");
            }
        } else {
            this.f3769b.removeLocationUpdates(this.i);
        }
    }
}
