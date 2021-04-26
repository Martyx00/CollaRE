package com.facebook.react.jscexecutor;

import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.WritableNativeMap;

/* compiled from: JSCExecutorFactory */
public class a implements JavaScriptExecutorFactory {

    /* renamed from: a  reason: collision with root package name */
    private final String f2752a;

    /* renamed from: b  reason: collision with root package name */
    private final String f2753b;

    public String toString() {
        return "JSIExecutor+JSCRuntime";
    }

    public a(String str, String str2) {
        this.f2752a = str;
        this.f2753b = str2;
    }

    @Override // com.facebook.react.bridge.JavaScriptExecutorFactory
    public JavaScriptExecutor create() {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("OwnerIdentity", "ReactNative");
        writableNativeMap.putString("AppIdentity", this.f2752a);
        writableNativeMap.putString("DeviceIdentity", this.f2753b);
        return new JSCExecutor(writableNativeMap);
    }
}
