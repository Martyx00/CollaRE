package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.fabric.mounting.c;

/* compiled from: RemoveMountItem */
public class g implements e {

    /* renamed from: a  reason: collision with root package name */
    private int f2727a;

    /* renamed from: b  reason: collision with root package name */
    private int f2728b;

    /* renamed from: c  reason: collision with root package name */
    private int f2729c;

    public g(int i, int i2, int i3) {
        this.f2727a = i;
        this.f2728b = i2;
        this.f2729c = i3;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.e
    public void a(c cVar) {
        cVar.a(this.f2728b, this.f2729c);
    }

    public String toString() {
        return "RemoveMountItem [" + this.f2727a + "] - parentTag: " + this.f2728b + " - index: " + this.f2729c;
    }
}
