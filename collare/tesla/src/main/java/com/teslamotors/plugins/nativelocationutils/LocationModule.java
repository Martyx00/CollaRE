package com.teslamotors.plugins.nativelocationutils;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.i;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.measurement.AppMeasurement;
import com.teslamotors.plugins.biometricauthentication.BiometricAuthenticationModule;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationModule extends ReactContextBaseJavaModule {
    private static final float RCT_DEFAULT_LOCATION_ACCURACY = 100.0f;
    private static final String TAG = "LocationModule";
    private static List<String> sProviders = Arrays.asList("gps", "network", "passive");
    private final LocationListener mLocationListener = new LocationListener() {
        /* class com.teslamotors.plugins.nativelocationutils.LocationModule.AnonymousClass1 */

        public void onProviderDisabled(String str) {
        }

        public void onProviderEnabled(String str) {
        }

        public void onLocationChanged(Location location) {
            if (LocationModule.this.getReactApplicationContext().hasActiveCatalystInstance()) {
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) LocationModule.this.getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("geolocationDidChange", LocationModule.locationToMap(location));
            }
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
            if (i == 0) {
                LocationModule locationModule = LocationModule.this;
                locationModule.emitError("Provider " + str + " is out of service.");
            } else if (i == 1) {
                LocationModule locationModule2 = LocationModule.this;
                locationModule2.emitError("Provider " + str + " is temporarily unavailable.");
            }
        }
    };
    private List<String> mWatchedProviders = new ArrayList();

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "TMLocationObserver";
    }

    public LocationModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    /* access modifiers changed from: private */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final long f5597a;

        /* renamed from: b  reason: collision with root package name */
        private final double f5598b;

        /* renamed from: c  reason: collision with root package name */
        private final boolean f5599c;

        /* renamed from: d  reason: collision with root package name */
        private final float f5600d;

        private a(long j, double d2, boolean z, float f) {
            this.f5597a = j;
            this.f5598b = d2;
            this.f5599c = z;
            this.f5600d = f;
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
            String validProvider = getValidProvider(locationManager, b2.f5599c);
            if (validProvider == null) {
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putInt("code", 1);
                writableNativeMap.putString(BiometricAuthenticationModule.BIOMETRICS_BUNDLE_KEY_MESSAGE, "No available location provider.");
                writableNativeMap.putInt("PERMISSION_DENIED", 1);
                writableNativeMap.putInt("POSITION_UNAVAILABLE", 0);
                writableNativeMap.putInt(InstanceID.ERROR_TIMEOUT, 0);
                callback2.invoke(writableNativeMap);
                return;
            }
            Location lastKnownLocation = locationManager.getLastKnownLocation(validProvider);
            if (lastKnownLocation == null || ((double) (i.a() - lastKnownLocation.getTime())) >= b2.f5598b) {
                new b(locationManager, validProvider, b2.f5597a, callback, callback2).a();
                return;
            }
            callback.invoke(locationToMap(lastKnownLocation));
        } catch (SecurityException e) {
            throwLocationPermissionMissing(e);
        }
    }

    @ReactMethod
    public void startObserving(ReadableMap readableMap) {
        if (this.mWatchedProviders.size() <= 0) {
            try {
                setupListeners((LocationManager) getReactApplicationContext().getSystemService("location"), a.b(readableMap));
                if (this.mWatchedProviders.size() == 0) {
                    emitError("No location providers are available.");
                }
            } catch (SecurityException e) {
                throwLocationPermissionMissing(e);
            }
        }
    }

    private void setupListeners(LocationManager locationManager, a aVar) {
        for (String str : sProviders) {
            if (!this.mWatchedProviders.contains(str)) {
                try {
                    locationManager.requestLocationUpdates(str, 1000, aVar.f5600d, this.mLocationListener);
                    this.mWatchedProviders.add(str);
                } catch (Exception unused) {
                    Log.e(TAG, String.format("Failed to add location provider: %s", str));
                }
            }
        }
    }

    @ReactMethod
    public void stopObserving() {
        ((LocationManager) getReactApplicationContext().getSystemService("location")).removeUpdates(this.mLocationListener);
        this.mWatchedProviders.clear();
    }

    private static String getValidProvider(LocationManager locationManager, boolean z) {
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
        createMap.putBoolean("mocked", location.isFromMockProvider());
        return createMap;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void emitError(String str) {
        if (getReactApplicationContext().hasActiveCatalystInstance()) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("geolocationError", str);
        }
    }

    private static void throwLocationPermissionMissing(SecurityException securityException) {
        throw new SecurityException("Looks like the app doesn't have the permission to access location.\nAdd the following line to your app's AndroidManifest.xml:\n<uses-permission android:name=\"android.permission.ACCESS_FINE_LOCATION\" />", securityException);
    }

    private static class b {

        /* renamed from: a  reason: collision with root package name */
        private final Callback f5601a;

        /* renamed from: b  reason: collision with root package name */
        private final Callback f5602b;

        /* renamed from: c  reason: collision with root package name */
        private final LocationManager f5603c;

        /* renamed from: d  reason: collision with root package name */
        private final String f5604d;
        private final long e;
        private final Handler f;
        private final Runnable g;
        private final LocationListener h;
        private boolean i;

        private b(LocationManager locationManager, String str, long j, Callback callback, Callback callback2) {
            this.f = new Handler();
            this.g = new Runnable() {
                /* class com.teslamotors.plugins.nativelocationutils.LocationModule.b.AnonymousClass1 */

                public void run() {
                    synchronized (b.this) {
                        if (!b.this.i) {
                            b.this.f5602b.invoke("Location request timed out");
                            b.this.f5603c.removeUpdates(b.this.h);
                            b.this.i = true;
                        }
                    }
                }
            };
            this.h = new LocationListener() {
                /* class com.teslamotors.plugins.nativelocationutils.LocationModule.b.AnonymousClass2 */

                public void onProviderDisabled(String str) {
                }

                public void onProviderEnabled(String str) {
                }

                public void onStatusChanged(String str, int i, Bundle bundle) {
                }

                public void onLocationChanged(Location location) {
                    synchronized (b.this) {
                        if (!b.this.i) {
                            b.this.f5601a.invoke(LocationModule.locationToMap(location));
                            b.this.f.removeCallbacks(b.this.g);
                            b.this.i = true;
                        }
                    }
                }
            };
            this.f5603c = locationManager;
            this.f5604d = str;
            this.e = j;
            this.f5601a = callback;
            this.f5602b = callback2;
        }

        public void a() {
            this.f5603c.requestSingleUpdate(this.f5604d, this.h, (Looper) null);
            this.f.postDelayed(this.g, this.e);
        }
    }
}
