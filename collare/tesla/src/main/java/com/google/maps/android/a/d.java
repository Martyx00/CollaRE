package com.google.maps.android.a;

import com.google.maps.android.a.b.b;
import com.google.maps.android.a.b.m;

/* compiled from: Layer */
public abstract class d {

    /* renamed from: a  reason: collision with root package name */
    private h f4008a;

    /* access modifiers changed from: protected */
    public void a() {
        h hVar = this.f4008a;
        if (hVar instanceof m) {
            ((m) hVar).j();
            return;
        }
        throw new UnsupportedOperationException("Stored renderer is not a KmlRenderer");
    }

    /* access modifiers changed from: protected */
    public void a(h hVar) {
        this.f4008a = hVar;
    }

    /* access modifiers changed from: protected */
    public Iterable<b> b() {
        h hVar = this.f4008a;
        if (hVar instanceof m) {
            return ((m) hVar).k();
        }
        return null;
    }
}
