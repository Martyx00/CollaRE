package com.teslamotors.plugins.notifications;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.f;
import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class PushNotificationModule extends ReactContextBaseJavaModule {
    private static final String DEVICE_TOKEN_KEY = "deviceToken";
    public static final String INITIAL_NOTIFICATION_BUNDLE_EXTRA_KEY = "initialNotification";
    private static final String JSON_MESSAGE_KEY = "dataJSON";
    public static final String NOTIFICATION_REACT_RECEIVED_EVENT = "remoteNotificationReceived";
    public static final String NOTIFICATION_REACT_TAG = "PushNotificationAndroid";
    public static final String REGISTRATION_REACT_EVENT = "registrationReceived";
    private static final String TAG = "PushNotificationModule";
    private BroadcastReceiver mNotificationBroadcastReceiver;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NOTIFICATION_REACT_TAG;
    }

    public PushNotificationModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        registerNotificationReceiver();
        registerRegistrationReceiver();
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        return new HashMap();
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        if (this.mNotificationBroadcastReceiver != null) {
            f.a(getReactApplicationContext()).a(this.mNotificationBroadcastReceiver);
            this.mNotificationBroadcastReceiver = null;
        }
        if (this.mRegistrationBroadcastReceiver != null) {
            f.a(getReactApplicationContext()).a(this.mRegistrationBroadcastReceiver);
            this.mRegistrationBroadcastReceiver = null;
        }
    }

    @ReactMethod
    public void getInitialNotification(Promise promise) {
        String notificationJSON;
        WritableMap createMap = Arguments.createMap();
        Activity currentActivity = getCurrentActivity();
        if (!(currentActivity == null || (notificationJSON = getNotificationJSON(currentActivity.getIntent())) == null)) {
            createMap.putString(INITIAL_NOTIFICATION_BUNDLE_EXTRA_KEY, formalizeNotificationJSON(notificationJSON, true));
        }
        promise.resolve(createMap);
    }

    public String getNotificationJSON(Intent intent) {
        String stringExtra = intent.getStringExtra("notification");
        if (!"CN".equals("US")) {
            return stringExtra;
        }
        String dataString = intent.getDataString();
        String stringExtra2 = intent.getStringExtra("JMessageExtra");
        if (dataString != null) {
            try {
                return new JSONObject(dataString).getJSONObject("n_extras").toString();
            } catch (JSONException e) {
                String str = TAG;
                Log.e(str, "Failed to Parse JSON: " + e.getMessage());
                return null;
            }
        } else if (stringExtra2 != null) {
            return new JSONObject(stringExtra2).getJSONObject("n_extras").toString();
        } else {
            if (stringExtra != null) {
                return stringExtra;
            }
            return null;
        }
    }

    @ReactMethod
    public void abandonPermissions() {
        b.a(getReactApplicationContext().getApplicationContext()).c();
    }

    public boolean onNotificationClick(Intent intent) {
        String notificationJSON = getNotificationJSON(intent);
        if (notificationJSON == null) {
            return false;
        }
        sendNotificationToJs(notificationJSON, true);
        return true;
    }

    private void registerNotificationReceiver() {
        IntentFilter intentFilter = new IntentFilter(NOTIFICATION_REACT_RECEIVED_EVENT);
        this.mNotificationBroadcastReceiver = new BroadcastReceiver() {
            /* class com.teslamotors.plugins.notifications.PushNotificationModule.AnonymousClass1 */

            public void onReceive(Context context, Intent intent) {
                String notificationJSON = PushNotificationModule.this.getNotificationJSON(intent);
                if (notificationJSON != null) {
                    PushNotificationModule.this.sendNotificationToJs(notificationJSON, false);
                }
            }
        };
        f.a(getReactApplicationContext()).a(this.mNotificationBroadcastReceiver, intentFilter);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendNotificationToJs(String str, boolean z) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(JSON_MESSAGE_KEY, formalizeNotificationJSON(str, z));
        sendEvent(NOTIFICATION_REACT_RECEIVED_EVENT, createMap);
    }

    private void registerRegistrationReceiver() {
        IntentFilter intentFilter = new IntentFilter("remoteNotificationRegistered");
        this.mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            /* class com.teslamotors.plugins.notifications.PushNotificationModule.AnonymousClass2 */

            public void onReceive(Context context, Intent intent) {
                WritableMap createMap = Arguments.createMap();
                createMap.putString(PushNotificationModule.DEVICE_TOKEN_KEY, intent.getStringExtra(PushNotificationModule.DEVICE_TOKEN_KEY));
                PushNotificationModule.this.sendEvent(PushNotificationModule.REGISTRATION_REACT_EVENT, createMap);
            }
        };
        f.a(getReactApplicationContext()).a(this.mRegistrationBroadcastReceiver, intentFilter);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendEvent(String str, WritableMap writableMap) {
        if (getReactApplicationContext().hasActiveCatalystInstance()) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
        }
    }

    private String formalizeNotificationJSON(String str, boolean z) {
        JSONObject i = new a(str).i();
        if (i == null) {
            return str;
        }
        try {
            i.put("was_user_actioned", z ? "true" : "false");
            return i.toString();
        } catch (JSONException unused) {
            Log.e(TAG, "Failed to attach was_user_actioned to notification JSON string");
            return str;
        }
    }
}
