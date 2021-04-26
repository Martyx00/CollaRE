package com.facebook.react.bridge;

import com.facebook.j.a.a;
import com.facebook.jni.HybridData;

@a
public abstract class NativeArray implements NativeArrayInterface {
    @a
    private HybridData mHybridData;

    @Override // com.facebook.react.bridge.NativeArrayInterface
    public native String toString();

    static {
        ReactBridge.staticInit();
    }

    protected NativeArray(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
