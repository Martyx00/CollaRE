package com.facebook.react.fabric.jsi;

import android.annotation.SuppressLint;
import com.facebook.jni.HybridData;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.events.a;

@SuppressLint({"MissingNativeLoadLibrary"})
public class EventBeatManager implements a {

    /* renamed from: a  reason: collision with root package name */
    private final ReactApplicationContext f2696a;
    @com.facebook.j.a.a
    private final HybridData mHybridData;

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private native void beat();

    private static native HybridData initHybrid(long j);

    static {
        a.a();
    }

    @Override // com.facebook.react.uimanager.events.a
    public void a() {
        b();
    }

    private void b() {
        if (this.f2696a.isOnJSQueueThread()) {
            beat();
        } else {
            this.f2696a.runOnJSQueueThread(new Runnable() {
                /* class com.facebook.react.fabric.jsi.EventBeatManager.AnonymousClass1 */

                public void run() {
                    EventBeatManager.this.beat();
                }
            });
        }
    }
}
