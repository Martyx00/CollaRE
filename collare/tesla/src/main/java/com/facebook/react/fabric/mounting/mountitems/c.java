package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.UiThreadUtil;

/* compiled from: DispatchCommandMountItem */
public class c implements e {

    /* renamed from: a  reason: collision with root package name */
    private final int f2718a;

    /* renamed from: b  reason: collision with root package name */
    private final int f2719b;

    /* renamed from: c  reason: collision with root package name */
    private final ReadableArray f2720c;

    public c(int i, int i2, ReadableArray readableArray) {
        this.f2718a = i;
        this.f2719b = i2;
        this.f2720c = readableArray;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.e
    public void a(com.facebook.react.fabric.mounting.c cVar) {
        UiThreadUtil.assertOnUiThread();
        cVar.a(this.f2718a, this.f2719b, this.f2720c);
    }

    public String toString() {
        return "DispatchCommandMountItem [" + this.f2718a + "] " + this.f2719b;
    }
}
