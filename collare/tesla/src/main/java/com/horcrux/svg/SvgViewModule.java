package com.horcrux.svg;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

class SvgViewModule extends ReactContextBaseJavaModule {
    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNSVGSvgViewManager";
    }

    SvgViewModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void toDataURL(int i, Callback callback) {
        SvgViewShadowNode shadowNodeByTag = SvgViewManager.getShadowNodeByTag(i);
        if (shadowNodeByTag != null) {
            callback.invoke(shadowNodeByTag.toDataURL());
        }
    }
}
