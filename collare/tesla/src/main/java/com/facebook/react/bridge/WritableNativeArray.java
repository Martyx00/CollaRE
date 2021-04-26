package com.facebook.react.bridge;

import com.facebook.j.a.a;
import com.facebook.jni.HybridData;

@a
public class WritableNativeArray extends ReadableNativeArray implements WritableArray {
    private static native HybridData initHybrid();

    private native void pushNativeArray(WritableNativeArray writableNativeArray);

    private native void pushNativeMap(WritableNativeMap writableNativeMap);

    @Override // com.facebook.react.bridge.WritableArray
    public native void pushBoolean(boolean z);

    @Override // com.facebook.react.bridge.WritableArray
    public native void pushDouble(double d2);

    @Override // com.facebook.react.bridge.WritableArray
    public native void pushInt(int i);

    @Override // com.facebook.react.bridge.WritableArray
    public native void pushNull();

    @Override // com.facebook.react.bridge.WritableArray
    public native void pushString(String str);

    static {
        ReactBridge.staticInit();
    }

    public WritableNativeArray() {
        super(initHybrid());
    }

    @Override // com.facebook.react.bridge.WritableArray
    public void pushArray(WritableArray writableArray) {
        com.facebook.i.a.a.a(writableArray == null || (writableArray instanceof WritableNativeArray), "Illegal type provided");
        pushNativeArray((WritableNativeArray) writableArray);
    }

    @Override // com.facebook.react.bridge.WritableArray
    public void pushMap(WritableMap writableMap) {
        com.facebook.i.a.a.a(writableMap == null || (writableMap instanceof WritableNativeMap), "Illegal type provided");
        pushNativeMap((WritableNativeMap) writableMap);
    }
}
