package com.facebook.react.modules.location;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.c;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.i;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.gms.measurement.AppMeasurement;

@SuppressLint({"MissingPermission"})
@com.facebook.react.module.a.a(a = LocationModule.NAME)
public class LocationModule extends ReactContextBaseJavaModule {
    public static final String NAME = "LocationObserver";
    private static final float RCT_DEFAULT_LOCATION_ACCURACY = 100.0f;
    private final LocationListener mLocationListener = new LocationListener() {
        /* class com.facebook.react.modules.location.LocationModule.AnonymousClass1 */

        public void onProviderDisabled(String str) {
        }

        public void onProviderEnabled(String str) {
        }

        public void onLocationChanged(Location location) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) LocationModule.this.getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("geolocationDidChange", LocationModule.locationToMap(location));
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
            if (i == 0) {
                LocationModule locationModule = LocationModule.this;
                int i2 = a.f2907b;
                locationModule.emitError(i2, "Provider " + str + " is out of service.");
            } else if (i == 1) {
                LocationModule locationModule2 = LocationModule.this;
                int i3 = a.f2908c;
                locationModule2.emitError(i3, "Provider " + str + " is temporarily unavailable.");
            }
        }
    };
    private String mWatchedProvider;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public LocationModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private final long f2896a;

        /* renamed from: b  reason: collision with root package name */
        private final double f2897b;

        /* renamed from: c  reason: collision with root package name */
        private final boolean f2898c;

        /* renamed from: d  reason: collision with root package name */
        private final float f2899d;

        private a(long j, double d2, boolean z, float f) {
            this.f2896a = j;
            this.f2897b = d2;
            this.f2898c = z;
            this.f2899d = f;
        }

        /* access modifiers changed from: private */
        public static a b(ReadableMap readableMap) {
            return new a(readableMap.hasKey("timeout") ? (long) readableMap.getDouble("timeout") : Long.MAX_VALUE, readableMap.hasKey("maximumAge") ? readableMap.getDouble("maximumAge") : Double.POSITIVE_INFINITY, readableMap.hasKey("enableHighAccuracy") && readableMap.getBoolean("enableHighAccuracy"), readableMap.hasKey("distanceFilter") ? (float) readableMap.getDouble("distanceFilter") : LocationModule.RCT_DEFAULT_LOCATION_ACCURACY);
        }
    }

    @ReactMethod
    public void getCurrentPosition(ReadableMap readableMap, Callback callback, Callback callback2) {
        a b2 = a.b(readableMap);
        try {
            LocationManager locationManager = (LocationManager) getReactApplicationContext().getSystemService("location");
            String validProvider = getValidProvider(locationManager, b2.f2898c);
            if (validProvider == null) {
                callback2.invoke(a.a(a.f2907b, "No location provider available."));
                return;
            }
            Location lastKnownLocation = locationManager.getLastKnownLocation(validProvider);
            if (lastKnownLocation == null || ((double) (i.a() - lastKnownLocation.getTime())) >= b2.f2897b) {
                new b(locationManager, validProvider, b2.f2896a, callback, callback2).a(lastKnownLocation);
                return;
            }
            callback.invoke(locationToMap(lastKnownLocation));
        } catch (SecurityException e) {
            throwLocationPermissionMissing(e);
        }
    }

    @ReactMethod
    public void startObserving(ReadableMap readableMap) {
        if (!"gps".equals(this.mWatchedProvider)) {
            a b2 = a.b(readableMap);
            try {
                LocationManager locationManager = (LocationManager) getReactApplicationContext().getSystemService("location");
                String validProvider = getValidProvider(locationManager, b2.f2898c);
                if (validProvider == null) {
                    emitError(a.f2907b, "No location provider available.");
                    return;
                }
                if (!validProvider.equals(this.mWatchedProvider)) {
                    locationManager.removeUpdates(this.mLocationListener);
                    locationManager.requestLocationUpdates(validProvider, 1000, b2.f2899d, this.mLocationListener);
                }
                this.mWatchedProvider = validProvider;
            } catch (SecurityException e) {
                throwLocationPermissionMissing(e);
            }
        }
    }

    @ReactMethod
    public void stopObserving() {
        ((LocationManager) getReactApplicationContext().getSystemService("location")).removeUpdates(this.mLocationListener);
        this.mWatchedProvider = null;
    }

    private String getValidProvider(LocationManager locationManager, boolean z) {
        String str = z ? "gps" : "network";
        if (!locationManager.isProviderEnabled(str)) {
            str = str.equals("gps") ? "network" : "gps";
            if (!locationManager.isProviderEnabled(str)) {
                return null;
            }
        }
        int b2 = c.b(getReactApplicationContext(), "android.permission.ACCESS_FINE_LOCATION");
        if (!str.equals("gps") || b2 == 0) {
            return str;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static WritableMap locationToMap(Location location) {
        WritableMap createMap = Arguments.createMap();
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putDouble("latitude", location.getLatitude());
        createMap2.putDouble("longitude", location.getLongitude());
        createMap2.putDouble("altitude", location.getAltitude());
        createMap2.putDouble("accuracy", (double) location.getAccuracy());
        createMap2.putDouble("heading", (double) location.getBearing());
        createMap2.putDouble("speed", (double) location.getSpeed());
        createMap.putMap("coords", createMap2);
        createMap.putDouble(AppMeasurement.Param.TIMESTAMP, (double) location.getTime());
        if (Build.VERSION.SDK_INT >= 18) {
            createMap.putBoolean("mocked", location.isFromMockProvider());
        }
        return createMap;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void emitError(int i, String str) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("geolocationError", a.a(i, str));
    }

    private static void throwLocationPermissionMissing(SecurityException securityException) {
        throw new SecurityException("Looks like the app doesn't have the permission to access location.\nAdd the following line to your app's AndroidManifest.xml:\n<uses-permission android:name=\"android.permission.ACCESS_FINE_LOCATION\" />", securityException);
    }

    private static class b {

        /* renamed from: a  reason: collision with root package name */
        private final Callback f2900a;

        /* renamed from: b  reason: collision with root package name */
        private final Callback f2901b;

        /* renamed from: c  reason: collision with root package name */
        private final LocationManager f2902c;

        /* renamed from: d  reason: collision with root package name */
        private final String f2903d;
        private final long e;
        private Location f;
        private final Handler g;
        private final Runnable h;
        private final LocationListener i;
        private boolean j;

        private b(LocationManager locationManager, String str, long j2, Callback callback, Callback callback2) {
            this.g = new Handler();
            this.h = new Runnable() {
                /* class com.facebook.react.modules.location.LocationModule.b.AnonymousClass1 */

                public void run() {
                    synchronized (b.this) {
                        if (!b.this.j) {
                            b.this.f2901b.invoke(a.a(a.f2908c, "Location request timed out"));
                            b.this.f2902c.removeUpdates(b.this.i);
                            com.facebook.common.e.a.b("ReactNative", "LocationModule: Location request timed out");
                            b.this.j = true;
                        }
                    }
                }
            };
            this.i = new LocationListener() {
                /* class com.facebook.react.modules.location.LocationModule.b.AnonymousClass2 */

                public void onProviderDisabled(String str) {
                }

                public void onProviderEnabled(String str) {
                }

                public void onStatusChanged(String str, int i, Bundle bundle) {
                }

                public void onLocationChanged(Location location) {
                    synchronized (b.this) {
                        if (!b.this.j && b.this.a((b) location, b.this.f)) {
                            b.this.f2900a.invoke(LocationModule.locationToMap(location));
                            b.this.g.removeCallbacks(b.this.h);
                            b.this.j = true;
                            b.this.f2902c.removeUpdates(b.this.i);
                        }
                        b.this.f = location;
                    }
                }
            };
            this.f2902c = locationManager;
            this.f2903d = str;
            this.e = j2;
            this.f2900a = callback;
            this.f2901b = callback2;
        }

        public void a(Location location) {
            this.f = location;
            this.f2902c.requestLocationUpdates(this.f2903d, 100, 1.0f, this.i);
            this.g.postDelayed(this.h, this.e);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private boolean a(Location location, Location location2) {
            if (location2 == null) {
                return true;
            }
            long time = location.getTime() - location2.getTime();
            boolean z = time > 120000;
            boolean z2 = time < -120000;
            boolean z3 = time > 0;
            if (z) {
                return true;
            }
            if (z2) {
                return false;
            }
            int accuracy = (int) (location.getAccuracy() - location2.getAccuracy());
            boolean z4 = accuracy > 0;
            boolean z5 = accuracy < 0;
            boolean z6 = accuracy > 200;
            boolean a2 = a(location.getProvider(), location2.getProvider());
            if (z5) {
                return true;
            }
            if (z3 && !z4) {
                return true;
            }
            if (!z3 || z6 || !a2) {
                return false;
            }
            return true;
        }

        private boolean a(String str, String str2) {
            if (str == null) {
                return str2 == null;
            }
            return str.equals(str2);
        }
    }
}
