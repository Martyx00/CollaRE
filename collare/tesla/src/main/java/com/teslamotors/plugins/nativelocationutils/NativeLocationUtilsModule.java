package com.teslamotors.plugins.nativelocationutils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.teslamotors.plugins.client.a.d;
import com.teslamotors.plugins.client.b;
import com.teslamotors.plugins.client.d.c;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;

public class NativeLocationUtilsModule extends ReactContextBaseJavaModule {
    private static final String BAIDU_LOCATION_UTILS_PATH = "com.teslamotors.plugins.nativelocationutils.BaiduLocationUtils";
    private static final String COUNTRY_NAME = "country_name";
    private static final String DIRECTIONS_NO_ERROR = "DIRECTIONS_ERROR_NO_ERROR";
    private static final String DIRECTIONS_UNABLE_TO_LAUNCH = "DIRECTIONS_ERROR_UNABLE_TO_LAUNCH";
    private static final String GEOCODING_INVALID_COORDINATES = "GEOCODING_ERROR_INVALID_COORDINATES";
    private static final String GEOCODING_NO_ERROR = "GEOCODING_ERROR_NO_ERROR";
    private static final String LATITUDE = "latitude";
    private static final String LINE_1 = "line_1";
    private static final String LINE_2 = "line_2";
    private static final String LONGITUDE = "longitude";
    private static final String NO_PLACEMARKS_RETURNED = "GEOCODING_ERROR_NO_PLACEMARKS_RETURNED";
    private static final String REACT_CLASS = "TMNativeLocationUtils";
    private static final String REGION_NAME = "region_name";
    private static final String RESULT = "result";
    private static final String STREET_NAME = "street_name";
    private static final String STREET_PREFIX = "street_prefix";
    private static final String TAG = "NativeLocationUtilsModule";
    private static final String UNABLE_TO_REVERSE_GEOCODE = "GEOCODING_ERROR_UNABLE_TO_REVERSE_GEOCODE";
    private final c mGeocodingHelper;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    public NativeLocationUtilsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mGeocodingHelper = c.a(reactApplicationContext);
    }

    @ReactMethod
    public void reverseGeocodeValueForCoordinate(double d2, double d3, String str, final Promise promise) {
        final WritableNativeMap writableNativeMap = new WritableNativeMap();
        if (Math.abs(d2) > 90.0d || Math.abs(d3) > 180.0d) {
            writableNativeMap.putString("result", GEOCODING_INVALID_COORDINATES);
            promise.resolve(writableNativeMap);
            return;
        }
        AnonymousClass1 r14 = new d() {
            /* class com.teslamotors.plugins.nativelocationutils.NativeLocationUtilsModule.AnonymousClass1 */

            @Override // com.teslamotors.plugins.client.a.d
            public void a(Map<String, String> map) {
                if (map.containsKey(NativeLocationUtilsModule.LINE_1) || map.containsKey(NativeLocationUtilsModule.STREET_NAME)) {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        writableNativeMap.putString(entry.getKey(), entry.getValue());
                    }
                    writableNativeMap.putString("result", NativeLocationUtilsModule.GEOCODING_NO_ERROR);
                } else {
                    writableNativeMap.putString("result", NativeLocationUtilsModule.NO_PLACEMARKS_RETURNED);
                }
                promise.resolve(writableNativeMap);
            }

            @Override // com.teslamotors.plugins.client.a.d
            public void a() {
                writableNativeMap.putString("result", NativeLocationUtilsModule.UNABLE_TO_REVERSE_GEOCODE);
                promise.resolve(writableNativeMap);
            }
        };
        if ("CN".equals("US")) {
            try {
                Class.forName(BAIDU_LOCATION_UTILS_PATH).getDeclaredMethod("reverseGeocodeBaidu", Double.TYPE, Double.TYPE, d.class).invoke(null, Double.valueOf(d2), Double.valueOf(d3), r14);
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                b.a(TAG, "Fail to invoke reverseGeocodeBaidu.", e);
            }
        } else {
            this.mGeocodingHelper.a(d2, d3, str, r14);
        }
    }

    @ReactMethod
    public void openMapsWithDirections(ReadableMap readableMap, ReadableMap readableMap2, boolean z, String str, Promise promise) {
        if ("CN".equals("US")) {
            try {
                Class.forName(BAIDU_LOCATION_UTILS_PATH).getDeclaredMethod("openBaiduMapsDirections", ReadableMap.class, ReadableMap.class, Activity.class, Promise.class).invoke(null, readableMap, readableMap2, getCurrentActivity(), promise);
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                b.c(TAG, "Fail to invoke openBaiduMapsDirections.");
            }
        } else {
            openGoogleMapsDirections(readableMap, readableMap2, str, promise);
        }
    }

    private void openGoogleMapsDirections(ReadableMap readableMap, ReadableMap readableMap2, String str, Promise promise) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        try {
            String string = readableMap2.getString("name");
            double d2 = readableMap2.getDouble(LATITUDE);
            double d3 = readableMap2.getDouble(LONGITUDE);
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(String.format(Locale.ENGLISH, "https://maps.google.com/maps?daddr=%s@%f,%f", URLEncoder.encode(string, StandardCharsets.UTF_8.name()), Double.valueOf(d2), Double.valueOf(d3))));
            intent.setPackage("com.google.android.apps.maps");
            getCurrentActivity().startActivity(intent);
            writableNativeMap.putString("result", DIRECTIONS_NO_ERROR);
        } catch (Exception e) {
            Log.e(TAG, "Failed to launch directions", e);
            writableNativeMap.putString("result", DIRECTIONS_UNABLE_TO_LAUNCH);
        } catch (Throwable th) {
            promise.resolve(writableNativeMap);
            throw th;
        }
        promise.resolve(writableNativeMap);
    }
}
