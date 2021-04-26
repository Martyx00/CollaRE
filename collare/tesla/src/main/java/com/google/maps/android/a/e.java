package com.google.maps.android.a;

import com.google.android.gms.maps.model.LatLng;
import java.util.List;

/* compiled from: LineString */
public class e implements c<List<LatLng>> {

    /* renamed from: a  reason: collision with root package name */
    private final List<LatLng> f4009a;

    @Override // com.google.maps.android.a.c
    public String c() {
        return "LineString";
    }

    public e(List<LatLng> list) {
        if (list != null) {
            this.f4009a = list;
            return;
        }
        throw new IllegalArgumentException("Coordinates cannot be null");
    }

    /* renamed from: a */
    public List<LatLng> d() {
        return this.f4009a;
    }

    public String toString() {
        return "LineString" + "{" + "\n coordinates=" + this.f4009a + "\n}\n";
    }
}
