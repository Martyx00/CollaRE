package com.facebook.react.animated;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableMap;

/* compiled from: AnimationDriver */
abstract class d {

    /* renamed from: a  reason: collision with root package name */
    boolean f2562a = false;

    /* renamed from: b  reason: collision with root package name */
    s f2563b;

    /* renamed from: c  reason: collision with root package name */
    Callback f2564c;

    /* renamed from: d  reason: collision with root package name */
    int f2565d;

    public abstract void a(long j);

    d() {
    }

    public void a(ReadableMap readableMap) {
        throw new JSApplicationCausedNativeException("Animation config for " + getClass().getSimpleName() + " cannot be reset");
    }
}
