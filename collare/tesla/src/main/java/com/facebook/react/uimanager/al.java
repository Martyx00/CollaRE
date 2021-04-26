package com.facebook.react.uimanager;

import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UIManager;

/* compiled from: UIManagerHelper */
public class al {
    public static UIManager a(ReactContext reactContext, int i) {
        CatalystInstance catalystInstance = reactContext.getCatalystInstance();
        if (i == 2) {
            return (UIManager) catalystInstance.getJSIModule(UIManager.class);
        }
        return (UIManager) catalystInstance.getNativeModule(UIManagerModule.class);
    }
}
