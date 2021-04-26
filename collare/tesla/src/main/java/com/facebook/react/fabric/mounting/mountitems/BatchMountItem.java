package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.j.a.a;
import com.facebook.react.fabric.mounting.c;

@a
public class BatchMountItem implements e {

    /* renamed from: a  reason: collision with root package name */
    private final e[] f2711a;

    /* renamed from: b  reason: collision with root package name */
    private final int f2712b;

    public BatchMountItem(e[] eVarArr, int i) {
        if (eVarArr == null) {
            throw new NullPointerException();
        } else if (i < 0 || i > eVarArr.length) {
            throw new IllegalArgumentException("Invalid size received by parameter size: " + i + " items.size = " + eVarArr.length);
        } else {
            this.f2711a = eVarArr;
            this.f2712b = i;
        }
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.e
    public void a(c cVar) {
        com.facebook.systrace.a.a(0, "FabricUIManager::mountViews (" + this.f2712b + " items)");
        for (int i = 0; i < this.f2712b; i++) {
            this.f2711a[i].a(cVar);
        }
        com.facebook.systrace.a.b(0);
    }
}
