package com.google.maps.android.a.a;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.a.a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: GeoJsonPolygon */
public class k implements a {

    /* renamed from: a  reason: collision with root package name */
    private final List<? extends List<LatLng>> f3978a;

    public String e() {
        return "Polygon";
    }

    public List<? extends List<LatLng>> f() {
        return this.f3978a;
    }

    /* renamed from: g */
    public List<? extends List<LatLng>> d() {
        return f();
    }

    @Override // com.google.maps.android.a.c
    public String c() {
        return e();
    }

    /* renamed from: h */
    public ArrayList<LatLng> a() {
        return (ArrayList) f().get(0);
    }

    /* renamed from: i */
    public ArrayList<ArrayList<LatLng>> b() {
        ArrayList<ArrayList<LatLng>> arrayList = new ArrayList<>();
        for (int i = 1; i < f().size(); i++) {
            arrayList.add((ArrayList) f().get(i));
        }
        return arrayList;
    }

    public String toString() {
        return "Polygon" + "{" + "\n coordinates=" + this.f3978a + "\n}\n";
    }
}
