package com.facebook.react.bridge;

import com.facebook.j.a.a;
import com.facebook.jni.HybridData;

@a
public abstract class JavaScriptExecutor {
    private final HybridData mHybridData;

    public abstract String getName();

    protected JavaScriptExecutor(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    public void close() {
        this.mHybridData.a();
    }
}
