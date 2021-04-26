package com.teslamotors.plugins.client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Vibrator;
import android.support.v4.app.ab;
import android.util.Base64;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.c;
import com.teslamotors.plugins.client.b.a;
import com.teslamotors.plugins.client.d.b;
import java.lang.reflect.InvocationTargetException;
import java.security.SecureRandom;
import org.json.JSONObject;

public class ClientModule extends ReactContextBaseJavaModule {
    private static final String LOCATION_SERVICES_PERMISSION_GRANTED = "GRANTED_ALWAYS";
    private static final String NOTIFICATION_REGISTRATION_SERVICE = "com.teslamotors.plugins.notifications.RegistrationIntentService";
    private static final String PERMISSION_DENIED = "DENIED";
    private static final String PERMISSION_GRANTED = "GRANTED";
    private static final String PERMISSION_NEVER_ASK_AGAIN = "RESTRICTED";
    private static final String PERMISSION_NOT_DETERMINED = "NOT_DETERMINED";
    private static final String REACT_CLASS = "TMClient";
    private static final String RESPONSE = "response";
    private static final String RESULT = "result";
    private static final String TAG = "ClientModule";

    @ReactMethod
    public void forceUpdateWidgetExtension() {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    public ClientModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void getCurrentCountryCode(Promise promise) {
        promise.resolve(f.a(getReactApplicationContext()).b());
    }

    @ReactMethod
    public void openNativePermissionSettings() {
        f.a(getReactApplicationContext()).a(getCurrentActivity());
    }

    @ReactMethod
    public void openNativeLocationServicesSettings() {
        f.a(getReactApplicationContext()).b(getCurrentActivity());
    }

    @ReactMethod
    public void checkCalendarPermission(Promise promise) {
        checkPermission("android.permission.READ_CALENDAR", promise);
    }

    @ReactMethod
    public void checkLocationServicesPermission(Promise promise) {
        checkPermission("android.permission.ACCESS_FINE_LOCATION", promise);
    }

    @ReactMethod
    public void checkContactsPermission(Promise promise) {
        checkPermission("android.permission.READ_CONTACTS", promise);
    }

    @ReactMethod
    public void checkCameraPermission(Promise promise) {
        checkPermission("android.permission.CAMERA", promise);
    }

    @ReactMethod
    public void checkPhotosPermission(Promise promise) {
        checkPermission("android.permission.READ_EXTERNAL_STORAGE", promise);
    }

    private void checkPermission(String str, Promise promise) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("result", f.a(getReactApplicationContext()).d(str) ? str.equals("android.permission.ACCESS_FINE_LOCATION") ? LOCATION_SERVICES_PERMISSION_GRANTED : PERMISSION_GRANTED : PERMISSION_NOT_DETERMINED);
        promise.resolve(writableNativeMap);
    }

    @ReactMethod
    public void checkLocationServicesEnabled(Promise promise) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putBoolean("result", f.a(getReactApplicationContext()).w());
        promise.resolve(writableNativeMap);
    }

    @ReactMethod
    public void checkNotificationPermission(Promise promise) {
        boolean a2 = ab.a(getReactApplicationContext()).a();
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        WritableNativeMap writableNativeMap2 = new WritableNativeMap();
        writableNativeMap2.putBoolean("alert", a2);
        writableNativeMap2.putBoolean("badge", a2);
        writableNativeMap2.putBoolean("sound", a2);
        writableNativeMap.putMap("result", writableNativeMap2);
        promise.resolve(writableNativeMap);
    }

    @ReactMethod
    public void requestCalendarPermission(Promise promise) {
        requestPermission("android.permission.READ_CALENDAR", promise);
    }

    @ReactMethod
    public void requestContactsPermission(Promise promise) {
        requestPermission("android.permission.READ_CONTACTS", promise);
    }

    @ReactMethod
    public void requestLocationServicesPermission(boolean z, Promise promise) {
        requestPermission("android.permission.ACCESS_FINE_LOCATION", promise);
    }

    @ReactMethod
    public void requestCameraPermission(Promise promise) {
        requestPermission("android.permission.CAMERA", promise);
    }

    @ReactMethod
    public void requestPhotosPermission(Promise promise) {
        requestPermission("android.permission.READ_EXTERNAL_STORAGE", promise);
    }

    @ReactMethod
    public void requestNotificationPermission(ReadableMap readableMap, Promise promise) {
        Intent intent = new Intent();
        intent.setClassName(getReactApplicationContext(), NOTIFICATION_REGISTRATION_SERVICE);
        try {
            Class.forName(NOTIFICATION_REGISTRATION_SERVICE).getMethod("enqueueWork", Context.class, Intent.class).invoke(null, getReactApplicationContext(), intent);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            b.a(TAG, "Fail to start notification registration service", e);
        }
        checkNotificationPermission(promise);
    }

    private void requestPermission(String str, final Promise promise) {
        final WritableNativeMap writableNativeMap = new WritableNativeMap();
        Activity currentActivity = getCurrentActivity();
        final boolean z = (currentActivity instanceof c) && Build.VERSION.SDK_INT >= 23 && !currentActivity.shouldShowRequestPermissionRationale(str);
        f.a(getReactApplicationContext()).a(getCurrentActivity(), str, new com.teslamotors.plugins.client.a.c() {
            /* class com.teslamotors.plugins.client.ClientModule.AnonymousClass1 */

            @Override // com.teslamotors.plugins.client.a.c
            public void a(int i, String str) {
                writableNativeMap.putString("result", str.equals("android.permission.ACCESS_FINE_LOCATION") ? ClientModule.LOCATION_SERVICES_PERMISSION_GRANTED : ClientModule.PERMISSION_GRANTED);
                promise.resolve(writableNativeMap);
            }

            @Override // com.teslamotors.plugins.client.a.c
            public void b(int i, String str) {
                writableNativeMap.putString("result", z ? ClientModule.PERMISSION_NEVER_ASK_AGAIN : ClientModule.PERMISSION_DENIED);
                promise.resolve(writableNativeMap);
            }
        });
    }

    @ReactMethod
    public void syncSharedSelectedVehicleData(ReadableMap readableMap, Promise promise) {
        ReadableMap map = readableMap.getMap("vehicle_data");
        if (map != null) {
            String string = map.getString("vin");
            a.a(string, getReactApplicationContext());
            if (map.hasKey("display_name")) {
                f.a(getReactApplicationContext()).c(string, map.getString("display_name"));
            }
        }
        JSONObject a2 = b.a(readableMap);
        if (a2 != null) {
            a.d(getReactApplicationContext(), a2.toString());
        }
    }

    @ReactMethod
    public void activateKeepAwake() {
        final Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.runOnUiThread(new Runnable() {
                /* class com.teslamotors.plugins.client.ClientModule.AnonymousClass2 */

                public void run() {
                    currentActivity.getWindow().addFlags(128);
                }
            });
        }
    }

    @ReactMethod
    public void deactivateKeepAwake() {
        final Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.runOnUiThread(new Runnable() {
                /* class com.teslamotors.plugins.client.ClientModule.AnonymousClass3 */

                public void run() {
                    currentActivity.getWindow().clearFlags(128);
                }
            });
        }
    }

    @ReactMethod
    public void selectionFeedback() {
        Vibrator vibrator = (Vibrator) getReactApplicationContext().getSystemService("vibrator");
        if (vibrator != null) {
            vibrator.vibrate(new long[]{0, 20}, -1);
        }
    }

    @ReactMethod
    public void randomBytes(int i, Promise promise) {
        byte[] bArr = new byte[i];
        new SecureRandom().nextBytes(bArr);
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putBoolean("result", true);
        writableNativeMap.putString("bytes", Base64.encodeToString(bArr, 0));
        promise.resolve(writableNativeMap);
    }
}
