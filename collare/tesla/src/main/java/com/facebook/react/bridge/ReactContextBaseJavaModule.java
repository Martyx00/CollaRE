package com.facebook.react.bridge;

import android.app.Activity;

public abstract class ReactContextBaseJavaModule extends BaseJavaModule {
    private final ReactApplicationContext mReactApplicationContext;

    public ReactContextBaseJavaModule(ReactApplicationContext reactApplicationContext) {
        this.mReactApplicationContext = reactApplicationContext;
    }

    /* access modifiers changed from: protected */
    public final ReactApplicationContext getReactApplicationContext() {
        return this.mReactApplicationContext;
    }

    /* access modifiers changed from: protected */
    public final Activity getCurrentActivity() {
        return this.mReactApplicationContext.getCurrentActivity();
    }
}
