package com.facebook.jni;

import com.facebook.j.a.a;
import com.facebook.jni.a;
import com.facebook.soloader.SoLoader;

@a
public class HybridData {
    @a
    private Destructor mDestructor = new Destructor(this);

    static {
        SoLoader.a("fb");
    }

    public synchronized void a() {
        this.mDestructor.a();
    }

    public static class Destructor extends a.AbstractC0053a {
        @com.facebook.j.a.a
        private long mNativePointer;

        static native void deleteNative(long j);

        Destructor(Object obj) {
            super(obj);
        }

        /* access modifiers changed from: package-private */
        @Override // com.facebook.jni.a.AbstractC0053a
        public void a() {
            deleteNative(this.mNativePointer);
            this.mNativePointer = 0;
        }
    }
}
