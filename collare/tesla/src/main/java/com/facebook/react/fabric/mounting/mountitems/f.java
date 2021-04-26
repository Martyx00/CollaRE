package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.fabric.mounting.c;
import com.facebook.react.uimanager.af;

/* compiled from: PreAllocateViewMountItem */
public class f implements e {

    /* renamed from: a  reason: collision with root package name */
    private final String f2724a;

    /* renamed from: b  reason: collision with root package name */
    private final int f2725b;

    /* renamed from: c  reason: collision with root package name */
    private final af f2726c;

    public f(af afVar, int i, String str) {
        this.f2726c = afVar;
        this.f2724a = str;
        this.f2725b = i;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.e
    public void a(c cVar) {
        cVar.a(this.f2726c, this.f2724a);
    }

    public String toString() {
        return "[" + this.f2725b + "] - Preallocate " + this.f2724a;
    }
}
