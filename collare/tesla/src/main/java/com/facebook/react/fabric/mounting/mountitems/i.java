package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.fabric.mounting.c;

/* compiled from: UpdateLayoutMountItem */
public class i implements e {

    /* renamed from: a  reason: collision with root package name */
    private final int f2732a;

    /* renamed from: b  reason: collision with root package name */
    private final int f2733b;

    /* renamed from: c  reason: collision with root package name */
    private final int f2734c;

    /* renamed from: d  reason: collision with root package name */
    private final int f2735d;
    private final int e;

    public i(int i, int i2, int i3, int i4, int i5) {
        this.f2732a = i;
        this.f2733b = i2;
        this.f2734c = i3;
        this.f2735d = i4;
        this.e = i5;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.e
    public void a(c cVar) {
        cVar.a(this.f2732a, this.f2733b, this.f2734c, this.f2735d, this.e);
    }

    public String toString() {
        return "UpdateLayoutMountItem [" + this.f2732a + "] - x: " + this.f2733b + " - y: " + this.f2734c + " - height: " + this.e + " - width: " + this.f2735d;
    }
}
