package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.fabric.mounting.c;

/* compiled from: UpdatePropsMountItem */
public class k implements e {

    /* renamed from: a  reason: collision with root package name */
    private final int f2738a;

    /* renamed from: b  reason: collision with root package name */
    private final ReadableMap f2739b;

    public k(int i, ReadableMap readableMap) {
        this.f2738a = i;
        this.f2739b = readableMap;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.e
    public void a(c cVar) {
        cVar.a(this.f2738a, this.f2739b);
    }

    public String toString() {
        return "UpdatePropsMountItem [" + this.f2738a + "] - props: " + this.f2739b;
    }
}
