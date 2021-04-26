package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import java.nio.channels.ReadableByteChannel;

public class NativeDeltaClient {
    private final HybridData mHybridData = initHybrid();

    private static native HybridData initHybrid();

    public native void processDelta(ReadableByteChannel readableByteChannel);

    public native void reset();

    static {
        ReactBridge.staticInit();
    }
}
