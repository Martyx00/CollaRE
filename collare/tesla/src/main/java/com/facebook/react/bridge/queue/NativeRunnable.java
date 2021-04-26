package com.facebook.react.bridge.queue;

import com.facebook.j.a.a;
import com.facebook.jni.HybridData;

@a
public class NativeRunnable implements Runnable {
    private final HybridData mHybridData;

    public native void run();

    @a
    private NativeRunnable(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
