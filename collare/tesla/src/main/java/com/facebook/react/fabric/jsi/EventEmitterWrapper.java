package com.facebook.react.fabric.jsi;

import android.annotation.SuppressLint;
import com.facebook.j.a.a;
import com.facebook.jni.HybridData;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;

@SuppressLint({"MissingNativeLoadLibrary"})
public class EventEmitterWrapper {
    @a
    private final HybridData mHybridData = initHybrid();

    private static native HybridData initHybrid();

    private native void invokeEvent(String str, NativeMap nativeMap);

    static {
        a.a();
    }

    private EventEmitterWrapper() {
    }

    public void a(String str, WritableMap writableMap) {
        invokeEvent(str, writableMap == null ? new WritableNativeMap() : (NativeMap) writableMap);
    }
}
