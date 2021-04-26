package com.facebook.react.bridge;

import com.facebook.react.bridge.JSIModule;

public interface JSIModuleProvider<T extends JSIModule> {
    T get();
}
