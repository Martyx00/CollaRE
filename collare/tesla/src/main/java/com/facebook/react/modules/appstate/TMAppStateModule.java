package com.facebook.react.modules.appstate;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.teslamotors.TeslaApp.a;
import com.teslamotors.TeslaApp.b;

public class TMAppStateModule extends ReactContextBaseJavaModule implements LifecycleEventListener, a {
    protected static final String NAME = "AppState";

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
    }

    public TMAppStateModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        getReactApplicationContext().addLifecycleEventListener(this);
        b.a().a(this);
    }

    @ReactMethod
    public void getCurrentAppState(Callback callback, Callback callback2) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("app_state", b.a().b());
        callback.invoke(createMap);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        b.a().a(b.a.BACKGROUND);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        b.a().a(b.a.UNINITIALIZED);
    }

    @Override // com.teslamotors.TeslaApp.a
    public void appStateDidChange(String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("app_state", str);
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("appStateDidChange", createMap);
    }
}
