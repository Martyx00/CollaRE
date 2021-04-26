package com.reactnativecommunity.netinfo;

import android.os.Build;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class NetInfoModule extends ReactContextBaseJavaModule {
    public static final String NAME = "RNCNetInfo";
    private final b mConnectivityReceiver;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public NetInfoModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        if (Build.VERSION.SDK_INT >= 24) {
            this.mConnectivityReceiver = new d(reactApplicationContext);
        } else {
            this.mConnectivityReceiver = new a(reactApplicationContext);
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        this.mConnectivityReceiver.a();
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        this.mConnectivityReceiver.b();
    }

    @ReactMethod
    public void getCurrentState(Promise promise) {
        this.mConnectivityReceiver.a(promise);
    }
}
