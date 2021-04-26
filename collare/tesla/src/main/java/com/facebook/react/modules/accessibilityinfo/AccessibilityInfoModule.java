package com.facebook.react.modules.accessibilityinfo;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.accessibility.AccessibilityManager;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.modules.core.DeviceEventManagerModule;

@com.facebook.react.module.a.a(a = AccessibilityInfoModule.NAME)
public class AccessibilityInfoModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    private static final String EVENT_NAME = "touchExplorationDidChange";
    public static final String NAME = "AccessibilityInfo";
    private AccessibilityManager mAccessibilityManager;
    private boolean mEnabled = false;
    private a mTouchExplorationStateChangeListener;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @TargetApi(19)
    private class a implements AccessibilityManager.TouchExplorationStateChangeListener {
        private a() {
        }

        public void onTouchExplorationStateChanged(boolean z) {
            AccessibilityInfoModule.this.updateAndSendChangeEvent(z);
        }
    }

    public AccessibilityInfoModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mAccessibilityManager = (AccessibilityManager) reactApplicationContext.getApplicationContext().getSystemService("accessibility");
        this.mEnabled = this.mAccessibilityManager.isTouchExplorationEnabled();
        if (Build.VERSION.SDK_INT >= 19) {
            this.mTouchExplorationStateChangeListener = new a();
        }
    }

    @ReactMethod
    public void isTouchExplorationEnabled(Callback callback) {
        callback.invoke(Boolean.valueOf(this.mEnabled));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateAndSendChangeEvent(boolean z) {
        if (this.mEnabled != z) {
            this.mEnabled = z;
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(EVENT_NAME, Boolean.valueOf(this.mEnabled));
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        if (Build.VERSION.SDK_INT >= 19) {
            this.mAccessibilityManager.addTouchExplorationStateChangeListener(this.mTouchExplorationStateChangeListener);
        }
        updateAndSendChangeEvent(this.mAccessibilityManager.isTouchExplorationEnabled());
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        if (Build.VERSION.SDK_INT >= 19) {
            this.mAccessibilityManager.removeTouchExplorationStateChangeListener(this.mTouchExplorationStateChangeListener);
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        getReactApplicationContext().addLifecycleEventListener(this);
        updateAndSendChangeEvent(this.mAccessibilityManager.isTouchExplorationEnabled());
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        getReactApplicationContext().removeLifecycleEventListener(this);
    }
}
