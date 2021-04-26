package com.facebook.react.bridge;

public interface LifecycleEventListener {
    void onHostDestroy();

    void onHostPause();

    void onHostResume();
}
