package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.fabric.mounting.c;
import com.facebook.react.uimanager.af;

/* compiled from: CreateMountItem */
public class a implements e {

    /* renamed from: a  reason: collision with root package name */
    private final String f2713a;

    /* renamed from: b  reason: collision with root package name */
    private final int f2714b;

    /* renamed from: c  reason: collision with root package name */
    private final af f2715c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f2716d;

    public a(af afVar, String str, int i, boolean z) {
        this.f2714b = i;
        this.f2715c = afVar;
        this.f2713a = str;
        this.f2716d = z;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.e
    public void a(c cVar) {
        cVar.a(this.f2715c, this.f2713a, this.f2714b, this.f2716d);
    }

    public String toString() {
        return "CreateMountItem [" + this.f2714b + "] " + this.f2713a;
    }
}
