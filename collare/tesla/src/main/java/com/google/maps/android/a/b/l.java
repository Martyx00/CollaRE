package com.google.maps.android.a.b;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.a.a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: KmlPolygon */
public class l implements a<ArrayList<ArrayList<LatLng>>> {

    /* renamed from: a  reason: collision with root package name */
    private final List<LatLng> f3997a;

    /* renamed from: b  reason: collision with root package name */
    private final List<List<LatLng>> f3998b;

    @Override // com.google.maps.android.a.c
    public String c() {
        return "Polygon";
    }

    public l(List<LatLng> list, List<List<LatLng>> list2) {
        if (list != null) {
            this.f3997a = list;
            this.f3998b = list2;
            return;
        }
        throw new IllegalArgumentException("Outer boundary coordinates cannot be null");
    }

    /* renamed from: e */
    public List<List<LatLng>> d() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.f3997a);
        List<List<LatLng>> list = this.f3998b;
        if (list != null) {
            arrayList.addAll(list);
        }
        return arrayList;
    }

    @Override // com.google.maps.android.a.a
    public List<LatLng> a() {
        return this.f3997a;
    }

    @Override // com.google.maps.android.a.a
    public List<List<LatLng>> b() {
        return this.f3998b;
    }

    public String toString() {
        return "Polygon" + "{" + "\n outer coordinates=" + this.f3997a + ",\n inner coordinates=" + this.f3998b + "\n}\n";
    }
}
