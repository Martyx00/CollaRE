package com.facebook.react.bridge;

import com.facebook.react.bridge.JSIModule;

public interface JSIModuleSpec<T extends JSIModule> {
    Class<? extends JSIModule> getJSIModuleClass();

    JSIModuleProvider<T> getJSIModuleProvider();
}
