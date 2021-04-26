package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.fabric.mounting.c;

/* compiled from: DeleteMountItem */
public class b implements e {

    /* renamed from: a  reason: collision with root package name */
    private int f2717a;

    public b(int i) {
        this.f2717a = i;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.e
    public void a(c cVar) {
        cVar.b(this.f2717a);
    }

    public String toString() {
        return "DeleteMountItem [" + this.f2717a + "]";
    }
}
