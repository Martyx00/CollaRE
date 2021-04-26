package com.facebook.react.bridge;

import com.facebook.j.a.a;
import com.facebook.jni.HybridData;

@a
class JSCJavaScriptExecutor extends JavaScriptExecutor {
    private static native HybridData initHybrid(ReadableNativeMap readableNativeMap);

    @Override // com.facebook.react.bridge.JavaScriptExecutor
    public String getName() {
        return "JSCJavaScriptExecutor";
    }

    static {
        ReactBridge.staticInit();
    }

    JSCJavaScriptExecutor(ReadableNativeMap readableNativeMap) {
        super(initHybrid(readableNativeMap));
    }
}
