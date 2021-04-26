package com.facebook.react.bridge;

import com.facebook.j.a.a;
import com.facebook.jni.HybridData;

@a
public class WritableNativeMap extends ReadableNativeMap implements WritableMap {
    private static native HybridData initHybrid();

    private native void mergeNativeMap(ReadableNativeMap readableNativeMap);

    private native void putNativeArray(String str, WritableNativeArray writableNativeArray);

    private native void putNativeMap(String str, WritableNativeMap writableNativeMap);

    @Override // com.facebook.react.bridge.WritableMap
    public native void putBoolean(String str, boolean z);

    @Override // com.facebook.react.bridge.WritableMap
    public native void putDouble(String str, double d2);

    @Override // com.facebook.react.bridge.WritableMap
    public native void putInt(String str, int i);

    @Override // com.facebook.react.bridge.WritableMap
    public native void putNull(String str);

    @Override // com.facebook.react.bridge.WritableMap
    public native void putString(String str, String str2);

    static {
        ReactBridge.staticInit();
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void putMap(String str, WritableMap writableMap) {
        com.facebook.i.a.a.a(writableMap == null || (writableMap instanceof WritableNativeMap), "Illegal type provided");
        putNativeMap(str, (WritableNativeMap) writableMap);
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void putArray(String str, WritableArray writableArray) {
        com.facebook.i.a.a.a(writableArray == null || (writableArray instanceof WritableNativeArray), "Illegal type provided");
        putNativeArray(str, (WritableNativeArray) writableArray);
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void merge(ReadableMap readableMap) {
        com.facebook.i.a.a.a(readableMap instanceof ReadableNativeMap, "Illegal type provided");
        mergeNativeMap((ReadableNativeMap) readableMap);
    }

    public WritableNativeMap() {
        super(initHybrid());
    }
}
