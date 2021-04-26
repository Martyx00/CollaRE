package com.teslamotors.plugins.proximitysensornotifier;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class ProximitySensorNotifierModule extends ReactContextBaseJavaModule implements SensorEventListener {
    private static final String PROXIMITY_SENSOR_NEAR = "near";
    private static final String REACT_CLASS = "TMProximitySensorNotifier";
    private static final String TAG = "ProximitySensorNotifierModule";
    private Sensor mProximitySensor = this.mSensorManager.getDefaultSensor(8);
    private SensorManager mSensorManager;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public ProximitySensorNotifierModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mSensorManager = (SensorManager) reactApplicationContext.getSystemService("sensor");
    }

    @ReactMethod
    public void setProximitySensorMonitoringEnabled(Boolean bool, Promise promise) {
        if (bool.booleanValue()) {
            this.mSensorManager.registerListener(this, this.mProximitySensor, 0);
        } else {
            this.mSensorManager.unregisterListener(this);
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        boolean z = false;
        float f = sensorEvent.values[0];
        if (f != this.mProximitySensor.getMaximumRange() && f <= 3.0f) {
            z = true;
        }
        sendProximitySensorStateChangedEvent(z);
    }

    private void sendProximitySensorStateChangedEvent(boolean z) {
        if (getReactApplicationContext().hasActiveCatalystInstance()) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("proximitySensorStateChanged", createProximitySensorStateMap(z));
        }
    }

    private WritableMap createProximitySensorStateMap(boolean z) {
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean(PROXIMITY_SENSOR_NEAR, z);
        return createMap;
    }
}
