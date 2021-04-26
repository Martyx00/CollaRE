package com.github.reactnativecommunity.location;

import android.location.Location;
import android.os.Build;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.google.android.gms.measurement.AppMeasurement;
import com.teslamotors.plugins.biometricauthentication.BiometricAuthenticationModule;

/* compiled from: Utils */
public class e {
    public static void a(ReactApplicationContext reactApplicationContext, String str, String str2) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(BiometricAuthenticationModule.BIOMETRICS_BUNDLE_KEY_MESSAGE, str);
        createMap.putString(AppMeasurement.Param.TYPE, str2);
        a(reactApplicationContext, "onWarning", createMap);
    }

    public static void a(ReactApplicationContext reactApplicationContext, String str, Object obj) {
        ((RCTNativeAppEventEmitter) reactApplicationContext.getJSModule(RCTNativeAppEventEmitter.class)).emit(str, obj);
    }

    public static WritableMap a(Location location) {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("latitude", location.getLatitude());
        createMap.putDouble("longitude", location.getLongitude());
        createMap.putDouble("accuracy", (double) location.getAccuracy());
        createMap.putDouble("altitude", location.getAltitude());
        if (Build.VERSION.SDK_INT >= 26) {
            createMap.putDouble("altitudeAccuracy", (double) location.getVerticalAccuracyMeters());
        } else {
            createMap.putDouble("altitudeAccuracy", 0.0d);
        }
        createMap.putDouble("course", (double) location.getBearing());
        if (Build.VERSION.SDK_INT >= 26) {
            createMap.putDouble("courseAccuracy", (double) location.getBearingAccuracyDegrees());
        } else {
            createMap.putDouble("courseAccuracy", 0.0d);
        }
        createMap.putDouble("speed", (double) location.getSpeed());
        if (Build.VERSION.SDK_INT >= 26) {
            createMap.putDouble("speedAccuracy", (double) location.getSpeedAccuracyMetersPerSecond());
        } else {
            createMap.putDouble("speedAccuracy", 0.0d);
        }
        createMap.putDouble(AppMeasurement.Param.TIMESTAMP, (double) location.getTime());
        return createMap;
    }

    public static boolean a() {
        try {
            Class.forName("com.google.android.gms.location.FusedLocationProviderClient");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
