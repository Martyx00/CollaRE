package com.facebook.react.modules.netinfo;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

@SuppressLint({"MissingPermission"})
@com.facebook.react.module.a.a(a = NetInfoModule.NAME)
public class NetInfoModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    private static final String CONNECTION_TYPE_BLUETOOTH = "bluetooth";
    private static final String CONNECTION_TYPE_CELLULAR = "cellular";
    private static final String CONNECTION_TYPE_ETHERNET = "ethernet";
    private static final String CONNECTION_TYPE_NONE = "none";
    private static final String CONNECTION_TYPE_NONE_DEPRECATED = "NONE";
    private static final String CONNECTION_TYPE_UNKNOWN = "unknown";
    private static final String CONNECTION_TYPE_UNKNOWN_DEPRECATED = "UNKNOWN";
    private static final String CONNECTION_TYPE_WIFI = "wifi";
    private static final String CONNECTION_TYPE_WIMAX = "wimax";
    private static final String EFFECTIVE_CONNECTION_TYPE_2G = "2g";
    private static final String EFFECTIVE_CONNECTION_TYPE_3G = "3g";
    private static final String EFFECTIVE_CONNECTION_TYPE_4G = "4g";
    private static final String EFFECTIVE_CONNECTION_TYPE_UNKNOWN = "unknown";
    private static final String ERROR_MISSING_PERMISSION = "E_MISSING_PERMISSION";
    private static final String MISSING_PERMISSION_MESSAGE = "To use NetInfo on Android, add the following to your AndroidManifest.xml:\n<uses-permission android:name=\"android.permission.ACCESS_NETWORK_STATE\" />";
    public static final String NAME = "NetInfo";
    private String mConnectionType = "unknown";
    private final a mConnectivityBroadcastReceiver;
    private String mConnectivityDeprecated = CONNECTION_TYPE_UNKNOWN_DEPRECATED;
    private final ConnectivityManager mConnectivityManager;
    private String mEffectiveConnectionType = "unknown";
    private boolean mNoNetworkPermission = false;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    public NetInfoModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mConnectivityManager = (ConnectivityManager) reactApplicationContext.getSystemService("connectivity");
        this.mConnectivityBroadcastReceiver = new a();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        registerReceiver();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        unregisterReceiver();
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        getReactApplicationContext().addLifecycleEventListener(this);
    }

    @ReactMethod
    public void getCurrentConnectivity(Promise promise) {
        if (this.mNoNetworkPermission) {
            promise.reject(ERROR_MISSING_PERMISSION, MISSING_PERMISSION_MESSAGE);
        } else {
            promise.resolve(createConnectivityEventMap());
        }
    }

    @ReactMethod
    public void isConnectionMetered(Promise promise) {
        if (this.mNoNetworkPermission) {
            promise.reject(ERROR_MISSING_PERMISSION, MISSING_PERMISSION_MESSAGE);
        } else {
            promise.resolve(Boolean.valueOf(android.support.v4.c.a.a(this.mConnectivityManager)));
        }
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        getReactApplicationContext().registerReceiver(this.mConnectivityBroadcastReceiver, intentFilter);
        this.mConnectivityBroadcastReceiver.a(true);
        updateAndSendConnectionType();
    }

    private void unregisterReceiver() {
        if (this.mConnectivityBroadcastReceiver.a()) {
            getReactApplicationContext().unregisterReceiver(this.mConnectivityBroadcastReceiver);
            this.mConnectivityBroadcastReceiver.a(false);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateAndSendConnectionType() {
        String str;
        String currentConnectionType;
        String str2 = "unknown";
        try {
            NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isConnected()) {
                    switch (activeNetworkInfo.getType()) {
                        case 0:
                        case 4:
                            str2 = getEffectiveConnectionType(activeNetworkInfo);
                            str = CONNECTION_TYPE_CELLULAR;
                            break;
                        case 1:
                            str = CONNECTION_TYPE_WIFI;
                            break;
                        case 2:
                        case 3:
                        case 5:
                        case 8:
                        default:
                            str = "unknown";
                            break;
                        case 6:
                            str = CONNECTION_TYPE_WIMAX;
                            break;
                        case 7:
                            str = CONNECTION_TYPE_BLUETOOTH;
                            break;
                        case 9:
                            str = CONNECTION_TYPE_ETHERNET;
                            break;
                    }
                    currentConnectionType = getCurrentConnectionType();
                    if (str.equalsIgnoreCase(this.mConnectionType) || !str2.equalsIgnoreCase(this.mEffectiveConnectionType) || !currentConnectionType.equalsIgnoreCase(this.mConnectivityDeprecated)) {
                        this.mConnectionType = str;
                        this.mEffectiveConnectionType = str2;
                        this.mConnectivityDeprecated = currentConnectionType;
                        sendConnectivityChangedEvent();
                    }
                    return;
                }
            }
            str = CONNECTION_TYPE_NONE;
        } catch (SecurityException unused) {
            this.mNoNetworkPermission = true;
            str = "unknown";
        }
        currentConnectionType = getCurrentConnectionType();
        if (str.equalsIgnoreCase(this.mConnectionType)) {
        }
        this.mConnectionType = str;
        this.mEffectiveConnectionType = str2;
        this.mConnectivityDeprecated = currentConnectionType;
        sendConnectivityChangedEvent();
    }

    private String getCurrentConnectionType() {
        try {
            NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return CONNECTION_TYPE_NONE_DEPRECATED;
            }
            if (!activeNetworkInfo.isConnected()) {
                return CONNECTION_TYPE_NONE_DEPRECATED;
            }
            return ConnectivityManager.isNetworkTypeValid(activeNetworkInfo.getType()) ? activeNetworkInfo.getTypeName().toUpperCase() : CONNECTION_TYPE_UNKNOWN_DEPRECATED;
        } catch (SecurityException unused) {
            this.mNoNetworkPermission = true;
            return CONNECTION_TYPE_UNKNOWN_DEPRECATED;
        }
    }

    private String getEffectiveConnectionType(NetworkInfo networkInfo) {
        switch (networkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return EFFECTIVE_CONNECTION_TYPE_2G;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
                return EFFECTIVE_CONNECTION_TYPE_3G;
            case 13:
            case 15:
                return EFFECTIVE_CONNECTION_TYPE_4G;
            default:
                return "unknown";
        }
    }

    private void sendConnectivityChangedEvent() {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("networkStatusDidChange", createConnectivityEventMap());
    }

    private WritableMap createConnectivityEventMap() {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("network_info", this.mConnectivityDeprecated);
        writableNativeMap.putString("connectionType", this.mConnectionType);
        writableNativeMap.putString("effectiveConnectionType", this.mEffectiveConnectionType);
        return writableNativeMap;
    }

    /* access modifiers changed from: private */
    public class a extends BroadcastReceiver {

        /* renamed from: b  reason: collision with root package name */
        private boolean f2910b;

        private a() {
            this.f2910b = false;
        }

        public void a(boolean z) {
            this.f2910b = z;
        }

        public boolean a() {
            return this.f2910b;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                NetInfoModule.this.updateAndSendConnectionType();
            }
        }
    }
}
