package com.facebook.react.common;

import com.facebook.i.a.a;

/* compiled from: SingleThreadAsserter */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private Thread f2616a = null;

    public void a() {
        Thread currentThread = Thread.currentThread();
        if (this.f2616a == null) {
            this.f2616a = currentThread;
        }
        a.a(this.f2616a == currentThread);
    }
}
