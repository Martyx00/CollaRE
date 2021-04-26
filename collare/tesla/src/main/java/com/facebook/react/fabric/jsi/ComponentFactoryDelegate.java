package com.facebook.react.fabric.jsi;

import com.facebook.j.a.a;
import com.facebook.jni.HybridData;

@a
public class ComponentFactoryDelegate {
    @a
    private final HybridData mHybridData = initHybrid();

    @a
    private static native HybridData initHybrid();

    static {
        a.a();
    }
}
