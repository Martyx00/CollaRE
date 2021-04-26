package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.fabric.mounting.c;

/* compiled from: InsertMountItem */
public class d implements e {

    /* renamed from: a  reason: collision with root package name */
    private int f2721a;

    /* renamed from: b  reason: collision with root package name */
    private int f2722b;

    /* renamed from: c  reason: collision with root package name */
    private int f2723c;

    public d(int i, int i2, int i3) {
        this.f2721a = i;
        this.f2722b = i2;
        this.f2723c = i3;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.e
    public void a(c cVar) {
        cVar.a(this.f2722b, this.f2721a, this.f2723c);
    }

    public String toString() {
        return "InsertMountItem [" + this.f2721a + "] - parentTag: " + this.f2722b + " - index: " + this.f2723c;
    }
}
