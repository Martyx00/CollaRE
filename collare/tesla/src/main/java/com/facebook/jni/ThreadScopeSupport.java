package com.facebook.jni;

import com.facebook.j.a.a;
import com.facebook.soloader.SoLoader;

@a
public class ThreadScopeSupport {
    private static native void runStdFunctionImpl(long j);

    static {
        SoLoader.a("fb");
    }

    @a
    private static void runStdFunction(long j) {
        runStdFunctionImpl(j);
    }
}
