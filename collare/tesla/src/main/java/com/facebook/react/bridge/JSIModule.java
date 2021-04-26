package com.facebook.react.bridge;

public interface JSIModule {
    void initialize();

    void onCatalystInstanceDestroy();
}
