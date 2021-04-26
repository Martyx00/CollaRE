package com.facebook.react.modules.core;

import com.facebook.react.bridge.JavaScriptModule;

public interface RCTNativeAppEventEmitter extends JavaScriptModule {
    void emit(String str, Object obj);
}
