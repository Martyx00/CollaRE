package com.facebook.react.modules.core;

import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.c;
import com.facebook.react.devsupport.a.b;
import com.facebook.react.module.a.a;

@a(a = ExceptionsManagerModule.NAME)
public class ExceptionsManagerModule extends BaseJavaModule {
    public static final String NAME = "ExceptionsManager";
    private final b mDevSupportManager;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public ExceptionsManagerModule(b bVar) {
        this.mDevSupportManager = bVar;
    }

    @ReactMethod
    public void reportFatalException(String str, ReadableArray readableArray, int i) {
        showOrThrowError(str, readableArray, i);
    }

    @ReactMethod
    public void reportSoftException(String str, ReadableArray readableArray, int i) {
        if (this.mDevSupportManager.d()) {
            this.mDevSupportManager.a(str, readableArray, i);
        } else {
            com.facebook.common.e.a.d("ReactNative", com.facebook.react.g.a.a(str, readableArray));
        }
    }

    private void showOrThrowError(String str, ReadableArray readableArray, int i) {
        if (this.mDevSupportManager.d()) {
            this.mDevSupportManager.a(str, readableArray, i);
            return;
        }
        throw new c(com.facebook.react.g.a.a(str, readableArray));
    }

    @ReactMethod
    public void updateExceptionMessage(String str, ReadableArray readableArray, int i) {
        if (this.mDevSupportManager.d()) {
            this.mDevSupportManager.b(str, readableArray, i);
        }
    }

    @ReactMethod
    public void dismissRedbox() {
        if (this.mDevSupportManager.d()) {
            this.mDevSupportManager.a();
        }
    }
}
