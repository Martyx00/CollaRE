package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.fabric.jsi.EventEmitterWrapper;
import com.facebook.react.fabric.mounting.c;

/* compiled from: UpdateEventEmitterMountItem */
public class h implements e {

    /* renamed from: a  reason: collision with root package name */
    private final EventEmitterWrapper f2730a;

    /* renamed from: b  reason: collision with root package name */
    private final int f2731b;

    public h(int i, EventEmitterWrapper eventEmitterWrapper) {
        this.f2731b = i;
        this.f2730a = eventEmitterWrapper;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.e
    public void a(c cVar) {
        cVar.a(this.f2731b, this.f2730a);
    }

    public String toString() {
        return "UpdateEventEmitterMountItem [" + this.f2731b + "]";
    }
}
