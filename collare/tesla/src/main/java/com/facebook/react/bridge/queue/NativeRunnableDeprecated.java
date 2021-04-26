package com.facebook.react.bridge.queue;

import com.facebook.j.a.a;
import com.facebook.jni.Countable;

@a
public class NativeRunnableDeprecated extends Countable implements Runnable {
    public native void run();

    @a
    private NativeRunnableDeprecated() {
    }
}
