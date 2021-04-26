package com.github.reactnativecommunity.location;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;

/* compiled from: RNStandardLocationProvider */
public class d implements b {

    /* renamed from: a  reason: collision with root package name */
    private final ReactApplicationContext f3779a;

    /* renamed from: b  reason: collision with root package name */
    private a f3780b = new a();

    /* renamed from: c  reason: collision with root package name */
    private String f3781c;

    /* renamed from: d  reason: collision with root package name */
    private final LocationListener f3782d = new LocationListener() {
        /* class com.github.reactnativecommunity.location.d.AnonymousClass1 */

        public void onProviderDisabled(String str) {
        }

        public void onProviderEnabled(String str) {
        }

        public void onLocationChanged(Location location) {
            d.this.a((d) location);
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
            if (i == 0) {
                ReactApplicationContext reactApplicationContext = d.this.f3779a;
                e.a(reactApplicationContext, "Provider " + str + " is out of service.", "500");
            } else if (i == 1) {
                ReactApplicationContext reactApplicationContext2 = d.this.f3779a;
                e.a(reactApplicationContext2, "Provider " + str + " is temporarily unavailable.", "501");
            }
        }
    };

    public d(ReactApplicationContext reactApplicationContext) {
        this.f3779a = reactApplicationContext;
    }

    @Override // com.github.reactnativecommunity.location.b
    public void a(Activity activity, ReadableMap readableMap, Promise promise) {
        this.f3780b = a.b(this.f3779a, readableMap);
        if (this.f3781c != null) {
            c();
        }
        promise.resolve(null);
    }

    @Override // com.github.reactnativecommunity.location.b
    public void a() {
        c();
    }

    @Override // com.github.reactnativecommunity.location.b
    public void b() {
        LocationManager locationManager = (LocationManager) this.f3779a.getSystemService("location");
        if (locationManager != null) {
            locationManager.removeUpdates(this.f3782d);
            this.f3781c = null;
        }
    }

    private void c() {
        try {
            LocationManager locationManager = (LocationManager) this.f3779a.getSystemService("location");
            if (locationManager == null) {
                e.a(this.f3779a, "No location manager is available.", "502");
                return;
            }
            String a2 = a(locationManager, this.f3780b.f3784a);
            if (a2 == null) {
                e.a(this.f3779a, "There is no valid location provider available.", "503");
                return;
            }
            if (!a2.equals(this.f3781c)) {
                locationManager.removeUpdates(this.f3782d);
                locationManager.requestLocationUpdates(a2, 1000, this.f3780b.f3785b, this.f3782d);
                Location lastKnownLocation = locationManager.getLastKnownLocation(a2);
                if (lastKnownLocation != null) {
                    a(lastKnownLocation);
                }
            }
            this.f3781c = a2;
        } catch (SecurityException e) {
            ReactApplicationContext reactApplicationContext = this.f3779a;
            e.a(reactApplicationContext, "Attempted to start updating the location without location permissions. Detail: " + e.getLocalizedMessage(), "403");
        }
    }

    private String a(LocationManager locationManager, boolean z) {
        String str = z ? "gps" : "network";
        if (!locationManager.isProviderEnabled(str)) {
            str = str.equals("gps") ? "network" : "gps";
            if (!locationManager.isProviderEnabled(str)) {
                return null;
            }
        }
        return str;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(Location location) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushMap(e.a(location));
        e.a(this.f3779a, "locationUpdated", createArray);
    }

    /* access modifiers changed from: private */
    /* compiled from: RNStandardLocationProvider */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final boolean f3784a;

        /* renamed from: b  reason: collision with root package name */
        private final float f3785b;

        private a() {
            this.f3784a = false;
            this.f3785b = 100.0f;
        }

        private a(boolean z, float f) {
            this.f3784a = z;
            this.f3785b = f;
        }

        /* access modifiers changed from: private */
        public static a b(ReactApplicationContext reactApplicationContext, ReadableMap readableMap) {
            float f;
            boolean z = false;
            if (readableMap.hasKey("desiredAccuracy")) {
                if (readableMap.getType("desiredAccuracy") == ReadableType.Map) {
                    ReadableMap map = readableMap.getMap("desiredAccuracy");
                    if (map.hasKey(io.a.a.a.a.b.a.ANDROID_CLIENT_TYPE)) {
                        if (map.getType(io.a.a.a.a.b.a.ANDROID_CLIENT_TYPE) == ReadableType.String) {
                            String string = map.getString(io.a.a.a.a.b.a.ANDROID_CLIENT_TYPE);
                            char c2 = 65535;
                            int hashCode = string.hashCode();
                            if (hashCode != -1837176303) {
                                if (hashCode != 1008548027) {
                                    if (hashCode != 1781006582) {
                                        if (hashCode == 2096298948 && string.equals("noPower")) {
                                            c2 = 3;
                                        }
                                    } else if (string.equals("balancedPowerAccuracy")) {
                                        c2 = 1;
                                    }
                                } else if (string.equals("highAccuracy")) {
                                    c2 = 0;
                                }
                            } else if (string.equals("lowPower")) {
                                c2 = 2;
                            }
                            switch (c2) {
                                case 0:
                                    z = true;
                                    break;
                                case 1:
                                case 2:
                                case 3:
                                    break;
                                default:
                                    e.a(reactApplicationContext, "desiredAccuracy.android was passed an unknown value: " + string, "401");
                                    break;
                            }
                        } else {
                            e.a(reactApplicationContext, "desiredAccuracy.android must be a string", "401");
                        }
                    }
                } else {
                    e.a(reactApplicationContext, "desiredAccuracy must be an object", "401");
                }
            }
            if (readableMap.hasKey("distanceFilter")) {
                if (readableMap.getType("distanceFilter") == ReadableType.Number) {
                    f = (float) readableMap.getDouble("distanceFilter");
                    return new a(z, f);
                }
                e.a(reactApplicationContext, "distanceFilter must be a number", "401");
            }
            f = 100.0f;
            return new a(z, f);
        }
    }
}
