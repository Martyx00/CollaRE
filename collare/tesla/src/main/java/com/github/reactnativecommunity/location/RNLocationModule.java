package com.github.reactnativecommunity.location;

import android.app.Activity;
import android.content.Intent;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

public class RNLocationModule extends ReactContextBaseJavaModule {
    private ActivityEventListener activityEventListener = new BaseActivityEventListener() {
        /* class com.github.reactnativecommunity.location.RNLocationModule.AnonymousClass1 */

        @Override // com.facebook.react.bridge.BaseActivityEventListener, com.facebook.react.bridge.ActivityEventListener
        public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
            if (RNLocationModule.this.locationProvider instanceof c) {
                ((c) RNLocationModule.this.locationProvider).a(i, i2, intent);
            }
        }
    };
    private b locationProvider;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNLocation";
    }

    public RNLocationModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        reactApplicationContext.addActivityEventListener(this.activityEventListener);
    }

    @ReactMethod
    public void configure(ReadableMap readableMap, Promise promise) {
        if (readableMap.hasKey("androidProvider")) {
            String string = readableMap.getString("androidProvider");
            char c2 = 65535;
            int hashCode = string.hashCode();
            if (hashCode != 3005871) {
                if (hashCode != 678398898) {
                    if (hashCode == 1312628413 && string.equals("standard")) {
                        c2 = 2;
                    }
                } else if (string.equals("playServices")) {
                    c2 = 1;
                }
            } else if (string.equals("auto")) {
                c2 = 0;
            }
            switch (c2) {
                case 0:
                    this.locationProvider = createDefaultLocationProvider();
                    break;
                case 1:
                    this.locationProvider = createPlayServicesLocationProvider();
                    break;
                case 2:
                    this.locationProvider = createStandardLocationProvider();
                    break;
                default:
                    ReactApplicationContext reactApplicationContext = getReactApplicationContext();
                    e.a(reactApplicationContext, "androidProvider was passed an unknown value: " + string, "401");
                    break;
            }
        } else if (this.locationProvider == null) {
            this.locationProvider = createDefaultLocationProvider();
        }
        this.locationProvider.a(getCurrentActivity(), readableMap, promise);
    }

    @ReactMethod
    public void startUpdatingLocation() {
        if (this.locationProvider == null) {
            this.locationProvider = createDefaultLocationProvider();
        }
        this.locationProvider.a();
    }

    @ReactMethod
    public void stopUpdatingLocation() {
        if (this.locationProvider == null) {
            this.locationProvider = createDefaultLocationProvider();
        }
        this.locationProvider.b();
    }

    private b createDefaultLocationProvider() {
        if (e.a()) {
            return createPlayServicesLocationProvider();
        }
        return createStandardLocationProvider();
    }

    private c createPlayServicesLocationProvider() {
        return new c(getCurrentActivity(), getReactApplicationContext());
    }

    private d createStandardLocationProvider() {
        return new d(getReactApplicationContext());
    }
}
