package com.facebook.react.fabric.jsi;

import android.annotation.SuppressLint;
import com.facebook.j.a.a;
import com.facebook.jni.HybridData;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.queue.MessageQueueThread;

@SuppressLint({"MissingNativeLoadLibrary"})
@a
public class Binding {
    @a
    private final HybridData mHybridData = initHybrid();

    private static native HybridData initHybrid();

    private native void installFabricUIManager(long j, Object obj, EventBeatManager eventBeatManager, MessageQueueThread messageQueueThread, ComponentFactoryDelegate componentFactoryDelegate, Object obj2);

    private native void uninstallFabricUIManager();

    public native void renderTemplateToSurface(int i, String str);

    public native void setConstraints(int i, float f, float f2, float f3, float f4);

    public native void setPixelDensity(float f);

    public native void startSurface(int i, NativeMap nativeMap);

    public native void stopSurface(int i);

    static {
        a.a();
    }

    public void a() {
        uninstallFabricUIManager();
    }
}
