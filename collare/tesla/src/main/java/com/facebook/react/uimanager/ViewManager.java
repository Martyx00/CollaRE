package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.e.a;
import com.facebook.react.e.d;
import com.facebook.react.uimanager.w;
import com.facebook.yoga.YogaMeasureMode;
import java.util.Map;

public abstract class ViewManager<T extends View, C extends w> extends BaseJavaModule {
    /* access modifiers changed from: protected */
    public void addEventEmitters(af afVar, T t) {
    }

    /* access modifiers changed from: protected */
    public abstract T createViewInstance(af afVar);

    public Map<String, Integer> getCommandsMap() {
        return null;
    }

    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return null;
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return null;
    }

    public Map<String, Object> getExportedViewConstants() {
        return null;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public abstract String getName();

    public abstract Class<? extends C> getShadowNodeClass();

    public long measure(ReactContext reactContext, ReadableNativeMap readableNativeMap, ReadableNativeMap readableNativeMap2, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(T t) {
    }

    public void onDropViewInstance(T t) {
    }

    public void receiveCommand(T t, int i, ReadableArray readableArray) {
    }

    public abstract void updateExtraData(T t, Object obj);

    public Object updateLocalData(T t, y yVar, y yVar2) {
        return null;
    }

    public final void updateProperties(T t, y yVar) {
        as.a(this, t, yVar);
        onAfterUpdateTransaction(t);
    }

    public final T createView(af afVar, a aVar) {
        T createViewInstance = createViewInstance(afVar);
        addEventEmitters(afVar, createViewInstance);
        if (createViewInstance instanceof d) {
            ((d) createViewInstance).setOnInterceptTouchEventListener(aVar);
        }
        return createViewInstance;
    }

    public C createShadowNodeInstance() {
        throw new RuntimeException("ViewManager subclasses must implement createShadowNodeInstance()");
    }

    public C createShadowNodeInstance(ReactApplicationContext reactApplicationContext) {
        return createShadowNodeInstance();
    }

    public Map<String, String> getNativeProps() {
        return as.a((Class<? extends ViewManager>) getClass(), (Class<? extends w>) getShadowNodeClass());
    }
}
