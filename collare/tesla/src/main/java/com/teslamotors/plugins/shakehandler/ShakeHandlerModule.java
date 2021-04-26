package com.teslamotors.plugins.shakehandler;

import android.hardware.SensorManager;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.common.f;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class ShakeHandlerModule extends ReactContextBaseJavaModule {
    private static final String REACT_CLASS = "TMShakeHandler";
    private static final String SHAKE_EVENT_IDENTIFIER = "TMShakeEventIdentifier";
    private a mShakeDetector = new a(new a());

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    private class a implements f.a {
        private a() {
        }

        @Override // com.facebook.react.common.f.a
        public void a() {
            if (ShakeHandlerModule.this.getReactApplicationContext().hasActiveCatalystInstance()) {
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) ShakeHandlerModule.this.getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(ShakeHandlerModule.SHAKE_EVENT_IDENTIFIER, null);
            }
        }
    }

    public ShakeHandlerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        a aVar = this.mShakeDetector;
        if (aVar != null) {
            aVar.a();
            this.mShakeDetector = null;
        }
    }

    @ReactMethod
    public void startMonitoringDeviceMotion() {
        a aVar = this.mShakeDetector;
        if (aVar != null) {
            aVar.a((SensorManager) getReactApplicationContext().getSystemService("sensor"));
        }
    }

    @ReactMethod
    public void stopMonitoringDeviceMotion() {
        a aVar = this.mShakeDetector;
        if (aVar != null) {
            aVar.a();
        }
    }
}
