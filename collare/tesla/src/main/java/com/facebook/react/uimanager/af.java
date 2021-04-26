package com.facebook.react.uimanager;

import android.app.Activity;
import android.content.Context;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;

/* compiled from: ThemedReactContext */
public class af extends ReactContext {

    /* renamed from: a  reason: collision with root package name */
    private final ReactApplicationContext f3087a;

    public af(ReactApplicationContext reactApplicationContext, Context context) {
        super(context);
        initializeWithInstance(reactApplicationContext.getCatalystInstance());
        this.f3087a = reactApplicationContext;
    }

    @Override // com.facebook.react.bridge.ReactContext
    public void addLifecycleEventListener(LifecycleEventListener lifecycleEventListener) {
        this.f3087a.addLifecycleEventListener(lifecycleEventListener);
    }

    @Override // com.facebook.react.bridge.ReactContext
    public void removeLifecycleEventListener(LifecycleEventListener lifecycleEventListener) {
        this.f3087a.removeLifecycleEventListener(lifecycleEventListener);
    }

    @Override // com.facebook.react.bridge.ReactContext
    public boolean hasCurrentActivity() {
        return this.f3087a.hasCurrentActivity();
    }

    @Override // com.facebook.react.bridge.ReactContext
    public Activity getCurrentActivity() {
        return this.f3087a.getCurrentActivity();
    }
}
