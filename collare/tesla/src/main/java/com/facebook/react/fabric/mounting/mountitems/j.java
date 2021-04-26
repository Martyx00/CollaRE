package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.fabric.mounting.c;

/* compiled from: UpdateLocalDataMountItem */
public class j implements e {

    /* renamed from: a  reason: collision with root package name */
    private final int f2736a;

    /* renamed from: b  reason: collision with root package name */
    private final ReadableMap f2737b;

    public j(int i, ReadableNativeMap readableNativeMap) {
        this.f2736a = i;
        this.f2737b = readableNativeMap;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.e
    public void a(c cVar) {
        cVar.b(this.f2736a, this.f2737b);
    }

    public String toString() {
        return "UpdateLocalDataMountItem [" + this.f2736a + "] - localData: " + this.f2737b;
    }
}
