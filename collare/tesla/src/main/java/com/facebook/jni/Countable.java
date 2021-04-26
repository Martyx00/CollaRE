package com.facebook.jni;

import com.facebook.j.a.a;
import com.facebook.soloader.SoLoader;

@a
public class Countable {
    @a
    private long mInstance = 0;

    public native void dispose();

    static {
        SoLoader.a("fb");
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        dispose();
        super.finalize();
    }
}
