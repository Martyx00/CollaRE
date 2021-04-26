package com.google.maps.android.a;

import com.google.android.gms.maps.model.LatLng;

/* compiled from: Point */
public class g implements c {

    /* renamed from: a  reason: collision with root package name */
    private final LatLng f4012a;

    @Override // com.google.maps.android.a.c
    public String c() {
        return "Point";
    }

    public g(LatLng latLng) {
        if (latLng != null) {
            this.f4012a = latLng;
            return;
        }
        throw new IllegalArgumentException("Coordinates cannot be null");
    }

    /* renamed from: a */
    public LatLng d() {
        return this.f4012a;
    }

    public String toString() {
        return "Point" + "{" + "\n coordinates=" + this.f4012a + "\n}\n";
    }
}
